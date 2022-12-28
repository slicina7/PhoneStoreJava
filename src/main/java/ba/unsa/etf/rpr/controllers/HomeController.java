package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController {
    public ListView<Brand> brandsListView;
    public Spinner<Integer> minPrice;
    public Spinner<Integer> maxPrice;
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
        SpinnerValueFactory<Integer> minvalueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,3900,0,100);
        minPrice.setValueFactory(minvalueFactory);
        SpinnerValueFactory<Integer> maxvalueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(100,4000,4000,100);
        maxPrice.setValueFactory(maxvalueFactory);
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
        minPrice.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {

            }
        });
        maxPrice.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {

            }
        });
    }
}

