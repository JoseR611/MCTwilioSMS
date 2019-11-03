import com.google.common.collect.Range;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.joda.time.DateTime;
import com.twilio.base.ResourceSet;
import static spark.Spark.*;

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
		lastMessage = new DateTime();
		ACCOUNT_SID = account;
		AUTH_TOKEN = auth;
	}
	
	/**
	 * Gets all messages from lastMessage to the present and returns them as a String
	 * @return - String where each line is a separate text message
	 */
	public String getMessages() {
		post("/sms", (req, res) -> "");
		String retval = "";
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		ResourceSet<Message> messages = Message.reader()
		.setDateSent(Range.lessThan(lastMessage))
		.limit(40)
		.read();
		
		for(Message record : messages) {
			//if(record.getStatus() == Message.Status.RECEIVED) {
				//retval = record.getBody() + "\n" + retval;
			retval = retval + record.getStatus() + "\n";
			//}
		}
		lastMessage = new DateTime();
		return retval;
	}
}
