package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.audiorecord.AudioRecordActivity;
import com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.plugin.PluginInfoActivity;
import com.xiaomi.smarthome.framework.webview.CommonWebViewActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.scene.geofence.manager.MIUIGeoLogActivity;
import com.xiaomi.smarthome.scene.geofence.manager.SmartFence;
import com.xiaomi.smarthome.scene.geofence.manager.TestMIUIGFActivity;
import com.xiaomi.smarthome.shop.utils.DeviceShopConstants;
import com.xiaomi.voiceassistant.mijava.NLProcessor;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.share.model.ShareChannel;

public class DeveloperSettingActivity extends BaseActivity {
    public static String[] mMiSipSerItems = {"production:42.62.48.115", "stage:58.83.200.17"};
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String[] f16720a = {"标准模式", "本地模式", "rn模式"};
    /* access modifiers changed from: private */
    public String[] b = {"production", "preview", AnalyticsConstants.bH};
    CheckBox mAppConfig;
    CheckBox mCbxDisableLog;
    Context mContext;
    View mHideDeviceEntrance;
    ListItemView mMiBrainEnvLIV;
    TextView mMiBrainEnvTV;
    ListItemView mMiSipEnvLIV;
    TextView mMiSipEnvTV;
    CheckBox mOperationConfig;
    View mPushId;
    TextView mPushIdTextView;
    CheckBox mRemoteDebug;
    CheckBox mRemoteDebugShop;
    SwitchButton mSbCardDebug;
    CheckBox mSpecAppConfig;
    CheckBox mUseOldPlugin;
    View mViewCardDebug;
    SwitchButton sBtn;
    SwitchButton sBtnHideDeviceEntrance;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        this.mContext = this;
        setContentView(R.layout.developer_setting_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingActivity.this.finish();
            }
        });
        findViewById(R.id.sip).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Toast.makeText(SHApplication.getAppContext(), R.string.loggin_first, 0).show();
                }
            }
        });
        findViewById(R.id.rn_package_setting).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingActivity.this.startActivity(new Intent(DeveloperSettingActivity.this.mContext, DevelopSettingRNActivity.class));
            }
        });
        findViewById(R.id.scan_code_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.about_developer);
        this.mRemoteDebug = (CheckBox) findViewById(R.id.remote_debug);
        this.mRemoteDebugShop = (CheckBox) findViewById(R.id.remote_debug_shop);
        this.mAppConfig = (CheckBox) findViewById(R.id.app_config);
        this.mSpecAppConfig = (CheckBox) findViewById(R.id.app_spec_config);
        this.mOperationConfig = (CheckBox) findViewById(R.id.operation_config);
        this.mUseOldPlugin = (CheckBox) findViewById(R.id.use_old_plugin_only);
        this.mCbxDisableLog = (CheckBox) findViewById(R.id.cbx_disable_log);
        if (GlobalSetting.s || GlobalSetting.q) {
            this.mUseOldPlugin.setChecked(PluginSmartHomeApi.a(getContext()));
            this.mUseOldPlugin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    PluginSmartHomeApi.a(DeveloperSettingActivity.this.getContext(), z);
                }
            });
        } else {
            this.mUseOldPlugin.setEnabled(false);
        }
        if (GlobalSetting.C) {
            this.mRemoteDebug.setChecked(true);
        } else {
            this.mRemoteDebug.setChecked(false);
        }
        if (GlobalSetting.E) {
            this.mAppConfig.setChecked(true);
        } else {
            this.mAppConfig.setChecked(false);
        }
        if (GlobalSetting.F) {
            this.mSpecAppConfig.setChecked(true);
        } else {
            this.mSpecAppConfig.setChecked(false);
        }
        GlobalSetting.G = SharePrefsManager.b(getContext(), "dev_pref", "use_stg_operation", false);
        if (GlobalSetting.G) {
            this.mOperationConfig.setChecked(true);
        } else {
            this.mOperationConfig.setChecked(false);
        }
        if (DeviceShopConstants.a()) {
            this.mRemoteDebugShop.setChecked(true);
        } else {
            this.mRemoteDebugShop.setChecked(false);
        }
        this.mRemoteDebug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    GlobalSetting.C = true;
                } else {
                    GlobalSetting.C = false;
                }
                SharePrefsManager.a(DeveloperSettingActivity.this.getContext(), "dev_pref", "use_test_url", GlobalSetting.C);
            }
        });
        this.mCbxDisableLog.setChecked(GlobalSetting.D);
        this.mCbxDisableLog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GlobalSetting.D = z;
                BluetoothContextManager.a(DeveloperSettingActivity.this.getContext(), z);
            }
        });
        this.mAppConfig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SharePrefsManager.a(CommonApplication.getAppContext(), AppConfigHelper.f13358a, AppConfigHelper.b, z);
                GlobalSetting.E = z;
            }
        });
        this.mSpecAppConfig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SharePrefsManager.a(CommonApplication.getAppContext(), AppConfigHelper.f13358a, AppConfigHelper.c, z);
                GlobalSetting.F = z;
            }
        });
        this.mOperationConfig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    GlobalSetting.G = true;
                } else {
                    GlobalSetting.G = false;
                }
                SharePrefsManager.a(DeveloperSettingActivity.this.getContext(), "dev_pref", "use_stg_operation", z);
            }
        });
        this.mRemoteDebugShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeviceShopConstants.a(z);
            }
        });
        this.mPushId = findViewById(R.id.push_id);
        this.mPushId.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("push_id", "pushid:" + SHApplication.getPushManager().g());
            }
        });
        this.mPushIdTextView = (TextView) findViewById(R.id.push_id_tv);
        this.mPushIdTextView.setText(SHApplication.getPushManager().g());
        this.mPushIdTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) DeveloperSettingActivity.this.getSystemService(ShareChannel.d)).setPrimaryClip(ClipData.newPlainText(DeveloperSettingActivity.this.mPushIdTextView.getText(), DeveloperSettingActivity.this.mPushIdTextView.getText()));
                ToastUtil.a((CharSequence) "已经复制:" + DeveloperSettingActivity.this.mPushIdTextView.getText());
            }
        });
        findViewById(R.id.plugin_device).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeveloperSettingActivity.this.mContext, PluginInfoActivity.class);
                intent.putExtra("type", 1);
                DeveloperSettingActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.plugin_package_installed).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeveloperSettingActivity.this.mContext, PluginInfoActivity.class);
                intent.putExtra("type", 2);
                DeveloperSettingActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.plugin_package_downloaded).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeveloperSettingActivity.this.mContext, PluginInfoActivity.class);
                intent.putExtra("type", 3);
                DeveloperSettingActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.shop_api_test).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c("https://home.mi.com/shop/apitest");
            }
        });
        findViewById(R.id.url_test).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(DeveloperSettingActivity.this);
                View inflate = LayoutInflater.from(DeveloperSettingActivity.this).inflate(R.layout.developer_setting_input_dialog, (ViewGroup) null);
                builder.b(inflate).a((CharSequence) "输入URL");
                final EditText editText = (EditText) inflate.findViewById(R.id.url_input);
                builder.a((CharSequence) "打开", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String obj = editText.getText().toString();
                        if (!TextUtils.isEmpty(obj)) {
                            Intent intent = new Intent(SHApplication.getAppContext(), CommonWebViewActivity.class);
                            intent.putExtra("url", obj);
                            intent.putExtra("title", "测试界面");
                            intent.setFlags(C.ENCODING_PCM_MU_LAW);
                            DeveloperSettingActivity.this.startActivity(intent);
                        }
                    }
                });
                builder.b((CharSequence) "取消", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.d();
            }
        });
        findViewById(R.id.operation_jump).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(DeveloperSettingActivity.this);
                View inflate = LayoutInflater.from(DeveloperSettingActivity.this).inflate(R.layout.developer_setting_input_dialog, (ViewGroup) null);
                builder.b(inflate).a((CharSequence) "输入URL");
                final EditText editText = (EditText) inflate.findViewById(R.id.url_input);
                builder.a((CharSequence) "打开", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String obj = editText.getText().toString();
                        if (!TextUtils.isEmpty(obj)) {
                            Intent intent = new Intent(SHApplication.getAppContext(), OperationCommonWebViewActivity.class);
                            intent.putExtra("url", obj);
                            DeveloperSettingActivity.this.startActivity(intent);
                        }
                    }
                });
                builder.b((CharSequence) "取消", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.d();
            }
        });
        findViewById(R.id.oauth_login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginApi.a().a((Activity) DeveloperSettingActivity.this, (int[]) null);
            }
        });
        findViewById(R.id.record_audio).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingActivity.this.startActivity(new Intent(DeveloperSettingActivity.this.mContext, AudioRecordActivity.class));
            }
        });
        this.sBtn = (SwitchButton) findViewById(R.id.update_check);
        this.sBtn.setChecked(DevelopSharePreManager.a().c());
        this.sBtn.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Miio.b("DeveloperSettingActivity", "---" + z);
                DevelopSharePreManager.a().b(z);
            }
        });
        this.mHideDeviceEntrance = findViewById(R.id.layout_hide_device_entrance);
        this.mViewCardDebug = findViewById(R.id.layout_debug_card);
        this.mSbCardDebug = (SwitchButton) findViewById(R.id.sb_debug_card);
        this.mMiBrainEnvLIV = (ListItemView) findViewById(R.id.mi_brain_server_env_liv);
        this.mMiBrainEnvTV = (TextView) findViewById(R.id.mi_brain_selected_server_env);
        this.mMiBrainEnvTV.setText(this.b[NLProcessor.f23141a]);
        this.mMiBrainEnvLIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingActivity.this.b().show();
            }
        });
        this.mMiSipEnvLIV = (ListItemView) findViewById(R.id.mi_sip_server_env_liv);
        this.mMiSipEnvTV = (TextView) findViewById(R.id.mi_sip_selected_server_env);
        this.mMiSipEnvLIV.setVisibility(8);
        this.mMiSipEnvLIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        findViewById(R.id.developer_page_mode).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MLAlertDialog unused = DeveloperSettingActivity.this.a();
            }
        });
        final XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance == null) {
            str = "fail";
            ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
        } else {
            try {
                str = this.f16720a[instance.getPageModel()];
            } catch (Exception unused) {
                str = "fail";
            }
        }
        ((TextView) findViewById(R.id.developer_page_mode_info)).setText(str);
        findViewById(R.id.developer_rn_branch).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingActivity.this.showInputRnBranch();
            }
        });
        setRnBranch();
        findViewById(R.id.developer_weex_branch).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = "";
                if (instance == null) {
                    ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
                    return;
                }
                Object preferenceValue = instance.getPreferenceValue("Dev_WeexBranch", "");
                if (preferenceValue != null && (preferenceValue instanceof String)) {
                    str = (String) preferenceValue;
                }
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(DeveloperSettingActivity.this);
                builder.a((int) R.string.developer_weex_branch);
                builder.a(str, false);
                builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        instance.setPreferenceValue("Dev_WeexBranch", ((MLAlertDialog) dialogInterface).getInputView().getEditableText().toString());
                        DeveloperSettingActivity.this.setWeexBranch();
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
            }
        });
        setWeexBranch();
        findViewById(R.id.developer_weex_time).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = "";
                if (instance == null) {
                    ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
                    return;
                }
                Object preferenceValue = instance.getPreferenceValue("Dev_WeexTime", "");
                if (preferenceValue != null && (preferenceValue instanceof String)) {
                    str = (String) preferenceValue;
                }
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(DeveloperSettingActivity.this);
                builder.a((int) R.string.developer_weex_detect_time);
                builder.a(str, false);
                builder.a().setInputType(2);
                builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        instance.setPreferenceValue("Dev_WeexTime", ((MLAlertDialog) dialogInterface).getInputView().getEditableText().toString());
                        DeveloperSettingActivity.this.setWeexTime();
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
            }
        });
        findViewById(R.id.misc_setting).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeveloperSettingActivity.this, DeveloperSettingMiscActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                DeveloperSettingActivity.this.startActivity(intent);
            }
        });
        setWeexTime();
        findViewById(R.id.test_recommend_scene).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeveloperSettingActivity.this.c(view);
            }
        });
        findViewById(R.id.test_miui_geofence).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeveloperSettingActivity.this.b(view);
            }
        });
        findViewById(R.id.test_miui_geofence_zat).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeveloperSettingActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        startActivity(new Intent(this, MIUIGeoLogActivity.class));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        startActivity(new Intent(this, TestMIUIGFActivity.class));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        startActivity(new Intent(this, SmartFence.class));
    }

    /* access modifiers changed from: package-private */
    public void setRnBranch() {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance == null) {
            ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
            return;
        }
        Object preferenceValue = instance.getPreferenceValue("Dev_RnBranch", "");
        if (preferenceValue != null && (preferenceValue instanceof String)) {
            ((TextView) findViewById(R.id.developer_rn_branch_info)).setText((String) preferenceValue);
        }
    }

    /* access modifiers changed from: package-private */
    public void setWeexBranch() {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance == null) {
            ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
            return;
        }
        Object preferenceValue = instance.getPreferenceValue("Dev_WeexBranch", "");
        if (preferenceValue != null && (preferenceValue instanceof String)) {
            ((TextView) findViewById(R.id.developer_weex_branch_info)).setText((String) preferenceValue);
        }
    }

    /* access modifiers changed from: package-private */
    public void setWeexTime() {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance == null) {
            ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
            return;
        }
        Object preferenceValue = instance.getPreferenceValue("Dev_WeexTime", "");
        if (preferenceValue != null && (preferenceValue instanceof String)) {
            ((TextView) findViewById(R.id.developer_weex_time_info)).setText((String) preferenceValue);
        }
    }

    /* access modifiers changed from: package-private */
    public void showInputRnBranch() {
        String str = "";
        final XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance == null) {
            ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
            return;
        }
        Object preferenceValue = instance.getPreferenceValue("Dev_RnBranch", "");
        if (preferenceValue != null && (preferenceValue instanceof String)) {
            str = (String) preferenceValue;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.developer_rn_branch);
        builder.a(str, false);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                instance.setPreferenceValue("Dev_RnBranch", ((MLAlertDialog) dialogInterface).getInputView().getEditableText().toString());
                LocalBroadcastManager.getInstance(DeveloperSettingActivity.this).sendBroadcast(new Intent("rnbranch_changed"));
                DeveloperSettingActivity.this.setRnBranch();
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.d();
    }

    /* access modifiers changed from: private */
    public MLAlertDialog a() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this.mContext);
        builder.a((int) R.string.developer_page_mode).a((CharSequence[]) this.f16720a, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                XmPluginHostApi instance = XmPluginHostApi.instance();
                if (instance == null) {
                    ToastUtil.a((CharSequence) "XmPluginHostApi is null!");
                    return;
                }
                instance.setPageModel(i);
                ((TextView) DeveloperSettingActivity.this.findViewById(R.id.developer_page_mode_info)).setText(DeveloperSettingActivity.this.f16720a[i]);
                dialogInterface.dismiss();
            }
        });
        return builder.d();
    }

    /* access modifiers changed from: private */
    public MLAlertDialog b() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this.mContext);
        builder.a((int) R.string.mi_brain_server_environment).a((CharSequence[]) this.b, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                NLProcessor.f23141a = i;
                DeveloperSettingActivity.this.mMiBrainEnvTV.setText(DeveloperSettingActivity.this.b[i]);
                dialogInterface.dismiss();
            }
        });
        return builder.b();
    }
}
