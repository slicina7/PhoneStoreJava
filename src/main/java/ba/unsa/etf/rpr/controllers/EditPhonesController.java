package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditPhonesController {
    public ChoiceBox<Brand> brandChoiceBox;
    BrandDaoSQLImpl brandDaoSQL;
    private ObservableList<Brand> brands;
    public TextField versionTextField;
    public DatePicker releaseDate;
    public Spinner priceSpinner;
    public Spinner stockSpinner;

    public EditPhonesController() {
        brandDaoSQL=new BrandDaoSQLImpl();
        brands= FXCollections.observableArrayList(brandDaoSQL.getAll());
    }

    @FXML
    public void initialize() {
        brandChoiceBox.setItems(brands);
    }
    public void addButtonAction(ActionEvent actionEvent) {
    }

    public void updateButtonAction(ActionEvent actionEvent) {
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
    }
}
