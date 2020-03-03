package com.mi.global.bbs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.ImageCheckView;
import com.mi.global.bbs.view.SettingsItemContainerView;
import com.mi.global.bbs.view.SettingsItemView;

public class DialogFactory {

    public static class DefaultOnAlertClick implements OnAlertClick {
        public void onCancelClick(View view) {
        }

        public void onOkClick(View view) {
        }
    }

    public interface OnAlertClick {
        void onCancelClick(View view);

        void onOkClick(View view);
    }

    public interface OnClickListener {
        void onClick(String str);
    }

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public interface OnOkClickListener {
        void onOkClick(String str);
    }

    public interface OnStringItemClickListener {
        void onItemClick(int i, String str);
    }

    public static void showMedalDialog(final Activity activity, String str, String str2, String str3) {
        if (activity != null && !activity.isFinishing()) {
            final AlertDialog dialog = getDialog(activity, R.layout.bbs_medal_dialog_layout);
            Window window = dialog.getWindow();
            ((TextView) window.findViewById(R.id.medal_dialog_des)).setText(str2);
            ((TextView) window.findViewById(R.id.medal_dialog_tip)).setText(str);
            ImageLoader.showImage((ImageView) window.findViewById(R.id.medal_dialog_img), str3);
            window.findViewById(R.id.medal_dialog_ok_bt).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ApiClient.clearMedal();
                    dialog.dismiss();
                }
            });
            window.findViewById(R.id.medal_dialog_watch_all_bt).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DialogFactory.openProf(activity);
                    ApiClient.clearMedal();
                    dialog.dismiss();
                }
            });
        }
    }

    public static void showReportDialog(Context context, String str, OnClickListener onClickListener) {
        String str2 = str;
        AlertDialog dialog = getDialog(context, R.layout.thought_report_dialog);
        Window window = dialog.getWindow();
        CheckedTextView checkedTextView = (CheckedTextView) window.findViewById(R.id.report_advert);
        CheckedTextView checkedTextView2 = (CheckedTextView) window.findViewById(R.id.report_lllegal);
        CheckedTextView checkedTextView3 = (CheckedTextView) window.findViewById(R.id.report_malicious);
        CheckedTextView checkedTextView4 = (CheckedTextView) window.findViewById(R.id.report_duplicated);
        CheckedTextView checkedTextView5 = (CheckedTextView) window.findViewById(R.id.report_other);
        final String[] strArr = {checkedTextView.getText().toString()};
        checkedTextView.setChecked(str2.equals(checkedTextView.getText().toString()));
        final String[] strArr2 = strArr;
        final CheckedTextView checkedTextView6 = checkedTextView;
        final CheckedTextView checkedTextView7 = checkedTextView2;
        final CheckedTextView checkedTextView8 = checkedTextView3;
        final CheckedTextView checkedTextView9 = checkedTextView4;
        AlertDialog alertDialog = dialog;
        AnonymousClass3 r1 = r4;
        final CheckedTextView checkedTextView10 = checkedTextView5;
        AnonymousClass3 r4 = new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                strArr2[0] = checkedTextView6.getText().toString();
                checkedTextView6.setChecked(true);
                checkedTextView7.setChecked(false);
                checkedTextView8.setChecked(false);
                checkedTextView9.setChecked(false);
                checkedTextView10.setChecked(false);
            }
        };
        checkedTextView.setOnCheckedChangeListener(r1);
        checkedTextView2.setChecked(str2.equals(checkedTextView2.getText().toString()));
        final CheckedTextView checkedTextView11 = checkedTextView2;
        final CheckedTextView checkedTextView12 = checkedTextView;
        checkedTextView2.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                strArr2[0] = checkedTextView11.getText().toString();
                checkedTextView12.setChecked(false);
                checkedTextView11.setChecked(true);
                checkedTextView8.setChecked(false);
                checkedTextView9.setChecked(false);
                checkedTextView10.setChecked(false);
            }
        });
        checkedTextView3.setChecked(str2.equals(checkedTextView3.getText().toString()));
        final CheckedTextView checkedTextView13 = checkedTextView3;
        final CheckedTextView checkedTextView14 = checkedTextView2;
        checkedTextView3.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                strArr2[0] = checkedTextView13.getText().toString();
                checkedTextView12.setChecked(false);
                checkedTextView14.setChecked(false);
                checkedTextView13.setChecked(true);
                checkedTextView9.setChecked(false);
                checkedTextView10.setChecked(false);
            }
        });
        checkedTextView4.setChecked(str2.equals(checkedTextView4.getText().toString()));
        final CheckedTextView checkedTextView15 = checkedTextView4;
        final CheckedTextView checkedTextView16 = checkedTextView3;
        checkedTextView4.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                strArr2[0] = checkedTextView15.getText().toString();
                checkedTextView12.setChecked(false);
                checkedTextView14.setChecked(false);
                checkedTextView16.setChecked(false);
                checkedTextView15.setChecked(true);
                checkedTextView10.setChecked(false);
            }
        });
        checkedTextView5.setChecked(str2.equals(checkedTextView5.getText().toString()));
        final CheckedTextView checkedTextView17 = checkedTextView5;
        final CheckedTextView checkedTextView18 = checkedTextView4;
        checkedTextView5.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                strArr2[0] = checkedTextView17.getText().toString();
                checkedTextView12.setChecked(false);
                checkedTextView14.setChecked(false);
                checkedTextView16.setChecked(false);
                checkedTextView18.setChecked(false);
                checkedTextView17.setChecked(true);
            }
        });
        final OnClickListener onClickListener2 = onClickListener;
        final AlertDialog alertDialog2 = alertDialog;
        ((TextView) window.findViewById(R.id.submit_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                alertDialog2.dismiss();
                onClickListener2.onClick(strArr[0].toString());
            }
        });
        ((ImageView) window.findViewById(R.id.close_img)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                alertDialog2.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public static void openProf(Activity activity) {
        String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_PROFILE, "");
        if (!TextUtils.isEmpty(stringPref) && stringPref.indexOf("%d") > 0) {
            try {
                String replace = stringPref.replace("%d", LoginManager.getInstance().getUserId());
                Intent intent = new Intent();
                intent.putExtra("url", replace);
                activity.setResult(-1, intent);
                activity.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        GoogleTrackerUtil.sendRecordEvent(Constants.TitleMenu.MENU_TASK, Constants.WebView.PAGE_PROFILE, "go_to_profile");
    }

    public static void showEditQuitHintDialog(Activity activity, final OnItemClickListener onItemClickListener) {
        if (activity != null && !activity.isFinishing()) {
            final AlertDialog dialog = getDialog(activity, R.layout.bbs_editor_left_hint_dialog);
            Window window = dialog.getWindow();
            window.findViewById(R.id.edit_quit_hint_item_1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(0);
                    }
                }
            });
            window.findViewById(R.id.edit_quit_hint_item_2).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(1);
                    }
                }
            });
            window.findViewById(R.id.edit_quit_hint_item_3).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(2);
                    }
                }
            });
        }
    }

    public static void showColumnPostSetting(Activity activity, boolean z, final SettingsItemContainerView.OnItemCheckedChangedListener onItemCheckedChangedListener) {
        if (activity != null && !activity.isFinishing()) {
            final AlertDialog dialog = getDialog(activity, R.layout.bbs_dialog_column_post_setting);
            SettingsItemView settingsItemView = new SettingsItemView(activity);
            settingsItemView.setTitle(activity.getResources().getString(R.string.column_post_setting));
            settingsItemView.setChecked(z);
            settingsItemView.setOnCheckedChangedListener(new ImageCheckView.OnCheckedChangedListener() {
                public void onCheckedChanged(ImageCheckView imageCheckView, boolean z) {
                    if (onItemCheckedChangedListener != null) {
                        onItemCheckedChangedListener.onItemCheckedChangedListener(0, z);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 200);
                    }
                }
            });
            ((LinearLayout) dialog.getWindow().findViewById(R.id.linear_post_setting)).addView(settingsItemView);
        }
    }

    public static void showInsertVideoUrlDialog(Activity activity, final OnOkClickListener onOkClickListener) {
        if (activity != null && !activity.isFinishing()) {
            final AlertDialog dialog = getDialog(activity, R.layout.bbs_alert_insert_video);
            Window window = dialog.getWindow();
            window.clearFlags(131072);
            final FontEditText fontEditText = (FontEditText) window.findViewById(R.id.alert_insert_video_edit);
            window.findViewById(R.id.alert_insert_video_cancel).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ImeUtils.hideIme(view);
                    dialog.dismiss();
                }
            });
            window.findViewById(R.id.alert_insert_video_ok).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String obj = fontEditText.getText().toString();
                    if (!TextUtils.isEmpty(obj) && DialogFactory.checkValid(obj)) {
                        ImeUtils.hideIme(view);
                        onOkClickListener.onOkClick(obj);
                        dialog.dismiss();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static boolean checkValid(String str) {
        if (str != null) {
            return str.startsWith("http");
        }
        Toast.makeText(BBSApplication.getInstance(), "not a correct url", 1).show();
        return false;
    }

    public static void showAlert(Context context, String str, DefaultOnAlertClick defaultOnAlertClick) {
        showAlert(context, str, context.getString(R.string.bbs_dialog_ask_ok), context.getString(R.string.bbs_dialog_ask_cancel), defaultOnAlertClick);
    }

    public static void showAlert(Context context, int i, int i2, int i3, final DefaultOnAlertClick defaultOnAlertClick) {
        final AlertDialog dialog = getDialog(context, R.layout.bbs_alert_common_layout);
        Window window = dialog.getWindow();
        ((TextView) window.findViewById(R.id.alert_content)).setText(i);
        TextView textView = (TextView) window.findViewById(R.id.alert_cancel);
        textView.setText(i3);
        TextView textView2 = (TextView) window.findViewById(R.id.alert_ok);
        textView2.setText(i2);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (defaultOnAlertClick != null) {
                    defaultOnAlertClick.onCancelClick(view);
                }
                dialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (defaultOnAlertClick != null) {
                    defaultOnAlertClick.onOkClick(view);
                }
                dialog.dismiss();
            }
        });
    }

    public static void showAlert(Context context, String str, String str2, String str3, final DefaultOnAlertClick defaultOnAlertClick) {
        final AlertDialog dialog = getDialog(context, R.layout.bbs_alert_common_layout);
        Window window = dialog.getWindow();
        ((TextView) window.findViewById(R.id.alert_content)).setText(str);
        TextView textView = (TextView) window.findViewById(R.id.alert_cancel);
        textView.setText(str3);
        TextView textView2 = (TextView) window.findViewById(R.id.alert_ok);
        textView2.setText(str2);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (defaultOnAlertClick != null) {
                    defaultOnAlertClick.onCancelClick(view);
                }
                dialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (defaultOnAlertClick != null) {
                    defaultOnAlertClick.onOkClick(view);
                }
                dialog.dismiss();
            }
        });
    }

    public static void showSortDialog(Context context, String str, final OnStringItemClickListener onStringItemClickListener) {
        final AlertDialog dialog = getDialog(context, R.layout.bbs_thread_sort_dialog);
        Window window = dialog.getWindow();
        final CheckedTextView checkedTextView = (CheckedTextView) window.findViewById(R.id.sort_by_latest_reply);
        checkedTextView.setChecked(str.equals(checkedTextView.getText().toString()));
        checkedTextView.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (onStringItemClickListener != null) {
                    onStringItemClickListener.onItemClick(0, checkedTextView.getText().toString());
                }
                dialog.dismiss();
            }
        });
        final CheckedTextView checkedTextView2 = (CheckedTextView) window.findViewById(R.id.sort_by_views);
        checkedTextView2.setChecked(str.equals(checkedTextView2.getText().toString()));
        checkedTextView2.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (onStringItemClickListener != null) {
                    onStringItemClickListener.onItemClick(1, checkedTextView2.getText().toString());
                }
                dialog.dismiss();
            }
        });
        final CheckedTextView checkedTextView3 = (CheckedTextView) window.findViewById(R.id.sort_by_replies);
        checkedTextView3.setChecked(str.equals(checkedTextView3.getText().toString()));
        checkedTextView3.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (onStringItemClickListener != null) {
                    onStringItemClickListener.onItemClick(2, checkedTextView3.getText().toString());
                }
                dialog.dismiss();
            }
        });
        final CheckedTextView checkedTextView4 = (CheckedTextView) window.findViewById(R.id.sort_by_newest_post);
        checkedTextView4.setChecked(str.equals(checkedTextView4.getText().toString()));
        checkedTextView4.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (onStringItemClickListener != null) {
                    onStringItemClickListener.onItemClick(3, checkedTextView4.getText().toString());
                }
                dialog.dismiss();
            }
        });
    }

    public static AlertDialog getDialog(Context context, @LayoutRes int i) {
        AlertDialog create = new AlertDialog.Builder(context).create();
        create.show();
        Window window = create.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setContentView(i);
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public static AlertDialog showListDialog(Context context, RecyclerView.Adapter adapter) {
        AlertDialog dialog = getDialog(context, R.layout.bbs_dialog_list_layout);
        RecyclerView recyclerView = (RecyclerView) dialog.getWindow().findViewById(R.id.dialog_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(context, 1, context.getResources().getDimensionPixelSize(R.dimen.divide_height), context.getResources().getColor(R.color.user_center_divider_color)));
        return dialog;
    }

    public static void showSignAlert(Context context) {
        final AlertDialog dialog = getDialog(context, R.layout.bbs_alert_layout);
        dialog.getWindow().findViewById(R.id.alert_ok).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}
