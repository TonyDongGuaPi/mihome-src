package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.xiaomi.payment.data.MibiConstants;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EnumDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f6106a;
    private final Map<Integer, Enum> b = new HashMap();
    private final Map<String, Enum> c = new HashMap();

    public int a() {
        return 2;
    }

    public EnumDeserializer(Class<?> cls) {
        this.f6106a = cls;
        try {
            for (Object obj : (Object[]) cls.getMethod(MibiConstants.gf, new Class[0]).invoke((Object) null, new Object[0])) {
                Enum enumR = (Enum) obj;
                this.b.put(Integer.valueOf(enumR.ordinal()), enumR);
                this.c.put(enumR.name(), enumR);
            }
        } catch (Exception unused) {
            throw new JSONException("init enum values error, " + cls.getName());
        }
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            JSONLexer n = defaultJSONParser.n();
            if (n.d() == 2) {
                Integer valueOf = Integer.valueOf(n.r());
                n.a(16);
                T t = this.b.get(valueOf);
                if (t != null) {
                    return t;
                }
                throw new JSONException("parse enum " + this.f6106a.getName() + " error, value : " + valueOf);
            } else if (n.d() == 4) {
                String z = n.z();
                n.a(16);
                if (z.length() == 0) {
                    return (Object) null;
                }
                this.c.get(z);
                return Enum.valueOf(this.f6106a, z);
            } else if (n.d() == 8) {
                n.a(16);
                return null;
            } else {
                Object l = defaultJSONParser.l();
                throw new JSONException("parse enum " + this.f6106a.getName() + " error, value : " + l);
            }
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }
}
