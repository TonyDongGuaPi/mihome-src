package com.payu.custombrowser.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.payu.custombrowser.util.CBUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CBAnalytics {
    public static final String ANALYTICS_URL = "https://info.payu.in/merchant/MobileAnalytics";
    private static CBAnalytics INSTANCE = null;
    private static final String PRODUCTION_URL = "https://info.payu.in/merchant/MobileAnalytics";
    private static final String TEST_URL = "https://mobiletest.payu.in/merchant/MobileAnalytics";
    private static final long TIMER_DELAY = 5000;
    /* access modifiers changed from: private */
    public String ANALYTICS_BUFFER_KEY = "analytics_buffer_key";
    /* access modifiers changed from: private */
    public CBUtil cbUtil;
    /* access modifiers changed from: private */
    public String fileName;
    /* access modifiers changed from: private */
    public volatile boolean mBufferLock;
    private Timer mTimer;
    /* access modifiers changed from: private */
    public volatile boolean mainFileLocked = false;
    /* access modifiers changed from: private */
    public final Context mcontext;

    private CBAnalytics(final Context context, String str) {
        this.mcontext = context;
        this.fileName = str;
        this.cbUtil = new CBUtil();
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                do {
                } while (CBAnalytics.this.mainFileLocked);
                CBAnalytics.this.setLock();
                try {
                    FileOutputStream openFileOutput = CBAnalytics.this.mcontext.openFileOutput(CBAnalytics.this.fileName, 0);
                    if (CBAnalytics.this.cbUtil.getStringSharedPreference(CBAnalytics.this.mcontext, CBAnalytics.this.ANALYTICS_BUFFER_KEY).length() > 0) {
                        JSONArray jSONArray = new JSONArray();
                        JSONArray jSONArray2 = new JSONArray(CBAnalytics.this.cbUtil.getStringSharedPreference(CBAnalytics.this.mcontext, CBAnalytics.this.ANALYTICS_BUFFER_KEY).toString());
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            jSONArray.put(jSONArray.length(), jSONArray2.getJSONObject(i));
                        }
                        openFileOutput.write(jSONArray.toString().getBytes());
                        CBAnalytics.this.cbUtil.deleteSharedPrefKey(context, CBAnalytics.this.ANALYTICS_BUFFER_KEY);
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                CBAnalytics.this.releaseLock();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public static CBAnalytics getInstance(Context context, String str) {
        if (INSTANCE == null) {
            synchronized (CBAnalytics.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CBAnalytics(context, str);
                }
            }
        }
        return INSTANCE;
    }

    public void log(final String str) {
        if (isOnline()) {
            resetTimer();
        }
        if (this.mainFileLocked) {
            new Thread(new Runnable() {
                public void run() {
                    JSONArray jSONArray;
                    while (CBAnalytics.this.mBufferLock) {
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    String stringSharedPreference = CBAnalytics.this.cbUtil.getStringSharedPreference(CBAnalytics.this.mcontext, CBAnalytics.this.ANALYTICS_BUFFER_KEY);
                    if (stringSharedPreference == null || stringSharedPreference.equalsIgnoreCase("")) {
                        jSONArray = new JSONArray();
                    } else {
                        jSONArray = new JSONArray(stringSharedPreference);
                    }
                    jSONArray.put(new JSONObject(str));
                    CBAnalytics.this.cbUtil.setStringSharedPreference(CBAnalytics.this.mcontext, CBAnalytics.this.ANALYTICS_BUFFER_KEY, jSONArray.toString());
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                public void run() {
                    JSONArray jSONArray;
                    do {
                    } while (CBAnalytics.this.mainFileLocked);
                    CBAnalytics.this.setLock();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String readFileInputStream = CBAnalytics.this.cbUtil.readFileInputStream(CBAnalytics.this.mcontext, CBAnalytics.this.fileName, 0);
                        if (readFileInputStream != null) {
                            if (!readFileInputStream.equalsIgnoreCase("")) {
                                jSONArray = new JSONArray(readFileInputStream);
                                FileOutputStream openFileOutput = CBAnalytics.this.mcontext.openFileOutput(CBAnalytics.this.fileName, 0);
                                jSONArray.put(jSONArray.length(), jSONObject);
                                openFileOutput.write(jSONArray.toString().getBytes());
                                openFileOutput.close();
                                CBAnalytics.this.releaseLock();
                            }
                        }
                        jSONArray = new JSONArray();
                        FileOutputStream openFileOutput2 = CBAnalytics.this.mcontext.openFileOutput(CBAnalytics.this.fileName, 0);
                        jSONArray.put(jSONArray.length(), jSONObject);
                        openFileOutput2.write(jSONArray.toString().getBytes());
                        openFileOutput2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    } catch (Throwable th) {
                        CBAnalytics.this.releaseLock();
                        throw th;
                    }
                    CBAnalytics.this.releaseLock();
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public void resetTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() {
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0068 A[Catch:{ Exception -> 0x0044 }, DONT_GENERATE] */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x0094 A[Catch:{ Exception -> 0x0044 }, DONT_GENERATE] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r8 = this;
                L_0x0000:
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this
                    boolean r0 = r0.mainFileLocked
                    if (r0 == 0) goto L_0x0009
                    goto L_0x0000
                L_0x0009:
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this
                    r0.setLock()
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this
                    boolean r0 = r0.isOnline()
                    r1 = 1
                    if (r0 == 0) goto L_0x01af
                    java.lang.String r0 = ""
                    r2 = 200(0xc8, float:2.8E-43)
                    r3 = 30000(0x7530, float:4.2039E-41)
                    com.payu.custombrowser.analytics.CBAnalytics r4 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ all -> 0x00f4 }
                    com.payu.custombrowser.util.CBUtil r4 = r4.cbUtil     // Catch:{ all -> 0x00f4 }
                    com.payu.custombrowser.analytics.CBAnalytics r5 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ all -> 0x00f4 }
                    android.content.Context r5 = r5.mcontext     // Catch:{ all -> 0x00f4 }
                    com.payu.custombrowser.analytics.CBAnalytics r6 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ all -> 0x00f4 }
                    java.lang.String r6 = r6.fileName     // Catch:{ all -> 0x00f4 }
                    r7 = 0
                    java.lang.String r4 = r4.readFileInputStream(r5, r6, r7)     // Catch:{ all -> 0x00f4 }
                    if (r4 == 0) goto L_0x0047
                    java.lang.String r0 = ""
                    boolean r0 = r4.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x0044 }
                    if (r0 != 0) goto L_0x0047
                    org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x0044 }
                    r0.<init>(r4)     // Catch:{ Exception -> 0x0044 }
                    goto L_0x004c
                L_0x0044:
                    r0 = move-exception
                    goto L_0x00ef
                L_0x0047:
                    org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x0044 }
                    r0.<init>()     // Catch:{ Exception -> 0x0044 }
                L_0x004c:
                    com.payu.custombrowser.analytics.CBAnalytics r4 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.util.CBUtil r4 = r4.cbUtil     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r5 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    android.content.Context r5 = r5.mcontext     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r6 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r6 = r6.ANALYTICS_BUFFER_KEY     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r4 = r4.getStringSharedPreference(r5, r6)     // Catch:{ Exception -> 0x0044 }
                    int r4 = r4.length()     // Catch:{ Exception -> 0x0044 }
                    if (r4 <= r1) goto L_0x008e
                    com.payu.custombrowser.analytics.CBAnalytics r4 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    boolean unused = r4.mBufferLock = r1     // Catch:{ Exception -> 0x0044 }
                    org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r5 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.util.CBUtil r5 = r5.cbUtil     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r6 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    android.content.Context r6 = r6.mcontext     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r7 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r7 = r7.ANALYTICS_BUFFER_KEY     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r5 = r5.getStringSharedPreference(r6, r7)     // Catch:{ Exception -> 0x0044 }
                    r4.<init>(r5)     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r5 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    org.json.JSONArray r0 = r5.copyBufferToFile(r0, r4)     // Catch:{ Exception -> 0x0044 }
                L_0x008e:
                    int r4 = r0.length()     // Catch:{ Exception -> 0x0044 }
                    if (r4 <= 0) goto L_0x01af
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0044 }
                    r4.<init>()     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r5 = "command=EventAnalytics&data="
                    r4.append(r5)     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0044 }
                    r4.append(r0)     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r4 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.util.CBUtil r4 = r4.cbUtil     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r5 = "https://info.payu.in/merchant/MobileAnalytics"
                    java.net.HttpURLConnection r0 = r4.getHttpsConn(r5, r0, r3)     // Catch:{ Exception -> 0x0044 }
                    if (r0 == 0) goto L_0x01af
                    int r3 = r0.getResponseCode()     // Catch:{ Exception -> 0x0044 }
                    if (r3 != r2) goto L_0x01af
                    java.io.InputStream r2 = r0.getInputStream()     // Catch:{ Exception -> 0x0044 }
                    if (r2 == 0) goto L_0x01af
                    java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x0044 }
                    java.lang.StringBuffer r0 = com.payu.custombrowser.util.CBUtil.getStringBufferFromInputStream(r0)     // Catch:{ Exception -> 0x0044 }
                    if (r0 == 0) goto L_0x01af
                    org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0044 }
                    r2.<init>(r0)     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r0 = "status"
                    boolean r0 = r2.has(r0)     // Catch:{ Exception -> 0x0044 }
                    if (r0 == 0) goto L_0x01af
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    android.content.Context r0 = r0.mcontext     // Catch:{ Exception -> 0x0044 }
                    com.payu.custombrowser.analytics.CBAnalytics r2 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x0044 }
                    java.lang.String r2 = r2.fileName     // Catch:{ Exception -> 0x0044 }
                    r0.deleteFile(r2)     // Catch:{ Exception -> 0x0044 }
                    goto L_0x01af
                L_0x00ef:
                    r0.printStackTrace()
                    goto L_0x01af
                L_0x00f4:
                    r4 = move-exception
                    java.lang.String r5 = ""
                    boolean r5 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x01aa }
                    if (r5 != 0) goto L_0x0103
                    org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ Exception -> 0x01aa }
                    r5.<init>(r0)     // Catch:{ Exception -> 0x01aa }
                    goto L_0x0108
                L_0x0103:
                    org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ Exception -> 0x01aa }
                    r5.<init>()     // Catch:{ Exception -> 0x01aa }
                L_0x0108:
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.util.CBUtil r0 = r0.cbUtil     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r6 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    android.content.Context r6 = r6.mcontext     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r7 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r7 = r7.ANALYTICS_BUFFER_KEY     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r0 = r0.getStringSharedPreference(r6, r7)     // Catch:{ Exception -> 0x01aa }
                    int r0 = r0.length()     // Catch:{ Exception -> 0x01aa }
                    if (r0 <= r1) goto L_0x014a
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    boolean unused = r0.mBufferLock = r1     // Catch:{ Exception -> 0x01aa }
                    org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r1 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.util.CBUtil r1 = r1.cbUtil     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r6 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    android.content.Context r6 = r6.mcontext     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r7 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r7 = r7.ANALYTICS_BUFFER_KEY     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r1 = r1.getStringSharedPreference(r6, r7)     // Catch:{ Exception -> 0x01aa }
                    r0.<init>(r1)     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r1 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    org.json.JSONArray r5 = r1.copyBufferToFile(r5, r0)     // Catch:{ Exception -> 0x01aa }
                L_0x014a:
                    int r0 = r5.length()     // Catch:{ Exception -> 0x01aa }
                    if (r0 <= 0) goto L_0x01ae
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01aa }
                    r0.<init>()     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r1 = "command=EventAnalytics&data="
                    r0.append(r1)     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r1 = r5.toString()     // Catch:{ Exception -> 0x01aa }
                    r0.append(r1)     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r1 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.util.CBUtil r1 = r1.cbUtil     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r5 = "https://info.payu.in/merchant/MobileAnalytics"
                    java.net.HttpURLConnection r0 = r1.getHttpsConn(r5, r0, r3)     // Catch:{ Exception -> 0x01aa }
                    if (r0 == 0) goto L_0x01ae
                    int r1 = r0.getResponseCode()     // Catch:{ Exception -> 0x01aa }
                    if (r1 != r2) goto L_0x01ae
                    java.io.InputStream r1 = r0.getInputStream()     // Catch:{ Exception -> 0x01aa }
                    if (r1 == 0) goto L_0x01ae
                    java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x01aa }
                    java.lang.StringBuffer r0 = com.payu.custombrowser.util.CBUtil.getStringBufferFromInputStream(r0)     // Catch:{ Exception -> 0x01aa }
                    if (r0 == 0) goto L_0x01ae
                    org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01aa }
                    r1.<init>(r0)     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r0 = "status"
                    boolean r0 = r1.has(r0)     // Catch:{ Exception -> 0x01aa }
                    if (r0 == 0) goto L_0x01ae
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    android.content.Context r0 = r0.mcontext     // Catch:{ Exception -> 0x01aa }
                    com.payu.custombrowser.analytics.CBAnalytics r1 = com.payu.custombrowser.analytics.CBAnalytics.this     // Catch:{ Exception -> 0x01aa }
                    java.lang.String r1 = r1.fileName     // Catch:{ Exception -> 0x01aa }
                    r0.deleteFile(r1)     // Catch:{ Exception -> 0x01aa }
                    goto L_0x01ae
                L_0x01aa:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x01ae:
                    throw r4
                L_0x01af:
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this
                    r0.releaseLock()
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this
                    com.payu.custombrowser.util.CBUtil r0 = r0.cbUtil
                    com.payu.custombrowser.analytics.CBAnalytics r2 = com.payu.custombrowser.analytics.CBAnalytics.this
                    android.content.Context r2 = r2.mcontext
                    com.payu.custombrowser.analytics.CBAnalytics r3 = com.payu.custombrowser.analytics.CBAnalytics.this
                    java.lang.String r3 = r3.ANALYTICS_BUFFER_KEY
                    java.lang.String r0 = r0.getStringSharedPreference(r2, r3)
                    int r0 = r0.length()
                    if (r0 <= r1) goto L_0x01d5
                    com.payu.custombrowser.analytics.CBAnalytics r0 = com.payu.custombrowser.analytics.CBAnalytics.this
                    r0.resetTimer()
                L_0x01d5:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.analytics.CBAnalytics.AnonymousClass4.run():void");
            }
        }, 5000);
    }

    /* access modifiers changed from: private */
    public synchronized void setLock() {
        while (this.mainFileLocked) {
        }
        this.mainFileLocked = true;
    }

    /* access modifiers changed from: private */
    public void releaseLock() {
        this.mainFileLocked = false;
    }

    /* access modifiers changed from: private */
    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mcontext.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Timer getmTimer() {
        return this.mTimer;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055 A[SYNTHETIC, Splitter:B:25:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0062 A[SYNTHETIC, Splitter:B:32:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONArray copyBufferToFile(org.json.JSONArray r7, org.json.JSONArray r8) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Exception -> 0x004f }
            java.lang.String r3 = r7.toString()     // Catch:{ Exception -> 0x004f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x004f }
            r3 = 0
        L_0x000c:
            int r4 = r8.length()     // Catch:{ Exception -> 0x004f }
            if (r3 >= r4) goto L_0x001c
            org.json.JSONObject r4 = r8.getJSONObject(r3)     // Catch:{ Exception -> 0x004f }
            r2.put(r4)     // Catch:{ Exception -> 0x004f }
            int r3 = r3 + 1
            goto L_0x000c
        L_0x001c:
            android.content.Context r8 = r6.mcontext     // Catch:{ Exception -> 0x004f }
            java.lang.String r3 = r6.fileName     // Catch:{ Exception -> 0x004f }
            java.io.FileOutputStream r8 = r8.openFileOutput(r3, r0)     // Catch:{ Exception -> 0x004f }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            byte[] r1 = r1.getBytes()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            r8.write(r1)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            com.payu.custombrowser.util.CBUtil r1 = r6.cbUtil     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            android.content.Context r3 = r6.mcontext     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            java.lang.String r4 = r6.ANALYTICS_BUFFER_KEY     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            r1.deleteSharedPrefKey(r3, r4)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            if (r8 == 0) goto L_0x0042
            r8.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0042:
            r6.mBufferLock = r0
            return r2
        L_0x0045:
            r7 = move-exception
            r1 = r8
            goto L_0x0060
        L_0x0048:
            r1 = move-exception
            r5 = r1
            r1 = r8
            r8 = r5
            goto L_0x0050
        L_0x004d:
            r7 = move-exception
            goto L_0x0060
        L_0x004f:
            r8 = move-exception
        L_0x0050:
            r8.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r8 = move-exception
            r8.printStackTrace()
        L_0x005d:
            r6.mBufferLock = r0
            return r7
        L_0x0060:
            if (r1 == 0) goto L_0x006a
            r1.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r8 = move-exception
            r8.printStackTrace()
        L_0x006a:
            r6.mBufferLock = r0
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.analytics.CBAnalytics.copyBufferToFile(org.json.JSONArray, org.json.JSONArray):org.json.JSONArray");
    }
}
