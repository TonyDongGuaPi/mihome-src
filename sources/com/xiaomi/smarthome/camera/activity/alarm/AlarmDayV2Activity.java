package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmItemV2;
import com.mijia.model.alarm.AlarmManagerV2;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.BaseSelectActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmDayV2Adapter;
import com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerForV3UpgradeActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerViewRefreshLayout;
import com.xiaomi.smarthome.camera.view.widget.WeekView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmDayV2Activity extends BaseSelectActivity implements AlarmDayV2Adapter.ClickCallBack, WeekView.OnDayChangeListener {
    private static final String TAG = "AlarmDayV2Activity";
    /* access modifiers changed from: private */
    public HashMap<Integer, ArrayList<AlarmDayBeanV2>> cachMap = new HashMap<>();
    /* access modifiers changed from: private */
    public AlarmDayV2Adapter mAdapter;
    /* access modifiers changed from: private */
    public LinearLayout mCareNoEventOfDayLl;
    /* access modifiers changed from: private */
    public RecyclerView mMediaListView;
    private XQProgressDialog mProgressDlg;
    private XQProgressDialog mPulingDlg;
    /* access modifiers changed from: private */
    public RecyclerViewRefreshLayout mRefreshLayout;
    private SimpleDateFormat mTimeFormater0 = new SimpleDateFormat("HH:mm");
    /* access modifiers changed from: private */
    public WeekView mWeekView;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_day_v2);
        TitleBarUtil.a(findViewById(R.id.title_bar_container));
        initView();
        loadData(true, true);
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        this.cachMap.clear();
        if (this.mAdapter != null) {
            this.mAdapter.destroyDisplayImageOptions();
        }
    }

    private void initView() {
        initSelectView();
        this.mWeekView = (WeekView) findViewById(R.id.view_week_view);
        this.mCareNoEventOfDayLl = (LinearLayout) findViewById(R.id.layout_care_no_event_of_day_ll);
        this.mWeekView.setOnDayChangeListener(this);
        findViewById(R.id.select_delete).setOnClickListener(this);
        this.mMediaListView = (RecyclerView) findViewById(R.id.list_event_listview);
        ((TextView) findViewById(R.id.title_bar_title)).setText(getResources().getString(R.string.history_alarm_string));
        this.mRefreshLayout = (RecyclerViewRefreshLayout) findViewById(R.id.recycle_refresh);
        this.mRefreshLayout.setLoadMore(false);
        this.mRefreshLayout.setRefreshing(false);
        this.mRefreshLayout.setMode(3);
        this.mRefreshLayout.setOnPullRefreshListener(new RecyclerViewRefreshLayout.OnPullRefreshListener() {
            public void onPullDistance(int i) {
            }

            public void onPullEnable(boolean z) {
            }

            public void onRefresh() {
                if (!AlarmDayV2Activity.this.isFinishing()) {
                    AlarmDayV2Activity.this.setMultiSelectMode(false);
                    AlarmDayV2Activity.this.loadData(true, false);
                }
            }
        });
        this.mRefreshLayout.setFooterRefreshView(LayoutInflater.from(this).inflate(R.layout.camera_list_load_more, (ViewGroup) null));
        this.mRefreshLayout.setOnPushLoadMoreListener(new RecyclerViewRefreshLayout.OnPushLoadMoreListener() {
            public void onPushDistance(int i) {
            }

            public void onPushEnable(boolean z) {
            }

            public void onLoadMore() {
                if (!AlarmDayV2Activity.this.isFinishing()) {
                    AlarmDayV2Activity.this.setMultiSelectMode(false);
                    AlarmDayV2Activity.this.loadMoreData(false);
                }
            }
        });
        this.mMediaListView.setLayoutManager(new GridLayoutManager(this, 3));
        this.mAdapter = new AlarmDayV2Adapter(this, this);
        this.mMediaListView.setAdapter(this.mAdapter);
        this.mMediaListView.addItemDecoration(new ItemDecoration());
        if (this.mCameraDevice.isReadOnlyShared()) {
            findViewById(R.id.layout_select_bottom).setVisibility(8);
            this.mAdapter.disableLongPress(true);
            this.mEditBtn.setVisibility(8);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSelectAll:
                if (this.mSelectAllShowed) {
                    this.mSelectAllShowed = false;
                    this.mSelectAllBtn.setImageResource(R.drawable.camera_edit_deselect_all_black);
                    this.mAdapter.selectAll();
                    refreshSelectTitle();
                    return;
                }
                this.mSelectAllShowed = true;
                this.mSelectAllBtn.setImageResource(R.drawable.camera_edit_select_all_black);
                this.mAdapter.unSelectAll();
                refreshSelectTitle();
                return;
            case R.id.ivSelectAllCancel:
                if (this.mIsMultiSelectMode) {
                    setMultiSelectMode(false);
                    return;
                }
                return;
            case R.id.select_delete:
                deleteAlertDialog();
                return;
            case R.id.title_bar_more:
                if (this.mCameraDevice.isReadOnlyShared()) {
                    ToastUtil.a((Context) this, (int) R.string.auth_fail);
                    return;
                } else if (this.mCareNoEventOfDayLl.getVisibility() == 0) {
                    ToastUtil.a((Context) this, (int) R.string.edit_no_data);
                    return;
                } else {
                    setMultiSelectMode(true);
                    return;
                }
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    public void onDayChange(boolean z, int i, int i2, int i3) {
        setMultiSelectMode(false);
        loadData(false, true);
    }

    /* access modifiers changed from: private */
    public void loadData(boolean z, boolean z2) {
        ArrayList arrayList = this.cachMap.get(Integer.valueOf(this.mWeekView.getCurrentDay()));
        if (arrayList == null || arrayList.isEmpty() || z) {
            final int currentDay = this.mWeekView.getCurrentDay();
            if (z2) {
                showLoadDlg();
            }
            AnonymousClass3 r10 = new AlarmManagerV2.IAlarmCallback<List<AlarmItemV2>>() {
                public void onSuccess(List<AlarmItemV2> list, Object obj) {
                    if (!AlarmDayV2Activity.this.isFinishing()) {
                        AlarmDayV2Activity.this.dealAlarm(currentDay, list, false);
                        AlarmDayV2Activity.this.hideLoadDlg();
                        if (AlarmDayV2Activity.this.mRefreshLayout.isRefreshing()) {
                            AlarmDayV2Activity.this.mRefreshLayout.setRefreshing(false);
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmDayV2Activity.this.isFinishing()) {
                        SDKLog.b(AlarmDayV2Activity.TAG, "loadData errorCode:" + i + " errorMessage:" + str);
                        AlarmDayV2Activity.this.hideLoadDlg();
                        if (AlarmDayV2Activity.this.mRefreshLayout.isRefreshing()) {
                            AlarmDayV2Activity.this.mRefreshLayout.setRefreshing(false);
                        }
                        if (AlarmDayV2Activity.this.mAdapter == null || AlarmDayV2Activity.this.mAdapter.getData().size() <= 0) {
                            AlarmDayV2Activity.this.mMediaListView.setVisibility(8);
                        } else {
                            AlarmDayV2Activity.this.mMediaListView.setVisibility(0);
                        }
                        AlarmDayV2Activity.this.mCareNoEventOfDayLl.setVisibility(0);
                    }
                }
            };
            this.mCameraDevice.j().b(((long) this.mWeekView.getCurrentStartTime()) * 1000, ((long) this.mWeekView.getCurrentEndTime()) * 1000, true, r10);
            return;
        }
        this.mAdapter.setData(arrayList);
        this.mCareNoEventOfDayLl.setVisibility(8);
        this.mMediaListView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void loadMoreData(boolean z) {
        final int currentDay = this.mWeekView.getCurrentDay();
        if (z) {
            showLoadDlg();
        }
        List list = this.cachMap.get(Integer.valueOf(currentDay));
        this.mCameraDevice.j().b(((long) this.mWeekView.getCurrentStartTime()) * 1000, ((AlarmDayBeanV2) list.get(list.size() - 1)).item.c, true, new AlarmManagerV2.IAlarmCallback<List<AlarmItemV2>>() {
            public void onSuccess(List<AlarmItemV2> list, Object obj) {
                if (!AlarmDayV2Activity.this.isFinishing()) {
                    AlarmDayV2Activity.this.hideLoadDlg();
                    AlarmDayV2Activity.this.mRefreshLayout.setLoadMore(false);
                    AlarmDayV2Activity.this.dealAlarm(currentDay, list, true);
                    if (AlarmDayV2Activity.this.mRefreshLayout.isRefreshing()) {
                        AlarmDayV2Activity.this.mRefreshLayout.setRefreshing(false);
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmDayV2Activity.this.isFinishing()) {
                    SDKLog.b(AlarmDayV2Activity.TAG, "loadMoreData errorCode:" + i + " errorMessage:" + str);
                    AlarmDayV2Activity.this.hideLoadDlg();
                    if (AlarmDayV2Activity.this.mRefreshLayout.isRefreshing()) {
                        AlarmDayV2Activity.this.mRefreshLayout.setRefreshing(false);
                    }
                    if (AlarmDayV2Activity.this.mAdapter == null || AlarmDayV2Activity.this.mAdapter.getData().size() <= 0) {
                        AlarmDayV2Activity.this.mMediaListView.setVisibility(8);
                    } else {
                        AlarmDayV2Activity.this.mMediaListView.setVisibility(0);
                    }
                    AlarmDayV2Activity.this.mCareNoEventOfDayLl.setVisibility(0);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void dealAlarm(int i, List<AlarmItemV2> list, boolean z) {
        List<AlarmItemV2> list2 = list;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (z) {
            Iterator it = this.cachMap.get(Integer.valueOf(this.mWeekView.getCurrentDay())).iterator();
            while (it.hasNext()) {
                AlarmDayBeanV2 alarmDayBeanV2 = (AlarmDayBeanV2) it.next();
                if (alarmDayBeanV2.item != null) {
                    arrayList2.add(alarmDayBeanV2.item);
                }
            }
            arrayList2.addAll(list2);
        } else {
            arrayList2.addAll(list2);
        }
        if (!arrayList2.isEmpty()) {
            Date date = new Date(((AlarmItemV2) arrayList2.get(0)).c);
            date.setMinutes(0);
            date.setSeconds(0);
            long time = date.getTime();
            long j = time + 3600000;
            String format = this.mTimeFormater0.format(date);
            date.setTime(date.getTime() + 3600000);
            String format2 = this.mTimeFormater0.format(date);
            arrayList.add(new AlarmDayBeanV2(format + "-" + format2, (AlarmItemV2) null));
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                AlarmItemV2 alarmItemV2 = (AlarmItemV2) arrayList2.get(i2);
                long j2 = alarmItemV2.c;
                if (j2 < time || j2 >= j) {
                    while (true) {
                        if (j2 >= time && j2 <= j) {
                            break;
                        }
                        time -= 3600000;
                        j -= 3600000;
                    }
                    date.setTime(time);
                    String format3 = this.mTimeFormater0.format(date);
                    date.setTime(j);
                    String format4 = this.mTimeFormater0.format(date);
                    arrayList.add(new AlarmDayBeanV2(format3 + "-" + format4, (AlarmItemV2) null));
                    arrayList.add(new AlarmDayBeanV2(this.mTimeFormater0.format(new Date(j2)), alarmItemV2));
                } else {
                    arrayList.add(new AlarmDayBeanV2(this.mTimeFormater0.format(new Date(j2)), alarmItemV2));
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.cachMap.put(Integer.valueOf(i), arrayList);
            if (this.mWeekView.getCurrentDay() == i) {
                this.mCareNoEventOfDayLl.setVisibility(8);
                this.mMediaListView.setVisibility(0);
                this.mAdapter.setData(arrayList);
                return;
            }
            return;
        }
        int i3 = i;
        this.mAdapter.setData(new ArrayList());
        this.cachMap.put(Integer.valueOf(i), arrayList);
        this.mMediaListView.setVisibility(8);
        this.mCareNoEventOfDayLl.setVisibility(0);
    }

    public void onRecyclerClick(View view) {
        if (this.mIsMultiSelectMode) {
            int childLayoutPosition = this.mMediaListView.getChildLayoutPosition(view);
            if (childLayoutPosition >= 0 && childLayoutPosition < this.mAdapter.getItemCount()) {
                this.mAdapter.selectToggle(childLayoutPosition);
                refreshSelectTitle();
                this.mAdapter.notifyItemChanged(childLayoutPosition);
                return;
            }
            return;
        }
        Event.a(Event.aS);
        Object tag = view.getTag();
        if (tag != null) {
            AlarmItemV2 alarmItemV2 = (AlarmItemV2) tag;
            Intent intent = new Intent();
            if (this.mCameraDevice.o()) {
                intent.putExtra("alarmItem", alarmItemV2.a());
                intent.setClass(this, LocalAlarmPlayerForV3UpgradeActivity.class);
                startActivityForResult(intent, 1001);
            } else if (alarmItemV2 != null && !TextUtils.isEmpty(alarmItemV2.g)) {
                intent.putExtra("alarmItem", alarmItemV2.a());
                intent.setClass(this, LocalAlarmPlayerV2Activity.class);
                startActivityForResult(intent, 1001);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1) {
            loadData(true, true);
        }
    }

    public void onRecyclerLongClick(View view) {
        int childLayoutPosition = this.mMediaListView.getChildLayoutPosition(view);
        if (childLayoutPosition >= 0 && childLayoutPosition < this.mAdapter.getItemCount()) {
            this.mAdapter.selectToggle(childLayoutPosition);
            if (!this.mIsMultiSelectMode) {
                setMultiSelectMode(true, true);
                this.mAdapter.setSelectMode(true);
            } else {
                this.mAdapter.notifyItemChanged(childLayoutPosition);
            }
            refreshSelectTitle();
        }
    }

    public void setMultiSelectMode(boolean z) {
        setMultiSelectMode(z, false);
        this.mAdapter.setSelectMode(z);
    }

    private void deleteAlertDialog() {
        if (this.mAdapter.getSelectedCount() == 0) {
            ToastUtil.a((Context) this, (int) R.string.bottom_action_tip);
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.b((CharSequence) getString(R.string.delete_hint, new Object[]{Integer.valueOf(this.mAdapter.getSelectedCount())}));
        builder.b((int) R.string.camera_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlarmDayV2Activity.this.setMultiSelectMode(false);
                dialogInterface.dismiss();
            }
        });
        builder.a((int) R.string.camera_sure, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                AlarmDayV2Activity.this.showDeleteDlg();
                ArrayList<AlarmItemV2> selectList = AlarmDayV2Activity.this.mAdapter.getSelectList();
                AlarmDayV2Activity.this.setMultiSelectMode(false);
                if (selectList != null && selectList.size() > 0) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        JSONArray jSONArray = new JSONArray();
                        Iterator<AlarmItemV2> it = selectList.iterator();
                        while (it.hasNext()) {
                            jSONArray.put("" + it.next().g);
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("fileIds", jSONArray);
                        jSONObject.put("fileIds", jSONObject2);
                        jSONObject.put("did", AlarmDayV2Activity.this.mCameraDevice.getDid());
                        jSONObject.put("model", AlarmDayV2Activity.this.mCameraDevice.getModel());
                        AlarmNetUtils.a().j(AlarmDayV2Activity.this.mCameraDevice.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                            public void onSuccess(JSONObject jSONObject) {
                                if (!AlarmDayV2Activity.this.isFinishing()) {
                                    AlarmDayV2Activity.this.hideDeleteDlg();
                                    if (jSONObject == null) {
                                        ToastUtil.a((Context) AlarmDayV2Activity.this, (int) R.string.delete_failed);
                                    } else if (jSONObject.optInt("code") == 0) {
                                        List list = (List) AlarmDayV2Activity.this.cachMap.get(Integer.valueOf(AlarmDayV2Activity.this.mWeekView.getCurrentDay()));
                                        if (list != null) {
                                            list.clear();
                                        }
                                        AlarmDayV2Activity.this.loadData(true, true);
                                        ToastUtil.a((Context) AlarmDayV2Activity.this, (int) R.string.delete_sucess);
                                    } else {
                                        ToastUtil.a((Context) AlarmDayV2Activity.this, (int) R.string.delete_failed);
                                    }
                                    AlarmDayV2Activity.this.setResult(-1);
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!AlarmDayV2Activity.this.isFinishing()) {
                                    AlarmDayV2Activity.this.hideDeleteDlg();
                                    ToastUtil.a((Context) AlarmDayV2Activity.this, (int) R.string.delete_failed);
                                }
                            }
                        });
                    } catch (JSONException unused) {
                    }
                }
            }
        });
        builder.d();
    }

    public void showLoadDlg() {
        if (this.mPulingDlg == null) {
            this.mPulingDlg = new XQProgressDialog(this);
            this.mPulingDlg.setMessage(getResources().getString(R.string.camera_loading));
            this.mPulingDlg.setCancelable(false);
        }
        this.mPulingDlg.show();
    }

    public void hideLoadDlg() {
        if (this.mPulingDlg != null && this.mPulingDlg.isShowing()) {
            this.mPulingDlg.dismiss();
        }
    }

    public void showDeleteDlg() {
        if (this.mProgressDlg == null) {
            this.mProgressDlg = new XQProgressDialog(this);
            this.mProgressDlg.setCancelable(false);
            this.mProgressDlg.setMessage(getResources().getString(R.string.deleting));
        }
        this.mProgressDlg.show();
    }

    public void hideDeleteDlg() {
        if (this.mProgressDlg != null && this.mProgressDlg.isShowing()) {
            this.mProgressDlg.dismiss();
        }
    }

    public void onBackPressed() {
        if (this.mIsMultiSelectMode) {
            setMultiSelectMode(false);
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public int getSelectCount() {
        return this.mAdapter.getSelectedCount();
    }

    /* access modifiers changed from: protected */
    public int getDataCount() {
        return this.mAdapter.getDataCount();
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public ItemDecoration() {
            this.space = 0;
            this.space = (int) (AppConfig.d * 2.0f);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int i;
            int i2;
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition >= 0 && childAdapterPosition < AlarmDayV2Activity.this.mAdapter.getItemCount()) {
                if (recyclerView.getAdapter().getItemViewType(childAdapterPosition) == 0) {
                    rect.set(0, 0, 0, 0);
                    return;
                }
                int dayIndex = AlarmDayV2Activity.this.mAdapter.getDayIndex(childAdapterPosition);
                if (dayIndex >= 0) {
                    int i3 = dayIndex % 3;
                    if (i3 == 1) {
                        i2 = this.space * 7;
                        i = this.space;
                    } else {
                        if (i3 == 2) {
                            i = this.space;
                        } else {
                            i = this.space * 7;
                        }
                        i2 = 0;
                    }
                    rect.set(i2, 0, i, 0);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSelectedMaxium() {
        return getSelectCount() == 50;
    }
}
