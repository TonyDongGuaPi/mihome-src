package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;

public abstract class AbstractContentBody implements ContentBody {

    /* renamed from: a  reason: collision with root package name */
    protected MultipartEntity.CallBackInfo f6353a = MultipartEntity.CallBackInfo.f6352a;
    private final String b;
    private final String c;
    private final String d;

    public AbstractContentBody(String str) {
        if (str != null) {
            this.b = str;
            int indexOf = str.indexOf(47);
            if (indexOf != -1) {
                this.c = str.substring(0, indexOf);
                this.d = str.substring(indexOf + 1);
                return;
            }
            this.c = str;
            this.d = null;
            return;
        }
        throw new IllegalArgumentException("MIME type may not be null");
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public void a(MultipartEntity.CallBackInfo callBackInfo) {
        this.f6353a = callBackInfo;
    }
}
