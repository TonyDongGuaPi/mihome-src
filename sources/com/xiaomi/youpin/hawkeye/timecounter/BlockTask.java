package com.xiaomi.youpin.hawkeye.timecounter;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.task.BaseTask;
import com.xiaomi.youpin.hawkeye.utils.HLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlockTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23376a = "BlockTask";
    private HandlerThread b = new HandlerThread("blockThread");
    private Handler e;
    private long f;
    private long g;
    /* access modifiers changed from: private */
    public List<String> h = new CopyOnWriteArrayList();
    private Runnable i = new Runnable() {
        public void run() {
            HLog.b("BlockTask", "********  BlockTask    run");
            if (BlockTask.this.e()) {
                StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
                if (BlockTask.this.h != null) {
                    BlockTask.this.h.clear();
                }
                for (StackTraceElement stackTraceElement : stackTrace) {
                    BlockTask.this.h.add(stackTraceElement.toString());
                }
            }
        }
    };

    public String a() {
        return "BlockTask";
    }

    public void aa_() {
        super.aa_();
        HLog.b("BlockTask", "*******  BlockTask  start  ");
        if (e() && !this.b.isAlive()) {
            this.b.start();
            this.e = new Handler(this.b.getLooper());
            HPrinterParser.a();
        }
    }

    public void b() {
        this.g = System.currentTimeMillis();
        this.e.postDelayed(this.i, HawkEye.c().f());
    }

    public void f() {
        long currentTimeMillis = System.currentTimeMillis();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(this.h);
        if (a(currentTimeMillis) && !copyOnWriteArrayList.isEmpty()) {
            this.f = System.currentTimeMillis() - this.g;
            HLog.b("BlockTask", "卡顿时间....   " + this.f + "  *******  mStackInfo  ****   " + this.h);
            a((List<String>) copyOnWriteArrayList);
        }
        this.e.removeCallbacks(this.i);
    }

    private void a(List<String> list) {
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.mTaskName = a();
        blockInfo.blockStack = list;
        blockInfo.blockTime = this.f;
        b(blockInfo);
    }

    private boolean a(long j) {
        return j - this.g > HawkEye.c().f();
    }

    public void d() {
        super.d();
        f();
        HPrinterParser.b();
    }
}
