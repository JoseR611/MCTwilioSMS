/**
 * A class used to combine all the classes dealing with sending and receiving SMS Messages via Twilio
 * @author Aaron
 *
 */
public class TwilioController {

	/**
	 * The Account SID, should be provided by the user securely.
	 */
	private static String ACCOUNT_SID;
	/**
	 * The Authority Token for the Twilio Account, provide securely.
	 */
	private static String AUTH_TOKEN;
	/**
	 * Structure which allows the user to send text messages to phone numbers.
	 */
	private SmsSender sender;
	/**
	 * Structure which gets received text messages for the user.
	 */
	private SmsReceiver receiver;
	
	/**
	 * Create a new TwilioController
	 * @param accountSid - Account SID token
	 * @param authToken - Account Authority Token
	 */
	public TwilioController(String accountSid, String authToken, String twilioNumber) {
		ACCOUNT_SID = accountSid;
		AUTH_TOKEN = authToken;
		sender = new SmsSender(ACCOUNT_SID, AUTH_TOKEN, twilioNumber);
		receiver = new SmsReceiver(ACCOUNT_SID, AUTH_TOKEN);
	}
	
	/**
	 * Send a text message to the provided number
	 * @param cell - Number Capable of receiving SMS Messages
	 * @param text - The text message
	 */
	public void sendText(String cell, String text) {
		try {
			sender.sendMessage(cell, text);
		} catch (com.twilio.exception.ApiException e) {
			System.out.println("The number must be validated when the account is a trial account.");
		}
	}
	
	/**
	 * Get all text messages since creation of this object or from the last time this function was called
	 * @return - the text messages, one message per line
	 */
	public String getMessages() {
		return receiver.getMessages();
	}
}
