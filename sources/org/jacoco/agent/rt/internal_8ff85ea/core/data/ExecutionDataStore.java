package org.jacoco.agent.rt.internal_8ff85ea.core.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ExecutionDataStore implements IExecutionDataVisitor {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Long, ExecutionData> f3613a = new HashMap();
    private final Set<String> b = new HashSet();

    public void a(ExecutionData executionData) throws IllegalStateException {
        Long valueOf = Long.valueOf(executionData.a());
        ExecutionData executionData2 = this.f3613a.get(valueOf);
        if (executionData2 == null) {
            this.f3613a.put(valueOf, executionData);
            this.b.add(executionData.b());
            return;
        }
        executionData2.a(executionData);
    }

    public void b(ExecutionData executionData) throws IllegalStateException {
        ExecutionData executionData2 = this.f3613a.get(Long.valueOf(executionData.a()));
        if (executionData2 != null) {
            executionData2.a(executionData, false);
        }
    }

    public void a(ExecutionDataStore executionDataStore) {
        for (ExecutionData b2 : executionDataStore.b()) {
            b(b2);
        }
    }

    public ExecutionData a(long j) {
        return this.f3613a.get(Long.valueOf(j));
    }

    public boolean a(String str) {
        return this.b.contains(str);
    }

    public ExecutionData a(Long l, String str, int i) {
        ExecutionData executionData = this.f3613a.get(l);
        if (executionData == null) {
            ExecutionData executionData2 = new ExecutionData(l.longValue(), str, i);
            this.f3613a.put(l, executionData2);
            this.b.add(str);
            return executionData2;
        }
        executionData.a(l.longValue(), str, i);
        return executionData;
    }

    public void a() {
        for (ExecutionData d : this.f3613a.values()) {
            d.d();
        }
    }

    public Collection<ExecutionData> b() {
        return new ArrayList(this.f3613a.values());
    }

    public void a(IExecutionDataVisitor iExecutionDataVisitor) {
        for (ExecutionData c : b()) {
            iExecutionDataVisitor.c(c);
        }
    }

    public void c(ExecutionData executionData) {
        a(executionData);
    }
}
