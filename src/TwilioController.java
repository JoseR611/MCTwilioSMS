
public class TwilioController {

	private static String ACCOUNT_SID;
	private static String AUTH_TOKEN;
	private SmsSender sender;
	private SmsReceiver receiver;
	
	public TwilioController(String accountSid, String authToken) {
		ACCOUNT_SID = accountSid;
		AUTH_TOKEN = authToken;
		sender = new SmsSender(ACCOUNT_SID, AUTH_TOKEN);
		receiver = new SmsReceiver(ACCOUNT_SID, AUTH_TOKEN);
	}
}
