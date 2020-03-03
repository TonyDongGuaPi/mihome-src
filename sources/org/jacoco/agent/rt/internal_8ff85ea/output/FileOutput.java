package org.jacoco.agent.rt.internal_8ff85ea.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ExecutionDataWriter;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.IExecutionDataVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ISessionInfoVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RuntimeData;

public class FileOutput implements IAgentOutput {

    /* renamed from: a  reason: collision with root package name */
    private RuntimeData f3635a;
    private File b;
    private boolean c;

    public void a() throws IOException {
    }

    public final void a(AgentOptions agentOptions, RuntimeData runtimeData) throws IOException {
        this.f3635a = runtimeData;
        this.b = new File(agentOptions.a()).getAbsoluteFile();
        this.c = agentOptions.b();
        File parentFile = this.b.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        b().close();
    }

    public void a(boolean z) throws IOException {
        OutputStream b2 = b();
        try {
            ExecutionDataWriter executionDataWriter = new ExecutionDataWriter(b2);
            this.f3635a.a((IExecutionDataVisitor) executionDataWriter, (ISessionInfoVisitor) executionDataWriter, z);
        } finally {
            b2.close();
        }
    }

    private OutputStream b() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(this.b, this.c);
        fileOutputStream.getChannel().lock();
        return fileOutputStream;
    }
}
