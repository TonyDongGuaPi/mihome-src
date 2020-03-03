package com.alipay.zoloz.android.phone.mrpc.core.gwprotocol;

import android.util.Log;
import com.adobe.xmp.XMPConst;
import com.alipay.zoloz.b.a;
import com.alipay.zoloz.mobile.a.a.b;
import java.util.ArrayList;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class JsonSerializer extends AbstractSerializer {
    public static final String VERSION = "1.0.0";

    /* renamed from: a  reason: collision with root package name */
    private int f1194a;
    private Object b;

    public JsonSerializer(int i, String str, Object obj) {
        super(str, obj);
        this.f1194a = i;
    }

    public void setExtParam(Object obj) {
        this.b = obj;
    }

    public byte[] packet() {
        String str;
        String str2;
        try {
            ArrayList arrayList = new ArrayList();
            if (this.b != null) {
                arrayList.add(new BasicNameValuePair("extParam", a.a(this.b)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.mOperationType));
            arrayList.add(new BasicNameValuePair("id", this.f1194a + ""));
            Log.d("JsonSerializer", "mParams is:" + this.mParams);
            if (this.mParams == null) {
                str2 = XMPConst.ai;
            } else {
                str2 = a.a(this.mParams);
            }
            arrayList.add(new BasicNameValuePair("requestData", str2));
            String format = URLEncodedUtils.format(arrayList, "utf-8");
            Log.i("JsonSerializer", "request = " + format);
            return format.getBytes();
        } catch (Exception e) {
            if (("request  =" + this.mParams + ":" + e) == null) {
                str = "";
            } else {
                str = e.getMessage();
            }
            throw new b(9, str, e);
        }
    }

    public int getId() {
        return this.f1194a;
    }

    public void setId(int i) {
        this.f1194a = i;
    }
}
