package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.DeliveryPopAdapter;
import com.mi.global.shop.newmodel.user.address.FourDeliveryData;
import com.mi.global.shop.newmodel.user.address.SmartBoxData;
import com.mi.global.shop.newmodel.user.address.SmartDetailItemData;
import com.mi.global.shop.util.BaseTypeConvertUtil;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.PopSpinnerView;
import com.mi.global.shop.widget.dialog.CustomCloseDialog;
import java.util.Iterator;

public class DeliveryActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5365a = "DeliveryActivity";
    /* access modifiers changed from: private */
    public CustomCloseDialog b;
    @BindView(2131493024)
    View btnConfirm;
    /* access modifiers changed from: private */
    public volatile int c = 0;
    String currency;
    /* access modifiers changed from: private */
    public String d;
    @BindView(2131493365)
    CheckBox deliveryCheckBox;
    @BindView(2131493258)
    CustomTextView deliveryFouHour;
    @BindView(2131493259)
    CustomTextView deliveryHome;
    @BindView(2131493260)
    View deliveryNoticeView;
    @BindView(2131493261)
    CustomTextView deliverySmartBox;
    @BindView(2131493366)
    ImageView deliveryView;
    FourDeliveryData fourDeliveryData;
    @BindView(2131493963)
    View fourHourDelivery;
    @BindView(2131493368)
    CustomTextView fourHourPrice;
    @BindView(2131493909)
    PopSpinnerView popSpinnerView;
    @BindView(2131493967)
    View smartBox;
    @BindView(2131494265)
    CustomTextView smartBoxConditions;
    SmartBoxData smartBoxData;
    String smartBoxSelecetdId;
    @BindView(2131494266)
    CustomTextView smartExplain;
    SmartDetailItemData smartSelectItemData;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_delivery);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.devivery_service));
        this.mBackView.setVisibility(0);
        this.mCartView.setVisibility(4);
        this.c = getIntent().getIntExtra("mode", 0);
        this.smartBoxData = (SmartBoxData) getIntent().getSerializableExtra("delivery_smart");
        this.fourDeliveryData = (FourDeliveryData) getIntent().getSerializableExtra("delivery_fourhour");
        this.currency = getIntent().getStringExtra("currency");
        this.smartBoxSelecetdId = getIntent().getStringExtra("smartbox_id");
        this.d = getIntent().getStringExtra("coupon_id");
        if (this.smartBoxData != null && this.smartBoxData.smartDetailListData.size() > 0) {
            if (TextUtils.isEmpty(this.smartBoxSelecetdId)) {
                this.smartBoxSelecetdId = this.smartBoxData.defaultid;
            }
            a(this.smartBoxData);
        }
        if (this.fourDeliveryData != null) {
            a(this.fourDeliveryData);
        }
        a();
        updateUI();
    }

    private void a() {
        this.deliveryHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DeliveryActivity.this.deliveryCheckBox.isChecked()) {
                    int unused = DeliveryActivity.this.c = 1;
                } else {
                    int unused2 = DeliveryActivity.this.c = 0;
                }
                DeliveryActivity.this.deliveryHome.setBackgroundResource(R.drawable.shop_bg_tv_selector);
                DeliveryActivity.this.deliveryHome.setTextColor(DeliveryActivity.this.getResources().getColor(R.color.delivery_selected));
                DeliveryActivity.this.deliverySmartBox.setBackgroundResource(R.drawable.shop_bg_tv_unselector);
                DeliveryActivity.this.deliverySmartBox.setTextColor(DeliveryActivity.this.getResources().getColor(R.color.delivery_unselected));
                DeliveryActivity.this.smartBox.setVisibility(8);
                if (DeliveryActivity.this.fourDeliveryData != null) {
                    DeliveryActivity.this.fourHourDelivery.setVisibility(0);
                }
            }
        });
        this.deliverySmartBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = DeliveryActivity.this.c = 2;
                DeliveryActivity.this.deliverySmartBox.setBackgroundResource(R.drawable.shop_bg_tv_selector);
                DeliveryActivity.this.deliverySmartBox.setTextColor(DeliveryActivity.this.getResources().getColor(R.color.delivery_selected));
                DeliveryActivity.this.deliveryHome.setBackgroundResource(R.drawable.shop_bg_tv_unselector);
                DeliveryActivity.this.deliveryHome.setTextColor(DeliveryActivity.this.getResources().getColor(R.color.delivery_unselected));
                DeliveryActivity.this.fourHourDelivery.setVisibility(8);
                DeliveryActivity.this.smartBox.setVisibility(0);
            }
        });
        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(DeliveryActivity.this.d)) {
                    if (DeliveryActivity.this.c == 1) {
                        DeliveryActivity.this.a(DeliveryActivity.this.getString(R.string.exchange_coupon_invalid_by_delivery_dialog_title));
                        return;
                    } else if (DeliveryActivity.this.c == 2) {
                        DeliveryActivity.this.a(DeliveryActivity.this.getString(R.string.exchange_coupon_invalid_by_smart_box_dialog_title));
                        return;
                    }
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("mode", DeliveryActivity.this.c);
                if (DeliveryActivity.this.c == 2) {
                    bundle.putSerializable("smart_selected", DeliveryActivity.this.smartSelectItemData);
                }
                intent.putExtras(bundle);
                DeliveryActivity.this.setResult(-1, intent);
                DeliveryActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        CustomCloseDialog.Builder builder = new CustomCloseDialog.Builder(this);
        builder.b(str).a((Boolean) true);
        CustomCloseDialog a2 = builder.a();
        if (!a2.isShowing()) {
            a2.show();
        }
    }

    public void updateUI() {
        if (this.c == 1) {
            this.deliveryCheckBox.setChecked(true);
        } else if (this.c == 2) {
            this.deliverySmartBox.setBackgroundResource(R.drawable.shop_bg_tv_selector);
            this.deliverySmartBox.setTextColor(getResources().getColor(R.color.delivery_selected));
            this.deliveryHome.setBackgroundResource(R.drawable.shop_bg_tv_unselector);
            this.deliveryHome.setTextColor(getResources().getColor(R.color.delivery_unselected));
            this.fourHourDelivery.setVisibility(8);
            this.smartBox.setVisibility(0);
        }
        SpannableString spannableString = new SpannableString(getString(R.string.smart_box_terms_conditions));
        spannableString.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#888888"));
                textPaint.setUnderlineText(true);
            }

            public void onClick(View view) {
                Intent intent = new Intent(DeliveryActivity.this, WebActivity.class);
                intent.putExtra("url", ConnectionHelper.cl);
                DeliveryActivity.this.startActivity(intent);
            }
        }, 60, 80, 33);
        spannableString.setSpan(new UnderlineSpan(), 60, 80, 33);
        this.smartBoxConditions.setText(spannableString);
        this.smartBoxConditions.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.b != null) {
            this.b.dismiss();
        }
        super.onDestroy();
    }

    private void a(final SmartBoxData smartBoxData2) {
        this.deliverySmartBox.setVisibility(0);
        this.deliverySmartBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeliveryActivity.this.deliverySmartBox.setBackgroundResource(R.drawable.shop_bg_tv_selector);
                DeliveryActivity.this.deliverySmartBox.setTextColor(DeliveryActivity.this.getResources().getColor(R.color.delivery_selected));
                DeliveryActivity.this.deliveryHome.setBackgroundResource(R.drawable.shop_bg_tv_unselector);
                DeliveryActivity.this.deliveryHome.setTextColor(DeliveryActivity.this.getResources().getColor(R.color.delivery_unselected));
                DeliveryActivity.this.fourHourDelivery.setVisibility(8);
                DeliveryActivity.this.smartBox.setVisibility(0);
            }
        });
        this.smartExplain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CustomCloseDialog.Builder builder = new CustomCloseDialog.Builder(DeliveryActivity.this);
                builder.a(smartBoxData2.title).b(smartBoxData2.explain).b((Boolean) false).a((Boolean) true);
                CustomCloseDialog unused = DeliveryActivity.this.b = builder.a();
                DeliveryActivity.this.b.show();
            }
        });
        this.popSpinnerView.init(new DeliveryPopAdapter(this, smartBoxData2.smartDetailListData, this.smartBoxSelecetdId), new PopSpinnerView.NameFilter() {
            public String a(int i) {
                DeliveryActivity.this.smartSelectItemData = smartBoxData2.smartDetailListData.get(i);
                return smartBoxData2.smartDetailListData.get(i).shortName;
            }
        });
        Iterator<SmartDetailItemData> it = smartBoxData2.smartDetailListData.iterator();
        while (it.hasNext()) {
            SmartDetailItemData next = it.next();
            if (next.id.equals(this.smartBoxSelecetdId)) {
                this.smartSelectItemData = next;
                this.popSpinnerView.setContent(next.shortName);
                this.popSpinnerView.setSelectId(next.id);
            }
        }
    }

    private void a(final FourDeliveryData fourDeliveryData2) {
        this.fourHourDelivery.setVisibility(0);
        this.deliveryFouHour.setText(fourDeliveryData2.brief);
        if (BaseTypeConvertUtil.a(fourDeliveryData2.amount, 0.0f) > 0.0f || TextUtils.isEmpty(fourDeliveryData2.no_amount)) {
            CustomTextView customTextView = this.fourHourPrice;
            customTextView.setText(this.currency + fourDeliveryData2.amount);
        } else {
            this.fourHourPrice.setText(fourDeliveryData2.no_amount);
        }
        this.deliveryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    DeliveryActivity.this.deliveryNoticeView.setBackgroundResource(R.drawable.shop_bg_tv_selector);
                    int unused = DeliveryActivity.this.c = 1;
                    return;
                }
                DeliveryActivity.this.deliveryNoticeView.setBackgroundResource(R.drawable.shop_bg_tv_unselector);
                int unused2 = DeliveryActivity.this.c = 0;
            }
        });
        this.deliveryNoticeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DeliveryActivity.this.deliveryCheckBox.isChecked()) {
                    DeliveryActivity.this.deliveryCheckBox.setChecked(false);
                } else {
                    DeliveryActivity.this.deliveryCheckBox.setChecked(true);
                }
            }
        });
        this.deliveryView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CustomCloseDialog.Builder builder = new CustomCloseDialog.Builder(DeliveryActivity.this);
                builder.a(fourDeliveryData2.title).b(fourDeliveryData2.description).b((Boolean) false).a((Boolean) true);
                CustomCloseDialog unused = DeliveryActivity.this.b = builder.a();
                DeliveryActivity.this.b.show();
            }
        });
    }
}
