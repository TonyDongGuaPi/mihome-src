package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class Metadata {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final List<Directory> f5207a = new ArrayList();

    @NotNull
    public Iterable<Directory> a() {
        return this.f5207a;
    }

    @NotNull
    public <T extends Directory> Collection<T> a(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        for (Directory next : this.f5207a) {
            if (cls.isAssignableFrom(next.getClass())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public int b() {
        return this.f5207a.size();
    }

    public <T extends Directory> void a(@NotNull T t) {
        this.f5207a.add(t);
    }

    @Nullable
    public <T extends Directory> T b(@NotNull Class<T> cls) {
        Iterator<Directory> it = this.f5207a.iterator();
        while (it.hasNext()) {
            T t = (Directory) it.next();
            if (cls.isAssignableFrom(t.getClass())) {
                return t;
            }
        }
        return null;
    }

    public boolean c(Class<? extends Directory> cls) {
        for (Directory directory : this.f5207a) {
            if (cls.isAssignableFrom(directory.getClass())) {
                return true;
            }
        }
        return false;
    }

    public boolean c() {
        for (Directory f : a()) {
            if (f.f()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        int b = b();
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(b);
        objArr[1] = b == 1 ? "directory" : "directories";
        return String.format("Metadata (%d %s)", objArr);
    }
}
