package Math;

import figures.Line;
import figures.Plain;
import javafx.geometry.Point3D;

public class MathLib {

    //Grundlegende Formeln der Vektorrechnung

    /** Addiert die Vektoren A und B.
     * Author: Lappöhn, René
     * @param a Vektor A.
     * @param b Vektor B
     * @return Ergebnisvektor.
     */
    public static Point3D vecAdd(Point3D a, Point3D b) {
        Point3D res = new Point3D((a.getX() + b.getX()), (a.getY() + b.getY()), (a.getZ() + b.getZ()));

        return res;
    }

    /** Subtrahiert die Vektoren A und B.
     * Author: Lappöhn, René
     * @param a Vektor A.
     * @param b Vektor B
     * @return Ergebnisvektor.
     */
    public static Point3D vecSub(Point3D a, Point3D b) {
        Point3D res = new Point3D((a.getX() - b.getX()), (a.getY() - b.getY()), (a.getZ() - b.getZ()));

        return res;
    }

    /** Berechnet die Länge des eingebenen Vektors(in Koordinatenform).
     * Author: Lappöhn, René
     * @param v Vektor V.
     * @return Länge des eingebenen Vektors.
     */
    public static double vecLength(Point3D v) {
        double vLength = 0;
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        vLength = Math.sqrt((x*x)+(y*y)+(z*z));
        return vLength;
    }

    /**Berechnet das Vektorprodukt der Vektoren A und B(in Koordinatenform).
     * Author: Lappöhn, René
     * @param a Vektor A.
     * @param b Vektor B.
     * @return Vektorprodukt der Vektoren A und B.
     */
    public static Point3D vecProduct(Point3D a, Point3D b) {
        Point3D vProd = new Point3D( a.getY()*b.getZ() - a.getZ()*b.getY(),
                                    a.getZ()*b.getX() - a.getX()*b.getZ(),
                                    a.getX()*b.getY() - a.getY()*b.getX());
        return vProd;
    }

    /** Berechnet das Skalarprodukt der Vektoren A und B(in Koordinatenform).
     * Author: Lappöhn, René
     * @param a Vektor A.
     * @param b Vektor B.
     * @return Skalarprodukt der Vektoren A und B.
     */
    public static double skalarProduct(Point3D a, Point3D b) {
        double sProd;

        sProd = ((a.getX()*b.getX())+(a.getY()*b.getY())+(a.getZ()*b.getZ()));
        return sProd;
    }

    /** Multipliziert einen Vektoren mit einem Skalar
     * Author: Lappöhn, René
     * @param v Vektor
     * @param ska Skalar
     * @return Multiplizierter Vektor
     */
    public static Point3D vecSkalarMult(Point3D v, double ska) {
        double skalar = ska/2;
        Point3D nv = new Point3D(v.getX() * skalar, v.getY() * skalar, v.getZ() * skalar);
        return nv;
    }

    /** Berechnet unter verwendung von vecProduct() und skalarProduct() das Spatprodukt der Vektoren A, B und C.
     * Author: Lappöhn, René
     * @param a Vektor A.
     * @param b Vektor B.
     * @param c Vektor C.
     * @return Spatprodukt der Vektoren A, B und C.
     */
    public static double spatProduct(Point3D a, Point3D b, Point3D c) {
        double spat;

        spat = Math.sqrt(Math.pow(skalarProduct(a, vecProduct(b,c)),2));
        return spat;
    }

    /** Berechnet Winkel zwischen 2 Vektoren
     * Author: Lappöhn, René
     * @param a Vektor A
     * @param b Vektor B
     * @return Winkel Phi
     */
    public static double vecAnkle(Point3D a, Point3D b) {
        double phi;

        phi = Math.acos((skalarProduct(a, b))/(vecLength(a) * vecLength(b)));

        return phi;
    }

    //Formeln der Geradenrechnung

    /** Erstellt eine Gerade in Punkt-Richtungs-Form.
     * Author: Lappöhn, René
     * @param r Ortsvektor der Geraden.
     * @param a Richtungsvektor der Geraden.
     * @param L Lambda der Geraden.
     * @return Gerade in Form eines Line-Objektes.
     *
    public static Line linPointDirForm(Point3D r, Point3D a, double L) {
    Line line = new Line(Point3D.ZERO, Point3D.ZERO,0,line.getName(),line.getID());
    line.setLine(r,a,null, L);

    return line;
    }
     * @DEV: Kann zum erweitern der Software verwendet werden!
     */

