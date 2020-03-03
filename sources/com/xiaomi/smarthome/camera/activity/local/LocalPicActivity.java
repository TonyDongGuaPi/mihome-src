package com.xiaomi.smarthome.camera.activity.local;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.model.local.LocalFileManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.util.ArrayList;
import java.util.List;

public class LocalPicActivity extends CameraBaseActivity {
    private View mBottomViewContainer;
    /* access modifiers changed from: private */
    public ArrayList<LocalFileManager.LocalFile> mDataList = new ArrayList<>();
    /* access modifiers changed from: private */
    public LocalFileManager.LocalFile mLocalFile;
    /* access modifiers changed from: private */
    public LocalFileManager mLocalFileManager;
    /* access modifiers changed from: private */
    public TextView mTitleView;
    private ViewPager mViewPage;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        Intent intent = getIntent();
        if (intent.getBooleanExtra("need_land", false)) {
            setRequestedOrientation(0);
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
        }
        setContentView(R.layout.camera_activity_local_pic);
        View findViewById = findViewById(R.id.title_bar);
        this.mLocalFileManager = this.mCameraDevice.b();
        this.mLocalFile = this.mLocalFileManager.b(intent.getStringExtra("file_path"));
        List<LocalFileManager.LocalFile> i = this.mLocalFileManager.i();
        if (i.isEmpty()) {
            finish();
            return;
        }
        for (int size = i.size() - 1; size >= 0; size--) {
            this.mDataList.add(i.get(size));
        }
        if (this.mLocalFile == null) {
            this.mLocalFile = this.mDataList.get(0);
        }
        initView();
        if (getRequestedOrientation() == 0) {
            this.mBottomViewContainer.setVisibility(8);
            findViewById.setVisibility(8);
        }
    }

    private void initView() {
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTitleView.setText(this.mLocalFile.c.getName());
        this.mViewPage = (ViewPager) findViewById(R.id.local_pic_pager);
        this.mViewPage.setAdapter(new PicAdapter());
        this.mBottomViewContainer = findViewById(R.id.bottom_tools_container);
        findViewById(R.id.local_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocalPicActivity.this.openSharePictureActivity("", "", LocalPicActivity.this.mLocalFile.c.getAbsolutePath());
            }
        });
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocalPicActivity.this.finish();
            }
        });
        findViewById(R.id.local_delete).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(LocalPicActivity.this.activity());
                builder.a((int) R.string.delete_title);
                builder.a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LocalPicActivity.this.mLocalFileManager.a(LocalPicActivity.this.mLocalFile);
                        Toast.makeText(LocalPicActivity.this.activity(), R.string.local_file_delete_success, 0).show();
                        LocalPicActivity.this.finish();
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
            }
        });
        int indexOf = this.mDataList.indexOf(this.mLocalFile);
        if (indexOf < 0) {
            indexOf = 0;
        }
        this.mViewPage.setCurrentItem(indexOf);
        this.mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (i > 0 && i < LocalPicActivity.this.mDataList.size()) {
                    LocalFileManager.LocalFile unused = LocalPicActivity.this.mLocalFile = (LocalFileManager.LocalFile) LocalPicActivity.this.mDataList.get(i);
                    if (LocalPicActivity.this.mLocalFile != null) {
                        LocalPicActivity.this.mTitleView.setText(LocalPicActivity.this.mLocalFile.c.getName());
                    }
                }
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (getRequestedOrientation() == 0) {
            Intent intent = new Intent(this, LocalPicActivity.class);
            intent.putExtra("file_path", this.mLocalFile.d);
            startActivity(intent);
        }
    }

    private class PicAdapter extends PagerAdapter {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private PicAdapter() {
        }

        public int getCount() {
            return LocalPicActivity.this.mDataList.size();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.mijia.model.local.LocalFileManager$LocalFile} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object instantiateItem(android.view.ViewGroup r3, int r4) {
            /*
                r2 = this;
                com.xiaomi.smarthome.camera.activity.local.LocalPicActivity r0 = com.xiaomi.smarthome.camera.activity.local.LocalPicActivity.this
                com.mijia.model.local.LocalFileManager$LocalFile r0 = r0.mLocalFile
                if (r4 < 0) goto L_0x0021
                com.xiaomi.smarthome.camera.activity.local.LocalPicActivity r1 = com.xiaomi.smarthome.camera.activity.local.LocalPicActivity.this
                java.util.ArrayList r1 = r1.mDataList
                int r1 = r1.size()
                if (r4 >= r1) goto L_0x0021
                com.xiaomi.smarthome.camera.activity.local.LocalPicActivity r0 = com.xiaomi.smarthome.camera.activity.local.LocalPicActivity.this
                java.util.ArrayList r0 = r0.mDataList
                java.lang.Object r4 = r0.get(r4)
                r0 = r4
                com.mijia.model.local.LocalFileManager$LocalFile r0 = (com.mijia.model.local.LocalFileManager.LocalFile) r0
            L_0x0021:
                com.xiaomi.smarthome.camera.view.widget.PhotoView r4 = new com.xiaomi.smarthome.camera.view.widget.PhotoView
                com.xiaomi.smarthome.camera.activity.local.LocalPicActivity r1 = com.xiaomi.smarthome.camera.activity.local.LocalPicActivity.this
                android.app.Activity r1 = r1.activity()
                r4.<init>(r1)
                r4.enable()
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER
                r4.setScaleType(r1)
                java.io.File r0 = r0.c
                java.lang.String r0 = r0.getAbsolutePath()
                r4.setFilePath(r0)
                r3.addView(r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.local.LocalPicActivity.PicAdapter.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
        }

        public void finishUpdate(ViewGroup viewGroup) {
            super.finishUpdate(viewGroup);
        }
    }
}
