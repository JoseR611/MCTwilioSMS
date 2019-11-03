import java.util.ArrayList;

public class MCMsgReader {
	
	public void parseMessage(ArrayList<ArrayList<String>> input) {
		ArrayList<String> display = new ArrayList<>();
		String willDisplay = "";
		for(int i = input.size(); i > -1 ; i--) {
			willDisplay = "At " + input.get(i).get(0);
			willDisplay = willDisplay + " " + input.get(i).get(1);
			willDisplay = willDisplay + " messaged: " + input.get(i).get(2);
			display.add(willDisplay);
		}
		
	}
}
