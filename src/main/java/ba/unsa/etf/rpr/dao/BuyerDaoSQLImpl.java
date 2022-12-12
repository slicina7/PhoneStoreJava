package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;

import java.sql.*;
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

    @Override
    public Buyer add(Buyer item) {
        return null;
    }

    @Override
    public Buyer update(Buyer item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Buyer> getAll() {
        return null;
    }
}
