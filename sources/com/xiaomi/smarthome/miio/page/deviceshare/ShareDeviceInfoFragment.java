package com.xiaomi.smarthome.miio.page.deviceshare;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.library.common.widget.SmartHomePtrHeader;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.page.ShareDeviceDetail;
import com.xiaomi.smarthome.newui.ListHeaderSpaceItemDecoration;
import com.xiaomi.smarthome.share.ShareActivity;
import com.xiaomi.smarthome.stat.STAT;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ShareDeviceInfoFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19773a = "user_id";
    private boolean A = false;
    XQProgressDialog b;
    private View c;
    /* access modifiers changed from: private */
    public List<Device> d = new ArrayList();
    /* access modifiers changed from: private */
    public HashMap<String, List<ShareDeviceDetail.ShareUser>> e = new HashMap<>();
    private View f;
    private View g;
    private View h;
    private View i;
    private ImageView j;
    /* access modifiers changed from: private */
    public ImageView k;
    private View l;
    private TextView m;
    private View n;
    /* access modifiers changed from: private */
    public TextView o;
    /* access modifiers changed from: private */
    public boolean p = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray q = new SparseBooleanArray();
    private RecyclerView r;
    /* access modifiers changed from: private */
    public SectionedRecyclerViewAdapter s;
    /* access modifiers changed from: private */
    public DevicePtrFrameLayout t;
    private SmartHomePtrHeader u;
    private PtrIndicator v;
    private RecyclerView.ItemDecoration w;
    /* access modifiers changed from: private */
    public boolean x;
    /* access modifiers changed from: private */
    public int y = 0;
    private boolean z = false;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            this.c = layoutInflater.inflate(R.layout.share_device_layout_v2, (ViewGroup) null);
            h();
            a();
        } else if (this.c.getParent() != null) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        return this.c;
    }

    public void onResume() {
        super.onResume();
        if (this.t != null) {
            this.t.autoRefresh();
        }
    }

    public void onPause() {
        super.onPause();
        this.x = false;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        List<Device> d2;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.b = new XQProgressDialog(activity);
            this.b.setCancelable(false);
            this.b.setMessage(getResources().getString(R.string.loading_share_info));
            List<Home> f2 = HomeManager.a().f();
            if (f2 == null || f2.isEmpty()) {
                this.r.setVisibility(8);
                this.f.setVisibility(0);
                return;
            }
            this.d.clear();
            Home m2 = HomeManager.a().m();
            ArrayList<Home> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < f2.size(); i2++) {
                Home home = f2.get(i2);
                if (!(home == null || (d2 = MultiHomeDeviceManager.a().d(home.j())) == null || d2.isEmpty())) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < d2.size(); i3++) {
                        Device device = d2.get(i3);
                        if (device != null && device.canBeShared()) {
                            arrayList2.add(device);
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        this.d.addAll(arrayList2);
                        if (m2 == null || !TextUtils.equals(m2.j(), home.j())) {
                            arrayList.add(home);
                        } else {
                            arrayList.add(0, home);
                        }
                    }
                }
            }
            this.y = arrayList.size();
            for (Home home2 : arrayList) {
                List<Device> d3 = MultiHomeDeviceManager.a().d(home2.j());
                if (d3 != null && !d3.isEmpty()) {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i4 = 0; i4 < d3.size(); i4++) {
                        Device device2 = d3.get(i4);
                        if (device2 != null && device2.canBeShared()) {
                            arrayList3.add(device2);
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        this.s.a((Section) new BaseSection(home2.k(), arrayList3));
                    }
                }
            }
            ((TextView) this.c.findViewById(R.id.common_white_empty_text)).setText(R.string.share_no_device);
            if (this.y == 0) {
                this.r.setVisibility(8);
                this.f.setVisibility(0);
                return;
            }
            this.s.notifyDataSetChanged();
        }
    }

    private void h() {
        this.f = this.c.findViewById(R.id.common_white_empty_view);
        this.g = this.c.findViewById(R.id.title_bar_choose_device);
        this.h = this.c.findViewById(R.id.menu_bar_choose_device);
        this.f.setVisibility(8);
        this.r = (RecyclerView) this.c.findViewById(R.id.share_device_list);
        this.r.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.w = new ListHeaderSpaceItemDecoration();
        this.t = (DevicePtrFrameLayout) this.c.findViewById(R.id.pull_down_refresh);
        this.s = new SectionedRecyclerViewAdapter();
        this.r.setAdapter(this.s);
        j();
        if (this.A && !this.z) {
            this.z = true;
            onPageSelected();
        }
        i();
    }

    private void i() {
        this.u = (SmartHomePtrHeader) this.c.findViewById(R.id.pull_header_new);
        this.v = new PtrIndicator();
        this.t.disableWhenHorizontalMove(true);
        this.t.setPullToRefresh(false);
        this.t.setPtrIndicator(this.v);
        this.t.addPtrUIHandler(new PtrUIHandler() {
            public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
            }

            public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIReset(PtrFrameLayout ptrFrameLayout) {
            }
        });
        this.t.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                RemoteFamilyApi.a().a((Context) ShareDeviceInfoFragment.this.getActivity(), (AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>) new AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(HashMap<String, List<ShareDeviceDetail.ShareUser>> hashMap) {
                        if (ShareDeviceInfoFragment.this.isValid()) {
                            if (!ShareDeviceInfoFragment.this.x) {
                                boolean unused = ShareDeviceInfoFragment.this.x = true;
                                STAT.c.b(hashMap.size());
                            }
                            HashMap unused2 = ShareDeviceInfoFragment.this.e = hashMap;
                            Set<String> keySet = hashMap.keySet();
                            final ArrayList arrayList = new ArrayList();
                            for (String str : keySet) {
                                arrayList.addAll(hashMap.get(str));
                            }
                            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                                public void run() {
                                    ShareUserRecord.batchInsert(arrayList);
                                }
                            });
                            ShareDeviceInfoFragment.this.s.notifyDataSetChanged();
                            if (ShareDeviceInfoFragment.this.b != null) {
                                ShareDeviceInfoFragment.this.b.dismiss();
                            }
                            ShareDeviceInfoFragment.this.t.refreshComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (ShareDeviceInfoFragment.this.isValid()) {
                            if (ShareDeviceInfoFragment.this.b != null) {
                                ShareDeviceInfoFragment.this.b.dismiss();
                            }
                            ShareDeviceInfoFragment.this.s.notifyDataSetChanged();
                            ShareDeviceInfoFragment.this.t.refreshComplete();
                        }
                    }
                });
            }
        });
    }

    protected class BaseSection extends Section {
        private List<Device> b;
        private String i;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BaseSection(String str, List<Device> list) {
            super(new SectionParameters.Builder(R.layout.message_item_new).a(ShareDeviceInfoFragment.this.y <= 1 ? R.layout.common_list_space_empty : R.layout.common_list_section_1).a());
            this.b = list;
            this.i = str;
        }

        public int a() {
            return this.b.size();
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ChildViewHolder(view);
        }

        public RecyclerView.ViewHolder a(View view) {
            return new HeaderViewHolder(view);
        }

        private int a(Device device) {
            for (int i2 = 0; i2 < ShareDeviceInfoFragment.this.d.size(); i2++) {
                Device device2 = (Device) ShareDeviceInfoFragment.this.d.get(i2);
                if (device2 != null && TextUtils.equals(device.did, device2.did)) {
                    return i2;
                }
            }
            return 0;
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i2) {
            String str;
            final ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
            Device device = this.b.get(i2);
            final int a2 = a(device);
            childViewHolder.e.setText(((Device) ShareDeviceInfoFragment.this.d.get(a2)).name);
            childViewHolder.f19786a.setVisibility(8);
            List list = (List) ShareDeviceInfoFragment.this.e.get(((Device) ShareDeviceInfoFragment.this.d.get(a2)).did);
            if (i2 == a() - 1) {
                childViewHolder.h.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            } else {
                childViewHolder.h.setBackgroundResource(R.drawable.common_white_list_padding);
            }
            if (list == null || list.size() == 0) {
                str = ShareDeviceInfoFragment.this.getString(R.string.smarthome_not_shared);
            } else {
                StringBuilder sb = new StringBuilder();
                int i3 = 0;
                while (i3 < list.size() && i3 < 2) {
                    if (i3 != 0) {
                        sb.append(',');
                    }
                    sb.append(((ShareDeviceDetail.ShareUser) list.get(i3)).b);
                    i3++;
                }
                if (list.size() > 2) {
                    str = ShareDeviceInfoFragment.this.getResources().getQuantityString(R.plurals.smarthome_has_shared_to_user_more, list.size(), new Object[]{sb.toString(), Integer.valueOf(list.size())});
                } else {
                    str = ShareDeviceInfoFragment.this.getString(R.string.smarthome_has_shared_to_user, sb.toString());
                }
            }
            childViewHolder.f.setText(str);
            if (childViewHolder.b != null) {
                childViewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(childViewHolder.b.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
                DeviceFactory.b(device.model, childViewHolder.b);
            }
            childViewHolder.h.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (!ShareDeviceInfoFragment.this.p) {
                        if (!ShareDeviceInfoFragment.this.q.get(a2)) {
                            childViewHolder.d.performClick();
                        }
                        ShareDeviceInfoFragment.this.g();
                        boolean unused = ShareDeviceInfoFragment.this.p = true;
                        ShareDeviceInfoFragment.this.s.notifyDataSetChanged();
                        StatHelper.K();
                        ((ShareDeviceInfoActivity) ShareDeviceInfoFragment.this.getActivity()).setViewPagerSwipe(false);
                    }
                    return true;
                }
            });
            childViewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!ShareDeviceInfoFragment.this.q.get(a2)) {
                        ShareDeviceInfoFragment.this.q.put(a2, true);
                    } else {
                        ShareDeviceInfoFragment.this.q.delete(a2);
                    }
                    if (ShareDeviceInfoFragment.this.q.size() == ShareDeviceInfoFragment.this.d.size()) {
                        ShareDeviceInfoFragment.this.k.setImageResource(R.drawable.un_select_selector);
                    } else {
                        ShareDeviceInfoFragment.this.k.setImageResource(R.drawable.all_select_selector);
                    }
                    if (ShareDeviceInfoFragment.this.q.size() > 0) {
                        ShareDeviceInfoFragment.this.o.setVisibility(0);
                        ShareDeviceInfoFragment.this.o.setText(ShareDeviceInfoFragment.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, ShareDeviceInfoFragment.this.q.size(), new Object[]{Integer.valueOf(ShareDeviceInfoFragment.this.q.size())}));
                        return;
                    }
                    ShareDeviceInfoFragment.this.o.setVisibility(0);
                    ShareDeviceInfoFragment.this.o.setText(ShareDeviceInfoFragment.this.getString(R.string.selected_0_cnt_tips));
                }
            });
            if (ShareDeviceInfoFragment.this.p) {
                childViewHolder.f19786a.setVisibility(0);
                childViewHolder.d.setVisibility(0);
                childViewHolder.c.setVisibility(8);
                childViewHolder.g.setVisibility(8);
                childViewHolder.h.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        childViewHolder.d.performClick();
                    }
                });
                if (ShareDeviceInfoFragment.this.q.get(a2)) {
                    childViewHolder.d.setChecked(true);
                } else {
                    childViewHolder.d.setChecked(false);
                }
            } else {
                childViewHolder.f19786a.setVisibility(0);
                childViewHolder.d.setVisibility(8);
                childViewHolder.c.setVisibility(8);
                childViewHolder.g.setVisibility(0);
                childViewHolder.h.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        STAT.d.af(((Device) ShareDeviceInfoFragment.this.d.get(a2)).model);
                        Intent intent = new Intent();
                        intent.setClass(ShareDeviceInfoFragment.this.getActivity(), ShareDeviceDetail.class);
                        intent.putExtra("did", ((Device) ShareDeviceInfoFragment.this.d.get(a2)).did);
                        intent.putExtra("pid", ((Device) ShareDeviceInfoFragment.this.d.get(a2)).pid);
                        Bundle arguments = ShareDeviceInfoFragment.this.getArguments();
                        if (arguments != null) {
                            intent.putExtra(ShareDeviceDetail.KEY_REF_FROM_APP, arguments.getBoolean(ShareDeviceInfoActivity.PARAM_KEY_REF_FROM_APP));
                        }
                        ShareDeviceInfoFragment.this.startActivity(intent);
                        STAT.d.aB();
                    }
                });
            }
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            if (ShareDeviceInfoFragment.this.y > 1) {
                ((HeaderViewHolder) viewHolder).f19787a.setText(this.i);
            }
        }

        class ChildViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            View f19786a;
            SimpleDraweeView b;
            TextView c;
            CheckBox d;
            TextView e;
            TextView f;
            View g;
            View h;

            public ChildViewHolder(View view) {
                super(view);
                this.h = view;
                this.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                this.c = (TextView) view.findViewById(R.id.right_tv);
                this.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                this.e = (TextView) view.findViewById(R.id.device_item);
                this.f = (TextView) view.findViewById(R.id.device_item_info);
                this.f19786a = view.findViewById(R.id.right_fl);
                this.g = view.findViewById(R.id.arrow);
            }
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f19787a;

            public HeaderViewHolder(View view) {
                super(view);
                this.f19787a = (TextView) view.findViewById(R.id.f13396tv);
            }
        }
    }

    public void onPageSelected() {
        if (this.j != null) {
            this.j.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ((ShareDeviceInfoActivity) ShareDeviceInfoFragment.this.getActivity()).setViewPagerSwipe(true);
                    ShareDeviceInfoFragment.this.e();
                }
            });
        }
        if (this.k != null) {
            this.k.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ShareDeviceInfoFragment.this.q.size() == ShareDeviceInfoFragment.this.d.size()) {
                        ShareDeviceInfoFragment.this.d();
                        StatHelper.c(false);
                        return;
                    }
                    ShareDeviceInfoFragment.this.c();
                    StatHelper.c(true);
                }
            });
        }
    }

    public void setMenuVisibility(boolean z2) {
        super.setMenuVisibility(z2);
        this.A = z2;
        if (!z2) {
            return;
        }
        if (getView() == null) {
            this.z = false;
            return;
        }
        onPageSelected();
        this.z = true;
    }

    private void j() {
        if (getActivity() != null && getActivity().findViewById(R.id.top_delete_bar) != null) {
            this.i = getActivity().findViewById(R.id.top_delete_bar);
            this.o = (TextView) this.i.findViewById(R.id.selected_cnt);
            this.j = (ImageView) this.i.findViewById(R.id.cancel_btn);
            this.k = (ImageView) this.i.findViewById(R.id.select_all_btn);
            this.l = this.c.findViewById(R.id.bottom_delete_bar);
            this.m = (TextView) this.l.findViewById(R.id.delete_msg_btn);
            this.m.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShareDeviceInfoFragment.this.b();
                    StatHelper.L();
                }
            });
            boolean z2 = TitleBarUtil.f11582a;
        }
    }

    public void b() {
        if (this.q.size() == 0) {
            ToastUtil.a((Context) getActivity(), (int) R.string.share_no_device);
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.mContext, DeviceShortcutUtils.a());
        if (this.q.size() == 1) {
            intent.putExtra("user_id", CoreApi.a().s());
            intent.putExtra("did", this.d.get(this.q.keyAt(0)).did);
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.q.size(); i2++) {
                int keyAt = this.q.keyAt(i2);
                if (this.q.get(keyAt) && this.d.size() > keyAt) {
                    arrayList.add(this.d.get(keyAt).did);
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("user_id", CoreApi.a().s());
            bundle.putStringArrayList(ShareActivity.ARGS_KEY_BATCH_DIDS, arrayList);
            intent.putExtras(bundle);
        }
        this.mContext.startActivity(intent);
    }

    public void c() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.q.put(i2, true);
        }
        this.k.setImageResource(R.drawable.un_select_selector);
        this.s.notifyDataSetChanged();
        this.o.setVisibility(0);
        this.o.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.q.size(), new Object[]{Integer.valueOf(this.q.size())}));
    }

    public void d() {
        this.q.clear();
        this.k.setImageResource(R.drawable.all_select_selector);
        this.s.notifyDataSetChanged();
        this.o.setVisibility(8);
    }

    public void e() {
        this.p = false;
        this.q.clear();
        f();
        this.s.notifyDataSetChanged();
    }

    public void f() {
        this.i.setVisibility(8);
        this.l.setVisibility(8);
    }

    public void g() {
        this.i.setVisibility(0);
        this.l.setVisibility(0);
        this.i.measure(0, 0);
        this.l.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i, View.Y, new float[]{(float) (-this.i.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.l.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.l, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.l.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public boolean onBackPressed() {
        if (!this.p) {
            return false;
        }
        e();
        return true;
    }
}
