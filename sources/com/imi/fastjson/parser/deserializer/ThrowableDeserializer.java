package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.ParserConfig;
import java.lang.reflect.Constructor;

public class ThrowableDeserializer extends JavaBeanDeserializer {
    public int a() {
        return 12;
    }

    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        if (java.lang.Throwable.class.isAssignableFrom(r13) != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T a(com.imi.fastjson.parser.DefaultJSONParser r12, java.lang.reflect.Type r13, java.lang.Object r14) {
        /*
            r11 = this;
            com.imi.fastjson.parser.JSONLexer r14 = r12.n()
            int r0 = r14.d()
            r1 = 8
            r2 = 0
            if (r0 != r1) goto L_0x0011
            r14.a()
            return r2
        L_0x0011:
            int r0 = r12.f()
            r3 = 2
            if (r0 != r3) goto L_0x001d
            r0 = 0
            r12.a((int) r0)
            goto L_0x0025
        L_0x001d:
            int r0 = r14.d()
            r3 = 12
            if (r0 != r3) goto L_0x0108
        L_0x0025:
            if (r13 == 0) goto L_0x0036
            boolean r0 = r13 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0036
            java.lang.Class r13 = (java.lang.Class) r13
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            boolean r0 = r0.isAssignableFrom(r13)
            if (r0 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r13 = r2
        L_0x0037:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r3 = r13
            r13 = r2
            r4 = r13
            r5 = r4
        L_0x0040:
            com.imi.fastjson.parser.SymbolTable r6 = r12.c()
            java.lang.String r6 = r14.a((com.imi.fastjson.parser.SymbolTable) r6)
            r7 = 13
            r8 = 16
            if (r6 != 0) goto L_0x0068
            int r9 = r14.d()
            if (r9 != r7) goto L_0x0059
            r14.a((int) r8)
            goto L_0x00e6
        L_0x0059:
            int r9 = r14.d()
            if (r9 != r8) goto L_0x0068
            com.imi.fastjson.parser.Feature r9 = com.imi.fastjson.parser.Feature.AllowArbitraryCommas
            boolean r9 = r14.a((com.imi.fastjson.parser.Feature) r9)
            if (r9 == 0) goto L_0x0068
            goto L_0x0040
        L_0x0068:
            r9 = 4
            r14.b((int) r9)
            java.lang.String r10 = com.imi.fastjson.JSON.DEFAULT_TYPE_KEY
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x008e
            int r3 = r14.d()
            if (r3 != r9) goto L_0x0086
            java.lang.String r3 = r14.z()
            java.lang.Class r3 = com.imi.fastjson.util.TypeUtils.a((java.lang.String) r3)
            r14.a((int) r8)
            goto L_0x00dd
        L_0x0086:
            com.imi.fastjson.JSONException r12 = new com.imi.fastjson.JSONException
            java.lang.String r13 = "syntax error"
            r12.<init>(r13)
            throw r12
        L_0x008e:
            java.lang.String r10 = "message"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x00b4
            int r4 = r14.d()
            if (r4 != r1) goto L_0x009e
            r4 = r2
            goto L_0x00a8
        L_0x009e:
            int r4 = r14.d()
            if (r4 != r9) goto L_0x00ac
            java.lang.String r4 = r14.z()
        L_0x00a8:
            r14.a()
            goto L_0x00dd
        L_0x00ac:
            com.imi.fastjson.JSONException r12 = new com.imi.fastjson.JSONException
            java.lang.String r13 = "syntax error"
            r12.<init>(r13)
            throw r12
        L_0x00b4:
            java.lang.String r9 = "cause"
            boolean r9 = r9.equals(r6)
            if (r9 == 0) goto L_0x00c5
            java.lang.String r13 = "cause"
            java.lang.Object r13 = r11.a((com.imi.fastjson.parser.DefaultJSONParser) r12, (java.lang.reflect.Type) r2, (java.lang.Object) r13)
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            goto L_0x00dd
        L_0x00c5:
            java.lang.String r9 = "stackTrace"
            boolean r9 = r9.equals(r6)
            if (r9 == 0) goto L_0x00d6
            java.lang.Class<java.lang.StackTraceElement[]> r5 = java.lang.StackTraceElement[].class
            java.lang.Object r5 = r12.a(r5)
            java.lang.StackTraceElement[] r5 = (java.lang.StackTraceElement[]) r5
            goto L_0x00dd
        L_0x00d6:
            java.lang.Object r9 = r12.l()
            r0.put(r6, r9)
        L_0x00dd:
            int r6 = r14.d()
            if (r6 != r7) goto L_0x0040
            r14.a((int) r8)
        L_0x00e6:
            if (r3 != 0) goto L_0x00ee
            java.lang.Exception r12 = new java.lang.Exception
            r12.<init>(r4, r13)
            goto L_0x00f9
        L_0x00ee:
            java.lang.Throwable r12 = r11.a((java.lang.String) r4, (java.lang.Throwable) r13, (java.lang.Class<?>) r3)     // Catch:{ Exception -> 0x00ff }
            if (r12 != 0) goto L_0x00f9
            java.lang.Exception r12 = new java.lang.Exception     // Catch:{ Exception -> 0x00ff }
            r12.<init>(r4, r13)     // Catch:{ Exception -> 0x00ff }
        L_0x00f9:
            if (r5 == 0) goto L_0x00fe
            r12.setStackTrace(r5)
        L_0x00fe:
            return r12
        L_0x00ff:
            r12 = move-exception
            com.imi.fastjson.JSONException r13 = new com.imi.fastjson.JSONException
            java.lang.String r14 = "create instance error"
            r13.<init>(r14, r12)
            throw r13
        L_0x0108:
            com.imi.fastjson.JSONException r12 = new com.imi.fastjson.JSONException
            java.lang.String r13 = "syntax error"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.deserializer.ThrowableDeserializer.a(com.imi.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    private Throwable a(String str, Throwable th, Class<?> cls) throws Exception {
        Constructor constructor = null;
        Constructor constructor2 = null;
        Constructor constructor3 = null;
        for (Constructor constructor4 : cls.getConstructors()) {
            if (constructor4.getParameterTypes().length == 0) {
                constructor3 = constructor4;
            } else if (constructor4.getParameterTypes().length == 1 && constructor4.getParameterTypes()[0] == String.class) {
                constructor2 = constructor4;
            } else if (constructor4.getParameterTypes().length == 2 && constructor4.getParameterTypes()[0] == String.class && constructor4.getParameterTypes()[1] == Throwable.class) {
                constructor = constructor4;
            }
        }
        if (constructor != null) {
            return (Throwable) constructor.newInstance(new Object[]{str, th});
        } else if (constructor2 != null) {
            return (Throwable) constructor2.newInstance(new Object[]{str});
        } else if (constructor3 != null) {
            return (Throwable) constructor3.newInstance(new Object[0]);
        } else {
            return null;
        }
    }
}
