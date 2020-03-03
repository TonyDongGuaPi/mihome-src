package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.react.ReactRootView;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.Reflector;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewUtils {
    public static TextView a(final Context context, final MLAlertDialog mLAlertDialog, final String str) {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (!I.getLanguage().equalsIgnoreCase(Locale.ENGLISH.getLanguage())) {
            boolean equalsIgnoreCase = I.getLanguage().equalsIgnoreCase(Locale.SIMPLIFIED_CHINESE.getLanguage());
        }
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.ml_alert_reset_textview, (ViewGroup) null);
        String string = context.getString(R.string.device_offline_check);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String replace = string.replace("#start#", "").replace("#end#", "");
        spannableStringBuilder.append(replace);
        if (indexOf < 0 || indexOf2 < 0) {
            textView.setText(replace);
            return textView;
        }
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(context, WebShellActivity.class);
                Bundle bundle = new Bundle();
                if (str != null) {
                    bundle.putString("url", str);
                } else {
                    bundle.putString("url", "https://io.mi.com/device/reset");
                }
                intent.putExtras(bundle);
                context.startActivity(intent);
                if (mLAlertDialog != null) {
                    mLAlertDialog.dismiss();
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, indexOf, indexOf2, 33);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLineSpacing(0.0f, 1.5f);
        return textView;
    }

    public static TextView a(Context context, MLAlertDialog mLAlertDialog) {
        String string = context.getString(R.string.mitv_device_offline_check);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), 0, string.length(), 33);
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.ml_alert_reset_textview, (ViewGroup) null);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLineSpacing(0.0f, 1.5f);
        return textView;
    }

    public static TextView b(Context context, MLAlertDialog mLAlertDialog) {
        String string = context.getString(R.string.ble_device_offline_check);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), 0, string.length(), 33);
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.ml_alert_reset_textview, (ViewGroup) null);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLineSpacing(0.0f, 1.5f);
        return textView;
    }

    public static TextView a(final Context context, final MLAlertDialog mLAlertDialog, final String str, String str2, String str3) {
        int i;
        int i2;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str2);
        if (!str2.isEmpty()) {
            i = str2.indexOf(str3);
            i2 = str3.length();
        } else {
            i = 0;
            i2 = 0;
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), 0, i, 33);
        int i3 = i2 + i;
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.h), i, i3, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), i3, spannableStringBuilder.length(), 33);
        if (str3 != null && !str3.isEmpty()) {
            spannableStringBuilder.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    Intent intent = new Intent(context, WebShellActivity.class);
                    Bundle bundle = new Bundle();
                    if (str != null) {
                        bundle.putString("url", str);
                    } else {
                        bundle.putString("url", "https://io.mi.com/device/reset");
                    }
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    if (mLAlertDialog != null) {
                        mLAlertDialog.dismiss();
                    }
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                }
            }, i, i3, 33);
        }
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.ml_alert_reset_textview, (ViewGroup) null);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLineSpacing(0.0f, 1.5f);
        return textView;
    }

    public static TextView a(final Context context, final MLAlertDialog mLAlertDialog, final List<String> list, String str, List<String> list2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!str.isEmpty()) {
            for (int i = 0; i < list2.size(); i++) {
                arrayList.add(Integer.valueOf(str.indexOf(list2.get(i))));
                arrayList2.add(Integer.valueOf(list2.get(i).length()));
            }
        }
        int size = arrayList.size();
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), 0, ((Integer) arrayList.get(0)).intValue(), 33);
        for (int i2 = 0; i2 < size; i2++) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.h), ((Integer) arrayList.get(i2)).intValue(), ((Integer) arrayList.get(i2)).intValue() + ((Integer) arrayList2.get(i2)).intValue(), 33);
        }
        int i3 = size - 1;
        if (((Integer) arrayList.get(i3)).intValue() + ((Integer) arrayList2.get(i3)).intValue() != spannableStringBuilder.length()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), ((Integer) arrayList.get(i3)).intValue() + ((Integer) arrayList2.get(i3)).intValue(), spannableStringBuilder.length(), 33);
        }
        for (final int i4 = 0; i4 < size; i4++) {
            if (list2.get(i4) != null && !list2.get(i4).isEmpty()) {
                spannableStringBuilder.setSpan(new ClickableSpan() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse((String) list.get(i4)));
                        context.startActivity(intent);
                        if (mLAlertDialog != null) {
                            mLAlertDialog.dismiss();
                        }
                    }

                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setUnderlineText(false);
                    }
                }, ((Integer) arrayList.get(i4)).intValue(), ((Integer) arrayList.get(i4)).intValue() + ((Integer) arrayList2.get(i4)).intValue(), 33);
            }
        }
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.ml_alert_reset_textview, (ViewGroup) null);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLineSpacing(0.0f, 1.5f);
        return textView;
    }

    public static TextView c(final Context context, final MLAlertDialog mLAlertDialog) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(context.getString(R.string.smarthome_unbinding_info));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.dialog_sub_title_txt_color)), 0, 23, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.h), 23, 27, 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(context, WebShellActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://io.mi.com/device/reset");
                intent.putExtras(bundle);
                context.startActivity(intent);
                if (mLAlertDialog != null) {
                    mLAlertDialog.dismiss();
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, 23, 27, 33);
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.ml_alert_reset_textview, (ViewGroup) null);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLineSpacing(0.0f, 1.5f);
        return textView;
    }

    public static void a(final Context context, final Dialog dialog, TextView textView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(context.getString(R.string.serach_no_device_blue));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.class_text_15)), 0, 14, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.h), 15, 18, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.class_text_15)), 19, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(context, WebShellActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://io.mi.com/device/reset");
                intent.putExtras(bundle);
                context.startActivity(intent);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, 15, 19, 33);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void a(final Context context, String str, TextView textView, int i, int i2, final String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.class_text_15)), 0, i, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.h), i, i2, 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(context, WebShellActivity.class);
                Bundle bundle = new Bundle();
                if (str2 != null) {
                    bundle.putString("url", str2);
                } else {
                    bundle.putString("url", "https://io.mi.com/device/reset");
                }
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, i, i2, 33);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static void c(View view) {
        if (view == null || (view instanceof ReactRootView)) {
            return;
        }
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap((Bitmap) null);
        } else if (view instanceof WebView) {
            WebView webView = (WebView) view;
            webView.setTag((Object) null);
            webView.stopLoading();
            webView.clearHistory();
            try {
                webView.removeAllViews();
            } catch (Exception unused) {
            }
            webView.clearView();
            try {
                ((ViewGroup) webView.getParent()).removeView(webView);
            } catch (Exception unused2) {
            }
            webView.destroy();
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                c(viewGroup.getChildAt(i));
            }
            try {
                viewGroup.removeAllViews();
            } catch (Exception unused3) {
            }
        }
    }

    public static void a(Activity activity) {
        try {
            c(activity.getWindow().getDecorView().findViewById(16908290));
        } catch (Exception e) {
            MyLog.a((Throwable) e);
        }
    }

    public static void b(Activity activity) {
        InputMethodManager inputMethodManager;
        if (activity != null && (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) != null) {
            Reflector.a(inputMethodManager, "windowDismissed", new Reflector.TypedObject(activity.getWindow().getDecorView().getWindowToken(), IBinder.class));
            Reflector.a(inputMethodManager, "startGettingWindowFocus", new Reflector.TypedObject((Object) null, View.class));
            String[] strArr = {"mCurRootView", "mServedView", "mNextServedView"};
            for (String declaredField : strArr) {
                try {
                    Field declaredField2 = inputMethodManager.getClass().getDeclaredField(declaredField);
                    if (!declaredField2.isAccessible()) {
                        declaredField2.setAccessible(true);
                    }
                    Object obj = declaredField2.get(inputMethodManager);
                    if (obj != null && (obj instanceof View)) {
                        if (((View) obj).getContext() == activity) {
                            declaredField2.set(inputMethodManager, (Object) null);
                        } else {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static List<View> a(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                arrayList.add(childAt);
                arrayList.addAll(a(childAt));
            }
        }
        return arrayList;
    }

    @NonNull
    public static RectF b(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF((float) iArr[0], (float) iArr[1], (float) (iArr[0] + view.getWidth()), (float) (iArr[1] + view.getHeight()));
    }
}
