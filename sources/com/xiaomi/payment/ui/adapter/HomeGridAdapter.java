package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.component.UnevenGrid;
import com.mibi.common.data.ArrayAdapter;
import com.xiaomi.payment.data.UnevenGridData;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.component.BaseHomeGridCommonView;
import com.xiaomi.payment.ui.component.HomeBannerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HomeGridAdapter extends ArrayAdapter<HomeGridItemData> {
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static HashMap<Integer, Integer> h = new HashMap<>();
    private Context g;
    private GridItemClickListener i;

    public interface GridItemClickListener {
        void a(UnevenGridData.SingleGridItemInfo singleGridItemInfo);
    }

    static {
        h.put(0, Integer.valueOf(R.layout.mibi_home_grid_common_item_one_one));
        h.put(1, Integer.valueOf(R.layout.mibi_home_grid_common_item_two_one));
        h.put(2, Integer.valueOf(R.layout.mibi_home_grid_common_item_two_one));
        h.put(3, Integer.valueOf(R.layout.mibi_home_grid_banner_item));
    }

    public HomeGridAdapter(Context context) {
        super(context);
        this.g = context;
    }

    public View a(Context context, int i2, HomeGridItemData homeGridItemData, ViewGroup viewGroup) {
        return LayoutInflater.from(this.g).inflate(h.get(Integer.valueOf(a(homeGridItemData.c))).intValue(), viewGroup, false);
    }

    public void a(View view, int i2, HomeGridItemData homeGridItemData) {
        UnevenGridData.SingleGridItemInfo singleGridItemInfo = homeGridItemData.c;
        int a2 = a(singleGridItemInfo);
        if (a2 == 0 || a2 == 1 || a2 == 2) {
            BaseHomeGridCommonView baseHomeGridCommonView = (BaseHomeGridCommonView) view;
            baseHomeGridCommonView.bindItemInfo(singleGridItemInfo);
            baseHomeGridCommonView.setOnGridItemClickListener(this.i);
        } else if (a2 == 3) {
            HomeBannerView homeBannerView = (HomeBannerView) view;
            homeBannerView.setOnBannerViewClickListener(this.i);
            homeBannerView.bindBannerInfo(((UnevenGridData.BannerGridItemInfo) singleGridItemInfo).f12233a);
        }
    }

    public int getViewTypeCount() {
        return h.size();
    }

    public int getItemViewType(int i2) {
        return a(((HomeGridItemData) getItem(i2)).c);
    }

    private int a(UnevenGridData.SingleGridItemInfo singleGridItemInfo) {
        if (singleGridItemInfo.g == 1) {
            return 3;
        }
        int i2 = singleGridItemInfo.h;
        int i3 = singleGridItemInfo.i;
        if (i2 == 1 && i3 == 1) {
            return 0;
        }
        if (i2 == 2 && i3 == 1) {
            return 1;
        }
        return 2;
    }

    public static ArrayList<HomeGridItemData> b(ArrayList<? extends UnevenGridData.SingleGridItemInfo> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        ArrayList<HomeGridItemData> arrayList2 = new ArrayList<>();
        Iterator<? extends UnevenGridData.SingleGridItemInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            UnevenGridData.SingleGridItemInfo singleGridItemInfo = (UnevenGridData.SingleGridItemInfo) it.next();
            if (singleGridItemInfo != null) {
                HomeGridItemData homeGridItemData = new HomeGridItemData();
                homeGridItemData.a(singleGridItemInfo);
                arrayList2.add(homeGridItemData);
            }
        }
        return arrayList2;
    }

    public void a(GridItemClickListener gridItemClickListener) {
        this.i = gridItemClickListener;
    }

    public static class HomeGridItemData extends UnevenGrid.GridItemData {
        public UnevenGridData.SingleGridItemInfo c;

        public void a(UnevenGridData.SingleGridItemInfo singleGridItemInfo) {
            this.c = singleGridItemInfo;
            this.f7494a = singleGridItemInfo.h;
            this.b = singleGridItemInfo.i;
        }
    }
}
