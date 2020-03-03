package com.xiaomi.youpin.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.xiaomi.youpin.share.model.PosterData;
import com.xiaomi.youpin.share.ui.PosterShareActivity;
import com.xiaomi.youpin.share.ui.assemble.ShareActivity;

public class ShareRouter {
    public static void a(Context context, String str, PosterData posterData) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra("shareUrl", str);
        intent.putExtra("poster", posterData);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void a(Context context, String str, boolean z) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra("shareUrl", str);
        intent.putExtra("isShareApp", z);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void b(Context context, String str, PosterData posterData) {
        Intent intent = new Intent(context, PosterShareActivity.class);
        intent.putExtra("shareUrl", str);
        intent.putExtra("poster", posterData);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }
}
