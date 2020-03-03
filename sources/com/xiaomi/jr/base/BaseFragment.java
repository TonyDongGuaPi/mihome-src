package com.xiaomi.jr.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.xiaomi.jr.base.LoadingIndicator;
import com.xiaomi.jr.common.utils.Utils;

public abstract class BaseFragment extends Fragment implements LoadingIndicator.Callback {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10327a = "pageId";
    public static final String b = "onResumeIntercepted";
    /* access modifiers changed from: protected */
    public String c;
    /* access modifiers changed from: protected */
    public String d;
    protected String e;
    protected boolean f;
    protected boolean g;
    protected boolean h;
    protected LoadingIndicator i;
    /* access modifiers changed from: protected */
    public IPageDelegate j;

    /* access modifiers changed from: protected */
    public abstract void b();

    public void e() {
    }

    public void f() {
    }

    public boolean g() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof IPageDelegate) {
            this.j = (IPageDelegate) getActivity();
        }
    }

    public void onDetach() {
        this.j = null;
        super.onDetach();
    }

    public void a() {
        if (!this.h) {
            this.i = new LoadingIndicator(getActivity(), this);
            this.i.a();
            a(getArguments());
            b();
            this.h = true;
        }
    }

    public void onDestroy() {
        this.i.b();
        super.onDestroy();
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
        a(bundle);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        if (bundle != null) {
            this.d = bundle.getString("url");
            this.c = bundle.getString("title");
            this.e = bundle.getString("pageId");
            this.f = bundle.getBoolean(b, false);
        }
    }

    public void onResume() {
        super.onResume();
        if (!this.f) {
            d();
        }
    }

    public void onPause() {
        c();
        super.onPause();
    }

    public void c() {
        if (this.h) {
            this.g = false;
        }
        this.i.d();
    }

    public void d() {
        if (this.i != null) {
            this.i.c();
        }
        if (this.h) {
            this.g = true;
        }
    }

    public void startActivity(Intent intent) {
        h();
        Utils.a((Context) getActivity(), intent);
        super.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i2) {
        h();
        Utils.a((Context) getActivity(), intent);
        super.startActivityForResult(intent, i2);
    }

    private void h() {
        if (this.j != null) {
            this.j.getPageReloader().c();
        }
    }
}
