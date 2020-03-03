package com.xiaomi.smarthome.operation.js_sdk.linker;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadActivity;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import java.util.Map;

public class JumperProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21084a = "JumperProvider";
    private static final Map<String, UrlJumper> b = new ArrayMap();

    interface UrlJumper {
        boolean handle(Uri uri, Map<String, String> map);
    }

    private JumperProvider() {
    }

    static {
        b.put("webPage", $$Lambda$JumperProvider$xLaQt1EQ1S5Rr7jn_4YRJn02ZVk.INSTANCE);
        b.put("cloudvideodownloadlist", $$Lambda$JumperProvider$tS3mLkUwoenCZ7JT2r1SYbdHDTk.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean b(Uri uri, Map map) {
        String str = (String) map.get("url");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent(SHApplication.getAppContext(), OperationCommonWebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("title", (String) map.get("title"));
        intent.putExtra(OperationCommonWebViewActivity.ARGS_FROM_DEEP_LINK, true);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        SHApplication.getAppContext().startActivity(intent);
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(Uri uri, Map map) {
        if (!a()) {
            Log.d(f21084a, "process: no login,ignore.");
            return false;
        }
        String str = (String) map.get("did");
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(f21084a, "process: invalid params,empty did.");
            return false;
        }
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 == null) {
            LogUtil.b(f21084a, "process: invalid params,no device found with did: " + str);
            return false;
        }
        Intent intent = new Intent(SHApplication.getAppContext(), CloudVideoDownloadActivity.class);
        intent.putExtra("did", str);
        intent.putExtra("uid", b2.userId);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        SHApplication.getApplication().startActivity(intent);
        return true;
    }

    public static UrlJumper a(String str) {
        return b.get(str);
    }

    private static boolean a() {
        return CoreApi.a().q();
    }
}
