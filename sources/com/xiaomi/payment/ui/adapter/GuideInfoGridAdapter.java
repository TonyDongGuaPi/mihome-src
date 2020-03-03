package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.data.ArrayAdapter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxHomePageGridTask;
import com.xiaomi.payment.ui.item.GuideGridItem;

public class GuideInfoGridAdapter extends ArrayAdapter<RxHomePageGridTask.Result.GuideItemType> {
    public GuideInfoGridAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, RxHomePageGridTask.Result.GuideItemType guideItemType, ViewGroup viewGroup) {
        GuideGridItem guideGridItem = (GuideGridItem) LayoutInflater.from(this.f7498a).inflate(R.layout.mibi_guide_grid_item, viewGroup, false);
        guideGridItem.bind();
        return guideGridItem;
    }

    public final void a(View view, int i, RxHomePageGridTask.Result.GuideItemType guideItemType) {
        if (guideItemType != null) {
            ((GuideGridItem) view).rebind(guideItemType);
            return;
        }
        throw new IllegalStateException("GuideItemData data is null at this position " + i);
    }
}
