import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import propertyFileContext.PropertyFileApplicationContext;
import quoters.Quoter;
import screensaver.ColorFrame;
import screensaver.Config;
import sun.awt.AppContext;

public class QuoterTest {

    private static final String CONTEXT_PATH = "context.xml";

    @BeforeClass
    public void init() {
//         context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
    }

    @Test(priority = 1)
    public void simpleInjectTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        Quoter terminatorQuoter = (Quoter) context.getBean("terminatorQuoter");
        terminatorQuoter.sayQuote();
    }

    @Test(priority = 2)
    public void injectRandomIntTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        Quoter terminatorQuoter = (Quoter) context.getBean("injectRandomIntTerminatorQuoter");
        terminatorQuoter.sayQuote();
    }

    @Test(priority = 3)
    @SneakyThrows
    public void profilingTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        Quoter terminator = (Quoter) context.getBean("profilingTerminatorQuoter");
        terminator.sayQuote();
        while (true) {
            terminator.sayQuote();
            Thread.sleep(1000L);
        }
    }

    @Test
    public void postProxyTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        Quoter terminator = (Quoter) context.getBean("postProxyTerminatorQuoter");
    }

    @Test
    public void deprecatedClassTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        Quoter terminator = (Quoter) context.getBean("deprecatedClassTerminatorQuoter");
        System.out.println("-----deprecated-----");
        terminator.sayQuote();
    }

    @Test
    public void propertyFileContextTest() {
        ApplicationContext context = new PropertyFileApplicationContext("context.properties");
        ((Quoter) context.getBean("quoter")).sayQuote();
    }

    @Test
    @SneakyThrows
    public void screenSaverTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(500);
        }
    }
}
