package com.loopj.android.http;

import com.taobao.weex.el.parse.Operators;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHttpResponseHandler extends TextHttpResponseHandler {
    private static final String LOG_TAG = "JsonHttpRH";
    /* access modifiers changed from: private */
    public boolean useRFC5179CompatibilityMode = true;

    public JsonHttpResponseHandler() {
        super("UTF-8");
    }

    public JsonHttpResponseHandler(String str) {
        super(str);
    }

    public JsonHttpResponseHandler(boolean z) {
        super("UTF-8");
        this.useRFC5179CompatibilityMode = z;
    }

    public JsonHttpResponseHandler(String str, boolean z) {
        super(str);
        this.useRFC5179CompatibilityMode = z;
    }

    public void onSuccess(int i, Header[] headerArr, JSONObject jSONObject) {
        AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
    }

    public void onSuccess(int i, Header[] headerArr, JSONArray jSONArray) {
        AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONObject jSONObject) {
        AsyncHttpClient.log.w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received", th);
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONArray jSONArray) {
        AsyncHttpClient.log.w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONArray) was not overriden, but callback was received", th);
    }

    public void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        AsyncHttpClient.log.w(LOG_TAG, "onFailure(int, Header[], String, Throwable) was not overriden, but callback was received", th);
    }

    public void onSuccess(int i, Header[] headerArr, String str) {
        AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], String) was not overriden, but callback was received");
    }

    public final void onSuccess(final int i, final Header[] headerArr, final byte[] bArr) {
        if (i != 204) {
            AnonymousClass1 r0 = new Runnable() {
                public void run() {
                    try {
                        final Object parseResponse = JsonHttpResponseHandler.this.parseResponse(bArr);
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                if (!JsonHttpResponseHandler.this.useRFC5179CompatibilityMode && parseResponse == null) {
                                    JsonHttpResponseHandler.this.onSuccess(i, headerArr, (String) null);
                                } else if (parseResponse instanceof JSONObject) {
                                    JsonHttpResponseHandler.this.onSuccess(i, headerArr, (JSONObject) parseResponse);
                                } else if (parseResponse instanceof JSONArray) {
                                    JsonHttpResponseHandler.this.onSuccess(i, headerArr, (JSONArray) parseResponse);
                                } else if (!(parseResponse instanceof String)) {
                                    JsonHttpResponseHandler jsonHttpResponseHandler = JsonHttpResponseHandler.this;
                                    int i = i;
                                    Header[] headerArr = headerArr;
                                    jsonHttpResponseHandler.onFailure(i, headerArr, (Throwable) new JSONException("Unexpected response type " + parseResponse.getClass().getName()), (JSONObject) null);
                                } else if (JsonHttpResponseHandler.this.useRFC5179CompatibilityMode) {
                                    JsonHttpResponseHandler.this.onFailure(i, headerArr, (String) parseResponse, (Throwable) new JSONException("Response cannot be parsed as JSON data"));
                                } else {
                                    JsonHttpResponseHandler.this.onSuccess(i, headerArr, (String) parseResponse);
                                }
                            }
                        });
                    } catch (JSONException e) {
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                JsonHttpResponseHandler.this.onFailure(i, headerArr, (Throwable) e, (JSONObject) null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode() || getUsePoolThread()) {
                r0.run();
            } else {
                new Thread(r0).start();
            }
        } else {
            onSuccess(i, headerArr, new JSONObject());
        }
    }

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        if (bArr != null) {
            final byte[] bArr2 = bArr;
            final int i2 = i;
            final Header[] headerArr2 = headerArr;
            final Throwable th2 = th;
            AnonymousClass2 r0 = new Runnable() {
                public void run() {
                    try {
                        final Object parseResponse = JsonHttpResponseHandler.this.parseResponse(bArr2);
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                if (!JsonHttpResponseHandler.this.useRFC5179CompatibilityMode && parseResponse == null) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, (String) null, th2);
                                } else if (parseResponse instanceof JSONObject) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, th2, (JSONObject) parseResponse);
                                } else if (parseResponse instanceof JSONArray) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, th2, (JSONArray) parseResponse);
                                } else if (parseResponse instanceof String) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, (String) parseResponse, th2);
                                } else {
                                    JsonHttpResponseHandler jsonHttpResponseHandler = JsonHttpResponseHandler.this;
                                    int i = i2;
                                    Header[] headerArr = headerArr2;
                                    jsonHttpResponseHandler.onFailure(i, headerArr, (Throwable) new JSONException("Unexpected response type " + parseResponse.getClass().getName()), (JSONObject) null);
                                }
                            }
                        });
                    } catch (JSONException e) {
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                JsonHttpResponseHandler.this.onFailure(i2, headerArr2, (Throwable) e, (JSONObject) null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode() || getUsePoolThread()) {
                r0.run();
            } else {
                new Thread(r0).start();
            }
        } else {
            AsyncHttpClient.log.v(LOG_TAG, "response body is null, calling onFailure(Throwable, JSONObject)");
            onFailure(i, headerArr, th, (JSONObject) null);
        }
    }

    /* access modifiers changed from: protected */
    public Object parseResponse(byte[] bArr) throws JSONException {
        Object obj = null;
        if (bArr == null) {
            return null;
        }
        String responseString = getResponseString(bArr, getCharset());
        if (responseString != null) {
            responseString = responseString.trim();
            if (this.useRFC5179CompatibilityMode) {
                if (responseString.startsWith(Operators.BLOCK_START_STR) || responseString.startsWith(Operators.ARRAY_START_STR)) {
                    obj = new JSONTokener(responseString).nextValue();
                }
            } else if ((responseString.startsWith(Operators.BLOCK_START_STR) && responseString.endsWith("}")) || (responseString.startsWith(Operators.ARRAY_START_STR) && responseString.endsWith(Operators.ARRAY_END_STR))) {
                obj = new JSONTokener(responseString).nextValue();
            } else if (responseString.startsWith("\"") && responseString.endsWith("\"")) {
                obj = responseString.substring(1, responseString.length() - 1);
            }
        }
        return obj == null ? responseString : obj;
    }

    public boolean isUseRFC5179CompatibilityMode() {
        return this.useRFC5179CompatibilityMode;
    }

    public void setUseRFC5179CompatibilityMode(boolean z) {
        this.useRFC5179CompatibilityMode = z;
    }
}
