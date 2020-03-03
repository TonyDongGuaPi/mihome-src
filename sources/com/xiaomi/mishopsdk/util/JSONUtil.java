package com.xiaomi.mishopsdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.List;

public class JSONUtil {
    public static <T> T parse(String str, Class<T> cls) {
        try {
            return JSON.parseObject(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T Parse(String str, TypeReference<T> typeReference) {
        try {
            return JSON.parseObject(str, typeReference, new Feature[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List parseList(String str, Class<T> cls) {
        try {
            new SerializeWriter();
            return JSON.parseArray(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Object obj) {
        try {
            return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
