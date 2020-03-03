package com.xiaomi.pluginhost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.plugin.IXmPluginHostActivity;
import com.xiaomi.plugin.XmPluginBaseActivity;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.plugin.XmPluginPackage;
import com.xiaomi.youpin.log.LogUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PluginHostActivity extends FragmentActivity implements IXmPluginHostActivity {
    public static final String EXTRA_CLASS = "class:";
    public static final String EXTRA_PACKAGE_PATH = "packagepth:";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    public static final String TAG = "PluginHostActivity";

    /* renamed from: a  reason: collision with root package name */
    private static ArrayList<WeakReference<PluginHostActivity>> f12588a = new ArrayList<>();
    private static Handler b = null;
    private HostInfo c = null;
    private XmPluginBaseActivity d;
    private int e;
    private String f;
    boolean mIsReady = true;
    XmPluginPackage mLoadedInfo;
    List<Runnable> mOnReadyRunableList = new ArrayList();
    Resources.Theme mTheme;

    public static class HostInfo {

        /* renamed from: a  reason: collision with root package name */
        String f12598a;
        String b;
        String c;
    }

    public final FragmentActivity activity() {
        return this;
    }

    protected static Handler getHandler() {
        if (b == null) {
            b = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message message) {
                    int i = message.what;
                }
            };
        }
        return b;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }

    private static synchronized void a(PluginHostActivity pluginHostActivity) {
        synchronized (PluginHostActivity.class) {
            if (pluginHostActivity != null) {
                f12588a.add(new WeakReference(pluginHostActivity));
            }
        }
    }

    public static synchronized PluginHostActivity getTopPluginHostActivity() {
        synchronized (PluginHostActivity.class) {
            if (f12588a.size() <= 0) {
                return null;
            }
            PluginHostActivity pluginHostActivity = (PluginHostActivity) f12588a.get(f12588a.size() - 1).get();
            return pluginHostActivity;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void b(com.xiaomi.pluginhost.PluginHostActivity r3) {
        /*
            java.lang.Class<com.xiaomi.pluginhost.PluginHostActivity> r0 = com.xiaomi.pluginhost.PluginHostActivity.class
            monitor-enter(r0)
            if (r3 != 0) goto L_0x0007
            monitor-exit(r0)
            return
        L_0x0007:
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.pluginhost.PluginHostActivity>> r1 = f12588a     // Catch:{ all -> 0x002a }
            int r1 = r1.size()     // Catch:{ all -> 0x002a }
            int r1 = r1 + -1
        L_0x000f:
            if (r1 < 0) goto L_0x0028
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.pluginhost.PluginHostActivity>> r2 = f12588a     // Catch:{ all -> 0x002a }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x002a }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x002a }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x002a }
            if (r2 != r3) goto L_0x0025
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.pluginhost.PluginHostActivity>> r3 = f12588a     // Catch:{ all -> 0x002a }
            r3.remove(r1)     // Catch:{ all -> 0x002a }
            goto L_0x0028
        L_0x0025:
            int r1 = r1 + -1
            goto L_0x000f
        L_0x0028:
            monitor-exit(r0)
            return
        L_0x002a:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.pluginhost.PluginHostActivity.b(com.xiaomi.pluginhost.PluginHostActivity):void");
    }

    public static synchronized void resetPluginHostActivity() {
        synchronized (PluginHostActivity.class) {
            f12588a.clear();
        }
    }

    public XmPluginPackage getXmPluginPackage() {
        return this.mLoadedInfo;
    }

    public Resources getResources() {
        if (this.mLoadedInfo == null) {
            return super.getResources();
        }
        return this.mLoadedInfo.resources;
    }

    public AssetManager getAssets() {
        if (this.mLoadedInfo == null) {
            return super.getAssets();
        }
        return this.mLoadedInfo.assetManager;
    }

    public Resources.Theme getTheme() {
        if (this.mTheme == null) {
            return super.getTheme();
        }
        return this.mTheme;
    }

    public ClassLoader getClassLoader() {
        if (this.mLoadedInfo == null) {
            return super.getClassLoader();
        }
        return this.mLoadedInfo.classLoader;
    }

    public void onRestoreInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onRestoreInstanceState(bundle, persistableBundle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.remove(FRAGMENTS_TAG);
        }
        super.onCreate(bundle);
        requestWindowFeature(1);
        XmPluginHostApi.instance().enableBlackTranslucentStatus(getWindow());
        AppInitialApi.a().b(getApplicationContext());
        this.mIsReady = true;
        doCreate(bundle);
        XmPluginHostApi.instance().onActivityCreate(this);
    }

    /* access modifiers changed from: package-private */
    public void doCreate(@Nullable Bundle bundle) {
        Intent intent = getIntent();
        Object lastCustomNonConfigurationInstance = getLastCustomNonConfigurationInstance();
        if (lastCustomNonConfigurationInstance != null) {
            this.c = (HostInfo) lastCustomNonConfigurationInstance;
        }
        if (this.c == null) {
            this.c = new HostInfo();
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                for (String next : categories) {
                    if (next.startsWith(EXTRA_CLASS)) {
                        this.c.f12598a = next.substring(EXTRA_CLASS.length());
                    } else if (next.startsWith(EXTRA_PACKAGE_PATH)) {
                        this.c.b = next.substring(EXTRA_PACKAGE_PATH.length());
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(this.c.b)) {
            this.mLoadedInfo = PluginRuntimeManager.a().b(this.c.b);
        }
        if (this.mLoadedInfo == null) {
            this.c.c = intent.getDataString();
            if (!TextUtils.isEmpty(this.c.c)) {
                this.mLoadedInfo = PluginRuntimeManager.a().a(this.c.c);
                this.c.b = this.mLoadedInfo.packagePath;
            }
        }
        if (this.mLoadedInfo == null) {
            LogUtils.e("PluginHostActivity", "PluginHostActivity onCreate mLoadedInfo is null");
            setResult(0);
            finish();
            return;
        }
        intent.setExtrasClassLoader(this.mLoadedInfo.classLoader);
        if (TextUtils.isEmpty(this.c.f12598a)) {
            this.c.f12598a = intent.getStringExtra("extra_class");
        }
        if (TextUtils.isEmpty(this.c.f12598a)) {
            LogUtils.e("PluginHostActivity", "PluginHostActivity onCreate mClass is null");
            setResult(0);
            finish();
            return;
        }
        if (!(bundle == null || this.mLoadedInfo.xmPluginMessageReceiver == null)) {
            this.mLoadedInfo.xmPluginMessageReceiver.handleMessage(getApplicationContext(), this.mLoadedInfo, -1, (Intent) null);
        }
        PluginRuntimeManager.a(this.mLoadedInfo);
        handleActivityInfo();
        a(intent);
        a(this);
        if (intent != null) {
            long currentTimeMillis = System.currentTimeMillis();
            LogUtils.d("DebugTime", "LaunchPluginActivityCreateTime:" + (currentTimeMillis - intent.getLongExtra("__StartTime__", currentTimeMillis)));
        }
    }

    /* access modifiers changed from: protected */
    public void handleActivityInfo() {
        Resources.Theme theme = super.getTheme();
        this.mTheme = this.mLoadedInfo.resources.newTheme();
        this.mTheme.setTo(theme);
        try {
            this.mTheme.applyStyle(Build.VERSION.SDK_INT >= 14 ? 16974120 : 16973829, true);
        } catch (Exception unused) {
        }
    }

    public XmPluginBaseActivity pluginBaseActivity() {
        return this.d;
    }

    public static void addWatermark(Activity activity) {
        if (XmPluginHostApi.instance().isDevMode()) {
            TextView textView = new TextView(activity);
            textView.setText("本地页面");
            textView.setTextColor(ColorStateList.valueOf(251658240));
            textView.setTextSize(1, 30.0f);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            ((FrameLayout) activity.findViewById(16908290)).addView(textView, layoutParams);
        }
    }

    private void a(Intent intent) {
        if (this.mLoadedInfo != null) {
            try {
                this.d = (XmPluginBaseActivity) this.mLoadedInfo.classLoader.loadClass(this.c.f12598a).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.d.attach(this, this.mLoadedInfo);
                this.d.setIntent(intent);
                this.d.onCreate(intent.getExtras());
                addWatermark(this.d);
            } catch (Throwable th) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, th);
                finish();
            }
        } else {
            try {
                this.d = (XmPluginBaseActivity) getClassLoader().loadClass(this.c.f12598a).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.d.attach(this, this.mLoadedInfo);
                this.d.setIntent(intent);
                this.d.onCreate(intent.getExtras());
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void recreate() {
        super.recreate();
        if (this.mIsReady) {
            doRecreate();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doRecreate();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doRecreate() {
        if (this.d != null) {
            try {
                this.d.recreate();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        if (this.mIsReady) {
            doPostCreate(bundle);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doPostCreate(bundle);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doPostCreate(Bundle bundle) {
        if (this.d != null) {
            try {
                this.d.onPostCreate(bundle);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.mIsReady) {
            doStart();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doStart();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doStart() {
        if (this.d != null) {
            try {
                this.d.onStart();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public String getPageName() {
        return this.d != null ? this.d.getClass().getName() : "";
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mIsReady) {
            doResume();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doResume();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doResume() {
        this.e = (int) (System.currentTimeMillis() / 1000);
        this.f = getPageName();
        if (TextUtils.isEmpty(this.f)) {
            this.f = getClass().getName();
        }
        if (this.d != null) {
            try {
                this.d.onResume();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        try {
            super.onPostResume();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.mIsReady) {
            doPosResume();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doPosResume();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doPosResume() {
        if (this.d != null) {
            try {
                this.d.onPostResume();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.d != null) {
            try {
                this.d.onPause();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.d != null) {
            try {
                this.d.onStop();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        b(this);
        if (this.d != null) {
            try {
                this.d.onDestroy();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
        if (!(getWindow() == null || getWindow().getDecorView() == null || getWindow().getDecorView().getHandler() == null)) {
            getWindow().getDecorView().getHandler().removeCallbacksAndMessages((Object) null);
        }
        ViewUtils.a((Activity) this);
        XmPluginHostApi.instance().onActivityDestroy(this);
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return this.c;
    }

    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onSaveInstanceState(bundle, persistableBundle);
        if (this.d != null) {
            try {
                this.d.onSaveInstanceState(bundle);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(final Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mIsReady) {
            doRestoreInstanceState(bundle);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doRestoreInstanceState(bundle);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doRestoreInstanceState(Bundle bundle) {
        if (this.d != null) {
            try {
                this.d.onRestoreInstanceState(bundle);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(final int i, final int i2, final Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mIsReady) {
            doActivityResult(i, i2, intent);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doActivityResult(i, i2, intent);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doActivityResult(int i, int i2, Intent intent) {
        if (this.d != null) {
            try {
                this.d.onActivityResult(i, i2, intent);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onRequestPermissionsResult(final int i, @NonNull final String[] strArr, @NonNull final int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (this.mIsReady) {
            doRequestPermissionsResult(i, strArr, iArr);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doRequestPermissionsResult(i, strArr, iArr);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.d != null) {
            try {
                this.d.onRequestPermissionsResult(i, strArr, iArr);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (this.mIsReady) {
            doNewIntent(intent);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doNewIntent(intent);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doNewIntent(Intent intent) {
        if (this.d != null) {
            try {
                this.d.onNewIntent(intent);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onBackPressed() {
        if (this.d != null) {
            try {
                this.d.onBackPressed();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d != null) {
            try {
                if (this.d.onTouchEvent(motionEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.d != null) {
            try {
                if (this.d.dispatchTouchEvent(motionEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.d != null) {
            try {
                if (this.d.onKeyDown(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.d != null) {
            try {
                if (this.d.onKeyUp(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (this.d != null) {
            try {
                if (this.d.onKeyLongPress(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onKeyLongPress(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (this.d != null) {
            try {
                if (this.d.onKeyMultiple(i, i2, keyEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (this.d != null) {
            try {
                if (this.d.onKeyShortcut(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        if (this.d != null) {
            try {
                this.d.onWindowAttributesChanged(layoutParams);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return;
            }
        }
        super.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        if (this.d != null) {
            try {
                this.d.onWindowFocusChanged(z);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return;
            }
        }
        super.onWindowFocusChanged(z);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.d != null) {
            try {
                this.d.onCreateOptionsMenu(menu);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.d != null) {
            try {
                this.d.onOptionsItemSelected(menuItem);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onContentChanged() {
        if (this.d != null) {
            try {
                this.d.onContentChanged();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d != null) {
            try {
                this.d.onAttachedToWindow();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            try {
                this.d.onDetachedFromWindow();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.d != null) {
            try {
                this.d.onLowMemory();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (this.d != null) {
            try {
                this.d.onTrimMemory(i);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.d != null) {
            try {
                if (this.d.onTrackballEvent(motionEvent)) {
                    return true;
                }
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
                return false;
            }
        }
        return super.onTrackballEvent(motionEvent);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        if (this.d != null) {
            try {
                this.d.onUserInteraction();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (this.d != null) {
            try {
                this.d.onUserLeaveHint();
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.d != null) {
            try {
                this.d.onConfigurationChanged(configuration);
            } catch (Exception e2) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e2);
                finish();
            }
        }
    }

    public static void startActivityForResult(Context context, XmPluginPackage xmPluginPackage, Intent intent, Class<? extends XmPluginBaseActivity> cls, int i) {
        Intent intent2 = new Intent(XmPluginHostApi.instance().context(), PluginRuntimeManager.a().b(intent, xmPluginPackage.packagePath));
        intent2.addCategory(EXTRA_PACKAGE_PATH + xmPluginPackage.packagePath);
        intent2.addCategory(EXTRA_CLASS + cls.getName());
        if (intent != null) {
            intent2.setData(intent.getData());
            intent2.putExtras(intent);
            intent2.setFlags(intent.getFlags());
        }
        if (context instanceof XmPluginBaseActivity) {
            ((XmPluginBaseActivity) context).activity().startActivityForResult(intent2, i);
        } else if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent2, i);
        } else {
            intent2.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent2);
        }
    }

    @NonNull
    public LayoutInflater getLayoutInflater() {
        if (this.d != null) {
            return this.d.getLayoutInflater();
        }
        return super.getLayoutInflater();
    }
}
