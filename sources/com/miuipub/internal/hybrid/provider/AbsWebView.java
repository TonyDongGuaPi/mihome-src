package com.miuipub.internal.hybrid.provider;

import android.content.Context;
import android.view.View;
import miuipub.hybrid.HybridBackForwardList;
import miuipub.hybrid.HybridSettings;
import miuipub.hybrid.HybridView;

public abstract class AbsWebView {

    /* renamed from: a  reason: collision with root package name */
    protected Context f8271a;
    protected HybridView b;

    public View a() {
        return null;
    }

    public void a(int i) {
    }

    public void a(AbsWebChromeClient absWebChromeClient) {
    }

    public void a(AbsWebViewClient absWebViewClient) {
    }

    public void a(Object obj, String str) {
    }

    public void a(String str) {
    }

    public void a(boolean z) {
    }

    public HybridSettings b() {
        return null;
    }

    public void c() {
    }

    public void d() {
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public void g() {
    }

    public String h() {
        return null;
    }

    public String i() {
        return null;
    }

    public int j() {
        return 0;
    }

    public float k() {
        return 1.0f;
    }

    public Context l() {
        return null;
    }

    public View m() {
        return null;
    }

    public HybridBackForwardList n() {
        return null;
    }

    public AbsWebView(Context context, HybridView hybridView) {
        this.f8271a = context;
        this.b = hybridView;
    }
}
