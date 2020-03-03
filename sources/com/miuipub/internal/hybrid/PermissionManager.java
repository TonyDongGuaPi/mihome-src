package com.miuipub.internal.hybrid;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class PermissionManager {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Boolean> f8264a = new HashMap();
    private Config b;

    public PermissionManager(Config config) {
        this.b = config;
    }

    private boolean b(String str) {
        String str2;
        String str3;
        Uri parse = Uri.parse(str);
        if ("file".equals(parse.getScheme())) {
            str2 = "*";
        } else {
            str2 = parse.getHost();
        }
        boolean z = false;
        for (Map.Entry<String, Permission> value : this.b.h().entrySet()) {
            Permission permission = (Permission) value.getValue();
            String a2 = permission.a();
            if ("*".equals(a2)) {
                str3 = "*";
            } else {
                str3 = Uri.parse(a2).getHost();
            }
            if (permission.b()) {
                String[] split = str3.split("\\.");
                String[] split2 = str2.split("\\.");
                if (split2.length >= split.length) {
                    int i = 1;
                    while (true) {
                        if (i > split.length) {
                            z = true;
                            continue;
                            break;
                        } else if (!split2[split2.length - i].equals(split[split.length - i])) {
                            z = false;
                            continue;
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    continue;
                }
            } else {
                z = str2.equals(str3);
                continue;
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    public boolean a(String str) {
        if (!this.f8264a.containsKey(str)) {
            this.f8264a.put(str, Boolean.valueOf(b(str)));
        }
        return this.f8264a.get(str).booleanValue();
    }
}
