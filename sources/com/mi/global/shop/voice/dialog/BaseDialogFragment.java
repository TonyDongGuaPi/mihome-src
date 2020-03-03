package com.mi.global.shop.voice.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.mi.global.shop.voice.dialog.ViewHolder;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 @2\u00020\u0001:\u0001@B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0000H&J\u001a\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u0012\u0010\u001d\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0012\u0010\u001f\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010#\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J&\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020!H\u0016J\b\u0010,\u001a\u00020\u0016H\u0016J\u0012\u0010-\u001a\u00020\u00002\b\b\u0001\u0010.\u001a\u00020\u0004H\u0016J\u0018\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004H\u0016J\u0010\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u0004H\u0016J\u0010\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\tH\u0016J\u0010\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u00020\u0004H\u0016J\u0010\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0012H\u0016J\u0010\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u0012H\u0016J\b\u0010<\u001a\u00020\u0004H&J\u0010\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020?H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\u00048\u0004@\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/mi/global/shop/voice/dialog/BaseDialogFragment;", "Landroid/support/v4/app/DialogFragment;", "()V", "mAnimStyle", "", "mContext", "Landroid/content/Context;", "mDialogStyle", "mDimAmount", "", "mHeight", "mLayoutResId", "getMLayoutResId", "()I", "setMLayoutResId", "(I)V", "mMargin", "mOutCancel", "", "mShowBottomEnable", "mWidth", "convertView", "", "holder", "Lcom/mi/global/shop/voice/dialog/ViewHolder;", "dialogFragment", "dp2px", "context", "dipValue", "getScreenWidth", "initParams", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onSaveInstanceState", "outState", "onStart", "setAnimStyle", "animStyle", "setDialogSize", "width", "height", "setDialogStyle", "theme", "setDimAmount", "dimAmount", "setMargin", "margin", "setOutCancel", "outCancel", "setShowBottom", "showBottom", "setUpLayoutId", "show", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "Companion", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public abstract class BaseDialogFragment extends DialogFragment {
    public static final Companion b = new Companion((DefaultConstructorMarker) null);
    private static final String l = "BaseDialogFragment";
    @LayoutRes

    /* renamed from: a  reason: collision with root package name */
    private int f7137a;
    private boolean c = true;
    private boolean d;
    private Context e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private float k = 0.5f;
    private HashMap m;

    public abstract int a();

    public abstract void a(@NotNull ViewHolder viewHolder, @NotNull BaseDialogFragment baseDialogFragment);

    public View b(int i2) {
        if (this.m == null) {
            this.m = new HashMap();
        }
        View view = (View) this.m.get(Integer.valueOf(i2));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i2);
        this.m.put(Integer.valueOf(i2), findViewById);
        return findViewById;
    }

    public void b() {
        if (this.m != null) {
            this.m.clear();
        }
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        b();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mi/global/shop/voice/dialog/BaseDialogFragment$Companion;", "", "()V", "TAG", "", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public final int c() {
        return this.f7137a;
    }

    /* access modifiers changed from: protected */
    public final void c(int i2) {
        this.f7137a = i2;
    }

    public void onAttach(@Nullable Context context) {
        Log.e(l, "BaseDialogFragment.onAttach() called. ");
        super.onAttach(context);
        this.e = context;
    }

    public void onCreate(@Nullable Bundle bundle) {
        Log.e(l, "BaseDialogFragment.onCreate() called. ");
        super.onCreate(bundle);
        if (this.i != 0) {
            setStyle(1, this.i);
        }
        this.f7137a = a();
    }

    public void onSaveInstanceState(@NotNull Bundle bundle) {
        Intrinsics.f(bundle, "outState");
        bundle.putInt("layoutId", this.f7137a);
        bundle.putInt("dialogStyle", this.i);
        super.onSaveInstanceState(bundle);
        Log.e(l, "BaseDialogFragment.onSaveInstanceState() called. ");
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Log.e(l, "BaseDialogFragment.onActivityCreated() called.  savedInstanceState = " + bundle);
        super.onActivityCreated(bundle);
        d();
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Dialog dialog;
        Window window;
        Intrinsics.f(layoutInflater, "inflater");
        Log.e(l, "BaseDialogFragment.onCreateView() called. savedInstanceState = " + bundle);
        if (!(this.i != 0 || (dialog = getDialog()) == null || (window = dialog.getWindow()) == null)) {
            window.setBackgroundDrawableResource(17170445);
        }
        if (bundle != null) {
            this.f7137a = bundle.getInt("layoutId");
            this.i = bundle.getInt("dialogStyle");
        }
        View inflate = layoutInflater.inflate(this.f7137a, viewGroup, false);
        ViewHolder.Companion companion = ViewHolder.f7138a;
        Intrinsics.b(inflate, "rootView");
        a(companion.a(inflate), this);
        return inflate;
    }

    public void onStart() {
        Log.e(l, "BaseDialogFragment.onStart() called. ");
        super.onStart();
    }

    private final void d() {
        Dialog dialog = getDialog();
        Intrinsics.b(dialog, "dialog");
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = this.k;
            if (this.f == 0) {
                attributes.width = a(getContext()) - (a(getContext(), (float) this.j) * 2);
            } else {
                attributes.width = a(getContext(), (float) this.f);
            }
            if (this.g != 0) {
                attributes.height = a(getContext(), (float) this.g);
            } else if (this.i == 0) {
                attributes.height = -2;
            } else {
                attributes.height = -1;
            }
            Log.e(l, "BaseDialogFragment.initParams() called. height = " + attributes.height + "  width = " + attributes.width + "  mDialogStyle = " + this.i);
            if (this.d) {
                attributes.gravity = 80;
            }
            if (this.h != 0) {
                window.setWindowAnimations(this.h);
            }
            window.setAttributes(attributes);
        }
        setCancelable(this.c);
    }

    @NotNull
    public BaseDialogFragment a(float f2) {
        this.k = f2;
        return this;
    }

    @NotNull
    public BaseDialogFragment a(boolean z) {
        this.d = z;
        return this;
    }

    @NotNull
    public BaseDialogFragment a(int i2, int i3) {
        this.f = i2;
        this.g = i3;
        return this;
    }

    @NotNull
    public BaseDialogFragment d(int i2) {
        this.j = i2;
        return this;
    }

    @NotNull
    public BaseDialogFragment e(@StyleRes int i2) {
        this.h = i2;
        return this;
    }

    @NotNull
    public BaseDialogFragment b(boolean z) {
        this.c = z;
        return this;
    }

    @NotNull
    public BaseDialogFragment f(int i2) {
        this.i = i2;
        return this;
    }

    @NotNull
    public BaseDialogFragment a(@NotNull FragmentManager fragmentManager) {
        Intrinsics.f(fragmentManager, "fragmentManager");
        try {
            super.show(fragmentManager, "");
        } catch (Exception unused) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            Intrinsics.b(beginTransaction, "fragmentManager.beginTransaction()");
            beginTransaction.add((Fragment) this, "");
            beginTransaction.commitAllowingStateLoss();
        }
        return this;
    }

    private final int a(Context context) {
        if (context == null) {
            Intrinsics.a();
        }
        Resources resources = context.getResources();
        Intrinsics.b(resources, "context!!.resources");
        return resources.getDisplayMetrics().widthPixels;
    }

    private final int a(Context context, float f2) {
        if (context == null) {
            Intrinsics.a();
        }
        Resources resources = context.getResources();
        Intrinsics.b(resources, "context!!.resources");
        return (int) ((f2 * resources.getDisplayMetrics().density) + 0.5f);
    }
}
