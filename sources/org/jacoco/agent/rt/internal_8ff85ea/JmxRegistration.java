package org.jacoco.agent.rt.internal_8ff85ea;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import org.jacoco.agent.rt.IAgent;

class JmxRegistration implements Callable<Void> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3582a = "org.jacoco:type=Runtime";
    private final MBeanServer b = ManagementFactory.getPlatformMBeanServer();
    private final ObjectName c = new ObjectName(f3582a);

    JmxRegistration(IAgent iAgent) throws Exception {
        this.b.registerMBean(new StandardMBean(iAgent, IAgent.class), this.c);
    }

    /* renamed from: a */
    public Void call() throws Exception {
        this.b.unregisterMBean(this.c);
        return null;
    }
}
