package com.xiaomi.smarthome.frame;

import com.xiaomi.smarthome.core.entity.net.NetResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class MiRechargeApiParser {

    /* renamed from: a  reason: collision with root package name */
    private static MiRechargeApiParser f15996a;
    private static Object b = new Object();
    private ExecutorService c = Executors.newCachedThreadPool();

    private MiRechargeApiParser() {
    }

    public static MiRechargeApiParser a() {
        if (f15996a == null) {
            synchronized (b) {
                if (f15996a == null) {
                    f15996a = new MiRechargeApiParser();
                }
            }
        }
        return f15996a;
    }

    public <R> void a(final NetResult netResult, final JsonParser<R> jsonParser, final AsyncCallback<R, Error> asyncCallback) {
        if (netResult != null) {
            this.c.submit(new Runnable() {
                public void run() {
                    try {
                        JSONObject jSONObject = new JSONObject(netResult.c);
                        Object obj = null;
                        if (jsonParser != null) {
                            obj = jsonParser.parse(jSONObject);
                        }
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(obj);
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
