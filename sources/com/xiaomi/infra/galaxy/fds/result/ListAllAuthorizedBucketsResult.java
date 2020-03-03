package com.xiaomi.infra.galaxy.fds.result;

import com.xiaomi.infra.galaxy.fds.bean.BucketBean;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListAllAuthorizedBucketsResult {

    /* renamed from: a  reason: collision with root package name */
    private List<BucketBean> f1368a;

    public ListAllAuthorizedBucketsResult() {
    }

    public ListAllAuthorizedBucketsResult(List<BucketBean> list) {
        this.f1368a = list;
    }

    public List<BucketBean> a() {
        return this.f1368a;
    }

    public void a(List<BucketBean> list) {
        this.f1368a = list;
    }
}
