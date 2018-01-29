package quoters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerminatorQuoter implements Quoter {

    private String message;

    public void sayQuote() {
        System.out.println("message = " + message);
    }
}
