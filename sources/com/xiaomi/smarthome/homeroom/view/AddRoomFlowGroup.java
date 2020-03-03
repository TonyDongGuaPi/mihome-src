package com.xiaomi.smarthome.homeroom.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.widget.FlowGroupV2;
import java.util.HashSet;
import java.util.List;

public class AddRoomFlowGroup extends FlowGroupV2 {
    public static final int SELECT_TYPE_ROOM = 1;
    public static final int SELECT_TYPE_TAG = 2;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public TagClickListener f18350a;
    private String b;
    private int c;

    public interface TagClickListener {
        void a();

        void a(Object obj);

        void b();
    }

    public boolean isSelectedStateChanged() {
        return false;
    }

    public AddRoomFlowGroup(Context context) {
        super(context);
    }

    public AddRoomFlowGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AddRoomFlowGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setCurrentSelect(int i, String str) {
        this.c = i;
        this.b = str;
        a();
        if (this.f18350a != null) {
            TagClickListener tagClickListener = this.f18350a;
            Object obj = str;
            if (this.c != 2) {
                obj = HomeManager.a().i(str);
            }
            tagClickListener.a(obj);
        }
    }

    public int getCurrentSelectType() {
        return this.c;
    }

    public String getCurrentSelectValue() {
        return this.b;
    }

    private void a() {
        this.mExtraViewAdded = false;
        this.mChildren.clear();
        removeAllViews();
        c();
        d();
        e();
        b();
        setMaxRows(2);
        this.mShowCount = 0;
        requestLayout();
    }

    public Object getSelected() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.isSelected()) {
                return childAt.getTag();
            }
        }
        return null;
    }

    private void a(View view, int i) {
        this.mChildren.add(i, view);
    }

    private void a(View view) {
        this.mChildren.add(view);
    }

    private void b() {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tag_item, (ViewGroup) null);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!view.isSelected()) {
                    for (int i = 0; i < AddRoomFlowGroup.this.getChildCount(); i++) {
                        AddRoomFlowGroup.this.getChildAt(i).setSelected(false);
                    }
                    view.setSelected(true);
                    if (AddRoomFlowGroup.this.f18350a != null) {
                        AddRoomFlowGroup.this.f18350a.a(view.getTag());
                    }
                    AddRoomFlowGroup.this.a(view.getTag());
                }
            }
        });
        if (this.c == 1) {
            Room i = HomeManager.a().i(this.b);
            if (i == null) {
                new Room().e(getResources().getString(R.string.room_default));
                return;
            } else {
                textView.setText(i.e());
                textView.setTag(i);
            }
        } else {
            textView.setText(this.b);
            textView.setTag(this.b);
        }
        textView.setSelected(true);
        a(textView, 0);
    }

    /* access modifiers changed from: private */
    public void a(Object obj) {
        if (obj instanceof Room) {
            this.c = 1;
            this.b = ((Room) obj).e();
            return;
        }
        this.c = 2;
        this.b = (String) obj;
    }

    private void c() {
        List<Room> e = HomeManager.a().e();
        if (e != null && e.size() > 0) {
            for (int i = 0; i < e.size(); i++) {
                Room room = e.get(i);
                if (room != null && (this.c != 1 || !TextUtils.equals(room.d(), this.b))) {
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tag_item, (ViewGroup) null);
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (!view.isSelected()) {
                                for (int i = 0; i < AddRoomFlowGroup.this.getChildCount(); i++) {
                                    AddRoomFlowGroup.this.getChildAt(i).setSelected(false);
                                }
                                view.setSelected(true);
                                if (AddRoomFlowGroup.this.f18350a != null) {
                                    AddRoomFlowGroup.this.f18350a.a(view.getTag());
                                }
                                AddRoomFlowGroup.this.a(view.getTag());
                            }
                        }
                    });
                    textView.setText(e.get(i).e());
                    textView.setTag(e.get(i));
                    a((View) textView);
                }
            }
        }
    }

    private void d() {
        HashSet hashSet = new HashSet();
        List<Room> e = HomeManager.a().e();
        if (e != null && e.size() > 0) {
            for (int i = 0; i < e.size(); i++) {
                Room room = e.get(i);
                if (room != null) {
                    hashSet.add(room.e());
                }
            }
        }
        List<String> c2 = SmartHomeDeviceHelper.a().b().c();
        for (int i2 = 0; i2 < c2.size(); i2++) {
            String str = c2.get(i2);
            if ((this.c != 2 || !TextUtils.equals(str, this.b)) && !hashSet.contains(str)) {
                TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tag_item, (ViewGroup) null);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!view.isSelected()) {
                            for (int i = 0; i < AddRoomFlowGroup.this.getChildCount(); i++) {
                                AddRoomFlowGroup.this.getChildAt(i).setSelected(false);
                            }
                            view.setSelected(true);
                            if (AddRoomFlowGroup.this.f18350a != null) {
                                AddRoomFlowGroup.this.f18350a.a(view.getTag());
                            }
                            AddRoomFlowGroup.this.a(view.getTag());
                        }
                    }
                });
                textView.setText(str);
                textView.setTag(str);
                a((View) textView);
            }
        }
    }

    private void e() {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tag_item, (ViewGroup) null);
        textView.setText(R.string.add);
        textView.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.camera_icon_add_nor), (Drawable) null, (Drawable) null, (Drawable) null);
        setAddView(textView);
        TextView textView2 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tag_item, (ViewGroup) null);
        textView2.setText(R.string.more_with_dot);
        setMoreView(textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AddRoomFlowGroup.this.f18350a != null) {
                    AddRoomFlowGroup.this.f18350a.a();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AddRoomFlowGroup.this.f18350a != null) {
                    AddRoomFlowGroup.this.f18350a.b();
                }
            }
        });
    }

    public void setOnTagClickListener(TagClickListener tagClickListener) {
        this.f18350a = tagClickListener;
    }
}
