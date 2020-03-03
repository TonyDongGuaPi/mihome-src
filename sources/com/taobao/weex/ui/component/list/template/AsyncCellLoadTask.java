package com.taobao.weex.ui.component.list.template;

import android.os.AsyncTask;
import android.os.Looper;
import android.os.MessageQueue;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.ui.component.list.WXCell;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class AsyncCellLoadTask extends AsyncTask<Void, Void, Void> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXCell source;
    private String template;
    private WXRecyclerTemplateList templateList;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-83502848425343874L, "com/taobao/weex/ui/component/list/template/AsyncCellLoadTask", 32);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ boolean access$000(AsyncCellLoadTask asyncCellLoadTask) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isDestory = asyncCellLoadTask.isDestory();
        $jacocoInit[29] = true;
        return isDestory;
    }

    static /* synthetic */ String access$100(AsyncCellLoadTask asyncCellLoadTask) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = asyncCellLoadTask.template;
        $jacocoInit[30] = true;
        return str;
    }

    static /* synthetic */ WXRecyclerTemplateList access$200(AsyncCellLoadTask asyncCellLoadTask) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerTemplateList wXRecyclerTemplateList = asyncCellLoadTask.templateList;
        $jacocoInit[31] = true;
        return wXRecyclerTemplateList;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Object doInBackground(Object[] objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        Void doInBackground = doInBackground((Void[]) objArr);
        $jacocoInit[28] = true;
        return doInBackground;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onPostExecute(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        onPostExecute((Void) obj);
        $jacocoInit[27] = true;
    }

    public AsyncCellLoadTask(String str, WXCell wXCell, WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        this.template = str;
        this.source = wXCell;
        this.templateList = wXRecyclerTemplateList;
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        boolean[] $jacocoInit = $jacocoInit();
        TemplateCache templateCache = this.templateList.getTemplatesCache().get(this.template);
        if (templateCache == null) {
            $jacocoInit[1] = true;
        } else if (templateCache.cells != null) {
            $jacocoInit[2] = true;
            while (templateCache.cells.size() < this.templateList.getTemplateCacheSize()) {
                $jacocoInit[5] = true;
                System.currentTimeMillis();
                $jacocoInit[6] = true;
                WXCell wXCell = (WXCell) this.templateList.copyComponentFromSourceCell(this.source);
                $jacocoInit[7] = true;
                if (!WXEnvironment.isOpenDebugLog()) {
                    $jacocoInit[8] = true;
                } else {
                    $jacocoInit[9] = true;
                }
                if (wXCell == null) {
                    $jacocoInit[10] = true;
                    return null;
                } else if (isDestory()) {
                    $jacocoInit[11] = true;
                    return null;
                } else {
                    templateCache.cells.add(wXCell);
                    $jacocoInit[12] = true;
                }
            }
            $jacocoInit[13] = true;
            return null;
        } else {
            $jacocoInit[3] = true;
        }
        $jacocoInit[4] = true;
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isDestory()) {
            $jacocoInit[14] = true;
            return;
        }
        final TemplateCache templateCache = this.templateList.getTemplatesCache().get(this.template);
        if (templateCache == null) {
            $jacocoInit[15] = true;
            return;
        }
        if (templateCache.cells == null) {
            $jacocoInit[16] = true;
        } else {
            ConcurrentLinkedQueue<WXCell> concurrentLinkedQueue = templateCache.cells;
            $jacocoInit[17] = true;
            if (concurrentLinkedQueue.size() == 0) {
                $jacocoInit[18] = true;
            } else {
                Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AsyncCellLoadTask this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(6232251107492281049L, "com/taobao/weex/ui/component/list/template/AsyncCellLoadTask$1", 11);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public boolean queueIdle() {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (AsyncCellLoadTask.access$000(this.this$0)) {
                            $jacocoInit[1] = true;
                            return false;
                        }
                        ConcurrentLinkedQueue<WXCell> concurrentLinkedQueue = templateCache.cells;
                        $jacocoInit[2] = true;
                        Iterator<WXCell> it = concurrentLinkedQueue.iterator();
                        $jacocoInit[3] = true;
                        while (it.hasNext()) {
                            $jacocoInit[4] = true;
                            WXCell next = it.next();
                            $jacocoInit[5] = true;
                            if (next.isLazy()) {
                                $jacocoInit[6] = true;
                                AsyncCellLoadTask.access$200(this.this$0);
                                WXRecyclerTemplateList.doCreateCellViewBindData(next, AsyncCellLoadTask.access$100(this.this$0), true);
                                $jacocoInit[7] = true;
                                boolean hasNext = it.hasNext();
                                $jacocoInit[8] = true;
                                return hasNext;
                            }
                            $jacocoInit[9] = true;
                        }
                        $jacocoInit[10] = true;
                        return false;
                    }
                });
                templateCache.isLoadIng = false;
                $jacocoInit[20] = true;
                return;
            }
        }
        templateCache.isLoadIng = false;
        $jacocoInit[19] = true;
    }

    private boolean isDestory() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.source.getInstance() == null) {
            $jacocoInit[21] = true;
        } else {
            WXCell wXCell = this.source;
            $jacocoInit[22] = true;
            if (wXCell.getInstance().isDestroy()) {
                $jacocoInit[23] = true;
            } else {
                boolean isDestoryed = this.templateList.isDestoryed();
                $jacocoInit[25] = true;
                return isDestoryed;
            }
        }
        $jacocoInit[24] = true;
        return true;
    }

    public void startTask() {
        boolean[] $jacocoInit = $jacocoInit();
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        $jacocoInit[26] = true;
    }
}
