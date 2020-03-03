package com.mi.global.bbs.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.view.TouchImageView;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;

public class WatchImageFragment extends BaseFragment implements WatchBigPicActivity.SaveImageListener {
    private static final String TAG = "WatchImageFragment";
    boolean isSaved = false;
    boolean loading = false;
    String mImageUrl = null;
    @BindView(2131493787)
    TouchImageView mPhotoView;
    @BindView(2131493556)
    RelativeLayout relPhotoView;

    public static WatchImageFragment newInstance(String str) {
        WatchImageFragment watchImageFragment = new WatchImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, str);
        watchImageFragment.setArguments(bundle);
        return watchImageFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mImageUrl = getArguments().getString(TAG);
        this.isSaved = false;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_fragment_watch_img, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((WatchBigPicActivity) getActivity()).addSaveImageListener(this);
        GoogleTrackerUtil.sendRecordPage(TAG);
        ImageLoader.showImage(this.mPhotoView, this.mImageUrl, (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_sub_forum_icon)).b(R.drawable.bbs_sub_forum_icon));
        this.mPhotoView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (WatchImageFragment.this.getActivity() != null) {
                    WatchImageFragment.this.getActivity().finish();
                }
            }
        });
    }

    public void onSaveImageAction(String str) {
        if (str.equals(this.mImageUrl)) {
            saveBitmap();
        }
    }

    public void saveBitmap() {
        if (this.isSaved || this.loading) {
            toast(R.string.You_have_saved_this_picture);
            return;
        }
        this.loading = true;
        Observable.just(this.mImageUrl).map(new Function<String, File>() {
            public File apply(@NonNull String str) throws Exception {
                return ImageUtil.saveBitmap(WatchImageFragment.this.getString(R.string.mi_community) + System.currentTimeMillis(), (Bitmap) Glide.a((Fragment) WatchImageFragment.this).j().a(str).b().get());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<File>() {
            public void accept(@NonNull File file) throws Exception {
                if (WatchImageFragment.this.getActivity() != null) {
                    WatchImageFragment.this.getActivity().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
                    WatchImageFragment.this.isSaved = true;
                    WatchImageFragment.this.loading = false;
                    WatchImageFragment.this.toast(R.string.saved_successfully);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                WatchImageFragment.this.loading = false;
                WatchImageFragment.this.isSaved = false;
            }
        });
    }

    public void onDestroyView() {
        ((WatchBigPicActivity) getActivity()).removeSaveImageListener(this);
        super.onDestroyView();
    }
}
