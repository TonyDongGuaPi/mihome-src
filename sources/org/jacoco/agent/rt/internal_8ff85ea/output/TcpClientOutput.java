package org.jacoco.agent.rt.internal_8ff85ea.output;

import java.io.IOException;
import java.net.Socket;
import org.jacoco.agent.rt.internal_8ff85ea.IExceptionLogger;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RuntimeData;

public class TcpClientOutput implements IAgentOutput {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final IExceptionLogger f3636a;
    /* access modifiers changed from: private */
    public TcpConnection b;
    private Thread c;

    public TcpClientOutput(IExceptionLogger iExceptionLogger) {
        this.f3636a = iExceptionLogger;
    }

    public void a(AgentOptions agentOptions, RuntimeData runtimeData) throws IOException {
        this.b = new TcpConnection(a(agentOptions), runtimeData);
        this.b.a();
        this.c = new Thread(new Runnable() {
            public void run() {
                try {
                    TcpClientOutput.this.b.b();
                } catch (IOException e) {
                    TcpClientOutput.this.f3636a.a(e);
                }
            }
        });
        this.c.setName(getClass().getName());
        this.c.setDaemon(true);
        this.c.start();
    }

    public void a() throws Exception {
        this.b.c();
        this.c.join();
    }

    public void a(boolean z) throws IOException {
        this.b.a(z);
    }

    /* access modifiers changed from: protected */
    public Socket a(AgentOptions agentOptions) throws IOException {
        return new Socket(agentOptions.k(), agentOptions.j());
    }
}
