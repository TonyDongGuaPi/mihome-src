package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class CollectionSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CollectionSerializer f6149a = new CollectionSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            Type type2 = null;
            int i = 0;
            if (jSONSerializer.a(SerializerFeature.WriteClassName) && (type instanceof ParameterizedType)) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            }
            Collection collection = (Collection) obj;
            SerialContext c = jSONSerializer.c();
            jSONSerializer.a(c, obj, obj2);
            if (jSONSerializer.a(SerializerFeature.WriteClassName)) {
                if (HashSet.class == collection.getClass()) {
                    p.append((CharSequence) "Set");
                } else if (TreeSet.class == collection.getClass()) {
                    p.append((CharSequence) "TreeSet");
                }
            }
            try {
                p.append((char) Operators.ARRAY_START);
                for (Object next : collection) {
                    int i2 = i + 1;
                    if (i != 0) {
                        p.append(',');
                    }
                    if (next == null) {
                        p.e();
                    } else {
                        Class<?> cls = next.getClass();
                        if (cls == Integer.class) {
                            p.b(((Integer) next).intValue());
                        } else if (cls == Long.class) {
                            p.a(((Long) next).longValue());
                            if (p.a(SerializerFeature.WriteClassName)) {
                                p.a('L');
                            }
                        } else {
                            jSONSerializer.a(cls).a(jSONSerializer, next, Integer.valueOf(i2 - 1), type2);
                        }
                    }
                    i = i2;
                }
                p.append((char) Operators.ARRAY_END);
            } finally {
                jSONSerializer.a(c);
            }
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
