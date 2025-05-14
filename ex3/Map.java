package assignments.ex3;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable {
	private int[][] _map;
	private boolean _cyclicFlag = true;
	public static final int black = -1;

	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 *
	 * @param w - wight of the map
	 * @param h - height of the map
	 * @param v - value of the map
	 */
	public Map(int w, int h, int v) {
		init(w, h, v);
	}

	/**
	 * Constructs a square map (size*size).
	 *
	 * @param size the size of the map
	 */
	public Map(int size) {
		this(size, size, 0);
	}

	/**
	 * Constructs a map from a given 2D array.
	 *
	 * @param data a matrix to build the map according
	 */
	public Map(int[][] data) {
		init(data);
	}

	/**
	 * Construct a map2D w*h matrix of integers.
	 *
	 * @param w the width of the underlying 2D array.
	 * @param h the height of the underlying 2D array.
	 * @param v the init value of all the entries in the 2D array.
	 */
	@Override
	public void init(int w, int h, int v) {
		// add you code here
		// Check that w, h, v are non-negative
		if (w<0 || h<0 ) {return;}

		_map = new int[w][h]; //construct a map2D w*h

		//set each value to the value v
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				_map[i][j] = v;
			}
		}
		////////////////////
	}

	/**
	 * Constructs a 2D raster map from a given 2D int array (deep copy).
	 * @throws RuntimeException if arr == null or if the array is empty or a ragged 2D array.
	 * @param arr a 2D int array.
	 */
	@Override
	public void init(int[][] arr) {
		// add you code here
		if (arr == null){
			throw new RuntimeException("arr is null");
		}
		_map = new int[arr.length][arr[0].length]; //build _map like the arr

		if (isRagged(_map) || isRagged(arr)) {
			throw new RuntimeException("is Ragged");
		}
		// deep copy each row from arr to _map
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null){
				throw new RuntimeException("is null");}
			_map[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		////////////////////
	}

	/**
	 * Computes a deep copy of the map2D matrix
	 * @return deep copy of the matrix
	 */
	@Override
	public int[][] getMap() {
		int[][] ans = null;
		// add you code here
		if (_map == null) {return ans;} //if _map equal to null return null

		ans = new int[_map.length][]; //save space for rows

		//deep copy each row from _map to ans
		for (int i = 0; i < _map.length; i++) {
			ans[i] = Arrays.copyOf(_map[i], _map[i].length);
		}
		////////////////////
		return ans;
	}

	@Override
	public int getWidth() {//the width of this 2D map
		return _map.length;
	}
	@Override
	public int getHeight() { //the height of this 2D map
		return _map[0].length;
	}

	@Override
	public int getPixel(int x, int y) { //get the value at the pixel in map[x][v]
			return _map[x][y];
		}

	@Override
	public int getPixel(Pixel2D p) { //get the value at the pixel in map
		return this.getPixel(p.getX(), p.getY());
	}

	@Override
	public void setPixel(int x, int y, int v) { //set the value at the pixel in map [x][v]
		_map[x][y] = v;
	}

	@Override
	public void setPixel(Pixel2D p, int v) {//set the value at the pixel in map
		setPixel(p.getX(), p.getY(), v);
	}


	@Override
	/**
	 *
	 * Fill the connected component of start pixel (p) in the new color (new_v)
	 * it will fill all connected pixels that have the same original color as the starting pixel.
	 * use arraylist to keep the pixels for each pixel in the arraylist change its color to the new color and add correct neighboring pixels.
	 * repeat until all correct pixels are filled.
	 * see:
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
		// add you code here
		int ans = 0; //the number of filled pixels
		int preColor = getPixel(xy); //check what the previous color of pixel

		//System.out.println("Start fill in (" + xy.getX() + ", " + xy.getY() + ") new color " + new_v + "  previous color " + preColor);
		if (preColor==new_v){return ans;} //if the color is same no pixels need to be changed

		ArrayList<Pixel2D> arr = new ArrayList<>(); // create a list to keep pixels
		Pixel2D p1 = new Index2D (xy.getX(),xy.getY()); //start pixel
		arr.add(p1); 	//add start pixel
		setPixel(p1, new_v); //set start pixel to new color
		ans = 1; // one pixel filled

		boolean isCyclic = isCyclic(); //check if the map is cyclic

		//remove all pixels in the arraylist until it is empty
			while (!arr.isEmpty()) {
				Pixel2D curr = arr.remove(arr.size() - 1); //pop from the end of the arraylist
				int x = curr.getX(); //get x of curr
				int y = curr.getY(); //get y of curr
				//System.out.println("pixel (" + x + ", " + y + ")");

				//check neighbor pixels and add them to the arraylist if correct, also update the ans according the pixel is field.
					ans += add(x, y-1, preColor, new_v, arr, isCyclic);//down
					ans += add(x, y+1, preColor, new_v, arr, isCyclic); //up
					ans += add(x+1, y, preColor, new_v, arr, isCyclic);//right
					ans += add(x-1, y, preColor, new_v, arr, isCyclic);//left
				}
			return ans; //return the number of filled pixel
		}


	/**
	 * Computes the distance of the shortest path (minimal number of consecutive neighbors) from p1 to p2.
	 * Notes: the distance is using computing the shortest path and returns its length-1, as the distance from a point
	 * to itself is 0, while the path contains a single point.
	 */
	public int shortestPathDist(Pixel2D p1, Pixel2D p2, int obsColor) {
		// add you code here
		if(p1.equals(p2)){return 0;} //if start and end are same (edge case)

		 Map2D alldismap = allDistance(p1, obsColor); //compute the distance map of p1
		if (alldismap == null) {return -1;} //check that the map is not null (edge case)

		int dis = alldismap.getPixel(p2.getX(), p2.getY()); //get the distance to p2 from the map
		if (dis == black){return -1;} //check that the pixel is not black (edge case)

		return dis; //distance from p1 to p2
		////////////////////
	}

	@Override
	/**
	 * Finds the shortest path from pixel p1 to pixel p2 using a BFS-like approach.
	 * It calculates the distance map from p1.
	 * it back from p2 to p1 to build the path and returns this.
	 * see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] ans = null;
		// add you code here
		if (p1.equals(p2)) { //check if the start and end are same
			return new Pixel2D[]{p1}; //if so, return array with p1
		}

		Map2D map = allDistance(p1, obsColor); //creat distance map from p1 to all points

		if (map == null) { //if the map is null its mean that no path
			return null;}

		int dis = map.getPixel(p2.getX(), p2.getY()); // in the map, p2 is the value of the distance between p1 and p2
		if (dis== black){return null;} //if p2 is black, it means it is unreachable

		Pixel2D[] paths = new Pixel2D[dis+1]; //create an array with the length of distance+1
		Pixel2D curr = p2; // start from the end point: p2
		int num = dis; //index in the paths array

		//start to back from p2 towards p1
		while (dis>0){
			if (num< 0) {
				throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + num);
			}
			paths[num--] = curr; //put the current pixel in the path array
			//get the neighbors pixels of the current pixel
			ArrayList<Pixel2D> near = getnear(curr.getX(),curr.getY()); //It is also possible to do with simple array of 4 members(more efficient). I didn't make it
		for (int i=0; i< near.size(); i++) { //go over each of the neighbors
			Pixel2D p = near.get(i);

			// Check if this neighbor is one step closer to p1
			if (map.getPixel(p.getX(), p.getY()) == map.getPixel(curr.getX(), curr.getY()) - 1) {
				curr = p; //move to this neighbor
				dis--; //decrease the distance
				break; //exit the from the loop
			}
		}
		}
		if (num>=0){
		paths[num] = p1;} //set the start pixel p1 in the path array
		return paths; // return the array from p1 to p2
	}

	/**
	 * Computes the shortest path that visits all given points
	 * @param points an array with a set of points.
	 * @param obsColor the color which is addressed as an obstacle.
	 * @return array of points with the shortest path that visits all points
	 *
	 * The function constructs the path using the Nearest Neighbor Algorithm:
	 * Calculates the shortest path distance between each pair of points and save them in a matrix.
	 * Starts from the first point, repeatedly selects the nearest unvisited point, and updates the path until all points are visited.
	 * see:
	 * https://en.wikipedia.org/wiki/Nearest_neighbor_algorithm
	 */
	@Override
	public Pixel2D[] shortestPath(Pixel2D[] points, int obsColor) {
		Pixel2D[] ans = null; //creat result array to null
		// add you code here
		if (points == null || points.length<2) {
			return ans; //return null if there are less than 2 points
		}

		int num = points.length; //get the number of points
		int[][] matdis = new int[num][num]; // create a matrix to save all distances between points

		for (int i=0; i<num; i++) {
			for (int j=0; j<num; j++) {
				if (i != j) { //not comparing a point with itself
					//calculate the distance between points
					matdis[i][j] = shortestPathDist(points[i], points[j], obsColor);
					if (matdis[i][j] ==-1) {
						return ans; //return null if not have path between the two points
					}
				}
			}
		}
		boolean[] passed = new boolean[num]; //arr to save points have been visited
		ArrayList<Pixel2D> path = new ArrayList<>(); //new arraylist to save the path
		int curr =0; // start from the first point
		passed[curr] = true; //mark the start point as visited
		path.add(points[curr]); //add the start point to the path

		/*System.out.println("distance matrix:");
		for (int k = 0; k < mapdis.length; k++) {
			System.out.println(Arrays.toString(mapdis[k]));
		}*/
		// loop through all points except the last one
		for (int i = 0; i < num-1; i++) {
			int next = -1; //the next point to visit
			int minDis = Integer.MAX_VALUE; //creat the minimum distance to a very large value

			//loop through all points to find the nearest point that not visited
			for (int j = 0; j < num; j++) {
				if (!passed[j] && matdis[curr][j] < minDis) { //check if point is unvisited and a smaller distance than the current minimum
					minDis = matdis[curr][j]; //update the minimum distance
					next = j; //set the index of the next point to visit
				}
			}
		/*	System.out.println("current path: ");
			for (int k = 0; k < path.size(); k++) {
				System.out.println(path.get(k));
			}
		*/
			if (next ==-1) {//no path found
				System.out.println("No path found.");
				return ans; //return null
			}
			path.add(points[next]); //add the next point to the path
			passed[next]=true; //mark the next point to visited
			curr = next;//move to the next point
		}
		/*System.out.println("path length: " + path.size());
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
		*/
		////////////////////
		return path.toArray(new Pixel2D[path.size()]); //return the path as array
	}

	@Override
	public boolean isInside(Pixel2D p) {
		return isInside(p.getX(), p.getY());
	}

	@Override
	public boolean isCyclic() {
		return _cyclicFlag;
	}

	@Override
	public void setCyclic(boolean cy) {
		_cyclicFlag = cy;
	}

	/**
	 * checks if the given x, y are within the map
	 * @param x to check
	 * @param y to check
	 * @return true if the coordinates are within the map's width and height
	 */
	private boolean isInside(int x, int y) {
		return x>=0 && y>=0 && x<this.getWidth() && y<this.getHeight();
	}

	/**
	 * Computes the distance map from a given point to all other points in the map
	 * @param start the source (starting) point
	 * @param obsColor the color representing obstacles
	 * @return a Map2D where each cell contains the distance from the starting point to that cell.
	 */
	@Override
	public Map2D allDistance(Pixel2D start, int obsColor) {
		// add you code here
		//create a new Map2D to save distances with the same width and height and obsColor
		Map2D ans = new Map(this.getWidth(),this.getHeight(), black);

		ans.setCyclic(this.isCyclic());//set the cyclic to the new map

		if (this.getPixel(start.getX(),start.getY()) != obsColor) { //check if the starting point is not an obsColor
			ans.setPixel(start.getX(),start.getY(), 0); //set the starting point to 0
			allDistance(ans, 0, obsColor); //compute distances from the starting point
		}
		////////////////////
		return ans; //return the map
	}

	/**
	 * Counts the number of connected components.
	 *
	 * @param obsColor the color used for obstacles
	 * @return the number of groups found
	 *
	 * It loops through each pixel.
	 * For each pixel that hasn’t been visited and isn’t an obstacle, it starts a new search.
	 * marks all connected pixels (part of the same group) as visited, it counts each new group it finds the number of connected components.
	 */

	@Override
	public int numberOfConnectedComponents(int obsColor) {
		int ans = 0; //the number of connected components
		// add you code here
		//create a matrix boolean array to save visited pixels
		boolean [][] passed = new boolean[this.getWidth()][this.getHeight()];

		//go over each pixel in the map
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
			//if the pixel is not visited and not an obsColor
			if (!passed[x][y] && this.getPixel(x,y) != obsColor) {
				//System.out.println("Start new component at (" + x + ", " + y + ")");

				//mark all connected pixels as visited
				mark(x,y,obsColor,passed);
				ans++;//update the number of components
			}
		}
		}
		////////////////////
		//System.out.println("Total components: " + ans);
		return ans;//return the number of connected components
	}


	/**
	 * Checks if this Map2D is equal to another object.
	 *
	 * @param ob the object to compare
	 * @return true if the objects are equal
	 */
	@Override
	public boolean equals(Object ob) {
		boolean ans = false;
		// add you code here
		if (ob==null || !(ob instanceof Map2D)) { //check if the object null or not Map2D
			return ans;
		}
		Map2D obm = (Map2D) ob;
		if (this._cyclicFlag !=obm.isCyclic()) { //check if the cyclic flag is same
			return ans;		}
		ans = Arrays.deepEquals(this._map, obm.getMap()); // compare the maps
		////////////////////
		return ans;
	}

	////////////////////// Private Methods ///////////////////////
	// add you code here

	//This is a private function of init
	// Checks if the 2D array is ragged
	private static boolean isRagged(int[][] arr) {
		if (arr == null || arr.length == 0) {return false;}
			int lengthRow = arr[0].length;
			for (int i = 0; i <  arr.length; i++) {
				 if ((arr[i].length) != lengthRow) { //comparing the length of the current line to lengthRow
					return true; //if the length is different, we have a ragged array
				}
			}
		return false;

	}
	//This is a private function of shortestpath
	//return the neighboring pixels of a given pixel
	private ArrayList<Pixel2D> getnear(int x, int y) {
		ArrayList<Pixel2D> near = new ArrayList<>(); //create an arraylist to sava neighboring pixels

		addnear(near,x,y+1); //up
		addnear(near,x,y-1); //down
		addnear(near,x+1,y); //right
		addnear(near,x-1,y); //left

		return near; //Return the arraylist of neighboring pixels
	}

	//adds a neighboring pixel to the list if it is within bounds.
	private boolean addnear(ArrayList<Pixel2D> near, int x, int y){
		if (isCyclic()) { //using the modulo to deal with the edges of the map
			x = (x + getWidth()) % getWidth();
			y = (y + getHeight()) % getHeight();
		}
		if (isInside(x, y)) {//check if the pixel is within the map
				near.add(new Index2D(x, y)); //add the pixel to the arraylist
				return true; //return true if the pixel was added
		}
		return false; //return false if the pixel is outside the map
	////////////////////
}

	//This is a private function of fill
	//updates the color of a pixel and adds it to the list
	//return The number of pixels updated
	private int add(int x, int y, int preColor, int new_v, ArrayList<Pixel2D> arr, boolean isCyclic) {
		if (isCyclic){ //using the modulo to deal with the edges of the map
			x = (x + getWidth()) % getWidth();
			y = (y + getHeight()) % getHeight();
		}
		int num = 0; //the number of filled pixel in 'add'

		//check if x,y are inside the map and the pixel now is pre-color
		if (isInside(x, y) && getPixel(x, y) == preColor) {
			setPixel(x, y, new_v); //set the pixel to the new color
			arr.add(new Index2D(x, y)); //add the pixel to the arraylist
			num++;
		}
		return num; //return the number of filled pixel
	}
	//This is a private function of allDistance
	//updates the distance for each pixel in the map
	private void allDistance(Map2D map, int rad, int obsColor) {
		boolean num = true;
		while (num){ //continue until that no more pixel updated
			num = false;

			//go over each pixel in the map
			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {

					if (map.getPixel(x,y) == rad) {//if the current pixel's distance is equal to 'rad'
						//check and update the distance for nears pixels
						if(addDist(map, new Index2D(x,y+1),rad, obsColor))num = true; //up
						if(addDist(map, new Index2D(x,y-1),rad, obsColor))num = true; //down
						if(addDist(map, new Index2D(x+1,y), rad, obsColor))num = true; //right
						if(addDist(map, new Index2D(x-1,y),rad, obsColor))num = true;  //left
						//System.out.println("pixel - ("+x+", "+y+"): "+map.getPixel(x,y)+"	 rad: "+rad);
					}
				}
			}
			rad++; //update the distance
		}
	}

	//updates the distance of a pixel if it within the bounds
	private boolean addDist (Map2D map, Pixel2D p ,int rad,int obsColor){
		int x = p.getX();
		int y = p.getY();

		if (map.isCyclic()) { //using modulo to deal with the edges of the map
			x = (x + map.getWidth()) % map.getWidth();
			y = (y + map.getHeight()) % map.getHeight();
		}else if (!isInside(x,y)) {return false;} //return false if the point is outside the map

		// if the pixel is unvisited and not an obsColor
		if (map.getPixel(x,y) == black && this.getPixel(x,y)!= obsColor && isInside(x,y)){
			// set the pixel's distance and return true
			map.setPixel(x,y,rad+1);
			return true;
		}
		return false; //return false if no update was made
	}

	//This is a private function of numberOfConnectedComponents
	//marks pixels in the area, avoiding obstacles and previously visited pixels.
	//passed[][] is save visited pixels
	private void mark(int x1, int y1, int obsColor, boolean[][] passed) {
		boolean isCyclic = (isCyclic());
		ArrayList<int[]> arr = new ArrayList<>();
		arr.add(new int[]{x1, y1});

		while (!arr.isEmpty()) {
			int[] curr = arr.remove(arr.size() - 1);
			int x = curr[0];
			int y = curr[1];

			if (isCyclic) { //using the modulo to deal with the edges of the map
				x = (x + getWidth()) % getWidth();
				y = (y + getHeight()) % getHeight();
			}
			//if the pixel already been visited, is an obstacle, or is outside the map, skip
			if (passed[x][y] || getPixel(x, y) == obsColor || !isInside(x, y)) {
				continue; //skip
			}
			passed[x][y] = true; //mark the pixel as visited

			// visit neighboring pixels
			addnear(arr,x, y+1,obsColor); //up
			addnear(arr,x, y-1,obsColor); //down
			addnear(arr, x+1, y,obsColor); //right
			addnear(arr,x-1, y,obsColor); //left
		}
	}
	//adds neighboring pixels to a list if it corrects
	private void addnear(ArrayList<int[]> arr, int x, int y, int obsColor) {
		boolean isCyclic = isCyclic();
		if (isCyclic) {
			x = (x + getWidth()) % getWidth();
			y = (y + getHeight()) % getHeight();
		}
		if (isInside(x, y) && getPixel(x, y) != obsColor) {
			arr.add(new int[]{x, y});
		}
	}


}




