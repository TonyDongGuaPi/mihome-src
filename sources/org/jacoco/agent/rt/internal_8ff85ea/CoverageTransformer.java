package org.jacoco.agent.rt.internal_8ff85ea;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import org.jacoco.agent.rt.internal_8ff85ea.core.instr.Instrumenter;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IRuntime;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.WildcardMatcher;

public class CoverageTransformer implements ClassFileTransformer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3580a;
    private final Instrumenter b;
    private final IExceptionLogger c;
    private final WildcardMatcher d;
    private final WildcardMatcher e;
    private final WildcardMatcher f;
    private final ClassFileDumper g;
    private final boolean h;
    private final boolean i;

    static {
        String name = CoverageTransformer.class.getName();
        f3580a = a(name.substring(0, name.lastIndexOf(46)));
    }

    public CoverageTransformer(IRuntime iRuntime, AgentOptions agentOptions, IExceptionLogger iExceptionLogger) {
        this.b = new Instrumenter(iRuntime);
        this.c = iExceptionLogger;
        this.d = new WildcardMatcher(a(agentOptions.c()));
        this.e = new WildcardMatcher(a(agentOptions.d()));
        this.f = new WildcardMatcher(agentOptions.e());
        this.g = new ClassFileDumper(agentOptions.m());
        this.h = agentOptions.f();
        this.i = agentOptions.g();
    }

    public byte[] a(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) throws IllegalClassFormatException {
        if (cls != null || !a(classLoader, str, protectionDomain)) {
            return null;
        }
        try {
            this.g.a(str, bArr);
            return this.b.a(bArr, str);
        } catch (Exception e2) {
            IllegalClassFormatException illegalClassFormatException = new IllegalClassFormatException(e2.getMessage());
            illegalClassFormatException.initCause(e2);
            this.c.a(illegalClassFormatException);
            throw illegalClassFormatException;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(ClassLoader classLoader, String str, ProtectionDomain protectionDomain) {
        if (classLoader == null) {
            if (!this.h) {
                return false;
            }
        } else if ((!this.i && !a(protectionDomain)) || this.f.a(classLoader.getClass().getName())) {
            return false;
        }
        if (str.startsWith(f3580a) || !this.d.a(str) || this.e.a(str)) {
            return false;
        }
        return true;
    }

    private boolean a(ProtectionDomain protectionDomain) {
        CodeSource codeSource;
        if (protectionDomain == null || (codeSource = protectionDomain.getCodeSource()) == null || codeSource.getLocation() == null) {
            return false;
        }
        return true;
    }

    private static String a(String str) {
        return str.replace('.', IOUtils.f15883a);
    }
}
