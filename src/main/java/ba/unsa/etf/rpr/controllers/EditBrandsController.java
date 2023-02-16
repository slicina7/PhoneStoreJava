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
            brandManager.insert(new Brand(brandTextField.getText()));
            new Alert(Alert.AlertType.CONFIRMATION,"Brand "+brandTextField.getText()+" successfully added.").show();
        }catch(BuyerException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
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
    /**
     * Delete button event handler
     * Delete brand
     * @param actionEvent
     */
    public void deleteButtonAction(ActionEvent actionEvent) {
        try {
            brandManager.delete(brandManager.searchByName(brandTextField.getText()).getId());
            new Alert(Alert.AlertType.CONFIRMATION,"Brand "+brandTextField.getText()+" successfully deleted.").show();
        }catch (BuyerException e){
            new Alert(Alert.AlertType.ERROR,"That brand doesn't exist!").show();
        }

    }
}
