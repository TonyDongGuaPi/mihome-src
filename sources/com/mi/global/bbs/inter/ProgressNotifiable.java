package com.mi.global.bbs.inter;

import android.os.Handler;
import com.mi.global.bbs.inter.BaseResult;

public interface ProgressNotifiable {
    void init(boolean z, boolean z2);

    void onError(boolean z, BaseResult.ResultStatus resultStatus, Handler.Callback callback);

    void onFinish();

    void startLoading(boolean z);

    void stopLoading(boolean z);
}
