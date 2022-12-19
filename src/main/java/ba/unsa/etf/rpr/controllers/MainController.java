package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {
    public TextField idEmail;
    public PasswordField idPassword;
    public Button idLogin;

    public void emailAction(ActionEvent actionEvent) {
    }

    public void passwordAction(ActionEvent actionEvent) {
    }

    public void loginButtonAction(ActionEvent actionEvent) {
        BuyerDaoSQLImpl daobuyer=new BuyerDaoSQLImpl();

        Buyer buyer=daobuyer.searchByEmailAndPassword(idEmail.getText(),idPassword.getText());
        System.out.println(buyer);
    }
}
