package com.imi.fastjson;

import com.imi.fastjson.serializer.JSONSerializer;
import com.imi.fastjson.serializer.SerializeWriter;
import com.imi.fastjson.serializer.SerializerFeature;
import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JSONWriter implements Closeable, Flushable {

    /* renamed from: a  reason: collision with root package name */
    private SerializeWriter f6083a;
    private JSONSerializer b = new JSONSerializer(this.f6083a);
    private JSONStreamContext c;

    public JSONWriter(Writer writer) {
        this.f6083a = new SerializeWriter(writer);
    }

    public void a(SerializerFeature serializerFeature, boolean z) {
        this.f6083a.a(serializerFeature, z);
    }

    public void a() {
        if (this.c != null) {
            i();
        }
        this.c = new JSONStreamContext(this.c, 1001);
        this.f6083a.a((char) Operators.BLOCK_START);
    }

    public void b() {
        this.f6083a.a((char) Operators.BLOCK_END);
        j();
    }

    public void a(String str) {
        b(str);
    }

    public void a(Object obj) {
        b(obj);
    }

    public void b(String str) {
        k();
        this.b.b(str);
        l();
    }

    public void b(Object obj) {
        k();
        this.b.d(obj);
        l();
    }

    public void c() {
        if (this.c != null) {
            i();
        }
        this.c = new JSONStreamContext(this.c, 1004);
        this.f6083a.a((char) Operators.ARRAY_START);
    }

    private void i() {
        int b2 = this.c.b();
        switch (b2) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.f6083a.a((char) Operators.CONDITION_IF_MIDDLE);
                return;
            case 1005:
                this.f6083a.a(',');
                return;
            default:
                throw new JSONException("illegal state : " + b2);
        }
    }

    public void d() {
        this.f6083a.a((char) Operators.ARRAY_END);
        j();
    }

    private void j() {
        int i;
        this.c = this.c.a();
        if (this.c != null) {
            switch (this.c.b()) {
                case 1001:
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
                this.c.a(i);
            }
        }
    }

    private void k() {
        if (this.c != null) {
            switch (this.c.b()) {
                case 1002:
                    this.f6083a.a((char) Operators.CONDITION_IF_MIDDLE);
                    return;
                case 1003:
                    this.f6083a.a(',');
                    return;
                case 1005:
                    this.f6083a.a(',');
                    return;
                default:
                    return;
            }
        }
    }

    private void l() {
        int i;
        if (this.c != null) {
            switch (this.c.b()) {
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
                this.c.a(i);
            }
        }
    }

    public void flush() throws IOException {
        this.f6083a.flush();
    }

    public void close() throws IOException {
        this.f6083a.close();
    }

    @Deprecated
    public void e() {
        a();
    }

    @Deprecated
    public void f() {
        b();
    }

    @Deprecated
    public void g() {
        c();
    }

    @Deprecated
    public void h() {
        d();
    }
}
