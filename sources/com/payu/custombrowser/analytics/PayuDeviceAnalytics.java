package com.payu.custombrowser.analytics;

import android.content.Context;
import android.os.AsyncTask;
import com.payu.custombrowser.util.CBUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayuDeviceAnalytics {
    private static final String PRODUCTION_URL_DEVICE_ANALYTICS = "https://info.payu.in/merchant/MobileAnalytics";
    private static final String TEST_URL_DEVICE_ANALYTICS = "http://10.50.23.170:6543/MobileAnalytics";
    /* access modifiers changed from: private */
    public long TIMER_DELAY = 0;
    /* access modifiers changed from: private */
    public CBUtil cbUtil;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public String file_name = "cb_local_cache_device";
    /* access modifiers changed from: private */
    public boolean isTimerCancelled;
    /* access modifiers changed from: private */
    public ArrayList<String> mBuffer;
    /* access modifiers changed from: private */
    public boolean mIsLocked = false;
    /* access modifiers changed from: private */
    public Timer mTimer;

    public PayuDeviceAnalytics(Context context2, final String str) {
        this.context = context2;
        this.file_name = str;
        this.mBuffer = new ArrayList<>();
        this.cbUtil = new CBUtil();
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                do {
                } while (PayuDeviceAnalytics.this.mIsLocked);
                PayuDeviceAnalytics.this.setLock();
                try {
                    FileOutputStream openFileOutput = PayuDeviceAnalytics.this.context.openFileOutput(str, 0);
                    int size = PayuDeviceAnalytics.this.mBuffer.size();
                    for (int i = 0; i < size; i++) {
                        openFileOutput.write((((String) PayuDeviceAnalytics.this.mBuffer.get(i)) + "\r\n").getBytes());
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PayuDeviceAnalytics.this.releaseLock();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public void log(String str) {
        JSONArray jSONArray;
        if (this.mIsLocked) {
            this.mBuffer.add(str);
        } else {
            setLock();
            try {
                JSONObject jSONObject = new JSONObject(str);
                String str2 = "";
                if (!new File(this.context.getFilesDir(), this.file_name).exists()) {
                    this.context.openFileOutput(this.file_name, 0);
                }
                FileInputStream openFileInput = this.context.openFileInput(this.file_name);
                while (true) {
                    int read = openFileInput.read();
                    if (read == -1) {
                        break;
                    }
                    str2 = str2 + Character.toString((char) read);
                }
                if (str2.equalsIgnoreCase("")) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(str2);
                }
                openFileInput.close();
                FileOutputStream openFileOutput = this.context.openFileOutput(this.file_name, 0);
                jSONArray.put(jSONArray.length(), jSONObject);
                openFileOutput.write(jSONArray.toString().getBytes());
                openFileOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
                this.mBuffer.add(str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            releaseLock();
        }
        resetTimer();
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
                    r6 = this;
                L_0x0000:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    boolean r0 = r0.mIsLocked
                    if (r0 == 0) goto L_0x0009
                    goto L_0x0000
                L_0x0009:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r1 = 5000(0x1388, double:2.4703E-320)
                    long unused = r0.TIMER_DELAY = r1
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r0.setLock()
                    java.lang.String r0 = ""
                    r1 = 1
                    r2 = 0
                    java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x00d2 }
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ IOException -> 0x00d2 }
                    android.content.Context r4 = r4.context     // Catch:{ IOException -> 0x00d2 }
                    java.io.File r4 = r4.getFilesDir()     // Catch:{ IOException -> 0x00d2 }
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r5 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ IOException -> 0x00d2 }
                    java.lang.String r5 = r5.file_name     // Catch:{ IOException -> 0x00d2 }
                    r3.<init>(r4, r5)     // Catch:{ IOException -> 0x00d2 }
                    boolean r3 = r3.exists()     // Catch:{ IOException -> 0x00d2 }
                    if (r3 != 0) goto L_0x0043
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ IOException -> 0x00d2 }
                    android.content.Context r3 = r3.context     // Catch:{ IOException -> 0x00d2 }
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ IOException -> 0x00d2 }
                    java.lang.String r4 = r4.file_name     // Catch:{ IOException -> 0x00d2 }
                    r3.openFileOutput(r4, r2)     // Catch:{ IOException -> 0x00d2 }
                L_0x0043:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ IOException -> 0x00d2 }
                    android.content.Context r3 = r3.context     // Catch:{ IOException -> 0x00d2 }
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ IOException -> 0x00d2 }
                    java.lang.String r4 = r4.file_name     // Catch:{ IOException -> 0x00d2 }
                    java.io.FileInputStream r3 = r3.openFileInput(r4)     // Catch:{ IOException -> 0x00d2 }
                L_0x0053:
                    int r4 = r3.read()     // Catch:{ IOException -> 0x00d2 }
                    r5 = -1
                    if (r4 == r5) goto L_0x0070
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d2 }
                    r5.<init>()     // Catch:{ IOException -> 0x00d2 }
                    r5.append(r0)     // Catch:{ IOException -> 0x00d2 }
                    char r4 = (char) r4     // Catch:{ IOException -> 0x00d2 }
                    java.lang.String r4 = java.lang.Character.toString(r4)     // Catch:{ IOException -> 0x00d2 }
                    r5.append(r4)     // Catch:{ IOException -> 0x00d2 }
                    java.lang.String r4 = r5.toString()     // Catch:{ IOException -> 0x00d2 }
                    r0 = r4
                    goto L_0x0053
                L_0x0070:
                    r3.close()     // Catch:{ IOException -> 0x00d2 }
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r3 = r3.mBuffer
                    int r3 = r3.size()
                L_0x007d:
                    if (r3 <= 0) goto L_0x00b9
                    int r3 = r3 + -1
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    r4.append(r0)
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    java.lang.Object r0 = r0.get(r3)
                    java.lang.String r0 = (java.lang.String) r0
                    r4.append(r0)
                    java.lang.String r0 = "\r\n"
                    r4.append(r0)
                    java.lang.String r0 = r4.toString()
                    if (r3 < 0) goto L_0x007d
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r4 = r4.mBuffer
                    int r4 = r4.size()
                    if (r4 <= r3) goto L_0x007d
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r4 = r4.mBuffer
                    r4.remove(r3)
                    goto L_0x007d
                L_0x00b9:
                    java.lang.String r0 = r0.trim()
                    int r3 = r0.length()
                    if (r3 <= 0) goto L_0x0135
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics$UploadData r3 = new com.payu.custombrowser.analytics.PayuDeviceAnalytics$UploadData
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r3.<init>(r0)
                    java.lang.String[] r1 = new java.lang.String[r1]
                    r1[r2] = r0
                    goto L_0x0131
                L_0x00cf:
                    r3 = move-exception
                    goto L_0x0155
                L_0x00d2:
                    r3 = move-exception
                    r3.printStackTrace()     // Catch:{ all -> 0x00cf }
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r3 = r3.mBuffer
                    int r3 = r3.size()
                L_0x00e0:
                    if (r3 <= 0) goto L_0x011c
                    int r3 = r3 + -1
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    r4.append(r0)
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    java.lang.Object r0 = r0.get(r3)
                    java.lang.String r0 = (java.lang.String) r0
                    r4.append(r0)
                    java.lang.String r0 = "\r\n"
                    r4.append(r0)
                    java.lang.String r0 = r4.toString()
                    if (r3 < 0) goto L_0x00e0
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r4 = r4.mBuffer
                    int r4 = r4.size()
                    if (r4 <= r3) goto L_0x00e0
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r4 = r4.mBuffer
                    r4.remove(r3)
                    goto L_0x00e0
                L_0x011c:
                    java.lang.String r0 = r0.trim()
                    int r3 = r0.length()
                    if (r3 <= 0) goto L_0x0135
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics$UploadData r3 = new com.payu.custombrowser.analytics.PayuDeviceAnalytics$UploadData
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r3.<init>(r0)
                    java.lang.String[] r1 = new java.lang.String[r1]
                    r1[r2] = r0
                L_0x0131:
                    r3.execute(r1)
                    goto L_0x013e
                L_0x0135:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.Timer r0 = r0.mTimer
                    r0.cancel()
                L_0x013e:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    int r0 = r0.size()
                    if (r0 <= 0) goto L_0x014f
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r0.resetTimer()
                L_0x014f:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r0.releaseLock()
                    return
                L_0x0155:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r4 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r4 = r4.mBuffer
                    int r4 = r4.size()
                L_0x015f:
                    if (r4 <= 0) goto L_0x019b
                    int r4 = r4 + -1
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    r5.append(r0)
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r0 = r0.mBuffer
                    java.lang.Object r0 = r0.get(r4)
                    java.lang.String r0 = (java.lang.String) r0
                    r5.append(r0)
                    java.lang.String r0 = "\r\n"
                    r5.append(r0)
                    java.lang.String r0 = r5.toString()
                    if (r4 < 0) goto L_0x015f
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r5 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r5 = r5.mBuffer
                    int r5 = r5.size()
                    if (r5 <= r4) goto L_0x015f
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r5 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.ArrayList r5 = r5.mBuffer
                    r5.remove(r4)
                    goto L_0x015f
                L_0x019b:
                    java.lang.String r0 = r0.trim()
                    int r4 = r0.length()
                    if (r4 <= 0) goto L_0x01b4
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics$UploadData r4 = new com.payu.custombrowser.analytics.PayuDeviceAnalytics$UploadData
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r5 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    r4.<init>(r0)
                    java.lang.String[] r1 = new java.lang.String[r1]
                    r1[r2] = r0
                    r4.execute(r1)
                    goto L_0x01bd
                L_0x01b4:
                    com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this
                    java.util.Timer r0 = r0.mTimer
                    r0.cancel()
                L_0x01bd:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.analytics.PayuDeviceAnalytics.AnonymousClass2.run():void");
            }
        }, this.TIMER_DELAY);
    }

    /* access modifiers changed from: private */
    public synchronized void setLock() {
        this.mIsLocked = true;
    }

    /* access modifiers changed from: private */
    public synchronized void releaseLock() {
        this.mIsLocked = false;
    }

    /* access modifiers changed from: private */
    public JSONArray removeJsonObjectAtJsonArrayIndex(JSONArray jSONArray, int i) throws JSONException {
        if (i < 0 || i > jSONArray.length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != i) {
                jSONArray2.put(jSONArray.get(i2));
            }
        }
        return jSONArray2;
    }

    public class UploadData extends AsyncTask<String, Void, String> {
        private String temp;

        UploadData(String str) {
            this.temp = str;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            com.payu.custombrowser.analytics.PayuDeviceAnalytics.access$1200(r7.this$0, r7.temp);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x012f, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0131, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            com.payu.custombrowser.analytics.PayuDeviceAnalytics.access$800(r7.this$0);
            r8.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x013b, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x013c, code lost:
            r8.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0140, code lost:
            r8.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
            return null;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x010f */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0131 A[ExcHandler: IOException (r8v3 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x013b A[Catch:{ Exception -> 0x012f }, ExcHandler: MalformedURLException | ProtocolException (r8v1 'e' java.io.IOException A[CUSTOM_DECLARE, Catch:{ Exception -> 0x012f }]), Splitter:B:0:0x0000] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r8) {
            /*
                r7 = this;
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                android.content.Context r0 = r0.context     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r0 == 0) goto L_0x0143
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                boolean r0 = r0.isTimerCancelled     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r0 != 0) goto L_0x0143
                org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r1 = 0
                r8 = r8[r1]     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r0.<init>(r8)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r2 = r0
                r8 = 0
            L_0x001a:
                int r3 = r0.length()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r8 >= r3) goto L_0x0067
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.util.CBUtil r3 = r3.cbUtil     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r4.<init>()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.Object r5 = r0.get(r8)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r6 = "merchant_key"
                java.lang.String r5 = r5.getString(r6)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r4.append(r5)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r5 = "|"
                r4.append(r5)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.Object r5 = r0.get(r8)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r6 = "txnid"
                java.lang.String r5 = r5.getString(r6)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r4.append(r5)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r5 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                android.content.Context r5 = r5.context     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                boolean r3 = r3.getBooleanSharedPreference(r4, r5)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r3 == 0) goto L_0x0064
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r2 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                org.json.JSONArray r2 = r2.removeJsonObjectAtJsonArrayIndex(r0, r8)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
            L_0x0064:
                int r8 = r8 + 1
                goto L_0x001a
            L_0x0067:
                int r8 = r2.length()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r8 <= 0) goto L_0x0143
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.<init>()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = "command=DeviceAnalytics&data="
                r8.append(r0)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r2.toString()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.append(r0)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r8 = r8.toString()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = "https://info.payu.in/merchant/MobileAnalytics"
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.util.CBUtil r3 = r3.cbUtil     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.net.HttpURLConnection r8 = r3.getHttpsConn(r0, r8)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r8 == 0) goto L_0x011f
                int r0 = r8.getResponseCode()     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r3 = 200(0xc8, float:2.8E-43)
                if (r0 != r3) goto L_0x0117
                java.io.InputStream r8 = r8.getInputStream()     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.StringBuffer r8 = com.payu.custombrowser.util.CBUtil.getStringBufferFromInputStream(r8)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r8 == 0) goto L_0x0143
                org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r0.<init>(r8)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r8 = "status"
                boolean r8 = r0.has(r8)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r8 == 0) goto L_0x0107
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r8 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                android.content.Context r8 = r8.context     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r0.file_name     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.deleteFile(r0)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
            L_0x00c2:
                int r8 = r2.length()     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                if (r1 >= r8) goto L_0x0143
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r8 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.util.CBUtil r8 = r8.cbUtil     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r0.<init>()     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.Object r3 = r2.get(r1)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r4 = "merchant_key"
                java.lang.String r3 = r3.getString(r4)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r0.append(r3)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r3 = "|"
                r0.append(r3)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.Object r3 = r2.get(r1)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r4 = "txnid"
                java.lang.String r3 = r3.getString(r4)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r0.append(r3)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r3 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r4 = 1
                r8.setBooleanSharedPreference(r0, r4, r3)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                int r1 = r1 + 1
                goto L_0x00c2
            L_0x0107:
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r8 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r7.temp     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.updateFile(r0)     // Catch:{ Exception -> 0x010f, MalformedURLException | ProtocolException -> 0x013b, MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                goto L_0x0143
            L_0x010f:
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r8 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r7.temp     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.updateFile(r0)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                goto L_0x0143
            L_0x0117:
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r8 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r7.temp     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.updateFile(r0)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                goto L_0x0143
            L_0x011f:
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r8 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                android.content.Context r8 = r8.context     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                java.lang.String r0 = r0.file_name     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                r8.deleteFile(r0)     // Catch:{ MalformedURLException | ProtocolException -> 0x013b, IOException -> 0x0131 }
                goto L_0x0143
            L_0x012f:
                r8 = move-exception
                goto L_0x0140
            L_0x0131:
                r8 = move-exception
                com.payu.custombrowser.analytics.PayuDeviceAnalytics r0 = com.payu.custombrowser.analytics.PayuDeviceAnalytics.this     // Catch:{ Exception -> 0x012f }
                r0.resetTimer()     // Catch:{ Exception -> 0x012f }
                r8.printStackTrace()     // Catch:{ Exception -> 0x012f }
                goto L_0x0143
            L_0x013b:
                r8 = move-exception
                r8.printStackTrace()     // Catch:{ Exception -> 0x012f }
                goto L_0x0143
            L_0x0140:
                r8.printStackTrace()
            L_0x0143:
                r8 = 0
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.analytics.PayuDeviceAnalytics.UploadData.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    /* access modifiers changed from: private */
    public void updateFile(String str) {
        try {
            FileOutputStream openFileOutput = this.context.openFileOutput(this.file_name, 0);
            openFileOutput.write(str.getBytes());
            openFileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Timer getmTimer() {
        this.isTimerCancelled = true;
        return this.mTimer;
    }
}
