package com.xiaomi.smarthome.frame.login;

import android.content.Context;
import android.net.Uri;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.frame.login.util.FileLoadManager;
import java.io.File;

public class LoginVideoManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16157a = "http://cdn.fds.api.xiaomi.com/miio.files/commonfile_mp4_212cb4a0436eef48ff92afb6e01b0ea3.mp4";
    private static final String b = "login_video.mp4";
    private static final String c = "login_video_temp.mp4";
    private static volatile LoginVideoManager d;
    private Uri e = Uri.EMPTY;
    private File f;

    private LoginVideoManager() {
    }

    public static LoginVideoManager a() {
        if (d == null) {
            synchronized (LoginVideoManager.class) {
                if (d == null) {
                    d = new LoginVideoManager();
                }
            }
        }
        return d;
    }

    public synchronized void a(Context context) {
        if (!b()) {
            this.f = FileUtils.b(context, b);
            if (this.f.exists()) {
                this.e = Uri.parse(this.f.getAbsolutePath());
            } else if (!FileUtils.b(context, c).exists()) {
                try {
                    FileLoadManager.a(context).a(c, b, f16157a);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            return;
        }
        return;
    }

    public boolean b() {
        return this.e != Uri.EMPTY && this.f.exists();
    }

    public Uri c() {
        return this.e;
    }
}
