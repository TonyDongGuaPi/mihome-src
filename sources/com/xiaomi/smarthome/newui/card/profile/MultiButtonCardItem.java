package com.xiaomi.smarthome.newui.card.profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.Operation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MultiButtonCardItem extends ProfileCardItem {
    private int I = -1;
    private Map<View, CardItem.State> J = new HashMap();
    private List<Integer> S = new ArrayList();
    private int T;
    private Object U = new Object();

    /* renamed from: a  reason: collision with root package name */
    private List<View> f20548a = new ArrayList();

    public MultiButtonCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        Context context = viewGroup.getContext();
        int size = this.y.size();
        View a2 = a(viewGroup, R.layout.card_item_multibutton);
        LinearLayout linearLayout = (LinearLayout) a2.findViewById(R.id.button_scroll_parent);
        LinearLayout linearLayout2 = (LinearLayout) a2.findViewById(R.id.button_parent);
        this.f20548a.clear();
        this.S.clear();
        this.T = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        c(context);
        b(context);
        a(context);
        if (size > 4) {
            linearLayout2.setVisibility(8);
            int i3 = 0;
            for (Operation operation : this.y) {
                View inflate = LayoutInflater.from(CommonApplication.getAppContext()).inflate(R.layout.miui10_card_item_button_item, (ViewGroup) null);
                linearLayout.addView(inflate);
                this.f20548a.add(inflate);
                TextView textView = (TextView) inflate.findViewById(R.id.button_name);
                this.S.add(Integer.valueOf(textView.getCurrentTextColor()));
                if (operation.f20520a != null) {
                    textView.setText(a(operation.f20520a));
                }
                operation.k = i3;
                i3++;
            }
        } else {
            linearLayout.setVisibility(8);
            if (this.y.size() == 2) {
                int i4 = linearLayout2.getResources().getDisplayMetrics().widthPixels / 8;
                linearLayout2.setPadding(i4, 0, i4, 0);
            }
            int i5 = 0;
            for (Operation operation2 : this.y) {
                View inflate2 = LayoutInflater.from(CommonApplication.getAppContext()).inflate(R.layout.miui10_card_item_button_item, (ViewGroup) null);
                ((ImageView) inflate2.findViewById(R.id.image)).setImageDrawable(((ProfileCardType) this.G).a(k(), this.w, (Object) operation2.b, CardItem.State.NOR));
                linearLayout2.addView(inflate2, new LinearLayout.LayoutParams(0, -1, 1.0f));
                this.f20548a.add(inflate2);
                TextView textView2 = (TextView) inflate2.findViewById(R.id.button_name);
                this.S.add(Integer.valueOf(textView2.getCurrentTextColor()));
                if (operation2.f20520a != null) {
                    textView2.setText(a(operation2.f20520a));
                }
                operation2.k = i5;
                i5++;
            }
        }
        Object d = ((ProfileCardType) this.G).b(k(), this.w);
        this.I = -1;
        int i6 = 0;
        while (true) {
            if (i6 >= this.y.size()) {
                break;
            } else if (((Operation) this.y.get(i6)).a(String.valueOf(d))) {
                this.I = i6;
                break;
            } else {
                i6++;
            }
        }
        synchronized (this.U) {
            for (int i7 = 0; i7 < this.f20548a.size(); i7++) {
                View view = this.f20548a.get(i7);
                if (a(k(), (Operation) this.y.get(i7))) {
                    this.J.put(view, CardItem.State.UNABLE);
                } else if (this.I == i7) {
                    this.J.put(view, CardItem.State.SELECTED);
                } else {
                    this.J.put(view, CardItem.State.NOR);
                }
            }
        }
        b(false);
        for (final Operation operation3 : this.y) {
            synchronized (this.U) {
                this.f20548a.get(operation3.k).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!MultiButtonCardItem.this.j()) {
                            MultiButtonCardItem.this.a(view);
                            MultiButtonCardItem.this.a(operation3.k, true);
                            if (k instanceof MiioDeviceV2) {
                                operation3.a((ProfileCardType) MultiButtonCardItem.this.G, operation3.b, k, (AsyncCallback<JSONObject, Error>) null);
                            }
                        }
                    }
                });
            }
        }
    }

    public void i() {
        synchronized (this.U) {
            super.i();
            a(true);
            this.E = null;
            this.f20548a.clear();
            this.J.clear();
        }
    }

    private float b(Context context) {
        return context.getResources().getDimension(R.dimen.multi_button_size);
    }

    private float c(Context context) {
        if (this.y.size() == 2) {
            return context.getResources().getDimension(R.dimen.multi_button_2image_margin);
        }
        if (this.y.size() > 2) {
            return context.getResources().getDimension(R.dimen.multi_button_3image_margin);
        }
        return 0.0f;
    }

    /* access modifiers changed from: private */
    public void a(int i, boolean z) {
        CardItem.State state;
        for (int i2 = 0; i2 < this.f20548a.size(); i2++) {
            View view = this.f20548a.get(i2);
            CardItem.State state2 = this.J.get(view);
            Operation operation = (Operation) this.y.get(i2);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            Animation animation = imageView.getAnimation();
            if (animation != null) {
                animation.cancel();
            }
            if (i2 == i) {
                state = CardItem.State.SELECTED;
            } else if (i2 == this.I) {
                state = CardItem.State.NOR;
            } else {
                state = a(k(), operation) ? CardItem.State.NOR : CardItem.State.UNABLE;
            }
            Drawable a2 = a(operation, state);
            if (!z || state2 == state) {
                imageView.setImageDrawable(a2);
            } else {
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{a(operation, state2), a2});
                transitionDrawable.setCrossFadeEnabled(true);
                imageView.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(200);
            }
        }
    }

    private Drawable a(Operation operation, CardItem.State state) {
        return ((ProfileCardType) this.G).a(k(), this.w, (Object) operation.b, state);
    }

    private void a(boolean z, List<View> list) {
        for (View next : list) {
            next.setEnabled(z);
            next.setFocusable(z);
            next.setClickable(z);
        }
    }

    public void a(String str, Object obj) {
        if (!j()) {
            if (this.I < 0 || this.I >= this.y.size()) {
                a(true, this.f20548a);
            } else {
                a(a(k(), (Operation) this.y.get(this.I)), this.f20548a);
            }
            b(false);
        }
    }

    private void b(boolean z) {
        CardItem.State state;
        if (!j() && this.E != null && !this.E.isFinishing()) {
            Object d = ((ProfileCardType) this.G).b(k(), this.w);
            if (d != null) {
                this.I = -1;
                int i = 0;
                while (true) {
                    if (i >= this.y.size()) {
                        break;
                    } else if (((Operation) this.y.get(i)).a(String.valueOf(d))) {
                        this.I = i;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            synchronized (this.U) {
                for (int i2 = 0; i2 < this.f20548a.size(); i2++) {
                    View view = this.f20548a.get(i2);
                    Operation operation = (Operation) this.y.get(i2);
                    CardItem.State state2 = this.J.get(view);
                    if (!a(k(), operation)) {
                        state = CardItem.State.UNABLE;
                    } else if (i2 == this.I) {
                        state = CardItem.State.SELECTED;
                    } else {
                        state = CardItem.State.NOR;
                    }
                    ImageView imageView = (ImageView) view.findViewById(R.id.image);
                    Animation animation = imageView.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                    }
                    if (!z || state == state2) {
                        imageView.setImageDrawable(a(operation, state));
                    } else {
                        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{a(operation, state2), a(operation, state)});
                        transitionDrawable.setCrossFadeEnabled(true);
                        imageView.setImageDrawable(transitionDrawable);
                        transitionDrawable.startTransition(200);
                    }
                    TextView textView = (TextView) view.findViewById(R.id.button_name);
                    if (state == CardItem.State.UNABLE) {
                        view.setClickable(false);
                        view.setFocusable(false);
                        view.setEnabled(false);
                        textView.setTextColor(this.T);
                    }
                    if (state == CardItem.State.SELECTED) {
                        view.setClickable(false);
                        view.setFocusable(true);
                        view.setEnabled(false);
                        textView.setTextColor(this.S.get(i2).intValue());
                    }
                    if (state == CardItem.State.NOR) {
                        view.setClickable(true);
                        view.setFocusable(true);
                        view.setEnabled(true);
                        textView.setTextColor(this.S.get(i2).intValue());
                    }
                    this.J.put(view, state);
                }
            }
        }
    }
}
