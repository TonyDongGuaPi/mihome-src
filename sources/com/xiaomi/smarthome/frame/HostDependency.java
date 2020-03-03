package com.xiaomi.smarthome.frame;

import com.xiaomi.smarthome.frame.baseui.PageHostApi;
import com.xiaomi.smarthome.frame.core.CoreHostApi;
import com.xiaomi.smarthome.frame.login.LoginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginActivityHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginBluetoothManagerHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginBluetoothSearchManagerHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginCommonHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginUPnPHostApi;

public class HostDependency {

    /* renamed from: a  reason: collision with root package name */
    private CoreHostApi f1520a;
    private PageHostApi b;
    private LoginHostApi c;
    private PluginHostApi d;
    private PluginActivityHostApi e;
    private PluginCommonHostApi f;
    private PluginUPnPHostApi g;
    private PluginBluetoothSearchManagerHostApi h;
    private PluginBluetoothManagerHostApi i;

    private HostDependency(Builder builder) {
        this.f1520a = builder.f1521a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
    }

    public CoreHostApi a() {
        return this.f1520a;
    }

    public PageHostApi b() {
        return this.b;
    }

    public LoginHostApi c() {
        return this.c;
    }

    public PluginHostApi d() {
        return this.d;
    }

    public PluginActivityHostApi e() {
        return this.e;
    }

    public PluginCommonHostApi f() {
        return this.f;
    }

    public PluginUPnPHostApi g() {
        return this.g;
    }

    public PluginBluetoothSearchManagerHostApi h() {
        return this.h;
    }

    public PluginBluetoothManagerHostApi i() {
        return this.i;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public CoreHostApi f1521a;
        /* access modifiers changed from: private */
        public PageHostApi b;
        /* access modifiers changed from: private */
        public LoginHostApi c;
        /* access modifiers changed from: private */
        public PluginHostApi d;
        /* access modifiers changed from: private */
        public PluginActivityHostApi e;
        /* access modifiers changed from: private */
        public PluginCommonHostApi f;
        /* access modifiers changed from: private */
        public PluginUPnPHostApi g;
        /* access modifiers changed from: private */
        public PluginBluetoothSearchManagerHostApi h;
        /* access modifiers changed from: private */
        public PluginBluetoothManagerHostApi i;

        public Builder a(CoreHostApi coreHostApi) {
            this.f1521a = coreHostApi;
            return this;
        }

        public Builder a(PageHostApi pageHostApi) {
            this.b = pageHostApi;
            return this;
        }

        public Builder a(LoginHostApi loginHostApi) {
            this.c = loginHostApi;
            return this;
        }

        public Builder a(PluginHostApi pluginHostApi) {
            this.d = pluginHostApi;
            return this;
        }

        public Builder a(PluginActivityHostApi pluginActivityHostApi) {
            this.e = pluginActivityHostApi;
            return this;
        }

        public Builder a(PluginCommonHostApi pluginCommonHostApi) {
            this.f = pluginCommonHostApi;
            return this;
        }

        public Builder a(PluginUPnPHostApi pluginUPnPHostApi) {
            this.g = pluginUPnPHostApi;
            return this;
        }

        public Builder a(PluginBluetoothSearchManagerHostApi pluginBluetoothSearchManagerHostApi) {
            this.h = pluginBluetoothSearchManagerHostApi;
            return this;
        }

        public Builder a(PluginBluetoothManagerHostApi pluginBluetoothManagerHostApi) {
            this.i = pluginBluetoothManagerHostApi;
            return this;
        }

        public HostDependency a() {
            if (this.f1521a == null) {
                throw new RuntimeException("CoreHostApi is null");
            } else if (this.b == null) {
                throw new RuntimeException("PageHostApi is null");
            } else if (this.c == null) {
                throw new RuntimeException("LoginHostApi is null");
            } else if (this.d == null) {
                throw new RuntimeException("PluginHostApi is null");
            } else if (this.e == null) {
                throw new RuntimeException("PluginActivityHostApi is null");
            } else if (this.f == null) {
                throw new RuntimeException("PluginCommonHostApi is null");
            } else if (this.g == null) {
                throw new RuntimeException("PluginUPnPHostApi is null");
            } else if (this.h == null) {
                throw new RuntimeException("PluginBluetoothSearchManagerHostApi is null");
            } else if (this.i != null) {
                return new HostDependency(this);
            } else {
                throw new RuntimeException("PluginBluetoothManagerHostApi is null");
            }
        }
    }
}
