package com.mibi.common.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import com.mibi.common.data.CommonConstants;
import com.mipay.core.runtime.BundleActivity;
import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.Assert;

public class StepActivity extends BundleActivity {
    static final int ACTIVITY_REQUEST_CODE_FRAGMENT = 100000;
    static final int ACTIVITY_RESULT_CODE_FRAGMENT_OK = 100001;
    static final int ACTIVITY_RESULT_CODE_JUMP_BACK = 999999;

    /* renamed from: a  reason: collision with root package name */
    private static final String f7468a = "StepActivity";
    private static final String g = "fragment_info";
    private Class<? extends StepFragment> b;
    private Bundle c;
    private String d;
    private int e;
    private String f;
    FragmentStack mFragmentStack;

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void doFragmentResult(int i, int i2, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void doJumpBackResult(int i, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void doNewIntent(Intent intent) {
    }

    /* access modifiers changed from: protected */
    public boolean doOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void doPause() {
    }

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void doRestoreInstanceState(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void doResume() {
    }

    /* access modifiers changed from: protected */
    public void doSaveInstanceState(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void doStart() {
    }

    /* access modifiers changed from: protected */
    public void doStop() {
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        doPreCreate(bundle);
        if (isFinishing()) {
            super.onCreate(bundle);
            return;
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("fragment");
        if (TextUtils.isEmpty(stringExtra)) {
            this.b = null;
        } else {
            try {
                this.b = Class.forName(stringExtra);
            } catch (Exception e2) {
                throw new IllegalStateException("fragment class to load has error", e2);
            }
        }
        this.c = intent.getBundleExtra("payment_fragment_arguments");
        this.d = intent.getStringExtra(CommonConstants.ay);
        this.e = intent.getIntExtra(CommonConstants.az, -1);
        this.f = intent.getStringExtra(CommonConstants.ax);
        super.onCreate(bundle);
        a(bundle);
        doCreate(bundle);
        if (bundle == null && this.b != null) {
            this.mFragmentStack.a(this.b, this.c, this.e, this.d, true, this.f);
        }
    }

    /* access modifiers changed from: protected */
    public void initFragment(Class<? extends StepFragment> cls, Bundle bundle, String str) {
        this.b = cls;
        this.c = bundle;
        this.f = str;
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            if (CommonConstants.b) {
                Log.v(f7468a, "restoring fragment stack");
            }
            this.mFragmentStack = (FragmentStack) bundle.getParcelable(g);
            this.mFragmentStack.b(getFragmentManager());
            this.mFragmentStack.a(this);
            return;
        }
        if (CommonConstants.b) {
            Log.v(f7468a, "building fragment stack");
        }
        this.mFragmentStack = new FragmentStack();
        this.mFragmentStack.a(this);
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (CommonConstants.b) {
            Log.v(f7468a, "saving fragment stack");
        }
        this.mFragmentStack.a(getFragmentManager());
        bundle.putParcelable(g, this.mFragmentStack);
        doSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        doRestoreInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!this.mFragmentStack.a()) {
            ((StepFragment) getFragmentManager().findFragmentByTag(this.mFragmentStack.b().f7458a)).a(intent);
            return;
        }
        doNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public final void onStart() {
        super.onStart();
        doStart();
    }

    /* access modifiers changed from: protected */
    public final void onResume() {
        super.onResume();
        doResume();
    }

    /* access modifiers changed from: protected */
    public final void onPause() {
        doPause();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public final void onStop() {
        doStop();
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        doDestroy();
        super.onDestroy();
    }

    public final void onBackPressed() {
        if (!this.mFragmentStack.a()) {
            this.mFragmentStack.d();
        } else {
            doBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void doBackPressed() {
        finish();
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (!this.mFragmentStack.a()) {
            return this.mFragmentStack.a(menuItem);
        }
        return doOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: package-private */
    public void addFlagForFragment(StepFragment stepFragment, String str) {
        this.mFragmentStack.a(stepFragment, str);
    }

    public void startFragment(Class<? extends StepFragment> cls, Bundle bundle, String str, Class<? extends StepActivity> cls2) {
        Assert.assertNotNull(cls2);
        startFragmentForResult((StepFragment) null, cls, bundle, -1, str, cls2);
    }

    /* access modifiers changed from: package-private */
    public void startFragmentForResult(StepFragment stepFragment, Class<? extends StepFragment> cls, Bundle bundle, int i, String str, Class<? extends StepActivity> cls2) {
        String tag;
        Assert.assertNotNull(cls);
        if (stepFragment == null) {
            tag = null;
        } else {
            tag = stepFragment.getTag();
        }
        String str2 = tag;
        if (cls2 != null) {
            Intent intent = new Intent(this, cls2);
            intent.putExtra("fragment", cls.getName());
            intent.putExtra("payment_fragment_arguments", bundle);
            intent.putExtra(CommonConstants.ay, str2);
            intent.putExtra(CommonConstants.az, i);
            intent.putExtra(CommonConstants.ax, str);
            startActivityForResult(intent, 100000);
            return;
        }
        this.mFragmentStack.a(cls, bundle, i, str2, false, str);
    }

    /* access modifiers changed from: package-private */
    public void finishFragment(StepFragment stepFragment) {
        this.mFragmentStack.a(stepFragment);
    }

    /* access modifiers changed from: package-private */
    public void finishFragmentJumpBack(String str, boolean z) {
        this.mFragmentStack.a(str, z);
    }

    /* access modifiers changed from: package-private */
    public void close(ArrayList<ResultInfo> arrayList) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(CommonConstants.aA, arrayList);
        setResult(100001, intent);
        finish();
    }

    /* access modifiers changed from: package-private */
    public void closeJumpBack(ArrayList<ResultInfo> arrayList, JumpBackResultInfo jumpBackResultInfo, boolean z) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(CommonConstants.aA, arrayList);
        intent.putExtra(CommonConstants.aB, jumpBackResultInfo);
        intent.putExtra(CommonConstants.aC, z);
        setResult(ACTIVITY_RESULT_CODE_JUMP_BACK, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        Fragment findFragmentByTag;
        super.onActivityResult(i, i2, intent);
        boolean z = false;
        boolean z2 = i == 100000;
        if (i2 == 100001 || i2 == ACTIVITY_RESULT_CODE_JUMP_BACK) {
            z = true;
        }
        if (z2) {
            if (z) {
                Iterator it = intent.getParcelableArrayListExtra(CommonConstants.aA).iterator();
                while (it.hasNext()) {
                    ResultInfo resultInfo = (ResultInfo) it.next();
                    String str = resultInfo.f7467a;
                    if (TextUtils.isEmpty(str) || (findFragmentByTag = getFragmentManager().findFragmentByTag(str)) == null) {
                        doFragmentResult(resultInfo.b, resultInfo.c, resultInfo.d);
                    } else {
                        ((StepFragment) findFragmentByTag).a(resultInfo.b, resultInfo.c, resultInfo.d);
                    }
                }
                if (i2 == ACTIVITY_RESULT_CODE_JUMP_BACK) {
                    this.mFragmentStack.a((JumpBackResultInfo) intent.getParcelableExtra(CommonConstants.aB), intent.getBooleanExtra(CommonConstants.aC, true));
                }
            }
        } else if (z) {
            Iterator it2 = intent.getParcelableArrayListExtra(CommonConstants.aA).iterator();
            while (it2.hasNext()) {
                ResultInfo resultInfo2 = (ResultInfo) it2.next();
                Intent intent2 = null;
                if (resultInfo2.d != null) {
                    intent2 = new Intent();
                    intent2.putExtras(resultInfo2.d);
                }
                doActivityResult(i, resultInfo2.c, intent2);
            }
            if (i2 == ACTIVITY_RESULT_CODE_JUMP_BACK) {
                this.mFragmentStack.a((JumpBackResultInfo) intent.getParcelableExtra(CommonConstants.aB), intent.getBooleanExtra(CommonConstants.aC, true));
            }
        } else {
            doActivityResult(i, i2, intent);
        }
    }
}
