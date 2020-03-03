package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.taobao.weex.ui.component.AbstractEditComponent;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.TextViewUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.spec.SpecCard;
import com.xiaomi.smarthome.newui.widget.DeviceDownloadItemViewWrapper;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseCardRender<C extends Card<E>, E extends Card.CardType<T>, T> implements ICardPropChangeCtl, ICardRender {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<? extends CardItem<C, E, T>> f20457a;
    protected final C b;
    protected final ViewGroup c;
    protected final Context d;
    protected final Device e;
    private DataInitState f = DataInitState.NOT;
    private List<ICardPropObserver> g = Collections.EMPTY_LIST;

    public enum LayoutPosition {
        TITLE,
        CONTENT
    }

    public void a(DeviceDownloadItemViewWrapper deviceDownloadItemViewWrapper) {
    }

    public abstract ProgressItemView c();

    public abstract View d();

    public void h() {
    }

    public enum DataInitState {
        DONE(AbstractEditComponent.ReturnTypes.DONE),
        DOING("doing"),
        NOT("not");
        
        String desc;

        private DataInitState(String str) {
            this.desc = str;
        }

        public String toString() {
            return this.desc;
        }
    }

    public void a(DataInitState dataInitState) {
        this.f = dataInitState;
    }

    public void a() {
        a((String) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup, Device device) {
        TextView textView = (TextView) viewGroup.findViewById(R.id.tv_device_name);
        if (textView != null) {
            CharSequence name = device.getName();
            if (!TextUtils.isEmpty(name)) {
                Resources resources = viewGroup.getResources();
                TextViewUtils.a(textView, name, resources.getDimension(R.dimen.max_card_name_length), resources.getText(R.string.device_card_name_replace));
                TextView textView2 = (TextView) viewGroup.findViewById(R.id.tv_offline);
                if (this.e instanceof BleDevice) {
                    long j = 0;
                    Iterator<? extends CardItem<C, E, T>> it = this.f20457a.iterator();
                    while (it.hasNext()) {
                        CardItem cardItem = (CardItem) it.next();
                        long g2 = cardItem.G.g(device, cardItem.m());
                        if (g2 > j) {
                            j = g2;
                        }
                    }
                    if (CardItemUtils.a(j) && textView2 != null) {
                        textView2.setVisibility(0);
                        textView2.setTextColor(viewGroup.getResources().getColor(R.color.tv_card_title));
                        textView2.setText(viewGroup.getResources().getString(R.string.wait_connect));
                    }
                    textView.setTextColor(viewGroup.getResources().getColor(R.color.tv_card_title));
                } else if (!this.e.isOnline) {
                    if (textView2 != null) {
                        textView2.setVisibility(0);
                        textView2.setText(viewGroup.getResources().getString(R.string.device_offline_title));
                    }
                    textView.setTextColor(viewGroup.getResources().getColor(R.color.tv_card_title_offline));
                } else {
                    if (this.f == DataInitState.NOT) {
                        if (textView2 != null) {
                            textView2.setVisibility(0);
                            textView2.setText(viewGroup.getResources().getString(R.string.device_offline_title));
                        }
                    } else if (textView2 != null) {
                        textView2.setVisibility(4);
                    }
                    textView.setTextColor(viewGroup.getResources().getColor(R.color.tv_card_title));
                }
                if (!GlobalSetting.r) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getText());
                    sb.append(this.b instanceof SpecCard ? "/Spec/" : "/Profile/");
                    sb.append(device.did);
                    textView.setText(sb.toString());
                }
            }
        }
    }

    private boolean a(Device device) {
        Map map;
        if (device == null || (map = ControlCardInfoManager.f().h().get(device.did)) == null) {
            return false;
        }
        long j = 0;
        for (Long longValue : map.values()) {
            long longValue2 = longValue.longValue();
            if (longValue2 > j) {
                j = longValue2;
            }
        }
        if (j == 0) {
            return false;
        }
        if (String.valueOf(j).length() > 10) {
            j /= 1000;
        }
        if (j + 43200 < System.currentTimeMillis() / 1000) {
            return true;
        }
        return false;
    }

    private void j() {
        a(this.c, this.e);
    }

    public LayoutPosition a(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return LayoutPosition.CONTENT;
        }
        if (viewGroup.getId() == R.id.ll_title_content) {
            return LayoutPosition.TITLE;
        }
        return LayoutPosition.CONTENT;
    }

    public BaseCardRender(C c2, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        this.b = c2;
        this.f20457a = arrayList;
        this.c = viewGroup;
        this.d = context;
        this.e = device;
    }

    public void b() {
        e();
        f();
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.g = new ArrayList();
        Iterator<? extends CardItem<C, E, T>> it = this.f20457a.iterator();
        while (it.hasNext()) {
            ICardPropObserver iCardPropObserver = (ICardPropObserver) it.next();
            iCardPropObserver.a(this);
            this.g.add(iCardPropObserver);
        }
    }

    /* access modifiers changed from: protected */
    public void f() {
        Iterator<? extends CardItem<C, E, T>> it = this.f20457a.iterator();
        while (it.hasNext()) {
            ((CardItem) it.next()).a(this.f);
        }
    }

    public void a(String str, Object obj) {
        LogUtil.c("mijia-card", "BaseCardRender notifyChange prop:" + str + " value:" + obj + " did:" + this.e.did);
        if (this.c != null) {
            this.c.post(new Runnable() {
                public final void run() {
                    BaseCardRender.this.k();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void k() {
        j();
        Iterator<? extends CardItem<C, E, T>> it = this.f20457a.iterator();
        while (it.hasNext()) {
            CardItem cardItem = (CardItem) it.next();
            cardItem.a(this.f);
            cardItem.a(cardItem.w, cardItem.G.b(this.e, cardItem.m()));
        }
    }

    public View g() {
        return this.c.getChildAt(0);
    }

    public void i() {
        Iterator<? extends CardItem<C, E, T>> it = this.f20457a.iterator();
        while (it.hasNext()) {
            ((CardItem) it.next()).i();
        }
    }
}
