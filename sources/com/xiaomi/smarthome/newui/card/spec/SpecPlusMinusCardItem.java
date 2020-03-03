package com.xiaomi.smarthome.newui.card.spec;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueRange;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import com.xiaomi.smarthome.newui.widget.RoundedHorizontalSeekBar;
import org.json.JSONObject;

public class SpecPlusMinusCardItem extends SpecCardItem {
    private ImageView I;
    private ImageView M;
    private RoundedHorizontalSeekBar N;
    /* access modifiers changed from: private */
    public double O;
    /* access modifiers changed from: private */
    public double P;
    private double Q;

    /* renamed from: a  reason: collision with root package name */
    private String f20598a = "";

    public SpecPlusMinusCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        Device k = k();
        View a2 = a(viewGroup, R.layout.card_item_plus_minus2);
        ImageView imageView = (ImageView) a2.findViewById(R.id.button_right);
        ImageView imageView2 = (ImageView) a2.findViewById(R.id.button_left);
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = (RoundedHorizontalSeekBar) a2.findViewById(R.id.button_center);
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
            return;
        }
        this.N = roundedHorizontalSeekBar;
        this.I = imageView;
        this.M = imageView2;
        if (imageView != null && imageView2 != null && roundedHorizontalSeekBar != null) {
            imageView.setOnClickListener(new View.OnClickListener(k) {
                private final /* synthetic */ Device f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SpecPlusMinusCardItem.this.b(this.f$1, view);
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener(k) {
                private final /* synthetic */ Device f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SpecPlusMinusCardItem.this.a(this.f$1, view);
                }
            });
            ConstraintValue constraintValue = ((SpecProperty) this.K.second).getPropertyDefinition().getConstraintValue();
            if (!(constraintValue instanceof ValueRange)) {
                LogUtilGrey.a("mijia-card", "SpecPlusMinusCardItem not define ValueRange:" + constraintValue);
                return;
            }
            ValueRange valueRange = (ValueRange) constraintValue;
            this.O = NumberUtils.a(valueRange.minValue().getObjectValue(), 0.0d);
            this.P = NumberUtils.a(valueRange.maxValue().getObjectValue(), 0.0d);
            this.Q = NumberUtils.a(valueRange.stepValue().getObjectValue(), 0.0d);
            if (this.Q == 0.0d) {
                if (this.A != 0.0d) {
                    this.Q = this.A;
                } else {
                    this.Q = 1.0d;
                }
            }
            this.f20598a = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
            if (this.f20598a == null) {
                this.f20598a = "";
            }
            roundedHorizontalSeekBar.setMax(100);
            a(this.w, ((SpecCardType) this.G).b(k, (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
            roundedHorizontalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (!SpecPlusMinusCardItem.this.j()) {
                        SpecPlusMinusCardItem specPlusMinusCardItem = SpecPlusMinusCardItem.this;
                        double b = SpecPlusMinusCardItem.this.O;
                        double progress = (double) seekBar.getProgress();
                        Double.isNaN(progress);
                        specPlusMinusCardItem.a(b + ((progress * (SpecPlusMinusCardItem.this.P - SpecPlusMinusCardItem.this.O)) / 100.0d));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Device device, View view) {
        if (!j()) {
            a(view);
            a(NumberUtils.a(((SpecCardType) this.G).b(device, (Pair<SpecService, ? extends Spec.SpecItem>) this.K), 0.0d) + this.Q);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Device device, View view) {
        if (!j()) {
            a(view);
            a(NumberUtils.a(((SpecCardType) this.G).b(device, (Pair<SpecService, ? extends Spec.SpecItem>) this.K), 0.0d) - this.Q);
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.I = null;
        this.M = null;
        this.N = null;
    }

    /* access modifiers changed from: private */
    public void a(double d) {
        MiotSpecCardManager.f().a(k().did, (SpecService) this.K.first, (SpecProperty) this.K.second, SpecUtils.a(d, this.O, this.P, this.Q, this.K.second == null ? null : ((SpecProperty) this.K.second).getPropertyDefinition().getFormat()), (AsyncCallback<JSONObject, Error>) null);
    }

    private void a(double d, RoundedHorizontalSeekBar roundedHorizontalSeekBar) {
        roundedHorizontalSeekBar.setProgress((int) ((((d - this.O) + 0.0d) / (this.P - this.O)) * 100.0d));
        a((Object) Double.valueOf(d), k(), roundedHorizontalSeekBar);
    }

    public void a(String str, Object obj) {
        if (!j() && this.E != null && !this.E.isFinishing()) {
            double d = 0.0d;
            if (obj != null) {
                d = NumberUtils.a((Object) String.valueOf(obj), 0.0d);
            }
            a(d, this.N);
            b(a(k(), (Operation) null));
        }
    }

    private void b(boolean z) {
        ImageView imageView = this.I;
        ImageView imageView2 = this.M;
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = this.N;
        if (imageView != null && imageView2 != null && roundedHorizontalSeekBar != null) {
            imageView.setEnabled(z);
            imageView2.setEnabled(z);
            roundedHorizontalSeekBar.updateEnableUI(z);
            if (z) {
                imageView.setImageResource(R.drawable.btn_plus);
                imageView2.setImageResource(R.drawable.btn_minus);
                return;
            }
            imageView.setImageResource(R.drawable.btn_plus_unable);
            imageView2.setImageResource(R.drawable.btn_minus_unable);
        }
    }

    private void a(Object obj, Device device, RoundedHorizontalSeekBar roundedHorizontalSeekBar) {
        if (this.K != null && (this.K.second instanceof SpecProperty)) {
            String a2 = ((SpecCardType) this.G).a((Pair<SpecService, ? extends Spec.SpecItem>) this.K, obj, ((SpecCardType) this.G).e(device, (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
            String g = ((SpecCardType) this.G).f(device, (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
            if (!TextUtils.isEmpty(g)) {
                a2 = a2 + g;
            }
            if (!TextUtils.isEmpty(this.f20598a)) {
                a2 = this.f20598a + " " + a2;
            }
            if (!TextUtils.isEmpty(a2)) {
                roundedHorizontalSeekBar.setTextInfo(a2);
            }
        }
    }
}
