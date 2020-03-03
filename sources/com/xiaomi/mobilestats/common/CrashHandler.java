package com.xiaomi.mobilestats.common;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mobilestats.data.BasicStoreTools;
import com.xiaomi.mobilestats.data.CrashMD5;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.List;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler t;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public String u;
    /* access modifiers changed from: private */
    public Thread.UncaughtExceptionHandler v;

    private CrashHandler() {
    }

    /* access modifiers changed from: private */
    public String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        String obj = stringWriter.toString();
        int length = TextUtils.isEmpty(obj) ? 0 : obj.length();
        if (length <= 2048) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(obj.substring(0, 2048));
        if (th instanceof StackOverflowError) {
            sb.append("\n\t... \n");
            sb.append(obj.substring((length - 2048) - 2048, length));
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(java.lang.String r9) {
        /*
            r8 = this;
            android.content.Context r0 = r8.mContext
            r1 = 0
            if (r0 == 0) goto L_0x0084
            boolean r0 = com.xiaomi.mobilestats.common.StrUtil.isEmpty(r9)
            if (r0 == 0) goto L_0x000d
            goto L_0x0084
        L_0x000d:
            com.xiaomi.mobilestats.data.BasicStoreTools r0 = com.xiaomi.mobilestats.data.BasicStoreTools.getInstance()
            android.content.Context r2 = r8.mContext
            java.lang.String r0 = r0.getCrashMD5List(r2)
            r2 = 0
            boolean r3 = com.xiaomi.mobilestats.common.StrUtil.isEmpty(r0)
            if (r3 != 0) goto L_0x0029
            java.lang.Class<com.xiaomi.mobilestats.data.CrashMD5> r3 = com.xiaomi.mobilestats.data.CrashMD5.class
            java.util.List r0 = com.xiaomi.mobilestats.common.JSONUtil.parseList(r0, r3)     // Catch:{ Exception -> 0x0025 }
            goto L_0x002a
        L_0x0025:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0029:
            r0 = r2
        L_0x002a:
            r2 = 1
            if (r0 == 0) goto L_0x0066
            int r3 = r0.size()
            if (r3 <= 0) goto L_0x0066
            java.util.Iterator r3 = r0.iterator()
        L_0x0037:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x006b
            java.lang.Object r4 = r3.next()
            com.xiaomi.mobilestats.data.CrashMD5 r4 = (com.xiaomi.mobilestats.data.CrashMD5) r4
            if (r4 == 0) goto L_0x0037
            java.lang.String r5 = r4.md5
            int r6 = r4.crashNum
            boolean r7 = com.xiaomi.mobilestats.common.StrUtil.isEmpty(r5)
            if (r7 != 0) goto L_0x0037
            boolean r5 = r5.equals(r9)
            if (r5 == 0) goto L_0x0037
            int r6 = r6 + r2
            r4.crashNum = r6
            java.lang.String r9 = com.xiaomi.mobilestats.common.JSONUtil.format(r0)
            com.xiaomi.mobilestats.data.BasicStoreTools r0 = com.xiaomi.mobilestats.data.BasicStoreTools.getInstance()
            android.content.Context r1 = r8.mContext
            r0.setCrashMD5List(r1, r9)
            return r2
        L_0x0066:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x006b:
            com.xiaomi.mobilestats.data.CrashMD5 r3 = new com.xiaomi.mobilestats.data.CrashMD5
            r3.<init>()
            r3.md5 = r9
            r3.crashNum = r2
            r0.add(r3)
            java.lang.String r9 = com.xiaomi.mobilestats.common.JSONUtil.format(r0)
            com.xiaomi.mobilestats.data.BasicStoreTools r0 = com.xiaomi.mobilestats.data.BasicStoreTools.getInstance()
            android.content.Context r2 = r8.mContext
            r0.setCrashMD5List(r2, r9)
        L_0x0084:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CrashHandler.b(java.lang.String):boolean");
    }

    public static synchronized CrashHandler getInstance() {
        CrashHandler crashHandler;
        synchronized (CrashHandler.class) {
            if (t == null) {
                t = new CrashHandler();
            }
            crashHandler = t;
        }
        return crashHandler;
    }

    public void clearCrashMD5List() {
        if (this.mContext != null) {
            BasicStoreTools.getInstance().setCrashMD5List(this.mContext, "");
        }
    }

    public int getCrashNumByMD5(String str) {
        if (this.mContext == null) {
            return 0;
        }
        try {
            List<CrashMD5> parseList = JSONUtil.parseList(BasicStoreTools.getInstance().getCrashMD5List(this.mContext), CrashMD5.class);
            if (parseList != null && parseList.size() > 0) {
                for (CrashMD5 crashMD5 : parseList) {
                    String str2 = crashMD5.md5;
                    int i = crashMD5.crashNum;
                    if (!StrUtil.isEmpty(str2) && str2.equals(str)) {
                        return i;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void init(Context context) {
        this.mContext = context;
        this.v = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        new a(this, th, thread).start();
    }
}
