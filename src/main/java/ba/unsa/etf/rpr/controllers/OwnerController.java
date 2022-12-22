package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.util.Date;

public class OwnerController {
    public TextField idBrand;
    public TextField idVersion;
    public TextField idPrice;
    public TextField idStock;
    public TextField idReleaseDate;
    public Button idAddButton;

    public void addAction(ActionEvent actionEvent) {
        Phone phone=new Phone();
        PhoneDaoSQLImpl phoneDaoSQL=new PhoneDaoSQLImpl();
        Brand brand=new Brand();
        brand.setName(idBrand.getText());
        phone.setBrand(brand);
        phone.setVersion(idVersion.getText());
        phone.setPrice(Integer.parseInt(idPrice.getText()));
        phone.setIn_stock(Integer.parseInt(idStock.getText()));
        phone.setRelease_date((java.sql.Date) Date.from(Instant.parse(idReleaseDate.getText())));
        phoneDaoSQL.insert(phone);
    }
}
