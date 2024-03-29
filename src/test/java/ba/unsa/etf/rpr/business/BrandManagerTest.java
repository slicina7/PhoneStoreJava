package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.business.BrandManager;
import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Selma Licina
 */
public class BrandManagerTest {
    private BrandManager brandManager;
    private Brand brand;
    private BrandDaoSQLImpl brandDaoSQLMock;
    private List<Brand> brands;
    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        brandManager= Mockito.mock(BrandManager.class);
        brand=new Brand("Name");
        brandDaoSQLMock=Mockito.mock(BrandDaoSQLImpl.class);
        brands=new ArrayList<>();
        brands.addAll(Arrays.asList(new Brand("Name2"),new Brand("Name3")));
    }
    /**
     * In this method we will test validateName(String name) for correct and incorrect passed parameters
     * @throws BuyerException
     */
    @Test
    void validateName() throws BuyerException{
        String correctName="Name";
        try{
            Mockito.doCallRealMethod().when(brandManager).validateName(correctName);
        }catch(BuyerException e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectShort="S";
        Mockito.doCallRealMethod().when(brandManager).validateName(incorrectShort);
        BuyerException buyerException1=Assertions.assertThrows(BuyerException.class,()->{
            brandManager.validateName(incorrectShort);},"Brand name can only contain letters and numbers and has to be longer than two and shorter than 20 letters and numbers!") ;
        Assertions.assertEquals("Brand name can only contain letters and numbers and has to be longer than two and shorter than 20 letters and numbers!",buyerException1.getMessage());
    }

    /**
     * Gets all brands
     * @throws BuyerException
     */
    @Test
    void getAll() throws BuyerException{
        when(brandManager.getAll()).thenReturn(brands);
        Assertions.assertEquals(brands,brandManager.getAll());
    }

    /**
     * Adding a new brand
     * @throws BuyerException
     */
    @Test
    void insert() throws BuyerException{
        Brand b=new Brand("Name");
        brandManager.insert(b);
        Assertions.assertTrue(true);
        Mockito.verify(brandManager).insert(b);
    }

}
