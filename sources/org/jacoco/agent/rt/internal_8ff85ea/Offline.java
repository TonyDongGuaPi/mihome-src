package org.jacoco.agent.rt.internal_8ff85ea;

import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.RuntimeData;

public final class Offline {

    /* renamed from: a  reason: collision with root package name */
    private static final RuntimeData f3583a = Agent.a(new AgentOptions(ConfigLoader.a(b, System.getProperties()))).e();
    private static final String b = "/jacoco-agent.properties";

    private Offline() {
    }

    public static boolean[] a(long j, String str, int i) {
        return f3583a.a(Long.valueOf(j), str, i).c();
    }
}
