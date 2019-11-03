import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class MCParser {
	
	//main function to read log of server, parse messages, and send them to the desired phone number
	public static void main(String[] args) throws URISyntaxException, ParseException, IOException, InterruptedException {	
		String[] command = {"cmd",};
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
		    new Thread(new Sync(p.getErrorStream(), System.err)).start();
	        new Thread(new Sync(p.getInputStream(), System.out)).start();
	        PrintWriter stdin = new PrintWriter(p.getOutputStream());
	        stdin.println("hostname");
	        stdin.close();
	        p.waitFor();
	    } catch (Exception e) {
	 		e.printStackTrace();
		}
		
		
//		MCLogReader reader = new MCLogReader();
//		reader.stream = new BufferedInputStream(new FileInputStream("src/1.14.4Server/logs/latest.log"));
//		reader.start();
		
	}
}