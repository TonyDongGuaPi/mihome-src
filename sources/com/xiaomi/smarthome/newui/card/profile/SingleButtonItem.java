package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.lite.LiteSoundManager;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.Operation;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class SingleButtonItem extends ProfileCardItem {
    private TextView I;
    private Drawable J;
    private Drawable S;
    private Drawable T;
    private State U = State.NOR;
    private int V;
    private int W;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f20564a;

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

    public SingleButtonItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        View a2 = a(viewGroup, R.layout.miui10_card_item_single_button);
        TextView textView = (TextView) a2.findViewById(R.id.desc);
        this.V = textView.getCurrentTextColor();
        this.W = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.I = textView;
        ImageView imageView = (ImageView) a2.findViewById(R.id.image);
        this.f20564a = imageView;
        CardItemUtils.a(i2, profileCard.b, profileCard.c, textView, imageView);
        String str = ((Operation) this.y.get(0)).b;
        this.S = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.NOR);
        this.J = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.SELECTED);
        this.T = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.UNABLE);
        a(this.w, ((ProfileCardType) this.G).b(k(), this.w));
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SingleButtonItem.this.j()) {
                    SingleButtonItem.this.a(view);
                    Operation a2 = Operation.a((List<Operation>) SingleButtonItem.this.y, ((ProfileCardType) SingleButtonItem.this.G).b(k, SingleButtonItem.this.w));
                    if (a2 != null && (k instanceof MiioDeviceV2)) {
                        a2.a((ProfileCardType) SingleButtonItem.this.G, a2.a((List<Operation>) SingleButtonItem.this.y, SingleButtonItem.this.n()), k, (AsyncCallback<JSONObject, Error>) null);
                    }
                }
            }
        });
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
        this.f20564a = null;
        this.I = null;
        this.J = null;
        this.S = null;
        this.T = null;
    }

    private void a(View view, boolean z) {
        view.setEnabled(z);
        view.setFocusable(z);
        view.setClickable(z);
    }

    private void a(String str, ImageView imageView, Object obj, boolean z) {
        TextView textView = this.I;
        ImageView imageView2 = this.f20564a;
        if (textView != null && imageView2 != null) {
            String a2 = ((ProfileCardType) this.G).a(k(), this.w, obj);
            if (a2 != null) {
                textView.setText(a2);
            }
            Drawable e = e(obj);
            State state = State.NOR;
            if (this.T == e) {
                state = State.UNABLE;
                textView.setTextColor(this.W);
                a((View) imageView2, false);
            } else {
                a((View) imageView2, true);
            }
            if (this.S == e) {
                state = State.NOR;
                textView.setTextColor(this.V);
            }
            if (this.J == e) {
                state = State.SELECTED;
                textView.setTextColor(this.V);
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
        if (!a(k(), (this.y == null || this.y.size() <= 0) ? null : (Operation) this.y.get(0))) {
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
        List<Object> list = n().j;
        if (obj == null || list == null) {
            LogUtil.b("mijia-card", "getDrawableOnOrOff model:" + k().model + " prop:" + n().f20557a + " switchStatus is not config");
            return this.S;
        }
        boolean z = false;
        Iterator<Object> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (String.valueOf(it.next()).equals(String.valueOf(obj))) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            return this.J;
        }
        return this.S;
    }

    public void a(String str, Object obj) {
        ImageView imageView;
        if (!j() && (imageView = this.f20564a) != null) {
            a(k().did, imageView, obj, false);
        }
    }
}
