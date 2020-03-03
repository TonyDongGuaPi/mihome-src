package com.xiaomi.youpin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.youpin.core.Error;
import com.xiaomi.youpin.core.JsonParser;
import com.xiaomi.youpin.core.RequestAsyncCallback;
import com.xiaomi.youpin.core.net.NetResult;

public class YouPinApiParser {

    /* renamed from: a  reason: collision with root package name */
    private static YouPinApiParser f23197a;
    private static Object b = new Object();

    private YouPinApiParser() {
    }

    public static YouPinApiParser a() {
        if (f23197a == null) {
            synchronized (b) {
                if (f23197a == null) {
                    f23197a = new YouPinApiParser();
                }
            }
        }
        return f23197a;
    }

    public <R> void a(NetResult netResult, JsonParser<R> jsonParser, RequestAsyncCallback<R, Error> requestAsyncCallback) {
        JsonElement jsonElement;
        if (netResult != null) {
            try {
                JsonObject asJsonObject = new com.google.gson.JsonParser().parse(netResult.d).getAsJsonObject();
                int asInt = asJsonObject.get("code").getAsInt();
                if (ErrorCode.valueof(asInt) == ErrorCode.SUCCESS) {
                    if (asJsonObject.has("data")) {
                        jsonElement = asJsonObject.get("data");
                    } else if (asJsonObject.has("result")) {
                        jsonElement = asJsonObject.get("result");
                    } else if (!netResult.b && requestAsyncCallback != null) {
                        requestAsyncCallback.b(new Error(ErrorCode.INVALID.getCode(), "wrong response format"));
                        return;
                    } else {
                        return;
                    }
                    if (jsonParser != null) {
                        R a2 = jsonParser.a(jsonElement);
                        if (!netResult.b) {
                            if (requestAsyncCallback != null) {
                                requestAsyncCallback.b(a2, netResult.c);
                            }
                        } else if (requestAsyncCallback != null) {
                            requestAsyncCallback.b(a2);
                        }
                    } else if (!netResult.b) {
                        if (requestAsyncCallback != null) {
                            requestAsyncCallback.b(null, netResult.c);
                        }
                    } else if (requestAsyncCallback != null) {
                        requestAsyncCallback.b(null);
                    }
                } else if (!netResult.b && requestAsyncCallback != null) {
                    requestAsyncCallback.b(new Error(asInt, asJsonObject.get("message").getAsString()));
                }
            } catch (Exception unused) {
                if (!netResult.b && requestAsyncCallback != null) {
                    requestAsyncCallback.b(new Error(ErrorCode.INVALID.getCode(), "wrong response format"));
                }
            }
        } else if (requestAsyncCallback != null) {
            requestAsyncCallback.b(new Error(ErrorCode.INVALID.getCode(), ""));
        }
    }
}
