package com.xiaomi.miot.store.alipay;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.l;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IPayProvider;
import java.util.HashMap;
import java.util.Map;

public class AlipayProvider implements IPayProvider {
    public void clear() {
    }

    public String name() {
        return "alipay";
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        return false;
    }

    public void pay(final Activity activity, final String str, final ICallback iCallback) {
        if (TextUtils.isEmpty(str) || activity == null) {
            iCallback.callback((Map) null);
        } else {
            new AsyncTask<Void, Void, String>() {
                private String a(String str, String str2) {
                    String str3 = str2 + "={";
                    return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public String doInBackground(Void... voidArr) {
                    return new PayTask(activity).pay(str, false);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(String str) {
                    HashMap hashMap = new HashMap();
                    if (!TextUtils.isEmpty(str)) {
                        for (String str2 : str.split(i.b)) {
                            if (str2.startsWith(l.f1135a)) {
                                hashMap.put(l.f1135a, a(str2, l.f1135a));
                            } else if (str2.startsWith("result")) {
                                hashMap.put("result", a(str2, "result"));
                            } else if (str2.startsWith(l.b)) {
                                hashMap.put(l.b, a(str2, l.b));
                            }
                        }
                    }
                    iCallback.callback(hashMap);
                }
            }.execute(new Void[0]);
        }
    }
}
