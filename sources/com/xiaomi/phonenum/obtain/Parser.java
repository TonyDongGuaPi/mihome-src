package com.xiaomi.phonenum.obtain;

import com.xiaomi.phonenum.http.Response;
import java.io.IOException;
import org.json.JSONException;

public abstract class Parser {

    /* renamed from: a  reason: collision with root package name */
    protected Parser f12570a;

    public abstract Response a(int i, String str) throws IOException, JSONException;

    public void a(Parser parser) {
        if (this.f12570a == null) {
            this.f12570a = parser;
        } else {
            this.f12570a.a(parser);
        }
    }
}
