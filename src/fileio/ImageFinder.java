package fileio;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import images.APImage;

/**
 * This is an iterator. Looks for images in the file system
 * and returns them one by one.
 * 
 * It saves its progress (an int) in a file called progress.txt
 * 
 * @author AlexanderWu
 */
public class ImageFinder implements Iterable<APImage>, Iterator<APImage> {
	
	public static final String IMAGE_TAIL = ".jpg";
	
	public static final int IMAGE_NUM = 24;
	
	/**
	 * Number of images to process at a time
	 */
	private static final int DEFAULT_STEP = 24;	// CHANGE THIS
	
	private int progress;
	private String fileHead;
	private String fileTail;
	/** exclusive */
	private int end;
	
	public ImageFinder(String filenameHead, String filenameTail, int end) throws Exception {
		progress = 0;
		fileHead = filenameHead;
		fileTail = filenameTail;
		this.end = end;
		System.out.println("Beginning from: " + progress);
	}

	/** Does the next {@code DEFAULT_STEP} images (because the program can run out of memory). */
	public ImageFinder(String filenameHead, String filenameTail) throws Exception {
		progress = 0;
		fileHead = filenameHead;
		fileTail = filenameTail;
		end = progress + DEFAULT_STEP;
		System.out.println("Beginning from: " + progress);
	}


	@Override
	public boolean hasNext() {
		return progress < end;
	}

	@Override
	public APImage next() {
		APImage img = new APImage(fileHead + String.format("%02d", progress) + fileTail);
		progress++;
		return img;
	}
	
	public int progress() {
		return progress;
	}
	
	public void close() throws IOException {
		System.out.println("Current progress: " + progress);
	}
	
	@Override
	public Iterator<APImage> iterator() {
		return this;
	}
	
 	/** tests */
	public static void main(String[] args) {
		try {
			
			ImageFinder imgf = new ImageFinder("images/CurvedCorkNov7/", IMAGE_TAIL, IMAGE_NUM);
			
			imgf.next().draw();
			
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			scan.close();
			
			imgf.next().draw();
			System.out.println("Finished!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
