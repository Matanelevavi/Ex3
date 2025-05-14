package assignments.gui;
import assignments.ex3.Map2D;
import assignments.ex3.Map;
import java.awt.*;
import static assignments.gui.StdDraw.*;



public class Ex3 {
    public static void main(String[] a) {
        int[][] mat = {{1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}};
        int[][] mat1 = {{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}};
        int[][] mat2 = {{1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}};
        int[][] mat3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
        int[][] mat4 = {{1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}};
        int[][] mat5 =  {{2,3,3,3,2,2,2},{1,3,3,3,2,2,2},{2,2,2,7,7,7,7},{3,3,3,3,3,3,3},{4,4,4,4,2,2,2},{2,2,2,4,2,2,2},{2,2,2,4,4,4,4}};
        int [][] mat6 = {{1,1,1,-1,1,-1,-1,-1,-1},{1,1,1,-1,1,-1,-1,1,-1},{-1,-1,-1,1,-1,1,-1,1,-1},{-1,-1,1,-1,-1,1,-1,1,-1},{-1,1,-1,1,-1,1,-1,1,-1},{1,-1,-1,1,-1,1,-1,1,-1},{-1,-1,-1,1,-1,1,-1,1,-1},{-1,-1,-1,1,-1,1,1,1,1}};
        int[][] mat7 ={{0}};
        int[][] mat8 = new int[1000][1000];
        int[][] mat9 = {{7, 8, 9, 1, 7}, {6, -1, 10, -1, 6}, {5, -1, -1, -1, 5}, {4, -1, 0, -1, 4}, {3, 2, 1, 2, 3}, {4, -1, 2, -1, 4}};
        int[][] mat10 = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};
        //mat[4][4] =1;

        Map2D map = new Map(mat9); // Create a Map2D according to the selected matrix
        drawMap(map); //call the drawMap method to draw the map


    }

    /**
     * Draws a visual of the map.
     * The method loops each pixel in the map, determines its color based on its value,
     * and draws a filled square for each pixel.
     *
     * @param map The Map2D containing the pixel values to be drawn.
     */
    public static void drawMap(Map2D map) {
        StdDraw.setScale(0, 1); //set the scale of the drawing area
        StdDraw.clear(); //clear the drawing area

        // Loop each pixel in the table
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int v = map.getPixel(x, y); //get the value of the pixel at x,y

                Color color = getColorForValue(v); //determine the color according the pixel value
                StdDraw.setPenColor(color); //set the color for drawing

                double r = 1.0 / (3 * map.getWidth()); //calculate the radius for drawing each pixel
                double x1 = (x + 0.5) / map.getWidth(); //calculate the x for the center of the square
                double y1 = (y + 0.5) / map.getHeight();//calculate the y for the center of the square

                StdDraw.filledSquare(x1, y1, r); //draw a filled each square


            }
        }

        StdDraw.show();  // show the drawn map
        //StdDraw.pause(2);
    }

    private static Color getColorForValue(int v) { //get color based on value
        switch (v) {
            case -1: return BLACK;
            case 0: return GRAY;
            case 1: return YELLOW;
            case 2: return RED;
            case 3: return GREEN;
            case 4: return MAGENTA;
            case 5: return CYAN;
            case 6: return ORANGE;
            case 7: return BLUE;
            case 8: return LIGHT_GRAY;
            case 9: return DARK_GRAY;
            default: return PINK;//
        }


    }
}

