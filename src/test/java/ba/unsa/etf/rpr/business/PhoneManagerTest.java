package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PhoneManagerTest {

    PhoneManager phoneManager=new PhoneManager();
    BrandManager brandManager=new BrandManager();

    @Test
    void uniqueVersion() throws BuyerException {
        Brand b=brandManager.searchByName("Huawei");
        Phone phone=new Phone(b,"Unique version",100,10,new Date(100,3,5));
        phoneManager.insert(phone);
        assertThrows(BuyerException.class, ()->
                phoneManager.insert(new Phone(b,"Unique version",200,20,new Date(120,10,15))));
        phoneManager.delete(phoneManager.searchByBrandAndVersion(b,"Unique version").getId());
    }
    @Test
    void validateVersion() {
        String shortNameForVersion="P";
       assertThrows(BuyerException.class,()->phoneManager.insert(new Phone(brandManager.searchByName("Samsung"),shortNameForVersion,1000,10,new Date(120,10,10))));
    }

    @Test
    void searchByBrandAndVersion() throws BuyerException{
        Phone p=new Phone(brandManager.searchByName("Samsung"),"Version",1000,20,new Date(120,10,10));
        phoneManager.insert(p);
        Phone p2=phoneManager.searchByBrandAndVersion(brandManager.searchByName("Samsung"),"Version");
        assertEquals(p,p2);
        phoneManager.delete(p2.getId());
    }

    @Test
    void insert() throws BuyerException{
        Phone p=new Phone(brandManager.searchByName("Apple"),"Version",1000,20,new Date(120,10,10));
        phoneManager.insert(p);
        Phone p2=phoneManager.searchByBrandAndVersion(brandManager.searchByName("Apple"),"Version");
        assertTrue(p2!=null);
        phoneManager.delete(p2.getId());
    }
}