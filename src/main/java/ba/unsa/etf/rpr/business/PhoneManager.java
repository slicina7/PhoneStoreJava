package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import java.util.List;
import java.util.Objects;
/**
 * Business logic layer for Phones
 * @author Selma Ličina
 */
public class PhoneManager {
    /**
     * method for version validation
     * @param version
     * @throws BuyerException
     */
    public void validateVersion(String version) throws BuyerException{
        if(version==null || version.length()<2 || version.length()>30 || !version.matches("^[a-zA-Z0-9 ]{2,20}$") || version.isEmpty())
            throw new BuyerException("Phone version can only contain letters and numbers and has to be longer than two and shorter than 30 letters and numbers!");
    }
    /**
     * method that returns list of all Phones from database
     * @return list of Phones
     * @throws BuyerException
     */
    public List<Phone> getAll() throws BuyerException{
        return DaoFactory.phoneDao().getAll();
    }
    /**
     * method that deletes Phone of given id from database
     * @param id
     * @throws BuyerException
     */
    public void delete(int id) throws BuyerException{
        DaoFactory.phoneDao().delete(id);
    }
    /**
     * method that returns Phone object with given id
     * @param id
     * @return Phone object
     * @throws BuyerException
     */
    public Phone getById(int id) throws BuyerException{
        return DaoFactory.phoneDao().getById(id);
    }
    /**
     * method that updates given Phone in database
     * @param phone
     * @throws BuyerException
     */
    public void update(Phone phone) throws  BuyerException{
        validateVersion(phone.getVersion());
        DaoFactory.phoneDao().update(phone);
    }
    /**
     * method for adding new Phone to database
     * @param phone
     * @throws BuyerException
     */
    public void insert(Phone phone) throws BuyerException{
        validateVersion(phone.getVersion());
        for(Phone p: getAll())
            if(Objects.equals(p.getVersion(),phone.getVersion()))
                throw new BuyerException("Phone with that version already exists !");
        DaoFactory.phoneDao().insert(phone);
    }
    /**
     * method that returns list of phones with given brand as parameter
     * @param brand - phone brand
     * @return list of Phones
     * @throws BuyerException
     */
    public  List<Phone> searchByBrand(Brand brand) throws BuyerException {
        return DaoFactory.phoneDao().searchByBrand(brand);
    }
    /**
     * method that returns list of phones whose price is between min and max that are given as parameters
     * @param min -minimum price
     * @param max -maximum price
     * @return list of Phones
     * @throws BuyerException
     */
    public List<Phone> searchByPrice(Integer min,Integer max) throws BuyerException{
        return DaoFactory.phoneDao().searchByPrice(min,max);
    }
    /**
     * method that returns phone with given brand and version as parameters
     * @param brand - Brand of the phone
     * @param version - phone version
     * @return Phone object
     * @throws BuyerException
     */
    public Phone searchByBrandAndVersion(Brand brand,String version) throws BuyerException{
        return DaoFactory.phoneDao().searchByBrandAndVersion(brand,version);
    }
}
