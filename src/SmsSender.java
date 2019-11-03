// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC4b5aa89ca455fcb98a84e8f27ebfada1";
    public static final String AUTH_TOKEN =
            "db52572b7b6c77723ba78cdf5161e830";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+17162922641"), // to
                        new PhoneNumber("+12019926017"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());
    }
}