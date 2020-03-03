package com.xiaomi.smarthome.light.group;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.BleMeshFirmwareUpdateInfoV2;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.update.ui.UpdateActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.light.group.LightGroupManageActivity;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import miuipub.view.EditActionMode;

public class LightGroupManageActivity extends BaseActivity {
    public static final String ARGS_KEY_UPGRADE_ONLY = "args_key_upgrade_only";
    public static final int EDIT_REQUEST_CODE = 101;

    /* renamed from: a  reason: collision with root package name */
    private static final String f19137a = "LightGroupManageActivity";
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public List<Device> c = new ArrayList();
    /* access modifiers changed from: private */
    public LightGroupAdapter d;
    /* access modifiers changed from: private */
    public Device e;
    private XQProgressDialog f;
    /* access modifiers changed from: private */
    public AtomicBoolean g = new AtomicBoolean(false);
    private boolean h;
    /* access modifiers changed from: private */
    public View i;
    /* access modifiers changed from: private */
    public AnimateLinearLayout j;
    /* access modifiers changed from: private */
    public boolean k = false;
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3 && SmartHomeDeviceManager.a().b(LightGroupManageActivity.this.b) != null) {
                SmartHomeDeviceManager.a().c(LightGroupManageActivity.this.listener);
                List unused = LightGroupManageActivity.this.c = SmartHomeDeviceManager.a().d(LightGroupManageActivity.this.b);
                List unused2 = LightGroupManageActivity.this.d.c = LightGroupManageActivity.this.c;
                LightGroupManageActivity.this.exitEditMode();
            }
        }
    };
    protected TextView mTxtEditTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_group_manage);
        this.b = getIntent().getStringExtra("did");
        this.h = getIntent().getBooleanExtra(ARGS_KEY_UPGRADE_ONLY, false);
        if (TextUtils.isEmpty(this.b)) {
            finish();
            return;
        }
        this.e = SmartHomeDeviceManager.a().b(this.b);
        if (this.e == null) {
            finish();
            return;
        }
        a();
        b();
        if (this.h) {
            STAT.c.q(this.e.model);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (101 == i2 && i3 == -1 && intent != null) {
            List<Device> d2 = SmartHomeDeviceManager.a().d(this.b);
            if (d2 != null) {
                List unused = this.d.c = d2;
                this.d.notifyDataSetChanged();
            }
            this.c = d2;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        hideLoadingDialog();
        SmartHomeDeviceManager.a().c(this.listener);
        this.g.set(false);
    }

    private void a() {
        this.c = SmartHomeDeviceManager.a().d(this.b);
    }

    private void b() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        textView.setText(R.string.light_group_manager);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupManageActivity.this.f(view);
            }
        });
        findViewById(R.id.ota_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupManageActivity.this.e(view);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_right_btn);
        imageView.setImageResource(R.drawable.home_icon_add_gray);
        imageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupManageActivity.this.d(view);
            }
        });
        if (this.h) {
            imageView.setVisibility(8);
            textView.setText(R.string.device_more_activity_firmware_update);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        this.d = new LightGroupAdapter(this, this.c);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.d);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        onBackPressed();
        STAT.d.bl(this.e.model);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        Intent intent = new Intent(this, LightGroupSettingActivity.class);
        intent.putExtra("did", this.b);
        intent.putExtra(LightGroupSettingActivity.ARGS_KEY_CREATE_MODE, false);
        intent.putExtra(LightGroupSettingActivity.ARGS_KEY_GROUP_MODEL, this.e.model);
        startActivityForResult(intent, 101);
    }

    public void onBackPressed() {
        if (this.k) {
            exitEditMode();
        } else {
            finish();
        }
    }

    private void a(List<String> list) {
        new MLAlertDialog.Builder(this).a((int) R.string.light_group_child_delete).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(list) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                LightGroupManageActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                LightGroupManageActivity.this.c(dialogInterface, i);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, DialogInterface dialogInterface, int i2) {
        DeviceApi.getInstance().modLightGroup(this, this.b, new ArrayList(), list, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                SmartHomeDeviceManager.a().a(LightGroupManageActivity.this.listener);
                SmartHomeDeviceManager.a().p();
                LightGroupManageActivity.this.hideLoadingDialog();
            }

            public void onFailure(Error error) {
                ToastUtil.a((CharSequence) "Error: " + error.b());
                LightGroupManageActivity.this.hideLoadingDialog();
            }
        }, new boolean[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
        ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
        hideLoadingDialog();
    }

    private void c() {
        new MLAlertDialog.Builder(this).a((int) R.string.light_group_group_delete).b((int) R.string.light_group_group_delete_desc).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                LightGroupManageActivity.this.b(dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                LightGroupManageActivity.this.a(dialogInterface, i);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b);
        SmartHomeDeviceManager.a().a((List<String>) arrayList, (Context) this, (SmartHomeDeviceManager.IDelDeviceBatchCallback) new SmartHomeDeviceManager.IDelDeviceBatchCallback() {
            public void a() {
                LightGroupManageActivity.this.finish();
                LightGroupManageActivity.this.overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
                Intent intent = new Intent(LightGroupManageActivity.this.getApplicationContext(), SmartHomeMainActivity.class);
                intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                LightGroupManageActivity.this.startActivity(intent);
                LightGroupManageActivity.this.hideLoadingDialog();
            }

            public void a(Error error) {
                ToastUtil.a((CharSequence) "Error: " + error.b());
                LightGroupManageActivity.this.hideLoadingDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
        hideLoadingDialog();
    }

    public void enterEditMode() {
        if (!this.k && this.d.getItemCount() >= 1) {
            try {
                if (this.i == null) {
                    this.i = ((ViewStub) findViewById(R.id.title_bar_choose_device_stub)).inflate();
                    this.i.findViewById(EditActionMode.f3057a).setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            LightGroupManageActivity.this.c(view);
                        }
                    });
                    this.i.findViewById(EditActionMode.b).setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            LightGroupManageActivity.this.b(view);
                        }
                    });
                    this.mTxtEditTitle = (TextView) this.i.findViewById(R.id.module_a_4_return_more_title);
                    TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
                }
                if (this.j == null) {
                    this.j = (AnimateLinearLayout) ((ViewStub) findViewById(R.id.edit_action_bar_stub)).inflate();
                    this.j.findViewById(R.id.btn_edit_sort).setVisibility(8);
                    ((Button) this.j.findViewById(R.id.btn_edit_delete)).setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            LightGroupManageActivity.this.a(view);
                        }
                    });
                }
                this.i.setVisibility(0);
                this.j.setTranslationY(0.0f);
                this.j.setVisibility(0);
                this.j.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(this.j.getChildCount()));
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i, View.Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(200);
                animatorSet.play(ofFloat);
                animatorSet.start();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.k = true;
            this.d.d.clear();
            this.d.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        exitEditMode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        this.d.a(this.d.d.size() < this.d.a());
        this.d.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        ArrayList arrayList = new ArrayList(this.d.d);
        if (arrayList.size() > 0 && arrayList.size() == this.d.c.size()) {
            showLoadingDialog();
            c();
        } else if (arrayList.size() > 0 && arrayList.size() < this.d.c.size()) {
            showLoadingDialog();
            a((List<String>) arrayList);
        }
    }

    public void exitEditMode() {
        if (this.k) {
            ViewGroup viewGroup = (ViewGroup) this.j.getParent();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i, View.Y, new float[]{0.0f, (float) (-this.i.getHeight())});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.j, View.Y, new float[]{(float) (viewGroup.getHeight() - this.j.getHeight()), (float) viewGroup.getHeight()});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200);
            animatorSet.play(ofFloat).with(ofFloat2);
            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    LightGroupManageActivity.this.i.clearAnimation();
                    LightGroupManageActivity.this.j.clearAnimation();
                    LightGroupManageActivity.this.i.setVisibility(8);
                    LightGroupManageActivity.this.j.setVisibility(8);
                }
            });
            this.k = false;
            this.d.d.clear();
            this.d.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (i2 > 0) {
            try {
                this.mTxtEditTitle.setText(getResources().getQuantityString(R.plurals.edit_choosed_device, i2, new Object[]{Integer.valueOf(i2)}));
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            this.mTxtEditTitle.setText(R.string.title_choose_device);
        }
        this.mTxtEditTitle.setTypeface((Typeface) null, 0);
        if (i2 >= this.d.a()) {
            ((ImageView) this.i.findViewById(EditActionMode.b)).setImageResource(R.drawable.un_select_selector);
        } else {
            ((ImageView) this.i.findViewById(EditActionMode.b)).setImageResource(R.drawable.all_select_selector);
        }
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (isValid()) {
            this.f = new XQProgressDialog(this);
            this.f.setMessage(getString(R.string.device_more_security_loading_operation));
            this.f.show();
            this.f.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public final void onCancel(DialogInterface dialogInterface) {
                    LightGroupManageActivity.this.a(dialogInterface);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface) {
        this.g.set(false);
    }

    /* access modifiers changed from: protected */
    public void hideLoadingDialog() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    private void d() {
        if (this.c.size() > 0) {
            ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            for (Device next : this.c) {
                if (next != null) {
                    DeviceCategory fromPid = DeviceCategory.fromPid(next.pid);
                    if (fromPid == DeviceCategory.BleMesh || fromPid == DeviceCategory.Bluetooth) {
                        arrayList.add(next.did);
                    } else {
                        arrayList2.add(new KeyValuePair(next.did, next.model));
                    }
                }
            }
            showLoadingDialog();
            this.g.set(true);
            LightGroupManager.a().a(this.e.model, arrayList, new Callback<List<BleMeshFirmwareUpdateInfoV2>>() {
                /* renamed from: a */
                public void onSuccess(List<BleMeshFirmwareUpdateInfoV2> list) {
                    if (LightGroupManageActivity.this.g.get()) {
                        if (list.size() > 0) {
                            LightGroupMemberUpdateActivity.open(LightGroupManageActivity.this, list, arrayList2);
                            LightGroupManageActivity.this.hideLoadingDialog();
                        } else if (arrayList2.size() > 0) {
                            LightGroupManageActivity.this.hideLoadingDialog();
                            UpdateActivity.start(LightGroupManageActivity.this, arrayList2);
                        } else {
                            ToastUtil.a((int) R.string.no_update);
                            LightGroupManageActivity.this.hideLoadingDialog();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    ToastUtil.a((CharSequence) str);
                    LightGroupManageActivity.this.hideLoadingDialog();
                    if (arrayList2.size() > 0) {
                        UpdateActivity.start(LightGroupManageActivity.this, arrayList2);
                    }
                }
            });
            return;
        }
        ToastUtil.a((int) R.string.no_update);
    }

    public class LightGroupAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LayoutInflater b;
        /* access modifiers changed from: private */
        public List<Device> c;
        /* access modifiers changed from: private */
        public Set<String> d = new LinkedHashSet();

        public LightGroupAdapter(Context context, List<Device> list) {
            this.b = LayoutInflater.from(context);
            this.c = list;
        }

        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(this.b.inflate(R.layout.item_light_group, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            Device device = this.c.get(i);
            if (device != null && myViewHolder != null) {
                myViewHolder.e.setVisibility(LightGroupManageActivity.this.k ? 0 : 8);
                myViewHolder.e.setEnabled(device.isOnline);
                myViewHolder.b.setText(device.name);
                myViewHolder.c.setText(HomeManager.a().r(LightGroupManageActivity.this.e.did) + "  " + device.getStatusDescription(LightGroupManageActivity.this));
                DeviceFactory.b(device.model, myViewHolder.d);
                if (this.d != null) {
                    myViewHolder.e.setChecked(this.d.contains(device.did));
                }
                myViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(myViewHolder) {
                    private final /* synthetic */ LightGroupManageActivity.MyViewHolder f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        LightGroupManageActivity.LightGroupAdapter.this.a(this.f$1, compoundButton, z);
                    }
                });
                if (myViewHolder.e.getVisibility() == 0) {
                    myViewHolder.f19144a.setOnClickListener(myViewHolder.e.isEnabled() ? new View.OnClickListener() {
                        public final void onClick(View view) {
                            LightGroupManageActivity.MyViewHolder.this.e.performClick();
                        }
                    } : null);
                    myViewHolder.f19144a.setOnLongClickListener((View.OnLongClickListener) null);
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(MyViewHolder myViewHolder, CompoundButton compoundButton, boolean z) {
            if (!this.d.contains(myViewHolder.e.getTag())) {
                a(myViewHolder.getLayoutPosition(), z);
            } else {
                a(myViewHolder.getLayoutPosition(), z);
            }
        }

        public int getItemCount() {
            return this.c.size();
        }

        private void a(int i, boolean z) {
            try {
                Device device = this.c.get(i);
                if (device == null) {
                    return;
                }
                if (device.isOnline) {
                    if (z) {
                        this.d.add(device.did);
                    } else {
                        this.d.remove(device.did);
                    }
                    LightGroupManageActivity.this.a(this.d.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int a() {
            int i = 0;
            for (Device device : this.c) {
                if (device.isOnline) {
                    i++;
                }
            }
            return i;
        }

        public void a(boolean z) {
            for (int i = 0; i < this.c.size(); i++) {
                if (this.c.get(i).isOnline) {
                    a(i, z);
                }
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19144a;
        TextView b;
        TextView c;
        SimpleDraweeView d;
        CheckBox e;

        public MyViewHolder(View view) {
            super(view);
            this.f19144a = view;
            this.b = (TextView) view.findViewById(R.id.title);
            this.c = (TextView) view.findViewById(R.id.desc);
            this.d = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.e = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
}
