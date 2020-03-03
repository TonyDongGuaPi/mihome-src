package com.mi.global.shop.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.order.NewListItem;
import com.mi.global.shop.newmodel.order.NewListProduct;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.RecycleViewDivider;
import com.mi.util.Coder;
import java.util.ArrayList;

public class OrderViewItemListViewAdapter extends ArrayAdapter<NewListProduct> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7039a = "OrderViewItemListViewAdapter";
    private PopupWindow b;
    private RecyclerView c;
    private BundleItemAdapter g;

    public int getItemViewType(int i) {
        return 1;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public static class BundleItemAdapter extends RecyclerView.Adapter<BundleItemViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        public static final String f7043a = "ReviewOrderListAdapter";
        /* access modifiers changed from: private */
        public Context b;
        private ArrayList<NewListItem> c = new ArrayList<>();

        public class BundleItemViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private BundleItemViewHolder f7045a;

            @UiThread
            public BundleItemViewHolder_ViewBinding(BundleItemViewHolder bundleItemViewHolder, View view) {
                this.f7045a = bundleItemViewHolder;
                bundleItemViewHolder.bundleItemImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.bundle_item_image, "field 'bundleItemImage'", SimpleDraweeView.class);
                bundleItemViewHolder.bundleItemName = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.bundle_item_name, "field 'bundleItemName'", CustomTextView.class);
            }

            @CallSuper
            public void unbind() {
                BundleItemViewHolder bundleItemViewHolder = this.f7045a;
                if (bundleItemViewHolder != null) {
                    this.f7045a = null;
                    bundleItemViewHolder.bundleItemImage = null;
                    bundleItemViewHolder.bundleItemName = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public BundleItemAdapter(Context context) {
            this.b = context;
        }

        public void a(ArrayList<NewListItem> arrayList) {
            if (arrayList != null) {
                this.c.clear();
                this.c.addAll(arrayList);
                notifyDataSetChanged();
            }
        }

        public void b(ArrayList<NewListItem> arrayList) {
            if (arrayList != null) {
                this.c.addAll(arrayList);
                notifyDataSetChanged();
            }
        }

        /* renamed from: a */
        public void onBindViewHolder(BundleItemViewHolder bundleItemViewHolder, int i) {
            final NewListItem newListItem = this.c.get(i);
            FrescoUtils.a(newListItem.image_url, bundleItemViewHolder.bundleItemImage);
            bundleItemViewHolder.bundleItemName.setText(newListItem.product_name);
            bundleItemViewHolder.bundleItemImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(BundleItemAdapter.this.b, WebActivity.class);
                    if (TextUtils.isEmpty(newListItem.jump_url)) {
                        intent.putExtra("url", ConnectionHelper.d(newListItem.commodity_id));
                    } else {
                        intent.putExtra("url", newListItem.jump_url);
                    }
                    BundleItemAdapter.this.b.startActivity(intent);
                }
            });
        }

        /* renamed from: a */
        public BundleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new BundleItemViewHolder(LayoutInflater.from(this.b).inflate(R.layout.shop_order_bundle_item, viewGroup, false));
        }

        public int getItemCount() {
            return this.c.size();
        }

        static class BundleItemViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131493043)
            SimpleDraweeView bundleItemImage;
            @BindView(2131493044)
            CustomTextView bundleItemName;

            BundleItemViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    public OrderViewItemListViewAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewListProduct newListProduct, ViewGroup viewGroup) {
        if (newListProduct == null) {
            return null;
        }
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_orderview_item, (ViewGroup) null, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.f7046a = inflate;
        viewHolder.b = (SimpleDraweeView) inflate.findViewById(R.id.item_image);
        viewHolder.c = (ImageView) inflate.findViewById(R.id.iv_arrow);
        viewHolder.f = (CustomTextView) inflate.findViewById(R.id.item_name);
        viewHolder.e = (CustomTextView) inflate.findViewById(R.id.item_desc);
        viewHolder.g = (CustomTextView) inflate.findViewById(R.id.item_dealer);
        viewHolder.d = (CustomTextView) inflate.findViewById(R.id.item_count);
        viewHolder.h = (LinearLayout) inflate.findViewById(R.id.item_bundle);
        inflate.setTag(viewHolder);
        return inflate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r5 = (com.mi.global.shop.user.OrderViewItemListViewAdapter.ViewHolder) r5.getTag();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.view.View r5, int r6, final com.mi.global.shop.newmodel.order.NewListProduct r7) {
        /*
            r4 = this;
            if (r7 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r5 = r5.getTag()
            com.mi.global.shop.user.OrderViewItemListViewAdapter$ViewHolder r5 = (com.mi.global.shop.user.OrderViewItemListViewAdapter.ViewHolder) r5
            if (r5 != 0) goto L_0x000c
            return
        L_0x000c:
            r6 = 1112014848(0x42480000, float:50.0)
            int r6 = com.mi.util.Coder.a((float) r6)
            java.lang.String r0 = r7.image_url
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x001e
            java.lang.String r0 = com.mi.global.shop.util.ImageUtil.a((int) r6, (int) r6, (java.lang.String) r0)
        L_0x001e:
            com.facebook.drawee.view.SimpleDraweeView r6 = r5.b
            com.mi.global.shop.util.fresco.FrescoUtils.a((java.lang.String) r0, (com.facebook.drawee.view.SimpleDraweeView) r6)
            com.facebook.drawee.view.SimpleDraweeView r6 = r5.b
            com.mi.global.shop.user.OrderViewItemListViewAdapter$1 r0 = new com.mi.global.shop.user.OrderViewItemListViewAdapter$1
            r0.<init>(r7)
            r6.setOnClickListener(r0)
            com.mi.global.shop.widget.CustomTextView r6 = r5.f
            java.lang.String r0 = r7.product_name
            r6.setText(r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = com.mi.global.shop.locale.LocaleHelper.e()
            r6.append(r0)
            java.lang.String r0 = r7.price_txt
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            com.mi.global.shop.widget.CustomTextView r0 = r5.d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "X"
            r1.append(r2)
            java.lang.String r2 = r7.product_count
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.setText(r1)
            com.mi.global.shop.widget.CustomTextView r0 = r5.e
            r0.setText(r6)
            com.mi.global.shop.newmodel.order.NewExtentions r6 = r7.extentions
            r0 = 8
            r1 = 0
            if (r6 == 0) goto L_0x0099
            com.mi.global.shop.newmodel.order.NewExtentions r6 = r7.extentions
            java.lang.String r6 = r6.goods_dealer
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0099
            com.mi.global.shop.widget.CustomTextView r6 = r5.g
            r6.setVisibility(r1)
            android.app.Application r6 = com.mi.global.shop.ShopApp.g()
            int r2 = com.mi.global.shop.R.string.goods_dealer
            java.lang.String r6 = r6.getString(r2)
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.mi.global.shop.newmodel.order.NewExtentions r3 = r7.extentions
            java.lang.String r3 = r3.goods_dealer
            r2[r1] = r3
            java.lang.String r6 = java.lang.String.format(r6, r2)
            com.mi.global.shop.widget.CustomTextView r2 = r5.g
            r2.setText(r6)
            goto L_0x009e
        L_0x0099:
            com.mi.global.shop.widget.CustomTextView r6 = r5.g
            r6.setVisibility(r0)
        L_0x009e:
            android.widget.LinearLayout r6 = r5.h
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            com.mi.global.shop.util.UIAdapter r2 = com.mi.global.shop.util.UIAdapter.a()
            r3 = 40
            int r2 = r2.b(r3)
            r3 = 1123024896(0x42f00000, float:120.0)
            int r3 = com.mi.util.Coder.a((float) r3)
            int r2 = r2 - r3
            r6.width = r2
            android.widget.LinearLayout r2 = r5.h
            r2.setLayoutParams(r6)
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewListItem> r6 = r7.list
            if (r6 == 0) goto L_0x00d1
            int r7 = r6.size()
            if (r7 <= 0) goto L_0x00d1
            com.mi.global.shop.widget.CustomTextView r7 = r5.g
            r7.setVisibility(r0)
            android.widget.LinearLayout r7 = r5.h
            r7.setVisibility(r1)
            goto L_0x00db
        L_0x00d1:
            com.mi.global.shop.widget.CustomTextView r7 = r5.g
            r7.setVisibility(r1)
            android.widget.LinearLayout r7 = r5.h
            r7.setVisibility(r0)
        L_0x00db:
            android.widget.LinearLayout r7 = r5.h
            com.mi.global.shop.user.OrderViewItemListViewAdapter$2 r0 = new com.mi.global.shop.user.OrderViewItemListViewAdapter$2
            r0.<init>(r5, r6)
            r7.setOnClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.user.OrderViewItemListViewAdapter.a(android.view.View, int, com.mi.global.shop.newmodel.order.NewListProduct):void");
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f7046a;
        SimpleDraweeView b;
        ImageView c;
        CustomTextView d;
        CustomTextView e;
        CustomTextView f;
        CustomTextView g;
        LinearLayout h;
        int i = 0;

        ViewHolder() {
        }
    }

    public void a(View view, ArrayList<NewListItem> arrayList, final ViewHolder viewHolder) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_order_bundle_pupview, (ViewGroup) null);
        this.c = (RecyclerView) inflate.findViewById(R.id.bundle_recycleview);
        this.c.setLayoutManager(new LinearLayoutManager(this.d));
        this.c.addItemDecoration(new RecycleViewDivider(this.d, 0, Coder.a(0.5f), this.d.getResources().getColor(R.color.divider_color)));
        this.g = new BundleItemAdapter(this.d);
        this.g.a(arrayList);
        this.c.setAdapter(this.g);
        this.b = new PopupWindow(inflate, UIAdapter.a().b(40) - Coder.a(120.0f), -2, false);
        this.b.setFocusable(true);
        this.b.setOutsideTouchable(true);
        this.b.setBackgroundDrawable(new BitmapDrawable());
        this.b.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                viewHolder.i = 0;
                viewHolder.c.setImageResource(R.drawable.shop_bundle_arrow_down);
            }
        });
        if (arrayList != null && arrayList.size() > 0) {
            this.b.showAsDropDown(view);
        }
    }
}
