package com.xiaomi.youpin.hawkeye.upload;

import android.content.Context;
import com.xiaomi.youpin.hawkeye.storage.StorageManager;
import com.xiaomi.youpin.hawkeye.utils.HLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultUploadImpl implements IUpload {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23379a = "HawkEye";
    private OkHttpClient b = OKHttpClientFactory.a();

    public boolean a(Context context, final int i, HashMap<String, String> hashMap) {
        this.b.newCall(new Request.Builder().get().url(a(hashMap)).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                HLog.b("HawkEye", "upload was fail");
            }

            public void onResponse(Call call, Response response) throws IOException {
                boolean a2 = StorageManager.a().a(i);
                HLog.b("HawkEye", "upload isSuccess  " + a2);
            }
        });
        return true;
    }

    private String a(HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer(UploadConstants.f23381a);
        if (hashMap != null && hashMap.size() > 0) {
            stringBuffer.append("?");
            ArrayList arrayList = new ArrayList(hashMap.keySet());
            Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                String str = (String) arrayList.get(i);
                String str2 = hashMap.get(str);
                if (i == arrayList.size() - 1) {
                    stringBuffer.append(String.format("%s=%s", new Object[]{str, str2}));
                } else {
                    stringBuffer.append(String.format("%s=%s&", new Object[]{str, str2}));
                }
            }
        }
        return stringBuffer.toString();
    }
}
