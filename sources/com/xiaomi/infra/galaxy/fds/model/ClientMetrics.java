package com.xiaomi.infra.galaxy.fds.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientMetrics {

    /* renamed from: a  reason: collision with root package name */
    private List<MetricData> f1364a = new ArrayList();

    public enum LatencyMetricType {
        ExecutionTime
    }

    public List<MetricData> a() {
        return this.f1364a;
    }

    public void a(List<MetricData> list) {
        this.f1364a = list;
    }

    public void a(MetricData metricData) {
        this.f1364a.add(metricData);
    }
}
