package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignupController {

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

                if (idName.getText().trim().isEmpty() || !idName.getText().matches("^[a-zA-ZčćđžšČĆŽŠĐ]{2,20}$")) {
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
                if (idSurname.getText().trim().isEmpty() || !idSurname.getText().matches("^[a-zA-ZčćđžšČĆŽŠĐd]{2,20}$")) {
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


    public void loginButtonAction(ActionEvent actionEvent) throws IOException{
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage primaryStage = (Stage) idLogin.getScene().getWindow();
            primaryStage.hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void signupButtonAction(ActionEvent actionEvent) {
        boolean tacan_unos=true;
        String greska="";
        if(!idName.getText().matches("^[a-zA-ZčćđžšČĆŽŠĐ]{2,20}$") || idName.getText().isEmpty()){
            tacan_unos=false;
            greska=greska+"Name can only contain letters and has to be longer than one letter!\n";
        }
        if(!idSurname.getText().matches("^[a-zA-ZčćđžšČĆŽŠĐd]{2,20}$") || idSurname.getText().isEmpty()){
            tacan_unos=false;
            greska=greska+"Surname can only contain letters and has to be longer than one letter!\n";
        }
        if(!idEmail.getText().matches("^[\\w-]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$") || idEmail.getText().isEmpty()){
            tacan_unos=false;
            greska=greska+"Invalid email input! \n";
        }
        if(!idAccountNumber.getText().matches("^[0-9]{5,30}$") || idAccountNumber.getText().isEmpty()){
            tacan_unos=false;
            greska=greska+"Account Number can only contain numbers!\n";
        }
        if(idPassword.getText().length()<8){
            tacan_unos=false;
            greska=greska+"Password must be longer than 7! \n";
        }
        if(tacan_unos){
            Buyer buyer=new Buyer();
            buyer.setName(idName.getText());
            buyer.setSurname(idSurname.getText());
            buyer.setEmail(idEmail.getText());
            buyer.setAccount_number(idAccountNumber.getText());
            buyer.setPassword(idPassword.getText());
            Random random=new Random();
            buyer.setAccount_balance(random.nextInt(5000));
            BuyerDaoSQLImpl buyerDaoSQL=new BuyerDaoSQLImpl();
            buyerDaoSQL.insert(buyer);
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
                stage.setTitle("Home page");
                stage.setScene(scene);
                stage.setResizable(false);
                HomeController homeController=new HomeController();
                homeController.setBuyer(buyer);
                loader.setController(homeController);
                stage.show();
                Stage primaryStage = (Stage) idSignup.getScene().getWindow();
                primaryStage.hide();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().setPrefSize(400, 250);
            alert.setTitle("Incorrect input");
            alert.setHeaderText("Error");
            alert.setContentText(greska);
            alert.showAndWait();
        }
    }
}
