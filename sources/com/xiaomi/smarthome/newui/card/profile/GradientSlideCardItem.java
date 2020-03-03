package com.xiaomi.smarthome.newui.card.profile;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.widget.RoundedGradientSeekBar;
import java.util.List;
import org.json.JSONObject;

public class GradientSlideCardItem extends ProfileCardItem {

    /* renamed from: a  reason: collision with root package name */
    private RoundedGradientSeekBar f20532a;

    public GradientSlideCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        this.f20532a = (RoundedGradientSeekBar) a(viewGroup, R.layout.card_item_slide_gradient).findViewById(R.id.seekbar);
        RoundedGradientSeekBar roundedGradientSeekBar = this.f20532a;
        if (roundedGradientSeekBar != null) {
            List list = profileCard.c;
            if (1 == profileCard.b && list.size() == 2 && ((ProfileCardType) list.get(0)).c() && i2 == 1) {
                this.f20532a.getLayoutParams().width = DisplayUtils.a(viewGroup.getContext(), 193.33f);
            }
            if (3 == profileCard.b && list.size() == 3 && ((ProfileCardType) list.get(1)).c() && i2 == 2) {
                this.f20532a.getLayoutParams().width = DisplayUtils.a(viewGroup.getContext(), 193.33f);
            }
            if (!TextUtils.isEmpty(this.L)) {
                roundedGradientSeekBar.setColorFrom(b(this.L));
            }
            if (!TextUtils.isEmpty(this.M)) {
                roundedGradientSeekBar.setColorTo(b(this.M));
            }
            if (this.y != null && this.y.size() != 0) {
                final Operation operation = (Operation) this.y.get(0);
                if (this.Q != this.R) {
                    Object d = ((ProfileCardType) this.G).b(k, this.w);
                    if (d != null && !d.equals("null")) {
                        try {
                            roundedGradientSeekBar.setProgress(Math.round((((float) (((long) Integer.valueOf(String.valueOf(d)).intValue()) - this.R)) * 100.0f) / ((float) (this.Q - this.R))));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    a(this.w, ((ProfileCardType) this.G).b(k(), this.w));
                    roundedGradientSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                        }

                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        public void onStopTrackingTouch(SeekBar seekBar) {
                            if (!GradientSlideCardItem.this.j()) {
                                Integer valueOf = Integer.valueOf(Math.round(((float) GradientSlideCardItem.this.R) + (((float) (((long) seekBar.getProgress()) * (GradientSlideCardItem.this.Q - GradientSlideCardItem.this.R))) / 100.0f)));
                                if (k instanceof MiioDeviceV2) {
                                    operation.a((ProfileCardType) GradientSlideCardItem.this.G, valueOf, k, (AsyncCallback<JSONObject, Error>) null);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20532a = null;
    }

    public void a(String str, Object obj) {
        if (!j()) {
            boolean a2 = a(k(), (Operation) this.y.get(0));
            a(k(), obj);
            b(a2);
        }
    }

    private void a(Device device, Object obj) {
        RoundedGradientSeekBar roundedGradientSeekBar = this.f20532a;
        if (roundedGradientSeekBar != null && obj != null && !obj.equals("null")) {
            try {
                roundedGradientSeekBar.setProgress(Math.round((((float) (((long) Integer.valueOf(String.valueOf(obj)).intValue()) - this.R)) * 100.0f) / ((float) (this.Q - this.R))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void b(boolean z) {
        RoundedGradientSeekBar roundedGradientSeekBar = this.f20532a;
        if (roundedGradientSeekBar != null) {
            roundedGradientSeekBar.setClickable(z);
            roundedGradientSeekBar.setFocusable(z);
            roundedGradientSeekBar.setCanDrag(z);
            roundedGradientSeekBar.updateEnableUI(z);
        }
    }
}
