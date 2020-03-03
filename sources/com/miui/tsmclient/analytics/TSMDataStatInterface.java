package com.miui.tsmclient.analytics;

import android.content.Context;
import com.miui.tsmclient.util.EnvironmentConfig;

public class TSMDataStatInterface {
    private static volatile TSMDataStatInterface sInstance;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public TSMDataStatManager mDataStatManger = new TSMDataStatManager();

    private TSMDataStatInterface() {
    }

    public static TSMDataStatInterface getInstance() {
        if (sInstance == null) {
            synchronized (TSMDataStatInterface.class) {
                if (sInstance == null) {
                    sInstance = new TSMDataStatInterface();
                    sInstance.init(EnvironmentConfig.getContext());
                }
            }
        }
        return sInstance;
    }

    private void init(Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
            return;
        }
        throw new IllegalArgumentException("context can not be null");
    }

    public void recordCountEvent(final int i) {
        if (this.mContext != null) {
            new Thread(new Runnable() {
                public void run() {
                    TSMDataStatInterface.this.mDataStatManger.recordCountEvent(TSMDataStatInterface.this.mContext, i);
                }
            }).start();
            return;
        }
        throw new IllegalArgumentException("context can not be null");
    }

    public void recordStringEvent(final int i, final String str) {
        if (this.mContext != null) {
            new Thread(new Runnable() {
                public void run() {
                    TSMDataStatInterface.this.mDataStatManger.recordStringEvent(TSMDataStatInterface.this.mContext, i, str);
                }
            }).start();
            return;
        }
        throw new IllegalArgumentException("context can not be null");
    }
}
