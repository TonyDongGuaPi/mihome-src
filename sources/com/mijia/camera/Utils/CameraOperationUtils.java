package com.mijia.camera.Utils;

import android.app.Activity;
import android.content.DialogInterface;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.model.sdcard.SdcardManager;
import com.mijia.model.sdcard.SdcardManagerEx;
import com.mijia.model.sdcard.TimeItem;
import com.smarthome_camera.R;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public class CameraOperationUtils {

    /* renamed from: a  reason: collision with root package name */
    static SimpleDateFormat f7904a;
    static SimpleDateFormat b;

    public static void a(Activity activity, final MijiaCameraDevice mijiaCameraDevice, final List<TimeItem> list, final XQProgressDialog xQProgressDialog, final Callback<Void> callback) {
        if (list == null || list.size() == 0) {
            ToastUtil.a(activity, activity.getString(R.string.bottom_action_tip));
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        builder.a(R.string.delete_title);
        if (list.size() == 1) {
            if (f7904a == null || b == null) {
                f7904a = new SimpleDateFormat(activity.getResources().getString(R.string.simple_date_format_mm_dd_hh_mm));
                b = new SimpleDateFormat(activity.getResources().getString(R.string.simple_date_format_hh_mm));
            }
            builder.b((CharSequence) Operators.BRACKET_START_STR + f7904a.format(new Date(list.get(0).f8098a)) + "-" + b.format(new Date(list.get(0).b())) + Operators.BRACKET_END_STR);
        }
        builder.a(R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (xQProgressDialog != null) {
                    xQProgressDialog.show();
                }
                if (mijiaCameraDevice == null || !mijiaCameraDevice.n()) {
                    SdcardManager c2 = mijiaCameraDevice.c();
                    if (c2 != null) {
                        c2.a((List<TimeItem>) list, (Callback<JSONObject>) new Callback<JSONObject>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                if (callback != null) {
                                    callback.onSuccess(null);
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (callback != null) {
                                    callback.onFailure(i, str);
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                SdcardManagerEx d2 = mijiaCameraDevice.d();
                if (d2 != null) {
                    d2.a((List<TimeItem>) list, (Callback<JSONObject>) new Callback<JSONObject>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (callback != null) {
                                callback.onSuccess(null);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (callback != null) {
                                callback.onFailure(i, str);
                            }
                        }
                    });
                }
            }
        });
        builder.b(R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.d();
    }

    public static void b(Activity activity, final MijiaCameraDevice mijiaCameraDevice, final List<TimeItem> list, final XQProgressDialog xQProgressDialog, final Callback<Void> callback) {
        if (list == null || list.size() == 0) {
            ToastUtil.a(activity, activity.getString(R.string.bottom_action_tip));
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).d != 0) {
                list.remove(size);
            }
        }
        if (list.size() == 0) {
            ToastUtil.a(activity, activity.getString(R.string.bottom_save_title));
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        StringBuilder sb = new StringBuilder();
        sb.append(activity.getString(R.string.save_title));
        if (list.size() == 1) {
            if (f7904a == null || b == null) {
                f7904a = new SimpleDateFormat(activity.getResources().getString(R.string.simple_date_format_mm_dd_hh_mm));
                b = new SimpleDateFormat(activity.getResources().getString(R.string.simple_date_format_hh_mm));
            }
            sb.append("\r\n\r\n");
            sb.append(Operators.BRACKET_START_STR + f7904a.format(new Date(list.get(0).f8098a)) + "-" + b.format(new Date(list.get(0).b())) + Operators.BRACKET_END_STR);
        }
        builder.b((CharSequence) sb.toString());
        builder.a(R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (xQProgressDialog != null) {
                    xQProgressDialog.show();
                }
                if (mijiaCameraDevice == null || !mijiaCameraDevice.n()) {
                    SdcardManager c2 = mijiaCameraDevice.c();
                    if (c2 != null) {
                        c2.b((List<TimeItem>) list, (Callback<JSONObject>) new Callback<JSONObject>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                if (callback != null) {
                                    callback.onSuccess(null);
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (callback != null) {
                                    callback.onFailure(i, str);
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                SdcardManagerEx d2 = mijiaCameraDevice.d();
                if (d2 != null) {
                    d2.b((List<TimeItem>) list, (Callback<JSONObject>) new Callback<JSONObject>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (callback != null) {
                                callback.onSuccess(null);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (callback != null) {
                                callback.onFailure(i, str);
                            }
                        }
                    });
                }
            }
        });
        builder.b(R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.d();
    }
}
