package com.taobao.weex.ui.component.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class DefaultDragHelper implements DragHelper {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String EVENT_END_DRAG = "dragend";
    private static final String EVENT_START_DRAG = "dragstart";
    private static final String TAG = "WXListExComponent";
    private static final String TAG_EXCLUDED = "drag_excluded";
    private boolean isDraggable = false;
    @NonNull
    private final List<WXComponent> mDataSource;
    @NonNull
    private final EventTrigger mEventTrigger;
    @NonNull
    private ItemTouchHelper mItemTouchHelper;
    private boolean mLongPressEnabled;
    @NonNull
    private final RecyclerView mRecyclerView;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6284392625092375273L, "com/taobao/weex/ui/component/list/DefaultDragHelper", 60);
        $jacocoData = a2;
        return a2;
    }

    DefaultDragHelper(@NonNull List<WXComponent> list, @NonNull RecyclerView recyclerView, @NonNull EventTrigger eventTrigger) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDataSource = list;
        this.mEventTrigger = eventTrigger;
        this.mRecyclerView = recyclerView;
        $jacocoInit[0] = true;
        this.mItemTouchHelper = new ItemTouchHelper(new DragSupportCallback(this, true));
        try {
            $jacocoInit[1] = true;
            this.mItemTouchHelper.attachToRecyclerView(this.mRecyclerView);
            $jacocoInit[2] = true;
        } catch (Throwable unused) {
            $jacocoInit[3] = true;
        }
        $jacocoInit[4] = true;
    }

    public void onDragStart(@NonNull WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            WXLogUtils.d(TAG, "list on drag start : from index " + i);
            $jacocoInit[7] = true;
        }
        this.mEventTrigger.triggerEvent(EVENT_START_DRAG, buildEvent(wXComponent.getRef(), i, -1));
        $jacocoInit[8] = true;
    }

    public void onDragEnd(@NonNull WXComponent wXComponent, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
            WXLogUtils.d(TAG, "list on drag end : from index " + i + " to index " + i2);
            $jacocoInit[11] = true;
        }
        this.mEventTrigger.triggerEvent(EVENT_END_DRAG, buildEvent(wXComponent.getRef(), i, i2));
        $jacocoInit[12] = true;
    }

    public void onDragging(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            WXLogUtils.d(TAG, "list on dragging : from index " + i + " to index " + i2);
            $jacocoInit[15] = true;
        }
        RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
        if (adapter == null) {
            $jacocoInit[16] = true;
            WXLogUtils.e(TAG, "drag failed because of RecyclerView#Adapter is not bound");
            $jacocoInit[17] = true;
            return;
        }
        if (i < 0) {
            $jacocoInit[18] = true;
        } else if (i > this.mDataSource.size() - 1) {
            $jacocoInit[19] = true;
        } else if (i2 < 0) {
            $jacocoInit[20] = true;
        } else if (i2 > this.mDataSource.size() - 1) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            Collections.swap(this.mDataSource, i, i2);
            $jacocoInit[23] = true;
            adapter.notifyItemMoved(i, i2);
            $jacocoInit[24] = true;
        }
        $jacocoInit[25] = true;
    }

    public boolean isLongPressDragEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mLongPressEnabled;
        $jacocoInit[26] = true;
        return z;
    }

    public void setLongPressDragEnabled(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLongPressEnabled = z;
        $jacocoInit[27] = true;
    }

    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.isDraggable) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            this.mItemTouchHelper.startDrag(viewHolder);
            $jacocoInit[30] = true;
        }
        $jacocoInit[31] = true;
    }

    public boolean isDraggable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isDraggable;
        $jacocoInit[32] = true;
        return z;
    }

    public void setDraggable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isDraggable = z;
        $jacocoInit[33] = true;
    }

    public void setDragExcluded(@NonNull RecyclerView.ViewHolder viewHolder, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (viewHolder.itemView == null) {
            $jacocoInit[34] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[35] = true;
            } else {
                $jacocoInit[36] = true;
                WXLogUtils.e(TAG, "[error] viewHolder.itemView is null");
                $jacocoInit[37] = true;
            }
            $jacocoInit[38] = true;
            return;
        }
        if (z) {
            $jacocoInit[39] = true;
            viewHolder.itemView.setTag(TAG_EXCLUDED);
            $jacocoInit[40] = true;
        } else {
            viewHolder.itemView.setTag((Object) null);
            $jacocoInit[41] = true;
        }
        $jacocoInit[42] = true;
    }

    public boolean isDragExcluded(@NonNull RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (viewHolder.itemView == null) {
            $jacocoInit[43] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[44] = true;
            } else {
                $jacocoInit[45] = true;
                WXLogUtils.e(TAG, "[error] viewHolder.itemView is null");
                $jacocoInit[46] = true;
            }
            $jacocoInit[47] = true;
            return false;
        }
        if (viewHolder.itemView.getTag() == null) {
            $jacocoInit[48] = true;
        } else if (!TAG_EXCLUDED.equals(viewHolder.itemView.getTag())) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
            z = true;
            $jacocoInit[52] = true;
            return z;
        }
        $jacocoInit[51] = true;
        $jacocoInit[52] = true;
        return z;
    }

    private Map<String, Object> buildEvent(@Nullable String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(4);
        $jacocoInit[53] = true;
        if (str == null) {
            str = "unknown";
            $jacocoInit[54] = true;
        } else {
            $jacocoInit[55] = true;
        }
        hashMap.put(TouchesHelper.TARGET_KEY, str);
        $jacocoInit[56] = true;
        hashMap.put("fromIndex", Integer.valueOf(i));
        $jacocoInit[57] = true;
        hashMap.put("toIndex", Integer.valueOf(i2));
        $jacocoInit[58] = true;
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        $jacocoInit[59] = true;
        return hashMap;
    }
}
