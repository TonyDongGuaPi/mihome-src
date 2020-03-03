package com.xiaomi.smarthome.newui.card.spec;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class SpecMultiButtonCardItem extends SpecCardItem {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20596a = 3;
    private List<View> I = new ArrayList();
    private int M = -1;
    private Map<View, CardItem.State> N = new HashMap();
    private List<Integer> O = new ArrayList();
    private int P;
    private final Object Q = new Object();
    private List<ValueDefinition> R = new ArrayList();
    private int S;
    private PropertyDefinition T;

    public SpecMultiButtonCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        viewGroup.getContext();
        Device k = k();
        View a2 = a(viewGroup, R.layout.card_item_multibutton);
        LinearLayout linearLayout = (LinearLayout) a2.findViewById(R.id.button_scroll_parent);
        LinearLayout linearLayout2 = (LinearLayout) a2.findViewById(R.id.button_parent);
        this.I.clear();
        this.O.clear();
        this.P = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
            return;
        }
        this.T = ((SpecProperty) this.K.second).getPropertyDefinition();
        ConstraintValue constraintValue = this.T.getConstraintValue();
        int i3 = 3;
        if (constraintValue instanceof ValueList) {
            this.R.clear();
            List<ValueDefinition> values = ((ValueList) constraintValue).values();
            if (this.J != null) {
                HashMap hashMap = new HashMap();
                for (int i4 = 0; i4 < values.size(); i4++) {
                    hashMap.put(CardItemUtils.a(values.get(i4).description()), Integer.valueOf(i4));
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = this.J.iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) hashMap.get(CardItemUtils.a((String) it.next()));
                    if (num != null) {
                        arrayList.add(values.get(num.intValue()));
                    }
                }
                if (arrayList.size() >= 3 || arrayList.size() == values.size()) {
                    this.R.addAll(arrayList);
                    LogUtil.c("mijia-card", "model:" + k.model + "SpecMultiButtonCardItem sort desc by " + this.J);
                } else {
                    this.R.addAll(values);
                }
            } else {
                this.R.addAll(values);
            }
        } else {
            LogUtil.c("mijia-card", "SpecMultiButtonCardItem not get valuelist with " + this.w + " did:" + k.did);
        }
        if (this.R.size() <= 3) {
            i3 = this.R.size();
        }
        this.S = i3;
        linearLayout.setVisibility(8);
        if (this.S == 2) {
            int i5 = linearLayout2.getResources().getDisplayMetrics().widthPixels / 8;
            linearLayout2.setPadding(i5, 0, i5, 0);
        }
        for (int i6 = 0; i6 < this.S; i6++) {
            View inflate = LayoutInflater.from(CommonApplication.getAppContext()).inflate(R.layout.miui10_card_item_button_item, (ViewGroup) null);
            linearLayout2.addView(inflate, new LinearLayout.LayoutParams(0, -1, 1.0f));
            final View findViewById = inflate.findViewById(R.id.rl_item);
            this.I.add(findViewById);
            TextView textView = (TextView) findViewById.findViewById(R.id.button_name);
            this.O.add(Integer.valueOf(textView.getCurrentTextColor()));
            textView.setText(((SpecCardType) this.G).a(k, (Pair<SpecService, ? extends Spec.SpecItem>) this.K, this.R.get(i6).value().getObjectValue()));
            inflate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (findViewById.isClickable()) {
                        findViewById.performClick();
                    }
                }
            });
        }
        a(this.w, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
        for (int i7 = 0; i7 < this.S; i7++) {
            synchronized (this.Q) {
                this.I.get(i7).setOnClickListener(new View.OnClickListener(i7, k) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ Device f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        SpecMultiButtonCardItem.this.a(this.f$1, this.f$2, view);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(int i, Device device, View view) {
        if (!j()) {
            a(view);
            a(i, true);
            if (device instanceof MiioDeviceV2) {
                Object objectValue = this.R.get(i).value().getObjectValue();
                LogUtil.c("mijia-card", "SpecMultiButtonCardItem click prop:" + this.w + " value:" + objectValue + " postion:" + i);
                MiotSpecCardManager.f().a(((MiioDeviceV2) device).n(), (SpecService) this.K.first, (SpecProperty) this.K.second, objectValue, (AsyncCallback<JSONObject, Error>) null);
            }
        }
    }

    public void i() {
        synchronized (this.Q) {
            super.i();
            a(true);
            this.E = null;
            this.I.clear();
            this.N.clear();
        }
    }

    private void a(int i, boolean z) {
        CardItem.State state;
        for (int i2 = 0; i2 < this.I.size(); i2++) {
            View view = this.I.get(i2);
            CardItem.State state2 = this.N.get(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            Animation animation = imageView.getAnimation();
            if (animation != null) {
                animation.cancel();
            }
            if (i2 == i) {
                state = CardItem.State.SELECTED;
            } else if (i2 == this.M) {
                state = CardItem.State.NOR;
            } else {
                state = a(k(), (Operation) null) ? CardItem.State.NOR : CardItem.State.UNABLE;
            }
            if (this.T.readable() || state == CardItem.State.UNABLE) {
                Object objectValue = this.R.get(i2).value().getObjectValue();
                Drawable a2 = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, objectValue, state);
                if (!z || state2 == state) {
                    imageView.setImageDrawable(a2);
                } else {
                    TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, objectValue, state2), a2});
                    transitionDrawable.setCrossFadeEnabled(true);
                    imageView.setImageDrawable(transitionDrawable);
                    transitionDrawable.startTransition(200);
                }
            } else {
                a(i2, imageView);
            }
        }
    }

    private void a(int i, ImageView imageView) {
        if (!(imageView.getDrawable() instanceof StateListDrawable)) {
            Object objectValue = this.R.get(i).value().getObjectValue();
            Drawable a2 = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, objectValue, CardItem.State.SELECTED);
            Drawable a3 = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, objectValue, CardItem.State.NOR);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{-16842910}, a3);
            stateListDrawable.addState(new int[]{16842919}, a2);
            stateListDrawable.addState(new int[0], a3);
            imageView.setImageDrawable(stateListDrawable);
        }
    }

    public void a(String str, Object obj) {
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
        } else {
            a(false, obj);
        }
    }

    private void a(boolean z, Object obj) {
        CardItem.State state;
        if (!j() && this.E != null && !this.E.isFinishing()) {
            try {
                this.M = -1;
                int i = 0;
                while (true) {
                    if (i < this.S) {
                        if (obj != null && obj.equals(this.R.get(i).value().getObjectValue())) {
                            this.M = i;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                Log.i("mijia-card", "SpecMultiButtonCardItem", e);
            }
            synchronized (this.Q) {
                for (int i2 = 0; i2 < this.I.size(); i2++) {
                    View view = this.I.get(i2);
                    CardItem.State state2 = this.N.get(view);
                    if (!a(k(), (Operation) null)) {
                        state = CardItem.State.UNABLE;
                    } else if (i2 == this.M) {
                        state = CardItem.State.SELECTED;
                    } else {
                        state = CardItem.State.NOR;
                    }
                    ImageView imageView = (ImageView) view.findViewById(R.id.image);
                    if (this.T.readable() || state == CardItem.State.UNABLE) {
                        Animation animation = imageView.getAnimation();
                        if (animation != null) {
                            animation.cancel();
                        }
                        Object objectValue = this.R.get(i2).value().getObjectValue();
                        Drawable a2 = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, objectValue, state);
                        if (!z || state == state2) {
                            imageView.setImageDrawable(a2);
                        } else {
                            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, objectValue, state2), a2});
                            transitionDrawable.setCrossFadeEnabled(true);
                            imageView.setImageDrawable(transitionDrawable);
                            transitionDrawable.startTransition(200);
                        }
                    } else {
                        a(i2, imageView);
                        state = CardItem.State.NOR;
                    }
                    TextView textView = (TextView) view.findViewById(R.id.button_name);
                    if (state == CardItem.State.UNABLE) {
                        view.setClickable(false);
                        view.setFocusable(false);
                        view.setEnabled(false);
                        textView.setTextColor(this.P);
                    }
                    if (state == CardItem.State.SELECTED) {
                        view.setClickable(false);
                        view.setFocusable(true);
                        view.setEnabled(false);
                        textView.setTextColor(this.O.get(i2).intValue());
                    }
                    if (state == CardItem.State.NOR) {
                        view.setClickable(true);
                        view.setFocusable(true);
                        view.setEnabled(true);
                        textView.setTextColor(this.O.get(i2).intValue());
                    }
                    this.N.put(view, state);
                }
            }
        }
    }
}
