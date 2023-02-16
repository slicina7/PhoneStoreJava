package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.BuyerManager;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for signup
 * @author Selma Ličina
 */
public class SignupController {

    //manager
    private final BuyerManager buyerManager=new BuyerManager();

    //form components
    public TextField idName;
    public TextField idSurname;
    public TextField idEmail;
    public TextField idAccountNumber;
    public PasswordField idPassword;
    public Button idSignup;
    public Button idLogin;

    @FXML
    public void initialize() {
        idName.getStyleClass().add("rightTextField");
        idSurname.getStyleClass().add("rightTextField");
        idEmail.getStyleClass().add("rightTextField");
        idPassword.getStyleClass().add("rightTextField");
        idAccountNumber.getStyleClass().add("rightTextField");
        idName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {

                if (idName.getText().trim().isEmpty() || !idName.getText().matches("^[a-zA-Z]{2,20}$") || idName.getText().length()<2 || idName.getText().length()>30) {
                    idName.getStyleClass().removeAll("rightTextField");
                    idName.getStyleClass().add("wrongTextField");
                } else {
                    idName.getStyleClass().removeAll("wrongTextField");
                    idName.getStyleClass().add("rightTextField");
                }
            }
        });
        idSurname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (idSurname.getText().trim().isEmpty() || !idSurname.getText().matches("^[a-zA-Z]{2,20}$") || idSurname.getText().length()<2 || idSurname.getText().length()>30) {
                    idSurname.getStyleClass().removeAll("rightTextField");
                    idSurname.getStyleClass().add("wrongTextField");
                } else {
                    idSurname.getStyleClass().removeAll("wrongTextField");
                    idSurname.getStyleClass().add("rightTextField");
                }
            }
        });
        idEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (idEmail.getText().trim().isEmpty() || !idEmail.getText().matches("^[\\w-]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) {
                    idEmail.getStyleClass().removeAll("rightTextField");
                    idEmail.getStyleClass().add("wrongTextField");
                } else {
                    idEmail.getStyleClass().removeAll("wrongTextField");
                    idEmail.getStyleClass().add("rightTextField");
                }
            }
        });
        idPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (idPassword.getText().trim().isEmpty() || idPassword.getText().length()<8) {
                    idPassword.getStyleClass().removeAll("rightTextField");
                    idPassword.getStyleClass().add("wrongTextField");
                } else {
                    idPassword.getStyleClass().removeAll("wrongTextField");
                    idPassword.getStyleClass().add("rightTextField");
                }
            }
        });
        idAccountNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (idAccountNumber.getText().trim().isEmpty() || !idAccountNumber.getText().matches("^[0-9]{5,30}$")) {
                    idAccountNumber.getStyleClass().removeAll("rightTextField");
                    idAccountNumber.getStyleClass().add("wrongTextField");
                } else {
                    idAccountNumber.getStyleClass().removeAll("wrongTextField");
                    idAccountNumber.getStyleClass().add("rightTextField");
                }
            }
        });
    }

    /**
     * Login button event handler
     * opens login dialog
     * @param actionEvent
     */
    public void loginButtonAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/login.png"));
            stage.show();
            Stage primaryStage = (Stage) idLogin.getScene().getWindow();
            primaryStage.hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Signup button event handler
     * checks if entered name,surname,email,account number and password are valid and opens home dialog
     * @param actionEvent
     */
    public void signupButtonAction(ActionEvent actionEvent) {
        boolean tacan_unos=true;
        try {
        buyerManager.validateName(idName.getText());
        buyerManager.validateSurname(idSurname.getText());
        buyerManager.validateEmail(idEmail.getText());
        buyerManager.validateAccountNumber(idAccountNumber.getText());
        buyerManager.validatePassword(idPassword.getText());
        }
        catch(BuyerException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            tacan_unos=false;
        }


        if(tacan_unos){
            Random random=new Random();
            Buyer buyer=new Buyer(idName.getText(),idSurname.getText(),idEmail.getText(),idAccountNumber.getText(),idPassword.getText(),random.nextInt(50000));
            try {
                buyerManager.insert(buyer);
            }catch (BuyerException e){
                System.out.println(e.getMessage());
            }
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                stage.setTitle("Home page");
                stage.setScene(scene);
                stage.setResizable(false);
                HomeController homeController=loader.getController();
                homeController.setBuyer(buyer);
                loader.setController(homeController);
                stage.show();
                Stage primaryStage = (Stage) idSignup.getScene().getWindow();
                primaryStage.hide();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
