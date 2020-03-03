package com.miui.tsmclient.model;

import android.content.Context;
import com.miui.tsmclient.common.mvp.OnModelChangedListener;
import com.miui.tsmclient.entity.UserExceptionLogInfo;
import com.miui.tsmclient.model.mitsm.MiTsmErrorCode;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.LogUtils;
import java.io.IOException;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UploadUserExceptionLogModel extends BaseModel {
    public static UploadUserExceptionLogModel create(Context context) {
        UploadUserExceptionLogModel uploadUserExceptionLogModel = new UploadUserExceptionLogModel();
        uploadUserExceptionLogModel.init(context, (OnModelChangedListener) null);
        return uploadUserExceptionLogModel;
    }

    public void uploadUserExceptionLog(final UserExceptionLogInfo userExceptionLogInfo) {
        if (userExceptionLogInfo == null) {
            LogUtils.e("uploadUserExceptionLog failed. userLogInfo is null");
        } else {
            Observable.fromCallable(new Callable<BaseResponse>() {
                public BaseResponse call() throws IOException, SeiTSMApiException {
                    TsmRpcModels.CommonResponse uploadExceptionUserLog = new SeiTsmAuthManager().uploadExceptionUserLog(UploadUserExceptionLogModel.this.getContext(), userExceptionLogInfo);
                    BaseResponse baseResponse = new BaseResponse(-1, new Object[0]);
                    if (uploadExceptionUserLog != null) {
                        baseResponse.mResultCode = MiTsmErrorCode.format(uploadExceptionUserLog.getResult());
                        baseResponse.mMsg = uploadExceptionUserLog.getErrorDesc();
                    }
                    return baseResponse;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BaseResponse>() {
                public void onCompleted() {
                    LogUtils.d("uploadUserExceptionLog onCompleted called.");
                }

                public void onError(Throwable th) {
                    LogUtils.e("uploadUserExceptionLog failed with an io exception.", th);
                }

                public void onNext(BaseResponse baseResponse) {
                    LogUtils.d("uploadUserExceptionLog errorCode:" + baseResponse.mResultCode + ", errorMsg:" + baseResponse.mMsg);
                }
            });
        }
    }
}
