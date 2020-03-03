package com.mi.global.shop.buy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.buy.model.BankLogos;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.buy.payu.PayUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollGridView;
import com.mi.log.LogUtil;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class NBfragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6834a = "net banking";
    private static final String c = "NBfragment";
    private View b;
    /* access modifiers changed from: private */
    public ViewHolder d;
    /* access modifiers changed from: private */
    public String e = "";
    /* access modifiers changed from: private */
    public ArrayList<InnerNetBankItem> f;
    /* access modifiers changed from: private */
    public ArrayList<InnerNetBankItem> g;
    private NoScrollGridView h;
    /* access modifiers changed from: private */
    public RecommendBankGridAdapter i;

    public void onCreate(Bundle bundle) {
        LogUtil.b(c, "onCreate");
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.b(c, "onCreateView");
        if (this.b == null) {
            this.b = layoutInflater.inflate(R.layout.shop_buy_confirm_payment_netbank, viewGroup, false);
            a();
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.b.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.b);
                LogUtil.b(c, "onCreateView remove from parent");
            }
        }
        return this.b;
    }

    private void a() {
        b();
        a(this.b);
        b(this.b);
        c();
    }

    private void b() {
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        if (PayU.T != null && PayU.T.has("all_json")) {
            try {
                JSONObject jSONObject = new JSONObject(PayU.T.optString("all_json"));
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    String obj2 = jSONObject.getJSONObject(obj).get("name").toString();
                    String obj3 = jSONObject.getJSONObject(obj).get("img").toString();
                    if (jSONObject.getJSONObject(obj).get(AutoSceneAction.f21495a).toString().equalsIgnoreCase("true")) {
                        InnerNetBankItem innerNetBankItem = new InnerNetBankItem();
                        innerNetBankItem.f6836a = obj;
                        innerNetBankItem.b = obj2;
                        innerNetBankItem.c = obj3;
                        this.f.add(innerNetBankItem);
                    }
                }
                LogUtil.b(c, "Get all bank:" + this.f.size());
                JSONArray jSONArray = PayU.T.getJSONArray("recommend");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    String obj4 = jSONArray.get(i2).toString();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= this.f.size()) {
                            break;
                        }
                        InnerNetBankItem innerNetBankItem2 = this.f.get(i3);
                        if (innerNetBankItem2.f6836a.equalsIgnoreCase(obj4)) {
                            InnerNetBankItem innerNetBankItem3 = new InnerNetBankItem();
                            innerNetBankItem3.f6836a = innerNetBankItem2.f6836a;
                            innerNetBankItem3.c = innerNetBankItem2.c;
                            innerNetBankItem3.b = innerNetBankItem2.b;
                            this.g.add(innerNetBankItem3);
                            break;
                        }
                        i3++;
                    }
                }
                LogUtil.b(c, "initData Done, recommed:" + this.g.size());
            } catch (Exception e2) {
                LogUtil.b(c, "initData Exception:" + e2.toString());
            }
        }
    }

    private void a(View view) {
        this.d = new ViewHolder();
        this.h = (NoScrollGridView) view.findViewById(R.id.buy_confirm_netbank_grid_view);
        this.i = new RecommendBankGridAdapter(getActivity());
        this.i.a(this.g);
        this.h.setAdapter(this.i);
        this.d.f6841a = (Spinner) view.findViewById(R.id.buy_confirm_payment_netbank_droplist);
        this.d.d = (CommonButton) view.findViewById(R.id.buy_confirm_netbank_payorder);
        this.d.d.setEnabled(false);
        this.d.b = (ImageView) view.findViewById(R.id.bank_logo_border);
        this.d.c = (ImageView) view.findViewById(R.id.bank_logo_corner);
    }

    private void c() {
        this.d.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(NBfragment.this.e)) {
                    LogUtil.b(NBfragment.c, "bank Code = null.");
                    return;
                }
                Params params = new Params();
                params.put(PayU.j, NBfragment.this.e);
                LogUtil.b(NBfragment.c, "submit bank Code = " + NBfragment.this.e);
                PayUtil.a(((ConfirmActivity) NBfragment.this.getActivity()).getconfirmOrder().f6881a, Constants.PAY_BANK_PAYU, Constants.PAYTYPE_NETBANK, (ConfirmActivity) NBfragment.this.getActivity(), PayU.PaymentMode.NB, params, "", "", "", "", "");
            }
        });
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        SpinnerSelectedListener() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            LogUtil.b(NBfragment.c, "onItemSelected position:" + i);
            if (i == 0) {
                String unused = NBfragment.this.e = "";
                NBfragment.this.d.d.setEnabled(false);
                NBfragment.this.d.b.setVisibility(4);
                NBfragment.this.d.c.setVisibility(4);
            } else {
                CustomTextView customTextView = (CustomTextView) view;
                if (customTextView != null) {
                    String charSequence = customTextView.getText().toString();
                    LogUtil.b(NBfragment.c, "onItemSelected name on position:" + charSequence);
                    NBfragment.this.d.d.setEnabled(true);
                    String unused2 = NBfragment.this.e = "";
                    for (int i2 = 0; i2 < NBfragment.this.f.size(); i2++) {
                        if (charSequence.equalsIgnoreCase(((InnerNetBankItem) NBfragment.this.f.get(i2)).b)) {
                            String unused3 = NBfragment.this.e = ((InnerNetBankItem) NBfragment.this.f.get(i2)).f6836a;
                        }
                    }
                    LogUtil.b(NBfragment.c, "onItemSelected Bank code:" + NBfragment.this.e);
                    NBfragment.this.d.b.setVisibility(0);
                    NBfragment.this.d.c.setVisibility(0);
                } else {
                    return;
                }
            }
            for (int i3 = 0; i3 < NBfragment.this.g.size(); i3++) {
                ((InnerNetBankItem) NBfragment.this.g.get(i3)).d = false;
            }
            NBfragment.this.i.notifyDataSetChanged();
        }
    }

    private View b(View view) {
        String[] strArr = new String[(this.f.size() + 1)];
        strArr[0] = ShopApp.g().getString(R.string.buy_confirm_NetBank_choosepromote);
        for (int i2 = 1; i2 < strArr.length; i2++) {
            strArr[i2] = this.f.get(i2 - 1).b;
        }
        if (strArr.length > 1) {
            Arrays.sort(strArr, 1, strArr.length);
        }
        this.d.f6841a.setAdapter(new ArrayAdapter(ShopApp.g(), R.layout.shop_buy_confirm_payment_spinneritem, strArr));
        this.d.f6841a.setOnItemSelectedListener(new SpinnerSelectedListener());
        return view;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public Spinner f6841a;
        public ImageView b;
        public ImageView c;
        public CommonButton d;

        ViewHolder() {
        }
    }

    public class InnerNetBankItem {

        /* renamed from: a  reason: collision with root package name */
        public String f6836a = "";
        public String b = "";
        public String c = "";
        public Boolean d = false;

        public InnerNetBankItem() {
        }
    }

    static class RecommendBankViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f6839a;
        ImageView b;
        SimpleDraweeView c;

        RecommendBankViewHolder() {
        }
    }

    public class RecommendBankGridAdapter extends com.mi.global.shop.adapter.util.ArrayAdapter<InnerNetBankItem> {
        private Context b;
        private RecommendBankViewHolder c;

        public RecommendBankGridAdapter(Context context) {
            super(context);
            this.b = context;
        }

        public View a(Context context, int i, InnerNetBankItem innerNetBankItem, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.shop_buy_confirm_payment_netbank_recommendbank, (ViewGroup) null);
            this.c = new RecommendBankViewHolder();
            this.c.c = (SimpleDraweeView) inflate.findViewById(R.id.bank_logo);
            this.c.f6839a = (ImageView) inflate.findViewById(R.id.bank_logo_border);
            this.c.b = (ImageView) inflate.findViewById(R.id.bank_logo_corner);
            inflate.setTag(this.c);
            return inflate;
        }

        public void a(View view, int i, final InnerNetBankItem innerNetBankItem) {
            RecommendBankViewHolder recommendBankViewHolder = (RecommendBankViewHolder) view.getTag();
            if (BankLogos.a(innerNetBankItem.f6836a) != null) {
                recommendBankViewHolder.c.setImageDrawable(BankLogos.a(innerNetBankItem.f6836a));
            } else {
                Uri parse = Uri.parse(innerNetBankItem.c);
                if (parse != null) {
                    FrescoUtils.a(parse, recommendBankViewHolder.c);
                }
            }
            if (innerNetBankItem.d.booleanValue()) {
                recommendBankViewHolder.f6839a.setVisibility(0);
                recommendBankViewHolder.b.setVisibility(0);
            } else {
                recommendBankViewHolder.f6839a.setVisibility(4);
                recommendBankViewHolder.b.setVisibility(4);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LogUtil.b(NBfragment.c, "shortcuts clicket, view:" + view.toString());
                    LogUtil.b(NBfragment.c, "shortcuts clicket, code:" + innerNetBankItem.f6836a + ",status:" + innerNetBankItem.d);
                    String unused = NBfragment.this.e = innerNetBankItem.f6836a;
                    if (TextUtils.isEmpty(NBfragment.this.e)) {
                        NBfragment.this.d.d.setEnabled(false);
                    } else {
                        NBfragment.this.d.d.setEnabled(true);
                    }
                    for (int i = 0; i < NBfragment.this.g.size(); i++) {
                        if (!((InnerNetBankItem) NBfragment.this.g.get(i)).f6836a.equalsIgnoreCase(NBfragment.this.e)) {
                            ((InnerNetBankItem) NBfragment.this.g.get(i)).d = false;
                        } else {
                            ((InnerNetBankItem) NBfragment.this.g.get(i)).d = true;
                        }
                    }
                    NBfragment.this.d.b.setVisibility(4);
                    NBfragment.this.d.c.setVisibility(4);
                    NBfragment.this.i.notifyDataSetChanged();
                }
            });
        }
    }
}
