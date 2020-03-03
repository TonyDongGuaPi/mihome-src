package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.Request;
import com.google.code.microlog4android.format.PatternFormatter;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.newmodel.user.address.GSTResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.XEditText;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;

public class GSTActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5378a = "GSTActivity";
    AllCapTransformationMethod allCapTransformationMethod;
    /* access modifiers changed from: private */
    public String b = "22  AAAAA0000A  1Z5";
    @BindView(2131492987)
    CustomEditTextView bg_gst;
    @BindView(2131493009)
    Button bt_gst;
    @BindView(2131493010)
    Button bt_gst_cancel;
    SimpleCallback callback;
    @BindView(2131493283)
    XEditText ed_gst;
    Context mContext;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setCustomContentView(R.layout.shop_activity_gst);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.gst_title));
        this.mBackView.setVisibility(0);
        this.mCartView.setVisibility(4);
        this.ed_gst.setInputLength(15);
        this.ed_gst.setSeparator(" ", 2);
        this.ed_gst.setPattern(new int[]{2, 10, 3});
        if (this.allCapTransformationMethod == null) {
            this.allCapTransformationMethod = new AllCapTransformationMethod();
        }
        this.bg_gst.setTransformationMethod(this.allCapTransformationMethod);
        this.bg_gst.setText(this.b);
        this.ed_gst.setTransformationMethod(this.allCapTransformationMethod);
        this.ed_gst.setFinishedEditListener(new XEditText.FinishedEditListener() {
            public void a(boolean z) {
                if (z) {
                    GSTActivity.this.bt_gst.setBackgroundResource(R.color.orange_red);
                    GSTActivity.this.bt_gst.setClickable(true);
                } else {
                    GSTActivity.this.bt_gst.setBackgroundResource(R.color.order_grey);
                    GSTActivity.this.bt_gst.setClickable(false);
                }
                int length = GSTActivity.this.ed_gst.getText().toString().trim().length();
                if (length == 0) {
                    GSTActivity.this.bg_gst.setText(GSTActivity.this.b);
                    return;
                }
                GSTActivity.this.bg_gst.setText(GSTActivity.this.ed_gst.getText().toString().trim() + GSTActivity.this.b.substring(length, GSTActivity.this.b.length()));
            }
        });
        this.bt_gst_cancel.setOnClickListener(this);
        this.bt_gst.setOnClickListener(this);
        String stringExtra = getIntent().getStringExtra(CheckoutActivity.GST_CODE_S);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.ed_gst.setText(stringExtra);
        } else {
            this.bt_gst.setClickable(false);
        }
    }

    public String getGSTUrl(String str) {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.r()).buildUpon();
        buildUpon.appendQueryParameter("invoice_company_code", str);
        if (ShopApp.n()) {
            buildUpon.appendQueryParameter("ot", "5");
        }
        return buildUpon.toString();
    }

    private void a(final String str) {
        Request request;
        showLoading();
        String gSTUrl = getGSTUrl(str);
        if (this.callback == null) {
            this.callback = new SimpleCallback<GSTResult>() {
                public void a(GSTResult gSTResult) {
                    GSTActivity.this.hideLoading();
                    if (gSTResult.data.valid) {
                        Intent intent = new Intent();
                        intent.putExtra(CheckoutActivity.GST_CODE_S, str);
                        GSTActivity.this.setResult(-1, intent);
                        GSTActivity.this.finish();
                        return;
                    }
                    MiToast.a(GSTActivity.this.mContext, (CharSequence) gSTResult.errmsg, 1);
                }

                public void a(String str) {
                    super.a(str);
                    GSTActivity.this.hideLoading();
                    MiToast.a(GSTActivity.this.mContext, (CharSequence) str, 0);
                }
            };
        }
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(gSTUrl, GSTResult.class, this.callback);
        } else {
            request = new SimpleJsonRequest(gSTUrl, GSTResult.class, this.callback);
        }
        request.setTag(f5378a);
        RequestQueueUtil.a().add(request);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.callback = null;
        super.onStop();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_gst_cancel) {
            Intent intent = new Intent();
            intent.putExtra(CheckoutActivity.GST_CODE_S, "");
            setResult(-1, intent);
            finish();
        } else if (id == R.id.bt_gst) {
            a(this.ed_gst.getText().toString().trim().toUpperCase().replaceAll(" ", ""));
        }
    }

    class AllCapTransformationMethod extends ReplacementTransformationMethod {
        AllCapTransformationMethod() {
        }

        /* access modifiers changed from: protected */
        public char[] getOriginal() {
            return new char[]{'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z'};
        }

        /* access modifiers changed from: protected */
        public char[] getReplacement() {
            return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z'};
        }
    }
}
