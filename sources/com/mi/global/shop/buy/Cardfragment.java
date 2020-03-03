package com.mi.global.shop.buy;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.volley.Request;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.buy.payu.PayUtil;
import com.mi.global.shop.newmodel.pay.payinfo.NewUserCardsType;
import com.mi.global.shop.newmodel.pay.savecard.NewCardsBodyResult;
import com.mi.global.shop.newmodel.pay.savecard.NewCardsListData;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.SlidingButton;
import com.mi.log.LogUtil;
import com.mi.util.RequestQueueUtil;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import java.util.ArrayList;

public class Cardfragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6798a = "credit/debit card";
    private static final String l = "Cardfragment";
    private NoScrollListView b;
    private SavedCardListAdapter c;
    private ArrayList<NewUserCardsType> d;
    private View e;
    private CustomButtonView f;
    private CustomButtonView g;
    private CustomTextView h;
    private ProgressDialog i;
    /* access modifiers changed from: private */
    public RelativeLayout j;
    /* access modifiers changed from: private */
    public LinearLayout k;

    public void onCreate(Bundle bundle) {
        LogUtil.b(l, "onCreate");
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.b(l, "onCreateView");
        if (this.e == null) {
            this.e = layoutInflater.inflate(R.layout.shop_buy_confirm_payment_credit_newdetail, viewGroup, false);
            new CardViewWraper(getActivity(), this.e);
            a(this.e);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.e.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.e);
                LogUtil.b(l, "onCreateView remove from parent");
            }
        }
        c();
        return this.e;
    }

    public void a(View view) {
        this.b = (NoScrollListView) view.findViewById(R.id.cardListView);
        this.k = (LinearLayout) view.findViewById(R.id.ll_add_card);
        this.j = (RelativeLayout) view.findViewById(R.id.rl_card_list);
        this.f = (CustomButtonView) view.findViewById(R.id.btn_add);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cardfragment.this.k.setVisibility(0);
                Cardfragment.this.j.setVisibility(8);
            }
        });
        this.g = (CustomButtonView) view.findViewById(R.id.btn_back);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cardfragment.this.k.setVisibility(8);
                Cardfragment.this.j.setVisibility(0);
            }
        });
        this.h = (CustomTextView) view.findViewById(R.id.tv_no_card_hint);
        this.i = new ProgressDialog(getActivity());
        this.i.setMessage(getString(R.string.please_wait));
        this.i.setIndeterminate(true);
        this.i.setCancelable(false);
        this.i.setCanceledOnTouchOutside(false);
    }

    private void c() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aX()).buildUpon();
        buildUpon.appendQueryParameter("ot", "5");
        AnonymousClass3 r1 = new SimpleCallback<NewCardsBodyResult>() {
            public void a(NewCardsBodyResult newCardsBodyResult) {
                Cardfragment.this.b();
                Cardfragment.this.a(newCardsBodyResult.data);
            }

            public void a(String str) {
                super.a(str);
                Cardfragment.this.b();
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewCardsBodyResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewCardsBodyResult.class, r1);
        }
        request.setTag(l);
        RequestQueueUtil.a().add(request);
        a();
    }

    public void a(NewCardsListData newCardsListData) {
        if (newCardsListData != null && newCardsListData.user_cards != null) {
            if (newCardsListData.user_cards.size() == 0) {
                this.h.setVisibility(0);
                this.f.performClick();
                return;
            }
            this.h.setVisibility(8);
            newCardsListData.user_cards.get(0).expand = true;
            this.d = newCardsListData.user_cards;
            this.c = new SavedCardListAdapter(ShopApp.g());
            this.c.a(this.d);
            this.b.setAdapter(this.c);
            this.k.setVisibility(8);
            this.j.setVisibility(0);
        }
    }

    private void a(String str) {
        this.f.performClick();
    }

    public void a() {
        if (BaseActivity.isActivityAlive(getActivity())) {
            if (this.i != null) {
                this.i.show();
            }
            this.f.setVisibility(8);
        }
    }

    public void b() {
        if (BaseActivity.isActivityAlive(getActivity()) && isAdded()) {
            if (this.i != null && this.i.isShowing()) {
                this.i.dismiss();
            }
            this.f.setVisibility(0);
        }
    }

    public class SavedCardListAdapter extends ArrayAdapter<NewUserCardsType> implements SlidingButton.OnCheckedChangedListener {
        public void a(SlidingButton slidingButton, boolean z) {
        }

        public int getItemViewType(int i) {
            return i;
        }

        public SavedCardListAdapter(Context context) {
            super(context);
        }

        public View a(Context context, int i, NewUserCardsType newUserCardsType, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_buy_confirm_payment_credit_exits, (ViewGroup) null, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.m = inflate.findViewById(R.id.detail_contianer);
            viewHolder.b = (ImageView) viewHolder.m.findViewById(R.id.card_type_detail);
            viewHolder.g = (CustomTextView) viewHolder.m.findViewById(R.id.card_number_detail);
            viewHolder.h = (CustomTextView) viewHolder.m.findViewById(R.id.card_name_detail);
            viewHolder.i = (CustomTextView) viewHolder.m.findViewById(R.id.card_expire_detail);
            viewHolder.c = (CustomEditTextView) viewHolder.m.findViewById(R.id.card_cvv);
            viewHolder.d = (ImageView) viewHolder.m.findViewById(R.id.card_cvv_image);
            viewHolder.e = (CommonButton) viewHolder.m.findViewById(R.id.pay_order);
            viewHolder.n = inflate.findViewById(R.id.simple_contianer);
            viewHolder.f6806a = (ImageView) viewHolder.n.findViewById(R.id.card_type);
            viewHolder.k = (CustomTextView) viewHolder.n.findViewById(R.id.card_number);
            viewHolder.l = (CustomTextView) viewHolder.n.findViewById(R.id.card_name);
            viewHolder.j = (CustomTextView) viewHolder.n.findViewById(R.id.card_expire);
            inflate.setTag(viewHolder);
            viewHolder.e.setEnabled(false);
            Cardfragment.this.a(viewHolder);
            return inflate;
        }

        public void a(View view, int i, final NewUserCardsType newUserCardsType) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            String str = "****" + newUserCardsType.card_no.substring(newUserCardsType.card_no.length() - 4);
            viewHolder.f6806a.setImageDrawable(Cards.l.get(a(newUserCardsType.card_brand)));
            viewHolder.k.setText(str);
            viewHolder.l.setText(newUserCardsType.name_on_card);
            viewHolder.j.setText(newUserCardsType.expiry_month + "/" + newUserCardsType.expiry_year);
            viewHolder.b.setImageDrawable(Cards.l.get(a(newUserCardsType.card_brand)));
            viewHolder.g.setText(str);
            viewHolder.h.setText(newUserCardsType.name_on_card);
            viewHolder.i.setText(newUserCardsType.expiry_month + "/" + newUserCardsType.expiry_year);
            int i2 = newUserCardsType.card_no.matches("^3[47]+[0-9|X]*") ? 4 : 3;
            viewHolder.c.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
            if (i2 == 4) {
                viewHolder.d.setImageResource(R.drawable.shop_cvv1234);
            } else {
                viewHolder.d.setImageResource(R.drawable.shop_cvv123);
            }
            viewHolder.f = newUserCardsType;
            if (newUserCardsType.expand) {
                viewHolder.n.setVisibility(8);
                viewHolder.m.setVisibility(0);
                return;
            }
            viewHolder.n.setVisibility(0);
            viewHolder.m.setVisibility(8);
            viewHolder.n.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ArrayList a2 = SavedCardListAdapter.this.a();
                    newUserCardsType.expand = true;
                    for (int i = 0; i < a2.size(); i++) {
                        if (!((NewUserCardsType) a2.get(i)).equals(newUserCardsType)) {
                            ((NewUserCardsType) a2.get(i)).expand = false;
                        }
                    }
                    SavedCardListAdapter.this.notifyDataSetChanged();
                }
            });
        }

        private String a(String str) {
            if (str == null) {
                return str;
            }
            String upperCase = str.toUpperCase();
            if ("MASTERCARD".equals(upperCase)) {
                return "MAST";
            }
            if ("VISA".equals(upperCase)) {
                return "VISA";
            }
            if ("MAESTRO".equals(upperCase)) {
                return "MAES";
            }
            if ("AMEX".equals(upperCase)) {
                return "AMEX";
            }
            if ("DINERS".equals(upperCase)) {
                return "DINR";
            }
            if ("DISCOVERCARD".equals(upperCase)) {
                return "DISCOVER";
            }
            return Cards.j.equals(upperCase) ? Cards.j : upperCase;
        }
    }

    /* access modifiers changed from: private */
    public void a(final ViewHolder viewHolder) {
        viewHolder.c.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if ((!viewHolder.f.card_no.matches("^3[47]+[0-9|X]*") || viewHolder.c.getText().length() != 4) && (viewHolder.f.card_no.matches("^3[47]+[0-9|X]*") || viewHolder.c.getText().length() != 3)) {
                    viewHolder.e.setEnabled(false);
                } else {
                    viewHolder.e.setEnabled(true);
                }
            }
        });
        viewHolder.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Params params = new Params();
                params.put(PayU.p, viewHolder.c.getText().toString());
                params.put("store_card_token", viewHolder.f.card_token);
                PayUtil.a(((ConfirmActivity) Cardfragment.this.getActivity()).getconfirmOrder().f6881a, Constants.PAY_BANK_PAYU, "card", (ConfirmActivity) Cardfragment.this.getActivity(), PayU.PaymentMode.valueOf(viewHolder.f.card_mode), params, viewHolder.k.toString().replace(" ", ""), "", "", "", "");
            }
        });
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f6806a;
        ImageView b;
        CustomEditTextView c;
        ImageView d;
        CommonButton e;
        NewUserCardsType f;
        CustomTextView g;
        CustomTextView h;
        CustomTextView i;
        CustomTextView j;
        CustomTextView k;
        CustomTextView l;
        View m;
        View n;

        ViewHolder() {
        }
    }
}
