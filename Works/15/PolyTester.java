/* Using IntelliJ?
(1) File=>Project Structure=>Libraries=>+=>From Maven=>org.junit.jupiter:junit-jupiter-api:5.4.2.
(2) change imports:
    import org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.*;
(*) SEE: https://www.jetbrains.com/help/idea/configuring-testing-libraries.html
 */
/* Using BlueJ?
    Just copy this class to project folder.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * a Tester for assignment 15
 * @author Aviv Naaman
 * @version 2020a.1.0
 */
public class PolyTester {
    /**
     * Default CTOR
     */
    public PolyTester() {}

    /**
     * Tests Question 1 : Class PolyNode
     */
    @Test
    public void TestPolyNode() {
        // PolyNode stuff: _COEFFICIENT_POWER
        PolyNode _0_0 = new PolyNode(0, 0),
                _0_1 = new PolyNode(1, 0),
                _1_0 = new PolyNode(0, 1),
                _1_1 = new PolyNode(1,1),
                _minus1_1 = new PolyNode(1, -1),
                _minus1_0 = new PolyNode(0, -1),
                _5_minus1 = new PolyNode(-1,5), // invalid values
                _5_minus10 = new PolyNode(-10, 5), // invalid values
                _5_5 = new PolyNode(5,5),
                _10_5 = new PolyNode(5,10),
                _minus1_5 = new PolyNode(5,-1),
                _minus5_1 = new PolyNode(1,-5);

        // Test toString and basic ctor.
        assertEquals("", _0_0.toString());
        assertEquals("", _0_1.toString());
        assertEquals("1.0", _1_0.toString());
        assertEquals("x", _1_1.toString());
        assertEquals("-1.0", _minus1_0.toString());
        assertEquals("-x", _minus1_1.toString());
        assertEquals("", _5_minus1.toString()); // invalid => ""
        assertEquals("", _5_minus10.toString()); // invalid => ""
        assertEquals("5.0x^5", _5_5.toString());
        assertEquals("10.0x^5", _10_5.toString());
        assertEquals("-x^5", _minus1_5.toString());
        assertEquals("-5.0x", _minus5_1.toString());

        /* Don't forget we <3 Aliasing! */
        // test CTOR aliasing:
        PolyNode test = new PolyNode(7, 2, _10_5);
        assertTrue(test.getNext() == _10_5);
        // test setNext aliasing:
        test.setNext(_minus5_1);
        assertTrue(test.getNext() == _minus5_1);

        // Test setPower
        test.setPower(1); // should change
        assertEquals(1, test.getPower());
        test.setPower(0); // should change
        assertEquals(0, test.getPower());
        test.setPower(-1); // shouldn't change
        assertEquals(0, test.getPower());
        test.setPower(3); // should change
        assertEquals(3, test.getPower());
        test.setPower(-2); // shouldn't change
        assertEquals(3, test.getPower());

        // Test setCoefficient
        test.setCoefficient(-1.2);
        assertEquals(-1.2, test.getCoefficient(), 0.0);
        test.setCoefficient(0);
        assertEquals(0, test.getCoefficient(), 0.0);
        test.setCoefficient(1.35);
        assertEquals(1.35, test.getCoefficient(), 0.0);

        // test copy CTOR:
        PolyNode test0 = new PolyNode(test);
        assertEquals(1.35, test.getCoefficient(), 0.0);
        assertEquals(3, test.getPower());
        assertTrue(test0.getNext() == test.getNext() && test0.getNext() == _minus5_1);
    }

