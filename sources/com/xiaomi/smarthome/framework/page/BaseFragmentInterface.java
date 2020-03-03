package com.xiaomi.smarthome.framework.page;

import android.support.v4.app.FragmentActivity;

public interface BaseFragmentInterface {
    FragmentActivity getValidActivity();

    boolean isValid();

    boolean onBackPressed();

    void onPageSelected();

    void refreshTitleBar();
}
