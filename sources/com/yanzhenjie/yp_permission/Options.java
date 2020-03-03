package com.yanzhenjie.yp_permission;

import android.os.Build;
import com.yanzhenjie.yp_permission.install.InstallRequest;
import com.yanzhenjie.yp_permission.install.NRequestFactory;
import com.yanzhenjie.yp_permission.install.ORequestFactory;
import com.yanzhenjie.yp_permission.overlay.LRequestFactory;
import com.yanzhenjie.yp_permission.overlay.MRequestFactory;
import com.yanzhenjie.yp_permission.overlay.OverlayRequest;
import com.yanzhenjie.yp_permission.runtime.PermissionRequest;
import com.yanzhenjie.yp_permission.runtime.Runtime;
import com.yanzhenjie.yp_permission.source.Source;

public class Options {

    /* renamed from: a  reason: collision with root package name */
    private static final InstallRequestFactory f2431a;
    private static final OverlayRequestFactory b;
    private Source c;

    public interface InstallRequestFactory {
        InstallRequest a(Source source);
    }

    public interface OverlayRequestFactory {
        OverlayRequest a(Source source);
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            f2431a = new ORequestFactory();
        } else {
            f2431a = new NRequestFactory();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            b = new MRequestFactory();
        } else {
            b = new LRequestFactory();
        }
    }

    Options(Source source) {
        this.c = source;
    }

    @Deprecated
    public PermissionRequest a(String... strArr) {
        return a().a(strArr);
    }

    @Deprecated
    public PermissionRequest a(String[]... strArr) {
        return a().a(strArr);
    }

    public Runtime a() {
        return new Runtime(this.c);
    }

    public InstallRequest b() {
        return f2431a.a(this.c);
    }

    public OverlayRequest c() {
        return b.a(this.c);
    }
}
