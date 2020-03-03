package com.xiaomi.smarthome.newui.card.spec;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
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

public class SpecSlideCardItem2 extends SpecCardItem {
    /* access modifiers changed from: private */
    public double I;
    /* access modifiers changed from: private */
    public double M;
    /* access modifiers changed from: private */
    public double N;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public RoundedHorizontalSeekBar f20604a;

    public SpecSlideCardItem2(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        SpecCard specCard = (SpecCard) l();
        final Device k = k();
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = (RoundedHorizontalSeekBar) a(viewGroup, R.layout.card_item_slide2).findViewById(R.id.seekbar);
        this.f20604a = roundedHorizontalSeekBar;
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
            return;
        }
        if (!TextUtils.isEmpty(this.z)) {
            StringBuilder sb = new StringBuilder();
            sb.append(MiotSpecCardManager.f().d());
            MiotSpecCardManager f = MiotSpecCardManager.f();
            sb.append(f.a(this.z + ".png"));
            roundedHorizontalSeekBar.setImageIcon(BitmapFactory.decodeFile(sb.toString()));
        }
        if (roundedHorizontalSeekBar != null) {
            ConstraintValue constraintValue = ((SpecProperty) this.K.second).getPropertyDefinition().getConstraintValue();
            if (!(constraintValue instanceof ValueRange)) {
                LogUtilGrey.a("mijia-card", "SpecSlideCardItem2 not define ValueRange:" + constraintValue);
                return;
            }
            ValueRange valueRange = (ValueRange) constraintValue;
            this.I = NumberUtils.a(valueRange.minValue().getObjectValue(), 0.0d);
            this.M = NumberUtils.a(valueRange.maxValue().getObjectValue(), 0.0d);
            this.N = NumberUtils.a(valueRange.stepValue().getObjectValue(), 0.0d);
            a(this.w, ((SpecCardType) this.G).b(k, (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
            roundedHorizontalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (!SpecSlideCardItem2.this.j() && SpecSlideCardItem2.this.f20604a != null) {
                        SpecProperty specProperty = (SpecProperty) SpecSlideCardItem2.this.K.second;
                        double c = SpecSlideCardItem2.this.I;
                        double progress = (double) seekBar.getProgress();
                        Double.isNaN(progress);
                        Number a2 = SpecUtils.a(c + ((progress * (SpecSlideCardItem2.this.M - SpecSlideCardItem2.this.I)) / 100.0d), SpecSlideCardItem2.this.I, SpecSlideCardItem2.this.M, SpecSlideCardItem2.this.N, specProperty == null ? null : specProperty.getPropertyDefinition().getFormat());
                        if (k instanceof MiioDeviceV2) {
                            MiotSpecCardManager.f().a(SpecSlideCardItem2.this.k().did, (SpecService) SpecSlideCardItem2.this.K.first, specProperty, a2, (AsyncCallback<JSONObject, Error>) null);
                        }
                    }
                }
            });
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20604a = null;
    }

    private void a(Device device, Object obj) {
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = this.f20604a;
        if (roundedHorizontalSeekBar != null && obj != null && !obj.equals("null")) {
            try {
                double intValue = (double) Integer.valueOf(String.valueOf(obj)).intValue();
                double d = this.I;
                Double.isNaN(intValue);
                roundedHorizontalSeekBar.setProgress((((int) (intValue - d)) * 100) / ((int) (this.M - this.I)));
            } catch (Exception e) {
                Log.e("mijia-card", "setSeekBar", e);
            }
        }
    }

    public void a(String str, Object obj) {
        if (!j() && this.E != null && !this.E.isFinishing()) {
            b(a(k(), (Operation) null));
            a(k(), obj);
        }
    }

    private void b(boolean z) {
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = this.f20604a;
        if (roundedHorizontalSeekBar != null) {
            roundedHorizontalSeekBar.setCanSeek(z);
            roundedHorizontalSeekBar.setFocusable(z);
            roundedHorizontalSeekBar.setClickable(z);
            roundedHorizontalSeekBar.updateEnableUI(z);
        }
    }
}
