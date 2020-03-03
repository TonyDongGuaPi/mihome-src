package org.jacoco.agent.rt.internal_8ff85ea.output;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.IExecutionDataVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ISessionInfoVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IRemoteCommandVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RemoteControlReader;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RemoteControlWriter;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RuntimeData;

class TcpConnection implements IRemoteCommandVisitor {

    /* renamed from: a  reason: collision with root package name */
    private final RuntimeData f3638a;
    private final Socket b;
    private RemoteControlWriter c;
    private RemoteControlReader d;
    private boolean e = false;

    public TcpConnection(Socket socket, RuntimeData runtimeData) {
        this.b = socket;
        this.f3638a = runtimeData;
    }

    public void a() throws IOException {
        this.c = new RemoteControlWriter(this.b.getOutputStream());
        this.d = new RemoteControlReader(this.b.getInputStream());
        this.d.a((IRemoteCommandVisitor) this);
        this.e = true;
    }

    public void b() throws IOException {
        do {
            try {
            } catch (SocketException e2) {
                if (!this.b.isClosed()) {
                    throw e2;
                }
            } catch (Throwable th) {
                c();
                throw th;
            }
        } while (this.d.a());
        c();
    }

    public void a(boolean z) throws IOException {
        if (this.e && !this.b.isClosed()) {
            a(true, z);
        }
    }

    public void c() throws IOException {
        if (!this.b.isClosed()) {
            this.b.close();
        }
    }

    public void a(boolean z, boolean z2) throws IOException {
        if (z) {
            this.f3638a.a((IExecutionDataVisitor) this.c, (ISessionInfoVisitor) this.c, z2);
        } else if (z2) {
            this.f3638a.b();
        }
        this.c.c();
    }
}
