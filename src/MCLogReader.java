import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MCLogReader extends Thread{
	
	boolean isRunning = true;//
	BufferedInputStream stream = null;
	
	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
		MCLogReader reader = new MCLogReader();
		File path = new File(new URI("file:///Users/jr607/Desktop/1.14.4%20Server/logs/latest.log"));
		reader.stream = new BufferedInputStream(new FileInputStream(path));
		reader.start();
	}
	
	public void run() {
		while(isRunning) {
			String line = "";
			try {
				if(stream.available() > 0) {
					while(stream.available() > 0) {
						line = line + (char) stream.read();
					}
					System.out.println(line);//print out line by line
				}else {
					try {
						sleep(500);
					} catch (InterruptedException ex) {
						isRunning = false;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
