package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController {
    public ListView<Brand> brandsListView;
    private BrandDaoSQLImpl brandDaoSQL;
    private ObservableList<Brand> brands;
    private PhoneDaoSQLImpl phoneDaoSQL;
    private ObservableList<Phone> phones;
    public TableView<Phone> phonesTableView;
    public TableColumn<Phone,String> colPhonesId;
    public TableColumn<Phone,String> colPhonesVersion;
    public TableColumn<Phone,String> colPhonesDate;
    public TableColumn<Phone,String> colPhonesStock;
    public TableColumn<Phone,String> colPhonesPrice;


    public HomeController(){
        brandDaoSQL=new BrandDaoSQLImpl();
        phoneDaoSQL=new PhoneDaoSQLImpl();
        brands=FXCollections.observableArrayList(brandDaoSQL.getAll());
        phones=FXCollections.observableArrayList();
    }
    @FXML
    public void initialize(){
        brandsListView.setItems(brands);
        colPhonesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPhonesVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        colPhonesDate.setCellValueFactory(new PropertyValueFactory<>("release_date"));
        colPhonesStock.setCellValueFactory(new PropertyValueFactory<>("in_stock"));
        colPhonesPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        brandsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldCat, newCat) -> {
            try {
                phonesTableView.getItems().clear();
                phones.addAll(phoneDaoSQL.searchByBrand(newCat));
                phonesTableView.refresh();
                phonesTableView.setItems(phones);
            } catch (BuyerException e) {
                System.out.println("Something went wrong with searchByCategory method from quoteDaoSQlImpl");
                throw new RuntimeException(e);
            }
        });
    }
}

