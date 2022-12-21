package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
                if (idName.getText().trim().isEmpty()) {
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
                if (idSurname.getText().trim().isEmpty()) {
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
        idAccountNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (idAccountNumber.getText().trim().isEmpty()) {
                    idAccountNumber.getStyleClass().removeAll("rightTextField");
                    idAccountNumber.getStyleClass().add("wrongTextField");
                } else {
                    idAccountNumber.getStyleClass().removeAll("wrongTextField");
                    idAccountNumber.getStyleClass().add("rightTextField");
                }
            }
        });
    }

    public void signupButtonAction(ActionEvent actionEvent) {
        boolean tacan_unos=true;
        if(!idName.getText().matches("^[a-zA-ZčćđžšČĆŽŠĐ\\d]{2,20}$") || idName.getText().isEmpty()) tacan_unos=false;
        if(!idSurname.getText().matches("^[a-zA-ZčćđžšČĆŽŠĐ\\d]{2,20}$") || idSurname.getText().isEmpty()) tacan_unos=false;
        if(!idEmail.getText().matches("^[\\w-]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$") || idEmail.getText().isEmpty()) tacan_unos=false;
        if(!idAccountNumber.getText().matches("^[0-9]{5,30}$") || idAccountNumber.getText().isEmpty()) tacan_unos=false;
        if(idPassword.getText().length()<8) tacan_unos=false;
        if(tacan_unos) System.out.println("Tacno");
        else System.out.println("Netacno");
    }
    public void loginButtonAction(ActionEvent actionEvent) throws IOException{
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
            stage.setTitle("Sing up");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage primaryStage = (Stage) idSignup.getScene().getWindow();
            primaryStage.hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
