package com.xiaomi.infra.galaxy.fds.result;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UploadPartResultList {

    /* renamed from: a  reason: collision with root package name */
    private List<UploadPartResult> f1377a = new ArrayList();

    public List<UploadPartResult> a() {
        return this.f1377a;
    }

    public void a(List<UploadPartResult> list) {
        this.f1377a = list;
    }

    public void a(UploadPartResult uploadPartResult) {
        this.f1377a.add(uploadPartResult);
    }
}
