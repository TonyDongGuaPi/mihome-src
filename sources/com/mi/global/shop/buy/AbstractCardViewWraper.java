package com.mi.global.shop.buy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.util.ClickUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import java.lang.reflect.Field;
import java.util.Calendar;

public abstract class AbstractCardViewWraper {

    /* renamed from: a  reason: collision with root package name */
    protected Context f6776a;
    protected View b;
    protected int c;
    protected int d;
    protected CustomEditTextView e;
    protected CustomEditTextView f;
    protected CustomEditTextView g;
    protected CustomEditTextView h;
    protected CheckBox i;
    protected boolean j = true;
    protected boolean k = false;
    protected boolean l = false;
    /* access modifiers changed from: private */
    public boolean m;
    private ImageView n;
    /* access modifiers changed from: private */
    public ImageView o;
    /* access modifiers changed from: private */
    public CustomTextView p;
    /* access modifiers changed from: private */
    public CustomTextView q;
    private CommonButton r;
    /* access modifiers changed from: private */
    public String s = "";
    /* access modifiers changed from: private */
    public boolean t;

    /* access modifiers changed from: protected */
    public abstract void c();

    /* access modifiers changed from: protected */
    public abstract void d();

    public AbstractCardViewWraper(Context context, View view, String str, boolean z) {
        this.f6776a = context;
        this.b = view;
        this.s = str;
        this.t = z;
        a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.h = (CustomEditTextView) this.b.findViewById(R.id.card_number);
        this.p = (CustomTextView) this.b.findViewById(R.id.card_number_not_support);
        this.q = (CustomTextView) this.b.findViewById(R.id.card_optional_expiry_cvv);
        this.g = (CustomEditTextView) this.b.findViewById(R.id.card_name);
        this.e = (CustomEditTextView) this.b.findViewById(R.id.card_expiry);
        this.f = (CustomEditTextView) this.b.findViewById(R.id.card_cvv);
        this.o = (ImageView) this.b.findViewById(R.id.card_cvv_image);
        this.i = (CheckBox) this.b.findViewById(R.id.store_card);
        this.i.setChecked(true);
        this.i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MiShopStatInterface.b("store_card", "ConfirmActivity", "", "checked", String.valueOf(z));
            }
        });
        this.r = (CommonButton) this.b.findViewById(R.id.pay_order);
        this.n = (ImageView) this.b.findViewById(R.id.store_card_question);
        this.e.setFocusable(false);
        this.r.setEnabled(false);
        e();
        f();
    }

    private void e() {
        if (this.t) {
            this.g.setHint("Name on card（Optional）");
            this.e.setHint("Expiry（Optional）");
            this.f.setHint("CVV（Optional）");
        } else {
            this.g.setHint("Name on card");
            this.e.setHint("Expiry");
            this.f.setHint("CVV");
        }
        a((CustomEditTextView) null, (Drawable) null);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.t = z;
        e();
        f();
    }

    private void f() {
        this.h.addTextChangedListener(new FourDigitCardFormatWatcher() {
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String replace = AbstractCardViewWraper.this.h.getText().toString().replace(" ", "");
                String c = Cards.c(replace);
                if (c.contentEquals("AMEX")) {
                    AbstractCardViewWraper.this.f.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                    AbstractCardViewWraper.this.o.setImageResource(R.drawable.shop_cvv1234);
                } else {
                    AbstractCardViewWraper.this.f.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                    AbstractCardViewWraper.this.o.setImageResource(R.drawable.shop_cvv123);
                }
                if (c.contentEquals("SMAE")) {
                    if (!PayU.aw.contains(c)) {
                        AbstractCardViewWraper.this.q.setVisibility(0);
                    }
                    AbstractCardViewWraper.this.l = true;
                } else {
                    AbstractCardViewWraper.this.q.setVisibility(8);
                    AbstractCardViewWraper.this.l = false;
                }
                if (Cards.k.contentEquals(c) || !PayU.aw.contains(c) || AbstractCardViewWraper.this.t) {
                    AbstractCardViewWraper.this.p.setVisibility(8);
                } else {
                    AbstractCardViewWraper.this.p.setVisibility(0);
                    AbstractCardViewWraper.this.p.setText(AbstractCardViewWraper.this.f6776a.getString(R.string.buy_confirm_card_notsupport));
                }
                boolean unused = AbstractCardViewWraper.this.m = false;
                if (c == null || Cards.l.get(c) == null) {
                    AbstractCardViewWraper.this.h.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                } else {
                    AbstractCardViewWraper.this.h.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, Cards.l.get(c), (Drawable) null);
                }
                if (Cards.a(replace).booleanValue()) {
                    boolean unused2 = AbstractCardViewWraper.this.m = true;
                } else {
                    boolean unused3 = AbstractCardViewWraper.this.m = false;
                }
                if (AbstractCardViewWraper.this.t) {
                    if (AbstractCardViewWraper.this.m || replace.length() <= 0) {
                        AbstractCardViewWraper.this.p.setVisibility(8);
                    } else {
                        AbstractCardViewWraper.this.p.setVisibility(0);
                        AbstractCardViewWraper.this.p.setText("Enter a valid number");
                    }
                }
                AbstractCardViewWraper.this.a(AbstractCardViewWraper.this.h, (Drawable) null);
            }
        });
        this.h.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                MiShopStatInterface.a("card_number_click", AbstractCardViewWraper.this.s);
                return false;
            }
        });
        this.g.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                MiShopStatInterface.a("name_click", AbstractCardViewWraper.this.s);
                return false;
            }
        });
        this.f.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                MiShopStatInterface.a("cvv_click", AbstractCardViewWraper.this.s);
                return false;
            }
        });
        this.f.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.toString();
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (Cards.a(AbstractCardViewWraper.this.h.getText().toString().replace(" ", ""), charSequence.toString())) {
                    AbstractCardViewWraper.this.k = true;
                    AbstractCardViewWraper.this.a(AbstractCardViewWraper.this.f, (Drawable) null);
                    return;
                }
                AbstractCardViewWraper.this.k = false;
                AbstractCardViewWraper.this.b(AbstractCardViewWraper.this.f, (Drawable) null);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiShopStatInterface.a("expiry_click", AbstractCardViewWraper.this.s);
                final Dialog dialog = new Dialog(AbstractCardViewWraper.this.f6776a, R.style.ProgressDialog);
                View inflate = LayoutInflater.from(AbstractCardViewWraper.this.f6776a).inflate(R.layout.shop_date_picker, (ViewGroup) null);
                dialog.setContentView(inflate);
                dialog.setCancelable(false);
                CustomButtonView customButtonView = (CustomButtonView) inflate.findViewById(R.id.datePickerOkButton);
                final DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.datePicker);
                Calendar instance = Calendar.getInstance();
                int i = instance.get(5);
                int i2 = instance.get(2);
                datePicker.setMinDate(instance.getTimeInMillis() - 1000);
                instance.set(instance.get(1) + 50, i2, i);
                datePicker.setMaxDate(instance.getTimeInMillis());
                try {
                    ((ViewGroup) ((ViewGroup) datePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(8);
                    for (Field field : datePicker.getClass().getDeclaredFields()) {
                        if ("mDayPicker".equals(field.getName()) || "mDaySpinner".equals(field.getName()) || "mDelegate".equals(field.getName())) {
                            field.setAccessible(true);
                            ((View) field.get(datePicker)).setVisibility(8);
                        }
                    }
                } catch (Exception unused) {
                }
                dialog.show();
                customButtonView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        AbstractCardViewWraper.this.d = datePicker.getMonth() + 1;
                        AbstractCardViewWraper.this.c = datePicker.getYear();
                        CustomEditTextView customEditTextView = AbstractCardViewWraper.this.e;
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(AbstractCardViewWraper.this.d < 10 ? "0" : "");
                        sb.append(AbstractCardViewWraper.this.d);
                        sb.append(" / ");
                        sb.append(AbstractCardViewWraper.this.c);
                        customEditTextView.setText(sb.toString());
                        AbstractCardViewWraper.this.j = false;
                        AbstractCardViewWraper.this.a(AbstractCardViewWraper.this.e, (Drawable) null);
                        dialog.dismiss();
                    }
                });
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!ClickUtil.a()) {
                    if (AbstractCardViewWraper.this.t) {
                        AbstractCardViewWraper.this.c();
                        return;
                    }
                    MiShopStatInterface.a("pay_click", AbstractCardViewWraper.this.s, "channl", EMIfragment.f6830a);
                    AbstractCardViewWraper.this.d();
                }
            }
        });
        AnonymousClass9 r0 = new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AbstractCardViewWraper.this.g();
                }
            }
        };
        this.h.setOnFocusChangeListener(r0);
        this.g.setOnFocusChangeListener(r0);
        this.e.setOnFocusChangeListener(r0);
        this.f.setOnFocusChangeListener(r0);
        this.n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiShopStatInterface.a("card_details_click", AbstractCardViewWraper.this.s);
                AlertDialog.Builder builder = new AlertDialog.Builder(AbstractCardViewWraper.this.f6776a);
                builder.setMessage(ShopApp.g().getString(R.string.buy_confirm_credit_qcontent));
                builder.setPositiveButton("OK", (DialogInterface.OnClickListener) null);
                builder.show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(CustomEditTextView customEditTextView, Drawable drawable) {
        if (!(customEditTextView == null || drawable == null)) {
            drawable.setAlpha(255);
            customEditTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        }
        if (b()) {
            this.r.setEnabled(true);
        } else {
            this.r.setEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        if (this.t) {
            return this.m;
        }
        return this.m && (this.l || (!this.j && this.k));
    }

    /* access modifiers changed from: private */
    public void b(CustomEditTextView customEditTextView, Drawable drawable) {
        if (drawable != null) {
            drawable.setAlpha(100);
            customEditTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        }
        this.r.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (!this.m && this.h.getText().toString().length() > 0 && !this.h.isFocused()) {
            this.h.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.f6776a.getResources().getDrawable(R.drawable.shop_error_icon), (Drawable) null);
        }
        if (!this.k && this.f.getText().toString().length() > 0 && !this.f.isFocused()) {
            this.f.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.f6776a.getResources().getDrawable(R.drawable.shop_error_icon), (Drawable) null);
        }
    }
}
