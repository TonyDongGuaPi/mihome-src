package com.alipay.deviceid.module.x;

import com.adobe.xmp.XMPConst;
import com.alipay.deviceid.module.rpc.mrpc.core.RpcException;
import java.util.ArrayList;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public final class bt extends bq {
    private int c;
    private Object d;

    public bt(int i, String str, Object obj) {
        super(str, obj);
        this.c = i;
    }

    public final void a(Object obj) {
        this.d = obj;
    }

    public final byte[] a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.d != null) {
                arrayList.add(new BasicNameValuePair("extParam", ai.a(this.d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.f897a));
            StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            arrayList.add(new BasicNameValuePair("id", sb.toString()));
            new StringBuilder("mParams is:").append(this.b);
            arrayList.add(new BasicNameValuePair("requestData", this.b == null ? XMPConst.ai : ai.a(this.b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(this.b);
            sb2.append(":");
            sb2.append(e);
            throw new RpcException(9, sb2.toString() == null ? "" : e.getMessage(), e);
        }
    }
}
