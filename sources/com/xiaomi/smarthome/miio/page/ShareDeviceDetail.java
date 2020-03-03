package com.xiaomi.smarthome.miio.page;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatPagev2;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareDeviceDetail extends BaseActivity {
    public static final String KEY_FROM_SHARE_DEVICE = "key_from_sharedeviceactivity";
    public static final String KEY_REF_FROM_APP = "key_from_page_ref";

    /* renamed from: a  reason: collision with root package name */
    private static int[] f19670a = {R.string.smarthome_device_delete_share};
    /* access modifiers changed from: private */
    public PullDownDragListView b;
    /* access modifiers changed from: private */
    public SimpleAdapter c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public List<ShareUser> f = new ArrayList();
    /* access modifiers changed from: private */
    public View g;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public boolean i = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray j = new SparseBooleanArray();
    private View k;
    private ImageView l;
    /* access modifiers changed from: private */
    public ImageView m;
    DateFormat mDateFormat;
    Device mDevice;
    XQProgressDialog mProcessDialog;
    private View n;
    private TextView o;
    private View p;
    /* access modifiers changed from: private */
    public TextView q;
    /* access modifiers changed from: private */
    public boolean r = false;

    public static class ShareUser {
        public static final int g = 0;
        public static final int h = 1;
        public static final int i = 2;

        /* renamed from: a  reason: collision with root package name */
        public String f19679a;
        public String b;
        public String c;
        public long d;
        public int e;
        public int f = 0;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.share_device_detail_layout);
        this.d = getIntent().getExtras().getString("did");
        this.e = getIntent().getExtras().getInt("pid");
        this.h = getIntent().getExtras().getBoolean(KEY_FROM_SHARE_DEVICE, false);
        if (this.d == null) {
            finish();
            return;
        }
        this.mDevice = SmartHomeDeviceManager.a().b(this.d);
        if (this.mDevice == null) {
            finish();
            return;
        }
        try {
            this.mDateFormat = LanguageUtil.b((Activity) this);
        } catch (Exception unused) {
            finish();
        }
        initViews();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initData();
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setCancelable(false);
        this.mProcessDialog.setMessage(getResources().getString(R.string.loading_share_info));
        this.mProcessDialog.show();
        this.f.clear();
        RemoteFamilyApi.a().a((Context) this, this.e, this.d, (AsyncCallback<List<ShareUser>, Error>) new AsyncCallback<List<ShareUser>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<ShareUser> list) {
                ShareDeviceDetail.this.f.addAll(list);
                ShareDeviceDetail.this.c.notifyDataSetChanged();
                ShareDeviceDetail.this.mProcessDialog.dismiss();
                ShareUserRecord.insert(list);
                if (ShareDeviceDetail.this.f.size() == 0) {
                    ShareDeviceDetail.this.b.setVisibility(8);
                    ShareDeviceDetail.this.g.setVisibility(0);
                }
                if (!ShareDeviceDetail.this.r) {
                    int i = 1;
                    boolean unused = ShareDeviceDetail.this.r = true;
                    StatPagev2 statPagev2 = STAT.c;
                    int size = list.size();
                    if (ShareDeviceDetail.this.getIntent().getBooleanExtra(ShareDeviceDetail.KEY_REF_FROM_APP, false)) {
                        i = 2;
                    }
                    statPagev2.a(size, i);
                }
            }

            public void onFailure(Error error) {
                ShareDeviceDetail.this.mProcessDialog.dismiss();
                Toast.makeText(ShareDeviceDetail.this, R.string.bind_error, 0).show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.r = false;
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.g = findViewById(R.id.common_white_empty_view);
        this.g.setVisibility(8);
        this.b = (PullDownDragListView) findViewById(R.id.share_user_list);
        this.c = new SimpleAdapter();
        this.b.setAdapter(this.c);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.share_no_user);
        ((TextView) findViewById(R.id.tv_add_scene)).setText(R.string.smarthome_add_share);
        findViewById(R.id.tv_add_scene).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.ay();
                if (ShareDeviceDetail.this.h) {
                    ShareDeviceDetail.this.finish();
                } else if (CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.putExtra("user_id", CoreApi.a().s());
                    intent.putExtra("did", ShareDeviceDetail.this.d);
                    intent.setClass(ShareDeviceDetail.this, DeviceShortcutUtils.a());
                    intent.putExtra(ShareDeviceActivity.KEY_FROM_SHAREDEVICEDETAIL, true);
                    ShareDeviceDetail.this.startActivity(intent);
                } else {
                    Toast.makeText(ShareDeviceDetail.this, R.string.loggin_first, 0).show();
                }
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.smarthome_device_user_detail_title, new Object[]{this.mDevice.name}));
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceDetail.this.finish();
                STAT.d.ax();
            }
        });
        a();
    }

    private void a() {
        this.k = findViewById(R.id.top_delete_bar);
        this.q = (TextView) this.k.findViewById(R.id.selected_cnt);
        this.l = (ImageView) this.k.findViewById(R.id.cancel_btn);
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceDetail.this.dismissEditMode();
            }
        });
        this.m = (ImageView) this.k.findViewById(R.id.select_all_btn);
        this.m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ShareDeviceDetail.this.j.size() == ShareDeviceDetail.this.f.size()) {
                    ShareDeviceDetail.this.unSelectAll();
                    StatHelper.e(false);
                    return;
                }
                ShareDeviceDetail.this.selectAll();
                StatHelper.e(true);
            }
        });
        this.n = findViewById(R.id.bottom_delete_bar);
        this.o = (TextView) this.n.findViewById(R.id.delete_msg_btn);
        this.o.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceDetail.this.deleteSelected();
                StatHelper.P();
            }
        });
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.k);
        }
    }

    public void deleteSelected() {
        if (this.j.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.j.size(), new Object[]{Integer.valueOf(this.j.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < ShareDeviceDetail.this.j.size(); i2++) {
                    int keyAt = ShareDeviceDetail.this.j.keyAt(i2);
                    if (ShareDeviceDetail.this.j.get(keyAt) && ShareDeviceDetail.this.f.size() > keyAt) {
                        ShareUser shareUser = (ShareUser) ShareDeviceDetail.this.f.get(keyAt);
                        arrayList.add(shareUser.f19679a);
                        arrayList2.add(shareUser);
                    }
                }
                RemoteFamilyApi.a().a((Context) ShareDeviceDetail.this, (List<String>) arrayList, ShareDeviceDetail.this.e, ShareDeviceDetail.this.d, (AsyncCallback<List<String>, Error>) new AsyncCallback<List<String>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(List<String> list) {
                        for (String equalsIgnoreCase : list) {
                            if (equalsIgnoreCase.equalsIgnoreCase(ShareDeviceDetail.this.d)) {
                                Toast.makeText(ShareDeviceDetail.this, R.string.smarthome_device_delete_fail, 0).show();
                                return;
                            }
                        }
                        ShareDeviceDetail.this.f.removeAll(arrayList2);
                        ShareDeviceDetail.this.c.notifyDataSetChanged();
                        if (ShareDeviceDetail.this.f.size() == 0) {
                            ShareDeviceDetail.this.b.setVisibility(8);
                            ShareDeviceDetail.this.g.setVisibility(0);
                        }
                        ShareDeviceDetail.this.dismissEditMode();
                        Toast.makeText(ShareDeviceDetail.this, R.string.smarthome_device_delete_success, 0).show();
                    }

                    public void onFailure(Error error) {
                        Toast.makeText(ShareDeviceDetail.this, R.string.smarthome_device_delete_fail, 0).show();
                    }
                });
            }
        }).b().show();
    }

    public void selectAll() {
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.j.put(i2, true);
        }
        this.m.setImageResource(R.drawable.un_select_selector);
        this.c.notifyDataSetChanged();
        this.q.setVisibility(0);
        this.q.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.j.size(), new Object[]{Integer.valueOf(this.j.size())}));
    }

    public void unSelectAll() {
        this.j.clear();
        this.m.setImageResource(R.drawable.all_select_selector);
        this.c.notifyDataSetChanged();
        this.q.setVisibility(8);
    }

    public void dismissEditMode() {
        this.b.setPullDownEnabled(true);
        this.i = false;
        this.j.clear();
        hideDeleteBars();
        this.c.notifyDataSetChanged();
    }

    public void hideDeleteBars() {
        this.k.setVisibility(8);
        this.n.setVisibility(8);
    }

    public void showDeleteBars() {
        this.k.setVisibility(0);
        this.n.setVisibility(0);
        this.k.measure(0, 0);
        this.n.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, View.Y, new float[]{(float) (-this.k.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.n.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.n, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.n.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19688a;
        SimpleDraweeView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;

        ViewHolder() {
        }
    }

    class SimpleAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return ShareDeviceDetail.this.f.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ShareDeviceDetail.this).inflate(R.layout.message_item_new, (ViewGroup) null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.c = (TextView) view.findViewById(R.id.right_tv);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.e = (TextView) view.findViewById(R.id.device_item);
                viewHolder.f = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.f19688a = view.findViewById(R.id.right_fl);
                view.setTag(viewHolder);
            }
            ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            final ShareUser shareUser = (ShareUser) ShareDeviceDetail.this.f.get(i);
            UserMamanger.a().b(shareUser.c, viewHolder2.b, (BasePostprocessor) null);
            String str = ShareDeviceDetail.this.getString(R.string.smarthome_shared_time_format, new Object[]{ShareDeviceDetail.this.mDateFormat.format(new Date(shareUser.d * 1000))}) + "  ";
            view.findViewById(R.id.arrow).setVisibility(8);
            switch (shareUser.e) {
                case 0:
                    str = str + ShareDeviceDetail.this.getResources().getString(R.string.smarthome_to_user_status_waiting);
                    break;
                case 1:
                    if (shareUser.f != 0) {
                        if (shareUser.f != 1) {
                            if (shareUser.f != 2) {
                                str = str + ShareDeviceDetail.this.getResources().getString(R.string.smarthome_to_user_status_accept);
                                break;
                            } else {
                                str = str + ShareDeviceDetail.this.getResources().getString(R.string.share_permission_can_control);
                                break;
                            }
                        } else {
                            str = str + ShareDeviceDetail.this.getResources().getString(R.string.share_permission_cannot_control);
                            break;
                        }
                    } else {
                        str = str + ShareDeviceDetail.this.getResources().getString(R.string.smarthome_to_user_status_accept);
                        break;
                    }
                case 2:
                    str = str + ShareDeviceDetail.this.getResources().getString(R.string.smarthome_to_user_status_reject);
                    break;
            }
            viewHolder2.f.setText(str);
            viewHolder2.e.setText(ShareDeviceDetail.this.getResources().getString(R.string.smarthome_has_shared_to_user, new Object[]{shareUser.b}));
            viewHolder2.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!ShareDeviceDetail.this.j.get(i)) {
                        ShareDeviceDetail.this.j.put(i, true);
                    } else {
                        ShareDeviceDetail.this.j.delete(i);
                    }
                    if (ShareDeviceDetail.this.j.size() == ShareDeviceDetail.this.f.size()) {
                        ShareDeviceDetail.this.m.setImageResource(R.drawable.un_select_selector);
                    } else {
                        ShareDeviceDetail.this.m.setImageResource(R.drawable.all_select_selector);
                    }
                    if (ShareDeviceDetail.this.j.size() > 0) {
                        ShareDeviceDetail.this.q.setVisibility(0);
                        ShareDeviceDetail.this.q.setText(ShareDeviceDetail.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, ShareDeviceDetail.this.j.size(), new Object[]{Integer.valueOf(ShareDeviceDetail.this.j.size())}));
                        return;
                    }
                    ShareDeviceDetail.this.q.setVisibility(0);
                    ShareDeviceDetail.this.q.setText(ShareDeviceDetail.this.getString(R.string.selected_0_cnt_tips));
                }
            });
            if (ShareDeviceDetail.this.i) {
                viewHolder2.d.setVisibility(0);
                viewHolder2.c.setVisibility(8);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ViewHolder viewHolder = (ViewHolder) view.getTag();
                        if (viewHolder != null) {
                            viewHolder.d.performClick();
                        }
                    }
                });
                if (ShareDeviceDetail.this.j.get(i)) {
                    viewHolder2.d.setChecked(true);
                } else {
                    viewHolder2.d.setChecked(false);
                }
            } else {
                viewHolder2.d.setVisibility(8);
                viewHolder2.c.setVisibility(0);
                viewHolder2.c.setTextColor(ShareDeviceDetail.this.getResources().getColorStateList(R.color.selector_common_text));
                viewHolder2.c.setBackgroundResource(R.drawable.selector_common_btn);
                viewHolder2.c.setPadding(ShareDeviceDetail.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_10), DisplayUtils.a(6.0f), ShareDeviceDetail.this.getResources().getDimensionPixelSize(R.dimen.txt_padding_10), DisplayUtils.a(6.0f));
                viewHolder2.c.setText(R.string.delete);
                viewHolder2.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        STAT.d.az();
                        new MLAlertDialog.Builder(ShareDeviceDetail.this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ShareUser shareUser = ShareDeviceDetail.this.f.size() > i ? shareUser : null;
                                if (shareUser != null) {
                                    RemoteFamilyApi.a().a((Context) ShareDeviceDetail.this, shareUser.f19679a, ShareDeviceDetail.this.e, ShareDeviceDetail.this.d, (AsyncCallback<List<String>, Error>) new AsyncCallback<List<String>, Error>() {
                                        /* renamed from: a */
                                        public void onSuccess(List<String> list) {
                                            if (ShareDeviceDetail.this.isValid() && ShareDeviceDetail.this.f.size() > i) {
                                                for (String equalsIgnoreCase : list) {
                                                    if (equalsIgnoreCase.equalsIgnoreCase(ShareDeviceDetail.this.d)) {
                                                        Toast.makeText(ShareDeviceDetail.this, R.string.smarthome_device_delete_fail, 0).show();
                                                        return;
                                                    }
                                                }
                                                ShareDeviceDetail.this.f.remove(i);
                                                ShareDeviceDetail.this.c.notifyDataSetChanged();
                                                if (ShareDeviceDetail.this.f.size() == 0) {
                                                    ShareDeviceDetail.this.b.setVisibility(8);
                                                    ShareDeviceDetail.this.g.setVisibility(0);
                                                }
                                                Toast.makeText(ShareDeviceDetail.this, R.string.smarthome_device_delete_success, 0).show();
                                            }
                                        }

                                        public void onFailure(Error error) {
                                            if (ShareDeviceDetail.this.isValid()) {
                                                Toast.makeText(ShareDeviceDetail.this, R.string.smarthome_device_delete_fail, 0).show();
                                            }
                                        }
                                    });
                                }
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).a(true).a((CharSequence) String.format(ShareDeviceDetail.this.getResources().getString(R.string.smarthome_device_delete_confirm), new Object[]{shareUser.b})).d();
                        Device b2 = SmartHomeDeviceManager.a().b(ShareDeviceDetail.this.d);
                        if (b2 != null) {
                            StatHelper.b(b2.model, b2.did, b2.mac);
                        }
                    }
                });
            }
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (!ShareDeviceDetail.this.i) {
                        ShareDeviceDetail.this.b.setPullDownEnabled(false);
                        if (!ShareDeviceDetail.this.j.get(i)) {
                            ((ViewHolder) view.getTag()).d.performClick();
                        }
                        ShareDeviceDetail.this.showDeleteBars();
                        boolean unused = ShareDeviceDetail.this.i = true;
                        SimpleAdapter.this.notifyDataSetChanged();
                        StatHelper.O();
                    }
                    return true;
                }
            });
            if (i != getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            }
            return view;
        }
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.i) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }
}
