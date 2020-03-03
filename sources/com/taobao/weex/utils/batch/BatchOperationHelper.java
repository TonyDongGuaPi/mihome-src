package com.taobao.weex.utils.batch;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BatchOperationHelper implements Interceptor {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private boolean isCollecting = false;
    private BactchExecutor mExecutor;
    private CopyOnWriteArrayList<Runnable> sRegisterTasks = new CopyOnWriteArrayList<>();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5646112265097923879L, "com/taobao/weex/utils/batch/BatchOperationHelper", 10);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ CopyOnWriteArrayList access$000(BatchOperationHelper batchOperationHelper) {
        boolean[] $jacocoInit = $jacocoInit();
        CopyOnWriteArrayList<Runnable> copyOnWriteArrayList = batchOperationHelper.sRegisterTasks;
        $jacocoInit[9] = true;
        return copyOnWriteArrayList;
    }

    public BatchOperationHelper(BactchExecutor bactchExecutor) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mExecutor = bactchExecutor;
        $jacocoInit[1] = true;
        bactchExecutor.setInterceptor(this);
        this.isCollecting = true;
        $jacocoInit[2] = true;
    }

    public boolean take(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.isCollecting) {
            $jacocoInit[3] = true;
            this.sRegisterTasks.add(runnable);
            $jacocoInit[4] = true;
            return true;
        }
        $jacocoInit[5] = true;
        return false;
    }

    public void flush() {
        boolean[] $jacocoInit = $jacocoInit();
        this.isCollecting = false;
        $jacocoInit[6] = true;
        this.mExecutor.post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ BatchOperationHelper this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5810220436155501726L, "com/taobao/weex/utils/batch/BatchOperationHelper$1", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Iterator it = BatchOperationHelper.access$000(this.this$0).iterator();
                $jacocoInit[1] = true;
                while (it.hasNext()) {
                    $jacocoInit[2] = true;
                    Runnable runnable = (Runnable) it.next();
                    $jacocoInit[3] = true;
                    runnable.run();
                    $jacocoInit[4] = true;
                    BatchOperationHelper.access$000(this.this$0).remove(runnable);
                    $jacocoInit[5] = true;
                }
                $jacocoInit[6] = true;
            }
        });
        $jacocoInit[7] = true;
        this.mExecutor.setInterceptor((Interceptor) null);
        $jacocoInit[8] = true;
    }
}
