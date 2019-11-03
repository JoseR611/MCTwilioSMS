import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MCLogReader extends Thread{
	
	boolean isRunning = true;
	BufferedInputStream stream = null;
	
	//Reads the log file 
	public void run() {
		while(isRunning) {
			String line = "";
			try {
				if(stream.available() > 0) {
					while(stream.available() > 0) {
						line = line + (char) stream.read();
					}
					parseLog(line);
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
	
	public void parseLog(String line) {
		ArrayList<String> TimeNameMessage = new ArrayList<>();
		TimeNameMessage.add(line.substring(1, 9));
		System.out.println(TimeNameMessage.get(0));
	}
}
