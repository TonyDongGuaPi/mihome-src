package com.xiaomi.shopviews.adapter.countdown;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.adapter.countdown.CountdownView;
import com.xiaomi.shopviews.adapter.countdown.DynamicConfig;
import com.xiaomi.shopviews.model.item.HomeItemContentFactory;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.Calendar;
import java.util.Date;

public class HomeCountDownViewCfgProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public static long c = 7200000;
    /* access modifiers changed from: private */
    public static long d = 600000;
    /* access modifiers changed from: private */
    public ProviderClickListener e;
    /* access modifiers changed from: private */
    public HomeRvAdapter f;
    private String[] g = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public int a() {
        return 30;
    }

    public HomeCountDownViewCfgProvider(ProviderClickListener providerClickListener, HomeRvAdapter homeRvAdapter) {
        this.e = providerClickListener;
        this.f = homeRvAdapter;
    }

    public int b() {
        return R.layout.count_down_list_item_cfg;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        long j;
        long j2;
        final PageDataBean pageDataBean2 = pageDataBean;
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.countdown_title);
        customTextView.setText(pageDataBean2.d);
        if (!TextUtils.isEmpty(pageDataBean2.i)) {
            customTextView.setTextColor(Color.parseColor(pageDataBean2.i));
        }
        final CustomTextView customTextView2 = (CustomTextView) view.findViewById(R.id.countdown_txt);
        if (!TextUtils.isEmpty(pageDataBean2.j)) {
            customTextView2.setTextColor(Color.parseColor(pageDataBean2.j));
        }
        final ImageButton imageButton = (ImageButton) view.findViewById(R.id.remind_btn);
        a(imageButton, pageDataBean2.z);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.remind_group);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeCountDownViewCfgProvider.this.e != null) {
                    HomeCountDownViewCfgProvider.this.e.a(pageDataBean2, HomeItemContentFactory.p, (Object) imageButton);
                }
            }
        });
        CountdownView countdownView = (CountdownView) view.findViewById(R.id.cv_countdownView);
        long j3 = this.f.b * 1000;
        if (TextUtils.isEmpty(pageDataBean2.e) || !TextUtils.isDigitsOnly(pageDataBean2.e)) {
            j2 = 0;
            j = 0;
        } else {
            long parseLong = Long.parseLong(pageDataBean2.e) * 1000;
            j = parseLong;
            j2 = parseLong - j3;
        }
        long j4 = pageDataBean2.y > 0 ? pageDataBean2.y * 1000 : 0;
        if (j2 > c) {
            pageDataBean2.u = PageDataBean.r;
            customTextView2.setText(a(j));
            countdownView.setVisibility(8);
            countdownView.start(j2);
        } else if (j2 > 0 && j2 < c) {
            pageDataBean2.u = PageDataBean.s;
            customTextView2.setText(this.f5143a.getString(R.string.count_down_start));
            countdownView.setVisibility(0);
            countdownView.start(j2);
        } else if (j3 < j4) {
            pageDataBean2.u = PageDataBean.t;
            customTextView2.setText(this.f5143a.getString(R.string.count_down_end_in));
            countdownView.setVisibility(0);
            countdownView.start(j4 - j3);
        } else {
            pageDataBean2.u = PageDataBean.t;
            customTextView2.setText(this.f5143a.getString(R.string.count_down_end_in));
            countdownView.setVisibility(0);
            countdownView.allShowZero();
        }
        countdownView.setOnCountdownIntervalListener(1000, new CountdownView.OnCountdownIntervalListener() {
            public void a(CountdownView countdownView, long j) {
                if (j < HomeCountDownViewCfgProvider.c && pageDataBean2.u == PageDataBean.r) {
                    pageDataBean2.u = PageDataBean.s;
                    customTextView2.setText(HomeCountDownViewCfgProvider.this.f5143a.getString(R.string.count_down_start));
                    countdownView.setVisibility(0);
                }
                if (j < HomeCountDownViewCfgProvider.d) {
                    linearLayout.setVisibility(8);
                }
            }
        });
        final PageDataBean pageDataBean3 = pageDataBean;
        final long j5 = j4;
        final CountdownView countdownView2 = countdownView;
        countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            public void a(CountdownView countdownView) {
                customTextView2.setText(HomeCountDownViewCfgProvider.this.f5143a.getString(R.string.count_down_end_in));
                pageDataBean3.u = PageDataBean.t;
                long j = HomeCountDownViewCfgProvider.this.f.b * 1000;
                if (j < j5) {
                    countdownView2.start(j5 - j);
                } else {
                    countdownView.allShowZero();
                }
            }
        });
        DynamicConfig.Builder builder = new DynamicConfig.Builder();
        DynamicConfig.BackgroundInfo backgroundInfo = new DynamicConfig.BackgroundInfo();
        if (!TextUtils.isEmpty(pageDataBean2.m)) {
            backgroundInfo.a(Integer.valueOf(Color.parseColor(pageDataBean2.m)));
        }
        if (!TextUtils.isEmpty(pageDataBean2.l)) {
            backgroundInfo.a(Integer.valueOf(Color.parseColor(pageDataBean2.l)));
        }
        builder.a(backgroundInfo);
        countdownView.dynamicShow(builder.a());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        CountDownListCfgAdapter countDownListCfgAdapter = new CountDownListCfgAdapter(view.getContext(), pageDataBean2, this.e);
        recyclerView.setAdapter(countDownListCfgAdapter);
        countDownListCfgAdapter.a(pageDataBean2.v);
    }

    private void a(ImageButton imageButton, boolean z) {
        if (z) {
            imageButton.setBackground(imageButton.getContext().getResources().getDrawable(R.drawable.remind));
        } else {
            imageButton.setBackground(imageButton.getContext().getResources().getDrawable(R.drawable.unremind));
        }
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
        return String.format(this.f5143a.getString(R.string.count_down_pre), new Object[]{Integer.valueOf(i2), this.g[i], str});
    }
}
