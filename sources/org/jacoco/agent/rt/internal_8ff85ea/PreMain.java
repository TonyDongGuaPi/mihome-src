package org.jacoco.agent.rt.internal_8ff85ea;

import java.lang.instrument.Instrumentation;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IRuntime;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.ModifiedSystemClassRuntime;

public final class PreMain {
    private PreMain() {
    }

    public static void a(String str, Instrumentation instrumentation) throws Exception {
        AgentOptions agentOptions = new AgentOptions(str);
        Agent a2 = Agent.a(agentOptions);
        IRuntime a3 = a(instrumentation);
        a3.a(a2.e());
        instrumentation.addTransformer(new CoverageTransformer(a3, agentOptions, IExceptionLogger.f3581a));
    }

    private static IRuntime a(Instrumentation instrumentation) throws Exception {
        return ModifiedSystemClassRuntime.a(instrumentation, "java/util/UUID");
    }
}
