package com.mobikwik.sdk.ui.frag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;
import com.mobikwik.sdk.lib.utils.UIFunctions;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.ui.data.b;
import com.mobikwik.sdk.ui.util.l;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.util.ArrayList;
import java.util.Calendar;

public class a extends aj implements View.OnClickListener, View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    private Spinner f8381a;
    private Spinner s;
    /* access modifiers changed from: private */
    public EditText t;
    private String u;
    /* access modifiers changed from: private */
    public EditText v;
    private EditText w;
    private EditText x;

    /* access modifiers changed from: private */
    public boolean a(boolean z) {
        String str;
        boolean equalsIgnoreCase = "maestro".equalsIgnoreCase(this.q);
        EditText editText = (EditText) getView().findViewById(R.id.editText_cvv);
        String obj = editText.getText().toString();
        editText.setBackgroundResource(R.drawable.mk_edit_text_bg);
        if (obj.length() == 0) {
            if (!equalsIgnoreCase) {
                if (!z) {
                    str = "Enter CVV";
                }
                editText.setBackgroundResource(R.drawable.mk_cvv_text_bg);
                this.h++;
                return false;
            }
            this.g = obj;
            return true;
        }
        if (obj.length() < 3 || ("amex".equalsIgnoreCase(this.q) && obj.length() != 4)) {
            if (!z) {
                str = "Please enter valid CVV";
            }
            editText.setBackgroundResource(R.drawable.mk_cvv_text_bg);
            this.h++;
            return false;
        }
        this.g = obj;
        return true;
        editText.setError(str);
        editText.requestFocus();
        editText.setBackgroundResource(R.drawable.mk_cvv_text_bg);
        this.h++;
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r6 == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0054, code lost:
        if (r6 == false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(boolean r6) {
        /*
            r5 = this;
            r0 = 0
            r5.h = r0
            android.widget.EditText r1 = r5.v
            android.text.Editable r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            boolean r2 = com.mobikwik.sdk.lib.utils.Utils.isNull(r1)
            r3 = 1
            if (r2 != 0) goto L_0x0057
            java.lang.String r2 = r1.trim()
            int r2 = r2.length()
            if (r2 != 0) goto L_0x001f
            goto L_0x0057
        L_0x001f:
            int r2 = r1.length()
            r4 = 13
            if (r2 < r4) goto L_0x0054
            int r2 = r1.length()
            r4 = 19
            if (r2 > r4) goto L_0x0054
            java.lang.String r2 = com.mobikwik.sdk.lib.utils.CardUtils.detectType(r1)
            r5.q = r2
            r5.f = r1
            java.lang.String r1 = r5.f
            boolean r1 = com.mobikwik.sdk.lib.utils.CardUtils.validateCardNumber(r1)
            int r2 = r5.h
            if (r2 != 0) goto L_0x0045
            if (r1 != 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            return r3
        L_0x0045:
            if (r6 != 0) goto L_0x004e
        L_0x0047:
            android.widget.EditText r6 = r5.v
            java.lang.String r1 = "Invalid card number"
            r6.setError(r1)
        L_0x004e:
            int r6 = r5.h
            int r6 = r6 + r3
            r5.h = r6
            return r0
        L_0x0054:
            if (r6 != 0) goto L_0x004e
            goto L_0x0047
        L_0x0057:
            if (r6 != 0) goto L_0x004e
            android.widget.EditText r6 = r5.v
            java.lang.String r1 = "Enter card number"
            r6.setError(r1)
            android.widget.EditText r6 = r5.v
            r6.requestFocus()
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.ui.frag.a.b(boolean):boolean");
    }

    private void c() {
        if (b(false)) {
            boolean equalsIgnoreCase = "maestro".equalsIgnoreCase(this.q);
            Spinner spinner = (Spinner) getView().findViewById(R.id.spinner_month);
            String obj = spinner.getSelectedItem().toString();
            String obj2 = ((Spinner) getView().findViewById(R.id.spinner_year)).getSelectedItem().toString();
            if (equalsIgnoreCase) {
                if (obj.compareTo("Month") == 0 && obj2.compareTo("Year") != 0) {
                    this.w.setError("Select month");
                    this.w.requestFocus();
                    this.h++;
                    return;
                } else if (obj.compareTo("Month") == 0 || obj2.compareTo("Year") != 0) {
                    int i = Calendar.getInstance().get(2) + 1;
                    int i2 = Calendar.getInstance().get(1) - 2000;
                    if (spinner.getSelectedItemPosition() < i) {
                        if (obj2.compareTo(UserConfig.g + i2) == 0) {
                            this.w.setError("Select month");
                            this.w.requestFocus();
                            this.h++;
                            return;
                        }
                    }
                    if (obj.compareTo("Month") == 0 && obj2.compareTo("Year") == 0) {
                        obj = "";
                        obj2 = "";
                    }
                } else {
                    this.x.setError("Select year");
                    this.x.requestFocus();
                    this.h++;
                    return;
                }
            } else if (obj.compareTo("Month") == 0) {
                this.w.setError("Select month");
                this.w.requestFocus();
                this.h++;
                return;
            } else if (obj2.compareTo("Year") == 0) {
                this.x.setError("Select year");
                this.x.requestFocus();
                this.h++;
                return;
            } else {
                int i3 = Calendar.getInstance().get(2) + 1;
                int i4 = Calendar.getInstance().get(1) - 2000;
                if (spinner.getSelectedItemPosition() < i3) {
                    if (obj2.compareTo(UserConfig.g + i4) == 0) {
                        this.w.setError("Select month");
                        this.w.requestFocus();
                        this.h++;
                        return;
                    }
                }
            }
            if (this.h != 0) {
                UIFunctions.showToast(this.k, "Invalid card details. Please correct the errors.");
                return;
            }
            this.b = obj;
            if (obj2 != null && obj2.length() == 4) {
                obj2 = obj2.substring(2);
            }
            this.c = obj2;
            if (a(false)) {
                this.j = ((CheckBox) getView().findViewById(R.id.cbSaveCardDetails)).isChecked();
            }
        }
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.u;
    }

    public void a(View view) {
        c();
        if (this.h == 0) {
            b();
        }
    }

    public void onClick(View view) {
        Utils.hideKeyBoard(view);
        if (view.getId() != R.id.cvvHelp) {
            if (view.getId() == R.id.cardPay) {
                a(view);
                return;
            }
            view.getId();
            int i = R.id.cbSaveCardDetails;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.mk_card_layout, viewGroup, false);
        this.w = (EditText) inflate.findViewById(R.id.errorVMonth);
        this.x = (EditText) inflate.findViewById(R.id.errorVYear);
        inflate.findViewById(R.id.cvvHelp).setOnClickListener(this);
        inflate.findViewById(R.id.cardPay).setOnClickListener(this);
        inflate.findViewById(R.id.cbSaveCardDetails).setOnClickListener(this);
        l.a((TextView) inflate.findViewById(R.id.amount), this.l.getAmount(), this.p);
        this.s = (Spinner) inflate.findViewById(R.id.spinner_month);
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this.k, R.array.months, 17367043);
        createFromResource.setDropDownViewResource(17367049);
        this.s.setAdapter(createFromResource);
        int i = Calendar.getInstance().get(1) - 2000;
        ArrayList arrayList = new ArrayList();
        arrayList.add("Year");
        for (int i2 = i; i2 < i + 30; i2++) {
            arrayList.add(UserConfig.g + i2);
        }
        this.f8381a = (Spinner) inflate.findViewById(R.id.spinner_year);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.k, 17367043, arrayList);
        arrayAdapter.setDropDownViewResource(17367049);
        this.f8381a.setAdapter(arrayAdapter);
        this.t = (EditText) inflate.findViewById(R.id.editText_cvv);
        this.v = (EditText) inflate.findViewById(R.id.card_number);
        this.v.setBackgroundResource(R.drawable.mk_cvv_text_bg);
        this.v.addTextChangedListener(new b(this));
        this.f8381a.setFocusableInTouchMode(true);
        this.s.setFocusableInTouchMode(true);
        this.f8381a.setOnTouchListener(this);
        this.s.setOnTouchListener(this);
        inflate.findViewById(R.id.card_number).setOnTouchListener(this);
        this.t.setOnTouchListener(this);
        inflate.findViewById(R.id.name_on_card).setOnTouchListener(this);
        inflate.findViewById(R.id.cbSaveCardDetails).setOnTouchListener(this);
        this.t.addTextChangedListener(new c(this));
        if (!this.r && !b.b((Context) this.k).b().contains(PaymentInstrumentType.SAVED_CARD)) {
            inflate.findViewById(R.id.cbSaveCardDetails).setVisibility(8);
        }
        if (this.r || (b.b((Context) this.k).b().contains(PaymentInstrumentType.SAVED_CARD) && this.m.isSaveCardByDefault())) {
            ((CheckBox) inflate.findViewById(R.id.cbSaveCardDetails)).setChecked(true);
        }
        return inflate;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.f8381a.setSelected(false);
        this.s.setSelected(false);
        if (this.w != null) {
            this.w.setError((CharSequence) null);
        }
        if (this.x != null) {
            this.x.setError((CharSequence) null);
        }
        if (view.getId() == R.id.spinner_month) {
            Utils.hideKeyBoard(view);
            this.s.setSelected(true);
            this.s.requestFocusFromTouch();
        } else if (view.getId() == R.id.spinner_year) {
            Utils.hideKeyBoard(view);
            this.f8381a.requestFocusFromTouch();
            this.f8381a.setSelected(true);
        }
        return false;
    }
}
