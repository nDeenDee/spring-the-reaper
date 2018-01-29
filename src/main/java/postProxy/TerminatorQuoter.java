package postProxy;

import injectRandomInt.InjectRandomInt;
import lombok.Getter;
import lombok.Setter;
import profiling.Profiling;
import quoters.Quoter;

import javax.annotation.PostConstruct;


@Getter
@Setter
@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println(this.getClass().getName() + " Фаза 1. Конструктор");
    }

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getName() + " Фаза 2. PostConstruct");
    }

    @PostProxy
    public void sayQuote() {
        System.out.println(this.getClass().getName() + " Фаза 3. Метод вызывается через ContextListener");
        for (int i = 1; i <= repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
