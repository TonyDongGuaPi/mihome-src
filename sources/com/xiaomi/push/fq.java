package com.xiaomi.push;

import com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class fq {

    /* renamed from: a  reason: collision with root package name */
    private XmlPullParser f12741a;

    fq() {
        try {
            this.f12741a = XmlPullParserFactory.newInstance().newPullParser();
            this.f12741a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (XmlPullParserException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public gl a(byte[] bArr, fu fuVar) {
        this.f12741a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
        this.f12741a.next();
        int eventType = this.f12741a.getEventType();
        String name = this.f12741a.getName();
        if (eventType != 2) {
            return null;
        }
        if (name.equals("message")) {
            return gt.a(this.f12741a);
        }
        if (name.equals("iq")) {
            return gt.a(this.f12741a, fuVar);
        }
        if (name.equals("presence")) {
            return gt.b(this.f12741a);
        }
        if (this.f12741a.getName().equals("stream")) {
            return null;
        }
        if (this.f12741a.getName().equals("error")) {
            throw new gf(gt.c(this.f12741a));
        } else if (this.f12741a.getName().equals("warning")) {
            this.f12741a.next();
            boolean equals = this.f12741a.getName().equals("multi-login");
            return null;
        } else {
            this.f12741a.getName().equals(LoginBindBaseWebActivity.SOURCE_BIND);
            return null;
        }
    }
}
