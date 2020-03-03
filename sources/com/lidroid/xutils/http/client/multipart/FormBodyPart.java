package com.lidroid.xutils.http.client.multipart;

import com.lidroid.xutils.http.client.multipart.content.ContentBody;

public class FormBodyPart {

    /* renamed from: a  reason: collision with root package name */
    private final String f6346a;
    private final MinimalFieldHeader b;
    private final ContentBody c;

    public FormBodyPart(String str, ContentBody contentBody) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (contentBody != null) {
            this.f6346a = str;
            this.c = contentBody;
            this.b = new MinimalFieldHeader();
            a(contentBody);
            b(contentBody);
            c(contentBody);
        } else {
            throw new IllegalArgumentException("Body may not be null");
        }
    }

    public FormBodyPart(String str, ContentBody contentBody, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (contentBody != null) {
            this.f6346a = str;
            this.c = contentBody;
            this.b = new MinimalFieldHeader();
            if (str2 != null) {
                a("Content-Disposition", str2);
            } else {
                a(contentBody);
            }
            b(contentBody);
            c(contentBody);
        } else {
            throw new IllegalArgumentException("Body may not be null");
        }
    }

    public String a() {
        return this.f6346a;
    }

    public ContentBody b() {
        return this.c;
    }

    public MinimalFieldHeader c() {
        return this.b;
    }

    public void a(String str, String str2) {
        if (str != null) {
            this.b.a(new MinimalField(str, str2));
            return;
        }
        throw new IllegalArgumentException("Field name may not be null");
    }

    /* access modifiers changed from: protected */
    public void a(ContentBody contentBody) {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"");
        sb.append(a());
        sb.append("\"");
        if (contentBody.d() != null) {
            sb.append("; filename=\"");
            sb.append(contentBody.d());
            sb.append("\"");
        }
        a("Content-Disposition", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void b(ContentBody contentBody) {
        StringBuilder sb = new StringBuilder();
        sb.append(contentBody.a());
        if (contentBody.e() != null) {
            sb.append("; charset=");
            sb.append(contentBody.e());
        }
        a("Content-Type", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void c(ContentBody contentBody) {
        a("Content-Transfer-Encoding", contentBody.f());
    }
}
