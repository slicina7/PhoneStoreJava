package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;

import java.sql.*;
import java.util.List;

public class PhoneDaoSQLImpl implements PhoneDao{
    private Connection connection;

    public PhoneDaoSQLImpl(){
        try{
            this.connection= DriverManager.getConnection("url","user","password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Phone getById(int id) {
        String query = "SELECT * FROM phones WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();  // rs is iterator
            if(rs.next()){
                Phone phone=new Phone();
                phone.setId(rs.getInt("id"));
                phone.setBrand(new BrandDaoSQLImpl().getById(rs.getInt(2)));
                phone.setVersion(rs.getString("version"));
                phone.setPrice(rs.getInt("price"));
                phone.setIn_stock(rs.getInt("in_stock"));
                phone.setRelease_date(rs.getDate("release_date"));
                rs.close();
                return phone;
            }else
                return null; // no elements in rs
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Phone insert(Phone item) {
        return null;
    }

    @Override
    public Phone update(Phone item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Phone> getAll() {
        return null;
    }

    @Override
    public List<Phone> searchByBrand(Brand brand) {
        return null;
    }
}
