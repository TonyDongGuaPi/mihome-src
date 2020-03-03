package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import java.util.Locale;
import java.util.Stack;
import javax.annotation.Nullable;

public final class DebugViews {

    /* renamed from: a  reason: collision with root package name */
    public static final int f17486a = 200;
    public static final Matrix b = new Matrix();

    public static int a(@NonNull String str, @NonNull String str2) {
        int i = 0;
        while (str2.length() > 0) {
            int indexOf = str2.indexOf("\n");
            int min = Math.min(str2.length(), Math.min(indexOf < 0 ? 200 : indexOf + 1, 200));
            Log.d(str, String.format(Locale.US, "%02d: %s", new Object[]{Integer.valueOf(i), str2.substring(0, min)}));
            str2 = str2.substring(min);
            i++;
        }
        return i;
    }

    @NonNull
    public static String a(@NonNull Activity activity) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById != null) {
            return a(findViewById);
        }
        return "Activity [" + activity.getClass().getSimpleName() + "] is not initialized yet. ";
    }

    @NonNull
    private static String a(@NonNull View view) {
        StringBuilder sb = new StringBuilder(8192);
        sb.append("\n");
        Resources resources = view.getResources();
        Stack stack = new Stack();
        stack.push(Pair.create("", view));
        while (!stack.empty()) {
            Pair pair = (Pair) stack.pop();
            View view2 = (View) pair.second;
            String str = (String) pair.first;
            boolean z = stack.empty() || !str.equals(((Pair) stack.peek()).first);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(str);
            sb2.append(z ? "└── " : "├── ");
            sb.append(sb2.toString() + view2.getClass().getSimpleName() + a(resources, view2));
            sb.append("\n");
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(z ? "    " : "│   ");
                    stack.push(Pair.create(sb3.toString(), viewGroup.getChildAt(childCount)));
                }
            }
        }
        return sb.toString();
    }

    @TargetApi(21)
    @NonNull
    private static String a(@NonNull Resources resources, @NonNull View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("id=");
        sb.append(view.getId());
        sb.append(b(resources, view));
        int visibility = view.getVisibility();
        if (visibility == 0) {
            sb.append(", V--");
        } else if (visibility == 4) {
            sb.append(", -I-");
        } else if (visibility != 8) {
            sb.append(", ---");
        } else {
            sb.append(", --G");
        }
        if (!view.getMatrix().equals(b)) {
            sb.append(", ");
            sb.append("matrix=");
            sb.append(view.getMatrix().toShortString());
            if (!(0.0f == view.getRotation() && 0.0f == view.getRotationX() && 0.0f == view.getRotationY())) {
                sb.append(", rotate=[");
                sb.append(view.getRotation());
                sb.append(",");
                sb.append(view.getRotationX());
                sb.append(",");
                sb.append(view.getRotationY());
                sb.append(Operators.ARRAY_END_STR);
                if (!(((float) (view.getWidth() / 2)) == view.getPivotX() && ((float) (view.getHeight() / 2)) == view.getPivotY())) {
                    sb.append(", pivot=[");
                    sb.append(view.getPivotX());
                    sb.append(",");
                    sb.append(view.getPivotY());
                    sb.append(Operators.ARRAY_END_STR);
                }
            }
            if (Build.VERSION.SDK_INT >= 21 && !(0.0f == view.getTranslationX() && 0.0f == view.getTranslationY() && 0.0f == view.getTranslationZ())) {
                sb.append(", translate=[");
                sb.append(view.getTranslationX());
                sb.append(",");
                sb.append(view.getTranslationY());
                sb.append(",");
                sb.append(view.getTranslationZ());
                sb.append(Operators.ARRAY_END_STR);
            }
            if (!(1.0f == view.getScaleX() && 1.0f == view.getScaleY())) {
                sb.append(", scale=[");
                sb.append(view.getScaleX());
                sb.append(",");
                sb.append(view.getScaleY());
                sb.append(Operators.ARRAY_END_STR);
            }
        }
        if (!(view.getPaddingStart() == 0 && view.getPaddingTop() == 0 && view.getPaddingEnd() == 0 && view.getPaddingBottom() == 0)) {
            sb.append(", ");
            sb.append("padding=[");
            sb.append(view.getPaddingStart());
            sb.append(",");
            sb.append(view.getPaddingTop());
            sb.append(",");
            sb.append(view.getPaddingEnd());
            sb.append(",");
            sb.append(view.getPaddingBottom());
            sb.append(Operators.ARRAY_END_STR);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (!(marginLayoutParams.leftMargin == 0 && marginLayoutParams.topMargin == 0 && marginLayoutParams.rightMargin == 0 && marginLayoutParams.bottomMargin == 0)) {
                sb.append(", ");
                sb.append("margin=[");
                sb.append(marginLayoutParams.leftMargin);
                sb.append(",");
                sb.append(marginLayoutParams.topMargin);
                sb.append(",");
                sb.append(marginLayoutParams.rightMargin);
                sb.append(",");
                sb.append(marginLayoutParams.bottomMargin);
                sb.append(Operators.ARRAY_END_STR);
            }
        }
        sb.append(", position=[");
        sb.append(view.getLeft());
        sb.append(",");
        sb.append(view.getTop());
        sb.append(Operators.ARRAY_END_STR);
        sb.append(", size=[");
        sb.append(view.getWidth());
        sb.append(",");
        sb.append(view.getHeight());
        sb.append(Operators.ARRAY_END_STR);
        if (view instanceof TextView) {
            sb.append(", text=\"");
            sb.append(((TextView) view).getText());
            sb.append("\"");
        }
        return sb.toString();
    }

    @NonNull
    private static String b(@Nullable Resources resources, @NonNull View view) {
        if (resources == null) {
            return "";
        }
        try {
            return " / " + resources.getResourceEntryName(view.getId());
        } catch (Throwable unused) {
            return "";
        }
    }
}
