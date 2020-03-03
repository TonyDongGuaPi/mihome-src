package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
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

class MapDeserializer implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    MapDeserializer() {
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == JSONObject.class && defaultJSONParser.fieldTypeResolver == null) {
            return defaultJSONParser.parseObject();
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        Map<?, ?> createMap = createMap(type);
        ParseContext parseContext = defaultJSONParser.contex;
        try {
            defaultJSONParser.setContext(parseContext, createMap, obj);
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type type2 = parameterizedType.getActualTypeArguments()[0];
                Type type3 = parameterizedType.getActualTypeArguments()[1];
                if (String.class == type2) {
                    return parseMap(defaultJSONParser, createMap, type3, obj);
                }
                T parseMap = parseMap(defaultJSONParser, createMap, type2, type3, obj);
                defaultJSONParser.setContext(parseContext);
                return parseMap;
            }
            T parseObject = defaultJSONParser.parseObject((Map) createMap, obj);
            defaultJSONParser.setContext(parseContext);
            return parseObject;
        } finally {
            defaultJSONParser.setContext(parseContext);
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r9 = r8.config.getDeserializer(r2);
        r0.nextToken(16);
        r8.resolveStatus = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e1, code lost:
        if (r1 == null) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e5, code lost:
        if ((r11 instanceof java.lang.Integer) != false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e7, code lost:
        r8.popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ea, code lost:
        r9 = (java.util.Map) r9.deserialze(r8, r2, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f0, code lost:
        r8.setContext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f3, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r8, java.util.Map<java.lang.String, java.lang.Object> r9, java.lang.reflect.Type r10, java.lang.Object r11) {
        /*
            com.alibaba.fastjson.parser.JSONLexer r0 = r8.lexer
            int r1 = r0.token
            r2 = 12
            if (r1 != r2) goto L_0x0154
            com.alibaba.fastjson.parser.ParseContext r1 = r8.contex
        L_0x000a:
            r0.skipWhitespace()     // Catch:{ all -> 0x014f }
            char r2 = r0.ch     // Catch:{ all -> 0x014f }
        L_0x000f:
            r3 = 44
            if (r2 != r3) goto L_0x001c
            r0.next()     // Catch:{ all -> 0x014f }
            r0.skipWhitespace()     // Catch:{ all -> 0x014f }
            char r2 = r0.ch     // Catch:{ all -> 0x014f }
            goto L_0x000f
        L_0x001c:
            r3 = 0
            r4 = 58
            r5 = 34
            r6 = 16
            if (r2 != r5) goto L_0x004f
            com.alibaba.fastjson.parser.SymbolTable r2 = r8.symbolTable     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r0.scanSymbol(r2, r5)     // Catch:{ all -> 0x014f }
            r0.skipWhitespace()     // Catch:{ all -> 0x014f }
            char r7 = r0.ch     // Catch:{ all -> 0x014f }
            if (r7 != r4) goto L_0x0033
            goto L_0x009a
        L_0x0033:
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x014f }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r10.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r11 = "syntax error, "
            r10.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r0.info()     // Catch:{ all -> 0x014f }
            r10.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x014f }
            r9.<init>(r10)     // Catch:{ all -> 0x014f }
            throw r9     // Catch:{ all -> 0x014f }
        L_0x004f:
            r7 = 125(0x7d, float:1.75E-43)
            if (r2 != r7) goto L_0x005f
            r0.next()     // Catch:{ all -> 0x014f }
            r0.sp = r3     // Catch:{ all -> 0x014f }
            r0.nextToken(r6)     // Catch:{ all -> 0x014f }
            r8.setContext(r1)
            return r9
        L_0x005f:
            r7 = 39
            if (r2 != r7) goto L_0x008d
            com.alibaba.fastjson.parser.SymbolTable r2 = r8.symbolTable     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r0.scanSymbol(r2, r7)     // Catch:{ all -> 0x014f }
            r0.skipWhitespace()     // Catch:{ all -> 0x014f }
            char r7 = r0.ch     // Catch:{ all -> 0x014f }
            if (r7 != r4) goto L_0x0071
            goto L_0x009a
        L_0x0071:
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x014f }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r10.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r11 = "syntax error, "
            r10.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r0.info()     // Catch:{ all -> 0x014f }
            r10.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x014f }
            r9.<init>(r10)     // Catch:{ all -> 0x014f }
            throw r9     // Catch:{ all -> 0x014f }
        L_0x008d:
            com.alibaba.fastjson.parser.SymbolTable r2 = r8.symbolTable     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r0.scanSymbolUnQuoted(r2)     // Catch:{ all -> 0x014f }
            r0.skipWhitespace()     // Catch:{ all -> 0x014f }
            char r7 = r0.ch     // Catch:{ all -> 0x014f }
            if (r7 != r4) goto L_0x012e
        L_0x009a:
            r0.next()     // Catch:{ all -> 0x014f }
            r0.skipWhitespace()     // Catch:{ all -> 0x014f }
            char r4 = r0.ch     // Catch:{ all -> 0x014f }
            r0.sp = r3     // Catch:{ all -> 0x014f }
            java.lang.String r3 = "@type"
            r4 = 13
            r7 = 0
            if (r2 != r3) goto L_0x00f4
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x014f }
            boolean r3 = r0.isEnabled(r3)     // Catch:{ all -> 0x014f }
            if (r3 != 0) goto L_0x00f4
            com.alibaba.fastjson.parser.SymbolTable r2 = r8.symbolTable     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r0.scanSymbol(r2, r5)     // Catch:{ all -> 0x014f }
            com.alibaba.fastjson.parser.ParserConfig r3 = r8.config     // Catch:{ all -> 0x014f }
            int r5 = r0.features     // Catch:{ all -> 0x014f }
            java.lang.Class r2 = r3.checkAutoType(r2, r7, r5)     // Catch:{ all -> 0x014f }
            java.lang.Class r3 = r9.getClass()     // Catch:{ all -> 0x014f }
            if (r2 != r3) goto L_0x00d5
            r0.nextToken(r6)     // Catch:{ all -> 0x014f }
            int r2 = r0.token     // Catch:{ all -> 0x014f }
            if (r2 != r4) goto L_0x000a
            r0.nextToken(r6)     // Catch:{ all -> 0x014f }
            r8.setContext(r1)
            return r9
        L_0x00d5:
            com.alibaba.fastjson.parser.ParserConfig r9 = r8.config     // Catch:{ all -> 0x014f }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r9 = r9.getDeserializer(r2)     // Catch:{ all -> 0x014f }
            r0.nextToken(r6)     // Catch:{ all -> 0x014f }
            r10 = 2
            r8.resolveStatus = r10     // Catch:{ all -> 0x014f }
            if (r1 == 0) goto L_0x00ea
            boolean r10 = r11 instanceof java.lang.Integer     // Catch:{ all -> 0x014f }
            if (r10 != 0) goto L_0x00ea
            r8.popContext()     // Catch:{ all -> 0x014f }
        L_0x00ea:
            java.lang.Object r9 = r9.deserialze(r8, r2, r11)     // Catch:{ all -> 0x014f }
            java.util.Map r9 = (java.util.Map) r9     // Catch:{ all -> 0x014f }
            r8.setContext(r1)
            return r9
        L_0x00f4:
            r0.nextToken()     // Catch:{ all -> 0x014f }
            r8.setContext(r1)     // Catch:{ all -> 0x014f }
            int r3 = r0.token     // Catch:{ all -> 0x014f }
            r5 = 8
            if (r3 != r5) goto L_0x0104
            r0.nextToken()     // Catch:{ all -> 0x014f }
            goto L_0x0108
        L_0x0104:
            java.lang.Object r7 = r8.parseObject((java.lang.reflect.Type) r10, (java.lang.Object) r2)     // Catch:{ all -> 0x014f }
        L_0x0108:
            r9.put(r2, r7)     // Catch:{ all -> 0x014f }
            int r3 = r8.resolveStatus     // Catch:{ all -> 0x014f }
            r5 = 1
            if (r3 != r5) goto L_0x0113
            r8.checkMapResolve(r9, r2)     // Catch:{ all -> 0x014f }
        L_0x0113:
            r8.setContext(r1, r7, r2)     // Catch:{ all -> 0x014f }
            int r2 = r0.token     // Catch:{ all -> 0x014f }
            r3 = 20
            if (r2 == r3) goto L_0x012a
            r3 = 15
            if (r2 != r3) goto L_0x0121
            goto L_0x012a
        L_0x0121:
            if (r2 != r4) goto L_0x000a
            r0.nextToken()     // Catch:{ all -> 0x014f }
            r8.setContext(r1)
            return r9
        L_0x012a:
            r8.setContext(r1)
            return r9
        L_0x012e:
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x014f }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r10.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r11 = "expect ':' at "
            r10.append(r11)     // Catch:{ all -> 0x014f }
            int r11 = r0.pos     // Catch:{ all -> 0x014f }
            r10.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = ", actual "
            r10.append(r11)     // Catch:{ all -> 0x014f }
            r10.append(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x014f }
            r9.<init>(r10)     // Catch:{ all -> 0x014f }
            throw r9     // Catch:{ all -> 0x014f }
        L_0x014f:
            r9 = move-exception
            r8.setContext(r1)
            throw r9
        L_0x0154:
            com.alibaba.fastjson.JSONException r8 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "syntax error, expect {, actual "
            r9.append(r10)
            int r10 = r0.token
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        r4.nextTokenWithChar(com.taobao.weex.el.parse.Operators.CONDITION_IF_MIDDLE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0073, code lost:
        if (r4.token != 4) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        r0 = r4.stringVal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007f, code lost:
        if ("..".equals(r0) == false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0081, code lost:
        r12 = r8.parent.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008c, code lost:
        if (com.taobao.weex.el.parse.Operators.DOLLAR_STR.equals(r0) == false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008e, code lost:
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0091, code lost:
        if (r0.parent == null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0093, code lost:
        r0 = r0.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0096, code lost:
        r12 = r0.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0099, code lost:
        r1.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r8, r0));
        r1.resolveStatus = 1;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a4, code lost:
        r4.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a9, code lost:
        if (r4.token != 13) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ab, code lost:
        r4.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b0, code lost:
        r1.setContext(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b3, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bb, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d6, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r9));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r16, java.util.Map<java.lang.Object, java.lang.Object> r17, java.lang.reflect.Type r18, java.lang.reflect.Type r19, java.lang.Object r20) {
        /*
            r1 = r16
            r0 = r17
            r2 = r18
            r3 = r19
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer
            int r5 = r4.token
            r6 = 16
            r7 = 12
            if (r5 == r7) goto L_0x0031
            if (r5 != r6) goto L_0x0015
            goto L_0x0031
        L_0x0015:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "syntax error, expect {, actual "
            r1.append(r2)
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r5)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0031:
            com.alibaba.fastjson.parser.ParserConfig r5 = r1.config
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r5 = r5.getDeserializer(r2)
            com.alibaba.fastjson.parser.ParserConfig r7 = r1.config
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r7 = r7.getDeserializer(r3)
            r4.nextToken()
            com.alibaba.fastjson.parser.ParseContext r8 = r1.contex
        L_0x0042:
            int r9 = r4.token     // Catch:{ all -> 0x014c }
            r10 = 13
            if (r9 != r10) goto L_0x004f
            r4.nextToken(r6)     // Catch:{ all -> 0x014c }
            r1.setContext(r8)
            return r0
        L_0x004f:
            r11 = 58
            r13 = 1
            r14 = 4
            if (r9 != r14) goto L_0x00d7
            int r15 = r4.sp     // Catch:{ all -> 0x014c }
            if (r15 != r14) goto L_0x00d7
            java.lang.String r15 = r4.text     // Catch:{ all -> 0x014c }
            java.lang.String r12 = "$ref"
            int r6 = r4.np     // Catch:{ all -> 0x014c }
            int r6 = r6 + r13
            boolean r6 = r15.startsWith(r12, r6)     // Catch:{ all -> 0x014c }
            if (r6 == 0) goto L_0x00d7
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x014c }
            boolean r6 = r4.isEnabled(r6)     // Catch:{ all -> 0x014c }
            if (r6 != 0) goto L_0x00d7
            r4.nextTokenWithChar(r11)     // Catch:{ all -> 0x014c }
            int r0 = r4.token     // Catch:{ all -> 0x014c }
            if (r0 != r14) goto L_0x00bc
            java.lang.String r0 = r4.stringVal()     // Catch:{ all -> 0x014c }
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x014c }
            if (r2 == 0) goto L_0x0086
            com.alibaba.fastjson.parser.ParseContext r0 = r8.parent     // Catch:{ all -> 0x014c }
            java.lang.Object r12 = r0.object     // Catch:{ all -> 0x014c }
            goto L_0x00a4
        L_0x0086:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x014c }
            if (r2 == 0) goto L_0x0099
            r0 = r8
        L_0x008f:
            com.alibaba.fastjson.parser.ParseContext r2 = r0.parent     // Catch:{ all -> 0x014c }
            if (r2 == 0) goto L_0x0096
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x014c }
            goto L_0x008f
        L_0x0096:
            java.lang.Object r12 = r0.object     // Catch:{ all -> 0x014c }
            goto L_0x00a4
        L_0x0099:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x014c }
            r2.<init>(r8, r0)     // Catch:{ all -> 0x014c }
            r1.addResolveTask(r2)     // Catch:{ all -> 0x014c }
            r1.resolveStatus = r13     // Catch:{ all -> 0x014c }
            r12 = 0
        L_0x00a4:
            r4.nextToken(r10)     // Catch:{ all -> 0x014c }
            int r0 = r4.token     // Catch:{ all -> 0x014c }
            if (r0 != r10) goto L_0x00b4
            r0 = 16
            r4.nextToken(r0)     // Catch:{ all -> 0x014c }
            r1.setContext(r8)
            return r12
        L_0x00b4:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x014c }
            java.lang.String r2 = "illegal ref"
            r0.<init>(r2)     // Catch:{ all -> 0x014c }
            throw r0     // Catch:{ all -> 0x014c }
        L_0x00bc:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x014c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r2.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r3 = "illegal ref, "
            r2.append(r3)     // Catch:{ all -> 0x014c }
            java.lang.String r3 = com.alibaba.fastjson.parser.JSONToken.name(r9)     // Catch:{ all -> 0x014c }
            r2.append(r3)     // Catch:{ all -> 0x014c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x014c }
            r0.<init>(r2)     // Catch:{ all -> 0x014c }
            throw r0     // Catch:{ all -> 0x014c }
        L_0x00d7:
            int r6 = r17.size()     // Catch:{ all -> 0x014c }
            if (r6 != 0) goto L_0x0109
            if (r9 != r14) goto L_0x0109
            java.lang.String r6 = "@type"
            java.lang.String r9 = r4.stringVal()     // Catch:{ all -> 0x014c }
            boolean r6 = r6.equals(r9)     // Catch:{ all -> 0x014c }
            if (r6 == 0) goto L_0x0109
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x014c }
            boolean r6 = r4.isEnabled(r6)     // Catch:{ all -> 0x014c }
            if (r6 != 0) goto L_0x0109
            r4.nextTokenWithChar(r11)     // Catch:{ all -> 0x014c }
            r6 = 16
            r4.nextToken(r6)     // Catch:{ all -> 0x014c }
            int r6 = r4.token     // Catch:{ all -> 0x014c }
            if (r6 != r10) goto L_0x0106
            r4.nextToken()     // Catch:{ all -> 0x014c }
            r1.setContext(r8)
            return r0
        L_0x0106:
            r4.nextToken()     // Catch:{ all -> 0x014c }
        L_0x0109:
            r6 = 0
            java.lang.Object r6 = r5.deserialze(r1, r2, r6)     // Catch:{ all -> 0x014c }
            int r9 = r4.token     // Catch:{ all -> 0x014c }
            r10 = 17
            if (r9 != r10) goto L_0x0132
            r4.nextToken()     // Catch:{ all -> 0x014c }
            java.lang.Object r9 = r7.deserialze(r1, r3, r6)     // Catch:{ all -> 0x014c }
            int r10 = r1.resolveStatus     // Catch:{ all -> 0x014c }
            if (r10 != r13) goto L_0x0122
            r1.checkMapResolve(r0, r6)     // Catch:{ all -> 0x014c }
        L_0x0122:
            r0.put(r6, r9)     // Catch:{ all -> 0x014c }
            int r6 = r4.token     // Catch:{ all -> 0x014c }
            r9 = 16
            if (r6 != r9) goto L_0x012e
            r4.nextToken()     // Catch:{ all -> 0x014c }
        L_0x012e:
            r6 = 16
            goto L_0x0042
        L_0x0132:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x014c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r2.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r3 = "syntax error, expect :, actual "
            r2.append(r3)     // Catch:{ all -> 0x014c }
            int r3 = r4.token     // Catch:{ all -> 0x014c }
            r2.append(r3)     // Catch:{ all -> 0x014c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x014c }
            r0.<init>(r2)     // Catch:{ all -> 0x014c }
            throw r0     // Catch:{ all -> 0x014c }
        L_0x014c:
            r0 = move-exception
            r1.setContext(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Map<?, ?> createMap(Type type) {
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
        if (type == JSONObject.class) {
            return new JSONObject();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (EnumMap.class.equals(rawType)) {
                return new EnumMap((Class) parameterizedType.getActualTypeArguments()[0]);
            }
            return createMap(rawType);
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
