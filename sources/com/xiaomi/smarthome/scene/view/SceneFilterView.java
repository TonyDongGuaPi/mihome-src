package com.xiaomi.smarthome.scene.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.scene.view.SceneFilterManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SceneFilterView extends LinearLayout {
    private static final int m = 500;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f21708a;
    /* access modifiers changed from: private */
    public Context b;
    private TextView c;
    private TextView d;
    private TextView e;
    private ListView f;
    /* access modifiers changed from: private */
    public LayoutInflater g;
    /* access modifiers changed from: private */
    public DeviceTagInterface h;
    private MyAdapter i;
    /* access modifiers changed from: private */
    public Map<String, Set<String>> j;
    /* access modifiers changed from: private */
    public List<SceneFilterManager.FilterInfo> k;
    /* access modifiers changed from: private */
    public PopupWindow l;
    /* access modifiers changed from: private */
    public OnItemSelectListener n;
    /* access modifiers changed from: private */
    public OnWindowDismissListener o;
    /* access modifiers changed from: private */
    public int p;

    public interface OnItemSelectListener {
        void a(Set<String> set, String str);
    }

    public interface OnWindowDismissListener {
        void a();
    }

    public OnItemSelectListener getOnItemSelectListener() {
        return this.n;
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.n = onItemSelectListener;
    }

    public SceneFilterView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SceneFilterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SceneFilterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f21708a = 0;
        this.i = new MyAdapter();
        this.k = new ArrayList();
        this.p = 2;
        this.b = context;
        this.g = LayoutInflater.from(this.b);
    }

    private void a() {
        this.c = (TextView) findViewById(R.id.filter_tv_left);
        this.d = (TextView) findViewById(R.id.filter_tv_mid);
        this.e = (TextView) findViewById(R.id.filter_tv_right);
        this.f = (ListView) findViewById(R.id.scene_filter_lv);
        this.f.setAdapter(this.i);
    }

    public void show(View view, final View view2, int i2, int i3, String str, int i4) {
        if (view != null) {
            this.f21708a = i4;
            this.l = new PopupWindow(this, -1, -2, true);
            if (Build.VERSION.SDK_INT >= 23) {
                this.l.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent, (Resources.Theme) null)));
            } else {
                this.l.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
            }
            a(i2, i3, str);
            this.l.getContentView().startAnimation(createInAnimation(this.b, -1));
            if (this.l != null && !this.l.isShowing()) {
                this.l.showAsDropDown(view);
            }
            if (view2 != null) {
                view2.setVisibility(0);
            }
            this.l.setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    SceneFilterView.this.l.getContentView().startAnimation(SceneFilterView.createOutAnimation(SceneFilterView.this.b));
                    view2.setVisibility(8);
                    if (SceneFilterView.this.o != null) {
                        SceneFilterView.this.o.a();
                    }
                }
            });
        }
    }

    public void dismiss() {
        this.l.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        a();
        b();
    }

    private void b() {
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SceneFilterView.this.a(2, 0, SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all));
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SceneFilterView.this.a(4, 0, SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all));
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SceneFilterView.this.a(0, 0, SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all));
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3, String str) {
        if (i2 == 0) {
            this.c.setSelected(false);
            this.d.setSelected(false);
            this.e.setSelected(true);
        } else if (i2 == 2) {
            this.c.setSelected(true);
            this.d.setSelected(false);
            this.e.setSelected(false);
        } else if (i2 == 4) {
            this.c.setSelected(false);
            this.d.setSelected(true);
            this.e.setSelected(false);
        }
        b(i2, i3, str);
        this.i.notifyDataSetChanged();
    }

    private void b(int i2, int i3, String str) {
        this.h = SmartHomeDeviceHelper.a().b();
        this.j = this.h.a(i2);
        this.k = SceneFilterManager.c().a(this.j, this.f21708a);
        this.i.a(i3);
        this.i.a(str);
        this.p = i2;
    }

    private class MyAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public int b;
        private String c;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        private MyAdapter() {
            this.b = 0;
            this.c = SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all);
        }

        public String a() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public void a(int i) {
            this.b = i;
        }

        public int getCount() {
            return SceneFilterView.this.k.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            final SceneFilterManager.FilterInfo filterInfo = (SceneFilterManager.FilterInfo) SceneFilterView.this.k.get(i);
            if (view == null) {
                view = SceneFilterView.this.g.inflate(R.layout.scene_fliter_item_view, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (SceneFilterView.this.p == 2) {
                TextView textView = viewHolder.f21715a;
                textView.setText(SceneFilterView.this.h.k(filterInfo.f21705a) + " (" + filterInfo.b + Operators.BRACKET_END_STR);
            } else {
                TextView textView2 = viewHolder.f21715a;
                textView2.setText(filterInfo.f21705a + " (" + filterInfo.b + Operators.BRACKET_END_STR);
            }
            if (this.c.equalsIgnoreCase(filterInfo.f21705a)) {
                viewHolder.f21715a.setSelected(true);
            } else {
                viewHolder.f21715a.setSelected(false);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String str;
                    int unused = MyAdapter.this.b = i;
                    if (SceneFilterView.this.p != 2 || i == 0) {
                        str = filterInfo.f21705a + " (" + filterInfo.b + Operators.BRACKET_END_STR;
                    } else {
                        str = SceneFilterView.this.h.k(filterInfo.f21705a) + " (" + filterInfo.b + Operators.BRACKET_END_STR;
                    }
                    if (MyAdapter.this.b == 0) {
                        if (SceneFilterView.this.f21708a == 1 || SceneFilterView.this.f21708a == 2) {
                            SceneFilterManager.c().a(SceneFilterView.this.p, MyAdapter.this.b, filterInfo.f21705a, filterInfo.b);
                            SceneFilterManager.c().a().clear();
                        } else if (SceneFilterView.this.f21708a == 3) {
                            SceneFilterManager.c().b(SceneFilterView.this.p, MyAdapter.this.b, filterInfo.f21705a, filterInfo.b);
                            SceneFilterManager.c().b().clear();
                        }
                    } else if (SceneFilterView.this.f21708a == 1 || SceneFilterView.this.f21708a == 2) {
                        SceneFilterManager.c().a(SceneFilterView.this.p, MyAdapter.this.b, filterInfo.f21705a, filterInfo.b);
                        SceneFilterManager.c().a((Set<String>) (Set) SceneFilterView.this.j.get(filterInfo.f21705a));
                    } else if (SceneFilterView.this.f21708a == 3) {
                        SceneFilterManager.c().b(SceneFilterView.this.p, MyAdapter.this.b, filterInfo.f21705a, filterInfo.b);
                        SceneFilterManager.c().b((Set<String>) (Set) SceneFilterView.this.j.get(filterInfo.f21705a));
                    }
                    if (SceneFilterView.this.n != null) {
                        if (MyAdapter.this.b == 0) {
                            SceneFilterView.this.n.a((Set<String>) null, str);
                        } else {
                            SceneFilterView.this.n.a((Set) SceneFilterView.this.j.get(filterInfo.f21705a), str);
                        }
                    }
                    MyAdapter.this.notifyDataSetChanged();
                }
            });
            return view;
        }
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f21715a;

        public ViewHolder(View view) {
            this.f21715a = (TextView) view.findViewById(R.id.scene_item_tv);
        }
    }

    public OnWindowDismissListener getmDismissListener() {
        return this.o;
    }

    public void setmDismissListener(OnWindowDismissListener onWindowDismissListener) {
        this.o = onWindowDismissListener;
    }

    public static Animation createInAnimation(Context context, int i2) {
        AnimationSet animationSet = new AnimationSet(context, (AttributeSet) null);
        animationSet.setFillAfter(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (-DisplayUtils.a(320.0f)), 0.0f);
        translateAnimation.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static Animation createInAnimation2(Context context) {
        AnimationSet animationSet = new AnimationSet(context, (AttributeSet) null);
        animationSet.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f, 1, 1.0f, 1, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(500);
        return animationSet;
    }

    public static Animation createOutAnimation(Context context) {
        AnimationSet animationSet = new AnimationSet(context, (AttributeSet) null);
        animationSet.setFillAfter(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-DisplayUtils.a(320.0f)));
        translateAnimation.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }
}
