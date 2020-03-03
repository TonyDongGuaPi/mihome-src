package com.xiaomi.smarthome.shop.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.shop.CurrentPage;
import com.xiaomi.youpin.RnMaskActivity;
import com.xiaomi.youpin.RnMaskManager;
import com.xiaomi.youpin.mimcmsg.MIMCMsgManager;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ShopFragment extends HybridFragment {
    static final String i = "ShopFragment";
    private static final int k = 10001;
    private static boolean l;
    BroadcastReceiver j = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (MiotStoreConstant.e.equals(intent.getAction())) {
                ShopFragment.this.b(intent.getStringExtra("url"));
            }
        }
    };
    private ShopFragmentHandler m = new ShopFragmentHandler(this);
    private boolean n = false;
    private boolean o = true;
    private FrameLayout p;

    /* access modifiers changed from: protected */
    public String b() {
        return "https://home.mi.com/shop/main";
    }

    public static class ShopFragmentHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<ShopFragment> f22171a;

        public ShopFragmentHandler(ShopFragment shopFragment) {
            this.f22171a = new WeakReference<>(shopFragment);
        }

        public void handleMessage(Message message) {
            if (message.what != 10001) {
                super.handleMessage(message);
            } else if (this.f22171a.get() != null) {
                ((ShopFragment) this.f22171a.get()).e();
            }
        }
    }

    private static class OnRnMaskListenerImpl implements RnMaskManager.OnRnMaskListener {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<ShopFragment> f22170a;

        OnRnMaskListenerImpl(ShopFragment shopFragment) {
            this.f22170a = new WeakReference<>(shopFragment);
        }

        public void a(String str) {
            if (this.f22170a.get() != null) {
                ((ShopFragment) this.f22170a.get()).a(str);
            }
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.p = (FrameLayout) view.findViewById(R.id.rn_mask_container);
        MIMCMsgManager.a().c();
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        RnMaskManager.a().a(new OnRnMaskListenerImpl(this));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MiotStoreConstant.e);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.j, intentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        RnMaskManager.a().a((RnMaskManager.OnRnMaskListener) null);
        this.m.removeMessages(10001);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.j);
    }

    public void onResume() {
        super.onResume();
        if (this.n && this.f16923a != null) {
            this.f16923a.requestLayout();
        }
    }

    public void a(boolean z) {
        CurrentPage.a(z);
        this.n = z;
        a((String) null, z);
        super.a(z);
        if (z) {
            this.m.postDelayed(new Runnable() {
                public final void run() {
                    ShopFragment.this.f();
                }
            }, 4000);
            SHApplication.setWXPayCallback();
            XmPluginHostApi.instance().addTouchRecord("smarthome_tab", "");
            MobclickAgent.c(getContext(), ShopFragment.class.getSimpleName());
            StatHelper.i(ShopFragment.class.getSimpleName());
            if (XmPluginHostApi.instance().isAccountLogined()) {
                XmPluginHostApi.instance().getRedpointManager().update();
            }
        } else {
            StatHelper.j(ShopFragment.class.getSimpleName());
        }
        if (!z) {
            this.m.removeMessages(10001);
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity instanceof SmartHomeMainActivity) {
                ((SmartHomeMainActivity) activity).updateTabviewShopDot(false);
            }
            if (this.o) {
                this.m.sendEmptyMessageDelayed(10001, 3000);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f() {
        if (isValid() && !l) {
            l = true;
            XmPluginHostApi.instance().openUrl(UrlConstants.red_envelope_rain);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.n && this.o && !HomeManager.A()) {
            this.o = false;
            RnMaskManager.a().b();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (this.n) {
            if (TextUtils.isEmpty(str)) {
                str = "http://home.mi.com/shop/background";
            }
            if (this.p.getVisibility() != 0) {
                Fragment newMiotStoreFragment = MiotStoreApi.a().newMiotStoreFragment(str, false);
                FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.rn_mask_container, newMiotStoreFragment);
                beginTransaction.commitAllowingStateLoss();
                this.p.setVisibility(4);
            }
            this.m.postDelayed(new Runnable() {
                public void run() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uri", "");
                    hashMap.put("open", true);
                    MiotStoreApi.a().sendJsEvent("MiotStoreEventPreloadData", hashMap);
                }
            }, 10);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        Intent intent = new Intent(getActivity(), RnMaskActivity.class);
        intent.putExtra("url", str);
        startActivity(intent);
    }
}
