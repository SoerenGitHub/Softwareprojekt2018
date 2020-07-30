package forms.Classes;

import figures.Figure;
import figures.Plain;
import Math.*;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.TextField;
import vectorview.Backend;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Sören Schuba
 * Controller der Punkt-Richtungs-Form einer Ebene
 */
public class PunktRichtungsFormEbene extends Form implements Initializable {

    //Textfelder
    public TextField tf_xr1;
    public TextField tf_yr1;
    public TextField tf_zr1;
    public TextField tf_xa;
    public TextField tf_ya;
    public TextField tf_za;
    public TextField tf_xb;
    public TextField tf_yb;
    public TextField tf_zb;
    public TextField tf_lamda;
    public TextField tf_mu;

    public PunktRichtungsFormEbene(){

    }

    /**
     * Author: Sören Schuba
     * Prompttext bestimmen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tf_xr1.setPromptText("x");
        tf_yr1.setPromptText("y");
        tf_zr1.setPromptText("z");
        tf_xa.setPromptText("x");
        tf_ya.setPromptText("y");
        tf_za.setPromptText("z");
        tf_xb.setPromptText("x");
        tf_yb.setPromptText("y");
        tf_zb.setPromptText("z");
        tf_lamda.setPromptText("λ");
        tf_mu.setPromptText("μ");
    }

    /**
     * Author: Sören Schuba
     * Textfelder leeren
     */
    @Override
    public void clearTextField(){
        tf_xr1.clear();
        tf_yr1.clear();
        tf_zr1.clear();
        tf_xa.clear();
        tf_ya.clear();
        tf_za.clear();
        tf_xb.clear();
        tf_yb.clear();
        tf_zb.clear();
        tf_lamda.clear();
        tf_mu.clear();
    }

    /**
     * Author: Sören Schuba
     * Anzeigen von Werten in der Punktrichtungsform einer Ebene
     */
    private void print(Point3D r1, Point3D a, Point3D b, double lamda, double mu){
        tf_xr1.setText(Double.toString(r1.getX()));
        tf_yr1.setText(Double.toString(r1.getY()));
        tf_zr1.setText(Double.toString(r1.getZ()));
        tf_xa.setText(Double.toString(a.getX()));
        tf_ya.setText(Double.toString(a.getY()));
        tf_za.setText(Double.toString(a.getZ()));
        tf_xb.setText(Double.toString(b.getX()));
        tf_yb.setText(Double.toString(b.getY()));
        tf_zb.setText(Double.toString(b.getZ()));
        tf_lamda.setText(Double.toString(lamda));
        tf_mu.setText(Double.toString(mu));
    }

    /**
     * Author: Sören Schuba
     * Routine zum Anzeigen der Werte in der Punkt-Richtungs-Form
     */
    @Override
    public void showFigure(Figure selectedfigure){
        //Ausgewählte Figure eine Ebene?
        if(selectedfigure.getClass().getSimpleName().equals("Plain")){
            //wenn Ebene nicht in der Punkt-Richtungs-Form angezeigt wird
            if(((Plain)selectedfigure).getState() != 1){
                //konvertiere in Punkt-Richtungs-Form
                selectedfigure = Calculation.convertForm(((Plain)selectedfigure), 2);
            }
            print(
                    ((Plain)selectedfigure).getR(),
                    ((Plain)selectedfigure).getA(),
                    ((Plain)selectedfigure).getB(),
                    ((Plain)selectedfigure).getL(),
                    ((Plain)selectedfigure).getM()
            );
        }
    }

    /**
     * Author: Sören Schuba
     * Sperichern einer Ebene in Punkt-Richtungs-Form
     */
    @Override
    public Figure save(int id, String name){
        //Formel-Variablen bestimmen
        double[] r1 = get_r1();
        double[] a = get_a();
        double[] b = get_b();
        Double lamda = get_lamda();
        Double mu = get_mu();
        if(lamda == null){
            tf_lamda.setText("1");
        }
        if(mu == null){
            tf_mu.setText("1");
        }
        //Figure erzeugen
        if(r1 != null && a != null && b != null && !name.isEmpty() && id > 0) {
            Figure newfigure = Calculation.createPlainPRF(
                    get_r1(),
                    get_a(),
                    get_b(),
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
     * Gibt Lamda zurück
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
     * Gibt Mu zurück
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
        return Backend.convertingTFtoDouble(tf_xr1, tf_yr1, tf_zr1);
    }

    public double[] get_a(){
        return Backend.convertingTFtoDouble(tf_xa, tf_ya, tf_za);
    }

    public double[] get_b(){
        return Backend.convertingTFtoDouble(tf_xb, tf_yb, tf_zb);
    }
}

