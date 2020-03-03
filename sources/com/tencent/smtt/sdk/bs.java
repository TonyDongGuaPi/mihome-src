package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;

final class bs extends Thread {
    bs() {
    }

    public void run() {
        if (WebView.j == null) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
            return;
        }
        o a2 = o.a(true);
        if (o.f9185a) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
            return;
        }
        ai a3 = ai.a(WebView.j);
        int c = a3.c();
        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + c);
        if (c == 2) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
            a2.a(String.valueOf(a3.b()));
            a2.b(true);
            return;
        }
        int b = a3.b("copy_status");
        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + b);
        if (b == 1) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
            a2.a(String.valueOf(a3.c("copy_core_ver")));
            a2.b(true);
        } else if (bt.a().b()) {
        } else {
            if (c == 3 || b == 3) {
                TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                a2.a(String.valueOf(o.d()));
                a2.b(true);
            }
        }
    }
}
