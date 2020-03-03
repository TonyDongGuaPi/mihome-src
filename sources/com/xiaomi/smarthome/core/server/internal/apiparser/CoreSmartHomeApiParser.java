package com.xiaomi.smarthome.core.server.internal.apiparser;

import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.frame.ErrorCode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class CoreSmartHomeApiParser {

    /* renamed from: a  reason: collision with root package name */
    private static CoreSmartHomeApiParser f14096a;
    private static Object b = new Object();
    private volatile ExecutorService c = Executors.newCachedThreadPool();

    private CoreSmartHomeApiParser() {
    }

    public static CoreSmartHomeApiParser a() {
        if (f14096a == null) {
            synchronized (b) {
                if (f14096a == null) {
                    f14096a = new CoreSmartHomeApiParser();
                }
            }
        }
        return f14096a;
    }

    public <R> void a(final NetResult netResult, final CoreJsonParser<R> coreJsonParser, final CoreAsyncCallback<R, CoreError> coreAsyncCallback) {
        if (this.c != null && netResult != null) {
            this.c.submit(new Runnable() {
                public void run() {
                    try {
                        JSONObject jSONObject = new JSONObject(netResult.c);
                        int optInt = jSONObject.optInt("code");
                        if (ErrorCode.valueof(optInt) == ErrorCode.SUCCESS) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("result");
                            if (optJSONObject != null) {
                                jSONObject = optJSONObject;
                            }
                            if (coreJsonParser != null) {
                                Object a2 = coreJsonParser.a(jSONObject);
                                if (coreAsyncCallback != null) {
                                    coreAsyncCallback.b(a2);
                                }
                            } else if (coreAsyncCallback != null) {
                                coreAsyncCallback.b(null);
                            }
                        } else if (coreAsyncCallback != null) {
                            coreAsyncCallback.b(new CoreError(optInt, jSONObject.optString("message")));
                        }
                    } catch (Exception unused) {
                        if (coreAsyncCallback != null) {
                            coreAsyncCallback.b(new CoreError(ErrorCode.INVALID.getCode(), "wrong response format"));
                        }
                    }
                }
            });
        } else if (coreAsyncCallback != null) {
            coreAsyncCallback.b(new CoreError(ErrorCode.INVALID.getCode(), ""));
        }
    }
}
