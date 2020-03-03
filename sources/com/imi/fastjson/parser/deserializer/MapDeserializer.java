package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.JSONToken;
import com.imi.fastjson.parser.ParseContext;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final MapDeserializer f6119a = new MapDeserializer();

    public int a() {
        return 12;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 8) {
            n.a(16);
            return null;
        }
        Map<Object, Object> a2 = a(type);
        ParseContext h = defaultJSONParser.h();
        try {
            defaultJSONParser.a(h, (Object) a2, obj);
            return a(defaultJSONParser, type, obj, (Map) a2);
        } finally {
            defaultJSONParser.a(h);
        }
    }

    /* access modifiers changed from: protected */
    public Object a(DefaultJSONParser defaultJSONParser, Type type, Object obj, Map map) {
        if (!(type instanceof ParameterizedType)) {
            return defaultJSONParser.a(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type type2 = parameterizedType.getActualTypeArguments()[0];
        Type type3 = parameterizedType.getActualTypeArguments()[1];
        if (String.class == type2) {
            return a(defaultJSONParser, (Map<String, Object>) map, type3, obj);
        }
        return a(defaultJSONParser, map, type2, type3, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r8 = r7.e().a((java.lang.reflect.Type) r2);
        r0.a(16);
        r7.a(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0112, code lost:
        if (r1 == null) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0116, code lost:
        if ((r10 instanceof java.lang.Integer) != false) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0118, code lost:
        r7.k();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x011b, code lost:
        r8 = (java.util.Map) r8.a(r7, r2, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0121, code lost:
        r7.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0124, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map a(com.imi.fastjson.parser.DefaultJSONParser r7, java.util.Map<java.lang.String, java.lang.Object> r8, java.lang.reflect.Type r9, java.lang.Object r10) {
        /*
            com.imi.fastjson.parser.JSONLexer r0 = r7.n()
            int r1 = r0.d()
            r2 = 12
            if (r1 != r2) goto L_0x018c
            com.imi.fastjson.parser.ParseContext r1 = r7.h()
        L_0x0010:
            r0.B()     // Catch:{ all -> 0x0187 }
            char r2 = r0.m()     // Catch:{ all -> 0x0187 }
            com.imi.fastjson.parser.Feature r3 = com.imi.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0187 }
            boolean r3 = r7.a((com.imi.fastjson.parser.Feature) r3)     // Catch:{ all -> 0x0187 }
            if (r3 == 0) goto L_0x002e
        L_0x001f:
            r3 = 44
            if (r2 != r3) goto L_0x002e
            r0.n()     // Catch:{ all -> 0x0187 }
            r0.B()     // Catch:{ all -> 0x0187 }
            char r2 = r0.m()     // Catch:{ all -> 0x0187 }
            goto L_0x001f
        L_0x002e:
            r3 = 58
            r4 = 34
            r5 = 16
            if (r2 != r4) goto L_0x0064
            com.imi.fastjson.parser.SymbolTable r2 = r7.c()     // Catch:{ all -> 0x0187 }
            java.lang.String r2 = r0.a((com.imi.fastjson.parser.SymbolTable) r2, (char) r4)     // Catch:{ all -> 0x0187 }
            r0.B()     // Catch:{ all -> 0x0187 }
            char r6 = r0.m()     // Catch:{ all -> 0x0187 }
            if (r6 != r3) goto L_0x0049
            goto L_0x00cf
        L_0x0049:
            com.imi.fastjson.JSONException r8 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0187 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0187 }
            r9.<init>()     // Catch:{ all -> 0x0187 }
            java.lang.String r10 = "expect ':' at "
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            int r10 = r0.f()     // Catch:{ all -> 0x0187 }
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0187 }
            r8.<init>(r9)     // Catch:{ all -> 0x0187 }
            throw r8     // Catch:{ all -> 0x0187 }
        L_0x0064:
            r6 = 125(0x7d, float:1.75E-43)
            if (r2 != r6) goto L_0x0075
            r0.n()     // Catch:{ all -> 0x0187 }
            r0.o()     // Catch:{ all -> 0x0187 }
            r0.a((int) r5)     // Catch:{ all -> 0x0187 }
            r7.a((com.imi.fastjson.parser.ParseContext) r1)
            return r8
        L_0x0075:
            r6 = 39
            if (r2 != r6) goto L_0x00b6
            com.imi.fastjson.parser.Feature r2 = com.imi.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0187 }
            boolean r2 = r7.a((com.imi.fastjson.parser.Feature) r2)     // Catch:{ all -> 0x0187 }
            if (r2 == 0) goto L_0x00ae
            com.imi.fastjson.parser.SymbolTable r2 = r7.c()     // Catch:{ all -> 0x0187 }
            java.lang.String r2 = r0.a((com.imi.fastjson.parser.SymbolTable) r2, (char) r6)     // Catch:{ all -> 0x0187 }
            r0.B()     // Catch:{ all -> 0x0187 }
            char r6 = r0.m()     // Catch:{ all -> 0x0187 }
            if (r6 != r3) goto L_0x0093
            goto L_0x00cf
        L_0x0093:
            com.imi.fastjson.JSONException r8 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0187 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0187 }
            r9.<init>()     // Catch:{ all -> 0x0187 }
            java.lang.String r10 = "expect ':' at "
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            int r10 = r0.f()     // Catch:{ all -> 0x0187 }
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0187 }
            r8.<init>(r9)     // Catch:{ all -> 0x0187 }
            throw r8     // Catch:{ all -> 0x0187 }
        L_0x00ae:
            com.imi.fastjson.JSONException r8 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0187 }
            java.lang.String r9 = "syntax error"
            r8.<init>(r9)     // Catch:{ all -> 0x0187 }
            throw r8     // Catch:{ all -> 0x0187 }
        L_0x00b6:
            com.imi.fastjson.parser.Feature r2 = com.imi.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0187 }
            boolean r2 = r7.a((com.imi.fastjson.parser.Feature) r2)     // Catch:{ all -> 0x0187 }
            if (r2 == 0) goto L_0x017f
            com.imi.fastjson.parser.SymbolTable r2 = r7.c()     // Catch:{ all -> 0x0187 }
            java.lang.String r2 = r0.b((com.imi.fastjson.parser.SymbolTable) r2)     // Catch:{ all -> 0x0187 }
            r0.B()     // Catch:{ all -> 0x0187 }
            char r6 = r0.m()     // Catch:{ all -> 0x0187 }
            if (r6 != r3) goto L_0x015c
        L_0x00cf:
            r0.n()     // Catch:{ all -> 0x0187 }
            r0.B()     // Catch:{ all -> 0x0187 }
            r0.m()     // Catch:{ all -> 0x0187 }
            r0.o()     // Catch:{ all -> 0x0187 }
            java.lang.String r3 = com.imi.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0187 }
            r6 = 13
            if (r2 != r3) goto L_0x0125
            com.imi.fastjson.parser.SymbolTable r2 = r7.c()     // Catch:{ all -> 0x0187 }
            java.lang.String r2 = r0.a((com.imi.fastjson.parser.SymbolTable) r2, (char) r4)     // Catch:{ all -> 0x0187 }
            java.lang.Class r2 = com.imi.fastjson.util.TypeUtils.a((java.lang.String) r2)     // Catch:{ all -> 0x0187 }
            java.lang.Class r3 = r8.getClass()     // Catch:{ all -> 0x0187 }
            if (r2 != r3) goto L_0x0103
            r0.a((int) r5)     // Catch:{ all -> 0x0187 }
            int r2 = r0.d()     // Catch:{ all -> 0x0187 }
            if (r2 != r6) goto L_0x0010
            r0.a((int) r5)     // Catch:{ all -> 0x0187 }
            r7.a((com.imi.fastjson.parser.ParseContext) r1)
            return r8
        L_0x0103:
            com.imi.fastjson.parser.ParserConfig r8 = r7.e()     // Catch:{ all -> 0x0187 }
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r8 = r8.a((java.lang.reflect.Type) r2)     // Catch:{ all -> 0x0187 }
            r0.a((int) r5)     // Catch:{ all -> 0x0187 }
            r9 = 2
            r7.a((int) r9)     // Catch:{ all -> 0x0187 }
            if (r1 == 0) goto L_0x011b
            boolean r9 = r10 instanceof java.lang.Integer     // Catch:{ all -> 0x0187 }
            if (r9 != 0) goto L_0x011b
            r7.k()     // Catch:{ all -> 0x0187 }
        L_0x011b:
            java.lang.Object r8 = r8.a(r7, r2, r10)     // Catch:{ all -> 0x0187 }
            java.util.Map r8 = (java.util.Map) r8     // Catch:{ all -> 0x0187 }
            r7.a((com.imi.fastjson.parser.ParseContext) r1)
            return r8
        L_0x0125:
            r0.a()     // Catch:{ all -> 0x0187 }
            int r3 = r0.d()     // Catch:{ all -> 0x0187 }
            r4 = 8
            if (r3 != r4) goto L_0x0135
            r3 = 0
            r0.a()     // Catch:{ all -> 0x0187 }
            goto L_0x0139
        L_0x0135:
            java.lang.Object r3 = r7.a((java.lang.reflect.Type) r9)     // Catch:{ all -> 0x0187 }
        L_0x0139:
            r8.put(r2, r3)     // Catch:{ all -> 0x0187 }
            r7.a((java.util.Map) r8, (java.lang.String) r2)     // Catch:{ all -> 0x0187 }
            r7.a((com.imi.fastjson.parser.ParseContext) r1, (java.lang.Object) r3, (java.lang.Object) r2)     // Catch:{ all -> 0x0187 }
            int r2 = r0.d()     // Catch:{ all -> 0x0187 }
            r3 = 20
            if (r2 == r3) goto L_0x0158
            r3 = 15
            if (r2 != r3) goto L_0x014f
            goto L_0x0158
        L_0x014f:
            if (r2 != r6) goto L_0x0010
            r0.a()     // Catch:{ all -> 0x0187 }
            r7.a((com.imi.fastjson.parser.ParseContext) r1)
            return r8
        L_0x0158:
            r7.a((com.imi.fastjson.parser.ParseContext) r1)
            return r8
        L_0x015c:
            com.imi.fastjson.JSONException r8 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0187 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0187 }
            r9.<init>()     // Catch:{ all -> 0x0187 }
            java.lang.String r10 = "expect ':' at "
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            int r10 = r0.f()     // Catch:{ all -> 0x0187 }
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            java.lang.String r10 = ", actual "
            r9.append(r10)     // Catch:{ all -> 0x0187 }
            r9.append(r6)     // Catch:{ all -> 0x0187 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0187 }
            r8.<init>(r9)     // Catch:{ all -> 0x0187 }
            throw r8     // Catch:{ all -> 0x0187 }
        L_0x017f:
            com.imi.fastjson.JSONException r8 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x0187 }
            java.lang.String r9 = "syntax error"
            r8.<init>(r9)     // Catch:{ all -> 0x0187 }
            throw r8     // Catch:{ all -> 0x0187 }
        L_0x0187:
            r8 = move-exception
            r7.a((com.imi.fastjson.parser.ParseContext) r1)
            throw r8
        L_0x018c:
            com.imi.fastjson.JSONException r7 = new com.imi.fastjson.JSONException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "syntax error, expect {, actual "
            r8.append(r9)
            int r9 = r0.d()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.deserializer.MapDeserializer.a(com.imi.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    public static Object a(DefaultJSONParser defaultJSONParser, Map<Object, Object> map, Type type, Type type2, Object obj) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 12 || n.d() == 16) {
            ObjectDeserializer a2 = defaultJSONParser.e().a(type);
            ObjectDeserializer a3 = defaultJSONParser.e().a(type2);
            n.a(a2.a());
            ParseContext h = defaultJSONParser.h();
            while (n.d() != 13) {
                try {
                    Object obj2 = null;
                    if (n.d() != 4 || !n.t()) {
                        if (map.size() == 0 && n.d() == 4 && JSON.DEFAULT_TYPE_KEY.equals(n.z())) {
                            n.b(4);
                            n.a(16);
                            if (n.d() == 13) {
                                n.a();
                                return map;
                            }
                            n.a(a2.a());
                        }
                        Object a4 = a2.a(defaultJSONParser, type, (Object) null);
                        if (n.d() == 17) {
                            n.a(a3.a());
                            map.put(a4, a3.a(defaultJSONParser, type2, a4));
                            if (n.d() == 16) {
                                n.a(a2.a());
                            }
                        } else {
                            throw new JSONException("syntax error, expect :, actual " + n.d());
                        }
                    } else {
                        n.b(4);
                        if (n.d() == 4) {
                            String z = n.z();
                            if ("..".equals(z)) {
                                obj2 = h.c().b();
                            } else if (Operators.DOLLAR_STR.equals(z)) {
                                ParseContext parseContext = h;
                                while (parseContext.c() != null) {
                                    parseContext = parseContext.c();
                                }
                                obj2 = parseContext.b();
                            } else {
                                defaultJSONParser.a(new DefaultJSONParser.ResolveTask(h, z));
                                defaultJSONParser.a(1);
                            }
                            n.a(13);
                            if (n.d() == 13) {
                                n.a(16);
                                defaultJSONParser.a(h);
                                return obj2;
                            }
                            throw new JSONException("illegal ref");
                        }
                        throw new JSONException("illegal ref, " + JSONToken.a(n.d()));
                    }
                } finally {
                    defaultJSONParser.a(h);
                }
            }
            n.a(16);
            defaultJSONParser.a(h);
            return map;
        }
        throw new JSONException("syntax error, expect {, actual " + n.e());
    }

    /* access modifiers changed from: protected */
    public Map<Object, Object> a(Type type) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type instanceof ParameterizedType) {
            return a(((ParameterizedType) type).getRawType());
        }
        Class cls = (Class) type;
        if (!cls.isInterface()) {
            try {
                return (Map) cls.newInstance();
            } catch (Exception e) {
                throw new JSONException("unsupport type " + type, e);
            }
        } else {
            throw new JSONException("unsupport type " + type);
        }
    }
}
