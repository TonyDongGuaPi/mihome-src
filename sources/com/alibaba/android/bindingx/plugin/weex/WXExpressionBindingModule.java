package com.alibaba.android.bindingx.plugin.weex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.IEventHandler;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.alibaba.android.bindingx.core.internal.ExpressionPair;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import java.util.List;
import java.util.Map;

@Deprecated
public final class WXExpressionBindingModule extends WXSDKEngine.DestroyableModule {
    private BindingXCore mExpressionBindingCore;
    private PlatformManager mPlatformManager;

    @JSMethod
    @Deprecated
    public void enableBinding(@Nullable String str, @Nullable String str2) {
        if (this.mPlatformManager == null) {
            this.mPlatformManager = WXBindingXModule.createPlatformManager(this.mWXSDKInstance);
        }
        if (this.mExpressionBindingCore == null) {
            this.mExpressionBindingCore = new BindingXCore(this.mPlatformManager);
            this.mExpressionBindingCore.a("scroll", (BindingXCore.ObjectCreator<IEventHandler, Context, PlatformManager>) new BindingXCore.ObjectCreator<IEventHandler, Context, PlatformManager>() {
                public IEventHandler a(@NonNull Context context, @NonNull PlatformManager platformManager, Object... objArr) {
                    return new BindingXScrollHandler(context, platformManager, objArr);
                }
            });
        }
    }

    @JSMethod
    @Deprecated
    public void createBinding(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable List<Map<String, Object>> list, @Nullable JSCallback jSCallback) {
        String str4 = null;
        enableBinding((String) null, (String) null);
        ExpressionPair a2 = ExpressionPair.a((String) null, str3);
        BindingXCore bindingXCore = this.mExpressionBindingCore;
        final JSCallback jSCallback2 = jSCallback;
        AnonymousClass2 r10 = new BindingXCore.JavaScriptCallback() {
            public void a(Object obj) {
                if (jSCallback2 != null) {
                    jSCallback2.invokeAndKeepAlive(obj);
                }
            }
        };
        Context context = this.mWXSDKInstance == null ? null : this.mWXSDKInstance.getContext();
        if (this.mWXSDKInstance != null) {
            str4 = this.mWXSDKInstance.getInstanceId();
        }
        bindingXCore.a(str, (String) null, str2, (Map<String, Object>) null, a2, list, (Map<String, ExpressionPair>) null, r10, context, str4);
    }

    @JSMethod
    @Deprecated
    public void disableBinding(@Nullable String str, @Nullable String str2) {
        if (this.mExpressionBindingCore != null) {
            this.mExpressionBindingCore.a(str, str2);
        }
    }

    @JSMethod
    @Deprecated
    public void disableAll() {
        if (this.mExpressionBindingCore != null) {
            this.mExpressionBindingCore.a();
        }
    }

    public void destroy() {
        if (this.mExpressionBindingCore != null) {
            this.mExpressionBindingCore.a();
            this.mExpressionBindingCore = null;
        }
    }

    public void onActivityPause() {
        if (this.mExpressionBindingCore != null) {
            this.mExpressionBindingCore.b();
        }
    }

    public void onActivityResume() {
        if (this.mExpressionBindingCore != null) {
            this.mExpressionBindingCore.c();
        }
    }
}
