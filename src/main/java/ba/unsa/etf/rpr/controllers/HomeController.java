package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BrandDaoSQLImpl;
import ba.unsa.etf.rpr.dao.BuyerDao;
import ba.unsa.etf.rpr.dao.PhoneDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public ListView<Brand> brandsListView;
    public Spinner<Integer> minPrice;
    public Spinner<Integer> maxPrice;
    public MenuItem IdBrandsEdit;
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
    public Buyer buyer;

    public HomeController(){
        brandDaoSQL=new BrandDaoSQLImpl();
        phoneDaoSQL=new PhoneDaoSQLImpl();
        brands=FXCollections.observableArrayList(brandDaoSQL.getAll());
        phones=FXCollections.observableArrayList();
        buyer=new Buyer();

    }
    public void setBuyer(Buyer b) {
        this.buyer = b;

    }

    public Buyer getBuyer() {
        return buyer;
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
                try {
                    phonesTableView.getItems().clear();
                    phones.addAll(phoneDaoSQL.searchByPrice(minPrice.getValue(),maxPrice.getValue()));
                    phonesTableView.refresh();
                    phonesTableView.setItems(phones);
                } catch (BuyerException e) {
                    System.out.println("Something went wrong with searchByCategory method from quoteDaoSQlImpl");
                    throw new RuntimeException(e);
                }
            }
        });
        maxPrice.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                try {
                    phonesTableView.getItems().clear();
                    phones.addAll(phoneDaoSQL.searchByPrice(minPrice.getValue(),maxPrice.getValue()));
                    phonesTableView.refresh();
                    phonesTableView.setItems(phones);
                } catch (BuyerException e) {
                    System.out.println("Something went wrong with searchByCategory method from quoteDaoSQlImpl");
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void brandsEdit(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_brands.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Edit brands");
            stage.setScene(scene);
            stage.setResizable(false);
            EditBrandsController editBrandsController=new EditBrandsController();
            loader.setController(editBrandsController);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void phonesEdit(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_phones.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Edit phones");
            stage.setScene(scene);
            stage.setResizable(false);
            EditPhonesController editPhonesController=new EditPhonesController();
            loader.setController(editPhonesController);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exitAction(ActionEvent actionEvent) {
    }

    public void userButtonAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user_profile.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("User profile");
            stage.setScene(scene);
            stage.setResizable(false);
            UserProfileController userProfileController=new UserProfileController();
            userProfileController.setBuyer(buyer);
            loader.setController(userProfileController);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cartButtonAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("User profile");
            stage.setScene(scene);
            stage.setResizable(false);
            CartController cartController=new CartController();
            loader.setController(cartController);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchButtonAction(ActionEvent actionEvent) {
    }
}

