package com.tiqiaa.icontrol.util;

import com.imi.fastjson.JSON;
import com.imi.fastjson.serializer.SerializerFeature;

public class TQJSON {
    public static String toJSONString(Object obj) {
        if (!TiqiaaService.isTestModeEnable()) {
            return "";
        }
        return JSON.toJSONString(obj, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }
}
