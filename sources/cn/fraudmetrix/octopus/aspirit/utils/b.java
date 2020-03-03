package cn.fraudmetrix.octopus.aspirit.utils;

import cn.fraudmetrix.octopus.aspirit.bean.g;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static b f652a;
    private g b;

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (f652a == null) {
                f652a = new b();
            }
            bVar = f652a;
        }
        return bVar;
    }

    public g b() {
        if (this.b == null) {
            this.b = new g();
            this.b.sdk_version = OctopusManager.b().h();
        }
        return this.b;
    }

    public void c() {
        this.b = null;
    }
}
