package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import org.json.JSONException;
import org.json.JSONObject;

class et implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f12726a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ es e;

    et(es esVar, String str, Context context, String str2, String str3) {
        this.e = esVar;
        this.f12726a = str;
        this.b = context;
        this.c = str2;
        this.d = str3;
    }

    public void run() {
        String str;
        String str2;
        Context context;
        Context context2;
        String str3;
        String str4;
        es esVar;
        eu euVar;
        Context context3;
        if (!TextUtils.isEmpty(this.f12726a)) {
            try {
                eo.a(this.b, this.f12726a, 1001, "get message");
                JSONObject jSONObject = new JSONObject(this.f12726a);
                String optString = jSONObject.optString("action");
                String optString2 = jSONObject.optString("awakened_app_packagename");
                String optString3 = jSONObject.optString("awake_app_packagename");
                String optString4 = jSONObject.optString("awake_app");
                String optString5 = jSONObject.optString("awake_type");
                int optInt = jSONObject.optInt("awake_foreground", 0);
                if (this.c.equals(optString3)) {
                    if (this.d.equals(optString4)) {
                        if (TextUtils.isEmpty(optString5) || TextUtils.isEmpty(optString3) || TextUtils.isEmpty(optString4) || TextUtils.isEmpty(optString2)) {
                            context2 = this.b;
                            str3 = this.f12726a;
                            str4 = "A receive a incorrect message with empty type";
                        } else {
                            this.e.b(optString3);
                            this.e.a(optString4);
                            er erVar = new er();
                            erVar.b(optString);
                            erVar.a(optString2);
                            erVar.a(optInt);
                            erVar.d(this.f12726a);
                            if ("service".equals(optString5)) {
                                if (!TextUtils.isEmpty(optString)) {
                                    esVar = this.e;
                                    euVar = eu.SERVICE_ACTION;
                                    context3 = this.b;
                                } else {
                                    erVar.c("com.xiaomi.mipush.sdk.PushMessageHandler");
                                    esVar = this.e;
                                    euVar = eu.SERVICE_COMPONENT;
                                    context3 = this.b;
                                }
                            } else if (eu.ACTIVITY.f68a.equals(optString5)) {
                                esVar = this.e;
                                euVar = eu.ACTIVITY;
                                context3 = this.b;
                            } else if (eu.PROVIDER.f68a.equals(optString5)) {
                                esVar = this.e;
                                euVar = eu.PROVIDER;
                                context3 = this.b;
                            } else {
                                context2 = this.b;
                                str3 = this.f12726a;
                                str4 = "A receive a incorrect message with unknown type " + optString5;
                            }
                            esVar.a(euVar, context3, erVar);
                            return;
                        }
                        eo.a(context2, str3, 1008, str4);
                        return;
                    }
                }
                eo.a(this.b, this.f12726a, 1008, "A receive a incorrect message with incorrect package info" + optString3);
            } catch (JSONException e2) {
                b.a((Throwable) e2);
                context = this.b;
                str2 = this.f12726a;
                str = "A meet a exception when receive the message";
            }
        } else {
            context = this.b;
            str2 = "null";
            str = "A receive a incorrect message with empty info";
            eo.a(context, str2, 1008, str);
        }
    }
}
