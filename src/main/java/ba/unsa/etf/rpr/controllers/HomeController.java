package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        try {
            brandDaoSQL=new BrandDaoSQLImpl();
            phoneDaoSQL=new PhoneDaoSQLImpl();
            brands=FXCollections.observableArrayList(brandDaoSQL.getAll());
            phones=FXCollections.observableArrayList();
        }catch (BuyerException e){

        }

        buyer=new Buyer();

    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
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
        phonesTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Phone p=phonesTableView.getItems().get(
                        phonesTableView.getSelectionModel().getSelectedIndex()
                );
                confirmationDialog(p);
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
    public void confirmationDialog(Phone p){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to buy this phone?");
        alert.setContentText(p.getBrand().getName()+" "+p.getVersion()+" is worth "+p.getPrice()+" and you have "+buyer.getAccount_balance()+" in your account");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes){
            if(buyer.getAccount_balance()<p.getPrice()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error Dialog");
                error.setHeaderText(null);
                error.setContentText("You don't have enough money , sorry");
                error.showAndWait();
            }else{
                try {
                    PurchaseDaoSQLImpl purchaseDaoSQL=new PurchaseDaoSQLImpl();
                    Purchase purchase=new Purchase();
                    purchase.setBuyer(buyer);
                    purchase.setPhone(p);
                    purchaseDaoSQL.insert(purchase);
                    int s=buyer.getAccount_balance();
                    buyer.setAccount_balance(s-p.getPrice());
                    BuyerDaoSQLImpl buyerDaoSQL=new BuyerDaoSQLImpl();
                    buyerDaoSQL.update(buyer);
                    int a=p.getIn_stock();
                    p.setIn_stock(a-1);
                    phoneDaoSQL.update(p);
                }catch (BuyerException e){

                }

            }
        } else {
            // ... user chose to close the dialog
        }

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
            UserProfileController userProfileController=loader.getController();
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

}

