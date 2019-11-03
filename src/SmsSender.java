// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * A class focusing on sending SMS messages to a phone number
 * @author Aaron
 *
 */
public class SmsSender {
	/**
	 * Account SID Token, provided by TwilioController
	 */
    public static String ACCOUNT_SID;
    /**
     * Account Authorization token, provided by TwilioController
     */
    public static String AUTH_TOKEN;
    
    /**
     * Create a new SmsSender object
     * @param account - Account SID token
     * @param auth - Account Authorization token
     */
    public SmsSender(String account, String auth) {
    	ACCOUNT_SID = account;
    	AUTH_TOKEN = auth;
    }
    
    /**
     * Send a text message to a particular phone number
     * @param cellNum - the phone number sending to
     * @param text - the text message being sent
     */
    public void sendMessage(String cellNum, String text) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	
    	Message message = Message
    			.creator(new PhoneNumber(cellNum), 
    					new PhoneNumber("+12019926017"), 
    					text)
    			.create();
    	
    	System.out.println(message.getSid());
    }
}