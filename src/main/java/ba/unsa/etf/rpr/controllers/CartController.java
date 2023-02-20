package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
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
/**
 * JavaFX controller for cart
 * @author Selma Ličina
 */
public class CartController {

    //form components
    public TableView<Phone> purchasesTableView;
    public TableColumn<Phone,String>  colPhone;
    public TableColumn<Phone,String>  colPrice;

    private ObservableList<Phone> phones;
    public Buyer buyer;

    public CartController() {
        phones= FXCollections.observableArrayList();
        buyer=new Buyer();
    }
    /**
     * Sets buyer
     * @param buyer -buyer to be set
     */
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
    /**
     * Sets rows into table of buyers purchases
     */
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
