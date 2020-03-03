package com.xiaomi.mishopsdk.plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.activity.BaseActivity;
import com.xiaomi.mishopsdk.plugin.Model.PluginRuntimeEnv;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.widget.LoadingView;
import com.xiaomi.mobilestats.StatService;
import java.util.HashMap;

public class BasePluginActivity extends BaseActivity {
    private static final String TAG = "BasePluginActivity";
    protected boolean mIsLoading = false;
    protected boolean mIsThemeInstalled = false;
    protected LoadingView mLoadingView;
    protected PluginInfo mPluginInfo;
    FrameLayout viewRoot;

    /* access modifiers changed from: protected */
    public boolean needFitsSystemWindows() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        this.mIsThemeInstalled = false;
        Bundle bundle2 = null;
        if (bundle == null || !bundle.containsKey(Constants.Plugin.ARGUMENT_PLUGININFO)) {
            super.onCreate(bundle);
            Intent intent = getIntent();
            if (intent != null) {
                bundle2 = intent.getExtras();
            }
            if (bundle2 == null || !bundle2.containsKey(Constants.Plugin.ARGUMENT_PLUGININFO)) {
                this.mPluginInfo = new PluginInfo();
            } else {
                this.mPluginInfo = (PluginInfo) bundle2.getSerializable(Constants.Plugin.ARGUMENT_PLUGININFO);
            }
        } else {
            super.onCreate((Bundle) null);
            this.mPluginInfo = (PluginInfo) bundle.getSerializable(Constants.Plugin.ARGUMENT_PLUGININFO);
            if (!TextUtils.isEmpty(this.mPluginInfo.id)) {
                installRunEnv(this.mPluginInfo);
            }
        }
        if (Log.isDebug()) {
            logActivityInfo();
        }
        setContentView(getContentView());
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public View getContentView() {
        this.viewRoot = new FrameLayout(this);
        if (Build.VERSION.SDK_INT >= 14 && needFitsSystemWindows()) {
            this.viewRoot.setFitsSystemWindows(true);
        }
        this.viewRoot.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.viewRoot.setId(16908300);
        this.mLoadingView = new LoadingView(this);
        this.mLoadingView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.viewRoot.addView(this.mLoadingView);
        return this.viewRoot;
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
            this.mLoadingView.startLoading(true, true);
        }
    }

    /* access modifiers changed from: protected */
    public void stopLoading() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.viewRoot != null) {
            this.viewRoot.removeView(this.mLoadingView);
        }
    }

    /* access modifiers changed from: protected */
    public void setErrorTip(String str, String str2, Handler.Callback callback) {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
            this.mLoadingView.showErrorWithActionButton(str, str2, callback);
        }
    }

    /* access modifiers changed from: protected */
    public void setErrorTip(String str) {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
            this.mLoadingView.showErrorFormally(str);
        }
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if ("fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        PluginRuntimeEnv pluginRunEnv = getPluginRunEnv();
        if (pluginRunEnv == null) {
            return null;
        }
        return pluginRunEnv.pluginLayoutInflaterFactory.onCreateView(str, context, attributeSet);
    }

    public AssetManager getAssets() {
        PluginRuntimeEnv pluginRunEnv = getPluginRunEnv();
        if (pluginRunEnv != null && this.mIsThemeInstalled) {
            return pluginRunEnv.pluginAsset;
        }
        Activity parent = getParent();
        if (parent != null) {
            return parent.getAssets();
        }
        return super.getAssets();
    }

    public Resources getResources() {
        PluginRuntimeEnv pluginRunEnv = getPluginRunEnv();
        if (pluginRunEnv != null && this.mIsThemeInstalled) {
            return pluginRunEnv.pluginRes;
        }
        Activity parent = getParent();
        if (parent != null) {
            return parent.getResources();
        }
        return super.getResources();
    }

    public ClassLoader getClassLoader() {
        if (getPluginRunEnv() == null) {
            return super.getClassLoader();
        }
        return getPluginRunEnv().pluginClassLoader;
    }

    public Resources.Theme getTheme() {
        PluginRuntimeEnv pluginRunEnv = getPluginRunEnv();
        if (pluginRunEnv != null && this.mIsThemeInstalled) {
            return pluginRunEnv.pluginTheme;
        }
        Activity parent = getParent();
        if (parent != null) {
            return parent.getTheme();
        }
        return super.getTheme();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mPluginInfo != null) {
            bundle.putSerializable(Constants.Plugin.ARGUMENT_PLUGININFO, this.mPluginInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mPluginInfo == null) {
            this.mPluginInfo = (PluginInfo) bundle.getSerializable(Constants.Plugin.ARGUMENT_PLUGININFO);
            installRunEnv(this.mPluginInfo);
        }
    }

    /* access modifiers changed from: protected */
    public PluginRuntimeEnv getPluginRunEnv() {
        PluginRuntimeEnv pluginRuntimeEnv;
        if (this.mPluginInfo == null || (pluginRuntimeEnv = ShopApp.getInstance().getPluginRuntimeEnv(this.mPluginInfo)) == null || pluginRuntimeEnv.pluginAsset == null || pluginRuntimeEnv.pluginClassLoader == null || pluginRuntimeEnv.pluginRes == null || pluginRuntimeEnv.pluginTheme == null) {
            return null;
        }
        return pluginRuntimeEnv;
    }

    /* access modifiers changed from: protected */
    public boolean installRunEnv(final PluginInfo pluginInfo) {
        try {
            PluginRuntimeEnv pluginRuntimeEnv = ShopApp.getInstance().getPluginRuntimeEnv(pluginInfo);
            if (pluginRuntimeEnv == null) {
                pluginRuntimeEnv = PluginInstallUtils.installRunEnv(pluginInfo, this);
                ShopApp.getInstance().addPluginRuntime(pluginRuntimeEnv);
            }
            if (pluginRuntimeEnv == null) {
                return false;
            }
            this.mIsThemeInstalled = true;
            return true;
        } catch (Exception e) {
            StatService.onError(ShopApp.instance, e, new HashMap<String, String>() {
                {
                    put("pId", pluginInfo.id);
                    put("md5", pluginInfo.md5);
                    put("ext_functionType", "installEunEnVError");
                }
            });
            setErrorTip("页面加载失败", "马上重试", new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    BasePluginActivity.this.startLoading();
                    PluginSyncUtils.getInstance().pullSinglePluginVersion(pluginInfo.id);
                    return false;
                }
            });
            e.printStackTrace();
            return false;
        } catch (Throwable th) {
            Exception exc = new Exception(th.getMessage());
            exc.setStackTrace(th.getStackTrace());
            StatService.onError(ShopApp.instance, exc, new HashMap<String, String>() {
                {
                    put("pId", pluginInfo.id);
                    put("md5", pluginInfo.md5);
                    put("ext_functionType", "installEunEnVError");
                }
            });
            setErrorTip("页面加载失败", "马上重试", new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    BasePluginActivity.this.startLoading();
                    PluginSyncUtils.getInstance().pullSinglePluginVersion(pluginInfo.id);
                    return false;
                }
            });
            exc.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(16908300);
        if (findFragmentById != null) {
            findFragmentById.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public String getFragmentClass() {
        Uri data;
        Intent intent = getIntent();
        if (intent == null || (data = intent.getData()) == null) {
            return null;
        }
        return data.getHost();
    }

    private void logActivityInfo() {
        android.util.Log.d("MishopLog_BasePluginActivity", "\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("小米商城打开一个页面,dump信息如下：\n");
        sb.append("\tpluginId=");
        sb.append(this.mPluginInfo.id);
        sb.append("\n");
        sb.append("\tpluginRoot=");
        sb.append(this.mPluginInfo.rootFragment);
        sb.append("\n");
        sb.append("\tpluginVersion=");
        sb.append(this.mPluginInfo.version);
        sb.append("\n");
        sb.append("\tpluginType=");
        sb.append(this.mPluginInfo.type);
        sb.append("\n");
        sb.append("\tpluginMd5=");
        sb.append(this.mPluginInfo.md5);
        sb.append("\n");
        sb.append("\tpluginPath=");
        sb.append(this.mPluginInfo.path);
        sb.append("\n");
        sb.append("\tintent中的参数如下：");
        sb.append("\n");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            for (String str : extras.keySet()) {
                sb.append("\t\t");
                sb.append("key=");
                sb.append(str);
                sb.append(", value=");
                sb.append(extras.get(str));
                sb.append("\n");
            }
        }
        sb.append("dump结束!\n\n");
        Log.d(TAG, sb.toString());
    }
}
