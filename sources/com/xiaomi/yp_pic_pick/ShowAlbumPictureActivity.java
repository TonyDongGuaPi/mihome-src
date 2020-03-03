package com.xiaomi.yp_pic_pick;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.yp_pic_pick.adapter.SelectImageAdapter;
import com.xiaomi.yp_pic_pick.bean.PictureAlbum;
import com.xiaomi.yp_pic_pick.bean.PictureItem;
import com.xiaomi.yp_ui.utils.FrescoImageLoader;
import com.xiaomi.yp_ui.widget.CommonLoadingView;
import com.xiaomi.yp_ui.widget.XMTitleBar;
import com.xiaomi.yp_ui.widget.zoomable.DoubleTapGestureListener;
import com.xiaomi.yp_ui.widget.zoomable.ZoomableDraweeView;
import com.xiaomi.yp_ui.widget.zoomable.ZoomableDraweeViewSupport;
import java.io.File;
import java.util.ArrayList;

public class ShowAlbumPictureActivity extends FragmentActivity implements View.OnClickListener {
    public static final int RESULT_OK_SEND = 109;
    public static final int TYPE_PRE_SEE_ALBUM = 1;
    public static final int TYPE_PRE_SEE_BUTTON = 2;

    /* renamed from: a  reason: collision with root package name */
    private int f19501a = 6;
    private ShowPictureAdapter b;
    /* access modifiers changed from: private */
    public int c;
    private int d;
    /* access modifiers changed from: private */
    public XMTitleBar e;
    private int f;
    private TextView g;
    /* access modifiers changed from: private */
    public View h;
    /* access modifiers changed from: private */
    public RecyclerView i;
    private View j;
    /* access modifiers changed from: private */
    public SelectImageAdapter k;
    /* access modifiers changed from: private */
    public PictureAlbum l;
    ArrayList<PictureItem> mShowItems;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_show_album_picture);
        a();
        b();
    }

    private void a() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        this.c = extras.getInt("position");
        this.d = extras.getInt("AlbumPosition");
        this.f19501a = extras.getInt("canSelectMaxImages");
        this.f = extras.getInt("PreviewType");
        if (this.f == 1) {
            ArrayList<PictureAlbum> arrayList = PicturePickActivity.mPictureAlbums;
            if (arrayList != null) {
                this.l = arrayList.get(this.d);
                ArrayList<PictureItem> a2 = this.l.a();
                if (this.d == 0) {
                    this.mShowItems = new ArrayList<>(a2);
                    this.mShowItems.remove(0);
                    this.c--;
                    return;
                }
                this.mShowItems = a2;
                return;
            }
            return;
        }
        this.mShowItems = new ArrayList<>(PicturePickActivity.mSelectedPicture);
    }

    private void b() {
        TitleBarUtil.a(getWindow());
        final ViewPager viewPager = (ViewPager) findViewById(R.id.image_list);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        this.b = new ShowPictureAdapter(this, this.mShowItems);
        viewPager.setPageMargin(10);
        viewPager.setAdapter(this.b);
        viewPager.setCurrentItem(this.c, false);
        this.e = (XMTitleBar) findViewById(R.id.title_bar);
        TitleBarUtil.a((View) this.e);
        this.e.setupRightImageButton(getResources().getDrawable(R.drawable.icon_img_unselect_big));
        this.e.setLeftClickListener(this);
        this.e.setRightClickListener(this);
        this.g = (TextView) findViewById(R.id.confirm);
        this.g.setOnClickListener(this);
        this.h = findViewById(R.id.already_select_layout);
        this.i = (RecyclerView) findViewById(R.id.already_select);
        this.i.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.i.setLayoutManager(linearLayoutManager);
        this.k = new SelectImageAdapter(this);
        this.i.setAdapter(this.k);
        this.k.a((SelectImageAdapter.OnItemClickListener) new SelectImageAdapter.OnItemClickListener() {
            public void a(int i) {
                if (PicturePickActivity.mSelectedPicture != null) {
                    PictureItem pictureItem = PicturePickActivity.mSelectedPicture.get(i);
                    if (pictureItem.f().equals(ShowAlbumPictureActivity.this.l.c()) || "null".equals(ShowAlbumPictureActivity.this.l.c())) {
                        for (int i2 = 0; i2 < ShowAlbumPictureActivity.this.mShowItems.size(); i2++) {
                            if (pictureItem == ShowAlbumPictureActivity.this.mShowItems.get(i2)) {
                                viewPager.setCurrentItem(i2);
                                ShowAlbumPictureActivity.this.k.a(i);
                                return;
                            }
                        }
                    }
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                ShowAlbumPictureActivity.this.a(i);
            }
        });
        this.j = findViewById(R.id.bottom_layout);
        c();
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.k.a(b(i2));
    }

    private int b(int i2) {
        if (this.mShowItems != null && this.mShowItems.size() > i2 && i2 >= 0) {
            PictureItem pictureItem = this.mShowItems.get(i2);
            if (PicturePickActivity.mSelectedPicture != null && PicturePickActivity.mSelectedPicture.size() > 0) {
                for (int i3 = 0; i3 < PicturePickActivity.mSelectedPicture.size(); i3++) {
                    if (pictureItem == PicturePickActivity.mSelectedPicture.get(i3)) {
                        return i3;
                    }
                }
            }
        }
        return -1;
    }

    private void c() {
        boolean z = false;
        int size = PicturePickActivity.mSelectedPicture != null ? PicturePickActivity.mSelectedPicture.size() : 0;
        this.g.setText(getString(R.string.select_ok, new Object[]{Integer.valueOf(size), Integer.valueOf(this.f19501a)}));
        this.h.setVisibility(size == 0 ? 8 : 0);
        TextView textView = this.g;
        if (size != 0) {
            z = true;
        }
        textView.setEnabled(z);
        this.k.a(PicturePickActivity.mSelectedPicture, b(this.c));
        this.i.postDelayed(new Runnable() {
            public void run() {
                ShowAlbumPictureActivity.this.i.smoothScrollToPosition(ShowAlbumPictureActivity.this.k.getItemCount());
            }
        }, 200);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bar_left_button) {
            onBackPressed();
        } else if (id == R.id.bar_right_button) {
            if (PicturePickActivity.mSelectedPicture != null) {
                PictureItem pictureItem = this.mShowItems.get(this.c);
                if (pictureItem.c()) {
                    pictureItem.a(false);
                    PicturePickActivity.mSelectedPicture.remove(pictureItem);
                    this.e.setupRightImageButton(getResources().getDrawable(R.drawable.icon_img_unselect_big));
                } else if (PicturePickActivity.mSelectedPicture.size() >= this.f19501a) {
                    ModuleToastManager a2 = ModuleToastManager.a();
                    a2.a(this, "最多只能选择" + this.f19501a + "张图哦！");
                } else {
                    pictureItem.a(true);
                    PicturePickActivity.mSelectedPicture.add(pictureItem);
                    this.e.setupRightImageButton(getResources().getDrawable(R.drawable.icon_img_select_big));
                }
                c();
            }
        } else if (id == R.id.confirm) {
            Intent intent = new Intent();
            intent.putExtra("confirm", true);
            setResult(-1, intent);
            finish();
        }
    }

    public void singleClick() {
        if (this.e.getVisibility() == 0) {
            this.e.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_exit));
            this.e.setVisibility(8);
            this.j.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_exit));
            this.j.setVisibility(8);
            this.h.setVisibility(8);
            return;
        }
        this.e.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_enter));
        this.e.setVisibility(0);
        this.j.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_enter));
        this.j.setVisibility(0);
        this.h.postDelayed(new Runnable() {
            public void run() {
                int i = 0;
                int size = PicturePickActivity.mSelectedPicture != null ? PicturePickActivity.mSelectedPicture.size() : 0;
                View access$400 = ShowAlbumPictureActivity.this.h;
                if (size == 0) {
                    i = 8;
                }
                access$400.setVisibility(i);
            }
        }, 250);
    }

    static class ShowPictureAdapter extends PagerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private ArrayList<PictureItem> f19507a;
        private ShowAlbumPictureActivity b;
        private boolean c = false;
        private ZoomableDraweeViewSupport d;

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public ShowPictureAdapter(ShowAlbumPictureActivity showAlbumPictureActivity, ArrayList<PictureItem> arrayList) {
            this.f19507a = arrayList;
            this.b = showAlbumPictureActivity;
        }

        public int getCount() {
            if (this.f19507a == null) {
                return 0;
            }
            return this.f19507a.size();
        }

        /* renamed from: a */
        public View instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.image_item_preview, viewGroup, false);
            ViewBean viewBean = new ViewBean();
            viewBean.f19509a = (ZoomableDraweeViewSupport) inflate.findViewById(R.id.image_preview);
            viewBean.b = (CommonLoadingView) inflate.findViewById(R.id.loading_view);
            viewBean.b.setBackground((Drawable) null);
            CustDoubleTapGestureListener custDoubleTapGestureListener = new CustDoubleTapGestureListener(this.b, viewBean.f19509a);
            custDoubleTapGestureListener.a((Activity) this.b);
            viewBean.f19509a.setTapListener(custDoubleTapGestureListener);
            inflate.setTag(viewBean);
            viewGroup.addView(inflate);
            this.c = false;
            viewBean.b.startAnimation();
            return inflate;
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            final ViewBean viewBean = (ViewBean) ((View) obj).getTag();
            if (viewBean == null) {
                return;
            }
            if (this.b.c != i || !this.c) {
                this.c = true;
                int unused = this.b.c = i;
                this.d = viewBean.f19509a;
                PictureItem pictureItem = this.f19507a.get(i);
                if (pictureItem.c()) {
                    this.b.e.setupRightImageButton(this.b.getResources().getDrawable(R.drawable.icon_img_select_big));
                } else {
                    this.b.e.setupRightImageButton(this.b.getResources().getDrawable(R.drawable.icon_img_unselect_big));
                }
                String uri = Uri.fromFile(new File(pictureItem.b())).toString();
                if (!TextUtils.isEmpty(uri)) {
                    new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) this.d).a(uri).a(ScalingUtils.ScaleType.FIT_CENTER).b(0).a((ControllerListener) new ControllerListener() {
                        public void onFailure(String str, Throwable th) {
                        }

                        public void onIntermediateImageFailed(String str, Throwable th) {
                        }

                        public void onIntermediateImageSet(String str, Object obj) {
                        }

                        public void onRelease(String str) {
                        }

                        public void onSubmit(String str, Object obj) {
                        }

                        public void onFinalImageSet(String str, Object obj, Animatable animatable) {
                            if (obj instanceof ImageInfo) {
                                viewBean.b.setVisibility(8);
                                viewBean.b.stopAnimation();
                            }
                        }
                    }).a().a();
                    this.d.setTag(uri);
                    return;
                }
                viewBean.b.setVisibility(8);
                viewBean.b.stopAnimation();
            }
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        private static class ViewBean {

            /* renamed from: a  reason: collision with root package name */
            ZoomableDraweeViewSupport f19509a;
            CommonLoadingView b;

            private ViewBean() {
            }
        }
    }

    static class CustDoubleTapGestureListener extends DoubleTapGestureListener {

        /* renamed from: a  reason: collision with root package name */
        ShowAlbumPictureActivity f19506a;

        public CustDoubleTapGestureListener(ShowAlbumPictureActivity showAlbumPictureActivity, ZoomableDraweeView zoomableDraweeView) {
            super(zoomableDraweeView);
            this.f19506a = showAlbumPictureActivity;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            this.f19506a.singleClick();
            return true;
        }
    }

    public void onBackPressed() {
        setResult(-1, new Intent());
        finish();
    }

    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float b = 0.8f;
        private static final float c = 0.5f;

        ZoomOutPageTransformer() {
        }

        @TargetApi(11)
        public void transformPage(View view, float f) {
            int width = view.getWidth();
            int height = view.getHeight();
            if (f < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f <= 1.0f) {
                float max = Math.max(0.8f, 1.0f - Math.abs(f));
                float f2 = 1.0f - max;
                float f3 = (((float) height) * f2) / 2.0f;
                float f4 = (((float) width) * f2) / 2.0f;
                if (f < 0.0f) {
                    view.setTranslationX(f4 - (f3 / 2.0f));
                } else {
                    view.setTranslationX((-f4) + (f3 / 2.0f));
                }
                view.setScaleX(max);
                view.setScaleY(max);
                view.setAlpha((((max - 0.8f) / 0.19999999f) * 0.5f) + 0.5f);
            } else {
                view.setAlpha(0.0f);
            }
        }
    }
}
