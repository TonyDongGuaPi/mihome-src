package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.widget.RoundedHorizontalSeekBar;
import org.json.JSONObject;

public class SlideCardItem2 extends ProfileCardItem {

    /* renamed from: a  reason: collision with root package name */
    RoundedHorizontalSeekBar f20566a;

    public SlideCardItem2(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = (RoundedHorizontalSeekBar) a(viewGroup, R.layout.card_item_slide2).findViewById(R.id.seekbar);
        this.f20566a = roundedHorizontalSeekBar;
        if (roundedHorizontalSeekBar != null) {
            if (!TextUtils.isEmpty(this.N)) {
                StringBuilder sb = new StringBuilder();
                sb.append(ControlCardInfoManager.f().d());
                ControlCardInfoManager f = ControlCardInfoManager.f();
                sb.append(f.a(this.N + ".png"));
                roundedHorizontalSeekBar.setImageIcon(BitmapFactory.decodeFile(sb.toString()));
            }
            if (this.y != null && this.y.size() != 0) {
                final Operation operation = (Operation) this.y.get(0);
                if (this.Q != this.R) {
                    a(this.w, ((ProfileCardType) this.G).b(k(), this.w));
                    roundedHorizontalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                        }

                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        public void onStopTrackingTouch(SeekBar seekBar) {
                            if (!SlideCardItem2.this.j() && SlideCardItem2.this.f20566a != null) {
                                Integer valueOf = Integer.valueOf(Math.round(((float) SlideCardItem2.this.R) + (((float) (((long) seekBar.getProgress()) * (SlideCardItem2.this.Q - SlideCardItem2.this.R))) / 100.0f)));
                                if (k instanceof MiioDeviceV2) {
                                    operation.a((ProfileCardType) SlideCardItem2.this.G, valueOf, k, (AsyncCallback<JSONObject, Error>) null);
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
        this.f20566a = null;
    }

    private void a(Device device, Object obj) {
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = this.f20566a;
        if (roundedHorizontalSeekBar != null && obj != null && !obj.equals("null")) {
            try {
                roundedHorizontalSeekBar.setProgress(Math.round((((float) (((long) Integer.valueOf(String.valueOf(obj)).intValue()) - this.R)) * 100.0f) / ((float) (this.Q - this.R))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a() {
        b(a(k(), (Operation) this.y.get(0)));
    }

    public void a(String str, Object obj) {
        if (!j() && this.E != null && !this.E.isFinishing()) {
            a(k(), obj);
            a();
        }
    }

    private void b(boolean z) {
        RoundedHorizontalSeekBar roundedHorizontalSeekBar = this.f20566a;
        if (roundedHorizontalSeekBar != null) {
            roundedHorizontalSeekBar.setCanSeek(z);
            roundedHorizontalSeekBar.setFocusable(z);
            roundedHorizontalSeekBar.setClickable(z);
            roundedHorizontalSeekBar.updateEnableUI(z);
        }
    }
}
