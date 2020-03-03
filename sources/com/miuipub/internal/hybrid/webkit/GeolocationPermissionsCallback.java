package com.miuipub.internal.hybrid.webkit;

import android.webkit.GeolocationPermissions;
import miuipub.hybrid.GeolocationPermissions;

public class GeolocationPermissionsCallback implements GeolocationPermissions.Callback {

    /* renamed from: a  reason: collision with root package name */
    private GeolocationPermissions.Callback f8274a;

    public GeolocationPermissionsCallback(GeolocationPermissions.Callback callback) {
        this.f8274a = callback;
    }

    public void a(String str, boolean z, boolean z2) {
        this.f8274a.invoke(str, z, z2);
    }
}
