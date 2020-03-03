package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.mijia.model.property.CameraPropertyBase;
import java.io.IOException;

final class zzaq implements Runnable {
    private final zzae zzal;
    private final FirebaseInstanceId zzaw;
    private final long zzcw;
    private final PowerManager.WakeLock zzcx = ((PowerManager) getContext().getSystemService(CameraPropertyBase.l)).newWakeLock(1, "fiid-sync");

    @VisibleForTesting
    zzaq(FirebaseInstanceId firebaseInstanceId, zzae zzae, long j) {
        this.zzaw = firebaseInstanceId;
        this.zzal = zzae;
        this.zzcw = j;
        this.zzcx.setReferenceCounted(false);
    }

    @VisibleForTesting
    private final boolean zzah() {
        zzap zzg = this.zzaw.zzg();
        if (zzg != null && !zzg.zzj(this.zzal.zzy())) {
            return true;
        }
        try {
            String zzh = this.zzaw.zzh();
            if (zzh == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzg == null || (zzg != null && !zzh.equals(zzg.zzcu))) {
                Context context = getContext();
                Intent intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
                Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
                intent2.setClass(context, FirebaseInstanceIdReceiver.class);
                intent2.putExtra("wrapped_intent", intent);
                context.sendBroadcast(intent2);
            }
            return true;
        } catch (IOException | SecurityException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Token retrieval failed: ".concat(valueOf) : new String("Token retrieval failed: "));
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (zzk(r1) != false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return false;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzai() {
        /*
            r3 = this;
        L_0x0000:
            com.google.firebase.iid.FirebaseInstanceId r0 = r3.zzaw
            monitor-enter(r0)
            com.google.firebase.iid.zzao r1 = com.google.firebase.iid.FirebaseInstanceId.zzi()     // Catch:{ all -> 0x0029 }
            java.lang.String r1 = r1.zzaf()     // Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x0018
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "topic sync succeeded"
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0029 }
            r1 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r1
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            boolean r0 = r3.zzk(r1)
            if (r0 != 0) goto L_0x0021
            r0 = 0
            return r0
        L_0x0021:
            com.google.firebase.iid.zzao r0 = com.google.firebase.iid.FirebaseInstanceId.zzi()
            r0.zzf(r1)
            goto L_0x0000
        L_0x0029:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzaq.zzai():boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        android.util.Log.d(r7, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzk(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "!"
            java.lang.String[] r7 = r7.split(r0)
            int r0 = r7.length
            r1 = 1
            r2 = 2
            if (r0 != r2) goto L_0x007b
            r0 = 0
            r2 = r7[r0]
            r7 = r7[r1]
            r3 = -1
            int r4 = r2.hashCode()     // Catch:{ IOException -> 0x005a }
            r5 = 83
            if (r4 == r5) goto L_0x0028
            r5 = 85
            if (r4 == r5) goto L_0x001e
            goto L_0x0031
        L_0x001e:
            java.lang.String r4 = "U"
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x005a }
            if (r2 == 0) goto L_0x0031
            r3 = 1
            goto L_0x0031
        L_0x0028:
            java.lang.String r4 = "S"
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x005a }
            if (r2 == 0) goto L_0x0031
            r3 = 0
        L_0x0031:
            switch(r3) {
                case 0: goto L_0x0049;
                case 1: goto L_0x0035;
                default: goto L_0x0034;
            }     // Catch:{ IOException -> 0x005a }
        L_0x0034:
            goto L_0x007b
        L_0x0035:
            com.google.firebase.iid.FirebaseInstanceId r2 = r6.zzaw     // Catch:{ IOException -> 0x005a }
            r2.zzc(r7)     // Catch:{ IOException -> 0x005a }
            boolean r7 = com.google.firebase.iid.FirebaseInstanceId.zzj()     // Catch:{ IOException -> 0x005a }
            if (r7 == 0) goto L_0x007b
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r2 = "unsubscribe operation succeeded"
        L_0x0045:
            android.util.Log.d(r7, r2)     // Catch:{ IOException -> 0x005a }
            goto L_0x007b
        L_0x0049:
            com.google.firebase.iid.FirebaseInstanceId r2 = r6.zzaw     // Catch:{ IOException -> 0x005a }
            r2.zzb((java.lang.String) r7)     // Catch:{ IOException -> 0x005a }
            boolean r7 = com.google.firebase.iid.FirebaseInstanceId.zzj()     // Catch:{ IOException -> 0x005a }
            if (r7 == 0) goto L_0x007b
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r2 = "subscribe operation succeeded"
            goto L_0x0045
        L_0x005a:
            r7 = move-exception
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Topic sync failed: "
            java.lang.String r7 = r7.getMessage()
            java.lang.String r7 = java.lang.String.valueOf(r7)
            int r3 = r7.length()
            if (r3 == 0) goto L_0x0072
            java.lang.String r7 = r2.concat(r7)
            goto L_0x0077
        L_0x0072:
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
        L_0x0077:
            android.util.Log.e(r1, r7)
            return r0
        L_0x007b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzaq.zzk(java.lang.String):boolean");
    }

    /* access modifiers changed from: package-private */
    public final Context getContext() {
        return this.zzaw.zze().getApplicationContext();
    }

    public final void run() {
        FirebaseInstanceId firebaseInstanceId;
        this.zzcx.acquire();
        try {
            boolean z = true;
            this.zzaw.zza(true);
            if (this.zzal.zzx() == 0) {
                z = false;
            }
            if (!z) {
                firebaseInstanceId = this.zzaw;
            } else {
                if (!zzaj()) {
                    new zzar(this).zzak();
                } else if (!zzah() || !zzai()) {
                    this.zzaw.zza(this.zzcw);
                } else {
                    firebaseInstanceId = this.zzaw;
                }
            }
            firebaseInstanceId.zza(false);
        } finally {
            this.zzcx.release();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaj() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