    /** Berechnet und erstellt eine Gerade in Zwei-Punkt-Form.
     * Author: Lappöhn, René
     * @param r1 Ortsvektor 1.
     * @param r2 Ortsvektor 2.
     * @param L Lambda der Geraden.
     * @Point3D a Berechneter Richtungsvektor.
     * @return Gerade in Form eines Line-Objektes.
     *
    public static Line linTwoPointForm(Point3D r1, Point3D r2, double L) {
    Point3D a = null;
    Line line = null;
    a.add((r1.getX() - r2.getX()), (r1.getY() - r2.getY()), (r1.getZ() - r2.getZ()));

    line.setLine(r1,a,r2,L);
    return line;
    }
     * @DEV: Kann zum erweitern der Software verwendet werden!
     */

    /** Testet ob die gegebenen Geraden einen Schnittpunkt besitzen
     * Author: Lappöhn, René
     * @param l1 Gerade 1
     * @param l2 Gerade 2
     * @return True wenn Schnittpunkt vorhanden, False wenn nicht
     */
    public static boolean linIntersection(Line l1, Line l2) {
        //Test auf parallelität
        if (l1.getA() == l2.getA())
        {
            return false;
        } else {

            //Test auf windschief
            if (!askewTest(l1, l2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /** Berechnet den Abstand der zwei eingebenen Geraden zueinander.
     * Author: Lappöhn, René
     * @param l1 Gerade L1.
     * @param l2 Gerade L2.
     * @double s Spatprodukt der Vektoren A beider Geraden
     * @double d Abstand der Geraden.
     * @return Abstand zwischen den Geraden A und B
     */
    public static double linDistance(Line l1, Line l2) {

        double s = 0;
        double d = 0;

        //Subtraktion der Ortsvektoren beider Geraden von einander.
        //Berechnen des Spatprodukts des Ergebnisvektoren mit den Richtungsvektoren beider Geraden
        s = spatProduct(vecSub(l1.getR(), l2.getR()), l1.getA(), l2.getA());

        //Betrag berechnen
        s = Math.sqrt(Math.pow(s,2));
        //Division des Oberen Betrags mit dem unteren Betrag
        d = s / vecLength(vecProduct(l1.getA(), l2.getA()));
        return d;
    }

    /** Überprüft über Betrag des Spatproducts der Vektoren von Gerade L1 und L2, ob jene Windschief sind.
     * Author: Lappöhn, René
     * @param l1 Gerade L1.
     * @param l2 Gerade L2.
     * @return Wenn Geracen zueinander windschief sind true, wenn nicht false.
     */
    public static boolean askewTest(Line l1, Line l2) {
        double d;

        d = spatProduct(vecSub(l1.getR(), l2.getR()), l1.getA(), l2.getA());
        d = Math.sqrt(Math.pow(d, d));

        if (d == 0) {
            return false;
        } else {
            return true;
        }
    }

    /** Berechnet den Abstand Zwischen einem Punkt P und einer Gerade L1.
     * Author: Lappöhn, René
     * @param l1 Gerade L1.
     * @param p Punkt P.
     * @return Abstand zwischen der Geraden L1 und dem Punkt P.
     */
    public static double linPointDistance(Line l1, Point3D p) {
        double d;

        //Betrag(Länge) des Vektorprodukts aus Richtungsvektor von L1 und Differenzvektor von Ortvektoren von L1 und P
        d = vecLength(vecProduct(l1.getA(),(vecSub(p,l1.getR()))));
        //Oberes Ergebnis wird durch Länge des Richtungsvektorens von L1 dividiert.
        d = d / vecLength(l1.getA());

        return d;
    }

    /** Berechnet den Winkel Phi zwischen den Geraden L1 und L2.
     * Author: Lappöhn, René
     * @param l1 Gerade L1
     * @param l2 Gerade L2
     * @return Wenn Geraden nicht windschief Winkel Phi, wenn windschief -1(error).
     */
    public static double linAnkle(Line l1, Line l2) {
        double phi;

        //Test auf Windschief
        if (!askewTest(l1, l2))
        {
            //Arccosinus des Quotientenwerts der Produkte von den Richtungsvektoren A1 und A2 und der Längen der Richtungsvektoren A1 und A2
            phi = Math.acos(skalarProduct(l1.getA(), l2.getA()) / (vecLength(l1.getA()) * vecLength(l2.getA())));
            return phi;
        } else {
            return -1;
        }
    }

    //Formeln der Flächenrechnung

    /** Erstellt Ebenen-Objekt in Punkt-Richtungs-Form.
     * Author: Lappöhn, René
     * @param r Ortsvetkor der Ebene
     * @param a Richtungsvektor A der Ebene
     * @param b Richtungsvektor B der Ebene
     * @param L Lambda der Ebene
     * @param M Mu der Ebene
     * @return Ebene in Punkt-Richtungs-Form.
     *
    public static Plain plaPointDirForm(Point3D r, Point3D a, Point3D b, double L, double M) {

    Plain plain = null;
    plain.setPlain(r, a, b, null, null, L, M);

    return plain;
    }
     * @DEV: Kann zum erweitern der Software genutzt werden.
     */

    /** Gibt Ebene in Punkt-Richtungsform, mittels der 3-Punkt-Form wieder.
     * Author: Lappöhn, René
     * @param r1 Ortsvektor Punkt 1
     * @param r2 Ortsvektor Punkt 2
     * @param r3 Ortsvektor Punkt 3
     * @param L Lambda der Ebene
     * @param M Mu der Ebene
     * @return Ebene in Punkt-Richtungs-Form.
     *
    public static Plain plaThreePointForm(Point3D r1, Point3D r2, Point3D r3, double L, double M) {
    Point3D a = vecSub(r2,r1);
    Point3D b = vecSub(r3,r1);

    Plain plain = null;
    plain.setPlain(r1, a, b, r2, r3, L, M);

    return plain;
    }
     * @DEV: Kann zum erweitern der Software genutzt werden.
     */

    /** Berechnet den Abstand eines Punktes P zu Ebene 'Plain'
     * Author: Lappöhn, René
     * @param plain Ebene
     * @param p Punkt P
     * @return Abstand zwischen Ebene und Punkt
     */
    public static double plaPointDistance(Plain plain, Point3D p) {
        Point3D n;
        double d;
        double x;

        //Normalenvektor berechnen
        n = vecProduct(plain.getA(), plain.getB());

        //Betrag des Skalarprodukts aus Normalenvektor und Differenz von Ortsvektor der Ebene und des Punktes berechnen
        x = skalarProduct(n,vecSub(p, plain.getR()));
        x = Math.sqrt(Math.pow(x, 2));

        //Distanz entspricht Quotientenwert von Betrag x und Länge des Normalenvektors.
        d = x / vecLength(n);
        return d;
    }

    /** Berechnet über plaPointDistance den Abstand zwischen der Ebene Plain und der Geraden Line
     * Author: Lappöhn, René
     * @param p Ebene plain
     * @param l Gerade Line
     * @return Abstand
     */
    public static double plaLinDistance(Plain p, Line l) {
        double d;

        d = plaPointDistance(p, l.getR());
        return d;
    }

    /** Berechnet den Abstand zwischen den zwei Ebenen P1 und P2
     * Author: Lappöhn, René
     * @param p1 Ebene P1
     * @param p2 Ebene P2
     * @return Abstand zwischen den Ebenen.
     */
    public static double plaDistance(Plain p1, Plain p2) {
        double d;
        double x;
        Point3D n;

        //Berechnung des Normalenvektors
        n = vecProduct(p1.getA(), p1.getB());

        //Berechnung des Skalarprodukts aus Normalenvektor und Diffrenz der Ortsvektoren beider Ebenen
        x = skalarProduct(n, vecSub(p2.getR(), p1.getR()));
        x = Math.sqrt(Math.pow(x, 2)); //Betrag berechnen

        //Wert des Quotients von Oberen Ergebnis und Länge des Normalenvektors
        d = x / vecLength(n);
        return d;
    }

    /** Berechnet den Schnittwinkel der Ebene p und der Geraden l
     * Author: Lappöhn, René
     * @param p Ebene P
     * @param l Gerade L
     * @return Schnittwinkel Phi.
     */
    public static double plaAnkleLine(Plain p, Line l) {
        double phi;
        double x;
        Point3D n;

        //Normalenvektor berechnen
        n = vecProduct(p.getA(), p.getB());
        x = skalarProduct(n, l.getA()); //Skalarprodukt aus Normalenvektor und Richtungsvektor der Linie

        //Quotientenwert aus oberen Ergebnis und Produkt der Vektorbeträge von n und a
        phi = x / (vecLength(n) * vecLength(l.getA()));

        //Arcsinus des Ergebnisses
        phi = Math.asin(phi);
        return phi;
    }

    /** Berechnet den Schnittwinkel der Ebenen p1 und p2
     * Author: Lappöhn, René
     * @param p1 Ebene P1
     * @param p2 Ebene P2
     * @return Schnittwinkel Phi.
     */
    public static double plaAnklePlain(Plain p1, Plain p2) {
        double phi;
        double x;
        Point3D n1;
        Point3D n2;

        //Berechnen der Normalenvektoren beider Ebenen
        n1 = vecProduct(p1.getA(), p1.getB());
        n2 = vecProduct(p2.getA(), p2.getB());
        //Skalarprodukt der Normalenvektor
        x = skalarProduct(n1, n2);

        //Quotientenwert aus oberen Skalarprodukt und Produkt der Beträge beider Normalenvektoren
        phi = x / (vecLength(n1) * vecLength(n2));
        //Arccos des Ergebnisses
        phi = Math.acos(phi);
        return phi;
    }

}
