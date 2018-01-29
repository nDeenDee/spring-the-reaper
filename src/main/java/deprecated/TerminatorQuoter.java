package deprecated;

import injectRandomInt.InjectRandomInt;
import lombok.Getter;
import lombok.Setter;
import postProxy.PostProxy;
import profiling.Profiling;
import quoters.Quoter;

import javax.annotation.PostConstruct;


@Getter
@Setter
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println(this.getClass().getName() + " Фаза 1. Конструктор");
    }

    public void init() {
        System.out.println(this.getClass().getName() + " Фаза 2. PostConstruct");
    }

    public void sayQuote() {
        System.out.println(this.getClass().getName() + " Фаза 3. Метод вызывается через ContextListener");
        for (int i = 1; i <= repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
