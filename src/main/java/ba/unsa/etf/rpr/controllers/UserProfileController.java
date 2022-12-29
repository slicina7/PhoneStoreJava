package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Buyer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserProfileController {
    public Label userInformation;

    public Buyer buyer;

    public UserProfileController() {
        buyer=new Buyer();
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;

    }
    @FXML
    public void initialize(){
        userInformation.setText(buyer.toString());
    }
}
