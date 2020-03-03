package com.alipay.mobile.common.logging;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.ProcessInfo;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;

public class ProcessInfoImpl implements ProcessInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f945a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;

    public ProcessInfoImpl(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            this.f945a = a(context);
            this.c = packageName;
            this.d = packageName + Operators.CONDITION_IF_MIDDLE + ProcessInfo.ALIAS_PUSH;
            this.e = packageName + Operators.CONDITION_IF_MIDDLE + ProcessInfo.ALIAS_TOOLS;
            this.f = this.c.equalsIgnoreCase(this.f945a);
            this.g = this.d.equalsIgnoreCase(this.f945a);
            this.h = this.e.equalsIgnoreCase(this.f945a);
            if (this.f) {
                this.b = "main";
            } else if (this.g) {
                this.b = ProcessInfo.ALIAS_PUSH;
            } else if (this.h) {
                this.b = ProcessInfo.ALIAS_TOOLS;
            } else {
                Log.e("logging.ProcessInfoImpl", "unknown process: " + this.f945a);
                if (TextUtils.isEmpty(this.f945a)) {
                    this.b = "unknown";
                    return;
                }
                String str = this.f945a;
                this.b = str.replace(packageName + Operators.CONDITION_IF_MIDDLE, "");
            }
        }
    }

    public String getProcessName() {
        return this.f945a;
    }

    public String getProcessAlias() {
        return this.b;
    }

    public int getProcessId() {
        return Process.myPid();
    }

    public boolean isMainProcess() {
        return this.f;
    }

    public boolean isPushProcess() {
        return this.g;
    }

    public boolean isToolsProcess() {
        return this.h;
    }

    public String getMainProcessName() {
        return this.c;
    }

    public String getPushProcessName() {
        return this.d;
    }

    public String getToolsProcessName() {
        return this.e;
    }

    private String a(Context context) {
        String str;
        try {
            Class<?> cls = Class.forName("android.ddm.DdmHandleAppName");
            str = (String) cls.getDeclaredMethod("getAppName", new Class[0]).invoke(cls, new Object[0]);
        } catch (Throwable th) {
            Log.e("logging.ProcessInfoImpl", "getCurProcessName", th);
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            try {
                int processId = getProcessId();
                Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ActivityManager.RunningAppProcessInfo next = it.next();
                    if (next.pid == processId) {
                        str = next.processName;
                        break;
                    }
                }
            } catch (Throwable th2) {
                Log.e("logging.ProcessInfoImpl", "getCurProcessName", th2);
            }
        }
        return str == null ? "" : str;
    }
}
