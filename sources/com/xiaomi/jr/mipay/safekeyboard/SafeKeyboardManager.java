package com.xiaomi.jr.mipay.safekeyboard;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.mipay.safekeyboard.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;

public class SafeKeyboardManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10980a = -1;

    private static class SafeKeyboardInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f10985a;

        private SafeKeyboardInfo() {
        }
    }

    public static SafeKeyboardView a(Activity activity, String str) {
        SafeKeyboardView safeKeyboardView = (SafeKeyboardView) activity.findViewById(R.id.jr_mipay_safe_keyboard);
        return safeKeyboardView == null ? new KeyboardContainer(activity, a(activity)).a(activity, str) : safeKeyboardView;
    }

    /* access modifiers changed from: private */
    public static SafeKeyboardInfo g(SafeKeyboardView safeKeyboardView) {
        SafeKeyboardInfo safeKeyboardInfo = (SafeKeyboardInfo) safeKeyboardView.getTag();
        if (safeKeyboardInfo != null) {
            return safeKeyboardInfo;
        }
        SafeKeyboardInfo safeKeyboardInfo2 = new SafeKeyboardInfo();
        int unused = safeKeyboardInfo2.f10985a = ((Activity) safeKeyboardView.getContext()).getWindow().getAttributes().softInputMode;
        safeKeyboardView.setTag(safeKeyboardInfo2);
        return safeKeyboardInfo2;
    }

    /* access modifiers changed from: private */
    public static void h(SafeKeyboardView safeKeyboardView) {
        ((Activity) safeKeyboardView.getContext()).getWindow().setSoftInputMode(3);
    }

    /* access modifiers changed from: private */
    public static void i(SafeKeyboardView safeKeyboardView) {
        SafeKeyboardInfo safeKeyboardInfo = (SafeKeyboardInfo) safeKeyboardView.getTag();
        if (safeKeyboardInfo != null) {
            ((Activity) safeKeyboardView.getContext()).getWindow().setSoftInputMode(safeKeyboardInfo.f10985a);
            safeKeyboardView.setTag((Object) null);
        }
    }

    private static int a(Activity activity) {
        return activity.getWindow().getAttributes().softInputMode;
    }

    private static class KeyboardContainer {

        /* renamed from: a  reason: collision with root package name */
        private ContainerLayout f10983a;
        private RelativeLayout.LayoutParams b = new RelativeLayout.LayoutParams(-1, -2);
        private RelativeLayout.LayoutParams c;

        KeyboardContainer(Context context, int i) {
            this.f10983a = new ContainerLayout(context);
            this.b.addRule(12, -1);
            if ((i & PsExtractor.VIDEO_STREAM_MASK) == 48) {
                this.c = new RelativeLayout.LayoutParams(-1, -1);
                return;
            }
            this.c = new RelativeLayout.LayoutParams(-1, -1);
            this.c.addRule(2, R.id.jr_mipay_safe_keyboard);
        }

        /* access modifiers changed from: package-private */
        public SafeKeyboardView a(Activity activity, String str) {
            a(activity);
            SafeKeyboardView b2 = b(activity, str);
            this.f10983a.setLayoutTransition(SafeKeyboardManager.b((Context) activity, SafeKeyboardManager.j(b2)));
            return b2;
        }

        private void a(Activity activity) {
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
            viewGroup.removeAllViews();
            viewGroup.setId(-1);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
            int i2 = marginLayoutParams.leftMargin;
            int i3 = marginLayoutParams.rightMargin;
            int i4 = marginLayoutParams.topMargin;
            int i5 = marginLayoutParams.bottomMargin;
            marginLayoutParams.setMargins(0, 0, 0, 0);
            this.c.setMargins(i2, i4, i3, i5);
            viewGroup.addView(this.f10983a);
            int paddingLeft = viewGroup.getPaddingLeft();
            int paddingRight = viewGroup.getPaddingRight();
            int paddingTop = viewGroup.getPaddingTop();
            int paddingBottom = viewGroup.getPaddingBottom();
            viewGroup.setPadding(0, 0, 0, 0);
            Drawable background = viewGroup.getBackground();
            viewGroup.setBackground(new ColorDrawable(0));
            FrameLayout frameLayout = new FrameLayout(activity);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                frameLayout.addView((View) it.next());
            }
            frameLayout.setId(16908290);
            frameLayout.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            frameLayout.setBackground(background);
            this.f10983a.addView(frameLayout, this.c);
        }

        private SafeKeyboardView b(Activity activity, String str) {
            SafeKeyboardView safeKeyboardView = new SafeKeyboardView(activity);
            safeKeyboardView.setId(R.id.jr_mipay_safe_keyboard);
            this.f10983a.addView(safeKeyboardView, this.b);
            SafeKeyboardManager.b(safeKeyboardView, str);
            return safeKeyboardView;
        }

        private class ContainerLayout extends RelativeLayout {
            private ContainerLayout(Context context) {
                super(context);
            }

            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                SafeKeyboardView safeKeyboardView;
                if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() != 0 || (safeKeyboardView = (SafeKeyboardView) findViewById(R.id.jr_mipay_safe_keyboard)) == null || safeKeyboardView.getVisibility() != 0) {
                    return super.dispatchKeyEvent(keyEvent);
                }
                SafeKeyboardManager.a(safeKeyboardView);
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public static LayoutTransition b(Context context, int i) {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(0, (Animator) null);
        layoutTransition.setAnimator(1, (Animator) null);
        if (i > 0) {
            float f = (float) i;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object) null, "translationY", new float[]{f, 0.0f});
            ofFloat.setDuration((long) context.getResources().getInteger(17694721));
            ofFloat.setInterpolator(new DecelerateInterpolator(2.5f));
            layoutTransition.setAnimator(2, ofFloat);
            layoutTransition.setStartDelay(2, 0);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object) null, "translationY", new float[]{0.0f, f});
            ofFloat2.setDuration((long) context.getResources().getInteger(17694721));
            ofFloat2.setInterpolator(new DecelerateInterpolator(2.5f));
            layoutTransition.setAnimator(3, ofFloat2);
            layoutTransition.setStartDelay(3, 0);
        }
        return layoutTransition;
    }

    public static void a(SafeKeyboardView safeKeyboardView) {
        safeKeyboardView.hide();
    }

    public static void b(SafeKeyboardView safeKeyboardView) {
        SafeKeyboard keyboard = safeKeyboardView.getKeyboard();
        if (keyboard != null && !TextUtils.equals(keyboard.f(), "none")) {
            safeKeyboardView.requestShow((View) null);
        }
    }

    public static void a(SafeKeyboardView safeKeyboardView, String str) {
        b(safeKeyboardView, str);
        b(safeKeyboardView);
    }

    public static void a(final View view, final SafeKeyboardView safeKeyboardView) {
        if (view instanceof EditText) {
            Utils.a((EditText) view, false);
        }
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    SafeKeyboardInfo unused = SafeKeyboardManager.g(safeKeyboardView);
                    SafeKeyboardManager.h(safeKeyboardView);
                    safeKeyboardView.requestShow(view);
                    return;
                }
                SafeKeyboardManager.i(safeKeyboardView);
                SafeKeyboardManager.a(safeKeyboardView);
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            private long c;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.c = System.currentTimeMillis();
                    return false;
                } else if (motionEvent.getAction() != 1 || a()) {
                    return false;
                } else {
                    safeKeyboardView.requestShow(view);
                    return false;
                }
            }

            private boolean a() {
                return System.currentTimeMillis() - this.c >= ((long) ViewConfiguration.getLongPressTimeout());
            }
        });
    }

    public static void a(View view) {
        if (view instanceof EditText) {
            Utils.a((EditText) view, true);
        }
        view.setOnFocusChangeListener((View.OnFocusChangeListener) null);
        view.setOnKeyListener((View.OnKeyListener) null);
        view.setOnTouchListener((View.OnTouchListener) null);
    }

    public static void b(SafeKeyboardView safeKeyboardView, String str) {
        a(str);
        if (safeKeyboardView != null) {
            SafeKeyboard keyboard = safeKeyboardView.getKeyboard();
            if (keyboard == null || !str.equalsIgnoreCase(keyboard.f())) {
                Context applicationContext = safeKeyboardView.getContext().getApplicationContext();
                SafeKeyboard safeKeyboard = new SafeKeyboard(applicationContext, str);
                safeKeyboardView.setKeyboard(safeKeyboard);
                ((ViewGroup) safeKeyboardView.getParent()).setLayoutTransition(b(applicationContext, safeKeyboard.b()));
            }
        }
    }

    private static void a(String str) {
        if (!"none".equalsIgnoreCase(str) && !"number".equalsIgnoreCase(str) && !SafeKeyboard.c.equalsIgnoreCase(str)) {
            throw new TypeNotPresentException("(Safekeyobard type :" + str + Operators.BRACKET_END_STR, (Throwable) null);
        }
    }

    /* access modifiers changed from: private */
    public static int j(SafeKeyboardView safeKeyboardView) {
        SafeKeyboard keyboard = safeKeyboardView.getKeyboard();
        if (keyboard == null) {
            return 0;
        }
        return keyboard.b();
    }
}
