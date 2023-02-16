package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.BrandManager;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
/**
 * JavaFX controller for edit brands
 * @author Selma Ličina
 */
public class EditBrandsController {
    //manager
    private final BrandManager brandManager=new BrandManager();

    //form components
    public TextField brandTextField;

    /**
     * Add button event handler
     * Adds new brands
     * @param actionEvent
     */
    public void addButtonAction(ActionEvent actionEvent)  {
        try {
            if(brandManager.searchByName(brandTextField.getText())!=null)
                new Alert(Alert.AlertType.ERROR,"That brand already exists!");
            else{
                Brand brand=new Brand();
                brand.setName(brandTextField.getText());
                brandManager.insert(brand);
            }
        }catch(BuyerException e){

        }

    }
    /**
     * Cancel button event handler
     * Closes edit brands dialog
     * @param actionEvent
     */
    public void cancelButtonAction(ActionEvent actionEvent) {
        brandTextField.getScene().getWindow().hide();
    }
}
