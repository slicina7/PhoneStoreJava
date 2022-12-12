package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import java.sql.*;
import java.util.List;

public class BrandDaoSQLImpl implements BrandDao{

    private Connection connection;

    public BrandDaoSQLImpl(){
        try{
            this.connection=DriverManager.getConnection("url","user","password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Brand getById(int id) {
        String query = "SELECT * FROM brand WHERE id = ?";
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
    public Brand add(Brand item) {
        String insert = "INSERT INTO brand(name) VALUES(?)";
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
        String insert = "UPDATE categories SET name = ? WHERE id = ?";
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

    }

    @Override
    public List<Brand> getAll() {
        return null;
    }

}
