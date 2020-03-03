package com.xiaomi.infra.galaxy.fds.android.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;

public class UserParam {

    /* renamed from: a  reason: collision with root package name */
    protected final Map<String, String> f10145a = new HashMap();

    public Map<String, String> a() {
        return Collections.unmodifiableMap(this.f10145a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry next : this.f10145a.entrySet()) {
            if (i != 0) {
                sb.append(Typography.c);
            }
            sb.append((String) next.getKey());
            String str = (String) next.getValue();
            if (str != null) {
                sb.append('=');
                sb.append(str);
            }
            i++;
        }
        return sb.toString();
    }
}
