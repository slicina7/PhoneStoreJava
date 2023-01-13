package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.BrandManager;
import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EditBrandsController {
    public TextField brandTextField;
    public BrandManager brandManager=new BrandManager();

    public void addButtonAction(ActionEvent actionEvent)  {
        try {
            if(brandManager.searchByName(brandTextField.getText()).size()!=0)
                new Alert(Alert.AlertType.ERROR,"That brand already exists!");
            else{
                Brand brand=new Brand();
                brand.setName(brandTextField.getText());
                brandManager.insert(brand);
            }
        }catch(BuyerException e){

        }

    }
    public void cancelButtonAction(ActionEvent actionEvent) {
        brandTextField.getScene().getWindow().hide();
    }
}
