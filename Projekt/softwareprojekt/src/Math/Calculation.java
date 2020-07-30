package Math;

import figures.Line;
import figures.Plain;
import javafx.geometry.Point3D;


public class Calculation {

    /**
     * erstellt ein double Array aus den angegebenen Werten
     * author: Spens, Daniel
     * @param x Wert X
     * @param y Wert Y
     * @param z Wert Z
     * @return ein int Array
     */
    public static double[] generateDoubleArr(double x, double y, double z) {
        double[] retArr = new double[3];
        retArr[0] = x;
        retArr[1] = y;
        retArr[2] = z;
        return retArr;
    }

    /**
     * nimmt die eingegeben Werte entgegen und erstellt daraus eine Line
     * author: Spens, Daniel
     * @param rArr Array mit den Werten des Ortsvektors
     * @param aArr Array mit den Werten des Richtungsvektors
     * @param lambda Lambda einer Geraden
     * @param name Name der Gerade
     * @param id ID der Geraden
     * @return eine neu erstellte Line
     */
    public static Line createLinePRF(double[] rArr, double[] aArr, double lambda, String name, int id) {

        Point3D r = new Point3D(rArr[0], rArr[1], rArr[2]);
        Point3D a = new Point3D(aArr[0], aArr[1], aArr[2]);

        Line newLine = new Line(r, a, lambda, name, id);

        return newLine;
    }

    /**
     * Methode zur Erstellung einer Linie in 2-Punkt-Form
     * author: Spens, Daniel
     * @param rArr Array mit den Werten des Ortsvektors
     * @param r2Arr Array mit den Werten des zweiten Vektors
     * @param lambda Lambda einer Geraden
     * @param name Name der Gerade
     * @param id ID der Geraden
     * @return  eine neu erstellte Line
     */
    public static Line createLineZPF(double[] rArr, double[] r2Arr, double lambda, String name, int id) {

        Point3D r = new Point3D(rArr[0], rArr[1], rArr[2]);
        Point3D r2 = new Point3D(r2Arr[0], r2Arr[1], r2Arr[2]);
        Point3D a = MathLib.vecSub(r2, r);
        //Point3D a = Point3D.ZERO;

        Line newLine = new Line(r, a, r2, lambda, name, id);

        return newLine;
    }

    /**
     * nimmt die eingegeben Werte entgegen und erstellt daraus eine Plain
     * author: Spens, Daniel
     * @param rArr Array mit den Werten des Ortsvektors
     * @param aArr Array mit den Werten des ersten Richtungsvektors
     * @param bArr Array mit den Werten des zweiten Richtungsvektors
     * @param lambda Lambda einer Ebene
     * @param mu Mue einer Ebene
     * @param name Name der Ebene
     * @param id ID der Ebene
     * @return eine neu erstellte Plain
     */
    public static Plain createPlainPRF(double[] rArr, double[] aArr, double[] bArr, double lambda, double mu, String name, int id) {

        Point3D r = new Point3D(rArr[0], rArr[1], rArr[2]);
        Point3D a = new Point3D(aArr[0], aArr[1], aArr[2]);
        Point3D b = new Point3D(bArr[0], bArr[1], bArr[2]);

        Plain newPlain = new Plain(r, a, b, lambda, mu, name, id);

        return newPlain;
    }

