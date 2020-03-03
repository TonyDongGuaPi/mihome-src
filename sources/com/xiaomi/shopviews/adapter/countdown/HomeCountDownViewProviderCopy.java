package com.xiaomi.shopviews.adapter.countdown;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.countdown.DynamicConfig;
import com.xiaomi.shopviews.model.item.HomeItemContentCountDown;
import com.xiaomi.shopviews.utils.VirtualViewUtils.VirtualViewUpdateUtil;
import com.xiaomi.shopviews.widget.R;
import java.io.FileInputStream;
import java.io.IOException;

public class HomeCountDownViewProviderCopy extends HomeItemProvider<HomeItemContentCountDown, BaseViewHolder> {
    public int a() {
        return 6;
    }

    public int b() {
        return R.layout.count_down_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentCountDown homeItemContentCountDown, int i) {
        LinearLayout linearLayout = (LinearLayout) baseViewHolder.itemView.findViewById(R.id.layout_container);
        byte[] a2 = a(VirtualViewUpdateUtil.f13228a + "/CountdownView.out");
        if (a2 == null) {
            Toast.makeText(this.f5143a, "读取模板数据失败", 1).show();
            return;
        }
        WidgetApplication.c().a(a2, true);
        View a3 = WidgetApplication.b().q().a("CountdownView", true);
        IContainer iContainer = (IContainer) a3;
        iContainer.getVirtualView().b((Object) "component_demo/ntext_style.json");
        CountdownVirtualView countdownVirtualView = (CountdownVirtualView) iContainer.getVirtualView().a(999).g_();
        countdownVirtualView.start(172800000);
        DynamicConfig.Builder builder = new DynamicConfig.Builder();
        builder.b((Boolean) false);
        countdownVirtualView.dynamicShow(builder.a());
        Layout.Params ae = iContainer.getVirtualView().ae();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ae.f9382a, ae.b);
        layoutParams.leftMargin = ae.d;
        layoutParams.topMargin = ae.h;
        layoutParams.rightMargin = ae.f;
        layoutParams.bottomMargin = ae.j;
        linearLayout.addView(a3, layoutParams);
    }

    private byte[] a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentCountDown homeItemContentCountDown, int i) {
        super.onClick(baseViewHolder, homeItemContentCountDown, i);
    }
}
