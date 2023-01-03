package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PurchaseDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartController {
    public TableView purchasesTableView;
    public TableColumn colPhone;
    public TableColumn colPrice;

    private PurchaseDaoSQLImpl purchaseDaoSQL;

    private ObservableList<Purchase> purchases;

    public CartController() {
        purchaseDaoSQL=new PurchaseDaoSQLImpl();
        purchases= FXCollections.observableArrayList();
    }
    @FXML
    public void initialize() {
        colPhone.setCellValueFactory(new PropertyValueFactory<>(""));
        colPrice.setCellValueFactory(new PropertyValueFactory<>(""));
        purchasesTableView.getItems().clear();
        try {
            purchases.addAll(purchaseDaoSQL.getAll());
            purchasesTableView.refresh();
            purchasesTableView.setItems(purchases);
        }catch (BuyerException e){

        }

    }
}
