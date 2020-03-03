package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.mijia.model.alarm.AlarmItemV2;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.player.FileNamer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class AlarmAdapterV2 extends BaseAdapter {
    private Context mContext;
    HashSet<String> mDownList = new HashSet<>();
    private List<AlarmItemV2> mList = new ArrayList();
    private DisplayImageOptions mOptions;
    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");

    public long getItemId(int i) {
        return (long) i;
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
    }

    private void initImageDisplayOptions(Context context) {
        this.mOptions = new DisplayImageOptions.Builder().a(context.getResources().getDrawable(R.drawable.camera_remind_load_fail)).b(context.getResources().getDrawable(R.drawable.camera_remind_load_fail)).c(context.getResources().getDrawable(R.drawable.camera_remind_load_fail)).b(true).d(true).e(true).a(Bitmap.Config.RGB_565).a(ImageScaleType.EXACTLY_STRETCHED).d();
    }

    public AlarmAdapterV2(Context context) {
        initImageDisplayOptions(context);
        this.mContext = context;
    }

    public void setData(List<AlarmItemV2> list, String str) {
        String[] list2;
        for (AlarmItemV2 next : list) {
            next.k = this.mTimeFormat.format(new Date(next.c));
            next.l = AlarmNetUtils.a().a(this.mContext, next.g);
        }
        this.mList = list;
        String c = FileUtil.c(str);
        this.mDownList.clear();
        if (!TextUtils.isEmpty(c)) {
            File file = new File(c);
            if (file.exists() && (list2 = file.list()) != null) {
                this.mDownList.addAll(Arrays.asList(list2));
            }
        }
        notifyDataSetChanged();
    }

    public void addDownSuccess(AlarmItemV2 alarmItemV2) {
        this.mDownList.add(FileNamer.a().a(alarmItemV2.c, true) + ".mp4");
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mList.size();
    }

    public AlarmItemV2 getItem(int i) {
        return this.mList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view2;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(this.mContext).inflate(R.layout.camera_alarm_list_item, (ViewGroup) null);
            viewHolder.mTypeImage = (ImageView) view2.findViewById(R.id.media_list_item_type_picture);
            viewHolder.mTime = (TextView) view2.findViewById(R.id.media_list_item_time);
            viewHolder.mTVMotionHint = (TextView) view2.findViewById(R.id.tvMotionHint);
            viewHolder.mImage = (ImageView) view2.findViewById(R.id.media_list_items_picture);
            viewHolder.mNormalItemLayout = (LinearLayout) view2.findViewById(R.id.normal_item_layout);
            viewHolder.mBottomLine = view2.findViewById(R.id.bottom_line);
            viewHolder.mTopLine = view2.findViewById(R.id.top_line);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        AlarmItemV2 item = getItem(i);
        AlarmItemV2 item2 = i > 0 ? getItem(i - 1) : null;
        viewHolder.mTime.setText(this.mList.get(i).k);
        String str = item.f;
        if (!(viewHolder == null || viewHolder.mImage == null)) {
            ImageLoader.a().b(viewHolder.mImage);
            if (TextUtils.isEmpty(str)) {
                viewHolder.mImage.setImageDrawable(view2.getContext().getResources().getDrawable(R.drawable.camera_remind_load_fail));
            } else {
                ImageLoader.a().a(str, viewHolder.mImage, this.mOptions);
            }
        }
        String str2 = FileNamer.a().a(item.c, true) + ".mp4";
        String str3 = "";
        if (item2 != null) {
            str3 = FileNamer.a().a(item2.c, true) + ".mp4";
        }
        if (this.mDownList.contains(str2) && this.mDownList.contains(str3)) {
            viewHolder.mTypeImage.setImageResource(R.drawable.camera_time_line_dot_click);
            viewHolder.mTopLine.setBackgroundColor(Color.parseColor("#dfdfdf"));
            viewHolder.mTime.setTextColor(Color.parseColor("#8d8d8d"));
            viewHolder.mBottomLine.setBackgroundColor(Color.parseColor("#dfdfdf"));
        } else if (this.mDownList.contains(str2)) {
            viewHolder.mTypeImage.setImageResource(R.drawable.camera_time_line_dot_click);
            viewHolder.mTopLine.setBackgroundColor(Color.parseColor("#dfdfdf"));
            viewHolder.mTime.setTextColor(Color.parseColor("#8d8d8d"));
            viewHolder.mBottomLine.setBackgroundColor(Color.parseColor("#dfdfdf"));
        } else if (item.l) {
            viewHolder.mTypeImage.setImageResource(R.drawable.camera_time_line_dot_click);
            viewHolder.mTopLine.setBackgroundColor(Color.parseColor("#dfdfdf"));
            viewHolder.mTime.setTextColor(Color.parseColor("#8d8d8d"));
            viewHolder.mBottomLine.setBackgroundColor(Color.parseColor("#dfdfdf"));
        } else if (this.mDownList.contains(str3)) {
            viewHolder.mTypeImage.setImageResource(R.drawable.camera_time_line_dot);
            viewHolder.mTopLine.setBackgroundColor(Color.parseColor("#ff692e"));
            viewHolder.mTime.setTextColor(Color.parseColor("#ff692e"));
            viewHolder.mBottomLine.setBackgroundColor(Color.parseColor("#ff692e"));
        } else {
            viewHolder.mTypeImage.setImageResource(R.drawable.camera_time_line_dot);
            viewHolder.mTopLine.setBackgroundColor(Color.parseColor("#ff692e"));
            viewHolder.mTime.setTextColor(Color.parseColor("#ff692e"));
            viewHolder.mBottomLine.setBackgroundColor(Color.parseColor("#ff692e"));
        }
        if (item == null || TextUtils.isEmpty(item.j) || !item.j.contains("people")) {
            viewHolder.mTVMotionHint.setText(R.string.alarm_change_string);
            viewHolder.mTVMotionHint.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            viewHolder.mTVMotionHint.setCompoundDrawablePadding(DisplayUtils.d(view2.getContext(), 10.0f));
        } else {
            viewHolder.mTVMotionHint.setText(R.string.alarm_change_people_move);
            viewHolder.mTVMotionHint.setCompoundDrawablesWithIntrinsicBounds(view2.getContext().getResources().getDrawable(R.drawable.camera_motion_icon), (Drawable) null, (Drawable) null, (Drawable) null);
            viewHolder.mTVMotionHint.setCompoundDrawablePadding(DisplayUtils.d(view2.getContext(), 10.0f));
        }
        return view2;
    }

    class ViewHolder {
        public View mBottomLine;
        public ImageView mImage;
        public LinearLayout mNormalItemLayout;
        public TextView mTVMotionHint;
        public TextView mTime;
        public View mTopLine;
        public ImageView mTypeImage;

        ViewHolder() {
        }
    }
}