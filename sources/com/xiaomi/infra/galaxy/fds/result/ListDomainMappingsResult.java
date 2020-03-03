package com.xiaomi.infra.galaxy.fds.result;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListDomainMappingsResult {

    /* renamed from: a  reason: collision with root package name */
    private List<String> f1370a = new ArrayList();

    public List<String> a() {
        return this.f1370a;
    }

    public void a(List<String> list) {
        this.f1370a = list;
    }
}
