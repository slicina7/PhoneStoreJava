package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    void insertException() {
        Brand brand=new Brand("Samsung");
        assertThrows(BuyerException.class,()->new BrandManager().insert(brand));
    }

    @Test
    void getById() throws Exception{
        BrandManager bm=new BrandManager();
        assertEquals(bm.getById(1).getName(),"Apple");
    }

    @Test
    void searchByName() throws Exception{
        List lista =new BrandManager().searchByName("Apple");
        assertEquals("[Apple]",lista.toString());
    }
}
