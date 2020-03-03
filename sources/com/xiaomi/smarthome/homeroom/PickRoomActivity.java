package com.xiaomi.smarthome.homeroom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.newui.MoveRoomDialogHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PickRoomActivity extends BaseActivity {
    public static final String INTENT_KEY_HOME_ID = "home_id";
    public static final String RESULT_KEY = "result";

    /* renamed from: a  reason: collision with root package name */
    private RecyclerView f18184a;
    private Home b;
    private List<Room> c;
    protected SectionedRecyclerViewAdapter mAdapter;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pick_room_layout);
        a();
        if (this.b == null) {
            finish();
            return;
        }
        initViews();
        List<Room> d = this.b.d();
        if (d == null) {
            this.c = new ArrayList();
        } else {
            this.c = new ArrayList(d);
        }
        if (this.mAdapter.getItemCount() <= 0) {
            c();
        } else {
            d();
        }
    }

    private void a() {
        Intent intent = getIntent();
        String stringExtra = intent != null ? intent.getStringExtra("home_id") : null;
        if (TextUtils.isEmpty(stringExtra)) {
            this.b = HomeManager.a().m();
        } else {
            this.b = HomeManager.a().j(stringExtra);
        }
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        View findViewById = findViewById(R.id.title_bar);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.tag_custom_empty);
        ((TextView) findViewById.findViewById(R.id.module_a_3_return_title)).setText(R.string.more_room);
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
        imageView.setImageResource(R.drawable.home_icon_add_black);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoveRoomDialogHelper.a((Context) PickRoomActivity.this, (List<String>) null, (MoveRoomDialogHelper.addRoomListener) new MoveRoomDialogHelper.addRoomListener() {
                    public void onSuccess(String str) {
                        PickRoomActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                PickRoomActivity.this.b();
                            }
                        });
                    }
                });
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PickRoomActivity.this.finish();
            }
        });
        this.f18184a = (RecyclerView) findViewById(R.id.recyclerview);
        this.mAdapter = new SectionedRecyclerViewAdapter();
        this.f18184a.setLayoutManager(new LinearLayoutManager(this));
        this.f18184a.setAdapter(this.mAdapter);
        this.f18184a.setHasFixedSize(false);
        b();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.mAdapter.a();
        this.mAdapter.a((Section) new RoomSection(this.b.d()));
        HashSet hashSet = new HashSet();
        List<Room> d = this.b.d();
        if (d != null && d.size() > 0) {
            for (int i = 0; i < d.size(); i++) {
                Room room = d.get(i);
                if (room != null) {
                    hashSet.add(room.e());
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        List<String> c2 = SmartHomeDeviceHelper.a().b().c();
        if (c2 != null && c2.size() > 0) {
            for (int i2 = 0; i2 < c2.size(); i2++) {
                String str = c2.get(i2);
                if (!hashSet.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        this.mAdapter.a((Section) new TagSection(arrayList));
        this.mAdapter.notifyDataSetChanged();
    }

    private void c() {
        this.f18184a.setVisibility(8);
    }

    private void d() {
        this.f18184a.setVisibility(0);
    }

    private class RoomSection extends BaseSection {
        /* access modifiers changed from: private */
        public List<Room> i;

        public RoomSection(List<Room> list) {
            super();
            this.i = list == null ? new ArrayList<>() : list;
        }

        /* access modifiers changed from: protected */
        public void a(SimpleDraweeView simpleDraweeView, int i2) {
            if (simpleDraweeView != null) {
                simpleDraweeView.setImageURI(Uri.parse("file://" + HomeManager.a().f(this.i.get(i2).a())));
            }
        }

        /* access modifiers changed from: protected */
        public void a(TextView textView, int i2) {
            if (i2 >= 0 && i2 < this.i.size()) {
                textView.setText(this.i.get(i2).e());
            }
        }

        /* access modifiers changed from: protected */
        public void a(View view, final int i2) {
            super.a(view, i2);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Room room = (Room) RoomSection.this.i.get(i2);
                    if (room == null) {
                        Log.e("PickRoomActivity", "error occured when get room from adapter");
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("result", room);
                    PickRoomActivity.this.setResult(-1, intent);
                    PickRoomActivity.this.finish();
                }
            });
        }

        public int a() {
            return this.i.size();
        }
    }

    private class TagSection extends BaseSection {
        /* access modifiers changed from: private */
        public List<String> i;

        public TagSection(List<String> list) {
            super();
            this.i = list == null ? new ArrayList<>() : list;
        }

        /* access modifiers changed from: protected */
        public void a(SimpleDraweeView simpleDraweeView, int i2) {
            if (simpleDraweeView != null) {
                simpleDraweeView.setImageURI(Uri.parse("file://" + HomeManager.a().a(this.i.get(i2), true)));
            }
        }

        /* access modifiers changed from: protected */
        public void a(TextView textView, int i2) {
            if (i2 >= 0 && i2 < this.i.size()) {
                textView.setText(this.i.get(i2));
            }
        }

        /* access modifiers changed from: protected */
        public void a(View view, final int i2) {
            super.a(view, i2);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("result", (String) TagSection.this.i.get(i2));
                    PickRoomActivity.this.setResult(-1, intent);
                    PickRoomActivity.this.finish();
                }
            });
        }

        public int a() {
            return this.i.size();
        }
    }

    private abstract class BaseSection extends Section {
        /* access modifiers changed from: protected */
        public void a(View view, int i) {
        }

        /* access modifiers changed from: protected */
        public abstract void a(TextView textView, int i);

        /* access modifiers changed from: protected */
        public abstract void a(SimpleDraweeView simpleDraweeView, int i);

        public BaseSection() {
            super(new SectionParameters.Builder(R.layout.tag_child_item_sort_edit).a((int) R.layout.item_none).a());
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i) {
            ((ItemViewHolder) viewHolder).a(i);
            viewHolder.itemView.findViewById(R.id.desc).setVisibility(8);
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ItemViewHolder(view);
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            private SimpleDraweeView b;
            private TextView c;

            public ItemViewHolder(View view) {
                super(view);
                this.b = (SimpleDraweeView) view.findViewById(R.id.room_icon);
                this.c = (TextView) view.findViewById(R.id.title);
            }

            public void a(int i) {
                BaseSection.this.a(this.b, i);
                BaseSection.this.a(this.c, i);
                BaseSection.this.a(this.itemView, i);
            }
        }
    }
}
