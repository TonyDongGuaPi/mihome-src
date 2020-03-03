package com.alipay.zoloz.android.phone.mrpc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public class HttpUrlRequest extends Request {

    /* renamed from: a  reason: collision with root package name */
    private String f1187a;
    private byte[] b;
    private String c;
    private ArrayList<Header> d;
    private Map<String, String> e;
    private boolean f;

    public HttpUrlRequest(String str) {
        this.f1187a = str;
        this.d = new ArrayList<>();
        this.e = new HashMap();
        this.c = "application/x-www-form-urlencoded";
    }

    public HttpUrlRequest(String str, byte[] bArr, ArrayList<Header> arrayList, HashMap<String, String> hashMap) {
        this.f1187a = str;
        this.b = bArr;
        this.d = arrayList;
        this.e = hashMap;
        this.c = "application/x-www-form-urlencoded";
    }

    public String getUrl() {
        return this.f1187a;
    }

    public String setUrl(String str) {
        this.f1187a = str;
        return str;
    }

    public byte[] getReqData() {
        return this.b;
    }

    public void setReqData(byte[] bArr) {
        this.b = bArr;
    }

    public String getContentType() {
        return this.c;
    }

    public void setContentType(String str) {
        this.c = str;
    }

    public void setHeaders(ArrayList<Header> arrayList) {
        this.d = arrayList;
    }

    public void addHeader(Header header) {
        this.d.add(header);
    }

    public ArrayList<Header> getHeaders() {
        return this.d;
    }

    public void setTags(Map<String, String> map) {
        this.e = map;
    }

    public void addTags(String str, String str2) {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put(str, str2);
    }

    public String getTag(String str) {
        if (this.e == null) {
            return null;
        }
        return this.e.get(str);
    }

    public boolean isResetCookie() {
        return this.f;
    }

    public void setResetCookie(boolean z) {
        this.f = z;
    }

    public String getKey() {
        return getUrl() + Integer.toHexString(getReqData().hashCode());
    }

    public String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{getUrl(), getHeaders()});
    }

    public int hashCode() {
        return (((this.e == null || !this.e.containsKey("id")) ? 1 : this.e.get("id").hashCode() + 31) * 31) + (this.f1187a == null ? 0 : this.f1187a.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpUrlRequest httpUrlRequest = (HttpUrlRequest) obj;
        if (this.b == null) {
            if (httpUrlRequest.b != null) {
                return false;
            }
        } else if (!this.b.equals(httpUrlRequest.b)) {
            return false;
        }
        if (this.f1187a == null) {
            if (httpUrlRequest.f1187a != null) {
                return false;
            }
        } else if (!this.f1187a.equals(httpUrlRequest.f1187a)) {
            return false;
        }
        return true;
    }
}
