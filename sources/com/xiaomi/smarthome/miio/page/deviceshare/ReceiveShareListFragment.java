package com.xiaomi.smarthome.miio.page.deviceshare;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReceiveShareListFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    XQProgressDialog f19749a;
    /* access modifiers changed from: private */
    public List<ReceiveShareItem> b = new ArrayList();
    private View c;
    /* access modifiers changed from: private */
    public PullDownDragListView d;
    /* access modifiers changed from: private */
    public View e;
    private LoadingMoreView f;
    /* access modifiers changed from: private */
    public SimpleAdapter g;
    /* access modifiers changed from: private */
    public AsyncHandle h;
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
    private boolean r = false;
    private boolean s = false;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            this.c = layoutInflater.inflate(R.layout.receive_share_list, (ViewGroup) null);
            TitleBarUtil.a(TitleBarUtil.a(), this.c.findViewById(R.id.title_bar_choose_device));
            a();
        } else if (this.c.getParent() != null) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.n = getActivity().getWindow().findViewById(R.id.title_bar);
        this.d = (PullDownDragListView) this.c.findViewById(R.id.share_message_list);
        this.d.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.common_list_space_empty, this.d, false));
        this.f = new LoadingMoreView(getActivity());
        this.f.setVisibility(0);
        this.d.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                ReceiveShareListFragment.this.h();
            }
        });
        this.e = this.c.findViewById(R.id.common_white_empty_view);
        this.e.setVisibility(8);
        ((TextView) this.c.findViewById(R.id.common_white_empty_text)).setText(R.string.miio_no_message);
        this.g = new SimpleAdapter();
        this.d.setAdapter(this.g);
        i();
        h();
        if (this.s && !this.r) {
            this.r = true;
            onPageSelected();
        }
    }

    private void i() {
        if (getActivity() != null && getActivity().findViewById(R.id.top_delete_bar) != null) {
            this.i = getActivity().findViewById(R.id.top_delete_bar);
            this.o = (TextView) this.i.findViewById(R.id.selected_cnt);
            this.j = (ImageView) this.i.findViewById(R.id.cancel_btn);
            this.k = (ImageView) this.i.findViewById(R.id.select_all_btn);
            this.l = this.c.findViewById(R.id.bottom_delete_bar);
            this.m = (TextView) this.l.findViewById(R.id.delete_msg_btn);
            this.m.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ReceiveShareListFragment.this.b();
                    StatHelper.N();
                }
            });
            boolean z = TitleBarUtil.f11582a;
        }
    }

    public void b() {
        if (this.q.size() == 0) {
            ToastUtil.a((Context) getActivity(), (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(getActivity()).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.q.size(), new Object[]{Integer.valueOf(this.q.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < ReceiveShareListFragment.this.q.size(); i2++) {
                    int keyAt = ReceiveShareListFragment.this.q.keyAt(i2);
                    if (ReceiveShareListFragment.this.q.get(keyAt) && ReceiveShareListFragment.this.b.size() > keyAt) {
                        ReceiveShareItem receiveShareItem = (ReceiveShareItem) ReceiveShareListFragment.this.b.get(keyAt);
                        if (!TextUtils.isEmpty(receiveShareItem.f19759a)) {
                            arrayList.add(receiveShareItem.f19759a);
                            arrayList2.add(receiveShareItem.d);
                        }
                    }
                }
                ReceiveShareListFragment.this.f19749a = new XQProgressDialog(ReceiveShareListFragment.this.getActivity());
                ReceiveShareListFragment.this.f19749a.setCancelable(true);
                ReceiveShareListFragment.this.f19749a.setMessage(ReceiveShareListFragment.this.getResources().getString(R.string.loading_share_info));
                ReceiveShareListFragment.this.f19749a.show();
                ReceiveShareListFragment.this.f19749a.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });
                RemoteFamilyApi.a().b((Context) ReceiveShareListFragment.this.getActivity(), (List<String>) arrayList, (List<String>) arrayList2, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (ReceiveShareListFragment.this.isValid()) {
                            ReceiveShareListFragment.this.q.clear();
                            ReceiveShareListFragment.this.h();
                            ReceiveShareListFragment.this.o.setVisibility(8);
                            ReceiveShareListFragment.this.k.setImageResource(R.drawable.all_select_selector);
                            ReceiveShareListFragment.this.d.setSelectionAfterHeaderView();
                            if (ReceiveShareListFragment.this.f19749a != null) {
                                ReceiveShareListFragment.this.f19749a.dismiss();
                            }
                            SmartHomeDeviceManager.a().p();
                        }
                    }

                    public void onFailure(Error error) {
                        if (ReceiveShareListFragment.this.isValid()) {
                            if (ReceiveShareListFragment.this.f19749a != null) {
                                ReceiveShareListFragment.this.f19749a.dismiss();
                            }
                            if (error.a() != -1 || !TextUtils.equals(error.b(), "too much device")) {
                                ToastUtil.a((int) R.string.smarthome_device_delete_fail);
                            } else {
                                ToastUtil.a((int) R.string.deldevicebatch_warring);
                            }
                        }
                    }
                });
            }
        }).b().show();
    }

    public void c() {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.q.put(i2, true);
        }
        this.k.setImageResource(R.drawable.un_select_selector);
        this.g.notifyDataSetChanged();
        this.o.setVisibility(0);
        this.o.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.q.size(), new Object[]{Integer.valueOf(this.q.size())}));
    }

    public void d() {
        this.q.clear();
        this.k.setImageResource(R.drawable.all_select_selector);
        this.g.notifyDataSetChanged();
        this.o.setVisibility(8);
    }

    public void e() {
        this.d.setPullDownEnabled(true);
        this.p = false;
        this.q.clear();
        f();
        this.g.notifyDataSetChanged();
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

    /* access modifiers changed from: package-private */
    public void h() {
        this.h = RemoteFamilyApi.a().k(getActivity().getApplicationContext(), CoreApi.a().s(), new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (ReceiveShareListFragment.this.isValid()) {
                    AsyncHandle unused = ReceiveShareListFragment.this.h = null;
                    FragmentActivity activity = ReceiveShareListFragment.this.getActivity();
                    if (activity != null && !activity.isFinishing()) {
                        if (ReceiveShareListFragment.this.f19749a != null) {
                            ReceiveShareListFragment.this.f19749a.dismiss();
                        }
                        ReceiveShareListFragment.this.d.postRefresh();
                        try {
                            ArrayList arrayList = new ArrayList();
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.isNull("result")) {
                                ReceiveShareListFragment.this.e.setVisibility(0);
                                ReceiveShareListFragment.this.d.setVisibility(8);
                                return;
                            }
                            JSONArray optJSONArray = jSONObject.optJSONArray("result");
                            if (optJSONArray != null) {
                                if (optJSONArray.length() != 0) {
                                    ReceiveShareListFragment.this.e.setVisibility(8);
                                    ReceiveShareListFragment.this.d.setVisibility(0);
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                        if (optJSONObject != null) {
                                            ReceiveShareItem a2 = ReceiveShareItem.a(optJSONObject);
                                            if (!(a2 == null || (a2.g == 0 && a2.i == 0))) {
                                                arrayList.add(a2);
                                            }
                                        }
                                    }
                                    List unused2 = ReceiveShareListFragment.this.b = arrayList;
                                    Collections.sort(arrayList);
                                    ReceiveShareListFragment.this.g.notifyDataSetChanged();
                                    if (arrayList.size() == 0) {
                                        ReceiveShareListFragment.this.e.setVisibility(0);
                                        ReceiveShareListFragment.this.d.setVisibility(8);
                                        return;
                                    }
                                    return;
                                }
                            }
                            ReceiveShareListFragment.this.e.setVisibility(0);
                            ReceiveShareListFragment.this.d.setVisibility(8);
                        } catch (JSONException unused3) {
                        }
                    }
                }
            }

            public void onFailure(Error error) {
                if (ReceiveShareListFragment.this.isValid()) {
                    ReceiveShareListFragment.this.d.postRefresh();
                    ToastUtil.a((int) R.string.message_center_setting_load_err);
                    ReceiveShareListFragment.this.e.setVisibility(0);
                    AsyncHandle unused = ReceiveShareListFragment.this.h = null;
                    FragmentActivity activity = ReceiveShareListFragment.this.getActivity();
                    if (activity != null && !activity.isFinishing() && ReceiveShareListFragment.this.f19749a != null) {
                        ReceiveShareListFragment.this.f19749a.dismiss();
                    }
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            this.h.cancel();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.d != null) {
            this.d.doRefresh();
        }
    }

    private class SimpleAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        private SimpleAdapter() {
        }

        public int getCount() {
            return ReceiveShareListFragment.this.b.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= ReceiveShareListFragment.this.b.size()) {
                return null;
            }
            return ReceiveShareListFragment.this.b.get(i);
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            View f19766a;
            SimpleDraweeView b;
            TextView c;
            CheckBox d;
            TextView e;
            TextView f;

            private ViewHolder() {
            }
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(ReceiveShareListFragment.this.getActivity()).inflate(R.layout.message_item_new, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.b.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
                viewHolder.c = (TextView) view.findViewById(R.id.right_tv);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.e = (TextView) view.findViewById(R.id.device_item);
                viewHolder.f = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.f19766a = view.findViewById(R.id.right_fl);
                view.findViewById(R.id.arrow).setVisibility(8);
                viewHolder.d.setVisibility(8);
                viewHolder.c.setVisibility(0);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ReceiveShareItem receiveShareItem = (ReceiveShareItem) getItem(i);
            if (i == getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            }
            if (viewHolder.b != null) {
                DeviceFactory.b(receiveShareItem.f, viewHolder.b);
            }
            viewHolder.e.setText(receiveShareItem.e);
            TextView textView = viewHolder.f;
            StringBuilder sb = new StringBuilder();
            sb.append(ReceiveShareListFragment.this.getString(R.string.comefrom_device));
            sb.append(" ");
            sb.append(TextUtils.isEmpty(receiveShareItem.c) ? receiveShareItem.j : receiveShareItem.c);
            textView.setText(sb.toString());
            viewHolder.f19766a.setVisibility(0);
            a(viewHolder.c, receiveShareItem);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (!ReceiveShareListFragment.this.p) {
                        ReceiveShareListFragment.this.d.setPullDownEnabled(false);
                        if (!ReceiveShareListFragment.this.q.get(i)) {
                            ((ViewHolder) view.getTag()).d.performClick();
                        }
                        ReceiveShareListFragment.this.g();
                        boolean unused = ReceiveShareListFragment.this.p = true;
                        SimpleAdapter.this.notifyDataSetChanged();
                        StatHelper.M();
                        ((ShareDeviceInfoActivity) ReceiveShareListFragment.this.getActivity()).setViewPagerSwipe(false);
                    }
                    return true;
                }
            });
            viewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!ReceiveShareListFragment.this.q.get(i)) {
                        ReceiveShareListFragment.this.q.put(i, true);
                    } else {
                        ReceiveShareListFragment.this.q.delete(i);
                    }
                    if (ReceiveShareListFragment.this.q.size() == ReceiveShareListFragment.this.b.size()) {
                        ReceiveShareListFragment.this.k.setImageResource(R.drawable.un_select_selector);
                    } else {
                        ReceiveShareListFragment.this.k.setImageResource(R.drawable.all_select_selector);
                    }
                    if (ReceiveShareListFragment.this.q.size() > 0) {
                        ReceiveShareListFragment.this.o.setVisibility(0);
                        ReceiveShareListFragment.this.o.setText(ReceiveShareListFragment.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, ReceiveShareListFragment.this.q.size(), new Object[]{Integer.valueOf(ReceiveShareListFragment.this.q.size())}));
                        return;
                    }
                    ReceiveShareListFragment.this.o.setVisibility(0);
                    ReceiveShareListFragment.this.o.setText(ReceiveShareListFragment.this.getString(R.string.selected_0_cnt_tips));
                }
            });
            if (ReceiveShareListFragment.this.p) {
                viewHolder.f19766a.setVisibility(0);
                viewHolder.d.setVisibility(0);
                viewHolder.c.setVisibility(8);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ViewHolder) view.getTag()).d.performClick();
                    }
                });
                if (ReceiveShareListFragment.this.q.get(i)) {
                    viewHolder.d.setChecked(true);
                } else {
                    viewHolder.d.setChecked(false);
                }
            } else {
                viewHolder.f19766a.setVisibility(0);
                viewHolder.d.setVisibility(8);
                viewHolder.c.setVisibility(0);
            }
            return view;
        }

        private void a(TextView textView, final ReceiveShareItem receiveShareItem) {
            int i = receiveShareItem.g;
            if (i == 0) {
                textView.setTextColor(ReceiveShareListFragment.this.getResources().getColor(R.color.white));
                textView.setBackgroundResource(R.drawable.std_button_important_selector);
                textView.setPadding(ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_10), ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3), ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_10), ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3));
                textView.setText(R.string.smarthome_share_accept);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ReceiveShareListFragment.this.f19749a = new XQProgressDialog(ReceiveShareListFragment.this.getActivity());
                        ReceiveShareListFragment.this.f19749a.setCancelable(true);
                        ReceiveShareListFragment.this.f19749a.setMessage(ReceiveShareListFragment.this.getResources().getString(R.string.loading_share_info));
                        ReceiveShareListFragment.this.f19749a.show();
                        ReceiveShareListFragment.this.f19749a.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            public void onDismiss(DialogInterface dialogInterface) {
                                ReceiveShareListFragment.this.h();
                            }
                        });
                        ReceiveShareListFragment.this.a(receiveShareItem);
                    }
                });
            } else if (i == 1) {
                textView.setTextColor(ReceiveShareListFragment.this.getResources().getColor(R.color.class_D));
                textView.setPadding(0, ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3), 0, ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3));
                textView.setBackgroundResource(0);
                if (receiveShareItem.k == 0) {
                    textView.setText(R.string.smarthome_share_accepted);
                } else if (receiveShareItem.k == 1) {
                    textView.setText(R.string.share_permission_cannot_control);
                } else if (receiveShareItem.k == 2) {
                    textView.setText(R.string.share_permission_can_control);
                } else {
                    textView.setText(R.string.smarthome_share_accepted);
                }
            } else if (i == 2) {
                textView.setTextColor(ReceiveShareListFragment.this.getResources().getColor(R.color.class_D));
                textView.setPadding(0, ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3), 0, ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3));
                textView.setBackgroundResource(0);
                textView.setText(R.string.smarthome_share_rejected);
            } else {
                textView.setTextColor(ReceiveShareListFragment.this.getResources().getColor(R.color.class_D));
                textView.setPadding(0, ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3), 0, ReceiveShareListFragment.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_3));
                textView.setBackgroundResource(0);
                textView.setText("");
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(ReceiveShareItem receiveShareItem) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), "accept", "", receiveShareItem.b, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (ReceiveShareListFragment.this.isValid()) {
                    SmartHomeDeviceManager.a().p();
                    ReceiveShareListFragment.this.h();
                }
            }

            public void onFailure(Error error) {
                if (ReceiveShareListFragment.this.isValid()) {
                    if (ReceiveShareListFragment.this.f19749a != null) {
                        ReceiveShareListFragment.this.f19749a.dismiss();
                    }
                    if (error.a() == -6) {
                        Toast.makeText(SHApplication.getAppContext(), R.string.smarthome_share_expired_toast, 0).show();
                    } else {
                        Toast.makeText(SHApplication.getAppContext(), R.string.handle_error, 0).show();
                    }
                }
            }
        });
    }

    private static class ReceiveShareItem implements Comparable<ReceiveShareItem> {
        public static final int l = 0;
        public static final int m = 1;
        public static final int n = 2;

        /* renamed from: a  reason: collision with root package name */
        public String f19759a;
        public int b;
        public String c;
        public String d;
        public String e;
        public String f;
        public int g;
        public long h;
        public long i;
        public String j;
        public int k = 0;

        private ReceiveShareItem() {
        }

        public static ReceiveShareItem a(JSONObject jSONObject) {
            ReceiveShareItem receiveShareItem = new ReceiveShareItem();
            receiveShareItem.b = jSONObject.optInt("inv_id");
            receiveShareItem.c = jSONObject.optString("sender_name");
            receiveShareItem.d = jSONObject.optString("did");
            receiveShareItem.e = jSONObject.optString("device_name");
            receiveShareItem.f = jSONObject.optString("model");
            receiveShareItem.g = jSONObject.optInt("status", -1);
            receiveShareItem.h = jSONObject.optLong("ctime");
            receiveShareItem.i = jSONObject.optLong("expire_time");
            receiveShareItem.j = jSONObject.optString(CBConstant.SENDER);
            receiveShareItem.f19759a = jSONObject.optString("msgId");
            if (!jSONObject.isNull("isReadOnly")) {
                receiveShareItem.k = jSONObject.optBoolean("isReadOnly") ? 1 : 2;
            }
            return receiveShareItem;
        }

        /* renamed from: a */
        public int compareTo(ReceiveShareItem receiveShareItem) {
            if (receiveShareItem == null) {
                return 1;
            }
            if (this.h > receiveShareItem.h) {
                return -1;
            }
            if (this.h < receiveShareItem.h) {
                return 1;
            }
            return 0;
        }
    }

    public boolean onBackPressed() {
        if (!this.p) {
            return false;
        }
        e();
        return true;
    }

    public void onPageSelected() {
        if (this.j != null) {
            this.j.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ((ShareDeviceInfoActivity) ReceiveShareListFragment.this.getActivity()).setViewPagerSwipe(true);
                    ReceiveShareListFragment.this.e();
                }
            });
        }
        if (this.k != null) {
            this.k.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ReceiveShareListFragment.this.q.size() == ReceiveShareListFragment.this.b.size()) {
                        ReceiveShareListFragment.this.d();
                        StatHelper.d(false);
                        return;
                    }
                    ReceiveShareListFragment.this.c();
                    StatHelper.d(true);
                }
            });
        }
    }

    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        this.s = z;
        if (!z) {
            return;
        }
        if (getView() == null) {
            this.r = false;
            return;
        }
        onPageSelected();
        this.r = true;
    }
}
