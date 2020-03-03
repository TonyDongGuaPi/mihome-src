package org.jacoco.agent.rt.internal_8ff85ea.core.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.data.CompactDataOutput;

public class ExecutionDataWriter implements IExecutionDataVisitor, ISessionInfoVisitor {

    /* renamed from: a  reason: collision with root package name */
    public static final char f3614a = 'ဇ';
    public static final char b = '샀';
    public static final byte c = 1;
    public static final byte d = 16;
    public static final byte e = 17;
    protected final CompactDataOutput f;

    public ExecutionDataWriter(OutputStream outputStream) throws IOException {
        this.f = new CompactDataOutput(outputStream);
        c();
    }

    private void c() throws IOException {
        this.f.writeByte(1);
        this.f.writeChar(49344);
        this.f.writeChar(f3614a);
    }

    public void a() throws IOException {
        this.f.flush();
    }

    public void a(SessionInfo sessionInfo) {
        try {
            this.f.writeByte(16);
            this.f.writeUTF(sessionInfo.a());
            this.f.writeLong(sessionInfo.b());
            this.f.writeLong(sessionInfo.c());
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void c(ExecutionData executionData) {
        if (executionData.e()) {
            try {
                this.f.writeByte(17);
                this.f.writeLong(executionData.a());
                this.f.writeUTF(executionData.b());
                this.f.a(executionData.c());
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public static final byte[] b() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ExecutionDataWriter(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
