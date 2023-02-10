package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BuyerManagerTest {
    private BuyerManager buyerManager;
    private Buyer buyer;
    private BuyerDaoSQLImpl buyerDaoSQLMock;
    private List<Buyer> buyers;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        buyerManager= Mockito.mock(BuyerManager.class);
        buyer=new Buyer("Name","Surename","email@gmail.com","123456","Password",12345);
        buyerDaoSQLMock=Mockito.mock(BuyerDaoSQLImpl.class);
        buyers=new ArrayList<>();
        buyers.addAll(Arrays.asList(new Buyer("Name2","Surename2","email2@gmail.com","1234567","Password",12345),
                new Buyer("Name3","Surename3","email3@gmail.com","12345678","Password",12345)));
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

    @Test
    void validateEmail() throws BuyerException {
        String correctEmail1 = "email@gmail.com";
        String correctEmail2 = "email@etf.unsa.ba";
        try {
            Mockito.doCallRealMethod().when(buyerManager).validateEmail(correctEmail1);
        } catch (BuyerException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        try {
            Mockito.doCallRealMethod().when(buyerManager).validateEmail(correctEmail2);
        } catch (BuyerException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrect1 = "email";
        Mockito.doCallRealMethod().when(buyerManager).validateEmail(incorrect1);
        BuyerException buyerException1 = assertThrows(BuyerException.class, () -> {
            buyerManager.validateEmail(incorrect1);
        }, "Invalid email input !");
        Assertions.assertEquals("Invalid email input !", buyerException1.getMessage());

        String incorrect2 = "email@";
        Mockito.doCallRealMethod().when(buyerManager).validateEmail(incorrect2);
        BuyerException buyerException2 = assertThrows(BuyerException.class, () -> {
            buyerManager.validateEmail(incorrect2);
        }, "Invalid email input !");
        Assertions.assertEquals("Invalid email input !", buyerException2.getMessage());
        }

        @Test
        void insert() throws BuyerException{
        Buyer newBuyer=new Buyer("Name","Surename","email@gmail.com","123456","Password",12345);
        buyerManager.insert(newBuyer);
        Assertions.assertTrue(true);
        Mockito.verify(buyerManager).insert(newBuyer);
        }

        @Test
        void insertExisting() throws BuyerException{
        when(buyerManager.getAll()).thenReturn(buyers);
        Buyer b=new Buyer("Name2","Surename2","email2@gmail.com","1234567","Password",12345);
        Mockito.doCallRealMethod().when(buyerManager).insert(b);
        BuyerException buyerException=Assertions.assertThrows(BuyerException.class,()->{buyerManager.insert(b);});

        Assertions.assertEquals("Buyer with that email or account number already exists !",buyerException.getMessage());
        Mockito.verify(buyerManager).insert(b);
        }

        @Test
        void getAll() throws BuyerException{
        when(buyerManager.getAll()).thenReturn(buyers);
        Assertions.assertEquals(buyers,buyerManager.getAll());
        }
    }