package com.xiaomi.smarthome.core.server;

import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.IServerHandle;

public abstract class CoreAsyncTask extends IServerHandle.Stub implements Runnable {
    public abstract void onCancel();

    public void execute() {
        CoreManager.a().a(this);
    }

    public final void cancel() throws RemoteException {
        onCancel();
    }
}
