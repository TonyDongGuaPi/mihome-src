package cn.fraudmetrix.octopus.aspirit.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import cn.fraudmetrix.octopus.aspirit.c.c;
import cn.fraudmetrix.octopus.aspirit.main.a;

public class OctopusIntentService extends AbstractOctopusBaseService {
    private Context b;
    private int c = 0;

    public OctopusIntentService() {
        super("OctopusIntentService");
    }

    public OctopusIntentService(Context context) {
        super("OctopusIntentService");
        this.b = context;
    }

    @RequiresApi(api = 16)
    public void onCreate() {
        super.onCreate();
        this.b = this;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        super.onHandleIntent(intent);
        a.C0022a.a();
        c.b.a();
    }

    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
