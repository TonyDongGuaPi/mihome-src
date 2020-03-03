package com.imi.fastjson.parser;

import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONArray;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.JSONObject;
import com.imi.fastjson.parser.deserializer.FieldDeserializer;
import com.imi.fastjson.parser.deserializer.IntegerDeserializer;
import com.imi.fastjson.parser.deserializer.ListResolveFieldDeserializer;
import com.imi.fastjson.parser.deserializer.LongDeserializer;
import com.imi.fastjson.parser.deserializer.MapResolveFieldDeserializer;
import com.imi.fastjson.parser.deserializer.ObjectDeserializer;
import com.imi.fastjson.parser.deserializer.StringDeserializer;
import com.imi.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DefaultJSONParser extends AbstractJSONParser implements Closeable {
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    private static final Set<Class<?>> i = new HashSet();

    /* renamed from: a  reason: collision with root package name */
    protected final Object f6085a;
    protected final SymbolTable b;
    protected ParserConfig c;
    protected final JSONLexer d;
    protected ParseContext e;
    private String j;
    private DateFormat k;
    private ParseContext[] l;
    private int m;
    private final List<ResolveTask> n;
    private int o;

    static {
        i.add(Boolean.TYPE);
        i.add(Byte.TYPE);
        i.add(Short.TYPE);
        i.add(Integer.TYPE);
        i.add(Long.TYPE);
        i.add(Float.TYPE);
        i.add(Double.TYPE);
        i.add(Boolean.class);
        i.add(Byte.class);
        i.add(Short.class);
        i.add(Integer.class);
        i.add(Long.class);
        i.add(Float.class);
        i.add(Double.class);
        i.add(BigInteger.class);
        i.add(BigDecimal.class);
        i.add(String.class);
    }

    public String a() {
        return this.j;
    }

    public DateFormat b() {
        if (this.k == null) {
            this.k = new SimpleDateFormat(this.j);
        }
        return this.k;
    }

    public void a(String str) {
        this.j = str;
        this.k = null;
    }

    public void a(DateFormat dateFormat) {
        this.k = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.b(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this((Object) str, (JSONLexer) new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i2) {
        this((Object) str, (JSONLexer) new JSONScanner(str, i2), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i2, ParserConfig parserConfig, int i3) {
        this((Object) cArr, (JSONLexer) new JSONScanner(cArr, i2, i3), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.b());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.j = JSON.DEFFAULT_DATE_FORMAT;
        this.l = new ParseContext[8];
        this.m = 0;
        this.n = new ArrayList();
        this.o = 0;
        this.d = jSONLexer;
        this.f6085a = obj;
        this.c = parserConfig;
        this.b = parserConfig.c();
        jSONLexer.a(12);
    }

    public SymbolTable c() {
        return this.b;
    }

    public String d() {
        if (this.f6085a instanceof char[]) {
            return new String((char[]) this.f6085a);
        }
        return this.f6085a.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        r3.a(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x021f, code lost:
        if (r3.d() != 4) goto L_0x02b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0221, code lost:
        r0 = r3.z();
        r3.a(13);
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x022f, code lost:
        if ("@".equals(r0) == false) goto L_0x0240;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0235, code lost:
        if (h() == null) goto L_0x029a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0237, code lost:
        r2 = h().b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0246, code lost:
        if ("..".equals(r0) == false) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0248, code lost:
        r5 = r4.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0250, code lost:
        if (r5.b() == null) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0252, code lost:
        r2 = r5.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0257, code lost:
        a(new com.imi.fastjson.parser.DefaultJSONParser.ResolveTask(r5, r0));
        a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0269, code lost:
        if (com.taobao.weex.el.parse.Operators.DOLLAR_STR.equals(r0) == false) goto L_0x028f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x026b, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0270, code lost:
        if (r5.c() == null) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0272, code lost:
        r5 = r5.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x027b, code lost:
        if (r5.b() == null) goto L_0x0283;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x027d, code lost:
        r2 = r5.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0283, code lost:
        a(new com.imi.fastjson.parser.DefaultJSONParser.ResolveTask(r5, r0));
        a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x028f, code lost:
        a(new com.imi.fastjson.parser.DefaultJSONParser.ResolveTask(r4, r0));
        a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x029e, code lost:
        if (r3.d() != 13) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02a0, code lost:
        r3.a(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02a5, code lost:
        a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02a8, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02b1, code lost:
        throw new com.imi.fastjson.JSONException("syntax error");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02d0, code lost:
        throw new com.imi.fastjson.JSONException("illegal ref, " + com.imi.fastjson.parser.JSONToken.a(r3.d()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x034c, code lost:
        if (r5 != '}') goto L_0x035e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x034e, code lost:
        r3.n();
        r3.o();
        r3.a();
        a((java.lang.Object) r17, r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x035a, code lost:
        a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x035d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0381, code lost:
        throw new com.imi.fastjson.JSONException("syntax error, position at " + r3.f() + ", name " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01ba, code lost:
        r3.a(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01c3, code lost:
        if (r3.d() != 13) goto L_0x01f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01c5, code lost:
        r3.a(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c8, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r2 = r1.c.a((java.lang.reflect.Type) r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01d1, code lost:
        if ((r2 instanceof com.imi.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d3, code lost:
        r0 = ((com.imi.fastjson.parser.deserializer.JavaBeanDeserializer) r2).a(r1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01d9, code lost:
        if (r0 != null) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01dd, code lost:
        if (r8 != java.lang.Cloneable.class) goto L_0x01e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01df, code lost:
        r0 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01e5, code lost:
        r0 = r8.newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01e9, code lost:
        a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01ec, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01f6, code lost:
        a(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01fb, code lost:
        if (r1.e == null) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ff, code lost:
        if ((r2 instanceof java.lang.Integer) != false) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0201, code lost:
        k();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0204, code lost:
        r0 = r1.c.a((java.lang.reflect.Type) r8).a(r1, r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x020e, code lost:
        a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0211, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0190 A[Catch:{ Exception -> 0x01ed, all -> 0x046d }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a3 A[Catch:{ Exception -> 0x01ed, all -> 0x046d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object a(java.util.Map r17, java.lang.Object r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            com.imi.fastjson.parser.JSONLexer r3 = r1.d
            int r4 = r3.d()
            r5 = 16
            r6 = 12
            if (r4 == r6) goto L_0x0035
            int r4 = r3.d()
            if (r4 != r5) goto L_0x0019
            goto L_0x0035
        L_0x0019:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "syntax error, expect {, actual "
            r2.append(r4)
            java.lang.String r3 = r3.e()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0035:
            com.imi.fastjson.parser.ParseContext r4 = r16.h()
            r7 = 0
        L_0x003a:
            r3.B()     // Catch:{ all -> 0x046d }
            char r8 = r3.m()     // Catch:{ all -> 0x046d }
            com.imi.fastjson.parser.Feature r9 = com.imi.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x046d }
            boolean r9 = r1.a((com.imi.fastjson.parser.Feature) r9)     // Catch:{ all -> 0x046d }
            r10 = 44
            if (r9 == 0) goto L_0x0058
        L_0x004b:
            if (r8 != r10) goto L_0x0058
            r3.n()     // Catch:{ all -> 0x046d }
            r3.B()     // Catch:{ all -> 0x046d }
            char r8 = r3.m()     // Catch:{ all -> 0x046d }
            goto L_0x004b
        L_0x0058:
            r12 = 45
            r13 = 57
            r14 = 48
            r15 = 125(0x7d, float:1.75E-43)
            r6 = 2
            r5 = 58
            r9 = 34
            r11 = 1
            if (r8 != r9) goto L_0x009d
            com.imi.fastjson.parser.SymbolTable r8 = r1.b     // Catch:{ all -> 0x046d }
            java.lang.String r8 = r3.a((com.imi.fastjson.parser.SymbolTable) r8, (char) r9)     // Catch:{ all -> 0x046d }
            r3.B()     // Catch:{ all -> 0x046d }
            char r9 = r3.m()     // Catch:{ all -> 0x046d }
            if (r9 != r5) goto L_0x007a
        L_0x0077:
            r5 = 0
            goto L_0x018e
        L_0x007a:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "expect ':' at "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.f()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x046d }
            r2.append(r8)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x009d:
            if (r8 != r15) goto L_0x00ac
            r3.n()     // Catch:{ all -> 0x046d }
            r3.o()     // Catch:{ all -> 0x046d }
            r3.a()     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x00ac:
            r9 = 39
            if (r8 != r9) goto L_0x00ec
            com.imi.fastjson.parser.Feature r8 = com.imi.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x046d }
            boolean r8 = r1.a((com.imi.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x046d }
            if (r8 == 0) goto L_0x00e3
            com.imi.fastjson.parser.SymbolTable r8 = r1.b     // Catch:{ all -> 0x046d }
            java.lang.String r8 = r3.a((com.imi.fastjson.parser.SymbolTable) r8, (char) r9)     // Catch:{ all -> 0x046d }
            r3.B()     // Catch:{ all -> 0x046d }
            char r9 = r3.m()     // Catch:{ all -> 0x046d }
            if (r9 != r5) goto L_0x00c8
            goto L_0x0077
        L_0x00c8:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "expect ':' at "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.f()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x00e3:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x00ec:
            r9 = 26
            if (r8 == r9) goto L_0x0464
            if (r8 == r10) goto L_0x045b
            if (r8 < r14) goto L_0x00f6
            if (r8 <= r13) goto L_0x00f8
        L_0x00f6:
            if (r8 != r12) goto L_0x0138
        L_0x00f8:
            r3.o()     // Catch:{ all -> 0x046d }
            r3.E()     // Catch:{ all -> 0x046d }
            int r8 = r3.d()     // Catch:{ all -> 0x046d }
            if (r8 != r6) goto L_0x0109
            java.lang.Number r8 = r3.h()     // Catch:{ all -> 0x046d }
            goto L_0x010d
        L_0x0109:
            java.lang.Number r8 = r3.a((boolean) r11)     // Catch:{ all -> 0x046d }
        L_0x010d:
            char r9 = r3.m()     // Catch:{ all -> 0x046d }
            if (r9 != r5) goto L_0x0115
            goto L_0x0077
        L_0x0115:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "expect ':' at "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.f()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x046d }
            r2.append(r8)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x0138:
            r9 = 123(0x7b, float:1.72E-43)
            if (r8 == r9) goto L_0x0186
            r9 = 91
            if (r8 != r9) goto L_0x0141
            goto L_0x0186
        L_0x0141:
            com.imi.fastjson.parser.Feature r8 = com.imi.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x046d }
            boolean r8 = r1.a((com.imi.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x046d }
            if (r8 == 0) goto L_0x017d
            com.imi.fastjson.parser.SymbolTable r8 = r1.b     // Catch:{ all -> 0x046d }
            java.lang.String r8 = r3.b((com.imi.fastjson.parser.SymbolTable) r8)     // Catch:{ all -> 0x046d }
            r3.B()     // Catch:{ all -> 0x046d }
            char r9 = r3.m()     // Catch:{ all -> 0x046d }
            if (r9 != r5) goto L_0x015a
            goto L_0x0077
        L_0x015a:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "expect ':' at "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.f()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r3 = ", actual "
            r2.append(r3)     // Catch:{ all -> 0x046d }
            r2.append(r9)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x017d:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x0186:
            r3.a()     // Catch:{ all -> 0x046d }
            java.lang.Object r8 = r16.l()     // Catch:{ all -> 0x046d }
            r5 = 1
        L_0x018e:
            if (r5 != 0) goto L_0x0196
            r3.n()     // Catch:{ all -> 0x046d }
            r3.B()     // Catch:{ all -> 0x046d }
        L_0x0196:
            char r5 = r3.m()     // Catch:{ all -> 0x046d }
            r3.o()     // Catch:{ all -> 0x046d }
            java.lang.String r9 = com.imi.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x046d }
            r15 = 13
            if (r8 != r9) goto L_0x0212
            com.imi.fastjson.parser.SymbolTable r5 = r1.b     // Catch:{ all -> 0x046d }
            r8 = 34
            java.lang.String r5 = r3.a((com.imi.fastjson.parser.SymbolTable) r5, (char) r8)     // Catch:{ all -> 0x046d }
            java.lang.Class r8 = com.imi.fastjson.util.TypeUtils.a((java.lang.String) r5)     // Catch:{ all -> 0x046d }
            if (r8 != 0) goto L_0x01ba
            java.lang.String r6 = com.imi.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x046d }
            r0.put(r6, r5)     // Catch:{ all -> 0x046d }
        L_0x01b6:
            r5 = 16
            goto L_0x003a
        L_0x01ba:
            r0 = 16
            r3.a((int) r0)     // Catch:{ all -> 0x046d }
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            if (r5 != r15) goto L_0x01f6
            r3.a((int) r0)     // Catch:{ all -> 0x046d }
            r0 = 0
            com.imi.fastjson.parser.ParserConfig r2 = r1.c     // Catch:{ Exception -> 0x01ed }
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.a((java.lang.reflect.Type) r8)     // Catch:{ Exception -> 0x01ed }
            boolean r3 = r2 instanceof com.imi.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ Exception -> 0x01ed }
            if (r3 == 0) goto L_0x01d9
            com.imi.fastjson.parser.deserializer.JavaBeanDeserializer r2 = (com.imi.fastjson.parser.deserializer.JavaBeanDeserializer) r2     // Catch:{ Exception -> 0x01ed }
            java.lang.Object r0 = r2.a(r1, r8)     // Catch:{ Exception -> 0x01ed }
        L_0x01d9:
            if (r0 != 0) goto L_0x01e9
            java.lang.Class<java.lang.Cloneable> r0 = java.lang.Cloneable.class
            if (r8 != r0) goto L_0x01e5
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x01ed }
            r0.<init>()     // Catch:{ Exception -> 0x01ed }
            goto L_0x01e9
        L_0x01e5:
            java.lang.Object r0 = r8.newInstance()     // Catch:{ Exception -> 0x01ed }
        L_0x01e9:
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x01ed:
            r0 = move-exception
            com.imi.fastjson.JSONException r2 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r3 = "create instance error"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x046d }
            throw r2     // Catch:{ all -> 0x046d }
        L_0x01f6:
            r1.a((int) r6)     // Catch:{ all -> 0x046d }
            com.imi.fastjson.parser.ParseContext r0 = r1.e     // Catch:{ all -> 0x046d }
            if (r0 == 0) goto L_0x0204
            boolean r0 = r2 instanceof java.lang.Integer     // Catch:{ all -> 0x046d }
            if (r0 != 0) goto L_0x0204
            r16.k()     // Catch:{ all -> 0x046d }
        L_0x0204:
            com.imi.fastjson.parser.ParserConfig r0 = r1.c     // Catch:{ all -> 0x046d }
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r0 = r0.a((java.lang.reflect.Type) r8)     // Catch:{ all -> 0x046d }
            java.lang.Object r0 = r0.a(r1, r8, r2)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x0212:
            java.lang.String r9 = "$ref"
            if (r8 != r9) goto L_0x02d1
            r0 = 4
            r3.a((int) r0)     // Catch:{ all -> 0x046d }
            int r0 = r3.d()     // Catch:{ all -> 0x046d }
            r2 = 4
            if (r0 != r2) goto L_0x02b2
            java.lang.String r0 = r3.z()     // Catch:{ all -> 0x046d }
            r3.a((int) r15)     // Catch:{ all -> 0x046d }
            r2 = 0
            java.lang.String r5 = "@"
            boolean r5 = r5.equals(r0)     // Catch:{ all -> 0x046d }
            if (r5 == 0) goto L_0x0240
            com.imi.fastjson.parser.ParseContext r0 = r16.h()     // Catch:{ all -> 0x046d }
            if (r0 == 0) goto L_0x029a
            com.imi.fastjson.parser.ParseContext r0 = r16.h()     // Catch:{ all -> 0x046d }
            java.lang.Object r2 = r0.b()     // Catch:{ all -> 0x046d }
            goto L_0x029a
        L_0x0240:
            java.lang.String r5 = ".."
            boolean r5 = r5.equals(r0)     // Catch:{ all -> 0x046d }
            if (r5 == 0) goto L_0x0263
            com.imi.fastjson.parser.ParseContext r5 = r4.c()     // Catch:{ all -> 0x046d }
            java.lang.Object r6 = r5.b()     // Catch:{ all -> 0x046d }
            if (r6 == 0) goto L_0x0257
            java.lang.Object r2 = r5.b()     // Catch:{ all -> 0x046d }
            goto L_0x029a
        L_0x0257:
            com.imi.fastjson.parser.DefaultJSONParser$ResolveTask r6 = new com.imi.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x046d }
            r6.<init>(r5, r0)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.DefaultJSONParser.ResolveTask) r6)     // Catch:{ all -> 0x046d }
            r1.a((int) r11)     // Catch:{ all -> 0x046d }
            goto L_0x029a
        L_0x0263:
            java.lang.String r5 = "$"
            boolean r5 = r5.equals(r0)     // Catch:{ all -> 0x046d }
            if (r5 == 0) goto L_0x028f
            r5 = r4
        L_0x026c:
            com.imi.fastjson.parser.ParseContext r6 = r5.c()     // Catch:{ all -> 0x046d }
            if (r6 == 0) goto L_0x0277
            com.imi.fastjson.parser.ParseContext r5 = r5.c()     // Catch:{ all -> 0x046d }
            goto L_0x026c
        L_0x0277:
            java.lang.Object r6 = r5.b()     // Catch:{ all -> 0x046d }
            if (r6 == 0) goto L_0x0283
            java.lang.Object r0 = r5.b()     // Catch:{ all -> 0x046d }
            r2 = r0
            goto L_0x029a
        L_0x0283:
            com.imi.fastjson.parser.DefaultJSONParser$ResolveTask r6 = new com.imi.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x046d }
            r6.<init>(r5, r0)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.DefaultJSONParser.ResolveTask) r6)     // Catch:{ all -> 0x046d }
            r1.a((int) r11)     // Catch:{ all -> 0x046d }
            goto L_0x029a
        L_0x028f:
            com.imi.fastjson.parser.DefaultJSONParser$ResolveTask r5 = new com.imi.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x046d }
            r5.<init>(r4, r0)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.DefaultJSONParser.ResolveTask) r5)     // Catch:{ all -> 0x046d }
            r1.a((int) r11)     // Catch:{ all -> 0x046d }
        L_0x029a:
            int r0 = r3.d()     // Catch:{ all -> 0x046d }
            if (r0 != r15) goto L_0x02a9
            r0 = 16
            r3.a((int) r0)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r2
        L_0x02a9:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x02b2:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "illegal ref, "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.d()     // Catch:{ all -> 0x046d }
            java.lang.String r3 = com.imi.fastjson.parser.JSONToken.a(r3)     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x02d1:
            if (r7 != 0) goto L_0x02e2
            r16.a((java.lang.Object) r17, (java.lang.Object) r18)     // Catch:{ all -> 0x046d }
            com.imi.fastjson.parser.ParseContext r7 = r1.e     // Catch:{ all -> 0x046d }
            if (r7 == 0) goto L_0x02e1
            boolean r7 = r2 instanceof java.lang.Integer     // Catch:{ all -> 0x046d }
            if (r7 != 0) goto L_0x02e1
            r16.k()     // Catch:{ all -> 0x046d }
        L_0x02e1:
            r7 = 1
        L_0x02e2:
            r9 = 34
            if (r5 != r9) goto L_0x031f
            r3.p()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = r3.z()     // Catch:{ all -> 0x046d }
            com.imi.fastjson.parser.Feature r6 = com.imi.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x046d }
            boolean r6 = r3.a((com.imi.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x046d }
            if (r6 == 0) goto L_0x030b
            com.imi.fastjson.parser.JSONScanner r6 = new com.imi.fastjson.parser.JSONScanner     // Catch:{ all -> 0x046d }
            r6.<init>(r5)     // Catch:{ all -> 0x046d }
            boolean r9 = r6.I()     // Catch:{ all -> 0x046d }
            if (r9 == 0) goto L_0x0308
            java.util.Calendar r5 = r6.q()     // Catch:{ all -> 0x046d }
            java.util.Date r5 = r5.getTime()     // Catch:{ all -> 0x046d }
        L_0x0308:
            r6.close()     // Catch:{ all -> 0x046d }
        L_0x030b:
            java.lang.Class r6 = r17.getClass()     // Catch:{ all -> 0x046d }
            java.lang.Class<com.imi.fastjson.JSONObject> r9 = com.imi.fastjson.JSONObject.class
            if (r6 != r9) goto L_0x031b
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x046d }
            r0.put(r6, r5)     // Catch:{ all -> 0x046d }
            goto L_0x033a
        L_0x031b:
            r0.put(r8, r5)     // Catch:{ all -> 0x046d }
            goto L_0x033a
        L_0x031f:
            if (r5 < r14) goto L_0x0323
            if (r5 <= r13) goto L_0x0325
        L_0x0323:
            if (r5 != r12) goto L_0x0382
        L_0x0325:
            r3.E()     // Catch:{ all -> 0x046d }
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            if (r5 != r6) goto L_0x0333
            java.lang.Number r5 = r3.h()     // Catch:{ all -> 0x046d }
            goto L_0x0337
        L_0x0333:
            java.lang.Number r5 = r3.H()     // Catch:{ all -> 0x046d }
        L_0x0337:
            r0.put(r8, r5)     // Catch:{ all -> 0x046d }
        L_0x033a:
            r3.B()     // Catch:{ all -> 0x046d }
            char r5 = r3.m()     // Catch:{ all -> 0x046d }
            if (r5 != r10) goto L_0x034a
            r3.n()     // Catch:{ all -> 0x046d }
            r6 = 16
            goto L_0x0435
        L_0x034a:
            r6 = 125(0x7d, float:1.75E-43)
            if (r5 != r6) goto L_0x035e
            r3.n()     // Catch:{ all -> 0x046d }
            r3.o()     // Catch:{ all -> 0x046d }
            r3.a()     // Catch:{ all -> 0x046d }
            r16.a((java.lang.Object) r17, (java.lang.Object) r18)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x035e:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "syntax error, position at "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.f()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x046d }
            r2.append(r8)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x0382:
            r6 = 91
            if (r5 != r6) goto L_0x03b4
            r3.a()     // Catch:{ all -> 0x046d }
            com.imi.fastjson.JSONArray r5 = new com.imi.fastjson.JSONArray     // Catch:{ all -> 0x046d }
            r5.<init>()     // Catch:{ all -> 0x046d }
            r1.a((java.util.Collection) r5, (java.lang.Object) r8)     // Catch:{ all -> 0x046d }
            r0.put(r8, r5)     // Catch:{ all -> 0x046d }
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            if (r5 != r15) goto L_0x03a1
            r3.a()     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x03a1:
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            r6 = 16
            if (r5 != r6) goto L_0x03ab
            goto L_0x0435
        L_0x03ab:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x03b4:
            r6 = 123(0x7b, float:1.72E-43)
            if (r5 != r6) goto L_0x0416
            r3.a()     // Catch:{ all -> 0x046d }
            com.imi.fastjson.JSONObject r5 = new com.imi.fastjson.JSONObject     // Catch:{ all -> 0x046d }
            r5.<init>()     // Catch:{ all -> 0x046d }
            java.lang.Object r5 = r1.a((java.util.Map) r5, (java.lang.Object) r8)     // Catch:{ all -> 0x046d }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x046d }
            r1.a((java.util.Map) r0, (java.lang.String) r6)     // Catch:{ all -> 0x046d }
            java.lang.Class r6 = r17.getClass()     // Catch:{ all -> 0x046d }
            java.lang.Class<com.imi.fastjson.JSONObject> r9 = com.imi.fastjson.JSONObject.class
            if (r6 != r9) goto L_0x03db
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x046d }
            r0.put(r6, r5)     // Catch:{ all -> 0x046d }
            goto L_0x03de
        L_0x03db:
            r0.put(r8, r5)     // Catch:{ all -> 0x046d }
        L_0x03de:
            r1.a((com.imi.fastjson.parser.ParseContext) r4, (java.lang.Object) r5, (java.lang.Object) r8)     // Catch:{ all -> 0x046d }
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            if (r5 != r15) goto L_0x03f1
            r3.a()     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x03f1:
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            r6 = 16
            if (r5 != r6) goto L_0x03fa
            goto L_0x0435
        L_0x03fa:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "syntax error, "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            java.lang.String r3 = r3.e()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x0416:
            r3.a()     // Catch:{ all -> 0x046d }
            java.lang.Object r5 = r16.l()     // Catch:{ all -> 0x046d }
            r0.put(r8, r5)     // Catch:{ all -> 0x046d }
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            if (r5 != r15) goto L_0x042d
            r3.a()     // Catch:{ all -> 0x046d }
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            return r0
        L_0x042d:
            int r5 = r3.d()     // Catch:{ all -> 0x046d }
            r6 = 16
            if (r5 != r6) goto L_0x0437
        L_0x0435:
            goto L_0x01b6
        L_0x0437:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x046d }
            r2.<init>()     // Catch:{ all -> 0x046d }
            java.lang.String r5 = "syntax error, position at "
            r2.append(r5)     // Catch:{ all -> 0x046d }
            int r3 = r3.f()     // Catch:{ all -> 0x046d }
            r2.append(r3)     // Catch:{ all -> 0x046d }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x046d }
            r2.append(r8)     // Catch:{ all -> 0x046d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x046d }
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x045b:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x0464:
            com.imi.fastjson.JSONException r0 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x046d }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x046d }
            throw r0     // Catch:{ all -> 0x046d }
        L_0x046d:
            r0 = move-exception
            r1.a((com.imi.fastjson.parser.ParseContext) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.DefaultJSONParser.a(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public ParserConfig e() {
        return this.c;
    }

    public void a(ParserConfig parserConfig) {
        this.c = parserConfig;
    }

    public <T> T a(Class<T> cls) {
        return a((Type) cls);
    }

    public <T> T a(Type type) {
        if (this.d.d() == 8) {
            this.d.a();
            return null;
        }
        try {
            return this.c.a(type).a(this, type, (Object) null);
        } catch (JSONException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> b(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        a((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void a(Class<?> cls, Collection collection) {
        a((Type) cls, collection);
    }

    public void a(Type type, Collection collection) {
        a(type, collection, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    public void a(Type type, Collection collection, Object obj) {
        ObjectDeserializer objectDeserializer;
        if (this.d.d() == 21 || this.d.d() == 22) {
            this.d.a();
        }
        if (this.d.d() == 14) {
            if (Integer.TYPE == type) {
                objectDeserializer = IntegerDeserializer.f6112a;
                this.d.a(2);
            } else if (String.class == type) {
                objectDeserializer = StringDeserializer.f6125a;
                this.d.a(4);
            } else {
                objectDeserializer = this.c.a(type);
                this.d.a(objectDeserializer.a());
            }
            ParseContext h2 = h();
            a((Object) collection, obj);
            int i2 = 0;
            while (true) {
                try {
                    if (a(Feature.AllowArbitraryCommas)) {
                        while (this.d.d() == 16) {
                            this.d.a();
                        }
                    }
                    if (this.d.d() == 15) {
                        a(h2);
                        this.d.a(16);
                        return;
                    }
                    Object obj2 = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerDeserializer.f6112a.a(this, (Type) null, (Object) null));
                    } else if (String.class == type) {
                        if (this.d.d() == 4) {
                            obj2 = this.d.z();
                            this.d.a(16);
                        } else {
                            Object l2 = l();
                            if (l2 != null) {
                                obj2 = l2.toString();
                            }
                        }
                        collection.add(obj2);
                    } else {
                        if (this.d.d() == 8) {
                            this.d.a();
                        } else {
                            obj2 = objectDeserializer.a(this, type, Integer.valueOf(i2));
                        }
                        collection.add(obj2);
                        a(collection);
                    }
                    if (this.d.d() == 16) {
                        this.d.a(objectDeserializer.a());
                    }
                    i2++;
                } catch (Throwable th) {
                    a(h2);
                    throw th;
                }
            }
        } else {
            throw new JSONException("exepct '[', but " + JSONToken.a(this.d.d()));
        }
    }

    public Object[] a(Type[] typeArr) {
        Object obj;
        boolean z;
        Class<?> cls;
        if (this.d.d() == 8) {
            this.d.a(16);
            return null;
        } else if (this.d.d() == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.d.a(15);
                if (this.d.d() == 15) {
                    this.d.a(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error");
            }
            this.d.a(2);
            int i2 = 0;
            while (i2 < typeArr.length) {
                if (this.d.d() == 8) {
                    this.d.a(16);
                    obj = null;
                } else {
                    Class<String> cls2 = typeArr[i2];
                    if (cls2 == Integer.TYPE || cls2 == Integer.class) {
                        if (this.d.d() == 2) {
                            obj = Integer.valueOf(this.d.r());
                            this.d.a(16);
                        } else {
                            obj = TypeUtils.a(l(), (Type) cls2, this.c);
                        }
                    } else if (cls2 != String.class) {
                        if (i2 != typeArr.length - 1 || !(cls2 instanceof Class)) {
                            cls = null;
                            z = false;
                        } else {
                            Class cls3 = cls2;
                            z = cls3.isArray();
                            cls = cls3.getComponentType();
                        }
                        if (!z || this.d.d() == 14) {
                            obj = this.c.a((Type) cls2).a(this, cls2, (Object) null);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            ObjectDeserializer a2 = this.c.a((Type) cls);
                            int a3 = a2.a();
                            if (this.d.d() != 15) {
                                while (true) {
                                    arrayList.add(a2.a(this, cls2, (Object) null));
                                    if (this.d.d() != 16) {
                                        break;
                                    }
                                    this.d.a(a3);
                                }
                                if (this.d.d() != 15) {
                                    throw new JSONException("syntax error :" + JSONToken.a(this.d.d()));
                                }
                            }
                            obj = TypeUtils.a((Object) arrayList, (Type) cls2, this.c);
                        }
                    } else if (this.d.d() == 4) {
                        obj = this.d.z();
                        this.d.a(16);
                    } else {
                        obj = TypeUtils.a(l(), (Type) cls2, this.c);
                    }
                }
                objArr[i2] = obj;
                if (this.d.d() == 15) {
                    break;
                } else if (this.d.d() == 16) {
                    if (i2 == typeArr.length - 1) {
                        this.d.a(15);
                    } else {
                        this.d.a(2);
                    }
                    i2++;
                } else {
                    throw new JSONException("syntax error :" + JSONToken.a(this.d.d()));
                }
            }
            if (this.d.d() == 15) {
                this.d.a(16);
                return objArr;
            }
            throw new JSONException("syntax error");
        } else {
            throw new JSONException("syntax error : " + this.d.e());
        }
    }

    public void a(Object obj) {
        Class<?> cls = obj.getClass();
        Map<String, FieldDeserializer> b2 = this.c.b(cls);
        if (this.d.d() == 12 || this.d.d() == 16) {
            Object[] objArr = new Object[1];
            while (true) {
                String a2 = this.d.a(this.b);
                if (a2 == null) {
                    if (this.d.d() == 13) {
                        this.d.a(16);
                        return;
                    } else if (this.d.d() == 16 && a(Feature.AllowArbitraryCommas)) {
                    }
                }
                FieldDeserializer fieldDeserializer = b2.get(a2);
                if (fieldDeserializer != null) {
                    Method b3 = fieldDeserializer.b();
                    Class<String> cls2 = b3.getParameterTypes()[0];
                    Type type = b3.getGenericParameterTypes()[0];
                    if (cls2 == Integer.TYPE) {
                        this.d.b(2);
                        objArr[0] = IntegerDeserializer.f6112a.a(this, type, (Object) null);
                    } else if (cls2 == String.class) {
                        this.d.b(4);
                        objArr[0] = StringDeserializer.a(this);
                    } else if (cls2 == Long.TYPE) {
                        this.d.b(2);
                        objArr[0] = LongDeserializer.f6118a.a(this, type, (Object) null);
                    } else {
                        ObjectDeserializer a3 = this.c.a((Class<?>) cls2, type);
                        this.d.b(a3.a());
                        objArr[0] = a3.a(this, type, (Object) null);
                    }
                    try {
                        b3.invoke(obj, objArr);
                        if (this.d.d() != 16 && this.d.d() == 13) {
                            this.d.a(16);
                            return;
                        }
                    } catch (Exception e2) {
                        throw new JSONException("set proprety error, " + b3.getName(), e2);
                    }
                } else if (a(Feature.IgnoreNotMatch)) {
                    this.d.c();
                    l();
                    if (this.d.d() == 13) {
                        this.d.a();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + a2);
                }
            }
        } else {
            throw new JSONException("syntax error, expect {, actual " + this.d.e());
        }
    }

    public Object b(Type type) {
        if (this.d.d() == 8) {
            this.d.a();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                ArrayList arrayList = new ArrayList();
                a((Class<?>) (Class) type2, (Collection) arrayList);
                return arrayList;
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                Type type3 = wildcardType.getUpperBounds()[0];
                if (!Object.class.equals(type3)) {
                    ArrayList arrayList2 = new ArrayList();
                    a((Class<?>) (Class) type3, (Collection) arrayList2);
                    return arrayList2;
                } else if (wildcardType.getLowerBounds().length == 0) {
                    return l();
                } else {
                    throw new JSONException("not support type : " + type);
                }
            } else {
                if (type2 instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) type2;
                    Type[] bounds = typeVariable.getBounds();
                    if (bounds.length == 1) {
                        Type type4 = bounds[0];
                        if (type4 instanceof Class) {
                            ArrayList arrayList3 = new ArrayList();
                            a((Class<?>) (Class) type4, (Collection) arrayList3);
                            return arrayList3;
                        }
                    } else {
                        throw new JSONException("not support : " + typeVariable);
                    }
                }
                if (type2 instanceof ParameterizedType) {
                    ArrayList arrayList4 = new ArrayList();
                    a((Type) (ParameterizedType) type2, (Collection) arrayList4);
                    return arrayList4;
                }
                throw new JSONException("TODO : " + type);
            }
        } else {
            throw new JSONException("not support type " + type);
        }
    }

    public int f() {
        return this.o;
    }

    public void a(int i2) {
        this.o = i2;
    }

    public Object b(String str) {
        for (int i2 = 0; i2 < this.m; i2++) {
            if (str.equals(this.l[i2].d())) {
                return this.l[i2].b();
            }
        }
        return null;
    }

    public void a(Collection collection) {
        if (this.o == 1) {
            ResolveTask j2 = j();
            j2.a((FieldDeserializer) new ListResolveFieldDeserializer(this, (List) collection, collection.size() - 1));
            j2.a(this.e);
            a(0);
        }
    }

    public void a(Map map, String str) {
        if (this.o == 1) {
            MapResolveFieldDeserializer mapResolveFieldDeserializer = new MapResolveFieldDeserializer(map, str);
            ResolveTask j2 = j();
            j2.a((FieldDeserializer) mapResolveFieldDeserializer);
            j2.a(this.e);
            a(0);
        }
    }

    public Object a(Map map) {
        return a(map, (Object) null);
    }

    public JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        a((Map) jSONObject);
        return jSONObject;
    }

    public final void b(Collection collection) {
        a(collection, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.imi.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: com.imi.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v24, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v25, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v29, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v30, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v32, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v33, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v35, resolved type: com.imi.fastjson.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.util.Collection r9, java.lang.Object r10) {
        /*
            r8 = this;
            com.imi.fastjson.parser.JSONLexer r0 = r8.n()
            int r1 = r0.d()
            r2 = 21
            if (r1 == r2) goto L_0x0014
            int r1 = r0.d()
            r2 = 22
            if (r1 != r2) goto L_0x0017
        L_0x0014:
            r0.a()
        L_0x0017:
            int r1 = r0.d()
            r2 = 14
            if (r1 != r2) goto L_0x00e4
            r1 = 4
            r0.a((int) r1)
            com.imi.fastjson.parser.ParseContext r2 = r8.h()
            r8.a((java.lang.Object) r9, (java.lang.Object) r10)
            r10 = 0
            r3 = 0
        L_0x002c:
            com.imi.fastjson.parser.Feature r4 = com.imi.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x00df }
            boolean r4 = r8.a((com.imi.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x00df }
            r5 = 16
            if (r4 == 0) goto L_0x0040
        L_0x0036:
            int r4 = r0.d()     // Catch:{ all -> 0x00df }
            if (r4 != r5) goto L_0x0040
            r0.a()     // Catch:{ all -> 0x00df }
            goto L_0x0036
        L_0x0040:
            int r4 = r0.d()     // Catch:{ all -> 0x00df }
            switch(r4) {
                case 2: goto L_0x00c5;
                case 3: goto L_0x00af;
                case 4: goto L_0x0089;
                case 5: goto L_0x0047;
                case 6: goto L_0x0083;
                case 7: goto L_0x007d;
                case 8: goto L_0x0078;
                case 9: goto L_0x0047;
                case 10: goto L_0x0047;
                case 11: goto L_0x0047;
                case 12: goto L_0x006a;
                case 13: goto L_0x0047;
                case 14: goto L_0x005d;
                case 15: goto L_0x0056;
                case 16: goto L_0x0047;
                case 17: goto L_0x0047;
                case 18: goto L_0x0047;
                case 19: goto L_0x0047;
                case 20: goto L_0x004d;
                default: goto L_0x0047;
            }     // Catch:{ all -> 0x00df }
        L_0x0047:
            java.lang.Object r4 = r8.l()     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x004d:
            com.imi.fastjson.JSONException r9 = new com.imi.fastjson.JSONException     // Catch:{ all -> 0x00df }
            java.lang.String r10 = "unclosed jsonArray"
            r9.<init>(r10)     // Catch:{ all -> 0x00df }
            throw r9     // Catch:{ all -> 0x00df }
        L_0x0056:
            r0.a((int) r5)     // Catch:{ all -> 0x00df }
            r8.a((com.imi.fastjson.parser.ParseContext) r2)
            return
        L_0x005d:
            com.imi.fastjson.JSONArray r4 = new com.imi.fastjson.JSONArray     // Catch:{ all -> 0x00df }
            r4.<init>()     // Catch:{ all -> 0x00df }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00df }
            r8.a((java.util.Collection) r4, (java.lang.Object) r6)     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x006a:
            com.imi.fastjson.JSONObject r4 = new com.imi.fastjson.JSONObject     // Catch:{ all -> 0x00df }
            r4.<init>()     // Catch:{ all -> 0x00df }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00df }
            java.lang.Object r4 = r8.a((java.util.Map) r4, (java.lang.Object) r6)     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x0078:
            r4 = 0
            r0.a((int) r1)     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x007d:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00df }
            r0.a((int) r5)     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x0083:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00df }
            r0.a((int) r5)     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x0089:
            java.lang.String r4 = r0.z()     // Catch:{ all -> 0x00df }
            r0.a((int) r5)     // Catch:{ all -> 0x00df }
            com.imi.fastjson.parser.Feature r6 = com.imi.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x00df }
            boolean r6 = r0.a((com.imi.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x00df }
            if (r6 == 0) goto L_0x00cc
            com.imi.fastjson.parser.JSONScanner r6 = new com.imi.fastjson.parser.JSONScanner     // Catch:{ all -> 0x00df }
            r6.<init>(r4)     // Catch:{ all -> 0x00df }
            boolean r7 = r6.I()     // Catch:{ all -> 0x00df }
            if (r7 == 0) goto L_0x00ab
            java.util.Calendar r4 = r6.q()     // Catch:{ all -> 0x00df }
            java.util.Date r4 = r4.getTime()     // Catch:{ all -> 0x00df }
        L_0x00ab:
            r6.close()     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x00af:
            com.imi.fastjson.parser.Feature r4 = com.imi.fastjson.parser.Feature.UseBigDecimal     // Catch:{ all -> 0x00df }
            boolean r4 = r0.a((com.imi.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x00df }
            if (r4 == 0) goto L_0x00bd
            r4 = 1
            java.lang.Number r4 = r0.a((boolean) r4)     // Catch:{ all -> 0x00df }
            goto L_0x00c1
        L_0x00bd:
            java.lang.Number r4 = r0.a((boolean) r10)     // Catch:{ all -> 0x00df }
        L_0x00c1:
            r0.a((int) r5)     // Catch:{ all -> 0x00df }
            goto L_0x00cc
        L_0x00c5:
            java.lang.Number r4 = r0.h()     // Catch:{ all -> 0x00df }
            r0.a((int) r5)     // Catch:{ all -> 0x00df }
        L_0x00cc:
            r9.add(r4)     // Catch:{ all -> 0x00df }
            r8.a((java.util.Collection) r9)     // Catch:{ all -> 0x00df }
            int r4 = r0.d()     // Catch:{ all -> 0x00df }
            if (r4 != r5) goto L_0x00db
            r0.a((int) r1)     // Catch:{ all -> 0x00df }
        L_0x00db:
            int r3 = r3 + 1
            goto L_0x002c
        L_0x00df:
            r9 = move-exception
            r8.a((com.imi.fastjson.parser.ParseContext) r2)
            throw r9
        L_0x00e4:
            com.imi.fastjson.JSONException r9 = new com.imi.fastjson.JSONException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r1 = "syntax error, expect [, actual "
            r10.append(r1)
            int r1 = r0.d()
            java.lang.String r1 = com.imi.fastjson.parser.JSONToken.a(r1)
            r10.append(r1)
            java.lang.String r1 = ", pos "
            r10.append(r1)
            int r0 = r0.f()
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.DefaultJSONParser.a(java.util.Collection, java.lang.Object):void");
    }

    public ParseContext h() {
        return this.e;
    }

    public List<ResolveTask> i() {
        return this.n;
    }

    public void a(ResolveTask resolveTask) {
        this.n.add(resolveTask);
    }

    public ResolveTask j() {
        return this.n.get(this.n.size() - 1);
    }

    public void a(ParseContext parseContext) {
        if (!a(Feature.DisableCircularReferenceDetect)) {
            this.e = parseContext;
        }
    }

    public void k() {
        if (!a(Feature.DisableCircularReferenceDetect)) {
            this.e = this.e.c();
            this.l[this.m - 1] = null;
            this.m--;
        }
    }

    public ParseContext a(Object obj, Object obj2) {
        if (a(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return a(this.e, obj, obj2);
    }

    public ParseContext a(ParseContext parseContext, Object obj, Object obj2) {
        if (a(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.e = new ParseContext(parseContext, obj, obj2);
        b(this.e);
        return this.e;
    }

    private void b(ParseContext parseContext) {
        int i2 = this.m;
        this.m = i2 + 1;
        if (i2 >= this.l.length) {
            ParseContext[] parseContextArr = new ParseContext[((this.l.length * 3) / 2)];
            System.arraycopy(this.l, 0, parseContextArr, 0, this.l.length);
            this.l = parseContextArr;
        }
        this.l[i2] = parseContext;
    }

    public Object l() {
        return b((Object) null);
    }

    public Object m() {
        if (this.d.d() != 18) {
            return b((Object) null);
        }
        String z = this.d.z();
        this.d.a(16);
        return z;
    }

    public Object b(Object obj) {
        JSONLexer n2 = n();
        switch (n2.d()) {
            case 2:
                Number h2 = n2.h();
                n2.a();
                return h2;
            case 3:
                Number a2 = n2.a(a(Feature.UseBigDecimal));
                n2.a();
                return a2;
            case 4:
                String z = n2.z();
                n2.a(16);
                if (n2.a(Feature.AllowISO8601DateFormat)) {
                    JSONScanner jSONScanner = new JSONScanner(z);
                    try {
                        if (jSONScanner.I()) {
                            return jSONScanner.q().getTime();
                        }
                        jSONScanner.close();
                    } finally {
                        jSONScanner.close();
                    }
                }
                return z;
            case 6:
                n2.a();
                return Boolean.TRUE;
            case 7:
                n2.a();
                return Boolean.FALSE;
            case 8:
                n2.a();
                return null;
            case 9:
                n2.a(18);
                if (n2.d() == 18) {
                    n2.a(10);
                    b(10);
                    long longValue = n2.h().longValue();
                    b(2);
                    b(11);
                    return new Date(longValue);
                }
                throw new JSONException("syntax error");
            case 12:
                return a((Map) new JSONObject(), obj);
            case 14:
                JSONArray jSONArray = new JSONArray();
                a((Collection) jSONArray, obj);
                return jSONArray;
            case 20:
                if (n2.A()) {
                    return null;
                }
                throw new JSONException("unterminated json string, pos " + n2.g());
            case 21:
                n2.a();
                HashSet hashSet = new HashSet();
                a((Collection) hashSet, obj);
                return hashSet;
            case 22:
                n2.a();
                TreeSet treeSet = new TreeSet();
                a((Collection) treeSet, obj);
                return treeSet;
            default:
                throw new JSONException("syntax error, pos " + n2.g());
        }
    }

    public void a(Feature feature, boolean z) {
        n().a(feature, z);
    }

    public boolean a(Feature feature) {
        return n().a(feature);
    }

    public JSONLexer n() {
        return this.d;
    }

    public final void b(int i2) {
        JSONLexer n2 = n();
        if (n2.d() == i2) {
            n2.a();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.a(i2) + ", actual " + JSONToken.a(n2.d()));
    }

    public final void a(int i2, int i3) {
        JSONLexer n2 = n();
        if (n2.d() == i2) {
            n2.a(i3);
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.a(i2) + ", actual " + JSONToken.a(n2.d()));
    }

    public void close() {
        JSONLexer n2 = n();
        try {
            if (a(Feature.AutoCloseSource)) {
                if (n2.d() != 20) {
                    throw new JSONException("not close json text, token : " + JSONToken.a(n2.d()));
                }
            }
        } finally {
            n2.close();
        }
    }

    public static class ResolveTask {

        /* renamed from: a  reason: collision with root package name */
        private final ParseContext f6086a;
        private final String b;
        private FieldDeserializer c;
        private ParseContext d;

        public ResolveTask(ParseContext parseContext, String str) {
            this.f6086a = parseContext;
            this.b = str;
        }

        public ParseContext a() {
            return this.f6086a;
        }

        public String b() {
            return this.b;
        }

        public FieldDeserializer c() {
            return this.c;
        }

        public void a(FieldDeserializer fieldDeserializer) {
            this.c = fieldDeserializer;
        }

        public ParseContext d() {
            return this.d;
        }

        public void a(ParseContext parseContext) {
            this.d = parseContext;
        }
    }
}
