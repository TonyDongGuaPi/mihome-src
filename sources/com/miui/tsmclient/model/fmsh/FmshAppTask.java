package com.miui.tsmclient.model.fmsh;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import cn.com.fmsh.nfcos.client.service.xm.CardAppManager;
import com.miui.tsmclient.model.BaseAppTask;
import com.miui.tsmclient.util.LogUtils;

public abstract class FmshAppTask extends BaseAppTask {
    private static final String FMSH_SERVICE_ACTION = "cn.com.fmsh.nfcos.client.service.xm.NfcosService4xm";
    private CardAppManager mCardAppManager = null;

    public FmshAppTask(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void doServiceConnected(IBinder iBinder) {
        this.mCardAppManager = CardAppManager.Stub.asInterface(iBinder);
        super.doServiceConnected(iBinder);
    }

    /* access modifiers changed from: protected */
    public synchronized void bindAppService() {
        Intent intent = new Intent(FMSH_SERVICE_ACTION);
        intent.setPackage(this.mContext.getPackageName());
        boolean bindService = this.mContext.bindService(intent, this, 1);
        LogUtils.d("FmshAppTask#bindAppService() called, execute result is:" + bindService);
    }

    /* access modifiers changed from: protected */
    public synchronized void unBindAppService() {
        super.unBindAppService();
        this.mCardAppManager = null;
    }

    public CardAppManager getService() {
        return this.mCardAppManager;
    }
}
