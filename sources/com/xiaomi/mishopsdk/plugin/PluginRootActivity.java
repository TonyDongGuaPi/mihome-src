package com.xiaomi.mishopsdk.plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.SdkUtils;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.plugin.Model.PluginSyncInfo;
import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.utils.StringUtils;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.util.Device;
import de.greenrobot.event.EventBus;
import java.util.HashMap;

public class PluginRootActivity extends BasePluginActivity {
    private static final String MISHOP_APP_PACKAGE_NAME = "com.xiaomi.shop";
    private final int REQUESTCODE_NETSETTING = 100;
    String mForceVersion = null;
    protected boolean mIsInstalledPlugin = false;
    String mPluginId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.mPluginId = getIntent().getStringExtra(Constants.Plugin.ARGUMENT_PLUGINID);
            this.mForceVersion = getIntent().getStringExtra(Constants.Plugin.ARGUMENT_FORCEVRESION);
        } else {
            this.mPluginId = bundle.getString(Constants.Plugin.ARGUMENT_PLUGINID);
            this.mForceVersion = bundle.getString(Constants.Plugin.ARGUMENT_FORCEVRESION);
        }
        if (ShopApp.isMijiaMode) {
            this.mPluginId = SdkUtils.mapMijiaPluginId(this.mPluginId);
        }
        if (StringUtils.isEmpty(this.mPluginId)) {
            setErrorTip(ShopApp.instance.getResources().getString(R.string.mishopsdk_error_server));
        } else {
            syncPluginById(this.mPluginId);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mPluginInfo != null) {
            bundle.putString(Constants.Plugin.ARGUMENT_PLUGINID, this.mPluginId);
            bundle.putString(Constants.Plugin.ARGUMENT_FORCEVRESION, this.mForceVersion);
        }
    }

    /* access modifiers changed from: protected */
    public void syncPluginById(final String str) {
        PluginSyncInfo pluginSyncInfo = PluginSyncManager.getInstance().getPluginSyncInfo(this, str, this.mForceVersion);
        if (pluginSyncInfo == null || !pluginSyncInfo.status.equals(PluginSyncManager.SyncStatus.waiting)) {
            syncPlugin(pluginSyncInfo);
            return;
        }
        startLoading();
        new Handler().post(new Runnable() {
            public void run() {
                PluginSyncManager.getInstance().checkPluginSyncInfo(str);
            }
        });
    }

    public void onEventMainThread(PluginSyncInfo pluginSyncInfo) {
        if (!this.mIsInstalledPlugin) {
            syncPlugin(pluginSyncInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void syncPlugin(PluginSyncInfo pluginSyncInfo) {
        if (!this.mIsInstalledPlugin) {
            if (pluginSyncInfo == null) {
                setErrorTip(ShopApp.instance.getResources().getString(R.string.mishopsdk_plugin_error));
                return;
            }
            if (this.mPluginInfo == null) {
                this.mPluginInfo = new PluginInfo();
            }
            if (pluginSyncInfo != null && !this.mPluginId.equals(pluginSyncInfo.pluginId)) {
                return;
            }
            if (PluginSyncManager.SyncStatus.waiting == pluginSyncInfo.status) {
                startLoading();
            } else if (PluginSyncManager.SyncStatus.synced == pluginSyncInfo.status) {
                this.mPluginInfo.deepCopy(pluginSyncInfo.pluginInfo);
                if (installRunEnv(this.mPluginInfo)) {
                    this.mIsThemeInstalled = true;
                    installPluginFragment();
                    return;
                }
                setErrorTip(ShopApp.instance.getResources().getString(R.string.mishopsdk_plugin_error));
            } else if (PluginSyncManager.SyncStatus.waittimeout == pluginSyncInfo.status || PluginSyncManager.SyncStatus.neterror == pluginSyncInfo.status) {
                setErrorTip(ShopApp.instance.getResources().getString(R.string.mishopsdk_network_errortip_server), ShopApp.instance.getResources().getString(R.string.mishopsdk_network_errorbtntip_server), new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        PluginRootActivity.this.syncPluginById(PluginRootActivity.this.mPluginId);
                        return true;
                    }
                });
            } else if (PluginSyncManager.SyncStatus.discard == pluginSyncInfo.status) {
                setErrorTip(ShopApp.instance.getResources().getString(R.string.mishopsdk_plugin_error));
            } else if (PluginSyncManager.SyncStatus.netdisconnetct == pluginSyncInfo.status) {
                setErrorTip(ShopApp.instance.getResources().getString(R.string.mishopsdk_network_errortip_noconnection), ShopApp.instance.getResources().getString(R.string.mishopsdk_network_errorbtntip_noconnection), new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        PluginRootActivity.this.startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 100);
                        return true;
                    }
                });
            } else if (PluginSyncManager.SyncStatus.error == pluginSyncInfo.status) {
                goToAppWhenPluginError(pluginSyncInfo);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void installPluginFragment() {
        try {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
            if (!isFinishing() && !this.mIsInstalledPlugin) {
                String hostFragment = getHostFragment();
                if (StringUtils.isEmpty(hostFragment)) {
                    hostFragment = this.mPluginInfo.rootFragment;
                }
                Fragment fragment = (Fragment) getClassLoader().loadClass(hostFragment).newInstance();
                Bundle extras = getIntent().getExtras();
                extras.putSerializable(Constants.Plugin.ARGUMENT_PLUGININFO, this.mPluginInfo);
                fragment.setArguments(extras);
                this.mIsLoading = false;
                getSupportFragmentManager().beginTransaction().replace(16908300, fragment).commitAllowingStateLoss();
                this.mIsInstalledPlugin = true;
                stopLoading();
            }
        } catch (Exception e) {
            e.printStackTrace();
            StatService.onError(ShopApp.instance, e, new HashMap<String, String>() {
                {
                    if (PluginRootActivity.this.mPluginInfo != null) {
                        put("pId", PluginRootActivity.this.mPluginInfo.id == null ? "" : PluginRootActivity.this.mPluginInfo.id);
                        put("md5", PluginRootActivity.this.mPluginInfo.md5 == null ? "" : PluginRootActivity.this.mPluginInfo.md5);
                    }
                    put("ext_functionType", "installPluginFragment");
                }
            });
            setErrorTip("页面加载失败", "马上重试", new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    PluginRootActivity.this.startLoading();
                    PluginSyncUtils.getInstance().pullSinglePluginVersion(PluginRootActivity.this.mPluginId);
                    return false;
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        EventBus.getDefault().register(this);
        super.startLoading();
    }

    /* access modifiers changed from: protected */
    public String getHostFragment() {
        if (getIntent() == null || getIntent().getData() == null || getIntent().getData().getHost() == null) {
            return null;
        }
        return getIntent().getData().getHost();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (!this.mIsThemeInstalled && i == 100) {
            syncPluginById(this.mPluginId);
        }
    }

    private void goToAppWhenPluginError(PluginSyncInfo pluginSyncInfo) {
        finish();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        boolean z = false;
        if (!TextUtils.isEmpty(pluginSyncInfo.pluginId) && PluginInstallUtils.pluginIsSupportedByApp(pluginSyncInfo.pluginId)) {
            Intent intent = getIntent();
            intent.setAction("com.xiaomi.shop2.pluginRoot");
            intent.putExtra("app_finish", true);
            intent.setPackage("com.xiaomi.shop");
            intent.setComponent(new ComponentName("com.xiaomi.shop", "com.xiaomi.shop2.plugin.PluginRootActivity"));
            if (Device.isIntentAvailable(intent)) {
                startActivity(intent);
                z = true;
            }
        }
        if (!z) {
            go2NoPluginPage(pluginSyncInfo);
        }
    }

    private void go2NoPluginPage(PluginSyncInfo pluginSyncInfo) {
        Intent intent = new Intent();
        if (!(pluginSyncInfo == null || pluginSyncInfo.pluginInfo == null || TextUtils.isEmpty(pluginSyncInfo.pluginInfo.title))) {
            intent.putExtra("title", pluginSyncInfo.pluginInfo.title);
        }
        intent.putExtra(Constants.Plugin.ARGUMENT_PLUGINID, "10005");
        intent.setAction("com.xiaomi.mishopsdk.pluginRoot");
        intent.setPackage(Device.PACKAGE);
        intent.setComponent(new ComponentName(Device.PACKAGE, "com.xiaomi.mishopsdk.plugin.PluginRootActivity"));
        startActivity(intent);
    }
}
