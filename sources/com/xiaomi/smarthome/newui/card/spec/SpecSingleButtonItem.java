package com.xiaomi.smarthome.newui.card.spec;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.lite.LiteSoundManager;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import org.json.JSONObject;

public class SpecSingleButtonItem extends SpecCardItem {
    private TextView I;
    private Drawable M;
    private Drawable N;
    private Drawable O;
    private int P;
    private int Q;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f20603a;

    public SpecSingleButtonItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_single_button);
        TextView textView = (TextView) a2.findViewById(R.id.desc);
        this.P = textView.getCurrentTextColor();
        this.Q = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.I = textView;
        ImageView imageView = (ImageView) a2.findViewById(R.id.image);
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
            return;
        }
        this.f20603a = imageView;
        if (this.C.a(viewGroup) == BaseCardRender.LayoutPosition.TITLE) {
            this.M = imageView.getResources().getDrawable(R.drawable.title_btn_power_on);
            this.N = imageView.getResources().getDrawable(R.drawable.title_btn_power_off);
            this.O = imageView.getResources().getDrawable(R.drawable.title_btn_power_unable);
        } else {
            this.N = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, (Object) null, CardItem.State.NOR);
            this.M = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, (Object) null, CardItem.State.SELECTED);
            this.O = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, (Object) null, CardItem.State.UNABLE);
        }
        CardItemUtils.a(i2, ((SpecCard) l()).b, ((SpecCard) l()).c, textView, imageView);
        a(this.w, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
        imageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SpecSingleButtonItem.this.c(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (!j()) {
            a(view);
            MiotSpecCardManager.f().a(k().did, (SpecService) this.K.first, (SpecProperty) this.K.second, ((SpecCardType) this.G).c(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K), (AsyncCallback<JSONObject, Error>) null);
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        super.a(view);
        if (this.D.hasVibrator()) {
            this.D.vibrate(100);
        }
        LiteSoundManager.a().d();
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20603a = null;
        this.I = null;
        this.M = null;
        this.N = null;
        this.O = null;
    }

    private void d(Object obj) {
        TextView textView = this.I;
        if (textView != null && this.K != null && this.K.second != null) {
            String a2 = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, obj);
            if (!TextUtils.isEmpty(a2)) {
                textView.setText(a2);
            }
        }
    }

    private void e(Object obj) {
        TextView textView = this.I;
        ImageView imageView = this.f20603a;
        if (textView != null && imageView != null) {
            a(imageView, textView, obj);
        }
    }

    private void a(ImageView imageView, TextView textView, Object obj) {
        Drawable drawable;
        if (!a(k(), (Operation) null)) {
            textView.setTextColor(this.Q);
            imageView.setImageDrawable(this.O);
            return;
        }
        Boolean bool = false;
        if (!bool.equals(obj)) {
            Integer num = 0;
            if (!num.equals(obj)) {
                drawable = this.M;
                imageView.setImageDrawable(drawable);
                textView.setTextColor(this.P);
            }
        }
        drawable = this.N;
        imageView.setImageDrawable(drawable);
        textView.setTextColor(this.P);
    }

    public void a(String str, Object obj) {
        if (!j()) {
            d(obj);
            if (this.f20603a != null) {
                e(obj);
            }
        }
    }
}
