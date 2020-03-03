package com.xiaomi.smarthome.homeroom.view;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.view.HomeListAdapter;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.newui.HomeEditorActivity;
import com.xiaomi.smarthome.stat.STAT;

public class HomeChildViewHolder extends BaseViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f18358a;
    private View c;
    private TextView d;
    private View e;
    private View f;
    private TextView g;
    private SwitchButton h;
    private HomeListAdapter.EditModeListener i;
    private int j;
    private TextView k;
    /* access modifiers changed from: private */
    public View.OnClickListener l;

    public HomeChildViewHolder(View view, HomeListAdapter.EditModeListener editModeListener, int i2) {
        super(view);
        this.c = view;
        this.j = i2;
        if (i2 == 0) {
            this.d = (TextView) view.findViewById(R.id.name);
            this.f18358a = view.findViewById(R.id.sort_icon);
            this.f = view.findViewById(R.id.divider_item);
            this.e = view.findViewById(R.id.next_btn);
            this.g = (TextView) view.findViewById(R.id.desc);
            this.k = (TextView) view.findViewById(R.id.right_desc);
        }
        this.i = editModeListener;
    }

    public void a(View.OnClickListener onClickListener) {
        this.l = onClickListener;
    }

    public void e(int i2) {
        this.f.setVisibility(i2);
    }

    public void a(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
        if (z) {
            layoutParams.leftMargin = 0;
        } else {
            layoutParams.leftMargin = DisplayUtils.a(SHApplication.getAppContext(), 24.0f);
        }
    }

    public void a(final HomeListAdapter homeListAdapter, final Home home, int i2, int i3) {
        if (this.j == 0) {
            final boolean k2 = homeListAdapter.k();
            boolean l2 = homeListAdapter.l();
            if (home != null) {
                if (!k2) {
                    this.f18358a.setVisibility(4);
                } else if (l2) {
                    this.f18358a.setVisibility(0);
                } else {
                    this.f18358a.setVisibility(4);
                }
                this.e.setVisibility(k2 ? 8 : 0);
                this.d.setText(home.k());
                int size = HomeManager.a().a(home.j(), new boolean[0]).size();
                int size2 = home.d().size();
                String quantityString = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.home_room_size, size2, new Object[]{Integer.valueOf(size2)});
                String quantityString2 = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.home_device_size, size, new Object[]{Integer.valueOf(size)});
                this.g.setText(quantityString + " / " + quantityString2);
                if (!home.p()) {
                    this.g.setText(R.string.share_polymerization_shared_home);
                }
                this.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!k2) {
                            if (HomeChildViewHolder.this.l != null) {
                                view.setTag(R.id.home_id, home.j());
                                HomeChildViewHolder.this.l.onClick(view);
                            } else {
                                Intent intent = new Intent(homeListAdapter.f(), HomeEditorActivity.class);
                                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                intent.putExtra("home_id", home.j());
                                homeListAdapter.f().startActivity(intent);
                            }
                            STAT.d.f(home.j(), home.p() ^ true ? 1 : 0);
                        }
                    }
                });
            }
        }
    }
}
