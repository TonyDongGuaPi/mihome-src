package com.xiaomi.mobilestats.common;

import com.alibaba.fastjson.JSON;
import java.util.List;

public class JSONUtil {
    public static String format(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object parse(String str, Class cls) {
        try {
            return JSON.parseObject(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List parseList(String str, Class cls) {
        try {
            return JSON.parseArray(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
