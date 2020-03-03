package com.xiaomi.shopviews.adapter.advertisement;

import android.widget.ImageView;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class HomeAdvertiseGalleryProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private BGABanner.Delegate<ImageView, PageDataBean.AssemblyInfoBean> c;
    private ProviderClickListener d;

    public int a() {
        return 2;
    }

    public HomeAdvertiseGalleryProvider(ProviderClickListener providerClickListener) {
        this.d = providerClickListener;
    }

    /* access modifiers changed from: private */
    public void onClick(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.d != null) {
            this.d.a(str, assemblyInfoBean, "");
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.d != null) {
            this.d.a(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.d != null) {
            this.d.a(str, assemblyInfoBean);
        }
    }

    public int b() {
        return R.layout.advertise_gallery_item;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.chad.library.adapter.base.BaseViewHolder r8, final com.xiaomi.shopviews.model.item.PageDataBean r9, int r10) {
        /*
            r7 = this;
            android.view.View r8 = r8.itemView
            int r10 = com.xiaomi.shopviews.widget.R.id.banner
            android.view.View r8 = r8.findViewById(r10)
            cn.bingoogolapple.bgabanner.BGABanner r8 = (cn.bingoogolapple.bgabanner.BGABanner) r8
            int r10 = r9.q
            if (r10 == 0) goto L_0x0013
            int r10 = r9.q
            r8.setAutoPlayInterval(r10)
        L_0x0013:
            android.content.res.Resources r10 = android.content.res.Resources.getSystem()
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            int r10 = r10.widthPixels
            r0 = 0
            r1 = 1
            if (r10 == 0) goto L_0x0075
            int r2 = r10 * 20
            int r2 = r2 / 72
            java.lang.String r3 = r9.b
            int r4 = r3.hashCode()
            r5 = -1684161233(0xffffffff9b9dbd2f, float:-2.6095728E-22)
            r6 = -1
            if (r4 == r5) goto L_0x0050
            r5 = 739542390(0x2c148576, float:2.110615E-12)
            if (r4 == r5) goto L_0x0046
            r5 = 1275590406(0x4c07f706, float:3.5642392E7)
            if (r4 == r5) goto L_0x003c
            goto L_0x005a
        L_0x003c:
            java.lang.String r4 = "slider_with_dot_middle"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
            r3 = 1
            goto L_0x005b
        L_0x0046:
            java.lang.String r4 = "slider_with_dot_small"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
            r3 = 0
            goto L_0x005b
        L_0x0050:
            java.lang.String r4 = "slider_with_dot_big"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005a
            r3 = 2
            goto L_0x005b
        L_0x005a:
            r3 = -1
        L_0x005b:
            switch(r3) {
                case 0: goto L_0x0068;
                case 1: goto L_0x0064;
                case 2: goto L_0x005f;
                default: goto L_0x005e;
            }
        L_0x005e:
            goto L_0x0068
        L_0x005f:
            int r10 = r10 * 80
            int r2 = r10 / 72
            goto L_0x0068
        L_0x0064:
            int r10 = r10 * 40
            int r2 = r10 / 72
        L_0x0068:
            android.view.ViewGroup$LayoutParams r10 = r8.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r10 = (android.widget.FrameLayout.LayoutParams) r10
            r10.width = r6
            r10.height = r2
            r8.setLayoutParams(r10)
        L_0x0075:
            com.xiaomi.base.imageloader.Option r10 = new com.xiaomi.base.imageloader.Option
            r10.<init>()
            int r2 = com.xiaomi.shopviews.widget.R.drawable.default_pic_small_inverse
            com.xiaomi.base.imageloader.Option r10 = r10.b((int) r2)
            com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseGalleryProvider$1 r2 = new com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseGalleryProvider$1
            r2.<init>(r10, r9)
            r8.setAdapter(r2)
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r10 = r9.v
            if (r10 == 0) goto L_0x0098
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r10 = r9.v
            int r10 = r10.size()
            if (r10 != r1) goto L_0x0098
            r8.setAutoPlayAble(r0)
            goto L_0x009b
        L_0x0098:
            r8.setAutoPlayAble(r1)
        L_0x009b:
            java.util.List<com.xiaomi.shopviews.model.item.PageDataBean$AssemblyInfoBean> r10 = r9.v
            r0 = 0
            r8.setData(r10, r0)
            com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseGalleryProvider$2 r10 = new com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseGalleryProvider$2
            r10.<init>(r9)
            r8.setDelegate(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseGalleryProvider.a(com.chad.library.adapter.base.BaseViewHolder, com.xiaomi.shopviews.model.item.PageDataBean, int):void");
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
