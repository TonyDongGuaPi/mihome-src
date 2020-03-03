package com.xiaomi.smarthome.frame;

import com.xiaomi.smarthome.core.entity.net.NetResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class RouterApiParser {

    /* renamed from: a  reason: collision with root package name */
    private static RouterApiParser f15998a;
    private static Object b = new Object();
    private ExecutorService c = Executors.newCachedThreadPool();

    private RouterApiParser() {
    }

    public static RouterApiParser a() {
        if (f15998a == null) {
            synchronized (b) {
                if (f15998a == null) {
                    f15998a = new RouterApiParser();
                }
            }
        }
        return f15998a;
    }

    public <R> void a(final NetResult netResult, final JsonParser<R> jsonParser, final AsyncCallback<R, Error> asyncCallback) {
        if (netResult != null) {
            this.c.submit(new Runnable() {
                public void run() {
                    try {
                        JSONObject jSONObject = new JSONObject(netResult.c);
                        int optInt = jSONObject.optInt("code");
                        if (optInt == 0) {
                            Object obj = null;
                            if (jsonParser != null) {
                                obj = jsonParser.parse(jSONObject);
                            }
                            if (asyncCallback != null) {
                                asyncCallback.sendSuccessMessage(obj);
                            }
                        } else if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(new Error(optInt, ""));
                        }
                    } catch (Exception unused) {
                        if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), "wrong response format"));
                        }
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
        }
    }
}
