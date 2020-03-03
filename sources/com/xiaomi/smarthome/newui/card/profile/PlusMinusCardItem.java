package com.xiaomi.smarthome.newui.card.profile;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataFormat;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.spec.SpecUtils;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import com.xiaomi.smarthome.newui.widget.RoundedHorizontalSeekBar;
import org.json.JSONObject;

public class PlusMinusCardItem extends ProfileCardItem {
    private ImageView I;
    private ImageView J;
    private RoundedHorizontalSeekBar S;

    /* renamed from: a  reason: collision with root package name */
    private String f20552a = "";

    public PlusMinusCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        View a2 = a(viewGroup, R.layout.card_item_plus_minus2);
        ImageView imageView = (ImageView) a2.findViewById(R.id.button_right);
        ImageView imageView2 = (ImageView) a2.findViewById(R.id.button_left);
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = (RoundedHorizontalSeekBar) a2.findViewById(R.id.button_center);
        this.S = roundedHorizontalSeekBar;
        this.I = imageView;
        this.J = imageView2;
        if (imageView != null && imageView2 != null && roundedHorizontalSeekBar != null) {
            this.A = this.A == 0.0d ? 1.0d : this.A;
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!PlusMinusCardItem.this.j()) {
                        PlusMinusCardItem.this.a(view);
                        PlusMinusCardItem.this.a(NumberUtils.a(((ProfileCardType) PlusMinusCardItem.this.G).b(k, PlusMinusCardItem.this.w), 0.0d) + PlusMinusCardItem.this.A);
                    }
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!PlusMinusCardItem.this.j()) {
                        PlusMinusCardItem.this.a(view);
                        PlusMinusCardItem.this.a(NumberUtils.a(((ProfileCardType) PlusMinusCardItem.this.G).b(k, PlusMinusCardItem.this.w), 0.0d) - PlusMinusCardItem.this.A);
                    }
                }
            });
            this.f20552a = o();
            if (this.f20552a == null) {
                this.f20552a = "";
            }
            roundedHorizontalSeekBar.setMax(100);
            a(this.w, ((ProfileCardType) this.G).b(k, this.w));
            roundedHorizontalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (!PlusMinusCardItem.this.j()) {
                        PlusMinusCardItem.this.a((double) (((float) PlusMinusCardItem.this.R) + (((float) (((long) seekBar.getProgress()) * (PlusMinusCardItem.this.Q - PlusMinusCardItem.this.R))) / 100.0f)));
                    }
                }
            });
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.I = null;
        this.J = null;
        this.S = null;
    }

    /* access modifiers changed from: private */
    public void a(double d) {
        if (this.y != null && this.y.size() != 0) {
            ((Operation) this.y.get(0)).a((ProfileCardType) this.G, SpecUtils.a(d, (double) this.R, (double) this.Q, this.A, (DataFormat) null), k(), (AsyncCallback<JSONObject, Error>) null);
        }
    }

    private void a(double d, RoundedHorizontalSeekBar roundedHorizontalSeekBar) {
        double d2 = (double) this.R;
        Double.isNaN(d2);
        double d3 = (double) (this.Q - this.R);
        Double.isNaN(d3);
        roundedHorizontalSeekBar.setProgress((int) ((((d - d2) + 0.0d) / d3) * 100.0d));
        a((Object) Double.valueOf(d), k(), roundedHorizontalSeekBar);
    }

    public void a(String str, Object obj) {
        if (!j() && this.E != null && !this.E.isFinishing()) {
            if (this.y != null && this.y.size() > 0) {
                b(a(k(), (Operation) this.y.get(0)));
            }
            double d = 0.0d;
            if (obj != null) {
                d = NumberUtils.a((Object) String.valueOf(obj), 0.0d);
            }
            a(d, this.S);
        }
    }

    private void b(boolean z) {
        ImageView imageView = this.I;
        ImageView imageView2 = this.J;
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = this.S;
        if (imageView != null && imageView2 != null && roundedHorizontalSeekBar != null) {
            imageView.setEnabled(z);
            imageView2.setEnabled(z);
            roundedHorizontalSeekBar.updateEnableUI(z);
            roundedHorizontalSeekBar.setCanSeek(z);
            roundedHorizontalSeekBar.setFocusable(z);
            roundedHorizontalSeekBar.setClickable(z);
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
        String a2 = ((ProfileCardType) this.G).a(this.w, obj, ((ProfileCardType) this.G).e(device, this.w));
        String g = ((ProfileCardType) this.G).f(device, this.w);
        if (!TextUtils.isEmpty(g)) {
            a2 = a2 + g;
        }
        if (!TextUtils.isEmpty(this.f20552a)) {
            a2 = this.f20552a + " " + a2;
        }
        if (!TextUtils.isEmpty(a2)) {
            roundedHorizontalSeekBar.setTextInfo(a2);
        }
    }
}
