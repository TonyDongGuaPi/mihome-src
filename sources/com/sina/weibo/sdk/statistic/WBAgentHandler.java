package com.sina.weibo.sdk.statistic;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

class WBAgentHandler {

    /* renamed from: a  reason: collision with root package name */
    private static WBAgentHandler f8837a = null;
    private static CopyOnWriteArrayList<PageLog> b = null;
    private static Map<String, PageLog> c = null;
    private static Timer d = null;
    private static Timer e = null;
    private static int f = 5;

    public static synchronized WBAgentHandler a() {
        WBAgentHandler wBAgentHandler;
        synchronized (WBAgentHandler.class) {
            if (f8837a == null) {
                f8837a = new WBAgentHandler();
            }
            wBAgentHandler = f8837a;
        }
        return wBAgentHandler;
    }

    private WBAgentHandler() {
        b = new CopyOnWriteArrayList<>();
        c = new HashMap();
        LogUtil.b(WBAgent.f8835a, "init handler");
    }

    public void a(String str) {
        if (!StatisticConfig.b) {
            PageLog pageLog = new PageLog(str);
            pageLog.a(LogType.FRAGMENT);
            synchronized (c) {
                c.put(str, pageLog);
            }
            LogUtil.a(WBAgent.f8835a, str + ", " + (pageLog.j() / 1000));
        }
    }

    public void b(String str) {
        if (!StatisticConfig.b) {
            if (c.containsKey(str)) {
                PageLog pageLog = c.get(str);
                pageLog.a(System.currentTimeMillis() - pageLog.j());
                synchronized (b) {
                    b.add(pageLog);
                }
                synchronized (c) {
                    c.remove(str);
                }
                LogUtil.a(WBAgent.f8835a, str + ", " + (pageLog.j() / 1000) + ", " + (pageLog.l() / 1000));
            } else {
                LogUtil.c(WBAgent.f8835a, "please call onPageStart before onPageEnd");
            }
            if (b.size() >= f) {
                synchronized (b) {
                    a(b);
                    b.clear();
                }
            }
        }
    }

    public void a(Context context) {
        if (LogReport.a() == null) {
            LogReport.a(context.getPackageName());
        }
        if (d == null) {
            d = a(context, 500, StatisticConfig.a());
        }
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        a(context, currentTimeMillis);
        if (StatisticConfig.b) {
            PageLog pageLog = new PageLog(name, currentTimeMillis);
            pageLog.a(LogType.ACTIVITY);
            synchronized (c) {
                c.put(name, pageLog);
            }
        }
        LogUtil.a(WBAgent.f8835a, name + ", " + (currentTimeMillis / 1000));
    }

    public void b(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        LogUtil.b(WBAgent.f8835a, "update last page endtime:" + (currentTimeMillis / 1000));
        PageLog.a(context, (String) null, 0L, Long.valueOf(currentTimeMillis));
        if (StatisticConfig.b) {
            if (c.containsKey(name)) {
                PageLog pageLog = c.get(name);
                pageLog.a(currentTimeMillis - pageLog.j());
                synchronized (b) {
                    b.add(pageLog);
                }
                synchronized (c) {
                    c.remove(name);
                }
                LogUtil.a(WBAgent.f8835a, name + ", " + (pageLog.j() / 1000) + ", " + (pageLog.l() / 1000));
            } else {
                LogUtil.c(WBAgent.f8835a, "please call onResume before onPause");
            }
            if (b.size() >= f) {
                synchronized (b) {
                    a(b);
                    b.clear();
                }
            }
        }
        f(context);
    }

    public void a(String str, String str2, Map<String, String> map) {
        EventLog eventLog = new EventLog(str, str2, map);
        eventLog.a(LogType.EVENT);
        synchronized (b) {
            b.add(eventLog);
        }
        if (map == null) {
            LogUtil.a(WBAgent.f8835a, "event--- page:" + str + " ,event name:" + str2);
        } else {
            LogUtil.a(WBAgent.f8835a, "event--- page:" + str + " ,event name:" + str2 + " ,extend:" + map.toString());
        }
        if (b.size() >= f) {
            synchronized (b) {
                a(b);
                b.clear();
            }
        }
    }

