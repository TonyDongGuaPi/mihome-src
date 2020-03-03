package cn.fraudmetrix.octopus.aspirit.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;
import cn.fraudmetrix.octopus.aspirit.R;
import cn.fraudmetrix.octopus.aspirit.activity.OctopusMainActivity;
import cn.fraudmetrix.octopus.aspirit.bean.OctopusParam;
import cn.fraudmetrix.octopus.aspirit.c.c;
import cn.fraudmetrix.octopus.aspirit.main.a;
import cn.fraudmetrix.octopus.aspirit.service.OctopusClearService;
import cn.fraudmetrix.octopus.aspirit.service.OctopusIntentService;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.b;
import cn.fraudmetrix.octopus.aspirit.utils.f;
import cn.fraudmetrix.octopus.aspirit.utils.g;
import com.tencent.smtt.sdk.QbSdk;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class OctopusManager {
    private static OctopusManager f;

    /* renamed from: a  reason: collision with root package name */
    public OctopusParam f644a;
    private final String b = "1.4.0.2";
    private String c;
    private String d;
    private String e = "https://api.shujumohe.com/";
    private int g = R.color.color_white;
    private int h = R.mipmap.img_navigation;
    private int i = R.color.color_white;
    private int j = R.color.color_font_grayest;
    private int k = 14;
    private int l = 17;
    private boolean m = true;
    private boolean n = true;
    private OctopusTaskCallBack o;
    private Context p;
    private ScheduledExecutorService q = new ScheduledThreadPoolExecutor(3);

    private void a(Activity activity, String str, OctopusParam octopusParam, OctopusTaskCallBack octopusTaskCallBack) {
        a(activity, str, (String) null, octopusParam, octopusTaskCallBack);
    }

    private void a(Context context, String str) {
        Toast.makeText(context, str, 1).show();
    }

    public static synchronized OctopusManager b() {
        OctopusManager octopusManager;
        synchronized (OctopusManager.class) {
            if (f == null) {
                f = new OctopusManager();
            }
            octopusManager = f;
        }
        return octopusManager;
    }

    private boolean b(Activity activity, String str, OctopusParam octopusParam, OctopusTaskCallBack octopusTaskCallBack) {
        int i2;
        String string;
        if (activity == null) {
            return false;
        }
        if (this.c == null || "".equals(this.c)) {
            i2 = R.string.octopus_partnercode_emp;
            string = activity.getString(i2);
        } else if (octopusParam != null && octopusParam.passbackarams.length() > 512) {
            string = activity.getResources().getString(R.string.octopus_pass_param_toolong);
        } else if (str == null || "".equals(str)) {
            i2 = R.string.octopus_channelcode_emp;
            string = activity.getString(i2);
        } else if (octopusTaskCallBack == null) {
            i2 = R.string.octopus_callback_emp;
            string = activity.getString(i2);
        } else {
            if (this.e.contains("https:")) {
                f.a();
            }
            OctopusConstants.z = 0;
            b.a().c();
            g.a().a(activity.getApplicationContext());
            return true;
        }
        a(activity, string);
        return false;
    }

    public ScheduledExecutorService a() {
        return this.q;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a(Activity activity, String str, String str2, OctopusParam octopusParam, OctopusTaskCallBack octopusTaskCallBack) {
        if (b(activity, str, octopusParam, octopusTaskCallBack)) {
            this.f644a = octopusParam;
            this.o = octopusTaskCallBack;
            Intent intent = new Intent(activity, OctopusMainActivity.class);
            intent.putExtra(OctopusConstants.d, str);
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra(OctopusConstants.h, str2);
            }
            activity.startActivity(intent);
        }
    }

    public void a(Context context) {
        Intent intent = new Intent(context, OctopusIntentService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
            context.startForegroundService(new Intent(context, OctopusClearService.class));
            return;
        }
        context.startService(intent);
    }

    public void a(Context context, String str, String str2) {
        this.d = str2;
        this.c = str;
        this.p = context;
        QbSdk.initX5Environment(context.getApplicationContext(), (QbSdk.PreInitCallback) null);
        f();
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public void b(int i2) {
        this.h = i2;
    }

    public void b(boolean z) {
        this.m = z;
    }

    public Context c() {
        return this.p;
    }

    public void c(int i2) {
        this.j = i2;
    }

    public String d() {
        return this.c;
    }

    public void d(int i2) {
        this.k = i2;
    }

    public String e() {
        return this.d;
    }

    public void e(int i2) {
        this.l = i2;
    }

    public void f() {
        a().execute(new Runnable() {
            public void run() {
                a.C0022a.a();
                c.b.a();
            }
        });
    }

    public void f(int i2) {
        this.i = i2;
    }

    public String g() {
        return this.e;
    }

    public String h() {
        return "1.4.0.2";
    }

    public int i() {
        return this.g;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.j;
    }

    public int l() {
        return this.k;
    }

    public int m() {
        return this.l;
    }

    public int n() {
        return this.i;
    }

    public boolean o() {
        return this.n;
    }

    public OctopusTaskCallBack p() {
        return this.o;
    }

    public boolean q() {
        return this.m;
    }
}
