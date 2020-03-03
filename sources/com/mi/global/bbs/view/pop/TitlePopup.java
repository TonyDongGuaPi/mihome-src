package com.mi.global.bbs.view.pop;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.multi_image_selector.utils.ScreenUtils;
import java.util.ArrayList;

public class TitlePopup extends PopupWindow {
    protected final int LIST_PADDING;
    /* access modifiers changed from: private */
    public ArrayList<ActionItem> mActionItems;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mIsDirty;
    /* access modifiers changed from: private */
    public OnItemOnClickListener mItemOnClickListener;
    private ListView mListView;
    private final int[] mLocation;
    private Rect mRect;
    private int mScreenHeight;
    private int mScreenWidth;
    private int popupGravity;

    public interface OnItemOnClickListener {
        void onItemClick(ActionItem actionItem, int i);
    }

    public TitlePopup(Context context) {
        this(context, -2, -2);
    }

    public TitlePopup(Context context, int i, int i2) {
        this.LIST_PADDING = 10;
        this.mRect = new Rect();
        this.mLocation = new int[2];
        this.popupGravity = 0;
        this.mActionItems = new ArrayList<>();
        this.mContext = context;
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        this.mScreenWidth = ScreenUtils.a(this.mContext).x;
        this.mScreenHeight = ScreenUtils.a(this.mContext).y;
        setWidth(i);
        setHeight(i2);
        setBackgroundDrawable(new BitmapDrawable());
        setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.bbs_title_popup, (ViewGroup) null));
        initUI();
    }

    private void initUI() {
        this.mListView = (ListView) getContentView().findViewById(R.id.title_list);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                TitlePopup.this.dismiss();
                if (TitlePopup.this.mItemOnClickListener != null) {
                    TitlePopup.this.mItemOnClickListener.onItemClick((ActionItem) TitlePopup.this.mActionItems.get(i), i);
                }
            }
        });
    }

    public void show(View view) {
        view.getLocationOnScreen(this.mLocation);
        this.mRect.set(this.mLocation[0], this.mLocation[1], this.mLocation[0] + view.getWidth(), this.mLocation[1] + ((view.getHeight() * 3) / 4));
        if (this.mIsDirty) {
            populateActions();
        }
        showAtLocation(view, this.popupGravity, (this.mScreenWidth - 10) - (getWidth() / 2), this.mRect.bottom);
    }

    private void populateActions() {
        this.mIsDirty = false;
        this.mListView.setAdapter(new BaseAdapter() {
            public long getItemId(int i) {
                return (long) i;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2;
                ViewHolder viewHolder;
                LayoutInflater from = LayoutInflater.from(TitlePopup.this.mContext);
                if (view == null) {
                    viewHolder = new ViewHolder();
                    view2 = from.inflate(R.layout.bbs_pop_list_item_layout, (ViewGroup) null);
                    viewHolder.imgIcon = (ImageView) view2.findViewById(R.id.pop_item_icon);
                    viewHolder.itemTitle = (TextView) view2.findViewById(R.id.pop_item_text);
                    viewHolder.layoutMes = (LinearLayout) view2.findViewById(R.id.ly_profile_count);
                    viewHolder.itemMes = (TextView) view2.findViewById(R.id.tv_profile_count);
                    view2.setTag(viewHolder);
                } else {
                    view2 = view;
                    viewHolder = (ViewHolder) view.getTag();
                }
                ActionItem actionItem = (ActionItem) TitlePopup.this.mActionItems.get(i);
                viewHolder.imgIcon.setImageResource(actionItem.mDrawable);
                viewHolder.itemTitle.setText(actionItem.mTitle);
                viewHolder.layoutMes.setVisibility(8);
                if (!TextUtils.isEmpty(actionItem.mes)) {
                    try {
                        if (Integer.valueOf(actionItem.mes).intValue() > 0) {
                            viewHolder.layoutMes.setVisibility(0);
                            viewHolder.itemMes.setText(actionItem.mes);
                        } else {
                            viewHolder.layoutMes.setVisibility(8);
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    viewHolder.layoutMes.setVisibility(8);
                }
                return view2;
            }

            public Object getItem(int i) {
                return TitlePopup.this.mActionItems.get(i);
            }

            public int getCount() {
                return TitlePopup.this.mActionItems.size();
            }
        });
    }

    static class ViewHolder {
        public ImageView imgIcon;
        public TextView itemMes;
        public TextView itemTitle;
        public LinearLayout layoutMes;

        ViewHolder() {
        }
    }

    public void addAction(ActionItem actionItem) {
        if (actionItem != null) {
            this.mActionItems.add(actionItem);
            this.mIsDirty = true;
        }
    }

    public void cleanAction() {
        if (!this.mActionItems.isEmpty()) {
            this.mActionItems.clear();
            this.mIsDirty = true;
        }
    }

    public ActionItem getAction(int i) {
        if (i < 0 || i > this.mActionItems.size()) {
            return null;
        }
        return this.mActionItems.get(i);
    }

    public void setItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.mItemOnClickListener = onItemOnClickListener;
    }
}
