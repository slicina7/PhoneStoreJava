package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;

import java.util.List;

/**
 * Dao interface for Phone domain bean
 *
 * @author Selma Licina
 */

public interface PhoneDao extends Dao<Phone>{

    /**
     * Returns all phones that contains given text.
     *
     * @param text search string for phones
     * @return list of phones
     */
    List<Phone> searchByText(String text);

    /**
     * Returns all phones that are given brand.
     *
     * @param brand search string for phones
     * @return list of phones
     */
    List<Phone> searchByBrand(Brand brand);
}
