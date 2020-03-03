package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] smartMatchHashArray;
    private transient int[] smartMatchHashArrayMapping;
    private final FieldDeserializer[] sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, cls, type, JavaBeanInfo.build(cls, cls.getModifiers(), type, false, true, true, true, parserConfig.propertyNamingStrategy));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type, JavaBeanInfo javaBeanInfo) {
        this.clazz = cls;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap hashMap = null;
        int i = 0;
        while (i < length) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, cls, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            HashMap hashMap2 = hashMap;
            for (String str : fieldInfo.alternateNames) {
                if (hashMap2 == null) {
                    hashMap2 = new HashMap();
                }
                hashMap2.put(str, createFieldDeserializer);
            }
            i++;
            hashMap = hashMap2;
        }
        this.alterNameFieldDeserializers = hashMap;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    /* access modifiers changed from: protected */
    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object obj;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject((defaultJSONParser.lexer.features & Feature.OrderedField.mask) != 0));
        } else if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        } else {
            if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
                return null;
            }
            try {
                Constructor<?> constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize != 0) {
                    obj = constructor.newInstance(new Object[]{defaultJSONParser.contex.object});
                } else if (constructor != null) {
                    obj = constructor.newInstance(new Object[0]);
                } else {
                    obj = this.beanInfo.factoryMethod.invoke((Object) null, new Object[0]);
                }
                if (!(defaultJSONParser == null || (defaultJSONParser.lexer.features & Feature.InitStringFieldAsEmpty.mask) == 0)) {
                    for (FieldInfo fieldInfo : this.beanInfo.fields) {
                        if (fieldInfo.fieldClass == String.class) {
                            fieldInfo.set(obj, "");
                        }
                    }
                }
                return obj;
            } catch (Exception e) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e);
            }
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, (Object) null);
    }

    private <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        Enum enumR;
        char c6;
        char c7;
        char c8;
        char c9;
        char c10;
        char c11;
        String str;
        char c12;
        char c13;
        char c14;
        char c15;
        char c16;
        DefaultJSONParser defaultJSONParser2 = defaultJSONParser;
        JSONLexer jSONLexer = defaultJSONParser2.lexer;
        T createInstance = createInstance(defaultJSONParser, type);
        int length = this.sortedFieldDeserializers.length;
        int i = 0;
        while (i < length) {
            char c17 = i == length + -1 ? Operators.ARRAY_END : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
            Class<?> cls = fieldInfo.fieldClass;
            try {
                if (cls == Integer.TYPE) {
                    int scanLongValue = (int) jSONLexer.scanLongValue();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setInt(createInstance, scanLongValue);
                    } else {
                        fieldDeserializer.setValue((Object) createInstance, (Object) new Integer(scanLongValue));
                    }
                    if (jSONLexer.ch == ',') {
                        int i2 = jSONLexer.bp + 1;
                        jSONLexer.bp = i2;
                        if (i2 >= jSONLexer.len) {
                            c16 = JSONLexer.EOI;
                        } else {
                            c16 = jSONLexer.text.charAt(i2);
                        }
                        jSONLexer.ch = c16;
                        jSONLexer.token = 16;
                    } else if (jSONLexer.ch == ']') {
                        int i3 = jSONLexer.bp + 1;
                        jSONLexer.bp = i3;
                        if (i3 >= jSONLexer.len) {
                            c15 = JSONLexer.EOI;
                        } else {
                            c15 = jSONLexer.text.charAt(i3);
                        }
                        jSONLexer.ch = c15;
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls == String.class) {
                    if (jSONLexer.ch == '\"') {
                        str = jSONLexer.scanStringValue('\"');
                    } else if (jSONLexer.ch != 'n' || !jSONLexer.text.startsWith("null", jSONLexer.bp)) {
                        throw new JSONException("not match string. feild : " + obj);
                    } else {
                        jSONLexer.bp += 4;
                        int i4 = jSONLexer.bp;
                        if (jSONLexer.bp >= jSONLexer.len) {
                            c14 = JSONLexer.EOI;
                        } else {
                            c14 = jSONLexer.text.charAt(i4);
                        }
                        jSONLexer.ch = c14;
                        str = null;
                    }
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.set(createInstance, str);
                    } else {
                        fieldDeserializer.setValue((Object) createInstance, (Object) str);
                    }
                    if (jSONLexer.ch == ',') {
                        int i5 = jSONLexer.bp + 1;
                        jSONLexer.bp = i5;
                        if (i5 >= jSONLexer.len) {
                            c13 = JSONLexer.EOI;
                        } else {
                            c13 = jSONLexer.text.charAt(i5);
                        }
                        jSONLexer.ch = c13;
                        jSONLexer.token = 16;
                    } else if (jSONLexer.ch == ']') {
                        int i6 = jSONLexer.bp + 1;
                        jSONLexer.bp = i6;
                        if (i6 >= jSONLexer.len) {
                            c12 = JSONLexer.EOI;
                        } else {
                            c12 = jSONLexer.text.charAt(i6);
                        }
                        jSONLexer.ch = c12;
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else {
                    Object obj3 = obj;
                    if (cls == Long.TYPE) {
                        long scanLongValue2 = jSONLexer.scanLongValue();
                        if (fieldInfo.fieldAccess) {
                            fieldInfo.field.setLong(createInstance, scanLongValue2);
                        } else {
                            fieldDeserializer.setValue((Object) createInstance, (Object) new Long(scanLongValue2));
                        }
                        if (jSONLexer.ch == ',') {
                            int i7 = jSONLexer.bp + 1;
                            jSONLexer.bp = i7;
                            if (i7 >= jSONLexer.len) {
                                c11 = JSONLexer.EOI;
                            } else {
                                c11 = jSONLexer.text.charAt(i7);
                            }
                            jSONLexer.ch = c11;
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i8 = jSONLexer.bp + 1;
                            jSONLexer.bp = i8;
                            if (i8 >= jSONLexer.len) {
                                c10 = JSONLexer.EOI;
                            } else {
                                c10 = jSONLexer.text.charAt(i8);
                            }
                            jSONLexer.ch = c10;
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else if (cls == Boolean.TYPE) {
                        boolean scanBoolean = jSONLexer.scanBoolean();
                        if (fieldInfo.fieldAccess) {
                            fieldInfo.field.setBoolean(createInstance, scanBoolean);
                        } else {
                            fieldDeserializer.setValue((Object) createInstance, (Object) Boolean.valueOf(scanBoolean));
                        }
                        if (jSONLexer.ch == ',') {
                            int i9 = jSONLexer.bp + 1;
                            jSONLexer.bp = i9;
                            if (i9 >= jSONLexer.len) {
                                c9 = JSONLexer.EOI;
                            } else {
                                c9 = jSONLexer.text.charAt(i9);
                            }
                            jSONLexer.ch = c9;
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i10 = jSONLexer.bp + 1;
                            jSONLexer.bp = i10;
                            if (i10 >= jSONLexer.len) {
                                c8 = JSONLexer.EOI;
                            } else {
                                c8 = jSONLexer.text.charAt(i10);
                            }
                            jSONLexer.ch = c8;
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else if (cls.isEnum()) {
                        char c18 = jSONLexer.ch;
                        if (c18 == '\"') {
                            String scanSymbol = jSONLexer.scanSymbol(defaultJSONParser2.symbolTable);
                            if (scanSymbol == null) {
                                enumR = null;
                            } else {
                                enumR = Enum.valueOf(cls, scanSymbol);
                            }
                        } else if (c18 < '0' || c18 > '9') {
                            throw new JSONException("illegal enum." + jSONLexer.info());
                        } else {
                            enumR = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser2.config)).ordinalEnums[(int) jSONLexer.scanLongValue()];
                        }
                        fieldDeserializer.setValue((Object) createInstance, (Object) enumR);
                        if (jSONLexer.ch == ',') {
                            int i11 = jSONLexer.bp + 1;
                            jSONLexer.bp = i11;
                            if (i11 >= jSONLexer.len) {
                                c7 = JSONLexer.EOI;
                            } else {
                                c7 = jSONLexer.text.charAt(i11);
                            }
                            jSONLexer.ch = c7;
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i12 = jSONLexer.bp + 1;
                            jSONLexer.bp = i12;
                            if (i12 >= jSONLexer.len) {
                                c6 = JSONLexer.EOI;
                            } else {
                                c6 = jSONLexer.text.charAt(i12);
                            }
                            jSONLexer.ch = c6;
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else if (cls == Date.class && jSONLexer.ch == '1') {
                        fieldDeserializer.setValue((Object) createInstance, (Object) new Date(jSONLexer.scanLongValue()));
                        if (jSONLexer.ch == ',') {
                            int i13 = jSONLexer.bp + 1;
                            jSONLexer.bp = i13;
                            if (i13 >= jSONLexer.len) {
                                c5 = JSONLexer.EOI;
                            } else {
                                c5 = jSONLexer.text.charAt(i13);
                            }
                            jSONLexer.ch = c5;
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i14 = jSONLexer.bp + 1;
                            jSONLexer.bp = i14;
                            if (i14 >= jSONLexer.len) {
                                c4 = JSONLexer.EOI;
                            } else {
                                c4 = jSONLexer.text.charAt(i14);
                            }
                            jSONLexer.ch = c4;
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else {
                        if (jSONLexer.ch == '[') {
                            int i15 = jSONLexer.bp + 1;
                            jSONLexer.bp = i15;
                            if (i15 >= jSONLexer.len) {
                                c3 = JSONLexer.EOI;
                            } else {
                                c3 = jSONLexer.text.charAt(i15);
                            }
                            jSONLexer.ch = c3;
                            jSONLexer.token = 14;
                        } else if (jSONLexer.ch == '{') {
                            int i16 = jSONLexer.bp + 1;
                            jSONLexer.bp = i16;
                            if (i16 >= jSONLexer.len) {
                                c2 = JSONLexer.EOI;
                            } else {
                                c2 = jSONLexer.text.charAt(i16);
                            }
                            jSONLexer.ch = c2;
                            jSONLexer.token = 12;
                        } else {
                            jSONLexer.nextToken();
                        }
                        fieldDeserializer.parseField(defaultJSONParser2, createInstance, fieldInfo.fieldType, (Map<String, Object>) null);
                        if (c17 == ']') {
                            if (jSONLexer.token != 15) {
                                throw new JSONException("syntax error");
                            }
                        } else if (c17 != ',') {
                            continue;
                        } else if (jSONLexer.token != 16) {
                            throw new JSONException("syntax error");
                        }
                    }
                    i++;
                }
                Object obj4 = obj;
                i++;
            } catch (IllegalAccessException e) {
                throw new JSONException("set " + fieldInfo.name + "error", e);
            }
        }
        if (jSONLexer.ch == ',') {
            int i17 = jSONLexer.bp + 1;
            jSONLexer.bp = i17;
            if (i17 >= jSONLexer.len) {
                c = JSONLexer.EOI;
            } else {
                c = jSONLexer.text.charAt(i17);
            }
            jSONLexer.ch = c;
            jSONLexer.token = 16;
        } else {
            jSONLexer.nextToken();
        }
        return createInstance;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: T} */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x02d6, code lost:
        if (r1 == 16) goto L_0x02d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:?, code lost:
        r10.nextTokenWithChar(com.taobao.weex.el.parse.Operators.CONDITION_IF_MIDDLE);
        r0 = r10.token;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x02ee, code lost:
        if (r0 != 4) goto L_0x0371;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x02f0, code lost:
        r0 = r10.stringVal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x02fa, code lost:
        if ("@".equals(r0) == false) goto L_0x0300;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:?, code lost:
        r0 = r14.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x02fe, code lost:
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0306, code lost:
        if ("..".equals(r0) == false) goto L_0x031e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:?, code lost:
        r1 = r14.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x030c, code lost:
        if (r1.object == null) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x030e, code lost:
        r0 = r1.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0311, code lost:
        r8.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r1, r0));
        r8.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x031c, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0324, code lost:
        if (com.taobao.weex.el.parse.Operators.DOLLAR_STR.equals(r0) == false) goto L_0x0341;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x0326, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0329, code lost:
        if (r1.parent == null) goto L_0x032e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x032b, code lost:
        r1 = r1.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0330, code lost:
        if (r1.object == null) goto L_0x0335;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0332, code lost:
        r0 = r1.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0335, code lost:
        r8.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r1, r0));
        r8.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:?, code lost:
        r8.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r14, r0));
        r8.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x034c, code lost:
        r10.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0353, code lost:
        if (r10.token != 13) goto L_0x0367;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x0355, code lost:
        r10.nextToken(16);
        r8.setContext(r14, r6, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x035d, code lost:
        r1 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x035f, code lost:
        if (r1 == null) goto L_0x0363;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0361, code lost:
        r1.object = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x0363, code lost:
        r8.setContext(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x0366, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0367, code lost:
        r1 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x0370, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x0371, code lost:
        r1 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x038d, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x039c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x03cf, code lost:
        r12 = r1;
        r13 = r6;
        r1 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x03e9, code lost:
        if (r4 != null) goto L_0x0414;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x03eb, code lost:
        r12 = r8.config.checkAutoType(r2, r7.clazz, r10.features);
        r0 = com.alibaba.fastjson.util.TypeUtils.getClass(r39);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x03f9, code lost:
        if (r0 == null) goto L_0x040d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x03fb, code lost:
        if (r12 == null) goto L_0x0404;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x0401, code lost:
        if (r0.isAssignableFrom(r12) == false) goto L_0x0404;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x040c, code lost:
        throw new com.alibaba.fastjson.JSONException("type not match");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:321:0x040d, code lost:
        r4 = r8.config.getDeserializer(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x0414, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x0417, code lost:
        if ((r4 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L_0x042c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x0419, code lost:
        r4 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r4;
        r0 = r4.deserialze(r8, r12, r9, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x0420, code lost:
        if (r3 == null) goto L_0x0430;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x0422, code lost:
        r3 = r4.getFieldDeserializer(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0426, code lost:
        if (r3 == null) goto L_0x0430;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x0428, code lost:
        r3.setValue((java.lang.Object) r0, (java.lang.Object) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x042c, code lost:
        r0 = r4.deserialze(r8, r12, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x0430, code lost:
        if (r1 == null) goto L_0x0434;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x0432, code lost:
        r1.object = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x0434, code lost:
        r8.setContext(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0437, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:487:0x0607, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:488:0x0608, code lost:
        r6 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:518:0x067b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:521:0x069a, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r7.beanInfo.creatorConstructor.toGenericString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:527:0x06ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:530:0x06cb, code lost:
        throw new com.alibaba.fastjson.JSONException("create factory method error, " + r7.beanInfo.factoryMethod.toString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:544:0x070a, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r10.token));
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:475:0x05ef, B:505:0x0643, B:524:0x06a1] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x02b9 A[SYNTHETIC, Splitter:B:226:0x02b9] */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x03ac A[Catch:{ all -> 0x039c }] */
    /* JADX WARNING: Removed duplicated region for block: B:335:0x0438 A[SYNTHETIC, Splitter:B:335:0x0438] */
    /* JADX WARNING: Removed duplicated region for block: B:341:0x0447  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x0450 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005f A[Catch:{ all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x05a4 A[Catch:{ IllegalAccessException -> 0x0573, all -> 0x0714 }] */
    /* JADX WARNING: Removed duplicated region for block: B:468:0x05dd A[Catch:{ IllegalAccessException -> 0x0573, all -> 0x0714 }] */
    /* JADX WARNING: Removed duplicated region for block: B:469:0x05de A[Catch:{ IllegalAccessException -> 0x0573, all -> 0x0714 }] */
    /* JADX WARNING: Removed duplicated region for block: B:552:0x071e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r38, java.lang.reflect.Type r39, java.lang.Object r40, java.lang.Object r41) {
        /*
            r37 = this;
            r7 = r37
            r8 = r38
            r0 = r39
            r9 = r40
            java.lang.Class<com.alibaba.fastjson.JSON> r2 = com.alibaba.fastjson.JSON.class
            if (r0 == r2) goto L_0x0724
            java.lang.Class<com.alibaba.fastjson.JSONObject> r2 = com.alibaba.fastjson.JSONObject.class
            if (r0 != r2) goto L_0x0012
            goto L_0x0724
        L_0x0012:
            com.alibaba.fastjson.parser.JSONLexer r10 = r8.lexer
            int r2 = r10.token
            r3 = 8
            r11 = 16
            r12 = 0
            if (r2 != r3) goto L_0x0021
            r10.nextToken(r11)
            return r12
        L_0x0021:
            boolean r13 = r10.disableCircularReferenceDetect
            com.alibaba.fastjson.parser.ParseContext r3 = r8.contex
            if (r41 == 0) goto L_0x002b
            if (r3 == 0) goto L_0x002b
            com.alibaba.fastjson.parser.ParseContext r3 = r3.parent
        L_0x002b:
            r14 = r3
            r15 = 13
            if (r2 != r15) goto L_0x0045
            r10.nextToken(r11)     // Catch:{ all -> 0x0040 }
            if (r41 != 0) goto L_0x003a
            java.lang.Object r0 = r37.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r38, (java.lang.reflect.Type) r39)     // Catch:{ all -> 0x0040 }
            goto L_0x003c
        L_0x003a:
            r0 = r41
        L_0x003c:
            r8.setContext(r14)
            return r0
        L_0x0040:
            r0 = move-exception
        L_0x0041:
            r6 = r41
            goto L_0x071c
        L_0x0045:
            r3 = 14
            r6 = 0
            if (r2 != r3) goto L_0x0067
            com.alibaba.fastjson.parser.JavaBeanInfo r3 = r7.beanInfo     // Catch:{ all -> 0x0040 }
            boolean r3 = r3.supportBeanToArray     // Catch:{ all -> 0x0040 }
            if (r3 != 0) goto L_0x005c
            int r3 = r10.features     // Catch:{ all -> 0x0040 }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0040 }
            int r4 = r4.mask     // Catch:{ all -> 0x0040 }
            r3 = r3 & r4
            if (r3 == 0) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            r3 = 0
            goto L_0x005d
        L_0x005c:
            r3 = 1
        L_0x005d:
            if (r3 == 0) goto L_0x0067
            java.lang.Object r0 = r37.deserialzeArrayMapping(r38, r39, r40, r41)     // Catch:{ all -> 0x0040 }
            r8.setContext(r14)
            return r0
        L_0x0067:
            r3 = 12
            r4 = 4
            if (r2 == r3) goto L_0x00b3
            if (r2 == r11) goto L_0x00b3
            boolean r0 = r10.isBlankInput()     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0078
            r8.setContext(r14)
            return r12
        L_0x0078:
            if (r2 != r4) goto L_0x008b
            java.lang.String r0 = r10.stringVal()     // Catch:{ all -> 0x0040 }
            int r0 = r0.length()     // Catch:{ all -> 0x0040 }
            if (r0 != 0) goto L_0x008b
            r10.nextToken()     // Catch:{ all -> 0x0040 }
            r8.setContext(r14)
            return r12
        L_0x008b:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ all -> 0x0040 }
            r0.<init>()     // Catch:{ all -> 0x0040 }
            java.lang.String r2 = "syntax error, expect {, actual "
            r0.append(r2)     // Catch:{ all -> 0x0040 }
            java.lang.String r2 = r10.info()     // Catch:{ all -> 0x0040 }
            r0.append(r2)     // Catch:{ all -> 0x0040 }
            boolean r2 = r9 instanceof java.lang.String     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x00a9
            java.lang.String r2 = ", fieldName "
            r0.append(r2)     // Catch:{ all -> 0x0040 }
            r0.append(r9)     // Catch:{ all -> 0x0040 }
        L_0x00a9:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0040 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0040 }
            r2.<init>(r0)     // Catch:{ all -> 0x0040 }
            throw r2     // Catch:{ all -> 0x0040 }
        L_0x00b3:
            int r2 = r8.resolveStatus     // Catch:{ all -> 0x0718 }
            r3 = 2
            if (r2 != r3) goto L_0x00ba
            r8.resolveStatus = r6     // Catch:{ all -> 0x0040 }
        L_0x00ba:
            com.alibaba.fastjson.parser.JavaBeanInfo r2 = r7.beanInfo     // Catch:{ all -> 0x0718 }
            java.lang.String r3 = r2.typeKey     // Catch:{ all -> 0x0718 }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r2 = r7.sortedFieldDeserializers     // Catch:{ all -> 0x0718 }
            int r2 = r2.length     // Catch:{ all -> 0x0718 }
            r16 = 0
            r6 = r41
            r20 = r12
            r21 = r20
            r18 = r13
            r12 = r16
            r1 = 0
        L_0x00ce:
            int r22 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r22 == 0) goto L_0x00e7
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r12 = r7.getFieldDeserializerByHash(r12)     // Catch:{ all -> 0x00e2 }
            if (r12 == 0) goto L_0x00dd
            com.alibaba.fastjson.util.FieldInfo r13 = r12.fieldInfo     // Catch:{ all -> 0x00e2 }
            java.lang.Class<?> r5 = r13.fieldClass     // Catch:{ all -> 0x00e2 }
            goto L_0x00df
        L_0x00dd:
            r5 = 0
            r13 = 0
        L_0x00df:
            r23 = r16
            goto L_0x00ec
        L_0x00e2:
            r0 = move-exception
        L_0x00e3:
            r12 = r20
            goto L_0x071c
        L_0x00e7:
            r23 = r12
            r5 = 0
            r12 = 0
            r13 = 0
        L_0x00ec:
            if (r12 != 0) goto L_0x00fd
            if (r1 >= r2) goto L_0x00fb
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r5 = r7.sortedFieldDeserializers     // Catch:{ all -> 0x00e2 }
            r12 = r5[r1]     // Catch:{ all -> 0x00e2 }
            com.alibaba.fastjson.util.FieldInfo r13 = r12.fieldInfo     // Catch:{ all -> 0x00e2 }
            java.lang.Class<?> r5 = r13.fieldClass     // Catch:{ all -> 0x00e2 }
            int r1 = r1 + 1
            goto L_0x00fd
        L_0x00fb:
            int r1 = r1 + 1
        L_0x00fd:
            r22 = r1
            r25 = 0
            if (r12 == 0) goto L_0x02ab
            r27 = r2
            long r1 = r13.nameHashCode     // Catch:{ all -> 0x00e2 }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00e2 }
            r11 = -2
            if (r5 == r4) goto L_0x0282
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            if (r5 != r4) goto L_0x0112
            goto L_0x0282
        L_0x0112:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x00e2 }
            if (r5 == r4) goto L_0x0268
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            if (r5 != r4) goto L_0x011c
            goto L_0x0268
        L_0x011c:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r5 != r4) goto L_0x013c
            java.lang.String r1 = r10.scanFieldString(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x012e
        L_0x0128:
            r28 = r16
            r31 = r25
            goto L_0x0275
        L_0x012e:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x0136:
            r28 = r16
            r31 = r25
            goto L_0x02b2
        L_0x013c:
            java.lang.Class<java.util.Date> r4 = java.util.Date.class
            if (r5 != r4) goto L_0x0151
            java.util.Date r1 = r10.scanFieldDate(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x0149
            goto L_0x0128
        L_0x0149:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x0151:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x00e2 }
            if (r5 == r4) goto L_0x0253
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            if (r5 != r4) goto L_0x015b
            goto L_0x0253
        L_0x015b:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x00e2 }
            if (r5 == r4) goto L_0x022e
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            if (r5 != r4) goto L_0x0165
            goto L_0x022e
        L_0x0165:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x00e2 }
            if (r5 == r4) goto L_0x021b
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            if (r5 != r4) goto L_0x016f
            goto L_0x021b
        L_0x016f:
            boolean r4 = r13.isEnum     // Catch:{ all -> 0x00e2 }
            if (r4 == 0) goto L_0x019d
            com.alibaba.fastjson.parser.ParserConfig r4 = r8.config     // Catch:{ all -> 0x00e2 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r4.getDeserializer(r5)     // Catch:{ all -> 0x00e2 }
            boolean r4 = r4 instanceof com.alibaba.fastjson.parser.EnumDeserializer     // Catch:{ all -> 0x00e2 }
            if (r4 == 0) goto L_0x019d
            long r1 = r10.scanFieldSymbol(r1)     // Catch:{ all -> 0x00e2 }
            int r4 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r4 <= 0) goto L_0x018c
            java.lang.Enum r1 = r12.getEnumByHashCode(r1)     // Catch:{ all -> 0x00e2 }
            r2 = 1
            r4 = 1
            goto L_0x0197
        L_0x018c:
            int r1 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r1 != r11) goto L_0x0194
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x0194:
            r1 = 0
            r2 = 0
            r4 = 0
        L_0x0197:
            r28 = r16
            r31 = r25
            goto L_0x02b4
        L_0x019d:
            java.lang.Class<int[]> r4 = int[].class
            if (r5 != r4) goto L_0x01b3
            int[] r1 = r10.scanFieldIntArray(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x01ab
            goto L_0x0128
        L_0x01ab:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x01b3:
            java.lang.Class<float[]> r4 = float[].class
            if (r5 != r4) goto L_0x01c9
            float[] r1 = r10.scanFieldFloatArray(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x01c1
            goto L_0x0128
        L_0x01c1:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x01c9:
            java.lang.Class<double[]> r4 = double[].class
            if (r5 != r4) goto L_0x01df
            double[] r1 = r10.scanFieldDoubleArray(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x01d7
            goto L_0x0128
        L_0x01d7:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x01df:
            java.lang.Class<float[][]> r4 = float[][].class
            if (r5 != r4) goto L_0x01f5
            float[][] r1 = r10.scanFieldFloatArray2(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x01ed
            goto L_0x0128
        L_0x01ed:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x01f5:
            java.lang.Class<double[][]> r4 = double[][].class
            if (r5 != r4) goto L_0x020b
            double[][] r1 = r10.scanFieldDoubleArray2(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x0203
            goto L_0x0128
        L_0x0203:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x020b:
            long r1 = r13.nameHashCode     // Catch:{ all -> 0x00e2 }
            boolean r1 = r10.matchField(r1)     // Catch:{ all -> 0x00e2 }
            if (r1 == 0) goto L_0x02d8
            r28 = r16
            r31 = r25
            r1 = 0
            r2 = 1
            goto L_0x02b3
        L_0x021b:
            double r25 = r10.scanFieldDouble(r1)     // Catch:{ all -> 0x00e2 }
            int r1 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r1 <= 0) goto L_0x0226
            r28 = r16
            goto L_0x0272
        L_0x0226:
            int r1 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r1 != r11) goto L_0x02ad
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x022e:
            float r1 = r10.scanFieldFloat(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x0242
            r30 = r1
            r28 = r16
            r31 = r25
            r1 = 0
            r2 = 1
            r4 = 1
        L_0x023f:
            r11 = 0
            goto L_0x02b7
        L_0x0242:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0249
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x0249:
            r30 = r1
            r28 = r16
            r31 = r25
            r1 = 0
            r2 = 0
            r4 = 0
            goto L_0x023f
        L_0x0253:
            boolean r1 = r10.scanFieldBoolean(r1)     // Catch:{ all -> 0x00e2 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x0261
            goto L_0x0128
        L_0x0261:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x0136
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x0268:
            long r1 = r10.scanFieldLong(r1)     // Catch:{ all -> 0x00e2 }
            int r4 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r4 <= 0) goto L_0x0278
            r28 = r1
        L_0x0272:
            r31 = r25
            r1 = 0
        L_0x0275:
            r2 = 1
            r4 = 1
            goto L_0x02b4
        L_0x0278:
            int r4 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r4 != r11) goto L_0x027f
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
            goto L_0x0299
        L_0x027f:
            r28 = r1
            goto L_0x02af
        L_0x0282:
            int r1 = r10.scanFieldInt(r1)     // Catch:{ all -> 0x00e2 }
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 <= 0) goto L_0x0293
            r11 = r1
            r28 = r16
            r31 = r25
            r1 = 0
            r2 = 1
            r4 = 1
            goto L_0x02b5
        L_0x0293:
            int r2 = r10.matchStat     // Catch:{ all -> 0x00e2 }
            if (r2 != r11) goto L_0x02a2
            long r12 = r10.fieldHash     // Catch:{ all -> 0x00e2 }
        L_0x0299:
            r1 = r22
            r2 = r27
            r4 = 4
            r11 = 16
            goto L_0x00ce
        L_0x02a2:
            r11 = r1
            r28 = r16
            r31 = r25
            r1 = 0
            r2 = 0
            r4 = 0
            goto L_0x02b5
        L_0x02ab:
            r27 = r2
        L_0x02ad:
            r28 = r16
        L_0x02af:
            r31 = r25
            r1 = 0
        L_0x02b2:
            r2 = 0
        L_0x02b3:
            r4 = 0
        L_0x02b4:
            r11 = 0
        L_0x02b5:
            r30 = 0
        L_0x02b7:
            if (r2 != 0) goto L_0x0447
            com.alibaba.fastjson.parser.SymbolTable r15 = r8.symbolTable     // Catch:{ all -> 0x0441 }
            java.lang.String r15 = r10.scanSymbol(r15)     // Catch:{ all -> 0x0441 }
            if (r15 != 0) goto L_0x02dc
            r33 = r1
            int r1 = r10.token     // Catch:{ all -> 0x00e2 }
            r34 = r13
            r13 = 13
            if (r1 != r13) goto L_0x02d4
            r13 = 16
            r10.nextToken(r13)     // Catch:{ all -> 0x00e2 }
            r1 = r20
            goto L_0x03cf
        L_0x02d4:
            r13 = 16
            if (r1 != r13) goto L_0x02e0
        L_0x02d8:
            r1 = r20
            goto L_0x03d7
        L_0x02dc:
            r33 = r1
            r34 = r13
        L_0x02e0:
            java.lang.String r1 = "$ref"
            r13 = 58
            if (r1 != r15) goto L_0x038e
            if (r14 == 0) goto L_0x038e
            r10.nextTokenWithChar(r13)     // Catch:{ all -> 0x0441 }
            int r0 = r10.token     // Catch:{ all -> 0x0441 }
            r1 = 4
            if (r0 != r1) goto L_0x0371
            java.lang.String r0 = r10.stringVal()     // Catch:{ all -> 0x0441 }
            java.lang.String r1 = "@"
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x0441 }
            if (r1 == 0) goto L_0x0300
            java.lang.Object r0 = r14.object     // Catch:{ all -> 0x00e2 }
        L_0x02fe:
            r6 = r0
            goto L_0x034c
        L_0x0300:
            java.lang.String r1 = ".."
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x0441 }
            if (r1 == 0) goto L_0x031e
            com.alibaba.fastjson.parser.ParseContext r1 = r14.parent     // Catch:{ all -> 0x00e2 }
            java.lang.Object r2 = r1.object     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x0311
            java.lang.Object r0 = r1.object     // Catch:{ all -> 0x00e2 }
            goto L_0x02fe
        L_0x0311:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x00e2 }
            r2.<init>(r1, r0)     // Catch:{ all -> 0x00e2 }
            r8.addResolveTask(r2)     // Catch:{ all -> 0x00e2 }
            r0 = 1
            r8.resolveStatus = r0     // Catch:{ all -> 0x00e2 }
        L_0x031c:
            r0 = r6
            goto L_0x02fe
        L_0x031e:
            java.lang.String r1 = "$"
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x0441 }
            if (r1 == 0) goto L_0x0341
            r1 = r14
        L_0x0327:
            com.alibaba.fastjson.parser.ParseContext r2 = r1.parent     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x032e
            com.alibaba.fastjson.parser.ParseContext r1 = r1.parent     // Catch:{ all -> 0x00e2 }
            goto L_0x0327
        L_0x032e:
            java.lang.Object r2 = r1.object     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x0335
            java.lang.Object r0 = r1.object     // Catch:{ all -> 0x00e2 }
            goto L_0x02fe
        L_0x0335:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x00e2 }
            r2.<init>(r1, r0)     // Catch:{ all -> 0x00e2 }
            r8.addResolveTask(r2)     // Catch:{ all -> 0x00e2 }
            r0 = 1
            r8.resolveStatus = r0     // Catch:{ all -> 0x00e2 }
            goto L_0x031c
        L_0x0341:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r1 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0441 }
            r1.<init>(r14, r0)     // Catch:{ all -> 0x0441 }
            r8.addResolveTask(r1)     // Catch:{ all -> 0x0441 }
            r1 = 1
            r8.resolveStatus = r1     // Catch:{ all -> 0x0441 }
        L_0x034c:
            r0 = 13
            r10.nextToken(r0)     // Catch:{ all -> 0x0441 }
            int r1 = r10.token     // Catch:{ all -> 0x0441 }
            if (r1 != r0) goto L_0x0367
            r0 = 16
            r10.nextToken(r0)     // Catch:{ all -> 0x0441 }
            r8.setContext(r14, r6, r9)     // Catch:{ all -> 0x0441 }
            r1 = r20
            if (r1 == 0) goto L_0x0363
            r1.object = r6
        L_0x0363:
            r8.setContext(r14)
            return r6
        L_0x0367:
            r1 = r20
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x039c }
            java.lang.String r2 = "illegal ref"
            r0.<init>(r2)     // Catch:{ all -> 0x039c }
            throw r0     // Catch:{ all -> 0x039c }
        L_0x0371:
            r1 = r20
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x039c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x039c }
            r3.<init>()     // Catch:{ all -> 0x039c }
            java.lang.String r4 = "illegal ref, "
            r3.append(r4)     // Catch:{ all -> 0x039c }
            java.lang.String r0 = com.alibaba.fastjson.parser.JSONToken.name(r0)     // Catch:{ all -> 0x039c }
            r3.append(r0)     // Catch:{ all -> 0x039c }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x039c }
            r2.<init>(r0)     // Catch:{ all -> 0x039c }
            throw r2     // Catch:{ all -> 0x039c }
        L_0x038e:
            r1 = r20
            if (r3 == 0) goto L_0x039f
            boolean r20 = r3.equals(r15)     // Catch:{ all -> 0x039c }
            if (r20 != 0) goto L_0x0399
            goto L_0x039f
        L_0x0399:
            r2 = 58
            goto L_0x03a4
        L_0x039c:
            r0 = move-exception
            goto L_0x0444
        L_0x039f:
            java.lang.String r13 = "@type"
            if (r13 != r15) goto L_0x044e
            goto L_0x0399
        L_0x03a4:
            r10.nextTokenWithChar(r2)     // Catch:{ all -> 0x039c }
            int r2 = r10.token     // Catch:{ all -> 0x039c }
            r4 = 4
            if (r2 != r4) goto L_0x0438
            java.lang.String r2 = r10.stringVal()     // Catch:{ all -> 0x039c }
            r4 = 16
            r10.nextToken(r4)     // Catch:{ all -> 0x039c }
            boolean r4 = r0 instanceof java.lang.Class     // Catch:{ all -> 0x039c }
            if (r4 == 0) goto L_0x03e1
            r4 = r0
            java.lang.Class r4 = (java.lang.Class) r4     // Catch:{ all -> 0x039c }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x039c }
            boolean r4 = r2.equals(r4)     // Catch:{ all -> 0x039c }
            if (r4 == 0) goto L_0x03e1
            int r2 = r10.token     // Catch:{ all -> 0x039c }
            r4 = 13
            if (r2 != r4) goto L_0x03d7
            r10.nextToken()     // Catch:{ all -> 0x039c }
        L_0x03cf:
            r12 = r1
            r13 = r6
            r1 = r21
            r19 = 0
            goto L_0x05eb
        L_0x03d7:
            r20 = r1
            r1 = r22
            r12 = r23
            r2 = r27
            goto L_0x06e6
        L_0x03e1:
            com.alibaba.fastjson.parser.ParserConfig r4 = r8.config     // Catch:{ all -> 0x039c }
            com.alibaba.fastjson.parser.JavaBeanInfo r5 = r7.beanInfo     // Catch:{ all -> 0x039c }
            com.alibaba.fastjson.parser.JavaBeanDeserializer r4 = r7.getSeeAlso(r4, r5, r2)     // Catch:{ all -> 0x039c }
            if (r4 != 0) goto L_0x0414
            com.alibaba.fastjson.parser.ParserConfig r4 = r8.config     // Catch:{ all -> 0x039c }
            java.lang.Class<?> r5 = r7.clazz     // Catch:{ all -> 0x039c }
            int r10 = r10.features     // Catch:{ all -> 0x039c }
            java.lang.Class r12 = r4.checkAutoType(r2, r5, r10)     // Catch:{ all -> 0x039c }
            java.lang.Class r0 = com.alibaba.fastjson.util.TypeUtils.getClass(r39)     // Catch:{ all -> 0x039c }
            if (r0 == 0) goto L_0x040d
            if (r12 == 0) goto L_0x0404
            boolean r0 = r0.isAssignableFrom(r12)     // Catch:{ all -> 0x039c }
            if (r0 == 0) goto L_0x0404
            goto L_0x040d
        L_0x0404:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x039c }
            java.lang.String r2 = "type not match"
            r0.<init>(r2)     // Catch:{ all -> 0x039c }
            throw r0     // Catch:{ all -> 0x039c }
        L_0x040d:
            com.alibaba.fastjson.parser.ParserConfig r0 = r8.config     // Catch:{ all -> 0x039c }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r0.getDeserializer(r12)     // Catch:{ all -> 0x039c }
            goto L_0x0415
        L_0x0414:
            r12 = 0
        L_0x0415:
            boolean r0 = r4 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer     // Catch:{ all -> 0x039c }
            if (r0 == 0) goto L_0x042c
            com.alibaba.fastjson.parser.JavaBeanDeserializer r4 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r4     // Catch:{ all -> 0x039c }
            r5 = 0
            java.lang.Object r0 = r4.deserialze(r8, r12, r9, r5)     // Catch:{ all -> 0x039c }
            if (r3 == 0) goto L_0x0430
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r3 = r4.getFieldDeserializer(r3)     // Catch:{ all -> 0x039c }
            if (r3 == 0) goto L_0x0430
            r3.setValue((java.lang.Object) r0, (java.lang.Object) r2)     // Catch:{ all -> 0x039c }
            goto L_0x0430
        L_0x042c:
            java.lang.Object r0 = r4.deserialze(r8, r12, r9)     // Catch:{ all -> 0x039c }
        L_0x0430:
            if (r1 == 0) goto L_0x0434
            r1.object = r6
        L_0x0434:
            r8.setContext(r14)
            return r0
        L_0x0438:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x039c }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x039c }
            throw r0     // Catch:{ all -> 0x039c }
        L_0x0441:
            r0 = move-exception
            r1 = r20
        L_0x0444:
            r12 = r1
            goto L_0x071c
        L_0x0447:
            r33 = r1
            r34 = r13
            r1 = r20
            r15 = 0
        L_0x044e:
            if (r6 != 0) goto L_0x0480
            if (r21 != 0) goto L_0x0480
            java.lang.Object r13 = r37.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r38, (java.lang.reflect.Type) r39)     // Catch:{ all -> 0x0479 }
            if (r13 != 0) goto L_0x046c
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x0467 }
            r35 = r1
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r1 = r7.fieldDeserializers     // Catch:{ all -> 0x0465 }
            int r1 = r1.length     // Catch:{ all -> 0x0465 }
            r6.<init>(r1)     // Catch:{ all -> 0x0465 }
            r21 = r6
            goto L_0x046e
        L_0x0465:
            r0 = move-exception
            goto L_0x046a
        L_0x0467:
            r0 = move-exception
            r35 = r1
        L_0x046a:
            r6 = r13
            goto L_0x047c
        L_0x046c:
            r35 = r1
        L_0x046e:
            if (r18 != 0) goto L_0x0483
            com.alibaba.fastjson.parser.ParseContext r1 = r8.setContext(r14, r13, r9)     // Catch:{ all -> 0x0465 }
            r20 = r1
            r6 = r21
            goto L_0x0487
        L_0x0479:
            r0 = move-exception
            r35 = r1
        L_0x047c:
            r12 = r35
            goto L_0x071c
        L_0x0480:
            r35 = r1
            r13 = r6
        L_0x0483:
            r6 = r21
            r20 = r35
        L_0x0487:
            if (r2 == 0) goto L_0x05a4
            if (r4 != 0) goto L_0x0498
            r12.parseField(r8, r13, r0, r6)     // Catch:{ all -> 0x0714 }
            r12 = r3
            r21 = r6
            r11 = r27
        L_0x0493:
            r15 = 1
            r19 = 0
            goto L_0x05d7
        L_0x0498:
            if (r13 != 0) goto L_0x04e6
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0714 }
            if (r5 == r1) goto L_0x04d6
            java.lang.Class<java.lang.Integer> r1 = java.lang.Integer.class
            if (r5 != r1) goto L_0x04a3
            goto L_0x04d6
        L_0x04a3:
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0714 }
            if (r5 == r1) goto L_0x04d1
            java.lang.Class<java.lang.Long> r1 = java.lang.Long.class
            if (r5 != r1) goto L_0x04ac
            goto L_0x04d1
        L_0x04ac:
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch:{ all -> 0x0714 }
            if (r5 == r1) goto L_0x04c9
            java.lang.Class<java.lang.Float> r1 = java.lang.Float.class
            if (r5 != r1) goto L_0x04b5
            goto L_0x04c9
        L_0x04b5:
            java.lang.Class r1 = java.lang.Double.TYPE     // Catch:{ all -> 0x0714 }
            if (r5 == r1) goto L_0x04c1
            java.lang.Class<java.lang.Double> r1 = java.lang.Double.class
            if (r5 != r1) goto L_0x04be
            goto L_0x04c1
        L_0x04be:
            r2 = r33
            goto L_0x04db
        L_0x04c1:
            java.lang.Double r1 = new java.lang.Double     // Catch:{ all -> 0x0714 }
            r4 = r31
            r1.<init>(r4)     // Catch:{ all -> 0x0714 }
            goto L_0x04da
        L_0x04c9:
            java.lang.Float r1 = new java.lang.Float     // Catch:{ all -> 0x0714 }
            r2 = r30
            r1.<init>(r2)     // Catch:{ all -> 0x0714 }
            goto L_0x04da
        L_0x04d1:
            java.lang.Long r1 = java.lang.Long.valueOf(r28)     // Catch:{ all -> 0x0714 }
            goto L_0x04da
        L_0x04d6:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0714 }
        L_0x04da:
            r2 = r1
        L_0x04db:
            r1 = r34
            java.lang.String r1 = r1.name     // Catch:{ all -> 0x0714 }
            r6.put(r1, r2)     // Catch:{ all -> 0x0714 }
            r36 = r3
            goto L_0x0592
        L_0x04e6:
            r36 = r3
            r2 = r30
            r3 = r31
            r1 = r34
            if (r33 != 0) goto L_0x058d
            java.lang.Class r15 = java.lang.Integer.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 == r15) goto L_0x055f
            java.lang.Class<java.lang.Integer> r15 = java.lang.Integer.class
            if (r5 != r15) goto L_0x04fa
            goto L_0x055f
        L_0x04fa:
            java.lang.Class r11 = java.lang.Long.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 == r11) goto L_0x0547
            java.lang.Class<java.lang.Long> r11 = java.lang.Long.class
            if (r5 != r11) goto L_0x0503
            goto L_0x0547
        L_0x0503:
            java.lang.Class r11 = java.lang.Float.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 == r11) goto L_0x0532
            java.lang.Class<java.lang.Float> r11 = java.lang.Float.class
            if (r5 != r11) goto L_0x050c
            goto L_0x0532
        L_0x050c:
            java.lang.Class r2 = java.lang.Double.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 == r2) goto L_0x051c
            java.lang.Class<java.lang.Double> r2 = java.lang.Double.class
            if (r5 != r2) goto L_0x0515
            goto L_0x051c
        L_0x0515:
            r2 = r33
            r12.setValue((java.lang.Object) r13, (java.lang.Object) r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x051c:
            boolean r2 = r1.fieldAccess     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r2 == 0) goto L_0x0529
            java.lang.Class r2 = java.lang.Double.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 != r2) goto L_0x0529
            r12.setValue((java.lang.Object) r13, (double) r3)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x0529:
            java.lang.Double r2 = new java.lang.Double     // Catch:{ IllegalAccessException -> 0x0573 }
            r2.<init>(r3)     // Catch:{ IllegalAccessException -> 0x0573 }
            r12.setValue((java.lang.Object) r13, (java.lang.Object) r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x0532:
            boolean r3 = r1.fieldAccess     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r3 == 0) goto L_0x053e
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 != r3) goto L_0x053e
            r12.setValue((java.lang.Object) r13, (float) r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x053e:
            java.lang.Float r3 = new java.lang.Float     // Catch:{ IllegalAccessException -> 0x0573 }
            r3.<init>(r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            r12.setValue((java.lang.Object) r13, (java.lang.Object) r3)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x0547:
            boolean r2 = r1.fieldAccess     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r2 == 0) goto L_0x0555
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 != r2) goto L_0x0555
            r2 = r28
            r12.setValue((java.lang.Object) r13, (long) r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x0555:
            r2 = r28
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            r12.setValue((java.lang.Object) r13, (java.lang.Object) r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x055f:
            boolean r2 = r1.fieldAccess     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r2 == 0) goto L_0x056b
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ IllegalAccessException -> 0x0573 }
            if (r5 != r2) goto L_0x056b
            r12.setValue((java.lang.Object) r13, (int) r11)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x056b:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ IllegalAccessException -> 0x0573 }
            r12.setValue((java.lang.Object) r13, (java.lang.Object) r2)     // Catch:{ IllegalAccessException -> 0x0573 }
            goto L_0x0592
        L_0x0573:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0714 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0714 }
            r3.<init>()     // Catch:{ all -> 0x0714 }
            java.lang.String r4 = "set property error, "
            r3.append(r4)     // Catch:{ all -> 0x0714 }
            java.lang.String r1 = r1.name     // Catch:{ all -> 0x0714 }
            r3.append(r1)     // Catch:{ all -> 0x0714 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0714 }
            r2.<init>(r1, r0)     // Catch:{ all -> 0x0714 }
            throw r2     // Catch:{ all -> 0x0714 }
        L_0x058d:
            r2 = r33
            r12.setValue((java.lang.Object) r13, (java.lang.Object) r2)     // Catch:{ all -> 0x0714 }
        L_0x0592:
            int r1 = r10.matchStat     // Catch:{ all -> 0x0714 }
            r4 = 4
            if (r1 != r4) goto L_0x059c
            r21 = r6
            r19 = 0
            goto L_0x05e7
        L_0x059c:
            r21 = r6
            r11 = r27
            r12 = r36
            goto L_0x0493
        L_0x05a4:
            r36 = r3
            r4 = 4
            r5 = 1
            r1 = r37
            r11 = r27
            r2 = r38
            r12 = r36
            r3 = r15
            r15 = 4
            r4 = r13
            r15 = 1
            r5 = r39
            r21 = r6
            r19 = 0
            boolean r1 = r1.parseField(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0714 }
            if (r1 != 0) goto L_0x05d1
            int r1 = r10.token     // Catch:{ all -> 0x0714 }
            r2 = 13
            if (r1 != r2) goto L_0x05ca
            r10.nextToken()     // Catch:{ all -> 0x0714 }
            goto L_0x05e7
        L_0x05ca:
            r2 = 16
        L_0x05cc:
            r3 = 13
            r4 = 0
            goto L_0x06df
        L_0x05d1:
            int r1 = r10.token     // Catch:{ all -> 0x0714 }
            r2 = 17
            if (r1 == r2) goto L_0x070b
        L_0x05d7:
            int r1 = r10.token     // Catch:{ all -> 0x0714 }
            r2 = 16
            if (r1 != r2) goto L_0x05de
            goto L_0x05cc
        L_0x05de:
            int r1 = r10.token     // Catch:{ all -> 0x0714 }
            r3 = 13
            if (r1 != r3) goto L_0x06d4
            r10.nextToken(r2)     // Catch:{ all -> 0x0714 }
        L_0x05e7:
            r12 = r20
            r1 = r21
        L_0x05eb:
            if (r13 != 0) goto L_0x06cc
            if (r1 != 0) goto L_0x060b
            java.lang.Object r1 = r37.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r38, (java.lang.reflect.Type) r39)     // Catch:{ all -> 0x0607 }
            if (r12 != 0) goto L_0x05ff
            com.alibaba.fastjson.parser.ParseContext r0 = r8.setContext(r14, r1, r9)     // Catch:{ all -> 0x05fb }
            r12 = r0
            goto L_0x05ff
        L_0x05fb:
            r0 = move-exception
            r6 = r1
            goto L_0x071c
        L_0x05ff:
            if (r12 == 0) goto L_0x0603
            r12.object = r1
        L_0x0603:
            r8.setContext(r14)
            return r1
        L_0x0607:
            r0 = move-exception
            r6 = r13
            goto L_0x071c
        L_0x060b:
            com.alibaba.fastjson.parser.JavaBeanInfo r0 = r7.beanInfo     // Catch:{ all -> 0x0607 }
            java.lang.String[] r0 = r0.creatorConstructorParameters     // Catch:{ all -> 0x0607 }
            if (r0 == 0) goto L_0x0613
            int r2 = r0.length     // Catch:{ all -> 0x0607 }
            goto L_0x0616
        L_0x0613:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r2 = r7.fieldDeserializers     // Catch:{ all -> 0x0607 }
            int r2 = r2.length     // Catch:{ all -> 0x0607 }
        L_0x0616:
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x0607 }
            r4 = 0
        L_0x0619:
            if (r4 >= r2) goto L_0x063d
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r5 = r7.fieldDeserializers     // Catch:{ all -> 0x0607 }
            r5 = r5[r4]     // Catch:{ all -> 0x0607 }
            com.alibaba.fastjson.util.FieldInfo r5 = r5.fieldInfo     // Catch:{ all -> 0x0607 }
            if (r0 == 0) goto L_0x062a
            java.lang.String r6 = r5.name     // Catch:{ all -> 0x0607 }
            java.lang.Object r6 = r1.remove(r6)     // Catch:{ all -> 0x0607 }
            goto L_0x0630
        L_0x062a:
            java.lang.String r6 = r5.name     // Catch:{ all -> 0x0607 }
            java.lang.Object r6 = r1.get(r6)     // Catch:{ all -> 0x0607 }
        L_0x0630:
            if (r6 != 0) goto L_0x0638
            java.lang.Class<?> r5 = r5.fieldClass     // Catch:{ all -> 0x0607 }
            java.lang.Object r6 = com.alibaba.fastjson.util.TypeUtils.defaultValue(r5)     // Catch:{ all -> 0x0607 }
        L_0x0638:
            r3[r4] = r6     // Catch:{ all -> 0x0607 }
            int r4 = r4 + 1
            goto L_0x0619
        L_0x063d:
            com.alibaba.fastjson.parser.JavaBeanInfo r2 = r7.beanInfo     // Catch:{ all -> 0x0607 }
            java.lang.reflect.Constructor<?> r2 = r2.creatorConstructor     // Catch:{ all -> 0x0607 }
            if (r2 == 0) goto L_0x069b
            com.alibaba.fastjson.parser.JavaBeanInfo r2 = r7.beanInfo     // Catch:{ Exception -> 0x067b }
            java.lang.reflect.Constructor<?> r2 = r2.creatorConstructor     // Catch:{ Exception -> 0x067b }
            java.lang.Object r2 = r2.newInstance(r3)     // Catch:{ Exception -> 0x067b }
            if (r0 == 0) goto L_0x0679
            java.util.Set r0 = r1.entrySet()     // Catch:{ all -> 0x0675 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0675 }
        L_0x0655:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0675 }
            if (r1 == 0) goto L_0x0679
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0675 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0675 }
            java.lang.Object r3 = r1.getKey()     // Catch:{ all -> 0x0675 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0675 }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r3 = r7.getFieldDeserializer(r3)     // Catch:{ all -> 0x0675 }
            if (r3 == 0) goto L_0x0655
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0675 }
            r3.setValue((java.lang.Object) r2, (java.lang.Object) r1)     // Catch:{ all -> 0x0675 }
            goto L_0x0655
        L_0x0675:
            r0 = move-exception
            r6 = r2
            goto L_0x071c
        L_0x0679:
            r13 = r2
            goto L_0x06cc
        L_0x067b:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0607 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0607 }
            r2.<init>()     // Catch:{ all -> 0x0607 }
            java.lang.String r3 = "create instance error, "
            r2.append(r3)     // Catch:{ all -> 0x0607 }
            com.alibaba.fastjson.parser.JavaBeanInfo r3 = r7.beanInfo     // Catch:{ all -> 0x0607 }
            java.lang.reflect.Constructor<?> r3 = r3.creatorConstructor     // Catch:{ all -> 0x0607 }
            java.lang.String r3 = r3.toGenericString()     // Catch:{ all -> 0x0607 }
            r2.append(r3)     // Catch:{ all -> 0x0607 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0607 }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0607 }
            throw r1     // Catch:{ all -> 0x0607 }
        L_0x069b:
            com.alibaba.fastjson.parser.JavaBeanInfo r0 = r7.beanInfo     // Catch:{ all -> 0x0607 }
            java.lang.reflect.Method r0 = r0.factoryMethod     // Catch:{ all -> 0x0607 }
            if (r0 == 0) goto L_0x06cc
            com.alibaba.fastjson.parser.JavaBeanInfo r0 = r7.beanInfo     // Catch:{ Exception -> 0x06ac }
            java.lang.reflect.Method r0 = r0.factoryMethod     // Catch:{ Exception -> 0x06ac }
            r4 = 0
            java.lang.Object r0 = r0.invoke(r4, r3)     // Catch:{ Exception -> 0x06ac }
            r13 = r0
            goto L_0x06cc
        L_0x06ac:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0607 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0607 }
            r2.<init>()     // Catch:{ all -> 0x0607 }
            java.lang.String r3 = "create factory method error, "
            r2.append(r3)     // Catch:{ all -> 0x0607 }
            com.alibaba.fastjson.parser.JavaBeanInfo r3 = r7.beanInfo     // Catch:{ all -> 0x0607 }
            java.lang.reflect.Method r3 = r3.factoryMethod     // Catch:{ all -> 0x0607 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0607 }
            r2.append(r3)     // Catch:{ all -> 0x0607 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0607 }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0607 }
            throw r1     // Catch:{ all -> 0x0607 }
        L_0x06cc:
            if (r12 == 0) goto L_0x06d0
            r12.object = r13
        L_0x06d0:
            r8.setContext(r14)
            return r13
        L_0x06d4:
            r4 = 0
            int r1 = r10.token     // Catch:{ all -> 0x0714 }
            r5 = 18
            if (r1 == r5) goto L_0x06ed
            int r1 = r10.token     // Catch:{ all -> 0x0714 }
            if (r1 == r15) goto L_0x06ed
        L_0x06df:
            r2 = r11
            r3 = r12
            r6 = r13
            r1 = r22
            r12 = r23
        L_0x06e6:
            r4 = 4
            r11 = 16
            r15 = 13
            goto L_0x00ce
        L_0x06ed:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0714 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0714 }
            r1.<init>()     // Catch:{ all -> 0x0714 }
            java.lang.String r2 = "syntax error, unexpect token "
            r1.append(r2)     // Catch:{ all -> 0x0714 }
            int r2 = r10.token     // Catch:{ all -> 0x0714 }
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)     // Catch:{ all -> 0x0714 }
            r1.append(r2)     // Catch:{ all -> 0x0714 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0714 }
            r0.<init>(r1)     // Catch:{ all -> 0x0714 }
            throw r0     // Catch:{ all -> 0x0714 }
        L_0x070b:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0714 }
            java.lang.String r1 = "syntax error, unexpect token ':'"
            r0.<init>(r1)     // Catch:{ all -> 0x0714 }
            throw r0     // Catch:{ all -> 0x0714 }
        L_0x0714:
            r0 = move-exception
            r6 = r13
            goto L_0x00e3
        L_0x0718:
            r0 = move-exception
            r4 = r12
            goto L_0x0041
        L_0x071c:
            if (r12 == 0) goto L_0x0720
            r12.object = r6
        L_0x0720:
            r8.setContext(r14)
            throw r0
        L_0x0724:
            java.lang.Object r0 = r38.parse()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public FieldDeserializer getFieldDeserializerByHash(long j) {
        for (FieldDeserializer fieldDeserializer : this.sortedFieldDeserializers) {
            if (fieldDeserializer.fieldInfo.nameHashCode == j) {
                return fieldDeserializer;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public FieldDeserializer getFieldDeserializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        if (this.beanInfo.ordered) {
            while (i < this.sortedFieldDeserializers.length) {
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
                if (fieldDeserializer.fieldInfo.name.equalsIgnoreCase(str)) {
                    return fieldDeserializer;
                }
                i++;
            }
            return null;
        }
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo <= 0) {
                return this.sortedFieldDeserializers[i2];
            } else {
                length = i2 - 1;
            }
        }
        if (this.alterNameFieldDeserializers != null) {
            return this.alterNameFieldDeserializers.get(str);
        }
        return null;
    }

    private boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        boolean z;
        DefaultJSONParser defaultJSONParser2 = defaultJSONParser;
        String str2 = str;
        Object obj2 = obj;
        JSONLexer jSONLexer = defaultJSONParser2.lexer;
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str2);
        if (fieldDeserializer == null) {
            long fnv_64_lower = TypeUtils.fnv_64_lower(str);
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                for (int i = 0; i < this.sortedFieldDeserializers.length; i++) {
                    jArr[i] = TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i].fieldInfo.name);
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int binarySearch = Arrays.binarySearch(this.smartMatchHashArray, fnv_64_lower);
            if (binarySearch < 0) {
                z = str2.startsWith("is");
                if (z) {
                    binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(str2.substring(2)));
                }
            } else {
                z = false;
            }
            if (binarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    int[] iArr = new int[this.smartMatchHashArray.length];
                    Arrays.fill(iArr, -1);
                    for (int i2 = 0; i2 < this.sortedFieldDeserializers.length; i2++) {
                        int binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i2].fieldInfo.name));
                        if (binarySearch2 >= 0) {
                            iArr[binarySearch2] = i2;
                        }
                    }
                    this.smartMatchHashArrayMapping = iArr;
                }
                int i3 = this.smartMatchHashArrayMapping[binarySearch];
                if (i3 != -1) {
                    fieldDeserializer = this.sortedFieldDeserializers[i3];
                    Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
                    if (!(!z || cls == Boolean.TYPE || cls == Boolean.class)) {
                        fieldDeserializer = null;
                    }
                }
            }
        }
        int i4 = Feature.SupportNonPublicField.mask;
        if (fieldDeserializer == null && !((defaultJSONParser2.lexer.features & i4) == 0 && (i4 & this.beanInfo.parserFeatures) == 0)) {
            if (this.extraFieldDeserializers == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1, 0.75f, 1);
                Class cls2 = this.clazz;
                while (cls2 != null && cls2 != Object.class) {
                    for (Field field : cls2.getDeclaredFields()) {
                        String name = field.getName();
                        if (getFieldDeserializer(name) == null) {
                            int modifiers = field.getModifiers();
                            if ((modifiers & 16) == 0 && (modifiers & 8) == 0) {
                                concurrentHashMap.put(name, field);
                            }
                        }
                    }
                    cls2 = cls2.getSuperclass();
                }
                this.extraFieldDeserializers = concurrentHashMap;
            }
            Object obj3 = this.extraFieldDeserializers.get(str2);
            if (obj3 != null) {
                if (obj3 instanceof FieldDeserializer) {
                    fieldDeserializer = (FieldDeserializer) obj3;
                } else {
                    Field field2 = (Field) obj3;
                    field2.setAccessible(true);
                    fieldDeserializer = new DefaultFieldDeserializer(defaultJSONParser2.config, this.clazz, new FieldInfo(str, field2.getDeclaringClass(), field2.getType(), field2.getGenericType(), field2, 0, 0));
                    this.extraFieldDeserializers.put(str2, fieldDeserializer);
                }
            }
        }
        if (fieldDeserializer == null) {
            parseExtra(defaultJSONParser2, obj2, str2);
            return false;
        }
        jSONLexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
        fieldDeserializer.parseField(defaultJSONParser2, obj2, type, map);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void parseExtra(DefaultJSONParser defaultJSONParser, Object obj, String str) {
        Object obj2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if ((defaultJSONParser.lexer.features & Feature.IgnoreNotMatch.mask) != 0) {
            jSONLexer.nextTokenWithChar(Operators.CONDITION_IF_MIDDLE);
            Type type = null;
            List<ExtraTypeProvider> list = defaultJSONParser.extraTypeProviders;
            if (list != null) {
                for (ExtraTypeProvider extraType : list) {
                    type = extraType.getExtraType(obj, str);
                }
            }
            if (type == null) {
                obj2 = defaultJSONParser.parse();
            } else {
                obj2 = defaultJSONParser.parseObject(type);
            }
            if (obj instanceof ExtraProcessable) {
                ((ExtraProcessable) obj).processExtra(str, obj2);
                return;
            }
            List<ExtraProcessor> list2 = defaultJSONParser.extraProcessors;
            if (list2 != null) {
                for (ExtraProcessor processExtra : list2) {
                    processExtra.processExtra(obj, str, obj2);
                }
                return;
            }
            return;
        }
        throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + str);
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object obj;
        double d;
        float f;
        if (this.beanInfo.creatorConstructor == null) {
            Object createInstance = createInstance((DefaultJSONParser) null, (Type) this.clazz);
            for (Map.Entry next : map.entrySet()) {
                FieldDeserializer fieldDeserializer = getFieldDeserializer((String) next.getKey());
                if (fieldDeserializer != null) {
                    Object value = next.getValue();
                    Method method = fieldDeserializer.fieldInfo.method;
                    if (method != null) {
                        method.invoke(createInstance, new Object[]{TypeUtils.cast(value, method.getGenericParameterTypes()[0], parserConfig)});
                    } else {
                        Field field = fieldDeserializer.fieldInfo.field;
                        Type type = fieldDeserializer.fieldInfo.fieldType;
                        if (type == Boolean.TYPE) {
                            if (value == Boolean.FALSE) {
                                field.setBoolean(createInstance, false);
                            } else if (value == Boolean.TRUE) {
                                field.setBoolean(createInstance, true);
                            }
                        } else if (type == Integer.TYPE) {
                            if (value instanceof Number) {
                                field.setInt(createInstance, ((Number) value).intValue());
                            }
                        } else if (type == Long.TYPE) {
                            if (value instanceof Number) {
                                field.setLong(createInstance, ((Number) value).longValue());
                            }
                        } else if (type == Float.TYPE) {
                            if (value instanceof Number) {
                                field.setFloat(createInstance, ((Number) value).floatValue());
                            } else if (value instanceof String) {
                                String str = (String) value;
                                if (str.length() <= 10) {
                                    f = TypeUtils.parseFloat(str);
                                } else {
                                    f = Float.parseFloat(str);
                                }
                                field.setFloat(createInstance, f);
                            }
                        } else if (type == Double.TYPE) {
                            if (value instanceof Number) {
                                field.setDouble(createInstance, ((Number) value).doubleValue());
                            } else if (value instanceof String) {
                                String str2 = (String) value;
                                if (str2.length() <= 10) {
                                    d = TypeUtils.parseDouble(str2);
                                } else {
                                    d = Double.parseDouble(str2);
                                }
                                field.setDouble(createInstance, d);
                            }
                        } else if (value != null && type == value.getClass()) {
                            field.set(createInstance, value);
                        }
                        String str3 = fieldDeserializer.fieldInfo.format;
                        if (str3 == null || type != Date.class || !(value instanceof String)) {
                            obj = type instanceof ParameterizedType ? TypeUtils.cast(value, (ParameterizedType) type, parserConfig) : TypeUtils.cast(value, type, parserConfig);
                        } else {
                            try {
                                obj = new SimpleDateFormat(str3).parse((String) value);
                            } catch (ParseException unused) {
                                obj = null;
                            }
                        }
                        field.set(createInstance, obj);
                    }
                }
            }
            return createInstance;
        }
        FieldInfo[] fieldInfoArr = this.beanInfo.fields;
        int length = fieldInfoArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = fieldInfoArr[i];
            Object obj2 = map.get(fieldInfo.name);
            if (obj2 == null) {
                obj2 = TypeUtils.defaultValue(fieldInfo.fieldClass);
            }
            objArr[i] = obj2;
        }
        if (this.beanInfo.creatorConstructor == null) {
            return null;
        }
        try {
            return this.beanInfo.creatorConstructor.newInstance(objArr);
        } catch (Exception e) {
            throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e);
        }
    }

    /* access modifiers changed from: protected */
    public JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class deserializer : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer2 = parserConfig.getDeserializer(deserializer);
            if (deserializer2 instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer2;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }
}
