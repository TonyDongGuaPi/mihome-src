package com.xiaomi.youpin.youpin_network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.xiaomi.youpin.network.bean.DownloadFileInfo;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.network.bean.UploadFileInfo;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import java.util.List;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public interface YouPinHttpsApiInterface {
    <T> Pair<T, NetError> a(@Nullable RequestParams requestParams, @NonNull NetRequest netRequest, UploadFileInfo uploadFileInfo, YouPinJsonParser<T> youPinJsonParser);

    @WorkerThread
    <T> Pair<T, NetError> a(@Nullable RequestParams requestParams, @NonNull NetRequest netRequest, @Nullable YouPinJsonParser<T> youPinJsonParser);

    void a(@NonNull NetRequest netRequest, @NonNull DownloadFileInfo downloadFileInfo, @Nullable RequestAsyncCallback<String, NetError> requestAsyncCallback);

    void a(@NonNull NetRequest netRequest, boolean z, @NonNull List<PipeRequest> list);

    <T> void a(@Nullable RequestParams requestParams, @NonNull NetRequest netRequest, UploadFileInfo uploadFileInfo, YouPinJsonParser<T> youPinJsonParser, @Nullable RequestAsyncCallback<T, NetError> requestAsyncCallback);

    <T> void a(@Nullable RequestParams requestParams, @NonNull NetRequest netRequest, boolean z, @Nullable YouPinJsonParser<T> youPinJsonParser, @Nullable RequestAsyncCallback<T, NetError> requestAsyncCallback);

    void a(@NonNull OkHttpClient okHttpClient, @NonNull Request request, @NonNull Callback callback, ISendRnRequest iSendRnRequest);

    OkHttpClient b();
}
