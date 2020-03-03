package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final URLDeserializer f6130a = new URLDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = (String) defaultJSONParser.l();
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new JSONException("create url error", e);
        }
    }
}
