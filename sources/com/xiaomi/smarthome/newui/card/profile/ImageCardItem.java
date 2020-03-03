package com.xiaomi.smarthome.newui.card.profile;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.Operation;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ImageCardItem extends ProfileCardItem {
    private Drawable I;
    private Drawable J;
    private Drawable S;
    private State T = State.NOR;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f20541a;

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

    public ImageCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        this.f20541a = (ImageView) a(viewGroup, R.layout.miui10_card_item_single_image).findViewById(R.id.image);
        if (this.y == null || this.y.size() < 1) {
            LogUtilGrey.a("mijia-card", "ImageCardItem not config operations");
            return;
        }
        String str = ((Operation) this.y.get(0)).b;
        this.J = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.NOR);
        this.I = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.SELECTED);
        this.S = ((ProfileCardType) this.G).a(k(), this.w, (Object) str, CardItem.State.UNABLE);
        ImageView imageView = this.f20541a;
        if (imageView != null) {
            a(this.w, ((ProfileCardType) this.G).b(k(), this.w));
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!ImageCardItem.this.j()) {
                        ImageCardItem.this.a(view);
                        Operation a2 = Operation.a((List<Operation>) ImageCardItem.this.y, ((ProfileCardType) ImageCardItem.this.G).b(k, ImageCardItem.this.w));
                        if (a2 != null && (k instanceof MiioDeviceV2)) {
                            a2.a((ProfileCardType) ImageCardItem.this.G, ImageCardItem.this.g(a2.b), k, (AsyncCallback<JSONObject, Error>) null);
                        }
                    }
                }
            });
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20541a = null;
        this.I = null;
        this.J = null;
        this.S = null;
    }

    public void a(String str, Object obj) {
        if (!j()) {
            Activity activity = this.E;
            ImageView imageView = this.f20541a;
            if (imageView != null && activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                a(k().did, imageView, obj, false);
            }
        }
    }

    private void a(View view, boolean z) {
        if (view != null) {
            view.setEnabled(z);
            view.setFocusable(z);
            view.setClickable(z);
        }
    }

    private void a(String str, ImageView imageView, Object obj, boolean z) {
        ImageView imageView2 = this.f20541a;
        if (imageView2 != null) {
            Drawable e = e(obj);
            State state = State.NOR;
            if (this.S == e) {
                state = State.UNABLE;
                a((View) imageView2, false);
            } else {
                a((View) imageView2, true);
            }
            if (this.J == e) {
                state = State.NOR;
            }
            if (this.I == e) {
                state = State.SELECTED;
            }
            if (!z) {
                this.T = state;
                imageView.setImageDrawable(e);
            } else if (state != this.T) {
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{e, a()});
                transitionDrawable.setCrossFadeEnabled(true);
                imageView.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(200);
                this.T = state;
            }
        }
    }

    private Drawable e(Object obj) {
        if (!a(k(), (this.y == null || this.y.size() <= 0) ? null : (Operation) this.y.get(0))) {
            return this.S;
        }
        return f(obj);
    }

    private Drawable a() {
        if (this.T == State.NOR) {
            return this.J;
        }
        if (this.T == State.SELECTED) {
            return this.I;
        }
        if (this.T == State.UNABLE) {
            return this.S;
        }
        return this.J;
    }

    private Drawable f(Object obj) {
        List<Object> list = n().j;
        if (obj == null || list == null) {
            LogUtil.b("mijia-card", "getDrawableOnOrOff model:" + k().model + " prop:" + n().f20557a + " switchStatus is not config");
            return this.J;
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
            return this.I;
        }
        return this.J;
    }

    /* access modifiers changed from: private */
    public Object g(Object obj) {
        List<Object> list = n().j;
        if (list == null) {
            LogUtil.b("mijia-card", "getSaveValue model:" + k().model + " prop:" + n().f20557a + " switchStatus is not config");
            return null;
        }
        for (Operation operation : this.y) {
            if (!operation.a(String.valueOf(obj)) && (!list.contains(obj) || !list.contains(operation.b))) {
                return operation.b;
            }
        }
        return null;
    }
}
