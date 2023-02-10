package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyerManagerTest {
    private BuyerManager buyerManager;
    private Buyer buyer;
    private BuyerDaoSQLImpl buyerDaoSQLMock;
    private List<Buyer> buyers;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        buyerManager= Mockito.mock(BuyerManager.class);
        buyer=new Buyer("Name","Surename","email","account","Password",12345);
        buyerDaoSQLMock=Mockito.mock(BuyerDaoSQLImpl.class);
    }
    @Test
    void validateName() throws BuyerException{
        String correctName="Name";
        try{
            Mockito.doCallRealMethod().when(buyerManager).validateName(correctName);
        }catch(BuyerException e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectShort="S";
        Mockito.doCallRealMethod().when(buyerManager).validateName(incorrectShort);
        BuyerException buyerException1=assertThrows(BuyerException.class,()->{
            buyerManager.validateName(incorrectShort);},"Name can only contain letters and has to be longer than two letter and shorter than 30 letters!") ;
        Assertions.assertEquals("Name can only contain letters and has to be longer than two letter and shorter than 30 letters!",buyerException1.getMessage());

        String incorrectLong="Ssssssssssssssssssssssssssssssssssss";
        Mockito.doCallRealMethod().when(buyerManager).validateName(incorrectLong);
        BuyerException buyerException2=assertThrows(BuyerException.class,()->{
            buyerManager.validateName(incorrectLong);},"Name can only contain letters and has to be longer than two letter and shorter than 30 letters!") ;
        Assertions.assertEquals("Name can only contain letters and has to be longer than two letter and shorter than 30 letters!",buyerException2.getMessage());

        String incorrectNumber = "Name123";
        Mockito.doCallRealMethod().when(buyerManager).validateName(incorrectNumber);
        BuyerException buyerException3  = assertThrows(BuyerException.class,()->{
            buyerManager.validateName(incorrectNumber);}, "Username must be between 3 and 45 chars, can't contain numbers");
        Assertions.assertEquals("Name can only contain letters and has to be longer than two letter and shorter than 30 letters!",buyerException3.getMessage());

    }

    @Test
    void validateSurname() throws BuyerException{
        String correctSurname="Surname";
        try{
            Mockito.doCallRealMethod().when(buyerManager).validateSurname(correctSurname);
        }catch(BuyerException e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectShort="S";
        Mockito.doCallRealMethod().when(buyerManager).validateSurname(incorrectShort);
        BuyerException buyerException1=assertThrows(BuyerException.class,()->{
            buyerManager.validateSurname(incorrectShort);},"Name can only contain letters and has to be longer than two letter and shorter than 30 letters!") ;
        Assertions.assertEquals("Last name can only contain letters and has to be longer than one letter and shorter than 30 letters!",buyerException1.getMessage());

        String incorrectLong="Ssssssssssssssssssssssssssssssssssss";
        Mockito.doCallRealMethod().when(buyerManager).validateSurname(incorrectLong);
        BuyerException buyerException2=assertThrows(BuyerException.class,()->{
            buyerManager.validateSurname(incorrectLong);},"Name can only contain letters and has to be longer than two letter and shorter than 30 letters!") ;
        Assertions.assertEquals("Last name can only contain letters and has to be longer than one letter and shorter than 30 letters!",buyerException2.getMessage());

        String incorrectNumber = "Name123";
        Mockito.doCallRealMethod().when(buyerManager).validateSurname(incorrectNumber);
        BuyerException buyerException3  = assertThrows(BuyerException.class,()->{
            buyerManager.validateSurname(incorrectNumber);}, "Username must be between 3 and 45 chars, can't contain numbers");
        Assertions.assertEquals("Last name can only contain letters and has to be longer than one letter and shorter than 30 letters!",buyerException3.getMessage());

    }

    @Test
    void validatePassword() throws BuyerException{
        String inccorectShort="123";
        Mockito.doCallRealMethod().when(buyerManager).validatePassword(inccorectShort);
        BuyerException buyerException=assertThrows(BuyerException.class,()->{ buyerManager.validatePassword(inccorectShort);},"Password must be longer than 7 and shorter than 30!");
        Assertions.assertEquals("Password must be longer than 7 and shorter than 30!",buyerException.getMessage());
    }

}