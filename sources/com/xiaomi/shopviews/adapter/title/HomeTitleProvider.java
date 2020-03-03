package com.xiaomi.shopviews.adapter.title;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.List;

public class HomeTitleProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private CustomTextView c;
    private ImageView d;

    public int a() {
        return 5;
    }

    public int b() {
        return R.layout.title_view;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        this.d = (ImageView) view.findViewById(R.id.iv_bg);
        if (!TextUtils.isEmpty(pageDataBean.g)) {
            ImageLoader.a().a(pageDataBean.g, this.d);
        }
        this.c = (CustomTextView) view.findViewById(R.id.titleText);
        List<PageDataBean.AssemblyInfoBean> list = pageDataBean.v;
        if (list == null || list.size() <= 0 || TextUtils.isEmpty(list.get(0).d)) {
            this.c.setText("");
        } else {
            this.c.setText(list.get(0).d);
        }
        if (!TextUtils.isEmpty(pageDataBean.i)) {
            this.c.setTextColor(Color.parseColor(pageDataBean.i));
        }
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
