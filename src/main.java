import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		TwilioController test = new TwilioController("AC4b5aa89ca455fcb98a84e8f27ebfada1", "db52572b7b6c77723ba78cdf5161e830", "+12019926017");
		//test.sendText("+19294611710", "test for new number");
		ArrayList<ArrayList<String>> testArr = test.getMessages();
		String testString = "";
		for(int i = 0; i < testArr.size(); i++) {
			testString = testString + testArr.get(i).get(0) + " " + testArr.get(i).get(1) + " " + testArr.get(i).get(2) + "\n";
		}
		System.out.println(testString);
	}
}
