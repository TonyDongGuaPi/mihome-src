package com.xiaomi.passport.ui.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.passport.ui.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0017J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010\u0010\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0007¨\u0006\u0012"}, d2 = {"com/xiaomi/passport/ui/internal/PhoneViewWrapper$setPhonePopList$adapter$1", "Landroid/widget/ArrayAdapter;", "", "(Lcom/xiaomi/passport/ui/internal/PhoneViewWrapper;Ljava/util/List;Ljava/util/ArrayList;Landroid/content/Context;ILjava/util/List;)V", "getView", "Landroid/view/View;", "position", "", "convertView", "parent", "Landroid/view/ViewGroup;", "initView", "", "view", "Landroid/widget/AutoCompleteTextView;", "setFirstItemToView", "setItemToView", "id", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhoneViewWrapper$setPhonePopList$adapter$1 extends ArrayAdapter<String> {
    final /* synthetic */ List $activateInfoList;
    final /* synthetic */ ArrayList $markedPhoneList;
    final /* synthetic */ PhoneViewWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhoneViewWrapper$setPhonePopList$adapter$1(PhoneViewWrapper phoneViewWrapper, List list, ArrayList arrayList, Context context, int i, List list2) {
        super(context, i, list2);
        this.this$0 = phoneViewWrapper;
        this.$activateInfoList = list;
        this.$markedPhoneList = arrayList;
    }

    @NotNull
    @SuppressLint({"ViewHolder"})
    public View getView(int i, @Nullable View view, @Nullable ViewGroup viewGroup) {
        View inflate = View.inflate(getContext(), R.layout.phone_list_item, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.image_sim_index);
        if (findViewById != null) {
            ImageView imageView = (ImageView) findViewById;
            if (i == 0) {
                imageView.setImageResource(R.drawable.passport_sim1);
            }
            if (i == 1) {
                imageView.setImageResource(R.drawable.passport_sim2);
            }
            View findViewById2 = inflate.findViewById(16908308);
            if (findViewById2 != null) {
                ((TextView) findViewById2).setText((CharSequence) getItem(i));
                Intrinsics.b(inflate, "view");
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
    }

    public final void setItemToView(@Nullable AutoCompleteTextView autoCompleteTextView, int i) {
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setText((CharSequence) getItem(i));
        }
        if (autoCompleteTextView != null) {
            autoCompleteTextView.dismissDropDown();
        }
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setEnabled(false);
        }
        TextView passport_operator_license = this.this$0.getPassport_operator_license();
        if (passport_operator_license != null) {
            passport_operator_license.setText(((ActivatorPhoneInfo) this.$activateInfoList.get(i)).copyWriter);
        }
        TextView passport_operator_license2 = this.this$0.getPassport_operator_license();
        if (passport_operator_license2 != null) {
            passport_operator_license2.setVisibility(0);
        }
    }

    public final void setFirstItemToView(@Nullable AutoCompleteTextView autoCompleteTextView) {
        if (getCount() > 0) {
            setItemToView(autoCompleteTextView, 0);
        }
    }

    public final void initView(@Nullable AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setAdapter(this);
        }
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setThreshold(0);
        }
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnItemClickListener(new PhoneViewWrapper$setPhonePopList$adapter$1$initView$1(this, autoCompleteTextView));
        }
        if (TextUtils.isEmpty(autoCompleteTextView != null ? autoCompleteTextView.getText() : null)) {
            setFirstItemToView(autoCompleteTextView);
        }
    }
}
