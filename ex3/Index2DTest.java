package assignments.ex3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Index2DTest {
    private static Index2D I1, I2;


    @BeforeEach
    void setUp() throws Exception {
        I1 = new Index2D(1,2);
        I2 = new Index2D(3,4);

    }
    @Test
    void Index2D(){
        String s = "1,2";
        String s0 = "1,,2"; //not valid format
        String s2 = "(1,2)"; //not valid format
        Index2D s1 = new Index2D(s);
        assertEquals(s1,I1);
        assertNotEquals(s1,I2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Index2D(s0);
        }, "String not valid must to throw IllegalArgumentException");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Index2D(s2);
        }, "String not valid must to throw IllegalArgumentException");
    }

    @Test
    void distance2D() {
        double x1 = I1.getX(), y1 = I1.getY(); //Math.sqrt(Math.pow(x1-x2)^2.0+Math.pow(y1-y2)^2.0);
        double x2 = I2.getX(), y2 = I2.getY();
        double d1 = I1.distance2D(I2);
        double d2 = Math.sqrt(Math.pow(x1-x2,2.0)+Math.pow(y1-y2,2.0));
        assertEquals(d1,d2, Index2D.EPS);
    }

    @Test
    void distance2DSame() {
        Index2D d3 = new Index2D(5,7);
        double d4 = d3.distance2D(d3);
        assertEquals(0,d4,Index2D.EPS);
    }

    @Test
    void testToString() {
        String str1 = I1.toString(); 	// str1 = "1,2"
        String t1 = "1,2";
        String str2 = I2.toString(); 	// str2 = "3,4"
        String t2 = "3,4";
        boolean b = I1 == I2;             // false;
        assertFalse(b);
        Assertions.assertEquals(t1, str1);
        Assertions.assertEquals(t2, str2);


    }

    @Test
    void testEquals() {
        int x = I1.getX(), y = I1.getY();
        Index2D t0 = new Index2D(x,y);
        Index2D t1 = new Index2D(I1);
        Pixel2D p0 = null;

        Assertions.assertEquals(t0,t1); // same
        assertNotEquals(I2, t1); //different
        Assertions.assertFalse(t0.equals(p0)); // null
        Assertions.assertFalse(t0.equals("string")); // different object
        
    }
    @Test
    void testNegativeValues() {
        Index2D I3 = new Index2D(-1, -2);
        assertEquals(-1, I3.getX());
        assertEquals(-2, I3.getY());
    }
}