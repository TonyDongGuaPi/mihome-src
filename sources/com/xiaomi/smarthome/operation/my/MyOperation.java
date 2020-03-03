package com.xiaomi.smarthome.operation.my;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mibigkoo.convenientbanner.ConvenientBanner;
import com.mibigkoo.convenientbanner.holder.Holder;
import com.mibigkoo.convenientbanner.listener.OnItemClickListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.operation.OperationRoute;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyOperation extends MyOperationProvider {
    private static final String h = "action_on_my_operation_visible_to_user";
    private WeakReference<View> b;
    private BroadcastReceiver c;
    private List<BannerItem> d;
    private List<TabItem> e;
    private boolean f = false;
    private List<Item> g;

    public void a(View view) {
        this.b = new WeakReference<>(view);
        k();
        a(0, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: protected */
    public void a(List<Item> list) {
        if (this.g == null || !this.g.equals(list)) {
            this.g = list;
            View view = (View) this.b.get();
            if (view != null) {
                View findViewById = view.findViewById(R.id.my_banner);
                MyOperationPopHelper myOperationPopHelper = (MyOperationPopHelper) view.findViewById(R.id.helper);
                if (this.e == null) {
                    this.e = new ArrayList();
                } else {
                    this.e.clear();
                }
                if (this.d == null) {
                    this.d = new ArrayList();
                } else {
                    this.d.clear();
                }
                for (Item next : list) {
                    if (next instanceof TabItem) {
                        this.e.add((TabItem) next);
                    } else {
                        this.d.add((BannerItem) next);
                    }
                }
                if (this.e.isEmpty()) {
                    findViewById.findViewById(R.id.tab_container).setVisibility(8);
                    myOperationPopHelper.setClipBannerOnly(true);
                } else {
                    findViewById.findViewById(R.id.tab_container).setVisibility(0);
                    myOperationPopHelper.setClipBannerOnly(false);
                }
                boolean z = list.get(0).k;
                if (!this.f && !z && Build.VERSION.SDK_INT >= 19) {
                    this.f = true;
                    TransitionManager.beginDelayedTransition((ViewGroup) view);
                }
                ConvenientBanner convenientBanner = (ConvenientBanner) findViewById.findViewById(R.id.op_banner);
                int[] iArr = {R.id.tab_1, R.id.tab_2, R.id.tab_3, R.id.tab_4};
                for (int i = 0; i < 4; i++) {
                    ((ViewGroup) findViewById.findViewById(iArr[i])).setVisibility(8);
                }
                for (int i2 = 0; i2 < Math.min(this.e.size(), 4); i2++) {
                    ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(iArr[i2]);
                    viewGroup.setVisibility(0);
                    final TabItem tabItem = this.e.get(i2);
                    ((ImageView) viewGroup.getChildAt(0)).setImageBitmap(tabItem.i);
                    ((TextView) viewGroup.getChildAt(1)).setText(tabItem.b);
                    viewGroup.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            OperationRoute.a(tabItem);
                            STAT.d.r(tabItem.f21148a);
                        }
                    });
                }
                if (this.d.isEmpty()) {
                    convenientBanner.setVisibility(8);
                    myOperationPopHelper.setClipBounds(false);
                    if (!(convenientBanner.getViewPager() == null || convenientBanner.getViewPager().getAdapter() == null)) {
                        convenientBanner.notifyDataSetChanged();
                    }
                } else {
                    convenientBanner.setVisibility(0);
                    myOperationPopHelper.setClipBounds(true);
                    if (convenientBanner.getViewPager() == null || convenientBanner.getViewPager().getAdapter() == null) {
                        convenientBanner.setPages($$Lambda$MyOperation$KhtYSESEg39B6uYB83CbIh0ccvQ.INSTANCE, this.d).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setPageIndicator(new int[]{R.drawable.dot_indicator_unselected, R.drawable.dot_indicator_selected}).setOnItemClickListener(new OnItemClickListener() {
                            public final void onItemClick(int i) {
                                MyOperation.this.a(i);
                            }
                        }).startTurning(3000);
                    } else {
                        convenientBanner.notifyDataSetChanged();
                    }
                    if (this.d.size() == 1) {
                        convenientBanner.setCanLoop(false);
                        convenientBanner.setPageIndicator(new int[]{R.drawable.dot_indicator_transparent, R.drawable.dot_indicator_transparent});
                    } else {
                        convenientBanner.setCanLoop(true);
                        convenientBanner.setPageIndicator(new int[]{R.drawable.dot_indicator_unselected, R.drawable.dot_indicator_selected});
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (BannerItem bannerItem : this.d) {
                    arrayList.add(bannerItem.f21134a);
                }
                for (TabItem tabItem2 : this.e) {
                    arrayList.add(tabItem2.f21148a);
                }
                myOperationPopHelper.attachLogInfos(arrayList);
                myOperationPopHelper.logAdPopUp();
                findViewById.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object n() {
        return new BitmapHolder();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(int i) {
        OperationRoute.a(this.d.get(i));
        STAT.d.q(this.d.get(i).f21134a);
    }

    static class BitmapHolder implements Holder<BannerItem> {

        /* renamed from: a  reason: collision with root package name */
        private ImageView f21137a;

        BitmapHolder() {
        }

        public View a(Context context, ViewGroup viewGroup) {
            this.f21137a = new SimpleDraweeView(context);
            this.f21137a.setScaleType(ImageView.ScaleType.FIT_XY);
            return this.f21137a;
        }

        public void a(Context context, int i, BannerItem bannerItem) {
            this.f21137a.setImageBitmap(bannerItem.i);
        }
    }

    public static void a() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(h));
    }

    private void k() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(MyOperation.h)) {
                        MyOperation.this.l();
                    } else if (intent.getAction().equals(LanguageUtil.f1553a)) {
                        MyOperation.this.m();
                        MyOperation.this.a("{}");
                        MyOperation.this.i();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter(h);
            intentFilter.addAction(LanguageUtil.f1553a);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.c, intentFilter);
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        MyOperationPopHelper myOperationPopHelper;
        View view = (View) this.b.get();
        if (view != null && (myOperationPopHelper = (MyOperationPopHelper) view.findViewById(R.id.helper)) != null && myOperationPopHelper.getVisibility() == 0) {
            myOperationPopHelper.logAdPopUp();
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        View findViewById;
        View view = (View) this.b.get();
        if (!(view == null || (findViewById = view.findViewById(R.id.my_banner)) == null || findViewById.getVisibility() != 0)) {
            findViewById.setVisibility(8);
        }
        this.g = null;
        this.f = false;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return super.b();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String c() {
        List<GridViewData> F;
        if (j() && (F = HomeManager.a().F()) != null) {
            return String.valueOf(F.size());
        }
        return "0";
    }

    /* access modifiers changed from: protected */
    public void d() {
        m();
    }

    /* access modifiers changed from: protected */
    public void e() {
        f();
        m();
    }

    /* access modifiers changed from: protected */
    public synchronized void f() {
        super.f();
        if (this.c != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.c);
            this.c = null;
        }
    }
}
