package com.xiaomi.smarthome.shop.mishop;

import android.app.LocalActivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.xiaomi.mishopsdk.SdkUtils;
import com.xiaomi.smarthome.SmartHomeMainActivity;

public class MiShopFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private LocalActivityManager f22175a;
    private View b;

    public static MiShopFragment a(String str) {
        MiShopFragment miShopFragment = new MiShopFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uri", str);
        miShopFragment.setArguments(bundle);
        return miShopFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22175a = ((SmartHomeMainActivity) getActivity()).getLocalActivityManager();
        String string = getArguments().getString("uri");
        if (this.f22175a != null) {
            this.b = SdkUtils.createViewOfPluginActivity(this.f22175a, Uri.parse(string));
        }
        return this.b;
    }

    public void onViewCreated(View view, Bundle bundle) {
        View view2 = this.b;
        while (view2 != null) {
            view2.setFitsSystemWindows(false);
            ViewParent parent = view2.getParent();
            view2 = parent instanceof View ? (View) parent : null;
        }
    }
}
