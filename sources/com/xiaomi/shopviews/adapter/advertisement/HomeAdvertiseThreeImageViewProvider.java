package com.xiaomi.shopviews.adapter.advertisement;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;

public class HomeAdvertiseThreeImageViewProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 3;
    }

    public HomeAdvertiseThreeImageViewProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    /* access modifiers changed from: private */
    public void onClick(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.c != null) {
            this.c.a(str, assemblyInfoBean, "");
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.c != null) {
            this.c.a(str, str2);
        }
    }

    private void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.c != null) {
            this.c.a(str, assemblyInfoBean);
        }
    }

    public int b() {
        return R.layout.advertise_three_image_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, final PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (i2 != 0) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = (i2 * 11) / 12;
            layoutParams.bottomMargin = Coder.a(view.getContext(), 20.0f);
            view.setLayoutParams(layoutParams);
        }
        if (pageDataBean.v.size() > 0) {
            Option option = new Option();
            option.a(Coder.a(view.getContext(), 10.0f));
            option.b(R.drawable.default_pic_small_inverse);
            option.c = true;
            option.e = true;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout1);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setVisibility(0);
            ImageLoader.a().a(pageDataBean.v.get(0).b, imageView, option);
            CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.new_price);
            CustomTextView customTextView2 = (CustomTextView) view.findViewById(R.id.old_price);
            ((CustomTextView) view.findViewById(R.id.tv_title)).setText(pageDataBean.v.get(0).d);
            if (!TextUtils.isEmpty(pageDataBean.v.get(0).e)) {
                StringBuilder sb = new StringBuilder();
                sb.append(pageDataBean.v.get(0).E ? "" : HomeRvAdapter.c());
                sb.append(pageDataBean.v.get(0).e);
                customTextView.setText(sb.toString());
            }
            if (!TextUtils.isEmpty(pageDataBean.v.get(0).f)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(pageDataBean.v.get(0).E ? "" : HomeRvAdapter.c());
                sb2.append(pageDataBean.v.get(0).f);
                customTextView2.setText(sb2.toString());
            }
            customTextView2.getPaint().setAntiAlias(true);
            customTextView2.getPaint().setFlags(16);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeAdvertiseThreeImageViewProvider.this.onClick(pageDataBean.b, pageDataBean.v.get(0));
                    HomeAdvertiseThreeImageViewProvider.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{1}), EventRecordConstants.PageID.d);
                }
            });
            a(pageDataBean.b, pageDataBean.v.get(0));
        }
        if (pageDataBean.v.size() > 1) {
            Option option2 = new Option();
            option2.a(Coder.a(view.getContext(), 10.0f));
            option2.b(R.drawable.default_pic_small_inverse);
            option2.b = true;
            option2.d = true;
            option2.e = true;
            RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
            imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.a().a(pageDataBean.v.get(1).b, imageView2, option2);
            CustomTextView customTextView3 = (CustomTextView) view.findViewById(R.id.new_price2);
            CustomTextView customTextView4 = (CustomTextView) view.findViewById(R.id.old_price2);
            ((CustomTextView) view.findViewById(R.id.tv_title2)).setText(pageDataBean.v.get(1).d);
            if (!TextUtils.isEmpty(pageDataBean.v.get(1).e)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(pageDataBean.v.get(1).E ? "" : HomeRvAdapter.c());
                sb3.append(pageDataBean.v.get(1).e);
                customTextView3.setText(sb3.toString());
            }
            if (!TextUtils.isEmpty(pageDataBean.v.get(1).f)) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(pageDataBean.v.get(1).E ? "" : HomeRvAdapter.c());
                sb4.append(pageDataBean.v.get(1).f);
                customTextView4.setText(sb4.toString());
            }
            customTextView4.getPaint().setAntiAlias(true);
            customTextView4.getPaint().setFlags(16);
            relativeLayout2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeAdvertiseThreeImageViewProvider.this.onClick(pageDataBean.b, pageDataBean.v.get(1));
                    HomeAdvertiseThreeImageViewProvider.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{2}), EventRecordConstants.PageID.d);
                }
            });
            a(pageDataBean.b, pageDataBean.v.get(1));
        }
        if (pageDataBean.v.size() > 2) {
            Option option3 = new Option();
            option3.a(Coder.a(view.getContext(), 10.0f));
            option3.b(R.drawable.default_pic_small_inverse);
            option3.b = true;
            option3.d = true;
            option3.c = true;
            RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.relativeLayout3);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView3);
            imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.a().a(pageDataBean.v.get(2).b, imageView3, option3);
            CustomTextView customTextView5 = (CustomTextView) view.findViewById(R.id.new_price3);
            CustomTextView customTextView6 = (CustomTextView) view.findViewById(R.id.old_price3);
            ((CustomTextView) view.findViewById(R.id.tv_title3)).setText(pageDataBean.v.get(2).d);
            if (!TextUtils.isEmpty(pageDataBean.v.get(2).e)) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(pageDataBean.v.get(2).E ? "" : HomeRvAdapter.c());
                sb5.append(pageDataBean.v.get(2).e);
                customTextView5.setText(sb5.toString());
            }
            if (!TextUtils.isEmpty(pageDataBean.v.get(2).f)) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(pageDataBean.v.get(2).E ? "" : HomeRvAdapter.c());
                sb6.append(pageDataBean.v.get(2).f);
                customTextView6.setText(sb6.toString());
            }
            customTextView6.getPaint().setAntiAlias(true);
            customTextView6.getPaint().setFlags(16);
            relativeLayout3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeAdvertiseThreeImageViewProvider.this.onClick(pageDataBean.b, pageDataBean.v.get(2));
                    HomeAdvertiseThreeImageViewProvider.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{3}), EventRecordConstants.PageID.d);
                }
            });
            a(pageDataBean.b, pageDataBean.v.get(2));
        }
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
