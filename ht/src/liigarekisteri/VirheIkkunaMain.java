package liigarekisteri;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author valtp
 * @version 30.1.2019
 *
 */
public class VirheIkkunaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("VirheIkkunaGUIView.fxml"));
            final Pane root = ldr.load();
            //final VirheIkkunaGUIController virheikkunaCtrl = (VirheIkkunaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("virheikkuna.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("virheIkkuna");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}