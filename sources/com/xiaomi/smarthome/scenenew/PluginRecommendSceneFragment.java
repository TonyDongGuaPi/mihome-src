package com.xiaomi.smarthome.scenenew;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.stat.STAT;

public class PluginRecommendSceneFragment extends BaseFragment {
    private static final String f = RecommendSceneFragment.class.getSimpleName();

    /* renamed from: a  reason: collision with root package name */
    View f21759a;
    MyAdapter b;
    CustomPullDownRefreshListView c;
    LinearLayout d;
    TextView e;
    /* access modifiers changed from: private */
    public String g;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f21759a == null) {
            this.f21759a = layoutInflater.inflate(R.layout.fragment_recommend_layout, (ViewGroup) null);
            this.c = (CustomPullDownRefreshListView) this.f21759a.findViewById(R.id.pull_listview);
            this.d = (LinearLayout) this.f21759a.findViewById(R.id.common_white_empty_view);
            this.e = (TextView) this.f21759a.findViewById(R.id.common_white_empty_text);
            this.e.setText(R.string.recommend_scene_no_data);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PluginRecommendSceneFragment.this.c != null) {
                        PluginRecommendSceneFragment.this.c.doRefresh();
                    }
                }
            });
            a();
        }
        return this.f21759a;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null && getArguments().containsKey("device_id")) {
            this.g = getArguments().getString("device_id");
        }
        Device b2 = SmartHomeDeviceManager.a().b(this.g);
        STAT.e.l(b2 != null ? b2.model : "");
        b();
    }

    private void a() {
        this.b = new MyAdapter(getActivity());
        this.c.setAdapter(this.b);
        this.c.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                FragmentActivity validActivity;
                View currentFocus;
                if (1 == i && (validActivity = PluginRecommendSceneFragment.this.getValidActivity()) != null && (currentFocus = validActivity.getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
    }

    private void b() {
        this.c.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public final void startRefresh() {
                PluginRecommendSceneFragment.this.c();
            }
        });
        this.c.doRefresh();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c() {
        LogUtil.a(f, "startRefresh");
        PluginRecommendSceneManager.a().b(this.g, -1, new AsyncCallback<PluginRecommendSceneInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                if (!(pluginRecommendSceneInfo == null || pluginRecommendSceneInfo.mSceneItems == null || pluginRecommendSceneInfo.mSceneItems.size() <= 0)) {
                    PluginRecommendSceneFragment.this.b.a(pluginRecommendSceneInfo);
                    PluginRecommendSceneFragment.this.b.notifyDataSetChanged();
                }
                PluginRecommendSceneFragment.this.c.postRefresh();
            }

            public void onFailure(Error error) {
                PluginRecommendSceneFragment.this.c.postRefresh();
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onPageSelected() {
        super.onPageSelected();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
    }

    public class MyAdapter extends BaseAdapter {
        private Context b;
        private LayoutInflater c;
        private PluginRecommendSceneInfo d = new PluginRecommendSceneInfo();

        public long getItemId(int i) {
            return (long) i;
        }

        public MyAdapter(Context context) {
            this.b = context;
            this.c = LayoutInflater.from(context);
        }

        public void a(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
            if (pluginRecommendSceneInfo != null) {
                this.d = pluginRecommendSceneInfo;
            } else {
                this.d = new PluginRecommendSceneInfo();
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.d == null || this.d.mSceneItems == null) {
                return 0;
            }
            return this.d.mSceneItems.size();
        }

        public Object getItem(int i) {
            return this.d.mSceneItems.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            MyViewHolder myViewHolder;
            if (view == null) {
                view = this.c.inflate(R.layout.plugin_recommend_scene_new_item, (ViewGroup) null);
                myViewHolder = new MyViewHolder(view);
                view.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) view.getTag();
            }
            PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem = this.d.mSceneItems.get(i);
            myViewHolder.f21764a.setHierarchy(new GenericDraweeHierarchyBuilder(this.b.getResources()).setFadeDuration(200).setPlaceholderImage(this.b.getResources().getDrawable(R.drawable.recommend_scene_list_default_icon)).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setActualImageScaleType(ScalingUtils.ScaleType.CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            if (!TextUtils.isEmpty(recommendSceneItem.cardImgUrl)) {
                myViewHolder.f21764a.setImageURI(Uri.parse(recommendSceneItem.cardImgUrl));
            } else {
                myViewHolder.f21764a.setImageResource(R.drawable.recommend_scene_list_default_icon);
            }
            if (!TextUtils.isEmpty(recommendSceneItem.intro)) {
                myViewHolder.b.setText(recommendSceneItem.intro);
            } else {
                myViewHolder.b.setText("");
            }
            if (!TextUtils.isEmpty(recommendSceneItem.cardDesc)) {
                myViewHolder.c.setText(recommendSceneItem.cardDesc);
            } else {
                myViewHolder.c.setText("");
            }
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener(recommendSceneItem) {
                private final /* synthetic */ PluginRecommendSceneInfo.RecommendSceneItem f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PluginRecommendSceneFragment.MyAdapter.this.a(this.f$1, view);
                }
            });
            return view;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
            if (r0.equals("1001") != false) goto L_0x0091;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ void a(com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.RecommendSceneItem r6, android.view.View r7) {
            /*
                r5 = this;
                if (r6 == 0) goto L_0x00f5
                java.lang.String r7 = r6.sr_id
                boolean r7 = android.text.TextUtils.isEmpty(r7)
                if (r7 != 0) goto L_0x00f5
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r7 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment r0 = com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment.this
                java.lang.String r0 = r0.g
                com.xiaomi.smarthome.device.Device r7 = r7.b((java.lang.String) r0)
                if (r7 == 0) goto L_0x0023
                com.xiaomi.smarthome.stat.StatClick r0 = com.xiaomi.smarthome.stat.STAT.d
                java.lang.String r7 = r7.model
                java.lang.String r1 = r6.sr_id
                r0.q(r7, r1)
            L_0x0023:
                r7 = 1
                java.lang.String r0 = r6.sr_id     // Catch:{ Exception -> 0x005e }
                long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x005e }
                r2 = 1000(0x3e8, double:4.94E-321)
                long r0 = r0 / r2
                r2 = 2
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 != 0) goto L_0x005e
                android.content.Intent r0 = new android.content.Intent     // Catch:{ Exception -> 0x005e }
                android.content.Context r1 = r5.b     // Catch:{ Exception -> 0x005e }
                java.lang.Class<com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity> r2 = com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity.class
                r0.<init>(r1, r2)     // Catch:{ Exception -> 0x005e }
                java.lang.String r1 = "need_choose_detail"
                r0.putExtra(r1, r7)     // Catch:{ Exception -> 0x005e }
                java.lang.String r1 = "sr_id"
                java.lang.Integer r2 = new java.lang.Integer     // Catch:{ Exception -> 0x005e }
                java.lang.String r3 = r6.sr_id     // Catch:{ Exception -> 0x005e }
                r2.<init>(r3)     // Catch:{ Exception -> 0x005e }
                r0.putExtra(r1, r2)     // Catch:{ Exception -> 0x005e }
                java.lang.String r1 = "did"
                com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment r2 = com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment.this     // Catch:{ Exception -> 0x005e }
                java.lang.String r2 = r2.g     // Catch:{ Exception -> 0x005e }
                r0.putExtra(r1, r2)     // Catch:{ Exception -> 0x005e }
                android.content.Context r1 = r5.b     // Catch:{ Exception -> 0x005e }
                r1.startActivity(r0)     // Catch:{ Exception -> 0x005e }
                return
            L_0x005e:
                java.lang.String r0 = r6.sr_id
                r1 = -1
                int r2 = r0.hashCode()
                switch(r2) {
                    case 1507423: goto L_0x0086;
                    case 1507424: goto L_0x007d;
                    case 1507425: goto L_0x0073;
                    case 1507426: goto L_0x0069;
                    default: goto L_0x0068;
                }
            L_0x0068:
                goto L_0x0090
            L_0x0069:
                java.lang.String r7 = "1003"
                boolean r7 = r0.equals(r7)
                if (r7 == 0) goto L_0x0090
                r7 = 3
                goto L_0x0091
            L_0x0073:
                java.lang.String r7 = "1002"
                boolean r7 = r0.equals(r7)
                if (r7 == 0) goto L_0x0090
                r7 = 2
                goto L_0x0091
            L_0x007d:
                java.lang.String r2 = "1001"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0090
                goto L_0x0091
            L_0x0086:
                java.lang.String r7 = "1000"
                boolean r7 = r0.equals(r7)
                if (r7 == 0) goto L_0x0090
                r7 = 0
                goto L_0x0091
            L_0x0090:
                r7 = -1
            L_0x0091:
                switch(r7) {
                    case 0: goto L_0x00bb;
                    case 1: goto L_0x0095;
                    case 2: goto L_0x0095;
                    case 3: goto L_0x0095;
                    default: goto L_0x0094;
                }
            L_0x0094:
                goto L_0x00f5
            L_0x0095:
                android.content.Intent r7 = new android.content.Intent
                android.content.Context r0 = r5.b
                java.lang.Class<com.xiaomi.smarthome.scenenew.pluginrecommend.LightActionStartActivity> r1 = com.xiaomi.smarthome.scenenew.pluginrecommend.LightActionStartActivity.class
                r7.<init>(r0, r1)
                java.lang.String r0 = "sr_id"
                java.lang.Integer r1 = new java.lang.Integer
                java.lang.String r6 = r6.sr_id
                r1.<init>(r6)
                r7.putExtra(r0, r1)
                java.lang.String r6 = "did"
                com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment r0 = com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment.this
                java.lang.String r0 = r0.g
                r7.putExtra(r6, r0)
                android.content.Context r6 = r5.b
                r6.startActivity(r7)
                goto L_0x00f5
            L_0x00bb:
                com.xiaomi.smarthome.stat.StatClick r7 = com.xiaomi.smarthome.stat.STAT.d
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment r1 = com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment.this
                java.lang.String r1 = r1.g
                com.xiaomi.smarthome.device.Device r0 = r0.b((java.lang.String) r1)
                java.lang.String r0 = r0.model
                r7.aB(r0)
                android.content.Intent r7 = new android.content.Intent
                android.content.Context r0 = r5.b
                java.lang.Class<com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity> r1 = com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity.class
                r7.<init>(r0, r1)
                java.lang.String r0 = "sr_id"
                java.lang.Integer r1 = new java.lang.Integer
                java.lang.String r6 = r6.sr_id
                r1.<init>(r6)
                r7.putExtra(r0, r1)
                java.lang.String r6 = "did"
                com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment r0 = com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment.this
                java.lang.String r0 = r0.g
                r7.putExtra(r6, r0)
                android.content.Context r6 = r5.b
                r6.startActivity(r7)
            L_0x00f5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment.MyAdapter.a(com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem, android.view.View):void");
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f21764a;
            TextView b;
            TextView c;

            public MyViewHolder(View view) {
                super(view);
                this.f21764a = (SimpleDraweeView) view.findViewById(R.id.recommend_sdv);
                this.b = (TextView) view.findViewById(R.id.recommend_title);
                this.c = (TextView) view.findViewById(R.id.recommend_desc);
            }
        }
    }
}
