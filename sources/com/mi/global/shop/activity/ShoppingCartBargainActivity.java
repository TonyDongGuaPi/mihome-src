package com.mi.global.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.CartChooseBargainListAdapter;
import com.mi.global.shop.model.ShoppingCartBargainModel;
import com.mi.global.shop.model.cart.List;
import com.mi.global.shop.request.MiHttpHttpsRequestHelper;
import com.mi.global.shop.request.MiProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.multimonitor.Request;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.squareup.wire.Wire;
import java.util.ArrayList;
import org.json.JSONObject;

public class ShoppingCartBargainActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5451a = "ShoppingCartBargainActivity";
    private View b;
    private CommonButton c;
    private CommonButton d;
    private NoScrollListView e;
    private CustomTextView f;
    public CartChooseBargainListAdapter mCartChooseBargainListAdapter;
    public ShoppingCartBargainModel shoppingCartBargainModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            LogUtil.b(f5451a, "onCreate, savedInstanceState:" + bundle.toString());
        }
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_cart_bargain_activity);
        setTitle(R.string.cart_title);
        String stringExtra = getIntent().getStringExtra("cart_bargain");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.b(f5451a, "jsondata is empty");
            c();
            return;
        }
        LogUtil.b(f5451a, "get jsondata:" + stringExtra);
        this.mCartView.setVisibility(4);
        this.b = findViewById(R.id.title_bar_home);
        this.b.setVisibility(0);
        this.b.setOnClickListener(this);
        a();
        a(stringExtra);
    }

    private void a(String str) {
        LogUtil.b(f5451a, "getBargainInfo, json:" + str);
        try {
            this.shoppingCartBargainModel = (ShoppingCartBargainModel) new Gson().fromJson(str.toString(), ShoppingCartBargainModel.class);
            a(this.shoppingCartBargainModel);
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        this.e = (NoScrollListView) findViewById(R.id.bargain_list);
        this.f = (CustomTextView) findViewById(R.id.bargain_select);
        this.c = (CommonButton) findViewById(R.id.bargain_add);
        this.d = (CommonButton) findViewById(R.id.bargain_back);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void b() {
        setResult(-1, new Intent());
        finish();
    }

    private void a(ShoppingCartBargainModel shoppingCartBargainModel2) {
        if (shoppingCartBargainModel2 == null || shoppingCartBargainModel2.bargainList == null || shoppingCartBargainModel2.bargainList.size() <= 0) {
            c();
            return;
        }
        LogUtil.b(f5451a, "model not null, updateview");
        this.f.setText(String.format(ShopApp.g().getString(R.string.cart_bargain_select), new Object[]{shoppingCartBargainModel2.bargainNum}));
        ArrayList<ShoppingCartBargainModel.BargainsItem> arrayList = shoppingCartBargainModel2.bargainList;
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                arrayList.get(i).Selected = true;
            } else {
                arrayList.get(i).Selected = false;
            }
        }
        this.mCartChooseBargainListAdapter = new CartChooseBargainListAdapter(this);
        this.mCartChooseBargainListAdapter.c();
        this.mCartChooseBargainListAdapter.a(arrayList);
        this.e.setAdapter(this.mCartChooseBargainListAdapter);
    }

    /* access modifiers changed from: private */
    public void c() {
        b(getString(R.string.shop_error_network));
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        MiToast.a((Context) this, (CharSequence) str, 0);
        this.c.setEnabled(true);
        this.d.setEnabled(true);
        setResult(0);
        finish();
        LogUtil.b(f5451a, "JSON parse error");
    }

    private void d() {
        this.c.setEnabled(true);
        this.d.setEnabled(true);
        MiToast.a((Context) this, R.string.shop_error_network, 0);
        LogUtil.b(f5451a, "JSON parse error");
    }

    public void onClick(View view) {
        try {
            if (view.getId() == R.id.title_bar_home) {
                onBackPressed();
            }
            if (view == this.d) {
                LogUtil.b(f5451a, "backBTn clicked");
                setResult(0);
                finish();
            } else if (view == this.c) {
                e();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        if (this.shoppingCartBargainModel != null && this.shoppingCartBargainModel.bargainList != null && this.shoppingCartBargainModel.bargainList.size() != 0) {
            ShoppingCartBargainModel.BargainsItem bargainsItem = null;
            int i = 0;
            while (true) {
                if (i >= this.shoppingCartBargainModel.bargainList.size()) {
                    break;
                } else if (this.shoppingCartBargainModel.bargainList.get(i).Selected.booleanValue()) {
                    bargainsItem = this.shoppingCartBargainModel.bargainList.get(i);
                    break;
                } else {
                    i++;
                }
            }
            if (bargainsItem != null) {
                d(bargainsItem.bargainGoodsId);
            }
        }
    }

    private String[] c(String str) {
        String[] J = ConnectionHelper.J();
        String[] strArr = new String[J.length];
        for (int i = 0; i < J.length; i++) {
            strArr[i] = ConnectionHelper.a(J[i], str, "", "", "");
        }
        return strArr;
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.setEnabled(false);
            this.d.setEnabled(false);
            if (ShopApp.n()) {
                MiProtobufRequest miProtobufRequest = new MiProtobufRequest(0, ConnectionHelper.a(ConnectionHelper.K(), str, "", "", ""), new Response.Listener<byte[]>() {
                    /* renamed from: a */
                    public void onResponse(byte[] bArr) {
                        try {
                            List decode = List.ADAPTER.decode(bArr);
                            if (((Integer) Wire.get(decode.errno, List.DEFAULT_ERRNO)).intValue() != 0) {
                                LogUtil.b(ShoppingCartBargainActivity.f5451a, "error msg:" + ((String) Wire.get(decode.errmsg, "")));
                                ShoppingCartBargainActivity.this.b((String) Wire.get(decode.errmsg, ""));
                                return;
                            }
                            ShoppingCartBargainActivity.this.b();
                            LogUtil.b(ShoppingCartBargainActivity.f5451a, "Add cart Process success");
                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtil.b(ShoppingCartBargainActivity.f5451a, "Exception:" + e.toString());
                            CrashReport.postCrash(Thread.currentThread(), (Throwable) e);
                            ShoppingCartBargainActivity.this.c();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        VolleyLog.d(ShoppingCartBargainActivity.f5451a, "Error: " + volleyError.getMessage());
                        ShoppingCartBargainActivity.this.c();
                    }
                });
                miProtobufRequest.setTag(f5451a);
                RequestQueueUtil.a().add(miProtobufRequest);
            } else {
                MiHttpHttpsRequestHelper miHttpHttpsRequestHelper = new MiHttpHttpsRequestHelper(0, ConnectionHelper.a(c(str)), new Response.Listener<JSONObject>() {
                    /* renamed from: a */
                    public void onResponse(JSONObject jSONObject) {
                        try {
                            if (jSONObject.getInt(Request.RESULT_CODE_KEY) != 0) {
                                LogUtil.b(ShoppingCartBargainActivity.f5451a, "errno=" + Integer.toString(jSONObject.getInt(Request.RESULT_CODE_KEY)));
                                ShoppingCartBargainActivity.this.c();
                                return;
                            }
                            try {
                                ShoppingCartBargainActivity.this.b();
                                LogUtil.b(ShoppingCartBargainActivity.f5451a, "Process success");
                            } catch (Exception e) {
                                LogUtil.b(ShoppingCartBargainActivity.f5451a, "JSON parse error");
                                e.printStackTrace();
                                CrashReport.postCrash(Thread.currentThread(), (Throwable) e);
                                ShoppingCartBargainActivity.this.c();
                            }
                        } catch (Exception e2) {
                            LogUtil.b(ShoppingCartBargainActivity.f5451a, "JSON parse error");
                            e2.printStackTrace();
                            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
                            ShoppingCartBargainActivity.this.c();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        LogUtil.b(ShoppingCartBargainActivity.f5451a, "Error: " + volleyError.getMessage());
                        ShoppingCartBargainActivity.this.c();
                    }
                });
                miHttpHttpsRequestHelper.a((Object) f5451a);
                miHttpHttpsRequestHelper.a((RetryPolicy) new DefaultRetryPolicy(10000, 1, 1.0f));
                miHttpHttpsRequestHelper.a();
            }
            LogUtil.b(f5451a, "added task to request quene");
        }
    }
}
