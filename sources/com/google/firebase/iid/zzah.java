package com.google.firebase.iid;

import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

final class zzah {
    @GuardedBy("this")
    private final Map<Pair<String, String>, TaskCompletionSource<String>> zzca = new ArrayMap();

    zzah() {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.gms.tasks.TaskCompletionSource<java.lang.String>, com.google.android.gms.tasks.TaskCompletionSource] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(com.google.android.gms.tasks.TaskCompletionSource<java.lang.String> r1) throws java.io.IOException {
        /*
            com.google.android.gms.tasks.Task r1 = r1.getTask()     // Catch:{ ExecutionException -> 0x0012, InterruptedException -> 0x000b }
            java.lang.Object r1 = com.google.android.gms.tasks.Tasks.await(r1)     // Catch:{ ExecutionException -> 0x0012, InterruptedException -> 0x000b }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ ExecutionException -> 0x0012, InterruptedException -> 0x000b }
            return r1
        L_0x000b:
            r1 = move-exception
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0012:
            r1 = move-exception
            java.lang.Throwable r1 = r1.getCause()
            boolean r0 = r1 instanceof java.io.IOException
            if (r0 != 0) goto L_0x0028
            boolean r0 = r1 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0022
            java.lang.RuntimeException r1 = (java.lang.RuntimeException) r1
            throw r1
        L_0x0022:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0028:
            java.io.IOException r1 = (java.io.IOException) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzah.zza(com.google.android.gms.tasks.TaskCompletionSource):java.lang.String");
    }

    private static String zza(zzak zzak, TaskCompletionSource<String> taskCompletionSource) throws IOException {
        try {
            String zzp = zzak.zzp();
            taskCompletionSource.setResult(zzp);
            return zzp;
        } catch (IOException | RuntimeException e) {
            taskCompletionSource.setException(e);
            throw e;
        }
    }

    private final synchronized zzak zzb(String str, String str2, zzak zzak) {
        Pair pair = new Pair(str, str2);
        TaskCompletionSource taskCompletionSource = this.zzca.get(pair);
        if (taskCompletionSource != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 29);
                sb.append("Joining ongoing request for: ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            return new zzai(taskCompletionSource);
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf2 = String.valueOf(pair);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 24);
            sb2.append("Making new request for: ");
            sb2.append(valueOf2);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        this.zzca.put(pair, taskCompletionSource2);
        return new zzaj(this, zzak, taskCompletionSource2, pair);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zza(zzak zzak, TaskCompletionSource taskCompletionSource, Pair pair) throws IOException {
        try {
            String zza = zza(zzak, taskCompletionSource);
            synchronized (this) {
                this.zzca.remove(pair);
            }
            return zza;
        } catch (Throwable th) {
            synchronized (this) {
                this.zzca.remove(pair);
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zza(String str, String str2, zzak zzak) throws IOException {
        return zzb(str, str2, zzak).zzp();
    }
}
