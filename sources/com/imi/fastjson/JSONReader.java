package com.imi.fastjson;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.Feature;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.JSONReaderScanner;
import com.imi.fastjson.util.IOUtils;
import com.imi.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class JSONReader implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final DefaultJSONParser f6081a;
    private JSONStreamContext b;

    public JSONReader(Reader reader) {
        this((JSONLexer) new JSONReaderScanner(reader));
    }

    public JSONReader(JSONLexer jSONLexer) {
        this(new DefaultJSONParser(jSONLexer));
    }

    public JSONReader(DefaultJSONParser defaultJSONParser) {
        this.f6081a = defaultJSONParser;
    }

    public void a(Feature feature, boolean z) {
        this.f6081a.a(feature, z);
    }

    public void a() {
        if (this.b == null) {
            this.b = new JSONStreamContext((JSONStreamContext) null, 1001);
        } else {
            j();
            this.b = new JSONStreamContext(this.b, 1001);
        }
        this.f6081a.a(12, 18);
    }

    public void b() {
        this.f6081a.b(13);
        k();
    }

    public void c() {
        if (this.b == null) {
            this.b = new JSONStreamContext((JSONStreamContext) null, 1004);
        } else {
            j();
            this.b = new JSONStreamContext(this.b, 1004);
        }
        this.f6081a.b(14);
    }

    public void d() {
        this.f6081a.b(15);
        k();
    }

    private void j() {
        switch (this.b.b()) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.f6081a.b(17);
                return;
            case 1003:
            case 1005:
                this.f6081a.b(16);
                return;
            default:
                throw new JSONException("illegal state : " + this.b.b());
        }
    }

    private void k() {
        int i;
        this.b = this.b.a();
        if (this.b != null) {
            switch (this.b.b()) {
                case 1001:
                case 1003:
                    i = 1002;
                    break;
                case 1002:
                    i = 1003;
                    break;
                case 1004:
                    i = 1005;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                this.b.a(i);
            }
        }
    }

    public boolean e() {
        if (this.b != null) {
            int d = this.f6081a.n().d();
            int b2 = this.b.b();
            switch (b2) {
                case 1001:
                case 1003:
                    if (d != 13) {
                        return true;
                    }
                    return false;
                case 1004:
                case 1005:
                    if (d != 15) {
                        return true;
                    }
                    return false;
                default:
                    throw new JSONException("illegal state : " + b2);
            }
        } else {
            throw new JSONException("context is null");
        }
    }

    public void close() {
        IOUtils.a((Closeable) this.f6081a);
    }

    public Integer f() {
        Object obj;
        if (this.b == null) {
            obj = this.f6081a.l();
        } else {
            l();
            obj = this.f6081a.l();
            m();
        }
        return TypeUtils.m(obj);
    }

    public Long g() {
        Object obj;
        if (this.b == null) {
            obj = this.f6081a.l();
        } else {
            l();
            obj = this.f6081a.l();
            m();
        }
        return TypeUtils.l(obj);
    }

    public String h() {
        Object obj;
        if (this.b == null) {
            obj = this.f6081a.l();
        } else {
            l();
            obj = this.f6081a.l();
            m();
        }
        return TypeUtils.a(obj);
    }

    public <T> T a(Type type) {
        if (this.b == null) {
            return this.f6081a.a(type);
        }
        l();
        T a2 = this.f6081a.a(type);
        m();
        return a2;
    }

    public <T> T a(Class<T> cls) {
        if (this.b == null) {
            return this.f6081a.a(cls);
        }
        l();
        T a2 = this.f6081a.a(cls);
        m();
        return a2;
    }

    public void a(Object obj) {
        if (this.b == null) {
            this.f6081a.a(obj);
            return;
        }
        l();
        this.f6081a.a(obj);
        m();
    }

    public Object i() {
        Object obj;
        if (this.b == null) {
            return this.f6081a.l();
        }
        l();
        int b2 = this.b.b();
        if (b2 == 1001 || b2 == 1003) {
            obj = this.f6081a.m();
        } else {
            obj = this.f6081a.l();
        }
        m();
        return obj;
    }

    public Object a(Map map) {
        if (this.b == null) {
            return this.f6081a.a(map);
        }
        l();
        Object a2 = this.f6081a.a(map);
        m();
        return a2;
    }

    private void l() {
        int b2 = this.b.b();
        switch (b2) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.f6081a.b(17);
                return;
            case 1003:
                this.f6081a.a(16, 18);
                return;
            case 1005:
                this.f6081a.b(16);
                return;
            default:
                throw new JSONException("illegal state : " + b2);
        }
    }

    private void m() {
        int b2 = this.b.b();
        int i = 1002;
        switch (b2) {
            case 1001:
            case 1003:
                break;
            case 1002:
                i = 1003;
                break;
            case 1004:
                i = 1005;
                break;
            case 1005:
                i = -1;
                break;
            default:
                throw new JSONException("illegal state : " + b2);
        }
        if (i != -1) {
            this.b.a(i);
        }
    }
}
