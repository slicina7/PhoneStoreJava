package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;

public class BuyerManager {
    public List<Buyer> getAll() throws BuyerException{
        return DaoFactory.buyerDao().getAll();
    }
    public Buyer searchByEmailAndPassword(String email,String password) throws BuyerException{
        return DaoFactory.buyerDao().searchByEmailAndPassword(email,password);
    }
    public void delete(int id) throws BuyerException{
        DaoFactory.buyerDao().delete(id);
    }
    public Buyer getById(int id) throws BuyerException{
        return DaoFactory.buyerDao().getById(id);
    }
    public void update(Buyer buyer) throws  BuyerException{
        DaoFactory.buyerDao().update(buyer);
    }
    public void insert(Buyer buyer) throws BuyerException{
        DaoFactory.buyerDao().insert(buyer);
    }


}
