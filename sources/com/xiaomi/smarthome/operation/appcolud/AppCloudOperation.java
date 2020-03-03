package com.xiaomi.smarthome.operation.appcolud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mibigkoo.convenientbanner.ConvenientBanner;
import com.mibigkoo.convenientbanner.holder.Holder;
import com.mibigkoo.convenientbanner.listener.OnItemClickListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.operation.OperationRoute;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppCloudOperation extends BaseOperationProviderV2 {
    private static final String e = "action_on_my_operation_visible_to_user";
    private WeakReference<View> b;
    private BroadcastReceiver c;
    private List<String> d = new ArrayList();

    /* access modifiers changed from: protected */
    public String d() {
        return "21";
    }

    public void a(View view) {
        this.b = new WeakReference<>(view);
        m();
        a(0, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: protected */
    public void a(List<Banner> list) {
        View view = (View) this.b.get();
        ConvenientBanner convenientBanner = view != null ? (ConvenientBanner) view.findViewById(R.id.op_banner) : null;
        if (convenientBanner != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                TransitionManager.beginDelayedTransition((ViewGroup) view);
            }
            convenientBanner.setVisibility(0);
            this.d.clear();
            for (Banner banner : list) {
                this.d.add(banner.c);
            }
            if (convenientBanner.getViewPager() == null || convenientBanner.getViewPager().getAdapter() == null) {
                convenientBanner.setPages($$Lambda$AppCloudOperation$5cKkr8rmY7NVj412gsHWK6FVGk.INSTANCE, list).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setPageIndicator(new int[]{R.drawable.dot_indicator_unselected, R.drawable.dot_indicator_selected}).setOnItemClickListener(new OnItemClickListener(list) {
                    private final /* synthetic */ List f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void onItemClick(int i) {
                        AppCloudOperation.a(this.f$0, i);
                    }
                }).startTurning(2500);
            } else {
                convenientBanner.notifyDataSetChanged();
            }
            if (list.size() == 1) {
                convenientBanner.setCanLoop(false);
                convenientBanner.setPageIndicator(new int[]{R.drawable.dot_indicator_transparent, R.drawable.dot_indicator_transparent});
            } else {
                convenientBanner.setCanLoop(true);
                convenientBanner.setPageIndicator(new int[]{R.drawable.dot_indicator_unselected, R.drawable.dot_indicator_selected});
            }
            a();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object o() {
        return new BitmapHolder();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(List list, int i) {
        OperationRoute.a((Banner) list.get(i));
        STAT.d.ai(((Banner) list.get(i)).c);
    }

    static class BitmapHolder implements Holder<Banner> {

        /* renamed from: a  reason: collision with root package name */
        private ImageView f21045a;

        BitmapHolder() {
        }

        public View a(Context context, ViewGroup viewGroup) {
            this.f21045a = new ImageView(context);
            this.f21045a.setScaleType(ImageView.ScaleType.FIT_XY);
            return this.f21045a;
        }

        public void a(Context context, int i, Banner banner) {
            this.f21045a.setImageBitmap(banner.f);
        }
    }

    public static void a() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(e));
    }

    private void m() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    AppCloudOperation.this.n();
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.c, new IntentFilter(e));
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        ConvenientBanner convenientBanner;
        View view = (View) this.b.get();
        if (view != null && (convenientBanner = (ConvenientBanner) view.findViewById(R.id.op_banner)) != null && convenientBanner.getVisibility() == 0) {
            for (String j : this.d) {
                STAT.e.j(j);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return l() && super.b();
    }

    /* access modifiers changed from: protected */
    public void c() {
        View findViewById;
        e();
        View view = (View) this.b.get();
        if (view != null && (findViewById = view.findViewById(R.id.op_banner)) != null) {
            findViewById.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void e() {
        super.e();
        if (this.c != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.c);
            this.c = null;
        }
        this.d.clear();
    }
}
