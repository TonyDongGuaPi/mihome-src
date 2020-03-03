package com.xiaomi.mipush.sdk;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.push.service.module.PushChannelRegion;

public class PushConfiguration {

    /* renamed from: a  reason: collision with root package name */
    private PushChannelRegion f11519a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;

    public static class PushConfigurationBuilder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public PushChannelRegion f11520a;
        private boolean b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        public boolean f;

        public PushConfigurationBuilder a(PushChannelRegion pushChannelRegion) {
            this.f11520a = pushChannelRegion;
            return this;
        }

        public PushConfigurationBuilder a(boolean z) {
            this.c = z;
            return this;
        }

        public PushConfiguration a() {
            return new PushConfiguration(this);
        }

        public PushConfigurationBuilder b(boolean z) {
            this.d = z;
            return this;
        }

        public PushConfigurationBuilder c(boolean z) {
            this.e = z;
            return this;
        }

        public PushConfigurationBuilder d(boolean z) {
            this.f = z;
            return this;
        }
    }

    public PushConfiguration() {
        this.f11519a = PushChannelRegion.China;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
    }

    private PushConfiguration(PushConfigurationBuilder pushConfigurationBuilder) {
        this.f11519a = pushConfigurationBuilder.f11520a == null ? PushChannelRegion.China : pushConfigurationBuilder.f11520a;
        this.c = pushConfigurationBuilder.c;
        this.d = pushConfigurationBuilder.d;
        this.e = pushConfigurationBuilder.e;
        this.f = pushConfigurationBuilder.f;
    }

    public PushChannelRegion a() {
        return this.f11519a;
    }

    public void a(PushChannelRegion pushChannelRegion) {
        this.f11519a = pushChannelRegion;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean b() {
        return this.c;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.d;
    }

    public void d(boolean z) {
        this.f = z;
    }

    public boolean d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PushConfiguration{");
        stringBuffer.append("Region:");
        stringBuffer.append(this.f11519a == null ? "null" : this.f11519a.name());
        stringBuffer.append(",mOpenHmsPush:" + this.c);
        stringBuffer.append(",mOpenFCMPush:" + this.d);
        stringBuffer.append(",mOpenCOSPush:" + this.e);
        stringBuffer.append(",mOpenFTOSPush:" + this.f);
        stringBuffer.append(Operators.BLOCK_END);
        return stringBuffer.toString();
    }
}
