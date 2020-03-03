package com.hannto.printservice.hanntoprintservice.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.print.PrinterInfo;
import android.printservice.PrintService;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.google.android.exoplayer2.C;
import com.hannto.printservice.hanntoprintservice.HanntoPrinter;
import com.hannto.printservice.hanntoprintservice.R;
import com.hannto.printservice.hanntoprintservice.entity.PrintInfo;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.printer.PrintJobProxy;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import miuipub.security.DigestUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONObject;

public class Upload {
    private static final String C = "code";
    private static final String D = "message";
    private static final String E = "result";
    private static final String F = "presigned_url";
    private static final String G = "expiration";
    private static final String H = "error_data";

    /* renamed from: a  reason: collision with root package name */
    public static final int f5790a = 1200;
    public static final int b = 1800;
    public static final int c = 1142;
    public static String d = "https://cnbj2.fds.api.xiaomi.com/";
    private static final int f = 50;
    private static final String g = "/v1";
    private static final String h = "http://dev.api.hannto.com";
    private static final String i = "https://api.hannto.com";
    private static final String j = "/c/res/gen/presigned_url/";
    private static final String k = "/c/res/gen/presigned_url";
    private static final boolean l = true;
    private static final String m = "https://api.hannto.com/v1/c/res/gen/presigned_url/";
    private static final String n = "file_name";
    private static final String o = "sha1";
    private static final String p = "model";
    private static final String q = "uid";
    private static final String r = "useto";
    private static final String s = "API-SIGNATURE";
    private static final String t = "PERMISSION-API-KEY";
    private static final String u = "PERMISSION-CLIENT-VERSION";
    private static final String v = "PERMISSION-API-NONCE";
    private static final String w = "PERMISSION-API-SIGNATURE";
    private static final String x = "permission";
    private static final String y = "IxomR6MCjsO3Ared";
    private static final String z = "e9zH1USvw82YVpk7OdloAIibqKZRGrEj";
    /* access modifiers changed from: private */
    public String A = "";
    /* access modifiers changed from: private */
    public String B = "";
    /* access modifiers changed from: private */
    public OkHttpClient I = new OkHttpClient();
    /* access modifiers changed from: private */
    public String J;
    /* access modifiers changed from: private */
    public String e = "servicetest";

