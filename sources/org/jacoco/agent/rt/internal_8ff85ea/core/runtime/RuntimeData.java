package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ExecutionData;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ExecutionDataStore;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.IExecutionDataVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.ISessionInfoVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.data.SessionInfo;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr.InstrSupport;

public class RuntimeData {

    /* renamed from: a  reason: collision with root package name */
    protected final ExecutionDataStore f3633a = new ExecutionDataStore();
    private long b = System.currentTimeMillis();
    private String c = "<none>";

    public void a(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public final void a(IExecutionDataVisitor iExecutionDataVisitor, ISessionInfoVisitor iSessionInfoVisitor, boolean z) {
        synchronized (this.f3633a) {
            iSessionInfoVisitor.a(new SessionInfo(this.c, this.b, System.currentTimeMillis()));
            this.f3633a.a(iExecutionDataVisitor);
            if (z) {
                b();
            }
        }
    }

    public final void b() {
        synchronized (this.f3633a) {
            this.f3633a.a();
            this.b = System.currentTimeMillis();
        }
    }

    public ExecutionData a(Long l, String str, int i) {
        ExecutionData a2;
        synchronized (this.f3633a) {
            a2 = this.f3633a.a(l, str, i);
        }
        return a2;
    }

    public void a(Object[] objArr) {
        objArr[0] = a(objArr[0], objArr[1], objArr[2].intValue()).c();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Object[]) {
            a((Object[]) obj);
        }
        return super.equals(obj);
    }

    public static void a(long j, String str, int i, MethodVisitor methodVisitor) {
        methodVisitor.e_(6);
        methodVisitor.a(189, "java/lang/Object");
        methodVisitor.e_(89);
        methodVisitor.e_(3);
        methodVisitor.a((Object) Long.valueOf(j));
        methodVisitor.a(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
        methodVisitor.e_(83);
        methodVisitor.e_(89);
        methodVisitor.e_(4);
        methodVisitor.a((Object) str);
        methodVisitor.e_(83);
        methodVisitor.e_(89);
        methodVisitor.e_(5);
        InstrSupport.a(methodVisitor, i);
        methodVisitor.a(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        methodVisitor.e_(83);
    }

    public static void b(long j, String str, int i, MethodVisitor methodVisitor) {
        a(j, str, i, methodVisitor);
        methodVisitor.e_(90);
        methodVisitor.a(182, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
        methodVisitor.e_(87);
        methodVisitor.e_(3);
        methodVisitor.e_(50);
        methodVisitor.a(192, InstrSupport.e);
    }
}
