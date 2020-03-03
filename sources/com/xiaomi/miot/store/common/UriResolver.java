package com.xiaomi.miot.store.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.exoplayer2.C;
import com.xiaomi.miot.store.ui.MiotStoreMainActivity;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.framework.navigate.PageUrl;

public final class UriResolver {
    public static boolean a(@NonNull Context context, @NonNull Uri uri) {
        if (!a(uri)) {
            return false;
        }
        Intent intent = new Intent(context, MiotStoreMainActivity.class);
        if (context instanceof Activity) {
            intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        } else {
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
        }
        intent.setData(uri);
        context.startActivity(intent);
        return true;
    }

    public static boolean a(@NonNull Uri uri) {
        if (("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) && PageUrl.f.equals(uri.getHost()) && uri.getPath() != null && uri.getPath().startsWith(PageUrl.n)) {
            return true;
        }
        return false;
    }
}
