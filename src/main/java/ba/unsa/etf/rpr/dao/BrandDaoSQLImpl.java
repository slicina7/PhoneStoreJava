package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.sql.*;
import java.util.*;
/**
 * MySQL implementation of the DAO
 */
public class BrandDaoSQLImpl extends AbstractDao<Brand> implements BrandDao{


    public BrandDaoSQLImpl(){super("brands");}

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws BuyerException in case of error
     */
    @Override
    public Brand rowToObject(ResultSet rs) throws BuyerException {
        try{
            Brand brand=new Brand();
            brand.setId(rs.getInt("id"));
            brand.setName(rs.getString("name"));
            return brand;
        }catch (SQLException e){
            throw new BuyerException(e.getMessage(),e);
        }
    }

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    @Override
    public Map<String, Object> objectToRow(Brand object) {
        Map<String, Object> row=new TreeMap<>();
        row.put("id",object.getId());
        row.put("name",object.getName());
        return row;
    }

    /**
     * Search brands in database based on name
     * @param name brand name
     * @return Brand with that name
     */
    @Override
    public List<Brand> searchByName(String name) throws BuyerException {
        return executeQuery("SELECT * FROM brands WHERE name = ?",new Object[]{name});
    }
}
