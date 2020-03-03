package com.payu.magicretry.analytics;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CBAnalytics {
    private static final boolean DEBUG = false;
    private static final String FILE_NAME = "mr_local_cache";
    private static CBAnalytics INSTANCE = null;
    private static final String PRODUCTION_URL = "https://info.payu.in/merchant/postservice.php";
    private static final String TEST_URL = "https://mobiledev.payu.in/merchant/postservice.php";
    private static final long TIMER_DELAY = 5000;
    private static MixpanelAPI mixpanel = null;
    private static final String projectToken = "68dbbac2c25bc048154999d13cb77a55";
    /* access modifiers changed from: private */
    public String ANALYTICS_URL = PRODUCTION_URL;
    /* access modifiers changed from: private */
    public final Activity mActivity;
    /* access modifiers changed from: private */
    public ArrayList<String> mBuffer = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean mIsLocked = false;
    private Timer mTimer;

    private CBAnalytics(Activity activity) {
        this.mActivity = activity;
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                do {
                } while (CBAnalytics.this.mIsLocked);
                CBAnalytics.this.setLock();
                try {
                    FileOutputStream openFileOutput = CBAnalytics.this.mActivity.openFileOutput(CBAnalytics.FILE_NAME, 0);
                    int size = CBAnalytics.this.mBuffer.size();
                    for (int i = 0; i < size; i++) {
                        openFileOutput.write((((String) CBAnalytics.this.mBuffer.get(i)) + "\r\n").getBytes());
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CBAnalytics.this.releaseLock();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public static synchronized CBAnalytics getInstance(Activity activity) {
        CBAnalytics cBAnalytics;
        synchronized (CBAnalytics.class) {
            if (INSTANCE == null) {
                INSTANCE = new CBAnalytics(activity);
            }
            cBAnalytics = INSTANCE;
        }
        return cBAnalytics;
    }

    public void log(final String str) {
        resetTimer();
        if (this.mIsLocked) {
            this.mBuffer.add(str);
            return;
        }
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                CBAnalytics.this.setLock();
                String str = "";
                try {
                    if (!new File(CBAnalytics.this.mActivity.getFilesDir(), CBAnalytics.FILE_NAME).exists()) {
                        CBAnalytics.this.mActivity.openFileOutput(CBAnalytics.FILE_NAME, 0);
                    }
                    FileInputStream openFileInput = CBAnalytics.this.mActivity.openFileInput(CBAnalytics.FILE_NAME);
                    while (true) {
                        int read = openFileInput.read();
                        if (read == -1) {
                            break;
                        }
                        str = str + Character.toString((char) read);
                    }
                    openFileInput.close();
                    FileOutputStream openFileOutput = CBAnalytics.this.mActivity.openFileOutput(CBAnalytics.FILE_NAME, 0);
                    openFileOutput.write((str + str + "\r\n").getBytes());
                    openFileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    CBAnalytics.this.mBuffer.add(str);
                }
                CBAnalytics.this.releaseLock();
                return null;
            }
        }.execute(new Void[]{null, null, null});
    }

    /* access modifiers changed from: private */
    public void resetTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() {
            /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            public void run() {
                /*
                    r12 = this;
                L_0x0000:
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    boolean r0 = r0.mIsLocked
                    if (r0 == 0) goto L_0x0009
                    goto L_0x0000
                L_0x0009:
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    r0.setLock()
                    java.lang.String r0 = ""
                    r1 = 200(0xc8, float:2.8E-43)
                    r2 = 1024(0x400, float:1.435E-42)
                    r3 = 1
                    r4 = 0
                    r5 = -1
                    java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x015f }
                    com.payu.magicretry.analytics.CBAnalytics r7 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ IOException -> 0x015f }
                    android.app.Activity r7 = r7.mActivity     // Catch:{ IOException -> 0x015f }
                    java.io.File r7 = r7.getFilesDir()     // Catch:{ IOException -> 0x015f }
                    java.lang.String r8 = "mr_local_cache"
                    r6.<init>(r7, r8)     // Catch:{ IOException -> 0x015f }
                    boolean r6 = r6.exists()     // Catch:{ IOException -> 0x015f }
                    if (r6 != 0) goto L_0x0039
                    com.payu.magicretry.analytics.CBAnalytics r6 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ IOException -> 0x015f }
                    android.app.Activity r6 = r6.mActivity     // Catch:{ IOException -> 0x015f }
                    java.lang.String r7 = "mr_local_cache"
                    r6.openFileOutput(r7, r4)     // Catch:{ IOException -> 0x015f }
                L_0x0039:
                    com.payu.magicretry.analytics.CBAnalytics r6 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ IOException -> 0x015f }
                    android.app.Activity r6 = r6.mActivity     // Catch:{ IOException -> 0x015f }
                    java.lang.String r7 = "mr_local_cache"
                    java.io.FileInputStream r6 = r6.openFileInput(r7)     // Catch:{ IOException -> 0x015f }
                L_0x0045:
                    int r7 = r6.read()     // Catch:{ IOException -> 0x015f }
                    if (r7 == r5) goto L_0x0061
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x015f }
                    r8.<init>()     // Catch:{ IOException -> 0x015f }
                    r8.append(r0)     // Catch:{ IOException -> 0x015f }
                    char r7 = (char) r7     // Catch:{ IOException -> 0x015f }
                    java.lang.String r7 = java.lang.Character.toString(r7)     // Catch:{ IOException -> 0x015f }
                    r8.append(r7)     // Catch:{ IOException -> 0x015f }
                    java.lang.String r7 = r8.toString()     // Catch:{ IOException -> 0x015f }
                    r0 = r7
                    goto L_0x0045
                L_0x0061:
                    r6.close()     // Catch:{ IOException -> 0x015f }
                    com.payu.magicretry.analytics.CBAnalytics r6 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r6 = r6.mBuffer
                    int r6 = r6.size()
                L_0x006e:
                    if (r6 <= 0) goto L_0x00aa
                    int r6 = r6 + -1
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder
                    r7.<init>()
                    r7.append(r0)
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    java.lang.Object r0 = r0.get(r6)
                    java.lang.String r0 = (java.lang.String) r0
                    r7.append(r0)
                    java.lang.String r0 = "\r\n"
                    r7.append(r0)
                    java.lang.String r0 = r7.toString()
                    if (r6 < 0) goto L_0x006e
                    com.payu.magicretry.analytics.CBAnalytics r7 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r7 = r7.mBuffer
                    int r7 = r7.size()
                    if (r7 <= r6) goto L_0x006e
                    com.payu.magicretry.analytics.CBAnalytics r7 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r7 = r7.mBuffer
                    r7.remove(r6)
                    goto L_0x006e
                L_0x00aa:
                    java.lang.String r0 = r0.trim()
                    int r6 = r0.length()
                    if (r6 <= 0) goto L_0x0245
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    java.lang.String r7 = "command=sdkWs&var1="
                    r6.append(r7)
                    r6.append(r0)
                    java.lang.String r6 = r6.toString()
                    java.lang.String r7 = "UTF-8"
                    byte[] r7 = r6.getBytes(r7)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.net.URL r8 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    com.payu.magicretry.analytics.CBAnalytics r9 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = r9.ANALYTICS_URL     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r8.<init>(r9)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.net.URLConnection r8 = r8.openConnection()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = "POST"
                    r8.setRequestMethod(r9)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = "Content-Type"
                    java.lang.String r10 = "application/x-www-form-urlencoded"
                    r8.setRequestProperty(r9, r10)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = "Content-Length"
                    int r6 = r6.length()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r8.setRequestProperty(r9, r6)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r8.setDoOutput(r3)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.io.OutputStream r3 = r8.getOutputStream()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r3.write(r7)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    int r3 = r8.getResponseCode()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.io.InputStream r6 = r8.getInputStream()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r7.<init>()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    byte[] r2 = new byte[r2]     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                L_0x010e:
                    int r8 = r6.read(r2)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    if (r8 == r5) goto L_0x011d
                    java.lang.String r9 = new java.lang.String     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r9.<init>(r2, r4, r8)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r7.append(r9)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    goto L_0x010e
                L_0x011d:
                    if (r3 != r1) goto L_0x012c
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    android.app.Activity r0 = r0.mActivity     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r1 = "mr_local_cache"
                    r0.deleteFile(r1)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    goto L_0x0245
                L_0x012c:
                    com.payu.magicretry.analytics.CBAnalytics r1 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ IOException -> 0x0144, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    android.app.Activity r1 = r1.mActivity     // Catch:{ IOException -> 0x0144, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    java.lang.String r2 = "mr_local_cache"
                    java.io.FileOutputStream r1 = r1.openFileOutput(r2, r4)     // Catch:{ IOException -> 0x0144, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0144, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    r1.write(r0)     // Catch:{ IOException -> 0x0144, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    r1.close()     // Catch:{ IOException -> 0x0144, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    goto L_0x0245
                L_0x0144:
                    r0 = move-exception
                    r0.printStackTrace()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    goto L_0x0245
                L_0x014a:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x0245
                L_0x0150:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x0245
                L_0x0156:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x0245
                L_0x015c:
                    r6 = move-exception
                    goto L_0x025c
                L_0x015f:
                    r6 = move-exception
                    r6.printStackTrace()     // Catch:{ all -> 0x015c }
                    com.payu.magicretry.analytics.CBAnalytics r6 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r6 = r6.mBuffer
                    int r6 = r6.size()
                L_0x016d:
                    if (r6 <= 0) goto L_0x01a9
                    int r6 = r6 + -1
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder
                    r7.<init>()
                    r7.append(r0)
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    java.lang.Object r0 = r0.get(r6)
                    java.lang.String r0 = (java.lang.String) r0
                    r7.append(r0)
                    java.lang.String r0 = "\r\n"
                    r7.append(r0)
                    java.lang.String r0 = r7.toString()
                    if (r6 < 0) goto L_0x016d
                    com.payu.magicretry.analytics.CBAnalytics r7 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r7 = r7.mBuffer
                    int r7 = r7.size()
                    if (r7 <= r6) goto L_0x016d
                    com.payu.magicretry.analytics.CBAnalytics r7 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r7 = r7.mBuffer
                    r7.remove(r6)
                    goto L_0x016d
                L_0x01a9:
                    java.lang.String r0 = r0.trim()
                    int r6 = r0.length()
                    if (r6 <= 0) goto L_0x0245
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    java.lang.String r7 = "command=sdkWs&var1="
                    r6.append(r7)
                    r6.append(r0)
                    java.lang.String r6 = r6.toString()
                    java.lang.String r7 = "UTF-8"
                    byte[] r7 = r6.getBytes(r7)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.net.URL r8 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    com.payu.magicretry.analytics.CBAnalytics r9 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = r9.ANALYTICS_URL     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r8.<init>(r9)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.net.URLConnection r8 = r8.openConnection()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = "POST"
                    r8.setRequestMethod(r9)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = "Content-Type"
                    java.lang.String r10 = "application/x-www-form-urlencoded"
                    r8.setRequestProperty(r9, r10)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r9 = "Content-Length"
                    int r6 = r6.length()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r8.setRequestProperty(r9, r6)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r8.setDoOutput(r3)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.io.OutputStream r3 = r8.getOutputStream()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r3.write(r7)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    int r3 = r8.getResponseCode()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.io.InputStream r6 = r8.getInputStream()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r7.<init>()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    byte[] r2 = new byte[r2]     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                L_0x020d:
                    int r8 = r6.read(r2)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    if (r8 == r5) goto L_0x021c
                    java.lang.String r9 = new java.lang.String     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r9.<init>(r2, r4, r8)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    r7.append(r9)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    goto L_0x020d
                L_0x021c:
                    if (r3 != r1) goto L_0x022a
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    android.app.Activity r0 = r0.mActivity     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    java.lang.String r1 = "mr_local_cache"
                    r0.deleteFile(r1)     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                    goto L_0x0245
                L_0x022a:
                    com.payu.magicretry.analytics.CBAnalytics r1 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ IOException -> 0x0241, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    android.app.Activity r1 = r1.mActivity     // Catch:{ IOException -> 0x0241, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    java.lang.String r2 = "mr_local_cache"
                    java.io.FileOutputStream r1 = r1.openFileOutput(r2, r4)     // Catch:{ IOException -> 0x0241, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0241, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    r1.write(r0)     // Catch:{ IOException -> 0x0241, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    r1.close()     // Catch:{ IOException -> 0x0241, MalformedURLException -> 0x0156, ProtocolException -> 0x0150 }
                    goto L_0x0245
                L_0x0241:
                    r0 = move-exception
                    r0.printStackTrace()     // Catch:{ MalformedURLException -> 0x0156, ProtocolException -> 0x0150, IOException -> 0x014a }
                L_0x0245:
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    int r0 = r0.size()
                    if (r0 <= 0) goto L_0x0256
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    r0.resetTimer()
                L_0x0256:
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    r0.releaseLock()
                    return
                L_0x025c:
                    com.payu.magicretry.analytics.CBAnalytics r7 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r7 = r7.mBuffer
                    int r7 = r7.size()
                L_0x0266:
                    if (r7 <= 0) goto L_0x02a2
                    int r7 = r7 + -1
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder
                    r8.<init>()
                    r8.append(r0)
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    java.lang.Object r0 = r0.get(r7)
                    java.lang.String r0 = (java.lang.String) r0
                    r8.append(r0)
                    java.lang.String r0 = "\r\n"
                    r8.append(r0)
                    java.lang.String r0 = r8.toString()
                    if (r7 < 0) goto L_0x0266
                    com.payu.magicretry.analytics.CBAnalytics r8 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r8 = r8.mBuffer
                    int r8 = r8.size()
                    if (r8 <= r7) goto L_0x0266
                    com.payu.magicretry.analytics.CBAnalytics r8 = com.payu.magicretry.analytics.CBAnalytics.this
                    java.util.ArrayList r8 = r8.mBuffer
                    r8.remove(r7)
                    goto L_0x0266
                L_0x02a2:
                    java.lang.String r0 = r0.trim()
                    int r7 = r0.length()
                    if (r7 <= 0) goto L_0x034d
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder
                    r7.<init>()
                    java.lang.String r8 = "command=sdkWs&var1="
                    r7.append(r8)
                    r7.append(r0)
                    java.lang.String r7 = r7.toString()
                    java.lang.String r8 = "UTF-8"
                    byte[] r8 = r7.getBytes(r8)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.net.URL r9 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    com.payu.magicretry.analytics.CBAnalytics r10 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.String r10 = r10.ANALYTICS_URL     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r9.<init>(r10)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.net.URLConnection r9 = r9.openConnection()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.String r10 = "POST"
                    r9.setRequestMethod(r10)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.String r10 = "Content-Type"
                    java.lang.String r11 = "application/x-www-form-urlencoded"
                    r9.setRequestProperty(r10, r11)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.String r10 = "Content-Length"
                    int r7 = r7.length()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r9.setRequestProperty(r10, r7)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r9.setDoOutput(r3)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.io.OutputStream r3 = r9.getOutputStream()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r3.write(r8)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    int r3 = r9.getResponseCode()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.io.InputStream r7 = r9.getInputStream()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.StringBuffer r8 = new java.lang.StringBuffer     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r8.<init>()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    byte[] r2 = new byte[r2]     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                L_0x0306:
                    int r9 = r7.read(r2)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    if (r9 == r5) goto L_0x0315
                    java.lang.String r10 = new java.lang.String     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r10.<init>(r2, r4, r9)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    r8.append(r10)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    goto L_0x0306
                L_0x0315:
                    if (r3 != r1) goto L_0x0323
                    com.payu.magicretry.analytics.CBAnalytics r0 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    android.app.Activity r0 = r0.mActivity     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    java.lang.String r1 = "mr_local_cache"
                    r0.deleteFile(r1)     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    goto L_0x034d
                L_0x0323:
                    com.payu.magicretry.analytics.CBAnalytics r1 = com.payu.magicretry.analytics.CBAnalytics.this     // Catch:{ IOException -> 0x033a, MalformedURLException -> 0x0349, ProtocolException -> 0x0344 }
                    android.app.Activity r1 = r1.mActivity     // Catch:{ IOException -> 0x033a, MalformedURLException -> 0x0349, ProtocolException -> 0x0344 }
                    java.lang.String r2 = "mr_local_cache"
                    java.io.FileOutputStream r1 = r1.openFileOutput(r2, r4)     // Catch:{ IOException -> 0x033a, MalformedURLException -> 0x0349, ProtocolException -> 0x0344 }
                    byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x033a, MalformedURLException -> 0x0349, ProtocolException -> 0x0344 }
                    r1.write(r0)     // Catch:{ IOException -> 0x033a, MalformedURLException -> 0x0349, ProtocolException -> 0x0344 }
                    r1.close()     // Catch:{ IOException -> 0x033a, MalformedURLException -> 0x0349, ProtocolException -> 0x0344 }
                    goto L_0x034d
                L_0x033a:
                    r0 = move-exception
                    r0.printStackTrace()     // Catch:{ MalformedURLException -> 0x0349, ProtocolException -> 0x0344, IOException -> 0x033f }
                    goto L_0x034d
                L_0x033f:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x034d
                L_0x0344:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x034d
                L_0x0349:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x034d:
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.payu.magicretry.analytics.CBAnalytics.AnonymousClass3.run():void");
            }
        }, 5000);
    }

    /* access modifiers changed from: package-private */
    public synchronized void setLock() {
        this.mIsLocked = true;
    }

    /* access modifiers changed from: package-private */
    public synchronized void releaseLock() {
        this.mIsLocked = false;
    }

    public void callMixPanel(String str, String str2, String str3) {
        try {
            log(getLogMessage(str.toLowerCase(), str2, str3));
        } catch (Exception e) {
            Log.e("#### PAYU", "MR Unable to add properties to JSONObject", e);
        }
    }

    public String getLogMessage(String str, String str2, String str3) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mActivity.getPackageName());
            sb.append(",");
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(",");
            sb.append(System.currentTimeMillis());
            sb.append(",");
            sb.append(str3);
            sb.append(",");
            sb.append(str);
            String sb2 = sb.toString();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(sb2.getBytes());
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return sb2 + "," + bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
