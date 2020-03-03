package com.xiaomi.shopviews.adapter.countdown;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.adapter.countdown.CountdownView;
import com.xiaomi.shopviews.adapter.countdown.DynamicConfig;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.Calendar;
import java.util.Date;

public class HomeCountDownViewProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public static long c = 7200000;
    private ProviderClickListener d;
    private HomeRvAdapter e;
    private String[] f = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public int a() {
        return 6;
    }

    public HomeCountDownViewProvider(ProviderClickListener providerClickListener, HomeRvAdapter homeRvAdapter) {
        this.d = providerClickListener;
        this.e = homeRvAdapter;
    }

    public int b() {
        return R.layout.count_down_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, final PageDataBean pageDataBean, int i) {
        long j;
        long j2;
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.countdown_title);
        customTextView.setText(pageDataBean.d);
        if (!TextUtils.isEmpty(pageDataBean.i)) {
            customTextView.setTextColor(Color.parseColor(pageDataBean.i));
        }
        final CustomTextView customTextView2 = (CustomTextView) view.findViewById(R.id.countdown_txt);
        if (!TextUtils.isEmpty(pageDataBean.j)) {
            customTextView2.setTextColor(Color.parseColor(pageDataBean.j));
        }
        final CountdownView countdownView = (CountdownView) view.findViewById(R.id.cv_countdownView);
        long j3 = this.e.b * 1000;
        if (TextUtils.isEmpty(pageDataBean.e) || !TextUtils.isDigitsOnly(pageDataBean.e)) {
            j2 = 0;
            j = 0;
        } else {
            j = Long.parseLong(pageDataBean.e) * 1000;
            j2 = j - j3;
        }
        if (j2 > c) {
            pageDataBean.u = PageDataBean.r;
            customTextView2.setText(a(j));
            countdownView.setVisibility(8);
            countdownView.start(j2);
        } else if (j2 <= 0 || j2 >= c) {
            pageDataBean.u = PageDataBean.t;
            customTextView2.setText(this.f5143a.getString(R.string.count_down_end));
            countdownView.setVisibility(8);
            countdownView.allShowZero();
        } else {
            pageDataBean.u = PageDataBean.s;
            customTextView2.setText(this.f5143a.getString(R.string.count_down_start));
            countdownView.setVisibility(0);
            countdownView.start(j2);
        }
        countdownView.setOnCountdownIntervalListener(1000, new CountdownView.OnCountdownIntervalListener() {
            public void a(CountdownView countdownView, long j) {
                if (j < HomeCountDownViewProvider.c && pageDataBean.u != PageDataBean.s) {
                    pageDataBean.u = PageDataBean.s;
                    customTextView2.setText(HomeCountDownViewProvider.this.f5143a.getString(R.string.count_down_start));
                    countdownView.setVisibility(0);
                }
            }
        });
        countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            public void a(CountdownView countdownView) {
                customTextView2.setText(HomeCountDownViewProvider.this.f5143a.getString(R.string.count_down_end));
                countdownView.setVisibility(8);
                countdownView.allShowZero();
            }
        });
        DynamicConfig.Builder builder = new DynamicConfig.Builder();
        DynamicConfig.BackgroundInfo backgroundInfo = new DynamicConfig.BackgroundInfo();
        if (!TextUtils.isEmpty(pageDataBean.m)) {
            backgroundInfo.a(Integer.valueOf(Color.parseColor(pageDataBean.m)));
        }
        if (!TextUtils.isEmpty(pageDataBean.l)) {
            backgroundInfo.a(Integer.valueOf(Color.parseColor(pageDataBean.l)));
        }
        builder.a(backgroundInfo);
        countdownView.dynamicShow(builder.a());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        CountDownListAdapter countDownListAdapter = new CountDownListAdapter(view.getContext(), pageDataBean, this.d);
        recyclerView.setAdapter(countDownListAdapter);
        countDownListAdapter.a(pageDataBean.v);
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }

    @SuppressLint({"StringFormatMatches"})
    private String a(long j) {
        String str;
        Object obj;
        Object obj2;
        if (j <= 0) {
            return "";
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        int i = instance.get(2);
        int i2 = instance.get(5);
        int i3 = instance.get(11);
        int i4 = instance.get(12);
        if (instance.get(9) == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(i3);
            sb.append(":");
            if (i4 < 10) {
                obj2 = "0" + i4;
            } else {
                obj2 = Integer.valueOf(i4);
            }
            sb.append(obj2);
            sb.append(" AM");
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i3);
            sb2.append(":");
            if (i4 < 10) {
                obj = "0" + i4;
            } else {
                obj = Integer.valueOf(i4);
            }
            sb2.append(obj);
            sb2.append(" PM");
            str = sb2.toString();
        }
        return String.format(this.f5143a.getString(R.string.count_down_pre), new Object[]{Integer.valueOf(i2), this.f[i], str});
    }
}
