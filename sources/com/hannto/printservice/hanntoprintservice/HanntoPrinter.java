package com.hannto.printservice.hanntoprintservice;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrinterCapabilitiesInfo;
import android.print.PrinterId;
import android.print.PrinterInfo;
import android.printservice.PrintService;
import android.util.Log;
import com.alipay.sdk.util.i;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.hannto.printservice.hanntoprintservice.entity.QueryJobStatusRunnable;
import com.hannto.printservice.hanntoprintservice.utils.PrinterUtils;
import com.hannto.printservice.hanntoprintservice.utils.Upload;
import com.hannto.printservice.hanntoprintservice.utils.UploadListener;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import com.xiaomi.smarthome.printer.IDeviceDiscovery;
import com.xiaomi.smarthome.printer.IMiPrinter;
import com.xiaomi.smarthome.printer.PrinterDiscoverySessionProxy;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class HanntoPrinter implements IMiPrinter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5762a = "HanntoPrinterService";
    public static final String b = "hannto.printer.anise";
    public static final String c = "hannto.printer.honey";
    private static final String d = "printer_service";
    private static final int e = 6672;
    private ArrayList<PrinterInfo> f = new ArrayList<>();
    /* access modifiers changed from: private */
    public Map<PrinterId, DeviceStat> g = new HashMap();

    public List<PrinterInfo> a(PrintService printService) {
        Log.i(f5762a, "HanntoPrinter getPrinters()");
        return this.f;
    }

    public void b(PrintService printService) {
        Log.i(f5762a, "HanntoPrinter onConnected()");
    }

    public void c(PrintService printService) {
        Log.i(f5762a, "HanntoPrinter onDisconnected()");
        this.f.clear();
        try {
            if (PrinterUtils.d != null && PrinterUtils.d.getLooper() != null) {
                PrinterUtils.d.getLooper().quit();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void d(PrintService printService) {
        Log.i(f5762a, "HanntoPrinter onCreatePrinterDiscoverySession()");
    }

    @TargetApi(19)
    public void a(List<PrinterId> list, PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, IDeviceDiscovery iDeviceDiscovery) {
        Log.i(f5762a, "HanntoPrinter onStartPrinterDiscovery()");
        ArrayList arrayList = new ArrayList();
        arrayList.add(b);
        if (Build.VERSION.SDK_INT >= 21) {
            arrayList.add(c);
        }
        ArrayList<DeviceStat> a2 = iDeviceDiscovery.a(arrayList);
        List<PrinterInfo> a3 = printerDiscoverySessionProxy.a();
        if (a2 != null) {
            Log.i(f5762a, "devices.size() = " + a2.size());
            Iterator<DeviceStat> it = a2.iterator();
            while (it.hasNext()) {
                DeviceStat next = it.next();
                Log.i(f5762a, "deviceStat.did = " + next.did + " deviceStat.model = " + next.model + " deviceStat.name = " + next.name);
                PrinterId generatePrinterId = printService.generatePrinterId(next.did);
                this.g.put(generatePrinterId, next);
                PrinterInfo build = new PrinterInfo.Builder(generatePrinterId, next.name, 1).setDescription(next.mac).build();
                a3.add(build);
                this.f.add(build);
            }
            printerDiscoverySessionProxy.a(a3);
        }
    }

    public void a(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy) {
        Log.i(f5762a, "HanntoPrinter onStopPrinterDiscovery()");
    }

    public void a(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, PrinterId printerId, PrinterInfo printerInfo) {
        Log.i(f5762a, "HanntoPrinter onStartPrinterStateTracking()");
        a(printerDiscoverySessionProxy, printerId, printerInfo);
    }

    @TargetApi(19)
    private void a(final PrinterDiscoverySessionProxy printerDiscoverySessionProxy, PrinterId printerId, final PrinterInfo printerInfo) {
        if (printerInfo == null) {
            Log.e(f5762a, " checkPrinterStatus printer == null");
            a(printerDiscoverySessionProxy, printerInfo, new PrinterCapabilitiesInfo.Builder(printerId).build(), 3);
        } else if (b.equals(this.g.get(printerId).model)) {
            final PrinterCapabilitiesInfo build = new PrinterCapabilitiesInfo.Builder(printerId).addMediaSize(PrintAttributes.MediaSize.ISO_A4, true).addMediaSize(new PrintAttributes.MediaSize(PrinterUtils.c, "6寸照片", 4000, 6000), false).addResolution(new PrintAttributes.Resolution("R2", "300x300", 300, 300), true).setColorModes(3, 1).build();
            final PrinterId printerId2 = printerId;
            final PrinterDiscoverySessionProxy printerDiscoverySessionProxy2 = printerDiscoverySessionProxy;
            final PrinterInfo printerInfo2 = printerInfo;
            PrinterUtils.a(this.g.get(printerId).did, this.g.get(printerId).model, (Callback) new Callback<String>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    try {
                        Log.i(HanntoPrinter.f5762a, " checkPrinterStatus 获取打印机状态返回 = " + str);
                        String[] split = new JSONObject(str).getJSONObject(((DeviceStat) HanntoPrinter.this.g.get(printerId2)).did).getString("prop.status").split(i.b);
                        for (String str2 : split) {
                            Log.d(HanntoPrinter.f5762a, "onSuccess: s = " + str2);
                        }
                        int intValue = Integer.valueOf(split[0]).intValue();
                        int i = 2;
                        if (!(1 == intValue || 2 == intValue || 3 == intValue || 5 == intValue)) {
                            if (6 != intValue) {
                                if (4 != intValue) {
                                    i = 3;
                                }
                                HanntoPrinter.this.a(printerDiscoverySessionProxy2, printerInfo2, build, i);
                            }
                        }
                        i = 1;
                        HanntoPrinter.this.a(printerDiscoverySessionProxy2, printerInfo2, build, i);
                    } catch (Exception e2) {
                        Log.e(HanntoPrinter.f5762a, " checkPrinterStatus " + e2.getMessage());
                        e2.printStackTrace();
                        HanntoPrinter.this.a(printerDiscoverySessionProxy2, printerInfo2, build, 3);
                    }
                }

                public void onFailure(int i, String str) {
                    Log.e(HanntoPrinter.f5762a, " checkPrinterStatus  error = " + i + " errorInfo = " + str);
                    HanntoPrinter.this.a(printerDiscoverySessionProxy2, printerInfo2, build, 3);
                }
            });
        } else if (c.equals(this.g.get(printerId).model)) {
            final PrinterCapabilitiesInfo build2 = new PrinterCapabilitiesInfo.Builder(printerId).addMediaSize(new PrintAttributes.MediaSize(PrinterUtils.c, "6寸照片", 4000, 6000), true).addResolution(new PrintAttributes.Resolution("R2", "300x300", 300, 300), true).setColorModes(2, 2).build();
            PrinterUtils.b(this.g.get(printerId).did, new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    int i;
                    try {
                        String string = jSONObject.getString("category");
                        if (!PrinterParmater.i.equals(string) && !PrinterParmater.l.equals(string)) {
                            if (!"off".equals(string)) {
                                i = PrinterParmater.j.equals(string) ? 2 : 3;
                                HanntoPrinter.this.a(printerDiscoverySessionProxy, printerInfo, build2, i);
                            }
                        }
                        i = 1;
                        HanntoPrinter.this.a(printerDiscoverySessionProxy, printerInfo, build2, i);
                    } catch (Exception e) {
                        Log.e(HanntoPrinter.f5762a, " checkPrinterStatus " + e.getMessage());
                        e.printStackTrace();
                        HanntoPrinter.this.a(printerDiscoverySessionProxy, printerInfo, build2, 3);
                    }
                }

                public void onFailure(int i, String str) {
                    Log.e(HanntoPrinter.f5762a, " checkPrinterStatus error = " + i + " errorInfo = " + str);
                    HanntoPrinter.this.a(printerDiscoverySessionProxy, printerInfo, build2, 3);
                }
            });
        } else {
            Log.e(f5762a, " checkPrinterStatus 未知model = " + this.g.get(printerId).model);
            a(printerDiscoverySessionProxy, printerInfo, new PrinterCapabilitiesInfo.Builder(printerId).build(), 3);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void a(PrinterDiscoverySessionProxy printerDiscoverySessionProxy, PrinterInfo printerInfo, PrinterCapabilitiesInfo printerCapabilitiesInfo, int i) {
        Log.i(f5762a, "refreshPrinterStatus() printerStatus = " + i);
        PrinterInfo build = new PrinterInfo.Builder(printerInfo).setCapabilities(printerCapabilitiesInfo).setStatus(i).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        printerDiscoverySessionProxy.a(arrayList);
    }

    public void b(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, PrinterId printerId, PrinterInfo printerInfo) {
        Log.i(f5762a, "onStopPrinterStateTracking()");
    }

    public void b(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy) {
        Log.i(f5762a, "onDestroyPrinterDiscoverySession()");
    }

    public void a(PrintService printService, PrinterDiscoverySessionProxy printerDiscoverySessionProxy, List<PrinterInfo> list) {
        Log.i(f5762a, "onValidatePrinters()");
    }

    @TargetApi(19)
    public void a(PrintService printService, PrinterInfo printerInfo, PrintJobProxy printJobProxy) {
        Log.i(f5762a, "onRequestCancelPrintJob()");
        a(printerInfo, printJobProxy);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b6  */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.print.PrinterInfo r6, final com.xiaomi.smarthome.device.api.printer.PrintJobProxy r7) {
        /*
            r5 = this;
            boolean r0 = r7.isCancelled()
            if (r0 != 0) goto L_0x00ba
            boolean r0 = r7.isCompleted()
            if (r0 != 0) goto L_0x00ba
            boolean r0 = r7.isFailed()
            if (r0 == 0) goto L_0x0014
            goto L_0x00ba
        L_0x0014:
            r0 = -1
            java.lang.String r1 = r7.getTag()     // Catch:{ Exception -> 0x003a }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x003a }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x003a }
            java.lang.String r0 = "HanntoPrinterService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0038 }
            r2.<init>()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r3 = "cancelJob()printJobId = "
            r2.append(r3)     // Catch:{ Exception -> 0x0038 }
            r2.append(r1)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0038 }
            android.util.Log.i(r0, r2)     // Catch:{ Exception -> 0x0038 }
            goto L_0x005a
        L_0x0038:
            r0 = move-exception
            goto L_0x003d
        L_0x003a:
            r1 = move-exception
            r0 = r1
            r1 = -1
        L_0x003d:
            java.lang.String r2 = "HanntoPrinterService"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "cancelJob()"
            r3.append(r4)
            java.lang.String r4 = r0.getMessage()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r2, r3)
            r0.printStackTrace()
        L_0x005a:
            if (r1 <= 0) goto L_0x00b6
            java.lang.String r0 = "hannto.printer.anise"
            java.util.Map<android.print.PrinterId, com.xiaomi.smarthome.device.api.DeviceStat> r2 = r5.g
            android.print.PrinterId r3 = r6.getId()
            java.lang.Object r2 = r2.get(r3)
            com.xiaomi.smarthome.device.api.DeviceStat r2 = (com.xiaomi.smarthome.device.api.DeviceStat) r2
            java.lang.String r2 = r2.model
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0089
            java.util.Map<android.print.PrinterId, com.xiaomi.smarthome.device.api.DeviceStat> r0 = r5.g
            android.print.PrinterId r6 = r6.getId()
            java.lang.Object r6 = r0.get(r6)
            com.xiaomi.smarthome.device.api.DeviceStat r6 = (com.xiaomi.smarthome.device.api.DeviceStat) r6
            java.lang.String r6 = r6.did
            com.hannto.printservice.hanntoprintservice.HanntoPrinter$3 r0 = new com.hannto.printservice.hanntoprintservice.HanntoPrinter$3
            r0.<init>(r7)
            com.hannto.printservice.hanntoprintservice.utils.PrinterUtils.a((java.lang.String) r6, (int) r1, (com.xiaomi.smarthome.device.api.Callback<org.json.JSONObject>) r0)
            goto L_0x00c1
        L_0x0089:
            java.lang.String r0 = "hannto.printer.honey"
            java.util.Map<android.print.PrinterId, com.xiaomi.smarthome.device.api.DeviceStat> r2 = r5.g
            android.print.PrinterId r3 = r6.getId()
            java.lang.Object r2 = r2.get(r3)
            com.xiaomi.smarthome.device.api.DeviceStat r2 = (com.xiaomi.smarthome.device.api.DeviceStat) r2
            java.lang.String r2 = r2.model
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00c1
            java.util.Map<android.print.PrinterId, com.xiaomi.smarthome.device.api.DeviceStat> r0 = r5.g
            android.print.PrinterId r6 = r6.getId()
            java.lang.Object r6 = r0.get(r6)
            com.xiaomi.smarthome.device.api.DeviceStat r6 = (com.xiaomi.smarthome.device.api.DeviceStat) r6
            java.lang.String r6 = r6.did
            com.hannto.printservice.hanntoprintservice.HanntoPrinter$4 r0 = new com.hannto.printservice.hanntoprintservice.HanntoPrinter$4
            r0.<init>(r7)
            com.hannto.printservice.hanntoprintservice.utils.PrinterUtils.a((java.lang.String) r6, (int) r1, (com.xiaomi.smarthome.device.api.Callback<org.json.JSONObject>) r0)
            goto L_0x00c1
        L_0x00b6:
            r7.cancel()
            goto L_0x00c1
        L_0x00ba:
            java.lang.String r6 = "HanntoPrinterService"
            java.lang.String r7 = "cancelJob()任务已结束"
            android.util.Log.i(r6, r7)
        L_0x00c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hannto.printservice.hanntoprintservice.HanntoPrinter.a(android.print.PrinterInfo, com.xiaomi.smarthome.device.api.printer.PrintJobProxy):void");
    }

    @TargetApi(19)
    public void b(PrintService printService, PrinterInfo printerInfo, PrintJobProxy printJobProxy) {
        Log.i(f5762a, "onPrintJobQueued");
        final Handler handler = new Handler(printService.getMainLooper());
        DeviceStat deviceStat = this.g.get(printerInfo.getId());
        final PrintJobProxy printJobProxy2 = printJobProxy;
        final DeviceStat deviceStat2 = deviceStat;
        final PrintService printService2 = printService;
        new Upload().a(printService, printerInfo, printJobProxy, deviceStat, (UploadListener) new UploadListener() {
            public void a() {
                Log.i(HanntoPrinter.f5762a, "onPrintJobQueued()onStart");
            }

            public void a(String str, long j) {
                Log.i(HanntoPrinter.f5762a, "onPrintJobQueued()onGetUploadUrl uploadUrl = " + str);
            }

            public void a(final String str, final String str2, final String str3) {
                Log.i(HanntoPrinter.f5762a, "onPrintJobQueued()onUploadSuccess downloadUrl = " + str + " sha1 = " + str2 + " fileName = " + str3);
                handler.post(new Runnable() {
                    public void run() {
                        if (!printJobProxy2.isCancelled()) {
                            HanntoPrinter.this.a(printJobProxy2, deviceStat2, str, str2, str3, printService2, handler);
                        }
                    }
                });
            }

            public void a(int i, final String str) {
                Log.i(HanntoPrinter.f5762a, "onPrintJobQueued()onFailed failType = " + i + " reason = " + str);
                handler.post(new Runnable() {
                    public void run() {
                        printJobProxy2.fail(str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void a(PrintJobProxy printJobProxy, DeviceStat deviceStat, String str, String str2, String str3, PrintService printService, Handler handler) {
        DeviceStat deviceStat2 = deviceStat;
        if (b.equals(deviceStat2.model)) {
            File file = new File(printService.getCacheDir(), str3);
            String absolutePath = file.getAbsolutePath();
            long length = file.exists() ? file.length() / 1024 : 0;
            String accountId = XmPluginHostApi.instance().getAccountId();
            String str4 = deviceStat2.did;
            final Handler handler2 = handler;
            final PrintJobProxy printJobProxy2 = printJobProxy;
            final PrintService printService2 = printService;
            final DeviceStat deviceStat3 = deviceStat;
            PrinterUtils.a(printJobProxy, accountId, str4, e, str, absolutePath, str2, length, new PrinterUtils.SubmitPrintJobListener() {
                public void a() {
                    Log.i(HanntoPrinter.f5762a, "submitPrintJob()onStart");
                }

                @TargetApi(19)
                public void a(boolean z, final int i, String str) {
                    handler2.post(new Runnable() {
                        public void run() {
                            Log.i(HanntoPrinter.f5762a, "submitPrintJob()printJobId = " + i);
                            printJobProxy2.setTag(String.valueOf(i));
                        }
                    });
                    HanntoPrinter.this.a(z, i, str, printJobProxy2, printService2, deviceStat3, handler2, HanntoPrinter.b);
                }
            });
            return;
        }
        String str5 = str3;
        if (c.equals(deviceStat2.model)) {
            final Handler handler3 = handler;
            final PrintJobProxy printJobProxy3 = printJobProxy;
            final PrintService printService3 = printService;
            final DeviceStat deviceStat4 = deviceStat;
            PrinterUtils.a(XmPluginHostApi.instance().getAccountId(), deviceStat2.did, d, str, str3, printJobProxy.getInfo().getCopies(), (PrinterUtils.SubmitPrintJobListener) new PrinterUtils.SubmitPrintJobListener() {
                public void a() {
                    Log.i(HanntoPrinter.f5762a, "submitPrintJob()onStart");
                }

                public void a(boolean z, final int i, String str) {
                    handler3.post(new Runnable() {
                        public void run() {
                            Log.i(HanntoPrinter.f5762a, "submitPrintJob()printJobId = " + i);
                            printJobProxy3.setTag(String.valueOf(i));
                        }
                    });
                    HanntoPrinter.this.a(z, i, str, printJobProxy3, printService3, deviceStat4, handler3, HanntoPrinter.c);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void a(boolean z, int i, String str, PrintJobProxy printJobProxy, PrintService printService, DeviceStat deviceStat, Handler handler, String str2) {
        final boolean z2 = z;
        final int i2 = i;
        final String str3 = str;
        final PrintService printService2 = printService;
        final DeviceStat deviceStat2 = deviceStat;
        final PrintJobProxy printJobProxy2 = printJobProxy;
        final Handler handler2 = handler;
        final String str4 = str2;
        handler.post(new Runnable() {
            public void run() {
                Log.i(HanntoPrinter.f5762a, "handleSubmitResult()isSuccess = " + z2 + " printJobId = " + i2 + " reason = " + str3);
                if (z2) {
                    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
                    newScheduledThreadPool.scheduleWithFixedDelay(new QueryJobStatusRunnable(printService2, i2, deviceStat2.did, newScheduledThreadPool, printJobProxy2, handler2, str4), 20, 10000, TimeUnit.MILLISECONDS);
                    return;
                }
                printJobProxy2.fail(XmPluginHostApi.instance().application().getString(R.string.service_submit_file_failed));
            }
        });
    }
}
