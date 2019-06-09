package calculation;

import java.awt.Color;
import java.io.IOException;

import fileio.DataWriter;
import images.APImage;
import images.Pixel;
import visualization.ImageVisual;

/**
 * Performs most calculations.
 * 
 * Some terminology:
 * Square - a collection of pixels in a square shape, has location and size
 * Grid   - an arrangement that divides an image into squares + some left over border,
 * 		    defined precisely using just grid size
 * 
 * @author AlexanderWu
 */
public class ColorAverager {
	
	public static int lowerTemp;
	public static int upperTemp;
	
	public static Color averageInSquare(APImage img, int upLeftX, int upLeftY, int gridSize) {
		int yLimit = upLeftY + gridSize;
		int xLimit = upLeftX + gridSize;
		double[] sums = new double[3];
		
		for (int y = upLeftY; y < yLimit; y++) {
			for (int x = upLeftX; x < xLimit; x++) {
				Pixel p = img.getPixel(x, y);
				sums[0] += p.getRed();
				sums[1] += p.getGreen();
				sums[2] += p.getBlue();
			}
		}
		int numPixels = gridSize * gridSize;
		for (int i = 0; i < 3; i++)
			sums[i] = sums[i] / numPixels + 0.5;
		return new Color((int) sums[0], (int) sums[1], (int) sums[2]);
	}

	public static void averageAllSquaresAndFindTemperatures(APImage img, int gridSize, String filename) throws IOException {
		int trimmedWidth = img.getWidth() / gridSize * gridSize;
		int trimmedHeight = (img.getHeight() / gridSize - 3) * gridSize; // exclude temperature part
		int dateTimeSize = 4 * gridSize; // exclude date time part
		
		DataWriter dw = new DataWriter(filename);
		
		for (int y = 0; y < dateTimeSize; y += gridSize) {
			for (int x = dateTimeSize; x < trimmedWidth; x += gridSize) {
				Color c = fillAverage(img, x, y, gridSize);
				double temperature = ScaleSampler.getTemperature(img, c, lowerTemp, upperTemp);
				dw.write(temperature);
			}
			dw.delimiter();
		}

		for (int y = dateTimeSize; y < trimmedHeight; y += gridSize) {
			for (int x = 0; x < trimmedWidth; x += gridSize) {
				Color c = fillAverage(img, x, y, gridSize);
				double temperature = ScaleSampler.getTemperature(img, c, lowerTemp, upperTemp);
				dw.write(temperature);
			}
			dw.delimiter();
		}
		
		dw.close();
	}
	
	/** returns the average color */
	private static Color fillAverage(APImage img, int upLeftX, int upLeftY, int gridSize) {
		Color col = averageInSquare(img, upLeftX, upLeftY, gridSize);
		ImageVisual.fillSquare(img, col, upLeftX, upLeftY, gridSize);
		return col;
	}
	
	public static void main(String[] args) {
//		APImage img = new APImage("images/CurvedCorkNov7/42.jpg");
//		APImage img = new APImage("images/CurvedCorkNov7/00.jpg");
		APImage img = new APImage("images/CurvedCorkNov2/00 A1.jpg");
		
		try {
			averageAllSquaresAndFindTemperatures(img, GridFinder.GRID_SIZE, "experiment2.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		ImageVisual.fillSquare(img, avg, 0, 50, GridFinder.GRID_SIZE);
//		img.draw();
		img.saveAs();

	}

}
