package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandDaoSQLImpl implements BrandDao{

    private Connection connection;

    public BrandDaoSQLImpl(){
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
    public Brand getById(int id) {
        String query = "SELECT * FROM brands WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();  // rs is iterator
            if(rs.next()){
                Brand brand=new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                rs.close();
                return brand;
            }else
                return null; // no elements in rs
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Brand insert(Brand item) {
        String insert = "INSERT INTO brands(name) VALUES(?)";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.executeUpdate();

            ResultSet rs=stmt.getGeneratedKeys();;  // rs is iterator
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Brand update(Brand item) {
        String insert = "UPDATE brands SET name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM brands WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Brand> getAll() {
        String query = "SELECT * FROM brands";
        List<Brand> brands = new ArrayList<Brand>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Brand brand=new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                brands.add(brand);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return brands;
    }

}
