import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

public class MCParser {
	
	//main function to read log of server, parse messages, and send them to the desired phone number
	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
		
		MCLogReader reader = new MCLogReader();
		reader.parseLog("[22:34:56]");
//		File path = new File(new URI("file:///Users/jr607/Desktop/1.14.4%20Server/logs/latest.log"));
//		reader.stream = new BufferedInputStream(new FileInputStream(path));
//		reader.start();
		
	}
}
