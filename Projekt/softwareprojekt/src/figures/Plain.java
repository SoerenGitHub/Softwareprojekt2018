package figures;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class Plain extends Figure{
//    private Point3D r; //Ortsvektor R der Ebene
//    private Point3D a; //Richtungsvektor A der Ebenen
//    private Point3D b; //Richtungsvektor B der Ebenen
//    private Point3D n; //Normalenvektor N der Ebene
    private Point3D coords; //Normalenvektor N der Ebene
    private Point3D r2; //Ortsvektor R2 der Ebene(Punkt)
    private Point3D r3; //Ortsvektor R3 der Ebene(Punkt)
    //private double L; //Lambda der Ebene
    private double M; //Mu der Ebene
    private double c; //Konstante der Koordinatenform
    private int state; //Aktuell eingestellte Form (1: PRF; 2: DPF)

    /** Konstruktor für Ebenen Objekt in der Punkt-Richtungs-Form
     * Author: Lappöhn, René
     * @param r Ortsvektor R
     * @param a Richtungsvektor A
     * @param b Richtungsvektor B
     * @param lambda Lamda
     * @param mu Mu
     */
    public Plain(Point3D r, Point3D a, Point3D b, double lambda, double mu, String name, int id) {
        this.setVec(r, a, b);
        this.setL(lambda);
        this.M = mu;
        this.settings(name, id);
        this.state = 1;
        this.setVisibility(true);
        this.setOpac(1);
    }

    /** Konstruktor für Ebene in der Normalenvektor-Form
     * Author: Lappöhn, René
     * @param n Normalenvektor N
     * @param r Ortsvektor R
     * @param r2 Ortsvektor R1
     *
    public Plain(Point3D n, Point3D r, Point3D r2, String name, int id) {
        this.n = n;
        this.setVec(r, this.getA());
        this.r2 = r2;
        this.settings(name, id);
    }
      @DEV: Kann für alternative Darstellungsform im Frontend benutzt werden.
     */

    /** Konstruktor für Ebenen in Koordiantenform
     * @param coords Koordinaten
     * @param c Konstante
     * @param name Name
     * @param id ID
     */
     public Plain(Point3D coords, double c, String name, int id) {
         this.coords = coords;
         this.c = c;
         this.settings(name,id);
     }

    /** Konstruktor für Ebenen Objekt in der Drei-Punkt-Form
     * Author: Lappöhn, René
     * @param r Ortsvektor R
     * @param a Richtungsvektor A
     * @param b Richtungsvektor B
     * @param r2 Ortsvektor R2
     * @param r3 Ortsvektor R3
     * @param lambda Lamda
     * @param mu Mu
     * @param name Name der Geraden
     */
    public Plain(Point3D r, Point3D a, Point3D b, Point3D r2, Point3D r3, double lambda, double mu, String name, int id) {
        this.setVec(r, a, b);
        this.r2 = r2;
        this.r3 = r3;
        this.setL(lambda);
        this.M = mu;
        this.settings(name, id);
        this.state = 2;
        this.setVisibility(true);
        this.setOpac(1);


    }

    /** Gibt Ortsvektor R2 der Ebene zurück
     * @return Ortsvektor R2
     */
    public Point3D getR2() {
        return this.r2;
    }

    /** Gibt Ortsvektor R3 der Ebene zurück
     * @return Ortsvektor R3
     */
    public Point3D getR3() {
        return this.r3;
    }

    /** Gibt Normalenvektor N der Ebene zurück
     * @return Normalenvektor N
     */
    public Point3D getCoords() {
        return this.coords;
    }

    /** Gibt Konstante C der Ebene zurück
     * @return Konstante C
     */
    public double getC() {
        return this.c;
    }

    /** Gibt Mu der Ebene zurück
     * @return Mu
     */
    public double getM() {
        return this.M;
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

    /** Setzt die Variablen der Ebene
     * @param r Ortsvektor R
     * @param a Richtungsvektor A
     * @param b Richtungsvektor B
     * @param lambda Lamda
     * @param mu Mu
     */
    public void setPlain(Point3D r, Point3D a, Point3D b, Point3D r2, Point3D r3, double lambda, double mu) {
        this.setVec(r, a, b);
        this.r2 = r2;
        this.r3 = r3;
        this.setL(lambda);
        this.M = mu;
    }
}