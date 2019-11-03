import com.google.common.collect.Range;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.joda.time.DateTime;
import com.twilio.base.ResourceSet;


public class SmsReceiver {
	
	private static String ACCOUNT_SID;
	private static String AUTH_TOKEN;
	private DateTime lastMessage;
	
	public SmsReceiver(String account, String auth) {
		lastMessage = new DateTime();
		ACCOUNT_SID = account;
		AUTH_TOKEN = auth;
	}
	
	public String getMessages() {
		String retval = "";
		//retval.concat(lastMessage.toString());
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		ResourceSet<Message> messages = Message.reader()
		.setDateSent(Range.lessThan(lastMessage))
		.limit(20)
		.read();
		
		for(Message record : messages) {
			if(record.getStatus() == Message.Status.RECEIVED) {
				retval = record.getBody() + "\n" + retval;
			}
		}
		lastMessage = new DateTime();
		//retval.concat(lastMessage.toString());
		return retval;
	}
}
