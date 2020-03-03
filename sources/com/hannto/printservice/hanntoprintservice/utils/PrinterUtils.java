package com.hannto.printservice.hanntoprintservice.utils;

import android.annotation.TargetApi;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.printservice.PrintService;
import android.text.TextUtils;
import android.util.Log;
import com.hannto.printservice.hanntoprintservice.HanntoPrinter;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PrinterUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5784a = "docu.pdf";
    public static final String b = "img.jpg";
    public static final String c = "4x6";
    public static HandlerThread d = null;
    private static final String e = "status";
    private static final String f = "get_prop";
    private static final String g = "Mono";
    private static final String h = "Color";
    private static final String i = "Plain";
    private static final String j = "Glossy";
    private static final String k = "High";
    private static final String l = "Normal";
    private static final int m = 0;
    private static final int n = 3;
    private static final String o = "A4";
    private static final String p = "A4";
    private static final String q = "method";
    private static final String r = "params";

    public interface SubmitPrintJobListener {
        void a();

        void a(boolean z, int i, String str);
    }

    public static void a(String str, Callback<JSONObject> callback) {
        if (TextUtils.isEmpty(str)) {
            callback.onFailure(-6, "did is null");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("status");
        XmPluginHostApi.instance().callMethod(str, f, jSONArray, callback, Parser.DEFAULT_PARSER);
    }

    public static void a(String str, String str2, Callback callback) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("prop.status");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("props", jSONArray);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(jSONObject);
        XmPluginHostApi.instance().batchGetDeviceProps(str2, jSONArray2, callback);
    }

    public static void b(String str, Callback<JSONObject> callback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("method", f);
            jSONObject.put("params", "status");
            Log.i(HanntoPrinter.f5762a, "getHoneyPrinterStatus");
            XmPluginHostApi.instance().callMethod(str, jSONObject.toString(), callback, Parser.DEFAULT_PARSER);
        } catch (Exception e2) {
            e2.printStackTrace();
            callback.onFailure(-1, e2.getMessage());
        }
    }

    @TargetApi(19)
    public static void a(PrintJobProxy printJobProxy, String str, String str2, int i2, String str3, String str4, String str5, long j2, SubmitPrintJobListener submitPrintJobListener) {
        String str6 = str4;
        final int copies = printJobProxy.getInfo().getCopies();
        final String str7 = printJobProxy.getInfo().getAttributes().getColorMode() == 2 ? h : g;
        final String str8 = printJobProxy.getDocumentInfo().getContentType() == 1 ? j : i;
        final int i3 = printJobProxy.getDocumentInfo().getContentType() == 1 ? 0 : 3;
        final String str9 = printJobProxy.getDocumentInfo().getContentType() == 1 ? k : l;
        final String str10 = printJobProxy.getInfo().getAttributes().getMediaSize().getId().equals(c) ? c : "A4";
        final String substring = str6.substring(str6.lastIndexOf("/") + 1);
        final SubmitPrintJobListener submitPrintJobListener2 = submitPrintJobListener;
        final String str11 = str2;
        final String str12 = str3;
        final String str13 = str5;
        final String str14 = str;
        final int i4 = i2;
        final long j3 = j2;
        ThreadPoolUtils.a().a(new Runnable() {
            public void run() {
                submitPrintJobListener2.a();
                PrinterUtils.b(str11, str12, (String) null, substring, str13, copies, str7, str8, str10, str14, i4, i3, j3, str9, new Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Log.d(HanntoPrinter.f5762a, jSONObject.toString());
                        try {
                            submitPrintJobListener2.a(true, jSONObject.getInt("print_job_id"), (String) null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            submitPrintJobListener2.a(false, -1, e.getMessage());
                        }
                    }

                    public void onFailure(int i, String str) {
                        submitPrintJobListener2.a(false, -1, str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(String str, String str2, String str3, String str4, String str5, int i2, String str6, String str7, String str8, String str9, int i3, int i4, long j2, String str10, Callback callback) {
        String str11 = str2;
        String str12 = str4;
        String str13 = str5;
        int i5 = i2;
        String str14 = str6;
        String str15 = str7;
        String str16 = str8;
        StringBuilder sb = new StringBuilder();
        sb.append("printAniseJob() url = ");
        sb.append(str2);
        sb.append(" fileType = ");
        String str17 = str3;
        sb.append(str3);
        sb.append(" fileName = ");
        sb.append(str4);
        sb.append(" sha1 = ");
        sb.append(str5);
        sb.append(" copies = ");
        sb.append(i2);
        sb.append(" color = ");
        sb.append(str14);
        sb.append(" paper_type = ");
        sb.append(str15);
        sb.append(" paper_size = ");
        sb.append(str16);
        Log.i(HanntoPrinter.f5762a, sb.toString());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("chn", i3);
            jSONObject.put("account", str9);
            jSONObject.put("url", str2);
            jSONObject.put("file_type", "pdf");
            jSONObject.put("file_name", str4);
            jSONObject.put("sha1", str5);
            jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_FILE_SIZE, j2);
            jSONObject.put("copies", i2);
            jSONObject.put("color", str14);
            jSONObject.put("paper_type", str15);
            jSONObject.put("paper_size", str16);
            jSONObject.put("print_quality", str10);
            jSONObject.put("send_time", System.currentTimeMillis() / 1000);
            jSONObject.put("job_type", i4);
            jSONObject.put("duplex", false);
            jSONObject.put("collate", false);
            jSONObject.put("output_order", "Reverse");
            jSONObject.put("ps", 0);
            jSONObject.put("rt", -1);
            jSONObject.put("pm", 4.2d);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callMethod(str, "print_job", jSONObject, callback, Parser.DEFAULT_PARSER);
    }

    public static void a(String str, String str2, String str3, String str4, String str5, int i2, SubmitPrintJobListener submitPrintJobListener) {
        final SubmitPrintJobListener submitPrintJobListener2 = submitPrintJobListener;
        final String str6 = str2;
        final String str7 = str4;
        final String str8 = str5;
        final int i3 = i2;
        final String str9 = str;
        final String str10 = str3;
        ThreadPoolUtils.a().a(new Runnable() {
            public void run() {
                submitPrintJobListener2.a();
                PrinterUtils.b(str6, str7, str8, i3, str9, str10, new Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Log.d(HanntoPrinter.f5762a, "onSuccess " + jSONObject.toString());
                        try {
                            submitPrintJobListener2.a(true, jSONObject.getInt("print_job_id"), (String) null);
                        } catch (Exception e) {
                            submitPrintJobListener2.a(false, -1, e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(int i, String str) {
                        submitPrintJobListener2.a(false, -1, str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(String str, String str2, String str3, int i2, String str4, String str5, Callback callback) {
        Log.i(HanntoPrinter.f5762a, "printHoneyJob() url = " + str2 + " fileName = " + str3 + " copies = " + i2);
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        try {
            jSONObject.put("channel", str5);
            jSONObject.put("account", str4);
            jSONObject.put("send_time", currentTimeMillis);
            jSONObject.put("url", str2);
            jSONObject.put("file_name", str3);
            jSONObject.put("copies", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callMethod(str, "print_job", jSONObject, callback, Parser.DEFAULT_PARSER);
    }

    public static void a(String str, int i2, Callback<JSONObject> callback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(i2);
            jSONObject.put("print_job_id", jSONArray);
            XmPluginHostApi.instance().callMethod(str, "cancel_job", jSONObject, callback, Parser.DEFAULT_PARSER);
        } catch (Exception e2) {
            e2.printStackTrace();
            callback.onFailure(-1, e2.getMessage());
        }
    }

    private static void a(PrintService printService, PrintJobProxy printJobProxy) throws IOException {
        File file = new File(printService.getCacheDir(), f5784a);
        if (file.exists()) {
            file.delete();
        }
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(printJobProxy.getDocumentData());
        byte[] bArr = new byte[1024];
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = autoCloseInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    try {
                        return;
                    } catch (Exception e2) {
                        return;
                    }
                }
            }
        } finally {
            try {
                autoCloseInputStream.close();
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
    }
}
