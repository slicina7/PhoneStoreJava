package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.PhoneManager;
import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.util.List;

public class EditPhonesController {
    public ChoiceBox<Brand> brandChoiceBox;
    BrandDaoSQLImpl brandDaoSQL;
    private ObservableList<Brand> brands;
    public TextField versionTextField;
    public DatePicker releaseDate;
    public Spinner<Integer> priceSpinner;
    public Spinner<Integer> stockSpinner;
    public PhoneManager phoneManager=new PhoneManager();

    public EditPhonesController() {
        try {
            brandDaoSQL=new BrandDaoSQLImpl();
            brands= FXCollections.observableArrayList(brandDaoSQL.getAll());
        }catch (BuyerException e){

        }

    }

    @FXML
    public void initialize() {
        brandChoiceBox.setItems(brands);
        SpinnerValueFactory<Integer> priceFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4000,0,100);
        priceSpinner.setValueFactory(priceFactory);
        SpinnerValueFactory<Integer> stockFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        stockSpinner.setValueFactory(stockFactory);
    }
    public void addButtonAction(ActionEvent actionEvent) {
        try{
            if(phoneManager.searchByBrandAndVersion(brandChoiceBox.getValue(),versionTextField.getText()).size()!=0)
                new Alert(Alert.AlertType.ERROR,"That phone already exists!");
            else {
                phoneManager.insert(new Phone(brandChoiceBox.getValue(),versionTextField.getText(),priceSpinner.getValue(),stockSpinner.getValue(),Date.valueOf(releaseDate.getValue())));
            }
        }catch (BuyerException e){
        }
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
        versionTextField.getScene().getWindow().hide();
    }
}
