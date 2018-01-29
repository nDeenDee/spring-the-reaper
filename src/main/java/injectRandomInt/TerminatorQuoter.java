package injectRandomInt;

import lombok.Getter;
import lombok.Setter;
import quoters.Quoter;


@Getter
@Setter
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public void sayQuote() {
        for (int i = 1; i <= repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
