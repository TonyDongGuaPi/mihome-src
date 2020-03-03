package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ah;
import com.xiaomi.stat.c.c;
import java.io.File;

public class hj implements XMPushService.l {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12776a = false;
    private Context b;
    private boolean c;
    private int d;

    public hj(Context context) {
        this.b = context;
    }

    private String a(String str) {
        return c.f23036a.equals(str) ? "1000271" : this.b.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, (String) null);
    }

    private void a(Context context) {
        this.c = ah.a(context).a(ht.TinyDataUploadSwitch.a(), true);
        this.d = ah.a(context).a(ht.TinyDataUploadFrequency.a(), 7200);
        this.d = Math.max(60, this.d);
    }

    public static void a(boolean z) {
        f12776a = z;
    }

    private boolean a(hn hnVar) {
        return az.c(this.b) && hnVar != null && !TextUtils.isEmpty(a(this.b.getPackageName())) && new File(this.b.getFilesDir(), "tiny_data.data").exists() && !f12776a;
    }

    private boolean b() {
        return Math.abs((System.currentTimeMillis() / 1000) - this.b.getSharedPreferences("mipush_extra", 4).getLong("last_tiny_data_upload_timestamp", -1)) > ((long) this.d);
    }

    public void a() {
        a(this.b);
        if (this.c && b()) {
            b.a("TinyData TinyDataCacheProcessor.pingFollowUpAction ts:" + System.currentTimeMillis());
            hn a2 = hm.a(this.b).a();
            if (!a(a2)) {
                b.a("TinyData TinyDataCacheProcessor.pingFollowUpAction !canUpload(uploader) ts:" + System.currentTimeMillis());
                return;
            }
            f12776a = true;
            hk.a(this.b, a2);
        }
    }
}
