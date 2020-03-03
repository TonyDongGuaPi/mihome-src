package cn.fraudmetrix.octopus.aspirit.a;

import cn.fraudmetrix.octopus.aspirit.b.b;
import cn.fraudmetrix.octopus.aspirit.bean.f;
import cn.fraudmetrix.octopus.aspirit.utils.d;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class a<Bean extends f> implements b<String, Bean> {

    /* renamed from: a  reason: collision with root package name */
    private c f587a = new c();

    public Bean a(String str) {
        Type[] actualTypeArguments;
        JSONObject a2 = this.f587a.a(str);
        d.a((Object) a2, "jsonObject is null");
        JSONObject jSONObject = a2.getJSONObject("data");
        d.a((Object) jSONObject, "data jsonObject is null");
        Type genericSuperclass = getClass().getGenericSuperclass();
        if ((genericSuperclass instanceof ParameterizedType) && (actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments()) != null && actualTypeArguments.length > 0) {
            return (f) jSONObject.toJavaObject((Class) actualTypeArguments[0]);
        }
        throw new RuntimeException("CallBean error");
    }

    public void a() {
    }

    public void b() {
    }
}
