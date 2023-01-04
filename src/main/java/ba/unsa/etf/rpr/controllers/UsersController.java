package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersController {
    public TableView<Buyer> usersTableView;
    public TableColumn<Buyer,String> colName;
    public TableColumn<Buyer,String> colSurname;
    private ObservableList<Buyer> buyers;

    public UsersController() {
        buyers= FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        try {
            usersTableView.getItems().clear();
            buyers.addAll(DaoFactory.buyerDao().getAll());
            usersTableView.refresh();
            usersTableView.setItems(buyers);
        } catch (BuyerException e) {
            throw new RuntimeException(e);
        }
    }
}
