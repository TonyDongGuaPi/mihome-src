package com.xiaomi.smarthome.library.http.sync;

import okhttp3.Response;

public class TextSyncHandler extends SyncHandler<String> {
    /* renamed from: a */
    public String b(Response response) throws Exception {
        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("failure code:" + response.code());
        }
    }
}
