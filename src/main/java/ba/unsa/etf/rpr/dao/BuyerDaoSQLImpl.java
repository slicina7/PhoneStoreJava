package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerDaoSQLImpl implements BuyerDao{

    private Connection connection;

    public BuyerDaoSQLImpl(){
        try{
            this.connection= DriverManager.getConnection("url","user","password");
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
                buyer.setAccount_number(rs.getString("account_number"));
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
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id)+1 FROM buyers");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Buyer insert(Buyer item) {
        int id=getMaxId();
        String insert = "INSERT INTO buyers VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setString(2, item.getName());
            stmt.setString(3,item.getSurname());
            stmt.setString(4, item.getAccount_number());
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
        String insert = "UPDATE buyers SET name = ?,surname=? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setString(3,item.getSurname());
            stmt.setString(4,item.getAccount_number());
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
                buyer.setAccount_number(rs.getString("account_number"));
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
}
