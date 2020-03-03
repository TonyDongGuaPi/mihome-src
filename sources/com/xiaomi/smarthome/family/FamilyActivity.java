package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.SmartHomeTitleMoreMenuHelper;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.ExpandGridView;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.page.SettingMainPageV2;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FamilyActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15589a = 100;
    /* access modifiers changed from: private */
    public CustomPullDownRefreshLinearLayout b;
    private ListView c;
    private ExpandGridView d;
    /* access modifiers changed from: private */
    public BaseAdapter e;
    /* access modifiers changed from: private */
    public BaseAdapter f;
    /* access modifiers changed from: private */
    public List<FamilyRecord> g;
    /* access modifiers changed from: private */
    public List<Integer> h = new ArrayList();
    /* access modifiers changed from: private */
    public String[] i;
    /* access modifiers changed from: private */
    public String[] j;
    private View k;
    /* access modifiers changed from: private */
    public SimpleDraweeView l;
    /* access modifiers changed from: private */
    public SimpleDraweeView m;
    /* access modifiers changed from: private */
    public TextView n;
    /* access modifiers changed from: private */
    public TextView o;
    private TextView p;
    private TextView q;
    private String r;
    /* access modifiers changed from: private */
    public FamilyRecord s;
    /* access modifiers changed from: private */
    public int t;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.family_activity_new);
        initView();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.setting_my_family_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b(SettingMainPageV2.b, true);
                PreferenceUtils.b("my_home_red_dot_clicked", true);
                FamilyActivity.this.finish();
            }
        });
        this.j = getResources().getStringArray(R.array.family_memeber_names);
        this.d = (ExpandGridView) findViewById(R.id.family_grid_view);
        this.e = new CustomAdapter();
        this.f = new GridViewAdpater();
        this.c = (ListView) findViewById(R.id.faimlly_list_view);
        this.b = (CustomPullDownRefreshLinearLayout) findViewById(R.id.family_refresh_view);
        this.b.setScrollView((ScrollView) findViewById(R.id.scroll_view));
        this.k = findViewById(R.id.add_family_container);
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyActivity.this.startActivityForResult(new Intent(FamilyActivity.this, AddFamilyActivity.class), 100);
            }
        });
        this.l = (SimpleDraweeView) findViewById(R.id.icon_1);
        this.m = (SimpleDraweeView) findViewById(R.id.icon_2);
        this.n = (TextView) findViewById(R.id.nick_name_1);
        this.o = (TextView) findViewById(R.id.nick_name_2);
        this.p = (TextView) findViewById(R.id.relation_ship_1);
        this.q = (TextView) findViewById(R.id.relation_ship_2);
        this.b.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                FamilyActivity.this.startQuery();
            }
        });
        initData();
        startQuery();
        this.c.setAdapter(this.e);
        this.d.setAdapter(this.f);
        findViewById(R.id.add_family_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyActivity.this.startActivity(new Intent(FamilyActivity.this, AddFamilyActivity.class));
            }
        });
        findViewById(R.id.top_container_2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyRecord access$000 = FamilyActivity.this.s;
                if (access$000 == null) {
                    access$000 = new FamilyRecord();
                    access$000.relation_id = String.valueOf(FamilyActivity.this.t);
                }
                FamilyActivity.this.onItemClick(access$000);
            }
        });
        findViewById(R.id.top_container_2).setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                FamilyRecord access$000 = FamilyActivity.this.s;
                if (access$000 == null) {
                    access$000 = new FamilyRecord();
                    access$000.relation_id = String.valueOf(FamilyActivity.this.t);
                }
                return FamilyActivity.this.onItemLongClick(access$000);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && i3 == -1) {
            startQuery();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        PreferenceUtils.b(SettingMainPageV2.b, true);
        PreferenceUtils.b("my_home_red_dot_clicked", true);
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        this.g = FamilyRecord.queryAll();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = defaultSharedPreferences.getString("family_member", "1,2,3,4,5,6,7");
        this.r = defaultSharedPreferences.getString("family_vip", "");
        this.i = string.split("\\,");
        initFamilyList();
        setHeadView(true);
    }

    /* access modifiers changed from: package-private */
    public void initFamilyList() {
        boolean z;
        this.h.clear();
        for (int i2 = 0; i2 < this.i.length; i2++) {
            Iterator<FamilyRecord> it = this.g.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().relation_id.equals(this.i[i2])) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                this.h.add(Integer.valueOf(this.i[i2]));
            }
        }
        Collections.sort(this.g, new Comparator<FamilyRecord>() {
            /* renamed from: a */
            public int compare(FamilyRecord familyRecord, FamilyRecord familyRecord2) {
                int intValue = Integer.valueOf(familyRecord.relation_id).intValue();
                int intValue2 = Integer.valueOf(familyRecord2.relation_id).intValue();
                if (intValue < 0) {
                    intValue = 0;
                }
                if (intValue2 < 0) {
                    intValue2 = 0;
                }
                if (!TextUtils.isEmpty(familyRecord.status) && familyRecord.status.equals("0")) {
                    intValue = -1;
                }
                if (!TextUtils.isEmpty(familyRecord.status) && familyRecord.status.equals("1")) {
                    intValue = 0;
                }
                if (!TextUtils.isEmpty(familyRecord2.status) && familyRecord2.status.equals("0")) {
                    intValue2 = -1;
                }
                if (!TextUtils.isEmpty(familyRecord2.status) && familyRecord2.status.equals("1")) {
                    intValue2 = 0;
                }
                return intValue - intValue2;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void setHeadView(boolean z) {
        if (z) {
            UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
                public void a(ShareUserRecord shareUserRecord) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.n.setText(shareUserRecord.nickName);
                        UserMamanger.a().b(shareUserRecord.url, FamilyActivity.this.l, (BasePostprocessor) null);
                    }
                }

                public void a(int i) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.l.setImageResource(R.drawable.user_default);
                        FamilyActivity.this.n.setText(CoreApi.a().s());
                    }
                }

                public void a(int i, Object obj) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.l.setImageResource(R.drawable.user_default);
                        FamilyActivity.this.n.setText(CoreApi.a().s());
                    }
                }
            });
            int i2 = 0;
            for (Device next : SmartHomeDeviceManager.a().d()) {
                if (next.isBinded() && next.canAuth) {
                    i2++;
                }
            }
            this.p.setText(getResources().getQuantityString(R.plurals.family_share_device_count, i2, new Object[]{Integer.valueOf(i2)}));
        }
        final FamilyRecord familyRecord = null;
        if (!TextUtils.isEmpty(this.r)) {
            FamilyRecord familyRecord2 = null;
            boolean z2 = false;
            for (FamilyRecord next2 : this.g) {
                if (next2.tUserId.equals(this.r)) {
                    familyRecord2 = next2;
                    z2 = true;
                }
            }
            if (!z2) {
                this.r = null;
                this.s = null;
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("family_vip", "").apply();
            }
            familyRecord = familyRecord2;
        }
        if (TextUtils.isEmpty(this.r) && this.g.size() > 0) {
            int i3 = 1001;
            int i4 = 0;
            for (int i5 = 0; i5 < this.g.size(); i5++) {
                if (Math.abs(Integer.valueOf(this.g.get(i5).relation_id).intValue()) < i3) {
                    i3 = Integer.valueOf(this.g.get(i5).relation_id).intValue();
                    i4 = i5;
                }
            }
            familyRecord = this.g.get(i4);
            this.r = familyRecord.userId;
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("family_vip", familyRecord.tUserId).apply();
        }
        if (familyRecord != null) {
            UserMamanger.a().a(familyRecord.tUserId, (AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
                public void a(ShareUserRecord shareUserRecord) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.o.setText(shareUserRecord.nickName);
                        UserMamanger.a().b(shareUserRecord.url, FamilyActivity.this.m, (BasePostprocessor) null);
                    }
                }

                public void a(int i) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.l.setImageResource(R.drawable.user_default);
                        FamilyActivity.this.n.setText(familyRecord.tUserId);
                    }
                }

                public void a(int i, Object obj) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.l.setImageResource(R.drawable.user_default);
                        FamilyActivity.this.n.setText(familyRecord.tUserId);
                    }
                }
            }, true);
            setRelationTextView(this.q, familyRecord);
            this.g.remove(familyRecord);
            this.s = familyRecord;
            this.t = 1000;
        } else {
            this.m.setImageResource(R.drawable.default_avatar);
            this.o.setText(String.format(getString(R.string.family_invite_in), new Object[]{this.j[this.h.get(0).intValue() - 1]}));
            this.q.setText("");
            this.t = this.h.get(0).intValue();
            this.h.remove(0);
        }
        if (this.h.size() == 1) {
            this.h.remove(0);
        }
        if (this.h.size() != 0 || this.t == 7) {
            this.k.setVisibility(8);
            this.d.setVisibility(0);
            return;
        }
        this.k.setVisibility(0);
        this.d.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void setRelationTextView(TextView textView, FamilyRecord familyRecord) {
        String str;
        if (Integer.valueOf(familyRecord.status).intValue() == 0) {
            String a2 = FamilyUtils.a(this, this.j, familyRecord);
            if (Integer.valueOf(familyRecord.relation_id).intValue() < 0) {
                str = getString(R.string.family_accept_title);
            } else {
                str = getString(R.string.family_wait_accept) + ", " + a2;
            }
            textView.setText(str);
            return;
        }
        textView.setText(FamilyUtils.a(this, this.j, familyRecord));
    }

    /* access modifiers changed from: package-private */
    public void onItemClick(final FamilyRecord familyRecord) {
        if (!TextUtils.isEmpty(familyRecord.status) && Integer.valueOf(familyRecord.status).intValue() == 0 && Integer.valueOf(familyRecord.relation_id).intValue() < 0) {
            new MLAlertDialog.Builder(this).b((CharSequence) String.format(getString(R.string.family_confirm_add_family), new Object[]{familyRecord.nickName})).a((int) R.string.family_accept, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    RemoteFamilyApi.a().c((Context) FamilyActivity.this, familyRecord.tUserId, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        public void onFailure(Error error) {
                        }

                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            FamilyActivity.this.startQuery();
                        }
                    });
                }
            }).b((int) R.string.family_reject, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    RemoteFamilyApi.a().d(FamilyActivity.this, familyRecord.tUserId, new AsyncCallback<Void, Error>() {
                        public void onFailure(Error error) {
                        }

                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            FamilyActivity.this.startQuery();
                        }
                    });
                }
            }).b().show();
        } else if (TextUtils.isEmpty(familyRecord.tUserId)) {
            Intent intent = new Intent(this, AddFamilyActivity.class);
            intent.putExtra(FamilyRecord.FIELD_RELATION_ID, familyRecord.relation_id);
            startActivityForResult(intent, 100);
        } else if (Integer.valueOf(familyRecord.status).intValue() != 0) {
            Intent intent2 = new Intent(this, FamilyShareDetail.class);
            intent2.putExtra("user_id", familyRecord.tUserId);
            startActivity(intent2);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onItemLongClick(final FamilyRecord familyRecord) {
        if (TextUtils.isEmpty(familyRecord.tUserId) && familyRecord.relation_id.equals("7")) {
            return false;
        }
        AnonymousClass12 r0 = new SmartHomeTitleMoreMenuHelper() {
            public TextView a() {
                return null;
            }

            public Device c() {
                return null;
            }

            public Context b() {
                return FamilyActivity.this;
            }

            public boolean a(int i) {
                if (i == R.string.family_modify_nickname) {
                    final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) FamilyActivity.this.getLayoutInflater().inflate(R.layout.client_remark_input_view, (ViewGroup) null);
                    clientRemarkInputView.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
                    MLAlertDialog d = new MLAlertDialog.Builder(FamilyActivity.this).a((int) R.string.family_input_new_relation).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, int i) {
                            RemoteFamilyApi.a().a((Context) FamilyActivity.this, familyRecord.tUserId, clientRemarkInputView.getEditText().getText().toString(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    if (FamilyActivity.this.b != null) {
                                        if (TextUtils.isEmpty(clientRemarkInputView.getEditText().getText().toString())) {
                                            Toast.makeText(FamilyActivity.this, R.string.family_nickname_empty, 0).show();
                                            return;
                                        }
                                        familyRecord.relationship = clientRemarkInputView.getEditText().getText().toString();
                                        familyRecord.update();
                                        FamilyActivity.this.b.doRefresh();
                                        FamilyActivity.this.e.notifyDataSetChanged();
                                        ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                                    }
                                }

                                public void onFailure(Error error) {
                                    if (FamilyActivity.this.b != null) {
                                        ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                                    }
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                        }
                    }).d();
                    clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, d, FamilyUtils.a(FamilyActivity.this, FamilyActivity.this.j, familyRecord));
                    final Button button = d.getButton(-1);
                    button.setEnabled(false);
                    button.setTextColor(FamilyActivity.this.getResources().getColor(R.color.std_list_subtitle));
                    clientRemarkInputView.getEditText().addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable editable) {
                        }

                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            clientRemarkInputView.setAlertText("");
                            button.setEnabled(true);
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            if (charSequence.length() <= 0) {
                                button.setEnabled(false);
                                button.setTextColor(FamilyActivity.this.getResources().getColor(R.color.std_list_subtitle));
                            } else if (!HomeManager.A(charSequence.toString())) {
                                clientRemarkInputView.setAlertText(FamilyActivity.this.getString(R.string.room_name_too_long));
                                button.setEnabled(false);
                                button.setTextColor(FamilyActivity.this.getResources().getColor(R.color.std_list_subtitle));
                            } else {
                                clientRemarkInputView.setAlertText("");
                                button.setEnabled(true);
                                button.setTextColor(FamilyActivity.this.getResources().getColor(R.color.std_dialog_button_green));
                            }
                        }
                    });
                } else if (i == R.string.family_delete) {
                    new MLAlertDialog.Builder(FamilyActivity.this).b((CharSequence) FamilyActivity.this.getResources().getString(R.string.confirm_delete_family)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (TextUtils.isEmpty(familyRecord.tUserId)) {
                                ArrayList arrayList = new ArrayList();
                                for (int i2 = 0; i2 < FamilyActivity.this.i.length; i2++) {
                                    if (!FamilyActivity.this.i[i2].equals(familyRecord.relation_id)) {
                                        arrayList.add(FamilyActivity.this.i[i2]);
                                    }
                                }
                                String str = "";
                                String[] unused = FamilyActivity.this.i = new String[arrayList.size()];
                                for (int i3 = 0; i3 < FamilyActivity.this.i.length; i3++) {
                                    FamilyActivity.this.i[i3] = (String) arrayList.get(i3);
                                    str = str + ((String) arrayList.get(i3)) + ",";
                                }
                                PreferenceManager.getDefaultSharedPreferences(FamilyActivity.this).edit().putString("family_member", str).apply();
                                FamilyActivity.this.e.notifyDataSetChanged();
                                return;
                            }
                            RemoteFamilyApi.a().e(FamilyActivity.this, familyRecord.tUserId, new AsyncCallback<Void, Error>() {
                                public void onFailure(Error error) {
                                }

                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    FamilyActivity.this.startQuery();
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                }
                return true;
            }
        };
        if (TextUtils.isEmpty(familyRecord.tUserId) || familyRecord.status.equals("0")) {
            return false;
        }
        r0.a(new int[]{R.string.family_delete, R.string.family_modify_nickname});
        r0.j();
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.b = null;
    }

    /* access modifiers changed from: package-private */
    public void startQuery() {
        if (this.b != null) {
            RemoteFamilyApi.a().b((Context) this, (AsyncCallback<List<FamilyRecord>, Error>) new AsyncCallback<List<FamilyRecord>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<FamilyRecord> list) {
                    if (FamilyActivity.this.b != null) {
                        try {
                            FamilyActivity.this.g.clear();
                            FamilyActivity.this.g.addAll(list);
                            FamilyActivity.this.initFamilyList();
                            FamilyActivity.this.setHeadView(false);
                            FamilyActivity.this.e.notifyDataSetChanged();
                            FamilyActivity.this.f.notifyDataSetChanged();
                            FamilyActivity.this.b.postRefresh();
                        } catch (Exception unused) {
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (FamilyActivity.this.b != null) {
                        FamilyActivity.this.b.postRefresh();
                    }
                }
            });
        }
    }

    class CustomAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        CustomAdapter() {
        }

        public int getCount() {
            return FamilyActivity.this.g.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(FamilyActivity.this).inflate(R.layout.family_item, (ViewGroup) null);
            }
            UserMamanger.a().b(((FamilyRecord) FamilyActivity.this.g.get(i)).url, (SimpleDraweeView) view.findViewById(R.id.family_mem_icon), new CircleAvatarProcessor());
            ImageView imageView = (ImageView) view.findViewById(R.id.arrow);
            if (TextUtils.isEmpty(((FamilyRecord) FamilyActivity.this.g.get(i)).tUserId)) {
                ((TextView) view.findViewById(R.id.user_define_nick_name)).setText(R.string.no_invite);
            } else {
                FamilyActivity.this.setRelationTextView((TextView) view.findViewById(R.id.user_define_nick_name), (FamilyRecord) FamilyActivity.this.g.get(i));
            }
            if (TextUtils.isEmpty(((FamilyRecord) FamilyActivity.this.g.get(i)).tUserId)) {
                int intValue = Integer.valueOf(((FamilyRecord) FamilyActivity.this.g.get(i)).relation_id).intValue();
                if (intValue == -1 || intValue == -2) {
                    intValue = intValue == -1 ? 2 : 1;
                }
                ((TextView) view.findViewById(R.id.nick_name)).setText(FamilyActivity.this.j[intValue - 1]);
            } else {
                ((TextView) view.findViewById(R.id.nick_name)).setText(((FamilyRecord) FamilyActivity.this.g.get(i)).nickName);
            }
            if (((FamilyRecord) FamilyActivity.this.g.get(i)).status.equals("0")) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FamilyActivity.this.onItemClick((FamilyRecord) FamilyActivity.this.g.get(i));
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return FamilyActivity.this.onItemLongClick((FamilyRecord) FamilyActivity.this.g.get(i));
                }
            });
            return view;
        }
    }

    class GridViewAdpater extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        GridViewAdpater() {
        }

        public int getCount() {
            return FamilyActivity.this.h.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(FamilyActivity.this).inflate(R.layout.family_item_grid, (ViewGroup) null);
            }
            ((TextView) view.findViewById(R.id.default_item_text)).setText(FamilyActivity.this.j[((Integer) FamilyActivity.this.h.get(i)).intValue() - 1]);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(FamilyActivity.this, AddFamilyActivity.class);
                    intent.putExtra(FamilyRecord.FIELD_RELATION_ID, String.valueOf(FamilyActivity.this.h.get(i)));
                    FamilyActivity.this.startActivityForResult(intent, 100);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (((Integer) FamilyActivity.this.h.get(i)).intValue() == 7) {
                        return false;
                    }
                    AnonymousClass1 r5 = new SmartHomeTitleMoreMenuHelper() {
                        public TextView a() {
                            return null;
                        }

                        public Device c() {
                            return null;
                        }

                        public Context b() {
                            return FamilyActivity.this;
                        }

                        public boolean a(int i) {
                            if (i != R.string.family_delete) {
                                return true;
                            }
                            new MLAlertDialog.Builder(FamilyActivity.this).b((CharSequence) FamilyActivity.this.getResources().getString(R.string.confirm_delete_family)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < FamilyActivity.this.i.length; i2++) {
                                        if (!FamilyActivity.this.i[i2].equals(String.valueOf(FamilyActivity.this.h.get(i)))) {
                                            arrayList.add(FamilyActivity.this.i[i2]);
                                        }
                                    }
                                    String str = "";
                                    String[] unused = FamilyActivity.this.i = new String[arrayList.size()];
                                    for (int i3 = 0; i3 < FamilyActivity.this.i.length; i3++) {
                                        FamilyActivity.this.i[i3] = (String) arrayList.get(i3);
                                        str = str + ((String) arrayList.get(i3)) + ",";
                                    }
                                    PreferenceManager.getDefaultSharedPreferences(FamilyActivity.this).edit().putString("family_member", str).apply();
                                    FamilyActivity.this.startQuery();
                                }
                            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                            return true;
                        }
                    };
                    r5.a(new int[]{R.string.family_delete});
                    r5.j();
                    return true;
                }
            });
            return view;
        }
    }
}
