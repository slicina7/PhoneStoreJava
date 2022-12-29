package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;
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
    Phone phone;
    PhoneDaoSQLImpl phoneDaoSQL;

    public EditPhonesController() {
        brandDaoSQL=new BrandDaoSQLImpl();
        brands= FXCollections.observableArrayList(brandDaoSQL.getAll());
        phone=new Phone();
        phoneDaoSQL=new PhoneDaoSQLImpl();
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
        phone.setVersion(versionTextField.getText());
        phone.setBrand(brandChoiceBox.getValue());
        phone.setPrice(priceSpinner.getValue());
        phone.setIn_stock(stockSpinner.getValue());
        phone.setRelease_date(Date.valueOf(releaseDate.getValue()));
        System.out.println(phone);
        phoneDaoSQL.insert(phone);
    }

    public void updateButtonAction(ActionEvent actionEvent) {
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
    }
}
