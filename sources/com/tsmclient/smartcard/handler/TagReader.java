package com.tsmclient.smartcard.handler;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TagReader {
    protected static final String TAG = "SmartCardReader";
    private final Activity mActivity;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final List<ISmartCardHandler<IsoDep>> mIsoDepHandlerList;
    /* access modifiers changed from: private */
    public SmartCardReaderListener mListener;
    /* access modifiers changed from: private */
    public final List<ISmartCardHandler<NfcF>> mNfcFHandlerList;
    private PendingIntent mPendingIntent;
    private boolean mPolling;
    private final AtomicBoolean mStop;
    private final Handler mWorkHandler;
    private HandlerThread mWorkThread;

    public interface SmartCardReaderListener {
        void onCompleteHandle(Bundle bundle);

        void onStartHandleTag();
    }

    public TagReader(Activity activity) {
        this(activity, (Handler) null, (Handler) null);
    }

    public TagReader(Activity activity, Handler handler, Handler handler2) {
        if (handler == null) {
            this.mWorkThread = new HandlerThread("SCHandlerThread");
            this.mWorkThread.start();
            handler = new Handler(this.mWorkThread.getLooper());
        }
        handler2 = handler2 == null ? new Handler(Looper.getMainLooper()) : handler2;
        this.mWorkHandler = handler;
        this.mHandler = handler2;
        this.mIsoDepHandlerList = new CopyOnWriteArrayList();
        this.mNfcFHandlerList = new CopyOnWriteArrayList();
        this.mActivity = activity;
        this.mStop = new AtomicBoolean();
    }

    public void addSmartCardHandler(ISmartCardHandler iSmartCardHandler) {
        switch (iSmartCardHandler.getTechType()) {
            case 1:
                this.mIsoDepHandlerList.add(iSmartCardHandler);
                return;
            case 2:
                this.mNfcFHandlerList.add(iSmartCardHandler);
                return;
            default:
                Log.w(TAG, "unknown card handler: " + iSmartCardHandler.getClass());
                return;
        }
    }

    public void startPoll() {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.mActivity);
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            if (this.mPendingIntent == null) {
                this.mPendingIntent = PendingIntent.getActivity(this.mActivity, 0, new Intent(this.mActivity, this.mActivity.getClass()).addFlags(536870912), C.ENCODING_PCM_MU_LAW);
            }
            try {
                defaultAdapter.enableForegroundDispatch(this.mActivity, this.mPendingIntent, (IntentFilter[]) null, (String[][]) null);
            } catch (IllegalStateException unused) {
            }
            this.mPolling = true;
        }
    }

    public void stopPoll() {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.mActivity);
        if (defaultAdapter != null && this.mPolling) {
            try {
                defaultAdapter.disableForegroundDispatch(this.mActivity);
            } catch (IllegalStateException unused) {
            }
            this.mPolling = false;
        }
    }

    public boolean isCardPolling() {
        return this.mPolling;
    }

    public void handleTag(final Tag tag) {
        if (this.mStop.get()) {
            Log.w(TAG, "card reader has stopped to handle tag");
            return;
        }
        if (this.mListener != null) {
            this.mListener.onStartHandleTag();
        }
        this.mWorkHandler.post(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
                r0 = e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x0062, code lost:
                r1 = new android.os.Bundle();
                r1.putBoolean("success", false);
                r1.putInt("error", 2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x0073, code lost:
                r1 = new android.os.Bundle();
                r1.putBoolean("success", false);
                r1.putInt("error", 1);
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:29:? A[ExcHandler: UnProcessableCardException (unused com.tsmclient.smartcard.exception.UnProcessableCardException), SYNTHETIC, Splitter:B:1:0x0002] */
            /* JADX WARNING: Removed duplicated region for block: B:31:? A[ExcHandler: IOException (unused java.io.IOException), SYNTHETIC, Splitter:B:1:0x0002] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    r0 = 0
                    r1 = 0
                    android.nfc.Tag r2 = r3     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    android.nfc.tech.IsoDep r2 = android.nfc.tech.IsoDep.get(r2)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    if (r2 == 0) goto L_0x0028
                    com.tsmclient.smartcard.handler.TagReader r3 = com.tsmclient.smartcard.handler.TagReader.this     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    java.util.List r3 = r3.mIsoDepHandlerList     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    boolean r3 = r3.isEmpty()     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    if (r3 != 0) goto L_0x0028
                    com.tsmclient.smartcard.handler.TagReader r3 = com.tsmclient.smartcard.handler.TagReader.this     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    android.os.Bundle r3 = r3.handleIsoDep(r2)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    java.lang.String r1 = "nfc_tag"
                    android.nfc.Tag r4 = r3     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0025 }
                    r3.putParcelable(r1, r4)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0025 }
                    r1 = r3
                    goto L_0x0028
                L_0x0025:
                    r0 = move-exception
                    r1 = r3
                    goto L_0x005a
                L_0x0028:
                    if (r2 != 0) goto L_0x004e
                    android.nfc.Tag r2 = r3     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    android.nfc.tech.NfcF r2 = android.nfc.tech.NfcF.get(r2)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    if (r2 == 0) goto L_0x0046
                    com.tsmclient.smartcard.handler.TagReader r3 = com.tsmclient.smartcard.handler.TagReader.this     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    java.util.List r3 = r3.mNfcFHandlerList     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    boolean r3 = r3.isEmpty()     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    if (r3 != 0) goto L_0x0046
                    com.tsmclient.smartcard.handler.TagReader r3 = com.tsmclient.smartcard.handler.TagReader.this     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    android.os.Bundle r2 = r3.handleNfcF(r2)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    r1 = r2
                    goto L_0x004e
                L_0x0046:
                    com.tsmclient.smartcard.exception.UnProcessableCardException r2 = new com.tsmclient.smartcard.exception.UnProcessableCardException     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    java.lang.String r3 = "SmartCardReaderunsupported card type"
                    r2.<init>(r3)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    throw r2     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                L_0x004e:
                    if (r1 == 0) goto L_0x0051
                    goto L_0x0083
                L_0x0051:
                    java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    java.lang.String r3 = "SmartCardReaderfailed to handle tag"
                    r2.<init>(r3)     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                    throw r2     // Catch:{ IOException -> 0x0073, UnProcessableCardException -> 0x0062, Exception -> 0x0059 }
                L_0x0059:
                    r0 = move-exception
                L_0x005a:
                    java.lang.String r2 = "SmartCardReader"
                    java.lang.String r3 = "RuntimeException :"
                    android.util.Log.e(r2, r3, r0)
                    goto L_0x0083
                L_0x0062:
                    android.os.Bundle r1 = new android.os.Bundle
                    r1.<init>()
                    java.lang.String r2 = "success"
                    r1.putBoolean(r2, r0)
                    java.lang.String r0 = "error"
                    r2 = 2
                    r1.putInt(r0, r2)
                    goto L_0x0083
                L_0x0073:
                    android.os.Bundle r1 = new android.os.Bundle
                    r1.<init>()
                    java.lang.String r2 = "success"
                    r1.putBoolean(r2, r0)
                    java.lang.String r0 = "error"
                    r2 = 1
                    r1.putInt(r0, r2)
                L_0x0083:
                    com.tsmclient.smartcard.handler.TagReader r0 = com.tsmclient.smartcard.handler.TagReader.this
                    com.tsmclient.smartcard.handler.TagReader$SmartCardReaderListener r0 = r0.mListener
                    if (r0 == 0) goto L_0x0099
                    com.tsmclient.smartcard.handler.TagReader r2 = com.tsmclient.smartcard.handler.TagReader.this
                    android.os.Handler r2 = r2.mHandler
                    com.tsmclient.smartcard.handler.TagReader$1$1 r3 = new com.tsmclient.smartcard.handler.TagReader$1$1
                    r3.<init>(r0, r1)
                    r2.post(r3)
                L_0x0099:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.handler.TagReader.AnonymousClass1.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    public Bundle handleIsoDep(IsoDep isoDep) throws IOException {
        Bundle bundle = new Bundle();
        try {
            isoDep.connect();
            for (ISmartCardHandler onHandleCard : this.mIsoDepHandlerList) {
                Bundle onHandleCard2 = onHandleCard.onHandleCard(isoDep);
                try {
                    isoDep.close();
                } catch (IOException unused) {
                }
                return onHandleCard2;
            }
            try {
                isoDep.close();
            } catch (IOException unused2) {
            }
            return bundle;
        } catch (UnProcessableCardException unused3) {
            bundle.putBoolean("success", false);
            bundle.putInt("error", 2);
        } catch (Throwable th) {
            try {
                isoDep.close();
            } catch (IOException unused4) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public Bundle handleNfcF(NfcF nfcF) throws IOException {
        Bundle bundle = new Bundle();
        try {
            nfcF.connect();
            for (ISmartCardHandler onHandleCard : this.mNfcFHandlerList) {
                Bundle onHandleCard2 = onHandleCard.onHandleCard(nfcF);
                try {
                    nfcF.close();
                } catch (IOException unused) {
                }
                return onHandleCard2;
            }
            try {
                nfcF.close();
                return null;
            } catch (IOException unused2) {
                return null;
            }
        } catch (UnProcessableCardException unused3) {
            bundle.putBoolean("success", false);
            bundle.putInt("error", 2);
        } catch (Throwable th) {
            try {
                nfcF.close();
            } catch (IOException unused4) {
            }
            throw th;
        }
    }

    public void shutdown() {
        this.mStop.set(true);
        if (this.mWorkThread != null) {
            this.mWorkThread.quit();
            this.mWorkThread.interrupt();
        }
        this.mWorkHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void setListener(SmartCardReaderListener smartCardReaderListener) {
        this.mListener = smartCardReaderListener;
    }
}
