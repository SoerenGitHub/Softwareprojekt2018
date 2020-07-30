package figures;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import vectorview.Backend;

public abstract class Figure {

    private Point3D r; //Ortsvektor R
    private Point3D a; //Richtungsvektor A
    private Point3D b; //Richtungsvektor B
    private double L; //Lambda der Geraden
    private String name; //Name des Objekts
    private int id; //ID des Objekts
    private double opac; //Opacity des Objekts
    private boolean visibility; //Sichtbarkeit des Objekts
    private Color color;


    /**
     * setzt die Farbe des Objektes
     * @param color
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * setzt Sichtbarkeit des Objektes
     * author: König, Luca
     * @param visibility
     */
    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    /**
     * setzt den Opac-Wert des Objektes
     * author: König, Luca
     * @param opac
     */
    public void setOpac(double opac){
        this.opac = opac;
    }

    /**
     * setzt die Vektoren r, a und b des Objekts
     * authos: Spens, Daniel
     * @param r Ortsvektor
     * @param a Richtungsvektor a
     * @param b Richtungsvektor b
     */
    protected void setVec(Point3D r, Point3D a, Point3D b) {
        this.r = r;
        this.a = a;
        this.b = b;
    }

    /**
     * setzt die Vektoren r und a des Objekts
     * authos: Spens, Daniel
     * @param r Ortsvektor
     * @param a Richtungsvektor
     */
    protected void setVec(Point3D r, Point3D a) {
        this.r = r;
        this.a = a;
    }

    /**
     * setzt den Lamba-Wert des Objekts
     * @param l Lamba-Wert
     */
    protected void setL(double l) {
        this.L = l;
    }

    /** Setzt Namen und ID
     * Author: Lappöhn, René
     * @param id
     */
    public void setID(int id){
        this.id = id;
    }

    /** Setzt Namen und ID des Objekts
     * Author: Lappöhn, René
     * @param name Name des Objekts
     * @param id ID des Objekts
     */
    protected void settings(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /** Gibt Ortsvektor R zurück
     * authos: Spens, Daniel
     * @return Ortsvektor R
     */
    public Point3D getR() {
        return this.r;
    }

    /** Gibt Richtungsvektor A zurück
     * authos: Spens, Daniel
     * @return Richtungsvektor A
     */
    public Point3D getA() {
        return this.a;
    }

    /** Gibt Richtungsvektor B zurück
     * authos: Spens, Daniel
     * @return Richtungsvektor B
     */
    public Point3D getB() {
        return this.b;
    }

    /**
     * gibt den Lambda-Wert des Objekts zuruek
     * authos: Spens, Daniel
     * @return Lambda-Wert
     */
    public double getL() { return this.L; }

    /** Gibt ID des Objektes zurueck
     * @return ID des Objekts
     */
    public int getID(){
        return this.id;
    }

    /** Gibt Namen des Objekts zurueck
     * @return Name des Objekts
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gibt den Sichtbarkeits-Wert des Objektes zurück
     * author: König, Luca
     * @return
     */
    public double getOpac() {
        return this.opac;
    }

    /**
     * gibt zurück, ob ein Objekt sichtbar ist
     * author: König, Luca
     * @return
     */
    public boolean getVisibility(){
        return this.visibility;
    }

    /**
     * gibt die Farbe des Objektes zurück
     * @return
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * liefert den Ortsvektor einer Ebene als float-Array zurueck
     * author: Spens, Daniel
     * @return den Ortsvektor der Ebene als float-Array
     */
    public float[] getRArr(){
        float[] res = new float[3];
        res[0] = (float)this.r.getX();
        res[1] = (float)this.r.getY();
        res[2] = (float)this.r.getZ();
        return res;
    }

    /**
     * liefert den Richtungsvektor A einer Ebene mit Lambda multipliziert als int-Array zurueck
     * author: Spens, Daniel
     * @return den Richtungsvektor A der Ebene als int-Array
     */
    public float[] getAArr(){
        float[] res = new float[3];
        res[0] = (float)((this.a.getX() * this.getL() + this.r.getX()));
        res[1] = (float)((this.a.getY() * this.getL() + this.r.getY()));
        res[2] = (float)((this.a.getZ() * this.getL() + this.r.getZ()));
        return res;
    }

    /**
     * liefert den Richtungsvektor B einer Ebene mit Mu multipliziert als int-Array zurueck
     * author: Spens, Daniel
     * @return den Richtungsvektor B der Ebene als int-Array
     */
    public float[] getBArr(){
        float[] res = new float[3];
        // temporaere Plain erstellen, da sonst nicht auf Mu zugegriffen werden kann
        Plain temp = (Plain)this;
        res[0] = (float)((this.b.getX() * temp.getM() + this.r.getX()));
        res[1] = (float)((this.b.getY() * temp.getM() + this.r.getY()));
        res[2] = (float)((this.b.getZ() * temp.getM() + this.r.getZ()));
        return res;
    }
}
