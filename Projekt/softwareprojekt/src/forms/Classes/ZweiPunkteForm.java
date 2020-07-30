package forms.Classes;

import figures.Figure;
import figures.Line;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.TextField;
import vectorview.Backend;
import Math.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Sören Schuba
 * Controller der Zwei-Punkte-Form
 */
public class ZweiPunkteForm extends Form implements Initializable {

    //Textfelder
    public TextField tf_xr1_c1;
    public TextField tf_yr1_c1;
    public TextField tf_zr1_c1;
    public TextField tf_xr1_c2;
    public TextField tf_yr1_c2;
    public TextField tf_zr1_c2;
    public TextField tf_xr2;
    public TextField tf_yr2;
    public TextField tf_zr2;
    public TextField tf_lamda;

    public ZweiPunkteForm(){

    }

    /**
     * Author: Sören Schuba
     * Prompttext bestimmen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tf_xr1_c1.setPromptText("x");
        tf_yr1_c1.setPromptText("y");
        tf_zr1_c1.setPromptText("z");
        tf_xr1_c2.setPromptText("x");
        tf_yr1_c2.setPromptText("y");
        tf_zr1_c2.setPromptText("z");
        tf_xr2.setPromptText("x");
        tf_yr2.setPromptText("y");
        tf_zr2.setPromptText("z");
        tf_lamda.setPromptText("λ");

        // Füllt automatisch r1-Felder anderer Vektoren
        autoFill();

    }

    /**
     * Author: Sören Schuba
     * Textfelder Autofill
     * Füllt automatisch r1-Felder anderer Vektoren
     */
    private void autoFill(){
        //x-Werte
        tf_xr1_c1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_xr1_c2.setText(tf_xr1_c1.getText());
            }
        });

        tf_xr1_c2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_xr1_c1.setText(tf_xr1_c2.getText());
            }
        });

        //y-Werte
        tf_yr1_c1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_yr1_c2.setText(tf_yr1_c1.getText());
            }
        });

        tf_yr1_c2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_yr1_c1.setText(tf_yr1_c2.getText());
            }
        });


        //y-Werte
        tf_zr1_c1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_zr1_c2.setText(tf_zr1_c1.getText());
            }
        });

        tf_zr1_c2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_zr1_c1.setText(tf_zr1_c2.getText());
            }
        });
    }

    /**
     * Author: Sören Schuba
     * Textfelder leeren
     */
    @Override
    public void clearTextField(){
        tf_xr1_c1.clear();
        tf_yr1_c1.clear();
        tf_zr1_c1.clear();
        tf_xr1_c2.clear();
        tf_yr1_c2.clear();
        tf_zr1_c2.clear();
        tf_xr2.clear();
        tf_yr2.clear();
        tf_zr2.clear();
        tf_lamda.clear();
    }

    /**
     * Author: Sören Schuba
     * Anzeigen von Werten in der Zwei-Punkte-Form
     */
    private void print(Point3D r1, Point3D r2, double lamda){
        tf_xr1_c1.setText(Double.toString(r1.getX()));
        tf_yr1_c1.setText(Double.toString(r1.getY()));
        tf_zr1_c1.setText(Double.toString(r1.getZ()));
        tf_xr1_c2.setText(Double.toString(r1.getX()));
        tf_yr1_c2.setText(Double.toString(r1.getY()));
        tf_zr1_c2.setText(Double.toString(r1.getZ()));
        tf_xr2.setText(Double.toString(r2.getX()));
        tf_yr2.setText(Double.toString(r2.getY()));
        tf_zr2.setText(Double.toString(r2.getZ()));
        tf_lamda.setText(Double.toString(lamda));
    }

    /**
     * Author: Sören Schuba
     * Routine zum Anzeigen der Werte in der Zwei-Punkte-Form
     */
    @Override
    public void showFigure(Figure selectedfigure){
        //prüfen ob es eine Gerade ist
        if(selectedfigure.getClass().getSimpleName().equals("Line")){
            //wenn die Ebene nicht in der Zwei-Punkte-Form abgespeichert
            if(((Line)selectedfigure).getState() != 1){
                //konvertiere sie in die Zwei-Punkte-Form
                selectedfigure = Calculation.convertForm(((Line)selectedfigure), 2);
            }
            //Anzeigen
            print(
                    ((Line)selectedfigure).getR(),
                    ((Line)selectedfigure).getR2(),
                    ((Line)selectedfigure).getL()
            );
        }
    }

    /**
     * Author: Sören Schuba
     * Sperichern einer Gerade in Zwei-Punkte-Form
     */
    @Override
    public Figure save(int id, String name){
        //Formel-Variablen bestimmen
        double[] r1 = get_r1();
        double[] r2 = get_r2();
        Double lamda = get_lamda();
        if(lamda == null){
            tf_lamda.setText("1");
        }
        //Figure erzeugen
        if(r1 != null && r2 != null && !name.isEmpty() && id > 0) {
            Figure newfigure = Calculation.createLineZPF(
                    get_r1(),
                    get_r2(),
                    get_lamda(),
                    name, id);
            return newfigure;
        }else{
            return null;
        }
    }

    /**
     * Author: Sören Schuba
     * gibt Lamda zurück
     */
    public Double get_lamda(){
        //lamda bestimmen
        Double lamda;
        String str_lamda = tf_lamda.getText();
        if(Backend.isNumeric(str_lamda) && !str_lamda.isEmpty())
        {
            lamda = Double.parseDouble(str_lamda);
        }
        else{
            lamda = null;
        }
        return lamda;
    }


    /**
     * Author: Sören Schuba
     * Folgende Methoden konvertieren Vektoreingaben in einen Double-Array und geben diesen zurück
     */
    public double[] get_r1(){
        return Backend.convertingTFtoDouble(tf_xr1_c1, tf_yr1_c1, tf_zr1_c1);
    }

    public double[] get_r2(){
        return Backend.convertingTFtoDouble(tf_xr2, tf_yr2, tf_zr2);
    }
}
