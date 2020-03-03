package com.mi.global.shop.buy.cod;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.buy.model.OrderPaymentInfo;
import com.mi.global.shop.model.cod.Result;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.log.LogUtil;
import com.squareup.wire.Wire;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class CODViewHelper implements TextWatcher, View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6866a = "CODViewHelper";
    private static final int e = 2;
    public Handler b = null;
    public TimerTask c = null;
    public Timer d = null;
    private View f = null;
    /* access modifiers changed from: private */
    public ConfirmActivity g = null;
    /* access modifiers changed from: private */
    public OrderPaymentInfo h = null;
    private String i = null;
    private LinearLayout j = null;
    private LinearLayout k = null;
    private LinearLayout l = null;
    /* access modifiers changed from: private */
    public CommonButton m = null;
    private CustomTextView n = null;
    private CustomTextView o = null;
    /* access modifiers changed from: private */
    public CommonButton p = null;
    private CustomTextView q = null;
    private CustomTextView r = null;
    private CustomEditTextView s = null;
    private SimpleDraweeView t = null;
    private CustomTextView u = null;
    private CustomTextView v = null;
    private CustomEditTextView w = null;
    private boolean x = false;

    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public CODViewHelper(ConfirmActivity confirmActivity, View view, OrderPaymentInfo orderPaymentInfo) {
        this.f = view;
        this.g = confirmActivity;
        this.h = orderPaymentInfo;
        if (this.h != null) {
            this.i = ConnectionHelper.e(this.h.b);
        }
    }

    public View a() {
        this.j = (LinearLayout) this.f.findViewById(R.id.buy_confirm_payment_cod_phone);
        this.k = (LinearLayout) this.f.findViewById(R.id.buy_confirm_payment_cod_pic);
        this.l = (LinearLayout) this.f.findViewById(R.id.buy_confirm_payment_cod_verify);
        this.m = (CommonButton) this.l.findViewById(R.id.buy_confirm_cod_confirm_your_order);
        this.m.setOnClickListener(this);
        this.m.setEnabled(false);
        this.o = (CustomTextView) this.f.findViewById(R.id.buy_confirm_cod_phone_number_text);
        this.r = (CustomTextView) this.f.findViewById(R.id.buy_confirm_payment_cod_tel_edit_button);
        this.r.setOnClickListener(this);
        this.q = (CustomTextView) this.f.findViewById(R.id.buy_confirm_cod_switch_to_img);
        this.q.setOnClickListener(this);
        this.p = (CommonButton) this.f.findViewById(R.id.buy_confirm_cod_phone_get_verification_code_button);
        this.p.setOnClickListener(this);
        this.s = (CustomEditTextView) this.f.findViewById(R.id.buy_confirm_cod_verification_code_text);
        this.s.addTextChangedListener(this);
        this.t = (SimpleDraweeView) this.f.findViewById(R.id.buy_confirm_payment_cod_pic_img_view);
        this.u = (CustomTextView) this.f.findViewById(R.id.buy_confirm_payment_cod_pic_refresh_button);
        this.u.setOnClickListener(this);
        this.v = (CustomTextView) this.f.findViewById(R.id.buy_confirm_cod_switch_to_sms);
        this.v.setOnClickListener(this);
        this.w = (CustomEditTextView) this.f.findViewById(R.id.buy_confirm_cod_vcode_text);
        this.w.addTextChangedListener(this);
        this.n = (CustomTextView) this.f.findViewById(R.id.buy_confirm_cod_not_support);
        return this.f;
    }

    public void b() {
        if (this.h != null) {
            switch (this.h.p) {
                case 0:
                    g();
                    return;
                case 1:
                    j();
                    return;
                case 2:
                    h();
                    return;
                case 3:
                    f();
                    return;
                default:
                    return;
            }
        }
    }

    private void f() {
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        this.n.setVisibility(0);
    }

    private void g() {
        if (this.h != null && this.h.p == 0) {
            this.m.setOnClickListener(this);
            this.m.setEnabled(true);
        }
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.l.setVisibility(0);
        ImageView imageView = (ImageView) this.l.findViewById(R.id.buy_confirm_cod_confirm_pic_noverify);
        CustomTextView customTextView = (CustomTextView) this.l.findViewById(R.id.buy_confirm_cod_confirm_tip_noverify);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (customTextView != null) {
            customTextView.setVisibility(0);
        }
        this.n.setVisibility(8);
    }

    private void h() {
        this.j.setVisibility(8);
        this.k.setVisibility(0);
        this.l.setVisibility(0);
        this.n.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void i() {
        FrescoUtils.a(this.i, this.t);
    }

    public void c() {
        if (this.h != null) {
            this.h.q = 0;
        }
    }

    public void d() {
        if (this.i != null) {
            LogUtil.b(f6866a, "refreshPic");
            this.i = ConnectionHelper.e(this.h.b);
            i();
        }
    }

    private void j() {
        this.j.setVisibility(0);
        this.k.setVisibility(8);
        this.l.setVisibility(0);
        this.n.setVisibility(8);
        if (this.h.q < 2) {
            this.q.setVisibility(8);
        } else {
            this.q.setVisibility(8);
        }
        this.o.setText(ShopApp.g().getString(R.string.user_address_phoneareacode) + this.h.d);
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.d != null) {
            this.d.cancel();
        }
        if (this.b != null) {
            this.b.sendEmptyMessage(0);
        }
    }

    private void l() {
        this.p.setText(String.format(this.g.getString(R.string.buy_confirm_COD_waitforresend), new Object[]{60}));
        this.p.setEnabled(false);
        this.p.setTextColor(Color.parseColor("#ffffffff"));
        if (this.d != null) {
            this.d.cancel();
        }
        this.d = new Timer();
        this.b = new Handler() {
            public void handleMessage(Message message) {
                CODViewHelper.this.p.setText(String.format(CODViewHelper.this.g.getString(R.string.buy_confirm_COD_waitforresend), new Object[]{Integer.valueOf(message.what)}));
                if (message.what <= 0) {
                    if (CODViewHelper.this.d != null) {
                        CODViewHelper.this.d.cancel();
                    }
                    CODViewHelper.this.p.setEnabled(true);
                    CODViewHelper.this.p.setTextColor(Color.parseColor("#ffffff"));
                    CODViewHelper.this.p.setText(R.string.buy_confirm_COD_getvcodebtn);
                }
            }
        };
        this.c = new TimerTask() {

            /* renamed from: a  reason: collision with root package name */
            int f6871a = 60;

            public void run() {
                Message message = new Message();
                this.f6871a--;
                message.what = this.f6871a;
                CODViewHelper.this.b.sendMessage(message);
            }
        };
        this.d.schedule(this.c, 1000, 1000);
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [com.android.volley.Request] */
    /* JADX WARNING: type inference failed for: r11v1, types: [com.mi.global.shop.request.MiJsonObjectRequest] */
    /* JADX WARNING: type inference failed for: r5v1, types: [com.mi.global.shop.request.MiProtobufRequest] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m() {
        /*
            r17 = this;
            r0 = r17
            java.lang.String r1 = com.mi.global.shop.util.ConnectionHelper.f()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            android.net.Uri$Builder r1 = r1.buildUpon()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r3 = "order_id"
            com.mi.global.shop.buy.model.OrderPaymentInfo r4 = r0.h
            java.lang.String r4 = r4.b
            r2.put(r3, r4)
            java.lang.String r3 = "phone"
            com.mi.global.shop.buy.model.OrderPaymentInfo r4 = r0.h
            java.lang.String r4 = r4.d
            r2.put(r3, r4)
            boolean r3 = com.mi.global.shop.ShopApp.n()
            r4 = 1
            if (r3 == 0) goto L_0x004a
            com.mi.global.shop.request.MiProtobufRequest r3 = new com.mi.global.shop.request.MiProtobufRequest
            r6 = 1
            java.lang.String r7 = r1.toString()
            java.util.Map r1 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r2, (boolean) r4)
            java.lang.String r8 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r1)
            com.mi.global.shop.buy.cod.CODViewHelper$3 r9 = new com.mi.global.shop.buy.cod.CODViewHelper$3
            r9.<init>()
            com.mi.global.shop.buy.cod.CODViewHelper$4 r10 = new com.mi.global.shop.buy.cod.CODViewHelper$4
            r10.<init>()
            r5 = r3
            r5.<init>(r6, r7, r8, r9, r10)
            goto L_0x0069
        L_0x004a:
            com.mi.global.shop.request.MiJsonObjectRequest r3 = new com.mi.global.shop.request.MiJsonObjectRequest
            r12 = 1
            java.lang.String r13 = r1.toString()
            java.util.Map r1 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r2, (boolean) r4)
            java.lang.String r14 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r1)
            com.mi.global.shop.buy.cod.CODViewHelper$5 r15 = new com.mi.global.shop.buy.cod.CODViewHelper$5
            r15.<init>()
            com.mi.global.shop.buy.cod.CODViewHelper$6 r1 = new com.mi.global.shop.buy.cod.CODViewHelper$6
            r1.<init>()
            r11 = r3
            r16 = r1
            r11.<init>(r12, r13, r14, r15, r16)
        L_0x0069:
            java.lang.String r1 = "CODViewHelper"
            r3.setTag(r1)
            com.android.volley.RequestQueue r1 = com.mi.util.RequestQueueUtil.a()
            r1.add(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.buy.cod.CODViewHelper.m():void");
    }

    public void onClick(View view) {
        if (view == this.p) {
            this.h.q++;
            int i2 = this.h.q;
            l();
            m();
        } else if (view == this.m) {
            switch (this.h.p) {
                case 0:
                    n();
                    return;
                case 1:
                    p();
                    return;
                case 2:
                    o();
                    return;
                default:
                    return;
            }
        } else if (view == this.q) {
            this.h.p = 2;
            this.g.runOnUiThread(new Runnable() {
                public void run() {
                    CODViewHelper.this.i();
                    CODViewHelper.this.b();
                }
            });
        } else if (view == this.v) {
            this.h.p = 1;
            this.g.runOnUiThread(new Runnable() {
                public void run() {
                    CODViewHelper.this.b();
                }
            });
        } else if (view == this.r) {
            this.g.startActivityForResult(new Intent(this.g, ChangeTelAcitivty.class), 102);
        } else if (view == this.u) {
            d();
        }
    }

    private void n() {
        this.x = false;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.i()).buildUpon();
        HashMap hashMap = new HashMap();
        hashMap.put("order_id", this.h.b);
        a(buildUpon.toString(), (Map<String, String>) hashMap);
    }

    private void o() {
        this.x = true;
        String obj = ((CustomEditTextView) this.f.findViewById(R.id.buy_confirm_cod_vcode_text)).getText().toString();
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.h()).buildUpon();
        HashMap hashMap = new HashMap();
        hashMap.put("order_id", this.h.b);
        hashMap.put("phone", this.h.d);
        hashMap.put("code", obj);
        a(buildUpon.toString(), (Map<String, String>) hashMap);
    }

    private void p() {
        this.x = false;
        String obj = this.s.getText().toString();
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.g()).buildUpon();
        HashMap hashMap = new HashMap();
        hashMap.put("order_id", this.h.b);
        hashMap.put("phone", this.h.d);
        hashMap.put("code", obj);
        a(buildUpon.toString(), (Map<String, String>) hashMap);
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.android.volley.Request] */
    /* JADX WARNING: type inference failed for: r4v2, types: [com.mi.global.shop.request.MiJsonObjectRequest] */
    /* JADX WARNING: type inference failed for: r4v3, types: [com.mi.global.shop.request.MiProtobufRequest] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r10 = this;
            java.lang.String r1 = "CODViewHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "doCODRequest url:"
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            com.mi.log.LogUtil.b(r1, r2)
            com.mi.global.shop.widget.CommonButton r1 = r10.m
            r2 = 0
            r1.setEnabled(r2)
            boolean r1 = com.mi.global.shop.ShopApp.n()
            r2 = 1
            if (r1 == 0) goto L_0x003e
            com.mi.global.shop.request.MiProtobufRequest r1 = new com.mi.global.shop.request.MiProtobufRequest
            r5 = 1
            java.util.Map r0 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r12, (boolean) r2)
            java.lang.String r7 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r0)
            com.mi.global.shop.buy.cod.CODViewHelper$9 r8 = new com.mi.global.shop.buy.cod.CODViewHelper$9
            r8.<init>()
            com.mi.global.shop.buy.cod.CODViewHelper$10 r9 = new com.mi.global.shop.buy.cod.CODViewHelper$10
            r9.<init>()
            r4 = r1
            r6 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            goto L_0x0058
        L_0x003e:
            com.mi.global.shop.request.MiJsonObjectRequest r1 = new com.mi.global.shop.request.MiJsonObjectRequest
            r5 = 1
            java.util.Map r0 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r12, (boolean) r2)
            java.lang.String r7 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r0)
            com.mi.global.shop.buy.cod.CODViewHelper$11 r8 = new com.mi.global.shop.buy.cod.CODViewHelper$11
            r8.<init>()
            com.mi.global.shop.buy.cod.CODViewHelper$12 r9 = new com.mi.global.shop.buy.cod.CODViewHelper$12
            r9.<init>()
            r4 = r1
            r6 = r11
            r4.<init>(r5, r6, r7, r8, r9)
        L_0x0058:
            java.lang.String r0 = "CODViewHelper"
            r1.setTag(r0)
            com.android.volley.RequestQueue r0 = com.mi.util.RequestQueueUtil.a()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.buy.cod.CODViewHelper.a(java.lang.String, java.util.Map):void");
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        Intent intent = new Intent();
        intent.putExtra("result", str);
        intent.putExtra("cod", true);
        this.g.setResult(-1, intent);
        StringBuilder sb = new StringBuilder(ConnectionHelper.j());
        sb.append(IOUtils.f15883a);
        sb.append(this.h.b);
        sb.append("?status=");
        sb.append(this.x ? "2" : "1");
        Intent intent2 = new Intent(this.g, WebActivity.class);
        intent2.putExtra("url", sb.toString());
        this.g.startActivity(intent2);
        this.g.finish();
    }

    /* access modifiers changed from: private */
    public JSONObject a(Result result) {
        JSONObject jSONObject = new JSONObject();
        if (result == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("url", Wire.get(result.url, ""));
            jSONObject.put("refresh", Wire.get(result.refresh, Result.DEFAULT_REFRESH));
            JSONObject jSONObject2 = new JSONObject();
            if (result.support != null) {
                jSONObject2.put("codstatus", Wire.get(result.support.codstatus, ""));
            }
            jSONObject.put("support", result);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void e() {
        if (this.d != null) {
            this.d.cancel();
        }
        this.f = null;
        this.g = null;
        this.b = null;
        this.d = null;
        this.c = null;
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            if (editable.toString().getBytes().length == 0) {
                this.m.setEnabled(false);
            } else {
                this.m.setEnabled(true);
            }
        }
    }

    public void a(OrderPaymentInfo orderPaymentInfo) {
        this.h = orderPaymentInfo;
        this.i = ConnectionHelper.e(this.h.b);
        b();
    }
}
