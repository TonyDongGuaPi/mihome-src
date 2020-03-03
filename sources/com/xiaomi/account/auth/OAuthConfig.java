package com.xiaomi.account.auth;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.account.openauth.AccountAuth;
import com.xiaomi.account.openauth.AuthorizeActivity;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

public class OAuthConfig {
    private static final char SCOPE_SPLITTOR = ' ';
    final AccountAuth accountAuth;
    final String appId;
    final Class<? extends AuthorizeActivityBase> authorizeActivityClazz;
    final Context context;
    final String deviceID;
    final boolean fastOAuth;
    final Boolean hideSwitch;
    final boolean keepCookies;
    final String loginType;
    final boolean notUseMiui;
    final PhoneInfo phoneInfo;
    final int platform;
    final String redirectUrl;
    final String responseType;
    final String scopes;
    final Boolean skipConfirm;
    final String state;

    public OAuthConfig(Builder builder) {
        this.scopes = makeScopeString(builder.scopes);
        this.notUseMiui = builder.notUseMiui;
        this.appId = builder.appId;
        this.redirectUrl = builder.redirectUrl;
        this.skipConfirm = builder.skipConfirm;
        this.state = builder.state;
        this.keepCookies = builder.keepCookies;
        this.authorizeActivityClazz = builder.authorizeActivityClazz;
        this.accountAuth = builder.accountAuth;
        this.platform = builder.platform;
        this.deviceID = builder.deviceID;
        this.responseType = builder.responseType;
        this.phoneInfo = builder.phoneInfo;
        this.fastOAuth = builder.fastOAuth;
        this.context = builder.context;
        this.loginType = builder.loginType;
        this.hideSwitch = builder.hideSwitch;
    }

    public Bundle makeOptions() {
        Bundle bundle = new Bundle();
        bundle.putString("extra_response_type", this.responseType);
        if (this.skipConfirm != null) {
            bundle.putBoolean("extra_skip_confirm", this.skipConfirm.booleanValue());
        }
        if (!TextUtils.isEmpty(this.state)) {
            bundle.putString(XiaomiOAuthConstants.EXTRA_STATE, this.state);
        }
        if (!TextUtils.isEmpty(this.scopes)) {
            bundle.putString("extra_scope", this.scopes);
        }
        if (!TextUtils.isEmpty(this.deviceID)) {
            bundle.putString("extra_deviceid", this.deviceID);
        }
        bundle.putInt("extra_platform", this.platform);
        bundle.putBoolean("extra_native_oauth", this.fastOAuth);
        if (this.hideSwitch != null) {
            bundle.putBoolean("extra_hide_switch", this.hideSwitch.booleanValue());
        }
        if (!TextUtils.isEmpty(this.loginType)) {
            bundle.putString(XiaomiOAuthConstants.EXTRA_LOGIN_TYPE, this.loginType);
        }
        return bundle;
    }

    private static String makeScopeString(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(i3);
            i++;
            i2 = i4;
        }
        return sb.toString();
    }

    public static class Builder {
        private static final Class<? extends AuthorizeActivityBase> DEFAULT_AUTHORIZE_ACTIVITY_CLASS = AuthorizeActivity.class;
        /* access modifiers changed from: private */
        public AccountAuth accountAuth;
        /* access modifiers changed from: private */
        public String appId = null;
        /* access modifiers changed from: private */
        public Class<? extends AuthorizeActivityBase> authorizeActivityClazz = DEFAULT_AUTHORIZE_ACTIVITY_CLASS;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String deviceID = null;
        /* access modifiers changed from: private */
        public boolean fastOAuth = false;
        /* access modifiers changed from: private */
        public Boolean hideSwitch;
        /* access modifiers changed from: private */
        public boolean keepCookies = false;
        /* access modifiers changed from: private */
        public String loginType;
        /* access modifiers changed from: private */
        public boolean notUseMiui = false;
        /* access modifiers changed from: private */
        public PhoneInfo phoneInfo;
        /* access modifiers changed from: private */
        public int platform = 0;
        /* access modifiers changed from: private */
        public String redirectUrl = null;
        /* access modifiers changed from: private */
        public String responseType = "code";
        /* access modifiers changed from: private */
        public int[] scopes = null;
        /* access modifiers changed from: private */
        public Boolean skipConfirm = false;
        /* access modifiers changed from: private */
        public String state = null;

        public Builder() {
        }

        public Builder(Builder builder) {
            this.notUseMiui = builder.notUseMiui;
            this.scopes = builder.scopes;
            this.appId = builder.appId;
            this.redirectUrl = builder.redirectUrl;
            this.skipConfirm = builder.skipConfirm;
            this.state = builder.state;
            this.keepCookies = builder.keepCookies;
            this.authorizeActivityClazz = builder.authorizeActivityClazz;
            this.accountAuth = builder.accountAuth;
            this.platform = builder.platform;
            this.phoneInfo = builder.phoneInfo;
            this.deviceID = builder.deviceID;
            this.responseType = builder.responseType;
            this.fastOAuth = builder.fastOAuth;
            this.context = builder.context;
            this.loginType = builder.loginType;
            this.hideSwitch = builder.hideSwitch;
        }

        public Builder scopes(int[] iArr) {
            this.scopes = iArr;
            return this;
        }

        public Builder appId(long j) {
            this.appId = String.valueOf(j);
            return this;
        }

        public Builder redirectUrl(String str) {
            this.redirectUrl = str;
            return this;
        }

        public Builder skipConfirm(boolean z) {
            this.skipConfirm = Boolean.valueOf(z);
            return this;
        }

        public Builder notUseMiui(boolean z) {
            this.notUseMiui = z;
            return this;
        }

        public Builder state(String str) {
            this.state = str;
            return this;
        }

        public Builder keepCookies(boolean z) {
            this.keepCookies = z;
            return this;
        }

        public Builder authorizeActivityClazz(Class<? extends AuthorizeActivityBase> cls) {
            this.authorizeActivityClazz = cls;
            return this;
        }

        public Builder accountAuth(AccountAuth accountAuth2) {
            this.accountAuth = accountAuth2;
            return this;
        }

        public Builder platform(int i) {
            this.platform = i;
            return this;
        }

        public Builder phoneInfo(PhoneInfo phoneInfo2) {
            this.phoneInfo = phoneInfo2;
            return this;
        }

        public Builder deviceID(String str) {
            this.deviceID = str;
            return this;
        }

        public Builder responseType(String str) {
            this.responseType = str;
            return this;
        }

        public Builder context(Context context2) {
            this.context = context2.getApplicationContext();
            return this;
        }

        public Context getContext() {
            return this.context;
        }

        public String getAppId() {
            return this.appId;
        }

        public Builder fastOAuth(boolean z) {
            this.fastOAuth = z;
            return this;
        }

        public Builder loginType(String str) {
            this.loginType = str;
            return this;
        }

        public Builder hideSwitch(boolean z) {
            this.hideSwitch = Boolean.valueOf(z);
            return this;
        }

        public OAuthConfig build() {
            return new OAuthConfig(this);
        }
    }
}
