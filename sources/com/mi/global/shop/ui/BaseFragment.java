package com.mi.global.shop.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.mi.global.shop.util.Utils;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6959a = "BaseFragment";
    private NetworkConnectivityChangedReceiver b;
    protected boolean h;

    /* access modifiers changed from: protected */
    public void b(int i) {
    }

    public void onRefresh() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a();
        this.h = Utils.Network.isNetWorkConnected(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        b();
    }

    private void a() {
        if (this.b == null) {
            this.b = new NetworkConnectivityChangedReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getActivity().registerReceiver(this.b, intentFilter);
    }

    private void b() {
        getActivity().unregisterReceiver(this.b);
    }

    private class NetworkConnectivityChangedReceiver extends BroadcastReceiver {
        private NetworkConnectivityChangedReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            boolean isNetWorkConnected = Utils.Network.isNetWorkConnected(BaseFragment.this.getActivity());
            if (!BaseFragment.this.h && isNetWorkConnected) {
                BaseFragment.this.b(Utils.Network.getActiveNetworkType(BaseFragment.this.getActivity()));
            }
            BaseFragment.this.h = isNetWorkConnected;
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public int d() {
        int i = 0;
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            int parseInt = Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString());
            try {
                return getResources().getDimensionPixelSize(parseInt);
            } catch (Exception e) {
                int i2 = parseInt;
                e = e;
                i = i2;
                e.printStackTrace();
                return i;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return i;
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        int d = d();
        if (d > 0) {
            view.getLayoutParams().height = d;
            view.requestLayout();
        }
    }

    public boolean e() {
        FragmentActivity activity = getActivity();
        if (activity == null || !isAdded()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !activity.isFinishing();
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }
}
