package com.xiaomi.jr.mipay.codepay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.xiaomi.jr.deeplink.DeeplinkConstants;
import com.xiaomi.jr.mipay.codepay.presenter.Presenter;

public class BaseFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private int f10912a;
    private int b;
    private Intent c;

    public Presenter c() {
        return null;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f10912a = arguments.getInt(DeeplinkConstants.KEY_REQUEST_CODE);
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (c() != null) {
            c().d();
        }
    }

    public void onResume() {
        super.onResume();
        if (c() != null) {
            c().h();
        }
    }

    public void onPause() {
        super.onPause();
        if (c() != null) {
            c().i();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (c() != null) {
            c().a(i, i2, intent);
        }
    }

    public void d() {
        FragmentActivity activity = getActivity();
        if (activity instanceof FragmentStackActivity) {
            ((FragmentStackActivity) activity).finishFragment();
        }
    }

    public void a(int i, Intent intent) {
        this.b = i;
        this.c = intent;
    }

    public int e() {
        return this.f10912a;
    }

    public int f() {
        return this.b;
    }

    public Intent g() {
        return this.c;
    }
}
