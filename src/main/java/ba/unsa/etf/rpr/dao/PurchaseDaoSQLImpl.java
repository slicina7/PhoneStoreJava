package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PurchaseDaoSQLImpl implements PurchaseDao {

    private Connection connection;

    public PurchaseDaoSQLImpl(){
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
    public Purchase getById(int id) {
        String query = "SELECT * FROM purchases WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();  // rs is iterator
            if(rs.next()){
                Purchase purchase=new Purchase();
                purchase.setId(rs.getInt("id"));
                purchase.setBuyer(new BuyerDaoSQLImpl().getById(rs.getInt(1)));
                purchase.setPhone(new PhoneDaoSQLImpl().getById(rs.getInt(1)));
                rs.close();
                return purchase;
            }else
                return null; // no elements in rs
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Purchase insert(Purchase item) {
        String insert = "INSERT INTO purchases VALUES(?,?,?)";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setInt(2, item.getBuyer().getId());
            stmt.setInt(3,item.getPhone().getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Purchase update(Purchase item) {
        String insert = "UPDATE purchases SET phone_id=? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1,item.getPhone().getId());
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
        String insert = "DELETE FROM purchases WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Purchase> getAll() {
        String query = "SELECT * FROM purchases";
        List<Purchase> purchases = new ArrayList<Purchase>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){                              // result set is iterator.
                Purchase purchase=new Purchase();
                purchase.setId(rs.getInt("id"));
                purchase.setBuyer(new BuyerDaoSQLImpl().getById(rs.getInt(1)));
                purchase.setPhone(new PhoneDaoSQLImpl().getById(rs.getInt(1)));
                purchases.add(purchase);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return purchases;
    }

    @Override
    public List<Purchase> searchByPhone(Phone phone) {
        String query = "SELECT * FROM purchases WHERE phone_id = ?";
        List<Purchase> purchases = new ArrayList<Purchase>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){                              // result set is iterator.
                Purchase purchase=new Purchase();
                purchase.setId(rs.getInt("id"));
                purchase.setBuyer(new BuyerDaoSQLImpl().getById(rs.getInt(1)));
                purchase.setPhone(new PhoneDaoSQLImpl().getById(rs.getInt(1)));
                purchases.add(purchase);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return purchases;
    }

    @Override
    public List<Purchase> searchByBuyer(Buyer buyer) {
        String query = "SELECT * FROM purchases WHERE buyer_id = ?";
        List<Purchase> purchases = new ArrayList<Purchase>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){                              // result set is iterator.
                Purchase purchase=new Purchase();
                purchase.setId(rs.getInt("id"));
                purchase.setBuyer(new BuyerDaoSQLImpl().getById(rs.getInt(1)));
                purchase.setPhone(new PhoneDaoSQLImpl().getById(rs.getInt(1)));
                purchases.add(purchase);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return purchases;
    }
    public void tableView() {
        String query="SELECT * FROM purchases";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int buyer=rs.getInt("buyer_id");
                int phone=rs.getInt("phone_id");
                System.out.println(id+" "+buyer+" "+phone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
