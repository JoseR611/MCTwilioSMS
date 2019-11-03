import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

public class MCParser {
	
	//main function to read log of server, parse messages, and send them to the desired phone number
	public static void main(String[] args) throws URISyntaxException, ParseException, IOException {	
		Runtime rt = Runtime.getRuntime();
		String[] start = new String[7];
		start[0] = "cmd.exe";
		start[1] = "/k";
		start[2] = "start";
		start[3] = "cmd.exe";
		start[4] = "/K";
		start[5] = "\"cd";
		start[6] = "src";
		Process p = rt.exec("cmd /c start cmd.exe /K \"cd \"src");
		String test = "dir";
		
		p.getOutputStream().write(test.getBytes());
		//rt.exec("cmd.exe \"dir");
		//rt.exec("cd src");
//		MCLogReader reader = new MCLogReader();
//		reader.stream = new BufferedInputStream(new FileInputStream("src/1.14.4Server/logs/latest.log"));
//		reader.start();
		
	}
}