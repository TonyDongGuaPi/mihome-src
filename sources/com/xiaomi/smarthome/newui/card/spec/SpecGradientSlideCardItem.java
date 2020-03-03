package com.xiaomi.smarthome.newui.card.spec;

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
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import com.xiaomi.smarthome.newui.widget.RoundedGradientSeekBar;
import org.json.JSONObject;

public class SpecGradientSlideCardItem extends SpecCardItem {
    /* access modifiers changed from: private */
    public double I;
    /* access modifiers changed from: private */
    public double M;
    /* access modifiers changed from: private */
    public double N;

    /* renamed from: a  reason: collision with root package name */
    private RoundedGradientSeekBar f20594a;

    public SpecGradientSlideCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        SpecCard specCard = (SpecCard) l();
        final Device k = k();
        this.f20594a = (RoundedGradientSeekBar) a(viewGroup, R.layout.card_item_slide_gradient).findViewById(R.id.seekbar);
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
            return;
        }
        RoundedGradientSeekBar roundedGradientSeekBar = this.f20594a;
        if (roundedGradientSeekBar != null) {
            if (1 == specCard.b && specCard.c.size() == 2 && ((SpecCardType) specCard.c.get(0)).c() && i2 == 1) {
                roundedGradientSeekBar.getLayoutParams().width = DisplayUtils.a(viewGroup.getContext(), 193.33f);
            }
            if (3 == specCard.b && specCard.c.size() == 3 && ((SpecCardType) specCard.c.get(1)).c() && i2 == 2) {
                roundedGradientSeekBar.getLayoutParams().width = DisplayUtils.a(viewGroup.getContext(), 193.33f);
            }
            ConstraintValue constraintValue = ((SpecProperty) this.K.second).getPropertyDefinition().getConstraintValue();
            if (!(constraintValue instanceof ValueRange)) {
                LogUtilGrey.a("mijia-card", "SpecGradientSlideCardItem not define ValueRange:" + constraintValue);
                return;
            }
            ValueRange valueRange = (ValueRange) constraintValue;
            this.I = NumberUtils.a(valueRange.minValue().getObjectValue(), 0.0d);
            this.M = NumberUtils.a(valueRange.maxValue().getObjectValue(), 0.0d);
            this.N = NumberUtils.a(valueRange.stepValue().getObjectValue(), 0.0d);
            a(this.w, ((SpecCardType) this.G).b(k, (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
            roundedGradientSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (!SpecGradientSlideCardItem.this.j()) {
                        SpecProperty specProperty = (SpecProperty) SpecGradientSlideCardItem.this.K.second;
                        double b2 = SpecGradientSlideCardItem.this.I;
                        double progress = (double) seekBar.getProgress();
                        Double.isNaN(progress);
                        Number a2 = SpecUtils.a(b2 + ((progress * (SpecGradientSlideCardItem.this.M - SpecGradientSlideCardItem.this.I)) / 100.0d), SpecGradientSlideCardItem.this.I, SpecGradientSlideCardItem.this.M, SpecGradientSlideCardItem.this.N, specProperty == null ? null : specProperty.getPropertyDefinition().getFormat());
                        if (k instanceof MiioDeviceV2) {
                            MiotSpecCardManager.f().a(SpecGradientSlideCardItem.this.k().did, (SpecService) SpecGradientSlideCardItem.this.K.first, (SpecProperty) SpecGradientSlideCardItem.this.K.second, a2, (AsyncCallback<JSONObject, Error>) null);
                        }
                    }
                }
            });
        }
    }

    private void a(Device device, Object obj) {
        RoundedGradientSeekBar roundedGradientSeekBar = this.f20594a;
        if (roundedGradientSeekBar != null && obj != null && !obj.equals("null")) {
            try {
                double intValue = (double) Integer.valueOf(String.valueOf(obj)).intValue();
                double d = this.I;
                Double.isNaN(intValue);
                roundedGradientSeekBar.setProgress((((int) (intValue - d)) * 100) / ((int) (this.M - this.I)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20594a = null;
    }

    public void a(String str, Object obj) {
        if (!j() && this.K != null && (this.K.second instanceof SpecProperty)) {
            a(k(), obj);
            b(a(k(), (Operation) null));
        }
    }

    private void b(boolean z) {
        RoundedGradientSeekBar roundedGradientSeekBar = this.f20594a;
        if (roundedGradientSeekBar != null) {
            roundedGradientSeekBar.setClickable(z);
            roundedGradientSeekBar.setFocusable(z);
            roundedGradientSeekBar.setCanDrag(z);
            roundedGradientSeekBar.updateEnableUI(z);
        }
    }
}
