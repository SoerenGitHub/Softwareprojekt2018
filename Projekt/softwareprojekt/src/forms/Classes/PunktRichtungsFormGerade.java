package forms.Classes;

        import figures.Figure;
        import figures.Line;
        import javafx.fxml.Initializable;
        import javafx.geometry.Point3D;
        import javafx.scene.control.TextField;
        import vectorview.Backend;
        import Math.*;

        import java.net.URL;
        import java.util.ResourceBundle;

/**
 * Author: Sören Schuba
 * Controller der Punkt-Richtungs-Form einer Gerade
 */
public class PunktRichtungsFormGerade extends Form implements Initializable {

    //Textfelder
    public TextField tf_xr1;
    public TextField tf_yr1;
    public TextField tf_zr1;
    public TextField tf_xa;
    public TextField tf_ya;
    public TextField tf_za;
    public TextField tf_lamda;

    public PunktRichtungsFormGerade(){

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
        tf_lamda.setPromptText("λ");
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
        tf_lamda.clear();
    }

    /**
     * Author: Sören Schuba
     * Anzeigen von Werten in der Punktrichtungsform einer Gerade
     */
    private void print(Point3D r1, Point3D a, double lamda){
        tf_xr1.setText(Double.toString(r1.getX()));
        tf_yr1.setText(Double.toString(r1.getY()));
        tf_zr1.setText(Double.toString(r1.getZ()));
        tf_xa.setText(Double.toString(a.getX()));
        tf_ya.setText(Double.toString(a.getY()));
        tf_za.setText(Double.toString(a.getZ()));
        tf_lamda.setText(Double.toString(lamda));
    }

    /**
     * Author: Sören Schuba
     * Routine zum Anzeigen der Werte in der Punkt-Richtungs-Form
     */
    @Override
    public void showFigure(Figure selectedfigure){
        //Ausgewählte Figure eine Gerade?
        if(selectedfigure.getClass().getSimpleName().equals("Line")){
            //wenn Gerade nicht in der Punkt-Richtungs-Form angezeigt wird
            if(((Line)selectedfigure).getState() != 2){
                //konvertiere in Punkt-Richtungs-Form
                selectedfigure = Calculation.convertForm(((Line)selectedfigure), 1);
            }
            print(
                    ((Line)selectedfigure).getR(),
                    ((Line)selectedfigure).getA(),
                    ((Line)selectedfigure).getL()
            );
        }
    }

    /**
     * Author: Sören Schuba
     * Sperichern einer Gerade in Punkt-Richtungs-Form
     */
    @Override
    public Figure save(int id, String name){
        //Formel-Variablen bestimmen
        double[] r1 = get_r1();
        double[] a = get_a();
        Double lamda = get_lamda();
        if(lamda == null){
            tf_lamda.setText("1");
        }
        //Figure erzeugen
        if(r1 != null && a != null && !name.isEmpty() && id > 0) {
            Figure newfigure = Calculation.createLinePRF(
                    get_r1(),
                    get_a(),
                    get_lamda(),
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
     * Folgende Methoden konvertieren Vektoreingaben in einen Double-Array und geben diesen zurück
     */
    public double[] get_r1(){
        return Backend.convertingTFtoDouble(tf_xr1, tf_yr1, tf_zr1);
    }

    public double[] get_a(){
        return Backend.convertingTFtoDouble(tf_xa, tf_ya, tf_za);
    }
}

