package com.xiaomi.smarthome.international;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONObject;

public class RxApi {
    public static <T> Observable<T> a(NetRequest netRequest, JsonParser<T> jsonParser) {
        return Observable.create(new ObservableOnSubscribe(jsonParser) {
            private final /* synthetic */ JsonParser f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                RxApi.a(NetRequest.this, this.f$1, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(NetRequest netRequest, JsonParser jsonParser, final ObservableEmitter observableEmitter) throws Exception {
        if (!observableEmitter.isDisposed()) {
            CoreApi.a().a((Context) null, netRequest, jsonParser, Crypto.RC4, new AsyncCallback<T, Error>() {
                public void onSuccess(T t) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onNext(t);
                        observableEmitter.onComplete();
                    }
                }

                public void onFailure(Error error) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(new ApiErrorWrapper(error));
                    }
                }
            });
        }
    }

    public static <T> Observable<T> a(Request request, JsonParser<T> jsonParser) {
        return Observable.create(new ObservableOnSubscribe(jsonParser) {
            private final /* synthetic */ JsonParser f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                RxApi.a(Request.this, this.f$1, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Request request, final JsonParser jsonParser, final ObservableEmitter observableEmitter) throws Exception {
        if (!observableEmitter.isDisposed()) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processResponse(Response response) {
                    if (!observableEmitter.isDisposed()) {
                        try {
                            observableEmitter.onNext(jsonParser.parse(new JSONObject(response.body().string())));
                            observableEmitter.onComplete();
                        } catch (Exception e) {
                            observableEmitter.onError(e);
                            e.printStackTrace();
                        }
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    observableEmitter.onError(iOException);
                }
            });
        }
    }

    public static <T> Observable<T> a(String str, JsonParser<T> jsonParser) {
        return a(new Request.Builder().a("GET").b(str).a(), jsonParser);
    }
}