    public void c(final Context context) {
        long currentTimeMillis = System.currentTimeMillis() - LogReport.a(context);
        if (LogReport.a(context) <= 0 || currentTimeMillis >= 30000) {
            WBAgentExecutor.a(new Runnable() {
                public void run() {
                    LogReport.a(context, WBAgentHandler.this.c());
                }
            });
            return;
        }
        a(context, 30000 - currentTimeMillis, 0);
    }

    public void d(Context context) {
        f(context);
    }

    private void f(Context context) {
        if (g(context)) {
            synchronized (b) {
                a(b);
                b.clear();
            }
        }
    }

    private boolean g(Context context) {
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (next.processName.equals(context.getPackageName())) {
                if (next.importance == 400) {
                    LogUtil.b(WBAgent.f8835a, "后台:" + next.processName);
                    return true;
                }
                LogUtil.b(WBAgent.f8835a, "前台:" + next.processName);
                return false;
            }
        }
        return false;
    }

    public void b() {
        LogUtil.b(WBAgent.f8835a, "save applogs and close timer and shutdown thread executor");
        synchronized (b) {
            a(b);
        }
        f8837a = null;
        d();
        WBAgentExecutor.a();
    }

    private void a(Context context, long j) {
        if (PageLog.a(context, j)) {
            PageLog pageLog = new PageLog(context);
            pageLog.a(LogType.SESSION_END);
            PageLog pageLog2 = new PageLog(context, j);
            pageLog2.a(LogType.SESSION_START);
            synchronized (b) {
                if (pageLog.k() > 0) {
                    b.add(pageLog);
                } else {
                    LogUtil.a(WBAgent.f8835a, "is a new install");
                }
                b.add(pageLog2);
            }
            LogUtil.a(WBAgent.f8835a, "last session--- starttime:" + pageLog.j() + " ,endtime:" + pageLog.k());
            StringBuilder sb = new StringBuilder();
            sb.append("is a new session--- starttime:");
            sb.append(pageLog2.j());
            LogUtil.a(WBAgent.f8835a, sb.toString());
            return;
        }
        LogUtil.b(WBAgent.f8835a, "is not a new session");
    }

    private synchronized void a(CopyOnWriteArrayList<PageLog> copyOnWriteArrayList) {
        final String a2 = LogBuilder.a(copyOnWriteArrayList);
        WBAgentExecutor.a(new Runnable() {
            public void run() {
                LogFileUtil.a(LogFileUtil.b(LogFileUtil.f8831a), a2, true);
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized String c() {
        String str;
        String a2;
        str = "";
        if (b.size() > 0) {
            synchronized (b) {
                a2 = LogBuilder.a(b);
                b.clear();
            }
            str = a2;
        }
        return str;
    }

    private Timer a(final Context context, long j, long j2) {
        Timer timer = new Timer();
        AnonymousClass3 r1 = new TimerTask() {
            public void run() {
                LogReport.a(context, WBAgentHandler.this.c());
            }
        };
        if (j2 == 0) {
            timer.schedule(r1, j);
        } else {
            timer.schedule(r1, j, j2);
        }
        return timer;
    }

    private void d() {
        if (d != null) {
            d.cancel();
            d = null;
        }
    }

    public void a(final Context context, final String str, Map<String, String> map) {
        try {
            final AdEventLog adEventLog = new AdEventLog();
            adEventLog.a(LogType.APP_AD_START);
            if (e(context)) {
                adEventLog.d("1");
            }
            adEventLog.c(MD5.a(h(context)));
            adEventLog.b(System.currentTimeMillis());
            adEventLog.a(map);
            String b2 = Utility.b(context, str);
            if (TextUtils.isEmpty(b2)) {
                AnonymousClass4 r6 = new TimerTask() {
                    public void run() {
                        adEventLog.a(Utility.b(context, str));
                        WBAgentHandler.this.a(context, adEventLog);
                    }
                };
                e = new Timer();
                e.schedule(r6, 5000);
                return;
            }
            adEventLog.a(b2);
            a(context, adEventLog);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static String h(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(final Context context, AdEventLog adEventLog) {
        b.add(adEventLog);
        WBAgentExecutor.a(new Runnable() {
            public void run() {
                LogReport.a(context, WBAgentHandler.this.c());
            }
        });
    }

    public static boolean e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(WBConstants.B, 0);
        boolean z = sharedPreferences.getBoolean(WBConstants.C, true);
        if (z) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(WBConstants.C, false);
            edit.commit();
        }
        return z;
    }
}
