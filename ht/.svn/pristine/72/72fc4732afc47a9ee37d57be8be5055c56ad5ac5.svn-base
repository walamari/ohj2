package liigarekisteri;

import javafx.application.Application;
import javafx.stage.Stage;
import liiga.Liiga;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Valtteri Peltoniemi & Konsta Kähtävä
 * @version 8.3.2019
 *
 */
public class FxliigaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("FxliigaGUIView.fxml"));
            final Pane root = ldr.load();                                                               //final koska atribuutti saa kerran arvon muodostajassa
            final FxliigaGUIController liigaCtrl = (FxliigaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("fxliiga.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("fxliiga");
          
            Liiga liiga = new Liiga();
            liigaCtrl.setLiiga(liiga);
            
            primaryStage.show();
            
            liigaCtrl.lueTiedosto("");
            

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Käynnistetään ohjelma
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}