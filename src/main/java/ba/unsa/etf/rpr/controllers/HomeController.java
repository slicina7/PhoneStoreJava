package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Phone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

public class HomeController {
    private BrandDaoSQLImpl brandDaoSQL;
    private PhoneDaoSQLImpl phoneDaoSQL;
    private ObservableList<Brand> brands;
    private ObservableList<Phone> phones;
    public TableView<Phone> phonesTableView;
    public ListView<Brand> brandsList;

    public HomeController(){
        phoneDaoSQL=new PhoneDaoSQLImpl();
        brandDaoSQL=new BrandDaoSQLImpl();
        brands= FXCollections.observableArrayList(brandDaoSQL.getAll());
        phones=FXCollections.observableArrayList();
    }
}