    /**
     * Tests Question 2 : Class Polynom
     */
    @Test
    public void TestPolynom() {
        PolyNode newP1Head = new PolyNode(5, 5);
        Polynom p = new Polynom(), p1 = new Polynom(newP1Head);
        testToStringAndAdd(p,p1);
        // test that the Polynom IS sorted by powers:
        // p
        PolyNode node = p.getHead();
        int prevPower = Integer.MAX_VALUE;
        while (node != null) {
            assertTrue(prevPower > node.getPower());
            node = node.getNext();
        }
        // p1
        node = p1.getHead();
        prevPower = Integer.MAX_VALUE;
        while (node != null) {
            assertTrue(prevPower > node.getPower());
            node = node.getNext();
        }

        // test getHead:
        assertTrue(newP1Head == p1.getHead().getNext());
        assertFalse(new PolyNode(newP1Head) == p.getHead());
        assertFalse(new PolyNode(newP1Head.getPower(), newP1Head.getCoefficient(), newP1Head.getNext()) == p.getHead());


        // test setHead : Coming Soon..

        // test multByScalar
        assertEquals("-2.0x^2-x-1.0", p.multByScalar(1).toString());
        assertEquals("-4.0x^2-2.0x-2.0", p.multByScalar(2).toString());
        assertEquals("8.0x^2+4.0x+4.0", p.multByScalar(-2).toString());
        assertEquals("-48.0x^2-24.0x-24.0", p.multByScalar(-6).toString());
        assertEquals("", p.multByScalar(0).toString());
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+7.0x^2+10.0", p1.multByScalar(1).toString());
        assertEquals("x^6+2.0x^5-4.0x^4+7.0x^3-7.0x^2-10.0", p1.multByScalar(-1).toString());
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+7.0x^2+10.0", p1.multByScalar(-1).toString());
        assertEquals("5.0x^6+10.0x^5-20.0x^4+35.0x^3-35.0x^2-50.0", p1.multByScalar(-5).toString());
        assertEquals("", p1.multByScalar(0).toString());
        assertTrue(p == p.multByScalar(145)); // test that we really return "this"
        // with fractions
        assertEquals("25.0x^3-14.0x^2-23.5", (new Polynom(new PolyNode(3, 5)).addNode(new PolyNode(2, -2.8))).addNode(new PolyNode(0, -4.7)).multByScalar(5).toString()); // (5x^3-2.8x^2-4.7)*5

        // test addPol
        // reset back
        newP1Head = new PolyNode(5, 5);
        p = new Polynom(); p1 = new Polynom(newP1Head);
        testToStringAndAdd(p,p1);
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+5.0x^2-x+9.0",p.addPol(p1).toString());
        newP1Head = new PolyNode(5, 5);
        p = new Polynom(); p1 = new Polynom(newP1Head);
        testToStringAndAdd(p,p1);
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+5.0x^2-x+9.0",p1.addPol(p).toString());
        assertEquals("-4.0x^2-2.0x-2.0", p.addPol(p).toString());
        assertEquals("-4.0x^2-2.0x-2.0", p.addPol(new Polynom()).toString());
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+5.0x^2-x+9.0", (new Polynom()).addPol(p1).toString());
        assertEquals("", (new Polynom()).addPol(new Polynom()).toString());
        assertEquals("-4.0x^2-2.0x-2.0", (new Polynom()).addPol(new Polynom()).addPol(p).toString());
        assertEquals("-4.0x^2-2.0x-2.0", p.toString());
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+5.0x^2-x+9.0", p1.toString());
        Polynom p3 = new Polynom(new PolyNode(0,-1)); // -1
        p3.addNode(new PolyNode(5, 9)); // 9x^5
        p3.addNode(new PolyNode(7,7)); // 7x^7
        assertEquals("10.0x^10+7.0x^7+9.0x^5-1.0", p3.addPol(new Polynom(new PolyNode(10,10))).toString());
        assertEquals("10.0x^10+7.0x^7+9.0x^5-1.0", p3.addPol(new Polynom(new PolyNode(5,5)).addPol(new Polynom()).addPol(new Polynom(new PolyNode(5,-5)))).toString());
        assertEquals("10.0x^10+7.0x^7+9.0x^5-6.5x-1.0", p3.addNode(new PolyNode(1, -6.5)).addPol(new Polynom()).toString());
        assertTrue(p == p.addPol(new Polynom())); // test that we really return "this"
        // with fractions
        assertEquals("5.4x^2-1.5x-5.8", new Polynom(new PolyNode(2, 5.4)).addNode(new PolyNode(1,8)).addPol((new Polynom(new PolyNode(0,-5.8))).addNode(new PolyNode(1,-9.5))).toString());

        // multPol
        assertEquals("-40.0x^12-20.0x^11-20.0x^10-28.0x^9-14.0x^8-50.0x^7-18.0x^6-18.0x^5+26.0x^3+17.0x^2+15.0x+2.0", p3.multPol(p).toString());
        assertEquals("40.0x^18+100.0x^17-100.0x^16+268.0x^15-70.0x^14+46.0x^13-182.0x^12-348.0x^11+92.0x^10-460.0x^9-109.0x^8-467.0x^7-290.0x^6-95.0x^5-38.0x^4+278.0x^3+148.0x^2+133.0x+18.0", p1.multPol(p3).toString());
        assertEquals("40.0x^18+100.0x^17-100.0x^16+268.0x^15-70.0x^14+46.0x^13-182.0x^12-348.0x^11+92.0x^10-460.0x^9-109.0x^8-467.0x^7-290.0x^6-95.0x^5-38.0x^4+278.0x^3+148.0x^2+133.0x+18.0",p1.multPol(new Polynom(new PolyNode(0,1))).toString()); // mult by 1
        assertEquals("-4.0x^2-2.0x-2.0", p.multPol(new Polynom(new PolyNode(55, 0))).toString());
        assertEquals("-4.0x^2-2.0x-2.0", p.multPol(new Polynom()).toString());
        assertEquals("16.0x^4+16.0x^3+20.0x^2+8.0x+4.0", p.multPol(p).toString());
        assertEquals("640.0x^22+2240.0x^21+800.0x^20+5008.0x^19+2128.0x^18+4576.0x^17-1832.0x^16-7048.0x^15-7648.0x^14-14120.0x^13-10776.0x^12-19072.0x^11-17604.0x^10-18212.0x^9-12100.0x^8-2248.0x^7+4136.0x^6+9372.0x^5+7448.0x^4+5244.0x^3+2016.0x^2+676.0x+72.0", p.multPol(p1).toString());
        assertEquals("", (new Polynom()).multPol(p).toString());
        assertEquals("", (new Polynom()).multPol(new Polynom()).toString());
        assertEquals("35.75x^7-65.2x^6+22.25x^5-33.0x^3+15.0x^2", (new Polynom(new PolyNode(4,6.5))).addNode(new PolyNode(3,-8.9)).addNode(new PolyNode(0,-6)).multPol((new Polynom(new PolyNode(2,-2.5))).addNode(new PolyNode(3,5.5))).toString());
        assertTrue(p == p.multPol(new Polynom(new PolyNode(0,1)))); // test that we really return "this"
        // with fractions - makes errors
        //assertEquals("47.5x^10-26.6x^9-83.15x^7+21.56x^6+36.19x^4-32.5x^3+18.2x^2+30.55", (new Polynom(new PolyNode(3, 5)).addNode(new PolyNode(2, -2.8))).addNode(new PolyNode(0, -4.7)).multPol((new Polynom(new PolyNode(7,9.5))).addNode(new PolyNode(4,-7.7)).addNode(new PolyNode(0,-6.5))).toString()); // (5x^3-2.8x^2-4.7)*(9.5x^7-7.7x^4-6.5)

        // differential
        assertEquals("14080.0x^21+47040.0x^20+16000.0x^19+95152.0x^18+38304.0x^17+77792.0x^16-29312.0x^15-105720.0x^14-107072.0x^13-183560.0x^12-129312.0x^11-209792.0x^10-176040.0x^9-163908.0x^8-96800.0x^7-15736.0x^6+24816.0x^5+46860.0x^4+29792.0x^3+15732.0x^2+4032.0x+676.0", p.differential().toString());
        assertEquals("295680.0x^20+940800.0x^19+304000.0x^18+1712736.0x^17+651168.0x^16+1244672.0x^15-439680.0x^14-1480080.0x^13-1391936.0x^12-2202720.0x^11-1422432.0x^10-2097920.0x^9-1584360.0x^8-1311264.0x^7-677600.0x^6-94416.0x^5+124080.0x^4+187440.0x^3+89376.0x^2+31464.0x+4032.0",p.differential().toString());
        assertEquals("195840.0x^15+408000.0x^14-336000.0x^13+731640.0x^12-152880.0x^11+78936.0x^10-240240.0x^9-344520.0x^8+66240.0x^7-231840.0x^6-36624.0x^5-98070.0x^4-34800.0x^3-5700.0x^2-912.0x+1668.0", p1.differential().differential().differential().toString()); // triple differential

        assertEquals("", new Polynom().differential().toString()); // (0)'=0
        assertEquals("", new Polynom(new PolyNode(0, 345678)).differential().toString()); // (345678)'=0
        assertTrue(p == p.differential()); // test that we really return "this"

        // with fractions
        assertEquals("26.5x^4-18.8x^3+5.6", new Polynom(new PolyNode(5,5.3)).addNode(new PolyNode(4,-4.7)).addNode(new PolyNode(1, 5.6)).addNode(new PolyNode(0, 8.7)).differential().toString()); // (5.3x^5-4.7x^4+5.6x+8.7)' =26.5x^4-18.8x^3+5.6


    }

