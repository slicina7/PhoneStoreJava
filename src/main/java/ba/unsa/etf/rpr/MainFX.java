package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController=new MainController();
        fxmlLoader.setController(mainController);
        Scene scene = new Scene(root, 600, 500);
        stage.setResizable(false);
        stage.setTitle("Login");
        //stage.getIcons().add(new Image(""));
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String[] args) {
        launch();
    }
}
