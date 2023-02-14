package ba.unsa.etf.rpr.controllers;

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
        SpinnerValueFactory<Integer> priceFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4000,0,50);
        priceSpinner.setValueFactory(priceFactory);
        SpinnerValueFactory<Integer> stockFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        stockSpinner.setValueFactory(stockFactory);
    }
    public void addButtonAction(ActionEvent actionEvent) {
        try{
            phoneManager.validateVersion(versionTextField.getText());
            if(brandChoiceBox.getValue()==null || versionTextField.getText().isEmpty() || releaseDate.getValue()==null || priceSpinner.getValue()==0 || stockSpinner.getValue()==0) {
                new Alert(Alert.AlertType.ERROR,"Invalid input!").show();
                System.out.println("sdfg");
            }
            if(phoneManager.searchByBrandAndVersion(brandChoiceBox.getValue(),versionTextField.getText())!=null)
                new Alert(Alert.AlertType.ERROR,"That phone already exists!").show();
            else {
                phoneManager.insert(new Phone(brandChoiceBox.getValue(),versionTextField.getText(),priceSpinner.getValue(),stockSpinner.getValue(),Date.valueOf(releaseDate.getValue())));
                new Alert(Alert.AlertType.CONFIRMATION,"Phone "+brandChoiceBox.getValue().getName()+" "+versionTextField.getText()+" successfully added.").show();

            }
        }catch (BuyerException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
        versionTextField.getScene().getWindow().hide();
    }
}
