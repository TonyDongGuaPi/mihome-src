package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.data.CRC64;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.ClassProbesAdapter;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IExecutionDataAccessorGenerator;

public final class ProbeArrayStrategyFactory {
    private ProbeArrayStrategyFactory() {
    }

    public static IProbeArrayStrategy a(ClassReader classReader, IExecutionDataAccessorGenerator iExecutionDataAccessorGenerator) {
        String b = classReader.b();
        int b2 = b(classReader);
        long a2 = CRC64.a(classReader.k);
        boolean z = b2 >= 50;
        if (!a(classReader)) {
            return new ClassFieldProbeArrayStrategy(b, a2, z, iExecutionDataAccessorGenerator);
        }
        ProbeCounter c = c(classReader);
        if (c.b() == 0) {
            return new NoneProbeArrayStrategy();
        }
        if (b2 < 52 || !c.c()) {
            return new LocalProbeArrayStrategy(b, a2, c.b(), iExecutionDataAccessorGenerator);
        }
        return new InterfaceFieldProbeArrayStrategy(b, a2, c.b(), iExecutionDataAccessorGenerator);
    }

    private static boolean a(ClassReader classReader) {
        return (classReader.a() & 512) != 0;
    }

    private static int b(ClassReader classReader) {
        return classReader.d(6);
    }

    private static ProbeCounter c(ClassReader classReader) {
        ProbeCounter probeCounter = new ProbeCounter();
        classReader.a((ClassVisitor) new ClassProbesAdapter(probeCounter, false), 0);
        return probeCounter;
    }
}
