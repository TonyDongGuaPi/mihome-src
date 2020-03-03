package com.mi.global.bbs.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectDateDialog extends Dialog {
    /* access modifiers changed from: private */
    public int mCurrentDay = 0;
    /* access modifiers changed from: private */
    public int mCurrentMonth = 0;
    @BindView(2131493180)
    LoopView mDayView;
    @BindView(2131493650)
    LoopView mMonthView;
    OnSelectCompletedListener mOnSelectCompletedListener;
    @BindView(2131494093)
    FontTextView mTitle;
    /* access modifiers changed from: private */
    public String[] months = null;

    public interface OnSelectCompletedListener {
        void onSelectCompleted(int i, int i2);
    }

    public SelectDateDialog(@NonNull Context context, int i, int i2) {
        super(context);
        setContentView(R.layout.select_calendar_dialog);
        ButterKnife.bind((Dialog) this);
        this.mCurrentMonth = i;
        this.mCurrentDay = i2 > 0 ? i2 - 1 : 0;
        this.months = context.getResources().getStringArray(R.array.bbs_months);
        this.mMonthView.setItems(Arrays.asList(this.months));
        this.mMonthView.setInitPosition(this.mCurrentMonth);
        this.mDayView.setItems(getDayItems());
        this.mDayView.setInitPosition(this.mCurrentDay);
        FontTextView fontTextView = this.mTitle;
        fontTextView.setText(this.months[this.mCurrentMonth] + " " + (this.mCurrentDay + 1));
        this.mDayView.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int unused = SelectDateDialog.this.mCurrentDay = i;
                FontTextView fontTextView = SelectDateDialog.this.mTitle;
                fontTextView.setText(SelectDateDialog.this.months[SelectDateDialog.this.mCurrentMonth] + " " + (SelectDateDialog.this.mCurrentDay + 1));
            }
        });
        this.mMonthView.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int unused = SelectDateDialog.this.mCurrentMonth = i;
                int unused2 = SelectDateDialog.this.mCurrentDay = 0;
                FontTextView fontTextView = SelectDateDialog.this.mTitle;
                fontTextView.setText(SelectDateDialog.this.months[SelectDateDialog.this.mCurrentMonth] + " " + (SelectDateDialog.this.mCurrentDay + 1));
                SelectDateDialog.this.mDayView.setItems(SelectDateDialog.this.getDayItems());
                SelectDateDialog.this.mDayView.setCurrentPosition(SelectDateDialog.this.mCurrentDay);
            }
        });
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    /* access modifiers changed from: private */
    public List<String> getDayItems() {
        ArrayList arrayList = new ArrayList();
        int i = isBigMonth() ? 31 : 30;
        for (int i2 = 1; i2 <= i; i2++) {
            arrayList.add(String.valueOf(i2));
        }
        return arrayList;
    }

    private boolean isBigMonth() {
        int i = this.mCurrentMonth + 1;
        if (!(i == 1 || i == 3 || i == 5 || i == 10 || i == 12)) {
            switch (i) {
                case 7:
                case 8:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    @OnClick({2131493045, 2131493758})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (R.id.cancel == id) {
            dismiss();
        } else if (R.id.ok == id) {
            if (this.mOnSelectCompletedListener != null) {
                this.mOnSelectCompletedListener.onSelectCompleted(this.mCurrentMonth, this.mCurrentDay);
            }
            dismiss();
        }
    }

    public void setOnSelectCompletedListener(OnSelectCompletedListener onSelectCompletedListener) {
        this.mOnSelectCompletedListener = onSelectCompletedListener;
    }
}