    /**
     * Methode fuer die Eingabe der 3-Punkt-Form
     * author: Spens, Daniel
     * @param rArr Array mit den Werten des ersten Ortsvektors
     * @param r2Arr Array mit den Werten eines weiteren Vektors
     * @param r3Arr Array mit den Werten eines weiteren Vektors
     * @param lambda Lambda einer Ebene
     * @param mu Mue einer Ebene
     * @param name Name der Ebene
     * @param id ID der Ebene
     * @return eine neu erstellte Plain
     */
    public static Plain createPlainDPF(double[] rArr, double[] r2Arr, double[] r3Arr, double lambda, double mu, String name, int id) {

        Point3D r = new Point3D(rArr[0], rArr[1], rArr[2]);
        Point3D r2 = new Point3D(r2Arr[0], r2Arr[1], r2Arr[2]);
        Point3D r3 = new Point3D(r3Arr[0], r3Arr[1], r3Arr[2]);
        Point3D a = MathLib.vecSub(r2, r);
        Point3D b = MathLib.vecSub(r3, r);

        Plain newPlain = new Plain(r, a, b, r2, r3, lambda, mu, name, id);

        return newPlain;
    }

//    /**
//     * liefert den Ortsvektor einer Geraden als int-Array zurueck
//     * author: Spens, Daniel
//     * @param line die Gerade
//     * @return ein int-Array mit den Werten des Ortsvektors
//     */
//    public static float[] getRLine(Line line){
//        float[] rArr = new float[3];
//        rArr[0] = (float)line.getR().getX();
//        rArr[1] = (float)line.getR().getY();
//        rArr[2] = (float)line.getR().getZ();
//        return rArr;
//    }

//    /**
//     * liefert den Richtungsvektor einer Geraden als int-Array zurueck
//     * author: Spens, Daniel
//     * @param line die Gerade
//     * @return ein int-Array mit den Werten des Richtungsvektors
//     */
//    public static float[] getALine(Line line){
//        float[] aArr = new float[3];
//        aArr[0] = (float)line.getA().getX();
//        aArr[1] = (float)line.getA().getY();
//        aArr[2] = (float)line.getA().getZ();
//        return aArr;
//    }

//    /**
//     * liefert den Lambda einer Geraden zurueck
//     * author: Spens, Daniel
//     * @param line die Gerade
//     * @return der Lamba-Wert
//     */
//    public static double getLambdaLine(Line line) {
//        return line.getL();
//    }
//
//    /**
//     * gibt den Namen des Objekts zurueck
//     * author: Spens, Daniel
//     * @param line die Gerade
//     * @return der Name der Geraden
//     */
//    public static String getNameLine(Line line) { return line.getName(); }
//
//    /**
//     * gibt die ID des Objekts zurueck
//     * author: Spens, Daniel
//     * @param line die Gerade
//     * @return die ID der Geraden
//     */
//    public static int getIDLine(Line line) { return line.getID(); }

//    /**
//     * liefert den Ortsvektor einer Ebene als int-Array zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return den Ortsvektor der Ebene als int-Array
//     */
//    public static float[] getRPlain(Plain plain){
//        float[] rArr = new float[3];
//        rArr[0] = (float)plain.getR().getX();
//        rArr[1] = (float)plain.getR().getY();
//        rArr[2] = (float)plain.getR().getZ();
//        return rArr;
//    }

//    /**
//     * liefert den Richtungsvektor A einer Ebene als int-Array zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return den Richtungsvektor A der Ebene als int-Array
//     */
//    public static float[] getAPlain(Plain plain){
//        float[] aArr = new float[3];
//        aArr[0] = (float)plain.getA().getX();
//        aArr[1] = (float)plain.getA().getY();
//        aArr[2] = (float)plain.getA().getZ();
//        return aArr;
//    }
//
//    /**
//     * liefert den Richtungsvektor B einer Ebene als int-Array zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return den Richtungsvektor B der Ebene als int-Array
//     */
//    public static float[] getBPlain(Plain plain){
//        float[] bArr = new float[3];
//        bArr[0] = (float)plain.getB().getX();
//        bArr[1] = (float)plain.getB().getY();
//        bArr[2] = (float)plain.getB().getZ();
//        return bArr;
//    }

//    /**
//     * liefert den Lamba-Wert der Ebene zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return der Lamba-Wert
//     */
//    public static double getLambdaPlain(Plain plain) {
//        return plain.getL();
//    }
//
//    /**
//     * liefert den Mu-Wert der Ebene zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return der Mu-Wert
//     */
//    public static double getMuPlain(Plain plain) {
//        return plain.getM();
//    }
//
//    /**
//     * gibt den Namen des Objekts zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return der Name der Ebene
//     */
//    public static String getNamePlain(Plain plain) { return plain.getName(); }
//
//    /**
//     * gibt die ID des Objekts zurueck
//     * author: Spens, Daniel
//     * @param plain die Ebene
//     * @return ID der Ebene
//     */
//    public static int getIDPlain(Plain plain) { return plain.getID(); }


    /**
     * ruft die Funktionen zum Umwandeln in die andere Formen auf
     * author: Spens, Daniel
     * @param line die Gerade, die geaendert werden soll
     * @param form int-Wert für die aktuelle Form
     * @return Gerade in neuer Form
     */
    public static Line convertForm(Line line, int form) {

        switch (form) {
            // Punkt-Richtungs-Form in 2-Punkte-Form
            case 1:
                line.setState(2);
                return convertPDtoTP(line);
            // 2-Punkte-Form in Punkt-Richtungs-Form
            case 2:
                line.setState(1);
                return convertTPtoPD(line);
        }
        return null;
    }

