package com.xiaomi.infra.galaxy.fds.result;

import com.xiaomi.infra.galaxy.fds.bean.GrantBean;
import com.xiaomi.infra.galaxy.fds.bean.OwnerBean;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccessControlPolicy {

    /* renamed from: a  reason: collision with root package name */
    private OwnerBean f1365a;
    private List<GrantBean> b;

    public AccessControlPolicy() {
    }

    public AccessControlPolicy(OwnerBean ownerBean, List<GrantBean> list) {
        this.f1365a = ownerBean;
        this.b = list;
    }

    public OwnerBean a() {
        return this.f1365a;
    }

    public void a(OwnerBean ownerBean) {
        this.f1365a = ownerBean;
    }

    public List<GrantBean> b() {
        return this.b;
    }

    public void a(List<GrantBean> list) {
        this.b = list;
    }
}
