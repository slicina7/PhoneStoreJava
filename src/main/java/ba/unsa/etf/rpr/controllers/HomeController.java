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
/**
 * JavaFX controller for home screen
 * @author Selma Ličina
 */
public class HomeController {

    //managers
    private final PurchaseManager purchaseManager=new PurchaseManager();
    private final PhoneManager phoneManager=new PhoneManager();

    //form components
    public ListView<Brand> brandsListView;
    public Label status;
    public TextField minPrice;
    public TextField maxPrice;
    public TableView<Phone> phonesTableView;
    public TableColumn<Phone,String> colPhonesId;
    public TableColumn<Phone,String> colPhonesVersion;
    public TableColumn<Phone,String> colPhonesDate;
    public TableColumn<Phone,String> colPhonesStock;
    public TableColumn<Phone,String> colPhonesPrice;

    int min=0;
    int max=4000;
    private ObservableList<Brand> brands;
    private ObservableList<Phone> phones;
    public Buyer buyer;

    public HomeController() {
        try {
            brands=FXCollections.observableArrayList(DaoFactory.brandDao().getAll());
            phones=FXCollections.observableArrayList();
        }catch (BuyerException e){
            System.out.println(e.getMessage());
        }
        buyer=new Buyer();
    }
    /**
     * Sets buyer
     * @param buyer - buyer to be sat
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @FXML
    public void initialize(){
        try {
            phones.addAll(phoneManager.getAll());
        }catch (BuyerException e){
            System.out.println(e.getMessage());
        }
        phonesTableView.setItems(phones);
        brandsListView.setItems(brands);
        colPhonesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPhonesVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        colPhonesDate.setCellValueFactory(new PropertyValueFactory<>("release_date"));
        colPhonesStock.setCellValueFactory(new PropertyValueFactory<>("in_stock"));
        colPhonesPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        brandsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldCat, newCat) -> {
            phones.clear();
            try {
                status.setText("Searching for phones with "+newCat.toString()+" brand.");
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
                if(p.getIn_stock()==0 )
                        new Alert(Alert.AlertType.ERROR,"Sorry , this phone is sold out.").show();
                else {
                    confirmationDialog(p);
                }
            }
        });
    }
    /**
     * Confirms if the buyer wants to buy that phone or not
     * @param p - phone that the buyer has clicked on
     */
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
    /**
     * Opens dialogs
     * @param file - fxml file of the dialog
     * @param controller - dialog controller
     * @param title -title of the dialog
     * @param icon -icon of the dialog
     */
    private void openDialog(String file,Object controller,String title,String icon){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+file));
            Scene scene = new Scene(loader.load(), USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
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
    /**
     * Brands edit event handler
     * Opens dialog for adding new brands
     * @param actionEvent
     */
    public void brandsEdit(ActionEvent actionEvent) {
        if(!buyer.getEmail().equals("owner@gmail.com"))
            new Alert(Alert.AlertType.ERROR,"Only owner can edit phones and brands!").show();
        else {
            status.setText("Opening edit brands");
            openDialog("edit_brands.fxml", new EditBrandsController(), "Edit brands", "phone.png");
        }
    }
    /**
     * Phone edit event handler
     * Opens dialog for adding new phones
     * @param actionEvent
     */
    public void phonesEdit(ActionEvent actionEvent) {
        if(!buyer.getEmail().equals("owner@gmail.com"))
            new Alert(Alert.AlertType.ERROR,"Only owner can edit phones and brands!").show();
        else {
            status.setText("Opening edit phones");
            openDialog("edit_phones.fxml", new EditPhonesController(), "Edit phones", "phone.png");
        }
    }

    /**
     * Closes home dialog
     * @param actionEvent
     */
    public void exitAction(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
    /**
     * User button event handler
     * Opens dialog with information of the buyer
     * @param actionEvent
     */
    public void userButtonAction(ActionEvent actionEvent) {
        status.setText("Opening user profile");
        openDialog("user_profile.fxml",new UserProfileController(),"User profile","user.png");
    }
    /**
     * Cart button event handler
     * Opens dialog with all purchases of that buyer
     * @param actionEvent
     */
    public void cartButtonAction(ActionEvent actionEvent) {
        status.setText("Opening cart");
        openDialog("cart.fxml",new CartController(),"Cart","cart.png");
    }
    /**
     * Opens dialog with information about the app
     * @param actionEvent
     */
    public void aboutAction(ActionEvent actionEvent) {
        status.setText("Opening about");
        openDialog("about.fxml",new AboutController(),"About","about.png");
    }
    /**
     * Opens dialog with names of all users of the app
     * @param actionEvent
     */
    public void usersAction(ActionEvent actionEvent) {
        status.setText("Viewing all app users");
        openDialog("users.fxml",new UsersController(),"Users","users.png");
    }
    /**
     * Search button event handler
     * Searches for all phones in that price range
     * @param actionEvent
     */
    public void priceRangeSearch(ActionEvent actionEvent) {
        try {
            if((!minPrice.getText().matches("^[0-9]{1,20}$") && !minPrice.getText().isEmpty()) || (!maxPrice.getText().matches("^[0-9]{1,20}$") && !maxPrice.getText().isEmpty()))
                new Alert(Alert.AlertType.ERROR,"Invalid input , try again ").show();
            else {
                if (!minPrice.getText().isEmpty())
                    min = Integer.parseInt(minPrice.getText());
                 else {
                    min=0;
                    minPrice.setText("0");
                }
                if (!maxPrice.getText().isEmpty()) max = Integer.parseInt(maxPrice.getText());
                else{
                    max=4000;
                    maxPrice.setText("4000");
                }
                if(min>max) {
                    new Alert(Alert.AlertType.ERROR,"Minimum price has to be lower than maximum price!").show();
                }else {
                    status.setText("Searching for phones with price range : " + min + " - " + max);
                    phonesTableView.getItems().clear();
                    phones.clear();
                    phones.addAll(DaoFactory.phoneDao().searchByPrice(min, max));
                    phonesTableView.refresh();
                    phonesTableView.setItems(phones);
                }
            }
        } catch (BuyerException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Refresh button event handler
     * Refreshes the phones table view
     * @param actionEvent
     */
    public void refreshButtonAction(ActionEvent actionEvent) {
        try {
            phones.clear();
            phones.addAll(phoneManager.getAll());
            phonesTableView.setItems(phones);
            status.setText("Refreshing phones table view.");
        }catch (BuyerException e){
            System.out.println(e.getMessage());
        }
    }
}

