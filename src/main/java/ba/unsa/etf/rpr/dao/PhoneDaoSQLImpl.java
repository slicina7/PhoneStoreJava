package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of the DAO
 */
public class PhoneDaoSQLImpl extends AbstractDao<Phone> implements PhoneDao{

    public PhoneDaoSQLImpl(){super("phones");}

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws BuyerException in case of error
     */
    @Override
    public Phone rowToObject(ResultSet rs) throws BuyerException {
        try {
            Phone phone = new Phone();
            phone.setId(rs.getInt("id"));
            phone.setBrand(DaoFactory.brandDao().getById(rs.getInt("brand_id")));
            phone.setVersion(rs.getString("version"));
            phone.setPrice(rs.getInt("price"));
            phone.setIn_stock(rs.getInt("in_stock"));
            phone.setRelease_date(rs.getDate("release_date"));
            return phone;
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
    public Map<String, Object> objectToRow(Phone object) {
        Map<String,Object> item=new TreeMap<>();
        item.put("id",object.getId());
        item.put("brand_id",object.getBrand().getId());
        item.put("version",object.getVersion());
        item.put("price",object.getPrice());
        item.put("in_stock",object.getIn_stock());
        item.put("release_date",object.getRelease_date());
        return item;
    }

    /**
     * Returns all phones that are given brand.
     * @param brand phone brand
     * @return List of phones
     */
    @Override
    public List<Phone> searchByBrand(Brand brand) throws BuyerException {
        return executeQuery("SELECT * FROM phones WHERE brand_id=?",new Object[]{brand.getId()});
    }

    /**
     * Search phones in database based on price range
     * @param min minimum price
     * @param max maximum price
     * @return List of phones from table
     */
    @Override
    public List<Phone> searchByPrice(Integer min, Integer max) throws BuyerException {
        return executeQuery("SELECT * FROM phones WHERE price BETWEEN ? AND ?",new Object[]{min,max});
    }
    /**
     * Search phones in database based on version
     * @param version , phone version
     * @return List of phones from table
     */
    @Override
    public List<Phone> searchByBrandAndVersion(Brand brand,String version) throws BuyerException {
        return executeQuery("SELECT * FROM phones WHERE brand_id = ? AND version = ?",new Object[]{brand.getId(),version});
    }


}
