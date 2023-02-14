package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;

import java.util.List;
import java.util.Objects;

public class PhoneManager {

    public void validateVersion(String version) throws BuyerException{
        if(version==null || version.length()<2 || version.length()>30 || !version.matches("^[a-zA-Z0-9 ]{2,20}$") || version.isEmpty())
            throw new BuyerException("Phone version can only contain letters and numbers and has to be longer than two and shorter than 30 letters and numbers!");
    }
    public List<Phone> getAll() throws BuyerException{
        return DaoFactory.phoneDao().getAll();
    }
    public  List<Phone> searchByBrand(Brand brand) throws BuyerException {
        return DaoFactory.phoneDao().searchByBrand(brand);
    }
    public List<Phone> searchByPrice(Integer min,Integer max) throws BuyerException{
        return DaoFactory.phoneDao().searchByPrice(min,max);
    }
     public Phone searchByBrandAndVersion(Brand brand,String version) throws BuyerException{
        return DaoFactory.phoneDao().searchByBrandAndVersion(brand,version);
    }
    public void delete(int id) throws BuyerException{
        DaoFactory.phoneDao().delete(id);
    }
    public Phone getById(int id) throws BuyerException{
        return DaoFactory.phoneDao().getById(id);
    }
    public void update(Phone phone) throws  BuyerException{
        validateVersion(phone.getVersion());
        DaoFactory.phoneDao().update(phone);
    }
    public void insert(Phone phone) throws BuyerException{
        validateVersion(phone.getVersion());
        for(Phone p: getAll())
            if(Objects.equals(p.getVersion(),phone.getVersion()))
                throw new BuyerException("Phone with that version already exists !");
        DaoFactory.phoneDao().insert(phone);
    }

}
