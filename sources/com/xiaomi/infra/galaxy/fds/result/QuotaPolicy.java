package com.xiaomi.infra.galaxy.fds.result;

import com.xiaomi.infra.galaxy.fds.bean.Quota;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuotaPolicy {

    /* renamed from: a  reason: collision with root package name */
    private List<Quota> f1374a = new ArrayList();

    public List<Quota> a() {
        return this.f1374a;
    }

    public void a(List<Quota> list) {
        this.f1374a = list;
    }

    public void a(Quota quota) {
        this.f1374a.add(quota);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QuotaPolicy quotaPolicy = (QuotaPolicy) obj;
        return this.f1374a == null ? quotaPolicy.f1374a == null : this.f1374a.equals(quotaPolicy.f1374a);
    }

    public int hashCode() {
        if (this.f1374a != null) {
            return this.f1374a.hashCode();
        }
        return 0;
    }
}
