package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class BrandDaoSQLImpl extends AbstractDao<Brand> implements BrandDao{

    private Connection connection;

    public BrandDaoSQLImpl(){super("brands");}


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

    @Override
    public Map<String, Object> objectToRow(Brand object) {
        Map<String, Object> row=new TreeMap<>();
        row.put("id",object.getId());
        row.put("name",object.getName());
        return row;
    }

}
