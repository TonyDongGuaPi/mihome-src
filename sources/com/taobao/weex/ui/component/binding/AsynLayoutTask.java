package com.taobao.weex.ui.component.binding;

import android.os.AsyncTask;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.ui.component.list.template.TemplateViewHolder;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class AsynLayoutTask extends AsyncTask<Void, Void, Void> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final WXComponent component;
    private final int position;
    private final TemplateViewHolder templateViewHolder;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2299735710954131645L, "com/taobao/weex/ui/component/binding/AsynLayoutTask", 24);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Object doInBackground(Object[] objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        Void doInBackground = doInBackground((Void[]) objArr);
        $jacocoInit[23] = true;
        return doInBackground;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onPostExecute(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        onPostExecute((Void) obj);
        $jacocoInit[22] = true;
    }

    AsynLayoutTask(TemplateViewHolder templateViewHolder2, int i, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.templateViewHolder = templateViewHolder2;
        this.position = i;
        this.component = wXComponent;
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        r6[9] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Void doInBackground(java.lang.Void... r6) {
        /*
            r5 = this;
            boolean[] r6 = $jacocoInit()
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r0 = r5.templateViewHolder
            int r0 = r0.getHolderPosition()
            int r1 = r5.position
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x0013
            r6[r3] = r3
            goto L_0x005a
        L_0x0013:
            r0 = 2
            r6[r0] = r3
            com.taobao.weex.ui.component.WXComponent r0 = r5.component
            com.taobao.weex.WXSDKInstance r0 = r0.getInstance()
            if (r0 != 0) goto L_0x0022
            r0 = 3
            r6[r0] = r3
            goto L_0x005a
        L_0x0022:
            com.taobao.weex.ui.component.WXComponent r0 = r5.component
            com.taobao.weex.WXSDKInstance r0 = r0.getInstance()
            boolean r0 = r0.isDestroy()
            if (r0 == 0) goto L_0x0032
            r0 = 4
            r6[r0] = r3
            goto L_0x005a
        L_0x0032:
            r0 = 5
            r6[r0] = r3
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r0 = r5.templateViewHolder
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r0 = r0.getTemplateList()
            monitor-enter(r0)
            r1 = 6
            r6[r1] = r3     // Catch:{ all -> 0x0065 }
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r1 = r5.templateViewHolder     // Catch:{ all -> 0x0065 }
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r1 = r1.getTemplateList()     // Catch:{ all -> 0x0065 }
            boolean r1 = r1.isDestoryed()     // Catch:{ all -> 0x0065 }
            if (r1 != 0) goto L_0x005f
            r1 = 7
            r6[r1] = r3     // Catch:{ all -> 0x0065 }
            com.taobao.weex.ui.component.WXComponent r1 = r5.component     // Catch:{ all -> 0x0065 }
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r4 = r5.templateViewHolder     // Catch:{ all -> 0x0065 }
            com.taobao.weex.ui.component.binding.Layouts.doLayoutOnly(r1, r4)     // Catch:{ all -> 0x0065 }
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            r0 = 9
            r6[r0] = r3
        L_0x005a:
            r0 = 11
            r6[r0] = r3
            return r2
        L_0x005f:
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            r0 = 8
            r6[r0] = r3
            return r2
        L_0x0065:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            r0 = 10
            r6[r0] = r3
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.binding.AsynLayoutTask.doInBackground(java.lang.Void[]):java.lang.Void");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.position != this.templateViewHolder.getHolderPosition()) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            if (this.component.getInstance() == null) {
                $jacocoInit[14] = true;
            } else if (this.component.getInstance().isDestroy()) {
                $jacocoInit[15] = true;
            } else {
                $jacocoInit[16] = true;
                Layouts.setLayout(this.component, false);
                $jacocoInit[17] = true;
                if (this.templateViewHolder.getHolderPosition() < 0) {
                    $jacocoInit[18] = true;
                } else {
                    $jacocoInit[19] = true;
                    this.templateViewHolder.getTemplateList().fireEvent(TemplateDom.ATTACH_CELL_SLOT, TemplateDom.findAllComponentRefs(this.templateViewHolder.getTemplateList().getRef(), this.position, this.component));
                    $jacocoInit[20] = true;
                }
            }
        }
        $jacocoInit[21] = true;
    }
}
