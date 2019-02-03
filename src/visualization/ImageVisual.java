package visualization;

import java.awt.Color;

import images.APImage;
import images.Pixel;

/**
 * Includes visualization methods for boxes on images.
 * 
 * @author AlexanderWu
 */
@SuppressWarnings("restriction")
public class ImageVisual {
	
	public static void fillSquare(APImage img, Color col, int startX, int startY, int gridSize) {
		int yLimit = startY + gridSize;
		int xLimit = startX + gridSize;

		for (int y = startY; y < yLimit; y++) {
			for (int x = startX; x < xLimit; x++) {
				Pixel p = img.getPixel(x, y);
				p.setRed(col.getRed());
				p.setGreen(col.getGreen());
				p.setBlue(col.getBlue());
			}
		}
	}

	/**
	 * Adds a box to the image, and then displays it.
	 */
 	public static void showGrid(APImage img, int startX, int startY, int gridSize) {
		addGrid(img, startX, startY, gridSize);
		img.draw();
	}
		
	/**
	 * Adds a box to the image. The image can then be saved and viewed.
	 */
	public static void addGrid(APImage img, int startX, int startY, int gridSize) {
		
		Color col = Color.BLUE;
		for (int x = startX; x < img.getWidth(); x += gridSize)
			addVerticalLine(img, x, col);
		for (int y = startX; y < img.getHeight(); y += gridSize)
			addHorizontalLine(img, y, col);
		
	}
	
	public static void addVerticalLine(APImage img, int x, Color col) {

		for (int y = 0; y < img.getHeight(); y++) {
			Pixel p = img.getPixel(x, y);
			p.setRed(col.getRed());
			p.setGreen(col.getGreen());
			p.setBlue(col.getBlue());
		}
		
	}
	
	public static void addHorizontalLine(APImage img, int y, Color col) {

		for (int x = 0; x < img.getWidth(); x++) {
			Pixel p = img.getPixel(x, y);
			p.setRed(col.getRed());
			p.setGreen(col.getGreen());
			p.setBlue(col.getBlue());
		}
		
	}

	/**
	 * Prints RGB values of some pixels in the image.
	 */
	public static void printPixels(APImage img, int x1, int x2, int y1, int y2) {
		
		for (int y = y1; y < y2; y++) {
			for (int x = x1; x < x2; x++) {
				Pixel p = img.getPixel(x, y);
				System.out.print(p + " ");
			}
			System.out.println();
		}
		
	}
	
}
