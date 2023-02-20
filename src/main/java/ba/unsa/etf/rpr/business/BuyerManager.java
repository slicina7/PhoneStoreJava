package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import java.util.List;
import java.util.Objects;
/**
 * Business logic layer for Buyers
 * @author Selma Ličina
 */
public class BuyerManager {
    /**
     * method for name validation
     * @param name
     * @throws BuyerException
     */
    public void validateName(String name) throws BuyerException{
        if(name==null || name.length()<=2 || name.length()>30 || !name.matches("^[a-zA-Z]{2,20}$") || name.isEmpty())
            throw new BuyerException("Name can only contain letters and has to be longer than two letters and shorter than 30 letters!");
    }
    /**
     * method for surname validation
     * @param surname
     * @throws BuyerException
     */
    public void validateSurname(String surname) throws BuyerException{
        if(surname==null || surname.length()<=2 || surname.length()>30 || !surname.matches("^[a-zA-Z]{2,20}$") || surname.isEmpty())
            throw new BuyerException("Last name can only contain letters and has to be longer than two letters and shorter than 30 letters!");
    }
    /**
     * method for email validation
     * @param email
     * @throws BuyerException
     */
    public void validateEmail(String email) throws BuyerException{
        if(email==null || email.length()>30 || !email.matches("^[\\w-]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$") || email.isEmpty())
            throw new BuyerException("Invalid email input !");
    }
    /**
     * method for account number validation
     * @param a_number
     * @throws BuyerException
     */
    public void validateAccountNumber(String a_number) throws BuyerException{
        if(a_number==null || a_number.length()<5 || a_number.length()>30 || !a_number.matches("^[0-9]{5,30}$"))
            throw new BuyerException("Account number can only contain numbers and has to be longer than 5 numbers!");
    }
    /**
     * method for password validation
     * @param password
     * @throws BuyerException
     */
    public void validatePassword(String password) throws BuyerException{
        if(password.length()<8 || password.length()>30)
            throw new BuyerException("Password must be longer than 7 and shorter than 30!");
    }
    /**
     * method that returns list of all Buyers from database
     * @return list of Buyers
     * @throws BuyerException
     */
    public List<Buyer> getAll() throws BuyerException{
        return DaoFactory.buyerDao().getAll();
    }
    /**
     * method that deletes Buyer of given id from database
     * @param id
     * @throws BuyerException
     */
    public void delete(int id) throws BuyerException{
        DaoFactory.buyerDao().delete(id);
    }
    /**
     * method that returns Buyer object with given id
     * @param id
     * @return Buyer object
     * @throws BuyerException
     */
    public Buyer getById(int id) throws BuyerException{
        return DaoFactory.buyerDao().getById(id);
    }
    /**
     * method that updates given Buyer in database
     * @param buyer
     * @throws BuyerException
     */
    public void update(Buyer buyer) throws  BuyerException{
        validateName(buyer.getName());
        validateSurname(buyer.getSurname());
        validateEmail(buyer.getEmail());
        validateAccountNumber(buyer.getAccount_number());
        validatePassword(buyer.getPassword());
        DaoFactory.buyerDao().update(buyer);
    }
    /**
     * method for adding new Buyer to database
     * @param buyer
     * @throws BuyerException
     */
    public void insert(Buyer buyer) throws BuyerException{
        validateName(buyer.getName());
        validateSurname(buyer.getSurname());
        validateEmail(buyer.getEmail());
        validateAccountNumber(buyer.getAccount_number());
        validatePassword(buyer.getPassword());
        for(Buyer b: getAll())
            if(Objects.equals(buyer.getEmail(),b.getEmail()) || Objects.equals(buyer.getAccount_number(),b.getAccount_number())) {
                throw new BuyerException("Buyer with that email or account number already exists !");
            }
        DaoFactory.buyerDao().insert(buyer);
    }
    /**
     * method that returns Buyer with given email and password as parameters
     * @param email -email of user
     * @param password -password of user
     * @return Buyer object
     * @throws BuyerException
     */
    public Buyer searchByEmailAndPassword(String email,String password) throws BuyerException{
        return DaoFactory.buyerDao().searchByEmailAndPassword(email, password);
    }
}
