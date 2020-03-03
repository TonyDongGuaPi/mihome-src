package com.xiaomi.infra.galaxy.fds.model;

public class MetricData {

    /* renamed from: a  reason: collision with root package name */
    private MetricType f10167a;
    private String b;
    private long c;
    private long d;

    public enum MetricType {
        Latency,
        Throughput,
        Counter
    }

    public MetricData() {
    }

    public MetricData(MetricType metricType, String str, long j, long j2) {
        this.f10167a = metricType;
        this.b = str;
        this.c = j;
        this.d = j2;
    }

    public MetricType a() {
        return this.f10167a;
    }

    public void a(MetricType metricType) {
        this.f10167a = metricType;
    }

    public MetricData b(MetricType metricType) {
        this.f10167a = metricType;
        return this;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public MetricData b(String str) {
        this.b = str;
        return this;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public MetricData b(long j) {
        this.c = j;
        return this;
    }

    public long d() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public MetricData d(long j) {
        this.d = j;
        return this;
    }
}
