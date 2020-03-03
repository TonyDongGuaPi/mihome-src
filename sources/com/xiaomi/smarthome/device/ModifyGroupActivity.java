package com.xiaomi.smarthome.device;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class ModifyGroupActivity extends BaseActivity {
    public static final String PLUGIN_GROUP_DEVICE = "plugin_device_group";
    Button mBtnTitle;
    String[] mDeviceIdList = null;
    String mGroupModel = null;
    String mGroupName = null;
    String[] mGroupedIdList = null;
    ListView mListGroup;
    View mLoadingView;
    String mMasterDid = null;
    TextView mTxtTitle;
    ViewSwitcher mVsRoot;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.modify_group_activity);
        Intent intent = getIntent();
        this.mDeviceIdList = intent.getStringArrayExtra("group_device_ids");
        this.mMasterDid = intent.getStringExtra("masterDid");
        this.mGroupName = intent.getStringExtra("group_name");
        this.mGroupModel = intent.getStringExtra("group_model");
        if (TextUtils.isEmpty(this.mGroupModel)) {
            this.mGroupModel = "yeelink.light.virtual";
        }
        findViewById(R.id.module_a_4_return_more_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ModifyGroupActivity.this.finish();
            }
        });
        this.mVsRoot = (ViewSwitcher) findViewById(R.id.vs_root);
        this.mLoadingView = findViewById(R.id.ll_loading);
        this.mTxtTitle = (TextView) findViewById(R.id.module_a_4_return_more_title);
        if (PLUGIN_GROUP_DEVICE.equals(getIntent().getStringExtra("from"))) {
            checkDeviceCount();
        }
        if (TextUtils.isEmpty(this.mMasterDid)) {
            this.mTxtTitle.setText(R.string.activity_title_create_group);
        } else {
            this.mTxtTitle.setText(R.string.activity_title_modify_group);
        }
        this.mBtnTitle = (Button) findViewById(R.id.module_a_4_return_finish_btn);
        this.mBtnTitle.setEnabled(false);
        this.mListGroup = (ListView) findViewById(R.id.list_group);
        if (this.mDeviceIdList == null || this.mDeviceIdList.length <= 0) {
            if (this.mVsRoot.getCurrentView() != this.mLoadingView) {
                this.mVsRoot.showNext();
            }
            ModelGroupInfo a2 = SmartHomeDeviceHelper.a().a(SmartHomeDeviceHelper.a().k(), this.mGroupModel);
            if (a2 != null) {
                this.mDeviceIdList = SmartHomeDeviceHelper.a().a(a2);
            } else {
                this.mDeviceIdList = null;
            }
            if (this.mDeviceIdList == null || this.mDeviceIdList.length <= 0) {
                showEmptyEditInfo();
            } else {
                queryGroupInfoAndUpdateUI();
            }
        } else {
            queryGroupInfoAndUpdateUI();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a().b(r0[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkDeviceCount() {
        /*
            r5 = this;
            java.lang.String r0 = r5.mGroupModel
            if (r0 != 0) goto L_0x0007
            r5.finish()
        L_0x0007:
            com.xiaomi.smarthome.device.SmartHomeDeviceHelper r0 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
            java.util.List r0 = r0.k()
            if (r0 == 0) goto L_0x008e
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0019
            goto L_0x008e
        L_0x0019:
            r1 = 0
            java.util.Iterator r0 = r0.iterator()
        L_0x001e:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r0.next()
            com.xiaomi.smarthome.device.ModelGroupInfo r2 = (com.xiaomi.smarthome.device.ModelGroupInfo) r2
            java.lang.String r3 = r2.d
            java.lang.String r4 = r5.mGroupModel
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x001e
            r1 = r2
        L_0x0035:
            if (r1 != 0) goto L_0x003a
            r5.finish()
        L_0x003a:
            com.xiaomi.smarthome.device.SmartHomeDeviceHelper r0 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
            java.lang.String[] r0 = r0.a((com.xiaomi.smarthome.device.ModelGroupInfo) r1)
            if (r0 == 0) goto L_0x008d
            int r1 = r0.length
            r2 = 1
            if (r1 != r2) goto L_0x008d
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            r2 = 0
            r0 = r0[r2]
            com.xiaomi.smarthome.device.Device r0 = r1.b((java.lang.String) r0)
            if (r0 == 0) goto L_0x008d
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r3 = r0.model
            boolean r1 = r1.c((java.lang.String) r3)
            if (r1 == 0) goto L_0x008d
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r1 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
            r1.<init>(r5)
            r3 = 2131494778(0x7f0c077a, float:1.8613074E38)
            r1.a((int) r3)
            r3 = 2131494755(0x7f0c0763, float:1.8613027E38)
            com.xiaomi.smarthome.device.ModifyGroupActivity$2 r4 = new com.xiaomi.smarthome.device.ModifyGroupActivity$2
            r4.<init>(r0)
            r1.a((int) r3, (android.content.DialogInterface.OnClickListener) r4)
            r0 = 2131494754(0x7f0c0762, float:1.8613025E38)
            com.xiaomi.smarthome.device.ModifyGroupActivity$3 r3 = new com.xiaomi.smarthome.device.ModifyGroupActivity$3
            r3.<init>()
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r1.b((int) r0, (android.content.DialogInterface.OnClickListener) r3)
            r0.a((boolean) r2)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r0 = r1.b()
            r0.show()
        L_0x008d:
            return
        L_0x008e:
            r5.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.ModifyGroupActivity.checkDeviceCount():void");
    }

    public void updateTitleBtn() {
        GroupListAdapter groupListAdapter;
        if (this.mListGroup != null && (groupListAdapter = (GroupListAdapter) this.mListGroup.getAdapter()) != null && groupListAdapter.getCount() > 0) {
            if (groupListAdapter.a() <= 1) {
                this.mBtnTitle.setEnabled(false);
            } else {
                this.mBtnTitle.setEnabled(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void showGetFailedInfo() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ModifyGroupActivity.this, R.string.toast_get_group_failed, 1).show();
            }
        });
        finish();
    }

    /* access modifiers changed from: package-private */
    public void showEmptyEditInfo() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ModifyGroupActivity.this, R.string.toast_no_editable_group_info, 1).show();
            }
        });
        finish();
    }

    /* access modifiers changed from: package-private */
    public void showModifyFailedInfo() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ModifyGroupActivity.this, R.string.toast_modify_group_failed, 1).show();
            }
        });
        finish();
    }

    /* access modifiers changed from: package-private */
    public void showModifyFailedDuplicateInfo() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ModifyGroupActivity.this, R.string.toast_modify_group_failed_duplicate, 1).show();
            }
        });
        finish();
    }

    /* access modifiers changed from: package-private */
    public void queryGroupInfoAndUpdateUI() {
        if (TextUtils.isEmpty(this.mMasterDid)) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ModifyGroupActivity.this.mListGroup.setAdapter(new GroupListAdapter(ModifyGroupActivity.this.mDeviceIdList, (String[]) null));
                    if (ModifyGroupActivity.this.mVsRoot.getCurrentView() == ModifyGroupActivity.this.mLoadingView) {
                        ModifyGroupActivity.this.mVsRoot.showNext();
                    }
                }
            });
            return;
        }
        if (this.mVsRoot.getCurrentView() != this.mLoadingView) {
            this.mVsRoot.showNext();
        }
        DeviceApi.getInstance().getVirtualDeviceInfoById(this, this.mMasterDid, new AsyncCallback<VirtualDeviceInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(VirtualDeviceInfo virtualDeviceInfo) {
                if (virtualDeviceInfo == null) {
                    ModifyGroupActivity.this.showGetFailedInfo();
                    return;
                }
                if (virtualDeviceInfo.e == null || virtualDeviceInfo.e.length <= 0) {
                    ModifyGroupActivity.this.mGroupedIdList = null;
                } else {
                    ModifyGroupActivity.this.mGroupedIdList = new String[virtualDeviceInfo.e.length];
                    for (int i = 0; i < virtualDeviceInfo.e.length; i++) {
                        try {
                            ModifyGroupActivity.this.mGroupedIdList[i] = new JSONObject(virtualDeviceInfo.e[i]).optString("did");
                        } catch (JSONException unused) {
                        }
                    }
                }
                ModifyGroupActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        ModifyGroupActivity.this.mListGroup.setAdapter(new GroupListAdapter(ModifyGroupActivity.this.mDeviceIdList, ModifyGroupActivity.this.mGroupedIdList));
                        if (ModifyGroupActivity.this.mVsRoot.getCurrentView() == ModifyGroupActivity.this.mLoadingView) {
                            ModifyGroupActivity.this.mVsRoot.showNext();
                        }
                    }
                });
            }

            public void onFailure(Error error) {
                ModifyGroupActivity.this.showGetFailedInfo();
            }
        });
    }

    class GroupListAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        Device[] f14923a;
        boolean[] b;

        public long getItemId(int i) {
            return (long) i;
        }

        public GroupListAdapter(String[] strArr, String[] strArr2) {
            this.f14923a = new Device[strArr.length];
            this.b = new boolean[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                this.f14923a[i] = SmartHomeDeviceManager.a().b(strArr[i]);
                this.b[i] = a(strArr2, strArr[i]);
            }
            ModifyGroupActivity.this.updateTitleBtn();
            ModifyGroupActivity.this.mBtnTitle.setOnClickListener(new View.OnClickListener(ModifyGroupActivity.this) {
                public void onClick(View view) {
                    ModifyGroupActivity.this.mBtnTitle.setEnabled(false);
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < GroupListAdapter.this.b.length; i++) {
                        if (GroupListAdapter.this.b[i]) {
                            arrayList.add(GroupListAdapter.this.f14923a[i].did);
                        }
                    }
                    String[] strArr = new String[arrayList.size()];
                    arrayList.toArray(strArr);
                    if (TextUtils.isEmpty(ModifyGroupActivity.this.mMasterDid)) {
                        DeviceApi.getInstance().createModelGroup(ModifyGroupActivity.this, strArr, "", new AsyncCallback<Device, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Device device) {
                                Toast.makeText(ModifyGroupActivity.this, R.string.toast_create_group_success, 0).show();
                                device.isNew = true;
                                device.scrollTo = true;
                                DeviceFinder.a().c(device.did);
                                SmartHomeDeviceManager.a().b(device);
                                CommonUseDeviceDataManager.a().a(device.did);
                                ModifyGroupActivity.this.finish();
                                CommonUseDeviceDataManager.a().a(device.did);
                            }

                            public void onFailure(Error error) {
                                String str = error.b() + "";
                                if (error.a() != -8 || !str.contains("duplicate")) {
                                    Toast.makeText(ModifyGroupActivity.this, R.string.toast_create_group_failed, 0).show();
                                } else {
                                    ModifyGroupActivity.this.showModifyFailedDuplicateInfo();
                                }
                                ModifyGroupActivity.this.updateTitleBtn();
                            }
                        });
                    } else {
                        DeviceApi.getInstance().modifyModelGroup(ModifyGroupActivity.this, strArr, ModifyGroupActivity.this.mMasterDid, ModifyGroupActivity.this.mGroupName, new AsyncCallback<String, Error>() {
                            /* renamed from: a */
                            public void onSuccess(String str) {
                                Intent intent = new Intent();
                                intent.putExtra("result", str);
                                ModifyGroupActivity.this.setResult(-1, intent);
                                ModifyGroupActivity.this.finish();
                            }

                            public void onFailure(Error error) {
                                ModifyGroupActivity.this.setResult(0);
                                String str = error.b() + "";
                                if (error.a() != -8 || !str.contains("duplicate")) {
                                    ModifyGroupActivity.this.showModifyFailedInfo();
                                } else {
                                    ModifyGroupActivity.this.showModifyFailedDuplicateInfo();
                                }
                            }
                        });
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public boolean a(String[] strArr, String str) {
            if (strArr == null || strArr.length <= 0 || TextUtils.isEmpty(str)) {
                return false;
            }
            for (String equals : strArr) {
                if (str.equals(equals)) {
                    return true;
                }
            }
            return false;
        }

        public int getCount() {
            return this.f14923a.length;
        }

        public Object getItem(int i) {
            return this.f14923a[i];
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(ModifyGroupActivity.this).inflate(R.layout.list_item_group, ModifyGroupActivity.this.mListGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f14929a = (SimpleDraweeView) view.findViewById(R.id.image);
                viewHolder.b = (TextView) view.findViewById(R.id.name);
                viewHolder.c = (TextView) view.findViewById(R.id.name_status);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ckb_group);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final CheckBox checkBox = viewHolder.d;
            view.findViewById(R.id.layout_root).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });
            Device device = this.f14923a[i];
            viewHolder.b.setText(device.name);
            DeviceRenderer.a(device).b(ModifyGroupActivity.this, viewHolder.c, device, false);
            DeviceFactory.b(device.model, viewHolder.f14929a);
            viewHolder.d.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            viewHolder.d.setTag(Integer.valueOf(i));
            viewHolder.d.setChecked(this.b[i]);
            viewHolder.d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    GroupListAdapter.this.b[((Integer) compoundButton.getTag()).intValue()] = z;
                    ModifyGroupActivity.this.updateTitleBtn();
                }
            });
            return view;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            if (this.b == null || this.b.length <= 0) {
                return 0;
            }
            int i = 0;
            for (boolean z : this.b) {
                if (z) {
                    i++;
                }
            }
            return i;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f14929a;
            TextView b;
            TextView c;
            CheckBox d;

            ViewHolder() {
            }
        }
    }
}
