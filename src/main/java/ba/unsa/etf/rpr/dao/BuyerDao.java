package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;

/**
 * Dao interface for Buyer domain bean
 *
 * @author Selma Licina
 */

public interface BuyerDao extends Dao<Buyer>{
    Buyer searchByEmailAndPassword(String email,String password) throws BuyerException;
}
