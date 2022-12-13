package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PhoneDaoSQLImpl implements PhoneDao{
    private Connection connection;

    public PhoneDaoSQLImpl(){
        try{
            FileReader reader=new FileReader("db.properties");
            Properties p=new Properties();
            p.load(reader);
            this.connection= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/"+p.getProperty("user"),p.getProperty("user"),p.getProperty("password"));
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
        String insert = "INSERT INTO phones VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setInt(2, item.getBrand().getId());
            stmt.setString(3,item.getVersion());
            stmt.setInt(4,item.getPrice());
            stmt.setInt(5,item.getIn_stock());
            stmt.setDate(6, (Date) item.getRelease_date());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Phone update(Phone item) {
        String insert = "UPDATE phones SET brand_id=?,version=?,price=?,in_stock=?,release_date=? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1, item.getBrand().getId());
            stmt.setString(2,item.getVersion());
            stmt.setInt(3,item.getPrice());
            stmt.setInt(4,item.getIn_stock());
            stmt.setDate(5, (Date) item.getRelease_date());
            stmt.setInt(6, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM phones WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Phone> getAll() {
        String query = "SELECT * FROM phones";
        List<Phone> phones = new ArrayList<Phone>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Phone phone=new Phone();
                phone.setId(rs.getInt("id"));
                phone.setBrand(new BrandDaoSQLImpl().getById(rs.getInt(2)));
                phone.setVersion(rs.getString("version"));
                phone.setPrice(rs.getInt("price"));
                phone.setIn_stock(rs.getInt("in_stock"));
                phone.setRelease_date(rs.getDate("release_date"));
                phones.add(phone);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public List<Phone> searchByBrand(Brand brand) {
        String query="SELECT * FROM phones WHERE brand_id=?";
        List<Phone> phones = new ArrayList<Phone>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,brand.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Phone phone=new Phone();
                phone.setId(rs.getInt("id"));
                phone.setBrand(new BrandDaoSQLImpl().getById(rs.getInt(2)));
                phone.setVersion(rs.getString("version"));
                phone.setPrice(rs.getInt("price"));
                phone.setIn_stock(rs.getInt("in_stock"));
                phone.setRelease_date(rs.getDate("release_date"));
                phones.add(phone);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return phones;
    }
    public void tableView() {
        String query="SELECT * FROM phones";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int brand_id=rs.getInt("brand_id");
                String version=rs.getString("version");
                int price=rs.getInt("price");
                int in_stock=rs.getInt("in_stock");
                Date release_date=rs.getDate("release_date");
                System.out.println(id+" "+brand_id+" "+version+" "+price+" "+in_stock+" "+release_date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
