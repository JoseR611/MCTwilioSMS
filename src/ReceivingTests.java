import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import java.util.ArrayList;

public class ReceivingTests {

	public static void main(String[] args) {
	
	DateTime utc = new DateTime(DateTimeZone.UTC);
	DateTime local = new DateTime();
	
	String utcS = SmsReceiver.convertDate(utc);
	String localS = SmsReceiver.convertDate(local);
	
	System.out.print(utcS.equals(localS));
	TwilioController con = new TwilioController("AC4b5aa89ca455fcb98a84e8f27ebfada1", "db52572b7b6c77723ba78cdf5161e830", "+12019926017");
	boolean printed = false;
	while(!printed) {
		ArrayList<ArrayList<String>> tester = con.getMessages();
		if(!tester.isEmpty()) {
			System.out.println("\n" + tester.get(0).get(0) + tester.get(0).get(1) + tester.get(0).get(2));
			printed = true;
		}
	}
	}
}
