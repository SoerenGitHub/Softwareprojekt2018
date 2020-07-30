package vectorview;

import figures.*;
import Math.*;
import forms.Classes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.web;

/**
 * Author: Sören Schuba
 * Das Backend managed alle Ein- und Ausgaben zum oder vom Frontend
 */
public class Backend implements Initializable{

    //Alle abgespeicherten Objekte
    private ArrayList<Figure> figures;

    //Subcontroller für die Darstellung der Ebene/Gerade
    @FXML
    private Form form;

    //Errorhandler
    private Errorhandler error = new Errorhandler();

    //Ausgewähltes Objekt
    private Figure selectedfigure = null;

    //SubScene = Darstellung
    private SubScene sub = null;

    //Visualisierungsschnittstelle
    private Visualization visual = null;

    // Farbenzähler für Plains
    private int colorCountP = 0;

    // Farbenzähler für Lines
    private int colorCountL = 0;

    // Farbenarray für Plains
    private Color[] colorListP = {Color.YELLOW, Color.ORANGE, Color.ORANGERED, Color.PINK, Color.HOTPINK, Color.PURPLE};

    // Farbenarray für Lines
    private Color[] colorListL = {Color.CYAN, Color.LIGHTBLUE, Color.LIGHTGREEN, Color.GREENYELLOW, Color.SEAGREEN, Color.OLIVE};



    //Alle Bestandteile in der Form
    public Button btn_delete;
    public AnchorPane p_main;
    public AnchorPane p_lefthandside;
    public ChoiceBox cb_objectname;
    public Slider sl_visibility;
    public ChoiceBox cb_form;
    public ToggleButton tb_visibility;
    public AnchorPane p_righthandside;
    public SplitPane splp;
    public TextField tf_objectname;
    public ChoiceBox cb_object;
    public AnchorPane p_inputtype;
    public Button btn_refresh;
    private boolean dis = false;



    //Konstruktor
     public Backend(){
         figures = new ArrayList<Figure>();
     }

