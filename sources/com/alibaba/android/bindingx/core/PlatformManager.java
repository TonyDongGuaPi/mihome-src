package com.alibaba.android.bindingx.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.Map;

public class PlatformManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public IDeviceResolutionTranslator f746a;
    /* access modifiers changed from: private */
    public IViewFinder b;
    /* access modifiers changed from: private */
    public IViewUpdater c;

    public interface IDeviceResolutionTranslator {
        double a(double d, Object... objArr);

        double b(double d, Object... objArr);
    }

    public interface IViewFinder {
        @Nullable
        View a(String str, Object... objArr);
    }

    public interface IViewUpdater {
        void a(@NonNull View view, @NonNull String str, @NonNull Object obj, @NonNull IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map, Object... objArr);
    }

    private PlatformManager() {
    }

    @NonNull
    public IDeviceResolutionTranslator a() {
        return this.f746a;
    }

    @NonNull
    public IViewFinder b() {
        return this.b;
    }

    @NonNull
    public IViewUpdater c() {
        return this.c;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private IDeviceResolutionTranslator f747a;
        private IViewFinder b;
        private IViewUpdater c;

        public PlatformManager a() {
            PlatformManager platformManager = new PlatformManager();
            IViewFinder unused = platformManager.b = this.b;
            IDeviceResolutionTranslator unused2 = platformManager.f746a = this.f747a;
            IViewUpdater unused3 = platformManager.c = this.c;
            return platformManager;
        }

        public Builder a(@NonNull IDeviceResolutionTranslator iDeviceResolutionTranslator) {
            this.f747a = iDeviceResolutionTranslator;
            return this;
        }

        public Builder a(@NonNull IViewFinder iViewFinder) {
            this.b = iViewFinder;
            return this;
        }

        public Builder a(@NonNull IViewUpdater iViewUpdater) {
            this.c = iViewUpdater;
            return this;
        }
    }
}
