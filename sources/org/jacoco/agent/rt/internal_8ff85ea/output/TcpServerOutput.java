package org.jacoco.agent.rt.internal_8ff85ea.output;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import org.jacoco.agent.rt.internal_8ff85ea.IExceptionLogger;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RuntimeData;

public class TcpServerOutput implements IAgentOutput {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public TcpConnection f3639a;
    /* access modifiers changed from: private */
    public final IExceptionLogger b;
    /* access modifiers changed from: private */
    public ServerSocket c;
    private Thread d;

    public TcpServerOutput(IExceptionLogger iExceptionLogger) {
        this.b = iExceptionLogger;
    }

    public void a(AgentOptions agentOptions, final RuntimeData runtimeData) throws IOException {
        this.c = a(agentOptions);
        this.d = new Thread(new Runnable() {
            public void run() {
                while (!TcpServerOutput.this.c.isClosed()) {
                    try {
                        synchronized (TcpServerOutput.this.c) {
                            TcpConnection unused = TcpServerOutput.this.f3639a = new TcpConnection(TcpServerOutput.this.c.accept(), runtimeData);
                        }
                        TcpServerOutput.this.f3639a.a();
                        TcpServerOutput.this.f3639a.b();
                    } catch (IOException e) {
                        if (!TcpServerOutput.this.c.isClosed()) {
                            TcpServerOutput.this.b.a(e);
                        }
                    }
                }
            }
        });
        this.d.setName(getClass().getName());
        this.d.setDaemon(true);
        this.d.start();
    }

    public void a() throws Exception {
        this.c.close();
        synchronized (this.c) {
            if (this.f3639a != null) {
                this.f3639a.c();
            }
        }
        this.d.join();
    }

    public void a(boolean z) throws IOException {
        if (this.f3639a != null) {
            this.f3639a.a(z);
        }
    }

    /* access modifiers changed from: protected */
    public ServerSocket a(AgentOptions agentOptions) throws IOException {
        return new ServerSocket(agentOptions.j(), 1, a(agentOptions.k()));
    }

    /* access modifiers changed from: protected */
    public InetAddress a(String str) throws UnknownHostException {
        if ("*".equals(str)) {
            return null;
        }
        return InetAddress.getByName(str);
    }
}
