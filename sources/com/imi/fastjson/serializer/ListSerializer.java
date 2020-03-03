package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public final class ListSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ListSerializer f6170a = new ListSerializer();

    /* JADX INFO: finally extract failed */
    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        JSONSerializer jSONSerializer2 = jSONSerializer;
        Object obj3 = obj;
        Object obj4 = obj2;
        Type type2 = type;
        boolean a2 = jSONSerializer2.a(SerializerFeature.WriteClassName);
        SerializeWriter p = jSONSerializer.p();
        int i = 0;
        Type type3 = (!a2 || !(type2 instanceof ParameterizedType)) ? null : ((ParameterizedType) type2).getActualTypeArguments()[0];
        if (obj3 != null) {
            List list = (List) obj3;
            int size = list.size();
            int i2 = size - 1;
            if (i2 == -1) {
                p.append((CharSequence) XMPConst.ai);
                return;
            }
            SerialContext c = jSONSerializer.c();
            jSONSerializer2.a(c, obj3, obj4);
            if (size > 1) {
                try {
                    if (p.a(SerializerFeature.PrettyFormat)) {
                        p.append((char) Operators.ARRAY_START);
                        jSONSerializer.g();
                        while (i < size) {
                            if (i != 0) {
                                p.append(',');
                            }
                            jSONSerializer.i();
                            Object obj5 = list.get(i);
                            if (obj5 == null) {
                                jSONSerializer.p().e();
                            } else if (jSONSerializer2.b(obj5)) {
                                jSONSerializer2.c(obj5);
                            } else {
                                ObjectSerializer a3 = jSONSerializer2.a(obj5.getClass());
                                jSONSerializer2.a(new SerialContext(c, obj3, obj4));
                                a3.a(jSONSerializer2, obj5, Integer.valueOf(i), type3);
                            }
                            i++;
                        }
                        jSONSerializer.h();
                        jSONSerializer.i();
                        p.append((char) Operators.ARRAY_END);
                        jSONSerializer2.a(c);
                        return;
                    }
                } catch (Throwable th) {
                    jSONSerializer2.a(c);
                    throw th;
                }
            }
            p.append((char) Operators.ARRAY_START);
            while (i < i2) {
                Object obj6 = list.get(i);
                if (obj6 == null) {
                    p.append((CharSequence) "null,");
                } else {
                    Class<?> cls = obj6.getClass();
                    if (cls == Integer.class) {
                        p.a(((Integer) obj6).intValue(), ',');
                    } else if (cls == Long.class) {
                        long longValue = ((Long) obj6).longValue();
                        if (a2) {
                            p.a(longValue, 'L');
                            p.a(',');
                        } else {
                            p.a(longValue, ',');
                        }
                    } else {
                        jSONSerializer2.a(new SerialContext(c, obj3, obj4));
                        if (jSONSerializer2.b(obj6)) {
                            jSONSerializer2.c(obj6);
                        } else {
                            jSONSerializer2.a(obj6.getClass()).a(jSONSerializer2, obj6, Integer.valueOf(i), type3);
                        }
                        p.append(',');
                    }
                }
                i++;
            }
            Object obj7 = list.get(i2);
            if (obj7 == null) {
                p.append((CharSequence) "null]");
            } else {
                Class<?> cls2 = obj7.getClass();
                if (cls2 == Integer.class) {
                    p.a(((Integer) obj7).intValue(), (char) Operators.ARRAY_END);
                } else if (cls2 != Long.class) {
                    jSONSerializer2.a(new SerialContext(c, obj3, obj4));
                    if (jSONSerializer2.b(obj7)) {
                        jSONSerializer2.c(obj7);
                    } else {
                        jSONSerializer2.a(obj7.getClass()).a(jSONSerializer2, obj7, Integer.valueOf(i2), type3);
                    }
                    p.append((char) Operators.ARRAY_END);
                } else if (a2) {
                    p.a(((Long) obj7).longValue(), 'L');
                    p.a((char) Operators.ARRAY_END);
                } else {
                    p.a(((Long) obj7).longValue(), (char) Operators.ARRAY_END);
                }
            }
            jSONSerializer2.a(c);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
