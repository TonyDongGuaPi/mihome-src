package cn.fraudmetrix.octopus.aspirit.service;

import android.content.Intent;
import android.support.annotation.Nullable;

public class OctopusClearService extends AbstractOctopusBaseService {
    public OctopusClearService() {
        super("OctopusClearService");
    }

    public OctopusClearService(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(@Nullable Intent intent) {
        super.onHandleIntent(intent);
        stopForeground(true);
    }

    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
