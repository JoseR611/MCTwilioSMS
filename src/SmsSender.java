// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static String ACCOUNT_SID;
    public static String AUTH_TOKEN;
    
    public SmsSender(String account, String auth) {
    	ACCOUNT_SID = account;
    	AUTH_TOKEN = auth;
    }
    
    public void sendMessage(String cellNum, String text) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	
    	Message message = Message
    			.creator(new PhoneNumber(cellNum), 
    					new PhoneNumber("+12019926017"), 
    					text)
    			.create();
    	
    	System.out.println(message.getSid());
    }

    /**
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+17162922641"), // to
                        new PhoneNumber("+12019926017"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());
    }
    **/
}