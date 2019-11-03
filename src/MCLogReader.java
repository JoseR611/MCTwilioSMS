import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MCLogReader extends Thread{
	
	boolean isRunning = true;
	BufferedInputStream stream = null;
	
	//Reads the log file 
	public void run() {
		SmsReceiver smsr = new SmsReceiver("AC4b5aa89ca455fcb98a84e8f27ebfada1", "db52572b7b6c77723ba78cdf5161e830");
		MCMsgReader mcmr = new MCMsgReader();
		while(isRunning) {
			String line = "";
			try {
				if(stream.available() > 0) {//catches messages from game and send to the real world
					while(stream.available() > 0) {
						line = line + (char) stream.read();
					}
					if(line.charAt(33) == '*') {
						parseLog(line);
					}
					
					//catches messages from the real world and send them to Minecraft
					ArrayList<String> msg = mcmr.parseMessage(smsr.getMessages());
					//command to write things into commandline
					
					for(String tell : msg) {
						String command = "/say " + tell;
						//run command prompt
					}
					try {
						sleep(500);
					} catch (InterruptedException ex) {
						isRunning = false;
					}
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void parseLog(String line) throws ParseException {
		ArrayList<String> TimeNameDestinationMessage = new ArrayList<>();
		
		String time = line.substring(1, 6);
		SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
		Date _24HourDt = _24HourSDF.parse(time);
		TimeNameDestinationMessage.add(_12HourSDF.format(_24HourDt));//put hh:mm AM/PM into arraylist with 12 hour format
		
		String username = "";
		String checkIfText = "";
		String destination = "";
		String msg = "";
		int countBlankSpaces = 0;
		char[] charArray = line.toCharArray();
		for(char character : charArray) {
			if(countBlankSpaces == 4) {
				username+=character;
			}else if(countBlankSpaces == 5) {
				checkIfText+=character;
			}else if(countBlankSpaces == 6) {
				destination+=character;
			}else if(countBlankSpaces > 6) {
				msg+=character;
			}
			if(character == ' ') {
				countBlankSpaces++;
			}
		}
		destination = destination.trim();
		username = username.trim();
		if(checkIfText.equals("text ") && destination.matches("[0-9]+")) {
			TimeNameDestinationMessage.add(username);//put the username of the account into the arraylist
			TimeNameDestinationMessage.add(destination);//put the number of the recipient into the arraylist
			TimeNameDestinationMessage.add(msg);//put the message into the arraylist
		}else {
			return;
		}
		SmsSender smsSend = new SmsSender("AC4b5aa89ca455fcb98a84e8f27ebfada1", "db52572b7b6c77723ba78cdf5161e830", "+12019926017");
		smsSend.sendMessage(TimeNameDestinationMessage.get(2), TimeNameDestinationMessage.get(3));
		
	}
}
