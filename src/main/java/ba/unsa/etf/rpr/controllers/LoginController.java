package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.BuyerManager;
import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
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

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    private final BuyerManager buyerManager=new BuyerManager();
    public Buyer buyer;
    public TextField idEmail;
    public PasswordField idPassword;
    public Button idLogin;
    public Button idSignup;

    @FXML
    public void initialize() {
        idEmail.getStyleClass().add("rightTextField");
        idPassword.getStyleClass().add("rightTextField");
        idEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (idEmail.getText().trim().isEmpty()) {
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
                if (idPassword.getText().trim().isEmpty()) {
                    idPassword.getStyleClass().removeAll("rightTextField");
                    idPassword.getStyleClass().add("wrongTextField");
                } else {
                    idPassword.getStyleClass().removeAll("wrongTextField");
                    idPassword.getStyleClass().add("rightTextField");
                }
            }
        });
    }

    public void loginButtonAction(ActionEvent actionEvent) throws BuyerException {
        buyer=null;
        try {
            buyer=buyerManager.searchByEmailAndPassword(idEmail.getText(), idPassword.getText());
        }catch(BuyerException message){
            new Alert(Alert.AlertType.ERROR,message.getMessage()).show();
            return;
        }
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
            stage.setTitle("Home page");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/home.png"));
            HomeController homeController=loader.getController();
            homeController.setBuyer(buyer);
            loader.setController(homeController);
            stage.show();
            Stage primaryStage = (Stage) idLogin.getScene().getWindow();
            primaryStage.hide();
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
    }

    public void signupButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/signup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
            stage.setTitle("Sing up");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/login.png"));
            stage.show();
            Stage primaryStage = (Stage) idSignup.getScene().getWindow();
            primaryStage.hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
