package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.taobao.weex.annotation.JSMethod;
import java.lang.reflect.Type;
import java.util.Locale;

public class LocaleDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final LocaleDeserializer f6117a = new LocaleDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = (String) defaultJSONParser.l();
        if (str == null) {
            return null;
        }
        String[] split = str.split(JSMethod.NOT_SET);
        if (split.length == 1) {
            return new Locale(split[0]);
        }
        if (split.length == 2) {
            return new Locale(split[0], split[1]);
        }
        return new Locale(split[0], split[1], split[2]);
    }
}
