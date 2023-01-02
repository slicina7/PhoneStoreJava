package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.io.FileReader;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class PhoneDaoSQLImpl extends AbstractDao<Phone> implements PhoneDao{

    public PhoneDaoSQLImpl(){super("phones");}

    @Override
    public Phone rowToObject(ResultSet rs) throws BuyerException {
        try {
            Phone phone = new Phone();
            phone.setId(rs.getInt("id"));
            phone.setBrand(DaoFactory.brandDao().getById(rs.getInt(2)));
            phone.setVersion(rs.getString("version"));
            phone.setPrice(rs.getInt("price"));
            phone.setIn_stock(rs.getInt("in_stock"));
            phone.setRelease_date(rs.getDate("release_date"));
            return phone;
        }catch (SQLException e){
            throw new BuyerException(e.getMessage(),e);
        }
    }

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

    @Override
    public List<Phone> searchByBrand(Brand brand) throws BuyerException {
        return executeQuery("SELECT * FROM phones WHERE brand_id=?",new Object[]{brand.getId()});
    }

    @Override
    public List<Phone> searchByPrice(Integer min, Integer max) throws BuyerException {
        return executeQuery("SELECT * FROM phones WHERE price BETWEEN ? AND ?",new Object[]{min,max});
    }

}
