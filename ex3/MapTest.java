package assignments.ex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
This is a very basic Testing class for Map - please note that this JUnit
 contains only a very limited testing method and should be added many other
 methods for testing all the functionality of Map2D - both in correctness and in runtime.
*/
 class MapTest {
    /**
     * _m_3_3 =
     * 0,1,0
     * 1,0,1
     * 0,1,0
     *
     * _m0 =
     * 1,1,1,1,1
     * 1,0,1,0,1
     * 1,0,0,0,1
     * 1,0,1,0,1
     * 1,1,1,1,1
     * 1,0,1,0,1
     *
     * 1, 1, 1, 1, 1
     * 1,-1, 1,-1, 1
     * 1,-1,-1,-1, 1
     * 1,-1, 1,-1, 1
     * 1, 1, 1, 1, 1
     * 1,-1, 1,-1, 1
     *
     * m2[3][2] = 0, m2[1][2] = 10, |sp|=11 (isCiclic = false;}
     * =============
     * 7, 8, 9, 1, 7
     * 6,-1,10,-1, 6
     * 5,-1,-1,-1, 5
     * 4,-1, 0,-1, 4
     * 3, 2, 1, 2, 3
     * 4,-1, 2,-1, 4
     *
     * m[3][2] = 0, m2[1][2] = 5, |sp|=5 (isCiclic = true;}
     * 5, 4, 3, 4, 5
     * 6,-1, 4,-1, 6
     * 5,-1,-1,-1, 5
     * 4,-1, 0,-1, 4
     * 3, 2, 1, 2, 3
     * 4,-1, 2,-1, 4
     */
    public static final int black = -1 ,grey = 0;
    private int[][] _map = {{1,1,1,1,1}, {1,0,1,0,1}, {1,0,0,0,1}, {1,0,1,0,1}, {1,1,1,1,1}, {1,0,1,0,1}};
    private int[][] _map1 = {{1,1,1,1,1,1,1,1},{1,0,0,0,0,0,0,1},{1,0,1,1,1,1,1,1},{1,0,1,0,0,0,0,1},{1,0,1,0,1,1,0,1},{1,0,1,1,1,1,0,1},{1,0,0,0,0,0,0,1},{1,1,1,1,1,1,1,1}};
    private int[][] _map2 ={{1,1,1,1},{1,0,0,0},{1,0,1,1},{1,0,0,1},{1,1,1,1}};
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private int[][] _map4 = {{1,0,1,0,1,0,1,0,1},{0,0,1,0,1,0,1,0,1},{1,1,1,0,1,0,1,0,1},{0,0,0,0,1,0,1,0,1},{1,1,1,1,1,0,1,0,1},{0,0,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0,1},{0,0,0,0,0,0,0,0,1},{1,1,1,1,1,1,1,1,1}};
    private int[][] _map5 = {{2,3,3,3,2,2,2},{1,3,3,3,2,2,2},{2,2,2,7,7,7,7},{3,3,3,3,3,3,3},{4,4,4,4,2,2,2},{2,2,2,4,2,2,2},{2,2,2,4,4,4,4}};
    private int[][] _map6 =  {{1,1,1,-1,1,-1,-1,-1,-1},{1,1,1,-1,1,-1,-1,1,-1},{-1,-1,-1,1,-1,1,-1,1,-1},{-1,-1,1,-1,-1,1,-1,1,-1},{-1,1,-1,1,-1,1,-1,1,-1},{1,-1,-1,1,-1,1,-1,1,-1},{-1,-1,-1,1,-1,1,-1,1,-1},{-1,-1,-1,1,-1,1,1,1,1}};
    private int[][] _map7 =  {{0}};
    private int[][] _map8 = new int[1000][1000];
    private Map2D _m0, _m1, _m2, _m3, _m3_3, _m4, _m5,_m6,_m7,_m8,_m9, _m10,_m11;
    @BeforeEach
    public void setuo() {
        _m0 = new assignments.ex3.Map(_map);
        _m1 = new assignments.ex3.Map(_map); _m1.setCyclic(true);
        _m2 = new assignments.ex3.Map(_map); _m2.setCyclic(false);
        _m3 = new assignments.ex3.Map(_map);
        _m3_3 = new assignments.ex3.Map(_map_3_3); _m3_3.setCyclic(false);
        _m4 = new assignments.ex3.Map(_map1); _m4.setCyclic(true);
        _m5 = new assignments.ex3.Map(_map1); _m5.setCyclic(false);
        _m6 = new assignments.ex3.Map(_map2); _m6.setCyclic(false);
        _m7 = new assignments.ex3.Map(_map4); _m7.setCyclic(false);
        _m8 = new assignments.ex3.Map(_map7); _m8.setCyclic(false);
        _m9 = new assignments.ex3.Map(_map5); _m9.setCyclic(false);
        _m10 = new assignments.ex3.Map(_map6); _m10.setCyclic(false);
        _m11 = new assignments.ex3.Map(_map8);_m11.setCyclic(false);
    }
    //Checks if the creat a 500x500 matrix in _m1 works correctly
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        int numfill = _m1.fill(p1,1);
        assertEquals(500*500,numfill);
    }

    //check if the equals method works correctly
    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        assertEquals(_m0,_m3);
        assertNotEquals(_m1,_m2);
        _m3.setPixel(2,2,17);
        assertNotEquals(_m0,_m3);
    }
    //check if the getMap method returns the correct matrix
    @Test
    void getMap() {
        int[][] m0 = _m0.getMap();
        _m1.init(m0);
        assertEquals(_m0,_m1);
        int[][] m1 = _m1.getMap();
        assertArrayEquals(m0, m1, "Maps should be identical");
    }
    //few tests with RuntimeException
    @Test
    void testGetPixelOut() {
        assertThrows(RuntimeException.class, () -> {
            _m0.getPixel(-1, -1);
        });
    }
    /**
     * throws RuntimeException if arr == null or if the array is empty or a ragged 2D array.
     */
    @Test
    void testInit(){
        assertThrows(RuntimeException.class, () -> {
            new Map((int[][]) null);
        });
        assertThrows(RuntimeException.class, () -> {
            new Map(new int[0][0]);
        });
    int[][] r1 = {{1, 2},{3}};
    assertThrows(RuntimeException.class, () -> {
        new Map(r1);
    });}
    /**
     * check the correctness of the fill method
     */
    @Test
    void testFill0() {
        Pixel2D p1 = new Index2D(0,0);
        int f0 = _m0.fill(p1,2);
        assertEquals(f0,21);
        int filled = _m0.fill(p1,2); //try to fill when the starting pixel already has the new color
        assertEquals(0, filled);

        //cyclic should not affect the filling of this map
        int f1 = _m5.fill(p1,2); //_m5 is not cyclic
        int f2 = _m4.fill(p1,2); //_m4 is cyclic
        assertEquals(f2,f1);
    }

    /**
     * Check that your fill algorithm dealing with different cases and edge cases correctly
     */
    @Test
    void testFill1() {
        Pixel2D p1 = new Index2D(0,1);
        _m0.setPixel(p1,0);
        int f0 = _m0.fill(p1,2);
        assertEquals(f0,9);

        //this map that set to not cyclic
        _m0.setCyclic(false);
        int f2 = _m0.fill(p1,3);
        assertEquals(f2,8);

        //the map have only a single pixel
        Pixel2D p2 = new Index2D(0,0);
        int f3 = _m8.fill(p2,2);
        assertEquals(1,f3);
        assertEquals(2, _m8.getPixel(p2));

        _m3_3.setCyclic(true);
        _m3_3.setPixel(p2,1);
        int f4 = _m3_3.fill(p2,2);
        assertEquals(5,f4);

    }

    /**
     *  Check that the allDistance function correctly calculates the distance
     *  from a given start point to all other points in the map.
     */
    @Test
    void testAllDistance() {
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(1,0);

        Map2D m00 = _m0.allDistance(p1, grey);
        assertEquals(6, m00.getPixel(p2));

        //Another test similar to another map
        Pixel2D p3 = new Index2D(4,4);
        Pixel2D p4 = new Index2D(4,0);
        Map2D m01 = _m4.allDistance(p3, grey);
        assertEquals(14, m01.getPixel(p4));

        //Another test similar to this map that set to not cyclic
        Pixel2D p5 = new Index2D(5,0);
        Map2D m02 = _m5.allDistance(p3, grey);
        m02.setCyclic(false);
        assertEquals(25, m02.getPixel(p5));

        //Another test similar to another map
        Pixel2D p6 = new Index2D(2,2);
        Pixel2D p7 = new Index2D(0,3);
        Map2D m03 = _m6.allDistance(p6, grey);
        m03.setCyclic(false);
        assertEquals(13, m03.getPixel(p7));

    }

    /**\
     * checks if the shortestPathDist function correctly calculates the shortest path distance between pairs of points,
     * both in cyclic and non-cyclic maps, and compares the results with expected values
     */
    @Test
    void testshortestPathDist() {
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(1,0);
        Pixel2D p3 = new Index2D(0,2);
        assignments.ex3.Map map = new assignments.ex3.Map(_map);
        int dist1 = map.shortestPathDist(p1, p2, grey);
        assertEquals(6, dist1);

        int dist2 = map.shortestPathDist(p1, p3, grey);
        assertEquals(3, dist2);

        //another test similar to another map
        Pixel2D p5 = new Index2D(4,4);
        Pixel2D p6 = new Index2D(5,0);
        assignments.ex3.Map map1 = new assignments.ex3.Map(_map1);  map1.setCyclic(false);
        int dist3 = map1.shortestPathDist(p5, p6, grey);
        assertEquals(25, dist3);

        //another test similar to this map that set to cyclic
        map1.setCyclic(true);
        int dist4 = map1.shortestPathDist(p5, p6, grey);
        assertEquals(15, dist4);


    }

    /**
     * checks that the shortestPath method correctly calculates the length of the shortest path between two points on different maps.
     * It ensures that the returned path lengths match the expected values.
     */
    @Test
    void testShortestPath1() {
        //checks if the length of the shortest path between two points is as expected on maps.
        Pixel2D p1 = new Index2D(3, 2);
        Pixel2D p2 = new Index2D(1, 2);
        Pixel2D[] path = _m0.shortestPath(p1, p2, grey);
        assertEquals(5, path.length); //checks the length of the array
        path = _m2.shortestPath(p1, p2, grey); //not cyclic
        assertEquals(11, path.length); //checks the length of the array

        //another test similar to another map
        Pixel2D p7 = new Index2D(4, 4);
        Pixel2D p8 = new Index2D(5, 0);
        Pixel2D[] path1 = _m5.shortestPath(p7, p8, grey);
        assertEquals(26, path1.length); //checks the length of the array
        //the position of the points within the array
        assertEquals(path1[0],p7);
        assertEquals(path1[25],p8);

        Pixel2D[] path2 = _m4.shortestPath(p7, p8, grey);
        assertEquals(16, path2.length); //checks the length of the array
        //the position of the points within the array
        assertEquals(path2[0],p7);
        assertEquals(path2[15],p8);
    }

    /**
     *  Checks if the shortest path can receive a map and how many points in it
     *  and return an array with the points in an orderly way
     *  that travel the shortest distance within the map
     */
        @Test
        void testShortestPath2() {
        Pixel2D p3 = new Index2D(0,1);
        Pixel2D p4 = new Index2D(4,1);
        Pixel2D p5 = new Index2D(5,0);
        Pixel2D p6 = new Index2D(2,4);
        Pixel2D[] path4 = _m0.shortestPath(new Pixel2D[] {p3,p4,p6,p5},grey);
        //the position of the necessary points within the array
        assertEquals(path4[0],p3);
        assertEquals(path4[1],p5);
        assertEquals(path4[2],p4);
        assertEquals(path4[3],p6);

        //another test similar to another map
        Pixel2D p7 = new Index2D(4, 4);
        Pixel2D p8 = new Index2D(5, 0);
        Pixel2D p9 = new Index2D(7,3);
        Pixel2D p10 = new Index2D(1,0);
        Pixel2D[] path5 = _m5.shortestPath(new Pixel2D[] {p7,p8,p9,p10},grey);
        //the position of the necessary points within the array
        assertEquals(path5[0],p7);
        assertEquals(path5[1],p9);
        assertEquals(path5[2],p8);
        assertEquals(path5[3],p10);

    }

    /**
     * This test check the number of connected components in different maps
     * using the numberOfConnectedComponents method.
     */
    @Test
    void numberOfConnectedComponents() {
        int num1 = _m3_3.numberOfConnectedComponents(grey);
        assertEquals(4, num1);

        int num2 = _m0.numberOfConnectedComponents(grey);
        assertEquals(1, num2);

        int num3 = _m7.numberOfConnectedComponents(grey);
        assertEquals(5, num3);
        _m7.setCyclic(true);
        int num4 = _m7.numberOfConnectedComponents(grey);
        assertEquals(1, num4);


        int num5 = _m10.numberOfConnectedComponents(black);
        assertEquals(8, num5);
        int num6 = _m10.numberOfConnectedComponents(1);
        assertEquals(5, num6);
        _m10.setCyclic(true);
        int num8 = _m10.numberOfConnectedComponents(black);
        assertEquals(8, num8);
        int num9 = _m10.numberOfConnectedComponents(1);
        assertEquals(3, num9);

        int num10 = _m9.numberOfConnectedComponents(3);
        assertEquals(2, num10);
    }

    /**
     * This test calculate how long it takes to run the shortestPathDist method
     *  from one corner of a 1000x1000 map to the opposite corner.
     *  It checks if this operation takes less than 10 millisecond.
     */
    @Test
    void timeShortestPathDist() {
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(999, 999);

        long start1 = System.nanoTime();
        ((assignments.ex3.Map) _m11).shortestPathDist(p1, p2, grey);
        long end1 = System.nanoTime();

        long timeDist = end1 - start1;
        //  System.out.println("time of shortestPathDist: " + timeDist + " nanoseconds");
        double timemills1 = timeDist / 1_000_000.0;
        assertTrue(timemills1 < 10, "time was" + timemills1 + "is more than 10 milliseconds");
    }

    /**\
     * check the time taken to fill a 1000x1000 map with a value
     * and ensures it completes in under 1000 milliseconds.
     */
    @Test
    void timeFill() {
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(999, 999);
        //go over on each pixel and put the value 1 there
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                Pixel2D p3 = new Index2D(i, j);
                _m11.setPixel(p3, 1); //set each pixel to 1
            }
        }

        //testing how long it takes for fill to paint all the map
        long start2 = System.nanoTime();
        int f1 = _m11.fill(p1, 2);
        long end2 = System.nanoTime();
        assertEquals(1000 * 1000, f1);

        long timefill = end2 - start2;
        // System.out.println("time of fill: " + timefill + " nanoseconds");
        double timemills2 = timefill / 1000000.0;
        assertTrue(timemills2 < 1000, "time was" + timemills2 + "is more than 1000 milliseconds");
    }

    /**
     * check the time taken to find the shortest path between two corners of the map
     * with cyclic or not cyclic behavior.
     */
    @Test
    void timeShortestPath() {
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(999, 999);
        _m11.setCyclic(false);
        long start3 = System.nanoTime();
        Pixel2D[] arr1 = _m11.shortestPath(p1, p2,black);
        long end3 = System.nanoTime();
        assertEquals(1999, arr1.length);

        long timeshortest = end3 - start3;
        // System.out.println("time of shrtestpath: " + timeshrtest + " nanoseconds");
        double timemills3 = timeshortest / 1000000.0;
        assertTrue(timemills3 < 6000, "time was " + timemills3 + "is more than 6000 milliseconds");

        //check the time to find the shortest path with cyclic.
        _m11.setCyclic(true);
        long start4 = System.nanoTime();
        Pixel2D[] arr2 = _m11.shortestPath(p1, p2,black);
        long end4 = System.nanoTime();
        assertEquals(3, arr2.length);

        long timeshortest1 = end4 - start4;
        // System.out.println("time of shrtestpath: " + timeshrtest1 + " nanoseconds");
        double timemills4 = timeshortest1 / 1000000.0;
        assertTrue(timemills4 < 3000, "time was " + timemills4 + "is more than 3000 milliseconds");
    }

}