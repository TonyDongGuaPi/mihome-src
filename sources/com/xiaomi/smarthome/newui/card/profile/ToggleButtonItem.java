package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.Operation;
import java.util.List;
import org.json.JSONObject;

public class ToggleButtonItem extends ProfileCardItem {
    private TextView I;
    private Drawable J;
    private Drawable S;
    private Drawable T;
    private State U = State.NOR;
    private int V;
    private int W;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f20572a;

    private enum State {
        NOR("normal"),
        SELECTED("selected"),
        UNABLE("unable");
        
        String desc;

        private State(String str) {
            this.desc = str;
        }

        public String toString() {
            return this.desc;
        }
    }

    public ToggleButtonItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_single_button);
        TextView textView = (TextView) a2.findViewById(R.id.desc);
        this.V = textView.getCurrentTextColor();
        this.W = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.I = textView;
        final ImageView imageView = (ImageView) a2.findViewById(R.id.image);
        this.f20572a = imageView;
        CardItemUtils.a(i2, ((ProfileCard) l()).b, ((ProfileCard) l()).c, textView, imageView);
        String str = ((Operation) this.y.get(0)).b;
        this.S = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.NOR);
        this.J = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.SELECTED);
        if (this.y.size() == 2 && !TextUtils.isEmpty(((Operation) this.y.get(1)).j)) {
            this.T = ((ProfileCardType) this.G).a(k(), this.w, (Object) ((Operation) this.y.get(1)).b, CardItem.State.UNABLE);
        }
        if (this.T == null && !TextUtils.isEmpty(((Operation) this.y.get(0)).j)) {
            this.T = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.UNABLE);
        }
        a(k().did, imageView, false, ((ProfileCardType) this.G).b(k(), this.w));
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!ToggleButtonItem.this.j()) {
                    ToggleButtonItem.this.a(view);
                    Object d = ((ProfileCardType) ToggleButtonItem.this.G).b(ToggleButtonItem.this.k(), ToggleButtonItem.this.w);
                    ToggleButtonItem.this.a(ToggleButtonItem.this.k().did, imageView, true, d);
                    Operation a2 = Operation.a((List<Operation>) ToggleButtonItem.this.y, d);
                    if (a2 != null && (ToggleButtonItem.this.k() instanceof MiioDeviceV2)) {
                        ToggleButtonItem.this.a((View) imageView, false);
                        a2.a((ProfileCardType) ToggleButtonItem.this.G, a2.a((List<Operation>) ToggleButtonItem.this.y, ToggleButtonItem.this.n()), ToggleButtonItem.this.k(), (AsyncCallback<JSONObject, Error>) null);
                    }
                }
            }
        });
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20572a = null;
        this.I = null;
        this.J = null;
        this.S = null;
        this.T = null;
    }

    /* access modifiers changed from: private */
    public void a(View view, boolean z) {
        view.setEnabled(z);
        view.setFocusable(z);
        view.setClickable(z);
    }

    /* access modifiers changed from: private */
    public void a(String str, ImageView imageView, boolean z, Object obj) {
        TextView textView = this.I;
        ImageView imageView2 = this.f20572a;
        if (textView != null && imageView2 != null) {
            Drawable e = e(obj);
            textView.setText(((ProfileCardType) this.G).a(k(), this.w, obj));
            State state = State.NOR;
            if (this.T == e) {
                state = State.UNABLE;
                textView.setTextColor(this.W);
                a((View) imageView2, false);
            } else {
                textView.setTextColor(this.V);
                a((View) imageView2, true);
            }
            if (this.S == e) {
                state = State.NOR;
            }
            if (this.J == e) {
                state = State.SELECTED;
            }
            if (!z) {
                this.U = state;
                imageView.setImageDrawable(e);
            } else if (state != this.U) {
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{e, a()});
                transitionDrawable.setCrossFadeEnabled(true);
                imageView.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(200);
                this.U = state;
            }
        }
    }

    private Drawable e(Object obj) {
        if (this.y == null || this.y.size() != 2) {
            return this.T;
        }
        Operation operation = (Operation) this.y.get(0);
        if (!((Operation) this.y.get(0)).a(String.valueOf(obj))) {
            operation = (Operation) this.y.get(1);
        }
        if (!a(k(), operation)) {
            return this.T;
        }
        return f(obj);
    }

    private Drawable a() {
        if (this.U == State.NOR) {
            return this.S;
        }
        if (this.U == State.SELECTED) {
            return this.J;
        }
        if (this.U == State.UNABLE) {
            return this.T;
        }
        return this.S;
    }

    private Drawable f(Object obj) {
        if (this.y == null || this.y.size() != 2) {
            return this.S;
        }
        if (((Operation) this.y.get(0)).a(String.valueOf(obj))) {
            return this.J;
        }
        return this.S;
    }

    public void a(String str, Object obj) {
        ImageView imageView;
        if (!j() && (imageView = this.f20572a) != null) {
            a(k().did, imageView, false, obj);
        }
    }
}
