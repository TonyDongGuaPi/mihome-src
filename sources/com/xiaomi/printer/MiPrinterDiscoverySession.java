package com.xiaomi.printer;

import android.annotation.TargetApi;
import android.print.PrinterId;
import android.print.PrinterInfo;
import android.printservice.PrintService;
import android.printservice.PrinterDiscoverySession;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.printer.PrinterControl;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.printer.IDeviceDiscovery;
import com.xiaomi.smarthome.printer.IMiPrinter;
import com.xiaomi.smarthome.printer.PrinterDiscoverySessionProxy;
import java.util.ArrayList;
import java.util.List;

@TargetApi(19)
public class MiPrinterDiscoverySession extends PrinterDiscoverySession implements PrinterControl.OnAddPrinterListener, IDeviceDiscovery {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12612a = "MiPrinterDiscoverySession";
    private final MiPrintService b;
    private final ArrayList<IMiPrinter> c;
    private IDeviceDiscovery d;
    private PrinterDiscoverySessionProxy e = new PrinterDiscoverySessionProxy(this, this);
    private PrinterControl.OnAddPrinterListener f;

    public MiPrinterDiscoverySession(final MiPrintService miPrintService, final ArrayList<IMiPrinter> arrayList) {
        this.b = miPrintService;
        this.c = arrayList;
        if (miPrintService.mOnReadyRunableList == null) {
            a(miPrintService, arrayList);
        } else {
            miPrintService.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.a(miPrintService, (ArrayList<IMiPrinter>) arrayList);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(MiPrintService miPrintService, ArrayList<IMiPrinter> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = arrayList.get(size);
            try {
                iMiPrinter.d(miPrintService);
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void onStartPrinterDiscovery(final List<PrinterId> list) {
        LogUtil.c(f12612a, "onStartPrinterDiscovery  " + list);
        if (this.b.mOnReadyRunableList == null) {
            b(list);
        } else {
            this.b.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.b((List<PrinterId>) list);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(List<PrinterId> list) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.c.get(size);
            try {
                iMiPrinter.a(list, (PrintService) this.b, this.e, a());
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void a(PrinterControl.OnAddPrinterListener onAddPrinterListener) {
        this.f = onAddPrinterListener;
    }

    public void onStopPrinterDiscovery() {
        LogUtil.c(f12612a, "onStopPrinterDiscovery");
        if (this.b.mOnReadyRunableList == null) {
            b();
        } else {
            this.b.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.b();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.c.get(size);
            try {
                iMiPrinter.a(this.b, this.e);
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void onValidatePrinters(final List<PrinterId> list) {
        LogUtil.c(f12612a, "onValidatePrinters  " + list);
        if (this.b.mOnReadyRunableList == null) {
            c(list);
        } else {
            this.b.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.c(list);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c(List<PrinterId> list) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.c.get(size);
            try {
                List<PrinterInfo> a2 = iMiPrinter.a(this.b);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < a2.size(); i++) {
                    PrinterInfo printerInfo = a2.get(size);
                    if (printerInfo != null && list.contains(printerInfo.getId())) {
                        arrayList.add(printerInfo);
                    }
                }
                iMiPrinter.a((PrintService) this.b, this.e, (List<PrinterInfo>) arrayList);
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void onStartPrinterStateTracking(final PrinterId printerId) {
        LogUtil.c(f12612a, "onStartPrinterStateTracking  " + printerId);
        if (this.b.mOnReadyRunableList == null) {
            a(printerId);
        } else {
            this.b.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.a(printerId);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(PrinterId printerId) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.c.get(size);
            try {
                List<PrinterInfo> a2 = iMiPrinter.a(this.b);
                if (a2 != null) {
                    for (PrinterInfo next : a2) {
                        if (next != null && next.getId().equals(printerId)) {
                            iMiPrinter.a((PrintService) this.b, this.e, printerId, next);
                        }
                    }
                }
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void onStopPrinterStateTracking(final PrinterId printerId) {
        LogUtil.c(f12612a, "onStopPrinterStateTracking  " + printerId);
        if (this.b.mOnReadyRunableList == null) {
            b(printerId);
        } else {
            this.b.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.b(printerId);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(PrinterId printerId) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.c.get(size);
            try {
                List<PrinterInfo> a2 = iMiPrinter.a(this.b);
                if (a2 != null) {
                    for (PrinterInfo next : a2) {
                        if (next != null && next.getId().equals(printerId)) {
                            iMiPrinter.b(this.b, this.e, printerId, next);
                        }
                    }
                }
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public void onDestroy() {
        LogUtil.c(f12612a, ActivityInfo.TYPE_STR_ONDESTROY);
        if (this.b.mOnReadyRunableList == null) {
            c();
        } else {
            this.b.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    MiPrinterDiscoverySession.this.c();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IMiPrinter iMiPrinter = this.c.get(size);
            try {
                iMiPrinter.b(this.b, this.e);
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, iMiPrinter.toString(), th);
            }
        }
    }

    public ArrayList<DeviceStat> a(List<String> list) {
        ArrayList<DeviceStat> arrayList = new ArrayList<>();
        List<DeviceStat> deviceList = XmPluginHostApi.instance().getDeviceList();
        if (!(deviceList == null || list == null)) {
            for (DeviceStat next : deviceList) {
                if (list.contains(next.model) && next.isOnline) {
                    arrayList.add(next);
                }
            }
        }
        LogUtil.c(f12612a, "getDevice  " + list + "  " + arrayList);
        return arrayList;
    }

    public void a(IDeviceDiscovery iDeviceDiscovery) {
        this.d = iDeviceDiscovery;
    }

    public IDeviceDiscovery a() {
        if (this.d == null) {
            this.d = this;
        }
        return this.d;
    }

    public void onAddPrinters(List<PrinterInfo> list) {
        if (this.f != null) {
            this.f.onAddPrinters(list);
        }
    }
}
