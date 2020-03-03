package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzhq;
    private final String zzhr;
    private final AtomicInteger zzhs;

    @KeepForSdk
    public NumberedThreadFactory(String str) {
        this(str, 0);
    }

    private NumberedThreadFactory(String str, int i) {
        this.zzhs = new AtomicInteger();
        this.zzhq = Executors.defaultThreadFactory();
        this.zzhr = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.zzhq.newThread(new zza(runnable, 0));
        String str = this.zzhr;
        int andIncrement = this.zzhs.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13);
        sb.append(str);
        sb.append(Operators.ARRAY_START_STR);
        sb.append(andIncrement);
        sb.append(Operators.ARRAY_END_STR);
        newThread.setName(sb.toString());
        return newThread;
    }
}
