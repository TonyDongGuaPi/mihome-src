package com.mi.global.shop.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.account.activity.AccountActivity;
import com.mi.global.shop.base.account.AccountListener;
import com.mi.global.shop.base.service.ConnectionHelperService;
import com.mi.global.shop.base.service.LoginManagerService;
import com.mi.global.shop.base.service.MiPushClientService;
import com.mi.global.shop.base.service.WebViewCookieManagerService;
import com.mi.global.shop.base.utils.FragmentUtil;
import com.mi.global.shop.base.utils.Utils;
import com.mi.global.shop.base.widget.BadgeView;
import com.mi.global.shop.base.widget.CustomEditTextView;
import com.mi.global.shop.base.widget.CustomInsetsFrameLayout;
import com.mi.log.LogUtil;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.mi.util.permission.PermissionUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseActivity extends AccountActivity implements AccountListener {
    private static final String TAG = "BaseActivity";
    private static int sDarkModeFlag = 0;
    private static Method sExtraFlagField = null;
    public static int shoppingCartNum = -1;
    public static int statusBarHeight;
    @Autowired
    public ConnectionHelperService connectionHelperService;
    public ImageView dividerView;
    public SimpleDraweeView iv_title_bg;
    public SimpleDraweeView iv_title_icon;
    public LinearLayout layoutSearchContainer;
    private Dialog loadingDialog;
    @Autowired
    public LoginManagerService loginManagerService;
    public View mBackView;
    public View mCartView;
    private CustomInsetsFrameLayout mContentContainer;
    private View mDecoratedView;
    public CustomTextView mForgetPwd;
    public View mMessageView;
    private View mTitleBarContainer;
    private View mTitleBarRL;
    private CustomTextView mTitleTv;
    private CustomTextView mainTabCenterTitle;
    public CustomTextView mainTabTitle;
    public FrameLayout mainTabTitleContainer;
    public SimpleDraweeView messageBtn;
    public BadgeView messageCountBv;
    @Autowired
    public MiPushClientService miPushClientService;
    /* access modifiers changed from: private */
    public String pageId;
    public SimpleDraweeView searchBtn;
    public View searchBtnContainer;
    public CustomEditTextView searchInputEtv;
    public SimpleDraweeView settingsBtn;
    public BadgeView shoppingCountBv;
    public SimpleDraweeView shopping_cart;
    @Autowired
    public WebViewCookieManagerService webViewCookieManagerService;

    /* access modifiers changed from: protected */
    public boolean needShowBadgeView() {
        return true;
    }

    /* access modifiers changed from: protected */
    public Fragment newFragmentByTag(String str) {
        return null;
    }

    public void onInvalidAuthonToken() {
    }

    public void startCartActivity() {
    }

    public void updateCartAndAccount() {
    }

    public Resources getResources() {
        if (Build.VERSION.SDK_INT >= 24) {
            return getApplicationContext().getResources();
        }
        return super.getResources();
    }

    public static void onShopActivityCreate(Activity activity) {
        if (statusBarHeight == 0 && Device.m >= 19) {
            try {
                Class<?> cls = Class.forName("com.android.internal.R$dimen");
                statusBarHeight = activity.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
            } catch (Exception unused) {
            }
        }
        Window window = activity.getWindow();
        try {
            if (sExtraFlagField == null) {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                sDarkModeFlag = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                sExtraFlagField = window.getClass().getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            }
            sExtraFlagField.invoke(window, new Object[]{Integer.valueOf(sDarkModeFlag), Integer.valueOf(sDarkModeFlag)});
            if (Build.VERSION.SDK_INT >= 23 && Device.p) {
                window.getDecorView().setSystemUiVisibility(8192);
            }
        } catch (Exception unused2) {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.b(TAG, "STARTUP onCreate");
        super.onCreate(bundle);
        onShopActivityCreate(this);
        setContentView(getLayoutResId());
        this.loginManagerService = (LoginManagerService) ARouter.a().a(LoginManagerService.class);
        this.connectionHelperService = (ConnectionHelperService) ARouter.a().a(ConnectionHelperService.class);
        this.webViewCookieManagerService = (WebViewCookieManagerService) ARouter.a().a(WebViewCookieManagerService.class);
        this.miPushClientService = (MiPushClientService) ARouter.a().a(MiPushClientService.class);
        if (this.loginManagerService == null || this.connectionHelperService == null || this.webViewCookieManagerService == null) {
            throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
        }
        this.mContentContainer = (CustomInsetsFrameLayout) findViewById(R.id.content_container);
        this.mTitleBarContainer = findViewById(R.id.title_bar_container);
        this.mTitleTv = (CustomTextView) findViewById(R.id.title_bar_title);
        this.mainTabTitle = (CustomTextView) findViewById(R.id.title_mi_text);
        this.mainTabCenterTitle = (CustomTextView) findViewById(R.id.title_mi_center_text);
        this.mainTabTitleContainer = (FrameLayout) findViewById(R.id.layout_title_container);
        this.mTitleBarRL = findViewById(R.id.title_bar_RL);
        this.shopping_cart = (SimpleDraweeView) findViewById(R.id.shopping_cart);
        this.iv_title_bg = (SimpleDraweeView) findViewById(R.id.iv_title_bg);
        this.iv_title_icon = (SimpleDraweeView) findViewById(R.id.iv_title_icon);
        this.mCartView = findViewById(R.id.title_bar_cart_view);
        this.mMessageView = findViewById(R.id.title_bar_message_view);
        this.mCartView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiShopStatInterface.b("cart_click", getClass().getSimpleName());
                MiShopStatInterface.a("cart_click", "navigation_cart");
                BaseActivity.this.startCartActivity();
            }
        });
        this.mBackView = findViewById(R.id.title_bar_back);
        this.mForgetPwd = (CustomTextView) findViewById(R.id.title_bar_text_right);
        if (statusBarHeight > 0) {
            new View(this);
            new LinearLayout.LayoutParams(-1, statusBarHeight);
            this.mTitleBarContainer.setPadding(0, statusBarHeight, 0, 0);
            updateCustomContentMarginTop(false);
        }
        setBadgeView();
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseActivity.this.finish();
                if (!TextUtils.isEmpty(BaseActivity.this.pageId)) {
                    MiShopStatInterface.a("return_click", BaseActivity.this.pageId);
                }
            }
        });
        this.loginManagerService.a(this);
        LogUtil.b(TAG, "STARTUP onCreate done");
        this.messageBtn = (SimpleDraweeView) findViewById(R.id.message_btn);
        this.settingsBtn = (SimpleDraweeView) findViewById(R.id.settings_btn);
        this.searchBtn = (SimpleDraweeView) findViewById(R.id.search_btn);
        this.searchBtnContainer = findViewById(R.id.search_btn_container);
        this.layoutSearchContainer = (LinearLayout) findViewById(R.id.layout_search_container);
        this.searchInputEtv = (CustomEditTextView) findViewById(R.id.etv_search_input);
        this.dividerView = (ImageView) findViewById(R.id.iv_divider);
        updateHeaderSkinGif((Uri) null);
    }

    /* access modifiers changed from: protected */
    public void setSearchViewVisible(int i) {
        this.searchBtnContainer.setVisibility(i);
        this.layoutSearchContainer.setVisibility(i);
    }

    public void setTitle(CharSequence charSequence) {
        if (this.mTitleTv != null) {
            this.mTitleTv.setText(charSequence);
            super.setTitle(charSequence);
        }
    }

    public void setTitle(int i) {
        if (this.mTitleTv != null) {
            this.mTitleTv.setText(i);
            super.setTitle(i);
        }
    }

    public CustomTextView getMainTabTitle() {
        return this.mainTabTitle;
    }

    public void setMainTabTitle(String str) {
        this.mainTabTitle.setText(str);
    }

    public CustomTextView getMainTabCenterTitle() {
        return this.mainTabCenterTitle;
    }

    public void setMainTabCenterTitle(String str) {
        this.mainTabCenterTitle.setText(str);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.feature_base_base_activity;
    }

    public View getTitleBarContainer() {
        return this.mTitleBarContainer;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showFragment(String str, Bundle bundle, boolean z) {
        showFragment(str, (Bundle) null, bundle, z);
    }

    public void showFragment(String str, Bundle bundle, Bundle bundle2, boolean z) {
        if (this.mDecoratedView == null) {
            LogUtil.c(TAG, "mDecoratedView is NOT FOUND.");
        } else if (this.mDecoratedView.getId() <= 0) {
            throw new IllegalArgumentException("The activity in xml layout MUST has argument 'id'.");
        } else if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragmentByTag = getFragmentByTag(str);
            if (fragmentByTag == null) {
                fragmentByTag = newFragmentByTag(str);
                if (bundle2 != null) {
                    fragmentByTag.setArguments(bundle2);
                }
            }
            if (fragmentByTag == null) {
                LogUtil.c(TAG, "NO fragment found by tag: " + str);
                return;
            }
            beginTransaction.setTransition(4099);
            beginTransaction.replace(this.mDecoratedView.getId(), fragmentByTag, str);
            if (z) {
                beginTransaction.addToBackStack((String) null);
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public Fragment getFragmentByTag(String str) {
        return getSupportFragmentManager().findFragmentByTag(str);
    }

    /* access modifiers changed from: protected */
    public void setCustomContentView(int i) {
        View inflate = View.inflate(this, i, (ViewGroup) null);
        if (inflate != null) {
            if (this.mDecoratedView != null) {
                this.mContentContainer.removeView(this.mDecoratedView);
            }
            this.mContentContainer.addView(inflate);
            this.mDecoratedView = inflate;
        } else if (this.mDecoratedView != null) {
            this.mContentContainer.removeView(this.mDecoratedView);
            this.mDecoratedView = null;
        }
    }

    /* access modifiers changed from: protected */
    public void updateCustomContentMarginTop(boolean z) {
        int i;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mContentContainer.getLayoutParams();
        if (z) {
            i = 0;
        } else {
            i = statusBarHeight + Coder.a((Activity) this, 45.0f);
        }
        layoutParams.topMargin = i;
        this.mContentContainer.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void setCartViewVisible(boolean z) {
        if (z) {
            this.mCartView.setVisibility(0);
            this.shoppingCountBv.setVisibility(0);
            updateShoppingCart();
            return;
        }
        this.mCartView.setVisibility(8);
        this.shoppingCountBv.setVisibility(8);
    }

    private void setBadgeView() {
        this.shoppingCountBv = new BadgeView((Context) this, this.mCartView);
        this.shoppingCountBv.setTextColor(getResources().getColor(17170443));
        this.shoppingCountBv.setTextSize(2, 10.0f);
        this.shoppingCountBv.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.feature_base_orangle_inner_solid_circle));
        this.shoppingCountBv.setmBadgePosition(2);
        this.shoppingCountBv.setBadgeMargin(0, Coder.a((Activity) this, 5.0f));
        this.messageCountBv = new BadgeView((Context) this, this.mMessageView);
        this.messageCountBv.setTextColor(getResources().getColor(17170443));
        this.messageCountBv.setTextSize(2, 10.0f);
        this.messageCountBv.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.feature_base_orangle_inner_solid_circle));
        this.messageCountBv.setmBadgePosition(2);
        this.messageCountBv.setBadgeMargin(0, Coder.a((Activity) this, 5.0f));
    }

    public void updateBadgeViewVisble(final String str) {
        LogUtil.b(TAG, "update cart visible:" + str);
        runOnUiThread(new Runnable() {
            public void run() {
                if (!BaseActivity.this.connectionHelperService.b(str)) {
                    BaseActivity.this.setCartViewVisible(false);
                    return;
                }
                BaseActivity.this.setCartViewVisible(true);
                BaseActivity.this.updateShoppingCart();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void updateCartBadgeView(final int i) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (i <= 0 || BaseActivity.this.mCartView.getVisibility() != 0) {
                    BaseActivity.this.shoppingCountBv.hide();
                    return;
                }
                BaseActivity.this.shoppingCountBv.show();
                BaseActivity.this.shoppingCountBv.setCount(i);
                BaseActivity.this.shoppingCountBv.invalidate();
            }
        });
    }

    public void updateMessageBadgeView(final int i) {
        runOnUiThread(new Runnable() {
            public void run() {
                PrintStream printStream = System.out;
                printStream.println("-----------messageBtn:" + BaseActivity.this.messageBtn.getVisibility());
                if (i <= 0 || BaseActivity.this.messageBtn.getVisibility() != 0) {
                    BaseActivity.this.messageCountBv.hide();
                    return;
                }
                BaseActivity.this.messageCountBv.show();
                BaseActivity.this.messageCountBv.setCount(i);
                BaseActivity.this.messageCountBv.invalidate();
            }
        });
    }

    public int getShoppingCart() {
        return Utils.Preference.getIntPref(this, "pref_key_shoppingcart_number", 0);
    }

    public void updateShoppingCart(int i) {
        LogUtil.b(TAG, "update cart:" + i);
        if (shoppingCartNum != i) {
            shoppingCartNum = i;
            Utils.Preference.setIntPref(this, "pref_key_shoppingcart_number", i);
        }
        updateCartBadgeView(i);
    }

    public void updateShoppingCart() {
        LogUtil.b(TAG, "update cart as pref value");
        if (shoppingCartNum == -1) {
            shoppingCartNum = Utils.Preference.getIntPref(this, "pref_key_shoppingcart_number", 0);
        }
        updateCartBadgeView(shoppingCartNum);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LogUtil.b(TAG, "onDestroy," + getClass().getName());
        this.loginManagerService.b(this);
    }

    private void autoLogin() {
        this.webViewCookieManagerService.a(this);
        this.webViewCookieManagerService.b(this);
    }

    public void onLogin(String str, String str2, String str3) {
        LogUtil.b(TAG, String.format("BaseActivity-onLogin:%s, %s, %s", new Object[]{str, str2, str3}));
        if (!TextUtils.isEmpty(str)) {
            if (PermissionUtil.a((Context) this, "android.permission.READ_PHONE_STATE") && this.miPushClientService != null) {
                this.miPushClientService.a(this, str, (String) null);
            }
        }
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i, String str4) {
        LogUtil.b(TAG, String.format("BaseActivity-userinfoupdate:%s, %s, %s,%d", new Object[]{str, str2, str3, Integer.valueOf(i)}));
        updateShoppingCart(i);
    }

    public void onLogout() {
        LogUtil.b(TAG, "logout");
        updateShoppingCart(0);
        this.webViewCookieManagerService.c(this);
        this.webViewCookieManagerService.d(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LogUtil.b(TAG, "onPause," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        LogUtil.b(TAG, "onRestart," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LogUtil.b(TAG, "onResume," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        LogUtil.b(TAG, "onStart," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        LogUtil.b(TAG, "onStop," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.b(TAG, "onSaveInstanceState , outState:" + bundle);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void refreshFragment() {
        LogUtil.b(TAG, "refreshFragment");
        List<Fragment> a2 = FragmentUtil.a(getSupportFragmentManager());
        LogUtil.b(TAG, "Fragments size:" + a2.size());
        for (Fragment next : a2) {
            LogUtil.b(TAG, "get fragment:" + next.toString());
            if (next instanceof MiFragment) {
                LogUtil.b(TAG, "refreshFragment:" + next.toString());
                ((MiFragment) next).a();
            }
        }
    }

    private void updateHeaderSkinGif(Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            enableImmersionStyle();
            updateHeaderHeight();
        }
    }

    public void enableImmersionStyle() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().setStatusBarColor(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            getWindow().addFlags(134217728);
        }
        if (Build.VERSION.SDK_INT >= 23 && Device.p) {
            getWindow().getDecorView().setSystemUiVisibility(8192);
        }
    }

    public void updateHeaderHeight() {
        if (statusBarHeight != 0) {
            LayoutParamsUtil.a(this.iv_title_bg, statusBarHeight + Coder.a((Activity) this, 45.0f));
            this.mTitleBarContainer.setPadding(0, 0, 0, 0);
        }
    }

    public void initProgressDialog() {
        this.loadingDialog = new Dialog(this);
        this.loadingDialog.setCanceledOnTouchOutside(true);
        this.loadingDialog.getWindow().setDimAmount(0.0f);
        this.loadingDialog.getWindow().getDecorView().setBackgroundColor(0);
        this.loadingDialog.setContentView(((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.feature_base_common_rabbit_loading_dialog, (ViewGroup) null));
    }

    public void showLoading() {
        if (isActivityAlive(this)) {
            if (this.loadingDialog != null) {
                this.loadingDialog.show();
                setLoadingAnimStatus(true);
                return;
            }
            initProgressDialog();
            this.loadingDialog.show();
            setLoadingAnimStatus(true);
        }
    }

    public void hideLoading() {
        if (isActivityAlive(this) && this.loadingDialog != null && this.loadingDialog.isShowing()) {
            setLoadingAnimStatus(false);
            this.loadingDialog.dismiss();
        }
    }

    private void setLoadingAnimStatus(boolean z) {
        ImageView imageView;
        if (this.loadingDialog != null && (imageView = (ImageView) this.loadingDialog.findViewById(R.id.iv_rabbit)) != null) {
            if (z) {
                ((AnimationDrawable) imageView.getDrawable()).start();
            } else {
                ((AnimationDrawable) imageView.getDrawable()).stop();
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getUpdateUrl() {
        return this.connectionHelperService.e();
    }

    public static boolean isActivityAlive(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !((Activity) context).isFinishing();
        }
        Activity activity = (Activity) context;
        if (!activity.isFinishing() || !activity.isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void setBackViewState(String str) {
        this.pageId = str;
    }
}
