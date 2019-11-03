import com.google.common.collect.Range;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.joda.time.DateTime;
import com.twilio.base.ResourceSet;

import static spark.Spark.*;

import java.util.ArrayList;

/**
 * A Class receiving and parsing incoming text messages
 * @author Aaron
 *
 */
public class SmsReceiver {
	
	/**
	 * Account SID token, provided by TwilioController
	 */
	private static String ACCOUNT_SID;
	/**
	 * Account Authorization token, provided by TwilioController
	 */
	private static String AUTH_TOKEN;
	/**
	 * The time of the oldest message desired
	 */
	private DateTime lastMessage;
	
	/**
	 * Creates a new SmsReceiver object, initializes lastMessage to the current time
	 * @param account - Account SID token
	 * @param auth - Account Authorization token
	 */
	public SmsReceiver(String account, String auth) {
		lastMessage = new DateTime(org.joda.time.DateTimeZone.UTC);
		ACCOUNT_SID = account;
		AUTH_TOKEN = auth;
	}
	
	private static String convertDate(DateTime utcDate) {
		String ampm = "AM";
		DateTime date = utcDate.withZone(org.joda.time.DateTimeZone.getDefault());
		String uncon = date.toString();
		uncon = uncon.substring(11, 16);
		int hour = Integer.parseInt(uncon.substring(0, 2));
		if(hour > 11) {
			ampm = "PM";
		}
		if(hour == 0 || hour > 12) {
			hour -= 12;
		}
		if(hour < 0) {
			hour = hour*-1;
		}
		String retval = Integer.toString(hour) + uncon.substring(2) + ampm;
		return retval;
	}
	
	/**
	 * Gets all messages from lastMessage to the present and returns them as a String
	 * @return - String where each line is a separate text message
	 */
	public ArrayList<ArrayList<String>> getMessages() {
		ArrayList<ArrayList<String>> retval = new ArrayList<ArrayList<String>>(10);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		ResourceSet<Message> messages = Message.reader()
		.setDateSent(Range.greaterThan(lastMessage))
		.limit(10)
		.read();
		lastMessage = new DateTime(org.joda.time.DateTimeZone.UTC);
		
		for(Message record : messages) {
			if(record.getStatus() == Message.Status.RECEIVED) {
				ArrayList<String> entry = new ArrayList<String>(3);
				entry.add(convertDate(record.getDateSent()));
				entry.add(1, record.getFrom().toString());
				entry.add(2, record.getBody());
				retval.add(entry);
			}
		}
		return retval;
	}
}
