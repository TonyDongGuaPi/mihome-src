package com.mics.core.ui.kit;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.core.fsm.State;
import com.mics.core.ui.data.RobotAnswerData;
import com.mics.core.ui.page.ChatAdapter;
import com.mics.core.ui.page.ChatDelegate;
import com.mics.core.ui.widget.LongPressMenu;
import com.mics.core.ui.widget.RobotBottom;
import com.mics.network.NetworkManager;
import com.mics.util.FrescoImageLoader;
import com.mics.util.HtmlText;

public class TextLeft extends AbsKit implements RobotBottom.OnClickFeedback, HtmlText.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f7697a;
    private RobotBottom b;
    private FrescoImageLoader.Builder c;
    private Data d;
    private ChatDelegate e;

    public void a(KitBaseAdapter kitBaseAdapter) {
        super.a(kitBaseAdapter);
        if (kitBaseAdapter instanceof ChatAdapter) {
            this.e = ((ChatAdapter) kitBaseAdapter).c();
        }
    }

    public View a(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        View a2 = a(LayoutInflater.from(context), viewGroup, R.layout.mics_kit_left_text);
        a2.setLayoutParams(layoutParams);
        this.b = new RobotBottom(a2);
        this.b.a(this);
        this.f7697a = (TextView) a2.findViewById(R.id.tv_content);
        this.f7697a.setMovementMethod(new HtmlText.MovementCheck());
        this.f7697a.setAutoLinkMask(0);
        this.f7697a.setLinksClickable(true);
        LongPressMenu.a(this.f7697a, R.drawable.mics_kit_left_bg, R.drawable.mics_kit_left_bg_selected);
        this.c = new FrescoImageLoader.Builder();
        this.c.a(true);
        this.c.b(R.drawable.mics_icon_cs_robot);
        this.c.b(ScalingUtils.ScaleType.CENTER_CROP);
        this.c.a(ScalingUtils.ScaleType.CENTER_CROP);
        this.c.a((DraweeView<GenericDraweeHierarchy>) (SimpleDraweeView) a2.findViewById(R.id.iv_avatar));
        return a2;
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            Data data = (Data) obj;
            this.d = data;
            String c2 = data.c();
            if (TextUtils.isEmpty(c2)) {
                this.c.a((String) null);
                this.c.a(R.drawable.mics_icon_cs_robot);
            } else {
                this.c.a(c2);
            }
            this.c.a().a();
            this.b.a(data.getQuestionId(), data.isHasChoose());
            CharSequence b2 = data.b();
            if (b2 != null) {
                this.f7697a.setText(HtmlText.a(this.f7697a.getContext(), b2.toString(), this));
                return;
            }
            this.f7697a.setText("");
        }
    }

    public void a(String str, int i) {
        if (this.e.u() == State.ROBOT && NetworkManager.b()) {
            boolean z = true;
            this.d.setHasChoose(true);
            ChatDelegate chatDelegate = this.e;
            String id = this.d.getId();
            if (i != 1) {
                z = false;
            }
            chatDelegate.a(id, str, z);
        }
    }

    public void onClick(String str) {
        if ((this.e.u() == State.ROBOT || this.e.u() == State.HUMAN || this.e.u() == State.IN_QUEUE) && NetworkManager.b()) {
            this.e.a((CharSequence) str);
        }
    }

    public void toLeaveMessage(String str) {
        if ((this.e.u() == State.ROBOT || this.e.u() == State.HUMAN || this.e.u() == State.IN_QUEUE) && NetworkManager.b() && !this.d.d()) {
            this.e.a(this.d.getId(), this.d.e());
            this.d.b(true);
        }
    }

    public void showHumanService(String str) {
        if (this.e.u() == State.ROBOT || NetworkManager.b()) {
            this.e.s();
        }
    }

    public static class Data extends RobotAnswerData {

        /* renamed from: a  reason: collision with root package name */
        private boolean f7698a = false;
        private CharSequence b;
        private String c;
        private boolean d = false;
        private String e;

        public void a(@DrawableRes int i) {
        }

        public boolean a() {
            return this.f7698a;
        }

        public void a(boolean z) {
            this.f7698a = z;
        }

        public Data() {
            a(R.drawable.mics_icon_cs_human);
        }

        public CharSequence b() {
            return this.b;
        }

        public void a(CharSequence charSequence) {
            this.b = charSequence;
            setContent(charSequence);
        }

        public void a(String str) {
            this.c = str;
        }

        /* access modifiers changed from: package-private */
        public String c() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public boolean d() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public void b(boolean z) {
            this.d = z;
        }

        public String e() {
            return this.e;
        }

        public void b(String str) {
            this.e = str;
        }
    }
}
