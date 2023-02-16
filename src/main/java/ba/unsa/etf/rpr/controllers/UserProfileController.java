package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Buyer;
import javafx.scene.control.Label;
/**
 * JavaFX controller for user profile
 * @author Selma Ličina
 */
public class UserProfileController {
    public Label userInformation;

    public Buyer buyer;

    public UserProfileController() {
        buyer=new Buyer();
    }
    /**
     * Sets buyer
     * @param buyer - buyer to be sat
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        userInformation.setText(buyer.toString());
    }

}
