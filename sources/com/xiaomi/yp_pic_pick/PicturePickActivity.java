package com.xiaomi.yp_pic_pick;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.youpin.common.cache.FileCache;
import com.xiaomi.youpin.common.cache.StringCache;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.common.util.crypto.MD5Utils;
import com.xiaomi.youpin.yp_permission.PermissionCallback;
import com.xiaomi.youpin.yp_permission.YouPinPermissionManager;
import com.xiaomi.yp_pic_pick.adapter.AlbumPickAdapter;
import com.xiaomi.yp_pic_pick.adapter.PicturePickAdapter;
import com.xiaomi.yp_pic_pick.bean.PictureAlbum;
import com.xiaomi.yp_pic_pick.bean.PictureItem;
import com.xiaomi.yp_pic_pick.utils.FileUtils;
import com.xiaomi.yp_pic_pick.utils.Utils;
import com.xiaomi.yp_ui.widget.XMTitleBar;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import com.yanzhenjie.yp_permission.FileProvider;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class PicturePickActivity extends FragmentActivity implements View.OnClickListener {
    public static final int MAX_SELECTED_IMAGES = 6;
    public static final int REQUEST_PRE_SEE = 108;

    /* renamed from: a  reason: collision with root package name */
    private static final int f19487a = 101;
    private static final String b = "images";
    public static ArrayList<PictureAlbum> mPictureAlbums = null;
    public static ArrayList<PictureItem> mSelectedPicture = null;
    private static final int o = 1;
    private static final int p = 2;
    private static final int q = 3;
    String CurrentAlbumName;
    /* access modifiers changed from: private */
    public int c = 6;
    private RecyclerView d;
    private String e;
    private ImagesLoadTask f;
    /* access modifiers changed from: private */
    public PicturePickAdapter g;
    /* access modifiers changed from: private */
    public ViewStub h;
    private RecyclerView i;
    boolean isNeedCompress = false;
    boolean isRn = false;
    private AlbumPickAdapter j;
    private TextView k;
    /* access modifiers changed from: private */
    public XMTitleBar l;
    /* access modifiers changed from: private */
    public TextView m;
    int mCurrentAlbumPosition = 0;
    int mToastID;
    private FileCache n;
    int showType = 3;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.picture_pick_layout);
        TitleBarUtil.b(getWindow());
        mSelectedPicture = new ArrayList<>(6);
        this.n = StringCache.b(this, "pic_pick", 20);
        a(getIntent().getStringExtra("url"));
        a();
        boolean z = true;
        if (this.showType == 1) {
            c();
            return;
        }
        if (this.showType != 3) {
            z = false;
        }
        this.f = new ImagesLoadTask(this, z);
        this.f.a((ImagesLoadTask.OnLoadFinishListener) new ImagesLoadTask.OnLoadFinishListener() {
            public void a(ArrayList<PictureAlbum> arrayList) {
                if (!PicturePickActivity.this.isFinishing() && arrayList != null) {
                    PicturePickActivity.this.updateView(arrayList);
                }
            }
        });
        this.f.execute(new String[0]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r8) {
        /*
            r7 = this;
            java.util.Map r8 = com.xiaomi.youpin.common.util.UrlUtils.d(r8)
            java.lang.String r0 = "alreadySelectedImages"
            boolean r0 = r8.containsKey(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001a
            java.lang.String r0 = "alreadySelectedImages"
            java.lang.Object r0 = r8.get(r0)     // Catch:{ NumberFormatException -> 0x001a }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x001a }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x001a }
            goto L_0x001b
        L_0x001a:
            r0 = 0
        L_0x001b:
            r2 = 6
            java.lang.String r3 = "maxSelectedImages"
            boolean r3 = r8.containsKey(r3)
            if (r3 == 0) goto L_0x0031
            java.lang.String r3 = "maxSelectedImages"
            java.lang.Object r3 = r8.get(r3)     // Catch:{ NumberFormatException -> 0x0031 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x0031 }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x0031 }
            r2 = r3
        L_0x0031:
            java.lang.String r3 = "openCameraDirectly"
            boolean r3 = r8.containsKey(r3)
            r4 = 3
            r5 = 1
            if (r3 == 0) goto L_0x004e
            java.lang.String r3 = "true"
            java.lang.String r6 = "openCameraDirectly"
            java.lang.Object r6 = r8.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            boolean r3 = r3.equalsIgnoreCase(r6)
            if (r3 == 0) goto L_0x004e
            r7.showType = r5
            goto L_0x0050
        L_0x004e:
            r7.showType = r4
        L_0x0050:
            java.lang.String r3 = "isNeedCompress"
            boolean r3 = r8.containsKey(r3)
            if (r3 == 0) goto L_0x0069
            java.lang.String r3 = "true"
            java.lang.String r6 = "isNeedCompress"
            java.lang.Object r6 = r8.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            boolean r3 = r3.equalsIgnoreCase(r6)
            if (r3 == 0) goto L_0x0069
            r1 = 1
        L_0x0069:
            r7.isNeedCompress = r1
            java.lang.String r1 = "rn"
            boolean r1 = r8.containsKey(r1)
            if (r1 == 0) goto L_0x0077
            r7.isRn = r5
            r7.isNeedCompress = r5
        L_0x0077:
            boolean r1 = r7.isRn
            if (r1 == 0) goto L_0x00b3
            java.lang.String r1 = "type"
            boolean r1 = r8.containsKey(r1)
            if (r1 == 0) goto L_0x00b3
            java.lang.String r1 = "type"
            java.lang.Object r8 = r8.get(r1)
            java.lang.String r8 = (java.lang.String) r8
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 != 0) goto L_0x00b1
            java.lang.String r1 = "default"
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x009a
            goto L_0x00b1
        L_0x009a:
            java.lang.String r1 = "onlycamera"
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x00a5
            r7.showType = r5
            goto L_0x00b3
        L_0x00a5:
            java.lang.String r1 = "hidecamera"
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L_0x00b3
            r8 = 2
            r7.showType = r8
            goto L_0x00b3
        L_0x00b1:
            r7.showType = r4
        L_0x00b3:
            int r2 = r2 - r0
            r7.c = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.yp_pic_pick.PicturePickActivity.a(java.lang.String):void");
    }

    private void a() {
        TitleBarUtil.a(getWindow());
        this.l = (XMTitleBar) findViewById(R.id.title_bar);
        TitleBarUtil.a((View) this.l);
        this.l.setLeftClickListener(this);
        this.l.setupTitleText(getResources().getString(R.string.total_image_set));
        this.m = this.l.getTitleTextView();
        this.m.setSingleLine();
        this.m.setMaxWidth(getResources().getDimensionPixelSize(R.dimen.size_240dp));
        this.m.setEllipsize(TextUtils.TruncateAt.END);
        this.l.setTitleClickListener(this);
        this.m.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.size_5dp));
        this.m.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(R.drawable.image_pick_normal), (Drawable) null);
        this.k = (TextView) findViewById(R.id.confirm);
        b();
        this.d = (RecyclerView) findViewById(R.id.recycle_view);
        this.d.setHasFixedSize(true);
        this.d.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.h = (ViewStub) findViewById(R.id.album_pick_view_stub);
    }

    public void updateView(ArrayList<PictureAlbum> arrayList) {
        if (!this.f.e) {
            displayFrameworkBugMessageAndExit(this, R.string.msg_read_write_framework_bug);
        }
        mPictureAlbums = arrayList;
        this.g = new PicturePickAdapter(this, mPictureAlbums);
        this.d.setAdapter(this.g);
        this.g.a((PicturePickAdapter.OnImageSelectListener) new PicturePickAdapter.OnImageSelectListener() {
            public void a(PictureItem pictureItem, int i) {
                if (PicturePickActivity.mSelectedPicture != null) {
                    if (!pictureItem.c()) {
                        PicturePickActivity.mSelectedPicture.remove(pictureItem);
                    } else if (PicturePickActivity.mSelectedPicture.size() >= PicturePickActivity.this.c) {
                        pictureItem.a(false);
                        PicturePickActivity.this.g.notifyItemChanged(i);
                        PicturePickActivity picturePickActivity = PicturePickActivity.this;
                        ModuleToastManager a2 = ModuleToastManager.a();
                        PicturePickActivity picturePickActivity2 = PicturePickActivity.this;
                        picturePickActivity.mToastID = a2.a(picturePickActivity2, "最多只能选择" + PicturePickActivity.this.c + "张图哦！");
                    } else {
                        PicturePickActivity.mSelectedPicture.add(pictureItem);
                    }
                    PicturePickActivity.this.b();
                }
            }
        });
        this.g.a((PicturePickAdapter.OnImageAboveListener) new PicturePickAdapter.OnImageAboveListener() {
            public void a(PictureItem pictureItem, int i) {
                Intent intent = new Intent(PicturePickActivity.this, ShowAlbumPictureActivity.class);
                intent.putExtra("position", i);
                intent.putExtra("canSelectMaxImages", PicturePickActivity.this.c);
                intent.putExtra("PreviewType", 1);
                intent.putExtra("AlbumPosition", PicturePickActivity.this.mCurrentAlbumPosition);
                PicturePickActivity.this.startActivityForResult(intent, 108);
            }
        });
        this.g.a((PicturePickAdapter.OnTakePhotoListener) new PicturePickAdapter.OnTakePhotoListener() {
            public void a() {
                PicturePickActivity.this.c();
            }
        });
        this.j = new AlbumPickAdapter(this, mPictureAlbums);
        this.j.a((AlbumPickAdapter.OnItemClickListener) new AlbumPickAdapter.OnItemClickListener() {
            public void a(int i) {
                Iterator<PictureAlbum> it = PicturePickActivity.mPictureAlbums.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    it.next().a(i == i2);
                    i2++;
                }
                PicturePickActivity.this.g.b(i);
                PicturePickActivity.this.mCurrentAlbumPosition = i;
                PicturePickActivity.this.g.notifyDataSetChanged();
                PicturePickActivity.this.CurrentAlbumName = PicturePickActivity.mPictureAlbums.get(i).b();
                PicturePickActivity.this.l.setupTitleText(PicturePickActivity.this.CurrentAlbumName);
                PicturePickActivity.this.m.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, PicturePickActivity.this.getResources().getDrawable(R.drawable.image_pick_normal), (Drawable) null);
                PicturePickActivity.this.f();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (mSelectedPicture != null) {
            int size = mSelectedPicture.size();
            boolean z = false;
            this.k.setText(getResources().getString(R.string.select_ok, new Object[]{Integer.valueOf(size), Integer.valueOf(this.c)}));
            TextView textView = this.k;
            if (size > 0) {
                z = true;
            }
            textView.setEnabled(z);
            this.k.setOnClickListener(this);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        final Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) == null) {
            this.mToastID = ModuleToastManager.a().a(this, "没有照相机");
        } else if (YouPinPermissionManager.a((Context) this, "android.permission.CAMERA")) {
            a(intent);
        } else {
            YouPinPermissionManager.a((Activity) this, "android.permission.CAMERA", (PermissionCallback) new PermissionCallback() {
                public void a(boolean z) {
                }

                public void b() {
                }

                public void a() {
                    PicturePickActivity.this.a(intent);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        Uri uri;
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        this.e = this.n.e() + "/" + format + ".jpg";
        File file = new File(this.e);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (parentFile.exists()) {
            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            } else {
                uri = Uri.fromFile(file);
            }
            intent.putExtra(AgentOptions.k, uri);
            startActivityForResult(intent, 101);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0011 A[SYNTHETIC, Splitter:B:10:0x0011] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isHasCameraPermission() {
        /*
            android.hardware.Camera r0 = android.hardware.Camera.open()     // Catch:{ Exception -> 0x000d }
            android.hardware.Camera$Parameters r1 = r0.getParameters()     // Catch:{ Exception -> 0x000e }
            r0.setParameters(r1)     // Catch:{ Exception -> 0x000e }
            r1 = 1
            goto L_0x000f
        L_0x000d:
            r0 = 0
        L_0x000e:
            r1 = 0
        L_0x000f:
            if (r0 == 0) goto L_0x001a
            r0.release()     // Catch:{ Exception -> 0x0015 }
            goto L_0x001a
        L_0x0015:
            r0 = move-exception
            r0.printStackTrace()
            return r1
        L_0x001a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.yp_pic_pick.PicturePickActivity.isHasCameraPermission():boolean");
    }

    public static void displayFrameworkBugMessageAndExit(final Activity activity, int i2) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        builder.setTitle((CharSequence) "需要允许授权");
        builder.setMessage((CharSequence) activity.getString(i2, new Object[]{AppInfo.c()}));
        builder.setPositiveButton((CharSequence) "去设置", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + AppInfo.b())));
                activity.finish();
            }
        });
        builder.setNegativeButton((CharSequence) "取消", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                activity.finish();
            }
        });
        builder.show();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i3 == -1) {
            if (i2 != 101) {
                if (i2 == 108) {
                    if (intent.getBooleanExtra("confirm", false)) {
                        this.k.performClick();
                        return;
                    }
                    this.g.notifyDataSetChanged();
                    b();
                }
            } else if (!TextUtils.isEmpty(this.e)) {
                FileUtils.a((Context) this, Uri.fromFile(new File(this.e)));
                Intent intent2 = new Intent();
                intent2.putExtra("images", new String[]{this.e});
                setResult(-1, intent2);
                this.e = null;
                onBackPressed();
            }
        } else if (this.showType == 1) {
            onBackPressed();
        }
    }

    public void onBackPressed() {
        if (this.i == null || this.i.getVisibility() != 0) {
            if (mSelectedPicture != null) {
                mSelectedPicture.clear();
                mSelectedPicture = null;
            }
            if (mPictureAlbums != null) {
                mPictureAlbums.clear();
                mPictureAlbums = null;
            }
            if (this.f != null) {
                this.f.cancel(true);
            }
            finish();
            overridePendingTransition(17432576, 17432577);
            return;
        }
        f();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bar_left_button) {
            onBackPressed();
        } else if (id == R.id.bar_title) {
            if (mPictureAlbums != null && mPictureAlbums.size() > 1) {
                if (this.i == null || this.i.getVisibility() != 0) {
                    e();
                } else {
                    f();
                }
            }
        } else if (id == R.id.confirm) {
            d();
        }
    }

    private void d() {
        if (mSelectedPicture != null) {
            String[] strArr = new String[mSelectedPicture.size()];
            Iterator<PictureItem> it = mSelectedPicture.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                strArr[i2] = it.next().b();
                i2++;
            }
            if (!this.isNeedCompress) {
                Intent intent = new Intent();
                intent.putExtra("images", strArr);
                setResult(-1, intent);
                onBackPressed();
                return;
            }
            new ImageCompressTask(this, strArr, this.n.e()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.n.c();
    }

    private void e() {
        if (this.j.getItemCount() != 0) {
            this.m.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(R.drawable.image_pick_open), (Drawable) null);
            this.h.setVisibility(0);
            if (this.i == null) {
                this.i = (RecyclerView) findViewById(R.id.album_select);
                findViewById(R.id.content_view).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (PicturePickActivity.this.h.getVisibility() == 0) {
                            PicturePickActivity.this.f();
                        }
                    }
                });
                this.i.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(1);
                this.i.setLayoutManager(linearLayoutManager);
            }
            this.i.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_up_top_in));
            this.i.setAdapter(this.j);
            this.i.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.j.getItemCount() != 0) {
            this.m.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(R.drawable.image_pick_normal), (Drawable) null);
            if (this.i != null) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_up_top_out);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        PicturePickActivity.this.h.setVisibility(8);
                    }
                });
                this.i.setAnimation(loadAnimation);
                this.i.setVisibility(8);
                return;
            }
            this.h.setVisibility(8);
        }
    }

    static class ImagesLoadTask extends AsyncTask<String, Integer, ArrayList<PictureAlbum>> {

        /* renamed from: a  reason: collision with root package name */
        OnLoadFinishListener f19500a;
        String[] b = null;
        Context c;
        boolean d;
        boolean e = true;

        interface OnLoadFinishListener {
            void a(ArrayList<PictureAlbum> arrayList);
        }

        public ImagesLoadTask(Context context, boolean z) {
            this.c = context.getApplicationContext();
            this.d = z;
            if (Build.VERSION.SDK_INT >= 16) {
                this.b = new String[]{"_display_name", Downloads._DATA, "_id", "bucket_id", "bucket_display_name", "mime_type", "_size", "width", "height", "datetaken"};
                return;
            }
            this.b = new String[]{"_display_name", Downloads._DATA, "_id", "mime_type", "_size", "bucket_id", "bucket_display_name", "datetaken"};
        }

        public void a(OnLoadFinishListener onLoadFinishListener) {
            this.f19500a = onLoadFinishListener;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x00e6  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x00fb  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0105  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0135  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0152  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.ArrayList<com.xiaomi.yp_pic_pick.bean.PictureAlbum> doInBackground(java.lang.String... r18) {
            /*
                r17 = this;
                r1 = r17
                boolean r0 = r17.isCancelled()
                if (r0 != 0) goto L_0x0176
                java.util.ArrayList r2 = new java.util.ArrayList
                r0 = 12
                r2.<init>(r0)
                com.xiaomi.yp_pic_pick.bean.PictureAlbum r0 = new com.xiaomi.yp_pic_pick.bean.PictureAlbum
                r0.<init>()
                java.lang.String r3 = "null"
                r0.b(r3)
                android.content.Context r3 = r1.c
                int r4 = com.xiaomi.yp_pic_pick.R.string.total_image_set
                java.lang.String r3 = r3.getString(r4)
                r0.a((java.lang.String) r3)
                r3 = 1
                r0.a((boolean) r3)
                r4 = 0
                android.content.Context r5 = r1.c     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                android.content.ContentResolver r6 = r5.getContentResolver()     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                android.net.Uri r7 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                java.lang.String[] r8 = r1.b     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                java.lang.String r9 = "_size>0 AND (mime_type=? OR mime_type=? OR mime_type=?)"
                r5 = 3
                java.lang.String[] r10 = new java.lang.String[r5]     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                java.lang.String r5 = "image/jpeg"
                r10[r4] = r5     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                java.lang.String r5 = "image/jpg"
                r10[r3] = r5     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                r5 = 2
                java.lang.String r11 = "image/png"
                r10[r5] = r11     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                java.lang.String r11 = "datetaken DESC"
                android.database.Cursor r5 = r6.query(r7, r8, r9, r10, r11)     // Catch:{ SecurityException -> 0x0173, Exception -> 0x016e }
                if (r5 != 0) goto L_0x004e
                return r2
            L_0x004e:
                java.util.HashMap r6 = new java.util.HashMap
                r6.<init>()
                boolean r7 = r1.d
                if (r7 == 0) goto L_0x0067
                com.xiaomi.yp_pic_pick.bean.PictureItem r7 = new com.xiaomi.yp_pic_pick.bean.PictureItem
                r7.<init>()
                java.lang.String r8 = "拍摄照片"
                r7.b(r8)
                r7.a((int) r3)
                r0.a((com.xiaomi.yp_pic_pick.bean.PictureItem) r7)
            L_0x0067:
                boolean r3 = r5.moveToNext()
                if (r3 == 0) goto L_0x0164
                java.lang.String r3 = "_display_name"
                int r3 = r5.getColumnIndex(r3)
                java.lang.String r3 = r5.getString(r3)
                java.lang.String r7 = "_data"
                int r7 = r5.getColumnIndex(r7)
                java.lang.String r7 = r5.getString(r7)
                java.lang.String r8 = "_id"
                int r8 = r5.getColumnIndex(r8)
                long r8 = r5.getLong(r8)
                java.lang.String r10 = "bucket_id"
                int r10 = r5.getColumnIndex(r10)
                java.lang.String r10 = r5.getString(r10)
                java.lang.String r11 = "bucket_display_name"
                int r11 = r5.getColumnIndex(r11)
                java.lang.String r11 = r5.getString(r11)
                int r12 = android.os.Build.VERSION.SDK_INT
                r13 = 16
                r14 = -1
                if (r12 < r13) goto L_0x00c1
                java.lang.String r12 = "width"
                int r12 = r5.getColumnIndex(r12)
                if (r12 == r14) goto L_0x00b3
                int r12 = r5.getInt(r12)
                goto L_0x00b4
            L_0x00b3:
                r12 = -1
            L_0x00b4:
                java.lang.String r15 = "height"
                int r15 = r5.getColumnIndex(r15)
                if (r15 == r14) goto L_0x00c2
                int r15 = r5.getInt(r15)
                goto L_0x00c3
            L_0x00c1:
                r12 = -1
            L_0x00c2:
                r15 = -1
            L_0x00c3:
                android.net.Uri r16 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                android.net.Uri$Builder r14 = r16.buildUpon()
                java.lang.String r8 = java.lang.Long.toString(r8)
                android.net.Uri$Builder r8 = r14.appendPath(r8)
                android.net.Uri r8 = r8.build()
                com.xiaomi.yp_pic_pick.bean.PictureItem r9 = new com.xiaomi.yp_pic_pick.bean.PictureItem
                r9.<init>()
                r9.b(r3)
                r9.a((java.lang.String) r7)
                boolean r3 = android.text.TextUtils.isEmpty(r7)
                if (r3 != 0) goto L_0x00ef
                java.io.File r3 = new java.io.File
                r3.<init>(r7)
                android.net.Uri r8 = android.net.Uri.fromFile(r3)
            L_0x00ef:
                r9.a((android.net.Uri) r8)
                r9.c(r10)
                boolean r3 = r6.containsKey(r10)
                if (r3 == 0) goto L_0x0105
                java.lang.Object r3 = r6.get(r10)
                com.xiaomi.yp_pic_pick.bean.PictureAlbum r3 = (com.xiaomi.yp_pic_pick.bean.PictureAlbum) r3
                r3.a((com.xiaomi.yp_pic_pick.bean.PictureItem) r9)
                goto L_0x0131
            L_0x0105:
                com.xiaomi.yp_pic_pick.bean.PictureAlbum r3 = new com.xiaomi.yp_pic_pick.bean.PictureAlbum
                r3.<init>()
                r3.b(r10)
                r3.a((java.lang.String) r11)
                android.net.Uri r7 = r9.a()
                r3.a((android.net.Uri) r7)
                r3.a((com.xiaomi.yp_pic_pick.bean.PictureItem) r9)
                java.lang.String r7 = "Camera"
                boolean r7 = r7.equals(r11)
                if (r7 == 0) goto L_0x012b
                java.lang.String r7 = "相机"
                r3.a((java.lang.String) r7)
                r2.add(r4, r3)
                goto L_0x012e
            L_0x012b:
                r2.add(r3)
            L_0x012e:
                r6.put(r10, r3)
            L_0x0131:
                int r3 = android.os.Build.VERSION.SDK_INT
                if (r3 < r13) goto L_0x0152
                r3 = 200(0xc8, float:2.8E-43)
                if (r12 <= r3) goto L_0x013b
                if (r15 > r3) goto L_0x0140
            L_0x013b:
                r3 = -1
                if (r12 == r3) goto L_0x0140
                if (r15 != r3) goto L_0x0067
            L_0x0140:
                r0.a((com.xiaomi.yp_pic_pick.bean.PictureItem) r9)
                android.net.Uri r3 = r0.e()
                if (r3 != 0) goto L_0x0067
                android.net.Uri r3 = r9.a()
                r0.a((android.net.Uri) r3)
                goto L_0x0067
            L_0x0152:
                r0.a((com.xiaomi.yp_pic_pick.bean.PictureItem) r9)
                android.net.Uri r3 = r0.e()
                if (r3 != 0) goto L_0x0067
                android.net.Uri r3 = r9.a()
                r0.a((android.net.Uri) r3)
                goto L_0x0067
            L_0x0164:
                r5.close()
                r6.clear()
                r2.add(r4, r0)
                return r2
            L_0x016e:
                r0 = move-exception
                r0.printStackTrace()
                return r2
            L_0x0173:
                r1.e = r4
                return r2
            L_0x0176:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.yp_pic_pick.PicturePickActivity.ImagesLoadTask.doInBackground(java.lang.String[]):java.util.ArrayList");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(ArrayList<PictureAlbum> arrayList) {
            if (!isCancelled() && arrayList != null && this.f19500a != null) {
                this.f19500a.a(arrayList);
            }
        }
    }

    static class ImageCompressTask extends AsyncTask<Void, Integer, String[]> {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<PicturePickActivity> f19499a;
        String[] b;
        String c;
        int d;

        public ImageCompressTask(PicturePickActivity picturePickActivity, String[] strArr, String str) {
            this.f19499a = new WeakReference<>(picturePickActivity);
            this.b = strArr;
            this.c = str;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            Activity activity = (Activity) this.f19499a.get();
            if (activity != null) {
                this.d = ModuleToastManager.a().b(activity, "压缩中...");
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String[] strArr) {
            ModuleToastManager.a().a(this.d);
            Activity activity = (Activity) this.f19499a.get();
            if (activity != null) {
                Intent intent = new Intent();
                intent.putExtra("images", strArr);
                activity.setResult(-1, intent);
                activity.onBackPressed();
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            ModuleToastManager.a().a(this.d);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String[] doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.b) {
                File file = new File(this.c, MD5Utils.e(str));
                if (Utils.a(new File(str), file)) {
                    arrayList.add(file.getAbsolutePath());
                }
            }
            return (String[]) arrayList.toArray(new String[0]);
        }
    }
}
