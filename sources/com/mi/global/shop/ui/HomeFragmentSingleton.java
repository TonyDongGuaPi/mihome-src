package com.mi.global.shop.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.account.LoginManager;
import com.mi.account.activity.AccountActivity;
import com.mi.account.util.Constants;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.OrderListAcitvity;
import com.mi.global.shop.activity.ProductActivity;
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.adapter.home.HomeListAdapter;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.loader.BaseResult;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.newmodel.domain.DomainResult;
import com.mi.global.shop.newmodel.home.NewHomeBlockData;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfo;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.newmodel.home.NewHomeBlockResult;
import com.mi.global.shop.newmodel.notice.NewNoticeData;
import com.mi.global.shop.newmodel.notice.NewNoticeResult;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoData;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.DefaultDomain;
import com.mi.global.shop.util.HomeFragmentInitHelper;
import com.mi.global.shop.util.LocationUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.SplashUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.widget.BadgeView;
import com.mi.global.shop.widget.BaseListView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import com.mi.global.shop.widget.pulltorefresh.SimplePullToRefreshLayout;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.global.shop.xmsf.account.ShopSdkInitParamGroup;
import com.mi.global.shop.xmsf.account.ShopSdkTokenExpiredListener;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mi.util.UserEncryptionUtil;
import com.mi.util.permission.PermissionCallback;
import com.mi.util.permission.PermissionUtil;
import com.mi.util.permission.SamplePermissionCallback;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class HomeFragmentSingleton extends BaseFragment implements View.OnClickListener, SimplePullToRefreshLayout.OnRefreshListener, LoginManager.AccountListener {
    /* access modifiers changed from: private */
    public static final String d = "HomeFragmentSingleton";
    private static final int e = 1;
    private static final String f = "4";

    /* renamed from: a  reason: collision with root package name */
    public View f6990a;
    public BadgeView b;
    public BadgeView c;
    /* access modifiers changed from: private */
    public SimplePullToRefreshLayout g;
    /* access modifiers changed from: private */
    public EmptyLoadingViewPlus i;
    private BaseListView j;
    private HomeListAdapter k;
    private LinearLayout l;
    private ImageView m;
    private CustomTextView n;
    /* access modifiers changed from: private */
    public NewNoticeData o;
    /* access modifiers changed from: private */
    public View p;
    private ImageView q;
    private View r;
    private boolean s = true;
    private SimpleDraweeView t;
    private NewHomeBlockInfoItem u;
    private AccountActivity v;

    public void onInvalidAuthonToken() {
    }

    public static HomeFragmentSingleton a(AccountActivity accountActivity, @NonNull ShopSdkInitParamGroup shopSdkInitParamGroup) {
        Bundle bundle = new Bundle();
        HomeFragmentSingleton homeFragmentSingleton = new HomeFragmentSingleton();
        homeFragmentSingleton.setArguments(bundle);
        ShopApp.h = shopSdkInitParamGroup;
        homeFragmentSingleton.v = accountActivity;
        return homeFragmentSingleton;
    }

    public static HomeFragmentSingleton a(@NonNull ShopSdkInitParamGroup shopSdkInitParamGroup, @NonNull ShopSdkTokenExpiredListener shopSdkTokenExpiredListener) {
        ShopApp.f = shopSdkTokenExpiredListener;
        ShopApp.h = shopSdkInitParamGroup;
        if (shopSdkInitParamGroup != null) {
            if (shopSdkInitParamGroup.f.equals("mihome_sdk")) {
                ShopApp.a(false, shopSdkInitParamGroup.b, shopSdkInitParamGroup.c);
            } else {
                ShopApp.a(shopSdkInitParamGroup.e, shopSdkInitParamGroup.b, shopSdkInitParamGroup.c);
            }
            if (!TextUtils.isEmpty(shopSdkInitParamGroup.d)) {
                LocaleHelper.a(shopSdkInitParamGroup.d);
            }
        }
        Bundle bundle = new Bundle();
        HomeFragmentSingleton homeFragmentSingleton = new HomeFragmentSingleton();
        homeFragmentSingleton.setArguments(bundle);
        return homeFragmentSingleton;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.shop_home_fragment_singleton, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        l();
        HomeFragmentInitHelper.a(getContext().getApplicationContext(), new SplashUtil.OnNoticeCallback() {
            public void a(NewNoticeData newNoticeData) {
                HomeFragmentSingleton.this.a(newNoticeData);
            }
        });
        if (ShopApp.j) {
            a((Activity) getActivity());
        }
        LocationUtil.a(getActivity());
        if (!TextUtils.isEmpty(LocationUtil.b())) {
            MiShopStatInterface.b("locationEvent", d, "location", "location", LocationUtil.b());
        }
        LoginManager.u().a((LoginManager.AccountListener) this);
        a();
    }

    public void a() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aD()).buildUpon();
        AnonymousClass2 r1 = new SimpleCallback<DomainResult>() {
            public void a(DomainResult domainResult) {
                if (domainResult.domainModels.size() > 0) {
                    Utils.Preference.setStringPref(ShopApp.g(), Constants.Prefence.S, new Gson().toJson((Object) domainResult.domainModels));
                    HomeFragmentSingleton.this.k();
                    ConnectionHelper.b();
                    return;
                }
                Utils.Preference.setStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a);
            }

            public void a(String str) {
                String j = HomeFragmentSingleton.d;
                LogUtil.b(j, "getDomain Exception:" + str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), DomainResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), DomainResult.class, r1);
        }
        request.setTag(d);
        RequestQueueUtil.a().add(request);
        k();
    }

    /* access modifiers changed from: private */
    public void k() {
        ArrayList arrayList;
        String stringPref = Utils.Preference.getStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a);
        if (!TextUtils.isEmpty(stringPref)) {
            ArrayList arrayList2 = new ArrayList();
            try {
                arrayList = (ArrayList) new Gson().fromJson(stringPref, new TypeToken<ArrayList<DomainModel>>() {
                }.getType());
            } catch (Exception unused) {
                arrayList = arrayList2;
            }
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    DomainModel domainModel = (DomainModel) it.next();
                    if (LocaleHelper.b.endsWith(domainModel.local)) {
                        if (System.currentTimeMillis() >= domainModel.launchTime) {
                            if (!TextUtils.isEmpty(domainModel.sid)) {
                                Constants.Account.f6727a = domainModel.sid;
                            }
                            if (!TextUtils.isEmpty(domainModel.cookieDomain)) {
                                ConnectionHelper.E = domainModel.cookieDomain;
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        com.mi.global.shop.xmsf.account.LoginManager.u().b((LoginManager.AccountListener) this);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        b(view);
        c(view);
    }

    public void onResume() {
        super.onResume();
        if (this.s) {
            this.s = false;
        } else {
            m();
        }
        g();
        f();
        c();
        MiShopStatInterface.a(getContext(), d);
    }

    public void onPause() {
        super.onPause();
        MiShopStatInterface.b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void b(View view) {
        this.g = (SimplePullToRefreshLayout) view.findViewById(R.id.home_fragment_ptr);
        this.g.setOnRefreshListener(new SimplePullToRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                HomeFragmentSingleton.this.onRefresh();
                HomeFragmentSingleton.this.g.onRefreshComplete();
            }
        });
        this.t = (SimpleDraweeView) view.findViewById(R.id.activity_entrance);
        this.t.setOnClickListener(this);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.t.getLayoutParams();
        layoutParams.leftMargin = (Device.f1349a * 4) / 5;
        layoutParams.topMargin = (Device.b * 2) / 5;
        this.t.setLayoutParams(layoutParams);
        this.r = view.findViewById(R.id.toolbar_agent);
        this.i = (EmptyLoadingViewPlus) view.findViewById(R.id.loading);
        this.i.setPullToRefreshLayout(this.g);
        this.l = (LinearLayout) view.findViewById(R.id.noticeboard);
        this.n = (CustomTextView) view.findViewById(R.id.notice_text);
        this.m = (ImageView) view.findViewById(R.id.notice_close);
        this.m.setOnClickListener(this);
        this.q = (ImageView) view.findViewById(R.id.iv_store_title);
        this.q.setVisibility(0);
        this.j = (BaseListView) view.findViewById(16908298);
        this.k = new HomeListAdapter(getContext());
        this.j.setAdapter(this.k);
        if (this.o != null) {
            a(this.o);
        }
        this.p = view.findViewById(R.id.iv_order_list);
        this.p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragmentSingleton.this.o();
            }
        });
        this.p.setVisibility(0);
        this.f6990a = view.findViewById(R.id.title_bar_cart_view);
        this.f6990a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragmentSingleton.this.b();
            }
        });
        a(this.r);
        p();
        q();
    }

    private void c(View view) {
        view.findViewById(R.id.fab_product).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragmentSingleton.this.startActivity(new Intent(HomeFragmentSingleton.this.getContext(), ProductActivity.class));
            }
        });
    }

    public void onRefresh() {
        LogUtil.b(d, j.e);
        l();
    }

    /* access modifiers changed from: private */
    public void a(NewHomeBlockData newHomeBlockData) {
        if (newHomeBlockData != null) {
            this.k.a(newHomeBlockData);
        }
    }

    /* access modifiers changed from: private */
    public void b(NewHomeBlockData newHomeBlockData) {
        if (newHomeBlockData != null) {
            if (newHomeBlockData.mHEntrance != null && newHomeBlockData.mHEntrance.size() > 0) {
                NewHomeBlockInfo newHomeBlockInfo = newHomeBlockData.mHEntrance.get(0);
                if (newHomeBlockInfo.mItems != null && newHomeBlockInfo.mItems.size() > 0) {
                    this.u = newHomeBlockInfo.mItems.get(0);
                    if (this.u != null) {
                        if (!"4".equals(this.u.mIconType) || !Utils.Preference.getStringPref(getContext(), "pref_key_home_entrance_viewid", "").equals(this.u.mViewId)) {
                            this.t.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(this.u.getImageUrl())).setAutoPlayAnimations(true)).build());
                            if (this.t.getVisibility() != 0) {
                                this.t.setVisibility(0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
            }
            if (this.t.getVisibility() == 0) {
                this.t.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.k != null && this.k.f5543a.size() != 0) {
            MiToast.a(getContext(), (CharSequence) str, 0);
        } else if (this.i != null) {
            this.i.onError(false, BaseResult.ResultStatus.NETWROK_ERROR, (Handler.Callback) null);
        }
    }

    private void l() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aF()).buildUpon();
        buildUpon.appendQueryParameter("phone_model", Device.e);
        buildUpon.appendQueryParameter("phone_device", Device.f);
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        buildUpon.appendQueryParameter("name", "pages_index");
        AnonymousClass8 r1 = new SimpleCallback<NewHomeBlockResult>() {
            public void a(NewHomeBlockResult newHomeBlockResult) {
                if (HomeFragmentSingleton.this.i != null) {
                    HomeFragmentSingleton.this.i.setVisibility(8);
                }
                HomeFragmentSingleton.this.a(newHomeBlockResult.data);
                HomeFragmentSingleton.this.b(newHomeBlockResult.data);
                LogUtil.b(HomeFragmentSingleton.d, "准备加载首页数据成功");
            }

            public void a(String str) {
                HomeFragmentSingleton.this.a(str);
                String j = HomeFragmentSingleton.d;
                LogUtil.b(j, "准备加载首页数据失败 + " + str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewHomeBlockResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewHomeBlockResult.class, r1);
        }
        String str = d;
        LogUtil.b(str, "准备加载首页数据，url == " + buildUpon.toString());
        request.setTag(d);
        RequestQueueUtil.a().add(request);
    }

    private void m() {
        Request request;
        if (n()) {
            String ai = ConnectionHelper.ai();
            AnonymousClass9 r1 = new SimpleCallback<NewNoticeResult>() {
                public void a(NewNoticeResult newNoticeResult) {
                    NewNoticeData unused = HomeFragmentSingleton.this.o = newNoticeResult.data;
                    HomeFragmentSingleton.this.b(HomeFragmentSingleton.this.o);
                }

                public void a(String str) {
                    String j = HomeFragmentSingleton.d;
                    LogUtil.b(j, "load notice error" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(ai, NewNoticeResult.class, r1);
            } else {
                request = new SimpleJsonRequest(ai, NewNoticeResult.class, r1);
            }
            request.setTag(d);
            RequestQueueUtil.a().add(request);
        }
    }

    private boolean n() {
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
            this.o = newNoticeData;
            if (n()) {
                b(newNoticeData);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(NewNoticeData newNoticeData) {
        if (this.l != null) {
            if (newNoticeData == null || TextUtils.isEmpty(newNoticeData.content)) {
                this.l.setVisibility(8);
            } else if ("0".equalsIgnoreCase(newNoticeData.type)) {
                this.l.setVisibility(8);
            } else if ("2".equals(newNoticeData.type)) {
                this.l.setVisibility(8);
            } else {
                String str = d;
                LogUtil.b(str, "noticeShow:" + newNoticeData);
                this.n.setText(newNoticeData.content);
                this.l.setOnClickListener(this);
                this.l.setVisibility(0);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.activity_entrance) {
            if (this.u != null) {
                HomeThemeItemClick.a(getContext(), this.u);
                if ("4".equals(this.u.mIconType)) {
                    this.t.setVisibility(8);
                    Utils.Preference.setStringPref(getContext(), "pref_key_home_entrance_viewid", this.u.mViewId);
                }
            }
        } else if (id == R.id.notice_close) {
            this.l.setVisibility(8);
            if (this.o != null && !TextUtils.isEmpty(this.o.type)) {
                Utils.Preference.setStringPref(getContext(), "pref_key_home_notice_closed_type", this.o.type);
                Utils.Preference.setLongPref(getContext(), "pref_key_home_notice_closed_time", Long.valueOf(System.currentTimeMillis()));
            }
        } else if (id == R.id.noticeboard && this.o != null && !TextUtils.isEmpty(this.o.content)) {
            if ("2".equalsIgnoreCase(this.o.type) && com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                if (LocaleHelper.g()) {
                    Intent intent = new Intent(getContext(), OrderListAcitvity.class);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                    return;
                }
                String t2 = ConnectionHelper.t();
                Intent intent2 = new Intent(getContext(), WebActivity.class);
                intent2.putExtra("url", t2);
                startActivity(intent2);
            }
            if ("1".equalsIgnoreCase(this.o.type) && !TextUtils.isEmpty(this.o.url)) {
                String str = this.o.url;
                Intent intent3 = new Intent(getContext(), WebActivity.class);
                intent3.putExtra("url", str);
                startActivity(intent3);
            }
        }
    }

    public void b() {
        if (LocaleHelper.g()) {
            startActivityForResult(new Intent(getContext(), ShoppingCartActivity.class), 22);
            return;
        }
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra("url", ConnectionHelper.ay());
        startActivity(intent);
    }

    public void c() {
        Request request;
        if (com.mi.global.shop.xmsf.account.LoginManager.u().x() && !TextUtils.isEmpty(com.mi.global.shop.xmsf.account.LoginManager.u().c())) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aJ()).buildUpon();
            buildUpon.appendQueryParameter("mUserId", UserEncryptionUtil.a(com.mi.global.shop.xmsf.account.LoginManager.u().c()));
            buildUpon.appendQueryParameter("cUserId", UserEncryptionUtil.b(com.mi.global.shop.xmsf.account.LoginManager.u().c()));
            buildUpon.appendQueryParameter("security", "true");
            AnonymousClass10 r1 = new SimpleCallback<NewUserInfoResult>() {
                public void a(NewUserInfoResult newUserInfoResult) {
                    NewUserInfoData newUserInfoData;
                    if (newUserInfoResult.data != null) {
                        if (newUserInfoResult.data.jsonUserInfoData == null) {
                            newUserInfoData = newUserInfoResult.data;
                        } else {
                            newUserInfoData = newUserInfoResult.data.jsonUserInfoData;
                        }
                        HomeFragmentSingleton.this.a(newUserInfoData.not_pay_order_count);
                    }
                }

                public void a(String str) {
                    String j = HomeFragmentSingleton.d;
                    LogUtil.b(j, "RefreshUserInfo Exception:" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            } else {
                request = new SimpleJsonRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            }
            request.setTag(d);
            RequestQueueUtil.a().add(request);
        }
    }

    public void a(int i2) {
        if (BaseActivity.unpaidNum != i2) {
            BaseActivity.unpaidNum = i2;
            Utils.Preference.setIntPref(getContext(), Constants.Prefence.d, i2);
        }
        e(i2);
    }

    private void d(final int i2) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (i2 <= 0 || HomeFragmentSingleton.this.f6990a.getVisibility() != 0) {
                        HomeFragmentSingleton.this.b.hide();
                        return;
                    }
                    HomeFragmentSingleton.this.b.show();
                    HomeFragmentSingleton.this.b.setCount(i2);
                    HomeFragmentSingleton.this.b.invalidate();
                }
            });
        }
    }

    public void f() {
        LogUtil.b(d, "update cart as pref value");
        if (BaseActivity.shoppingCartNum == -1) {
            BaseActivity.shoppingCartNum = Utils.Preference.getIntPref(getContext(), "pref_key_shoppingcart_number", 0);
        }
        d(BaseActivity.shoppingCartNum);
    }

    public void g() {
        LogUtil.b(d, "update cart as pref value");
        if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
            if (BaseActivity.unpaidNum == -1) {
                BaseActivity.unpaidNum = Utils.Preference.getIntPref(getContext(), Constants.Prefence.d, 0);
            }
            e(BaseActivity.unpaidNum);
        }
    }

    private void e(final int i2) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (i2 <= 0 || HomeFragmentSingleton.this.p.getVisibility() != 0) {
                        HomeFragmentSingleton.this.c.hide();
                        return;
                    }
                    HomeFragmentSingleton.this.c.show();
                    HomeFragmentSingleton.this.c.setCount(i2);
                    HomeFragmentSingleton.this.c.invalidate();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        if (ShopApp.h.f.equals("community_sdk")) {
            if (LocaleHelper.g()) {
                if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                    Intent intent = new Intent(getContext(), OrderListAcitvity.class);
                    intent.putExtra("type", 0);
                    startActivityForResult(intent, 1);
                    return;
                } else if (getActivity() != null) {
                    ((AccountActivity) getActivity()).gotoAccount();
                }
            }
            if (!LocaleHelper.n() && !LocaleHelper.i() && !LocaleHelper.h() && !LocaleHelper.l() && !LocaleHelper.k() && !LocaleHelper.j() && !LocaleHelper.m() && !LocaleHelper.o()) {
                return;
            }
            if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                String u2 = ConnectionHelper.u();
                Intent intent2 = new Intent(getContext(), WebActivity.class);
                intent2.putExtra("url", u2);
                startActivity(intent2);
            } else if (getActivity() != null) {
                ((AccountActivity) getActivity()).gotoAccount();
            }
        } else {
            if (LocaleHelper.g()) {
                if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                    Intent intent3 = new Intent(getContext(), OrderListAcitvity.class);
                    intent3.putExtra("type", 0);
                    startActivityForResult(intent3, 1);
                    return;
                }
                ShopApp.e();
            }
            if (!LocaleHelper.n() && !LocaleHelper.i() && !LocaleHelper.h() && !LocaleHelper.l() && !LocaleHelper.k() && !LocaleHelper.j() && !LocaleHelper.m() && !LocaleHelper.o()) {
                return;
            }
            if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                String u3 = ConnectionHelper.u();
                Intent intent4 = new Intent(getContext(), WebActivity.class);
                intent4.putExtra("url", u3);
                startActivity(intent4);
                return;
            }
            ShopApp.e();
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1 && i3 == 0) {
            if (!com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                if (ShopApp.h.f.equals("community_sdk")) {
                    this.v.gotoAccount();
                } else {
                    ShopApp.e();
                }
            } else {
                return;
            }
        }
        ShopApp.m().onActivityResult(i2, i3, intent);
    }

    private void p() {
        this.b = new BadgeView(getContext(), this.f6990a);
        this.b.setTextColor(getResources().getColor(17170443));
        this.b.setTextSize(2, 10.0f);
        this.b.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.shop_orangle_inner_solid_circle));
        this.b.setmBadgePosition(2);
        this.b.setBadgeMargin(0, Coder.a((Activity) getActivity(), 5.0f));
    }

    private void q() {
        this.c = new BadgeView(getContext(), this.p);
        this.c.setTextColor(getResources().getColor(17170443));
        this.c.setTextSize(2, 10.0f);
        this.c.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.shop_orangle_inner_solid_circle));
        this.c.setmBadgePosition(2);
        this.c.setBadgeMargin(0, Coder.a((Activity) getActivity(), 5.0f));
    }

    public void c(int i2) {
        String str = d;
        LogUtil.b(str, "update cart:" + i2);
        if (BaseActivity.shoppingCartNum != i2) {
            BaseActivity.shoppingCartNum = i2;
            Utils.Preference.setIntPref(getContext(), "pref_key_shoppingcart_number", i2);
        }
        d(i2);
    }

    private static void a(Activity activity) {
        final Context applicationContext = activity.getApplication().getApplicationContext();
        PermissionUtil.a(activity, (PermissionCallback) new SamplePermissionCallback() {
            public void onDenied() {
            }

            public void onResult() {
                HomeFragmentSingleton.b(applicationContext);
            }
        }, "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: private */
    public static void b(Context context) {
        boolean a2 = PermissionUtil.a(context, "android.permission.READ_PHONE_STATE");
        if (a2) {
            Device.a(ShopApp.g(), a2);
        }
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i2, String str4) {
        c(i2);
    }

    public void onLogin(String str, String str2, String str3) {
        c();
    }

    public void onLogout() {
        c(0);
        a(0);
        WebViewCookieManager.b(getContext());
        WebViewCookieManager.c(getContext());
    }

    public void h() {
        ApplicationContextHolder.l();
    }

    public static String i() {
        return d;
    }
}
