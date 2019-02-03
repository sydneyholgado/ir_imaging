package calculation;

import visualization.ImageVisual;
import images.APImage;

@SuppressWarnings("restriction")
public class GridFinder {
	
	public static int GRID_SIZE = 25;	// in pixels

	public static void main(String[] args) {
		APImage img = new APImage("images/CurvedCorkNov7/42 A8.jpg");
		
		ImageVisual.showGrid(img, 0, 0, GRID_SIZE);
	}
	
}
