package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

final class zzao {
    private final SharedPreferences zzcq;
    private final zzp zzcr;
    @GuardedBy("this")
    private final Map<String, zzq> zzcs;
    private final Context zzz;

    public zzao(Context context) {
        this(context, new zzp());
    }

    private zzao(Context context, zzp zzp) {
        this.zzcs = new ArrayMap();
        this.zzz = context;
        this.zzcq = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzcr = zzp;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzz), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    zzag();
                    FirebaseInstanceId.getInstance().zzk();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
                }
            }
        }
    }

    private final synchronized boolean isEmpty() {
        return this.zzcq.getAll().isEmpty();
    }

    private static String zza(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    static String zzd(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    public final synchronized void zza(String str) {
        String string = this.zzcq.getString("topic_operaion_queue", "");
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 1 + String.valueOf(str).length());
        sb.append(string);
        sb.append(",");
        sb.append(str);
        this.zzcq.edit().putString("topic_operaion_queue", sb.toString()).apply();
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zza = zzap.zza(str4, str5, System.currentTimeMillis());
        if (zza != null) {
            SharedPreferences.Editor edit = this.zzcq.edit();
            edit.putString(zza(str, str2, str3), zza);
            edit.commit();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        return null;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String zzaf() {
        /*
            r4 = this;
            monitor-enter(r4)
            android.content.SharedPreferences r0 = r4.zzcq     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = "topic_operaion_queue"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0023
            java.lang.String r1 = ","
            java.lang.String[] r0 = r0.split(r1)     // Catch:{ all -> 0x0025 }
            int r1 = r0.length     // Catch:{ all -> 0x0025 }
            r3 = 1
            if (r1 <= r3) goto L_0x0023
            r1 = r0[r3]     // Catch:{ all -> 0x0025 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r0 = r0[r3]     // Catch:{ all -> 0x0025 }
            monitor-exit(r4)
            return r0
        L_0x0023:
            monitor-exit(r4)
            return r2
        L_0x0025:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzao.zzaf():java.lang.String");
    }

    public final synchronized void zzag() {
        this.zzcs.clear();
        zzp.zza(this.zzz);
        this.zzcq.edit().clear().commit();
    }

    public final synchronized zzap zzb(String str, String str2, String str3) {
        return zzap.zzi(this.zzcq.getString(zza(str, str2, str3), (String) null));
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        String zza = zza(str, str2, str3);
        SharedPreferences.Editor edit = this.zzcq.edit();
        edit.remove(zza);
        edit.commit();
    }

    public final synchronized boolean zzf(String str) {
        boolean z;
        String string = this.zzcq.getString("topic_operaion_queue", "");
        String valueOf = String.valueOf(",");
        String valueOf2 = String.valueOf(str);
        if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
            String valueOf3 = String.valueOf(",");
            String valueOf4 = String.valueOf(str);
            this.zzcq.edit().putString("topic_operaion_queue", string.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length())).apply();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized zzq zzg(String str) {
        zzq zzq;
        zzq zzq2 = this.zzcs.get(str);
        if (zzq2 != null) {
            return zzq2;
        }
        try {
            zzq = this.zzcr.zzb(this.zzz, str);
        } catch (zzr unused) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.getInstance().zzk();
            zzq = this.zzcr.zzc(this.zzz, str);
        }
        this.zzcs.put(str, zzq);
        return zzq;
    }

    public final synchronized void zzh(String str) {
        String concat = String.valueOf(str).concat("|T|");
        SharedPreferences.Editor edit = this.zzcq.edit();
        for (String next : this.zzcq.getAll().keySet()) {
            if (next.startsWith(concat)) {
                edit.remove(next);
            }
        }
        edit.commit();
    }
}
