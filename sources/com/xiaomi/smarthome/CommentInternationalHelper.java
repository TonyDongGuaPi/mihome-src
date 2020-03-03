package com.xiaomi.smarthome;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.feedback.FeedbackActivity;
import com.xiaomi.smarthome.feedback.FeedbackApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;

public class CommentInternationalHelper {
    public static void a() {
        RemoteFamilyApi.a().f(new AsyncCallback<Boolean, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                boolean z;
                int i = 0;
                if (bool == null) {
                    z = false;
                } else {
                    try {
                        z = bool.booleanValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                String s = CoreApi.a().s();
                if (s == null) {
                    return;
                }
                if (!s.isEmpty()) {
                    Context appContext = SHApplication.getAppContext();
                    SharedPreferences.Editor edit = appContext.getSharedPreferences("com.xiaomi.mihome.enter" + s, 0).edit();
                    if (z) {
                        i = 1;
                    }
                    edit.putInt("mihome_enter_main_page_show_flag_intl", i).commit();
                }
            }
        });
    }

    public static boolean b() {
        String s;
        if (!HomeManager.A() || (s = CoreApi.a().s()) == null || s.isEmpty() || TextUtils.isEmpty(CommonUtils.t(SHApplication.getAppContext())) || SmartHomeDeviceManager.a().d().size() <= 0) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("com.xiaomi.mihome.enter" + s, 0);
        if (sharedPreferences == null || sharedPreferences.getInt("mihome_enter_main_page_show_flag_intl", 0) != 1) {
            return false;
        }
        if (Math.abs(System.currentTimeMillis() - sharedPreferences.getLong("mihome_enter_main_page_intl_shown_ts", 0)) < 1296000000) {
            return false;
        }
        int i = sharedPreferences.getInt("mihome_enter_main_page_intl", 0);
        if (sharedPreferences.getInt("mihome_enter_main_page_intl_version", 63000) != 63000) {
            i = 0;
        }
        int i2 = i + 1;
        long currentTimeMillis = System.currentTimeMillis();
        if (i2 == 4) {
            if (currentTimeMillis - sharedPreferences.getLong("mihome_first_count_time_intl", 0) <= 259200000) {
                return true;
            }
            i2 = 1;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (i2 == 1) {
            edit.remove("mihome_first_count_time_intl");
            edit.putLong("mihome_first_count_time_intl", currentTimeMillis);
        }
        edit.remove("mihome_enter_main_page_intl");
        edit.putInt("mihome_enter_main_page_intl", i2);
        edit.putInt("mihome_enter_main_page_intl_version", 63000);
        edit.apply();
        return false;
    }

    public static Dialog a(final BaseActivity baseActivity) {
        MLAlertDialog b = new MLAlertDialog.Builder(baseActivity).d(false).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
                STAT.d.ap();
            }
        }).a((int) R.string.comment_for_mihome_title).b((int) R.string.give_advice, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent = new Intent(SHApplication.getAppContext(), FeedbackActivity.class);
                intent.putExtra("extra_device_model", FeedbackApi.COMMON_EXP);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                SHApplication.getAppContext().startActivity(intent);
                STAT.d.ao();
            }
        }).a((int) R.string.like_it_btn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean z;
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    String t = CommonUtils.t(baseActivity);
                    if (!TextUtils.isEmpty(t)) {
                        intent.setPackage(t);
                    }
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.setData(Uri.parse("market://details?id=" + baseActivity.getPackageName()));
                    if (intent.resolveActivity(baseActivity.getPackageManager()) != null) {
                        baseActivity.startActivity(intent);
                    } else {
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + baseActivity.getPackageName()));
                        if (intent.resolveActivity(baseActivity.getPackageManager()) != null) {
                            baseActivity.startActivity(intent);
                        }
                    }
                    z = true;
                } catch (Exception unused) {
                    z = false;
                }
                if (z) {
                    STAT.d.an();
                }
                dialogInterface.dismiss();
            }
        }).b();
        TextView textView = new TextView(baseActivity);
        textView.setText(R.string.comment_for_mihome_detail_international);
        textView.setTextColor(baseActivity.getResources().getColor(R.color.class_Y));
        textView.setLineSpacing(0.0f, 1.0f);
        b.setView(textView, DisplayUtils.a(20.0f), DisplayUtils.a(5.0f) * -1, DisplayUtils.a(20.0f), DisplayUtils.a(10.0f));
        if (baseActivity.isValid()) {
            b.show();
            String s = CoreApi.a().s();
            if (!TextUtils.isEmpty(s)) {
                Context appContext = SHApplication.getAppContext();
                SharedPreferences.Editor edit = appContext.getSharedPreferences("com.xiaomi.mihome.enter" + s, 0).edit();
                edit.putLong("mihome_enter_main_page_intl_shown_ts", System.currentTimeMillis());
                edit.putInt("mihome_enter_main_page_intl", 0);
                edit.apply();
            }
            STAT.e.c();
        }
        return b;
    }
}
