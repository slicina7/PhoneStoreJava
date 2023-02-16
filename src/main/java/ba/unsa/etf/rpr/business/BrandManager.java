package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;
import java.util.List;
import java.util.Objects;
/**
 * Business logic layer for Brands
 * @author Selma Ličina
 */
public class BrandManager {
    /**
     * method for brand name validation
     * @param name
     * @throws BuyerException
     */
    public void validateName(String name) throws BuyerException{
        if(name==null || name.length()<2 || name.length()>20 || !name.matches("^[a-zA-Z0-9]{2,20}$") || name.isEmpty())
            throw new BuyerException("Brand name can only contain letters and numbers and has to be longer than two and shorter than 20 letters and numbers!");
    }
    /**
     * method that returns list of all Brands from database
     * @return list of Brands
     * @throws BuyerException
     */
    public List<Brand> getAll() throws BuyerException {
        return DaoFactory.brandDao().getAll();
    }
    /**
     * method that deletes Brand of given id from database
     * @param id
     * @throws BuyerException
     */
    public void delete(int id) throws BuyerException{
        DaoFactory.brandDao().delete(id);
    }
    /**
     * method that returns Brand object with given id
     * @param id
     * @return Brand object
     * @throws BuyerException
     */
    public Brand getById(int id) throws BuyerException{
        return DaoFactory.brandDao().getById(id);
    }
    /**
     * method that updates given Brand in database
     * @param brand
     * @throws BuyerException
     */
    public void update(Brand brand) throws  BuyerException{
        validateName(brand.getName());
        DaoFactory.brandDao().update(brand);
    }
    /**
     * method for adding new Brand to database
     * @param brand
     * @throws BuyerException
     */
    public void insert(Brand brand) throws BuyerException{
        validateName(brand.getName());
        for(Brand b: getAll())
            if(Objects.equals(b.getName(),brand.getName()))
                throw new BuyerException("Brand with that name already exists !");
        DaoFactory.brandDao().insert(brand);
    }
    /**
     * method that returns Brand with given name as parameter
     * @param name - name of brand
     * @return Brand object
     * @throws BuyerException
     */
    public Brand searchByName(String name) throws BuyerException{
        return DaoFactory.brandDao().searchByName(name);
    }
}
