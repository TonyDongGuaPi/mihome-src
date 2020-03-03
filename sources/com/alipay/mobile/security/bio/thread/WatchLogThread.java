package com.alipay.mobile.security.bio.thread;

import android.content.Context;
import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.service.BioSysBehavior;
import com.alipay.mobile.security.bio.service.BioSysBehaviorType;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.DateUtil;
import com.alipay.mobile.security.bio.utils.FileUtil;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.download.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WatchLogThread extends WatchThread {

    /* renamed from: a  reason: collision with root package name */
    private BlockingQueue<BioSysBehavior> f1018a = new LinkedBlockingQueue(30);
    private File b = null;
    private File c = null;
    private File d = null;
    private File e = null;
    private File f = null;
    private File g = null;
    private Context h = null;

    public WatchLogThread(Context context, String str) {
        super(str);
        if (context != null) {
            this.h = context;
            this.b = this.h.getDir("BIO_LOGS", 0);
            String dateFormat = DateUtil.getDateFormat(System.currentTimeMillis());
            File file = this.b;
            this.c = new File(file, "BIO_API_" + dateFormat + Constants.n);
            File file2 = this.b;
            this.d = new File(file2, "BIO_METHOD_" + dateFormat + Constants.n);
            File file3 = this.b;
            this.e = new File(file3, "BIO_CLICK_" + dateFormat + Constants.n);
            File file4 = this.b;
            this.f = new File(file4, "BIO_NET_" + dateFormat + Constants.n);
            File file5 = this.b;
            this.g = new File(file5, "BIO_EVENT_" + dateFormat + Constants.n);
            return;
        }
        throw new BioIllegalArgumentException();
    }

    public WatchLogThread(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void a() {
        try {
            a(this.f1018a.poll(50, TimeUnit.SECONDS));
        } catch (InterruptedException e2) {
            BioLog.w((Throwable) e2);
        }
    }

    public void addThreadItem(BioSysBehavior bioSysBehavior) {
        try {
            this.f1018a.add(bioSysBehavior);
        } catch (Throwable th) {
            BioLog.e(th.toString());
        }
    }

    private void a(BioSysBehavior bioSysBehavior) {
        if (bioSysBehavior != null) {
            HashMap<String, String> ext = bioSysBehavior.getExt();
            StringBuilder sb = new StringBuilder();
            if (!ext.isEmpty()) {
                for (String next : ext.keySet()) {
                    sb.append("[key:" + next + ", value:" + ext.get(next) + Operators.ARRAY_END_STR);
                }
            }
            BioSysBehaviorType type = bioSysBehavior.getType();
            a(type, bioSysBehavior.getMsg() + sb.toString());
        }
    }

    private void a(BioSysBehaviorType bioSysBehaviorType, String str) {
        switch (a.f1020a[bioSysBehaviorType.ordinal()]) {
            case 1:
                FileUtil.saveContent(this.c, str, true);
                return;
            case 2:
                FileUtil.saveContent(this.e, str, true);
                return;
            case 3:
                FileUtil.saveContent(this.f, str, true);
                return;
            case 4:
                FileUtil.saveContent(this.g, str, true);
                return;
            case 5:
                FileUtil.saveContent(this.d, str, true);
                return;
            default:
                return;
        }
    }
}
