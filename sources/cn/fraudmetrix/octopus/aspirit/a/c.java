package cn.fraudmetrix.octopus.aspirit.a;

import cn.fraudmetrix.octopus.aspirit.b.e;
import cn.fraudmetrix.octopus.aspirit.utils.d;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class c implements e<String, JSONObject> {
    public JSONObject a(String str) {
        d.a(str, "apply string is null");
        JSONObject parseObject = JSON.parseObject(str);
        if (parseObject.getIntValue(parseObject.containsKey("result_code") ? "result_code" : "code") == 0) {
            return parseObject;
        }
        throw new RuntimeException(parseObject.getString(parseObject.containsKey("result_message") ? "result_message" : "message"));
    }
}
