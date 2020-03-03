package com.xiaomi.smarthome.scenenew.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class SelectRoomDialogView extends LinearLayout {
    public static final String ALL_ROOM_ID = "ALL_ROOM";
    public static final String DEFAULT_ROOM_ID = "DEFAULT_ROOM";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Room f22036a;
    IOnRoomSelectCallBack callBack;
    MLAlertDialog dialog;
    SelectRoomAdapter mAdapter;
    TextView mCancel;
    Context mContext;
    ListView mListView;

    public interface IOnRoomSelectCallBack {
        void a(Room room);
    }

    public SelectRoomDialogView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SelectRoomDialogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SelectRoomDialogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        LogUtil.a("SelectRoomDialogView", "SelectRoomDialogView");
    }

    public void setDialog(MLAlertDialog mLAlertDialog) {
        this.dialog = mLAlertDialog;
    }

    public void setCallBack(IOnRoomSelectCallBack iOnRoomSelectCallBack) {
        this.callBack = iOnRoomSelectCallBack;
    }

    public void setData(List<Room> list) {
        this.mAdapter.a(list);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        LogUtil.a("SelectRoomDialogView", "onFinishInflate");
        this.mListView = (ListView) findViewById(R.id.list_view);
        this.mAdapter = new SelectRoomAdapter();
        this.mListView.setAdapter(this.mAdapter);
    }

    public Room getSelectRoom() {
        return this.f22036a;
    }

    public void setSelectRoom(Room room) {
        this.f22036a = room;
    }

    class SelectRoomAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        LayoutInflater f22037a = LayoutInflater.from(SHApplication.getAppContext());
        List<Room> b = new ArrayList();

        public long getItemId(int i) {
            return (long) i;
        }

        public SelectRoomAdapter() {
        }

        public SelectRoomAdapter(List<Room> list) {
            this.b.clear();
            this.b.addAll(list);
        }

        public void a(List<Room> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = this.f22037a.inflate(R.layout.smarthome_select_room_dialog_item, (ViewGroup) null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.right_arrow);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.room_icon);
            TextView textView = (TextView) inflate.findViewById(R.id.room_name);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.divide_line);
            if (i == this.b.size() - 1) {
                imageView2.setVisibility(8);
            } else {
                imageView2.setVisibility(0);
            }
            final Room room = this.b.get(i);
            imageView.setVisibility(8);
            textView.setText(room.e());
            if (SelectRoomDialogView.this.f22036a == null || !room.d().equalsIgnoreCase(SelectRoomDialogView.this.f22036a.d())) {
                if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
                    simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.all_1_normal));
                } else if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
                    simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.default_1_normal));
                } else {
                    simpleDraweeView.setImageURI(Uri.parse("file://" + HomeManager.a().f(room.a())));
                }
                imageView.setVisibility(4);
                textView.setTextColor(SelectRoomDialogView.this.mContext.getResources().getColor(R.color.class_V));
            } else {
                if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
                    simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.all_1_pres));
                } else if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
                    simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.default_1_pres));
                } else {
                    simpleDraweeView.setImageURI(Uri.parse("file://" + HomeManager.a().g(room.a())));
                }
                imageView.setVisibility(0);
                textView.setTextColor(SelectRoomDialogView.this.mContext.getResources().getColor(R.color.class_text_17));
            }
            inflate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SelectRoomDialogView.this.callBack != null) {
                        SelectRoomDialogView.this.callBack.a(room);
                        SelectRoomDialogView.this.dialog.dismiss();
                    }
                }
            });
            return inflate;
        }
    }
}
