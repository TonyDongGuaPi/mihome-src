package com.xiaomi.smarthome.newui.card.spec;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecAction;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import java.util.List;
import org.json.JSONObject;

public class SpecToggleButtonItem extends SpecCardItem {
    private TextView I;
    private int M;
    private int N;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f20608a;

    public SpecToggleButtonItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_single_button);
        TextView textView = (TextView) a2.findViewById(R.id.desc);
        this.I = textView;
        if (textView != null) {
            this.M = textView.getCurrentTextColor();
            this.N = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
            if (this.K == null || !(this.K.second instanceof SpecProperty)) {
                Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
                return;
            }
            ImageView imageView = (ImageView) a2.findViewById(R.id.image);
            this.f20608a = imageView;
            if (imageView != null) {
                CardItemUtils.a(i2, ((SpecCard) l()).b, ((SpecCard) l()).c, textView, imageView);
                a(this.w, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
                imageView.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        SpecToggleButtonItem.this.c(view);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (!j()) {
            a(view);
            Pair<SpecService, SpecAction> a2 = SpecUtils.a(MiotSpecCardManager.f().a(k()), Operation.a((List<Operation>) this.y, (Object) SpecUtils.a((SpecProperty) this.K.second, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K))));
            if (a2 != null) {
                MiotSpecCardManager.f().a(k().did, a2, (Pair[]) this.v, (AsyncCallback<JSONObject, Error>) null);
            } else {
                MiotSpecCardManager.f().a(k().did, (SpecService) this.K.first, (SpecProperty) this.K.second, ((SpecCardType) this.G).c(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K), (AsyncCallback<JSONObject, Error>) null);
            }
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20608a = null;
        this.I = null;
    }

    private void a(Object obj, Operation operation, Pair<SpecService, SpecAction> pair) {
        TextView textView = this.I;
        if (textView != null) {
            String str = null;
            if (!(operation == null || pair == null)) {
                str = SpecCardType.a(k(), (SpecService) pair.first, (Spec.SpecItem) pair.second, (String) null, (Object) null);
                if (TextUtils.isEmpty(str)) {
                    str = SpecCardType.a(k(), (SpecService) this.K.first, (Spec.SpecItem) this.K.second, String.valueOf(operation.b), ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, obj);
            }
            textView.setText(str);
            if (!a(k(), operation)) {
                textView.setTextColor(this.N);
            } else {
                textView.setTextColor(this.M);
            }
        }
    }

    private void a(ImageView imageView, Object obj, Operation operation, Pair<SpecService, SpecAction> pair) {
        CardItem.State state;
        boolean a2 = a(k(), operation);
        if (this.f20608a != null) {
            this.f20608a.setEnabled(a2);
        }
        Drawable drawable = null;
        if (!a2) {
            state = CardItem.State.UNABLE;
        } else if ((pair == null || this.y.indexOf(operation) != 0) && !SpecUtils.a(obj)) {
            state = CardItem.State.NOR;
        } else {
            state = CardItem.State.SELECTED;
        }
        if (!(operation == null || pair == null)) {
            drawable = state == CardItem.State.UNABLE ? SpecCardType.a(k(), (SpecService) pair.first, (Spec.SpecItem) pair.second, (String) null, (Object) null, CardItem.State.UNABLE) : SpecCardType.a(k(), (SpecService) pair.first, (Spec.SpecItem) pair.second, (String) null, (Object) null, CardItem.State.SELECTED);
        }
        if (drawable == null) {
            drawable = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, obj, state);
        }
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void a(String str, Object obj) {
        ImageView imageView;
        Activity activity;
        if (!j() && this.K != null && (this.K.second instanceof SpecProperty) && (imageView = this.f20608a) != null && (activity = this.E) != null && !activity.isFinishing()) {
            Operation a2 = Operation.a((List<Operation>) this.y, (Object) SpecUtils.a((SpecProperty) this.K.second, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K)));
            Pair<SpecService, SpecAction> a3 = SpecUtils.a(MiotSpecCardManager.f().a(k()), a2);
            a(imageView, obj, a2, a3);
            a(obj, a2, a3);
        }
    }
}
