package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;

import java.sql.*;
import java.util.List;

public class PurchaseDaoSQLImpl implements PurchaseDao {

    private Connection connection;

    public PurchaseDaoSQLImpl(){
        try{
            this.connection= DriverManager.getConnection("url","user","password");
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
        String insert = "INSERT INTO purchases VALUES(?,?)";
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
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Purchase> getAll() {
        return null;
    }

    @Override
    public List<Purchase> searchByPhone(Phone phone) {
        return null;
    }

    @Override
    public List<Purchase> searchByBuyer(Buyer buyer) {
        return null;
    }
}
