package cn.fraudmetrix.octopus.aspirit.utils;

import android.content.Context;
import android.os.Process;
import cn.fraudmetrix.octopus.aspirit.bean.a.a;
import cn.fraudmetrix.octopus.aspirit.c.c;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class g implements Thread.UncaughtExceptionHandler {
    private static g g;

    /* renamed from: a  reason: collision with root package name */
    e f655a = null;
    private Thread.UncaughtExceptionHandler b;
    private Context c;
    private Map<String, String> d = new HashMap();
    private SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private String f = getClass().getSimpleName();

    private g() {
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (g == null) {
                g = new g();
            }
            gVar = g;
        }
        return gVar;
    }

    private boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        cn.fraudmetrix.octopus.aspirit.bean.g c2 = c.d().c();
        c2.stage += ",crash";
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        do {
            th.printStackTrace(printWriter);
            th = th.getCause();
        } while (th != null);
        printWriter.close();
        a aVar = new a();
        aVar.message = stringWriter.toString();
        aVar.code = "10000";
        aVar.cost_time = this.e.format(new Date());
        aVar.url = OctopusManager.b().h();
        c.d().a(aVar);
        h.a("crash:" + ((String) this.f655a.b(OctopusConstants.k, "")));
        return true;
    }

    public void a(Context context) {
        this.c = context;
        this.b = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        int i;
        this.f655a = new e(this.c);
        a(th);
        long longValue = ((Long) this.f655a.b(OctopusConstants.q, 0L)).longValue();
        this.f655a.a(OctopusConstants.q, Long.valueOf(System.currentTimeMillis()));
        if (System.currentTimeMillis() - longValue < 10000) {
            Process.killProcess(Process.myPid());
            i = 1;
        } else if (this.b != null) {
            this.b.uncaughtException(thread, th);
            return;
        } else {
            Process.killProcess(Process.myPid());
            i = 0;
        }
        System.exit(i);
    }
}
