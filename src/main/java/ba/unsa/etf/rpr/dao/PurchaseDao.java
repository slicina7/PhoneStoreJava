package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;

import java.util.List;

/**
 * Dao interface for Purchase domain bean
 *
 * @author Selma Licina
 */
public interface PurchaseDao extends Dao<Purchase> {
    /**
     * Returns all purchases  of that phone.
     *
     * @param phone search string for purchases
     * @return list of purchases
     */
    List<Purchase> searchByPhone(Phone phone);
    /**
     * Returns all purchases of that buyer.
     *
     * @param buyer search string for purchases
     * @return list of purchases
     */
    List<Purchase> searchByBuyer(Buyer buyer);
}