    public void a(final PrintService printService, PrinterInfo printerInfo, PrintJobProxy printJobProxy, final DeviceStat deviceStat, final UploadListener uploadListener) {
        uploadListener.a();
        if (deviceStat == null) {
            uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_get_did_failed));
            return;
        }
        printJobProxy.start();
        try {
            if (!a(printService, printJobProxy, deviceStat, uploadListener)) {
                Log.e(this.e, "保存文件失败0");
            } else {
                ThreadPoolUtils.a().a(new Runnable() {
                    public void run() {
                        String str;
                        try {
                            if (deviceStat.model.equals(HanntoPrinter.c)) {
                                str = "honey";
                            } else if (deviceStat.model.equals(HanntoPrinter.b)) {
                                str = "anise";
                            } else {
                                Log.e(HanntoPrinter.f5762a, "未知model deviceStat.model = " + deviceStat.model);
                                str = "anise";
                            }
                            String str2 = "";
                            try {
                                str2 = printService.getPackageManager().getPackageInfo(printService.getPackageName(), 0).versionName;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String accountId = XmPluginHostApi.instance().getAccountId();
                            String absolutePath = new File(printService.getCacheDir(), Upload.this.J).getAbsolutePath();
                            String name = new File(absolutePath).getName();
                            String a2 = MD5Util.a(absolutePath);
                            Upload.this.a();
                            String str3 = "https://api.hannto.com/v1/c/res/gen/presigned_url/?file_name=" + name + a.b + Upload.o + "=" + a2 + a.b + "model" + "=" + str + a.b + "uid" + "=" + accountId + a.b + Upload.r + "=print_file";
                            Log.i(Upload.this.e, "finalString = " + str3 + " mNonce = " + Upload.this.A + " mSign = " + Upload.this.B);
                            Response execute = Upload.this.I.newCall(new Request.Builder().header(Upload.s, Upload.x).header(Upload.t, Upload.y).header(Upload.u, str2).header(Upload.v, Upload.this.A).header(Upload.w, Upload.this.B).url(str3).build()).execute();
                            if (execute.isSuccessful()) {
                                String string = execute.body().string();
                                Log.d(Upload.this.e, "bodyString = " + string);
                                JSONObject jSONObject = new JSONObject(string);
                                String string2 = jSONObject.getString("code");
                                String string3 = jSONObject.getString("message");
                                Log.d(Upload.this.e, "code = " + string2 + " message = " + string3);
                                if (string2.equals("0")) {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                                    String string4 = jSONObject2.getString(Upload.F);
                                    long j = jSONObject2.getLong(Upload.G);
                                    Log.d(Upload.this.e, "presigned_url = " + string4 + " expiration = " + j);
                                    uploadListener.a(string4, j);
                                    Upload.this.a(absolutePath, string4, a2, uploadListener);
                                    return;
                                }
                                String string5 = jSONObject.getString(Upload.H);
                                Log.e(Upload.this.e, "error_data = " + string5);
                                uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_upload_file_failed));
                                return;
                            }
                            Log.e(Upload.this.e, execute.toString());
                            uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_upload_file_failed));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_upload_file_failed));
                        }
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(this.e, "保存文件失败", e2);
            uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_save_file_failed));
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, UploadListener uploadListener) {
        try {
            if (TextUtils.isEmpty(str) || !new File(str).exists() || new File(str).length() <= 52428800) {
                str.substring(str.lastIndexOf(".") + 1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                httpURLConnection.setChunkedStreamingMode(0);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod(HttpPut.METHOD_NAME);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setRequestProperty("Content-Type", "");
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                dataOutputStream.flush();
                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        int read2 = inputStream.read();
                        if (read2 != -1) {
                            stringBuffer.append((char) read2);
                        } else {
                            Log.d(this.e, stringBuffer.toString());
                            dataOutputStream.close();
                            PrintInfo a2 = JsonUtils.a(stringBuffer.toString());
                            uploadListener.a(d + a2.b() + "/" + a2.d() + "?GalaxyAccessKeyId=" + a2.a() + "&Expires=" + a2.c() + "&Signature=" + a2.e(), str3, this.J);
                            return;
                        }
                    }
                } else {
                    uploadListener.a(1, XmPluginHostApi.instance().application().getString(R.string.service_upload_file_failed));
                }
            } else {
                String str4 = this.e;
                Log.e(str4, "uploadFile: new File(filePath).length() = " + new File(str).length());
                uploadListener.a(1, XmPluginHostApi.instance().application().getString(R.string.service_file_too_big));
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            uploadListener.a(1, XmPluginHostApi.instance().application().getString(R.string.service_upload_file_failed));
        } catch (Exception e3) {
            e3.printStackTrace();
            uploadListener.a(1, XmPluginHostApi.instance().application().getString(R.string.service_upload_file_failed));
        }
    }

    /* access modifiers changed from: private */
    public void a() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bArr = new byte[12];
        int nextInt = new Random().nextInt();
        long currentTimeMillis = System.currentTimeMillis();
        System.arraycopy(ByteBuffer.allocate(4).putInt(nextInt).array(), 0, bArr, 0, 4);
        System.arraycopy(ByteBuffer.allocate(4).putInt((int) (currentTimeMillis / 1000)).array(), 0, bArr, 4, 4);
        System.arraycopy(ByteBuffer.allocate(4).putInt((int) (currentTimeMillis % 1000)).array(), 0, bArr, 8, 4);
        String trim = Base64.encodeToString(bArr, 0).trim();
        byte[] bytes = z.getBytes("utf-8");
        byte[] bArr2 = new byte[(bytes.length + bArr.length)];
        System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
        System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(bArr2);
        String format = String.format("%s&%s&%s&%s", new Object[]{"GET", "/v1/c/res/gen/presigned_url/", y, Base64.encodeToString(instance.digest(), 0).trim()});
        Log.d(this.e, String.format("sha1String: %s", new Object[]{format}));
        MessageDigest instance2 = MessageDigest.getInstance(DigestUtils.b);
        instance2.update(format.getBytes());
        String trim2 = Base64.encodeToString(instance2.digest(), 0).trim();
        Log.d(this.e, String.format("PERMISSION-API-NONCE: %s\nPERMISSION-API-SIGNATURE: %s", new Object[]{trim, trim2}));
        this.A = trim;
        this.B = trim2;
    }

    @TargetApi(21)
    private boolean a(PrintService printService, PrintJobProxy printJobProxy, DeviceStat deviceStat, UploadListener uploadListener) throws Exception {
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream;
        File file;
        try {
            String name = printJobProxy.getDocumentInfo().getName();
            Log.d(this.e, "pringJobName = " + name);
            autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(printJobProxy.getDocumentData());
            try {
                Log.e(this.e, "fileInputStream.available() = " + autoCloseInputStream.available() + " printJob.getDocumentInfo().getDataSize() = " + printJobProxy.getDocumentInfo().getDataSize());
                if (printJobProxy.getDocumentInfo().getDataSize() > 52428800) {
                    uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_file_too_big));
                    try {
                        autoCloseInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return false;
                } else if (HanntoPrinter.b.equals(deviceStat.model)) {
                    if (!TextUtils.isEmpty(name)) {
                        this.J = name;
                    } else {
                        this.J = PrinterUtils.f5784a;
                    }
                    file = new File(printService.getCacheDir(), this.J);
                    if (file.exists()) {
                        file.delete();
                    }
                    if (!file.createNewFile()) {
                        Log.w(this.e, "创建文件失败 mFileName1 = " + this.J);
                        this.J = PrinterUtils.f5784a;
                        file = new File(printService.getCacheDir(), this.J);
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                    }
                    byte[] bArr = new byte[1024];
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        int read = autoCloseInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                    try {
                        autoCloseInputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return true;
                } else if (HanntoPrinter.c.equals(deviceStat.model)) {
                    File file2 = new File(printService.getCacheDir(), PrinterUtils.f5784a);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    byte[] bArr2 = new byte[1024];
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                    while (true) {
                        int read2 = autoCloseInputStream.read(bArr2);
                        if (read2 <= 0) {
                            break;
                        }
                        fileOutputStream2.write(bArr2, 0, read2);
                    }
                    fileOutputStream2.close();
                    PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file2, C.ENCODING_PCM_MU_LAW));
                    if (pdfRenderer.getPageCount() > 1) {
                        uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_file_too_many));
                        try {
                            autoCloseInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        return false;
                    } else if (pdfRenderer.getPageCount() <= 0) {
                        uploadListener.a(0, XmPluginHostApi.instance().application().getString(R.string.service_file_error));
                        try {
                            autoCloseInputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        return false;
                    } else {
                        PdfRenderer.Page openPage = pdfRenderer.openPage(0);
                        Bitmap createBitmap = Bitmap.createBitmap(openPage.getWidth() * 5, openPage.getHeight() * 5, Bitmap.Config.ARGB_8888);
                        openPage.render(createBitmap, (Rect) null, (Matrix) null, 2);
                        Bitmap a2 = a(a(createBitmap), (String) null, 1024, 4);
                        if (!TextUtils.isEmpty(name)) {
                            this.J = name;
                        } else {
                            this.J = PrinterUtils.b;
                        }
                        File file3 = new File(printService.getCacheDir(), this.J);
                        if (file3.exists()) {
                            file3.delete();
                        }
                        try {
                            if (!file3.createNewFile()) {
                                Log.w(this.e, "创建文件失败1 mFileName = " + this.J);
                                this.J = PrinterUtils.b;
                                file3 = new File(printService.getCacheDir(), this.J);
                                if (file3.exists()) {
                                    file3.delete();
                                }
                                file3.createNewFile();
                            }
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            Log.e(this.e, "创建文件失败 = " + e6.getMessage());
                            Log.w(this.e, "创建文件失败2 mFileName = " + this.J);
                            this.J = PrinterUtils.b;
                            file3 = new File(printService.getCacheDir(), this.J);
                            if (file3.exists()) {
                                file3.delete();
                            }
                            file3.createNewFile();
                        }
                        FileOutputStream fileOutputStream3 = new FileOutputStream(file3);
                        a2.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream3);
                        fileOutputStream3.close();
                        openPage.close();
                        pdfRenderer.close();
                        try {
                            autoCloseInputStream.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                        return true;
                    }
                } else {
                    Log.e(HanntoPrinter.f5762a, "错误的model = " + deviceStat.model);
                    try {
                        autoCloseInputStream.close();
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                    return false;
                }
            } catch (Exception e9) {
                e9.printStackTrace();
                Log.e(this.e, "创建文件失败 = " + e9.getMessage());
                Log.e(this.e, "创建文件失败 mFileName2 = " + this.J);
                this.J = PrinterUtils.f5784a;
                file = new File(printService.getCacheDir(), this.J);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            } catch (Throwable th) {
                th = th;
                try {
                    autoCloseInputStream.close();
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            autoCloseInputStream = null;
            autoCloseInputStream.close();
            throw th;
        }
    }

    private Bitmap a(Bitmap bitmap) {
        Bitmap bitmap2;
        if (bitmap == null) {
            Log.e(HanntoPrinter.f5762a, " getFinalBitmap bitmap == null");
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.d(HanntoPrinter.f5762a, "getFinalBitmap originalWidth = " + width + " originalHeight = " + height);
        if (bitmap.getWidth() == bitmap.getHeight()) {
            Bitmap createBitmap = Bitmap.createBitmap(1200, 1800, Bitmap.Config.ARGB_8888);
            createBitmap.eraseColor(Color.parseColor("#FFFFFF"));
            Canvas canvas = new Canvas(createBitmap);
            float height2 = 1142.0f / ((float) bitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(height2, height2, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
            canvas.drawBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true), 29.0f, 29.0f, (Paint) null);
            bitmap2 = createBitmap;
        } else if (height > width) {
            bitmap2 = Bitmap.createBitmap(1200, 1800, Bitmap.Config.ARGB_8888);
            bitmap2.eraseColor(Color.parseColor("#FFFFFF"));
            Canvas canvas2 = new Canvas(bitmap2);
            if (height * 2 > width * 3) {
                Log.i(HanntoPrinter.f5762a, "getFinalBitmap height > width >3/2");
                float height3 = 1800.0f / ((float) bitmap.getHeight());
                Matrix matrix2 = new Matrix();
                matrix2.postScale(height3, height3, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
                Bitmap createBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
                canvas2.drawBitmap(createBitmap2, (float) ((1200 - createBitmap2.getWidth()) / 2), 0.0f, (Paint) null);
            } else {
                Log.i(HanntoPrinter.f5762a, "getFinalBitmap height > width <3/2");
                float width2 = 1200.0f / ((float) bitmap.getWidth());
                Matrix matrix3 = new Matrix();
                matrix3.postScale(width2, width2, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
                Bitmap createBitmap3 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix3, true);
                canvas2.drawBitmap(createBitmap3, 0.0f, (float) ((1800 - createBitmap3.getHeight()) / 2), (Paint) null);
            }
        } else {
            bitmap2 = Bitmap.createBitmap(1800, 1200, Bitmap.Config.ARGB_8888);
            bitmap2.eraseColor(Color.parseColor("#FFFFFF"));
            Canvas canvas3 = new Canvas(bitmap2);
            if (width * 2 > height * 3) {
                Log.i(HanntoPrinter.f5762a, "getFinalBitmap width > height >3/2");
                float width3 = 1800.0f / ((float) bitmap.getWidth());
                Matrix matrix4 = new Matrix();
                matrix4.postScale(width3, width3, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
                Bitmap createBitmap4 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix4, true);
                canvas3.drawBitmap(createBitmap4, 0.0f, (float) ((1200 - createBitmap4.getHeight()) / 2), (Paint) null);
            } else {
                Log.i(HanntoPrinter.f5762a, "getFinalBitmap width > height <3/2");
                float height4 = 1200.0f / ((float) bitmap.getHeight());
                Matrix matrix5 = new Matrix();
                matrix5.postScale(height4, height4, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
                Bitmap createBitmap5 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix5, true);
                canvas3.drawBitmap(createBitmap5, (float) ((1800 - createBitmap5.getWidth()) / 2), 0.0f, (Paint) null);
            }
        }
        Log.i(HanntoPrinter.f5762a, "getFinalBitmap  resultWidth = " + bitmap2.getWidth() + " resultHeight = " + bitmap2.getHeight());
        return bitmap2;
    }

    private Bitmap a(Bitmap bitmap, String str, int i2, int i3) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i4 = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int length = byteArrayOutputStream.toByteArray().length / 1024;
        if (length < i2) {
            Log.i(HanntoPrinter.f5762a, "compressAndGenImage size = " + length);
            return bitmap;
        }
        while (i4 > 20 && byteArrayOutputStream.toByteArray().length / 1024 > i2) {
            byteArrayOutputStream.reset();
            i4 -= i3;
            Log.i(HanntoPrinter.f5762a, "compressAndGenImage options = " + i4);
            bitmap.compress(Bitmap.CompressFormat.JPEG, i4, byteArrayOutputStream);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private String a(Bitmap bitmap, String str) {
        if (bitmap == null) {
            return "";
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, System.currentTimeMillis() + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return file2.getAbsolutePath();
    }
}
