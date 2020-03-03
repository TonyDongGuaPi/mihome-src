package com.xiaomi.smarthome.framework.redpoint;

import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import java.util.HashMap;
import java.util.List;

public class RedPointController implements RedPointManagerNew.RedPointListener {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, Boolean> f17679a = new HashMap<>();

    public RedPointController(List<String> list, boolean z) {
        for (String next : list) {
            this.f17679a.put(next, Boolean.valueOf(z));
            RedPointManagerNew.a().a(next, (RedPointManagerNew.RedPointListener) this);
        }
    }

    public boolean a(String str) {
        if (this.f17679a.containsKey(str)) {
            return this.f17679a.get(str).booleanValue();
        }
        return false;
    }

    public void b(String str) {
        this.f17679a.put(str, false);
    }

    public void a() {
        a(false);
    }

    public void a(boolean z) {
        for (String next : this.f17679a.keySet()) {
            this.f17679a.put(next, Boolean.valueOf(z));
            RedPointManagerNew.a().b(next);
        }
    }

    public void a(String str, boolean z) {
        if (this.f17679a.keySet().contains(str)) {
            this.f17679a.put(str, Boolean.valueOf(z));
            RedPointManagerNew.a().b(str);
        }
    }

    public void b() {
        for (String b : this.f17679a.keySet()) {
            RedPointManagerNew.a().b(b, this);
        }
    }
}
