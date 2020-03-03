package com.xiaomi.youpin;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.util.List;

public class ServiceTokenUtil {
    public static WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uid", CoreApi.a().s());
        if (CoreApi.a().q()) {
            createMap.putString("mode", UserMode.f23179a);
            WritableMap createMap2 = Arguments.createMap();
            List<MiServiceTokenInfo> x = CoreApi.a().x();
            if (x != null) {
                for (MiServiceTokenInfo next : x) {
                    createMap2.putString(next.f23514a, next.c);
                }
                createMap.putMap("tokens", createMap2);
            }
        }
        return createMap;
    }
}
