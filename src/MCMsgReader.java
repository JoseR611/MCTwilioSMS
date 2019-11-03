import java.util.ArrayList;

public class MCMsgReader {
	
	public ArrayList<String> parseMessage(ArrayList<ArrayList<String>> input) {
		ArrayList<String> display = new ArrayList<>();
		String willDisplay = "";
		for(int i = input.size() - 1; i > -1 ; i--) {
			willDisplay = "At " + input.get(i).get(0);//time
			willDisplay = willDisplay + " " + input.get(i).get(1);//sender
			willDisplay = willDisplay + " messaged: " + input.get(i).get(2);//message
			display.add(willDisplay);
		}
		return display;
		
	}
}
