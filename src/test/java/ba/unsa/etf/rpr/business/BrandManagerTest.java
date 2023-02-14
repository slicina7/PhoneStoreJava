package ba.unsa.etf.rpr.business;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BrandManagerTest {
    private BrandManager brandManager;
    private Brand brand;
    private BrandDaoSQLImpl brandDaoSQLMock;
    private List<Brand> brands;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        brandManager= Mockito.mock(BrandManager.class);
        brand=new Brand("Name");
        brandDaoSQLMock=Mockito.mock(BrandDaoSQLImpl.class);
        brands=new ArrayList<>();
        brands.addAll(Arrays.asList(new Brand("Name2"),new Brand("Name3")));
    }
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
        BuyerException buyerException1=assertThrows(BuyerException.class,()->{
            brandManager.validateName(incorrectShort);},"Brand name can only contain letters and numbers and has to be longer than two and shorter than 20 letters and numbers!") ;
        Assertions.assertEquals("Brand name can only contain letters and numbers and has to be longer than two and shorter than 20 letters and numbers!",buyerException1.getMessage());

    }
    @Test
    void insertException() {
        Brand brand=new Brand("Samsung");
        assertThrows(BuyerException.class,()->new BrandManager().insert(brand));
    }
    @Test
    void getAll() throws BuyerException{
        when(brandManager.getAll()).thenReturn(brands);
        Assertions.assertEquals(brands,brandManager.getAll());
    }
    @Test
    void insert() throws BuyerException{
        Brand b=new Brand("Name");
        brandManager.insert(b);
        Assertions.assertTrue(true);
        Mockito.verify(brandManager).insert(b);
    }

    @Test
    void getById() throws Exception{
        BrandManager bm=new BrandManager();
        assertEquals(bm.getById(1).getName(),"Apple");
    }

    @Test
    void searchByName() throws Exception{
        Brand b =new BrandManager().searchByName("Apple");
        assertEquals("Apple",b.getName());
    }
}
