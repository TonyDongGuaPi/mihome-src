package com.xiaomi.smarthome.notificationquickop;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.newui.dragsort.ItemTouchHelperAdapter;
import com.xiaomi.smarthome.notishortcut.ISmartNoti;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickOpDeviceSettingActivity extends BaseActivity implements View.OnClickListener {
    View.OnClickListener InnerClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int childPosition;
            if (QuickOpDeviceSettingActivity.this.f || QuickOpDeviceSettingActivity.this.mRecyclerView.getChildPosition(view) - 1 == -1 || childPosition >= QuickOpDeviceSettingActivity.this.c.size() || QuickOpDeviceSettingActivity.this.f) {
                return;
            }
            if (QuickOpDeviceSettingActivity.this.d.size() == 4) {
                ToastUtil.a((int) R.string.choose_most_four);
                return;
            }
            QuickOpDeviceSettingActivity.this.d.add(QuickOpDeviceSettingActivity.this.c.get(childPosition));
            QuickOpDeviceSettingActivity.this.f20968a.add(((ViewItem) QuickOpDeviceSettingActivity.this.c.get(childPosition)).f20985a);
            QuickOpDeviceSettingActivity.this.c.remove(childPosition);
            QuickOpDeviceSettingActivity.this.mResultAdapter.notifyDataSetChanged();
            QuickOpDeviceSettingActivity.this.mAdapter.notifyDataSetChanged();
            QuickOpDeviceSettingActivity.this.c();
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Set<String> f20968a = new HashSet();
    @BindView(2131427681)
    View animBody;
    /* access modifiers changed from: private */
    public List<ViewItem> b = new ArrayList();
    @BindView(2131430969)
    ImageView backBtn;
    /* access modifiers changed from: private */
    public List<ViewItem> c = new ArrayList();
    /* access modifiers changed from: private */
    public List<ViewItem> d = new ArrayList();
    /* access modifiers changed from: private */
    public boolean e = false;
    @BindView(2131428996)
    View emptyDevideLine;
    @BindView(2131428501)
    TextView emptyText;
    @BindView(2131428503)
    View emptyView;
    /* access modifiers changed from: private */
    public boolean f = false;
    private BroadcastReceiver g = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals("com.xiaomi.smarthome.notishortcut.notification.save_success", action)) {
                boolean unused = QuickOpDeviceSettingActivity.this.f = false;
            } else if (TextUtils.equals("com.xiaomi.smarthome.notishortcut.notification.save_fail", action)) {
                boolean unused2 = QuickOpDeviceSettingActivity.this.f = false;
            }
        }
    };
    InnerAdapter mAdapter;
    SmartNotiApi.ConfigReadyCallback mConfigReadyCallback = new SmartNotiApi.ConfigReadyCallback() {
        public void a(final ISmartNoti iSmartNoti) {
            if (QuickOpDeviceSettingActivity.this.isValid()) {
                QuickOpDeviceSettingActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        List<String> list;
                        List<Device> d = SmartHomeDeviceManager.a().d();
                        if (d == null || d.size() == 0) {
                            QuickOpDeviceSettingActivity.this.d();
                            return;
                        }
                        QuickOpDeviceSettingActivity.this.emptyView.setVisibility(8);
                        QuickOpDeviceSettingActivity.this.emptyDevideLine.setVisibility(8);
                        try {
                            boolean unused = QuickOpDeviceSettingActivity.this.e = iSmartNoti.getSettingSwitch();
                            list = QuickOpDeviceSettingActivity.this.a(iSmartNoti.getDeviceSettingFromFile());
                        } catch (RemoteException unused2) {
                            LogUtil.b("QuickOp", "RemoteException!!! ");
                            list = null;
                        }
                        if (list != null) {
                            QuickOpDeviceSettingActivity.this.f20968a.addAll(list);
                        }
                        if (QuickOpDeviceSettingActivity.this.f20968a == null || QuickOpDeviceSettingActivity.this.f20968a.size() <= 0) {
                            QuickOpDeviceSettingActivity.this.a(d);
                        } else {
                            QuickOpDeviceSettingActivity.this.b.clear();
                            QuickOpDeviceSettingActivity.this.d.clear();
                            QuickOpDeviceSettingActivity.this.c.clear();
                            for (String str : list) {
                                ViewItem viewItem = new ViewItem();
                                viewItem.f20985a = str;
                                QuickOpDeviceSettingActivity.this.d.add(viewItem);
                            }
                            QuickOpDeviceSettingActivity.this.b(d);
                        }
                        if (QuickOpDeviceSettingActivity.this.b == null || QuickOpDeviceSettingActivity.this.b.size() == 0) {
                            QuickOpDeviceSettingActivity.this.d();
                            return;
                        }
                        QuickOpDeviceSettingActivity.this.c();
                        QuickOpDeviceSettingActivity.this.mAdapter.notifyDataSetChanged();
                        QuickOpDeviceSettingActivity.this.mResultAdapter.notifyDataSetChanged();
                        QuickOpDeviceSettingActivity.this.switchBtn.setChecked(QuickOpDeviceSettingActivity.this.e);
                        QuickOpDeviceSettingActivity.this.a(QuickOpDeviceSettingActivity.this.e, false);
                    }
                });
            }
        }
    };
    ItemTouchHelper.Callback mItemTouchHelperCallback = new ItemTouchHelper.Callback() {
        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(12, 0);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
                return false;
            }
            QuickOpDeviceSettingActivity.this.mResultAdapter.a(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
            return true;
        }
    };
    @BindView(2131431792)
    RecyclerView mRecyclerView;
    ResultAdapter mResultAdapter;
    @BindView(2131431794)
    RecyclerView mResultView;
    @BindView(2131432280)
    View mSelectTip;
    @BindView(2131433015)
    View mTopPanel;
    @BindView(2131433017)
    View mTopSpace;
    View.OnClickListener resultClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int childPosition;
            if (QuickOpDeviceSettingActivity.this.f || (childPosition = QuickOpDeviceSettingActivity.this.mResultView.getChildPosition(view)) == -1 || childPosition >= QuickOpDeviceSettingActivity.this.d.size() || QuickOpDeviceSettingActivity.this.f) {
                return;
            }
            if (QuickOpDeviceSettingActivity.this.d.size() == 1) {
                ToastUtil.a((int) R.string.choose_least_one);
                return;
            }
            QuickOpDeviceSettingActivity.this.f20968a.remove(((ViewItem) QuickOpDeviceSettingActivity.this.d.get(childPosition)).f20985a);
            QuickOpDeviceSettingActivity.this.d.remove(childPosition);
            QuickOpDeviceSettingActivity.this.e();
            QuickOpDeviceSettingActivity.this.mResultAdapter.notifyDataSetChanged();
            QuickOpDeviceSettingActivity.this.mAdapter.notifyDataSetChanged();
            QuickOpDeviceSettingActivity.this.c();
        }
    };
    @BindView(2131432697)
    SwitchButton switchBtn;
    @BindView(2131432698)
    View switchView;
    @BindView(2131430975)
    TextView tittle;
    @BindView(2131432919)
    View topBar;

    class InnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        int f20976a = 0;
        int b = 1;
        int c;
        int d;

        public class Holder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private Holder f20980a;

            @UiThread
            public Holder_ViewBinding(Holder holder, View view) {
                this.f20980a = holder;
                holder.txtDeviceName = (TextView) Utils.findRequiredViewAsType(view, R.id.txt_device_name, "field 'txtDeviceName'", TextView.class);
                holder.chooseDevice = (ImageView) Utils.findRequiredViewAsType(view, R.id.choose_device, "field 'chooseDevice'", ImageView.class);
                holder.imgDevice = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.img_device, "field 'imgDevice'", SimpleDraweeView.class);
                holder.rootView = Utils.findRequiredView(view, R.id.item_view, "field 'rootView'");
            }

            @CallSuper
            public void unbind() {
                Holder holder = this.f20980a;
                if (holder != null) {
                    this.f20980a = null;
                    holder.txtDeviceName = null;
                    holder.chooseDevice = null;
                    holder.imgDevice = null;
                    holder.rootView = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public InnerAdapter() {
            float f = QuickOpDeviceSettingActivity.this.getContext().getResources().getDisplayMetrics().density;
            this.c = (int) (16.0f * f);
            this.d = (int) (f * 6.0f);
        }

        public int getItemViewType(int i) {
            if (i == 0) {
                return this.f20976a;
            }
            return this.b;
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    public int getSpanSize(int i) {
                        return i == 0 ? 4 : 1;
                    }
                });
            }
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (this.f20976a == i) {
                return new HeaderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_quickop_setting, viewGroup, false));
            }
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quickop_setting, viewGroup, false));
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (this.f20976a != getItemViewType(i)) {
                Holder holder = (Holder) viewHolder;
                int i2 = i % 4;
                if (i2 == 1) {
                    holder.rootView.setPadding(this.c, this.d, this.d, this.d);
                } else if (i2 == 0) {
                    holder.rootView.setPadding(this.d, this.d, this.c, this.d);
                } else {
                    holder.rootView.setPadding(this.d, this.d, this.d, this.d);
                }
                holder.rootView.setOnClickListener(QuickOpDeviceSettingActivity.this.InnerClickListener);
                ViewItem viewItem = (ViewItem) QuickOpDeviceSettingActivity.this.c.get(i - 1);
                holder.txtDeviceName.setText(viewItem.b);
                holder.txtDeviceName.setTextColor(QuickOpDeviceSettingActivity.this.getResources().getColor(R.color.std_list_title));
                holder.chooseDevice.setImageResource(R.drawable.btn_noti_add);
                holder.chooseDevice.setVisibility(0);
                if (!TextUtils.isEmpty(viewItem.c)) {
                    PluginRecord d2 = CoreApi.a().d(viewItem.c);
                    if (d2 == null || TextUtils.isEmpty(d2.q())) {
                        holder.imgDevice.setImageURI(CommonUtils.c((int) R.drawable.ic_noti_defualt_on));
                    } else {
                        holder.imgDevice.setImageURI(Uri.parse(d2.q()));
                    }
                } else {
                    holder.imgDevice.setImageURI(CommonUtils.c((int) R.drawable.ic_noti_defualt_on));
                }
            }
        }

        public int getItemCount() {
            return QuickOpDeviceSettingActivity.this.c.size() + 1;
        }

        class Holder extends RecyclerView.ViewHolder {
            @BindView(2131428337)
            ImageView chooseDevice;
            @BindView(2131429810)
            SimpleDraweeView imgDevice;
            @BindView(2131430062)
            View rootView;
            @BindView(2131433573)
            TextView txtDeviceName;

            public Holder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }

        class HeaderHolder extends RecyclerView.ViewHolder {
            public HeaderHolder(View view) {
                super(view);
            }
        }
    }

    class ResultAdapter extends RecyclerView.Adapter<Holder> implements ItemTouchHelperAdapter {
        public void a() {
        }

        public void a(int i) {
        }

        public void a(int[] iArr) {
        }

        public void b() {
        }

        public int getItemCount() {
            return 4;
        }

        public class Holder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private Holder f20984a;

            @UiThread
            public Holder_ViewBinding(Holder holder, View view) {
                this.f20984a = holder;
                holder.txtDeviceName = (TextView) Utils.findRequiredViewAsType(view, R.id.txt_device_name, "field 'txtDeviceName'", TextView.class);
                holder.chooseDevice = (ImageView) Utils.findRequiredViewAsType(view, R.id.choose_device, "field 'chooseDevice'", ImageView.class);
                holder.imgDevice = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.img_device, "field 'imgDevice'", SimpleDraweeView.class);
                holder.rootView = Utils.findRequiredView(view, R.id.item_view, "field 'rootView'");
            }

            @CallSuper
            public void unbind() {
                Holder holder = this.f20984a;
                if (holder != null) {
                    this.f20984a = null;
                    holder.txtDeviceName = null;
                    holder.chooseDevice = null;
                    holder.imgDevice = null;
                    holder.rootView = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        ResultAdapter() {
        }

        /* renamed from: a */
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quickop_setting, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(Holder holder, int i) {
            if (i >= QuickOpDeviceSettingActivity.this.d.size()) {
                holder.txtDeviceName.setText("");
                holder.chooseDevice.setVisibility(8);
                holder.imgDevice.setImageURI(CommonUtils.c((int) R.drawable.ic_noti_place_holder));
                return;
            }
            holder.txtDeviceName.setText(((ViewItem) QuickOpDeviceSettingActivity.this.d.get(i)).b);
            holder.itemView.setOnClickListener(QuickOpDeviceSettingActivity.this.resultClickListener);
            holder.chooseDevice.setImageResource(R.drawable.btn_noti_del);
            holder.chooseDevice.setVisibility(0);
            if (!TextUtils.isEmpty(((ViewItem) QuickOpDeviceSettingActivity.this.d.get(i)).c)) {
                PluginRecord d = CoreApi.a().d(((ViewItem) QuickOpDeviceSettingActivity.this.d.get(i)).c);
                if (d == null || TextUtils.isEmpty(d.q())) {
                    holder.imgDevice.setImageURI(CommonUtils.c((int) R.drawable.ic_noti_defualt_on));
                } else {
                    holder.imgDevice.setImageURI(Uri.parse(d.q()));
                }
            } else {
                holder.imgDevice.setImageURI(CommonUtils.c((int) R.drawable.ic_noti_defualt_on));
            }
            DraweeController controller = holder.imgDevice.getController();
            if (controller != null && (controller instanceof AbstractDraweeController)) {
                ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                    public void onFailure(String str, Throwable th) {
                        Log.e("Failure", str + " decode failed");
                    }
                });
            }
        }

        public boolean a(int i, int i2) {
            if (i >= QuickOpDeviceSettingActivity.this.d.size() || i2 >= QuickOpDeviceSettingActivity.this.d.size()) {
                return false;
            }
            if (i < i2) {
                int i3 = i;
                while (i3 < i2) {
                    int i4 = i3 + 1;
                    Collections.swap(QuickOpDeviceSettingActivity.this.d, i3, i4);
                    i3 = i4;
                }
            } else {
                for (int i5 = i; i5 > i2; i5--) {
                    Collections.swap(QuickOpDeviceSettingActivity.this.d, i5, i5 - 1);
                }
            }
            notifyItemMoved(i, i2);
            QuickOpDeviceSettingActivity.this.c();
            return true;
        }

        public class Holder extends RecyclerView.ViewHolder {
            @BindView(2131428337)
            ImageView chooseDevice;
            @BindView(2131429810)
            SimpleDraweeView imgDevice;
            @BindView(2131430062)
            View rootView;
            @BindView(2131433573)
            TextView txtDeviceName;

            public Holder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_quickop_setting);
        ButterKnife.bind((Activity) this);
        LoginMiAccount y = CoreApi.a().y();
        if (y != null) {
            SmartNotiApi.a((Context) this).a(y.e());
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.smarthome.notishortcut.notification.save_success");
        intentFilter.addAction("com.xiaomi.smarthome.notishortcut.notification.save_fail");
        registerReceiver(this.g, intentFilter);
        b();
        f();
        SmartNotiApi.a((Context) this).a(this.mConfigReadyCallback);
        a();
    }

    private void a() {
        getSharedPreferences(NotiQuickOpManager.f20964a + MD5.a(CoreApi.a().s()), 0).edit().clear().commit();
        NotiQuickOpManager.a(this).b();
    }

    /* access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        if (z) {
            this.mRecyclerView.setVisibility(0);
            this.mTopPanel.setVisibility(0);
            this.mTopSpace.setVisibility(8);
            if (!z2) {
                this.tittle.setTextColor(getResources().getColor(R.color.white));
                this.topBar.setBackgroundColor(getResources().getColor(R.color.lite_device_name_close));
                return;
            }
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this.mTopPanel, "backgroundColor", new int[]{getResources().getColor(R.color.white), getResources().getColor(R.color.lite_device_name_close)});
            ofInt.setDuration(300);
            ofInt.setEvaluator(new ArgbEvaluator());
            ObjectAnimator ofInt2 = ObjectAnimator.ofInt(this.tittle, "textColor", new int[]{getResources().getColor(R.color.lite_device_name_close), getResources().getColor(R.color.white)});
            ofInt2.setDuration(300);
            ofInt2.setEvaluator(new ArgbEvaluator());
            ObjectAnimator ofInt3 = ObjectAnimator.ofInt(this.topBar, "backgroundColor", new int[]{getResources().getColor(R.color.white), getResources().getColor(R.color.lite_device_name_close)});
            ofInt3.setDuration(300);
            ofInt3.setEvaluator(new ArgbEvaluator());
            this.mTopPanel.measure(0, 0);
            this.mTopSpace.measure(0, 0);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.animBody, View.TRANSLATION_Y, new float[]{(float) (this.mTopSpace.getMeasuredHeight() - this.mTopPanel.getMeasuredHeight()), 0.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.play(ofFloat).with(ofInt).with(ofInt2).with(ofInt3);
            animatorSet.start();
            return;
        }
        this.mTopSpace.setVisibility(0);
        ObjectAnimator ofInt4 = ObjectAnimator.ofInt(this.mTopPanel, "backgroundColor", new int[]{getResources().getColor(R.color.lite_device_name_close), getResources().getColor(R.color.white)});
        ofInt4.setDuration(300);
        ofInt4.setEvaluator(new ArgbEvaluator());
        ObjectAnimator ofInt5 = ObjectAnimator.ofInt(this.tittle, "textColor", new int[]{getResources().getColor(R.color.white), getResources().getColor(R.color.lite_device_name_close)});
        ofInt5.setDuration(300);
        ofInt5.setEvaluator(new ArgbEvaluator());
        ObjectAnimator ofInt6 = ObjectAnimator.ofInt(this.topBar, "backgroundColor", new int[]{getResources().getColor(R.color.lite_device_name_close), getResources().getColor(R.color.white)});
        ofInt6.setDuration(300);
        ofInt6.setEvaluator(new ArgbEvaluator());
        this.mTopPanel.measure(0, 0);
        this.topBar.measure(0, 0);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.animBody, View.TRANSLATION_Y, new float[]{(float) this.mTopPanel.getHeight(), 0.0f});
        this.mRecyclerView.setVisibility(8);
        this.mTopPanel.setVisibility(8);
        if (!z2) {
            this.tittle.setTextColor(getResources().getColor(R.color.lite_device_name_close));
            this.topBar.setBackgroundColor(getResources().getColor(R.color.white));
            return;
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(300);
        animatorSet2.play(ofFloat2).with(ofInt4).with(ofInt5).with(ofInt6);
        animatorSet2.start();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.g);
    }

    private void b() {
        this.tittle.setText(R.string.notification_quick_op_title);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.b == null || this.b.size() == 0) {
            finish();
            return true;
        }
        if (this.e) {
            c();
        } else {
            SmartNotiApi.a((Context) this).b();
        }
        if (this.d == null || this.d.size() == 0) {
            ToastUtil.a((CharSequence) "至少选择一个设备");
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.e) {
            this.f = true;
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            JSONArray jSONArray3 = new JSONArray();
            for (ViewItem next : this.d) {
                JSONObject c2 = c(SmartHomeDeviceManager.a().b(next.f20985a));
                JSONObject b2 = b(SmartHomeDeviceManager.a().b(next.f20985a));
                if (!(c2 == null || b2 == null)) {
                    jSONArray.put(next.f20985a);
                    jSONArray2.put(c2);
                    jSONArray3.put(b2);
                }
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("list", jSONArray2);
                jSONObject.put("config", jSONArray3);
            } catch (JSONException unused) {
            }
            SmartNotiApi.a((Context) this).a(jSONObject.toString(), jSONArray.toString());
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    boolean unused = QuickOpDeviceSettingActivity.this.f;
                    boolean unused2 = QuickOpDeviceSettingActivity.this.f = false;
                }
            }, 10000);
        }
    }

    /* access modifiers changed from: private */
    public List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(str)) {
                return arrayList;
            }
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (JSONException e2) {
            LogUtil.b("QuickOp", "getSettingFromFile fail: " + e2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.e = false;
        this.emptyText.setText(R.string.no_supported_device_new);
        this.emptyView.setVisibility(0);
        this.mRecyclerView.setVisibility(8);
        this.emptyDevideLine.setVisibility(0);
        a(false, false);
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list) {
        this.b.clear();
        this.d.clear();
        this.c.clear();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (a(list.get(i2))) {
                ViewItem viewItem = new ViewItem();
                viewItem.f20985a = list.get(i2).did;
                viewItem.b = list.get(i2).name;
                viewItem.d = list.get(i2).isOnline;
                viewItem.c = list.get(i2).model;
                this.b.add(viewItem);
                if (i < 4) {
                    this.d.add(viewItem);
                    this.f20968a.add(viewItem.f20985a);
                    i++;
                } else {
                    this.c.add(viewItem);
                }
            }
        }
    }

    private boolean a(Device device) {
        return 1 == DeviceListSwitchManager.a().a(device);
    }

    private JSONObject b(Device device) {
        if (device == null || DeviceListSwitchManager.a().c() == null || DeviceListSwitchManager.a().c().get(device.model) == null || DeviceListSwitchManager.a().c().get(device.model).c() == null || DeviceListSwitchManager.a().c().get(device.model).c().length < 2) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", device.model);
            String c2 = DeviceListSwitchManager.a().c().get(device.model).c()[0].c();
            String c3 = DeviceListSwitchManager.a().c().get(device.model).c()[1].c();
            jSONObject.put("prop_on", c2);
            jSONObject.put("prop_off", c3);
            if (DeviceListSwitchManager.a().c().get(device.model).c()[0].a() != null) {
                JSONObject jSONObject2 = new JSONObject(DeviceListSwitchManager.a().c().get(device.model).c()[0].a().toString());
                if (jSONObject2.has("rpc_params")) {
                    jSONObject.put("prop_params_on", jSONObject2.optJSONArray("rpc_params").toString());
                }
            }
            if (DeviceListSwitchManager.a().c().get(device.model).c()[1].a() != null) {
                JSONObject jSONObject3 = new JSONObject(DeviceListSwitchManager.a().c().get(device.model).c()[1].a().toString());
                if (jSONObject3.has("rpc_params")) {
                    jSONObject.put("prop_params_off", jSONObject3.optJSONArray("rpc_params").toString());
                }
            }
            jSONObject.put("prop_name", DeviceListSwitchManager.a().c().get(device.model).c()[0].b());
            String d2 = DeviceListSwitchManager.a().c().get(device.model).c()[0].d();
            String d3 = DeviceListSwitchManager.a().c().get(device.model).c()[1].d();
            jSONObject.put("rpc_method_on", d2);
            jSONObject.put("rpc_method_off", d3);
        } catch (JSONException e2) {
            LogUtil.b("config_exception", e2.getMessage());
        }
        return jSONObject;
    }

    private JSONObject c(Device device) {
        if (device == null || DeviceListSwitchManager.a().c() == null || DeviceListSwitchManager.a().c().get(device.model) == null || DeviceListSwitchManager.a().c().get(device.model).c() == null || DeviceListSwitchManager.a().c().get(device.model).c().length < 2) {
            return null;
        }
        DeviceStat o = SmartHomeDeviceManager.a().o(device.did);
        PluginRecord d2 = CoreApi.a().d(device.model);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", device.did);
            jSONObject.put("name", device.name);
            jSONObject.put("model", device.model);
            jSONObject.put("is_online", device.isOnline);
            if (d2 != null) {
                if (!TextUtils.isEmpty(d2.q())) {
                    jSONObject.put("ic_on", d2.q());
                }
                if (!TextUtils.isEmpty(d2.r())) {
                    jSONObject.put("ic_off", d2.r());
                }
                if (!TextUtils.isEmpty(d2.s())) {
                    jSONObject.put("ic_offline", d2.s());
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            if (!(o == null || o.propInfo == null)) {
                LogUtil.a("rpc", device.model + "   :   " + o.propInfo.toString());
                if (!(DeviceListSwitchManager.a().c() == null || DeviceListSwitchManager.a().c().get(device.model) == null || DeviceListSwitchManager.a().c().get(device.model).c() == null || DeviceListSwitchManager.a().c().get(device.model).c().length <= 0)) {
                    String b2 = DeviceListSwitchManager.a().c().get(device.model).c()[0].b();
                    if (!TextUtils.isEmpty(b2) && !TextUtils.isEmpty(o.propInfo.optString(b2))) {
                        jSONObject2.put(b2, o.propInfo.optString(b2));
                    }
                }
            }
            jSONObject.put("props", jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public void b(List<Device> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (a(list.get(size))) {
                ViewItem viewItem = new ViewItem();
                viewItem.f20985a = list.get(size).did;
                viewItem.b = list.get(size).name;
                viewItem.d = list.get(size).isOnline;
                viewItem.c = list.get(size).model;
                this.b.add(viewItem);
                if (this.f20968a.contains(list.get(size).did)) {
                    int size2 = this.d.size() - 1;
                    while (true) {
                        if (size2 < 0) {
                            break;
                        } else if (TextUtils.equals(this.d.get(size2).f20985a, list.get(size).did)) {
                            this.d.get(size2).f20985a = viewItem.f20985a;
                            this.d.get(size2).e = viewItem.e;
                            this.d.get(size2).d = viewItem.d;
                            this.d.get(size2).c = viewItem.c;
                            this.d.get(size2).b = viewItem.b;
                            break;
                        } else {
                            size2--;
                        }
                    }
                } else {
                    this.c.add(viewItem);
                }
            }
        }
        for (int size3 = this.d.size() - 1; size3 >= 0; size3--) {
            if (TextUtils.isEmpty(this.d.get(size3).b)) {
                this.f20968a.remove(this.d.get(size3).f20985a);
                this.d.remove(size3);
            }
        }
        if (this.d == null || this.d.size() == 0) {
            this.c.clear();
            this.d.clear();
            a(list);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        this.c.clear();
        for (ViewItem next : this.b) {
            if (!this.f20968a.contains(next.f20985a)) {
                this.c.add(next);
            }
        }
    }

    private void f() {
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        this.mAdapter = new InnerAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mResultView.setHasFixedSize(true);
        this.mResultView.setLayoutManager(new GridLayoutManager(this, 4));
        this.mResultAdapter = new ResultAdapter();
        this.mResultView.setAdapter(this.mResultAdapter);
        new ItemTouchHelper(this.mItemTouchHelperCallback).attachToRecyclerView(this.mResultView);
        this.emptyView.setVisibility(0);
        this.emptyDevideLine.setVisibility(0);
        this.mRecyclerView.setVisibility(0);
        this.switchBtn.setOnTouchEnable(false);
        this.switchView.setOnClickListener(this);
    }

    @OnClick({2131430969})
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.module_a_3_return_btn) {
            if (id == R.id.switch_setting_view) {
                this.e = !this.e;
                this.switchBtn.setChecked(this.e);
                if (this.e) {
                    STAT.d.c(1);
                } else {
                    STAT.d.c(2);
                }
                a(this.e, true);
                if (this.e) {
                    c();
                } else {
                    SmartNotiApi.a((Context) this).b();
                }
            }
        } else if (this.b == null || this.b.size() == 0) {
            finish();
        } else {
            c();
            if (this.d == null || this.d.size() == 0) {
                ToastUtil.a((int) R.string.choose_least_one);
            } else {
                finish();
            }
        }
    }

    private static class ViewItem {

        /* renamed from: a  reason: collision with root package name */
        String f20985a;
        String b;
        String c;
        boolean d;
        boolean e;

        private ViewItem() {
            this.d = false;
            this.e = true;
        }
    }
}
