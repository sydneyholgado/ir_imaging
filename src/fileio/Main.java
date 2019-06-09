package fileio;

import calculation.ColorAverager;
import calculation.GridFinder;
import images.APImage;

public class Main {
	
	public static final String IMAGE_HEAD = "images/CurvedPLAOct23/";	// CHANGE THIS

	private static ImageFinder imgFinder() throws Exception {
		return new ImageFinder(IMAGE_HEAD, ImageFinder.IMAGE_TAIL);
	}
	
	private static String WRITE_FILE_HEAD = "CurvedPLAOct23/temperaturesCPOct23 ";	// CHANGE THIS

	private static String WRITE_FILE_TAIL = ".txt";

	private static void updateTempBounds(int counter) {
		// change the bounds on color/temperature scale
		switch (counter) {										// CHANGE THIS
		case 0:
			ColorAverager.lowerTemp = 25;
			ColorAverager.upperTemp = 26;
			break;
		case 18:
			ColorAverager.lowerTemp = 24;
			ColorAverager.upperTemp = 26;
//			break;
//		case 10:
//			ColorAverager.lowerTemp = 26;
//			ColorAverager.upperTemp = 27;
//			break;
//		case 12:
//			ColorAverager.lowerTemp = 25;
//			ColorAverager.upperTemp = 26;
//			break;
//		case 31:
//			ColorAverager.lowerTemp = 24;
//			ColorAverager.upperTemp = 25;
//			break;
//		case 34:
//			ColorAverager.lowerTemp = 25;
//			ColorAverager.upperTemp = 26;
//			break;
//		case 36:
//			ColorAverager.lowerTemp = 24;
//			ColorAverager.upperTemp = 25;
//			break;
//		case 42:
//			ColorAverager.lowerTemp = 22;
//			ColorAverager.upperTemp = 25;
//			break;
//		case 43:
//			ColorAverager.lowerTemp = 21;
//			ColorAverager.upperTemp = 23;
//			break;
//		case 44:
//			ColorAverager.lowerTemp = 20;
//			ColorAverager.upperTemp = 24;
//			break;
//		case 46:
//			ColorAverager.lowerTemp = 21;
//			break;
		}
	}
	
	public static void main(String[] args) {

		try {
			ImageFinder in = imgFinder();
			
			try {
				
				int counter = in.progress();
				
				for (APImage img : in) {
					updateTempBounds(counter);
					
					ColorAverager.averageAllSquaresAndFindTemperatures(img, GridFinder.GRID_SIZE,
							WRITE_FILE_HEAD + String.format("%02d", counter) + WRITE_FILE_TAIL);
					img.save();
					System.out.print(".");	// progress bar
					counter++;
				}

				System.out.println("Finished!");

			} catch (Exception e) {
				System.out.println("Interrupted at progress " + in.progress());
				e.printStackTrace();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}

}
