package com.xiaomi.infra.galaxy.fds.bean;

import com.xiaomi.infra.galaxy.fds.Action;

public class Quota {

    /* renamed from: a  reason: collision with root package name */
    private QuotaType f10162a;
    private Action b;
    private long c;

    public enum QuotaType {
        QPS,
        Throughput
    }

    public Quota() {
    }

    public Quota(QuotaType quotaType, Action action, long j) {
        this.f10162a = quotaType;
        this.b = action;
        this.c = j;
    }

    public QuotaType a() {
        return this.f10162a;
    }

    public void a(QuotaType quotaType) {
        this.f10162a = quotaType;
    }

    public Action b() {
        return this.b;
    }

    public void a(Action action) {
        this.b = action;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quota quota = (Quota) obj;
        return this.c == quota.c && this.b == quota.b && this.f10162a == quota.f10162a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f10162a != null ? this.f10162a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) (this.c ^ (this.c >>> 32)));
    }
}
