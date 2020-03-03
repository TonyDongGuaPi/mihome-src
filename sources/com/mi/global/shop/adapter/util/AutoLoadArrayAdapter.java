package com.mi.global.shop.adapter.util;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomTextView;

public abstract class AutoLoadArrayAdapter<T> extends ArrayAdapter<T> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5582a = 0;
    private LoadMoreStatus b;
    private LoadMoreCallback c;

    public interface LoadMoreCallback {
        void a();
    }

    public enum LoadMoreStatus {
        idle,
        loading,
        error,
        disable
    }

    public int a(int i) {
        return 0;
    }

    public int e() {
        return 1;
    }

    public class LoadMoreViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private LoadMoreViewHolder f5585a;

        @UiThread
        public LoadMoreViewHolder_ViewBinding(LoadMoreViewHolder loadMoreViewHolder, View view) {
            this.f5585a = loadMoreViewHolder;
            loadMoreViewHolder.mContent = Utils.findRequiredView(view, R.id.load_more_content, "field 'mContent'");
            loadMoreViewHolder.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.load_more_progressBar, "field 'mProgressBar'", ProgressBar.class);
            loadMoreViewHolder.mMoreText = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.load_more_text, "field 'mMoreText'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            LoadMoreViewHolder loadMoreViewHolder = this.f5585a;
            if (loadMoreViewHolder != null) {
                this.f5585a = null;
                loadMoreViewHolder.mContent = null;
                loadMoreViewHolder.mProgressBar = null;
                loadMoreViewHolder.mMoreText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public AutoLoadArrayAdapter(Context context) {
        super(context);
    }

    public void a(LoadMoreStatus loadMoreStatus) {
        this.b = loadMoreStatus;
        notifyDataSetChanged();
    }

    public void a(LoadMoreCallback loadMoreCallback) {
        this.c = loadMoreCallback;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LoadMoreViewHolder loadMoreViewHolder;
        if (getItemViewType(i) != 0) {
            return super.getView(i, view, viewGroup);
        }
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_load_more_layout, viewGroup, false);
            loadMoreViewHolder = new LoadMoreViewHolder(view);
            view.setTag(loadMoreViewHolder);
        } else {
            loadMoreViewHolder = (LoadMoreViewHolder) view.getTag();
        }
        loadMoreViewHolder.a(this.b, this.c);
        if (this.b == LoadMoreStatus.idle) {
            a(LoadMoreStatus.loading);
        }
        return view;
    }

    public int getCount() {
        return d() + (this.b == LoadMoreStatus.disable ? 0 : 1);
    }

    public int getItemViewType(int i) {
        if (i < d()) {
            return a(i) + 1;
        }
        return 0;
    }

    public int getViewTypeCount() {
        return e() + 1;
    }

    public Object getItem(int i) {
        if (i < d()) {
            return b(i);
        }
        return null;
    }

    public int d() {
        if (this.e != null) {
            return this.e.size();
        }
        return 0;
    }

    public Object b(int i) {
        if (this.e != null) {
            return this.e.get(i);
        }
        return null;
    }

    static class LoadMoreViewHolder {
        @BindView(2131493685)
        View mContent;
        @BindView(2131493690)
        CustomTextView mMoreText;
        @BindView(2131493689)
        ProgressBar mProgressBar;

        LoadMoreViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        /* access modifiers changed from: package-private */
        public void a(LoadMoreStatus loadMoreStatus, final LoadMoreCallback loadMoreCallback) {
            switch (loadMoreStatus) {
                case error:
                    this.mProgressBar.setVisibility(8);
                    this.mMoreText.setText(R.string.more_error);
                    this.mContent.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (loadMoreCallback != null) {
                                loadMoreCallback.a();
                            }
                        }
                    });
                    return;
                case idle:
                    this.mProgressBar.setVisibility(0);
                    this.mMoreText.setText(R.string.more_loading);
                    if (loadMoreCallback != null) {
                        loadMoreCallback.a();
                    }
                    this.mContent.setOnClickListener((View.OnClickListener) null);
                    return;
                case loading:
                    this.mProgressBar.setVisibility(0);
                    this.mMoreText.setText(R.string.more_loading);
                    this.mContent.setOnClickListener((View.OnClickListener) null);
                    return;
                case disable:
                    this.mProgressBar.setVisibility(8);
                    this.mMoreText.setText(R.string.no_more);
                    this.mContent.setOnClickListener((View.OnClickListener) null);
                    return;
                default:
                    return;
            }
        }
    }
}
