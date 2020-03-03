package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.Java9Support;

public class ModifiedSystemClassRuntime extends AbstractRuntime {
    private static final String b = "Ljava/lang/Object;";
    private final Class<?> c;
    private final String d;
    private final String e;

    public void b() {
    }

    public ModifiedSystemClassRuntime(Class<?> cls, String str) {
        this.c = cls;
        this.d = cls.getName().replace('.', IOUtils.f15883a);
        this.e = str;
    }

    public void a(RuntimeData runtimeData) throws Exception {
        super.a(runtimeData);
        this.c.getField(this.e).set((Object) null, runtimeData);
    }

    public int a(long j, String str, int i, MethodVisitor methodVisitor) {
        methodVisitor.a(178, this.d, this.e, b);
        RuntimeData.b(j, str, i, methodVisitor);
        return 6;
    }

    public static IRuntime a(Instrumentation instrumentation, String str) throws ClassNotFoundException {
        return a(instrumentation, str, "$jacocoAccess");
    }

    public static IRuntime a(Instrumentation instrumentation, final String str, final String str2) throws ClassNotFoundException {
        AnonymousClass1 r0 = new ClassFileTransformer() {
            public byte[] a(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) throws IllegalClassFormatException {
                if (str.equals(str)) {
                    return ModifiedSystemClassRuntime.a(bArr, str2);
                }
                return null;
            }
        };
        instrumentation.addTransformer(r0);
        Class<?> cls = Class.forName(str.replace(IOUtils.f15883a, '.'));
        instrumentation.removeTransformer(r0);
        try {
            cls.getField(str2);
            return new ModifiedSystemClassRuntime(cls, str2);
        } catch (NoSuchFieldException e2) {
            throw new RuntimeException(String.format("Class %s could not be instrumented.", new Object[]{str}), e2);
        }
    }

    public static byte[] a(byte[] bArr, final String str) {
        ClassReader classReader = new ClassReader(Java9Support.b(bArr));
        ClassWriter classWriter = new ClassWriter(classReader, 0);
        classReader.a((ClassVisitor) new ClassVisitor(327680, classWriter) {
            public void a() {
                ModifiedSystemClassRuntime.b(this.b, str);
                super.a();
            }
        }, 8);
        return classWriter.b();
    }

    /* access modifiers changed from: private */
    public static void b(ClassVisitor classVisitor, String str) {
        classVisitor.a(4233, str, b, (String) null, (Object) null);
    }
}
