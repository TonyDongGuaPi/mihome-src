package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

public class RoomListActivity extends BaseActivity {
    public static final String HOME_ID = "home_id";
    public static final String IS_CATEGORY = "is_category";
    public static final String ROOM_ID = "room_id";
    public static final String ROOM_NAME = "room_name";
    private static final String e = "RoomListActivity";

    /* renamed from: a  reason: collision with root package name */
    private String f18193a;
    private String b;
    private String c;
    private boolean d;

    public static void showRoomDeviceList(Context context, @Nullable String str, String str2, String str3) {
        Intent intent = new Intent(context, RoomListActivity.class);
        intent.putExtra("home_id", str);
        intent.putExtra("room_id", str2);
        intent.putExtra("room_name", str3);
        if (context instanceof Application) {
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_room_list);
        this.f18193a = getIntent().getStringExtra("home_id");
        if (TextUtils.isEmpty(this.f18193a)) {
            this.f18193a = HomeManager.a().l();
        }
        this.b = getIntent().getStringExtra("room_id");
        this.c = getIntent().getStringExtra("room_name");
        this.d = HomeManager.a().i(this.b) == null && SmartHomeDeviceHelper.a().b().d(this.c) != null;
        a();
    }

    private void a() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RoomListActivity.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (!TextUtils.isEmpty(this.c)) {
            textView.setText(this.c);
        } else {
            Room i = HomeManager.a().i(this.b);
            if (i != null && !TextUtils.isEmpty(i.e())) {
                textView.setText(i.e());
            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(DisplayUtils.a((Activity) this, 24.0f), 1));
        recyclerView.setAdapter(new RoomDeviceAdapter(this, a(this.b, this.c), b()));
    }

    private boolean b() {
        return this.d;
    }

    private List<Device> a(String str, String str2) {
        if (!b()) {
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -2077299665) {
                if (hashCode != -252753263) {
                    if (hashCode != 491886639) {
                        if (hashCode == 1189320177 && str.equals(HomeManager.h)) {
                            c2 = 0;
                        }
                    } else if (str.equals(HomeManager.e)) {
                        c2 = 1;
                    }
                } else if (str.equals(HomeManager.d)) {
                    c2 = 3;
                }
            } else if (str.equals(HomeManager.f)) {
                c2 = 2;
            }
            switch (c2) {
                case 0:
                    List<GridViewData> F = HomeManager.a().F();
                    ArrayList arrayList = new ArrayList();
                    for (GridViewData next : F) {
                        if (!(next == null || next.b == null)) {
                            arrayList.add(next.b);
                        }
                    }
                    return arrayList;
                case 1:
                    return MultiHomeDeviceManager.a().e();
                case 2:
                    return MultiHomeDeviceManager.a().g();
                case 3:
                    return HomeManager.a().j();
                default:
                    Room i = HomeManager.a().i(str);
                    if (i != null) {
                        return HomeManager.a().c(i);
                    }
                    LogUtil.a(e, "getRoomDids: should not  happened");
                    return new ArrayList();
            }
        } else {
            ArrayList arrayList2 = new ArrayList();
            List<String> list = SmartHomeDeviceHelper.a().b().j(this.f18193a).get(str2);
            ArrayList arrayList3 = new ArrayList();
            if (list != null) {
                for (String b2 : list) {
                    Device b3 = SmartHomeDeviceManager.a().b(b2);
                    if (b3 != null) {
                        arrayList3.add(b3.did);
                    }
                }
            }
            for (String b4 : a(arrayList3)) {
                Device b5 = SmartHomeDeviceManager.a().b(b4);
                if (b5 != null) {
                    arrayList2.add(b5);
                }
            }
            return arrayList2;
        }
    }

    private List<String> a(List<String> list) {
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        if (!b()) {
            return list;
        }
        DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        DeviceTagInterface.Category d2 = (b2 == null || this.c == null) ? null : b2.d(this.c);
        if (d2 == null) {
            return list;
        }
        HomeManager a2 = HomeManager.a();
        List<String> a3 = a2.a((String) null, d2.f15435a + "");
        if (a3 == null || a3.isEmpty()) {
            return list;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < a3.size(); i++) {
            String str = a3.get(i);
            if (!TextUtils.isEmpty(str) && arrayList.contains(str)) {
                arrayList2.add(str);
                arrayList.remove(str);
            }
        }
        arrayList2.addAll(arrayList);
        return arrayList2;
    }

    private static class RoomDeviceAdapter extends RecyclerView.Adapter<MyViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        List<Device> f18196a = new ArrayList();
        private final LayoutInflater b;
        /* access modifiers changed from: private */
        public boolean c;
        private Context d;

        public RoomDeviceAdapter(Context context, List<Device> list, boolean z) {
            this.d = context;
            this.f18196a = list;
            this.c = z;
            this.b = LayoutInflater.from(this.d);
        }

        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(this.b.inflate(R.layout.room_list_device_item, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            myViewHolder.a(this.f18196a.get(i));
        }

        public int getItemCount() {
            if (this.f18196a == null) {
                return 0;
            }
            return this.f18196a.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView b;
            private TextView c;
            private SimpleDraweeView d;

            public MyViewHolder(View view) {
                super(view);
                this.b = (TextView) view.findViewById(R.id.title);
                this.c = (TextView) view.findViewById(R.id.room);
                this.d = (SimpleDraweeView) view.findViewById(R.id.icon);
            }

            public void a(Device device) {
                if (device != null) {
                    this.b.setText(device.name);
                    if (RoomDeviceAdapter.this.c) {
                        this.c.setVisibility(0);
                        String r = HomeManager.a().r(device.did);
                        if (!TextUtils.isEmpty(r)) {
                            this.c.setText(r);
                        } else {
                            this.c.setVisibility(8);
                        }
                    } else {
                        this.c.setVisibility(8);
                    }
                    DeviceFactory.b(device.model, this.d);
                }
            }
        }
    }

    private static class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private final Paint f18195a = new Paint(1);
        private final Paint b = new Paint(1);
        private int c;
        private int d;

        public MyDividerItemDecoration(int i, int i2) {
            this.c = i;
            this.d = i2;
            this.b.setColor(-1);
            this.f18195a.setColor(0);
            this.f18195a.setAlpha(38);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            rect.bottom = this.d;
            if (childAdapterPosition == 0) {
                rect.top = this.d;
            }
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            for (int i = 0; i < linearLayoutManager.getChildCount(); i++) {
                View childAt = linearLayoutManager.getChildAt(i);
                if (i == 0) {
                    canvas.drawRect(0.0f, (float) childAt.getTop(), (float) childAt.getWidth(), (float) (childAt.getTop() + this.d), this.f18195a);
                    canvas.drawRect(0.0f, (float) childAt.getBottom(), (float) this.c, (float) (childAt.getBottom() + this.d), this.b);
                    canvas.drawRect((float) this.c, (float) childAt.getBottom(), (float) childAt.getWidth(), (float) (childAt.getBottom() + this.d), this.f18195a);
                }
                if (i == linearLayoutManager.getItemCount() - 1) {
                    canvas.drawRect(0.0f, (float) childAt.getBottom(), (float) childAt.getWidth(), (float) (childAt.getBottom() + this.d), this.f18195a);
                } else {
                    canvas.drawRect(0.0f, (float) childAt.getBottom(), (float) this.c, (float) (childAt.getBottom() + this.d), this.b);
                    canvas.drawRect((float) this.c, (float) childAt.getBottom(), (float) childAt.getWidth(), (float) (childAt.getBottom() + this.d), this.f18195a);
                }
            }
        }
    }
}
