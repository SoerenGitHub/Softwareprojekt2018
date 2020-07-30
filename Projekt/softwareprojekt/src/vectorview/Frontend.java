package vectorview;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Frontend {

    private Stage primaryStage;
    private Scene scene = null;

    public Frontend(Stage primaryStage){
        this.primaryStage = primaryStage;
        initialisation();
    }

    private void initialisation(){
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("Frontend.fxml"));
            primaryStage.setMinWidth(500);
            primaryStage.setMinHeight(500);
            scene = new Scene(root, 1000, 510);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vectorview");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
