package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;
import java.util.Objects;

public class BrandManager {
    public void validateName(String name) throws BuyerException{
        if(name==null || name.length()<2 || name.length()>20 || !name.matches("^[a-zA-Z0-9]{2,20}$") || name.isEmpty())
            throw new BuyerException("Brand name can only contain letters and numbers and has to be longer than two and shorter than 20 letters and numbers!");
    }
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
        validateName(brand.getName());
        DaoFactory.brandDao().update(brand);
    }
    public void insert(Brand brand) throws BuyerException{
        validateName(brand.getName());
        for(Brand b: getAll())
            if(Objects.equals(b.getName(),brand.getName()))
                throw new BuyerException("Brand with that name already exists !");
        DaoFactory.brandDao().insert(brand);
    }
    public Brand searchByName(String name) throws BuyerException{
        return DaoFactory.brandDao().searchByName(name);
    }
}
