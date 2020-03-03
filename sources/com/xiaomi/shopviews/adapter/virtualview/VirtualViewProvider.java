package com.xiaomi.shopviews.adapter.virtualview;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.VirtualViewUtils.VirtualViewUpdateUtil;
import com.xiaomi.shopviews.widget.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class VirtualViewProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    public int a() {
        return 100;
    }

    public int b() {
        return R.layout.virtual_view;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        LinearLayout linearLayout = (LinearLayout) baseViewHolder.itemView.findViewById(R.id.layout_container);
        String str = pageDataBean.o;
        String str2 = pageDataBean.p;
        byte[] a2 = a(VirtualViewUpdateUtil.f13228a + "/" + str + ".out");
        if (a2 != null) {
            WidgetApplication.c().a(a2, true);
            if (!TextUtils.isEmpty(str2)) {
                byte[] a3 = a(VirtualViewUpdateUtil.f13228a + "/" + str2 + ".out");
                if (a3 != null) {
                    WidgetApplication.c().a(a3, true);
                }
            }
            View a4 = WidgetApplication.b().q().a(str, true);
            IContainer iContainer = (IContainer) a4;
            try {
                iContainer.getVirtualView().b((Object) new JSONObject(new Gson().toJson((Object) pageDataBean)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Layout.Params ae = iContainer.getVirtualView().ae();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ae.f9382a, ae.b);
            layoutParams.leftMargin = ae.d;
            layoutParams.topMargin = ae.h;
            layoutParams.rightMargin = ae.f;
            layoutParams.bottomMargin = ae.j;
            linearLayout.removeAllViews();
            linearLayout.addView(a4, layoutParams);
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) linearLayout.getLayoutParams();
            layoutParams2.bottomMargin = Coder.a(linearLayout.getContext(), 35.0f);
            linearLayout.setLayoutParams(layoutParams2);
        }
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

    private JSONObject b(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f5143a.getAssets().open(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return new JSONObject(sb.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
