package com.xiaomi.smarthome;

import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import java.util.LinkedList;
import java.util.List;

public class FrescoMemoryTrimmableRegistry implements MemoryTrimmableRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final List<MemoryTrimmable> f1498a = new LinkedList();

    public void registerMemoryTrimmable(MemoryTrimmable memoryTrimmable) {
        this.f1498a.add(memoryTrimmable);
    }

    public void unregisterMemoryTrimmable(MemoryTrimmable memoryTrimmable) {
        this.f1498a.remove(memoryTrimmable);
    }

    public synchronized void a(MemoryTrimType memoryTrimType) {
        for (MemoryTrimmable trim : this.f1498a) {
            trim.trim(memoryTrimType);
        }
    }
}
