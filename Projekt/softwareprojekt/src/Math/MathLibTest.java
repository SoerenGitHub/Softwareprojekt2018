package Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.geometry.Point3D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import figures.*;

public class MathLibTest {

    static final Point3D in1 = new Point3D(1,4,1);
    static final Point3D in2 = new Point3D(1,5,2);
    static final Point3D in3 = new Point3D(3,9,6);
    static final Point3D in4 = new Point3D(3,7,5);
    static final Line l1 = new Line(in1, in2, 6, "l1", 1);
    static final Line l2 = new Line(in3, in4, 8 , "l2", 2);
    static final Plain p1 = new Plain(in1,in2,in3,6,4,"p1",1);
    static final Plain p2 = new Plain(in4,in1,in3,3,2,"p2",2);

    @Test
    @DisplayName("Vektor addition (vecAdd)")
    void vecAdd(){
        Point3D test = new Point3D(2,9,3);

        assertEquals(test, MathLib.vecAdd(in1, in2));
    }

    @Test
    @DisplayName("Vektor subtrahieren (vecSub)")
    void vecSub(){
        Point3D test = new Point3D(0,-1,-1);

        assertEquals(test, MathLib.vecSub(in1, in2));
    }

    @Test
    @DisplayName("Länge eines Vektoren (vecLength)")
    void vecLength(){
        double test = 1.4142135623730951;

        Point3D vtest = MathLib.vecSub(in1, in2);

        assertEquals(test, MathLib.vecLength(vtest));
    }

    @Test
    @DisplayName("Vektorprodukt (vecProduct")
    void vecProduct(){
        Point3D test = new Point3D(1,20,2);

        assertEquals(test, MathLib.vecProduct(in1,in2));
    }

    @Test
    @DisplayName("Skalardprodukt (skalarProduct)")
    void skalarProduct(){
        double test = 23;
        assertEquals(test, MathLib.skalarProduct(in1,in2));

    }

    @Test
    @DisplayName("Spatprodukt (spatProduct)")
    void spatProduct() {
        double test = 195;
        assertEquals(test, MathLib.spatProduct(in1, in2, in3));
    }

    @Test
    @DisplayName("Abstand zwischen zwei Geraden (linDistance)")
    void linDistance(){
        double test = 6.3246145831336955;
        assertEquals(test,MathLib.linDistance(l1,l2));
    }

    @Test
    @DisplayName("Test auf Windschief(askewTest)")
    void askewTest(){
        assertEquals(true,MathLib.askewTest(l1,l2));
    }

    @Test
    @DisplayName("Abstand Linie zu Gerade (linPointDistance)")
    void linPointDistance() {
        double test = 3.125166662222459;
        assertEquals(test, MathLib.linPointDistance(l1,in4));
    }

    @Test
    @DisplayName("Winkel zw. zwei Geraden (linAnkle)")
    void linAnkle() {
        double test = -1;
        assertEquals(test, MathLib.linAnkle(l1,l2));
    }

    @Test
    @DisplayName("Abstand Ebene zu Punkt (plaPointDistance")
    void plaPointDistance() {
        double test = 4.049793383159318;
        assertEquals(test, MathLib.plaPointDistance(p1,in4));
    }

    @Test
    @DisplayName("Abstand Ebene zu Gerade (plaLinDistance)")
    void plaLinDistance() {
        double test = 0.0;
        assertEquals(test, MathLib.plaLinDistance(p1,l1));
    }

    @Test
    @DisplayName("Abstand Ebene zu Ebene")
    void plaDistance() {
        double test = 4.049793383159318;
        assertEquals(test,MathLib.plaDistance(p1,p2));
    }

    @Test
    @DisplayName("Schnittwinkel Gerade Ebene (plaAnkleLine)")
    void plaAnkleLine() {
        double test = 1.127067157243256;
        assertEquals(test,MathLib.plaAnkleLine(p1,l2));
    }

    @Test
    @DisplayName("Schnittwinkel Ebene Ebene (plaAnklePlain)")
    void plaAnklePlain() {
        double test = 0.0968262145755409;
        assertEquals(test, MathLib.plaAnklePlain(p1,p2));
    }
}
