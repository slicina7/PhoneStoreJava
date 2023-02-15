package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;
/**
 * MySQL implementation of DAO
 * Singleton pattern
 * @author Selma Ličina
 */
public class BuyerDaoSQLImpl extends AbstractDao<Buyer> implements BuyerDao{
    private static BuyerDaoSQLImpl instance=null;
    public BuyerDaoSQLImpl(){super("buyers");}
    public static BuyerDaoSQLImpl getInstance(){
        if(instance==null)
            instance=new BuyerDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws BuyerException in case of error
     */
    @Override
    public Buyer rowToObject(ResultSet rs) throws BuyerException {
        try{
            Buyer buyer=new Buyer();
            buyer.setId(rs.getInt("id"));
            buyer.setName(rs.getString("name"));
            buyer.setSurname(rs.getString("surname"));
            buyer.setEmail(rs.getString("email"));
            buyer.setAccount_number(rs.getString("account_number"));
            buyer.setPassword(rs.getString("password"));
            buyer.setAccount_balance(rs.getInt("account_balance"));
            return buyer;
        }catch(SQLException e){
            throw new BuyerException(e.getMessage(),e);
        }
    }

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    @Override
    public Map<String, Object> objectToRow(Buyer object) {
        Map<String,Object> item=new TreeMap<>();
        item.put("id",object.getId());
        item.put("name",object.getName());
        item.put("surname",object.getSurname());
        item.put("email",object.getEmail());
        item.put("account_number",object.getAccount_number());
        item.put("password",object.getPassword());
        item.put("account_balance",object.getAccount_balance());
        return item;
    }

    /**
     * Search buyers in database based email and password
     * @param email user email
     * @param password user password
     * @return Buyer with that email and password
     */
    @Override
    public Buyer searchByEmailAndPassword(String email,String password) throws BuyerException {
        return executeQueryUnique("SELECT * FROM buyers WHERE email = ? AND password = ?",new Object[]{email,password});
    }
}
