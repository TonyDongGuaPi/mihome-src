package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.j;
import com.tencent.smtt.utils.v;
import com.xiaomi.smarthome.homeroom.HomeManager;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

class ag {
    private static int d = 5;
    private static int e = 1;
    private static final String[] f = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    private Set<String> A;
    private int B = d;
    private boolean C;

    /* renamed from: a  reason: collision with root package name */
    String f9137a;
    String[] b = null;
    int c = 0;
    private Context g;
    private String h;
    private String i;
    private String j;
    private File k;
    private long l;
    private int m = 30000;
    private int n = 20000;
    private boolean o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private HttpURLConnection t;
    private String u;
    private TbsLogReport.TbsLogInfo v;
    private String w;
    private int x;
    private boolean y;
    private Handler z;

    public ag(Context context) {
        this.g = context.getApplicationContext();
        this.v = TbsLogReport.a(this.g).a();
        this.A = new HashSet();
        this.u = "tbs_downloading_" + this.g.getPackageName();
        am.a();
        this.k = am.s(this.g);
        if (this.k != null) {
            f();
            this.w = null;
            this.x = -1;
            return;
        }
        throw new NullPointerException("TbsCorePrivateDir is null!");
    }

    private long a(long j2, long j3) {
        long currentTimeMillis = System.currentTimeMillis();
        this.v.setDownConsumeTime(currentTimeMillis - j2);
        this.v.setDownloadSize(j3);
        return currentTimeMillis;
    }

