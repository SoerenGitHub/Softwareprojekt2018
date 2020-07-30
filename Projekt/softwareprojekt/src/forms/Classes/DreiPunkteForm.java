package forms.Classes;

import figures.Figure;
import figures.Plain;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.TextField;
import vectorview.Backend;
import Math.Calculation;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Sören Schuba
 * Controller der Drei-Punkte-Form
 */
public class DreiPunkteForm extends Form implements Initializable {
    //Textfelder
    public TextField tf_xr1_c1;
    public TextField tf_yr1_c1;
    public TextField tf_zr1_c1;
    public TextField tf_xr1_c2;
    public TextField tf_yr1_c2;
    public TextField tf_zr1_c2;
    public TextField tf_xr1_c3;
    public TextField tf_yr1_c3;
    public TextField tf_zr1_c3;
    public TextField tf_xr2;
    public TextField tf_yr2;
    public TextField tf_zr2;
    public TextField tf_xr3;
    public TextField tf_yr3;
    public TextField tf_zr3;
    public TextField tf_lamda;
    public TextField tf_mu;

    public DreiPunkteForm(){

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
        tf_xr1_c3.setPromptText("x");
        tf_yr1_c3.setPromptText("y");
        tf_zr1_c3.setPromptText("z");
        tf_xr2.setPromptText("x");
        tf_yr2.setPromptText("y");
        tf_zr2.setPromptText("z");
        tf_xr3.setPromptText("x");
        tf_yr3.setPromptText("y");
        tf_zr3.setPromptText("z");
        tf_lamda.setPromptText("λ");
        tf_mu.setPromptText("μ");

        //Füllt automatisch r1-Felder anderer Vektoren
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
                tf_xr1_c3.setText(tf_xr1_c1.getText());
            }
        });

        tf_xr1_c2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_xr1_c1.setText(tf_xr1_c2.getText());
                tf_xr1_c3.setText(tf_xr1_c2.getText());
            }
        });
        tf_xr1_c3.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_xr1_c2.setText(tf_xr1_c3.getText());
                tf_xr1_c1.setText(tf_xr1_c3.getText());
            }
        });

        //y-Werte
        tf_yr1_c1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_yr1_c2.setText(tf_yr1_c1.getText());
                tf_yr1_c3.setText(tf_yr1_c1.getText());
            }
        });

        tf_yr1_c2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_yr1_c1.setText(tf_yr1_c2.getText());
                tf_yr1_c3.setText(tf_yr1_c2.getText());
            }
        });
        tf_yr1_c3.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_yr1_c2.setText(tf_yr1_c3.getText());
                tf_yr1_c1.setText(tf_yr1_c3.getText());
            }
        });

        //y-Werte
        tf_zr1_c1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_zr1_c2.setText(tf_zr1_c1.getText());
                tf_zr1_c3.setText(tf_zr1_c1.getText());
            }
        });

        tf_zr1_c2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_zr1_c1.setText(tf_zr1_c2.getText());
                tf_zr1_c3.setText(tf_zr1_c2.getText());
            }
        });
        tf_zr1_c3.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tf_zr1_c2.setText(tf_zr1_c3.getText());
                tf_zr1_c1.setText(tf_zr1_c3.getText());
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
        tf_xr1_c3.clear();
        tf_yr1_c3.clear();
        tf_zr1_c3.clear();
        tf_xr2.clear();
        tf_yr2.clear();
        tf_zr2.clear();
        tf_xr3.clear();
        tf_yr3.clear();
        tf_zr3.clear();
        tf_lamda.clear();
        tf_mu.clear();
    }

    /**
     * Author: Sören Schuba
     * Anzeigen von Werten in der Drei-Punkte-Form
     */
    private void print(Point3D r1, Point3D r2, Point3D r3, double lamda, double mu){
        tf_xr1_c1.setText(Double.toString(r1.getX()));
        tf_yr1_c1.setText(Double.toString(r1.getY()));
        tf_zr1_c1.setText(Double.toString(r1.getZ()));
        tf_xr1_c2.setText(Double.toString(r1.getX()));
        tf_yr1_c2.setText(Double.toString(r1.getY()));
        tf_zr1_c2.setText(Double.toString(r1.getZ()));
        tf_xr1_c3.setText(Double.toString(r1.getX()));
        tf_yr1_c3.setText(Double.toString(r1.getY()));
        tf_zr1_c3.setText(Double.toString(r1.getZ()));
        tf_xr2.setText(Double.toString(r2.getX()));
        tf_yr2.setText(Double.toString(r2.getY()));
        tf_zr2.setText(Double.toString(r2.getZ()));
        tf_xr3.setText(Double.toString(r3.getX()));
        tf_yr3.setText(Double.toString(r3.getY()));
        tf_zr3.setText(Double.toString(r3.getZ()));
        tf_lamda.setText(Double.toString(lamda));
        tf_mu.setText(Double.toString(mu));
    }

    /**
     * Author: Sören Schuba
     * Routine zum Anzeigen der Werte in der Drei-Punkte-Form
     */
    @Override
    public void showFigure(Figure selectedfigure){
        //prüfen ob es eine Ebene ist
        if(selectedfigure.getClass().getSimpleName().equals("Plain")){
            //wenn die Ebene nicht in der Drei-Punkte-Form abgespeichert
            if(((Plain)selectedfigure).getState() != 2){
                //konvertiere sie in die Drei-Punkte-Form
                selectedfigure = Calculation.convertForm(((Plain)selectedfigure), 1);
            }
            //Anzeigen
            print(
                    ((Plain)selectedfigure).getR(),
                    ((Plain)selectedfigure).getR2(),
                    ((Plain)selectedfigure).getR3(),
                    ((Plain)selectedfigure).getL(),
                    ((Plain)selectedfigure).getM()
            );
        }
    }

    /**
     * Author: Sören Schuba
     * Speichern einer Ebene in Drei-Punkte-Form
     */
    @Override
    public Figure save(int id, String name){
        //Formel-Variablen bestimmen
        double[] r1 = get_r1();
        double[] r2 = get_r2();
        double[] r3 = get_r3();
        Double lamda = get_lamda();
        Double mu = get_mu();

        if(lamda == null){
            tf_lamda.setText("1");
        }
        if(mu == null){
            tf_mu.setText("1");
        }

        //Figure erzeugen
        if(r1 != null && r2 != null && r3 != null && !name.isEmpty() && id > 0) {
            Figure newfigure = Calculation.createPlainDPF(
                    get_r1(),
                    get_r2(),
                    get_r3(),
                    get_lamda(),
                    get_mu(),
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
     * gibt Mu zurück
     */
    public Double get_mu(){
        //mu bestimmen
        Double mu;
        String str_mu = tf_mu.getText();
        if(Backend.isNumeric(str_mu) && !str_mu.isEmpty())
        {
            mu = Double.parseDouble(str_mu);
        }
        else{
            mu = null;
        }
        return mu;
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

    public double[] get_r3(){
        return Backend.convertingTFtoDouble(tf_xr3, tf_yr3, tf_zr3);
    }

}
