package com.imi.fastjson.parser.deserializer;

import com.facebook.react.devsupport.StackTraceHelper;
import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.Feature;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.JSONToken;
import java.lang.reflect.Type;

public class StackTraceElementDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final StackTraceElementDeserializer f6124a = new StackTraceElementDeserializer();

    public int a() {
        return 12;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 8) {
            n.a();
            return null;
        } else if (n.d() == 12 || n.d() == 16) {
            String str = null;
            String str2 = null;
            String str3 = null;
            int i = 0;
            while (true) {
                String a2 = n.a(defaultJSONParser.c());
                if (a2 == null) {
                    if (n.d() == 13) {
                        n.a(16);
                        break;
                    } else if (n.d() == 16 && n.a(Feature.AllowArbitraryCommas)) {
                    }
                }
                n.b(4);
                if (a2 == "className") {
                    if (n.d() == 8) {
                        str = null;
                    } else if (n.d() == 4) {
                        str = n.z();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (a2 == "methodName") {
                    if (n.d() == 8) {
                        str2 = null;
                    } else if (n.d() == 4) {
                        str2 = n.z();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (a2 == "fileName") {
                    if (n.d() == 8) {
                        str3 = null;
                    } else if (n.d() == 4) {
                        str3 = n.z();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (a2 == StackTraceHelper.LINE_NUMBER_KEY) {
                    if (n.d() == 8) {
                        i = 0;
                    } else if (n.d() == 2) {
                        i = n.r();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (a2 == "nativeMethod") {
                    if (n.d() == 8) {
                        n.a(16);
                    } else if (n.d() == 6) {
                        n.a(16);
                    } else if (n.d() == 7) {
                        n.a(16);
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (a2 != JSON.DEFAULT_TYPE_KEY) {
                    throw new JSONException("syntax error : " + a2);
                } else if (n.d() == 4) {
                    String z = n.z();
                    if (!z.equals("java.lang.StackTraceElement")) {
                        throw new JSONException("syntax error : " + z);
                    }
                } else if (n.d() != 8) {
                    throw new JSONException("syntax error");
                }
                if (n.d() == 13) {
                    n.a(16);
                    break;
                }
            }
            return new StackTraceElement(str, str2, str3, i);
        } else {
            throw new JSONException("syntax error: " + JSONToken.a(n.d()));
        }
    }
}
