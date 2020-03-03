package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import java.util.Random;

public abstract class AbstractRuntime implements IRuntime {
    private static final Random b = new Random();

    /* renamed from: a  reason: collision with root package name */
    protected RuntimeData f3629a;

    public void a(RuntimeData runtimeData) throws Exception {
        this.f3629a = runtimeData;
    }

    public static String a() {
        return Integer.toHexString(b.nextInt());
    }
}
