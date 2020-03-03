package org.jacoco.agent.rt.internal_8ff85ea.core.data;

import java.io.IOException;

public class IncompatibleExecDataVersionException extends IOException {
    private static final long serialVersionUID = 1;
    private final int actualVersion;

    public IncompatibleExecDataVersionException(int i) {
        super(String.format("Cannot read execution data version 0x%x. This version of JaCoCo uses execution data version 0x%x.", new Object[]{Integer.valueOf(i), Integer.valueOf(ExecutionDataWriter.f3614a)}));
        this.actualVersion = i;
    }

    public int getExpectedVersion() {
        return ExecutionDataWriter.f3614a;
    }

    public int getActualVersion() {
        return this.actualVersion;
    }
}
