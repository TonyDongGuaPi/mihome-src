package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.j;
import java.io.File;
import java.io.FileFilter;

class aq extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9144a;
    final /* synthetic */ Context b;
    final /* synthetic */ am c;

    aq(am amVar, Context context, Context context2) {
        this.c = amVar;
        this.f9144a = context;
        this.b = context2;
    }

    public void run() {
        File file;
        am amVar;
        Context context;
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
        try {
            if (this.f9144a == null) {
                file = new File(TbsShareManager.getHostCorePathAppDefined());
            } else {
                if (!TbsShareManager.isThirdPartyApp(this.b)) {
                    amVar = this.c;
                    context = this.f9144a;
                } else if (TbsShareManager.c(this.b) == null || !TbsShareManager.c(this.b).contains("decouple")) {
                    amVar = this.c;
                    context = this.f9144a;
                } else {
                    file = this.c.p(this.f9144a);
                }
                file = amVar.q(context);
            }
            File q = this.c.q(this.b);
            int i = Build.VERSION.SDK_INT;
            if (i != 19 && i < 21) {
                j.a(file, q, (FileFilter) new ar(this));
            }
            j.a(file, q, (FileFilter) new as(this));
            TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
