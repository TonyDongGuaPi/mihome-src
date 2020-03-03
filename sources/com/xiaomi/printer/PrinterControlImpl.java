package com.xiaomi.printer;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.print.PrinterInfo;
import android.printservice.PrinterDiscoverySession;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import com.xiaomi.smarthome.device.api.printer.PrinterControl;
import com.xiaomi.smarthome.printer.IDeviceDiscovery;
import java.util.ArrayList;
import java.util.List;

public class PrinterControlImpl implements Handler.Callback, PrinterControl, IDeviceDiscovery {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12620a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 5;
    private static final int f = 6;
    private static final int g = 7;
    private static final int h = 8;
    private static final int i = 9;
    private static final int j = 10;
    private static final int k = 11;
    private static final int l = 12;
    private final MiPrintService m = new MiPrintService();
    private final Handler n = new Handler(Looper.getMainLooper(), this);
    private MiPrinterDiscoverySession o;
    private DeviceStat p;
    private PrinterInfo q;
    private PrinterControl.OnAddPrinterListener r;

    public void onCreate(DeviceStat deviceStat) {
        this.p = deviceStat;
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.n.sendMessage(obtain);
        Message obtain2 = Message.obtain();
        obtain2.what = 2;
        this.n.sendMessage(obtain2);
        Message obtain3 = Message.obtain();
        obtain3.what = 3;
        this.n.sendMessage(obtain3);
        Message obtain4 = Message.obtain();
        obtain4.what = 4;
        this.n.sendMessage(obtain4);
    }

    public void onDestroy() {
        Message obtain = Message.obtain();
        obtain.what = 5;
        this.n.sendMessage(obtain);
        Message obtain2 = Message.obtain();
        obtain2.what = 10;
        this.n.sendMessage(obtain2);
        Message obtain3 = Message.obtain();
        obtain3.what = 12;
        this.n.sendMessage(obtain3);
        Message obtain4 = Message.obtain();
        obtain4.what = 11;
        this.n.sendMessage(obtain4);
    }

    public void onStartPrinterStateTracking(PrinterControl.OnAddPrinterListener onAddPrinterListener) {
        this.r = onAddPrinterListener;
        Message obtain = Message.obtain();
        obtain.what = 6;
        this.n.sendMessage(obtain);
    }

    public void onStopPrinterStateTracking() {
        Message obtain = Message.obtain();
        obtain.what = 7;
        this.n.sendMessage(obtain);
    }

    public void onPrintJobQueued(PrintJobProxy printJobProxy) {
        Message obtain = Message.obtain();
        obtain.what = 9;
        obtain.obj = printJobProxy;
        this.n.sendMessage(obtain);
    }

    public void onRequestCancelPrintJob(PrintJobProxy printJobProxy) {
        Message obtain = Message.obtain();
        obtain.obj = printJobProxy;
        obtain.what = 8;
        this.n.sendMessage(obtain);
    }

    @TargetApi(19)
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.m.onCreate();
                return true;
            case 2:
                this.m.onConnected();
                return true;
            case 3:
                PrinterDiscoverySession onCreatePrinterDiscoverySession = this.m.onCreatePrinterDiscoverySession();
                if (!(onCreatePrinterDiscoverySession instanceof MiPrinterDiscoverySession)) {
                    return true;
                }
                this.o = (MiPrinterDiscoverySession) onCreatePrinterDiscoverySession;
                this.o.a((IDeviceDiscovery) this);
                return true;
            case 4:
                if (this.o == null) {
                    return true;
                }
                this.o.onStartPrinterDiscovery(new ArrayList());
                List printers = this.o.getPrinters();
                if (printers == null || printers.size() <= 0) {
                    return true;
                }
                this.q = (PrinterInfo) printers.get(0);
                return true;
            case 5:
                if (this.o == null) {
                    return true;
                }
                this.o.onStopPrinterDiscovery();
                return true;
            case 6:
                if (this.o == null || this.q == null) {
                    return true;
                }
                this.o.onStartPrinterStateTracking(this.q.getId());
                this.o.a(this.r);
                return true;
            case 7:
                if (this.o == null || this.q == null) {
                    return true;
                }
                this.o.onStopPrinterStateTracking(this.q.getId());
                this.o.a((PrinterControl.OnAddPrinterListener) null);
                return true;
            case 8:
                this.m.onRequestCancelPrintJob((PrintJobProxy) message.obj);
                return true;
            case 9:
                this.m.onPrintJobQueued((PrintJobProxy) message.obj);
                return true;
            case 10:
                if (this.o == null) {
                    return true;
                }
                this.o.onDestroy();
                return true;
            case 11:
                this.m.onDestroy();
                return true;
            case 12:
                this.m.onDisconnected();
                return true;
            default:
                return true;
        }
    }

    public ArrayList<DeviceStat> a(List<String> list) {
        if (this.p == null || list.indexOf(this.p.model) < 0) {
            return null;
        }
        ArrayList<DeviceStat> arrayList = new ArrayList<>();
        arrayList.add(this.p);
        return arrayList;
    }
}
