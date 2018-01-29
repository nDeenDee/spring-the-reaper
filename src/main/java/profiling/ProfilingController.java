package profiling;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Getter
@Setter
public class ProfilingController implements ProfilingControllerMBean {

    @SneakyThrows
    void init() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(this, new ObjectName("profiling", "name", "controller"));
    }

    private boolean enabled = true;
}
