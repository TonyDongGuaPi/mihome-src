package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public ParserConfig config;
    protected ParseContext contex;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    protected List<ExtraProcessor> extraProcessors;
    protected List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.locale);
            this.dateFormat.setTimeZone(this.lexer.timeZone);
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat2) {
        this.dateFormat = dateFormat2;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(new JSONLexer(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(new JSONLexer(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(new JSONLexer(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.global);
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = jSONLexer;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char c = jSONLexer.ch;
        char c2 = JSONLexer.EOI;
        if (c == '{') {
            int i = jSONLexer.bp + 1;
            jSONLexer.bp = i;
            jSONLexer.ch = i < jSONLexer.len ? jSONLexer.text.charAt(i) : c2;
            jSONLexer.token = 12;
        } else if (jSONLexer.ch == '[') {
            int i2 = jSONLexer.bp + 1;
            jSONLexer.bp = i2;
            jSONLexer.ch = i2 < jSONLexer.len ? jSONLexer.text.charAt(i2) : c2;
            jSONLexer.token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0254, code lost:
        r3.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x025b, code lost:
        if (r3.token != 13) goto L_0x02c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x025d, code lost:
        r3.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:?, code lost:
        r2 = r1.config.getDeserializer(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0268, code lost:
        if ((r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L_0x029c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x026a, code lost:
        r2 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r2;
        r3 = r2.createInstance(r1, (java.lang.reflect.Type) r6);
        r0 = r17.entrySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x027c, code lost:
        if (r0.hasNext() == false) goto L_0x029d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x027e, code lost:
        r4 = (java.util.Map.Entry) r0.next();
        r7 = r4.getKey();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x028a, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L_0x0278;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x028c, code lost:
        r7 = r2.getFieldDeserializer((java.lang.String) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0292, code lost:
        if (r7 == null) goto L_0x0278;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0294, code lost:
        r7.setValue((java.lang.Object) r3, r4.getValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x029c, code lost:
        r3 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x029d, code lost:
        if (r3 != null) goto L_0x02ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02a1, code lost:
        if (r6 != java.lang.Cloneable.class) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02a3, code lost:
        r3 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x02af, code lost:
        if ("java.util.Collections$EmptyMap".equals(r5) == false) goto L_0x02b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x02b1, code lost:
        r3 = java.util.Collections.emptyMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x02b6, code lost:
        r3 = r6.newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x02ba, code lost:
        if (r13 != false) goto L_0x02be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x02bc, code lost:
        r1.contex = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x02be, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02c8, code lost:
        r1.resolveStatus = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02cd, code lost:
        if (r1.contex == null) goto L_0x02d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02d1, code lost:
        if ((r2 instanceof java.lang.Integer) != false) goto L_0x02d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x02d3, code lost:
        popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02da, code lost:
        if (r17.size() <= 0) goto L_0x02ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x02dc, code lost:
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r6, r1.config);
        parseObject((java.lang.Object) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x02e5, code lost:
        if (r13 != false) goto L_0x02e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x02e7, code lost:
        r1.contex = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x02e9, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:?, code lost:
        r0 = r1.config.getDeserializer(r6);
        r2 = r0.deserialze(r1, r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x02f6, code lost:
        if ((r0 instanceof com.alibaba.fastjson.parser.MapDeserializer) == false) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x02f8, code lost:
        r1.resolveStatus = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x02fb, code lost:
        if (r13 != false) goto L_0x02ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x02fd, code lost:
        r1.contex = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x02ff, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x030f, code lost:
        r3.nextToken(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0314, code lost:
        if (r3.token != 4) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0316, code lost:
        r0 = r3.stringVal();
        r3.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0323, code lost:
        if ("@".equals(r0) == false) goto L_0x033b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0325, code lost:
        r0 = r1.contex;
        r6 = r0.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x032b, code lost:
        if ((r6 instanceof java.lang.Object[]) != false) goto L_0x0383;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x032f, code lost:
        if ((r6 instanceof java.util.Collection) == false) goto L_0x0332;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0334, code lost:
        if (r0.parent == null) goto L_0x0382;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0336, code lost:
        r6 = r0.parent.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0341, code lost:
        if ("..".equals(r0) == false) goto L_0x0355;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0345, code lost:
        if (r14.object == null) goto L_0x034a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0347, code lost:
        r6 = r14.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x034a, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r14, r0));
        r1.resolveStatus = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x035b, code lost:
        if (com.taobao.weex.el.parse.Operators.DOLLAR_STR.equals(r0) == false) goto L_0x0378;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x035d, code lost:
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0360, code lost:
        if (r2.parent == null) goto L_0x0365;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0362, code lost:
        r2 = r2.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0367, code lost:
        if (r2.object == null) goto L_0x036d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0369, code lost:
        r6 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x036d, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r0));
        r1.resolveStatus = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0378, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r14, r0));
        r1.resolveStatus = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0382, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0385, code lost:
        if (r3.token != 13) goto L_0x0391;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0387, code lost:
        r3.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x038c, code lost:
        if (r13 != false) goto L_0x0390;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x038e, code lost:
        r1.contex = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0390, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x03ac, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, " + r3.info());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x03c9, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r3.token));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x05bb, code lost:
        if (r5 != '}') goto L_0x063e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x05bd, code lost:
        r4 = r3.bp + 1;
        r3.bp = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x05c5, code lost:
        if (r4 < r3.len) goto L_0x05ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x05c7, code lost:
        r9 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x05ca, code lost:
        r9 = r3.text.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x05d0, code lost:
        r3.ch = r9;
        r3.sp = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x05d7, code lost:
        if (r9 != ',') goto L_0x05f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x05d9, code lost:
        r4 = r3.bp + 1;
        r3.bp = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x05e1, code lost:
        if (r4 < r3.len) goto L_0x05e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x05e3, code lost:
        r9 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x05e6, code lost:
        r9 = r3.text.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x05ec, code lost:
        r3.ch = r9;
        r3.token = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x05f5, code lost:
        if (r9 != '}') goto L_0x0611;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x05f7, code lost:
        r4 = r3.bp + 1;
        r3.bp = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x05ff, code lost:
        if (r4 < r3.len) goto L_0x0604;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0601, code lost:
        r9 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x0604, code lost:
        r9 = r3.text.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x060a, code lost:
        r3.ch = r9;
        r3.token = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x0613, code lost:
        if (r9 != ']') goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x0615, code lost:
        r4 = r3.bp + 1;
        r3.bp = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x061d, code lost:
        if (r4 < r3.len) goto L_0x0622;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x061f, code lost:
        r9 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x0622, code lost:
        r9 = r3.text.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x0628, code lost:
        r3.ch = r9;
        r3.token = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x062f, code lost:
        r3.nextToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x0632, code lost:
        if (r13 != false) goto L_0x0639;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x0634, code lost:
        setContext(r1.contex, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x0639, code lost:
        if (r13 != false) goto L_0x063d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x063b, code lost:
        r1.contex = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x063d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0659, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, " + r3.info());
     */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0202 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0215 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x04f4 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0503 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x050c A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x0510 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0515 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x052c  */
    /* JADX WARNING: Removed duplicated region for block: B:433:0x051e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x0146 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0131 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0136 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x013c A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0144 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01c8 A[Catch:{ Exception -> 0x02bf, NumberFormatException -> 0x0161, all -> 0x06e5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r17, java.lang.Object r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            com.alibaba.fastjson.parser.JSONLexer r3 = r1.lexer
            int r4 = r3.token
            r5 = 8
            r6 = 0
            if (r4 != r5) goto L_0x0013
            r3.nextToken()
            return r6
        L_0x0013:
            r7 = 12
            r8 = 16
            if (r4 == r7) goto L_0x0044
            if (r4 != r8) goto L_0x001c
            goto L_0x0044
        L_0x001c:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "syntax error, expect {, actual "
            r2.append(r5)
            java.lang.String r4 = com.alibaba.fastjson.parser.JSONToken.name(r4)
            r2.append(r4)
            java.lang.String r4 = ", "
            r2.append(r4)
            java.lang.String r3 = r3.info()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0044:
            boolean r4 = r0 instanceof com.alibaba.fastjson.JSONObject
            r10 = 1
            if (r4 == 0) goto L_0x0053
            r4 = r0
            com.alibaba.fastjson.JSONObject r4 = (com.alibaba.fastjson.JSONObject) r4
            java.util.Map r4 = r4.getInnerMap()
            r11 = r4
            r4 = 1
            goto L_0x0055
        L_0x0053:
            r11 = r0
            r4 = 0
        L_0x0055:
            int r12 = r3.features
            com.alibaba.fastjson.parser.Feature r13 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat
            int r13 = r13.mask
            r12 = r12 & r13
            if (r12 == 0) goto L_0x0060
            r12 = 1
            goto L_0x0061
        L_0x0060:
            r12 = 0
        L_0x0061:
            boolean r13 = r3.disableCircularReferenceDetect
            com.alibaba.fastjson.parser.ParseContext r14 = r1.contex
            r15 = 0
        L_0x0066:
            char r6 = r3.ch     // Catch:{ all -> 0x06e5 }
            r5 = 125(0x7d, float:1.75E-43)
            r7 = 34
            if (r6 == r7) goto L_0x0075
            if (r6 == r5) goto L_0x0075
            r3.skipWhitespace()     // Catch:{ all -> 0x06e5 }
            char r6 = r3.ch     // Catch:{ all -> 0x06e5 }
        L_0x0075:
            r8 = 44
            if (r6 != r8) goto L_0x0082
            r3.next()     // Catch:{ all -> 0x06e5 }
            r3.skipWhitespace()     // Catch:{ all -> 0x06e5 }
            char r6 = r3.ch     // Catch:{ all -> 0x06e5 }
            goto L_0x0075
        L_0x0082:
            r8 = 58
            r9 = 26
            if (r6 != r7) goto L_0x00bf
            com.alibaba.fastjson.parser.SymbolTable r6 = r1.symbolTable     // Catch:{ all -> 0x06e5 }
            java.lang.String r6 = r3.scanSymbol(r6, r7)     // Catch:{ all -> 0x06e5 }
            char r7 = r3.ch     // Catch:{ all -> 0x06e5 }
            if (r7 == r8) goto L_0x00bb
            r3.skipWhitespace()     // Catch:{ all -> 0x06e5 }
            char r7 = r3.ch     // Catch:{ all -> 0x06e5 }
            if (r7 != r8) goto L_0x009a
            goto L_0x00bb
        L_0x009a:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "expect ':' at "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            int r3 = r3.pos     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            r2.append(r6)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x00bb:
            r7 = r6
            r6 = 0
            goto L_0x01c4
        L_0x00bf:
            if (r6 != r5) goto L_0x00e0
            int r2 = r3.bp     // Catch:{ all -> 0x06e5 }
            int r2 = r2 + r10
            r3.bp = r2     // Catch:{ all -> 0x06e5 }
            int r4 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r2 < r4) goto L_0x00cb
            goto L_0x00d1
        L_0x00cb:
            java.lang.String r4 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r4.charAt(r2)     // Catch:{ all -> 0x06e5 }
        L_0x00d1:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            r2 = 0
            r3.sp = r2     // Catch:{ all -> 0x06e5 }
            r2 = 16
            r3.nextToken(r2)     // Catch:{ all -> 0x06e5 }
            if (r13 != 0) goto L_0x00df
            r1.contex = r14
        L_0x00df:
            return r0
        L_0x00e0:
            r7 = 39
            if (r6 != r7) goto L_0x0111
            com.alibaba.fastjson.parser.SymbolTable r6 = r1.symbolTable     // Catch:{ all -> 0x06e5 }
            r7 = 39
            java.lang.String r6 = r3.scanSymbol(r6, r7)     // Catch:{ all -> 0x06e5 }
            char r7 = r3.ch     // Catch:{ all -> 0x06e5 }
            if (r7 == r8) goto L_0x00f3
            r3.skipWhitespace()     // Catch:{ all -> 0x06e5 }
        L_0x00f3:
            char r7 = r3.ch     // Catch:{ all -> 0x06e5 }
            if (r7 != r8) goto L_0x00f8
            goto L_0x00bb
        L_0x00f8:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "expect ':' at "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            int r3 = r3.pos     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x0111:
            if (r6 == r9) goto L_0x06c9
            r7 = 44
            if (r6 == r7) goto L_0x06ad
            r7 = 48
            if (r6 < r7) goto L_0x0122
            r7 = 57
            if (r6 <= r7) goto L_0x0120
            goto L_0x0122
        L_0x0120:
            r6 = 0
            goto L_0x0127
        L_0x0122:
            r7 = 45
            if (r6 != r7) goto L_0x017c
            goto L_0x0120
        L_0x0127:
            r3.sp = r6     // Catch:{ all -> 0x06e5 }
            r3.scanNumber()     // Catch:{ all -> 0x06e5 }
            int r6 = r3.token     // Catch:{ NumberFormatException -> 0x0161 }
            r7 = 2
            if (r6 != r7) goto L_0x0136
            java.lang.Number r6 = r3.integerValue()     // Catch:{ NumberFormatException -> 0x0161 }
            goto L_0x013a
        L_0x0136:
            java.lang.Number r6 = r3.decimalValue(r10)     // Catch:{ NumberFormatException -> 0x0161 }
        L_0x013a:
            if (r4 == 0) goto L_0x0140
            java.lang.String r6 = r6.toString()     // Catch:{ NumberFormatException -> 0x0161 }
        L_0x0140:
            char r7 = r3.ch     // Catch:{ all -> 0x06e5 }
            if (r7 != r8) goto L_0x0146
            goto L_0x00bb
        L_0x0146:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "parse number key error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x0161:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "parse number key error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x017c:
            r7 = 123(0x7b, float:1.72E-43)
            if (r6 == r7) goto L_0x01bb
            r7 = 91
            if (r6 != r7) goto L_0x0185
            goto L_0x01bb
        L_0x0185:
            com.alibaba.fastjson.parser.SymbolTable r6 = r1.symbolTable     // Catch:{ all -> 0x06e5 }
            java.lang.String r6 = r3.scanSymbolUnQuoted(r6)     // Catch:{ all -> 0x06e5 }
            r3.skipWhitespace()     // Catch:{ all -> 0x06e5 }
            char r7 = r3.ch     // Catch:{ all -> 0x06e5 }
            if (r7 != r8) goto L_0x019a
            if (r4 == 0) goto L_0x00bb
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x06e5 }
            goto L_0x00bb
        L_0x019a:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "expect ':' at "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            int r3 = r3.bp     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = ", actual "
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            r2.append(r7)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x01bb:
            r3.nextToken()     // Catch:{ all -> 0x06e5 }
            java.lang.Object r6 = r16.parse()     // Catch:{ all -> 0x06e5 }
            r7 = r6
            r6 = 1
        L_0x01c4:
            r8 = 13
            if (r6 != 0) goto L_0x0202
            int r6 = r3.bp     // Catch:{ all -> 0x06e5 }
            int r6 = r6 + r10
            r3.bp = r6     // Catch:{ all -> 0x06e5 }
            int r9 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r6 < r9) goto L_0x01d4
            r9 = 26
            goto L_0x01da
        L_0x01d4:
            java.lang.String r9 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r9.charAt(r6)     // Catch:{ all -> 0x06e5 }
        L_0x01da:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
        L_0x01dc:
            r6 = 32
            if (r9 > r6) goto L_0x01ff
            r6 = 32
            if (r9 == r6) goto L_0x01f7
            r6 = 10
            if (r9 == r6) goto L_0x01f7
            if (r9 == r8) goto L_0x01f7
            r6 = 9
            if (r9 == r6) goto L_0x01f7
            r6 = 12
            if (r9 == r6) goto L_0x01f7
            r6 = 8
            if (r9 != r6) goto L_0x0206
            goto L_0x01f9
        L_0x01f7:
            r6 = 8
        L_0x01f9:
            r3.next()     // Catch:{ all -> 0x06e5 }
            char r9 = r3.ch     // Catch:{ all -> 0x06e5 }
            goto L_0x01dc
        L_0x01ff:
            r6 = 8
            goto L_0x0206
        L_0x0202:
            r6 = 8
            char r9 = r3.ch     // Catch:{ all -> 0x06e5 }
        L_0x0206:
            r6 = 0
            r3.sp = r6     // Catch:{ all -> 0x06e5 }
            java.lang.String r6 = "@type"
            if (r7 != r6) goto L_0x0300
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x06e5 }
            boolean r6 = r3.isEnabled(r6)     // Catch:{ all -> 0x06e5 }
            if (r6 != 0) goto L_0x0300
            com.alibaba.fastjson.parser.SymbolTable r5 = r1.symbolTable     // Catch:{ all -> 0x06e5 }
            r6 = 34
            java.lang.String r5 = r3.scanSymbol(r5, r6)     // Catch:{ all -> 0x06e5 }
            r6 = 0
        L_0x021e:
            int r7 = r5.length()     // Catch:{ all -> 0x06e5 }
            if (r6 >= r7) goto L_0x0236
            char r7 = r5.charAt(r6)     // Catch:{ all -> 0x06e5 }
            r9 = 48
            if (r7 < r9) goto L_0x0234
            r9 = 57
            if (r7 <= r9) goto L_0x0231
            goto L_0x0234
        L_0x0231:
            int r6 = r6 + 1
            goto L_0x021e
        L_0x0234:
            r6 = 0
            goto L_0x0237
        L_0x0236:
            r6 = 1
        L_0x0237:
            if (r6 != 0) goto L_0x0243
            com.alibaba.fastjson.parser.ParserConfig r6 = r1.config     // Catch:{ all -> 0x06e5 }
            int r7 = r3.features     // Catch:{ all -> 0x06e5 }
            r9 = 0
            java.lang.Class r6 = r6.checkAutoType(r5, r9, r7)     // Catch:{ all -> 0x06e5 }
            goto L_0x0245
        L_0x0243:
            r9 = 0
            r6 = r9
        L_0x0245:
            if (r6 != 0) goto L_0x0254
            java.lang.String r6 = "@type"
            r0.put(r6, r5)     // Catch:{ all -> 0x06e5 }
            r5 = 8
            r7 = 12
            r8 = 16
            goto L_0x0066
        L_0x0254:
            r4 = 16
            r3.nextToken(r4)     // Catch:{ all -> 0x06e5 }
            int r7 = r3.token     // Catch:{ all -> 0x06e5 }
            if (r7 != r8) goto L_0x02c8
            r3.nextToken(r4)     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.parser.ParserConfig r2 = r1.config     // Catch:{ Exception -> 0x02bf }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.getDeserializer(r6)     // Catch:{ Exception -> 0x02bf }
            boolean r3 = r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer     // Catch:{ Exception -> 0x02bf }
            if (r3 == 0) goto L_0x029c
            com.alibaba.fastjson.parser.JavaBeanDeserializer r2 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r2     // Catch:{ Exception -> 0x02bf }
            java.lang.Object r3 = r2.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r1, (java.lang.reflect.Type) r6)     // Catch:{ Exception -> 0x02bf }
            java.util.Set r0 = r17.entrySet()     // Catch:{ Exception -> 0x02bf }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x02bf }
        L_0x0278:
            boolean r4 = r0.hasNext()     // Catch:{ Exception -> 0x02bf }
            if (r4 == 0) goto L_0x029d
            java.lang.Object r4 = r0.next()     // Catch:{ Exception -> 0x02bf }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Exception -> 0x02bf }
            java.lang.Object r7 = r4.getKey()     // Catch:{ Exception -> 0x02bf }
            boolean r8 = r7 instanceof java.lang.String     // Catch:{ Exception -> 0x02bf }
            if (r8 == 0) goto L_0x0278
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x02bf }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r7 = r2.getFieldDeserializer(r7)     // Catch:{ Exception -> 0x02bf }
            if (r7 == 0) goto L_0x0278
            java.lang.Object r4 = r4.getValue()     // Catch:{ Exception -> 0x02bf }
            r7.setValue((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x02bf }
            goto L_0x0278
        L_0x029c:
            r3 = r9
        L_0x029d:
            if (r3 != 0) goto L_0x02ba
            java.lang.Class<java.lang.Cloneable> r0 = java.lang.Cloneable.class
            if (r6 != r0) goto L_0x02a9
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ Exception -> 0x02bf }
            r3.<init>()     // Catch:{ Exception -> 0x02bf }
            goto L_0x02ba
        L_0x02a9:
            java.lang.String r0 = "java.util.Collections$EmptyMap"
            boolean r0 = r0.equals(r5)     // Catch:{ Exception -> 0x02bf }
            if (r0 == 0) goto L_0x02b6
            java.util.Map r3 = java.util.Collections.emptyMap()     // Catch:{ Exception -> 0x02bf }
            goto L_0x02ba
        L_0x02b6:
            java.lang.Object r3 = r6.newInstance()     // Catch:{ Exception -> 0x02bf }
        L_0x02ba:
            if (r13 != 0) goto L_0x02be
            r1.contex = r14
        L_0x02be:
            return r3
        L_0x02bf:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = "create instance error"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x06e5 }
            throw r2     // Catch:{ all -> 0x06e5 }
        L_0x02c8:
            r3 = 2
            r1.resolveStatus = r3     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.parser.ParseContext r3 = r1.contex     // Catch:{ all -> 0x06e5 }
            if (r3 == 0) goto L_0x02d6
            boolean r3 = r2 instanceof java.lang.Integer     // Catch:{ all -> 0x06e5 }
            if (r3 != 0) goto L_0x02d6
            r16.popContext()     // Catch:{ all -> 0x06e5 }
        L_0x02d6:
            int r3 = r17.size()     // Catch:{ all -> 0x06e5 }
            if (r3 <= 0) goto L_0x02ea
            com.alibaba.fastjson.parser.ParserConfig r2 = r1.config     // Catch:{ all -> 0x06e5 }
            java.lang.Object r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r6, (com.alibaba.fastjson.parser.ParserConfig) r2)     // Catch:{ all -> 0x06e5 }
            r1.parseObject((java.lang.Object) r0)     // Catch:{ all -> 0x06e5 }
            if (r13 != 0) goto L_0x02e9
            r1.contex = r14
        L_0x02e9:
            return r0
        L_0x02ea:
            com.alibaba.fastjson.parser.ParserConfig r0 = r1.config     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r0 = r0.getDeserializer(r6)     // Catch:{ all -> 0x06e5 }
            java.lang.Object r2 = r0.deserialze(r1, r6, r2)     // Catch:{ all -> 0x06e5 }
            boolean r0 = r0 instanceof com.alibaba.fastjson.parser.MapDeserializer     // Catch:{ all -> 0x06e5 }
            if (r0 == 0) goto L_0x02fb
            r0 = 0
            r1.resolveStatus = r0     // Catch:{ all -> 0x06e5 }
        L_0x02fb:
            if (r13 != 0) goto L_0x02ff
            r1.contex = r14
        L_0x02ff:
            return r2
        L_0x0300:
            java.lang.String r6 = "$ref"
            r5 = 4
            if (r7 != r6) goto L_0x03ca
            if (r14 == 0) goto L_0x03ca
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x06e5 }
            boolean r6 = r3.isEnabled(r6)     // Catch:{ all -> 0x06e5 }
            if (r6 != 0) goto L_0x03ca
            r3.nextToken(r5)     // Catch:{ all -> 0x06e5 }
            int r0 = r3.token     // Catch:{ all -> 0x06e5 }
            if (r0 != r5) goto L_0x03ad
            java.lang.String r0 = r3.stringVal()     // Catch:{ all -> 0x06e5 }
            r3.nextToken(r8)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = "@"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x033b
            com.alibaba.fastjson.parser.ParseContext r0 = r1.contex     // Catch:{ all -> 0x06e5 }
            java.lang.Object r6 = r0.object     // Catch:{ all -> 0x06e5 }
            boolean r2 = r6 instanceof java.lang.Object[]     // Catch:{ all -> 0x06e5 }
            if (r2 != 0) goto L_0x0383
            boolean r2 = r6 instanceof java.util.Collection     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x0332
            goto L_0x0383
        L_0x0332:
            com.alibaba.fastjson.parser.ParseContext r2 = r0.parent     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x0382
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x06e5 }
            java.lang.Object r6 = r0.object     // Catch:{ all -> 0x06e5 }
            goto L_0x0383
        L_0x033b:
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x0355
            java.lang.Object r2 = r14.object     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x034a
            java.lang.Object r6 = r14.object     // Catch:{ all -> 0x06e5 }
            goto L_0x0383
        L_0x034a:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x06e5 }
            r2.<init>(r14, r0)     // Catch:{ all -> 0x06e5 }
            r1.addResolveTask(r2)     // Catch:{ all -> 0x06e5 }
            r1.resolveStatus = r10     // Catch:{ all -> 0x06e5 }
            goto L_0x0382
        L_0x0355:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x0378
            r2 = r14
        L_0x035e:
            com.alibaba.fastjson.parser.ParseContext r4 = r2.parent     // Catch:{ all -> 0x06e5 }
            if (r4 == 0) goto L_0x0365
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent     // Catch:{ all -> 0x06e5 }
            goto L_0x035e
        L_0x0365:
            java.lang.Object r4 = r2.object     // Catch:{ all -> 0x06e5 }
            if (r4 == 0) goto L_0x036d
            java.lang.Object r0 = r2.object     // Catch:{ all -> 0x06e5 }
            r6 = r0
            goto L_0x0383
        L_0x036d:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x06e5 }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x06e5 }
            r1.addResolveTask(r4)     // Catch:{ all -> 0x06e5 }
            r1.resolveStatus = r10     // Catch:{ all -> 0x06e5 }
            goto L_0x0382
        L_0x0378:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x06e5 }
            r2.<init>(r14, r0)     // Catch:{ all -> 0x06e5 }
            r1.addResolveTask(r2)     // Catch:{ all -> 0x06e5 }
            r1.resolveStatus = r10     // Catch:{ all -> 0x06e5 }
        L_0x0382:
            r6 = 0
        L_0x0383:
            int r0 = r3.token     // Catch:{ all -> 0x06e5 }
            if (r0 != r8) goto L_0x0391
            r0 = 16
            r3.nextToken(r0)     // Catch:{ all -> 0x06e5 }
            if (r13 != 0) goto L_0x0390
            r1.contex = r14
        L_0x0390:
            return r6
        L_0x0391:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x03ad:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "illegal ref, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            int r3 = r3.token     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = com.alibaba.fastjson.parser.JSONToken.name(r3)     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x03ca:
            if (r13 != 0) goto L_0x03db
            if (r15 != 0) goto L_0x03db
            com.alibaba.fastjson.parser.ParseContext r6 = r1.contex     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.parser.ParseContext r6 = r1.setContext(r6, r0, r2)     // Catch:{ all -> 0x06e5 }
            if (r14 != 0) goto L_0x03d7
            r14 = r6
        L_0x03d7:
            r6 = 34
            r15 = 1
            goto L_0x03dd
        L_0x03db:
            r6 = 34
        L_0x03dd:
            if (r9 != r6) goto L_0x0405
            java.lang.String r5 = r3.scanStringValue(r6)     // Catch:{ all -> 0x06e5 }
            if (r12 == 0) goto L_0x03f9
            com.alibaba.fastjson.parser.JSONLexer r6 = new com.alibaba.fastjson.parser.JSONLexer     // Catch:{ all -> 0x06e5 }
            r6.<init>(r5)     // Catch:{ all -> 0x06e5 }
            boolean r9 = r6.scanISO8601DateIfMatch(r10)     // Catch:{ all -> 0x06e5 }
            if (r9 == 0) goto L_0x03f6
            java.util.Calendar r5 = r6.calendar     // Catch:{ all -> 0x06e5 }
            java.util.Date r5 = r5.getTime()     // Catch:{ all -> 0x06e5 }
        L_0x03f6:
            r6.close()     // Catch:{ all -> 0x06e5 }
        L_0x03f9:
            if (r11 == 0) goto L_0x0400
            r11.put(r7, r5)     // Catch:{ all -> 0x06e5 }
            goto L_0x058b
        L_0x0400:
            r0.put(r7, r5)     // Catch:{ all -> 0x06e5 }
            goto L_0x058b
        L_0x0405:
            r6 = 48
            if (r9 < r6) goto L_0x040d
            r6 = 57
            if (r9 <= r6) goto L_0x0411
        L_0x040d:
            r6 = 45
            if (r9 != r6) goto L_0x041a
        L_0x0411:
            java.lang.Number r5 = r3.scanNumberValue()     // Catch:{ all -> 0x06e5 }
            r11.put(r7, r5)     // Catch:{ all -> 0x06e5 }
            goto L_0x058b
        L_0x041a:
            r6 = 91
            if (r9 != r6) goto L_0x048e
            r5 = 14
            r3.token = r5     // Catch:{ all -> 0x06e5 }
            int r5 = r3.bp     // Catch:{ all -> 0x06e5 }
            int r5 = r5 + r10
            r3.bp = r5     // Catch:{ all -> 0x06e5 }
            int r6 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r5 < r6) goto L_0x042e
            r9 = 26
            goto L_0x0434
        L_0x042e:
            java.lang.String r6 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r6.charAt(r5)     // Catch:{ all -> 0x06e5 }
        L_0x0434:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x06e5 }
            r5.<init>()     // Catch:{ all -> 0x06e5 }
            if (r2 == 0) goto L_0x0447
            java.lang.Class r6 = r18.getClass()     // Catch:{ all -> 0x06e5 }
            java.lang.Class<java.lang.Integer> r9 = java.lang.Integer.class
            if (r6 != r9) goto L_0x0447
            r6 = 1
            goto L_0x0448
        L_0x0447:
            r6 = 0
        L_0x0448:
            if (r6 != 0) goto L_0x044d
            r1.setContext(r14)     // Catch:{ all -> 0x06e5 }
        L_0x044d:
            r1.parseArray((java.util.Collection) r5, (java.lang.Object) r7)     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.JSONArray r6 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x06e5 }
            r6.<init>((java.util.List<java.lang.Object>) r5)     // Catch:{ all -> 0x06e5 }
            if (r11 == 0) goto L_0x045b
            r11.put(r7, r6)     // Catch:{ all -> 0x06e5 }
            goto L_0x045e
        L_0x045b:
            r0.put(r7, r6)     // Catch:{ all -> 0x06e5 }
        L_0x045e:
            int r5 = r3.token     // Catch:{ all -> 0x06e5 }
            if (r5 != r8) goto L_0x046c
            r6 = 16
            r3.nextToken(r6)     // Catch:{ all -> 0x06e5 }
            if (r13 != 0) goto L_0x046b
            r1.contex = r14
        L_0x046b:
            return r0
        L_0x046c:
            r6 = 16
            if (r5 != r6) goto L_0x0472
            goto L_0x05b3
        L_0x0472:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x048e:
            r6 = 123(0x7b, float:1.72E-43)
            if (r9 != r6) goto L_0x054e
            int r5 = r3.bp     // Catch:{ all -> 0x06e5 }
            int r5 = r5 + r10
            r3.bp = r5     // Catch:{ all -> 0x06e5 }
            int r6 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r5 < r6) goto L_0x049e
            r9 = 26
            goto L_0x04a4
        L_0x049e:
            java.lang.String r6 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r6.charAt(r5)     // Catch:{ all -> 0x06e5 }
        L_0x04a4:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            r6 = 12
            r3.token = r6     // Catch:{ all -> 0x06e5 }
            boolean r5 = r2 instanceof java.lang.Integer     // Catch:{ all -> 0x06e5 }
            int r9 = r3.features     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x06e5 }
            int r6 = r6.mask     // Catch:{ all -> 0x06e5 }
            r6 = r6 & r9
            if (r6 == 0) goto L_0x04c0
            com.alibaba.fastjson.JSONObject r6 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x06e5 }
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap     // Catch:{ all -> 0x06e5 }
            r9.<init>()     // Catch:{ all -> 0x06e5 }
            r6.<init>((java.util.Map<java.lang.String, java.lang.Object>) r9)     // Catch:{ all -> 0x06e5 }
            goto L_0x04c5
        L_0x04c0:
            com.alibaba.fastjson.JSONObject r6 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x06e5 }
            r6.<init>()     // Catch:{ all -> 0x06e5 }
        L_0x04c5:
            if (r13 != 0) goto L_0x04ce
            if (r5 != 0) goto L_0x04ce
            com.alibaba.fastjson.parser.ParseContext r9 = r1.setContext(r14, r6, r7)     // Catch:{ all -> 0x06e5 }
            goto L_0x04cf
        L_0x04ce:
            r9 = 0
        L_0x04cf:
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r8 = r1.fieldTypeResolver     // Catch:{ all -> 0x06e5 }
            if (r8 == 0) goto L_0x04f0
            if (r7 == 0) goto L_0x04da
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x06e5 }
            goto L_0x04db
        L_0x04da:
            r8 = 0
        L_0x04db:
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r10 = r1.fieldTypeResolver     // Catch:{ all -> 0x06e5 }
            java.lang.reflect.Type r8 = r10.resolve(r0, r8)     // Catch:{ all -> 0x06e5 }
            if (r8 == 0) goto L_0x04f0
            com.alibaba.fastjson.parser.ParserConfig r10 = r1.config     // Catch:{ all -> 0x06e5 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r10 = r10.getDeserializer(r8)     // Catch:{ all -> 0x06e5 }
            java.lang.Object r8 = r10.deserialze(r1, r8, r7)     // Catch:{ all -> 0x06e5 }
            r10 = r8
            r8 = 1
            goto L_0x04f2
        L_0x04f0:
            r8 = 0
            r10 = 0
        L_0x04f2:
            if (r8 != 0) goto L_0x04f8
            java.lang.Object r10 = r1.parseObject((java.util.Map) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x06e5 }
        L_0x04f8:
            if (r9 == 0) goto L_0x04fe
            if (r6 == r10) goto L_0x04fe
            r9.object = r0     // Catch:{ all -> 0x06e5 }
        L_0x04fe:
            int r6 = r1.resolveStatus     // Catch:{ all -> 0x06e5 }
            r8 = 1
            if (r6 != r8) goto L_0x050a
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x06e5 }
            r1.checkMapResolve(r0, r6)     // Catch:{ all -> 0x06e5 }
        L_0x050a:
            if (r11 == 0) goto L_0x0510
            r11.put(r7, r10)     // Catch:{ all -> 0x06e5 }
            goto L_0x0513
        L_0x0510:
            r0.put(r7, r10)     // Catch:{ all -> 0x06e5 }
        L_0x0513:
            if (r5 == 0) goto L_0x0518
            r1.setContext(r14, r10, r7)     // Catch:{ all -> 0x06e5 }
        L_0x0518:
            int r5 = r3.token     // Catch:{ all -> 0x06e5 }
            r6 = 13
            if (r5 != r6) goto L_0x052c
            r6 = 16
            r3.nextToken(r6)     // Catch:{ all -> 0x06e5 }
            if (r13 != 0) goto L_0x0527
            r1.contex = r14     // Catch:{ all -> 0x06e5 }
        L_0x0527:
            if (r13 != 0) goto L_0x052b
            r1.contex = r14
        L_0x052b:
            return r0
        L_0x052c:
            r6 = 16
            if (r5 != r6) goto L_0x0532
            goto L_0x05b3
        L_0x0532:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x054e:
            r6 = 116(0x74, float:1.63E-43)
            if (r9 != r6) goto L_0x056e
            java.lang.String r5 = r3.text     // Catch:{ all -> 0x06e5 }
            java.lang.String r6 = "true"
            int r8 = r3.bp     // Catch:{ all -> 0x06e5 }
            boolean r5 = r5.startsWith(r6, r8)     // Catch:{ all -> 0x06e5 }
            if (r5 == 0) goto L_0x058b
            int r5 = r3.bp     // Catch:{ all -> 0x06e5 }
            int r5 = r5 + 3
            r3.bp = r5     // Catch:{ all -> 0x06e5 }
            r3.next()     // Catch:{ all -> 0x06e5 }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x06e5 }
            r0.put(r7, r5)     // Catch:{ all -> 0x06e5 }
            goto L_0x058b
        L_0x056e:
            r6 = 102(0x66, float:1.43E-43)
            if (r9 != r6) goto L_0x065a
            java.lang.String r6 = r3.text     // Catch:{ all -> 0x06e5 }
            java.lang.String r8 = "false"
            int r9 = r3.bp     // Catch:{ all -> 0x06e5 }
            boolean r6 = r6.startsWith(r8, r9)     // Catch:{ all -> 0x06e5 }
            if (r6 == 0) goto L_0x058b
            int r6 = r3.bp     // Catch:{ all -> 0x06e5 }
            int r6 = r6 + r5
            r3.bp = r6     // Catch:{ all -> 0x06e5 }
            r3.next()     // Catch:{ all -> 0x06e5 }
            java.lang.Boolean r5 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x06e5 }
            r0.put(r7, r5)     // Catch:{ all -> 0x06e5 }
        L_0x058b:
            char r5 = r3.ch     // Catch:{ all -> 0x06e5 }
            r6 = 44
            if (r5 == r6) goto L_0x059a
            r6 = 125(0x7d, float:1.75E-43)
            if (r5 == r6) goto L_0x059a
            r3.skipWhitespace()     // Catch:{ all -> 0x06e5 }
            char r5 = r3.ch     // Catch:{ all -> 0x06e5 }
        L_0x059a:
            r6 = 44
            if (r5 != r6) goto L_0x05b9
            int r5 = r3.bp     // Catch:{ all -> 0x06e5 }
            r6 = 1
            int r5 = r5 + r6
            r3.bp = r5     // Catch:{ all -> 0x06e5 }
            int r6 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r5 < r6) goto L_0x05ab
            r9 = 26
            goto L_0x05b1
        L_0x05ab:
            java.lang.String r6 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r6.charAt(r5)     // Catch:{ all -> 0x06e5 }
        L_0x05b1:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
        L_0x05b3:
            r5 = 0
            r6 = 1
            r7 = 16
            goto L_0x0688
        L_0x05b9:
            r4 = 125(0x7d, float:1.75E-43)
            if (r5 != r4) goto L_0x063e
            int r4 = r3.bp     // Catch:{ all -> 0x06e5 }
            r5 = 1
            int r4 = r4 + r5
            r3.bp = r4     // Catch:{ all -> 0x06e5 }
            int r5 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r4 < r5) goto L_0x05ca
            r9 = 26
            goto L_0x05d0
        L_0x05ca:
            java.lang.String r5 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r5.charAt(r4)     // Catch:{ all -> 0x06e5 }
        L_0x05d0:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            r5 = 0
            r3.sp = r5     // Catch:{ all -> 0x06e5 }
            r4 = 44
            if (r9 != r4) goto L_0x05f3
            int r4 = r3.bp     // Catch:{ all -> 0x06e5 }
            r5 = 1
            int r4 = r4 + r5
            r3.bp = r4     // Catch:{ all -> 0x06e5 }
            int r5 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r4 < r5) goto L_0x05e6
            r9 = 26
            goto L_0x05ec
        L_0x05e6:
            java.lang.String r5 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r5.charAt(r4)     // Catch:{ all -> 0x06e5 }
        L_0x05ec:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            r4 = 16
            r3.token = r4     // Catch:{ all -> 0x06e5 }
            goto L_0x0632
        L_0x05f3:
            r4 = 125(0x7d, float:1.75E-43)
            if (r9 != r4) goto L_0x0611
            int r4 = r3.bp     // Catch:{ all -> 0x06e5 }
            r5 = 1
            int r4 = r4 + r5
            r3.bp = r4     // Catch:{ all -> 0x06e5 }
            int r5 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r4 < r5) goto L_0x0604
            r9 = 26
            goto L_0x060a
        L_0x0604:
            java.lang.String r5 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r5.charAt(r4)     // Catch:{ all -> 0x06e5 }
        L_0x060a:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            r4 = 13
            r3.token = r4     // Catch:{ all -> 0x06e5 }
            goto L_0x0632
        L_0x0611:
            r4 = 93
            if (r9 != r4) goto L_0x062f
            int r4 = r3.bp     // Catch:{ all -> 0x06e5 }
            r6 = 1
            int r4 = r4 + r6
            r3.bp = r4     // Catch:{ all -> 0x06e5 }
            int r5 = r3.len     // Catch:{ all -> 0x06e5 }
            if (r4 < r5) goto L_0x0622
            r9 = 26
            goto L_0x0628
        L_0x0622:
            java.lang.String r5 = r3.text     // Catch:{ all -> 0x06e5 }
            char r9 = r5.charAt(r4)     // Catch:{ all -> 0x06e5 }
        L_0x0628:
            r3.ch = r9     // Catch:{ all -> 0x06e5 }
            r4 = 15
            r3.token = r4     // Catch:{ all -> 0x06e5 }
            goto L_0x0632
        L_0x062f:
            r3.nextToken()     // Catch:{ all -> 0x06e5 }
        L_0x0632:
            if (r13 != 0) goto L_0x0639
            com.alibaba.fastjson.parser.ParseContext r3 = r1.contex     // Catch:{ all -> 0x06e5 }
            r1.setContext(r3, r0, r2)     // Catch:{ all -> 0x06e5 }
        L_0x0639:
            if (r13 != 0) goto L_0x063d
            r1.contex = r14
        L_0x063d:
            return r0
        L_0x063e:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x065a:
            r5 = 0
            r6 = 1
            r3.nextToken()     // Catch:{ all -> 0x06e5 }
            java.lang.Object r8 = r16.parse()     // Catch:{ all -> 0x06e5 }
            java.lang.Class r9 = r17.getClass()     // Catch:{ all -> 0x06e5 }
            java.lang.Class<com.alibaba.fastjson.JSONObject> r10 = com.alibaba.fastjson.JSONObject.class
            if (r9 != r10) goto L_0x066f
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x06e5 }
        L_0x066f:
            r0.put(r7, r8)     // Catch:{ all -> 0x06e5 }
            int r7 = r3.token     // Catch:{ all -> 0x06e5 }
            r8 = 13
            if (r7 != r8) goto L_0x0682
            r7 = 16
            r3.nextToken(r7)     // Catch:{ all -> 0x06e5 }
            if (r13 != 0) goto L_0x0681
            r1.contex = r14
        L_0x0681:
            return r0
        L_0x0682:
            r7 = 16
            int r8 = r3.token     // Catch:{ all -> 0x06e5 }
            if (r8 != r7) goto L_0x0691
        L_0x0688:
            r5 = 8
            r7 = 12
            r8 = 16
            r10 = 1
            goto L_0x0066
        L_0x0691:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x06ad:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x06c9:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x06e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06e5 }
            r2.<init>()     // Catch:{ all -> 0x06e5 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x06e5 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x06e5 }
            r2.append(r3)     // Catch:{ all -> 0x06e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06e5 }
            r0.<init>(r2)     // Catch:{ all -> 0x06e5 }
            throw r0     // Catch:{ all -> 0x06e5 }
        L_0x06e5:
            r0 = move-exception
            if (r13 != 0) goto L_0x06ea
            r1.contex = r14
        L_0x06ea:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public <T> T parseObject(Class<T> cls) {
        return parseObject((Type) cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return parseObject(type, (Object) null);
    }

    public <T> T parseObject(Type type, Object obj) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (this.lexer.token == 4) {
            if (type == byte[].class) {
                Object bytesValue = this.lexer.bytesValue();
                this.lexer.nextToken();
                return bytesValue;
            } else if (type == char[].class) {
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return stringVal.toCharArray();
            }
        }
        try {
            return this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer objectDeserializer;
        Object obj2;
        if (this.lexer.token == 21 || this.lexer.token == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token == 14) {
            if (Integer.TYPE == type) {
                objectDeserializer = IntegerCodec.instance;
                this.lexer.nextToken(2);
            } else if (String.class == type) {
                objectDeserializer = StringCodec.instance;
                this.lexer.nextToken(4);
            } else {
                objectDeserializer = this.config.getDeserializer(type);
                this.lexer.nextToken(12);
            }
            ParseContext parseContext = this.contex;
            if (!this.lexer.disableCircularReferenceDetect) {
                setContext(this.contex, collection, obj);
            }
            int i = 0;
            while (true) {
                try {
                    if (this.lexer.token == 16) {
                        this.lexer.nextToken();
                    } else if (this.lexer.token == 15) {
                        this.contex = parseContext;
                        this.lexer.nextToken(16);
                        return;
                    } else {
                        Object obj3 = null;
                        if (Integer.TYPE == type) {
                            collection.add(IntegerCodec.instance.deserialze(this, (Type) null, (Object) null));
                        } else if (String.class == type) {
                            if (this.lexer.token == 4) {
                                obj2 = this.lexer.stringVal();
                                this.lexer.nextToken(16);
                            } else {
                                Object parse = parse();
                                if (parse != null) {
                                    obj3 = parse.toString();
                                }
                                obj2 = obj3;
                            }
                            collection.add(obj2);
                        } else {
                            if (this.lexer.token == 8) {
                                this.lexer.nextToken();
                            } else {
                                obj3 = objectDeserializer.deserialze(this, type, Integer.valueOf(i));
                            }
                            collection.add(obj3);
                            if (this.resolveStatus == 1) {
                                checkListResolve(collection);
                            }
                        }
                        if (this.lexer.token == 16) {
                            this.lexer.nextToken();
                        }
                        i++;
                    }
                } catch (Throwable th) {
                    this.contex = parseContext;
                    throw th;
                }
            }
        } else {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token) + ", " + this.lexer.info());
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object obj;
        boolean z;
        Class<?> cls;
        if (this.lexer.token == 8) {
            this.lexer.nextToken(16);
            return null;
        } else if (this.lexer.token == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token == 15) {
                    this.lexer.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            }
            this.lexer.nextToken(2);
            int i = 0;
            while (i < typeArr.length) {
                if (this.lexer.token == 8) {
                    this.lexer.nextToken(16);
                    obj = null;
                } else {
                    Class<String> cls2 = typeArr[i];
                    if (cls2 == Integer.TYPE || cls2 == Integer.class) {
                        if (this.lexer.token == 2) {
                            obj = Integer.valueOf(this.lexer.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            obj = TypeUtils.cast(parse(), (Type) cls2, this.config);
                        }
                    } else if (cls2 != String.class) {
                        if (i != typeArr.length - 1 || !(cls2 instanceof Class)) {
                            cls = null;
                            z = false;
                        } else {
                            Class cls3 = cls2;
                            z = cls3.isArray();
                            cls = cls3.getComponentType();
                        }
                        if (!z || this.lexer.token == 14) {
                            obj = this.config.getDeserializer(cls2).deserialze(this, cls2, (Object) null);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer(cls);
                            if (this.lexer.token != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, cls2, (Object) null));
                                    if (this.lexer.token != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(12);
                                }
                                if (this.lexer.token != 15) {
                                    throw new JSONException("syntax error, " + this.lexer.info());
                                }
                            }
                            obj = TypeUtils.cast((Object) arrayList, (Type) cls2, this.config);
                        }
                    } else if (this.lexer.token == 4) {
                        obj = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        obj = TypeUtils.cast(parse(), (Type) cls2, this.config);
                    }
                }
                objArr[i] = obj;
                if (this.lexer.token == 15) {
                    break;
                } else if (this.lexer.token == 16) {
                    if (i == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i++;
                } else {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
            }
            if (this.lexer.token == 15) {
                this.lexer.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error, " + this.lexer.info());
        } else {
            throw new JSONException("syntax error, " + this.lexer.info());
        }
    }

    public void parseObject(Object obj) {
        Object obj2;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        int i = this.lexer.token;
        if (i == 12 || i == 16) {
            while (true) {
                String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
                if (scanSymbol == null) {
                    if (this.lexer.token == 13) {
                        this.lexer.nextToken(16);
                        return;
                    } else if (this.lexer.token == 16) {
                        continue;
                    }
                }
                FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
                if (fieldDeserializer != null) {
                    Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                    Type type = fieldDeserializer.fieldInfo.fieldType;
                    if (cls2 == Integer.TYPE) {
                        this.lexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
                        obj2 = IntegerCodec.instance.deserialze(this, type, (Object) null);
                    } else if (cls2 == String.class) {
                        this.lexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
                        obj2 = parseString();
                    } else if (cls2 == Long.TYPE) {
                        this.lexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
                        obj2 = IntegerCodec.instance.deserialze(this, type, (Object) null);
                    } else {
                        ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                        this.lexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
                        obj2 = deserializer2.deserialze(this, type, (Object) null);
                    }
                    fieldDeserializer.setValue(obj, obj2);
                    if (this.lexer.token != 16 && this.lexer.token == 13) {
                        this.lexer.nextToken(16);
                        return;
                    }
                } else if ((this.lexer.features & Feature.IgnoreNotMatch.mask) != 0) {
                    this.lexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
                    parse();
                    if (this.lexer.token == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
            }
        } else {
            throw new JSONException("syntax error, expect {, actual " + JSONToken.name(i));
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                ArrayList arrayList = new ArrayList();
                parseArray((Class<?>) (Class) type2, (Collection) arrayList);
                return arrayList;
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                Type type3 = wildcardType.getUpperBounds()[0];
                if (!Object.class.equals(type3)) {
                    ArrayList arrayList2 = new ArrayList();
                    parseArray((Class<?>) (Class) type3, (Collection) arrayList2);
                    return arrayList2;
                } else if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
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
                            parseArray((Class<?>) (Class) type4, (Collection) arrayList3);
                            return arrayList3;
                        }
                    } else {
                        throw new JSONException("not support : " + typeVariable);
                    }
                }
                if (type2 instanceof ParameterizedType) {
                    ArrayList arrayList4 = new ArrayList();
                    parseArray((Type) (ParameterizedType) type2, (Collection) arrayList4);
                    return arrayList4;
                }
                throw new JSONException("TODO : " + type);
            }
        } else {
            throw new JSONException("not support type " + type);
        }
    }

    /* access modifiers changed from: protected */
    public void checkListResolve(Collection collection) {
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.contex;
            this.resolveStatus = 0;
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    /* access modifiers changed from: protected */
    public void checkMapResolve(Map map, Object obj) {
        ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
        ResolveTask lastResolveTask = getLastResolveTask();
        lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
        lastResolveTask.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) (this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject((Map<String, Object>) new LinkedHashMap()) : new JSONObject(), (Object) null);
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v32, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v33, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v34, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v35, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v36, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v37, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v38, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v40, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0131 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x013a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a4 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d9 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0116 A[Catch:{ all -> 0x0257 }, LOOP:1: B:60:0x0114->B:61:0x0116, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0124 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x012a A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0144 A[SYNTHETIC, Splitter:B:71:0x0144] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0152 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0177 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x017d A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0185 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x018d A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01b8 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01d8 A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01ea A[Catch:{ all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01f3 A[Catch:{ all -> 0x0257 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseArray(java.util.Collection r17, java.lang.Object r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            com.alibaba.fastjson.parser.JSONLexer r2 = r1.lexer
            int r2 = r2.token
            r3 = 21
            if (r2 == r3) goto L_0x0010
            r3 = 22
            if (r2 != r3) goto L_0x0019
        L_0x0010:
            com.alibaba.fastjson.parser.JSONLexer r2 = r1.lexer
            r2.nextToken()
            com.alibaba.fastjson.parser.JSONLexer r2 = r1.lexer
            int r2 = r2.token
        L_0x0019:
            r3 = 14
            if (r2 != r3) goto L_0x025d
            com.alibaba.fastjson.parser.JSONLexer r2 = r1.lexer
            boolean r2 = r2.disableCircularReferenceDetect
            com.alibaba.fastjson.parser.ParseContext r3 = r1.contex
            if (r2 != 0) goto L_0x002c
            com.alibaba.fastjson.parser.ParseContext r4 = r1.contex
            r5 = r18
            r1.setContext(r4, r0, r5)
        L_0x002c:
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            char r4 = r4.ch     // Catch:{ all -> 0x0257 }
            r5 = 123(0x7b, float:1.72E-43)
            r6 = 93
            r7 = 4
            r8 = 12
            r10 = 0
            r11 = 34
            r12 = 16
            r13 = 1
            if (r4 == r11) goto L_0x007a
            if (r4 != r6) goto L_0x0050
            com.alibaba.fastjson.parser.JSONLexer r0 = r1.lexer     // Catch:{ all -> 0x0257 }
            r0.next()     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r0 = r1.lexer     // Catch:{ all -> 0x0257 }
            r0.nextToken(r12)     // Catch:{ all -> 0x0257 }
            if (r2 != 0) goto L_0x004f
            r1.contex = r3
        L_0x004f:
            return
        L_0x0050:
            if (r4 != r5) goto L_0x0073
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r14 = r4.bp     // Catch:{ all -> 0x0257 }
            int r14 = r14 + r13
            r4.bp = r14     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r15 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r15 = r15.len     // Catch:{ all -> 0x0257 }
            if (r14 < r15) goto L_0x0064
            r14 = 26
            goto L_0x006c
        L_0x0064:
            com.alibaba.fastjson.parser.JSONLexer r15 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.String r15 = r15.text     // Catch:{ all -> 0x0257 }
            char r14 = r15.charAt(r14)     // Catch:{ all -> 0x0257 }
        L_0x006c:
            r4.ch = r14     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            r4.token = r8     // Catch:{ all -> 0x0257 }
            goto L_0x0078
        L_0x0073:
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            r4.nextToken(r8)     // Catch:{ all -> 0x0257 }
        L_0x0078:
            r4 = 0
            goto L_0x008d
        L_0x007a:
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r4 = r4.features     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.Feature r14 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x0257 }
            int r14 = r14.mask     // Catch:{ all -> 0x0257 }
            r4 = r4 & r14
            if (r4 != 0) goto L_0x0087
            r4 = 1
            goto L_0x008d
        L_0x0087:
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            r4.nextToken(r7)     // Catch:{ all -> 0x0257 }
            goto L_0x0078
        L_0x008d:
            r14 = 0
        L_0x008e:
            if (r4 == 0) goto L_0x0110
            com.alibaba.fastjson.parser.JSONLexer r15 = r1.lexer     // Catch:{ all -> 0x0257 }
            char r15 = r15.ch     // Catch:{ all -> 0x0257 }
            if (r15 != r11) goto L_0x0110
            com.alibaba.fastjson.parser.JSONLexer r15 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.String r15 = r15.scanStringValue(r11)     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r9 = r1.lexer     // Catch:{ all -> 0x0257 }
            char r9 = r9.ch     // Catch:{ all -> 0x0257 }
            r8 = 44
            if (r9 != r8) goto L_0x00d9
            com.alibaba.fastjson.parser.JSONLexer r8 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r9 = r8.bp     // Catch:{ all -> 0x0257 }
            int r9 = r9 + r13
            r8.bp = r9     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r8 = r1.lexer     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.len     // Catch:{ all -> 0x0257 }
            if (r9 < r5) goto L_0x00b6
            r9 = 26
            goto L_0x00be
        L_0x00b6:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.String r5 = r5.text     // Catch:{ all -> 0x0257 }
            char r9 = r5.charAt(r9)     // Catch:{ all -> 0x0257 }
        L_0x00be:
            r8.ch = r9     // Catch:{ all -> 0x0257 }
            r0.add(r15)     // Catch:{ all -> 0x0257 }
            int r5 = r1.resolveStatus     // Catch:{ all -> 0x0257 }
            if (r5 != r13) goto L_0x00ca
            r16.checkListResolve(r17)     // Catch:{ all -> 0x0257 }
        L_0x00ca:
            if (r9 != r11) goto L_0x00d2
        L_0x00cc:
            r8 = 123(0x7b, float:1.72E-43)
            r9 = 12
            goto L_0x024d
        L_0x00d2:
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            r4.nextToken()     // Catch:{ all -> 0x0257 }
            r4 = 0
            goto L_0x0110
        L_0x00d9:
            if (r9 != r6) goto L_0x010b
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r4.bp     // Catch:{ all -> 0x0257 }
            int r5 = r5 + r13
            r4.bp = r5     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r6 = r6.len     // Catch:{ all -> 0x0257 }
            if (r5 < r6) goto L_0x00ed
            r9 = 26
            goto L_0x00f5
        L_0x00ed:
            com.alibaba.fastjson.parser.JSONLexer r6 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.String r6 = r6.text     // Catch:{ all -> 0x0257 }
            char r9 = r6.charAt(r5)     // Catch:{ all -> 0x0257 }
        L_0x00f5:
            r4.ch = r9     // Catch:{ all -> 0x0257 }
            r0.add(r15)     // Catch:{ all -> 0x0257 }
            int r4 = r1.resolveStatus     // Catch:{ all -> 0x0257 }
            if (r4 != r13) goto L_0x0101
            r16.checkListResolve(r17)     // Catch:{ all -> 0x0257 }
        L_0x0101:
            com.alibaba.fastjson.parser.JSONLexer r0 = r1.lexer     // Catch:{ all -> 0x0257 }
            r0.nextToken(r12)     // Catch:{ all -> 0x0257 }
            if (r2 != 0) goto L_0x010a
            r1.contex = r3
        L_0x010a:
            return
        L_0x010b:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken()     // Catch:{ all -> 0x0257 }
        L_0x0110:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.token     // Catch:{ all -> 0x0257 }
        L_0x0114:
            if (r5 != r12) goto L_0x0120
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken()     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.token     // Catch:{ all -> 0x0257 }
            goto L_0x0114
        L_0x0120:
            r8 = 0
            switch(r5) {
                case 2: goto L_0x01d8;
                case 3: goto L_0x01b8;
                case 4: goto L_0x018d;
                case 6: goto L_0x0185;
                case 7: goto L_0x017d;
                case 8: goto L_0x0177;
                case 12: goto L_0x0152;
                case 14: goto L_0x0144;
                case 15: goto L_0x013a;
                case 20: goto L_0x0131;
                case 23: goto L_0x012a;
                default: goto L_0x0124;
            }     // Catch:{ all -> 0x0257 }
        L_0x0124:
            java.lang.Object r8 = r16.parse()     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x012a:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r7)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x0131:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0257 }
            java.lang.String r4 = "unclosed jsonArray"
            r0.<init>(r4)     // Catch:{ all -> 0x0257 }
            throw r0     // Catch:{ all -> 0x0257 }
        L_0x013a:
            com.alibaba.fastjson.parser.JSONLexer r0 = r1.lexer     // Catch:{ all -> 0x0257 }
            r0.nextToken(r12)     // Catch:{ all -> 0x0257 }
            if (r2 != 0) goto L_0x0143
            r1.contex = r3
        L_0x0143:
            return
        L_0x0144:
            com.alibaba.fastjson.JSONArray r8 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x0257 }
            r8.<init>()     // Catch:{ all -> 0x0257 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x0257 }
            r1.parseArray((java.util.Collection) r8, (java.lang.Object) r5)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x0152:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.features     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x0257 }
            int r8 = r8.mask     // Catch:{ all -> 0x0257 }
            r5 = r5 & r8
            if (r5 == 0) goto L_0x0168
            com.alibaba.fastjson.JSONObject r5 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0257 }
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap     // Catch:{ all -> 0x0257 }
            r8.<init>()     // Catch:{ all -> 0x0257 }
            r5.<init>((java.util.Map<java.lang.String, java.lang.Object>) r8)     // Catch:{ all -> 0x0257 }
            goto L_0x016d
        L_0x0168:
            com.alibaba.fastjson.JSONObject r5 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0257 }
            r5.<init>()     // Catch:{ all -> 0x0257 }
        L_0x016d:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x0257 }
            java.lang.Object r8 = r1.parseObject((java.util.Map) r5, (java.lang.Object) r8)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x0177:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r7)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x017d:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r12)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x0185:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r12)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x018d:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.String r8 = r5.stringVal()     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r12)     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.features     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.Feature r9 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x0257 }
            int r9 = r9.mask     // Catch:{ all -> 0x0257 }
            r5 = r5 & r9
            if (r5 == 0) goto L_0x01e3
            com.alibaba.fastjson.parser.JSONLexer r5 = new com.alibaba.fastjson.parser.JSONLexer     // Catch:{ all -> 0x0257 }
            r5.<init>(r8)     // Catch:{ all -> 0x0257 }
            boolean r9 = r5.scanISO8601DateIfMatch(r13)     // Catch:{ all -> 0x0257 }
            if (r9 == 0) goto L_0x01b4
            java.util.Calendar r8 = r5.calendar     // Catch:{ all -> 0x0257 }
            java.util.Date r8 = r8.getTime()     // Catch:{ all -> 0x0257 }
        L_0x01b4:
            r5.close()     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x01b8:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.features     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.UseBigDecimal     // Catch:{ all -> 0x0257 }
            int r8 = r8.mask     // Catch:{ all -> 0x0257 }
            r5 = r5 & r8
            if (r5 == 0) goto L_0x01cb
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.Number r5 = r5.decimalValue(r13)     // Catch:{ all -> 0x0257 }
        L_0x01c9:
            r8 = r5
            goto L_0x01d2
        L_0x01cb:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.Number r5 = r5.decimalValue(r10)     // Catch:{ all -> 0x0257 }
            goto L_0x01c9
        L_0x01d2:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r12)     // Catch:{ all -> 0x0257 }
            goto L_0x01e3
        L_0x01d8:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.Number r8 = r5.integerValue()     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken(r12)     // Catch:{ all -> 0x0257 }
        L_0x01e3:
            r0.add(r8)     // Catch:{ all -> 0x0257 }
            int r5 = r1.resolveStatus     // Catch:{ all -> 0x0257 }
            if (r5 != r13) goto L_0x01ed
            r16.checkListResolve(r17)     // Catch:{ all -> 0x0257 }
        L_0x01ed:
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r5 = r5.token     // Catch:{ all -> 0x0257 }
            if (r5 != r12) goto L_0x00cc
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            char r5 = r5.ch     // Catch:{ all -> 0x0257 }
            if (r5 != r11) goto L_0x0208
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r8 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r8 = r8.bp     // Catch:{ all -> 0x0257 }
            r5.pos = r8     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.scanString()     // Catch:{ all -> 0x0257 }
            goto L_0x00cc
        L_0x0208:
            r8 = 48
            if (r5 < r8) goto L_0x021f
            r8 = 57
            if (r5 > r8) goto L_0x021f
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r8 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r8 = r8.bp     // Catch:{ all -> 0x0257 }
            r5.pos = r8     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.scanNumber()     // Catch:{ all -> 0x0257 }
            goto L_0x00cc
        L_0x021f:
            r8 = 123(0x7b, float:1.72E-43)
            if (r5 != r8) goto L_0x0246
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r9 = 12
            r5.token = r9     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r15 = r5.bp     // Catch:{ all -> 0x0257 }
            int r15 = r15 + r13
            r5.bp = r15     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r1.lexer     // Catch:{ all -> 0x0257 }
            int r6 = r6.len     // Catch:{ all -> 0x0257 }
            if (r15 < r6) goto L_0x023b
            r6 = 26
            goto L_0x0243
        L_0x023b:
            com.alibaba.fastjson.parser.JSONLexer r6 = r1.lexer     // Catch:{ all -> 0x0257 }
            java.lang.String r6 = r6.text     // Catch:{ all -> 0x0257 }
            char r6 = r6.charAt(r15)     // Catch:{ all -> 0x0257 }
        L_0x0243:
            r5.ch = r6     // Catch:{ all -> 0x0257 }
            goto L_0x024d
        L_0x0246:
            r9 = 12
            com.alibaba.fastjson.parser.JSONLexer r5 = r1.lexer     // Catch:{ all -> 0x0257 }
            r5.nextToken()     // Catch:{ all -> 0x0257 }
        L_0x024d:
            int r14 = r14 + 1
            r5 = 123(0x7b, float:1.72E-43)
            r6 = 93
            r8 = 12
            goto L_0x008e
        L_0x0257:
            r0 = move-exception
            if (r2 != 0) goto L_0x025c
            r1.contex = r3
        L_0x025c:
            throw r0
        L_0x025d:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "syntax error, expect [, actual "
            r3.append(r4)
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)
            r3.append(r2)
            java.lang.String r2 = ", pos "
            r3.append(r2)
            com.alibaba.fastjson.parser.JSONLexer r2 = r1.lexer
            int r2 = r2.pos
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    /* access modifiers changed from: protected */
    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public void setContext(ParseContext parseContext) {
        if (!this.lexer.disableCircularReferenceDetect) {
            this.contex = parseContext;
        }
    }

    /* access modifiers changed from: protected */
    public void popContext() {
        this.contex = this.contex.parent;
        this.contextArray[this.contextArrayIndex - 1] = null;
        this.contextArrayIndex--;
    }

    /* access modifiers changed from: protected */
    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.disableCircularReferenceDetect) {
            return null;
        }
        this.contex = new ParseContext(parseContext, obj, obj2);
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            ParseContext[] parseContextArr = new ParseContext[((this.contextArray.length * 3) / 2)];
            System.arraycopy(this.contextArray, 0, parseContextArr, 0, this.contextArray.length);
            this.contextArray = parseContextArr;
        }
        this.contextArray[i] = this.contex;
        return this.contex;
    }

    public Object parse() {
        return parse((Object) null);
    }

    public Object parse(Object obj) {
        boolean z = true;
        switch (this.lexer.token) {
            case 2:
                Number integerValue = this.lexer.integerValue();
                this.lexer.nextToken();
                return integerValue;
            case 3:
                if ((this.lexer.features & Feature.UseBigDecimal.mask) == 0) {
                    z = false;
                }
                Number decimalValue = this.lexer.decimalValue(z);
                this.lexer.nextToken();
                return decimalValue;
            case 4:
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken(16);
                if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                    JSONLexer jSONLexer = new JSONLexer(stringVal);
                    try {
                        if (jSONLexer.scanISO8601DateIfMatch(true)) {
                            return jSONLexer.calendar.getTime();
                        }
                        jSONLexer.close();
                    } finally {
                        jSONLexer.close();
                    }
                }
                return stringVal;
            case 6:
                this.lexer.nextToken(16);
                return Boolean.TRUE;
            case 7:
                this.lexer.nextToken(16);
                return Boolean.FALSE;
            case 8:
            case 23:
                this.lexer.nextToken();
                return null;
            case 9:
                this.lexer.nextToken(18);
                if (this.lexer.token == 18) {
                    this.lexer.nextToken(10);
                    accept(10);
                    long longValue = this.lexer.integerValue().longValue();
                    accept(2);
                    accept(11);
                    return new Date(longValue);
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            case 12:
                return parseObject((Map) (this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject((Map<String, Object>) new LinkedHashMap()) : new JSONObject(), obj);
            case 14:
                JSONArray jSONArray = new JSONArray();
                parseArray((Collection) jSONArray, obj);
                return jSONArray;
            case 20:
                if (this.lexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            case 21:
                this.lexer.nextToken();
                HashSet hashSet = new HashSet();
                parseArray((Collection) hashSet, obj);
                return hashSet;
            case 22:
                this.lexer.nextToken();
                TreeSet treeSet = new TreeSet();
                parseArray((Collection) treeSet, obj);
                return treeSet;
            default:
                throw new JSONException("syntax error, " + this.lexer.info());
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public final void accept(int i) {
        if (this.lexer.token == i) {
            this.lexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token));
    }

    public void close() {
        try {
            if (this.lexer.token != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(this.lexer.token));
            }
        } finally {
            this.lexer.close();
        }
    }

    public void handleResovleTask(Object obj) {
        if (this.resolveTaskList != null) {
            int size = this.resolveTaskList.size();
            for (int i = 0; i < size; i++) {
                ResolveTask resolveTask = this.resolveTaskList.get(i);
                FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
                if (fieldDeserializer != null) {
                    Object obj2 = null;
                    Object obj3 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
                    String access$000 = resolveTask.referenceValue;
                    if (access$000.startsWith(Operators.DOLLAR_STR)) {
                        for (int i2 = 0; i2 < this.contextArrayIndex; i2++) {
                            if (access$000.equals(this.contextArray[i2].toString())) {
                                obj2 = this.contextArray[i2].object;
                            }
                        }
                    } else {
                        obj2 = resolveTask.context.object;
                    }
                    fieldDeserializer.setValue(obj3, obj2);
                }
            }
        }
    }

    public String parseString() {
        int i = this.lexer.token;
        if (i == 4) {
            String stringVal = this.lexer.stringVal();
            char c = this.lexer.ch;
            char c2 = JSONLexer.EOI;
            if (c == ',') {
                JSONLexer jSONLexer = this.lexer;
                int i2 = jSONLexer.bp + 1;
                jSONLexer.bp = i2;
                JSONLexer jSONLexer2 = this.lexer;
                if (i2 < this.lexer.len) {
                    c2 = this.lexer.text.charAt(i2);
                }
                jSONLexer2.ch = c2;
                this.lexer.token = 16;
            } else if (this.lexer.ch == ']') {
                JSONLexer jSONLexer3 = this.lexer;
                int i3 = jSONLexer3.bp + 1;
                jSONLexer3.bp = i3;
                JSONLexer jSONLexer4 = this.lexer;
                if (i3 < this.lexer.len) {
                    c2 = this.lexer.text.charAt(i3);
                }
                jSONLexer4.ch = c2;
                this.lexer.token = 15;
            } else if (this.lexer.ch == '}') {
                JSONLexer jSONLexer5 = this.lexer;
                int i4 = jSONLexer5.bp + 1;
                jSONLexer5.bp = i4;
                JSONLexer jSONLexer6 = this.lexer;
                if (i4 < this.lexer.len) {
                    c2 = this.lexer.text.charAt(i4);
                }
                jSONLexer6.ch = c2;
                this.lexer.token = 13;
            } else {
                this.lexer.nextToken();
            }
            return stringVal;
        } else if (i == 2) {
            String numberString = this.lexer.numberString();
            this.lexer.nextToken(16);
            return numberString;
        } else {
            Object parse = parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
    }

    public static class ResolveTask {
        /* access modifiers changed from: private */
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        /* access modifiers changed from: private */
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }
}
