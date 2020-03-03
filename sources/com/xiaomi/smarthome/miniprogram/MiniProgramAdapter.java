package com.xiaomi.smarthome.miniprogram;

import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miniprogram.MiniProgramManager;
import com.xiaomi.smarthome.miniprogram.model.MyMiniProgramDevice;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class MiniProgramAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private LayoutInflater f20000a;
    /* access modifiers changed from: private */
    public OnItemLongClickListener b;
    /* access modifiers changed from: private */
    public OnItemCheckedListener c;
    /* access modifiers changed from: private */
    public List<MyMiniProgramDevice> d = new ArrayList();
    /* access modifiers changed from: private */
    public XQProgressDialog e;
    /* access modifiers changed from: private */
    public CommonActivity f;

    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f20007a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f20007a = viewHolder;
            viewHolder.title = (TextView) Utils.findRequiredViewAsType(view, R.id.device_name, "field 'title'", TextView.class);
            viewHolder.send = (TextView) Utils.findRequiredViewAsType(view, R.id.btn_share, "field 'send'", TextView.class);
            viewHolder.shareState = (TextView) Utils.findRequiredViewAsType(view, R.id.share_flag, "field 'shareState'", TextView.class);
            viewHolder.deviceImg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_img, "field 'deviceImg'", SimpleDraweeView.class);
            viewHolder.checkbox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'checkbox'", CheckBox.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f20007a;
            if (viewHolder != null) {
                this.f20007a = null;
                viewHolder.title = null;
                viewHolder.send = null;
                viewHolder.shareState = null;
                viewHolder.deviceImg = null;
                viewHolder.checkbox = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MiniProgramAdapter(CommonActivity commonActivity) {
        this.f = commonActivity;
        this.f20000a = LayoutInflater.from(commonActivity);
        this.e = new XQProgressDialog(commonActivity);
        this.e.setMessage(commonActivity.getString(R.string.device_shop_dialog_loading));
        this.e.setCancelable(true);
        this.e.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                MiniProgramManager.a().i();
            }
        });
    }

    public void a(OnItemLongClickListener onItemLongClickListener) {
        this.b = onItemLongClickListener;
    }

    public void a(OnItemCheckedListener onItemCheckedListener) {
        this.c = onItemCheckedListener;
    }

    public void a(List<MyMiniProgramDevice> list) {
        this.d.clear();
        this.d.addAll(list);
    }

    public void b(List<MyMiniProgramDevice> list) {
        this.d.addAll(list);
    }

    public void a(MyMiniProgramDevice myMiniProgramDevice) {
        this.d.add(myMiniProgramDevice);
    }

    public void a() {
        this.d.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        if (i < 0 || i >= this.d.size()) {
            return null;
        }
        return this.d.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f20000a.inflate(R.layout.item_mini_program, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setBackgroundResource(i == getCount() - 1 ? R.drawable.common_white_list_padding_no_left_margin : R.drawable.common_white_list_padding);
        if (EditController.a().c == 1) {
            viewHolder.send.setVisibility(0);
            viewHolder.checkbox.setVisibility(8);
        } else {
            viewHolder.send.setVisibility(8);
            viewHolder.checkbox.setVisibility(0);
        }
        if (EditController.a().d.get(i)) {
            viewHolder.checkbox.setChecked(true);
        } else {
            viewHolder.checkbox.setChecked(false);
        }
        MyMiniProgramDevice myMiniProgramDevice = this.d.get(i);
        viewHolder.title.setText(myMiniProgramDevice.c.getName());
        viewHolder.title.setTextColor(view.getContext().getResources().getColor(R.color.std_list_title));
        DeviceFactory.b(myMiniProgramDevice.c.model, viewHolder.deviceImg);
        if (!myMiniProgramDevice.f20051a || myMiniProgramDevice.b <= 0) {
            viewHolder.shareState.setText(R.string.not_auth_program);
            viewHolder.shareState.setTextColor(view.getContext().getResources().getColor(R.color.std_list_subtitle));
        } else {
            viewHolder.shareState.setText(R.string.already_auth_program);
            viewHolder.shareState.setTextColor(view.getContext().getResources().getColor(R.color.class_text_17));
        }
        viewHolder.send.setTextColor(view.getContext().getResources().getColorStateList(R.color.selector_common_text));
        viewHolder.send.setBackgroundResource(R.drawable.selector_common_btn);
        float f2 = view.getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) (15.0f * f2);
        int i3 = (int) (f2 * 7.0f);
        viewHolder.send.setPadding(i2, i3, i2, i3);
        viewHolder.send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SHApplication.getIWXAPI().isWXAppInstalled()) {
                    if (MiniProgramAdapter.this.e != null && !MiniProgramAdapter.this.e.isShowing() && MiniProgramAdapter.this.f.isValid()) {
                        MiniProgramAdapter.this.e.show();
                    }
                    MiniProgramManager.a().a((MyMiniProgramDevice) MiniProgramAdapter.this.d.get(i), "", true, true, (MiniProgramManager.WXShareCallbackImp) new MiniProgramManager.WXShareCallbackImp(((MyMiniProgramDevice) MiniProgramAdapter.this.d.get(i)).c.did) {
                        public void a() {
                            if (MiniProgramAdapter.this.e != null && MiniProgramAdapter.this.e.isShowing() && MiniProgramAdapter.this.f.isValid()) {
                                MiniProgramAdapter.this.e.dismiss();
                            }
                            MyMiniProgramDevice myMiniProgramDevice = (MyMiniProgramDevice) MiniProgramAdapter.this.d.get(i);
                            if (myMiniProgramDevice != null && myMiniProgramDevice.c != null) {
                                STAT.d.b(myMiniProgramDevice.c.model, true);
                            }
                        }

                        public void a(String str) {
                            if (MiniProgramAdapter.this.e != null && MiniProgramAdapter.this.e.isShowing() && MiniProgramAdapter.this.f.isValid()) {
                                MiniProgramAdapter.this.e.dismiss();
                            }
                            ToastUtil.a((int) R.string.share_failed);
                            MyMiniProgramDevice myMiniProgramDevice = (MyMiniProgramDevice) MiniProgramAdapter.this.d.get(i);
                            if (myMiniProgramDevice != null && myMiniProgramDevice.c != null) {
                                STAT.d.b(myMiniProgramDevice.c.model, false);
                            }
                        }
                    });
                    return;
                }
                ToastUtil.a((int) R.string.wx_not_installed);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                boolean z = EditController.a().d.get(i);
                if (EditController.a().c == 0) {
                    if (z) {
                        viewHolder.checkbox.setChecked(false);
                        EditController.a().d.delete(i);
                    } else {
                        viewHolder.checkbox.setChecked(true);
                        EditController.a().d.put(i, true);
                    }
                    if (MiniProgramAdapter.this.c != null) {
                        MiniProgramAdapter.this.c.onCheck(EditController.a().d.size());
                    }
                    MiniProgramAdapter.this.notifyDataSetChanged();
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (MiniProgramAdapter.this.b != null && MiniProgramAdapter.this.b.allowPerformLongClick()) {
                    MiniProgramAdapter.this.b.onLongClick();
                    ((ViewHolder) view.getTag()).checkbox.setChecked(true);
                    EditController.a().d.put(i, true);
                    MiniProgramAdapter.this.notifyDataSetChanged();
                }
                return true;
            }
        });
        return view;
    }

    class ViewHolder {
        @BindView(2131428355)
        CheckBox checkbox;
        @BindView(2131428794)
        SimpleDraweeView deviceImg;
        @BindView(2131428021)
        TextView send;
        @BindView(2131432398)
        TextView shareState;
        @BindView(2131428814)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
