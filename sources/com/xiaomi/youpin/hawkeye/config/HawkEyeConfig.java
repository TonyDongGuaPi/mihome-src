package com.xiaomi.youpin.hawkeye.config;

import com.xiaomi.youpin.hawkeye.network.DefaultNetWorkFilterImpl;
import com.xiaomi.youpin.hawkeye.network.INetWorkRecordFilter;
import com.xiaomi.youpin.hawkeye.storage.DBSaveImpl;
import com.xiaomi.youpin.hawkeye.storage.ISaveData;
import com.xiaomi.youpin.hawkeye.timecounter.IPageListener;
import com.xiaomi.youpin.hawkeye.upload.DefaultUploadImpl;
import com.xiaomi.youpin.hawkeye.upload.IUpload;
import com.xiaomi.youpin.hawkeye.utils.HLogDefaultLoggingDelegate;
import com.xiaomi.youpin.hawkeye.utils.ILoggingDelegate;

public class HawkEyeConfig {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f23351a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public IUpload d;
    /* access modifiers changed from: private */
    public ILoggingDelegate e;
    /* access modifiers changed from: private */
    public long f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public INetWorkRecordFilter h;
    /* access modifiers changed from: private */
    public IPageListener i;
    /* access modifiers changed from: private */
    public ISaveData j;

    public boolean a() {
        return this.f23351a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public IUpload d() {
        return this.d;
    }

    public ILoggingDelegate e() {
        return this.e;
    }

    public long f() {
        return this.f;
    }

    public long g() {
        return this.g;
    }

    public INetWorkRecordFilter h() {
        return this.h;
    }

    public IPageListener i() {
        return this.i;
    }

    public ISaveData j() {
        return this.j;
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private boolean f23352a;
        private String b;
        private boolean c = true;
        private IUpload d = new DefaultUploadImpl();
        private ILoggingDelegate e = new HLogDefaultLoggingDelegate();
        private long f = 2000;
        private long g = 60000;
        private INetWorkRecordFilter h = new DefaultNetWorkFilterImpl();
        private IPageListener i;
        private ISaveData j = new DBSaveImpl();

        public Builder b(long j2) {
            return this;
        }

        public static Builder a() {
            return new Builder();
        }

        public Builder a(boolean z) {
            this.f23352a = z;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder b(boolean z) {
            this.c = z;
            return this;
        }

        public Builder a(IUpload iUpload) {
            this.d = iUpload;
            return this;
        }

        public Builder a(ILoggingDelegate iLoggingDelegate) {
            this.e = iLoggingDelegate;
            return this;
        }

        public Builder a(long j2) {
            this.f = j2;
            return this;
        }

        public Builder a(INetWorkRecordFilter iNetWorkRecordFilter) {
            this.h = iNetWorkRecordFilter;
            return this;
        }

        public Builder a(IPageListener iPageListener) {
            this.i = iPageListener;
            return this;
        }

        public Builder a(ISaveData iSaveData) {
            this.j = iSaveData;
            return this;
        }

        public HawkEyeConfig b() {
            HawkEyeConfig hawkEyeConfig = new HawkEyeConfig();
            boolean unused = hawkEyeConfig.c = this.c;
            IUpload unused2 = hawkEyeConfig.d = this.d;
            String unused3 = hawkEyeConfig.b = this.b;
            boolean unused4 = hawkEyeConfig.f23351a = this.f23352a;
            long unused5 = hawkEyeConfig.f = this.f;
            long unused6 = hawkEyeConfig.g = this.g;
            ILoggingDelegate unused7 = hawkEyeConfig.e = this.e;
            INetWorkRecordFilter unused8 = hawkEyeConfig.h = this.h;
            IPageListener unused9 = hawkEyeConfig.i = this.i;
            ISaveData unused10 = hawkEyeConfig.j = this.j;
            return hawkEyeConfig;
        }
    }
}
