package com.xiaomi.smarthome.framework.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.login.LoginHostApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.international.ServerHelper;
import com.xiaomi.smarthome.miio.user.UserMamanger;

public class LoginHostApiImpl extends LoginHostApi {
    public void a() {
        SHApplication.getStateNotifier().e();
    }

    public void a(final int i) {
        if (GlobalSetting.u) {
            LogUtilGrey.a("login", "LoginHostApiImpl onLoginSuccess " + i);
        }
        if (i != 1) {
            LoginManager.a().a(i);
        } else {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    LoginManager.a().a(i);
                }
            });
        }
    }

    public void b() {
        SHApplication.getStateNotifier().g();
        SHApplication.getPushManager().f();
    }

    public void a(String str, View view) {
        if (view instanceof SimpleDraweeView) {
            UserMamanger.a().a(str, (SimpleDraweeView) view, (BasePostprocessor) null);
        }
    }

    public void c() {
        SHApplication.initFacebookSdk();
    }

    public void a(View view, String str) {
        if (view instanceof SimpleDraweeView) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view;
            ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setRoundingParams(RoundingParams.asCircle());
            ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setPlaceholderImage((int) R.drawable.login_profile_picture_loading);
            if (!TextUtils.isEmpty(str)) {
                simpleDraweeView.setImageURI(str);
            }
        }
    }

    public void a(String str) {
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            MyLog.f(str);
        }
    }

    public void a(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, SmartHomeMainActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    public void b(String str) {
        ServerHelper.a(SHApplication.getAppContext(), 2, new ServerHelper.InternationalCallback() {
            public void a() {
            }

            public void b() {
            }
        }, str);
    }
}
