package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Selma Licina
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    private static Connection connection =null;
    private String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        if(connection==null) makeConnection();
    }
    /**
     * Connecting to database
     */
    private static void makeConnection(){
        try{
            FileReader reader=new FileReader("db.properties");
            Properties p=new Properties();
            p.load(reader);
            AbstractDao.connection= DriverManager.getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));
        }catch(Exception e){
            System.out.println("It is not possible to connect to database");
            e.printStackTrace();
            System.exit(0);
        }
    }
    /**
     * Gets the connection
     * @return Connection
     */
    public static Connection getConnection() {
        return AbstractDao.connection;
    }
    /**
     * Sets the connection
     * @param connection the connection to be set.
     */
    public void setConnection(Connection connection){
        if(AbstractDao.connection!=null) {
            try {
                AbstractDao.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        AbstractDao.connection = connection;
    }

    /**
     * Removes connection
     */
    public void removeConnection(){
        if(this.connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Unable to close connection");
            }
        }
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws BuyerException in case of error
     */
    public abstract T rowToObject(ResultSet rs) throws BuyerException;

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> objectToRow(T object);

    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws BuyerException in case of error with db
     */
    public List<T> executeQuery(String query, Object[] params) throws BuyerException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(rowToObject(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new BuyerException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     * @throws BuyerException in case when object is not found
     */
    public T executeQueryUnique(String query, Object[] params) throws BuyerException{
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new BuyerException("Object not found");
        }
    }

    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - row to be converted intro string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
    public T getById(int id) throws BuyerException {
        return executeQueryUnique("SELECT * FROM "+tableName+" WHERE id = ?", new Object[]{id});
    }

    /**
     * Inserts new row in table
     */
    public T insert(T item) throws BuyerException {
        Map<String, Object> row = objectToRow(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new BuyerException(e.getMessage(), e);
        }
    }

    /**
     * Updates row in table
     */
    public T update(T item) throws BuyerException {
        Map<String, Object> row = objectToRow(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new BuyerException(e.getMessage(), e);
        }
    }

    /**
     * Deletes rows from table
     */
    public void delete(int id) throws BuyerException{
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new BuyerException(e.getMessage(), e);
        }
    }

    /**
     * @return List of all rows in table
     */
    public List<T> getAll() throws BuyerException{
        return executeQuery("SELECT * FROM "+ tableName, null);
    }
}
