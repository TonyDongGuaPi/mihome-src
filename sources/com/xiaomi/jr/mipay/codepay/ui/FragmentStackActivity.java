package com.xiaomi.jr.mipay.codepay.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.miui.supportlite.app.Activity;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.mipay.codepay.util.CodePayUtils;
import java.util.ArrayList;

public class FragmentStackActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10932a = "FragmentStackActivity";
    private ArrayList<BaseFragment> b = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = getIntent().getDataString();
        }
        if (TextUtils.isEmpty(stringExtra)) {
            Utils.b(getApplicationContext(), "url empty");
            finish();
        } else if (!XiaomiAccountManager.a().d()) {
            Utils.b(getApplicationContext(), "has not login");
            finish();
        } else {
            Fragment a2 = CodePayUtils.a(stringExtra);
            if (a2 != null) {
                a2.setArguments(getIntent().getExtras());
                startFragment(a2);
                return;
            }
            Context applicationContext = getApplicationContext();
            Utils.b(applicationContext, "no fragment matches " + stringExtra);
            finish();
        }
    }

    public void onBackPressed() {
        finishFragment();
    }

    public void startFragment(Fragment fragment) {
        if (fragment instanceof BaseFragment) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            try {
                BaseFragment a2 = a();
                if (a2 != null) {
                    beginTransaction.hide(a2);
                }
                beginTransaction.add(16908290, fragment, fragment.toString());
                beginTransaction.commitAllowingStateLoss();
                this.b.add((BaseFragment) fragment);
            } catch (Exception e) {
                MifiLog.e(f10932a, "start fragment fail. " + e.getMessage());
            }
        }
    }

    public void finishFragment() {
        BaseFragment a2 = a();
        if (a2 == null) {
            finish();
            return;
        }
        BaseFragment a3 = a(-2);
        if (a3 == null) {
            setResult(a2.f(), a2.g());
            finish();
            return;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        try {
            beginTransaction.remove(a2);
            beginTransaction.show(a3);
            beginTransaction.commitAllowingStateLoss();
            this.b.remove(this.b.size() - 1);
        } catch (Exception e) {
            MifiLog.e(f10932a, "start fragment fail. " + e.getMessage());
        }
        a3.onActivityResult(a2.e(), a2.f(), a2.g());
    }

    private BaseFragment a() {
        return a(-1);
    }

    private BaseFragment a(int i) {
        int size = this.b.size() + i;
        if (size >= this.b.size() || size < 0) {
            return null;
        }
        return this.b.get(size);
    }
}
