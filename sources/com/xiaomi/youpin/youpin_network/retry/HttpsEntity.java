package com.xiaomi.youpin.youpin_network.retry;

import com.xiaomi.youpin.network.bean.DownloadFileInfo;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.network.bean.UploadFileInfo;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import java.util.List;

public class HttpsEntity<T> {

    /* renamed from: a  reason: collision with root package name */
    private RequestParams f23862a;
    private NetRequest b;
    private boolean c;
    private YouPinJsonParser<T> d;
    private RequestAsyncCallback<T, NetError> e;
    private UploadFileInfo f;
    private DownloadFileInfo g;
    private List<PipeRequest> h;

    public static <T> HttpsEntity<T> a(RequestParams requestParams, NetRequest netRequest, boolean z, YouPinJsonParser<T> youPinJsonParser, RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        HttpsEntity<T> httpsEntity = new HttpsEntity<>();
        httpsEntity.a(requestParams);
        httpsEntity.a(netRequest);
        httpsEntity.a(z);
        httpsEntity.a(requestAsyncCallback);
        httpsEntity.a(youPinJsonParser);
        return httpsEntity;
    }

    public static <T> HttpsEntity<T> a(RequestParams requestParams, NetRequest netRequest, UploadFileInfo uploadFileInfo, YouPinJsonParser<T> youPinJsonParser, RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        HttpsEntity<T> httpsEntity = new HttpsEntity<>();
        httpsEntity.a(requestParams);
        httpsEntity.a(netRequest);
        httpsEntity.a(uploadFileInfo);
        httpsEntity.a(requestAsyncCallback);
        httpsEntity.a(youPinJsonParser);
        return httpsEntity;
    }

    public static <T> HttpsEntity<T> a(NetRequest netRequest, DownloadFileInfo downloadFileInfo, RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        HttpsEntity<T> httpsEntity = new HttpsEntity<>();
        httpsEntity.a(netRequest);
        httpsEntity.a(downloadFileInfo);
        httpsEntity.a(requestAsyncCallback);
        return httpsEntity;
    }

    public static HttpsEntity a(NetRequest netRequest, boolean z, List<PipeRequest> list) {
        HttpsEntity httpsEntity = new HttpsEntity();
        httpsEntity.a(netRequest);
        httpsEntity.a(z);
        httpsEntity.a(list);
        return httpsEntity;
    }

    public NetRequest a() {
        return this.b;
    }

    public void a(NetRequest netRequest) {
        this.b = netRequest;
    }

    public boolean b() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public YouPinJsonParser<T> c() {
        return this.d;
    }

    public void a(YouPinJsonParser<T> youPinJsonParser) {
        this.d = youPinJsonParser;
    }

    public RequestAsyncCallback<T, NetError> d() {
        return this.e;
    }

    public void a(RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        this.e = requestAsyncCallback;
    }

    public UploadFileInfo e() {
        return this.f;
    }

    public void a(UploadFileInfo uploadFileInfo) {
        this.f = uploadFileInfo;
    }

    public DownloadFileInfo f() {
        return this.g;
    }

    public void a(DownloadFileInfo downloadFileInfo) {
        this.g = downloadFileInfo;
    }

    public List<PipeRequest> g() {
        return this.h;
    }

    public void a(List<PipeRequest> list) {
        this.h = list;
    }

    public boolean h() {
        return this.h != null && !this.h.isEmpty();
    }

    public RequestParams i() {
        return this.f23862a;
    }

    public void a(RequestParams requestParams) {
        this.f23862a = requestParams;
    }
}
