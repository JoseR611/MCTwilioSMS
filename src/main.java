
public class main {

	public static void main(String[] args) {
		SmsReceiver test = new SmsReceiver();
		String testString = test.getMessages();
		System.out.println(testString);
	}
}
