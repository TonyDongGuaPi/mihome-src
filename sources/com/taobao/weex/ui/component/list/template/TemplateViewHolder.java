package com.taobao.weex.ui.component.list.template;

import android.os.AsyncTask;
import android.view.View;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TemplateViewHolder extends ListBaseViewHolder {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public AsyncTask<Void, Void, Void> asyncTask;
    public Object data;
    private int holderPosition = -1;
    private WXCell template;
    private WXRecyclerTemplateList templateList;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6160606680012518551L, "com/taobao/weex/ui/component/list/template/TemplateViewHolder", 6);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TemplateViewHolder(WXRecyclerTemplateList wXRecyclerTemplateList, WXCell wXCell, int i) {
        super((WXComponent) wXCell, i);
        boolean[] $jacocoInit = $jacocoInit();
        this.template = wXCell;
        this.templateList = wXRecyclerTemplateList;
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TemplateViewHolder(WXRecyclerTemplateList wXRecyclerTemplateList, View view, int i) {
        super(view, i);
        boolean[] $jacocoInit = $jacocoInit();
        this.templateList = wXRecyclerTemplateList;
        $jacocoInit[1] = true;
    }

    public int getHolderPosition() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.holderPosition;
        $jacocoInit[2] = true;
        return i;
    }

    public void setHolderPosition(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.holderPosition = i;
        $jacocoInit[3] = true;
    }

    public WXCell getTemplate() {
        boolean[] $jacocoInit = $jacocoInit();
        WXCell wXCell = this.template;
        $jacocoInit[4] = true;
        return wXCell;
    }

    public WXRecyclerTemplateList getTemplateList() {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerTemplateList wXRecyclerTemplateList = this.templateList;
        $jacocoInit[5] = true;
        return wXRecyclerTemplateList;
    }
}
