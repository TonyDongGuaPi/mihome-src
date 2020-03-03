package com.mics.core.ui.kit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.mics.R;
import com.mics.core.fsm.State;
import com.mics.core.ui.data.RobotAnswerData;
import com.mics.core.ui.page.ChatAdapter;
import com.mics.core.ui.page.ChatDelegate;
import com.mics.core.ui.widget.CardMultiView;
import com.mics.core.ui.widget.RobotBottom;
import com.mics.network.NetworkManager;
import com.mics.util.HtmlText;
import java.util.List;

public class CardMutli extends AbsKit implements RobotBottom.OnClickFeedback, HtmlText.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private CardMultiView f7685a;
    private RobotBottom b;
    private Data c;
    private ChatDelegate d;

    public void showHumanService(String str) {
    }

    public void toLeaveMessage(String str) {
    }

    public void a(KitBaseAdapter kitBaseAdapter) {
        super.a(kitBaseAdapter);
        if (kitBaseAdapter instanceof ChatAdapter) {
            this.d = ((ChatAdapter) kitBaseAdapter).c();
        }
    }

    public View a(ViewGroup viewGroup) {
        View a2 = a(LayoutInflater.from(viewGroup.getContext()), viewGroup, R.layout.mics_kit_card_multi);
        this.f7685a = (CardMultiView) a2.findViewById(R.id.card_robot);
        this.b = new RobotBottom(a2);
        this.b.a(this);
        return a2;
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            Data data = (Data) obj;
            this.c = data;
            this.f7685a.setData(data.a(), this);
            this.b.a(data.getQuestionId(), data.isHasChoose());
        }
    }

    public void onClick(String str) {
        if (this.d.u() == State.ROBOT && NetworkManager.b()) {
            this.c.setHasChoose(true);
            this.d.a((CharSequence) str);
        }
    }

    public void a(String str, int i) {
        ChatDelegate chatDelegate = this.d;
        String id = this.c.getId();
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        chatDelegate.a(id, str, z);
    }

    public static class Data extends RobotAnswerData {

        /* renamed from: a  reason: collision with root package name */
        private List<CardMultiView.Article> f7686a;

        public List<CardMultiView.Article> a() {
            return this.f7686a;
        }

        public void a(List<CardMultiView.Article> list) {
            this.f7686a = list;
            if (list != null && list.size() > 0) {
                setContent(list.get(0).a());
            }
        }
    }
}
