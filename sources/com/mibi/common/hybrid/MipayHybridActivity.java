package com.mibi.common.hybrid;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.mibi.common.R;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.PermissionUtils;

public class MipayHybridActivity extends BaseActivity {
    public static final String EXTRA_URL = "com.miui.sdk.hybrid.extra.URL";

    /* renamed from: a  reason: collision with root package name */
    private static final String f7569a = "MipayHybridActivity";
    private static final int b = 1;
    private MipayHybridFragment c;
    private MenuData[] d;
    private OptionsItemSelectedListener e;

    public static class MenuData {

        /* renamed from: a  reason: collision with root package name */
        int f7570a;
        String b;
    }

    public interface OptionsItemSelectedListener {
        void a(int i);
    }

    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
        super.handleCreate(bundle);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(getContentViewResource());
        this.c = (MipayHybridFragment) getFragmentManager().findFragmentById(R.id.hybrid_fragment);
        requestPermissions(PermissionUtils.f7535a);
    }

    /* access modifiers changed from: protected */
    public int getContentViewResource() {
        return R.layout.mibi_hybrid_activity_default;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.d != null) {
            for (MenuData menuData : this.d) {
                menu.add(0, menuData.f7570a, 0, menuData.b);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public boolean doOptionsItemSelected(MenuItem menuItem) {
        if (this.d == null) {
            return super.doOptionsItemSelected(menuItem);
        }
        for (MenuData menuData : this.d) {
            if (menuItem.getGroupId() == 0 && menuData.f7570a == menuItem.getItemId() && this.e != null) {
                this.e.a(menuData.f7570a);
            }
        }
        return true;
    }

    public void setupMenu(MenuData[] menuDataArr, OptionsItemSelectedListener optionsItemSelectedListener) {
        this.d = menuDataArr;
        this.e = optionsItemSelectedListener;
        setImmersionMenuEnabled(true);
    }

    public void clearMenu() {
        this.d = null;
        this.e = null;
        setImmersionMenuEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void requestPermissions(String... strArr) {
        String[] b2 = PermissionUtils.b(this, strArr);
        if (b2 != null) {
            ActivityCompat.requestPermissions(this, b2, 1);
        } else {
            onPermissionGranted();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (CommonConstants.b) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                Log.d(f7569a, "permission result: " + strArr[i2] + " " + iArr[i2]);
            }
        }
        if (PermissionUtils.a(iArr)) {
            onPermissionGranted();
        } else {
            onPermissionDenied();
        }
    }

    /* access modifiers changed from: protected */
    public void onPermissionGranted() {
        this.c.c();
    }

    /* access modifiers changed from: protected */
    public void onPermissionDenied() {
        Log.d(f7569a, "user not granted permissions");
        finish();
    }

    public void doActivityResult(int i, int i2, Intent intent) {
        super.doActivityResult(i, i2, intent);
        this.c.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.c.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
