package com.xiaomi.smarthome.operation;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.xiaomi.smarthome.operation.appcolud.AppCloudOperation;
import com.xiaomi.smarthome.operation.indexdefault.IndexNoDeviceOperation;
import com.xiaomi.smarthome.operation.mainpage.MainPageBannerHelper;
import com.xiaomi.smarthome.operation.my.MyOperation;
import com.xiaomi.smarthome.operation.smartrecommend.SmartRecommendOperation;

public class OperationCollector {
    private OperationCollector() {
    }

    public static MainPageBannerHelper a(FragmentActivity fragmentActivity) {
        return (MainPageBannerHelper) ViewModelProviders.a(fragmentActivity).a(MainPageBannerHelper.class);
    }

    public static IndexNoDeviceOperation b(FragmentActivity fragmentActivity) {
        return (IndexNoDeviceOperation) ViewModelProviders.a(fragmentActivity).a(IndexNoDeviceOperation.class);
    }

    public static SmartRecommendOperation a(Fragment fragment) {
        return (SmartRecommendOperation) ViewModelProviders.a(fragment).a(SmartRecommendOperation.class);
    }

    public static MyOperation b(Fragment fragment) {
        return (MyOperation) ViewModelProviders.a(fragment).a(MyOperation.class);
    }

    public static AppCloudOperation c(FragmentActivity fragmentActivity) {
        return (AppCloudOperation) ViewModelProviders.a(fragmentActivity).a(AppCloudOperation.class);
    }
}
