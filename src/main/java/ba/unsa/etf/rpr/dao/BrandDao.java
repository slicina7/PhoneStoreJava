package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;

/**
 * Dao interface for Brand domain bean
 *
 * @author Selma Licina
 */

public interface BrandDao extends Dao<Brand>{
    /**
     * Search brands in database based on name
     * @param name brand name
     * @return Brand with that name
     */
    List<Brand> searchByName(String name) throws BuyerException;
}