    @TargetApi(8)
    static File a(Context context) {
        try {
            File file = Build.VERSION.SDK_INT >= 8 ? new File(j.a(context, 4)) : null;
            if (file != null && !file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            return file;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private static File a(Context context, int i2) {
        File file = new File(j.a(context, i2));
        if (file.exists() && file.isDirectory()) {
            if (new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org").exists()) {
                return file;
            }
        }
        return null;
    }

    private String a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    private String a(URL url) {
        try {
            return InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        } catch (Error e3) {
            e3.printStackTrace();
            return "";
        }
    }

    private void a(int i2, String str, boolean z2) {
        if (z2 || this.p > this.B) {
            this.v.setErrorCode(i2);
            this.v.setFailDetail(str);
        }
    }

    private void a(long j2) {
        this.p++;
        if (j2 <= 0) {
            try {
                j2 = m();
            } catch (Exception unused) {
                return;
            }
        }
        Thread.sleep(j2);
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(File file, Context context) {
        File file2;
        if (file != null && file.exists()) {
            try {
                File a2 = a(context);
                if (a2 != null) {
                    if (TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
                        file2 = new File(a2, "x5.tbs.decouple");
                    } else {
                        file2 = new File(a2, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                    }
                    file2.delete();
                    j.b(file, file2);
                    if (TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) != 1 && TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0) == a.a(context, file)) {
                        File file3 = new File(a2, "x5.tbs.decouple");
                        if (a.a(context, file) != a.a(context, file3)) {
                            file3.delete();
                            j.b(file, file3);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    private void a(String str) {
        URL url = new URL(str);
        if (this.t != null) {
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        this.t = (HttpURLConnection) url.openConnection();
        this.t.setRequestProperty("User-Agent", TbsDownloader.a(this.g));
        this.t.setRequestProperty("Accept-Encoding", "identity");
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.n);
        this.t.setReadTimeout(this.m);
    }

    @TargetApi(8)
    static File b(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 8) {
                return null;
            }
            File a2 = a(context, 4);
            if (a2 == null) {
                a2 = a(context, 3);
            }
            if (a2 == null) {
                a2 = a(context, 2);
            }
            return a2 == null ? a(context, 1) : a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private void b(boolean z2) {
        v.a(this.g);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.g);
        instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
        instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -123);
        instance.commit();
        QbSdk.m.onDownloadFinish(z2 ? 100 : 120);
        int i2 = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
        int i3 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0);
        if (i2 == 3 || i2 > 10000) {
            File a2 = a(this.g);
            if (a2 != null) {
                File file = new File(a2, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                int a3 = a.a(this.g, file);
                File file2 = new File(this.k, "x5.tbs");
                String absolutePath = file2.exists() ? file2.getAbsolutePath() : null;
                int i4 = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
                Bundle bundle = new Bundle();
                bundle.putInt(HomeManager.v, i2);
                bundle.putInt("old_core_ver", a3);
                bundle.putInt("new_core_ver", i4);
                bundle.putString("old_apk_location", file.getAbsolutePath());
                bundle.putString("new_apk_location", absolutePath);
                bundle.putString("diff_file_location", absolutePath);
                am.a().b(this.g, bundle);
                return;
            }
            d();
            instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, true);
            instance.commit();
            return;
        }
        am.a().a(this.g, new File(this.k, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
        a(new File(this.k, "x5.tbs"), this.g);
    }

    private boolean b(int i2) {
        try {
            File file = new File(this.k, "x5.tbs");
            File a2 = a(this.g);
            if (a2 == null) {
                return false;
            }
            File file2 = new File(a2, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            file.delete();
            j.b(file2, file);
            if (a.a(this.g, file, 0, i2)) {
                return true;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] verifyTbsApk error!!");
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e2.getMessage());
            return false;
        }
    }

    public static void c(Context context) {
        try {
            am.a();
            File s2 = am.s(context);
            new File(s2, "x5.tbs").delete();
            new File(s2, "x5.tbs.temp").delete();
            File a2 = a(context);
            if (a2 != null) {
                new File(a2, "x5.tbs.org").delete();
                new File(a2, "x5.oversea.tbs.org").delete();
            }
        } catch (Exception unused) {
        }
    }

    private boolean c(boolean z2) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z2);
        File file = z2 ? new File(this.k, "x5.tbs") : new File(this.k, "x5.tbs.temp");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0086, code lost:
        if (r7 != r5) goto L_0x0088;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(boolean r10, boolean r11) {
        /*
            r9 = this;
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[TbsApkDownloader.verifyTbsApk] isTempFile="
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            java.io.File r0 = new java.io.File
            java.io.File r1 = r9.k
            if (r10 != 0) goto L_0x001f
            java.lang.String r2 = "x5.tbs"
            goto L_0x0021
        L_0x001f:
            java.lang.String r2 = "x5.tbs.temp"
        L_0x0021:
            r0.<init>(r1, r2)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 != 0) goto L_0x002c
            return r2
        L_0x002c:
            android.content.Context r1 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1)
            android.content.SharedPreferences r1 = r1.mPreferences
            java.lang.String r3 = "tbs_apk_md5"
            r4 = 0
            java.lang.String r1 = r1.getString(r3, r4)
            java.lang.String r3 = com.tencent.smtt.utils.a.a(r0)
            if (r1 == 0) goto L_0x01e6
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0049
            goto L_0x01e6
        L_0x0049:
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsApkDownloader.verifyTbsApk] md5("
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = ") successful!"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r3)
            r5 = 0
            if (r10 == 0) goto L_0x00c2
            android.content.Context r1 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1)
            android.content.SharedPreferences r1 = r1.mPreferences
            java.lang.String r3 = "tbs_apkfilesize"
            long r7 = r1.getLong(r3, r5)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x0088
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x00c2
            long r5 = r0.length()
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x00c2
        L_0x0088:
            java.lang.String r11 = "TbsDownload"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "[TbsApkDownloader.verifyTbsApk] isTempFile="
            r0.append(r1)
            r0.append(r10)
            java.lang.String r10 = " filelength failed"
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r10)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r10 = r9.v
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "fileLength:"
            r11.append(r0)
            r11.append(r5)
            java.lang.String r0 = ",contentLength:"
            r11.append(r0)
            r11.append(r7)
            java.lang.String r11 = r11.toString()
            r10.setCheckErrorDetail(r11)
            return r2
        L_0x00c2:
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = "[TbsApkDownloader.verifyTbsApk] length("
            r3.append(r7)
            r3.append(r5)
            java.lang.String r5 = ") successful!"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r3)
            r1 = -1
            if (r11 == 0) goto L_0x0134
            if (r10 != 0) goto L_0x0134
            android.content.Context r1 = r9.g
            int r1 = com.tencent.smtt.utils.a.a(r1, r0)
            android.content.Context r3 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r5 = "tbs_download_version"
            int r3 = r3.getInt(r5, r2)
            if (r3 == r1) goto L_0x0134
            java.lang.String r11 = "TbsDownload"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "[TbsApkDownloader.verifyTbsApk] isTempFile="
            r0.append(r4)
            r0.append(r10)
            java.lang.String r4 = " versionCode failed"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r0)
            if (r10 == 0) goto L_0x0133
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r10 = r9.v
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "fileVersion:"
            r11.append(r0)
            r11.append(r1)
            java.lang.String r0 = ",configVersion:"
            r11.append(r0)
            r11.append(r3)
            java.lang.String r11 = r11.toString()
            r10.setCheckErrorDetail(r11)
        L_0x0133:
            return r2
        L_0x0134:
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsApkDownloader.verifyTbsApk] tbsApkVersionCode("
            r5.append(r6)
            r5.append(r1)
            java.lang.String r1 = ") successful!"
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r1)
            if (r11 == 0) goto L_0x01a2
            if (r10 != 0) goto L_0x01a2
            android.content.Context r11 = r9.g
            java.lang.String r11 = com.tencent.smtt.utils.b.a((android.content.Context) r11, (java.io.File) r0)
            java.lang.String r1 = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L_0x01a2
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "[TbsApkDownloader.verifyTbsApk] isTempFile="
            r1.append(r3)
            r1.append(r10)
            java.lang.String r3 = " signature failed"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            if (r10 == 0) goto L_0x01a1
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r10 = r9.v
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "signature:"
            r0.append(r1)
            if (r11 != 0) goto L_0x018f
            java.lang.String r11 = "null"
            goto L_0x0197
        L_0x018f:
            int r11 = r11.length()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
        L_0x0197:
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            r10.setCheckErrorDetail(r11)
        L_0x01a1:
            return r2
        L_0x01a2:
            java.lang.String r11 = "TbsDownload"
            java.lang.String r1 = "[TbsApkDownloader.verifyTbsApk] signature successful!"
            com.tencent.smtt.utils.TbsLog.i(r11, r1)
            r11 = 1
            if (r10 == 0) goto L_0x01c9
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x01ba }
            java.io.File r1 = r9.k     // Catch:{ Exception -> 0x01ba }
            java.lang.String r3 = "x5.tbs"
            r10.<init>(r1, r3)     // Catch:{ Exception -> 0x01ba }
            boolean r10 = r0.renameTo(r10)     // Catch:{ Exception -> 0x01ba }
            goto L_0x01bd
        L_0x01ba:
            r10 = move-exception
            r4 = r10
            r10 = 0
        L_0x01bd:
            if (r10 != 0) goto L_0x01ca
            r10 = 109(0x6d, float:1.53E-43)
            java.lang.String r0 = r9.a((java.lang.Throwable) r4)
            r9.a(r10, r0, r11)
            return r2
        L_0x01c9:
            r10 = 0
        L_0x01ca:
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[TbsApkDownloader.verifyTbsApk] rename("
            r1.append(r2)
            r1.append(r10)
            java.lang.String r10 = ") successful!"
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r10)
            return r11
        L_0x01e6:
            java.lang.String r11 = "TbsDownload"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "[TbsApkDownloader.verifyTbsApk] isTempFile="
            r0.append(r1)
            r0.append(r10)
            java.lang.String r1 = " md5 failed"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r0)
            if (r10 == 0) goto L_0x020a
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r10 = r9.v
            java.lang.String r11 = "fileMd5 not match"
            r10.setCheckErrorDetail(r11)
        L_0x020a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ag.c(boolean, boolean):boolean");
    }

    private void f() {
        this.p = 0;
        this.q = 0;
        this.l = -1;
        this.j = null;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
    }

    private void g() {
        TbsLogReport a2;
        TbsLogReport.EventType eventType;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        if (this.t != null) {
            if (!this.r) {
                this.v.setResolveIp(a(this.t.getURL()));
            }
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.t = null;
        }
        int i2 = this.v.f9097a;
        if (this.r || !this.y) {
            TbsDownloader.f9092a = false;
            return;
        }
        this.v.setEventTime(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.g);
        if (apnInfo == null) {
            apnInfo = "";
        }
        int apnType = Apn.getApnType(this.g);
        this.v.setApn(apnInfo);
        this.v.setNetworkType(apnType);
        if (apnType != this.x || !apnInfo.equals(this.w)) {
            this.v.setNetworkChange(0);
        }
        if ((this.v.f9097a == 0 || this.v.f9097a == 107) && this.v.getDownFinalFlag() == 0 && (!Apn.isNetworkAvailable(this.g) || !l())) {
            a(101, (String) null, true);
        }
        if (TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1) {
            a2 = TbsLogReport.a(this.g);
            eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
        } else {
            a2 = TbsLogReport.a(this.g);
            eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
        }
        a2.a(eventType, this.v);
        this.v.resetArgs();
        if (i2 != 100) {
            QbSdk.m.onDownloadFinish(i2);
        }
    }

    private boolean h() {
        File file = new File(j.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        int i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i2 == 0) {
            i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return a.a(this.g, file, 0, i2);
    }

    private void i() {
        try {
            if (TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) != 1) {
                File file = new File(j.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean j() {
        return new File(this.k, "x5.tbs.temp").exists();
    }

    private long k() {
        File file = new File(this.k, "x5.tbs.temp");
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }

    /* JADX WARNING: type inference failed for: r1v8, types: [java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean l() {
        /*
            r9 = this;
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.String r1 = "www.qq.com"
            r2 = 0
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            r4.<init>()     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            java.lang.String r5 = "ping "
            r4.append(r5)     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            r4.append(r1)     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            java.lang.String r1 = r4.toString()     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            java.lang.Process r0 = r0.exec(r1)     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Throwable -> 0x006b, all -> 0x0067 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0061, all -> 0x005e }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0061, all -> 0x005e }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0058, all -> 0x0055 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x0058, all -> 0x0055 }
            r3 = 0
        L_0x002c:
            java.lang.String r5 = r4.readLine()     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            r6 = 1
            if (r5 == 0) goto L_0x004a
            java.lang.String r7 = "TTL"
            boolean r7 = r5.contains(r7)     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            if (r7 != 0) goto L_0x0049
            java.lang.String r7 = "ttl"
            boolean r5 = r5.contains(r7)     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            if (r5 == 0) goto L_0x0044
            goto L_0x0049
        L_0x0044:
            int r3 = r3 + r6
            r5 = 5
            if (r3 < r5) goto L_0x002c
            goto L_0x004a
        L_0x0049:
            r2 = 1
        L_0x004a:
            r9.a((java.io.Closeable) r0)
            goto L_0x0074
        L_0x004e:
            r2 = move-exception
            goto L_0x007d
        L_0x0050:
            r3 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x006e
        L_0x0055:
            r2 = move-exception
            r4 = r3
            goto L_0x007d
        L_0x0058:
            r4 = move-exception
            r8 = r3
            r3 = r0
            r0 = r4
            r4 = r8
            goto L_0x006e
        L_0x005e:
            r2 = move-exception
            r4 = r3
            goto L_0x007e
        L_0x0061:
            r1 = move-exception
            r4 = r3
            r3 = r0
            r0 = r1
            r1 = r4
            goto L_0x006e
        L_0x0067:
            r2 = move-exception
            r0 = r3
            r4 = r0
            goto L_0x007e
        L_0x006b:
            r0 = move-exception
            r1 = r3
            r4 = r1
        L_0x006e:
            r0.printStackTrace()     // Catch:{ all -> 0x007b }
            r9.a((java.io.Closeable) r3)
        L_0x0074:
            r9.a((java.io.Closeable) r1)
            r9.a((java.io.Closeable) r4)
            return r2
        L_0x007b:
            r2 = move-exception
            r0 = r3
        L_0x007d:
            r3 = r1
        L_0x007e:
            r9.a((java.io.Closeable) r0)
            r9.a((java.io.Closeable) r3)
            r9.a((java.io.Closeable) r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ag.l():boolean");
    }

    private long m() {
        switch (this.p) {
            case 1:
            case 2:
                return ((long) this.p) * 20000;
            case 3:
            case 4:
                return 100000;
            default:
                return 200000;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0094 A[SYNTHETIC, Splitter:B:26:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a A[SYNTHETIC, Splitter:B:29:0x009a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean n() {
        /*
            r8 = this;
            android.content.Context r0 = r8.g
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r1 = 1
            r2 = 0
            r3 = 3
            if (r0 != r3) goto L_0x000d
            r0 = 1
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi="
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r4)
            r3 = 0
            if (r0 == 0) goto L_0x009e
            android.content.Context r0 = r8.g
            java.lang.String r0 = com.tencent.smtt.utils.Apn.getWifiSSID(r0)
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsApkDwonloader.detectWifiNetworkAvailable] localBSSID="
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            java.net.URL r4 = new java.net.URL     // Catch:{ Throwable -> 0x008e }
            java.lang.String r5 = "http://pms.mb.qq.com/rsp204"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x008e }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ Throwable -> 0x008e }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ Throwable -> 0x008e }
            r4.setInstanceFollowRedirects(r2)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r3 = 10000(0x2710, float:1.4013E-41)
            r4.setConnectTimeout(r3)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r4.setReadTimeout(r3)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r4.setUseCaches(r2)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r4.getInputStream()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            int r3 = r4.getResponseCode()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r6.<init>()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.String r7 = "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode="
            r6.append(r7)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r6.append(r3)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r5 = 204(0xcc, float:2.86E-43)
            if (r3 != r5) goto L_0x0080
            r2 = 1
        L_0x0080:
            if (r4 == 0) goto L_0x009f
            r4.disconnect()     // Catch:{ Exception -> 0x009f }
            goto L_0x009f
        L_0x0086:
            r0 = move-exception
            goto L_0x0098
        L_0x0088:
            r1 = move-exception
            r3 = r4
            goto L_0x008f
        L_0x008b:
            r0 = move-exception
            r4 = r3
            goto L_0x0098
        L_0x008e:
            r1 = move-exception
        L_0x008f:
            r1.printStackTrace()     // Catch:{ all -> 0x008b }
            if (r3 == 0) goto L_0x009f
            r3.disconnect()     // Catch:{ Exception -> 0x009f }
            goto L_0x009f
        L_0x0098:
            if (r4 == 0) goto L_0x009d
            r4.disconnect()     // Catch:{ Exception -> 0x009d }
        L_0x009d:
            throw r0
        L_0x009e:
            r0 = r3
        L_0x009f:
            if (r2 != 0) goto L_0x00c7
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x00c7
            java.util.Set<java.lang.String> r1 = r8.A
            boolean r1 = r1.contains(r0)
            if (r1 != 0) goto L_0x00c7
            java.util.Set<java.lang.String> r1 = r8.A
            r1.add(r0)
            r8.o()
            android.os.Handler r1 = r8.z
            r3 = 150(0x96, float:2.1E-43)
            android.os.Message r1 = r1.obtainMessage(r3, r0)
            android.os.Handler r3 = r8.z
            r4 = 120000(0x1d4c0, double:5.9288E-319)
            r3.sendMessageDelayed(r1, r4)
        L_0x00c7:
            if (r2 == 0) goto L_0x00d6
            java.util.Set<java.lang.String> r1 = r8.A
            boolean r1 = r1.contains(r0)
            if (r1 == 0) goto L_0x00d6
            java.util.Set<java.lang.String> r1 = r8.A
            r1.remove(r0)
        L_0x00d6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ag.n():boolean");
    }

    private void o() {
        if (this.z == null) {
            this.z = new ah(this, al.a().getLooper());
        }
    }

    public void a(int i2) {
        try {
            File file = new File(this.k, "x5.tbs");
            int a2 = a.a(this.g, file);
            if (-1 == a2 || (i2 > 0 && i2 == a2)) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    public boolean a() {
        TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #1");
        try {
            File file = new File(j.a(this.g, 4), "x5.tbs.decouple");
            if (file.exists()) {
                TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #2");
            } else {
                File b2 = TbsDownloader.b(TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1));
                if (b2 != null && b2.exists()) {
                    j.b(b2, file);
                }
            }
            if (a.a(this.g, file, 0, TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1))) {
                TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #3");
                return am.a().e(this.g);
            }
            TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup no backup file !!!");
            File file2 = new File(j.a(this.g, 4), "x5.tbs.decouple");
            if (!file2.exists()) {
                return false;
            }
            file2.delete();
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean a(boolean z2) {
        if ((z2 && !n() && (!QbSdk.getDownloadWithoutWifi() || !Apn.isNetworkAvailable(this.g))) || this.b == null || this.c < 0 || this.c >= this.b.length) {
            return false;
        }
        String[] strArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        this.j = strArr[i2];
        this.p = 0;
        this.q = 0;
        this.l = -1;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(boolean r8, boolean r9) {
        /*
            r7 = this;
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r0 = "use_backup_version"
            r1 = 0
            int r8 = r8.getInt(r0, r1)
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()
            android.content.Context r2 = r7.g
            int r0 = r0.i(r2)
            if (r8 != 0) goto L_0x002e
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r2 = "tbs_download_version"
            int r8 = r8.getInt(r2, r1)
            java.lang.String r2 = "by default key"
        L_0x002b:
            r7.f9137a = r2
            goto L_0x0031
        L_0x002e:
            java.lang.String r2 = "by new key"
            goto L_0x002b
        L_0x0031:
            if (r8 == 0) goto L_0x013d
            if (r8 != r0) goto L_0x0037
            goto L_0x013d
        L_0x0037:
            r0 = -214(0xffffffffffffff2a, float:NaN)
            r2 = 1
            if (r9 == 0) goto L_0x00fb
            java.io.File r3 = com.tencent.smtt.sdk.TbsDownloader.a((int) r8)
            if (r3 == 0) goto L_0x007a
            boolean r4 = r3.exists()
            if (r4 == 0) goto L_0x007a
            java.io.File r4 = new java.io.File
            android.content.Context r5 = r7.g
            r6 = 4
            java.lang.String r5 = com.tencent.smtt.utils.j.a((android.content.Context) r5, (int) r6)
            android.content.Context r6 = r7.g
            boolean r6 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r6)
            if (r6 == 0) goto L_0x005c
            java.lang.String r6 = "x5.oversea.tbs.org"
            goto L_0x005e
        L_0x005c:
            java.lang.String r6 = "x5.tbs.org"
        L_0x005e:
            r4.<init>(r5, r6)
            android.content.Context r5 = r7.g     // Catch:{ Exception -> 0x0076 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Exception -> 0x0076 }
            android.content.SharedPreferences r5 = r5.mPreferences     // Catch:{ Exception -> 0x0076 }
            java.lang.String r6 = "tbs_download_version_type"
            int r5 = r5.getInt(r6, r1)     // Catch:{ Exception -> 0x0076 }
            if (r5 == r2) goto L_0x007a
            com.tencent.smtt.utils.j.b(r3, r4)     // Catch:{ Exception -> 0x0076 }
            r4 = 1
            goto L_0x007b
        L_0x0076:
            r4 = move-exception
            r4.printStackTrace()
        L_0x007a:
            r4 = 0
        L_0x007b:
            boolean r5 = r7.h()
            if (r5 == 0) goto L_0x00eb
            boolean r8 = r7.b((int) r8)
            if (r8 == 0) goto L_0x00fb
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            java.util.Map<java.lang.String, java.lang.Object> r8 = r8.f9091a
            java.lang.String r9 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r8.put(r9, r3)
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            r8.setDownloadInterruptCode(r0)
            r7.b((boolean) r1)
            if (r4 == 0) goto L_0x00ea
            r8 = 100
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "use local backup apk in startDownload"
            r9.append(r0)
            java.lang.String r0 = r7.f9137a
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r7.a(r8, r9, r2)
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r9 = "tbs_downloaddecouplecore"
            int r8 = r8.getInt(r9, r1)
            if (r8 != r2) goto L_0x00dc
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r8)
            com.tencent.smtt.sdk.TbsLogReport$EventType r9 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
        L_0x00d6:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r7.v
            r8.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r9, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r0)
            goto L_0x00e5
        L_0x00dc:
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r8)
            com.tencent.smtt.sdk.TbsLogReport$EventType r9 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            goto L_0x00d6
        L_0x00e5:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r8 = r7.v
            r8.resetArgs()
        L_0x00ea:
            return r2
        L_0x00eb:
            r7.i()
            android.content.Context r4 = r7.g
            r5 = 0
            boolean r8 = com.tencent.smtt.utils.a.a(r4, r3, r5, r8)
            if (r8 != 0) goto L_0x00fb
            com.tencent.smtt.utils.j.b((java.io.File) r3)
        L_0x00fb:
            boolean r8 = r7.c(r1, r9)
            if (r8 == 0) goto L_0x011f
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            java.util.Map<java.lang.String, java.lang.Object> r8 = r8.f9091a
            java.lang.String r9 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r8.put(r9, r3)
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            r8.setDownloadInterruptCode(r0)
            r7.b((boolean) r1)
            return r2
        L_0x011f:
            boolean r8 = r7.c((boolean) r2)
            if (r8 != 0) goto L_0x013d
            boolean r8 = r7.c((boolean) r2)
            if (r8 != 0) goto L_0x013d
            java.lang.String r8 = "TbsDownload"
            java.lang.String r9 = "[TbsApkDownloader] delete file failed!"
            com.tencent.smtt.utils.TbsLog.e(r8, r9)
            android.content.Context r8 = r7.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            r9 = -301(0xfffffffffffffed3, float:NaN)
            r8.setDownloadInterruptCode(r9)
        L_0x013d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ag.a(boolean, boolean):boolean");
    }

    public int b() {
        File a2 = a(this.g);
        if (a2 == null) {
            return 0;
        }
        return a.a(this.g, new File(a2, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0395, code lost:
        if (r6.equals(r1.w) == false) goto L_0x0397;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0416, code lost:
        if (r2 == false) goto L_0x0418;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0418, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).f9091a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r3));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).commit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0432, code lost:
        r8 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0490, code lost:
        if (r2 == false) goto L_0x0418;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x04d5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x04d6, code lost:
        r28 = r3;
        r32 = r10;
        r22 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x04ef, code lost:
        if (r2 == false) goto L_0x04f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x04f1, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).f9091a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0502, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).commit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x050d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x052d, code lost:
        if (r2 == false) goto L_0x04f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0534, code lost:
        if (r2 == false) goto L_0x0536;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x05d3, code lost:
        if (r2 != false) goto L_0x0b50;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x05d7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x05d8, code lost:
        r8 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x06b5, code lost:
        if (r2 == false) goto L_0x04f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:?, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...Canceled!", true);
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g);
        r9 = -309;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0749, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x074a, code lost:
        r28 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x0751, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x0752, code lost:
        r28 = r3;
        r9 = r7;
        r32 = r10;
        r12 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x0767, code lost:
        if (r1.b == null) goto L_0x078c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x076e, code lost:
        if (c(true, r8) != false) goto L_0x078c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0770, code lost:
        if (r2 != false) goto L_0x077e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x0776, code lost:
        if (a(false) == false) goto L_0x077e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0778, code lost:
        r28 = r3;
        r32 = r10;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x077e, code lost:
        r1.s = true;
        r28 = r3;
        r32 = r10;
        r3 = false;
        r22 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x078c, code lost:
        r1.s = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x0791, code lost:
        if (r1.b == null) goto L_0x0795;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x0793, code lost:
        r22 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x0795, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).setDownloadInterruptCode(-311);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:?, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...failed because you exceeded max flow!", true);
        a(112, "downloadFlow=" + r3 + " downloadMaxflow=" + r10, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).setDownloadInterruptCode(-307);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:399:0x08bc, code lost:
        c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x08c1, code lost:
        if (com.tencent.smtt.sdk.QbSdk.m == null) goto L_0x08ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:402:?, code lost:
        com.tencent.smtt.sdk.QbSdk.m.onDownloadFinish(111);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:?, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "Download is paused due to NOT_WIFI error!", false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:?, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).setDownloadInterruptCode(-304);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x08dd, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:413:0x08eb, code lost:
        if (r2 == false) goto L_0x0aa0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x08f7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:0x08f8, code lost:
        r12 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x08fc, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x08fd, code lost:
        r12 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:461:0x09cb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:462:0x09cc, code lost:
        r28 = r3;
        r22 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0168, code lost:
        r22 = r8;
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:0x0a42, code lost:
        if (r2 == false) goto L_0x0a44;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:482:0x0a44, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).f9091a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r28));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:492:0x0a7e, code lost:
        if (r2 == false) goto L_0x0aa0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:498:0x0a9e, code lost:
        if (r2 == false) goto L_0x0aa0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:499:0x0aa0, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).f9091a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r28));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).commit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x0aba, code lost:
        r7 = r8;
        r8 = r22;
        r3 = r28;
        r10 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:509:0x0ad6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:510:0x0ad7, code lost:
        r32 = r10;
        r8 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:528:0x0b4c, code lost:
        if (r2 == false) goto L_0x0a44;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:574:0x0c31, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).f9091a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r28));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).commit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0208, code lost:
        if (r2 == false) goto L_0x020a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x020a, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).f9091a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r3));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.g).commit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0246, code lost:
        if (r2 == false) goto L_0x020a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0249, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x024a, code lost:
        r28 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x03a6 A[SYNTHETIC, Splitter:B:137:0x03a6] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x03d5 A[SYNTHETIC, Splitter:B:143:0x03d5] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x040b A[SYNTHETIC, Splitter:B:159:0x040b] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0436  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x075a  */
    /* JADX WARNING: Removed duplicated region for block: B:411:0x08e0 A[SYNTHETIC, Splitter:B:411:0x08e0] */
    /* JADX WARNING: Removed duplicated region for block: B:414:0x08ef  */
    /* JADX WARNING: Removed duplicated region for block: B:472:0x09f1 A[Catch:{ all -> 0x0ac3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:519:0x0aed A[ADDED_TO_REGION, Catch:{ all -> 0x0c2c }] */
    /* JADX WARNING: Removed duplicated region for block: B:555:0x0baf  */
    /* JADX WARNING: Removed duplicated region for block: B:556:0x0bc5  */
    /* JADX WARNING: Removed duplicated region for block: B:561:0x0bed  */
    /* JADX WARNING: Removed duplicated region for block: B:565:0x0bf5  */
    /* JADX WARNING: Removed duplicated region for block: B:574:0x0c31  */
    /* JADX WARNING: Removed duplicated region for block: B:591:0x022e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:597:0x0b41 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:600:0x072d A[EDGE_INSN: B:600:0x072d->B:322:0x072d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0249 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 
      PHI: (r3v113 long) = (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v78 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v56 long), (r3v25 long), (r3v114 long) binds: [B:72:0x025c, B:73:?, B:83:0x0294, B:84:?, B:114:0x0361, B:139:0x03af, B:140:?, B:156:0x0407, B:157:?, B:262:0x05e0, B:289:0x06bd, B:290:?, B:454:0x0993, B:266:0x061f, B:184:0x0495, B:185:?, B:219:0x0552, B:210:0x051a, B:189:0x04a4, B:200:0x04e1, B:201:?, B:192:0x04aa, B:193:?, B:172:0x0448, B:180:0x0482, B:181:?, B:159:0x040b, B:160:?, B:143:0x03d5, B:137:0x03a6, B:138:?, B:126:0x038b, B:127:?, B:132:0x0397, B:129:0x038f, B:121:0x037f, B:86:0x029d, B:87:?, B:89:0x02b9, B:90:?, B:100:0x0301, B:101:?, B:103:0x0315, B:104:?, B:94:0x02c3, B:95:?, B:81:0x028f, B:82:?, B:77:0x0265, B:75:0x0262, B:76:?, B:53:0x018a, B:62:0x0226] A[DONT_GENERATE, DONT_INLINE], Splitter:B:114:0x0361] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(boolean r35, boolean r36) {
        /*
            r34 = this;
            r1 = r34
            r2 = r35
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r4 = r1.g
            boolean r3 = r3.c(r4)
            r4 = 0
            if (r3 == 0) goto L_0x0021
            if (r2 != 0) goto L_0x0021
            com.tencent.smtt.sdk.TbsDownloader.f9092a = r4
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -322(0xfffffffffffffebe, float:NaN)
        L_0x001d:
            r2.setDownloadInterruptCode(r3)
            return
        L_0x0021:
            android.content.Context r3 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r5 = "tbs_responsecode"
            int r3 = r3.getInt(r5, r4)
            r5 = 2
            r6 = 1
            if (r3 == r6) goto L_0x003b
            if (r3 == r5) goto L_0x003b
            r7 = 4
            if (r3 != r7) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            r7 = 0
            goto L_0x003c
        L_0x003b:
            r7 = 1
        L_0x003c:
            if (r36 != 0) goto L_0x0047
            boolean r3 = r1.a((boolean) r2, (boolean) r7)
            if (r3 == 0) goto L_0x0047
            com.tencent.smtt.sdk.TbsDownloader.f9092a = r4
            return
        L_0x0047:
            r1.C = r2
            android.content.Context r3 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r8 = "tbs_downloadurl"
            r9 = 0
            java.lang.String r3 = r3.getString(r8, r9)
            r1.h = r3
            android.content.Context r3 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r8 = "tbs_downloadurl_list"
            java.lang.String r3 = r3.getString(r8, r9)
            java.lang.String r8 = "TbsDownload"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "backupUrlStrings:"
            r10.append(r11)
            r10.append(r3)
            java.lang.String r10 = r10.toString()
            com.tencent.smtt.utils.TbsLog.i(r8, r10, r6)
            r1.b = r9
            r1.c = r4
            if (r2 != 0) goto L_0x009e
            if (r3 == 0) goto L_0x009e
            java.lang.String r8 = ""
            java.lang.String r10 = r3.trim()
            boolean r8 = r8.equals(r10)
            if (r8 != 0) goto L_0x009e
            java.lang.String r8 = r3.trim()
            java.lang.String r10 = ";"
            java.lang.String[] r8 = r8.split(r10)
            r1.b = r8
        L_0x009e:
            java.lang.String r8 = "TbsDownload"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "[TbsApkDownloader.startDownload] mDownloadUrl="
            r10.append(r11)
            java.lang.String r11 = r1.h
            r10.append(r11)
            java.lang.String r11 = " backupUrlStrings="
            r10.append(r11)
            r10.append(r3)
            java.lang.String r3 = " mLocation="
            r10.append(r3)
            java.lang.String r3 = r1.j
            r10.append(r3)
            java.lang.String r3 = " mCanceled="
            r10.append(r3)
            boolean r3 = r1.r
            r10.append(r3)
            java.lang.String r3 = " mHttpRequest="
            r10.append(r3)
            java.net.HttpURLConnection r3 = r1.t
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            com.tencent.smtt.utils.TbsLog.i(r8, r3)
            java.lang.String r3 = r1.h
            r8 = 110(0x6e, float:1.54E-43)
            if (r3 != 0) goto L_0x00f5
            java.lang.String r3 = r1.j
            if (r3 != 0) goto L_0x00f5
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r2.onDownloadFinish(r8)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -302(0xfffffffffffffed2, float:NaN)
            goto L_0x001d
        L_0x00f5:
            java.net.HttpURLConnection r3 = r1.t
            if (r3 == 0) goto L_0x010c
            boolean r3 = r1.r
            if (r3 != 0) goto L_0x010c
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r2.onDownloadFinish(r8)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -303(0xfffffffffffffed1, float:NaN)
            goto L_0x001d
        L_0x010c:
            r3 = -304(0xfffffffffffffed0, float:NaN)
            if (r2 != 0) goto L_0x0132
            java.util.Set<java.lang.String> r10 = r1.A
            android.content.Context r11 = r1.g
            java.lang.String r11 = com.tencent.smtt.utils.Apn.getWifiSSID(r11)
            boolean r10 = r10.contains(r11)
            if (r10 == 0) goto L_0x0132
            java.lang.String r2 = "TbsDownload"
            java.lang.String r4 = "[TbsApkDownloader.startDownload] WIFI Unavailable"
            com.tencent.smtt.utils.TbsLog.i(r2, r4)
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r2.onDownloadFinish(r8)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            goto L_0x001d
        L_0x0132:
            r34.f()
            java.lang.String r8 = "TbsDownload"
            java.lang.String r10 = "STEP 1/2 begin downloading..."
            com.tencent.smtt.utils.TbsLog.i(r8, r10, r6)
            android.content.Context r8 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            long r10 = r8.getDownloadMaxflow()
            android.content.Context r8 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r12 = "tbs_downloadflow"
            r13 = 0
            long r15 = r8.getLong(r12, r13)
            if (r2 == 0) goto L_0x015d
            int r8 = e
        L_0x015a:
            r1.B = r8
            goto L_0x0160
        L_0x015d:
            int r8 = d
            goto L_0x015a
        L_0x0160:
            r3 = r15
            r8 = 0
        L_0x0162:
            int r12 = r1.p
            int r15 = r1.B
            if (r12 <= r15) goto L_0x016d
        L_0x0168:
            r22 = r8
            r8 = r7
            goto L_0x0b50
        L_0x016d:
            int r12 = r1.q
            r15 = 8
            if (r12 <= r15) goto L_0x0184
            r2 = 123(0x7b, float:1.72E-43)
            r1.a(r2, r9, r6)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -306(0xfffffffffffffece, float:NaN)
            r2.setDownloadInterruptCode(r3)
            goto L_0x0168
        L_0x0184:
            long r15 = java.lang.System.currentTimeMillis()
            if (r2 != 0) goto L_0x025c
            android.content.Context r12 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            android.content.SharedPreferences r12 = r12.mPreferences     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.lang.String r5 = "tbs_downloadstarttime"
            long r17 = r12.getLong(r5, r13)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r5 = 0
            long r17 = r15 - r17
            r19 = 86400000(0x5265c00, double:4.2687272E-316)
            int r5 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r5 <= 0) goto L_0x01d6
            java.lang.String r5 = "TbsDownload"
            java.lang.String r12 = "[TbsApkDownloader.startDownload] OVER DOWNLOAD_PERIOD"
            com.tencent.smtt.utils.TbsLog.i(r5, r12)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.f9091a     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.lang.String r12 = "tbs_downloadstarttime"
            java.lang.Long r9 = java.lang.Long.valueOf(r15)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r5.put(r12, r9)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.f9091a     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.lang.String r9 = "tbs_downloadflow"
            java.lang.Long r12 = java.lang.Long.valueOf(r13)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r5.put(r9, r12)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r5.commit()     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r3 = r13
            goto L_0x0226
        L_0x01d6:
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r9.<init>()     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.lang.String r12 = "[TbsApkDownloader.startDownload] downloadFlow="
            r9.append(r12)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r9.append(r3)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.utils.TbsLog.i(r5, r9)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0226
            java.lang.String r5 = "TbsDownload"
            java.lang.String r9 = "STEP 1/2 begin downloading...failed because you exceeded max flow!"
            com.tencent.smtt.utils.TbsLog.i(r5, r9, r6)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r5 = 0
            r9 = 112(0x70, float:1.57E-43)
            r1.a(r9, r5, r6)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r9 = -307(0xfffffffffffffecd, float:NaN)
            r5.setDownloadInterruptCode(r9)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            if (r2 != 0) goto L_0x0168
        L_0x020a:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2.put(r5, r3)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
            goto L_0x0168
        L_0x0226:
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            boolean r5 = com.tencent.smtt.utils.j.b((android.content.Context) r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            if (r5 != 0) goto L_0x025c
            java.lang.String r5 = "TbsDownload"
            java.lang.String r9 = "DownloadBegin FreeSpace too small"
            com.tencent.smtt.utils.TbsLog.i(r5, r9, r6)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r5 = 0
            r9 = 105(0x69, float:1.47E-43)
            r1.a(r9, r5, r6)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r9 = -308(0xfffffffffffffecc, float:NaN)
            r5.setDownloadInterruptCode(r9)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            if (r2 != 0) goto L_0x0168
            goto L_0x020a
        L_0x0249:
            r0 = move-exception
            r28 = r3
        L_0x024c:
            r3 = r0
            goto L_0x0c2f
        L_0x024f:
            r0 = move-exception
            r28 = r3
            r22 = r8
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r3 = r0
            r8 = r7
            goto L_0x0ae9
        L_0x025c:
            r1.y = r6     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            java.lang.String r5 = r1.j     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            if (r5 == 0) goto L_0x0265
            java.lang.String r5 = r1.j     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            goto L_0x0267
        L_0x0265:
            java.lang.String r5 = r1.h     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
        L_0x0267:
            java.lang.String r9 = "TbsDownload"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            r12.<init>()     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            java.lang.String r13 = "try url:"
            r12.append(r13)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            r12.append(r5)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            java.lang.String r13 = ",mRetryTimes:"
            r12.append(r13)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            int r13 = r1.p     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            r12.append(r13)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            com.tencent.smtt.utils.TbsLog.i(r9, r12, r6)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            java.lang.String r9 = r1.i     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            boolean r9 = r5.equals(r9)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            if (r9 != 0) goto L_0x0294
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r9 = r1.v     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
            r9.setDownloadUrl(r5)     // Catch:{ Throwable -> 0x024f, all -> 0x0249 }
        L_0x0294:
            r1.i = r5     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            r1.a((java.lang.String) r5)     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            boolean r5 = r1.o     // Catch:{ Throwable -> 0x0ade, all -> 0x0249 }
            if (r5 != 0) goto L_0x035b
            long r13 = r34.k()     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            r9.<init>()     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            java.lang.String r12 = "[TbsApkDownloader.startDownload] range="
            r9.append(r12)     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            r9.append(r13)     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            com.tencent.smtt.utils.TbsLog.i(r5, r9)     // Catch:{ Throwable -> 0x034f, all -> 0x0249 }
            r21 = r7
            long r6 = r1.l     // Catch:{ Throwable -> 0x0345, all -> 0x0249 }
            r17 = 0
            int r5 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r5 > 0) goto L_0x02ff
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r6.<init>()     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.lang.String r7 = "STEP 1/2 begin downloading...current"
            r6.append(r7)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r6.append(r13)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r5, r6, r7)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.lang.String r6 = "Range"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r7.<init>()     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.lang.String r9 = "bytes="
            r7.append(r9)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r7.append(r13)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.lang.String r9 = "-"
            r7.append(r9)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r5.setRequestProperty(r6, r7)     // Catch:{ Throwable -> 0x02f9, all -> 0x0249 }
            r22 = r8
            goto L_0x0361
        L_0x02f9:
            r0 = move-exception
            r28 = r3
            r22 = r8
            goto L_0x034a
        L_0x02ff:
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0345, all -> 0x0249 }
            r6.<init>()     // Catch:{ Throwable -> 0x0345, all -> 0x0249 }
            java.lang.String r7 = "#1 STEP 1/2 begin downloading...current/total="
            r6.append(r7)     // Catch:{ Throwable -> 0x0345, all -> 0x0249 }
            r6.append(r13)     // Catch:{ Throwable -> 0x0345, all -> 0x0249 }
            java.lang.String r7 = "/"
            r6.append(r7)     // Catch:{ Throwable -> 0x0345, all -> 0x0249 }
            r22 = r8
            long r7 = r1.l     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r6.append(r7)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r5, r6, r7)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r6 = "Range"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r7.<init>()     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r8 = "bytes="
            r7.append(r8)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r7.append(r13)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r8 = "-"
            r7.append(r8)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            long r8 = r1.l     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r5.setRequestProperty(r6, r7)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            goto L_0x0361
        L_0x0345:
            r0 = move-exception
            r22 = r8
        L_0x0348:
            r28 = r3
        L_0x034a:
            r32 = r10
            r8 = r21
            goto L_0x0357
        L_0x034f:
            r0 = move-exception
            r22 = r8
            r28 = r3
            r8 = r7
        L_0x0355:
            r32 = r10
        L_0x0357:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x0ae8
        L_0x035b:
            r21 = r7
            r22 = r8
            r13 = 0
        L_0x0361:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r1.v     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r6 = 0
            int r8 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x036b
            r6 = 0
            goto L_0x036c
        L_0x036b:
            r6 = 1
        L_0x036c:
            r5.setDownloadCancel(r6)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            int r5 = com.tencent.smtt.utils.Apn.getApnType(r5)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            android.content.Context r6 = r1.g     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            java.lang.String r6 = com.tencent.smtt.utils.Apn.getApnInfo(r6)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            java.lang.String r7 = r1.w     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            if (r7 != 0) goto L_0x038b
            int r7 = r1.x     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r8 = -1
            if (r7 != r8) goto L_0x038b
            r1.w = r6     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r1.x = r5     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            goto L_0x03a1
        L_0x0389:
            r0 = move-exception
            goto L_0x0348
        L_0x038b:
            int r7 = r1.x     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            if (r5 != r7) goto L_0x0397
            java.lang.String r7 = r1.w     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            boolean r7 = r6.equals(r7)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r7 != 0) goto L_0x03a1
        L_0x0397:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r7 = r1.v     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r8 = 0
            r7.setNetworkChange(r8)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r1.w = r6     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r1.x = r5     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
        L_0x03a1:
            int r5 = r1.p     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r6 = 1
            if (r5 < r6) goto L_0x03af
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r6 = "Referer"
            java.lang.String r7 = r1.h     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r5.addRequestProperty(r6, r7)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
        L_0x03af:
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            int r5 = r5.getResponseCode()     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            java.lang.String r6 = "TbsDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r7.<init>()     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            java.lang.String r8 = "[TbsApkDownloader.startDownload] responseCode="
            r7.append(r8)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r7.append(r5)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r6 = r1.v     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r6.setHttpCode(r5)     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            r6 = 111(0x6f, float:1.56E-43)
            r7 = 3
            if (r2 != 0) goto L_0x0407
            android.content.Context r8 = r1.g     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            boolean r8 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r8)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r8 != 0) goto L_0x0407
            android.content.Context r8 = r1.g     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            int r8 = com.tencent.smtt.utils.Apn.getApnType(r8)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r8 == r7) goto L_0x03eb
            boolean r8 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r8 == 0) goto L_0x03f3
        L_0x03eb:
            android.content.Context r8 = r1.g     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            int r8 = com.tencent.smtt.utils.Apn.getApnType(r8)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r8 != 0) goto L_0x0407
        L_0x03f3:
            r34.c()     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsListener r8 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r8 == 0) goto L_0x03ff
            com.tencent.smtt.sdk.TbsListener r8 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r8.onDownloadFinish(r6)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
        L_0x03ff:
            java.lang.String r8 = "TbsDownload"
            java.lang.String r9 = "Download is canceled due to NOT_WIFI error!"
            r12 = 0
            com.tencent.smtt.utils.TbsLog.i(r8, r9, r12)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
        L_0x0407:
            boolean r8 = r1.r     // Catch:{ Throwable -> 0x0ad6, all -> 0x0249 }
            if (r8 == 0) goto L_0x0436
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r6 = -309(0xfffffffffffffecb, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r2 != 0) goto L_0x0432
        L_0x0418:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2.put(r5, r3)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
        L_0x0432:
            r8 = r21
            goto L_0x0b50
        L_0x0436:
            r8 = 200(0xc8, float:2.8E-43)
            if (r5 == r8) goto L_0x05de
            r8 = 206(0xce, float:2.89E-43)
            if (r5 != r8) goto L_0x0440
            goto L_0x05de
        L_0x0440:
            r6 = 300(0x12c, float:4.2E-43)
            if (r5 < r6) goto L_0x0493
            r6 = 307(0x133, float:4.3E-43)
            if (r5 > r6) goto L_0x0493
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            java.lang.String r6 = "Location"
            java.lang.String r5 = r5.getHeaderField(r6)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r6 != 0) goto L_0x047e
            r1.j = r5     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            int r5 = r1.q     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r6 = 1
            int r5 = r5 + r6
            r1.q = r5     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r2 != 0) goto L_0x047a
            android.content.Context r5 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.f9091a
            java.lang.String r6 = "tbs_downloadflow"
            java.lang.Long r7 = java.lang.Long.valueOf(r3)
            r5.put(r6, r7)
            android.content.Context r5 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            r5.commit()
        L_0x047a:
            r8 = r21
            goto L_0x066b
        L_0x047e:
            r5 = 124(0x7c, float:1.74E-43)
            r6 = 1
            r7 = 0
            r1.a(r5, r7, r6)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            r6 = -312(0xfffffffffffffec8, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x0389, all -> 0x0249 }
            if (r2 != 0) goto L_0x0432
            goto L_0x0418
        L_0x0493:
            r6 = 102(0x66, float:1.43E-43)
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ Throwable -> 0x05d7, all -> 0x0249 }
            r8 = 0
            r1.a(r6, r7, r8)     // Catch:{ Throwable -> 0x05d7, all -> 0x0249 }
            r6 = 416(0x1a0, float:5.83E-43)
            if (r5 != r6) goto L_0x0510
            r8 = r21
            r6 = 1
            boolean r5 = r1.c(r6, r8)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r5 == 0) goto L_0x04e0
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x04d5, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x04d5, all -> 0x0249 }
            r6 = -214(0xffffffffffffff2a, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x04d5, all -> 0x0249 }
            if (r2 != 0) goto L_0x04d1
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2.put(r5, r3)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
        L_0x04d1:
            r22 = 1
            goto L_0x0b50
        L_0x04d5:
            r0 = move-exception
            r28 = r3
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r22 = 1
            goto L_0x0ae8
        L_0x04e0:
            r5 = 0
            r1.c((boolean) r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -313(0xfffffffffffffec7, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x0b50
        L_0x04f1:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2.put(r5, r3)
        L_0x0502:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
            goto L_0x0b50
        L_0x050d:
            r0 = move-exception
            goto L_0x05da
        L_0x0510:
            r8 = r21
            r6 = 403(0x193, float:5.65E-43)
            if (r5 == r6) goto L_0x051a
            r6 = 406(0x196, float:5.69E-43)
            if (r5 != r6) goto L_0x0530
        L_0x051a:
            long r6 = r1.l     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r12 = -1
            int r9 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x0530
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -314(0xfffffffffffffec6, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x0b50
        L_0x052f:
            goto L_0x04f1
        L_0x0530:
            r6 = 202(0xca, float:2.83E-43)
            if (r5 != r6) goto L_0x0552
            if (r2 != 0) goto L_0x066b
        L_0x0536:
            android.content.Context r5 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.f9091a
            java.lang.String r6 = "tbs_downloadflow"
            java.lang.Long r7 = java.lang.Long.valueOf(r3)
            r5.put(r6, r7)
            android.content.Context r5 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            r5.commit()
            goto L_0x066b
        L_0x0552:
            int r6 = r1.p     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            int r7 = r1.B     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r6 >= r7) goto L_0x0581
            r6 = 503(0x1f7, float:7.05E-43)
            if (r5 != r6) goto L_0x0581
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r6 = "Retry-After"
            java.lang.String r5 = r5.getHeaderField(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            long r5 = java.lang.Long.parseLong(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r1.a((long) r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            boolean r5 = r1.r     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r5 == 0) goto L_0x057e
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -309(0xfffffffffffffecb, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x0b50
            goto L_0x04f1
        L_0x057e:
            if (r2 != 0) goto L_0x066b
            goto L_0x0536
        L_0x0581:
            int r6 = r1.p     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            int r7 = r1.B     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r6 >= r7) goto L_0x05af
            r6 = 408(0x198, float:5.72E-43)
            if (r5 == r6) goto L_0x0595
            r7 = 504(0x1f8, float:7.06E-43)
            if (r5 == r7) goto L_0x0595
            r7 = 502(0x1f6, float:7.03E-43)
            if (r5 == r7) goto L_0x0595
            if (r5 != r6) goto L_0x05af
        L_0x0595:
            r5 = 0
            r1.a((long) r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            boolean r5 = r1.r     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r5 == 0) goto L_0x05ac
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -309(0xfffffffffffffecb, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x0b50
            goto L_0x052f
        L_0x05ac:
            if (r2 != 0) goto L_0x066b
            goto L_0x0536
        L_0x05af:
            long r6 = r34.k()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r12 = 0
            int r9 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r9 > 0) goto L_0x05c8
            boolean r6 = r1.o     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r6 != 0) goto L_0x05c8
            r6 = 410(0x19a, float:5.75E-43)
            if (r5 == r6) goto L_0x05c8
            r5 = 1
            r1.o = r5     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x066b
            goto L_0x0536
        L_0x05c8:
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -315(0xfffffffffffffec5, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x0b50
            goto L_0x04f1
        L_0x05d7:
            r0 = move-exception
            r8 = r21
        L_0x05da:
            r28 = r3
            goto L_0x0355
        L_0x05de:
            r8 = r21
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            int r5 = r5.getContentLength()     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            long r6 = (long) r5     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            long r6 = r6 + r13
            r1.l = r6     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            r6.<init>()     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            java.lang.String r7 = "[TbsApkDownloader.startDownload] mContentLength="
            r6.append(r7)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            r23 = r13
            long r12 = r1.l     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            r6.append(r12)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r1.v     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            long r6 = r1.l     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            r5.setPkgSize(r6)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            android.content.SharedPreferences r5 = r5.mPreferences     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            java.lang.String r6 = "tbs_apkfilesize"
            r12 = 0
            long r5 = r5.getLong(r6, r12)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            int r7 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r7 == 0) goto L_0x06b9
            long r12 = r1.l     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x06b9
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r9.<init>()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r12 = "DownloadBegin tbsApkFileSize="
            r9.append(r12)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r9.append(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r12 = "  but contentLength="
            r9.append(r12)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            long r12 = r1.l     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r9.append(r12)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r12 = 1
            com.tencent.smtt.utils.TbsLog.i(r7, r9, r12)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r2 != 0) goto L_0x06a2
            boolean r7 = r34.n()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r7 != 0) goto L_0x065c
            boolean r7 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r7 == 0) goto L_0x06a2
            android.content.Context r7 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            boolean r7 = com.tencent.smtt.utils.Apn.isNetworkAvailable(r7)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r7 == 0) goto L_0x06a2
        L_0x065c:
            java.lang.String[] r7 = r1.b     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r7 == 0) goto L_0x0675
            r7 = 0
            boolean r9 = r1.a((boolean) r7)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            if (r9 == 0) goto L_0x0675
            if (r2 != 0) goto L_0x066b
            goto L_0x0536
        L_0x066b:
            r7 = r8
            r8 = r22
        L_0x066e:
            r5 = 2
            r6 = 1
            r9 = 0
            r13 = 0
            goto L_0x0162
        L_0x0675:
            r7 = 113(0x71, float:1.58E-43)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r9.<init>()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r12 = "tbsApkFileSize="
            r9.append(r12)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r9.append(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r5 = "  but contentLength="
            r9.append(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            long r5 = r1.l     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r9.append(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            java.lang.String r5 = r9.toString()     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = 1
            r1.a(r7, r5, r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -310(0xfffffffffffffeca, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            goto L_0x06b5
        L_0x06a2:
            r5 = 101(0x65, float:1.42E-43)
            java.lang.String r6 = "WifiNetworkUnAvailable"
            r7 = 1
            r1.a(r5, r6, r7)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
            r6 = -304(0xfffffffffffffed0, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ Throwable -> 0x050d, all -> 0x0249 }
        L_0x06b5:
            if (r2 != 0) goto L_0x0b50
            goto L_0x052f
        L_0x06b9:
            java.lang.String r5 = "TbsDownload"
            java.lang.String r6 = "[TbsApkDownloader.startDownload] begin readResponse"
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ Throwable -> 0x0ad2, all -> 0x0249 }
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ IOException -> 0x09df, all -> 0x09d2 }
            java.io.InputStream r9 = r5.getInputStream()     // Catch:{ IOException -> 0x09df, all -> 0x09d2 }
            if (r9 == 0) goto L_0x098a
            java.net.HttpURLConnection r5 = r1.t     // Catch:{ IOException -> 0x097f, all -> 0x0974 }
            java.lang.String r5 = r5.getContentEncoding()     // Catch:{ IOException -> 0x097f, all -> 0x0974 }
            if (r5 == 0) goto L_0x06f6
            java.lang.String r6 = "gzip"
            boolean r6 = r5.contains(r6)     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            if (r6 == 0) goto L_0x06f6
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            r5.<init>(r9)     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            goto L_0x070d
        L_0x06de:
            r0 = move-exception
            r28 = r3
            r12 = r9
            r32 = r10
            r5 = 0
            r7 = 0
        L_0x06e6:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x09dc
        L_0x06ea:
            r0 = move-exception
            r28 = r3
            r12 = r9
            r32 = r10
            r5 = 0
            r9 = 0
        L_0x06f2:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x09e9
        L_0x06f6:
            if (r5 == 0) goto L_0x070c
            java.lang.String r6 = "deflate"
            boolean r5 = r5.contains(r6)     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            if (r5 == 0) goto L_0x070c
            java.util.zip.InflaterInputStream r5 = new java.util.zip.InflaterInputStream     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            java.util.zip.Inflater r6 = new java.util.zip.Inflater     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            r7 = 1
            r6.<init>(r7)     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            r5.<init>(r9, r6)     // Catch:{ IOException -> 0x06ea, all -> 0x06de }
            goto L_0x070d
        L_0x070c:
            r5 = r9
        L_0x070d:
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x096b, all -> 0x0962 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x096b, all -> 0x0962 }
            java.io.File r12 = new java.io.File     // Catch:{ IOException -> 0x096b, all -> 0x0962 }
            java.io.File r13 = r1.k     // Catch:{ IOException -> 0x096b, all -> 0x0962 }
            java.lang.String r14 = "x5.tbs.temp"
            r12.<init>(r13, r14)     // Catch:{ IOException -> 0x096b, all -> 0x0962 }
            r13 = 1
            r7.<init>(r12, r13)     // Catch:{ IOException -> 0x096b, all -> 0x0962 }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0957, all -> 0x094d }
            r25 = r9
            r14 = r15
            r17 = r23
        L_0x0729:
            boolean r9 = r1.r     // Catch:{ IOException -> 0x0947, all -> 0x0941 }
            if (r9 == 0) goto L_0x075a
            java.lang.String r6 = "TbsDownload"
            java.lang.String r9 = "STEP 1/2 begin downloading...Canceled!"
            r12 = 1
            com.tencent.smtt.utils.TbsLog.i(r6, r9, r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            android.content.Context r6 = r1.g     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r9 = -309(0xfffffffffffffecb, float:NaN)
        L_0x073d:
            r6.setDownloadInterruptCode(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
        L_0x0740:
            r28 = r3
            r32 = r10
            r3 = 0
        L_0x0745:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x08de
        L_0x0749:
            r0 = move-exception
            r28 = r3
        L_0x074c:
            r32 = r10
        L_0x074e:
            r12 = r25
            goto L_0x06e6
        L_0x0751:
            r0 = move-exception
            r28 = r3
            r9 = r7
            r32 = r10
            r12 = r25
            goto L_0x06f2
        L_0x075a:
            r9 = 8192(0x2000, float:1.14794E-41)
            r26 = r12
            r12 = 0
            int r9 = r5.read(r6, r12, r9)     // Catch:{ IOException -> 0x0947, all -> 0x0941 }
            if (r9 > 0) goto L_0x07a1
            java.lang.String[] r6 = r1.b     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            if (r6 == 0) goto L_0x078c
            r6 = 1
            boolean r9 = r1.c(r6, r8)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            if (r9 != 0) goto L_0x078c
            if (r2 != 0) goto L_0x077e
            boolean r6 = r1.a((boolean) r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            if (r6 == 0) goto L_0x077e
            r28 = r3
            r32 = r10
            r3 = 1
            goto L_0x0745
        L_0x077e:
            r6 = 1
            r1.s = r6     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r28 = r3
            r32 = r10
            r3 = 0
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r22 = 0
            goto L_0x08de
        L_0x078c:
            r6 = 1
            r1.s = r6     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String[] r6 = r1.b     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            if (r6 == 0) goto L_0x0795
            r22 = 1
        L_0x0795:
            android.content.Context r6 = r1.g     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r9 = -311(0xfffffffffffffec9, float:NaN)
            r6.setDownloadInterruptCode(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            goto L_0x0740
        L_0x07a1:
            r7.write(r6, r12, r9)     // Catch:{ IOException -> 0x0947, all -> 0x0941 }
            r7.flush()     // Catch:{ IOException -> 0x0947, all -> 0x0941 }
            if (r2 != 0) goto L_0x082c
            long r12 = (long) r9
            long r3 = r3 + r12
            int r12 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x07e3
            java.lang.String r6 = "TbsDownload"
            java.lang.String r9 = "STEP 1/2 begin downloading...failed because you exceeded max flow!"
            r12 = 1
            com.tencent.smtt.utils.TbsLog.i(r6, r9, r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r6.<init>()     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String r9 = "downloadFlow="
            r6.append(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r6.append(r3)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String r9 = " downloadMaxflow="
            r6.append(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r6.append(r10)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r9 = 1
            r12 = 112(0x70, float:1.57E-43)
            r1.a(r12, r6, r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            android.content.Context r6 = r1.g     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r13 = -307(0xfffffffffffffecd, float:NaN)
            r6.setDownloadInterruptCode(r13)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            goto L_0x0740
        L_0x07e3:
            r13 = -307(0xfffffffffffffecd, float:NaN)
            android.content.Context r12 = r1.g     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            boolean r12 = com.tencent.smtt.utils.j.b((android.content.Context) r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            if (r12 != 0) goto L_0x082e
            java.lang.String r6 = "TbsDownload"
            java.lang.String r9 = "DownloadEnd FreeSpace too small "
            r12 = 1
            com.tencent.smtt.utils.TbsLog.i(r6, r9, r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r6.<init>()     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String r9 = "freespace="
            r6.append(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            long r12 = com.tencent.smtt.utils.v.a()     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r6.append(r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String r9 = ",and minFreeSpace="
            r6.append(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            android.content.Context r9 = r1.g     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            long r12 = r9.getDownloadMinFreeSpace()     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r6.append(r12)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r9 = 1
            r12 = 105(0x69, float:1.47E-43)
            r1.a(r12, r6, r9)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            android.content.Context r6 = r1.g     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)     // Catch:{ IOException -> 0x0751, all -> 0x0749 }
            r9 = -308(0xfffffffffffffecc, float:NaN)
            goto L_0x073d
        L_0x082c:
            r13 = -307(0xfffffffffffffecd, float:NaN)
        L_0x082e:
            r28 = r3
            long r3 = (long) r9
            long r14 = r1.a((long) r14, (long) r3)     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            long r19 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            r9 = 0
            long r3 = r23 + r3
            long r23 = r19 - r26
            r30 = 1000(0x3e8, double:4.94E-321)
            int r9 = (r23 > r30 ? 1 : (r23 == r30 ? 0 : -1))
            if (r9 <= 0) goto L_0x091e
            java.lang.String r9 = "TbsDownload"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            r12.<init>()     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            java.lang.String r13 = "#2 STEP 1/2 begin downloading...current/total="
            r12.append(r13)     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            r12.append(r3)     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            java.lang.String r13 = "/"
            r12.append(r13)     // Catch:{ IOException -> 0x0936, all -> 0x0933 }
            r32 = r10
            long r10 = r1.l     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            r12.append(r10)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            java.lang.String r10 = r12.toString()     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            r11 = 1
            com.tencent.smtt.utils.TbsLog.i(r9, r10, r11)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            com.tencent.smtt.sdk.TbsListener r9 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            if (r9 == 0) goto L_0x0892
            double r9 = (double) r3
            long r11 = r1.l     // Catch:{ IOException -> 0x0889, all -> 0x0881 }
            double r11 = (double) r11
            java.lang.Double.isNaN(r9)
            java.lang.Double.isNaN(r11)
            double r9 = r9 / r11
            r11 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r9 = r9 * r11
            int r9 = (int) r9
            com.tencent.smtt.sdk.TbsListener r10 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0889, all -> 0x0881 }
            r10.onDownloadProgress(r9)     // Catch:{ IOException -> 0x0889, all -> 0x0881 }
            goto L_0x0892
        L_0x0881:
            r0 = move-exception
            r3 = r0
            r12 = r25
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x0ac6
        L_0x0889:
            r0 = move-exception
            r3 = r0
            r9 = r7
            r12 = r25
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x09ea
        L_0x0892:
            if (r2 != 0) goto L_0x090f
            long r9 = r3 - r17
            r11 = 1048576(0x100000, double:5.180654E-318)
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x090f
            android.content.Context r9 = r1.g     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            boolean r9 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r9)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            if (r9 != 0) goto L_0x0905
            android.content.Context r9 = r1.g     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            int r9 = com.tencent.smtt.utils.Apn.getApnType(r9)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            r10 = 3
            if (r9 == r10) goto L_0x08b4
            boolean r9 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ IOException -> 0x0889, all -> 0x0881 }
            if (r9 == 0) goto L_0x08bc
        L_0x08b4:
            android.content.Context r9 = r1.g     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            int r9 = com.tencent.smtt.utils.Apn.getApnType(r9)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            if (r9 != 0) goto L_0x0900
        L_0x08bc:
            r34.c()     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            if (r3 == 0) goto L_0x08ca
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0889, all -> 0x0881 }
            r9 = 111(0x6f, float:1.56E-43)
            r3.onDownloadFinish(r9)     // Catch:{ IOException -> 0x0889, all -> 0x0881 }
        L_0x08ca:
            java.lang.String r3 = "TbsDownload"
            java.lang.String r4 = "Download is paused due to NOT_WIFI error!"
            r6 = 0
            com.tencent.smtt.utils.TbsLog.i(r3, r4, r6)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            android.content.Context r3 = r1.g     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ IOException -> 0x091c, all -> 0x0919 }
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r3.setDownloadInterruptCode(r11)     // Catch:{ IOException -> 0x08fc, all -> 0x08f7 }
            r3 = 0
        L_0x08de:
            if (r3 == 0) goto L_0x08ef
            r1.a((java.io.Closeable) r7)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r5)     // Catch:{ Throwable -> 0x0ad0 }
            r12 = r25
            r1.a((java.io.Closeable) r12)     // Catch:{ Throwable -> 0x0ad0 }
            if (r2 != 0) goto L_0x0aba
            goto L_0x0aa0
        L_0x08ef:
            r12 = r25
            r6 = r22
            r3 = r28
            goto L_0x0993
        L_0x08f7:
            r0 = move-exception
            r12 = r25
            goto L_0x09dc
        L_0x08fc:
            r0 = move-exception
            r12 = r25
            goto L_0x093d
        L_0x0900:
            r12 = r25
            r9 = 111(0x6f, float:1.56E-43)
            goto L_0x090a
        L_0x0905:
            r12 = r25
            r9 = 111(0x6f, float:1.56E-43)
            r10 = 3
        L_0x090a:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r17 = r3
            goto L_0x0916
        L_0x090f:
            r12 = r25
            r9 = 111(0x6f, float:1.56E-43)
            r10 = 3
            r11 = -304(0xfffffffffffffed0, float:NaN)
        L_0x0916:
            r26 = r19
            goto L_0x0927
        L_0x0919:
            r0 = move-exception
            goto L_0x074e
        L_0x091c:
            r0 = move-exception
            goto L_0x0939
        L_0x091e:
            r32 = r10
            r12 = r25
            r9 = 111(0x6f, float:1.56E-43)
            r10 = 3
            r11 = -304(0xfffffffffffffed0, float:NaN)
        L_0x0927:
            r23 = r3
            r25 = r12
            r12 = r26
            r3 = r28
            r10 = r32
            goto L_0x0729
        L_0x0933:
            r0 = move-exception
            goto L_0x074c
        L_0x0936:
            r0 = move-exception
            r32 = r10
        L_0x0939:
            r12 = r25
            r11 = -304(0xfffffffffffffed0, float:NaN)
        L_0x093d:
            r3 = r0
            r9 = r7
            goto L_0x09ea
        L_0x0941:
            r0 = move-exception
            r32 = r10
            r12 = r25
            goto L_0x0951
        L_0x0947:
            r0 = move-exception
            r32 = r10
            r12 = r25
            goto L_0x095b
        L_0x094d:
            r0 = move-exception
            r12 = r9
            r32 = r10
        L_0x0951:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            goto L_0x09dc
        L_0x0957:
            r0 = move-exception
            r12 = r9
            r32 = r10
        L_0x095b:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            r9 = r7
            goto L_0x09e9
        L_0x0962:
            r0 = move-exception
            r12 = r9
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            goto L_0x097d
        L_0x096b:
            r0 = move-exception
            r12 = r9
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            goto L_0x0988
        L_0x0974:
            r0 = move-exception
            r12 = r9
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            r5 = 0
        L_0x097d:
            r7 = 0
            goto L_0x09dc
        L_0x097f:
            r0 = move-exception
            r12 = r9
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            r5 = 0
        L_0x0988:
            r9 = 0
            goto L_0x09e9
        L_0x098a:
            r12 = r9
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r6 = r22
            r5 = 0
            r7 = 0
        L_0x0993:
            r1.a((java.io.Closeable) r7)     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
            r1.a((java.io.Closeable) r5)     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
            r1.a((java.io.Closeable) r12)     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
            boolean r5 = r1.s     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
            if (r5 != 0) goto L_0x09ab
            android.content.Context r5 = r1.g     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
            r7 = -319(0xfffffffffffffec1, float:NaN)
            r5.setDownloadInterruptCode(r7)     // Catch:{ Throwable -> 0x09cb, all -> 0x0249 }
        L_0x09ab:
            if (r2 != 0) goto L_0x09c7
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2.put(r5, r3)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
        L_0x09c7:
            r22 = r6
            goto L_0x0b50
        L_0x09cb:
            r0 = move-exception
            r28 = r3
            r22 = r6
            goto L_0x0ae8
        L_0x09d2:
            r0 = move-exception
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            r5 = 0
            r7 = 0
            r12 = 0
        L_0x09dc:
            r3 = r0
            goto L_0x0ac6
        L_0x09df:
            r0 = move-exception
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r28 = r3
            r5 = 0
            r9 = 0
            r12 = 0
        L_0x09e9:
            r3 = r0
        L_0x09ea:
            r3.printStackTrace()     // Catch:{ all -> 0x0ac3 }
            boolean r4 = r3 instanceof java.net.SocketTimeoutException     // Catch:{ all -> 0x0ac3 }
            if (r4 != 0) goto L_0x0a81
            boolean r4 = r3 instanceof java.net.SocketException     // Catch:{ all -> 0x0ac3 }
            if (r4 == 0) goto L_0x09f7
            goto L_0x0a81
        L_0x09f7:
            if (r2 != 0) goto L_0x0a57
            android.content.Context r4 = r1.g     // Catch:{ all -> 0x0ac3 }
            boolean r4 = com.tencent.smtt.utils.j.b((android.content.Context) r4)     // Catch:{ all -> 0x0ac3 }
            if (r4 != 0) goto L_0x0a57
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0ac3 }
            r3.<init>()     // Catch:{ all -> 0x0ac3 }
            java.lang.String r4 = "freespace="
            r3.append(r4)     // Catch:{ all -> 0x0ac3 }
            long r6 = com.tencent.smtt.utils.v.a()     // Catch:{ all -> 0x0ac3 }
            r3.append(r6)     // Catch:{ all -> 0x0ac3 }
            java.lang.String r4 = ",and minFreeSpace="
            r3.append(r4)     // Catch:{ all -> 0x0ac3 }
            android.content.Context r4 = r1.g     // Catch:{ all -> 0x0ac3 }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ all -> 0x0ac3 }
            long r6 = r4.getDownloadMinFreeSpace()     // Catch:{ all -> 0x0ac3 }
            r3.append(r6)     // Catch:{ all -> 0x0ac3 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0ac3 }
            r4 = 1
            r6 = 105(0x69, float:1.47E-43)
            r1.a(r6, r3, r4)     // Catch:{ all -> 0x0ac3 }
            android.content.Context r3 = r1.g     // Catch:{ all -> 0x0ac3 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0ac3 }
            r4 = -308(0xfffffffffffffecc, float:NaN)
            r3.setDownloadInterruptCode(r4)     // Catch:{ all -> 0x0ac3 }
            r1.a((java.io.Closeable) r9)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r5)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r12)     // Catch:{ Throwable -> 0x0ad0 }
            if (r2 != 0) goto L_0x0b50
        L_0x0a44:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.String r3 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r28)
            r2.put(r3, r4)
            goto L_0x0502
        L_0x0a57:
            r6 = 0
            r1.a((long) r6)     // Catch:{ all -> 0x0ac3 }
            boolean r4 = r34.j()     // Catch:{ all -> 0x0ac3 }
            if (r4 != 0) goto L_0x0a6d
            r4 = 106(0x6a, float:1.49E-43)
            java.lang.String r3 = r1.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0ac3 }
            r6 = 0
        L_0x0a69:
            r1.a(r4, r3, r6)     // Catch:{ all -> 0x0ac3 }
            goto L_0x0a75
        L_0x0a6d:
            r6 = 0
            r4 = 104(0x68, float:1.46E-43)
            java.lang.String r3 = r1.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0ac3 }
            goto L_0x0a69
        L_0x0a75:
            r1.a((java.io.Closeable) r9)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r5)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r12)     // Catch:{ Throwable -> 0x0ad0 }
            if (r2 != 0) goto L_0x0aba
            goto L_0x0aa0
        L_0x0a81:
            r4 = 100000(0x186a0, float:1.4013E-40)
            r1.m = r4     // Catch:{ all -> 0x0ac3 }
            r6 = 0
            r1.a((long) r6)     // Catch:{ all -> 0x0ac3 }
            r4 = 103(0x67, float:1.44E-43)
            java.lang.String r3 = r1.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0ac3 }
            r6 = 0
            r1.a(r4, r3, r6)     // Catch:{ all -> 0x0ac3 }
            r1.a((java.io.Closeable) r9)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r5)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r12)     // Catch:{ Throwable -> 0x0ad0 }
            if (r2 != 0) goto L_0x0aba
        L_0x0aa0:
            android.content.Context r3 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r3.f9091a
            java.lang.String r4 = "tbs_downloadflow"
            java.lang.Long r5 = java.lang.Long.valueOf(r28)
            r3.put(r4, r5)
            android.content.Context r3 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            r3.commit()
        L_0x0aba:
            r7 = r8
            r8 = r22
            r3 = r28
            r10 = r32
            goto L_0x066e
        L_0x0ac3:
            r0 = move-exception
            r3 = r0
            r7 = r9
        L_0x0ac6:
            r1.a((java.io.Closeable) r7)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r5)     // Catch:{ Throwable -> 0x0ad0 }
            r1.a((java.io.Closeable) r12)     // Catch:{ Throwable -> 0x0ad0 }
            throw r3     // Catch:{ Throwable -> 0x0ad0 }
        L_0x0ad0:
            r0 = move-exception
            goto L_0x0ae8
        L_0x0ad2:
            r0 = move-exception
            r32 = r10
            goto L_0x0adb
        L_0x0ad6:
            r0 = move-exception
            r32 = r10
            r8 = r21
        L_0x0adb:
            r11 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x0ae6
        L_0x0ade:
            r0 = move-exception
            r22 = r8
            r32 = r10
            r11 = -304(0xfffffffffffffed0, float:NaN)
            r8 = r7
        L_0x0ae6:
            r28 = r3
        L_0x0ae8:
            r3 = r0
        L_0x0ae9:
            boolean r4 = r3 instanceof javax.net.ssl.SSLHandshakeException     // Catch:{ all -> 0x0c2c }
            if (r4 == 0) goto L_0x0b2a
            if (r2 != 0) goto L_0x0b2a
            java.lang.String[] r4 = r1.b     // Catch:{ all -> 0x0c2c }
            if (r4 == 0) goto L_0x0b2a
            r4 = 0
            boolean r5 = r1.a((boolean) r4)     // Catch:{ all -> 0x0c2c }
            if (r5 == 0) goto L_0x0b2a
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0c2c }
            r5.<init>()     // Catch:{ all -> 0x0c2c }
            java.lang.String r6 = "[startdownload]url:"
            r5.append(r6)     // Catch:{ all -> 0x0c2c }
            java.lang.String r6 = r1.j     // Catch:{ all -> 0x0c2c }
            r5.append(r6)     // Catch:{ all -> 0x0c2c }
            java.lang.String r6 = " download exception："
            r5.append(r6)     // Catch:{ all -> 0x0c2c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0c2c }
            r5.append(r3)     // Catch:{ all -> 0x0c2c }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0c2c }
            com.tencent.smtt.utils.TbsLog.e(r4, r3)     // Catch:{ all -> 0x0c2c }
            r3 = 125(0x7d, float:1.75E-43)
            r4 = 1
            r5 = 0
            r1.a(r3, r5, r4)     // Catch:{ all -> 0x0c2c }
            r3 = 0
            r6 = 0
            goto L_0x0bf6
        L_0x0b2a:
            r5 = 0
            r3.printStackTrace()     // Catch:{ all -> 0x0c2c }
            r6 = 0
            r1.a((long) r6)     // Catch:{ all -> 0x0c2c }
            r4 = 107(0x6b, float:1.5E-43)
            java.lang.String r3 = r1.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0c2c }
            r9 = 0
            r1.a(r4, r3, r9)     // Catch:{ all -> 0x0c2c }
            boolean r3 = r1.r     // Catch:{ all -> 0x0c2c }
            if (r3 == 0) goto L_0x0bf5
            android.content.Context r3 = r1.g     // Catch:{ all -> 0x0c2c }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0c2c }
            r4 = -309(0xfffffffffffffecb, float:NaN)
            r3.setDownloadInterruptCode(r4)     // Catch:{ all -> 0x0c2c }
            if (r2 != 0) goto L_0x0b50
            goto L_0x0a44
        L_0x0b50:
            boolean r2 = r1.r
            if (r2 != 0) goto L_0x0bf1
            boolean r2 = r1.s
            if (r2 == 0) goto L_0x0ba6
            java.lang.String[] r2 = r1.b
            if (r2 != 0) goto L_0x0b63
            if (r22 != 0) goto L_0x0b63
            r2 = 1
            boolean r22 = r1.c(r2, r8)
        L_0x0b63:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.v
            if (r22 == 0) goto L_0x0b69
            r3 = 1
            goto L_0x0b6a
        L_0x0b69:
            r3 = 0
        L_0x0b6a:
            r2.setUnpkgFlag(r3)
            if (r8 != 0) goto L_0x0b77
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.v
            if (r22 == 0) goto L_0x0b75
            r3 = 1
            goto L_0x0b7a
        L_0x0b75:
            r3 = 2
            goto L_0x0b7a
        L_0x0b77:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.v
            r3 = 0
        L_0x0b7a:
            r2.setPatchUpdateFlag(r3)
            if (r22 == 0) goto L_0x0b96
            r2 = 1
            r1.b((boolean) r2)
            android.content.Context r3 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            r4 = -317(0xfffffffffffffec3, float:NaN)
            r3.setDownloadInterruptCode(r4)
            r3 = 100
            java.lang.String r4 = "success"
            r1.a(r3, r4, r2)
            goto L_0x0ba6
        L_0x0b96:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -318(0xfffffffffffffec2, float:NaN)
            r2.setDownloadInterruptCode(r3)
            r3 = 0
            r1.c((boolean) r3)
            goto L_0x0ba7
        L_0x0ba6:
            r3 = 0
        L_0x0ba7:
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            if (r22 == 0) goto L_0x0bc5
            android.content.SharedPreferences r4 = r2.mPreferences
            java.lang.String r5 = "tbs_download_success_retrytimes"
            int r4 = r4.getInt(r5, r3)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r2.f9091a
            java.lang.String r6 = "tbs_download_success_retrytimes"
            r9 = 1
            int r4 = r4 + r9
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.put(r6, r4)
            goto L_0x0be6
        L_0x0bc5:
            r9 = 1
            android.content.SharedPreferences r4 = r2.mPreferences
            java.lang.String r5 = "tbs_download_failed_retrytimes"
            int r4 = r4.getInt(r5, r3)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r2.f9091a
            java.lang.String r6 = "tbs_download_failed_retrytimes"
            int r4 = r4 + r9
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r5.put(r6, r7)
            int r5 = r2.getDownloadFailedMaxRetrytimes()
            if (r4 != r5) goto L_0x0be6
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r4 = r1.v
            r10 = 2
            r4.setDownloadCancel(r10)
        L_0x0be6:
            r2.commit()
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.v
            if (r22 == 0) goto L_0x0bee
            r3 = 1
        L_0x0bee:
            r2.setDownFinalFlag(r3)
        L_0x0bf1:
            r34.g()
            return
        L_0x0bf5:
            r3 = 0
        L_0x0bf6:
            r9 = 1
            r10 = 2
            android.content.Context r4 = r1.g     // Catch:{ all -> 0x0c2c }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ all -> 0x0c2c }
            r12 = -316(0xfffffffffffffec4, float:NaN)
            r4.setDownloadInterruptCode(r12)     // Catch:{ all -> 0x0c2c }
            if (r2 != 0) goto L_0x0c1f
            android.content.Context r4 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r4.f9091a
            java.lang.String r12 = "tbs_downloadflow"
            java.lang.Long r13 = java.lang.Long.valueOf(r28)
            r4.put(r12, r13)
            android.content.Context r4 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
        L_0x0c1f:
            r9 = r5
            r13 = r6
            r7 = r8
            r8 = r22
            r3 = r28
            r10 = r32
            r5 = 2
            r6 = 1
            goto L_0x0162
        L_0x0c2c:
            r0 = move-exception
            goto L_0x024c
        L_0x0c2f:
            if (r2 != 0) goto L_0x0c4b
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.f9091a
            java.lang.Long r4 = java.lang.Long.valueOf(r28)
            java.lang.String r5 = "tbs_downloadflow"
            r2.put(r5, r4)
            android.content.Context r2 = r1.g
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
        L_0x0c4b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ag.b(boolean, boolean):void");
    }

    public void c() {
        TbsLogReport a2;
        TbsLogReport.EventType eventType;
        this.r = true;
        if (TbsShareManager.isThirdPartyApp(this.g)) {
            TbsLogReport.TbsLogInfo a3 = TbsLogReport.a(this.g).a();
            a3.setErrorCode(-309);
            a3.setFailDetail((Throwable) new Exception());
            if (TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1) {
                a2 = TbsLogReport.a(this.g);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
            } else {
                a2 = TbsLogReport.a(this.g);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
            }
            a2.a(eventType, a3);
        }
    }

    public void d() {
        c();
        c(false);
        c(true);
    }

    public boolean e() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.C);
        return this.C;
    }
}
