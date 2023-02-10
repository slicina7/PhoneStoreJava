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
        /*
        String incorrectName1="S";
        Mockito.doCallRealMethod().when(buyerManager).validateName(incorrectName1);
        BuyerException buyerException1=assertThrows(BuyerException.class,()->{
            buyerManager.validateName(incorrectName1);},"Name can only contain letters and has to be longer than two letter and shorter than 30 letters!") ;
        Assertions.assertEquals("Name can only contain letters and has to be longer than two letter and shorter than 30 letters!",buyerException1.getMessage());


        String incorrectName2 = "Name123";
        Mockito.doCallRealMethod().when(buyerManager).validateName(incorrectName2);
        BuyerException buyerException2  = assertThrows(BuyerException.class,()->{
            buyerManager.validateName(incorrectName2);}, "Username must be between 3 and 45 chars, can't contain numbers");
        Assertions.assertEquals("Name can only contain letters and has to be longer than two letter and shorter than 30 letters!",buyerException2.getMessage());
*/
    }

}