package com.mi.global.shop.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.alipay.sdk.widget.j;
import com.android.volley.Request;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.OrderListAcitvity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.adapter.home.HomeListAdapter;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.loader.BaseResult;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.home.NewHomeBlockData;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfo;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.newmodel.home.NewHomeBlockResult;
import com.mi.global.shop.newmodel.notice.NewNoticeData;
import com.mi.global.shop.newmodel.notice.NewNoticeResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.widget.BaseListView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import com.mi.global.shop.widget.pulltorefresh.SimplePullToRefreshLayout;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.Device;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HomeFragment extends BaseFragment implements View.OnClickListener, SimplePullToRefreshLayout.OnRefreshListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f6962a = "HomeFragment";
    private static final String o = "4";
    /* access modifiers changed from: private */
    public SimplePullToRefreshLayout b;
    /* access modifiers changed from: private */
    public EmptyLoadingViewPlus c;
    private BaseListView d;
    private View e;
    private HomeListAdapter f;
    private LinearLayout g;
    private ImageView i;
    private CustomTextView j;
    /* access modifiers changed from: private */
    public NewNoticeData k;
    private boolean l = true;
    private SimpleDraweeView m;
    private NewHomeBlockInfoItem n;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.shop_home_fragment, viewGroup, false);
        b(inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
    }

    public void onResume() {
        super.onResume();
        if (this.l) {
            this.l = false;
        } else {
            c();
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void b(View view) {
        this.e = getActivity().findViewById(R.id.title_mi_logo);
        this.e.setVisibility(0);
        this.b = (SimplePullToRefreshLayout) view.findViewById(R.id.home_fragment_ptr);
        this.b.setOnRefreshListener(new SimplePullToRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                HomeFragment.this.onRefresh();
                HomeFragment.this.b.onRefreshComplete();
            }
        });
        this.m = (SimpleDraweeView) view.findViewById(R.id.activity_entrance);
        this.m.setOnClickListener(this);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.m.getLayoutParams();
        layoutParams.leftMargin = (Device.f1349a * 4) / 5;
        layoutParams.topMargin = (Device.b * 2) / 5;
        this.m.setLayoutParams(layoutParams);
        this.c = (EmptyLoadingViewPlus) view.findViewById(R.id.loading);
        this.c.setPullToRefreshLayout(this.b);
        this.g = (LinearLayout) view.findViewById(R.id.noticeboard);
        this.j = (CustomTextView) view.findViewById(R.id.notice_text);
        this.i = (ImageView) view.findViewById(R.id.notice_close);
        this.i.setOnClickListener(this);
        this.d = (BaseListView) view.findViewById(16908298);
        this.f = new HomeListAdapter(getActivity());
        this.d.setAdapter(this.f);
        if (this.k != null) {
            a(this.k);
        }
    }

    public void onRefresh() {
        LogUtil.b(f6962a, j.e);
        b();
    }

    /* access modifiers changed from: private */
    public void a(NewHomeBlockData newHomeBlockData) {
        if (newHomeBlockData != null) {
            this.f.a(newHomeBlockData);
        }
    }

    /* access modifiers changed from: private */
    public void b(NewHomeBlockData newHomeBlockData) {
        if (newHomeBlockData != null) {
            if (newHomeBlockData.mHEntrance != null && newHomeBlockData.mHEntrance.size() > 0) {
                NewHomeBlockInfo newHomeBlockInfo = newHomeBlockData.mHEntrance.get(0);
                if (newHomeBlockInfo.mItems != null && newHomeBlockInfo.mItems.size() > 0) {
                    this.n = newHomeBlockInfo.mItems.get(0);
                    if (this.n != null) {
                        if (!"4".equals(this.n.mIconType) || !Utils.Preference.getStringPref(getContext(), "pref_key_home_entrance_viewid", "").equals(this.n.mViewId)) {
                            this.m.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(this.n.getImageUrl())).setAutoPlayAnimations(true)).build());
                            if (this.m.getVisibility() != 0) {
                                this.m.setVisibility(0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
            }
            if (this.m.getVisibility() == 0) {
                this.m.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.f != null && this.f.f5543a.size() != 0) {
            MiToast.a(getContext(), (CharSequence) str, 0);
        } else if (this.c != null) {
            this.c.onError(false, BaseResult.ResultStatus.NETWROK_ERROR, (Handler.Callback) null);
        }
    }

    private void b() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aF()).buildUpon();
        buildUpon.appendQueryParameter("phone_model", Device.e);
        buildUpon.appendQueryParameter("phone_device", Device.f);
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        buildUpon.appendQueryParameter("name", "pages_index");
        AnonymousClass2 r1 = new SimpleCallback<NewHomeBlockResult>() {
            public void a(NewHomeBlockResult newHomeBlockResult) {
                if (HomeFragment.this.c != null) {
                    HomeFragment.this.c.setVisibility(8);
                }
                HomeFragment.this.a(newHomeBlockResult.data);
                HomeFragment.this.b(newHomeBlockResult.data);
            }

            public void a(String str) {
                HomeFragment.this.a(str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewHomeBlockResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewHomeBlockResult.class, r1);
        }
        request.setTag(f6962a);
        RequestQueueUtil.a().add(request);
    }

    private void c() {
        Request request;
        if (f()) {
            String ai = ConnectionHelper.ai();
            AnonymousClass3 r1 = new SimpleCallback<NewNoticeResult>() {
                public void a(NewNoticeResult newNoticeResult) {
                    NewNoticeData unused = HomeFragment.this.k = newNoticeResult.data;
                    HomeFragment.this.b(HomeFragment.this.k);
                }

                public void a(String str) {
                    String a2 = HomeFragment.f6962a;
                    LogUtil.b(a2, "load notice error" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(ai, NewNoticeResult.class, r1);
            } else {
                request = new SimpleJsonRequest(ai, NewNoticeResult.class, r1);
            }
            request.setTag(f6962a);
            RequestQueueUtil.a().add(request);
        }
    }

    private boolean f() {
        boolean z;
        String stringPref = Utils.Preference.getStringPref(getContext(), "pref_key_home_notice_closed_type", "");
        long longPref = Utils.Preference.getLongPref(getContext(), "pref_key_home_notice_closed_time", 0);
        if (longPref != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            z = simpleDateFormat.format(Long.valueOf(currentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(longPref)));
        } else {
            z = false;
        }
        if (!z || TextUtils.isEmpty(stringPref) || !stringPref.equals("1")) {
            return true;
        }
        return false;
    }

    public void a(NewNoticeData newNoticeData) {
        if (newNoticeData != null && !TextUtils.isEmpty(newNoticeData.content)) {
            this.k = newNoticeData;
            if (f()) {
                b(newNoticeData);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(NewNoticeData newNoticeData) {
        if (this.g != null) {
            if (newNoticeData == null || TextUtils.isEmpty(newNoticeData.content)) {
                this.g.setVisibility(8);
            } else if ("0".equalsIgnoreCase(newNoticeData.type)) {
                this.g.setVisibility(8);
            } else if ("2".equals(newNoticeData.type)) {
                this.g.setVisibility(8);
            } else {
                String str = f6962a;
                LogUtil.b(str, "noticeShow:" + newNoticeData);
                this.j.setText(newNoticeData.content);
                this.g.setOnClickListener(this);
                this.g.setVisibility(0);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.activity_entrance) {
            if (this.n != null) {
                HomeThemeItemClick.a(getContext(), this.n);
                if ("4".equals(this.n.mIconType)) {
                    this.m.setVisibility(8);
                    Utils.Preference.setStringPref(getContext(), "pref_key_home_entrance_viewid", this.n.mViewId);
                }
            }
        } else if (id == R.id.notice_close) {
            this.g.setVisibility(8);
            if (this.k != null && !TextUtils.isEmpty(this.k.type)) {
                Utils.Preference.setStringPref(getContext(), "pref_key_home_notice_closed_type", this.k.type);
                Utils.Preference.setLongPref(getContext(), "pref_key_home_notice_closed_time", Long.valueOf(System.currentTimeMillis()));
            }
        } else if (id == R.id.noticeboard && this.k != null && !TextUtils.isEmpty(this.k.content)) {
            if ("2".equalsIgnoreCase(this.k.type) && LoginManager.u().x()) {
                if (LocaleHelper.g()) {
                    Intent intent = new Intent(getActivity(), OrderListAcitvity.class);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                    return;
                }
                String t = ConnectionHelper.t();
                Intent intent2 = new Intent(getActivity(), WebActivity.class);
                intent2.putExtra("url", t);
                getActivity().startActivity(intent2);
            }
            if ("1".equalsIgnoreCase(this.k.type) && !TextUtils.isEmpty(this.k.url)) {
                String str = this.k.url;
                Intent intent3 = new Intent(getActivity(), WebActivity.class);
                intent3.putExtra("url", str);
                getActivity().startActivity(intent3);
            }
        }
    }
}
