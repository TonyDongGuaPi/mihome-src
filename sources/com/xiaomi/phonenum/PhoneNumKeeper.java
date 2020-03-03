package com.xiaomi.phonenum;

import android.content.Context;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.innetdate.InNetDateHelper;
import com.xiaomi.phonenum.innetdate.InNetDateResult;
import com.xiaomi.phonenum.obtain.PhoneLevel;
import com.xiaomi.phonenum.phone.PhoneUtil;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PhoneNumKeeper {

    /* renamed from: a  reason: collision with root package name */
    private final int f12549a;
    private ExecutorService[] b = new ExecutorService[this.f12549a];
    /* access modifiers changed from: private */
    public PhoneNumGetter c;

    public interface SetupFinishedListener {
        void onSetupFinished(Error error);
    }

    PhoneNumKeeper(PhoneUtil phoneUtil) {
        this.f12549a = phoneUtil.a();
    }

    public void a(PhoneNumGetter phoneNumGetter) {
        this.c = phoneNumGetter;
    }

    public void a(SetupFinishedListener setupFinishedListener) {
        this.c.a(setupFinishedListener);
    }

    public int a() {
        return this.f12549a;
    }

    public synchronized Future<PhoneNum> a(int i) {
        return a(i, PhoneLevel.CACHE);
    }

    public synchronized Future<PhoneNum> b(int i) {
        return a(i, PhoneLevel.SMS_VERIFY);
    }

    public synchronized Future<PhoneNum> a(final int i, final PhoneLevel phoneLevel) {
        FutureTask futureTask;
        futureTask = new FutureTask(new Callable<PhoneNum>() {
            /* renamed from: a */
            public PhoneNum call() throws ExecutionException, InterruptedException, IOException {
                return PhoneNumKeeper.this.c.b(i, phoneLevel);
            }
        });
        if (this.b[i] == null) {
            this.b[i] = Executors.newSingleThreadExecutor();
        }
        this.b[i].execute(futureTask);
        return futureTask;
    }

    public boolean c(int i) {
        return this.c.a(i, this.c.a(i, PhoneLevel.LINE_NUMBER));
    }

    @Deprecated
    public boolean d(int i) {
        return c(i);
    }

    public PhoneNum e(int i) {
        return this.c.a(i, PhoneLevel.LINE_NUMBER);
    }

    public PhoneNum b(int i, PhoneLevel phoneLevel) {
        return this.c.a(i, phoneLevel);
    }

    public InNetDateResult a(Context context, int i) throws IOException {
        return new InNetDateHelper(context).a(i);
    }

    public void b() {
        this.c.a();
    }
}
