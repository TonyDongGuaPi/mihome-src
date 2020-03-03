package com.miuipub.internal.hybrid.webkit;

import miuipub.hybrid.HybridSettings;

public class WebSettings extends HybridSettings {

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.WebSettings f8281a;

    public WebSettings(android.webkit.WebSettings webSettings) {
        this.f8281a = webSettings;
    }

    public void a(boolean z) {
        this.f8281a.setJavaScriptEnabled(z);
    }

    public void a(String str) {
        this.f8281a.setUserAgentString(str);
    }

    public String a() {
        return this.f8281a.getUserAgentString();
    }

    public void b(boolean z) {
        this.f8281a.setUseWideViewPort(z);
    }

    public void c(boolean z) {
        this.f8281a.setSupportMultipleWindows(z);
    }

    public void d(boolean z) {
        this.f8281a.setLoadWithOverviewMode(z);
    }

    public void e(boolean z) {
        this.f8281a.setDomStorageEnabled(z);
    }

    public void f(boolean z) {
        this.f8281a.setDatabaseEnabled(z);
    }

    public void g(boolean z) {
        this.f8281a.setAllowFileAccessFromFileURLs(z);
    }

    public void h(boolean z) {
        this.f8281a.setAllowUniversalAccessFromFileURLs(z);
    }

    public void a(int i) {
        this.f8281a.setCacheMode(i);
    }

    public void i(boolean z) {
        this.f8281a.setJavaScriptCanOpenWindowsAutomatically(z);
    }

    public void b(int i) {
        this.f8281a.setTextZoom(i);
    }

    public void j(boolean z) {
        this.f8281a.setGeolocationEnabled(z);
    }

    public void k(boolean z) {
        this.f8281a.setAppCacheEnabled(z);
    }

    public void b(String str) {
        this.f8281a.setAppCachePath(str);
    }

    public void c(String str) {
        this.f8281a.setGeolocationDatabasePath(str);
    }
}
