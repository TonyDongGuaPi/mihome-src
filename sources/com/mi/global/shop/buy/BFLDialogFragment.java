package com.mi.global.shop.buy;

import android.support.annotation.LayoutRes;
import android.view.View;
import com.mi.global.shop.voice.dialog.BaseDialogFragment;
import com.mi.global.shop.voice.dialog.ViewConvertListener;
import com.mi.global.shop.voice.dialog.ViewHolder;
import com.mobikwik.sdk.lib.Constants;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0016J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\f\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/mi/global/shop/buy/BFLDialogFragment;", "Lcom/mi/global/shop/voice/dialog/BaseDialogFragment;", "()V", "mConvertListener", "Lcom/mi/global/shop/voice/dialog/ViewConvertListener;", "convertView", "", "holder", "Lcom/mi/global/shop/voice/dialog/ViewHolder;", "dialogFragment", "setConvertListener", "convertListener", "setLayoutId", "layoutId", "", "setUpLayoutId", "Companion", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLDialogFragment extends BaseDialogFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final Companion f6788a = new Companion((DefaultConstructorMarker) null);
    private ViewConvertListener c;
    private HashMap d;

    public View b(int i) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        View view = (View) this.d.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this.d.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void b() {
        if (this.d != null) {
            this.d.clear();
        }
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        b();
    }

    public int a() {
        return c();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/mi/global/shop/buy/BFLDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/mi/global/shop/buy/BFLDialogFragment;", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BFLDialogFragment a() {
            return new BFLDialogFragment();
        }
    }

    public void a(@NotNull ViewHolder viewHolder, @NotNull BaseDialogFragment baseDialogFragment) {
        Intrinsics.f(viewHolder, Constants.HOLDER);
        Intrinsics.f(baseDialogFragment, "dialogFragment");
        ViewConvertListener viewConvertListener = this.c;
        if (viewConvertListener != null) {
            try {
                viewConvertListener.a(viewHolder, baseDialogFragment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NotNull
    public final BFLDialogFragment a(@LayoutRes int i) {
        c(i);
        return this;
    }

    @NotNull
    public final BFLDialogFragment a(@NotNull ViewConvertListener viewConvertListener) {
        Intrinsics.f(viewConvertListener, "convertListener");
        this.c = viewConvertListener;
        return this;
    }
}
