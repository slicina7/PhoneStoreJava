package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PurchaseDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CartController {
    public TableView<Phone> purchasesTableView;
    public TableColumn<Phone,String>  colPhone;
    public TableColumn<Phone,String>  colPrice;
    private ObservableList<Phone> phones;
    private PurchaseDaoSQLImpl purchaseDaoSQL;
    private PhoneDaoSQLImpl phoneDaoSQL;

    public Buyer buyer;

    public CartController() {
        purchaseDaoSQL=new PurchaseDaoSQLImpl();
        phones= FXCollections.observableArrayList();
        phoneDaoSQL=new PhoneDaoSQLImpl();
        buyer=new Buyer();
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        setTable();
    }
    @FXML
    public void initialize() {
        colPhone.setCellValueFactory(new PropertyValueFactory<>("version"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        purchasesTableView.getItems().clear();

    }
    public void setTable(){
        try {
            purchasesTableView.getItems().clear();
            List<Purchase> purchases= DaoFactory.purchaseDao().searchByBuyer(buyer);
            for(int i=0;i<purchases.size();i++){
                phones.add(DaoFactory.phoneDao().getById(Integer.parseInt(purchases.get(i).toString())));
            }
            purchasesTableView.refresh();
            purchasesTableView.setItems(phones);
        }catch (BuyerException e){

        }
    }
}