    /**
     * Author: Sören Schuba
     * @param url
     * @param resourceBundle
     * Initialisierung der Form
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Visualisiert Ebenen und Geraden
        handleVisualisation();
        //abrufen, speichern und erzeugen von Objekten (Namensgebung)
        handleObjectNames();
        //Handling von Objekten Gerade und Ebene -> bestimmen der zur Verfügung stehenden Darstellungsformen
        handleObjects();
        //Sorgt nur für die Optische Darstellung einer Ebene/Gerade
        handleVectorForm();
        //Managed die Sichtbarkeit eines Objekts
        handleVisibility();
        //Mit dem Betätigen des Anwenden-Buttons wird eine Routine ausgelöst
        handleInput();
        //Löschen eines Objekts
        handleDelete();
        //Bestimmt die Fokussierung: Benutzeroberfläche <-> Visualisierung
        handleFocus();
        //Keyboardsteuerung
        handlekeyboard();
    }

    /**
     * Author: Sören Schuba
     * Bestimmt die Fokussierung: Benutzeroberfläche <-> Visualisierung
     */
    private void handleFocus(){
        p_righthandside.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for(int i = 0 ; i < p_lefthandside.getChildren().size() ; i++){
                    p_lefthandside.getChildren().get(i).setDisable(true);
                    p_righthandside.requestFocus();
                }
            }
        });
        p_lefthandside.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for(int i = 0 ; i < p_lefthandside.getChildren().size() ; i++){
                    p_lefthandside.getChildren().get(i).setDisable(false);
                    sl_visibility.setDisable(dis);
                    p_lefthandside.requestFocus();
                }
            }
        });
    }

    /**
     * Author: Sören Schuba
     * Visualisiert Ebenen und Geraden
     */
    private void handleVisualisation(){
        visual = new Visualization();
        sub = visual.start();
        sub.setPickOnBounds(true);
        p_righthandside.getChildren().add(sub);

        ChangeListener<Number> listener = new ChangeListener<Number>() {
            double height = 0;
            double width = 0;
            double previouswidth = 0;
            double previousheigth= 0;

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                height = p_righthandside.getHeight();
                width = p_righthandside.getWidth();

                if(previousheigth != height){
                    sub.setHeight(height);
                }
                if(previouswidth != width){
                    sub.setWidth(width);
                }

                previouswidth = width;
                previousheigth = height;
            }
        };
        p_righthandside.widthProperty().addListener(listener);
        p_righthandside.heightProperty().addListener(listener);
    }

    /**
     * Author: Sören Schuba
     * Funktion zum Löschen der Objekte
     */
    private void handleDelete(){
        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ist ein Objekt ausgewählt?
                if(selectedfigure != null){
                    int index = -1;
                    for(int i = 0; i < figures.size(); i++){
                        //Objektindex finden
                        if(figures.get(i).getID() == selectedfigure.getID()){
                            index = i;
                        }
                    }
                    //wenn index gefunden -> Wenn ausgewähltes Objekt existiert
                    if(index != -1){
                        // Löschen in der Visualisierung
                        // feststellen ob es sich um eine Ebene oder eine Linie handelt
                        String type = selectedfigure.getClass().getSimpleName();
                        if(type.equals("Plain")){
                            visual.deletePlane(selectedfigure.getID() - 1);
                        }
                        else if(type.equals("Line")){
                            visual.deleteLine(selectedfigure.getID() - 1);
                        }
                        //Aus Choicebox-Liste löschen
                        cb_objectname.getItems().remove(selectedfigure.getID());
                        //Aus Objekt-Liste löschen
                        figures.remove(index);
                        //Die ID des Objektes ist der Index in der Choicebox-Liste, deswegen: nachfolgende ID's anpassen
                        for(Figure fig : figures){
                            if(fig.getID() > index){
                                fig.setID((fig.getID()-1));
                            }
                        }
                        selectedfigure = null;
                        cb_objectname.getSelectionModel().selectFirst();
                    }
                }
                else{
                    error.printError(1);
                }
            }
        });
    }

    /**
     * Author: Sören Schuba, Luca König
     * In dieser Funktion werden je nach Darstellungsformen die entsprechenden Routinen ausgeführt
     */
    private void handleInput(){
        btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               //Soll ein Element bearbeitet oder erstellt werden?
               boolean generate = true;
               if(!cb_objectname.getSelectionModel().isEmpty() && cb_objectname.getSelectionModel().getSelectedItem().toString() != "+ hinzufügen"){
                    generate = false;
               }

               Figure newfigure = null;

               if(form != null && !tf_objectname.getText().isEmpty()) {

                   //id bestimmen
                   int id;
                   if(!generate){
                       id = cb_objectname.getSelectionModel().getSelectedIndex();
                   }else{
                       id = cb_objectname.getItems().size();
                   }

                   //Name bestimmen und Objekt erstellen
                   String name = tf_objectname.getText();

                   //Abfrage, ob der Name schon existiert
                   boolean exist = false;
                   for(Figure fig : figures){
                       if(name.equals(fig.getName())){
                           exist = true;
                       }
                   }

                   //Erstellen des neuen Objekts
                   newfigure = form.save(id, name);

                   if(newfigure != null){
                       //Speicherprozess, wenn das Objekt NEU erstellt werden soll
                       if(generate && !exist){
                           figures.add(newfigure);
                           cb_objectname.getItems().add(name);
                           // create object in Visualization
                           float[] va = newfigure.getAArr();
                           float[] vr = newfigure.getRArr();
                           if(newfigure.getClass().getSimpleName().equals("Plain")){
                               Plain coordPlain = Calculation.convertPDtoCoord((Plain) newfigure);
                               float a = (float) coordPlain.getCoords().getX();
                               float b = (float) coordPlain.getCoords().getY();
                               float c = (float) coordPlain.getCoords().getZ();
                               float d = (float) coordPlain.getC();
                               float[] vb = newfigure.getBArr();
                               Color color = colorListP[colorCountP % 6];
                               colorCountP++;
                               newfigure.setColor(color);
                               visual.createPlane(newfigure.getID() -1, vr, va, vb, a, b, c, d, color,true, Calculation.multiplicator((Plain)newfigure));
                           }else{
                               Color color = colorListL[colorCountL % 6];
                               colorCountL++;
                               newfigure.setColor(color);
                               visual.createLine(-1, vr, va, color);
                           }
                           tf_objectname.setText("");
                           form.clearTextField();
                       //Speicherprozess, wenn das Objekt BEARBEITET werden soll
                       }else if(!generate){
                           int index = (-1);
                           //Keine doppelte Namensgebung
                           boolean nameexist = false;
                           for(int i = 0; i < figures.size(); i++){
                               if(figures.get(i).getID() == id){
                                    index = i;
                               }
                               if(exist && figures.get(i).getName().equals(name) && figures.get(i).getID() != id){
                                    nameexist = true;
                               }
                           }
                           //Wenn Objekt wirklich existiert, aktualisieren
                           if(index != (-1) && !nameexist){
                               //In der Visualisierung anpassen
                               String existingfigure = figures.get(index).getClass().getSimpleName();

                               //altes löschen
                               if(existingfigure.equals("Plain")){
                                   visual.deletePlane(selectedfigure.getID() - 1);
                               }
                               else if(existingfigure.equals("Line")){
                                   visual.deleteLine(selectedfigure.getID() - 1);
                               }

                               String replacedfigure = newfigure.getClass().getSimpleName();

                               //neues einfügen
                               if(replacedfigure.equals("Plain")){
                                   Plain coordPlain = Calculation.convertPDtoCoord((Plain) newfigure);
                                   float[] va = newfigure.getAArr();
                                   float[] vr = newfigure.getRArr();
                                   float a = (float) coordPlain.getCoords().getX();
                                   float b = (float) coordPlain.getCoords().getY();
                                   float c = (float) coordPlain.getCoords().getZ();
                                   float d = (float) coordPlain.getC();
                                   float[] vb = newfigure.getBArr();
                                   Color color = selectedfigure.getColor();
                                   visual.createPlane(newfigure.getID(), vr, va, vb, a, b, c, d, color,true, Calculation.multiplicator((Plain)newfigure));
                                   newfigure.setColor(color);
                               }
                               else if(replacedfigure.equals("Line")){
                                   float[] va = newfigure.getAArr();
                                   float[] vr = newfigure.getRArr();
                                   Color color = selectedfigure.getColor();
                                   visual.createLine(-1, vr, va, color);
                                   newfigure.setColor(color);
                               }
                               //Im Backend anpassen
                               figures.set(index, newfigure);
                               cb_objectname.getItems().set(id, name);
                               cb_objectname.getSelectionModel().select(id);
                           }

                       }
                   }

               }
           }
        });
    }

    /**
     * Author: Sören Schuba
     * abrufen, speichern und erzeugen von Objekten (Namensgebung)
     */
    private void handleObjectNames(){
        cb_objectname.getItems().add("+ hinzufügen");
        cb_objectname.getSelectionModel().selectFirst();

        cb_objectname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int option = cb_objectname.getSelectionModel().getSelectedIndex();
                //Fall Objekt hinzufügen
                if(option == 0){
                    tf_objectname.setStyle("-fx-text-inner-color: white;");
                    selectedfigure = null;
                    tf_objectname.setText("");
                    form.clearTextField();
                //Fall Bestehendes Objekt
                }else{
                    tf_objectname.clear();
                    //Objektname anzeigen
                    if(!cb_objectname.getSelectionModel().isEmpty()){
                        tf_objectname.setText(cb_objectname.getSelectionModel().getSelectedItem().toString());
                    }
                    //Ausgewähltes Objekt global abspeichern
                    selectedfigure = null;
                    for(Figure fig : figures){
                        if(cb_objectname.getSelectionModel().getSelectedIndex() == fig.getID()){
                            selectedfigure = fig;
                        }
                    }
                    //vorgesehende Darstellungsform auswählen
                    if(selectedfigure != null) {
                        if(selectedfigure.getClass().getSimpleName().equals("Line")){
                            cb_object.getSelectionModel().select(1);
                            changeObject("Gerade");
                        }else if(selectedfigure.getClass().getSimpleName().equals("Plain")){
                            cb_object.getSelectionModel().select(0);
                            changeObject("Ebene");
                        }
                        //Darstellung mit Werten füllen
                        form.showFigure(selectedfigure);
                        //Textfarbe wählen
                        if (selectedfigure.getColor() != null) {
                            String colorhex = String.format( "#%02X%02X%02X",
                                    (int)( selectedfigure.getColor().getRed() * 255 ),
                                    (int)( selectedfigure.getColor().getGreen() * 255 ),
                                    (int)( selectedfigure.getColor().getBlue() * 255 ) );
                            if(colorhex != null){
                                tf_objectname.setStyle("-fx-text-fill: " + colorhex + ";");
                            }
                        }

                    }
                }
            }
        });
    }

    /**
     * Author: Sören Schuba
     * Handeling von Objekten Gerade und Ebene -> bestimmen der zur Verfügung stehenden Darstellungsformen
     */
    private void handleObjects(){
        cb_object.getItems().add("Ebene");
        cb_object.getItems().add("Gerade");

        cb_object.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String option = cb_object.getSelectionModel().getSelectedItem().toString();
                changeObject(option);
            }
        });
    }

    /**
     * Author: Sören Schuba
     * ändert von Ebene zu Gerade oder anders herum
     */
    private void changeObject(String option){
        if(option == "Ebene"){
            if(cb_form.getItems().size() > 0) {
                cb_form.setValue("");
                cb_form.getSelectionModel().clearSelection();
                cb_form.getItems().clear();
            }
            cb_form.getItems().add("Punkt-Richtungs-Form");
            cb_form.getItems().add("3-Punkte-Form");
            cb_form.getSelectionModel().selectFirst();
        }else {
            if(cb_form.getItems().size() > 0) {
                cb_form.setValue("");
                cb_form.getSelectionModel().clearSelection();
                cb_form.getItems().clear();
            }
            cb_form.getItems().add("Punkt-Richtungs-Form");
            cb_form.getItems().add("2-Punkte-Form");
            cb_form.getSelectionModel().selectFirst();
        }
    }

    /**
     * Author: Sören Schuba
     * Sorgt nur für die Optische Darstellung einer Ebene/Gerade
     */
    private void handleVectorForm(){
        //Eventlistener welcher Darstellungsform verändert
        cb_form.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Textfelder leeren
                if(cb_objectname.getSelectionModel().getSelectedIndex() == 0){
                    if(form != null){
                        form.clearTextField();
                    }
                }

                String filename = "";

                if(!cb_form.getSelectionModel().isEmpty()){
                    filename = cb_form.getSelectionModel().getSelectedItem().toString();
                }


                //Extraregel für Punkt-richtungs-Form, weil sie für Linie und Ebene existiert
                if(filename == "Punkt-Richtungs-Form"){
                    filename = filename + cb_object.getSelectionModel().getSelectedItem().toString();
                }
                //Extraregel für 3-Punkte-Form, weil der Button in diesem Fall nach unten verschoben werden muss
                if(filename == "3-Punkte-Form"){
                    btn_refresh.layoutYProperty().setValue(411);
                    btn_delete.layoutYProperty().setValue(411);
                }else{
                    btn_refresh.layoutYProperty().setValue(328);
                    btn_delete.layoutYProperty().setValue(328);
                }
                if(filename != ""){
                    String path_fxml = "/forms/"+filename+".fxml";

                    //Eingabendarstellung laden
                    Pane input = null;
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource(path_fxml));
                        input = (Pane)loader.load();
                        p_inputtype.getChildren().removeAll();
                        p_inputtype.getChildren().setAll(input);

                        //Controller laden
                        form = loader.getController();

                    } catch (IOException e) {
                        System.out.println("File not found!");
                    }
                    //Darstellung der Formel mit Werten anzeigen
                    if(selectedfigure != null) {
                        form.showFigure(selectedfigure);
                    }
                }
            }
        });
    }

    /**
     * Author: Luca König
     * Managed die Sichtbarkeit eines Objekts
     */
    private void handleVisibility(){
        // set the slider value
        sl_visibility.setShowTickMarks(true);
        sl_visibility.setShowTickLabels(false);
        sl_visibility.setMajorTickUnit(100);
        sl_visibility.setBlockIncrement(100);
        sl_visibility.setValue(sl_visibility.getMax());


        tb_visibility.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tb_visibility.isSelected() && selectedfigure != null){
                    tb_visibility.setStyle("-fx-border-color: red");
                    sl_visibility.setDisable(true);
                    dis = true;
                    selectedfigure.setVisibility(false);
                }else if(selectedfigure != null){
                    tb_visibility.setStyle("-fx-border-color: green");
                    sl_visibility.setDisable(false);
                    dis = false;
                    selectedfigure.setVisibility(true);
                }
                else{
                    error.printError(1);
                }
                // changes the visibility of an object
                if(selectedfigure != null) {
                    if (selectedfigure.getClass().getSimpleName().equals("Plain")) {
                        visual.toggleVisibility(selectedfigure.getID() - 1, true);
                    } else if (selectedfigure.getClass().getSimpleName().equals("Line")) {
                        visual.toggleVisibility(selectedfigure.getID() - 1, false);
                    }
                }
                else{
                    error.printError(1);
                }
            }
        });

        // creating a new listener for the slider
        ChangeListener<Number> slListener = new ChangeListener<Number>() {
            double oVis = 0;
            double nVis = 0;
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                nVis = sl_visibility.getValue();
                if(selectedfigure != null && selectedfigure.getVisibility()) {
                    if (oVis != nVis) {
                        int id = selectedfigure.getID() - 1;
                        float[] vr = selectedfigure.getRArr();
                        float[] va = selectedfigure.getAArr();
                        float[] vb = {0};
                        float a = 0;
                        float b = 0;
                        float c = 0;
                        float d = 0;
                        if (selectedfigure.getClass().getSimpleName().equals("Plain")) {
                            Plain coordPlain = Calculation.convertPDtoCoord((Plain) selectedfigure);
                            a = (float) coordPlain.getCoords().getX();
                            b = (float) coordPlain.getCoords().getY();
                            c = (float) coordPlain.getCoords().getZ();
                            d = (float) coordPlain.getC();
                        }
                        double opac = sl_visibility.getValue() / 100;
                        if (selectedfigure != null) {
                            selectedfigure.setOpac(opac);
                            if (selectedfigure.getClass().getSimpleName().equals("Plain")) {
                                vb = selectedfigure.getBArr();
                                visual.editFigure(id, vr, va, vb, a, b, c, d, Color.rgb((int)(selectedfigure.getColor().getRed() * 255), (int)(selectedfigure.getColor().getGreen() * 255), (int)(selectedfigure.getColor().getBlue() * 255), opac), true, Calculation.multiplicator((Plain)selectedfigure));
                            } else if (selectedfigure.getClass().getSimpleName().equals("Line")) {
                                visual.editFigure(id, vr, va, vb, a, b, c, d, Color.rgb((int)(selectedfigure.getColor().getRed() * 255), (int)(selectedfigure.getColor().getGreen() * 255), (int)(selectedfigure.getColor().getBlue() * 255), opac), false, 0);
                            }
                        }
                    }
                }
                else if(selectedfigure == null){
                    error.printError(1);
                }
            }
        };
        sl_visibility.valueProperty().addListener(slListener);

        ChangeListener<String> objListener = new ChangeListener<String>() {
            int oId = -1;
            int nId = -1;
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(selectedfigure != null) {
                    nId = selectedfigure.getID();
                    if (nId != oId) {
                        sl_visibility.setValue(selectedfigure.getOpac() * 100);
                        oId = nId;

                        if(selectedfigure.getVisibility() == true){
                            tb_visibility.setStyle("-fx-border-color: green");
                            sl_visibility.setDisable(false);
                        }
                        else{
                            tb_visibility.setStyle("-fx-border-color: red");
                            sl_visibility.setDisable(true);
                        }
                    }
                }
                else {
                    sl_visibility.setValue(sl_visibility.getMax());
                    tb_visibility.setStyle("-fx-border-color: green");
                    oId = -1;
                }
            }
        };
        cb_objectname.valueProperty().addListener(objListener);
    }

    /**
     * Author: Luca König
     * Function: handles the keyboard control in the visualization
     * see Actions in Visualization for description
     */
    private void handlekeyboard(){
        p_righthandside.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                        case R:
                            visual.reset();
                            break;
                        case LEFT:
                            if (selectedfigure != null && selectedfigure.getClass().getSimpleName().equals("Line")) {
                                visual.move_forward(selectedfigure.getID() - 1);
                            }
                            else if(selectedfigure != null){
                                visual.move_pos_x(selectedfigure.getID() - 1);
                            }
                            else {
                                error.printError(1);
                            }
                            break;

                        case RIGHT:
                            if (selectedfigure != null && selectedfigure.getClass().getSimpleName().equals("Line")) {
                                visual.move_back(selectedfigure.getID() - 1);
                            } else if(selectedfigure != null) {
                                visual.move_neg_x(selectedfigure.getID() - 1);
                            }
                            else{
                                error.printError(1);
                            }
                            break;

                        case UP:
                            if (selectedfigure != null && selectedfigure.getClass().getSimpleName().equals("Plain")) {
                                visual.move_pos_y(selectedfigure.getID() - 1);
                            }
                            else{
                                error.printError(1);
                            }
                            break;

                        case DOWN:
                            if (selectedfigure != null && selectedfigure.getClass().getSimpleName().equals("Plain")) {
                                visual.move_neg_y(selectedfigure.getID() - 1);
                            }
                            else{
                                error.printError(1);
                            }
                            break;

                        case X:
                            visual.toggle_axis();
                            break;

                        case NUMPAD0:
                            break;

                        case NUMPAD1:
                            visual.front_view();
                            break;

                        case NUMPAD2:
                            visual.add_tilt();
                            break;

                        case NUMPAD3:
                            visual.side_view();
                            break;

                        case NUMPAD4:
                            visual.sub_rotate();
                            break;

                        case NUMPAD5:
                            visual.reset();
                            break;

                        case NUMPAD6:
                            visual.add_rotate();
                            break;

                        case NUMPAD7:
                            visual.top_view();
                            break;

                        case NUMPAD8:
                            visual.sub_tilt();
                            break;

                        case NUMPAD9:
                            visual.flip_view();
                            break;
                    }
                }
        });
    }

    //-------------------------------------------------------------------------------Convertings-------------------------------------------------------

    /**
     * Author: Sören Schuba
     * Ist ein Textfeld eine Zahl?
     */
    public static boolean isNumeric(String str)
    {
        boolean first = true;
        boolean dot = false;

        for (char c : str.toCharArray())
        {
            if (!(Character.isDigit(c) || ((c == '.') && !first && !dot) || ((c == '-') && first))) {
                return false;
            }
            if(c == '.'){
                dot = true;
            }
            if(first){
                first = false;
            }
        }
        return true;
    }


    /**
     * Author: Sören Schuba
     * TextFeld zu Double
     */
    public static double[] convertingTFtoDouble(TextField tf_x, TextField tf_y, TextField tf_z){
        if(
                isNumeric(tf_x.getText())  &&
                isNumeric(tf_y.getText())  &&
                isNumeric(tf_z.getText())  &&
                !tf_x.getText().isEmpty()  &&
                !tf_y.getText().isEmpty()  &&
                !tf_z.getText().isEmpty()
        ){
            Double x = Double.parseDouble(tf_x.getText());
            Double y = Double.parseDouble(tf_y.getText());
            Double z = Double.parseDouble(tf_z.getText());
            return Calculation.generateDoubleArr(x, y, z);
        }else{
            return null;
        }
    }



}
