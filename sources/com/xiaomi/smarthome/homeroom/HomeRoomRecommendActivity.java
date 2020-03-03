package com.xiaomi.smarthome.homeroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.model.RoomConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeRoomRecommendActivity extends BaseActivity implements View.OnClickListener {
    public static final String FROM_PLUG = "come_from_plug";
    public static final String HOME_ID_PARAM = "home_id_param";

    /* renamed from: a  reason: collision with root package name */
    private View f18117a;
    /* access modifiers changed from: private */
    public GridView b;
    /* access modifiers changed from: private */
    public Locale c;
    private View d;
    /* access modifiers changed from: private */
    public String e;
    private boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;

    public static void startActivity(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(context, HomeRoomRecommendActivity.class);
            intent.putExtra("home_id_param", str);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_recommend);
        this.e = getIntent().getStringExtra("home_id_param");
        if (TextUtils.isEmpty(this.e)) {
            this.e = HomeManager.a().l();
        }
        this.f = getIntent().getBooleanExtra(FROM_PLUG, false);
        this.f18117a = findViewById(R.id.module_a_3_return_btn);
        findViewById(R.id.module_a_3_right_text_btn).setVisibility(8);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.tag_add_title);
        this.f18117a.setOnClickListener(this);
        a();
        this.c = CoreApi.a().I();
        if (this.c == null) {
            this.c = Locale.getDefault();
        }
        if (HomeManager.a().f().size() > 1) {
            this.d = ((ViewStub) findViewById(R.id.import_device)).inflate();
            this.d.setOnClickListener(this);
            findViewById(R.id.divider_top).setVisibility(8);
        }
    }

    public void onClick(View view) {
        if (view == this.f18117a) {
            finish();
        } else if (view == this.d) {
            HomeRoomImportRoom.startActivity(this, this.e);
            finish();
        }
    }

    private void a() {
        this.b = (GridView) findViewById(R.id.grid_view);
        ((TextView) findViewById(R.id.title_left)).setText(R.string.tag_recommend_title);
        List<RoomConfig> A = ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).A();
        final HomeRoomRecommendAdapter homeRoomRecommendAdapter = new HomeRoomRecommendAdapter(this);
        homeRoomRecommendAdapter.a(A);
        this.b.setAdapter(homeRoomRecommendAdapter);
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (!HomeRoomRecommendActivity.this.g) {
                    boolean unused = HomeRoomRecommendActivity.this.g = true;
                    HomeRoomRecommendActivity.this.a(HomeRoomRecommendActivity.this.b, homeRoomRecommendAdapter.getCount(), HomeRoomRecommendActivity.this.b.getNumColumns());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(GridView gridView, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = gridView.getHeight() * ((i / i2) + 2);
        gridView.setLayoutParams(layoutParams);
    }

    class HomeRoomRecommendAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public List<RoomConfig> b = new ArrayList();
        private Context c;

        public long getItemId(int i) {
            return 0;
        }

        public HomeRoomRecommendAdapter(Context context) {
            this.c = context;
        }

        public void a(List<RoomConfig> list) {
            this.b = list;
        }

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        /* renamed from: a */
        public RoomConfig getItem(int i) {
            if (i < 0 || this.b == null || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.c).inflate(R.layout.tag_child_item_recommend, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f18121a = (SimpleDraweeView) view.findViewById(R.id.room_icon);
                viewHolder.b = (TextView) view.findViewById(R.id.room_name);
                view.setTag(viewHolder);
                viewHolder.a();
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.b.setTag(Integer.valueOf(i));
            viewHolder.f18121a.setTag(view);
            RoomConfig roomConfig = this.b.get(i);
            viewHolder.b.setText(roomConfig.a(HomeRoomRecommendActivity.this.c));
            if (TextUtils.equals(roomConfig.a(), "more")) {
                viewHolder.f18121a.setHierarchy(new GenericDraweeHierarchyBuilder(HomeRoomRecommendActivity.this.getResources()).setPlaceholderImage((Drawable) null).build());
                viewHolder.f18121a.setBackgroundResource(R.drawable.selector_add_custom_room);
                viewHolder.b.setText(HomeRoomRecommendActivity.this.getString(R.string.tag_recommend_custom));
            } else {
                String str = roomConfig.a() + "_1";
                viewHolder.f18121a.setImageURI(Uri.parse("file://" + HomeManager.a().f(str)));
                viewHolder.f18121a.setBackgroundResource(R.drawable.selector_add_recommend_room);
            }
            viewHolder.f18121a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    RoomConfig roomConfig = (RoomConfig) HomeRoomRecommendAdapter.this.b.get(i);
                    String b2 = SmartHomeDeviceHelper.a().b().b(4, roomConfig.a(HomeRoomRecommendActivity.this.c), HomeRoomRecommendActivity.this.e);
                    String a2 = roomConfig.a();
                    if (a2 == null || a2.isEmpty()) {
                        a2 = "more";
                    }
                    if (TextUtils.equals("more", a2)) {
                        b2 = "";
                    }
                    Intent intent = new Intent(HomeRoomRecommendActivity.this, HomeRoomEditorActivityV2.class);
                    intent.putExtra("room_name", b2);
                    intent.putExtra("home_id_param", HomeRoomRecommendActivity.this.e);
                    intent.putExtra("room_icon", a2 + "_1");
                    HomeRoomRecommendActivity.this.startActivity(intent);
                    HomeRoomRecommendActivity.this.finish();
                }
            });
            return view;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f18121a;
            TextView b;

            ViewHolder() {
            }

            public void a() {
                this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        int intValue = ((Integer) ViewHolder.this.b.getTag()).intValue();
                        if (intValue > 0) {
                            int numColumns = intValue / HomeRoomRecommendActivity.this.b.getNumColumns();
                            int i = intValue - 1;
                            if (numColumns == i / HomeRoomRecommendActivity.this.b.getNumColumns()) {
                                View view = (View) ViewHolder.this.f18121a.getTag();
                                int height = view.getHeight();
                                View childAt = HomeRoomRecommendActivity.this.b.getChildAt(i);
                                int height2 = childAt.getHeight();
                                if (height > height2) {
                                    childAt.setLayoutParams(new AbsListView.LayoutParams(-1, height));
                                } else if (height < height2) {
                                    view.setLayoutParams(new AbsListView.LayoutParams(-1, height2));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
