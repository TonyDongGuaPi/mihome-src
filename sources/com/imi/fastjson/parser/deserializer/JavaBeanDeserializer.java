package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.JSONObject;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.Feature;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.DeserializeBeanInfo;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JavaBeanDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, FieldDeserializer> f6115a;
    private final List<FieldDeserializer> b;
    private final Class<?> c;
    private DeserializeBeanInfo d;

    public int a() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this.f6115a = new IdentityHashMap();
        this.b = new ArrayList();
        this.c = cls;
        this.d = DeserializeBeanInfo.a(cls, type);
        for (FieldInfo b2 : this.d.e()) {
            b(parserConfig, cls, b2);
        }
    }

    public Map<String, FieldDeserializer> b() {
        return this.f6115a;
    }

    private void b(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        FieldDeserializer a2 = a(parserConfig, cls, fieldInfo);
        this.f6115a.put(fieldInfo.d().intern(), a2);
        this.b.add(a2);
    }

    public FieldDeserializer a(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        return parserConfig.a(parserConfig, cls, fieldInfo);
    }

    public Object a(DefaultJSONParser defaultJSONParser, Type type) {
        Object obj;
        if ((type instanceof Class) && this.c.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        } else if (this.d.b() == null) {
            return null;
        } else {
            try {
                Constructor<?> b2 = this.d.b();
                if (b2.getParameterTypes().length == 0) {
                    obj = b2.newInstance(new Object[0]);
                } else {
                    obj = b2.newInstance(new Object[]{defaultJSONParser.h().b()});
                }
                if (defaultJSONParser.a(Feature.InitStringFieldAsEmpty)) {
                    for (FieldInfo next : this.d.e()) {
                        if (next.b() == String.class) {
                            try {
                                next.a((Object) obj, (Object) "");
                            } catch (Exception e) {
                                throw new JSONException("create instance error, class " + this.c.getName(), e);
                            }
                        }
                    }
                }
                return obj;
            } catch (Exception e2) {
                throw new JSONException("create instance error, class " + this.c.getName(), e2);
            }
        }
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return a(defaultJSONParser, type, obj, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0218, code lost:
        r1 = r16;
        r2 = r17;
        r3 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x021e, code lost:
        if (r1 != null) goto L_0x02bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0220, code lost:
        if (r2 != null) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        r0 = a(r20, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0226, code lost:
        if (r3 == null) goto L_0x022b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0228, code lost:
        r3.a((java.lang.Object) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x022b, code lost:
        r8.a(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x022e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:?, code lost:
        r0 = r7.d.e();
        r4 = r0.size();
        r5 = new java.lang.Object[r4];
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x023c, code lost:
        if (r6 >= r4) goto L_0x0251;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x023e, code lost:
        r5[r6] = r2.get(r0.get(r6).d());
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0257, code lost:
        if (r7.d.c() == null) goto L_0x0286;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        r0 = r7.d.c().newInstance(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x028c, code lost:
        if (r7.d.d() == null) goto L_0x02bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
        r0 = r7.d.d().invoke((java.lang.Object) null, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x02bb, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02bc, code lost:
        if (r3 == null) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02be, code lost:
        r3.a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x02c1, code lost:
        r8.a(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02c4, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b9, code lost:
        r10.b(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c0, code lost:
        if (r10.d() != 4) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c2, code lost:
        r0 = r10.z();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cc, code lost:
        if ("@".equals(r0) == false) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ce, code lost:
        r0 = r13.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d2, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00da, code lost:
        if ("..".equals(r0) == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dc, code lost:
        r2 = r13.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e4, code lost:
        if (r2.b() == null) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e6, code lost:
        r0 = r2.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00eb, code lost:
        r8.a(new com.imi.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r0));
        r8.a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f6, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fe, code lost:
        if (com.taobao.weex.el.parse.Operators.DOLLAR_STR.equals(r0) == false) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0100, code lost:
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0105, code lost:
        if (r2.c() == null) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0107, code lost:
        r2 = r2.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0110, code lost:
        if (r2.b() == null) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0112, code lost:
        r0 = r2.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0117, code lost:
        r8.a(new com.imi.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r0));
        r8.a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0123, code lost:
        r8.a(new com.imi.fastjson.parser.DefaultJSONParser.ResolveTask(r13, r0));
        r8.a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x012e, code lost:
        r10.a(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0135, code lost:
        if (r10.d() != 13) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0137, code lost:
        r10.a(16);
        r8.a(r13, r1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013d, code lost:
        if (r3 == null) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x013f, code lost:
        r3.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0142, code lost:
        r8.a(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0145, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x014d, code lost:
        throw new com.imi.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x016c, code lost:
        throw new com.imi.fastjson.JSONException("illegal ref, " + com.imi.fastjson.parser.JSONToken.a(r10.d()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01b0, code lost:
        if (r3 == null) goto L_0x01b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b2, code lost:
        r3.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01b5, code lost:
        r8.a(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01b8, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0308  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T a(com.imi.fastjson.parser.DefaultJSONParser r20, java.lang.reflect.Type r21, java.lang.Object r22, java.lang.Object r23) {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            r0 = r21
            r9 = r22
            com.imi.fastjson.parser.JSONLexer r10 = r20.n()
            int r2 = r10.d()
            r11 = 0
            r12 = 16
            r3 = 8
            if (r2 != r3) goto L_0x001b
            r10.a((int) r12)
            return r11
        L_0x001b:
            com.imi.fastjson.parser.ParseContext r2 = r20.h()
            if (r23 == 0) goto L_0x0025
            com.imi.fastjson.parser.ParseContext r2 = r2.c()
        L_0x0025:
            r13 = r2
            int r2 = r10.d()     // Catch:{ all -> 0x0302 }
            r14 = 13
            if (r2 != r14) goto L_0x003e
            r10.a((int) r12)     // Catch:{ all -> 0x0302 }
            if (r23 != 0) goto L_0x0038
            java.lang.Object r0 = r19.a(r20, r21)     // Catch:{ all -> 0x0302 }
            goto L_0x003a
        L_0x0038:
            r0 = r23
        L_0x003a:
            r8.a((com.imi.fastjson.parser.ParseContext) r13)
            return r0
        L_0x003e:
            int r2 = r10.d()     // Catch:{ all -> 0x0302 }
            r3 = 12
            if (r2 == r3) goto L_0x007f
            int r2 = r10.d()     // Catch:{ all -> 0x0302 }
            if (r2 == r12) goto L_0x007f
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ all -> 0x0302 }
            r0.<init>()     // Catch:{ all -> 0x0302 }
            java.lang.String r2 = "syntax error, expect {, actual "
            r0.append(r2)     // Catch:{ all -> 0x0302 }
            java.lang.String r2 = r10.e()     // Catch:{ all -> 0x0302 }
            r0.append(r2)     // Catch:{ all -> 0x0302 }
            java.lang.String r2 = ", pos "
            r0.append(r2)     // Catch:{ all -> 0x0302 }
            int r2 = r10.f()     // Catch:{ all -> 0x0302 }
            r0.append(r2)     // Catch:{ all -> 0x0302 }
            boolean r2 = r9 instanceof java.lang.String     // Catch:{ all -> 0x0302 }
            if (r2 == 0) goto L_0x0075
            java.lang.String r2 = ", fieldName "
            r0.append(r2)     // Catch:{ all -> 0x0302 }
            r0.append(r9)     // Catch:{ all -> 0x0302 }
        L_0x0075:
            com.imi.fastjson.JSONException r2 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0302 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0302 }
            r2.<init>(r0)     // Catch:{ all -> 0x0302 }
            throw r2     // Catch:{ all -> 0x0302 }
        L_0x007f:
            int r2 = r20.f()     // Catch:{ all -> 0x0302 }
            r3 = 2
            r15 = 0
            if (r2 != r3) goto L_0x008a
            r8.a((int) r15)     // Catch:{ all -> 0x0302 }
        L_0x008a:
            r1 = r23
            r2 = r11
            r3 = r2
        L_0x008e:
            com.imi.fastjson.parser.SymbolTable r4 = r20.c()     // Catch:{ all -> 0x0300 }
            java.lang.String r4 = r10.a((com.imi.fastjson.parser.SymbolTable) r4)     // Catch:{ all -> 0x0300 }
            if (r4 != 0) goto L_0x00b3
            int r5 = r10.d()     // Catch:{ all -> 0x0300 }
            if (r5 != r14) goto L_0x00a3
            r10.a((int) r12)     // Catch:{ all -> 0x0300 }
            goto L_0x021e
        L_0x00a3:
            int r5 = r10.d()     // Catch:{ all -> 0x0300 }
            if (r5 != r12) goto L_0x00b3
            com.imi.fastjson.parser.Feature r5 = com.imi.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0300 }
            boolean r5 = r8.a((com.imi.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0300 }
            if (r5 == 0) goto L_0x00b3
            goto L_0x019d
        L_0x00b3:
            java.lang.String r5 = "$ref"
            r6 = 4
            r15 = 1
            if (r5 != r4) goto L_0x016d
            r10.b((int) r6)     // Catch:{ all -> 0x0300 }
            int r0 = r10.d()     // Catch:{ all -> 0x0300 }
            if (r0 != r6) goto L_0x014e
            java.lang.String r0 = r10.z()     // Catch:{ all -> 0x0300 }
            java.lang.String r2 = "@"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0300 }
            if (r2 == 0) goto L_0x00d4
            java.lang.Object r0 = r13.b()     // Catch:{ all -> 0x0300 }
        L_0x00d2:
            r1 = r0
            goto L_0x012e
        L_0x00d4:
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0300 }
            if (r2 == 0) goto L_0x00f8
            com.imi.fastjson.parser.ParseContext r2 = r13.c()     // Catch:{ all -> 0x0300 }
            java.lang.Object r4 = r2.b()     // Catch:{ all -> 0x0300 }
            if (r4 == 0) goto L_0x00eb
            java.lang.Object r0 = r2.b()     // Catch:{ all -> 0x0300 }
            goto L_0x00d2
        L_0x00eb:
            com.imi.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.imi.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0300 }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x0300 }
            r8.a((com.imi.fastjson.parser.DefaultJSONParser.ResolveTask) r4)     // Catch:{ all -> 0x0300 }
            r8.a((int) r15)     // Catch:{ all -> 0x0300 }
        L_0x00f6:
            r0 = r1
            goto L_0x00d2
        L_0x00f8:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0300 }
            if (r2 == 0) goto L_0x0123
            r2 = r13
        L_0x0101:
            com.imi.fastjson.parser.ParseContext r4 = r2.c()     // Catch:{ all -> 0x0300 }
            if (r4 == 0) goto L_0x010c
            com.imi.fastjson.parser.ParseContext r2 = r2.c()     // Catch:{ all -> 0x0300 }
            goto L_0x0101
        L_0x010c:
            java.lang.Object r4 = r2.b()     // Catch:{ all -> 0x0300 }
            if (r4 == 0) goto L_0x0117
            java.lang.Object r0 = r2.b()     // Catch:{ all -> 0x0300 }
            goto L_0x00d2
        L_0x0117:
            com.imi.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.imi.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0300 }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x0300 }
            r8.a((com.imi.fastjson.parser.DefaultJSONParser.ResolveTask) r4)     // Catch:{ all -> 0x0300 }
            r8.a((int) r15)     // Catch:{ all -> 0x0300 }
            goto L_0x00f6
        L_0x0123:
            com.imi.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.imi.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0300 }
            r2.<init>(r13, r0)     // Catch:{ all -> 0x0300 }
            r8.a((com.imi.fastjson.parser.DefaultJSONParser.ResolveTask) r2)     // Catch:{ all -> 0x0300 }
            r8.a((int) r15)     // Catch:{ all -> 0x0300 }
        L_0x012e:
            r10.a((int) r14)     // Catch:{ all -> 0x0300 }
            int r0 = r10.d()     // Catch:{ all -> 0x0300 }
            if (r0 != r14) goto L_0x0146
            r10.a((int) r12)     // Catch:{ all -> 0x0300 }
            r8.a((com.imi.fastjson.parser.ParseContext) r13, (java.lang.Object) r1, (java.lang.Object) r9)     // Catch:{ all -> 0x0300 }
            if (r3 == 0) goto L_0x0142
            r3.a((java.lang.Object) r1)
        L_0x0142:
            r8.a((com.imi.fastjson.parser.ParseContext) r13)
            return r1
        L_0x0146:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0300 }
            java.lang.String r2 = "illegal ref"
            r0.<init>(r2)     // Catch:{ all -> 0x0300 }
            throw r0     // Catch:{ all -> 0x0300 }
        L_0x014e:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0300 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0300 }
            r2.<init>()     // Catch:{ all -> 0x0300 }
            java.lang.String r4 = "illegal ref, "
            r2.append(r4)     // Catch:{ all -> 0x0300 }
            int r4 = r10.d()     // Catch:{ all -> 0x0300 }
            java.lang.String r4 = com.imi.fastjson.parser.JSONToken.a(r4)     // Catch:{ all -> 0x0300 }
            r2.append(r4)     // Catch:{ all -> 0x0300 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0300 }
            r0.<init>(r2)     // Catch:{ all -> 0x0300 }
            throw r0     // Catch:{ all -> 0x0300 }
        L_0x016d:
            java.lang.String r5 = com.imi.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0300 }
            if (r5 != r4) goto L_0x01c1
            r10.b((int) r6)     // Catch:{ all -> 0x0300 }
            int r4 = r10.d()     // Catch:{ all -> 0x0300 }
            if (r4 != r6) goto L_0x01b9
            java.lang.String r4 = r10.z()     // Catch:{ all -> 0x0300 }
            r10.a((int) r12)     // Catch:{ all -> 0x0300 }
            boolean r5 = r0 instanceof java.lang.Class     // Catch:{ all -> 0x0300 }
            if (r5 == 0) goto L_0x01a0
            r5 = r0
            java.lang.Class r5 = (java.lang.Class) r5     // Catch:{ all -> 0x0300 }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0300 }
            boolean r5 = r4.equals(r5)     // Catch:{ all -> 0x0300 }
            if (r5 == 0) goto L_0x01a0
            int r4 = r10.d()     // Catch:{ all -> 0x0300 }
            if (r4 != r14) goto L_0x019d
            r10.a()     // Catch:{ all -> 0x0300 }
            goto L_0x021e
        L_0x019d:
            r15 = 0
            goto L_0x008e
        L_0x01a0:
            java.lang.Class r0 = com.imi.fastjson.util.TypeUtils.a((java.lang.String) r4)     // Catch:{ all -> 0x0300 }
            com.imi.fastjson.parser.ParserConfig r2 = r20.e()     // Catch:{ all -> 0x0300 }
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.a((java.lang.reflect.Type) r0)     // Catch:{ all -> 0x0300 }
            java.lang.Object r0 = r2.a(r8, r0, r9)     // Catch:{ all -> 0x0300 }
            if (r3 == 0) goto L_0x01b5
            r3.a((java.lang.Object) r1)
        L_0x01b5:
            r8.a((com.imi.fastjson.parser.ParseContext) r13)
            return r0
        L_0x01b9:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0300 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0300 }
            throw r0     // Catch:{ all -> 0x0300 }
        L_0x01c1:
            if (r1 != 0) goto L_0x01e6
            if (r2 != 0) goto L_0x01e6
            java.lang.Object r5 = r19.a(r20, r21)     // Catch:{ all -> 0x0300 }
            if (r5 != 0) goto L_0x01db
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x01d7 }
            java.util.List<com.imi.fastjson.parser.deserializer.FieldDeserializer> r1 = r7.b     // Catch:{ all -> 0x01d7 }
            int r1 = r1.size()     // Catch:{ all -> 0x01d7 }
            r2.<init>(r1)     // Catch:{ all -> 0x01d7 }
            goto L_0x01db
        L_0x01d7:
            r0 = move-exception
            r1 = r5
            goto L_0x0306
        L_0x01db:
            com.imi.fastjson.parser.ParseContext r1 = r8.a((com.imi.fastjson.parser.ParseContext) r13, (java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ all -> 0x01d7 }
            r18 = r1
            r17 = r2
            r16 = r5
            goto L_0x01ec
        L_0x01e6:
            r16 = r1
            r17 = r2
            r18 = r3
        L_0x01ec:
            r1 = r19
            r2 = r20
            r3 = r4
            r4 = r16
            r5 = r21
            r6 = r17
            boolean r1 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x02fa }
            if (r1 != 0) goto L_0x0207
            int r1 = r10.d()     // Catch:{ all -> 0x02fa }
            if (r1 != r14) goto L_0x02d3
            r10.a()     // Catch:{ all -> 0x02fa }
            goto L_0x0218
        L_0x0207:
            int r1 = r10.d()     // Catch:{ all -> 0x02fa }
            if (r1 != r12) goto L_0x020f
            goto L_0x02d3
        L_0x020f:
            int r1 = r10.d()     // Catch:{ all -> 0x02fa }
            if (r1 != r14) goto L_0x02c5
            r10.a((int) r12)     // Catch:{ all -> 0x02fa }
        L_0x0218:
            r1 = r16
            r2 = r17
            r3 = r18
        L_0x021e:
            if (r1 != 0) goto L_0x02bb
            if (r2 != 0) goto L_0x022f
            java.lang.Object r0 = r19.a(r20, r21)     // Catch:{ all -> 0x0300 }
            if (r3 == 0) goto L_0x022b
            r3.a((java.lang.Object) r0)
        L_0x022b:
            r8.a((com.imi.fastjson.parser.ParseContext) r13)
            return r0
        L_0x022f:
            com.imi.fastjson.util.DeserializeBeanInfo r0 = r7.d     // Catch:{ all -> 0x0300 }
            java.util.List r0 = r0.e()     // Catch:{ all -> 0x0300 }
            int r4 = r0.size()     // Catch:{ all -> 0x0300 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0300 }
            r6 = 0
        L_0x023c:
            if (r6 >= r4) goto L_0x0251
            java.lang.Object r9 = r0.get(r6)     // Catch:{ all -> 0x0300 }
            com.imi.fastjson.util.FieldInfo r9 = (com.imi.fastjson.util.FieldInfo) r9     // Catch:{ all -> 0x0300 }
            java.lang.String r9 = r9.d()     // Catch:{ all -> 0x0300 }
            java.lang.Object r9 = r2.get(r9)     // Catch:{ all -> 0x0300 }
            r5[r6] = r9     // Catch:{ all -> 0x0300 }
            int r6 = r6 + 1
            goto L_0x023c
        L_0x0251:
            com.imi.fastjson.util.DeserializeBeanInfo r0 = r7.d     // Catch:{ all -> 0x0300 }
            java.lang.reflect.Constructor r0 = r0.c()     // Catch:{ all -> 0x0300 }
            if (r0 == 0) goto L_0x0286
            com.imi.fastjson.util.DeserializeBeanInfo r0 = r7.d     // Catch:{ Exception -> 0x0264 }
            java.lang.reflect.Constructor r0 = r0.c()     // Catch:{ Exception -> 0x0264 }
            java.lang.Object r0 = r0.newInstance(r5)     // Catch:{ Exception -> 0x0264 }
            goto L_0x02bc
        L_0x0264:
            r0 = move-exception
            com.imi.fastjson.JSONException r2 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0300 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0300 }
            r4.<init>()     // Catch:{ all -> 0x0300 }
            java.lang.String r5 = "create instance error, "
            r4.append(r5)     // Catch:{ all -> 0x0300 }
            com.imi.fastjson.util.DeserializeBeanInfo r5 = r7.d     // Catch:{ all -> 0x0300 }
            java.lang.reflect.Constructor r5 = r5.c()     // Catch:{ all -> 0x0300 }
            java.lang.String r5 = r5.toGenericString()     // Catch:{ all -> 0x0300 }
            r4.append(r5)     // Catch:{ all -> 0x0300 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0300 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0300 }
            throw r2     // Catch:{ all -> 0x0300 }
        L_0x0286:
            com.imi.fastjson.util.DeserializeBeanInfo r0 = r7.d     // Catch:{ all -> 0x0300 }
            java.lang.reflect.Method r0 = r0.d()     // Catch:{ all -> 0x0300 }
            if (r0 == 0) goto L_0x02bb
            com.imi.fastjson.util.DeserializeBeanInfo r0 = r7.d     // Catch:{ Exception -> 0x0299 }
            java.lang.reflect.Method r0 = r0.d()     // Catch:{ Exception -> 0x0299 }
            java.lang.Object r0 = r0.invoke(r11, r5)     // Catch:{ Exception -> 0x0299 }
            goto L_0x02bc
        L_0x0299:
            r0 = move-exception
            com.imi.fastjson.JSONException r2 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0300 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0300 }
            r4.<init>()     // Catch:{ all -> 0x0300 }
            java.lang.String r5 = "create factory method error, "
            r4.append(r5)     // Catch:{ all -> 0x0300 }
            com.imi.fastjson.util.DeserializeBeanInfo r5 = r7.d     // Catch:{ all -> 0x0300 }
            java.lang.reflect.Method r5 = r5.d()     // Catch:{ all -> 0x0300 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0300 }
            r4.append(r5)     // Catch:{ all -> 0x0300 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0300 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0300 }
            throw r2     // Catch:{ all -> 0x0300 }
        L_0x02bb:
            r0 = r1
        L_0x02bc:
            if (r3 == 0) goto L_0x02c1
            r3.a((java.lang.Object) r0)
        L_0x02c1:
            r8.a((com.imi.fastjson.parser.ParseContext) r13)
            return r0
        L_0x02c5:
            int r1 = r10.d()     // Catch:{ all -> 0x02fa }
            r2 = 18
            if (r1 == r2) goto L_0x02db
            int r1 = r10.d()     // Catch:{ all -> 0x02fa }
            if (r1 == r15) goto L_0x02db
        L_0x02d3:
            r1 = r16
            r2 = r17
            r3 = r18
            goto L_0x019d
        L_0x02db:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x02fa }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fa }
            r1.<init>()     // Catch:{ all -> 0x02fa }
            java.lang.String r2 = "syntax error, unexpect token "
            r1.append(r2)     // Catch:{ all -> 0x02fa }
            int r2 = r10.d()     // Catch:{ all -> 0x02fa }
            java.lang.String r2 = com.imi.fastjson.parser.JSONToken.a(r2)     // Catch:{ all -> 0x02fa }
            r1.append(r2)     // Catch:{ all -> 0x02fa }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x02fa }
            r0.<init>(r1)     // Catch:{ all -> 0x02fa }
            throw r0     // Catch:{ all -> 0x02fa }
        L_0x02fa:
            r0 = move-exception
            r1 = r16
            r3 = r18
            goto L_0x0306
        L_0x0300:
            r0 = move-exception
            goto L_0x0306
        L_0x0302:
            r0 = move-exception
            r1 = r23
            r3 = r11
        L_0x0306:
            if (r3 == 0) goto L_0x030b
            r3.a((java.lang.Object) r1)
        L_0x030b:
            r8.a((com.imi.fastjson.parser.ParseContext) r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.deserializer.JavaBeanDeserializer.a(com.imi.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public boolean a(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        JSONLexer n = defaultJSONParser.n();
        FieldDeserializer fieldDeserializer = this.f6115a.get(str);
        if (fieldDeserializer == null) {
            Iterator<Map.Entry<String, FieldDeserializer>> it = this.f6115a.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (((String) next.getKey()).equalsIgnoreCase(str)) {
                    fieldDeserializer = (FieldDeserializer) next.getValue();
                    break;
                }
            }
        }
        if (fieldDeserializer != null) {
            n.b(fieldDeserializer.a());
            fieldDeserializer.a(defaultJSONParser, obj, type, map);
            return true;
        } else if (defaultJSONParser.a(Feature.IgnoreNotMatch)) {
            n.c();
            defaultJSONParser.l();
            return false;
        } else {
            throw new JSONException("setter not found, class " + this.c.getName() + ", property " + str);
        }
    }
}
