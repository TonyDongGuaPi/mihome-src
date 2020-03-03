package com.xiaomi.smarthome.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.plugin.XmPluginBaseFragment;
import com.xiaomi.pluginhost.AppInitialApi;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.TabFragment;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.log.LogUtils;

public abstract class HybridFragment extends TabFragment {
    static final String c = "android:support:fragments";
    final String b = (getClass().getSimpleName() + " <HybridFragment> ");
    Fragment d;
    View e;
    boolean f = false;
    boolean g = false;
    Handler h = new Handler();

    /* access modifiers changed from: protected */
    public abstract String b();

    public void c() {
    }

    public void d() {
    }

    public void refreshTitleBar() {
    }

    public HybridFragment() {
        Miio.h(this.b, "HybridFragment()");
    }

    public void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.remove(c);
        }
        super.onCreate(bundle);
        Miio.h(this.b, "onCreate");
        refreshTitleBar();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.g) {
            if (this.d == null) {
                try {
                    LogUtils.d(this.b, "create fragment onSwitchtoPage");
                    this.f = UrlDispatchManger.a().b(b());
                    FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
                    this.d = UrlDispatchManger.a().a(getContext(), b(), true);
                    beginTransaction.replace(R.id.fragment_container, this.d);
                    beginTransaction.commit();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (this.d.getHost() != null) {
                this.d.onStart();
                this.d.onResume();
            }
            if (this.d instanceof XmPluginBaseFragment) {
                ((XmPluginBaseFragment) this.d).onStartForStat();
            }
            if (!CoreApi.a().h()) {
                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsAccountReadyCallback) new CoreApi.IsAccountReadyCallback() {
                    public void a(boolean z, String str) {
                        LogUtils.d(HybridFragment.this.b, "onAccountReady");
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("mijiashop.update.tab.data"));
                    }
                });
            }
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Miio.h(this.b, "onCreateView");
        this.e = layoutInflater.inflate(R.layout.main_tab_fragment, (ViewGroup) null);
        return this.e;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        Miio.h(this.b, "onViewCreated");
        if (bundle != null) {
            a(bundle);
        }
    }

    public void onResume() {
        super.onResume();
        Miio.h(this.b, "onResume");
    }

    public void onPause() {
        super.onPause();
        Miio.h(this.b, "onPause");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Miio.h(this.b, "onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Miio.h(this.b, ActivityInfo.TYPE_STR_ONDESTROY);
        this.h.removeCallbacksAndMessages((Object) null);
    }

    public void a(boolean z) {
        String str = this.b;
        LogUtils.d(str, "onSwitchtoPage:" + z);
        this.g = z;
        if (z) {
            AppInitialApi.a().a(getContext(), (AppInitialApi.IsInitialedCallback) new AppInitialApi.IsInitialedCallback() {
                public void a() {
                    LogUtils.d(HybridFragment.this.b, "onInitialed onSwitchtoPage");
                    if (HybridFragment.this.d == null) {
                        HybridFragment.this.h.postDelayed(new Runnable() {
                            public void run() {
                                HybridFragment.this.a();
                            }
                        }, 100);
                    } else {
                        HybridFragment.this.a();
                    }
                }
            });
        } else {
            if (!(this.d == null || this.d.getHost() == null)) {
                this.d.onPause();
                this.d.onStop();
            }
            if (this.d instanceof XmPluginBaseFragment) {
                ((XmPluginBaseFragment) this.d).onStopForStat();
            }
        }
        if (getActivity() != null) {
        }
    }

    private void a(Bundle bundle) {
        String string = bundle.getString("source");
        if (TextUtils.isEmpty(string)) {
            string = bundle.getString(MIOTStat.SOURCE);
        }
        if (!TextUtils.isEmpty(string)) {
            MIOTStat.Log(MIOTStat.SOURCE, string.replace("@", String.valueOf(System.currentTimeMillis())));
        }
    }
}
