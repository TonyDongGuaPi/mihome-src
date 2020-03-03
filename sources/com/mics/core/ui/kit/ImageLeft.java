package com.mics.core.ui.kit;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.core.ui.data.BaseData;
import com.mics.core.ui.page.ChatAdapter;
import com.mics.core.ui.page.ChatDelegate;
import com.mics.util.DraweeViewUtil;
import com.mics.util.FrescoImageLoader;

public class ImageLeft extends AbsKit implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f7693a;
    private FrescoImageLoader.Builder b;
    private Data c;
    private ChatDelegate d;

    public void a(KitBaseAdapter kitBaseAdapter) {
        super.a(kitBaseAdapter);
        if (kitBaseAdapter instanceof ChatAdapter) {
            this.d = ((ChatAdapter) kitBaseAdapter).c();
        }
    }

    public View a(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        View a2 = a(LayoutInflater.from(context), viewGroup, R.layout.mics_kit_left_image);
        a2.setLayoutParams(layoutParams);
        this.f7693a = (SimpleDraweeView) a2.findViewById(R.id.iv_img);
        this.f7693a.setOnClickListener(this);
        this.b = new FrescoImageLoader.Builder();
        this.b.a(true);
        this.b.b(R.drawable.mics_icon_cs_robot);
        this.b.b(ScalingUtils.ScaleType.CENTER_CROP);
        this.b.a(ScalingUtils.ScaleType.CENTER_CROP);
        this.b.a((DraweeView<GenericDraweeHierarchy>) (SimpleDraweeView) a2.findViewById(R.id.iv_avatar));
        return a2;
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            Data data = (Data) obj;
            this.c = data;
            DraweeViewUtil.a().a(this.f7693a, data.a());
            String b2 = data.b();
            if (TextUtils.isEmpty(b2)) {
                this.b.a(R.drawable.mics_icon_cs_robot);
            } else {
                this.b.a(b2);
            }
            this.b.a().a();
        }
    }

    public void onClick(View view) {
        if ((view instanceof SimpleDraweeView) && !TextUtils.isEmpty(this.c.a())) {
            float applyDimension = TypedValue.applyDimension(1, 4.0f, view.getResources().getDisplayMetrics());
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            float f = applyDimension * 2.0f;
            int height = (int) (((float) view.getHeight()) - f);
            this.d.a(this.c.getId(), (int) (((float) iArr[0]) + applyDimension), (int) (((float) iArr[1]) + applyDimension), (int) (((float) view.getWidth()) - f), height);
        }
    }

    public static class Data extends BaseData {

        /* renamed from: a  reason: collision with root package name */
        private String f7694a;
        private String b;

        public String a() {
            return this.f7694a;
        }

        public void a(String str) {
            this.f7694a = str;
            setContent("[图片]");
        }

        public void b(String str) {
            this.b = str;
        }

        /* access modifiers changed from: package-private */
        public String b() {
            return this.b;
        }
    }
}
