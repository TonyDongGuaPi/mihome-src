package com.mi.global.shop.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.account.LoginManager;
import com.mi.account.activity.AccountActivity;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.buy.MiFragment;
import com.mi.global.shop.newmodel.NewPageMessage;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.FragmentUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.widget.BadgeView;
import com.mi.global.shop.widget.CustomInsetsFrameLayout;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseActivity extends AccountActivity implements LoginManager.AccountListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5336a = 1;
    private static final String b = "BaseActivity";
    private static int h = 0;
    private static Method i = null;
    public static int shoppingCartNum = -1;
    public static int statusBarHeight = 0;
    public static int unpaidNum = -1;
    private View c;
    private View d;
    private CustomInsetsFrameLayout e;
    private CustomTextView f;
    private CustomTextView g;
    private View j;
    private SimpleDraweeView k;
    private SimpleDraweeView l;
    private SimpleDraweeView m;
    public View mBackView;
    public View mCartView;
    public CustomTextView mForgetPwd;
    public View mOrderListView;
    private ProgressDialog n;
    public BadgeView orderListCountBv;
    public BadgeView shoppingCountBv;

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
            if (i == null) {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                h = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                i = window.getClass().getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            }
            i.invoke(window, new Object[]{Integer.valueOf(h), Integer.valueOf(h)});
            if (Build.VERSION.SDK_INT >= 23 && Device.p) {
                window.getDecorView().setSystemUiVisibility(8192);
            }
        } catch (Exception unused2) {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setTheme(R.style.Theme_Light);
        LogUtil.b(b, "STARTUP onCreate");
        super.onCreate(bundle);
        onShopActivityCreate(this);
        setContentView(getLayoutResId());
        this.e = (CustomInsetsFrameLayout) findViewById(R.id.content_container);
        this.d = findViewById(R.id.title_bar_container);
        this.f = (CustomTextView) findViewById(R.id.title_bar_title);
        this.g = (CustomTextView) findViewById(R.id.title_mi_text);
        this.j = findViewById(R.id.title_bar_RL);
        this.k = (SimpleDraweeView) findViewById(R.id.shopping_cart);
        this.l = (SimpleDraweeView) findViewById(R.id.iv_title_bg);
        this.m = (SimpleDraweeView) findViewById(R.id.iv_title_icon);
        this.mCartView = findViewById(R.id.title_bar_cart_view);
        this.mCartView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseActivity.this.startCartActivity();
            }
        });
        this.mOrderListView = findViewById(R.id.iv_order_list);
        this.mOrderListView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseActivity.this.a();
            }
        });
        this.mBackView = findViewById(R.id.title_bar_back);
        this.mForgetPwd = (CustomTextView) findViewById(R.id.title_bar_text_right);
        if (statusBarHeight > 0) {
            new LinearLayout.LayoutParams(-1, statusBarHeight);
            this.d.setPadding(0, statusBarHeight, 0, 0);
        }
        b();
        c();
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseActivity.this.finish();
            }
        });
        if (LoginManager.u() != null) {
            LoginManager.u().a((LoginManager.AccountListener) this);
        }
        LogUtil.b(b, "STARTUP onCreate done");
    }

    /* access modifiers changed from: private */
    public void a() {
        if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
            Intent intent = new Intent(this, OrderListAcitvity.class);
            intent.putExtra("type", 0);
            startActivityForResult(intent, 1);
        } else if (ShopApp.h.f.equals("community_sdk")) {
            gotoAccount();
        } else {
            ShopApp.e();
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.f != null) {
            this.f.setText(charSequence);
            super.setTitle(charSequence);
        }
    }

    public void setTitle(int i2) {
        if (this.f != null) {
            this.f.setText(i2);
            super.setTitle(i2);
        }
    }

    public CustomTextView getMainTabTitle() {
        return this.g;
    }

    public void setMainTabTitle(String str) {
        this.g.setText(str);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.shop_base_activity;
    }

    public View getTitleBarContainer() {
        return this.d;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showFragment(String str, Bundle bundle, boolean z) {
        showFragment(str, (Bundle) null, bundle, z);
    }

    public void showFragment(String str, Bundle bundle, Bundle bundle2, boolean z) {
        if (this.c == null) {
            LogUtil.c(b, "mDecoratedView is NOT FOUND.");
        } else if (this.c.getId() <= 0) {
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
                LogUtil.c(b, "NO fragment found by tag: " + str);
                return;
            }
            beginTransaction.setTransition(4099);
            beginTransaction.replace(this.c.getId(), fragmentByTag, str);
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
    public void setCustomContentView(int i2) {
        View inflate = View.inflate(this, i2, (ViewGroup) null);
        if (inflate != null) {
            if (this.c != null) {
                this.e.removeView(this.c);
            }
            this.e.addView(inflate);
            this.c = inflate;
        } else if (this.c != null) {
            this.e.removeView(this.c);
            this.c = null;
        }
    }

    /* access modifiers changed from: protected */
    public void setCartViewVisible(boolean z) {
        if (z) {
            this.mCartView.setVisibility(0);
            return;
        }
        this.mCartView.setVisibility(4);
        this.shoppingCountBv.hide();
    }

    private void b() {
        this.shoppingCountBv = new BadgeView((Context) this, this.mCartView);
        this.shoppingCountBv.setTextColor(getResources().getColor(17170443));
        this.shoppingCountBv.setTextSize(2, 10.0f);
        this.shoppingCountBv.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.shop_orangle_inner_solid_circle));
        this.shoppingCountBv.setmBadgePosition(2);
        this.shoppingCountBv.setBadgeMargin(0, Coder.a((Activity) this, 5.0f));
    }

    private void c() {
        this.orderListCountBv = new BadgeView((Context) this, this.mOrderListView);
        this.orderListCountBv.setTextColor(getResources().getColor(17170443));
        this.orderListCountBv.setTextSize(2, 10.0f);
        this.orderListCountBv.setBadgeBackgroundDrawable(getResources().getDrawable(R.drawable.shop_orangle_inner_solid_circle));
        this.orderListCountBv.setmBadgePosition(2);
        this.orderListCountBv.setBadgeMargin(0, Coder.a((Activity) this, 5.0f));
    }

    public void updateCartBadgeViewVisble(final String str) {
        LogUtil.b(b, "update cart visible:" + str);
        runOnUiThread(new Runnable() {
            public void run() {
                if (!ConnectionHelper.l(str)) {
                    BaseActivity.this.setCartViewVisible(false);
                    return;
                }
                BaseActivity.this.setCartViewVisible(true);
                BaseActivity.this.updateShoppingCart();
            }
        });
    }

    private void a(final int i2) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (i2 <= 0 || BaseActivity.this.mCartView.getVisibility() != 0) {
                    BaseActivity.this.shoppingCountBv.hide();
                    return;
                }
                BaseActivity.this.shoppingCountBv.show();
                BaseActivity.this.shoppingCountBv.setCount(i2);
                BaseActivity.this.shoppingCountBv.invalidate();
            }
        });
    }

    private void b(final int i2) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (i2 <= 0 || BaseActivity.this.mOrderListView.getVisibility() != 0) {
                    BaseActivity.this.orderListCountBv.hide();
                    return;
                }
                BaseActivity.this.orderListCountBv.show();
                BaseActivity.this.orderListCountBv.setCount(i2);
                BaseActivity.this.orderListCountBv.invalidate();
            }
        });
    }

    public int getShoppingCart() {
        return Utils.Preference.getIntPref(this, "pref_key_shoppingcart_number", 0);
    }

    public void updateShoppingCart(int i2) {
        LogUtil.b(b, "update cart:" + i2);
        if (shoppingCartNum != i2) {
            shoppingCartNum = i2;
            Utils.Preference.setIntPref(this, "pref_key_shoppingcart_number", i2);
        }
        a(i2);
    }

    public void saveAndUpdateUnpaidNum(int i2) {
        LogUtil.b(b, "update cart:" + i2);
        if (unpaidNum != i2) {
            unpaidNum = i2;
            Utils.Preference.setIntPref(this, Constants.Prefence.d, i2);
        }
        b(i2);
    }

    public void updateShoppingCart() {
        LogUtil.b(b, "update cart as pref value");
        if (shoppingCartNum == -1) {
            shoppingCartNum = Utils.Preference.getIntPref(this, "pref_key_shoppingcart_number", 0);
        }
        a(shoppingCartNum);
    }

    public void updateUnpaidBadge() {
        LogUtil.b(b, "update cart as pref value");
        if (com.mi.global.shop.xmsf.account.LoginManager.u().x()) {
            if (unpaidNum == -1) {
                unpaidNum = Utils.Preference.getIntPref(this, Constants.Prefence.d, 0);
            }
            b(unpaidNum);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LogUtil.b(b, "onDestroy," + getClass().getName());
        com.mi.global.shop.xmsf.account.LoginManager.u().b((LoginManager.AccountListener) this);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void d() {
        WebViewCookieManager.e(this);
        WebViewCookieManager.d(this);
    }

    public void onLogin(String str, String str2, String str3) {
        LogUtil.b(b, String.format("BaseActivity-onLogin:%s, %s, %s", new Object[]{str, str2, str3}));
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i2, String str4) {
        LogUtil.b(b, String.format("BaseActivity-userinfoupdate:%s, %s, %s,%d", new Object[]{str, str2, str3, Integer.valueOf(i2)}));
        updateShoppingCart(i2);
    }

    public void onLogout() {
        LogUtil.b(b, "logout");
        updateShoppingCart(0);
        saveAndUpdateUnpaidNum(0);
        WebViewCookieManager.b((Context) this);
        WebViewCookieManager.c(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MiShopStatInterface.b();
        LogUtil.b(b, "onPause," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        LogUtil.b(b, "onRestart," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateShoppingCart();
        if (!(this instanceof WebActivity)) {
            MiShopStatInterface.a((Context) this, getClass().getSimpleName());
        }
        LogUtil.b(b, "onResume," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        LogUtil.b(b, "onStart," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        LogUtil.b(b, "onStop," + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.b(b, "onSaveInstanceState , outState:" + bundle);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void refreshFragment() {
        LogUtil.b(b, "refreshFragment");
        List<Fragment> a2 = FragmentUtil.a(getSupportFragmentManager());
        LogUtil.b(b, "Fragments size:" + a2.size());
        for (Fragment next : a2) {
            LogUtil.b(b, "get fragment:" + next.toString());
            if (next instanceof MiFragment) {
                LogUtil.b(b, "refreshFragment:" + next.toString());
                ((MiFragment) next).a();
            }
        }
    }

    public void showPageNotice(NewPageMessage newPageMessage) {
        View findViewById = findViewById(R.id.notice_view);
        if (findViewById != null) {
            if (newPageMessage == null || TextUtils.isEmpty(newPageMessage.pagemsg)) {
                findViewById.setVisibility(8);
                return;
            }
            findViewById.setVisibility(0);
            ((CustomTextView) findViewById.findViewById(R.id.notice_text)).setText(newPageMessage.pagemsg);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById.findViewById(R.id.notice_icon);
            if (TextUtils.isEmpty(newPageMessage.icon)) {
                simpleDraweeView.setVisibility(8);
                return;
            }
            simpleDraweeView.setVisibility(0);
            FrescoUtils.a(newPageMessage.icon, simpleDraweeView);
        }
    }

    public void initProgressDialog() {
        this.n = new ProgressDialog(this);
        this.n.setMessage(getString(R.string.please_wait));
        this.n.setIndeterminate(true);
        this.n.setCancelable(false);
        this.n.setCanceledOnTouchOutside(false);
    }

    public void showLoading() {
        if (isActivityAlive(this)) {
            if (this.n != null) {
                this.n.show();
                return;
            }
            initProgressDialog();
            this.n.show();
        }
    }

    public void hideLoading() {
        if (isActivityAlive(this) && this.n != null && this.n.isShowing()) {
            this.n.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public String getUpdateUrl() {
        return ConnectionHelper.aA();
    }

    public static boolean isActivityAlive(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !((Activity) context).isFinishing();
        }
        Activity activity = (Activity) context;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }

    public void appendOriginalUrl(Uri.Builder builder, Activity activity) {
        String stringExtra = activity.getIntent().getStringExtra(WebActivity.ORIGINAL_URL);
        if (!TextUtils.isEmpty(stringExtra)) {
            builder.appendQueryParameter(WebActivity.ORIGINAL_URL, stringExtra);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 1 || i3 != 0) {
            return;
        }
        if (ShopApp.h.f.equals("community_sdk")) {
            gotoAccount();
        } else {
            ShopApp.e();
        }
    }
}
