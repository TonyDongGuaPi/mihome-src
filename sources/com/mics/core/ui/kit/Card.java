package com.mics.core.ui.kit;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.core.fsm.State;
import com.mics.core.ui.page.ChatAdapter;
import com.mics.core.ui.page.ChatDelegate;
import com.mics.network.NetworkManager;

public class Card extends AbsKit implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f7683a;
    private TextView b;
    private TextView c;
    private TextView d;
    private Data e;
    private ChatDelegate f;

    public void a(KitBaseAdapter kitBaseAdapter) {
        super.a(kitBaseAdapter);
        if (kitBaseAdapter instanceof ChatAdapter) {
            this.f = ((ChatAdapter) kitBaseAdapter).c();
        }
    }

    public View a(ViewGroup viewGroup) {
        View a2 = a(LayoutInflater.from(viewGroup.getContext()), viewGroup, R.layout.mics_kit_card);
        this.f7683a = (SimpleDraweeView) a2.findViewById(R.id.iv_cover);
        this.b = (TextView) a2.findViewById(R.id.tv_card);
        this.c = (TextView) a2.findViewById(R.id.tv_card_price);
        this.d = (TextView) a2.findViewById(R.id.tv_send_good);
        return a2;
    }

    public void onClick(View view) {
        StringBuilder sb;
        String d2;
        if (this.e != null && this.e.e() && this.f.u() == State.HUMAN && NetworkManager.b()) {
            this.e.a(false);
            this.d.setText("已发送");
            this.d.setOnClickListener((View.OnClickListener) null);
            if (this.e.g()) {
                sb = new StringBuilder();
                sb.append("我要咨询的订单号：");
                d2 = this.e.f();
            } else {
                sb = new StringBuilder();
                sb.append(this.e.c());
                sb.append("\n");
                d2 = this.e.d();
            }
            sb.append(d2);
            this.f.a((CharSequence) sb.toString());
        }
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            Data data = (Data) obj;
            this.e = data;
            AbstractDraweeController build = Fresco.newDraweeControllerBuilder().setUri(data.a()).build();
            GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(this.f7683a.getResources());
            RoundingParams fromCornersRadius = RoundingParams.fromCornersRadius(TypedValue.applyDimension(1, 2.0f, this.f7683a.getResources().getDisplayMetrics()));
            fromCornersRadius.setBorder(Color.parseColor("#a0a0a0"), 1.0f / this.f7683a.getResources().getDisplayMetrics().density);
            genericDraweeHierarchyBuilder.setRoundingParams(fromCornersRadius);
            this.f7683a.setHierarchy(genericDraweeHierarchyBuilder.setFadeDuration(300).setPlaceholderImage(R.drawable.mics_placeholder_image).build());
            this.f7683a.setController(build);
            this.b.setText(data.c());
            this.c.setText(data.b() + "元");
            if (this.e.e()) {
                this.d.setOnClickListener(this);
                this.d.setText(data.g() ? "发送订单 >>" : "发送链接 >>");
                return;
            }
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d.setText("已发送");
        }
    }

    public static class Data {

        /* renamed from: a  reason: collision with root package name */
        private String f7684a;
        private String b;
        private String c;
        private String d;
        private float e;
        private boolean f = true;

        public String a() {
            return this.f7684a;
        }

        public void a(String str) {
            this.f7684a = str;
        }

        public float b() {
            return this.e;
        }

        public void a(float f2) {
            this.e = f2;
        }

        public String c() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String d() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        /* access modifiers changed from: package-private */
        public boolean e() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            this.f = z;
        }

        public String f() {
            return this.d;
        }

        public void d(String str) {
            this.d = str;
        }

        /* access modifiers changed from: private */
        public boolean g() {
            return this.d != null;
        }
    }
}
