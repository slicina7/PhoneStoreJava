package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.sql.*;
import java.util.*;
/**
 * MySQL implementation of the DAO
 */
public class PurchaseDaoSQLImpl extends AbstractDao<Purchase> implements PurchaseDao {

    public PurchaseDaoSQLImpl(){ super("purchases");}

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws BuyerException in case of error
     */
    @Override
    public Purchase rowToObject(ResultSet rs) throws BuyerException {
        try{
            Purchase purchase=new Purchase();
            purchase.setId(rs.getInt("id"));
            purchase.setPhone(DaoFactory.phoneDao().getById(rs.getInt("phone_id")));
            purchase.setBuyer(DaoFactory.buyerDao().getById(rs.getInt("buyer_id")));
            return purchase;
        }catch(SQLException e){
            throw new BuyerException(e.getMessage(),e);
        }
    }

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    @Override
    public Map<String, Object> objectToRow(Purchase object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("phone_id", object.getPhone().getId());
        item.put("buyer_id", object.getBuyer().getId());
        return item;
    }

    /**
     * Returns all purchases  of that phone.
     * @param phone search string for purchases
     * @return List of purchases
     */
    @Override
    public List<Purchase> searchByPhone(Phone phone) throws BuyerException {
        return executeQuery("SELECT * FROM purchases WHERE phone_id = ?",new Object[]{phone.getId()});
    }

    /**
     * Returns all purchases of that buyer.
     * @param buyer search string for purchases
     * @return List of purchases
     */
    @Override
    public List<Purchase> searchByBuyer(Buyer buyer) throws BuyerException{
        return executeQuery("SELECT * FROM purchases WHERE buyer_id=?",new Object[]{buyer.getId()});
    }
}
