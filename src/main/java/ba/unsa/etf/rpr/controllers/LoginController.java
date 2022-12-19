package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public TextField idEmail;
    public PasswordField idPassword;
    public Button idLogin;
    public Button idSignup;


    
    public void emailAction(ActionEvent actionEvent) {

    }

    public void passwordAction(ActionEvent actionEvent) {
    }

    public void loginButtonAction(ActionEvent actionEvent) throws IOException {
        BuyerDaoSQLImpl dao=new BuyerDaoSQLImpl();
        Buyer buyer=dao.searchByEmailAndPassword(idEmail.getText(),idPassword.getText());
        if(buyer.getName()==null){
            System.out.println("Greska");
        }else {
            System.out.println(buyer);

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
            stage.show();
            Stage primaryStage = (Stage) idSignup.getScene().getWindow();
            primaryStage.hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
