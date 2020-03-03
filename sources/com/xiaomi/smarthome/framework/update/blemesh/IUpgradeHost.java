package com.xiaomi.smarthome.framework.update.blemesh;

import android.content.Context;

public interface IUpgradeHost {
    Context getContext();

    void hideLoaing();

    void onCallback(int i);

    void showFailedInfo(String str);

    void showFinishInfo(boolean z);

    void showLoading();

    void showLog(String str);

    void showProgress(int i);

    void updateUpgradeInfoView();
}
