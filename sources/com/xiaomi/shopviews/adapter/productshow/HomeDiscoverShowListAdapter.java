package com.xiaomi.shopviews.adapter.productshow;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.DiscoverExtendedBean;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.utils.TimeFormater;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class HomeDiscoverShowListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int e = 1;
    private static final int f = 2;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ProviderClickListener f13136a;
    private Context b;
    /* access modifiers changed from: private */
    public PageDataBean c;
    private List<PageDataBean.AssemblyInfoBean> d = new ArrayList();

    public HomeDiscoverShowListAdapter(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
        this.b = context;
        this.c = pageDataBean;
        this.f13136a = providerClickListener;
    }

    public void a(List<PageDataBean.AssemblyInfoBean> list) {
        if (list != null) {
            this.d.clear();
            this.d.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        String a2;
        String a3;
        final PageDataBean.AssemblyInfoBean assemblyInfoBean = this.d.get(i);
        if ((viewHolder instanceof ProductTopViewHolder) && i == 0) {
            ProductTopViewHolder productTopViewHolder = (ProductTopViewHolder) viewHolder;
            if (assemblyInfoBean.r != null && !TextUtils.isEmpty(assemblyInfoBean.r)) {
                try {
                    final DiscoverExtendedBean discoverExtendedBean = (DiscoverExtendedBean) new Gson().fromJson(assemblyInfoBean.r, DiscoverExtendedBean.class);
                    if (discoverExtendedBean != null) {
                        String f2 = discoverExtendedBean.f();
                        if (f2 != null && !TextUtils.isEmpty(f2)) {
                            productTopViewHolder.c.setText(f2);
                        }
                        if (!(discoverExtendedBean.a() == null || (a3 = TimeFormater.a(discoverExtendedBean.a().longValue())) == null || TextUtils.isEmpty(a3))) {
                            productTopViewHolder.e.setText(a3);
                        }
                        productTopViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (HomeDiscoverShowListAdapter.this.f13136a != null) {
                                    HomeDiscoverShowListAdapter.this.f13136a.a(HomeDiscoverShowListAdapter.this.c.b, assemblyInfoBean, discoverExtendedBean.e());
                                    HomeDiscoverShowListAdapter.this.f13136a.a(String.format(EventRecordConstants.EventID.b, new Object[]{Integer.valueOf(i + 1)}), EventRecordConstants.PageID.h);
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (assemblyInfoBean.d != null && !TextUtils.isEmpty(assemblyInfoBean.d)) {
                productTopViewHolder.b.setText(assemblyInfoBean.d);
            }
            int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
            if (i2 != 0) {
                double a4 = (double) (i2 - Coder.a((Context) WidgetApplication.f13060a, 30.0f));
                Double.isNaN(a4);
                int i3 = (int) (a4 / 2.1d);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) productTopViewHolder.f13142a.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = i3;
                productTopViewHolder.f13142a.setLayoutParams(layoutParams);
            }
            if (assemblyInfoBean.b != null && !TextUtils.isEmpty(assemblyInfoBean.b)) {
                Option b2 = new Option().b(R.drawable.default_pic_small_inverse);
                b2.a(Coder.a(productTopViewHolder.f13142a.getContext(), 10.0f));
                ImageLoader.a().a(assemblyInfoBean.b, productTopViewHolder.f13142a, b2);
            }
            productTopViewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            if (this.f13136a != null) {
                this.f13136a.a(this.c.b, assemblyInfoBean);
            }
        } else if (viewHolder instanceof ProductNormalViewHolder) {
            ProductNormalViewHolder productNormalViewHolder = (ProductNormalViewHolder) viewHolder;
            if (assemblyInfoBean.r != null && !TextUtils.isEmpty(assemblyInfoBean.r)) {
                try {
                    final DiscoverExtendedBean discoverExtendedBean2 = (DiscoverExtendedBean) new Gson().fromJson(assemblyInfoBean.r, DiscoverExtendedBean.class);
                    if (discoverExtendedBean2 != null) {
                        String f3 = discoverExtendedBean2.f();
                        if (f3 != null && !TextUtils.isEmpty(f3)) {
                            productNormalViewHolder.c.setText(f3);
                        }
                        if (!(discoverExtendedBean2.a() == null || (a2 = TimeFormater.a(discoverExtendedBean2.a().longValue())) == null || TextUtils.isEmpty(a2))) {
                            productNormalViewHolder.e.setText(a2);
                        }
                        productNormalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (HomeDiscoverShowListAdapter.this.f13136a != null) {
                                    HomeDiscoverShowListAdapter.this.f13136a.a(HomeDiscoverShowListAdapter.this.c.b, assemblyInfoBean, discoverExtendedBean2.e());
                                    HomeDiscoverShowListAdapter.this.f13136a.a(String.format(EventRecordConstants.EventID.b, new Object[]{Integer.valueOf(i + 1)}), EventRecordConstants.PageID.h);
                                }
                            }
                        });
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            if (assemblyInfoBean.d != null && !TextUtils.isEmpty(assemblyInfoBean.d)) {
                productNormalViewHolder.b.setText(assemblyInfoBean.d);
            }
            if (assemblyInfoBean.b != null && !TextUtils.isEmpty(assemblyInfoBean.b)) {
                Option b3 = new Option().b(R.drawable.default_pic_small_inverse);
                b3.a(Coder.a(productNormalViewHolder.f13141a.getContext(), 10.0f));
                ImageLoader.a().a(assemblyInfoBean.b, productNormalViewHolder.f13141a, b3);
            }
            if (this.f13136a != null) {
                this.f13136a.a(this.c.b, assemblyInfoBean);
            }
            productNormalViewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new ProductTopViewHolder(LayoutInflater.from(this.b).inflate(R.layout.home_discover_list_top_item, viewGroup, false));
        }
        return new ProductNormalViewHolder(LayoutInflater.from(this.b).inflate(R.layout.home_discover_list_normal_item, viewGroup, false));
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        if (i != 0) {
            return 2;
        }
        return super.getItemViewType(i);
    }

    public int getItemCount() {
        return this.d.size();
    }

    static class ProductTopViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f13142a;
        CustomTextView b;
        CustomTextView c;
        ImageView d;
        CustomTextView e;

        ProductTopViewHolder(View view) {
            super(view);
            this.f13142a = (ImageView) view.findViewById(R.id.iv_product_image);
            this.b = (CustomTextView) view.findViewById(R.id.tv_title);
            this.d = (ImageView) view.findViewById(R.id.iv_like);
            this.c = (CustomTextView) view.findViewById(R.id.tv_like_number);
            this.e = (CustomTextView) view.findViewById(R.id.tv_time);
        }
    }

    static class ProductNormalViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f13141a;
        CustomTextView b;
        CustomTextView c;
        ImageView d;
        CustomTextView e;

        ProductNormalViewHolder(View view) {
            super(view);
            this.f13141a = (ImageView) view.findViewById(R.id.iv_product_image);
            this.b = (CustomTextView) view.findViewById(R.id.tv_title);
            this.d = (ImageView) view.findViewById(R.id.iv_like);
            this.c = (CustomTextView) view.findViewById(R.id.tv_like_number);
            this.e = (CustomTextView) view.findViewById(R.id.tv_time);
        }
    }
}
