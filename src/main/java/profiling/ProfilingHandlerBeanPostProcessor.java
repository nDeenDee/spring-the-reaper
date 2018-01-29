package profiling;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    //При создании прокси, имя оригинального класса будет уже потеряно
    //На этапе Before откладываем сюда бины с которыми надо будет работать в After
    private Map<String, Class> map = new HashMap<>();
    private ProfilingController profilingController;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Profiling.class)) {
            map.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (profilingController.isEnabled()) {
                        System.out.println("Профилирую...");
                        Long beforeTime = System.nanoTime();
                        Object retVal = method.invoke(bean, args);
                        Long afterTime = System.nanoTime();
                        System.out.println(afterTime - beforeTime + " нс");
                        System.out.println("Всё");
                        return retVal;
                    } else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
