package calculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Calculates percentage change in temperature based on text files created by ColorAverager.
 *  
 * @author AlexanderWu
 */
public class ChangeCalculator {
	
	public final static String FILE_HEAD2 = "results/CurvedCorkNov2/temperaturesCCNov2 ";
	public final static String FILE_HEAD3 = "results/CurvedCorkNov3/temperaturesCCNov3 ";
	public final static String FILE_HEAD7 = "results/CurvedCorkNov7/temperaturesCCNov7 ";

	public final static String FILE_HEAD18 = "results/CurvedPLAOct18/temperaturesCPOct18 ";
	public final static String FILE_HEAD23 = "results/CurvedPLAOct23/temperaturesCPOct23 ";

	public final static double ERROR = 0.5;	// error where temperatures are considered equal
	public final static int NUM_SQUARES = 384;	// save this value
	
	public static double percentageChange(String file1, String file2, double error) throws IOException {
		BufferedReader in1 = new BufferedReader(new FileReader(file1));
		BufferedReader in2 = new BufferedReader(new FileReader(file2));
		
		String line1 = in1.readLine();
		String line2 = in2.readLine();
		int diffCount = 0;	// number of different temperatures
		while (line1 != null) {
			if (!line1.equals("\\")) {
				double diff = Math.abs(Double.parseDouble(line1) - Double.parseDouble(line2));
				if (diff >= error)
					diffCount++;
			}
			line1 = in1.readLine();
			line2 = in2.readLine();
		}

		in1.close();
		in2.close();
		return (diffCount + 0.0) / NUM_SQUARES;
	}
	
	public static void main(String[] args) {
		
		try {
			for (int i = 0; i < 24; i++) {
				String file1 = FILE_HEAD18 + String.format("%02d", i) +".txt";
				String file2 = FILE_HEAD23 + String.format("%02d", i) +".txt";
				System.out.println(percentageChange(file1, file2, ERROR));
				if (i % 6 == 5)
					System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
