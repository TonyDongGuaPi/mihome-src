package com.mi.global.shop.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.Request;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.loader.BaseResult;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.discover.NewDiscoverPageViewsDate;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.newmodel.domain.DomainResult;
import com.mi.global.shop.newmodel.home.HomeFlashSaleFollowResult;
import com.mi.global.shop.newmodel.home.HomePageConfigResult;
import com.mi.global.shop.newmodel.notice.NewNoticeData;
import com.mi.global.shop.newmodel.notice.NewNoticeResult;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoData;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoResult;
import com.mi.global.shop.newmodel.virtualview.VirtualViewModel;
import com.mi.global.shop.newmodel.virtualview.VirtualViewResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.router.RouterConfig;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.DefaultDomain;
import com.mi.global.shop.util.HomeFragmentInitHelper;
import com.mi.global.shop.util.LocationUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.SaveObjectUtils;
import com.mi.global.shop.util.SplashUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.util.run.MainThreadHandler;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.widget.BadgeView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.MoveImageView;
import com.mi.global.shop.widget.pulltorefresh.SimplePullToRefreshLayout;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.global.shop.xmsf.account.ShopSdkInitParamGroup;
import com.mi.global.shop.xmsf.account.ShopSdkTokenExpiredListener;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.StaticConstants;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ScreenInfo;
import com.mi.util.UserEncryptionUtil;
import com.mi.util.permission.PermissionCallback;
import com.mi.util.permission.PermissionUtil;
import com.mi.util.permission.SamplePermissionCallback;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.HomeItemContentFactory;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.model.item.PageDataBeanExtend;
import com.xiaomi.shopviews.utils.VirtualViewUtils.VirtualViewUpdateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragmentNew extends BaseFragment implements View.OnClickListener, SimplePullToRefreshLayout.OnRefreshListener, LoginManager.AccountListener {
    /* access modifiers changed from: private */
    public static final String k = "HomeFragmentNew";
    private static final int l = 1;
    /* access modifiers changed from: private */
    public int A = Coder.a(170.0f);
    /* access modifiers changed from: private */
    public float B = 0.0f;
    /* access modifiers changed from: private */
    public int C = 0;
    /* access modifiers changed from: private */
    public MoveImageView D;
    private AccountActivity E;
    private long F;
    private int G;
    private Dialog H;

    /* renamed from: a  reason: collision with root package name */
    public SimpleDraweeView f6966a;
    public SimpleDraweeView b;
    public View c;
    public View d;
    public SimpleDraweeView e;
    public View f;
    public boolean g = true;
    public BadgeView i;
    public BadgeView j;
    /* access modifiers changed from: private */
    public SimplePullToRefreshLayout m;
    /* access modifiers changed from: private */
    public EmptyLoadingViewPlus n;
    /* access modifiers changed from: private */
    public HomeRvAdapter o;
    private RecyclerView p;
    private View q;
    private LinearLayout r;
    private ImageView s;
    private CustomTextView t;
    /* access modifiers changed from: private */
    public NewNoticeData u;
    private boolean v = true;
    private String w = (HomeFragmentNew.class.getSimpleName() + "_assembly");
    /* access modifiers changed from: private */
    public List<PageDataBean> x;
    private int y = 0;
    /* access modifiers changed from: private */
    public int z = 0;

    public void onInvalidAuthonToken() {
    }

    public static HomeFragmentNew a(AccountActivity accountActivity, @NonNull ShopSdkInitParamGroup shopSdkInitParamGroup) {
        if (shopSdkInitParamGroup != null && !TextUtils.isEmpty(shopSdkInitParamGroup.d)) {
            LocaleHelper.a(shopSdkInitParamGroup.d);
        }
        Bundle bundle = new Bundle();
        HomeFragmentNew homeFragmentNew = new HomeFragmentNew();
        homeFragmentNew.setArguments(bundle);
        ShopApp.h = shopSdkInitParamGroup;
        homeFragmentNew.E = accountActivity;
        return homeFragmentNew;
    }

    public static HomeFragmentNew a(@NonNull ShopSdkInitParamGroup shopSdkInitParamGroup, @NonNull ShopSdkTokenExpiredListener shopSdkTokenExpiredListener) {
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
        HomeFragmentNew homeFragmentNew = new HomeFragmentNew();
        homeFragmentNew.setArguments(bundle);
        return homeFragmentNew;
    }

    public void setUserVisibleHint(boolean z2) {
        super.setUserVisibleHint(z2);
        if (z2) {
            a(this.B, true);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.shop_home_fragment_new, viewGroup, false);
        b(inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a((Activity) getActivity());
        f();
        s();
        HomeFragmentInitHelper.a(getContext().getApplicationContext(), new SplashUtil.OnNoticeCallback() {
            public void a(NewNoticeData newNoticeData) {
                HomeFragmentNew.this.a(newNoticeData);
            }
        });
        if (ShopApp.j) {
            b((Activity) getActivity());
        }
        LocationUtil.a(getActivity());
        if (!TextUtils.isEmpty(LocationUtil.b())) {
            MiShopStatInterface.b("locationEvent", k, "location", "location", LocationUtil.b());
        }
        LoginManager.u().a((LoginManager.AccountListener) this);
        c();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a(this.B, true);
    }

    public void onResume() {
        super.onResume();
        StaticConstants.b = System.currentTimeMillis();
        if (this.v) {
            this.v = false;
        } else {
            z();
        }
        h();
        g();
        j();
        MiShopStatInterface.a(getContext(), k);
    }

    public void onDestroyView() {
        super.onDestroyView();
        com.mi.global.shop.xmsf.account.LoginManager.u().b((LoginManager.AccountListener) this);
    }

    private void b(View view) {
        this.e = (SimpleDraweeView) view.findViewById(R.id.search_btn);
        this.f = view.findViewById(R.id.search_btn_container);
        this.f.setVisibility(0);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ARouter.a().a(RouterConfig.c).navigation();
            }
        });
        this.c = view.findViewById(R.id.title_bar_cart_view);
        this.b = (SimpleDraweeView) view.findViewById(R.id.shopping_cart);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragmentNew.this.i();
            }
        });
        this.d = view.findViewById(R.id.iv_order_list_fl);
        this.f6966a = (SimpleDraweeView) view.findViewById(R.id.iv_order_list);
        this.f6966a.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragmentNew.this.y();
            }
        });
        this.q = view.findViewById(R.id.title_bar_container);
        this.D = (MoveImageView) view.findViewById(R.id.iv_screen_bg);
        this.n = (EmptyLoadingViewPlus) view.findViewById(R.id.loading);
        this.r = (LinearLayout) view.findViewById(R.id.noticeboard);
        this.t = (CustomTextView) view.findViewById(R.id.notice_text);
        this.s = (ImageView) view.findViewById(R.id.notice_close);
        this.s.setOnClickListener(this);
        this.p = (RecyclerView) view.findViewById(R.id.rv);
        this.p.setLayoutManager(new LinearLayoutManager(getContext()));
        this.m = (SimplePullToRefreshLayout) view.findViewById(R.id.home_fragment_ptr);
        this.m.setOnRefreshListener(new SimplePullToRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                HomeFragmentNew.this.onRefresh();
                HomeFragmentNew.this.m.onRefreshComplete();
            }
        });
        this.m.setOnContentOffsetListener(new SimplePullToRefreshLayout.OnContentOffsetListener() {
            public void a(int i) {
                HomeFragmentNew.this.D.setLocation(0, -i);
            }
        });
        this.n.setPullToRefreshLayout(this.m);
        if (this.u != null) {
            a(this.u);
        }
        p();
        w();
        x();
        r();
        SplashUtil.a((SplashUtil.ISearchConfListener) new MySearchConfListener());
    }

    private class MySearchConfListener implements SplashUtil.ISearchConfListener {
        private MySearchConfListener() {
        }

        public void a(boolean z) {
            if (HomeFragmentNew.this.getActivity() == null) {
                return;
            }
            if (!z || !LocaleHelper.p()) {
                HomeFragmentNew.this.f.setVisibility(4);
            } else {
                HomeFragmentNew.this.f.setVisibility(0);
            }
        }
    }

    public void a(Activity activity) {
        LogUtil.b(k, "不设置 MIUI 深色主题");
    }

    private static void b(Activity activity) {
        final Context applicationContext = activity.getApplication().getApplicationContext();
        PermissionUtil.a(activity, (PermissionCallback) new SamplePermissionCallback() {
            public void onDenied() {
            }

            public void onResult() {
                HomeFragmentNew.b(applicationContext);
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

    private void p() {
        this.p.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                int b = HomeFragmentNew.this.b();
                HomeFragmentNew.this.D.setLocation(0, b);
                if (Math.abs(b - HomeFragmentNew.this.z) >= 5) {
                    float e = (((float) b) * 1.0f) / ((float) HomeFragmentNew.this.A);
                    if (e < HomeFragmentNew.this.B) {
                        float unused = HomeFragmentNew.this.B = e;
                    }
                    Log.d("onScrolled", "-------" + b + "------------" + e + "---");
                    if (e >= 0.0f && HomeFragmentNew.this.B <= 1.0f) {
                        float unused2 = HomeFragmentNew.this.B = e;
                        int unused3 = HomeFragmentNew.this.z = b;
                        HomeFragmentNew.this.a(e, false);
                    }
                }
            }
        });
    }

    public boolean a() {
        return this.B >= 0.5f;
    }

    public int b() {
        LinearLayoutManager linearLayoutManager;
        int findFirstVisibleItemPosition;
        int i2 = 0;
        if (this.x == null || (findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()) == -1) {
            return 0;
        }
        View findViewByPosition = (linearLayoutManager = (LinearLayoutManager) this.p.getLayoutManager()).findViewByPosition(findFirstVisibleItemPosition);
        PageDataBean pageDataBean = this.x.get(findFirstVisibleItemPosition);
        if (pageDataBean != null && pageDataBean.x == 0) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) findViewByPosition.getLayoutParams();
            pageDataBean.x = findViewByPosition.getHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
        }
        for (int i3 = findFirstVisibleItemPosition - 1; i3 >= 0; i3--) {
            View findViewByPosition2 = linearLayoutManager.findViewByPosition(i3);
            PageDataBean pageDataBean2 = this.x.get(i3);
            if (pageDataBean2 != null) {
                if (pageDataBean2.x == 0 && findViewByPosition2 != null) {
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) findViewByPosition2.getLayoutParams();
                    pageDataBean2.x = findViewByPosition2.getHeight() + layoutParams2.bottomMargin + layoutParams2.topMargin;
                }
                i2 += pageDataBean2.x;
            }
        }
        return i2 - findViewByPosition.getTop();
    }

    /* access modifiers changed from: private */
    public void a(float f2, boolean z2) {
        if (e() && this.e != null) {
            if (f2 < 0.5f || z2) {
                this.g = true;
                if (this.C == 1) {
                    r();
                } else {
                    q();
                }
            } else if (f2 >= 0.5f || z2) {
                this.g = false;
                r();
            }
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            this.q.setBackgroundColor(Color.argb((int) (f2 * 255.0f), 255, 255, 255));
        }
    }

    private void q() {
        this.e.setImageDrawable(getResources().getDrawable(R.drawable.shop_search_white));
        this.f6966a.setImageDrawable(getResources().getDrawable(R.drawable.shop_ic_order_list_white));
        this.b.setImageDrawable(getResources().getDrawable(R.drawable.shop_cart_white));
    }

    private void r() {
        this.e.setImageDrawable(getResources().getDrawable(R.drawable.shop_search_grey));
        this.f6966a.setImageDrawable(getResources().getDrawable(R.drawable.shop_ic_order_list));
        this.b.setImageDrawable(getResources().getDrawable(R.drawable.shop_cart_grey));
    }

    public void onRefresh() {
        s();
    }

    /* access modifiers changed from: private */
    public void a(HomePageConfigResult.DataBean dataBean) {
        if (dataBean != null) {
            this.x = dataBean.page_data;
            b(dataBean);
            this.o = new HomeRvAdapter(this.x, new ProviderClickListener() {
                public void a(PageDataBean pageDataBean, String str, String str2, String str3, int i, int i2) {
                }

                public void a(String str, String str2, String str3, String str4) {
                    b(str, str3);
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str4)) {
                        MiShopStatInterface.b(str2, str4);
                    }
                }

                public void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean, String str2) {
                    if (assemblyInfoBean != null) {
                        b(assemblyInfoBean.c, str2);
                        if (!TextUtils.isEmpty(assemblyInfoBean.f13217a) && !TextUtils.isEmpty(assemblyInfoBean.D)) {
                            MiShopStatInterface.a(assemblyInfoBean.f13217a, assemblyInfoBean.D, HomeFragmentNew.a(str, (Object) assemblyInfoBean));
                        }
                    }
                }

                public void a(String str, PageDataBeanExtend.NavList navList, String str2) {
                    if (navList != null) {
                        b(navList.c, str2);
                        if (!TextUtils.isEmpty(navList.f13219a) && !TextUtils.isEmpty(navList.f)) {
                            MiShopStatInterface.a(navList.f13219a, navList.f, HomeFragmentNew.a(str, (Object) navList));
                        }
                    }
                }

                private void b(String str, String str2) {
                    if (!TextUtils.isEmpty(str)) {
                        if (!TextUtils.isEmpty(str2)) {
                            HomeFragmentNew.this.a(str2);
                        }
                        if (str.contains("youtube")) {
                            HomeFragmentNew.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                            return;
                        }
                        Intent intent = new Intent(HomeFragmentNew.this.getActivity(), WebActivity.class);
                        intent.putExtra("url", str);
                        HomeFragmentNew.this.startActivity(intent);
                    }
                }

                public void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
                    if (assemblyInfoBean != null && !TextUtils.isEmpty(assemblyInfoBean.f13217a) && !TextUtils.isEmpty(assemblyInfoBean.D)) {
                        MiShopStatInterface.b(assemblyInfoBean.f13217a, assemblyInfoBean.D, HomeFragmentNew.a(str, (Object) assemblyInfoBean));
                    }
                }

                public void b(String str, PageDataBeanExtend.NavList navList, String str2) {
                    if (navList != null && !TextUtils.isEmpty(navList.f13219a) && !TextUtils.isEmpty(str2)) {
                        MiShopStatInterface.b(navList.f13219a, str2, HomeFragmentNew.a(str, (Object) navList));
                    }
                }

                public void a(String str, String str2) {
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        MiShopStatInterface.a(str, str2);
                    }
                }

                public void a(PageDataBean pageDataBean, String str, Object obj) {
                    if (str.equals(HomeItemContentFactory.t)) {
                        boolean e = HomeFragmentNew.this.e();
                    } else if (!str.equals(HomeItemContentFactory.p)) {
                    } else {
                        if (!com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                            ShopApp.e();
                        } else if (obj != null) {
                            HomeFragmentNew.this.a(pageDataBean, obj);
                        }
                    }
                }

                public void a(int i, int i2) {
                    int unused = HomeFragmentNew.this.C = i;
                    if (HomeFragmentNew.this.e()) {
                        HomeFragmentNew.this.a(i, i2);
                    }
                }
            });
            HomeRvAdapter.a(LocaleHelper.e());
            this.p.setAdapter(this.o);
        }
    }

    public static String a(String str, Object obj) {
        try {
            Gson gson = new Gson();
            Map map = (Map) gson.fromJson(gson.toJson(obj), new TypeToken<Map<String, Object>>() {
            }.getType());
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Object value = ((Map.Entry) it.next()).getValue();
                if (value == null || TextUtils.isEmpty(value.toString())) {
                    it.remove();
                }
            }
            map.put("assembly_key", str);
            return gson.toJson((Object) map);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* access modifiers changed from: private */
    public void a(final PageDataBean pageDataBean, final Object obj) {
        if (System.currentTimeMillis() - this.F >= 800) {
            this.F = System.currentTimeMillis();
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(pageDataBean.f);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (jSONObject != null) {
                String bI = pageDataBean.z ? ConnectionHelper.bI() : ConnectionHelper.bH();
                final String optString = jSONObject.optString("follow_success");
                HashMap hashMap = new HashMap(4);
                hashMap.put("event_code", jSONObject.opt("event_code") + "");
                hashMap.put("follow_type", jSONObject.opt("follow_type") + "");
                hashMap.put("block_code", jSONObject.opt("block_code") + "");
                hashMap.put("build_type", jSONObject.opt("build_type") + "");
                SimpleJsonRequest simpleJsonRequest = new SimpleJsonRequest(bI, HomeFlashSaleFollowResult.class, hashMap, new SimpleCallback<HomeFlashSaleFollowResult>() {
                    public void a(String str) {
                    }

                    public void a(HomeFlashSaleFollowResult homeFlashSaleFollowResult) {
                        if (homeFlashSaleFollowResult != null && homeFlashSaleFollowResult.data != null) {
                            if (homeFlashSaleFollowResult.data.followed) {
                                ((ImageButton) obj).setBackground(HomeFragmentNew.this.getActivity().getResources().getDrawable(R.drawable.remind));
                                pageDataBean.z = true;
                                if (!TextUtils.isEmpty(optString)) {
                                    Toast.makeText(HomeFragmentNew.this.getActivity(), optString, 0).show();
                                }
                            } else if (homeFlashSaleFollowResult.data.removed) {
                                ((ImageButton) obj).setBackground(HomeFragmentNew.this.getActivity().getResources().getDrawable(R.drawable.unremind));
                                pageDataBean.z = false;
                            }
                        }
                    }
                });
                simpleJsonRequest.setTag(k);
                RequestQueueUtil.a().add(simpleJsonRequest);
            }
        }
    }

    public void a(int i2, int i3) {
        if (this.g && this.G != i2) {
            this.G = i2;
            if (i2 == 1) {
                this.e.setImageDrawable(getResources().getDrawable(R.drawable.shop_search_grey));
                this.f6966a.setImageDrawable(getResources().getDrawable(R.drawable.shop_ic_order_list));
                this.b.setImageDrawable(getResources().getDrawable(R.drawable.shop_cart_grey));
                return;
            }
            this.e.setImageDrawable(getResources().getDrawable(R.drawable.shop_search_white));
            this.f6966a.setImageDrawable(getResources().getDrawable(R.drawable.shop_ic_order_list_white));
            this.b.setImageDrawable(getResources().getDrawable(R.drawable.shop_cart_white));
        }
    }

    private void b(HomePageConfigResult.DataBean dataBean) {
        if (!TextUtils.isEmpty(dataBean.ext_page_data_str)) {
            try {
                final String optString = new JSONObject(dataBean.ext_page_data_str).optString("background_img");
                if (!TextUtils.isEmpty(optString)) {
                    this.D.setEnable(true);
                    Glide.c(ShopApp.g()).j().a(optString).a(new SimpleTarget<Bitmap>() {
                        public void a(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            if (bitmap != null) {
                                int width = bitmap.getWidth();
                                int height = bitmap.getHeight();
                                if (width != 0) {
                                    HomeFragmentNew.this.D.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (((((float) height) * 1.0f) / ((float) width)) * ((float) ScreenInfo.a().b()))));
                                }
                            }
                            ImageLoader.a().a(optString, (ImageView) HomeFragmentNew.this.D);
                        }
                    });
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (str != null && !TextUtils.isEmpty(str)) {
            AnonymousClass13 r0 = new SimpleCallback<NewDiscoverPageViewsDate>() {
                public void a(NewDiscoverPageViewsDate newDiscoverPageViewsDate) {
                }
            };
            HashMap hashMap = new HashMap();
            hashMap.put("id", str);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.ar(), NewDiscoverPageViewsDate.class, hashMap, r0);
            simpleProtobufRequest.setTag(k);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (this.o != null && this.o.q().size() != 0) {
            MiToast.a(getContext(), (CharSequence) str, 0);
        } else if (this.n != null) {
            this.n.onError(false, BaseResult.ResultStatus.NETWROK_ERROR, (Handler.Callback) null);
        }
    }

    private void s() {
        Request request;
        t();
        k();
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aG()).buildUpon();
        AnonymousClass14 r1 = new SimpleCallback<HomePageConfigResult>() {
            public void a(HomePageConfigResult homePageConfigResult) {
                HomeFragmentNew.this.l();
                if (HomeFragmentNew.this.n != null) {
                    HomeFragmentNew.this.n.setVisibility(8);
                }
                HomeFragmentNew.this.a(homePageConfigResult.data);
                HomeFragmentNew.this.c(homePageConfigResult.data);
            }

            public void a(String str) {
                HomeFragmentNew.this.b(str);
                HomeFragmentNew.this.l();
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), HomePageConfigResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), HomePageConfigResult.class, r1);
        }
        request.setTag(k);
        RequestQueueUtil.a().add(request);
    }

    /* access modifiers changed from: private */
    public void c(HomePageConfigResult.DataBean dataBean) {
        if (dataBean != null) {
            try {
                SaveObjectUtils.a((Context) ShopApp.g(), "pref_key_home_new_list", (Object) dataBean);
            } catch (Exception unused) {
            }
        }
    }

    private HomePageConfigResult.DataBean t() {
        HomePageConfigResult.DataBean dataBean;
        try {
            dataBean = (HomePageConfigResult.DataBean) SaveObjectUtils.a((Context) ShopApp.g(), "pref_key_home_new_list", HomePageConfigResult.DataBean.class);
        } catch (Exception unused) {
            dataBean = null;
        }
        a(dataBean);
        return dataBean;
    }

    public void c() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aD()).buildUpon();
        AnonymousClass15 r1 = new SimpleCallback<DomainResult>() {
            public void a(DomainResult domainResult) {
                if (domainResult == null || domainResult.domainModels == null || domainResult.domainModels.size() <= 0) {
                    Utils.Preference.setStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a);
                    return;
                }
                Utils.Preference.setStringPref(ShopApp.g(), Constants.Prefence.S, new Gson().toJson((Object) domainResult.domainModels));
                HomeFragmentNew.this.u();
                ConnectionHelper.b();
            }

            public void a(String str) {
                String o = HomeFragmentNew.k;
                LogUtil.b(o, "getDomain Exception:" + str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), DomainResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), DomainResult.class, r1);
        }
        request.setTag(k);
        RequestQueueUtil.a().add(request);
        u();
    }

    /* access modifiers changed from: private */
    public void u() {
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

    public void f() {
        if (LocaleHelper.g()) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aE()).buildUpon();
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), VirtualViewResult.class, new SimpleCallback<VirtualViewResult>() {
                public void a(VirtualViewResult virtualViewResult) {
                    if (virtualViewResult.data != null && virtualViewResult.data.version_info != null) {
                        VirtualViewModel.VersionInfo versionInfo = virtualViewResult.data.version_info;
                        VirtualViewUpdateUtil.a(versionInfo.url, versionInfo.version, new VirtualViewUpdateUtil.VirtualViewCallBack() {
                            public void a() {
                                HomeFragmentNew.this.v();
                            }
                        });
                    }
                }

                public void a(String str) {
                    String o = HomeFragmentNew.k;
                    LogUtil.b(o, "getVirtual Exception:" + str);
                }
            });
            simpleProtobufRequest.setTag(k);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    /* access modifiers changed from: private */
    public void v() {
        if (this.x != null && this.o != null) {
            MainThreadHandler.c((Runnable) new Runnable() {
                public void run() {
                    for (int i = 0; i < HomeFragmentNew.this.x.size(); i++) {
                        if (HomeItemContentFactory.J.equals(((PageDataBean) HomeFragmentNew.this.x.get(i)).b)) {
                            HomeFragmentNew.this.o.notifyItemChanged(i);
                        }
                    }
                }
            });
        }
    }

    private void w() {
        this.i = new BadgeView(getContext(), this.c);
        this.i.setTextColor(getResources().getColor(17170443));
        this.i.setTextSize(2, 10.0f);
        this.i.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.shop_orangle_inner_solid_circle));
        this.i.setmBadgePosition(2);
        this.i.setBadgeMargin(0, Coder.a((Activity) getActivity(), 5.0f));
    }

    private void x() {
        this.j = new BadgeView(getContext(), this.d);
        this.j.setTextColor(getResources().getColor(17170443));
        this.j.setTextSize(2, 10.0f);
        this.j.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.shop_orangle_inner_solid_circle));
        this.j.setmBadgePosition(2);
        this.j.setBadgeMargin(0, Coder.a((Activity) getActivity(), 5.0f));
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
                    if (i2 <= 0 || HomeFragmentNew.this.b.getVisibility() != 0) {
                        HomeFragmentNew.this.i.hide();
                        return;
                    }
                    HomeFragmentNew.this.i.show();
                    HomeFragmentNew.this.i.setCount(i2);
                    HomeFragmentNew.this.i.invalidate();
                }
            });
        }
    }

    public void g() {
        LogUtil.b(k, "update cart as pref value");
        if (BaseActivity.shoppingCartNum == -1) {
            BaseActivity.shoppingCartNum = Utils.Preference.getIntPref(getContext(), "pref_key_shoppingcart_number", 0);
        }
        d(BaseActivity.shoppingCartNum);
    }

    public void h() {
        LogUtil.b(k, "update cart as pref value");
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
                    if (i2 <= 0 || HomeFragmentNew.this.f6966a.getVisibility() != 0) {
                        HomeFragmentNew.this.j.hide();
                        return;
                    }
                    HomeFragmentNew.this.j.show();
                    HomeFragmentNew.this.j.setCount(i2);
                    HomeFragmentNew.this.j.invalidate();
                }
            });
        }
    }

    public void c(int i2) {
        String str = k;
        LogUtil.b(str, "update cart:" + i2);
        if (BaseActivity.shoppingCartNum != i2) {
            BaseActivity.shoppingCartNum = i2;
            Utils.Preference.setIntPref(getContext(), "pref_key_shoppingcart_number", i2);
        }
        d(i2);
    }

    public void i() {
        if (LocaleHelper.g()) {
            startActivityForResult(new Intent(getContext(), ShoppingCartActivity.class), 22);
            return;
        }
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra("url", ConnectionHelper.ay());
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void y() {
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

    private void z() {
        Request request;
        if (A()) {
            String ai = ConnectionHelper.ai();
            AnonymousClass21 r1 = new SimpleCallback<NewNoticeResult>() {
                public void a(NewNoticeResult newNoticeResult) {
                    NewNoticeData unused = HomeFragmentNew.this.u = newNoticeResult.data;
                    HomeFragmentNew.this.b(HomeFragmentNew.this.u);
                }

                public void a(String str) {
                    String o = HomeFragmentNew.k;
                    LogUtil.b(o, "load notice error" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(ai, NewNoticeResult.class, r1);
            } else {
                request = new SimpleJsonRequest(ai, NewNoticeResult.class, r1);
            }
            request.setTag(k);
            RequestQueueUtil.a().add(request);
        }
    }

    private boolean A() {
        boolean z2;
        String stringPref = Utils.Preference.getStringPref(getContext(), "pref_key_home_notice_closed_type", "");
        long longPref = Utils.Preference.getLongPref(getContext(), "pref_key_home_notice_closed_time", 0);
        if (longPref != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            z2 = simpleDateFormat.format(Long.valueOf(currentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(longPref)));
        } else {
            z2 = false;
        }
        if (!z2 || TextUtils.isEmpty(stringPref) || !stringPref.equals("1")) {
            return true;
        }
        return false;
    }

    public void a(NewNoticeData newNoticeData) {
        if (newNoticeData != null && !TextUtils.isEmpty(newNoticeData.content)) {
            this.u = newNoticeData;
            if (A()) {
                b(newNoticeData);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(NewNoticeData newNoticeData) {
        if (this.r != null) {
            if (newNoticeData == null || TextUtils.isEmpty(newNoticeData.content)) {
                this.r.setVisibility(8);
            } else if ("0".equalsIgnoreCase(newNoticeData.type)) {
                this.r.setVisibility(8);
            } else if ("2".equals(newNoticeData.type)) {
                this.r.setVisibility(8);
            } else {
                String str = k;
                LogUtil.b(str, "noticeShow:" + newNoticeData);
                this.t.setText(newNoticeData.content);
                this.r.setOnClickListener(this);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.r.getLayoutParams();
                layoutParams.topMargin = BaseActivity.statusBarHeight + Coder.a(40.0f);
                this.r.setLayoutParams(layoutParams);
                this.r.setVisibility(0);
            }
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1 && i3 == 0) {
            if (!com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
                if (ShopApp.h.f.equals("community_sdk")) {
                    this.E.gotoAccount();
                } else {
                    ShopApp.e();
                }
            } else {
                return;
            }
        }
        ShopApp.m().onActivityResult(i2, i3, intent);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.notice_close) {
            this.r.setVisibility(8);
            if (this.u != null && !TextUtils.isEmpty(this.u.type)) {
                Utils.Preference.setStringPref(getContext(), "pref_key_home_notice_closed_type", this.u.type);
                Utils.Preference.setLongPref(getContext(), "pref_key_home_notice_closed_time", Long.valueOf(System.currentTimeMillis()));
            }
        } else if (id == R.id.noticeboard && this.u != null && !TextUtils.isEmpty(this.u.content)) {
            if ("2".equalsIgnoreCase(this.u.type) && com.mi.global.shop.xmsf.account.LoginManager.u().i()) {
                if (LocaleHelper.g()) {
                    Intent intent = new Intent(getActivity(), OrderListAcitvity.class);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                    return;
                }
                String t2 = ConnectionHelper.t();
                Intent intent2 = new Intent(getActivity(), WebActivity.class);
                intent2.putExtra("url", t2);
                getActivity().startActivity(intent2);
            }
            if ("1".equalsIgnoreCase(this.u.type) && !TextUtils.isEmpty(this.u.url)) {
                String str = this.u.url;
                Intent intent3 = new Intent(getActivity(), WebActivity.class);
                intent3.putExtra("url", str);
                getActivity().startActivity(intent3);
            }
        }
    }

    public void j() {
        Request request;
        if (com.mi.global.shop.xmsf.account.LoginManager.u().x() && !TextUtils.isEmpty(com.mi.global.shop.xmsf.account.LoginManager.u().c())) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aJ()).buildUpon();
            buildUpon.appendQueryParameter("mUserId", UserEncryptionUtil.a(com.mi.global.shop.xmsf.account.LoginManager.u().c()));
            buildUpon.appendQueryParameter("cUserId", UserEncryptionUtil.b(com.mi.global.shop.xmsf.account.LoginManager.u().c()));
            buildUpon.appendQueryParameter("security", "true");
            AnonymousClass22 r1 = new SimpleCallback<NewUserInfoResult>() {
                public void a(NewUserInfoResult newUserInfoResult) {
                    NewUserInfoData newUserInfoData;
                    if (newUserInfoResult.data != null) {
                        if (newUserInfoResult.data.jsonUserInfoData == null) {
                            newUserInfoData = newUserInfoResult.data;
                        } else {
                            newUserInfoData = newUserInfoResult.data.jsonUserInfoData;
                        }
                        HomeFragmentNew.this.a(newUserInfoData.not_pay_order_count);
                    }
                }

                public void a(String str) {
                    String o = HomeFragmentNew.k;
                    LogUtil.b(o, "RefreshUserInfo Exception:" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            } else {
                request = new SimpleJsonRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            }
            request.setTag(k);
            RequestQueueUtil.a().add(request);
        }
    }

    public void k() {
        if (e()) {
            if (this.H != null) {
                this.H.show();
                a(true);
                return;
            }
            n();
            this.H.show();
            a(true);
        }
    }

    public void l() {
        if (e() && this.H != null && this.H.isShowing()) {
            a(false);
            this.H.dismiss();
        }
    }

    private void a(boolean z2) {
        ImageView imageView;
        if (this.H != null && (imageView = (ImageView) this.H.findViewById(R.id.iv_rabbit)) != null) {
            if (z2) {
                ((AnimationDrawable) imageView.getDrawable()).start();
            } else {
                ((AnimationDrawable) imageView.getDrawable()).stop();
            }
        }
    }

    public void m() {
        ApplicationContextHolder.l();
    }

    public void n() {
        this.H = new Dialog(getActivity());
        this.H.setCanceledOnTouchOutside(true);
        this.H.getWindow().setDimAmount(0.0f);
        this.H.getWindow().getDecorView().setBackgroundColor(0);
        this.H.setContentView(((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.shop_common_rabbit_loading_dialog, (ViewGroup) null));
    }

    public boolean e() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !activity.isFinishing();
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i2, String str4) {
        c(i2);
    }

    public void onLogin(String str, String str2, String str3) {
        j();
        if (LocaleHelper.j()) {
            com.mi.global.shop.xmsf.account.LoginManager.u().y();
        }
        if (getActivity() != null) {
            WebActivity.setCookies(ShopApp.g());
        }
        com.mi.global.shop.xmsf.account.LoginManager.u().F();
    }

    public void onLogout() {
        c(0);
        a(0);
        WebViewCookieManager.b(getContext());
        WebViewCookieManager.c(getContext());
    }
}
