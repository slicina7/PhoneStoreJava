package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;

public class PurchaseManager {
    public List<Purchase> getAll() throws BuyerException {
        return DaoFactory.purchaseDao().getAll();
    }
    public void delete(int id) throws BuyerException{
        DaoFactory.purchaseDao().delete(id);
    }
    public Purchase getById(int id) throws BuyerException{
        return DaoFactory.purchaseDao().getById(id);
    }
    public void update(Purchase purchase) throws  BuyerException{
        DaoFactory.purchaseDao().update(purchase);
    }
    public void insert(Purchase purchase) throws BuyerException{
        DaoFactory.purchaseDao().insert(purchase);
    }
}
