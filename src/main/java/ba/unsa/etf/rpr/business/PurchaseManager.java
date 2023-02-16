package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;
import java.util.List;
/**
 * Business logic layer for Purchases
 * @author Selma Ličina
 */
public class PurchaseManager {
    /**
     * method that returns list of all Purchases from database
     * @return list of Purchases
     * @throws BuyerException
     */
    public List<Purchase> getAll() throws BuyerException {
        return DaoFactory.purchaseDao().getAll();
    }
    /**
     * method that deletes Purchase of given id from database
     * @param id
     * @throws BuyerException
     */
    public void delete(int id) throws BuyerException{
        DaoFactory.purchaseDao().delete(id);
    }
    /**
     * method that returns Purchase object with given id
     * @param id
     * @return Purchase object
     * @throws BuyerException
     */
    public Purchase getById(int id) throws BuyerException{
        return DaoFactory.purchaseDao().getById(id);
    }
    /**
     * method that updates given Purchases in database
     * @param purchase
     * @throws BuyerException
     */
    public void update(Purchase purchase) throws  BuyerException{
        DaoFactory.purchaseDao().update(purchase);
    }
    /**
     * method for adding new Purchase to database
     * @param purchase
     * @throws BuyerException
     */
    public void insert(Purchase purchase) throws BuyerException{
        DaoFactory.purchaseDao().insert(purchase);
    }
}
