package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.PhoneManager;
import ba.unsa.etf.rpr.business.PurchaseManager;
import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import ba.unsa.etf.rpr.domain.Purchase;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    private final PurchaseManager purchaseManager=new PurchaseManager();
    private final PhoneManager phoneManager=new PhoneManager();
    public ListView<Brand> brandsListView;
    public Spinner<Integer> minPrice;
    public Spinner<Integer> maxPrice;
    private ObservableList<Brand> brands;
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
            brands=FXCollections.observableArrayList(DaoFactory.brandDao().getAll());
            phones=FXCollections.observableArrayList();
        }catch (BuyerException e){
            new BuyerException(e.getMessage(),e);
        }
        buyer=new Buyer();
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @FXML
    public void initialize(){

        SpinnerValueFactory<Integer> minvalueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,0,3500,500);
        minPrice.setValueFactory(minvalueFactory);
        SpinnerValueFactory<Integer> maxvalueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(500,4000,4000,500);
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
                phones.addAll(DaoFactory.phoneDao().searchByBrand(newCat));
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
                    phones.addAll(DaoFactory.phoneDao().searchByPrice(minPrice.getValue(),maxPrice.getValue()));
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
                    phones.addAll(DaoFactory.phoneDao().searchByPrice(minPrice.getValue(),maxPrice.getValue()));
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
                new Alert(Alert.AlertType.ERROR,"You don't have enough money , sorry").show();
            }else{
                try {
                    purchaseManager.insert(new Purchase(buyer,p));
                    int s=buyer.getAccount_balance();
                    buyer.setAccount_balance(s-p.getPrice());
                    BuyerDaoSQLImpl buyerDaoSQL=new BuyerDaoSQLImpl();
                    buyerDaoSQL.update(buyer);
                    int a=p.getIn_stock();
                    p.setIn_stock(a-1);
                    phoneManager.update(p);
                }catch (BuyerException e){
                }
            }
        } else {
            // ... user chose to close the dialog
        }

    }
    private void openDialog(String file,Object controller,String title,String icon){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+file));
            Scene scene = new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/"+icon));
            if(title.equals("User profile")) {
                UserProfileController userProfileController=loader.getController();
                userProfileController.setBuyer(buyer);
                loader.setController(userProfileController);
            }else if(title.equals("Cart")) {
                CartController cartController=loader.getController();
                cartController.setBuyer(buyer);
                loader.setController(cartController);
            }
            else  loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void brandsEdit(ActionEvent actionEvent) {
        openDialog("edit_brands.fxml",new EditBrandsController(),"Edit brands","phone.png");
    }

    public void phonesEdit(ActionEvent actionEvent) {
        openDialog("edit_phones.fxml",new EditPhonesController(),"Edit phones","phone.png");
    }

    public void exitAction(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void userButtonAction(ActionEvent actionEvent) {
        openDialog("user_profile.fxml",new UserProfileController(),"User profile","user.png");
    }

    public void cartButtonAction(ActionEvent actionEvent) {
        openDialog("cart.fxml",new CartController(),"Cart","cart.png");
    }

    public void aboutAction(ActionEvent actionEvent) {
        openDialog("about.fxml",new AboutController(),"About","about.png");
    }

    public void usersAction(ActionEvent actionEvent) {
        openDialog("users.fxml",new UsersController(),"Users","users.png");
    }
}

