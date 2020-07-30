package vectorview;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;



//Mit dieser Klasse kann eine Fehlermeldung erstellt werden
//Eine Fehlermeldung erstellen:
//  1. In der Funktion "define()" wie vorgegeben Fehler-Text abspeichern
//  2. An der stelle, wo der Fehler auftritt / auftreten kann, die Funktion "printError(fehlerindex)" benutzen.

public class Errorhandler extends JDialog {

    //Formparts
    private JPanel contentPane;
    private JButton okay;
    private JTextArea meldung;
    private JLabel icon;

    // Liste aller Errors
    private ArrayList<String> error = new ArrayList<String>();

    public Errorhandler() {
        define();
        setContentPane(contentPane);
        setUndecorated(true);
        setModal(true);
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }






    //Hier alle Errors abspeichern!----------------------------------------------------------------------------------------------------------------------
    private void define() {
        //Beispiel:
        //Weil erster eintrag, Errorcode = 0.
        error.add("Hier ein Test, Text der Fehlermeldung");
        error.add("Bitte ein Objekt auswählen");
    }






    //Zeigt eine Errormeldung an
    public void printError(int id){
        Errorhandler errorform = createError();
        if(error.size() > id) {
            errorform.setError(error.get(id));
        }
        errorform.setVisible(true);
        //System.exit(0);
    }

    //Form wird konfiguriert
    private Errorhandler createError(){
        Errorhandler errorform = new Errorhandler();
        errorform.pack();
        errorform.setLocationRelativeTo(null);
        return errorform;
    }

    //Errormeldung setzen
    public void setError(String errormsg){
        meldung.setText(errormsg);
    }

    private void createUIComponents() {
    }
}
