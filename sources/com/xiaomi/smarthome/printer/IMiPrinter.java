package com.xiaomi.smarthome.printer;

import android.print.PrinterId;
import android.print.PrinterInfo;
import android.printservice.PrintService;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import java.util.List;

public interface IMiPrinter {
    List<PrinterInfo> a(PrintService printService);

    void a(PrintService printService, PrinterInfo printerInfo, PrintJobProxy printJobProxy);

    void a(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy);

    void a(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, PrinterId printerId, PrinterInfo printerInfo);

    void a(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, List<PrinterInfo> list);

    void a(List<PrinterId> list, PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, IDeviceDiscovery iDeviceDiscovery);

    void b(PrintService printService);

    void b(PrintService printService, PrinterInfo printerInfo, PrintJobProxy printJobProxy);

    void b(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy);

    void b(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, PrinterId printerId, PrinterInfo printerInfo);

    void c(PrintService printService);

    void d(PrintService printService);
}
