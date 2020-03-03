package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.CategoryGroup;
import com.mi.global.bbs.model.CategoryItem;
import com.mi.global.bbs.utils.ImeUtils;
import java.util.ArrayList;
import java.util.List;

public class CategoryView extends LinearLayout implements PopupWindow.OnDismissListener {
    static final int TYPE_CHILD = 1;
    static final int TYPE_GROUP = 0;
    static final int TYPE_QUESION = 2;
    private CategoryAdapter adapter;
    private int currentPosition;
    private boolean isShowPopupWindow;
    @BindView(2131493609)
    TextView mCategoryTextView;
    private ListView mListView;
    public AdapterView.OnItemClickListener onItemClickListener;
    private PopupWindow popupWindow;

    public CategoryView(Context context) {
        super(context);
        this.currentPosition = 0;
        this.isShowPopupWindow = false;
        init((AttributeSet) null, 0);
    }

    public CategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.currentPosition = 0;
        this.isShowPopupWindow = false;
        init(attributeSet, 0);
    }

    public CategoryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currentPosition = 0;
        this.isShowPopupWindow = false;
        init(attributeSet, i);
    }

    public CategoryView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        LayoutInflater.from(getContext()).inflate(R.layout.bbs_forum_category_item, this, true);
        ButterKnife.bind((View) this);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.white_pressed_selector));
        this.popupWindow = new PopupWindow(getContext());
        this.popupWindow.setBackgroundDrawable((Drawable) null);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setOnDismissListener(this);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.bbs_pop_list_layout, (ViewGroup) null, false);
        this.mListView = (ListView) inflate.findViewById(R.id.pop_list);
        this.adapter = new CategoryAdapter((List<String>) null);
        this.mListView.setAdapter(this.adapter);
        this.mListView.setOnItemClickListener(this.adapter);
        this.popupWindow.setContentView(inflate);
        this.popupWindow.setWidth(-1);
        this.popupWindow.setHeight(-2);
        setClickable(true);
    }

    public void setText(CharSequence charSequence) {
        this.mCategoryTextView.setText(charSequence);
    }

    public void setText(int i) {
        this.mCategoryTextView.setText(i);
    }

    public void getText() {
        this.mCategoryTextView.getText();
    }

    public void setStringList(List<String> list, int i, int i2) {
        if (list != null && list.size() > 0) {
            if (i < 0) {
                if (i2 == 1) {
                    setText(R.string.select_a_forum);
                } else if (i2 == 0) {
                    setText(R.string.select_category);
                } else if (i2 == 2) {
                    setText(R.string.qa_question_category_hint);
                }
                this.mCategoryTextView.setTextColor(getResources().getColor(R.color.discover_item_advanced_color));
            } else {
                setText((CharSequence) list.get(i));
                this.mCategoryTextView.setTextColor(-16777216);
            }
            setCurrentPosition(i);
            this.adapter.update(list);
        }
    }

    public void setCategoryGroupList(List<CategoryGroup> list, String str) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            int i = -1;
            for (int i2 = 0; i2 < size; i2++) {
                String name = list.get(i2).getName();
                if (!TextUtils.isEmpty(str) && str.equals(name)) {
                    i = i2;
                }
                arrayList.add(name);
            }
            setStringList(arrayList, i, 0);
        }
    }

    public void setCategoryItemList(List<CategoryItem> list) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(list.get(i).getName());
            }
            setStringList(arrayList, -1, 1);
        }
    }

    public void setQuesionItemList(List<CategoryGroup> list, String str) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            int i = -1;
            for (int i2 = 0; i2 < size; i2++) {
                String name = list.get(i2).getName();
                if (!TextUtils.isEmpty(str) && str.equals(name)) {
                    i = i2;
                }
                arrayList.add(name);
            }
            setStringList(arrayList, i, 2);
        }
    }

    public boolean performClick() {
        ImeUtils.hideIme(this);
        if (this.isShowPopupWindow) {
            dismiss();
        } else {
            show();
        }
        return super.performClick();
    }

    private void show() {
        this.isShowPopupWindow = true;
        if (this.popupWindow != null) {
            this.popupWindow.showAsDropDown(this);
        }
    }

    /* access modifiers changed from: private */
    public void dismiss() {
        if (this.popupWindow != null) {
            this.popupWindow.dismiss();
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public void setCurrentPosition(int i) {
        this.currentPosition = i;
    }

    public void onDismiss() {
        this.isShowPopupWindow = false;
    }

    public class CategoryAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
        private List<String> categoryItems;

        public long getItemId(int i) {
            return (long) i;
        }

        public CategoryAdapter(List<String> list) {
            this.categoryItems = list;
        }

        public void update(List<String> list) {
            if (this.categoryItems == null) {
                this.categoryItems = list;
            } else {
                this.categoryItems.clear();
                this.categoryItems.addAll(list);
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.categoryItems != null) {
                return this.categoryItems.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return this.categoryItems.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(CategoryView.this.getContext()).inflate(R.layout.bbs_simple_text_item, viewGroup, false);
                viewHolder = new ViewHolder();
                TextView unused = viewHolder.mTextView = (TextView) view.findViewById(R.id.mSimpleTextView);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mTextView.setText(this.categoryItems.get(i));
            return view;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CategoryView.this.setText((CharSequence) this.categoryItems.get(i));
            CategoryView.this.mCategoryTextView.setTextColor(-16777216);
            CategoryView.this.setCurrentPosition(i);
            CategoryView.this.dismiss();
            if (CategoryView.this.onItemClickListener != null) {
                CategoryView.this.onItemClickListener.onItemClick(adapterView, view, i, j);
            }
        }

        public class ViewHolder {
            /* access modifiers changed from: private */
            public TextView mTextView;

            public ViewHolder() {
            }
        }
    }
}
