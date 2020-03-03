package com.mi.global.shop.loader;

import android.os.Handler;
import com.mi.global.shop.loader.BaseResult;

public interface ProgressNotifiable {
    void init(boolean z, boolean z2);

    void onError(boolean z, BaseResult.ResultStatus resultStatus, Handler.Callback callback);

    void onFinish();

    void startLoading(boolean z);

    void stopLoading(boolean z);
}
