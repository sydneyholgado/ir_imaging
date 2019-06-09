package calculation;

import java.awt.Color;

import images.APImage;
import images.Pixel;

/**
 * Samples colors on the scale at bottom of image.
 * 
 * @author AlexanderWu
 */
public class ScaleSampler {

	/** x for lower temperature, is ACTUALLY LARGER */
	public static final int LOWER_X = 630;
	/** x for higher temperature, is ACTUALLY SMALLER */
	public static final int UPPER_X = 20;
	
	public static Color lowerColor(APImage img) {
		return getColor(img, LOWER_X);
	}

	public static Color upperColor(APImage img) {
		return getColor(img, UPPER_X);
	}
	
	private static Color getColor(APImage img, int x) {
		Pixel p = img.getPixel(x, img.getHeight() - 1);
		return new Color(p.getRed(), p.getGreen(), p.getBlue());
	}

	public static double getTemperature(APImage img, Color col, int lowerTemp, int upperTemp) {
		int minX = UPPER_X;
		double minDiff = colorDifference(getColor(img, UPPER_X), col);
		for (int x = UPPER_X + 1; x <= LOWER_X; x++) {
			double diff = colorDifference(getColor(img, x), col);
			if (diff < minDiff) {
				minX = x;
				minDiff = diff;
			}
		}
		return upperTemp - (minX - UPPER_X + 0.0) * (upperTemp - lowerTemp) / (LOWER_X - UPPER_X);
	}

	private static double colorDifference(Color c1, Color c2) {
		return Math.abs(c1.getRed() - c2.getRed()) + Math.abs(c1.getGreen() - c2.getGreen()) +
			   Math.abs(c1.getBlue() - c2.getBlue());
	}

	public static void main(String[] args) {
		APImage img = new APImage("images/CurvedCorkNov7/42 A8.jpg");
		
//		Color c = ColorAverager.averageInSquare(img, 0, 0, GridFinder.GRID_SIZE);
		Color c = Color.green;
		System.out.println(getTemperature(img, c, 22, 25));
		System.exit(0);
//		ImageVisual.fillSquare(img, c, 0, 0, GridFinder.GRID_SIZE);
//		img.draw();
	}

}
