package com.xiaomi.smarthome.printer;

import android.annotation.TargetApi;
import android.print.PrinterId;
import android.print.PrinterInfo;
import android.printservice.PrinterDiscoverySession;
import com.xiaomi.smarthome.device.api.printer.PrinterControl;
import java.util.List;

@TargetApi(19)
public class PrinterDiscoverySessionProxy {

    /* renamed from: a  reason: collision with root package name */
    private final PrinterDiscoverySession f21158a;
    private PrinterControl.OnAddPrinterListener b;

    public PrinterDiscoverySessionProxy(PrinterDiscoverySession printerDiscoverySession, PrinterControl.OnAddPrinterListener onAddPrinterListener) {
        this.f21158a = printerDiscoverySession;
        this.b = onAddPrinterListener;
    }

    public List<PrinterInfo> a() {
        return this.f21158a.getPrinters();
    }

    public void a(List<PrinterInfo> list) {
        this.f21158a.addPrinters(list);
    }

    public void b(List<PrinterId> list) {
        this.f21158a.removePrinters(list);
    }

    public List<PrinterId> b() {
        return this.f21158a.getTrackedPrinters();
    }

    public final boolean c() {
        return this.f21158a.isDestroyed();
    }

    public final boolean d() {
        return this.f21158a.isPrinterDiscoveryStarted();
    }
}
