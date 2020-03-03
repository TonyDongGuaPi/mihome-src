package com.ximalaya.ting.android.opensdk.model.xdcs;

import java.util.HashMap;
import java.util.Map;

public class XdcsEvent extends BaseEvent {

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f2139a = new HashMap();

    public Map<String, String> h() {
        return this.f2139a;
    }

    public void a(Map<String, String> map) {
        this.f2139a = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("XdcsEvent { ");
        for (Map.Entry next : this.f2139a.entrySet()) {
            sb.append(((String) next.getKey()) + " : " + ((String) next.getValue()) + " ");
        }
        sb.append(" } ");
        return sb.toString();
    }
}
