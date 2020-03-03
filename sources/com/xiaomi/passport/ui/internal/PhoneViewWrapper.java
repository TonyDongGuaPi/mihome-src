package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.passport.ui.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\rJ\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u0004\u0018\u00010\"J\b\u0010#\u001a\u00020$H\u0002J\u0016\u0010%\u001a\u00020 2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006&"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhoneViewWrapper;", "", "sid", "", "context", "Landroid/content/Context;", "phone", "Landroid/widget/AutoCompleteTextView;", "countryCode", "Landroid/widget/TextView;", "deletePhone", "Landroid/view/View;", "passport_operator_license", "(Ljava/lang/String;Landroid/content/Context;Landroid/widget/AutoCompleteTextView;Landroid/widget/TextView;Landroid/view/View;Landroid/widget/TextView;)V", "activateInfoList", "", "Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;", "getContext", "()Landroid/content/Context;", "getDeletePhone", "()Landroid/view/View;", "passportRepo", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "getPassport_operator_license", "()Landroid/widget/TextView;", "getPhone", "()Landroid/widget/AutoCompleteTextView;", "phoneTextWatcher", "Landroid/text/TextWatcher;", "getSid", "()Ljava/lang/String;", "destroy", "", "getPhoneWrapper", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "isInputValid", "", "setPhonePopList", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhoneViewWrapper {
    /* access modifiers changed from: private */
    public List<? extends ActivatorPhoneInfo> activateInfoList;
    @NotNull
    private final Context context;
    private final TextView countryCode;
    @NotNull
    private final View deletePhone;
    private PassportRepo passportRepo = new PassportRepoImpl();
    @Nullable
    private final TextView passport_operator_license;
    @NotNull
    private final AutoCompleteTextView phone;
    private TextWatcher phoneTextWatcher;
    @NotNull
    private final String sid;

    public PhoneViewWrapper(@NotNull String str, @NotNull Context context2, @NotNull AutoCompleteTextView autoCompleteTextView, @NotNull TextView textView, @NotNull View view, @Nullable TextView textView2) {
        Intrinsics.f(str, "sid");
        Intrinsics.f(context2, "context");
        Intrinsics.f(autoCompleteTextView, "phone");
        Intrinsics.f(textView, Constant.KEY_COUNTRY_CODE);
        Intrinsics.f(view, "deletePhone");
        this.sid = str;
        this.context = context2;
        this.phone = autoCompleteTextView;
        this.countryCode = textView;
        this.deletePhone = view;
        this.passport_operator_license = textView2;
        if (!PassportUI.INSTANCE.getInternational()) {
            this.passportRepo.getLocalActivatorPhone(this.context, true).getSuccess(new Function1<List<? extends ActivatorPhoneInfo>, Unit>(this) {
                final /* synthetic */ PhoneViewWrapper this$0;

                {
                    this.this$0 = r1;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List<? extends ActivatorPhoneInfo>) (List) obj);
                    return Unit.f2693a;
                }

                public final void invoke(@NotNull List<? extends ActivatorPhoneInfo> list) {
                    Intrinsics.f(list, "it");
                    this.this$0.activateInfoList = list;
                    this.this$0.setPhonePopList(list);
                }
            });
        }
        this.phoneTextWatcher = new TextWatcher(this) {
            final /* synthetic */ PhoneViewWrapper this$0;

            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                this.this$0 = r1;
            }

            public void afterTextChanged(@Nullable Editable editable) {
                if (TextUtils.isEmpty(editable != null ? editable.toString() : null)) {
                    this.this$0.getDeletePhone().setVisibility(8);
                    TextView passport_operator_license = this.this$0.getPassport_operator_license();
                    if (passport_operator_license != null) {
                        passport_operator_license.setVisibility(8);
                        return;
                    }
                    return;
                }
                this.this$0.getDeletePhone().setVisibility(0);
            }
        };
        this.phone.addTextChangedListener(this.phoneTextWatcher);
        this.deletePhone.setVisibility(8);
        this.deletePhone.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ PhoneViewWrapper this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                this.this$0.getPhone().setEnabled(true);
                this.this$0.getPhone().setText("");
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final AutoCompleteTextView getPhone() {
        return this.phone;
    }

    @NotNull
    public final String getSid() {
        return this.sid;
    }

    @NotNull
    public final View getDeletePhone() {
        return this.deletePhone;
    }

    @Nullable
    public final TextView getPassport_operator_license() {
        return this.passport_operator_license;
    }

    private final boolean isInputValid() {
        return !TextUtils.isEmpty(this.phone.getText().toString());
    }

    @Nullable
    public final PhoneWrapper getPhoneWrapper() {
        if (!isInputValid()) {
            return null;
        }
        String obj = this.phone.getText().toString();
        if (this.activateInfoList != null) {
            List<? extends ActivatorPhoneInfo> list = this.activateInfoList;
            if (list == null) {
                Intrinsics.a();
            }
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                if (Intrinsics.a((Object) ((ActivatorPhoneInfo) next).phone, (Object) obj)) {
                    arrayList.add(next);
                }
            }
            Iterator it = ((List) arrayList).iterator();
            if (it.hasNext()) {
                return new PhoneWrapper((ActivatorPhoneInfo) it.next(), this.sid);
            }
        }
        if (Intrinsics.a((Object) this.countryCode.getText().toString(), (Object) PassportUI.CHINA_COUNTRY_CODE)) {
            return new PhoneWrapper(obj, this.sid);
        }
        return new PhoneWrapper(this.countryCode.getText().toString() + obj, this.sid);
    }

    public final void destroy() {
        this.phone.removeTextChangedListener(this.phoneTextWatcher);
        this.phoneTextWatcher = null;
    }

    /* access modifiers changed from: private */
    public final void setPhonePopList(List<? extends ActivatorPhoneInfo> list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (ActivatorPhoneInfo activatorPhoneInfo : list) {
                String str = activatorPhoneInfo.phone;
                Intrinsics.b(str, "it.phone");
                arrayList.add(str);
            }
            Collection collection = arrayList;
            new PhoneViewWrapper$setPhonePopList$adapter$1(this, list, arrayList, this.context, R.layout.phone_list_item, arrayList).initView(this.phone);
        }
    }
}
