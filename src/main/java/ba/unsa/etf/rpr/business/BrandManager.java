package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;

public class BrandManager {
    public List<Brand> getAll() throws BuyerException {
        return DaoFactory.brandDao().getAll();
    }

    public void delete(int id) throws BuyerException{
        DaoFactory.brandDao().delete(id);
    }
    public Brand getById(int id) throws BuyerException{
        return DaoFactory.brandDao().getById(id);
    }
    public void update(Brand brand) throws  BuyerException{
        DaoFactory.brandDao().update(brand);
    }
    public void insert(Brand brand) throws BuyerException{
        DaoFactory.brandDao().insert(brand);
    }
    public List<Brand> searchByName(String name) throws BuyerException{
        return DaoFactory.brandDao().searchByName(name);
    }
}
