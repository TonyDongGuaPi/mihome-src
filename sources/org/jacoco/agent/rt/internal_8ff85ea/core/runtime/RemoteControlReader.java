package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import java.io.IOException;
import java.io.InputStream;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ExecutionDataReader;

public class RemoteControlReader extends ExecutionDataReader {
    private IRemoteCommandVisitor b;

    public RemoteControlReader(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    /* access modifiers changed from: protected */
    public boolean a(byte b2) throws IOException {
        if (b2 == 32) {
            return false;
        }
        if (b2 != 64) {
            return super.a(b2);
        }
        b();
        return true;
    }

    public void a(IRemoteCommandVisitor iRemoteCommandVisitor) {
        this.b = iRemoteCommandVisitor;
    }

    private void b() throws IOException {
        if (this.b != null) {
            this.b.a(this.f3612a.readBoolean(), this.f3612a.readBoolean());
            return;
        }
        throw new IOException("No remote command visitor.");
    }
}
