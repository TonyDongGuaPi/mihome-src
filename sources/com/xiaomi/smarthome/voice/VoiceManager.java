package com.xiaomi.smarthome.voice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.media.AudioRecord;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alipay.sdk.util.i;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MenuDialog;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.ArrayList;
import java.util.List;

public class VoiceManager {

    /* renamed from: a  reason: collision with root package name */
    public static int f22796a = 1;
    public static int b = 44100;
    public static int c = 12;
    public static int d = 2;
    public static int e = 0;
    /* access modifiers changed from: private */
    public static String h = "is_open";
    private static String i = "voice_type";
    private static volatile VoiceManager j = null;
    private static final String k = "VoiceManager";
    private static final String l = "com.xiaomi.smarthome.voice.smarthomevoicelauncher";
    private static final long m = 604800000;
    /* access modifiers changed from: private */
    public boolean f;
    private String g;

    public enum VoiceType {
        MiBrain(StatUtil.f),
        Micro(StatUtil.g);
        
        public String type;

        private VoiceType(String str) {
            this.type = str;
        }
    }

    private VoiceManager() {
        this.f = true;
        this.g = VoiceType.MiBrain.type;
        this.g = VoiceType.MiBrain.type;
        if (CoreApi.a().l()) {
            String m2 = AccountManager.a().m();
            if (!TextUtils.isEmpty(m2)) {
                this.f = PreferenceUtils.a(h + MD5.d(m2), true);
                return;
            }
            return;
        }
        IntentFilter intentFilter = new IntentFilter(CoreHostApiImpl.e);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                LoginMiAccount y = CoreApi.a().y();
                if (y != null && !TextUtils.isEmpty(y.a())) {
                    VoiceManager voiceManager = VoiceManager.this;
                    boolean unused = voiceManager.f = PreferenceUtils.a(VoiceManager.h + MD5.d(y.a()), true);
                }
            }
        }, intentFilter);
    }

    public static VoiceManager a() {
        if (j == null) {
            synchronized (VoiceManager.class) {
                if (j == null) {
                    j = new VoiceManager();
                }
            }
        }
        return j;
    }

    public void a(boolean z, String str) {
        this.f = z;
        if (!TextUtils.isEmpty(str)) {
            this.g = str;
        }
        String m2 = AccountManager.a().m();
        if (!TextUtils.isEmpty(m2)) {
            PreferenceUtils.b(h + MD5.d(m2), z);
            PreferenceUtils.b(i + MD5.d(m2), this.g);
        }
    }

    public void a(AsyncCallback asyncCallback) {
        if (asyncCallback != null) {
            asyncCallback.onSuccess(Boolean.valueOf(this.f));
        }
    }

    public void b(final AsyncCallback asyncCallback) {
        UserConfig userConfig = new UserConfig();
        userConfig.B = 0;
        userConfig.C = "8";
        UserConfigApi.a().a(SHApplication.getAppContext(), 0, new String[]{"8"}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<UserConfig> arrayList) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(Boolean.valueOf(VoiceManager.this.b()));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public boolean b() {
        String m2 = AccountManager.a().m();
        if (TextUtils.isEmpty(m2)) {
            return false;
        }
        this.f = PreferenceUtils.a(h + MD5.d(m2), true);
        return this.f;
    }

    public String c() {
        return VoiceType.MiBrain.type;
    }

    public void d() {
        j = null;
    }

    public boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            try {
                e = 0;
                e = AudioRecord.getMinBufferSize(b, c, d);
                AudioRecord audioRecord = new AudioRecord(f22796a, b, c, d, e);
                audioRecord.startRecording();
                if (audioRecord.getRecordingState() != 3) {
                    return false;
                }
                audioRecord.stop();
                audioRecord.release();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } else if (context.checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean e() {
        return PreferenceUtils.a("voice_show_point_v3", true);
    }

    public void f() {
        PreferenceUtils.b("voice_show_point_v3", false);
    }

    public boolean g() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 != null) {
            for (int i2 = 0; i2 < d2.size(); i2++) {
                Device device = d2.get(i2);
                if (device != null && device.voiceCtrl > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean h() {
        return PreferenceUtils.a("voice_show_setting_main_point", true);
    }

    public void i() {
        PreferenceUtils.b("voice_show_setting_main_point", false);
    }

    public static void a(Activity activity) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", activity.getString(R.string.voice_shotcut));
        Intent intent2 = new Intent(l);
        intent2.setComponent(new ComponentName(activity.getPackageName(), SmartHomeVoiceLauncherActivity.class.getName()));
        intent.putExtra("duplicate", false);
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        if (ApiHelper.c) {
            ShortcutManager shortcutManager = (ShortcutManager) SHApplication.getAppContext().getSystemService(ShortcutManager.class);
            if (shortcutManager.isRequestPinShortcutSupported()) {
                shortcutManager.requestPinShortcut(new ShortcutInfo.Builder(activity, activity.getString(R.string.voice_shotcut)).setIcon(Icon.createWithResource(activity, R.drawable.voice_short_cut_icon)).setShortLabel(activity.getString(R.string.voice_shotcut)).setIntent(intent2).build(), (IntentSender) null);
            } else {
                intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(activity, R.drawable.lite_scene_home));
                activity.sendBroadcast(intent);
            }
        } else {
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(activity, R.drawable.voice_short_cut_icon));
            activity.sendBroadcast(intent);
        }
        ToastUtil.a((int) R.string.smarthome_scene_add_short_cut_success);
    }

    public static void a(final Activity activity, final View.OnClickListener onClickListener) {
        final LayoutInflater from = LayoutInflater.from(activity);
        final MenuDialog menuDialog = new MenuDialog(activity);
        menuDialog.a((BaseAdapter) new BaseAdapter() {
            public int getCount() {
                return 2;
            }

            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                switch (i) {
                    case 0:
                        View inflate = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate.findViewById(R.id.text1)).setText(R.string.voice_add_home);
                        inflate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                VoiceManager.a(activity);
                                menuDialog.dismiss();
                                PreferenceUtils.b("has_show_shortcut_dialog", true);
                            }
                        });
                        return inflate;
                    case 1:
                        View inflate2 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate2.findViewById(R.id.text1)).setText(R.string.voice_setting);
                        inflate2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (onClickListener != null) {
                                    onClickListener.onClick(view);
                                }
                                menuDialog.dismiss();
                            }
                        });
                        return inflate2;
                    default:
                        return view;
                }
            }
        });
        menuDialog.setCanceledOnTouchOutside(true);
        menuDialog.show();
    }

    public static void a(final BaseActivity baseActivity) {
        if (k() && baseActivity.isValid()) {
            new MLAlertDialog.Builder(baseActivity).b((int) R.string.voice_noti_dialog_title).a((int) R.string.voice_add_btn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    VoiceManager.a((Activity) baseActivity);
                    dialogInterface.dismiss();
                    PreferenceUtils.b("has_show_shortcut_dialog", true);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PreferenceUtils.b("has_show_shortcut_dialog", true);
                }
            }).b().show();
        }
    }

    private static boolean k() {
        String[] strArr;
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (PreferenceUtils.a("has_show_shortcut_dialog", false)) {
            return false;
        }
        String a2 = PreferenceUtils.a("history_show_shortcut_log", "");
        if (TextUtils.isEmpty(a2)) {
            strArr = new String[0];
        } else {
            strArr = a2.split(i.b);
        }
        String[] strArr2 = new String[(strArr.length < 3 ? strArr.length + 1 : strArr.length)];
        LogUtil.a(k, "times.length" + strArr.length + " times " + strArr.toString() + "    newtime.length  " + strArr2.length);
        if (strArr.length == 3) {
            System.arraycopy(strArr, 1, strArr2, 0, 2);
            strArr2[2] = currentTimeMillis + "";
            z = a(strArr2);
        } else if (strArr.length == 2) {
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            strArr2[2] = currentTimeMillis + "";
            z = a(strArr2);
        } else {
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            strArr2[strArr2.length - 1] = currentTimeMillis + "";
            z = false;
        }
        if (z) {
            PreferenceUtils.b("has_show_shortcut_dialog", true);
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            if (i2 == 0) {
                sb.append(strArr2[i2]);
            } else {
                sb.append(i.b);
                sb.append(strArr2[i2]);
            }
        }
        PreferenceUtils.b("history_show_shortcut_log", sb.toString());
        return z;
    }

    private static boolean a(String[] strArr) {
        return Long.valueOf(strArr[strArr.length - 1]).longValue() - Long.valueOf(strArr[0]).longValue() < 604800000;
    }
}
