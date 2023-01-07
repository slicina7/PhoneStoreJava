package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;

public class PhoneManager {
    public List<Phone> getAll() throws BuyerException{
        return DaoFactory.phoneDao().getAll();
    }
    List<Phone> searchByBrand(Brand brand) throws BuyerException {
        return DaoFactory.phoneDao().searchByBrand(brand);
    }
    List<Phone> searchByPrice(Integer min,Integer max) throws BuyerException{
        return DaoFactory.phoneDao().searchByPrice(min,max);
    }
    public void delete(int id) throws BuyerException{
        DaoFactory.phoneDao().delete(id);
    }
    public Phone getById(int id) throws BuyerException{
        return DaoFactory.phoneDao().getById(id);
    }
    public void update(Phone phone) throws  BuyerException{
        DaoFactory.phoneDao().update(phone);
    }
    public void insert(Phone phone) throws BuyerException{
        DaoFactory.phoneDao().insert(phone);
    }
}
