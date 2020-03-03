package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import com.google.code.microlog4android.appender.DatagramAppender;

class SipTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private Context f2449a;

    SipTest(Context context) {
        this.f2449a = context;
    }

    public boolean a() throws Throwable {
        SipManager newInstance;
        if (!SipManager.isApiSupported(this.f2449a) || (newInstance = SipManager.newInstance(this.f2449a)) == null) {
            return true;
        }
        SipProfile.Builder builder = new SipProfile.Builder("Permission", DatagramAppender.DEFAULT_HOST);
        builder.setPassword("password");
        SipProfile build = builder.build();
        newInstance.open(build);
        newInstance.close(build.getUriString());
        return true;
    }
}
