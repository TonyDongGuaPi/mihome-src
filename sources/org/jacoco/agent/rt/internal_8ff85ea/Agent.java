package org.jacoco.agent.rt.internal_8ff85ea;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import org.jacoco.agent.rt.IAgent;
import org.jacoco.agent.rt.internal_8ff85ea.core.JaCoCo;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ExecutionDataWriter;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.IExecutionDataVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ISessionInfoVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AbstractRuntime;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RuntimeData;
import org.jacoco.agent.rt.internal_8ff85ea.output.FileOutput;
import org.jacoco.agent.rt.internal_8ff85ea.output.IAgentOutput;
import org.jacoco.agent.rt.internal_8ff85ea.output.NoneOutput;
import org.jacoco.agent.rt.internal_8ff85ea.output.TcpClientOutput;
import org.jacoco.agent.rt.internal_8ff85ea.output.TcpServerOutput;

public class Agent implements IAgent {

    /* renamed from: a  reason: collision with root package name */
    private static Agent f3575a;
    private final AgentOptions b;
    private final IExceptionLogger c;
    private final RuntimeData d = new RuntimeData();
    private IAgentOutput e;
    private Callable<Void> f;

    public static synchronized Agent a(AgentOptions agentOptions) {
        Agent agent;
        synchronized (Agent.class) {
            if (f3575a == null) {
                Agent agent2 = new Agent(agentOptions, IExceptionLogger.f3581a);
                agent2.f();
                Runtime.getRuntime().addShutdownHook(new Thread(agent2) {

                    /* renamed from: a  reason: collision with root package name */
                    final /* synthetic */ Agent f3576a;

                    {
                        this.f3576a = r1;
                    }

                    public void run() {
                        this.f3576a.g();
                    }
                });
                f3575a = agent2;
            }
            agent = f3575a;
        }
        return agent;
    }

    public static synchronized Agent d() throws IllegalStateException {
        Agent agent;
        synchronized (Agent.class) {
            if (f3575a != null) {
                agent = f3575a;
            } else {
                throw new IllegalStateException("JaCoCo agent not started.");
            }
        }
        return agent;
    }

    Agent(AgentOptions agentOptions, IExceptionLogger iExceptionLogger) {
        this.b = agentOptions;
        this.c = iExceptionLogger;
    }

    public RuntimeData e() {
        return this.d;
    }

    public void f() {
        try {
            String h = this.b.h();
            if (h == null) {
                h = i();
            }
            this.d.a(h);
            this.e = h();
            this.e.a(this.b, this.d);
            if (this.b.n()) {
                this.f = new JmxRegistration(this);
            }
        } catch (Exception e2) {
            this.c.a(e2);
        }
    }

    public void g() {
        try {
            if (this.b.i()) {
                this.e.a(false);
            }
            this.e.a();
            if (this.f != null) {
                this.f.call();
            }
        } catch (Exception e2) {
            this.c.a(e2);
        }
    }

    /* access modifiers changed from: package-private */
    public IAgentOutput h() {
        AgentOptions.OutputMode l = this.b.l();
        switch (l) {
            case file:
                return new FileOutput();
            case tcpserver:
                return new TcpServerOutput(this.c);
            case tcpclient:
                return new TcpClientOutput(this.c);
            case none:
                return new NoneOutput();
            default:
                throw new AssertionError(l);
        }
    }

    private String i() {
        String str;
        try {
            str = InetAddress.getLocalHost().getHostName();
        } catch (Exception unused) {
            str = "unknownhost";
        }
        return str + "-" + AbstractRuntime.a();
    }

    public String a() {
        return JaCoCo.f3610a;
    }

    public String b() {
        return this.d.a();
    }

    public void a(String str) {
        this.d.a(str);
    }

    public void c() {
        this.d.b();
    }

    public byte[] a(boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ExecutionDataWriter executionDataWriter = new ExecutionDataWriter(byteArrayOutputStream);
            this.d.a((IExecutionDataVisitor) executionDataWriter, (ISessionInfoVisitor) executionDataWriter, z);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public void b(boolean z) throws IOException {
        this.e.a(z);
    }
}
