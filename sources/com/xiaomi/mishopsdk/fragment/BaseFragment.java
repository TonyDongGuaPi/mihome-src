package com.xiaomi.mishopsdk.fragment;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.sdk.util.i;
import com.mishopsdk.volley.Response;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.ExtendedAuthToken;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.activity.BaseActivity;
import com.xiaomi.mishopsdk.event.RelogoutEvent;
import com.xiaomi.mishopsdk.event.UpdateCountEvent;
import com.xiaomi.mishopsdk.fragment.BasePluginFragment;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.NetworkUtil;
import com.xiaomi.mishopsdk.util.ToastUtil;
import com.xiaomi.mishopsdk.widget.BadgeView;
import com.xiaomi.mishopsdk.widget.CommonAlertDialog;
import com.xiaomi.mishopsdk.widget.PermissionTipsDialog;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.util.Device;
import com.xiaomi.shop2.util.DeviceUtil;
import com.xiaomi.shop2.util.PermissionUtil;
import de.greenrobot.event.EventBus;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseFragment extends Fragment implements LoginManager.AccountListener {
    public static final String[] APPCOOKIE = {"hd.mi.com", "s1.mi.com", "m.mi.com"};
    public static final String COOKIE_KEY_C_USER_ID = "cUserId";
    public static final String COOKIE_KEY_D_SID = "mishop_dSid";
    public static final String COOKIE_KEY_D_TOKEN = "mishop_dToken";
    public static final String COOKIE_KEY_PASS_TOKEN = "passToken";
    public static final String COOKIE_KEY_SERVICE_TOKEN = "serviceToken";
    public static final String COOKIE_KEY_USER_ID = "userId";
    public static final String COOKIE_NAME_PLATFORM = "Android_native";
    public static final String COOKIE_VALUE_PLATFROM = "platform";
    public static final String DOMAIN_ACCOUNT = "account.xiaomi.com";
    public static final String DOMAIN_APP_SHOPAPI = "app.shopapi.xiaomi.com";
    public static final String DOMAIN_BASE = "xiaomi.com";
    public static final String DOMAIN_BASE2 = "mi.com";
    public static final String DOMAIN_T_HD = "t.hd.xiaomi.com";
    private static final String TAG = "BaseFragment";
    private static final String THIRD_PARTY_ID_COOKIE_NAME = "masid";
    protected static boolean mIsAfterMiui6 = isAfterMiuiV6();
    public static LoginNoticeWebCallback mListener;
    protected static PermissionTipsDialog mPermissionTipsDialog;
    protected Account mAccount;
    protected View mBaseFragmentView;
    protected View mCartCenter;
    protected BadgeView mCartCount;
    protected View mCartView;
    protected Handler mHandler;
    protected CommonAlertDialog mLoginDialog;
    protected boolean mNetworkConnected;
    private NetworkConnectivityChangedReceiver mNetworkConnectivityReceiver;
    protected View mNotchBar;
    private ProgressDialog mProgressDialog;
    protected TextView mTitle;
    protected View mTitleBar;
    protected ImageView mTitleLeft;
    protected View mTitleRootView;
    protected DialogInterface.OnDismissListener onDialogDismiss = new DialogInterface.OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            if (BaseFragment.mListener != null) {
                BaseFragment.mListener.noticeWebView(0);
            }
        }
    };

    public interface LoginNoticeWebCallback {
        void noticeWebView(int i);
    }

    private void showLocalLogin() {
    }

    /* access modifiers changed from: protected */
    public abstract View createContentView(LayoutInflater layoutInflater, Bundle bundle);

    public void gotoLogin() {
    }

    public void onInvalidAuthonToken() {
    }

    /* access modifiers changed from: protected */
    public void onNetworkDisConnected() {
    }

    public abstract void reload(int i);

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        registerConnectivityReceiver();
        this.mNetworkConnected = NetworkUtil.isNetWorkConnected(getActivity());
    }

    @Deprecated
    public boolean checkPermission(String str, int i) {
        return PermissionUtil.checkPermission((Fragment) this, str, i);
    }

    @Deprecated
    public boolean checkPermissions(String[] strArr, int i) {
        return PermissionUtil.checkPermissions((Fragment) this, strArr, i);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        FragmentActivity activity = getActivity();
        if (activity != null && isAdded() && !activity.isFinishing()) {
            PermissionUtil.onRequestPermissionsResult(this, i, iArr);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        View inflate = layoutInflater.inflate(getLayoutResId(), viewGroup, false);
        this.mBaseFragmentView = inflate;
        this.mTitleBar = inflate.findViewById(R.id.mishopsdk_basefragment_titlebar);
        this.mTitleRootView = inflate.findViewById(R.id.mishopsdk_basetitle_layoutcenter);
        this.mCartView = inflate.findViewById(R.id.mishopsdk_basetitle_layoutright_cartview);
        this.mCartCenter = inflate.findViewById(R.id.mishopsdk_basetitle_layoutright_cartcenter);
        if (this.mCartView != null) {
            this.mCartView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    StatService.onEvent(BaseFragment.this.getActivity(), "20000000110001006", "");
                    if (LoginManager.getInstance().hasLogin()) {
                        BaseFragment.this.gotoShoppingCart();
                        return;
                    }
                    ToastUtil.show(ShopApp.instance.getString(R.string.mishopsdk_login_before_check_shopping_cart));
                    BaseFragment.this.gotoAccount();
                }
            });
            this.mCartView.setContentDescription("购物车");
        }
        this.mCartCount = (BadgeView) inflate.findViewById(R.id.mishopsdk_basetitle_layoutright_txtcartcount);
        this.mTitle = (TextView) inflate.findViewById(R.id.mishopsdk_basetitle_layoutcenter_title);
        this.mTitleLeft = (ImageView) inflate.findViewById(R.id.mishopsdk_basetitle_iconleft);
        if (this.mTitleLeft != null) {
            this.mTitleLeft.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseFragment.this.getActivity().onBackPressed();
                }
            });
            this.mTitleLeft.setContentDescription(InifraredContants.w);
        }
        View createContentView = createContentView(layoutInflater, bundle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        if (createContentView != null) {
            ((LinearLayout) inflate).addView(createContentView, layoutParams);
        }
        View findViewById = inflate.findViewById(R.id.mishopsdk_basefragment_content);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        LoginManager.getInstance().addLoginListener(this);
        this.mHandler = new Handler(Looper.getMainLooper());
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void initHomeButton() {
        if (Device.MISHOP_SDK_VERSION < 20161201) {
            Log.d(TAG, "SDK not support home button. initHomeButton return.");
        } else if (!"true".equalsIgnoreCase(ShopApp.getInstance().getSdkProperty(Constants.SdkSettings.KEY_HOME_BTN_VISIBLE, "true"))) {
            Log.d(TAG, "SDK home switch closed. initHomeButton return.");
        } else if (this.mTitleBar == null) {
            Log.e(TAG, "titlebar is not initialized, initHomeButton return.");
        } else {
            View findViewById = this.mTitleBar.findViewById(R.id.mishopsdk_basetitle_homeview);
            if (findViewById == null) {
                Log.e(TAG, "did not found homeView, initHomeButton return.");
                return;
            }
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseFragment.this.getActivity().finish();
                    BasePluginFragment.Fasade.startNewPluginActivity(BaseFragment.this.getActivity(), "100", new Bundle());
                }
            });
            findViewById.setVisibility(0);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        AndroidUtil.runOnUIThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                if (BaseFragment.this.getView() != null && BaseFragment.this.getView().getParent() != null && Build.VERSION.SDK_INT >= 14) {
                    String sdkProperty = ShopApp.getInstance().getSdkProperty(Constants.SdkSettings.KEY_ENABLE_TINT, Constants.SdkSettings.VALUE_TINT_MIUI6_ENABLE);
                    if ((Constants.SdkSettings.VALUE_TINT_MIUI6_ENABLE.equalsIgnoreCase(sdkProperty) && BaseFragment.mIsAfterMiui6) || Constants.SdkSettings.VALUE_TINT_ALL_ENABLE.equalsIgnoreCase(sdkProperty)) {
                        ((ViewGroup) BaseFragment.this.getView().getParent()).setFitsSystemWindows(true);
                        if (BaseFragment.mIsAfterMiui6) {
                            BaseFragment.this.setMiui6StatusBarDarkMode(true, BaseFragment.this.getActivity());
                        }
                        ((BaseActivity) BaseFragment.this.getActivity()).mSystemBarTintManager.setTintColor(BaseFragment.this.getResources().getColor(BaseFragment.this.getTintColor()));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public int getTintColor() {
        int i = R.color.mishopsdk_bg_grey_state;
        return (Build.VERSION.SDK_INT < 24 || !getActivity().isInMultiWindowMode()) ? i : R.color.mishopsdk_transparent;
    }

    protected static boolean isAfterMiuiV6() {
        String miuiVersion = Device.getMiuiVersion();
        try {
            if (Integer.valueOf(miuiVersion).intValue() >= 6) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.d(TAG, "getMiuiVersion failed. miuiVer=%s", miuiVersion, e);
            return false;
        }
    }

    public void setMiui6StatusBarDarkMode(boolean z, Activity activity) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Window window2 = activity.getWindow();
            View decorView = window2.getDecorView();
            window2.addFlags(Integer.MIN_VALUE);
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & -8193);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.mishopsdk_base_fragment;
    }

    /* access modifiers changed from: protected */
    public void gotoShoppingCart() {
        Intent intent = new Intent();
        intent.putExtra(Constants.Plugin.ARGUMENT_PLUGINID, "102");
        intent.putExtras(new Bundle());
        intent.setAction(Constants.Plugin.ACTION_ROOT);
        intent.setPackage(Constants.REAL_PACKAGE_NAME);
        getActivity().startActivity(intent);
    }

    public void setLeftView(int i, View.OnClickListener onClickListener) {
        if (this.mTitleLeft != null) {
            this.mTitleLeft.setImageResource(i);
            this.mTitleLeft.setOnClickListener(onClickListener);
        }
    }

    public void setCenterView(View view) {
        LinearLayout linearLayout = (LinearLayout) this.mTitleBar.findViewById(R.id.mishopsdk_basetitle_layoutcenter);
        linearLayout.setGravity(16);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view, new LinearLayout.LayoutParams(-1, -2));
    }

    public void setRightView(View view) {
        LinearLayout linearLayout = (LinearLayout) this.mTitleBar.findViewById(R.id.mishopsdk_basetitle_layoutright);
        linearLayout.setGravity(16);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
    }

    public void addRightView(View view) {
        LinearLayout linearLayout = (LinearLayout) this.mTitleBar.findViewById(R.id.mishopsdk_basetitle_layoutright);
        linearLayout.setGravity(16);
        linearLayout.addView(view, 0);
    }

    public class CartCount {
        public int result;

        public CartCount() {
        }
    }

    public void onEvent(UpdateCountEvent updateCountEvent) {
        updateShoppingCountView();
    }

    public void onEvent(RelogoutEvent relogoutEvent) {
        long j = relogoutEvent.time;
        if (j - ShopApp.invalidate_event_time > 10000) {
            ShopApp.invalidate_event_time = j;
            if (LoginManager.getInstance().hasLogin()) {
                AndroidUtil.runOnUIThread(new Runnable() {
                    public void run() {
                        LoginManager.getInstance().logout();
                        FragmentActivity activity = BaseFragment.this.getActivity();
                        if (activity != null && BaseFragment.this.isAdded()) {
                            activity.finish();
                        }
                        BaseFragment.this.gotoAccount();
                    }
                });
            }
        }
    }

    public void gotoAccount() {
        AndroidUtil.runOnUIThread(new Runnable() {
            public void run() {
                if (!BaseFragment.this.isAdded()) {
                    return;
                }
                if (LoginManager.iShopAccountManager == null) {
                    Log.d(BaseFragment.TAG, "login failed, the iShopAccountManager is null.");
                } else {
                    LoginManager.iShopAccountManager.gotoAccount(BaseFragment.this.getActivity());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void updateShoppingCountView() {
        if (this.mCartCount != null) {
            if (ShopApp.sShoppingCount > 0) {
                setBadgeCount(this.mCartCount, ShopApp.sShoppingCount);
            } else {
                this.mCartCount.setVisibility(8);
            }
            if (this.mTitleBar.getVisibility() != 0) {
                this.mCartCount.setVisibility(8);
            }
        }
    }

    private void setBadgeCount(BadgeView badgeView, int i) {
        if (badgeView != null) {
            badgeView.setCount(i);
        }
    }

    public void highlightShoppingCount() {
        if (this.mCartCount != null && this.mCartCount.isShown()) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(300);
            scaleAnimation.setFillAfter(true);
            ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation2.setInterpolator(new OvershootInterpolator());
            scaleAnimation2.setDuration(200);
            scaleAnimation2.setStartOffset(300);
            scaleAnimation2.setFillAfter(true);
            AnimationSet animationSet = new AnimationSet(false);
            animationSet.setFillAfter(false);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(scaleAnimation2);
            this.mCartCount.startAnimation(animationSet);
        }
    }

    public void onLogin(String str, String str2, String str3) {
        ((BaseActivity) getActivity()).updateShoppingCount();
    }

    public void onLogout() {
        ((BaseActivity) getActivity()).updateShoppingCount(-1);
    }

    public void onResume() {
        super.onResume();
        hideLoginDialogifLogined();
    }

    public void onDestroy() {
        super.onDestroy();
        RequestQueueManager.getInstance().clearRequest(this);
        LoginManager.getInstance().removeLoginListener(this);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onDetach() {
        super.onDetach();
        unregisterConnectivityReceiver();
    }

    /* access modifiers changed from: protected */
    public void hideLoginDialogifLogined() {
        if (this.mLoginDialog != null && LoginManager.getInstance().hasLogin()) {
            this.mLoginDialog.dismiss();
        }
    }

    public static void setOnLoginNoticeWebCallback(LoginNoticeWebCallback loginNoticeWebCallback) {
        mListener = loginNoticeWebCallback;
    }

    public static void setThirdPartyId(Context context, String str) {
        setCookie(context, THIRD_PARTY_ID_COOKIE_NAME, str);
    }

    public static void removeThirdPartyId(Context context) {
        removeCookie(context, THIRD_PARTY_ID_COOKIE_NAME);
    }

    public static void setCookie(Context context, String str, String str2) {
        setCookie(context, str, str2, "xiaomi.com");
        setCookie(context, str, str2, "mi.com");
    }

    public static void removeCookie(Context context, String str) {
        removeCookie(context, str, "xiaomi.com");
        removeCookie(context, str, "mi.com");
    }

    public static void setCookie(Context context, String str, String str2, String str3) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        if (instance != null) {
            instance.setCookie(str3, str + "=" + str2 + "; domain=" + str3);
            CookieSyncManager.getInstance().sync();
        }
    }

    public static void removeCookie(Context context, String str, String str2) {
        CookieManager instance = CookieManager.getInstance();
        CookieSyncManager.createInstance(context);
        if (instance != null) {
            String cookie = instance.getCookie(str2);
            if (!TextUtils.isEmpty(cookie)) {
                String[] split = cookie.split(i.b);
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String[] split2 = split[i].split("=");
                    if (split2.length >= 2) {
                        if (TextUtils.equals(split2[0].trim(), str)) {
                            instance.setCookie(str2, str + "=;domain=" + str2 + ";expires=-1");
                            CookieSyncManager.getInstance().sync();
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static void setLoginCookies(final Context context) {
        AndroidUtil.sStageQueue.postRunnable(new Runnable() {
            public void run() {
                String str;
                ShopApp.getInstance().waitForInitAsync();
                LoginManager instance = LoginManager.getInstance();
                if (instance.hasLogin()) {
                    if (!TextUtils.isEmpty(instance.getPrefEncryptedUserId())) {
                        BaseFragment.setCookie(context, "cUserId", instance.getPrefEncryptedUserId());
                    } else {
                        BaseFragment.setCookie(context, "userId", instance.getAccountId());
                    }
                    if (instance.getExtendedAuthToken("eshopmobile") == null) {
                        str = null;
                    } else {
                        str = instance.getExtendedAuthToken("eshopmobile").authToken;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        BaseFragment.setCookie(context, "serviceToken", URLEncoder.encode(str), BaseFragment.DOMAIN_APP_SHOPAPI);
                    }
                    BaseFragment.setWebRequiredServiceTokens(context);
                    BaseFragment.setSecurityInfoCookie();
                }
            }
        });
    }

    public static void setWebCookieWithApp(Context context) {
        for (String str : APPCOOKIE) {
            try {
                setCookie(context, COOKIE_KEY_D_TOKEN, DeviceUtil.getDToken(), str);
                setCookie(context, COOKIE_KEY_D_SID, DeviceUtil.getDSid(), str);
            } catch (Throwable th) {
                Exception exc = new Exception(th.getMessage());
                exc.setStackTrace(th.getStackTrace());
                StatService.onError(ShopApp.instance, exc, new HashMap<String, String>() {
                    {
                        put("setWebCookieWithApp", "so load failed");
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setWebRequiredServiceTokens(Context context) {
        LoginManager instance = LoginManager.getInstance();
        setServiceTokenCookies(context, instance.getWebRequiredCachedServiceTokens(), false);
        setServiceTokenCookies(context, instance.getWebRequiredServiceTokens(), true);
    }

    private static void setServiceTokenCookies(Context context, Map<String, ExtendedAuthToken> map, boolean z) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                ExtendedAuthToken extendedAuthToken = (ExtendedAuthToken) next.getValue();
                String str = extendedAuthToken != null ? extendedAuthToken.authToken : null;
                if (!TextUtils.isEmpty(str)) {
                    if (((String) next.getKey()).equals("wap.phonerecharge.pay.xiaomi.com")) {
                        setCookie(context, "serviceToken", str, (String) next.getKey());
                    } else {
                        setCookie(context, "serviceToken", URLEncoder.encode(str), (String) next.getKey());
                    }
                }
            }
            if (z && mListener != null) {
                mListener.noticeWebView(1);
            }
        } else if (z && mListener != null) {
            mListener.noticeWebView(0);
        }
    }

    public static void removeLoginCookies(Context context) {
        AndroidUtil.runOnUIThread(new Runnable() {
            public void run() {
                CookieManager.getInstance().removeSessionCookie();
            }
        });
        removeCookie(context, "serviceToken", DOMAIN_T_HD);
        CookieSyncManager.getInstance().sync();
        initSettingCookies(context);
    }

    public static void initSettingCookies(Context context) {
        setCookie(context, "Android_native", "platform");
        setWebCookieWithApp(context);
    }

    public static void setSecurityInfoCookie() {
        HashMap hashMap = new HashMap();
        hashMap.put("user_id", "1");
        RequestQueueManager instance = RequestQueueManager.getInstance();
        instance.postApiRequest((Object) "securityInfo", HostManager.FORMAL_DOMAIN_APP_SHOPAPI_HTTPS + "user/securityInfo", (HashMap<String, String>) hashMap, JSONArray.class, false, new Response.SimpleListener<JSONArray>() {
            public void onSuccess(JSONArray jSONArray, boolean z) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    BaseFragment.setCookie(ShopApp.instance, optJSONObject.optString("key"), optJSONObject.optString("value"), optJSONObject.optString("domain"));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onNetworkConnected(int i) {
        reload(i);
    }

    private void registerConnectivityReceiver() {
        if (this.mNetworkConnectivityReceiver == null) {
            this.mNetworkConnectivityReceiver = new NetworkConnectivityChangedReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getActivity().registerReceiver(this.mNetworkConnectivityReceiver, intentFilter);
    }

    private void unregisterConnectivityReceiver() {
        getActivity().unregisterReceiver(this.mNetworkConnectivityReceiver);
    }

    private class NetworkConnectivityChangedReceiver extends BroadcastReceiver {
        private NetworkConnectivityChangedReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            boolean isNetWorkConnected = NetworkUtil.isNetWorkConnected(context);
            if (!BaseFragment.this.mNetworkConnected && isNetWorkConnected) {
                BaseFragment.this.onNetworkConnected(NetworkUtil.getActiveNetworkType(context));
            } else if (BaseFragment.this.mNetworkConnected && !isNetWorkConnected) {
                BaseFragment.this.onNetworkDisConnected();
            }
            BaseFragment.this.mNetworkConnected = isNetWorkConnected;
        }
    }

    public FragmentManager getSupportFragmentManager() {
        if (getActivity() == null) {
            return null;
        }
        return getActivity().getSupportFragmentManager();
    }

    public ProgressDialog getProgressDialog() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(getActivity());
            this.mProgressDialog.setCancelable(false);
        }
        return this.mProgressDialog;
    }
}
