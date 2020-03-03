package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class MessageCenterSettingActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MessageCenterSettingActivity f19871a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private View o;

    @UiThread
    public MessageCenterSettingActivity_ViewBinding(MessageCenterSettingActivity messageCenterSettingActivity) {
        this(messageCenterSettingActivity, messageCenterSettingActivity.getWindow().getDecorView());
    }

    @UiThread
    public MessageCenterSettingActivity_ViewBinding(final MessageCenterSettingActivity messageCenterSettingActivity, View view) {
        this.f19871a = messageCenterSettingActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onClick'");
        messageCenterSettingActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        messageCenterSettingActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        messageCenterSettingActivity.moduleA3ReturnMoreMoreBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_more_more_btn, "field 'moduleA3ReturnMoreMoreBtn'", ImageView.class);
        messageCenterSettingActivity.titleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", FrameLayout.class);
        messageCenterSettingActivity.deviceShareItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.device_share_item_title, "field 'deviceShareItemTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.device_share_item_btn, "field 'deviceShareItemBtn' and method 'onClick'");
        messageCenterSettingActivity.deviceShareItemBtn = (SwitchButton) Utils.castView(findRequiredView2, R.id.device_share_item_btn, "field 'deviceShareItemBtn'", SwitchButton.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.device_share_item, "field 'deviceShareItem' and method 'onClick'");
        messageCenterSettingActivity.deviceShareItem = (ListItemView) Utils.castView(findRequiredView3, R.id.device_share_item, "field 'deviceShareItem'", ListItemView.class);
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        messageCenterSettingActivity.familyInvitationItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.family_invitation_item_title, "field 'familyInvitationItemTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.family_invitation_btn, "field 'familyInvitationBtn' and method 'onClick'");
        messageCenterSettingActivity.familyInvitationBtn = (SwitchButton) Utils.castView(findRequiredView4, R.id.family_invitation_btn, "field 'familyInvitationBtn'", SwitchButton.class);
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.family_invitation_item, "field 'familyInvitationItem' and method 'onClick'");
        messageCenterSettingActivity.familyInvitationItem = (ListItemView) Utils.castView(findRequiredView5, R.id.family_invitation_item, "field 'familyInvitationItem'", ListItemView.class);
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        messageCenterSettingActivity.shopItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.shop_item_title, "field 'shopItemTitle'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, R.id.shop_item_btn, "field 'shopItemBtn' and method 'onClick'");
        messageCenterSettingActivity.shopItemBtn = (SwitchButton) Utils.castView(findRequiredView6, R.id.shop_item_btn, "field 'shopItemBtn'", SwitchButton.class);
        this.g = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.shop_item, "field 'shopItem' and method 'onClick'");
        messageCenterSettingActivity.shopItem = (ListItemView) Utils.castView(findRequiredView7, R.id.shop_item, "field 'shopItem'", ListItemView.class);
        this.h = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        messageCenterSettingActivity.pushSilentItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.push_silent_item_title, "field 'pushSilentItemTitle'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, R.id.push_silent_item_btn, "field 'pushSilentItemBtn' and method 'onClick'");
        messageCenterSettingActivity.pushSilentItemBtn = (SwitchButton) Utils.castView(findRequiredView8, R.id.push_silent_item_btn, "field 'pushSilentItemBtn'", SwitchButton.class);
        this.i = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.push_silent_item, "field 'pushSilentItem' and method 'onClick'");
        messageCenterSettingActivity.pushSilentItem = (ListItemView) Utils.castView(findRequiredView9, R.id.push_silent_item, "field 'pushSilentItem'", ListItemView.class);
        this.j = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        messageCenterSettingActivity.pushSilentTimeItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.push_silent_time_item_title, "field 'pushSilentTimeItemTitle'", TextView.class);
        messageCenterSettingActivity.pushSilentTimeTv = (TextView) Utils.findRequiredViewAsType(view, R.id.push_silent_time_tv, "field 'pushSilentTimeTv'", TextView.class);
        View findRequiredView10 = Utils.findRequiredView(view, R.id.push_silent_time_item, "field 'pushSilentTimeItem' and method 'onClick'");
        messageCenterSettingActivity.pushSilentTimeItem = (ListItemView) Utils.castView(findRequiredView10, R.id.push_silent_time_item, "field 'pushSilentTimeItem'", ListItemView.class);
        this.k = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.push_device_item_btn, "field 'pushDeviceItemBtn' and method 'onClick'");
        messageCenterSettingActivity.pushDeviceItemBtn = (SwitchButton) Utils.castView(findRequiredView11, R.id.push_device_item_btn, "field 'pushDeviceItemBtn'", SwitchButton.class);
        this.l = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.push_home_device_item, "field 'pushHomeDeviceItem' and method 'onClick'");
        messageCenterSettingActivity.pushHomeDeviceItem = (ListItemView) Utils.castView(findRequiredView12, R.id.push_home_device_item, "field 'pushHomeDeviceItem'", ListItemView.class);
        this.m = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.push_share_device_item, "field 'pushShareDeviceItem' and method 'onClick'");
        messageCenterSettingActivity.pushShareDeviceItem = (ListItemView) Utils.castView(findRequiredView13, R.id.push_share_device_item, "field 'pushShareDeviceItem'", ListItemView.class);
        this.n = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.push_device_item, "method 'onClick'");
        this.o = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                messageCenterSettingActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        MessageCenterSettingActivity messageCenterSettingActivity = this.f19871a;
        if (messageCenterSettingActivity != null) {
            this.f19871a = null;
            messageCenterSettingActivity.moduleA3ReturnBtn = null;
            messageCenterSettingActivity.moduleA3ReturnTitle = null;
            messageCenterSettingActivity.moduleA3ReturnMoreMoreBtn = null;
            messageCenterSettingActivity.titleBar = null;
            messageCenterSettingActivity.deviceShareItemTitle = null;
            messageCenterSettingActivity.deviceShareItemBtn = null;
            messageCenterSettingActivity.deviceShareItem = null;
            messageCenterSettingActivity.familyInvitationItemTitle = null;
            messageCenterSettingActivity.familyInvitationBtn = null;
            messageCenterSettingActivity.familyInvitationItem = null;
            messageCenterSettingActivity.shopItemTitle = null;
            messageCenterSettingActivity.shopItemBtn = null;
            messageCenterSettingActivity.shopItem = null;
            messageCenterSettingActivity.pushSilentItemTitle = null;
            messageCenterSettingActivity.pushSilentItemBtn = null;
            messageCenterSettingActivity.pushSilentItem = null;
            messageCenterSettingActivity.pushSilentTimeItemTitle = null;
            messageCenterSettingActivity.pushSilentTimeTv = null;
            messageCenterSettingActivity.pushSilentTimeItem = null;
            messageCenterSettingActivity.pushDeviceItemBtn = null;
            messageCenterSettingActivity.pushHomeDeviceItem = null;
            messageCenterSettingActivity.pushShareDeviceItem = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            this.f.setOnClickListener((View.OnClickListener) null);
            this.f = null;
            this.g.setOnClickListener((View.OnClickListener) null);
            this.g = null;
            this.h.setOnClickListener((View.OnClickListener) null);
            this.h = null;
            this.i.setOnClickListener((View.OnClickListener) null);
            this.i = null;
            this.j.setOnClickListener((View.OnClickListener) null);
            this.j = null;
            this.k.setOnClickListener((View.OnClickListener) null);
            this.k = null;
            this.l.setOnClickListener((View.OnClickListener) null);
            this.l = null;
            this.m.setOnClickListener((View.OnClickListener) null);
            this.m = null;
            this.n.setOnClickListener((View.OnClickListener) null);
            this.n = null;
            this.o.setOnClickListener((View.OnClickListener) null);
            this.o = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
