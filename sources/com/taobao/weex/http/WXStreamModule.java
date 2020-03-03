package com.taobao.weex.http;

import android.net.Uri;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.common.WXResponse;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.http.Options;
import com.taobao.weex.utils.WXLogUtils;
import com.xiaomi.smarthome.download.Downloads;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.client.methods.HttpPut;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXStreamModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    static final Pattern CHARSET_PATTERN = Pattern.compile("charset=([a-z0-9-]+)");
    public static final String STATUS = "status";
    public static final String STATUS_TEXT = "statusText";
    final IWXHttpAdapter mAdapter;

    private interface ResponseCallback {
        void onResponse(WXResponse wXResponse, Map<String, String> map);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5760341154964473216L, "com/taobao/weex/http/WXStreamModule", 135);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[134] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WXStreamModule() {
        this((IWXHttpAdapter) null);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public WXStreamModule(IWXHttpAdapter iWXHttpAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAdapter = iWXHttpAdapter;
        $jacocoInit[1] = true;
    }

    @JSMethod(uiThread = false)
    @Deprecated
    public void sendHttp(JSONObject jSONObject, final String str) {
        boolean[] $jacocoInit = $jacocoInit();
        String string = jSONObject.getString("method");
        $jacocoInit[2] = true;
        String string2 = jSONObject.getString("url");
        $jacocoInit[3] = true;
        JSONObject jSONObject2 = jSONObject.getJSONObject("header");
        $jacocoInit[4] = true;
        String string3 = jSONObject.getString("body");
        $jacocoInit[5] = true;
        int intValue = jSONObject.getIntValue("timeout");
        $jacocoInit[6] = true;
        if (string == null) {
            $jacocoInit[7] = true;
        } else {
            string = string.toUpperCase();
            $jacocoInit[8] = true;
        }
        Options.Builder builder = new Options.Builder();
        $jacocoInit[9] = true;
        if ("GET".equals(string)) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            if ("POST".equals(string)) {
                $jacocoInit[12] = true;
            } else {
                $jacocoInit[13] = true;
                if (HttpPut.METHOD_NAME.equals(string)) {
                    $jacocoInit[14] = true;
                } else {
                    $jacocoInit[15] = true;
                    if ("DELETE".equals(string)) {
                        $jacocoInit[16] = true;
                    } else {
                        $jacocoInit[17] = true;
                        if ("HEAD".equals(string)) {
                            $jacocoInit[18] = true;
                        } else {
                            $jacocoInit[19] = true;
                            if ("PATCH".equals(string)) {
                                $jacocoInit[20] = true;
                            } else {
                                string = "GET";
                                $jacocoInit[21] = true;
                                Options.Builder method = builder.setMethod(string);
                                $jacocoInit[23] = true;
                                Options.Builder url = method.setUrl(string2);
                                $jacocoInit[24] = true;
                                Options.Builder body = url.setBody(string3);
                                $jacocoInit[25] = true;
                                Options.Builder timeout = body.setTimeout(intValue);
                                $jacocoInit[26] = true;
                                extractHeaders(jSONObject2, timeout);
                                $jacocoInit[27] = true;
                                Options createOptions = timeout.createOptions();
                                AnonymousClass1 r5 = new ResponseCallback(this) {
                                    private static transient /* synthetic */ boolean[] $jacocoData;
                                    final /* synthetic */ WXStreamModule this$0;

                                    private static /* synthetic */ boolean[] $jacocoInit() {
                                        boolean[] zArr = $jacocoData;
                                        if (zArr != null) {
                                            return zArr;
                                        }
                                        boolean[] a2 = Offline.a(1120018063071113402L, "com/taobao/weex/http/WXStreamModule$1", 13);
                                        $jacocoData = a2;
                                        return a2;
                                    }

                                    {
                                        boolean[] $jacocoInit = $jacocoInit();
                                        this.this$0 = r2;
                                        $jacocoInit[0] = true;
                                    }

                                    public void onResponse(WXResponse wXResponse, Map<String, String> map) {
                                        String str;
                                        String str2;
                                        boolean[] $jacocoInit = $jacocoInit();
                                        if (str == null) {
                                            $jacocoInit[1] = true;
                                        } else if (this.this$0.mWXSDKInstance == null) {
                                            $jacocoInit[2] = true;
                                        } else {
                                            $jacocoInit[3] = true;
                                            WXBridgeManager instance = WXBridgeManager.getInstance();
                                            String instanceId = this.this$0.mWXSDKInstance.getInstanceId();
                                            String str3 = str;
                                            if (wXResponse == null) {
                                                $jacocoInit[4] = true;
                                            } else if (wXResponse.originalData == null) {
                                                $jacocoInit[5] = true;
                                            } else {
                                                byte[] bArr = wXResponse.originalData;
                                                if (map != null) {
                                                    $jacocoInit[7] = true;
                                                    str2 = WXStreamModule.getHeader(map, "Content-Type");
                                                    $jacocoInit[8] = true;
                                                } else {
                                                    str2 = "";
                                                    $jacocoInit[9] = true;
                                                }
                                                str = WXStreamModule.readAsString(bArr, str2);
                                                $jacocoInit[10] = true;
                                                instance.callback(instanceId, str3, str);
                                                $jacocoInit[11] = true;
                                            }
                                            str = "{}";
                                            $jacocoInit[6] = true;
                                            instance.callback(instanceId, str3, str);
                                            $jacocoInit[11] = true;
                                        }
                                        $jacocoInit[12] = true;
                                    }
                                };
                                WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
                                $jacocoInit[28] = true;
                                String instanceId = wXSDKInstance.getInstanceId();
                                String bundleUrl = this.mWXSDKInstance.getBundleUrl();
                                $jacocoInit[29] = true;
                                sendRequest(createOptions, r5, (JSCallback) null, instanceId, bundleUrl);
                                $jacocoInit[30] = true;
                            }
                        }
                    }
                }
            }
        }
        $jacocoInit[22] = true;
        Options.Builder method2 = builder.setMethod(string);
        $jacocoInit[23] = true;
        Options.Builder url2 = method2.setUrl(string2);
        $jacocoInit[24] = true;
        Options.Builder body2 = url2.setBody(string3);
        $jacocoInit[25] = true;
        Options.Builder timeout2 = body2.setTimeout(intValue);
        $jacocoInit[26] = true;
        extractHeaders(jSONObject2, timeout2);
        $jacocoInit[27] = true;
        Options createOptions2 = timeout2.createOptions();
        AnonymousClass1 r52 = new ResponseCallback(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXStreamModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(1120018063071113402L, "com/taobao/weex/http/WXStreamModule$1", 13);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onResponse(WXResponse wXResponse, Map<String, String> map) {
                String str;
                String str2;
                boolean[] $jacocoInit = $jacocoInit();
                if (str == null) {
                    $jacocoInit[1] = true;
                } else if (this.this$0.mWXSDKInstance == null) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    WXBridgeManager instance = WXBridgeManager.getInstance();
                    String instanceId = this.this$0.mWXSDKInstance.getInstanceId();
                    String str3 = str;
                    if (wXResponse == null) {
                        $jacocoInit[4] = true;
                    } else if (wXResponse.originalData == null) {
                        $jacocoInit[5] = true;
                    } else {
                        byte[] bArr = wXResponse.originalData;
                        if (map != null) {
                            $jacocoInit[7] = true;
                            str2 = WXStreamModule.getHeader(map, "Content-Type");
                            $jacocoInit[8] = true;
                        } else {
                            str2 = "";
                            $jacocoInit[9] = true;
                        }
                        str = WXStreamModule.readAsString(bArr, str2);
                        $jacocoInit[10] = true;
                        instance.callback(instanceId, str3, str);
                        $jacocoInit[11] = true;
                    }
                    str = "{}";
                    $jacocoInit[6] = true;
                    instance.callback(instanceId, str3, str);
                    $jacocoInit[11] = true;
                }
                $jacocoInit[12] = true;
            }
        };
        WXSDKInstance wXSDKInstance2 = this.mWXSDKInstance;
        $jacocoInit[28] = true;
        String instanceId2 = wXSDKInstance2.getInstanceId();
        String bundleUrl2 = this.mWXSDKInstance.getBundleUrl();
        $jacocoInit[29] = true;
        sendRequest(createOptions2, r52, (JSCallback) null, instanceId2, bundleUrl2);
        $jacocoInit[30] = true;
    }

    @JSMethod(uiThread = false)
    public void fetch(JSONObject jSONObject, JSCallback jSCallback, JSCallback jSCallback2) {
        boolean[] $jacocoInit = $jacocoInit();
        fetch(jSONObject, jSCallback, jSCallback2, this.mWXSDKInstance.getInstanceId(), this.mWXSDKInstance.getBundleUrl());
        $jacocoInit[31] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fetch(com.alibaba.fastjson.JSONObject r11, final com.taobao.weex.bridge.JSCallback r12, com.taobao.weex.bridge.JSCallback r13, java.lang.String r14, java.lang.String r15) {
        /*
            r10 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 0
            r2 = 1
            if (r11 != 0) goto L_0x000d
            r3 = 32
            r0[r3] = r2
            goto L_0x0019
        L_0x000d:
            java.lang.String r3 = "url"
            java.lang.String r3 = r11.getString(r3)
            if (r3 != 0) goto L_0x001f
            r3 = 33
            r0[r3] = r2
        L_0x0019:
            r3 = 34
            r0[r3] = r2
            r3 = 1
            goto L_0x0024
        L_0x001f:
            r3 = 35
            r0[r3] = r2
            r3 = 0
        L_0x0024:
            if (r3 == 0) goto L_0x005e
            if (r12 != 0) goto L_0x002d
            r11 = 36
            r0[r11] = r2
            goto L_0x0059
        L_0x002d:
            r11 = 37
            r0[r11] = r2
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            r13 = 38
            r0[r13] = r2
            java.lang.String r13 = "ok"
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r1)
            r11.put(r13, r14)
            r13 = 39
            r0[r13] = r2
            java.lang.String r13 = "statusText"
            java.lang.String r14 = "ERR_INVALID_REQUEST"
            r11.put(r13, r14)
            r13 = 40
            r0[r13] = r2
            r12.invoke(r11)
            r11 = 41
            r0[r11] = r2
        L_0x0059:
            r11 = 42
            r0[r11] = r2
            return
        L_0x005e:
            java.lang.String r1 = "method"
            java.lang.String r1 = r11.getString(r1)
            r3 = 43
            r0[r3] = r2
            java.lang.String r3 = "url"
            java.lang.String r3 = r11.getString(r3)
            r4 = 44
            r0[r4] = r2
            java.lang.String r4 = "headers"
            com.alibaba.fastjson.JSONObject r4 = r11.getJSONObject(r4)
            r5 = 45
            r0[r5] = r2
            java.lang.String r5 = "body"
            java.lang.String r5 = r11.getString(r5)
            r6 = 46
            r0[r6] = r2
            java.lang.String r6 = "type"
            java.lang.String r6 = r11.getString(r6)
            r7 = 47
            r0[r7] = r2
            java.lang.String r7 = "timeout"
            int r11 = r11.getIntValue(r7)
            r7 = 48
            r0[r7] = r2
            com.taobao.weex.WXSDKManager r7 = com.taobao.weex.WXSDKManager.getInstance()
            com.taobao.weex.WXSDKInstance r7 = r7.getSDKInstance(r14)
            if (r7 != 0) goto L_0x00a9
            r7 = 49
            r0[r7] = r2
            goto L_0x00d8
        L_0x00a9:
            r8 = 50
            r0[r8] = r2
            com.taobao.weex.WXSDKInstance$StreamNetworkHandler r8 = r7.getStreamNetworkHandler()
            if (r8 != 0) goto L_0x00b8
            r7 = 51
            r0[r7] = r2
            goto L_0x00d8
        L_0x00b8:
            r8 = 52
            r0[r8] = r2
            com.taobao.weex.WXSDKInstance$StreamNetworkHandler r7 = r7.getStreamNetworkHandler()
            java.lang.String r7 = r7.fetchLocal(r3)
            r8 = 53
            r0[r8] = r2
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 == 0) goto L_0x00d3
            r7 = 54
            r0[r7] = r2
            goto L_0x00d8
        L_0x00d3:
            r3 = 55
            r0[r3] = r2
            r3 = r7
        L_0x00d8:
            if (r1 != 0) goto L_0x00df
            r7 = 56
            r0[r7] = r2
            goto L_0x00e7
        L_0x00df:
            java.lang.String r1 = r1.toUpperCase()
            r7 = 57
            r0[r7] = r2
        L_0x00e7:
            com.taobao.weex.http.Options$Builder r7 = new com.taobao.weex.http.Options$Builder
            r7.<init>()
            java.lang.String r8 = "GET"
            r9 = 58
            r0[r9] = r2
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x00fd
            r8 = 59
            r0[r8] = r2
            goto L_0x0151
        L_0x00fd:
            java.lang.String r8 = "POST"
            r9 = 60
            r0[r9] = r2
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x010e
            r8 = 61
            r0[r8] = r2
            goto L_0x0151
        L_0x010e:
            java.lang.String r8 = "PUT"
            r9 = 62
            r0[r9] = r2
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x011f
            r8 = 63
            r0[r8] = r2
            goto L_0x0151
        L_0x011f:
            java.lang.String r8 = "DELETE"
            r9 = 64
            r0[r9] = r2
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0130
            r8 = 65
            r0[r8] = r2
            goto L_0x0151
        L_0x0130:
            java.lang.String r8 = "HEAD"
            r9 = 66
            r0[r9] = r2
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0141
            r8 = 67
            r0[r8] = r2
            goto L_0x0151
        L_0x0141:
            java.lang.String r8 = "PATCH"
            r9 = 68
            r0[r9] = r2
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0156
            r8 = 69
            r0[r8] = r2
        L_0x0151:
            r8 = 71
            r0[r8] = r2
            goto L_0x015c
        L_0x0156:
            java.lang.String r1 = "GET"
            r8 = 70
            r0[r8] = r2
        L_0x015c:
            com.taobao.weex.http.Options$Builder r1 = r7.setMethod(r1)
            r7 = 72
            r0[r7] = r2
            com.taobao.weex.http.Options$Builder r1 = r1.setUrl(r3)
            r3 = 73
            r0[r3] = r2
            com.taobao.weex.http.Options$Builder r1 = r1.setBody(r5)
            r3 = 74
            r0[r3] = r2
            com.taobao.weex.http.Options$Builder r1 = r1.setType((java.lang.String) r6)
            r3 = 75
            r0[r3] = r2
            com.taobao.weex.http.Options$Builder r11 = r1.setTimeout(r11)
            r1 = 76
            r0[r1] = r2
            r10.extractHeaders(r4, r11)
            r1 = 77
            r0[r1] = r2
            com.taobao.weex.http.Options r4 = r11.createOptions()
            r11 = 78
            r0[r11] = r2
            com.taobao.weex.http.WXStreamModule$2 r5 = new com.taobao.weex.http.WXStreamModule$2
            r5.<init>(r10, r12, r4)
            r3 = r10
            r6 = r13
            r7 = r14
            r8 = r15
            r3.sendRequest(r4, r5, r6, r7, r8)
            r11 = 79
            r0[r11] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.http.WXStreamModule.fetch(com.alibaba.fastjson.JSONObject, com.taobao.weex.bridge.JSCallback, com.taobao.weex.bridge.JSCallback, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public Object parseData(String str, Options.Type type) throws JSONException {
        boolean[] $jacocoInit = $jacocoInit();
        if (type == Options.Type.json) {
            $jacocoInit[80] = true;
            Object parse = JSONObject.parse(str);
            $jacocoInit[81] = true;
            return parse;
        } else if (type == Options.Type.jsonp) {
            $jacocoInit[82] = true;
            if (str == null) {
                $jacocoInit[83] = true;
            } else if (str.isEmpty()) {
                $jacocoInit[84] = true;
            } else {
                int indexOf = str.indexOf(Operators.BRACKET_START_STR) + 1;
                $jacocoInit[86] = true;
                int lastIndexOf = str.lastIndexOf(Operators.BRACKET_END_STR);
                if (indexOf == 0) {
                    $jacocoInit[87] = true;
                } else if (indexOf >= lastIndexOf) {
                    $jacocoInit[88] = true;
                } else if (lastIndexOf <= 0) {
                    $jacocoInit[89] = true;
                } else {
                    String substring = str.substring(indexOf, lastIndexOf);
                    $jacocoInit[91] = true;
                    Object parse2 = JSONObject.parse(substring);
                    $jacocoInit[92] = true;
                    return parse2;
                }
                JSONObject jSONObject = new JSONObject();
                $jacocoInit[90] = true;
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            $jacocoInit[85] = true;
            return jSONObject2;
        } else {
            $jacocoInit[93] = true;
            return str;
        }
    }

    static String getHeader(Map<String, String> map, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[94] = true;
        } else if (str == null) {
            $jacocoInit[95] = true;
        } else if (map.containsKey(str)) {
            $jacocoInit[97] = true;
            String str2 = map.get(str);
            $jacocoInit[98] = true;
            return str2;
        } else {
            String str3 = map.get(str.toLowerCase());
            $jacocoInit[99] = true;
            return str3;
        }
        $jacocoInit[96] = true;
        return null;
    }

    static String readAsString(byte[] bArr, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        String str2 = "utf-8";
        if (str == null) {
            $jacocoInit[100] = true;
        } else {
            $jacocoInit[101] = true;
            Matcher matcher = CHARSET_PATTERN.matcher(str.toLowerCase());
            $jacocoInit[102] = true;
            if (!matcher.find()) {
                $jacocoInit[103] = true;
            } else {
                $jacocoInit[104] = true;
                str2 = matcher.group(1);
                try {
                    $jacocoInit[105] = true;
                } catch (UnsupportedEncodingException e) {
                    $jacocoInit[107] = true;
                    WXLogUtils.e("", (Throwable) e);
                    $jacocoInit[108] = true;
                    String str3 = new String(bArr);
                    $jacocoInit[109] = true;
                    return str3;
                }
            }
        }
        String str4 = new String(bArr, str2);
        $jacocoInit[106] = true;
        return str4;
    }

    private void extractHeaders(JSONObject jSONObject, Options.Builder builder) {
        boolean[] $jacocoInit = $jacocoInit();
        String assembleUserAgent = WXHttpUtil.assembleUserAgent(WXEnvironment.getApplication(), WXEnvironment.getConfig());
        if (jSONObject == null) {
            $jacocoInit[110] = true;
        } else {
            $jacocoInit[111] = true;
            $jacocoInit[112] = true;
            for (String next : jSONObject.keySet()) {
                $jacocoInit[114] = true;
                if (next.equals(WXHttpUtil.KEY_USER_AGENT)) {
                    $jacocoInit[115] = true;
                    assembleUserAgent = jSONObject.getString(next);
                    $jacocoInit[116] = true;
                } else {
                    builder.putHeader(next, jSONObject.getString(next));
                    $jacocoInit[117] = true;
                }
            }
            $jacocoInit[113] = true;
        }
        builder.putHeader(WXHttpUtil.KEY_USER_AGENT, assembleUserAgent);
        $jacocoInit[118] = true;
    }

    private void sendRequest(Options options, ResponseCallback responseCallback, JSCallback jSCallback, String str, String str2) {
        IWXHttpAdapter iWXHttpAdapter;
        boolean[] $jacocoInit = $jacocoInit();
        WXRequest wXRequest = new WXRequest();
        $jacocoInit[119] = true;
        wXRequest.method = options.getMethod();
        $jacocoInit[120] = true;
        wXRequest.url = WXSDKManager.getInstance().getURIAdapter().rewrite(str2, "request", Uri.parse(options.getUrl())).toString();
        $jacocoInit[121] = true;
        wXRequest.body = options.getBody();
        $jacocoInit[122] = true;
        wXRequest.timeoutMs = options.getTimeout();
        wXRequest.instanceId = str;
        $jacocoInit[123] = true;
        if (options.getHeaders() == null) {
            $jacocoInit[124] = true;
        } else if (wXRequest.paramMap == null) {
            $jacocoInit[125] = true;
            wXRequest.paramMap = options.getHeaders();
            $jacocoInit[126] = true;
        } else {
            wXRequest.paramMap.putAll(options.getHeaders());
            $jacocoInit[127] = true;
        }
        if (this.mAdapter == null) {
            iWXHttpAdapter = WXSDKManager.getInstance().getIWXHttpAdapter();
            $jacocoInit[128] = true;
        } else {
            iWXHttpAdapter = this.mAdapter;
            $jacocoInit[129] = true;
        }
        if (iWXHttpAdapter != null) {
            $jacocoInit[130] = true;
            iWXHttpAdapter.sendRequest(wXRequest, new StreamHttpListener(responseCallback, jSCallback, (AnonymousClass1) null));
            $jacocoInit[131] = true;
        } else {
            WXLogUtils.e("WXStreamModule", "No HttpAdapter found,request failed.");
            $jacocoInit[132] = true;
        }
        $jacocoInit[133] = true;
    }

    private static class StreamHttpListener implements IWXHttpAdapter.OnHttpListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private ResponseCallback mCallback;
        private JSCallback mProgressCallback;
        private Map<String, String> mRespHeaders;
        private Map<String, Object> mResponse;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-491164645741986836L, "com/taobao/weex/http/WXStreamModule$StreamHttpListener", 43);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ StreamHttpListener(ResponseCallback responseCallback, JSCallback jSCallback, AnonymousClass1 r3) {
            this(responseCallback, jSCallback);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[42] = true;
        }

        private StreamHttpListener(ResponseCallback responseCallback, JSCallback jSCallback) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.mResponse = new HashMap();
            this.mCallback = responseCallback;
            this.mProgressCallback = jSCallback;
            $jacocoInit[1] = true;
        }

        public void onHttpStart() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mProgressCallback == null) {
                $jacocoInit[2] = true;
            } else {
                $jacocoInit[3] = true;
                this.mResponse.put("readyState", 1);
                $jacocoInit[4] = true;
                this.mResponse.put("length", 0);
                $jacocoInit[5] = true;
                this.mProgressCallback.invokeAndKeepAlive(new HashMap(this.mResponse));
                $jacocoInit[6] = true;
            }
            $jacocoInit[7] = true;
        }

        public void onHttpUploadProgress(int i) {
            $jacocoInit()[8] = true;
        }

        public void onHeadersReceived(int i, Map<String, List<String>> map) {
            String str;
            boolean[] $jacocoInit = $jacocoInit();
            this.mResponse.put("readyState", 2);
            $jacocoInit[9] = true;
            this.mResponse.put("status", Integer.valueOf(i));
            $jacocoInit[10] = true;
            HashMap hashMap = new HashMap();
            if (map == null) {
                $jacocoInit[11] = true;
            } else {
                $jacocoInit[12] = true;
                $jacocoInit[13] = true;
                for (Map.Entry next : map.entrySet()) {
                    $jacocoInit[15] = true;
                    $jacocoInit[16] = true;
                    if (((List) next.getValue()).size() <= 0) {
                        $jacocoInit[17] = true;
                    } else {
                        $jacocoInit[18] = true;
                        if (next.getKey() == null) {
                            str = JSMethod.NOT_SET;
                            $jacocoInit[19] = true;
                        } else {
                            str = (String) next.getKey();
                            $jacocoInit[20] = true;
                        }
                        hashMap.put(str, ((List) next.getValue()).get(0));
                        $jacocoInit[21] = true;
                    }
                    $jacocoInit[22] = true;
                }
                $jacocoInit[14] = true;
            }
            this.mResponse.put(Downloads.RequestHeaders.e, hashMap);
            this.mRespHeaders = hashMap;
            if (this.mProgressCallback == null) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                this.mProgressCallback.invokeAndKeepAlive(new HashMap(this.mResponse));
                $jacocoInit[25] = true;
            }
            $jacocoInit[26] = true;
        }

        public void onHttpResponseProgress(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mResponse.put("length", Integer.valueOf(i));
            if (this.mProgressCallback == null) {
                $jacocoInit[27] = true;
            } else {
                $jacocoInit[28] = true;
                this.mProgressCallback.invokeAndKeepAlive(new HashMap(this.mResponse));
                $jacocoInit[29] = true;
            }
            $jacocoInit[30] = true;
        }

        public void onHttpFinish(WXResponse wXResponse) {
            String str;
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mCallback == null) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                this.mCallback.onResponse(wXResponse, this.mRespHeaders);
                $jacocoInit[33] = true;
            }
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[34] = true;
            } else {
                $jacocoInit[35] = true;
                if (wXResponse == null) {
                    $jacocoInit[36] = true;
                } else if (wXResponse.originalData == null) {
                    $jacocoInit[37] = true;
                } else {
                    String str2 = new String(wXResponse.originalData);
                    $jacocoInit[38] = true;
                    str = str2;
                    WXLogUtils.d("WXStreamModule", str);
                    $jacocoInit[40] = true;
                }
                str = "response data is NUll!";
                $jacocoInit[39] = true;
                WXLogUtils.d("WXStreamModule", str);
                $jacocoInit[40] = true;
            }
            $jacocoInit[41] = true;
        }
    }
}
