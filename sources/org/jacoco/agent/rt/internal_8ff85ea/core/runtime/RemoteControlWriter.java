package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import java.io.IOException;
import java.io.OutputStream;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ExecutionDataWriter;

public class RemoteControlWriter extends ExecutionDataWriter implements IRemoteCommandVisitor {
    public static final byte g = 32;
    public static final byte h = 64;

    public RemoteControlWriter(OutputStream outputStream) throws IOException {
        super(outputStream);
    }

    public void c() throws IOException {
        this.f.writeByte(32);
    }

    public void a(boolean z, boolean z2) throws IOException {
        this.f.writeByte(64);
        this.f.writeBoolean(z);
        this.f.writeBoolean(z2);
    }
}
