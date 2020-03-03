package com.xiaomi.printer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.print.PrinterId;
import android.print.PrinterInfo;
import android.printservice.PrintJob;
import android.printservice.PrintService;
import android.printservice.PrinterDiscoverySession;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.printer.IMiPrinter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(19)
public class MiPrintService extends PrintService {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12604a = "MiPrintService";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private ArrayList<IMiPrinter> e = new ArrayList<>();
    protected ArrayList<Runnable> mOnReadyRunableList = new ArrayList<>();

    public void onCreate() {
        super.onCreate();
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new SmartHomeDeviceManager.IsDeviceReadyCallback() {
                    public void onDeviceReady(List<Device> list) {
                        Iterator<Runnable> it = MiPrintService.this.mOnReadyRunableList.iterator();
                        while (it.hasNext()) {
                            it.next().run();
                        }
                        MiPrintService.this.mOnReadyRunableList = null;
                    }
                });
            }
        });
        if (this.mOnReadyRunableList == null) {
            a();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrintService.this.a();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            for (String str : applicationInfo.metaData.keySet()) {
                if (str != null && str.startsWith("MIPRINT_")) {
                    String string = applicationInfo.metaData.getString(str);
                    try {
                        IMiPrinter iMiPrinter = (IMiPrinter) Class.forName(string).newInstance();
                        if (iMiPrinter != null) {
                            this.e.add(iMiPrinter);
                        }
                    } catch (Exception e2) {
                        Log.e(f12604a, string, e2);
                    }
                }
            }
            LogUtil.c(f12604a, "getDevice  " + this.e);
        } catch (Exception e3) {
            Log.e(f12604a, "GET_META_DATA", e3);
        }
    }

    /* access modifiers changed from: protected */
    public void onConnected() {
        super.onConnected();
        LogUtil.c(f12604a, "onConnected");
        if (this.mOnReadyRunableList == null) {
            b();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrintService.this.b();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.e.get(size);
            try {
                iMiPrinter.b(this);
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public PrinterDiscoverySession onCreatePrinterDiscoverySession() {
        return new MiPrinterDiscoverySession(this, this.e);
    }

    /* access modifiers changed from: protected */
    public void onRequestCancelPrintJob(final PrintJob printJob) {
        LogUtil.c(f12604a, "onRequestCancelPrintJob  " + printJob);
        if (this.mOnReadyRunableList == null) {
            onRequestCancelPrintJob(new PrintJobProxy(printJob));
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrintService.this.onRequestCancelPrintJob(new PrintJobProxy(printJob));
                }
            });
        }
    }

    public void onRequestCancelPrintJob(PrintJobProxy printJobProxy) {
        PrinterId printerId = printJobProxy.getInfo().getPrinterId();
        for (int size = this.e.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.e.get(size);
            try {
                List<PrinterInfo> a2 = iMiPrinter.a(this);
                if (a2 != null) {
                    Iterator<PrinterInfo> it = a2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            PrinterInfo next = it.next();
                            if (next != null && next.getId().equals(printerId)) {
                                iMiPrinter.a((PrintService) this, next, printJobProxy);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPrintJobQueued(final PrintJob printJob) {
        LogUtil.c(f12604a, "onPrintJobQueued  " + printJob);
        if (this.mOnReadyRunableList == null) {
            onPrintJobQueued(new PrintJobProxy(printJob));
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrintService.this.onPrintJobQueued(new PrintJobProxy(printJob));
                }
            });
        }
    }

    public void onPrintJobQueued(PrintJobProxy printJobProxy) {
        PrinterId printerId = printJobProxy.getInfo().getPrinterId();
        for (int size = this.e.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.e.get(size);
            try {
                List<PrinterInfo> a2 = iMiPrinter.a(this);
                if (a2 != null) {
                    Iterator<PrinterInfo> it = a2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            PrinterInfo next = it.next();
                            if (next != null && next.getId().equals(printerId)) {
                                iMiPrinter.b(this, next, printJobProxy);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDisconnected() {
        super.onDisconnected();
        LogUtil.c(f12604a, "onDisconnected");
        if (this.mOnReadyRunableList == null) {
            c();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrintService.this.c();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.e.get(size);
            try {
                iMiPrinter.c(this);
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LogUtil.c(f12604a, ActivityInfo.TYPE_STR_ONDESTROY);
        this.e.clear();
    }
}
