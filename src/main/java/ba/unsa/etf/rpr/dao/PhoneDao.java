package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;

/**
 * Dao interface for Phone domain bean
 *
 * @author Selma Licina
 */

public interface PhoneDao extends Dao<Phone>{
    /**
     * Returns all phones that are given brand.
     * @param brand phone brand
     * @return List of phones
     */
    List<Phone> searchByBrand(Brand brand) throws BuyerException;
    /**
     * Search phones in database based on price range
     * @param min minimum price
     * @param max maximum price
     * @return List of phones from table
     */
    List<Phone> searchByPrice(Integer min,Integer max) throws BuyerException;
    /**
     * Search phones in database based on version
     * @param version , phone version
     * @return List of phones from table
     */
    List<Phone> searchByBrandAndVersion(Brand brand,String version) throws BuyerException;

}
