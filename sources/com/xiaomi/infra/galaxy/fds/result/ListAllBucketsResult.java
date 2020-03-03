package com.xiaomi.infra.galaxy.fds.result;

import com.xiaomi.infra.galaxy.fds.bean.BucketBean;
import com.xiaomi.infra.galaxy.fds.bean.OwnerBean;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListAllBucketsResult {

    /* renamed from: a  reason: collision with root package name */
    private OwnerBean f1369a;
    private List<BucketBean> b;

    public ListAllBucketsResult() {
    }

    public ListAllBucketsResult(OwnerBean ownerBean, List<BucketBean> list) {
        this.f1369a = ownerBean;
        this.b = list;
    }

    public OwnerBean a() {
        return this.f1369a;
    }

    public void a(OwnerBean ownerBean) {
        this.f1369a = ownerBean;
    }

    public List<BucketBean> b() {
        return this.b;
    }

    public void a(List<BucketBean> list) {
        this.b = list;
    }
}
