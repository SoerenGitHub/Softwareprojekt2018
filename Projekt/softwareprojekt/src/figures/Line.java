package figures;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class Line extends Figure{
    //private Point3D r; //Ortsvektor R der Geraden
    private Point3D r2; //Ortsvektor R2 der Geraden (Zweipunktform)
    //private Point3D a; //Richtungsvektor A der Geraden
    //private double L; //Lambda der Geraden
    private int state; //Aktuell eingestellte Form (1: PRF; 2: ZPF)

    /** Konstruktor für Geraden Objekt in der Punkt-Richtungs-Form
     * Author: Lappöhn, René
     * @param r Ortsvektor R der Geraden
     * @param a Richtungsvektor A der Geraden
     * @param lambda Lambda der Geraden
     */
    public Line(Point3D r, Point3D a, double lambda, String name, int id) {
        this.setVec(r, a);
        this.r2 = null;
        this.setL(lambda);
        this.settings(name, id); //Name der Geraden
        this.state = 1;
        this.setVisibility(true);
        this.setOpac(1);
    }

    /** Konstruktor für Geraden Objekt in der Zwei-Punkt-Form
     * Author: Lappöhn, René
     * @param r Ortsvektor R der Geraden
     * @param a Richtungsvektor A der Geraden
     * @param r2 Ortsvektor R2 der Geraden
     * @param lambda Lambda der Geraden
     */
    public Line(Point3D r, Point3D a, Point3D r2, double lambda, String name, int id) {
        this.setVec(r, a);
        this.r2 = r2;
        this.setL(lambda);
        this.settings(name, id); //Name der Geraden
        this.state = 2;
        this.setVisibility(true);
        this.setOpac(1);
    }

    /** Gibt Ortsvektor der Geraden wieder.
     * Author: Lappöhn, René
     * @return Ortsvektor R.
     */
    public Point3D getR2() {
        return this.r2;
    }

    /** Gibt aktuellen Darstellungszustand zurück
     * @return Zustand als Integer
     */
    public int getState() {
        return this.state;
    }

    /** Setzt status auf neuen Status nach Konvertieren
     * @param newstate Neuer status
     */
    public void setState(int newstate) {
        this.state = newstate;
    }

    /** Setzt Werte der Geraden
     *
     * @param r Ortsvektor R
     * @param a Richtungsvektor A
     * @param lambda Lambda
     */
    public void setLine(Point3D r, Point3D a, Point3D r2, double lambda) {
        this.setVec(r, a);
        this.r2 = r2;
        this.setL(lambda);
    }
}