    /**
     * ruft die Funktionen zum Umwandeln in die andere Formen auf
     * author: Spens, Daniel
     * @param plain die Ebene, die geaendert werden soll
     * @param form int-Wert für die aktuelle Form
     * @return Ebene in neuer Form
     */
    public static Plain convertForm(Plain plain, int form) {

        switch (form) {
            // Punkt-Richtungs-Form in 3-Punkte-Form
            case 1:
                plain.setState(2);
                return convertPDtoTP(plain);
            // 3-Punkte-Form in Punkt-Richtungsform
            case 2:
                plain.setState(1);
                return convertTPtoPD(plain);
        }
        return null;
    }

    /** Konvertierung der Geraden von Punkt-Richtugns-Form in 2-Punkt-Form
     * Author: Lappöhn, René; Spens, Daniel
     * @param l1 Gerade 1 in Punkt-Richtugns-Form
     * @return Gerade 1 in 2-Punkt-Form
     */
    public static Line convertPDtoTP(Line l1) {
        Point3D r2 = MathLib.vecAdd(l1.getA(),l1.getR());

        l1.setLine(l1.getR(), l1.getA(), r2, l1.getL());
        return l1;
    }

    /** Konvertierung der Geraden von 2-Punkt-Form in Punkt-Richtugns-Form
     * Author: Lappöhn, René; Spens, Daniel
     * @param l1 Gerade 1 in 2-Punkt-Form
     * @return Gerade 1 in Punkt-Richtugns-Form
     */
    public static Line convertTPtoPD(Line l1) {
        Point3D a = MathLib.vecSub(l1.getR2(), l1.getR());

        l1.setLine(l1.getR(), a, l1.getR2(), l1.getL());
        return l1;
    }

    /** Konvertierung der Ebene von Punkt-Richtungs-Form in die 3-Punkt-Form
     * Author: Lappöhn, René; Spens, Daniel
     * @param p1 Ebene P1
     * @return Ebene P1 in 3-Punkt-Form
     */
    public static Plain convertPDtoTP(Plain p1) {
        Point3D r2;
        Point3D r3;

        r2 = MathLib.vecAdd(p1.getA(),p1.getR());
        r3 = MathLib.vecAdd(p1.getB(),p1.getR());

        p1.setPlain(p1.getR(), p1.getA(), p1.getB(), r2, r3, p1.getL(), p1.getM());

        return p1;
    }

    /** Konvertierung der Ebene von 3-Punkt-Form ind die Punkt-Richtungs-Form
     * Author: Lappöhn, René; Spens, Daniel
     * @param p1 Ebene P1
     * @return Ebene 1 in Punkt-Richtungs-Form
     */
    public static Plain convertTPtoPD(Plain p1) {
        Point3D a;
        Point3D b;

        a = MathLib.vecSub(p1.getR2(), p1.getR());
        b = MathLib.vecSub(p1.getR3(), p1.getR());

        p1.setPlain(p1.getR(), a, b, p1.getR2(), p1.getR3(), p1.getL(), p1.getM());
        return p1;
    }

    /** Konvertiert die Punkt-Richtungs-Form einer Ebene in die Koordinatenform
     * Author: Lappöhn, René
     * @param plain Ebene
     * @return Ebene in Koordinatenform
     */
    public static Plain convertPDtoCoord(Plain plain) {
        Point3D n;
        double c;

        n = MathLib.vecProduct(plain.getA(), plain.getB()); //Berechnen des Normalenvektors
        c = MathLib.skalarProduct(n, plain.getR()); //Berechnen der Konstante der Koordinatenform

        Plain coordP = new Plain(n,c,plain.getName(),plain.getID()); //Erstellen der Ebene in Coordinatenform

        return coordP;
    }

    /** Berechnet den Multiplikator zur Ebenenskalierung aus den skalierten Spannvektoren zwischen Orts- und Richtungsvektoren
     *  Es wird der größere gewählt, um zu gewährleisten, dass alle Punkte in der gezeichneten Ebene liegen
     * Author: Lappöhn, René; Spens, Daniel
     * @param p1 Ebene p1
     * @return Multiplikator
     */
    public static float multiplicator(Plain p1) {
        float l1 = (float)MathLib.vecLength(MathLib.vecAdd(p1.getR(), MathLib.vecSkalarMult(p1.getA(), p1.getL())));
        float l2 = (float)MathLib.vecLength(MathLib.vecAdd(p1.getR(), MathLib.vecSkalarMult(p1.getB(), p1.getM())));

        if(Float.compare(l1,l2) < 0){
          return l2;
        }

        return l1;
    }
}
