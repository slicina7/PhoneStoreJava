package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class EditBrandsController {
    public TextField brandTextField;

    public void addButtonAction(ActionEvent actionEvent) {
        Brand brand=new Brand();
        brand.setName(brandTextField.getText());
        BrandDaoSQLImpl brandDaoSQL=new BrandDaoSQLImpl();
        brandDaoSQL.insert(brand);
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
    }
}
