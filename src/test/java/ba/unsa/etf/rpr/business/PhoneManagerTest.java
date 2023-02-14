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

    @Test
    void uniqueVersion() throws BuyerException {
        BrandManager bm=new BrandManager();
        Brand b=bm.searchByName("Huawei");
        Phone phone=new Phone(b,"Unique version",100,10,new Date(2000,3,5));
        phoneManager.insert(phone);
        assertThrows(BuyerException.class, ()->
                phoneManager.insert(new Phone(b,"Unique version",200,20,new Date(2020,10,15))));
        phoneManager.delete(phoneManager.searchByBrandAndVersion(b,"Unique version").getId());
    }
    @Test
    void validateVersion() {
    }

    @Test
    void getAll() {
    }

    @Test
    void searchByBrand() {
    }

    @Test
    void searchByPrice() {
    }

    @Test
    void searchByBrandAndVersion() {
    }

    @Test
    void delete() {
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }
}