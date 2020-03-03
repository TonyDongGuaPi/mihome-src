package com.mi.global.shop.voice.dialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\b\u001a\u0004\u0018\u0001H\t\"\b\b\u0000\u0010\t*\u00020\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u001a\u0010\u0010\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0013\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bJ\u001e\u0010\u0018\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aJ\u0018\u0010\u001b\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010\u001b\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0018\u0010 \u001a\u00020\u000e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u0015R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/mi/global/shop/voice/dialog/ViewHolder;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "mConvertView", "mViews", "Landroid/util/SparseArray;", "getView", "T", "viewId", "", "(I)Landroid/view/View;", "setBackgroundColor", "", "colorId", "setBackgroundDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setEnabled", "isEnable", "", "setImageSrcDrawable", "drawableId", "setOnClickListener", "listenerFun", "Lkotlin/Function0;", "setText", "text", "Landroid/text/SpannableString;", "", "setTextColor", "setVisibility", "isVisible", "Companion", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class ViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public static final Companion f7138a = new Companion((DefaultConstructorMarker) null);
    private View b;
    private SparseArray<View> c = new SparseArray<>();

    public ViewHolder(@NotNull View view) {
        Intrinsics.f(view, "view");
        this.b = view;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/mi/global/shop/voice/dialog/ViewHolder$Companion;", "", "()V", "create", "Lcom/mi/global/shop/voice/dialog/ViewHolder;", "view", "Landroid/view/View;", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ViewHolder a(@NotNull View view) {
            Intrinsics.f(view, "view");
            return new ViewHolder(view);
        }
    }

    @Nullable
    public final <T extends View> T a(@IdRes int i) {
        SparseArray<View> sparseArray = this.c;
        T t = sparseArray != null ? (View) sparseArray.get(i) : null;
        if (t == null) {
            View view = this.b;
            t = view != null ? view.findViewById(i) : null;
            SparseArray<View> sparseArray2 = this.c;
            if (sparseArray2 != null) {
                sparseArray2.put(i, t);
            }
        }
        if (!(t instanceof View)) {
            return null;
        }
        return t;
    }

    public final void a(@IdRes int i, @NotNull String str) {
        Intrinsics.f(str, "text");
        TextView textView = (TextView) a(i);
        if (textView != null) {
            textView.setText(str);
        }
    }

    public final void a(@IdRes int i, @NotNull SpannableString spannableString) {
        Intrinsics.f(spannableString, "text");
        TextView textView = (TextView) a(i);
        if (textView != null) {
            textView.setText(spannableString);
        }
    }

    public final void a(@IdRes int i, int i2) {
        TextView textView = (TextView) a(i);
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public final void b(@IdRes int i, int i2) {
        View a2 = a(i);
        if (a2 != null) {
            a2.setBackgroundColor(i2);
        }
    }

    public final void c(@IdRes int i, int i2) {
        ImageView imageView = (ImageView) a(i);
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
    }

    @SuppressLint({"NewApi"})
    public final void a(@IdRes int i, @NotNull Drawable drawable) {
        Intrinsics.f(drawable, "drawable");
        View a2 = a(i);
        if (a2 != null) {
            a2.setBackground(drawable);
        }
    }

    public final void a(@IdRes int i, boolean z) {
        View a2 = a(i);
        if (a2 != null) {
            a2.setVisibility(z ? 0 : 8);
        }
    }

    public final void b(@IdRes int i, boolean z) {
        View a2 = a(i);
        if (a2 != null) {
            a2.setEnabled(z);
        }
    }

    public final void a(@IdRes int i, @NotNull Function0<Unit> function0) {
        Intrinsics.f(function0, "listenerFun");
        View a2 = a(i);
        if (a2 != null) {
            a2.setOnClickListener(new ViewHolder$setOnClickListener$1(function0));
        }
    }
}
