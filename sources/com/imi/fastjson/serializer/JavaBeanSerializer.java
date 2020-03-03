package com.imi.fastjson.serializer;

import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.util.FieldInfo;
import com.imi.fastjson.util.TypeUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JavaBeanSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    private final FieldSerializer[] f6169a;
    private final FieldSerializer[] b;

    public FieldSerializer[] a() {
        return this.f6169a;
    }

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, a(strArr));
    }

    static Map<String, String> a(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (FieldInfo a2 : TypeUtils.a(cls, map, false)) {
            arrayList.add(a(a2));
        }
        this.f6169a = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
        ArrayList arrayList2 = new ArrayList();
        for (FieldInfo a3 : TypeUtils.a(cls, map, true)) {
            arrayList2.add(a(a3));
        }
        this.b = (FieldSerializer[]) arrayList2.toArray(new FieldSerializer[arrayList2.size()]);
    }

    /* access modifiers changed from: protected */
    public boolean a(JSONSerializer jSONSerializer, Object obj, Type type, Object obj2) {
        return jSONSerializer.a(type, obj);
    }

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        FieldSerializer[] fieldSerializerArr;
        boolean z;
        Field b2;
        SerializeWriter p = jSONSerializer.p();
        if (obj == null) {
            p.e();
        } else if (jSONSerializer.b(obj)) {
            a(jSONSerializer, obj);
        } else {
            if (p.a(SerializerFeature.SortField)) {
                fieldSerializerArr = this.b;
            } else {
                fieldSerializerArr = this.f6169a;
            }
            SerialContext c = jSONSerializer.c();
            jSONSerializer.a(c, obj, obj2);
            try {
                p.append((char) Operators.BLOCK_START);
                if (fieldSerializerArr.length > 0 && p.a(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.g();
                    jSONSerializer.i();
                }
                if (!a(jSONSerializer, obj, type, obj2) || obj.getClass() == type) {
                    z = false;
                } else {
                    p.c(JSON.DEFAULT_TYPE_KEY);
                    jSONSerializer.d(obj.getClass());
                    z = true;
                }
                for (FieldSerializer fieldSerializer : fieldSerializerArr) {
                    if (!jSONSerializer.a(SerializerFeature.SkipTransientField) || (b2 = fieldSerializer.b()) == null || !Modifier.isTransient(b2.getModifiers())) {
                        if (FilterUtils.a(jSONSerializer, obj, fieldSerializer.c())) {
                            Object a2 = fieldSerializer.a(obj);
                            if (FilterUtils.c(jSONSerializer, obj, fieldSerializer.c(), a2)) {
                                String b3 = FilterUtils.b(jSONSerializer, obj, fieldSerializer.c(), a2);
                                Object a3 = FilterUtils.a(jSONSerializer, obj, fieldSerializer.c(), a2);
                                if (a3 != null || fieldSerializer.a() || jSONSerializer.a(SerializerFeature.WriteMapNullValue)) {
                                    if (z) {
                                        p.append(',');
                                        if (p.a(SerializerFeature.PrettyFormat)) {
                                            jSONSerializer.i();
                                        }
                                    }
                                    if (b3 != fieldSerializer.c()) {
                                        p.c(b3);
                                        jSONSerializer.d(a3);
                                    } else if (a2 != a3) {
                                        fieldSerializer.a(jSONSerializer);
                                        jSONSerializer.d(a3);
                                    } else {
                                        fieldSerializer.a(jSONSerializer, a3);
                                    }
                                    z = true;
                                }
                            }
                        }
                    }
                }
                if (fieldSerializerArr.length > 0 && p.a(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.h();
                    jSONSerializer.i();
                }
                p.append((char) Operators.BLOCK_END);
                jSONSerializer.a(c);
            } catch (Exception e) {
                throw new JSONException("write javaBean error", e);
            } catch (Throwable th) {
                jSONSerializer.a(c);
                throw th;
            }
        }
    }

    public void a(JSONSerializer jSONSerializer, Object obj) {
        jSONSerializer.c(obj);
    }

    public FieldSerializer a(FieldInfo fieldInfo) {
        if (fieldInfo.b() == Number.class) {
            return new NumberFieldSerializer(fieldInfo);
        }
        return new ObjectFieldSerializer(fieldInfo);
    }
}
