package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.FontManager;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TextNumberCardItem extends ProfileCardItem {
    private int I;
    private int J;
    private int S;
    private TextView T;
    private TextView U;
    private TextView V;

    /* renamed from: a  reason: collision with root package name */
    private int f20568a;

    public TextNumberCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_text_number);
        this.f20568a = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.T = (TextView) a2.findViewById(R.id.desc);
        this.U = (TextView) a2.findViewById(R.id.value);
        this.V = (TextView) a2.findViewById(R.id.unit);
        TextView textView = this.T;
        TextView textView2 = this.U;
        TextView textView3 = this.V;
        if (textView != null && textView2 != null && textView3 != null) {
            this.I = textView.getCurrentTextColor();
            this.J = textView2.getCurrentTextColor();
            textView2.setText(CommonApplication.getAppContext().getString(R.string.card_item_fail));
            this.S = textView3.getCurrentTextColor();
            CardItemUtils.a(l(), textView, textView2, textView3);
            textView2.setTypeface(FontManager.a(textView2.getContext(), "fonts/Mitype2018-50.otf"));
            CardItemUtils.a((ArrayList) ((ProfileCard) l()).c, a2, i2);
            a();
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.T = null;
        this.U = null;
        this.V = null;
    }

    private void a(Object obj, Device device, TextView textView, TextView textView2) {
        try {
            String a2 = ((ProfileCardType) this.G).a(this.w, obj, ((ProfileCardType) this.G).e(device, this.w));
            if (TextUtils.isEmpty(a2)) {
                textView.setText(HelpFormatter.f);
            } else {
                textView.setText(a2);
            }
            String g = ((ProfileCardType) this.G).f(device, this.w);
            if (!TextUtils.isEmpty(g)) {
                textView2.setText(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a() {
        if (!j()) {
            TextView textView = this.T;
            final TextView textView2 = this.U;
            final TextView textView3 = this.V;
            if (textView != null && textView2 != null && textView3 != null) {
                String o = o();
                if ((k() instanceof BleDevice) || ((ProfileCard) l()).g || (k().isOnline && this.H == BaseCardRender.DataInitState.DONE)) {
                    textView2.setTextColor(this.J);
                    textView.setTextColor(this.I);
                    textView3.setTextColor(this.S);
                } else {
                    textView.setTextColor(this.f20568a);
                    textView2.setTextColor(this.f20568a);
                    textView3.setTextColor(this.f20568a);
                }
                if (o != null) {
                    textView.setText(o);
                }
                if (a(k())) {
                    XmPluginHostApi.instance().callMethod(k().did, "miIO.info", new JSONArray(), new Callback<Object>() {
                        public void onFailure(int i, String str) {
                        }

                        public void onSuccess(Object obj) {
                        }
                    }, new Parser<Object>() {
                        public Object parse(String str) throws JSONException {
                            if (!TextNumberCardItem.this.j() && !TextUtils.isEmpty(str)) {
                                String optString = new JSONObject(str).optJSONObject("ap").optString("rssi");
                                if (!TextUtils.isEmpty(optString)) {
                                    textView2.setText(optString);
                                    textView3.setText("dBm");
                                }
                            }
                            return null;
                        }
                    });
                } else if (this.w.equals(ControlCardInfoManager.g)) {
                    if (n() == null) {
                        a((Object) null, k(), textView2, textView3);
                    }
                    Map<String, Object> b = ControlCardInfoManager.f().b(k().did);
                    if (b == null || b.get(this.w) == null) {
                        a((Object) null, k(), textView2, textView3);
                    } else {
                        a(b.get(this.w), k(), textView2, textView3);
                    }
                } else {
                    Object d = ((ProfileCardType) this.G).b(k(), this.w);
                    if (!k().model.equals("cgllc.airmonitor.b1") || !"99999".equals(String.valueOf(d))) {
                        a(d, k(), textView2, textView3);
                    } else {
                        textView2.setText(CommonApplication.getAppContext().getString(R.string.card_item_fail));
                        return;
                    }
                }
                String d2 = d(((ProfileCardType) this.G).b(k(), this.w));
                if (!TextUtils.isEmpty(d2)) {
                    this.J = Color.parseColor(d2);
                    textView2.setTextColor(this.J);
                }
            }
        }
    }

    public void a(String str, Object obj) {
        if (!j() && k() != null && this.E != null && !this.E.isFinishing()) {
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Device device) {
        if (device == null) {
            return false;
        }
        return "xiaomi.repeater.v1".equals(device.model) || "xiaomi.repeater.v2".equals(device.model) || "xiaomi.repeater.v3".equals(device.model);
    }
}
