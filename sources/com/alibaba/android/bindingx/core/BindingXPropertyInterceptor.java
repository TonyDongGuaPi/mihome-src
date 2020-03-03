package com.alibaba.android.bindingx.core;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.alibaba.android.bindingx.core.PlatformManager;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BindingXPropertyInterceptor {
    private static BindingXPropertyInterceptor c = new BindingXPropertyInterceptor();

    /* renamed from: a  reason: collision with root package name */
    private final Handler f742a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final LinkedList<IPropertyUpdateInterceptor> b = new LinkedList<>();

    public interface IPropertyUpdateInterceptor {
        boolean a(@Nullable View view, @NonNull String str, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map, Object... objArr);
    }

    private BindingXPropertyInterceptor() {
    }

    @NonNull
    public static BindingXPropertyInterceptor a() {
        return c;
    }

    public void a(@Nullable IPropertyUpdateInterceptor iPropertyUpdateInterceptor) {
        if (iPropertyUpdateInterceptor != null) {
            this.b.add(iPropertyUpdateInterceptor);
        }
    }

    public boolean b(@Nullable IPropertyUpdateInterceptor iPropertyUpdateInterceptor) {
        if (iPropertyUpdateInterceptor != null) {
            return this.b.remove(iPropertyUpdateInterceptor);
        }
        return false;
    }

    public void b() {
        this.b.clear();
    }

    public void a(@Nullable View view, @NonNull String str, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map, Object... objArr) {
        if (!this.b.isEmpty()) {
            final View view2 = view;
            final String str2 = str;
            final Object obj2 = obj;
            final PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator2 = iDeviceResolutionTranslator;
            final Map<String, Object> map2 = map;
            final Object[] objArr2 = objArr;
            this.f742a.post(new WeakRunnable(new Runnable() {
                public void run() {
                    Iterator it = BindingXPropertyInterceptor.this.b.iterator();
                    while (it.hasNext()) {
                        ((IPropertyUpdateInterceptor) it.next()).a(view2, str2, obj2, iDeviceResolutionTranslator2, map2, objArr2);
                    }
                }
            }));
        }
    }

    public void c() {
        this.f742a.removeCallbacksAndMessages((Object) null);
    }

    @NonNull
    public List<IPropertyUpdateInterceptor> d() {
        return Collections.unmodifiableList(this.b);
    }
}
