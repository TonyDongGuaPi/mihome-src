package me.drakeet.support.toast;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;
import java.lang.reflect.Field;

public final class ToastCompat extends Toast {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    private final Toast f2918a;

    private ToastCompat(Context context, @NonNull Toast toast) {
        super(context);
        this.f2918a = toast;
    }

    public static ToastCompat a(Context context, CharSequence charSequence, int i) {
        Toast makeText = Toast.makeText(context, charSequence, i);
        a(makeText.getView(), new SafeToastContext(context, makeText));
        return new ToastCompat(context, makeText);
    }

    public static Toast a(Context context, @StringRes int i, int i2) throws Resources.NotFoundException {
        return a(context, context.getResources().getText(i), i2);
    }

    @NonNull
    public ToastCompat a(@NonNull BadTokenListener badTokenListener) {
        Context context = getView().getContext();
        if (context instanceof SafeToastContext) {
            ((SafeToastContext) context).a(badTokenListener);
        }
        return this;
    }

    public void show() {
        this.f2918a.show();
    }

    public void setDuration(int i) {
        this.f2918a.setDuration(i);
    }

    public void setGravity(int i, int i2, int i3) {
        this.f2918a.setGravity(i, i2, i3);
    }

    public void setMargin(float f, float f2) {
        this.f2918a.setMargin(f, f2);
    }

    public void setText(int i) {
        this.f2918a.setText(i);
    }

    public void setText(CharSequence charSequence) {
        this.f2918a.setText(charSequence);
    }

    public void setView(View view) {
        this.f2918a.setView(view);
        a(view, new SafeToastContext(view.getContext(), this));
    }

    public float getHorizontalMargin() {
        return this.f2918a.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return this.f2918a.getVerticalMargin();
    }

    public int getDuration() {
        return this.f2918a.getDuration();
    }

    public int getGravity() {
        return this.f2918a.getGravity();
    }

    public int getXOffset() {
        return this.f2918a.getXOffset();
    }

    public int getYOffset() {
        return this.f2918a.getYOffset();
    }

    public View getView() {
        return this.f2918a.getView();
    }

    @NonNull
    public Toast a() {
        return this.f2918a;
    }

    private static void a(@NonNull View view, @NonNull Context context) {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                Field declaredField = View.class.getDeclaredField("mContext");
                declaredField.setAccessible(true);
                declaredField.set(view, context);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
