package com.mi.util.permission;

public interface PermissionCallback {
    void onDenied();

    void onGranted();

    void onResult();
}
