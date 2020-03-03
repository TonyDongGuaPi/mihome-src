package org.jacoco.agent.rt.internal_8ff85ea.core.data;

import java.util.Arrays;

public final class ExecutionData {

    /* renamed from: a  reason: collision with root package name */
    private final long f3611a;
    private final String b;
    private final boolean[] c;

    public ExecutionData(long j, String str, boolean[] zArr) {
        this.f3611a = j;
        this.b = str;
        this.c = zArr;
    }

    public ExecutionData(long j, String str, int i) {
        this.f3611a = j;
        this.b = str;
        this.c = new boolean[i];
    }

    public long a() {
        return this.f3611a;
    }

    public String b() {
        return this.b;
    }

    public boolean[] c() {
        return this.c;
    }

    public void d() {
        Arrays.fill(this.c, false);
    }

    public boolean e() {
        for (boolean z : this.c) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public void a(ExecutionData executionData) {
        a(executionData, true);
    }

    public void a(ExecutionData executionData, boolean z) {
        a(executionData.a(), executionData.b(), executionData.c().length);
        boolean[] c2 = executionData.c();
        for (int i = 0; i < this.c.length; i++) {
            if (c2[i]) {
                this.c[i] = z;
            }
        }
    }

    public void a(long j, String str, int i) throws IllegalStateException {
        if (this.f3611a != j) {
            throw new IllegalStateException(String.format("Different ids (%016x and %016x).", new Object[]{Long.valueOf(this.f3611a), Long.valueOf(j)}));
        } else if (!this.b.equals(str)) {
            throw new IllegalStateException(String.format("Different class names %s and %s for id %016x.", new Object[]{this.b, str, Long.valueOf(j)}));
        } else if (this.c.length != i) {
            throw new IllegalStateException(String.format("Incompatible execution data for class %s with id %016x.", new Object[]{str, Long.valueOf(j)}));
        }
    }

    public String toString() {
        return String.format("ExecutionData[name=%s, id=%016x]", new Object[]{this.b, Long.valueOf(this.f3611a)});
    }
}
