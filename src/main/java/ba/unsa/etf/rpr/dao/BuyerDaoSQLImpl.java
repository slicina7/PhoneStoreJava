package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BuyerDaoSQLImpl implements BuyerDao{

    private Connection connection;

    public BuyerDaoSQLImpl(){
        try{
            FileReader reader=new FileReader("db.properties");
            Properties p=new Properties();
            p.load(reader);
            this.connection= DriverManager.getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Buyer getById(int id) {
        String query = "SELECT * FROM buyers WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();  // rs is iterator
            if(rs.next()){
                Buyer buyer=new Buyer();
                buyer.setId(rs.getInt("id"));
                buyer.setName(rs.getString("name"));
                buyer.setSurname(rs.getString("surname"));
                buyer.setEmail(rs.getString("email"));
                buyer.setPassword(rs.getInt("password"));
                buyer.setAccount_balance(rs.getInt("account_balance"));
                rs.close();
                return buyer;
            }else
                return null; // no elements in rs
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buyer insert(Buyer item) {
        String insert = "INSERT INTO buyers VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setString(2, item.getName());
            stmt.setString(3,item.getSurname());
            stmt.setString(4, item.getEmail());
            stmt.setInt(5,item.getPassword());
            stmt.setInt(6,item.getAccount_balance());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buyer update(Buyer item) {
        String insert = "UPDATE buyers SET account_balance = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);

            stmt.setInt(1,item.getAccount_balance());
            stmt.setInt(2, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM buyers WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Buyer> getAll() {
        String query = "SELECT * FROM buyers";
        List<Buyer> buyers = new ArrayList<Buyer>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Buyer buyer=new Buyer();
                buyer.setId(rs.getInt("id"));
                buyer.setName(rs.getString("name"));
                buyer.setSurname(rs.getString("surname"));
                buyer.setEmail(rs.getString("email"));
                buyer.setPassword(rs.getInt("password"));
                buyer.setAccount_balance(rs.getInt("account_balance"));
                buyers.add(buyer);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return buyers;
    }
    public void tableView() {
        String query="SELECT * FROM buyers";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                int password=rs.getInt("password");
                int balance=rs.getInt("account_balance");
                System.out.println(id+" "+name+" "+surname+" "+email+" "+password+" "+balance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
