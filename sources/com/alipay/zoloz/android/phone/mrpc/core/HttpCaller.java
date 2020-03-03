package com.alipay.zoloz.android.phone.mrpc.core;

import android.util.Log;
import com.alipay.zoloz.mobile.a.a.b;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class HttpCaller extends AbstractRpcCaller {

    /* renamed from: a  reason: collision with root package name */
    private Config f1183a;

    private int a(int i) {
        switch (i) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 15;
            case 9:
                return 16;
            default:
                return i;
        }
    }

    public HttpCaller(Config config, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.f1183a = config;
    }

    public Object call() {
        HttpUrlRequest httpUrlRequest = new HttpUrlRequest(this.f1183a.getUrl());
        httpUrlRequest.setReqData(this.mReqData);
        httpUrlRequest.setContentType(this.mContentType);
        httpUrlRequest.setResetCookie(this.mResetCookie);
        httpUrlRequest.addTags("id", String.valueOf(this.mId));
        httpUrlRequest.addTags("operationType", this.mOperationType);
        httpUrlRequest.addTags("gzip", String.valueOf(this.f1183a.isGzip()));
        a(httpUrlRequest);
        Log.i("HttpCaller", "threadid = " + Thread.currentThread().getId() + "; " + httpUrlRequest.toString());
        try {
            Response response = a().execute(httpUrlRequest).get();
            if (response != null) {
                return response.getResData();
            }
            throw new b((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new b(13, "", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new b(9, "", e2);
            }
            HttpException httpException = (HttpException) cause;
            throw new b(Integer.valueOf(a(httpException.getCode())), httpException.getMsg());
        } catch (CancellationException e3) {
            throw new b(13, "", e3);
        }
    }

    private void a(HttpUrlRequest httpUrlRequest) {
        httpUrlRequest.addHeader(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> headers = this.f1183a.getRpcParams().getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Header addHeader : headers) {
                httpUrlRequest.addHeader(addHeader);
            }
        }
    }

    private Transport a() {
        return this.f1183a.getTransport();
    }
}
