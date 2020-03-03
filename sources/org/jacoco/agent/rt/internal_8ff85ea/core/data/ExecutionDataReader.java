package org.jacoco.agent.rt.internal_8ff85ea.core.data;

import java.io.IOException;
import java.io.InputStream;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.data.CompactDataInput;

public class ExecutionDataReader {

    /* renamed from: a  reason: collision with root package name */
    protected final CompactDataInput f3612a;
    private ISessionInfoVisitor b = null;
    private IExecutionDataVisitor c = null;
    private boolean d = true;

    public ExecutionDataReader(InputStream inputStream) {
        this.f3612a = new CompactDataInput(inputStream);
    }

    public void a(ISessionInfoVisitor iSessionInfoVisitor) {
        this.b = iSessionInfoVisitor;
    }

    public void a(IExecutionDataVisitor iExecutionDataVisitor) {
        this.c = iExecutionDataVisitor;
    }

    public boolean a() throws IOException, IncompatibleExecDataVersionException {
        byte b2;
        do {
            int read = this.f3612a.read();
            if (read == -1) {
                return false;
            }
            b2 = (byte) read;
            if (!this.d || b2 == 1) {
                this.d = false;
            } else {
                throw new IOException("Invalid execution data file.");
            }
        } while (a(b2));
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(byte b2) throws IOException {
        if (b2 != 1) {
            switch (b2) {
                case 16:
                    c();
                    return true;
                case 17:
                    d();
                    return true;
                default:
                    throw new IOException(String.format("Unknown block type %x.", new Object[]{Byte.valueOf(b2)}));
            }
        } else {
            b();
            return true;
        }
    }

    private void b() throws IOException {
        if (this.f3612a.readChar() == 49344) {
            char readChar = this.f3612a.readChar();
            if (readChar != ExecutionDataWriter.f3614a) {
                throw new IncompatibleExecDataVersionException(readChar);
            }
            return;
        }
        throw new IOException("Invalid execution data file.");
    }

    private void c() throws IOException {
        if (this.b != null) {
            this.b.a(new SessionInfo(this.f3612a.readUTF(), this.f3612a.readLong(), this.f3612a.readLong()));
            return;
        }
        throw new IOException("No session info visitor.");
    }

    private void d() throws IOException {
        if (this.c != null) {
            this.c.c(new ExecutionData(this.f3612a.readLong(), this.f3612a.readUTF(), this.f3612a.b()));
            return;
        }
        throw new IOException("No execution data visitor.");
    }
}