    /**
     * Tests addNode and resets polynoms to required state.
     */
    private void testToStringAndAdd(Polynom p, Polynom p1) {
        // Test toString & addNode
        assertEquals("", p.toString());
        p.addNode(new PolyNode(0,0));
        assertEquals("", p.toString());
        p.addNode(new PolyNode(50, 0));
        assertEquals("", p.toString());
        p.addNode(new PolyNode(0, -1));
        assertEquals("-1.0", p.toString());
        p.addNode(new PolyNode(1, -1));
        assertEquals("-x-1.0", p.toString());
        p.addNode(new PolyNode(2, -2));
        assertEquals("-2.0x^2-x-1.0", p.toString());

        assertEquals("5.0x^5", p1.toString());
        p1.addNode(new PolyNode(5, 5));
        assertEquals("10.0x^5", p1.toString());
        p1.addNode(new PolyNode(0, -2));
        assertEquals("10.0x^5-2.0", p1.toString());
        p1.addNode(new PolyNode(0,0));
        assertEquals("10.0x^5-2.0", p1.toString());
        p1.addNode(new PolyNode(3, -7));
        assertEquals("10.0x^5-7.0x^3-2.0", p1.toString());
        p1.addNode(new PolyNode(4, 4));
        assertEquals("10.0x^5+4.0x^4-7.0x^3-2.0", p1.toString());
        p1.addNode(new PolyNode(6, -1));
        assertEquals("-x^6+10.0x^5+4.0x^4-7.0x^3-2.0", p1.toString());
        p1.addNode(new PolyNode(2, 7));
        assertEquals("-x^6+10.0x^5+4.0x^4-7.0x^3+7.0x^2-2.0", p1.toString());
        p1.addNode(new PolyNode(5, -12));
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+7.0x^2-2.0", p1.toString());
        p1.addNode(new PolyNode(0, 12));
        assertEquals("-x^6-2.0x^5+4.0x^4-7.0x^3+7.0x^2+10.0", p1.toString());
        assertTrue(p1.addNode(new PolyNode(6,0)) == p1); // test that we really return "this"
    }
}
