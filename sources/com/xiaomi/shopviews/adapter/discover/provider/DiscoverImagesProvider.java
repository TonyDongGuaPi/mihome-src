package com.xiaomi.shopviews.adapter.discover.provider;

import android.text.TextUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.google.gson.Gson;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.DiscoverExtendedBean;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class DiscoverImagesProvider extends BaseItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 15;
    }

    public DiscoverImagesProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public DiscoverImagesProvider() {
    }

    public int b() {
        return R.layout.discover_article_list_item;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x02b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.chad.library.adapter.base.BaseViewHolder r22, com.xiaomi.shopviews.model.item.PageDataBean r23, int r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            android.view.View r4 = r1.itemView
            int r5 = com.xiaomi.shopviews.widget.R.id.tv_title
            android.view.View r5 = r4.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            int r6 = com.xiaomi.shopviews.widget.R.id.tv_content
            android.view.View r6 = r4.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            int r7 = com.xiaomi.shopviews.widget.R.id.tv_addtime
            android.view.View r7 = r4.findViewById(r7)
            android.widget.TextView r7 = (android.widget.TextView) r7
            int r8 = com.xiaomi.shopviews.widget.R.id.tv_viewCount
            android.view.View r8 = r4.findViewById(r8)
            android.widget.TextView r8 = (android.widget.TextView) r8
            int r9 = com.xiaomi.shopviews.widget.R.id.id_discover_article_iv
            android.view.View r9 = r4.findViewById(r9)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            int r10 = com.xiaomi.shopviews.widget.R.id.id_discover_gallery_1
            android.view.View r10 = r4.findViewById(r10)
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            int r11 = com.xiaomi.shopviews.widget.R.id.id_discover_gallery_2
            android.view.View r11 = r4.findViewById(r11)
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            int r12 = com.xiaomi.shopviews.widget.R.id.id_discover_gallery_3
            android.view.View r12 = r4.findViewById(r12)
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            int r13 = com.xiaomi.shopviews.widget.R.id.id_video_player_btn
            android.view.View r4 = r4.findViewById(r13)
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            r13 = 8
            r4.setVisibility(r13)
            r4 = 0
            r10.setVisibility(r4)
            r11.setVisibility(r4)
            r12.setVisibility(r4)
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r14 = r2.v
            java.lang.Object r14 = r14.get(r4)
            com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean r14 = (com.xiaomi.shopviews.model.item.PageDataBean.AssemblyInfoBean) r14
            java.lang.String r15 = r14.r
            if (r15 == 0) goto L_0x0260
            java.lang.String r15 = r14.r
            boolean r15 = android.text.TextUtils.isEmpty(r15)
            if (r15 != 0) goto L_0x0260
            com.google.gson.Gson r15 = new com.google.gson.Gson
            r15.<init>()
            java.lang.String r13 = r14.r
            java.lang.Class<com.xiaomi.shopviews.model.item.DiscoverExtendedBean> r4 = com.xiaomi.shopviews.model.item.DiscoverExtendedBean.class
            java.lang.Object r4 = r15.fromJson((java.lang.String) r13, r4)
            com.xiaomi.shopviews.model.item.DiscoverExtendedBean r4 = (com.xiaomi.shopviews.model.item.DiscoverExtendedBean) r4
            if (r4 == 0) goto L_0x00b6
            java.lang.String r13 = r4.f()
            if (r13 == 0) goto L_0x0099
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0099
            java.lang.String r13 = r4.f()
            r8.setText(r13)
        L_0x0099:
            java.lang.Long r8 = r4.a()
            if (r8 == 0) goto L_0x00b6
            java.lang.Long r8 = r4.a()
            long r17 = r8.longValue()
            java.lang.String r8 = com.xiaomi.shopviews.utils.TimeFormater.a(r17)
            if (r8 == 0) goto L_0x00b6
            boolean r13 = android.text.TextUtils.isEmpty(r8)
            if (r13 != 0) goto L_0x00b6
            r7.setText(r8)
        L_0x00b6:
            if (r4 == 0) goto L_0x0260
            java.util.List r7 = r4.b()
            if (r7 == 0) goto L_0x0260
            java.util.List r7 = r4.b()
            int r7 = r7.size()
            r17 = 4611911198408756429(0x4000cccccccccccd, double:2.1)
            r13 = 2
            r15 = 1092616192(0x41200000, float:10.0)
            r8 = 1
            if (r7 == r8) goto L_0x00db
            java.util.List r7 = r4.b()
            int r7 = r7.size()
            if (r7 != r13) goto L_0x0147
        L_0x00db:
            r7 = 0
            r9.setVisibility(r7)
            r7 = 4
            r10.setVisibility(r7)
            r7 = 8
            r11.setVisibility(r7)
            r12.setVisibility(r7)
            com.xiaomi.base.imageloader.Option r7 = new com.xiaomi.base.imageloader.Option
            r7.<init>()
            int r13 = com.xiaomi.shopviews.widget.R.drawable.default_pic_small_inverse
            com.xiaomi.base.imageloader.Option r7 = r7.b((int) r13)
            android.content.Context r13 = r9.getContext()
            int r13 = com.xiaomi.base.utils.Coder.a((android.content.Context) r13, (float) r15)
            r7.a((int) r13)
            r7.e = r8
            r7.d = r8
            android.content.res.Resources r16 = android.content.res.Resources.getSystem()
            android.util.DisplayMetrics r8 = r16.getDisplayMetrics()
            int r8 = r8.widthPixels
            if (r8 == 0) goto L_0x0131
            android.content.Context r15 = r9.getContext()
            r13 = 1106247680(0x41f00000, float:30.0)
            int r15 = com.xiaomi.base.utils.Coder.a((android.content.Context) r15, (float) r13)
            int r8 = r8 - r15
            double r1 = (double) r8
            java.lang.Double.isNaN(r1)
            double r1 = r1 / r17
            int r1 = (int) r1
            android.view.ViewGroup$LayoutParams r2 = r9.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r2 = (android.support.constraint.ConstraintLayout.LayoutParams) r2
            r8 = -1
            r2.width = r8
            r2.height = r1
            r9.setLayoutParams(r2)
        L_0x0131:
            com.xiaomi.base.imageloader.ImageLoaderInterface r1 = com.xiaomi.base.imageloader.ImageLoader.a()
            java.util.List r2 = r4.b()
            r8 = 0
            java.lang.Object r2 = r2.get(r8)
            com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean r2 = (com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean) r2
            java.lang.String r2 = r2.a()
            r1.a((java.lang.String) r2, (android.widget.ImageView) r9, (com.xiaomi.base.imageloader.Option) r7)
        L_0x0147:
            java.util.List r1 = r4.b()
            int r1 = r1.size()
            r2 = 3
            if (r1 < r2) goto L_0x0260
            com.xiaomi.base.imageloader.Option r1 = new com.xiaomi.base.imageloader.Option
            r1.<init>()
            int r2 = com.xiaomi.shopviews.widget.R.drawable.default_pic_small_inverse
            com.xiaomi.base.imageloader.Option r1 = r1.b((int) r2)
            android.content.Context r2 = r10.getContext()
            r7 = 1092616192(0x41200000, float:10.0)
            int r2 = com.xiaomi.base.utils.Coder.a((android.content.Context) r2, (float) r7)
            r1.a((int) r2)
            r2 = 1
            r1.d = r2
            r1.e = r2
            r1.c = r2
            com.xiaomi.base.imageloader.Option r8 = new com.xiaomi.base.imageloader.Option
            r8.<init>()
            int r9 = com.xiaomi.shopviews.widget.R.drawable.default_pic_small_inverse
            com.xiaomi.base.imageloader.Option r8 = r8.b((int) r9)
            android.content.Context r9 = r11.getContext()
            int r9 = com.xiaomi.base.utils.Coder.a((android.content.Context) r9, (float) r7)
            r8.a((int) r9)
            r8.d = r2
            r8.e = r2
            r8.b = r2
            com.xiaomi.base.imageloader.Option r9 = new com.xiaomi.base.imageloader.Option
            r9.<init>()
            int r13 = com.xiaomi.shopviews.widget.R.drawable.default_pic_small_inverse
            com.xiaomi.base.imageloader.Option r9 = r9.b((int) r13)
            android.content.Context r13 = r12.getContext()
            int r7 = com.xiaomi.base.utils.Coder.a((android.content.Context) r13, (float) r7)
            r9.a((int) r7)
            r9.d = r2
            r9.e = r2
            r9.b = r2
            r9.c = r2
            android.content.res.Resources r2 = android.content.res.Resources.getSystem()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            if (r2 == 0) goto L_0x0217
            android.content.Context r7 = r10.getContext()
            r13 = 1106247680(0x41f00000, float:30.0)
            int r7 = com.xiaomi.base.utils.Coder.a((android.content.Context) r7, (float) r13)
            int r7 = r2 - r7
            r19 = r6
            double r6 = (double) r7
            java.lang.Double.isNaN(r6)
            double r6 = r6 / r17
            int r6 = (int) r6
            android.content.Context r7 = r10.getContext()
            r13 = 1120403456(0x42c80000, float:100.0)
            int r7 = com.xiaomi.base.utils.Coder.a((android.content.Context) r7, (float) r13)
            r20 = r14
            double r13 = (double) r6
            r15 = 4611798608418072166(0x4000666666666666, double:2.05)
            java.lang.Double.isNaN(r13)
            double r13 = r13 / r15
            int r13 = (int) r13
            android.content.Context r14 = r10.getContext()
            r15 = 1107820544(0x42080000, float:34.0)
            int r14 = com.xiaomi.base.utils.Coder.a((android.content.Context) r14, (float) r15)
            int r2 = r2 - r14
            int r2 = r2 - r7
            android.view.ViewGroup$LayoutParams r14 = r10.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r14 = (android.support.constraint.ConstraintLayout.LayoutParams) r14
            r14.width = r2
            r14.height = r6
            r10.setLayoutParams(r14)
            android.view.ViewGroup$LayoutParams r2 = r11.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r2 = (android.support.constraint.ConstraintLayout.LayoutParams) r2
            r2.width = r7
            r2.height = r13
            r11.setLayoutParams(r2)
            android.view.ViewGroup$LayoutParams r2 = r12.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r2 = (android.support.constraint.ConstraintLayout.LayoutParams) r2
            r2.width = r7
            r2.height = r13
            r12.setLayoutParams(r2)
            goto L_0x021b
        L_0x0217:
            r19 = r6
            r20 = r14
        L_0x021b:
            com.xiaomi.base.imageloader.ImageLoaderInterface r2 = com.xiaomi.base.imageloader.ImageLoader.a()
            java.util.List r6 = r4.b()
            r7 = 0
            java.lang.Object r6 = r6.get(r7)
            com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean r6 = (com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean) r6
            java.lang.String r6 = r6.a()
            r2.a((java.lang.String) r6, (android.widget.ImageView) r10, (com.xiaomi.base.imageloader.Option) r1)
            com.xiaomi.base.imageloader.ImageLoaderInterface r1 = com.xiaomi.base.imageloader.ImageLoader.a()
            java.util.List r2 = r4.b()
            r6 = 1
            java.lang.Object r2 = r2.get(r6)
            com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean r2 = (com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean) r2
            java.lang.String r2 = r2.a()
            r1.a((java.lang.String) r2, (android.widget.ImageView) r11, (com.xiaomi.base.imageloader.Option) r8)
            com.xiaomi.base.imageloader.ImageLoaderInterface r1 = com.xiaomi.base.imageloader.ImageLoader.a()
            java.util.List r2 = r4.b()
            r4 = 2
            java.lang.Object r2 = r2.get(r4)
            com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean r2 = (com.xiaomi.shopviews.model.item.DiscoverExtendedGalleryBean) r2
            java.lang.String r2 = r2.a()
            r1.a((java.lang.String) r2, (android.widget.ImageView) r12, (com.xiaomi.base.imageloader.Option) r9)
            r14 = r20
            goto L_0x0262
        L_0x0260:
            r19 = r6
        L_0x0262:
            java.lang.String r1 = r14.d
            if (r1 == 0) goto L_0x0273
            java.lang.String r1 = r14.d
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0273
            java.lang.String r1 = r14.d
            r5.setText(r1)
        L_0x0273:
            java.lang.String r1 = r14.i
            if (r1 == 0) goto L_0x0286
            java.lang.String r1 = r14.i
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0286
            java.lang.String r1 = r14.i
            r6 = r19
            r6.setText(r1)
        L_0x0286:
            com.xiaomi.shopviews.adapter.ProviderClickListener r1 = r0.c
            if (r1 == 0) goto L_0x02b9
            r1 = r23
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r2 = r1.v
            r4 = 0
            java.lang.Object r2 = r2.get(r4)
            com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean r2 = (com.xiaomi.shopviews.model.item.PageDataBean.AssemblyInfoBean) r2
            java.lang.String r2 = r2.f13217a
            if (r2 == 0) goto L_0x02bb
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r2 = r1.v
            java.lang.Object r2 = r2.get(r4)
            com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean r2 = (com.xiaomi.shopviews.model.item.PageDataBean.AssemblyInfoBean) r2
            java.lang.String r2 = r2.f13217a
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x02bb
            com.xiaomi.shopviews.adapter.ProviderClickListener r2 = r0.c
            java.lang.String r5 = r1.b
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r6 = r1.v
            java.lang.Object r4 = r6.get(r4)
            com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean r4 = (com.xiaomi.shopviews.model.item.PageDataBean.AssemblyInfoBean) r4
            r2.a((java.lang.String) r5, (com.xiaomi.shopviews.model.item.PageDataBean.AssemblyInfoBean) r4)
            goto L_0x02bb
        L_0x02b9:
            r1 = r23
        L_0x02bb:
            int r2 = com.xiaomi.shopviews.widget.R.id.id_discover_gallery_1
            r4 = r22
            r4.b((int) r2)
            int r2 = com.xiaomi.shopviews.widget.R.id.id_discover_gallery_2
            r4.b((int) r2)
            int r2 = com.xiaomi.shopviews.widget.R.id.id_discover_gallery_3
            r4.b((int) r2)
            com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider$1 r2 = new com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider$1
            r2.<init>(r4, r1, r3)
            r10.setOnClickListener(r2)
            com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider$2 r2 = new com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider$2
            r2.<init>(r4, r1, r3)
            r11.setOnClickListener(r2)
            com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider$3 r2 = new com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider$3
            r2.<init>(r4, r1, r3)
            r12.setOnClickListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider.a(com.chad.library.adapter.base.BaseViewHolder, com.xiaomi.shopviews.model.item.PageDataBean, int):void");
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        a(baseViewHolder, pageDataBean, i, 0);
    }

    /* access modifiers changed from: private */
    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i, int i2) {
        DiscoverExtendedBean discoverExtendedBean;
        if (this.c != null) {
            String str = "";
            if (!(pageDataBean.v.get(0).r == null || TextUtils.isEmpty(pageDataBean.v.get(0).r) || (discoverExtendedBean = (DiscoverExtendedBean) new Gson().fromJson(pageDataBean.v.get(0).r, DiscoverExtendedBean.class)) == null)) {
                str = discoverExtendedBean.e();
            }
            PageDataBean pageDataBean2 = pageDataBean;
            this.c.a(pageDataBean2, pageDataBean.v.get(0).f13217a, str, pageDataBean.v.get(0).D, i2, i);
        }
    }
}
