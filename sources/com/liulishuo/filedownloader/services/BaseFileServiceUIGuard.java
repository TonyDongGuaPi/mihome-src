package com.liulishuo.filedownloader.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.liulishuo.filedownloader.FileDownloadEventPool;
import com.liulishuo.filedownloader.IFileDownloadServiceProxy;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseFileServiceUIGuard<CALLBACK extends Binder, INTERFACE extends IInterface> implements ServiceConnection, IFileDownloadServiceProxy {

    /* renamed from: a  reason: collision with root package name */
    private final CALLBACK f6446a;
    private volatile INTERFACE b;
    private final Class<?> c;
    private final HashMap<String, Object> d = new HashMap<>();
    private final List<Context> e = new ArrayList();
    private final ArrayList<Runnable> f = new ArrayList<>();

    /* access modifiers changed from: protected */
    public abstract void a(INTERFACE interfaceR, CALLBACK callback) throws RemoteException;

    /* access modifiers changed from: protected */
    public abstract CALLBACK b();

    /* access modifiers changed from: protected */
    public abstract INTERFACE b(IBinder iBinder);

    /* access modifiers changed from: protected */
    public abstract void b(INTERFACE interfaceR, CALLBACK callback) throws RemoteException;

    /* access modifiers changed from: protected */
    public CALLBACK g() {
        return this.f6446a;
    }

    /* access modifiers changed from: protected */
    public INTERFACE h() {
        return this.b;
    }

    protected BaseFileServiceUIGuard(Class<?> cls) {
        this.c = cls;
        this.f6446a = b();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.b = b(iBinder);
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "onServiceConnected %s %s", componentName, this.b);
        }
        try {
            b(this.b, this.f6446a);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        this.f.clear();
        for (Runnable run : (List) this.f.clone()) {
            run.run();
        }
        FileDownloadEventPool.a().b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.ConnectStatus.connected, this.c));
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "onServiceDisconnected %s %s", componentName, this.b);
        }
        b(true);
    }

    private void b(boolean z) {
        if (!z && this.b != null) {
            try {
                a(this.b, this.f6446a);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "release connect resources %s", this.b);
        }
        this.b = null;
        FileDownloadEventPool.a().b(new DownloadServiceConnectChangedEvent(z ? DownloadServiceConnectChangedEvent.ConnectStatus.lost : DownloadServiceConnectChangedEvent.ConnectStatus.disconnected, this.c));
    }

    public void a(Context context) {
        a(context, (Runnable) null);
    }

    public void a(Context context, Runnable runnable) {
        if (!FileDownloadUtils.a(context)) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "bindStartByContext %s", context.getClass().getSimpleName());
            }
            Intent intent = new Intent(context, this.c);
            if (runnable != null && !this.f.contains(runnable)) {
                this.f.add(runnable);
            }
            if (!this.e.contains(context)) {
                this.e.add(context);
            }
            context.bindService(intent, this, 1);
            context.startService(intent);
            return;
        }
        throw new IllegalStateException("Fatal-Exception: You can't bind the FileDownloadService in :filedownloader process.\n It's the invalid operation, and is likely to cause unexpected problems.\n Maybe you want to use non-separate process mode for FileDownloader, More detail about non-separate mode, please move to wiki manually: https://github.com/lingochamp/FileDownloader/wiki/filedownloader.properties");
    }

    public void b(Context context) {
        if (this.e.contains(context)) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "unbindByContext %s", context);
            }
            this.e.remove(context);
            if (this.e.isEmpty()) {
                b(false);
            }
            Intent intent = new Intent(context, this.c);
            context.unbindService(this);
            context.stopService(intent);
        }
    }

    public void c(Context context) {
        context.startService(new Intent(context, this.c));
    }

    /* access modifiers changed from: protected */
    public Object a(String str) {
        return this.d.remove(str);
    }

    /* access modifiers changed from: protected */
    public String a(Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        this.d.put(obj2, obj);
        return obj2;
    }

    public boolean e() {
        return h() != null;
    }
}
