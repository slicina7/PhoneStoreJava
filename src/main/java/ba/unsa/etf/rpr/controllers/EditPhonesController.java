package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.BrandManager;
import ba.unsa.etf.rpr.business.PhoneManager;
import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
/**
 * JavaFX controller for edit phones
 * @author Selma Ličina
 */
public class EditPhonesController {

    //managers
    private final BrandManager brandManager=new BrandManager();
    private final PhoneManager phoneManager=new PhoneManager();

    //form components
    public ChoiceBox<Brand> brandChoiceBox;
    public TextField versionTextField;
    public DatePicker releaseDate;
    public Spinner<Integer> priceSpinner;
    public Spinner<Integer> stockSpinner;

    private ObservableList<Brand> brands;


    public EditPhonesController() {
        try {
            brands= FXCollections.observableArrayList(brandManager.getAll());
        }catch (BuyerException e){

        }

    }

    @FXML
    public void initialize() {
        brandChoiceBox.setItems(brands);
        SpinnerValueFactory<Integer> priceFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4000,0,50);
        priceSpinner.setValueFactory(priceFactory);
        SpinnerValueFactory<Integer> stockFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        stockSpinner.setValueFactory(stockFactory);
    }
    /**
     * Add button event handler
     * Adds new phone
     * @param actionEvent
     */
    public void addButtonAction(ActionEvent actionEvent) {
        try{
            if(brandChoiceBox.getValue()==null || releaseDate.getValue()==null)
                throw new BuyerException("Invalid input!");
            phoneManager.insert(new Phone(brandChoiceBox.getValue(),versionTextField.getText(),priceSpinner.getValue(),stockSpinner.getValue(),Date.valueOf(releaseDate.getValue())));
            new Alert(Alert.AlertType.CONFIRMATION,"Phone "+brandChoiceBox.getValue().getName()+" "+versionTextField.getText()+" successfully added.").show();
        }catch (BuyerException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    /**
     * Cancel button event handler
     * Closes edit phones dialog
     * @param actionEvent
     */
    public void cancelButtonAction(ActionEvent actionEvent) {
        versionTextField.getScene().getWindow().hide();
    }
    /**
     * Update button event handler
     * Update phone in stock number, price and date
     * @param actionEvent
     */
    public void updateButtonAction(ActionEvent actionEvent) {
        try{
            if(brandChoiceBox.getValue()==null || releaseDate.getValue()==null)
                throw new BuyerException("Invalid input!");
            Phone p=phoneManager.searchByBrandAndVersion(brandChoiceBox.getValue(),versionTextField.getText());
            p.setIn_stock(stockSpinner.getValue());
            p.setPrice(priceSpinner.getValue());
            p.setRelease_date(Date.valueOf(releaseDate.getValue()));
            phoneManager.update(p);
            new Alert(Alert.AlertType.CONFIRMATION,"Phone "+brandChoiceBox.getValue().getName()+" "+versionTextField.getText()+" successfully updated.").show();
        }catch (BuyerException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    /**
     * Delete button event handler
     * Delete phone
     * @param actionEvent
     */
    public void deleteButtonAction(ActionEvent actionEvent) {
        try{
            Phone p=phoneManager.searchByBrandAndVersion(brandChoiceBox.getValue(),versionTextField.getText());
            phoneManager.delete(p.getId());
            new Alert(Alert.AlertType.CONFIRMATION,"Phone "+brandChoiceBox.getValue().getName()+" "+versionTextField.getText()+" successfully deleted.").show();
        }catch (BuyerException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
