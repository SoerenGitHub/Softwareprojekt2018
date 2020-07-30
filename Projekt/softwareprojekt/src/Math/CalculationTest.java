package Math;

import figures.Line;
import figures.Plain;
import javafx.geometry.Point3D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    static final double[] doubArr1 = {1,4,1};
    static final double[] doubArr2 = {1,5,2};
    static final double[] doubArr3 = {3,9,6};
    static final double[] doubArr4 = {3,7,5};

    static final Point3D in1 = new Point3D(1,4,1);
    static final Point3D in2 = new Point3D(1,5,2);
    static final Point3D in3 = new Point3D(3,9,6);
    static final Point3D in4 = new Point3D(3,7,5);

    static final Point3D a = MathLib.vecSub(in2, in1);
    static final Point3D a1 = MathLib.vecSub(in4, in3);
    static final Point3D a2 = MathLib.vecSub(in3, in1);
    static final Point3D a3 = MathLib.vecSub(in4, in2);

    static final Point3D b = MathLib.vecSub(in2, in1);
    static final Point3D b1 = MathLib.vecSub(in3, in1);
    static final Point3D b2 = MathLib.vecSub(in3, in2);
    static final Point3D b3 = MathLib.vecSub(in4, in2);
    static final Point3D b4 = MathLib.vecSub(in1, in3);
    static final Point3D b5 = MathLib.vecSub(in2, in3);
    static final Point3D b6 = MathLib.vecSub(in2, in4);
    static final Point3D b7 = MathLib.vecSub(in3, in4);

    static final double lambda = 2;
    static final double mu = 4;

    static final Line testLine = new Line(in1, in2, lambda, "line1", 0);
    static final Line testLine1 = new Line(in3, in4, lambda, "line2", 1);
    static final Line testLine2 = new Line(in1, in3, lambda, "line3", 2);
    static final Line testLine3 = new Line(in2, in4, lambda, "line4", 3);

    static final Line testLinez = new Line(in1, a, in2, lambda, "line1", 0);
    static final Line testLinez1 = new Line(in3, a1, in4, lambda, "line2", 1);
    static final Line testLinez2 = new Line(in1, a2, in3, lambda, "line3", 2);
    static final Line testLinez3 = new Line(in2, a3, in4, lambda, "line4", 3);

    static final Plain testPlain = new Plain(in1, in2, in3, lambda, mu, "plain1", 0);
    static final Plain testPlain1 = new Plain(in2, in3, in4, lambda, mu, "plain2", 1);
    static final Plain testPlain2 = new Plain(in3, in4, in1, lambda, mu, "plain3", 2);
    static final Plain testPlain3 = new Plain(in4, in1, in2, lambda, mu, "plain4", 3);

    static final Plain testPlaind = new Plain(in1, b, b1, in2, in3, lambda, mu, "plain1", 0);
    static final Plain testPlaind1 = new Plain(in2, b2, b3, in3, in4, lambda, mu, "plain2", 1);
    static final Plain testPlaind2 = new Plain(in3, b4, b5, in1, in2, lambda, mu, "plain3", 2);
    static final Plain testPlaind3 = new Plain(in4, b6, b7, in2, in3, lambda, mu, "plain4", 3);

    @Test
    @DisplayName("Generieren DoubleArray: generateDoubleArr")
    void generateDoubleArr() {
        assertArrayEquals(doubArr1, Calculation.generateDoubleArr(1,4,1));
        assertArrayEquals(doubArr2, Calculation.generateDoubleArr(1,5,2));
        assertArrayEquals(doubArr3, Calculation.generateDoubleArr(3,9,6));
        assertArrayEquals(doubArr4, Calculation.generateDoubleArr(3,7,5));
    }

    @Test
    @DisplayName("Generieren einer Geraden in Punkt-Richtungs-Form: createLinePRF")
    void createLinePRF() {
        Line test = Calculation.createLinePRF(doubArr1, doubArr2, lambda, "line1", 0);
        assertEquals(testLine.getR(), test.getR());
        assertEquals(testLine.getA(), test.getA());
        assertEquals(testLine.getL(), test.getL());
        assertEquals(testLine.getName(), test.getName());
        assertEquals(testLine.getID(), test.getID());

        Line test1 = Calculation.createLinePRF(doubArr3, doubArr4, lambda, "line2", 1);
        assertEquals(testLine1.getR(), test1.getR());
        assertEquals(testLine1.getA(), test1.getA());
        assertEquals(testLine1.getL(), test1.getL());
        assertEquals(testLine1.getName(), test1.getName());
        assertEquals(testLine1.getID(), test1.getID());

        Line test2 = Calculation.createLinePRF(doubArr1, doubArr3, lambda, "line3", 2);
        assertEquals(testLine2.getR(), test2.getR());
        assertEquals(testLine2.getA(), test2.getA());
        assertEquals(testLine2.getL(), test2.getL());
        assertEquals(testLine2.getName(), test2.getName());
        assertEquals(testLine2.getID(), test2.getID());

        Line test3 = Calculation.createLinePRF(doubArr2, doubArr4, lambda, "line4", 3);
        assertEquals(testLine3.getR(), test3.getR());
        assertEquals(testLine3.getA(), test3.getA());
        assertEquals(testLine3.getL(), test3.getL());
        assertEquals(testLine3.getName(), test3.getName());
        assertEquals(testLine3.getID(), test3.getID());
    }

    @Test
    @DisplayName("Generieren einer Geraden in Zwei-Punkte-Form: createLineZPF")
    void createLineZPF() {
        Line test = Calculation.createLineZPF(doubArr1, doubArr2, lambda, "line1", 0);
        assertEquals(testLinez.getR(), test.getR());
        assertEquals(testLinez.getA(), test.getA());
        assertEquals(testLinez.getR2(), test.getR2());
        assertEquals(testLinez.getL(), test.getL());
        assertEquals(testLinez.getName(), test.getName());
        assertEquals(testLinez.getID(), test.getID());

        Line test1 = Calculation.createLineZPF(doubArr3, doubArr4, lambda, "line2", 1);
        assertEquals(testLinez1.getR(), test1.getR());
        assertEquals(testLinez1.getA(), test1.getA());
        assertEquals(testLinez1.getR2(), test1.getR2());
        assertEquals(testLinez1.getL(), test1.getL());
        assertEquals(testLinez1.getName(), test1.getName());
        assertEquals(testLinez1.getID(), test1.getID());

        Line test2 = Calculation.createLineZPF(doubArr1, doubArr3, lambda, "line3", 2);
        assertEquals(testLinez2.getR(), test2.getR());
        assertEquals(testLinez2.getA(), test2.getA());
        assertEquals(testLinez2.getR2(), test2.getR2());
        assertEquals(testLinez2.getL(), test2.getL());
        assertEquals(testLinez2.getName(), test2.getName());
        assertEquals(testLinez2.getID(), test2.getID());

        Line test3 = Calculation.createLineZPF(doubArr2, doubArr4, lambda, "line4", 3);
        assertEquals(testLinez3.getR(), test3.getR());
        assertEquals(testLinez3.getA(), test3.getA());
        assertEquals(testLinez3.getR2(), test3.getR2());
        assertEquals(testLinez3.getL(), test3.getL());
        assertEquals(testLinez3.getName(), test3.getName());
        assertEquals(testLinez3.getID(), test3.getID());
    }

    @Test
    @DisplayName("Generieren einer Ebene in Punkt-Richtungs-Form: createPlainPRF")
    void createPlainPRF() {
        Plain test = Calculation.createPlainPRF(doubArr1, doubArr2, doubArr3, lambda, mu, "plain1", 0);
        assertEquals(testPlain.getR(), test.getR());
        assertEquals(testPlain.getA(), test.getA());
        assertEquals(testPlain.getB(), test.getB());
        assertEquals(testPlain.getL(), test.getL());
        assertEquals(testPlain.getM(), test.getM());
        assertEquals(testPlain.getName(), test.getName());
        assertEquals(testPlain.getID(), test.getID());

        Plain test1 = Calculation.createPlainPRF(doubArr2, doubArr3, doubArr4, lambda, mu, "plain2", 1);
        assertEquals(testPlain1.getR(), test1.getR());
        assertEquals(testPlain1.getA(), test1.getA());
        assertEquals(testPlain1.getB(), test1.getB());
        assertEquals(testPlain1.getL(), test1.getL());
        assertEquals(testPlain1.getM(), test1.getM());
        assertEquals(testPlain1.getName(), test1.getName());
        assertEquals(testPlain1.getID(), test1.getID());

        Plain test2 = Calculation.createPlainPRF(doubArr3, doubArr4, doubArr1, lambda, mu, "plain3", 2);
        assertEquals(testPlain2.getR(), test2.getR());
        assertEquals(testPlain2.getA(), test2.getA());
        assertEquals(testPlain2.getB(), test2.getB());
        assertEquals(testPlain2.getL(), test2.getL());
        assertEquals(testPlain2.getM(), test2.getM());
        assertEquals(testPlain2.getName(), test2.getName());
        assertEquals(testPlain2.getID(), test2.getID());

        Plain test3 = Calculation.createPlainPRF(doubArr4, doubArr1, doubArr2, lambda, mu, "plain4", 3);
        assertEquals(testPlain3.getR(), test3.getR());
        assertEquals(testPlain3.getA(), test3.getA());
        assertEquals(testPlain3.getB(), test3.getB());
        assertEquals(testPlain3.getL(), test3.getL());
        assertEquals(testPlain3.getM(), test3.getM());
        assertEquals(testPlain3.getName(), test3.getName());
        assertEquals(testPlain3.getID(), test3.getID());
    }

    @Test
    @DisplayName("Generieren einer Ebene in Drei-Punkte-Form: createPlainDPF")
    void createPlainDPF() {
        Plain test = Calculation.createPlainDPF(doubArr1, doubArr2, doubArr3, lambda, mu, "plain1", 0);
        assertEquals(testPlaind.getR(), test.getR());
        assertEquals(testPlaind.getA(), test.getA());
        assertEquals(testPlaind.getB(), test.getB());
        assertEquals(testPlaind.getR2(), test.getR2());
        assertEquals(testPlaind.getR3(), test.getR3());
        assertEquals(testPlaind.getL(), test.getL());
        assertEquals(testPlaind.getM(), test.getM());
        assertEquals(testPlaind.getName(), test.getName());
        assertEquals(testPlaind.getID(), test.getID());
        Plain test1 = Calculation.createPlainDPF(doubArr2, doubArr3, doubArr4, lambda, mu, "plain2", 1);
        assertEquals(testPlaind1.getR(), test1.getR());
        assertEquals(testPlaind1.getA(), test1.getA());
        assertEquals(testPlaind1.getB(), test1.getB());
        assertEquals(testPlaind1.getR2(), test1.getR2());
        assertEquals(testPlaind1.getR3(), test1.getR3());
        assertEquals(testPlaind1.getL(), test1.getL());
        assertEquals(testPlaind1.getM(), test1.getM());
        assertEquals(testPlaind1.getName(), test1.getName());
        assertEquals(testPlaind1.getID(), test1.getID());
        Plain test2 = Calculation.createPlainDPF(doubArr3, doubArr1, doubArr2, lambda, mu, "plain3", 2);
        assertEquals(testPlaind2.getR(), test2.getR());
        assertEquals(testPlaind2.getA(), test2.getA());
        assertEquals(testPlaind2.getB(), test2.getB());
        assertEquals(testPlaind2.getR2(), test2.getR2());
        assertEquals(testPlaind2.getR3(), test2.getR3());
        assertEquals(testPlaind2.getL(), test2.getL());
        assertEquals(testPlaind2.getM(), test2.getM());
        assertEquals(testPlaind2.getName(), test2.getName());
        assertEquals(testPlaind2.getID(), test2.getID());
        Plain test3 = Calculation.createPlainDPF(doubArr4, doubArr2, doubArr3, lambda, mu, "plain4", 3);
        assertEquals(testPlaind3.getR(), test3.getR());
        assertEquals(testPlaind3.getA(), test3.getA());
        assertEquals(testPlaind3.getB(), test3.getB());
        assertEquals(testPlaind3.getR2(), test3.getR2());
        assertEquals(testPlaind3.getR3(), test3.getR3());
        assertEquals(testPlaind3.getL(), test3.getL());
        assertEquals(testPlaind3.getM(), test3.getM());
        assertEquals(testPlaind3.getName(), test3.getName());
        assertEquals(testPlaind3.getID(), test3.getID());
    }

    @Test
    void convertForm() {
        // Punkt-Richtung in 2-Punkte
        Line test = Calculation.convertForm(testLine, 1);
        Line toTest =  new Line(in1, in2, MathLib.vecAdd(in1, in2), lambda, "line1", 0);
        assertEquals(toTest.getR(), test.getR());
        assertEquals(toTest.getA(), test.getA());
        assertEquals(toTest.getR2(), test.getR2());
        assertEquals(toTest.getL(), test.getL());
        assertEquals(toTest.getName(), test.getName());
        assertEquals(toTest.getID(), test.getID());

        Line test2 = Calculation.convertForm(testLine1, 1);
        Line toTest2 =  new Line(in3, in4, MathLib.vecAdd(in3, in4), lambda, "line2", 1);
        assertEquals(toTest2.getR(), test2.getR());
        assertEquals(toTest2.getA(), test2.getA());
        assertEquals(toTest2.getR2(), test2.getR2());
        assertEquals(toTest2.getL(), test2.getL());
        assertEquals(toTest2.getName(), test2.getName());
        assertEquals(toTest2.getID(), test2.getID());

        // 2-Punkte in Punkt-Richtung
        Line test3 = null;
        Line test4 = null;

        // PR in 2P in PR
        Line testBack = Calculation.convertForm(test, 2);
        assertEquals(testLine.getR(), testBack.getR());
        assertEquals(testLine.getA(), testBack.getA());
        assertEquals(testLine.getL(), testBack.getL());
        assertEquals(testLine.getName(), testBack.getName());
        assertEquals(testLine.getID(), testBack.getID());

        // 2P in PR in 2P

    }

    @Test
    void convertForm1() {
    }

//    @Test
//    void convertPDtoTP() {
//    }
//
//    @Test
//    void convertTPtoPD() {
//    }
//
//    @Test
//    void convertPDtoTP1() {
//    }
//
//    @Test
//    void convertTPtoPD1() {
//    }

    @Test
    void convertPDtoCoord() {
    }
}