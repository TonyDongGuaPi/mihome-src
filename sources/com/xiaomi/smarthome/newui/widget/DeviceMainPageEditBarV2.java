package com.xiaomi.smarthome.newui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeRoomDeviceMoveActivity;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.library.safejson.JSONArraySafe;
import com.xiaomi.smarthome.library.safejson.JSONObjectSafe;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.light.group.LightGroupSettingV2Activity;
import com.xiaomi.smarthome.newui.DeviceListPageActionInterface;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.MyScaleAnimation;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.widget.DeviceMainPageEditBarV2;
import com.xiaomi.smarthome.share.ShareActivity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;

public class DeviceMainPageEditBarV2 extends AnimateLinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public LinearLayout f20840a = ((LinearLayout) findViewById(R.id.first_line));
    private Button b = ((Button) findViewById(R.id.btn_edit_move));
    private Button c = ((Button) findViewById(R.id.btn_edit_share));
    private Button d = ((Button) findViewById(R.id.btn_edit_rename));
    private Button e = ((Button) findViewById(R.id.btn_edit_shortcut));
    private Button f = ((Button) findViewById(R.id.btn_edit_delete));
    private Button g = ((Button) findViewById(R.id.btn_light_group));
    /* access modifiers changed from: private */
    public DeviceListPageActionInterface h;
    /* access modifiers changed from: private */
    public XQProgressDialog i;
    /* access modifiers changed from: private */
    public OnMenuBarPositionChangeListener j;

    public interface OnMenuBarPositionChangeListener {
        void a(int i);
    }

    public DeviceMainPageEditBarV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R.layout.device_main_page_edit_bar_v3, this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    private void a() {
        this.b.setEnabled(false);
        this.e.setEnabled(false);
        this.c.setEnabled(false);
        this.f.setEnabled(false);
        this.d.setEnabled(false);
        this.g.setEnabled(false);
    }

    public void setDeviceMainPage(DeviceListPageActionInterface deviceListPageActionInterface) {
        this.h = deviceListPageActionInterface;
    }

    public void enterEditMode(final DviceEditInterface dviceEditInterface) {
        a();
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DeviceMainPageEditBarV2.this.h != null && DeviceMainPageEditBarV2.this.h.getActivity() != null) {
                    DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(dviceEditInterface) {
                        private final /* synthetic */ DviceEditInterface f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            DeviceMainPageEditBarV2.AnonymousClass1.this.a(this.f$1);
                        }
                    });
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(DviceEditInterface dviceEditInterface) {
                Set<String> l = dviceEditInterface.l();
                if (l != null && !l.isEmpty()) {
                    ArrayList arrayList = new ArrayList(l);
                    Intent intent = new Intent(DeviceMainPageEditBarV2.this.h.getActivity(), HomeRoomDeviceMoveActivity.class);
                    intent.putStringArrayListExtra(HomeRoomDeviceMoveActivity.KEY_CHECKED_DIDS, arrayList);
                    DeviceMainPageEditBarV2.this.h.exitEditMode();
                    DeviceMainPageEditBarV2.this.h.getActivity().startActivityForResult(intent, DeviceMainPage.q);
                    STAT.d.c("2", dviceEditInterface.q());
                }
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void a(DviceEditInterface dviceEditInterface) {
                DeviceMainPageEditBarV2.this.c(dviceEditInterface);
            }

            public void onClick(View view) {
                DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(dviceEditInterface) {
                    private final /* synthetic */ DviceEditInterface f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DeviceMainPageEditBarV2.AnonymousClass2.this.a(this.f$1);
                    }
                });
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(dviceEditInterface) {
                    private final /* synthetic */ DviceEditInterface f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DeviceMainPageEditBarV2.AnonymousClass3.this.a(this.f$1);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(DviceEditInterface dviceEditInterface) {
                DeviceMainPageEditBarV2.this.a(dviceEditInterface);
                STAT.d.c("5", dviceEditInterface.q());
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void a(DviceEditInterface dviceEditInterface) {
                DeviceMainPageEditBarV2.this.b(dviceEditInterface);
            }

            public void onClick(View view) {
                DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(dviceEditInterface) {
                    private final /* synthetic */ DviceEditInterface f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DeviceMainPageEditBarV2.AnonymousClass4.this.a(this.f$1);
                    }
                });
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void a(DviceEditInterface dviceEditInterface) {
                DeviceMainPageEditBarV2.this.d(dviceEditInterface);
            }

            public void onClick(View view) {
                DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(dviceEditInterface) {
                    private final /* synthetic */ DviceEditInterface f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DeviceMainPageEditBarV2.AnonymousClass5.this.a(this.f$1);
                    }
                });
            }
        });
        a(0, AnimateLinearLayout.calcStepDelay(this.f20840a.getChildCount()));
    }

    /* access modifiers changed from: private */
    public void a(View view, final Runnable runnable) {
        MyScaleAnimation myScaleAnimation = new MyScaleAnimation(1.1f, 1, 0.5f, 1, 0.5f);
        myScaleAnimation.setDuration(100);
        myScaleAnimation.setInterpolator(new LinearInterpolator());
        myScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                runnable.run();
            }
        });
        view.startAnimation(myScaleAnimation);
    }

    public void exitEditMode() {
        clearAnimation();
        setVisibility(8);
        if (this.j != null) {
            this.j.a(0);
        }
    }

    public void updateActionItems(final DviceEditInterface dviceEditInterface, String str, boolean z) {
        try {
            Set l = dviceEditInterface.l();
            if (l == null) {
                l = new HashSet();
            }
            ArrayList arrayList = new ArrayList(l);
            if (arrayList.size() < 1) {
                this.b.setEnabled(false);
                this.e.setEnabled(false);
                this.c.setEnabled(false);
                this.f.setEnabled(false);
                this.d.setEnabled(false);
                this.g.setEnabled(false);
            } else if (arrayList.size() == 1) {
                if (!l.contains(str)) {
                    Iterator it = l.iterator();
                    if (it.hasNext()) {
                        str = (String) it.next();
                    }
                }
                final Device b2 = SmartHomeDeviceManager.a().b(str);
                if (b2 != null) {
                    this.b.setEnabled(a((List<String>) arrayList));
                    this.e.setEnabled(!MultiHomeDeviceManager.a(b2));
                    this.f.setEnabled(true);
                    this.c.setEnabled(z);
                    this.d.setEnabled(b2.canRename());
                    this.g.setEnabled(false);
                    this.c.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(b2, dviceEditInterface) {
                                private final /* synthetic */ Device f$1;
                                private final /* synthetic */ DviceEditInterface f$2;

                                {
                                    this.f$1 = r2;
                                    this.f$2 = r3;
                                }

                                public final void run() {
                                    DeviceMainPageEditBarV2.AnonymousClass7.this.a(this.f$1, this.f$2);
                                }
                            });
                        }

                        /* access modifiers changed from: private */
                        public /* synthetic */ void a(Device device, DviceEditInterface dviceEditInterface) {
                            DeviceRenderer.b(DeviceMainPageEditBarV2.this.getContext(), device);
                            DeviceMainPageEditBarV2.this.h.exitEditMode();
                            DeviceMainPageEditBarV2.this.logDeviceEditEvent("DevicelistEditShare");
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(device);
                            StatHelper.a((List<Device>) arrayList);
                            STAT.d.c("4", dviceEditInterface.q());
                        }
                    });
                }
            } else if (arrayList.size() >= 2) {
                this.b.setEnabled(a((List<String>) arrayList));
                this.f.setEnabled(true);
                this.d.setEnabled(false);
                this.e.setEnabled(false);
                this.c.setEnabled(z);
                this.g.setEnabled(b((List<String>) arrayList));
                this.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        DeviceMainPageEditBarV2.this.a(view, (Runnable) new Runnable(dviceEditInterface) {
                            private final /* synthetic */ DviceEditInterface f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                DeviceMainPageEditBarV2.AnonymousClass8.this.a(this.f$1);
                            }
                        });
                    }

                    /* access modifiers changed from: private */
                    public /* synthetic */ void a(DviceEditInterface dviceEditInterface) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (String next : dviceEditInterface.l()) {
                            Device b2 = SmartHomeDeviceManager.a().b(next);
                            if (b2 != null && b2.canBeShared()) {
                                arrayList.add(next);
                                arrayList2.add(b2);
                            }
                        }
                        if (arrayList.size() > 0) {
                            Intent intent = new Intent();
                            intent.setClass(DeviceMainPageEditBarV2.this.getContext(), DeviceShortcutUtils.a());
                            Bundle bundle = new Bundle();
                            bundle.putString("user_id", CoreApi.a().s());
                            bundle.putStringArrayList(ShareActivity.ARGS_KEY_BATCH_DIDS, arrayList);
                            intent.putExtras(bundle);
                            DeviceMainPageEditBarV2.this.getContext().startActivity(intent);
                        }
                        DeviceMainPageEditBarV2.this.h.exitEditMode();
                        DeviceMainPageEditBarV2.this.logDeviceEditEvent("DevicelistEditShareBatch");
                        StatHelper.a((List<Device>) arrayList2);
                        STAT.d.c("4", dviceEditInterface.q());
                    }
                });
            }
            if (dviceEditInterface.q() == 2) {
                this.f.setEnabled(false);
                this.e.setEnabled(false);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void a(final DviceEditInterface dviceEditInterface) {
        if (CommonUtils.s()) {
            Toast.makeText(SHApplication.getAppContext(), R.string.smarthome_device_mijia_cant_delete, 0).show();
            return;
        }
        ArrayList arrayList = new ArrayList(dviceEditInterface.l());
        if (!arrayList.isEmpty()) {
            final JSONArraySafe jSONArraySafe = new JSONArraySafe();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Device b2 = SmartHomeDeviceManager.a().b((String) arrayList.get(i2));
                if (b2 != null) {
                    JSONObjectSafe jSONObjectSafe = new JSONObjectSafe();
                    try {
                        jSONObjectSafe.put("model", (Object) b2.model);
                        jSONArraySafe.put((Object) jSONObjectSafe);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(getContext());
            builder.b((CharSequence) getResources().getQuantityString(R.plurals.dialog_title_confirm_delete_device, dviceEditInterface.m(), new Object[]{Integer.valueOf(dviceEditInterface.m())}));
            builder.a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    XQProgressDialog unused = DeviceMainPageEditBarV2.this.i = new XQProgressDialog(DeviceMainPageEditBarV2.this.getContext());
                    DeviceMainPageEditBarV2.this.i.setMessage(DeviceMainPageEditBarV2.this.getContext().getString(R.string.smarthome_deleting));
                    DeviceMainPageEditBarV2.this.i.setCancelable(true);
                    DeviceMainPageEditBarV2.this.i.show();
                    ArrayList arrayList = new ArrayList();
                    final ArrayList arrayList2 = new ArrayList(dviceEditInterface.l());
                    if (arrayList2.size() <= 0 || arrayList2.size() > 20) {
                        DeviceMainPageEditBarV2.this.h.onStateChanged();
                        DeviceMainPageEditBarV2.this.i.dismiss();
                        ToastUtil.a((int) R.string.deldevicebatch_warring);
                    } else {
                        if (dviceEditInterface.q() == 4) {
                            HomeManager.a().a(dviceEditInterface.r(), (List<String>) arrayList2);
                        }
                        SmartHomeDeviceManager.a().a((List<String>) arrayList2, DeviceMainPageEditBarV2.this.getContext(), (SmartHomeDeviceManager.IDelDeviceBatchCallback) new SmartHomeDeviceManager.IDelDeviceBatchCallback() {
                            public void a() {
                                DeviceMainPageEditBarV2.this.h.onStateChanged();
                                HomeManager.a().b();
                                Iterator it = arrayList2.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    if (LightGroupManager.a().b(SmartHomeDeviceManager.a().b((String) it.next()))) {
                                        Handler globalHandler = SHApplication.getGlobalHandler();
                                        if (globalHandler != null) {
                                            globalHandler.postDelayed($$Lambda$DeviceMainPageEditBarV2$9$1$iBSYrhpC8IccJTP8u5WrTV3vvjU.INSTANCE, 300);
                                        }
                                    }
                                }
                                DeviceMainPageEditBarV2.this.i.dismiss();
                            }

                            public void a(Error error) {
                                Device b2 = SmartHomeDeviceManager.a().b(error.b().substring(error.b().indexOf("did:") + 4));
                                if (b2 == null || !HomeManager.Q.contains(b2.model)) {
                                    ToastUtil.a((int) R.string.bind_error);
                                } else {
                                    ToastUtil.a((int) R.string.miband_remove_warning);
                                }
                                SmartHomeDeviceManager.a().p();
                                DeviceMainPageEditBarV2.this.h.onStateChanged();
                                DeviceMainPageEditBarV2.this.i.dismiss();
                            }
                        });
                    }
                    StatHelper.b((List<Device>) arrayList);
                    STAT.d.ae(jSONArraySafe.toString());
                    dialogInterface.dismiss();
                }
            });
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    STAT.d.ad(jSONArraySafe.toString());
                }
            });
            builder.a(true);
            builder.b().show();
        }
    }

    private void a(int i2, int i3) {
        setVisibility(4);
        setTranslationY(0.0f);
        if (this.f20840a != null) {
            this.f20840a.setVisibility(0);
        }
        postDelayed(new Runnable() {
            public void run() {
                if (DeviceMainPageEditBarV2.this.j != null) {
                    DeviceMainPageEditBarV2.this.j.a(DeviceMainPageEditBarV2.this.f20840a.getHeight());
                }
            }
        }, 200);
    }

    private static boolean a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b(list.get(i2));
            if (b2 != null && (b2.isShared() || b2.isFamily() || b2.isSharedReadOnly() || MultiHomeDeviceManager.a(b2) || !HomeManager.a(b2))) {
                return false;
            }
        }
        return true;
    }

    private static boolean b(List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b(list.get(i2));
            if (b2 != null && (b2.isShared() || b2.isFamily() || b2.isSharedReadOnly() || MultiHomeDeviceManager.a(b2) || !HomeManager.a(b2) || !TextUtils.equals("light", DeviceUtils.e(b2)) || b2.pid == Device.PID_VIRTUAL_GROUP)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void b(DviceEditInterface dviceEditInterface) {
        Set<String> l = dviceEditInterface.l();
        if (l == null || l.isEmpty()) {
            this.h.exitEditMode();
            return;
        }
        Device b2 = SmartHomeDeviceManager.a().b(l.iterator().next());
        if (b2 != null) {
            dviceEditInterface.s();
            DeviceRenderer.a(getContext(), b2, (AsyncCallback) new AsyncCallback() {
                public void onSuccess(Object obj) {
                    DeviceMainPageEditBarV2.this.h.exitEditMode();
                }

                public void onFailure(Error error) {
                    DeviceMainPageEditBarV2.this.h.exitEditMode();
                }
            });
            new ArrayList().add(b2);
            StatHelper.a(b2);
            STAT.d.c("3", dviceEditInterface.q());
        }
    }

    /* access modifiers changed from: private */
    public void c(DviceEditInterface dviceEditInterface) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (String b2 : dviceEditInterface.l()) {
            Device b3 = SmartHomeDeviceManager.a().b(b2);
            if (b3 != null && b3.hasShortcut()) {
                arrayList.add(b3);
                DeviceShortcutUtils.a(true, b3, (Intent) null, "device_page", (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                    public void a(int i, Object obj) {
                    }

                    public void a(Void voidR) {
                    }

                    public void a(int i) {
                        if (i == -1) {
                            Context context = DeviceMainPageEditBarV2.this.getContext();
                            if ((context instanceof Activity) && Build.VERSION.SDK_INT >= 23) {
                                Activity activity = (Activity) context;
                                if (activity.shouldShowRequestPermissionRationale("com.android.launcher.permission.INSTALL_SHORTCUT")) {
                                    activity.requestPermissions(new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, 1);
                                    return;
                                }
                            }
                            ToastUtil.a((int) R.string.permission_tips_denied_msg);
                        }
                    }
                });
                z = true;
            }
        }
        StatHelper.c((List<Device>) arrayList);
        if (z) {
            ToastUtil.a((int) R.string.smarthome_scene_add_short_cut_success);
        }
        this.h.onStateChanged();
        logDeviceEditEvent("DevicelistEditShortcut");
        STAT.d.c("1", dviceEditInterface.q());
    }

    /* access modifiers changed from: private */
    public void d(DviceEditInterface dviceEditInterface) {
        Set<String> l = dviceEditInterface.l();
        if (l == null || l.isEmpty()) {
            this.h.exitEditMode();
            return;
        }
        ArrayList arrayList = new ArrayList(l);
        Intent intent = new Intent(this.h.getActivity(), LightGroupSettingV2Activity.class);
        intent.putStringArrayListExtra(LightGroupSettingV2Activity.ARGS_KEY_DID_LIST, arrayList);
        this.h.getActivity().startActivity(intent);
        this.h.exitEditMode();
        STAT.d.c("6", dviceEditInterface.q());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.i != null) {
            this.i.dismiss();
        }
        super.onDetachedFromWindow();
    }

    public void logDeviceEditEvent(String str) {
        CoreApi.a().a(StatType.DEVICE_EDIT, str, (String) null, (String) null, false);
    }

    public void setOnMenuBarPositionChangeListener(OnMenuBarPositionChangeListener onMenuBarPositionChangeListener) {
        this.j = onMenuBarPositionChangeListener;
    }
}
