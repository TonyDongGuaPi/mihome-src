package com.mics.core.ui.kit;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.core.MiCS;
import com.mics.core.ui.data.BaseData;
import com.mics.core.ui.widget.LongPressMenu;
import com.mics.util.FrescoImageLoader;
import com.mics.util.HtmlText;

public class TextRight extends AbsKit {

    /* renamed from: a  reason: collision with root package name */
    private TextView f7699a;
    private TextView b;
    private FrescoImageLoader.Builder c;

    public View a(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        View a2 = a(LayoutInflater.from(context), viewGroup, R.layout.mics_kit_right_text);
        a2.setLayoutParams(layoutParams);
        this.f7699a = (TextView) a2.findViewById(R.id.tv_content);
        this.f7699a.setMovementMethod(new HtmlText.MovementCheck());
        this.f7699a.setAutoLinkMask(0);
        this.f7699a.setLinksClickable(true);
        LongPressMenu.a(this.f7699a, R.drawable.mics_kit_right_bg, R.drawable.mics_kit_right_bg_selected);
        this.b = (TextView) a2.findViewById(R.id.tv_has_read);
        this.c = new FrescoImageLoader.Builder();
        this.c.a(true);
        this.c.a(R.drawable.mics_icon_default_avatar);
        this.c.b(R.drawable.mics_icon_default_avatar);
        this.c.b(ScalingUtils.ScaleType.FIT_CENTER);
        this.c.a(ScalingUtils.ScaleType.CENTER_CROP);
        this.c.a((DraweeView<GenericDraweeHierarchy>) (SimpleDraweeView) a2.findViewById(R.id.iv_avatar));
        return a2;
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            Data data = (Data) obj;
            CharSequence d = data.d();
            if (d != null) {
                this.f7699a.setText(HtmlText.a(this.f7699a.getContext(), d.toString()));
            } else {
                this.f7699a.setText("");
            }
            this.b.setText(data.a());
            this.b.setTextColor(data.b());
            this.c.a(data.e());
            this.c.a().a();
        }
    }

    public static class Data extends BaseData {

        /* renamed from: a  reason: collision with root package name */
        private CharSequence f7700a;
        private int b;

        /* access modifiers changed from: package-private */
        public String a() {
            if (this.b == 0) {
                return "发送中";
            }
            if (this.b == 1) {
                return "发送成功";
            }
            return this.b == -1 ? "发送失败" : "";
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return Color.parseColor(this.b == 1 ? "#cccccc" : this.b == 0 ? "#dfaf7e" : "#b60909");
        }

        public boolean c() {
            return this.b == 1;
        }

        public void a(int i) {
            this.b = i;
        }

        public CharSequence d() {
            return this.f7700a;
        }

        public void a(CharSequence charSequence) {
            this.f7700a = charSequence;
            setContent(charSequence);
        }

        /* access modifiers changed from: package-private */
        public String e() {
            return MiCS.a().p();
        }
    }
}
