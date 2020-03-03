package com.mi.global.shop.imageselector;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.imageselector.adapter.FolderAdapter;
import com.mi.global.shop.imageselector.adapter.ImageGridAdapter;
import com.mi.global.shop.imageselector.bean.Folder;
import com.mi.global.shop.imageselector.bean.Image;
import com.mi.global.shop.imageselector.utils.FileUtils;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.Device;
import com.mi.util.ResourceUtil;
import com.xiaomi.smarthome.download.Downloads;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class MultiImageSelectorActivity extends BaseActivity {
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    public static final String EXTRA_RESULT = "select_result";
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    public static final int MODE_MULTI = 1;
    public static final int MODE_SINGLE = 0;
    public static final String TAG = "MultiImageSelectorActivity";

    /* renamed from: a  reason: collision with root package name */
    private static final int f6909a = 100;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 9;
    private static final String e = "KEY_CAMERA_TEMP_URI";
    private static final String f = "KEY_CAMERA_TEMP_FILE";
    /* access modifiers changed from: private */
    public ArrayList<String> g = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<Folder> h = new ArrayList<>();
    /* access modifiers changed from: private */
    public int i;
    private boolean j;
    /* access modifiers changed from: private */
    public int k = 9;
    /* access modifiers changed from: private */
    public GridView l;
    /* access modifiers changed from: private */
    public View m;
    private CustomButtonView n;
    /* access modifiers changed from: private */
    public ImageGridAdapter o;
    /* access modifiers changed from: private */
    public FolderAdapter p;
    /* access modifiers changed from: private */
    public ListPopupWindow q;
    private CustomTextView r;
    private View s;
    private File t;
    private LoaderManager.LoaderCallbacks<Cursor> u = new LoaderManager.LoaderCallbacks<Cursor>() {
        private final String[] b = {Downloads._DATA, "_display_name", "date_added", "mime_type", "_size", "_id"};

        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            int i2 = i;
            if (i2 == 0) {
                return new CursorLoader(MultiImageSelectorActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.b, this.b[4] + ">0 AND " + this.b[3] + "=? OR " + this.b[3] + "=? ", new String[]{"image/jpeg", "image/png"}, this.b[2] + " DESC");
            } else if (i2 != 1) {
                return null;
            } else {
                return new CursorLoader(MultiImageSelectorActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.b, this.b[4] + ">0 AND " + this.b[0] + " like '%" + bundle.getString("path") + "%'", (String[]) null, this.b[2] + " DESC");
            }
        }

        private boolean a(String str) {
            if (!TextUtils.isEmpty(str)) {
                return new File(str).exists();
            }
            return false;
        }

        /* renamed from: a */
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            String str = "/sdcard";
            if (MultiImageSelectorActivity.this.p.getCount() > MultiImageSelectorActivity.this.p.a()) {
                str = MultiImageSelectorActivity.this.p.getItem(MultiImageSelectorActivity.this.p.a()).b;
            }
            MultiImageSelectorActivity.this.h.clear();
            Folder folder = new Folder();
            folder.f6927a = MultiImageSelectorActivity.this.getResources().getString(R.string.shop_mis_folder_all);
            folder.b = "/sdcard";
            folder.d = new ArrayList<>();
            MultiImageSelectorActivity.this.h.add(folder);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    String string = cursor.getString(cursor.getColumnIndexOrThrow(this.b[0]));
                    String string2 = cursor.getString(cursor.getColumnIndexOrThrow(this.b[1]));
                    long j = cursor.getLong(cursor.getColumnIndexOrThrow(this.b[2]));
                    if (a(string)) {
                        Image image = null;
                        if (!TextUtils.isEmpty(string2)) {
                            image = new Image(string, string2, j);
                            folder.d.add(image);
                            if (folder.c == null) {
                                folder.c = image;
                            }
                        }
                        File parentFile = new File(string).getParentFile();
                        if (parentFile != null && parentFile.exists()) {
                            String absolutePath = parentFile.getAbsolutePath();
                            Folder access$1200 = MultiImageSelectorActivity.this.a(absolutePath);
                            if (access$1200 == null) {
                                Folder folder2 = new Folder();
                                folder2.f6927a = parentFile.getName();
                                folder2.b = absolutePath;
                                folder2.c = image;
                                ArrayList<Image> arrayList = new ArrayList<>();
                                arrayList.add(image);
                                folder2.d = arrayList;
                                MultiImageSelectorActivity.this.h.add(folder2);
                            } else {
                                access$1200.d.add(image);
                            }
                        }
                    }
                } while (cursor.moveToNext());
                MultiImageSelectorActivity.this.p.a((List<Folder>) MultiImageSelectorActivity.this.h);
                int access$1300 = MultiImageSelectorActivity.this.b(str);
                MultiImageSelectorActivity.this.p.b(access$1300);
                MultiImageSelectorActivity.this.a(access$1300);
                if (MultiImageSelectorActivity.this.g != null && MultiImageSelectorActivity.this.g.size() > 0) {
                    MultiImageSelectorActivity.this.o.a((ArrayList<String>) MultiImageSelectorActivity.this.g);
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle);
        setCustomContentView(R.layout.shop_activity_multi_image);
        this.mCartView.setVisibility(8);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MultiImageSelectorActivity.this.setResult(0);
                MultiImageSelectorActivity.this.finish();
            }
        });
        setTitle((CharSequence) "Images");
        Intent intent = getIntent();
        this.k = intent.getIntExtra("max_select_count", 9);
        boolean z = true;
        this.i = intent.getIntExtra("select_count_mode", 1);
        this.j = intent.getBooleanExtra("show_camera", true);
        if (this.i == 1 && intent.hasExtra("default_list")) {
            this.g = intent.getStringArrayListExtra("default_list");
        }
        this.n = (CustomButtonView) findViewById(R.id.commit);
        if (this.i == 1) {
            updateDoneText();
            this.n.setVisibility(0);
            this.n.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MultiImageSelectorActivity.this.g == null || MultiImageSelectorActivity.this.g.size() <= 0) {
                        MultiImageSelectorActivity.this.setResult(0);
                    } else {
                        Intent intent = new Intent();
                        intent.putStringArrayListExtra("select_result", MultiImageSelectorActivity.this.g);
                        MultiImageSelectorActivity.this.setResult(-1, intent);
                    }
                    MultiImageSelectorActivity.this.finish();
                }
            });
        } else {
            this.n.setVisibility(8);
        }
        this.o = new ImageGridAdapter(this, c(), 3);
        ImageGridAdapter imageGridAdapter = this.o;
        if (this.i != 1) {
            z = false;
        }
        imageGridAdapter.a(z);
        this.o.a(this.g);
        this.o.a(this.k);
        this.s = findViewById(R.id.footer);
        this.r = (CustomTextView) findViewById(R.id.category_btn);
        this.r.setText(R.string.shop_mis_folder_all);
        this.r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MultiImageSelectorActivity.this.q == null) {
                    MultiImageSelectorActivity.this.a();
                }
                if (MultiImageSelectorActivity.this.q.isShowing()) {
                    MultiImageSelectorActivity.this.q.dismiss();
                    return;
                }
                MultiImageSelectorActivity.this.q.show();
                MultiImageSelectorActivity.this.m.setVisibility(0);
                int a2 = MultiImageSelectorActivity.this.p.a();
                if (a2 != 0) {
                    a2--;
                }
                MultiImageSelectorActivity.this.q.getListView().setSelection(a2);
            }
        });
        this.l = (GridView) findViewById(R.id.grid);
        this.l.setAdapter(this.o);
        this.l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!MultiImageSelectorActivity.this.o.a()) {
                    Intent intent = new Intent(MultiImageSelectorActivity.this, ImageViewActivity.class);
                    intent.putParcelableArrayListExtra("data", MultiImageSelectorActivity.this.o.b());
                    intent.putStringArrayListExtra(Tags.MiHomeStorage.RESULTS, MultiImageSelectorActivity.this.g);
                    intent.putExtra("pager", i);
                    intent.putExtra("count", MultiImageSelectorActivity.this.k);
                    intent.putExtra("mode", MultiImageSelectorActivity.this.i);
                    MultiImageSelectorActivity.this.startActivityForResult(intent, 123);
                } else if (i == 0) {
                    MultiImageSelectorActivity.this.b();
                } else {
                    try {
                        Intent intent2 = new Intent(MultiImageSelectorActivity.this, ImageViewActivity.class);
                        intent2.putParcelableArrayListExtra("data", MultiImageSelectorActivity.this.o.b());
                        intent2.putStringArrayListExtra(Tags.MiHomeStorage.RESULTS, MultiImageSelectorActivity.this.g);
                        intent2.putExtra("pager", i - 1);
                        intent2.putExtra("count", MultiImageSelectorActivity.this.k);
                        intent2.putExtra("mode", MultiImageSelectorActivity.this.i);
                        MultiImageSelectorActivity.this.startActivityForResult(intent2, 123);
                    } catch (Exception e) {
                        ArrayList<Image> b = MultiImageSelectorActivity.this.o.b();
                        StringBuilder sb = new StringBuilder();
                        sb.append(b.toString() + "\n");
                        sb.append(MultiImageSelectorActivity.this.g.toString() + "\n");
                        CrashReport.postCrash(Thread.currentThread(), (Throwable) new Exception(sb.toString(), e));
                    }
                }
            }
        });
        this.l.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 2) {
                    FrescoUtils.a();
                } else {
                    FrescoUtils.b();
                }
            }
        });
        this.m = findViewById(R.id.grid_mark);
        this.p = new FolderAdapter(this);
        getSupportLoaderManager().initLoader(0, (Bundle) null, this.u);
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            this.t = (File) bundle.getSerializable(f);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.t != null) {
            bundle.putSerializable(f, this.t);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        int i2 = Device.f1349a;
        this.q = new ListPopupWindow(this);
        this.q.setBackgroundDrawable(new ColorDrawable(-1));
        this.q.setAdapter(this.p);
        this.q.setContentWidth(i2);
        this.q.setWidth(i2);
        this.q.setHeight((int) (((float) Device.b) * 0.5625f));
        this.q.setAnchorView(this.s);
        this.q.setModal(true);
        this.q.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != MultiImageSelectorActivity.this.p.a()) {
                    MultiImageSelectorActivity.this.p.b(i);
                    MultiImageSelectorActivity.this.q.dismiss();
                    MultiImageSelectorActivity.this.a(i);
                    MultiImageSelectorActivity.this.l.smoothScrollToPosition(0);
                }
            }
        });
        this.q.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                MultiImageSelectorActivity.this.m.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        Folder a2 = this.p.getItem(i2);
        if (a2 != null) {
            this.o.b(a2.d);
            this.r.setText(a2.f6927a);
        }
        if (i2 == 0) {
            this.o.b(true);
        } else {
            this.o.b(false);
        }
    }

    public void updateDoneText() {
        int i2;
        if (this.g == null || this.g.size() <= 0) {
            this.n.setText(R.string.shop_mis_action_done);
            this.n.setEnabled(false);
            i2 = 0;
        } else {
            i2 = this.g.size();
            this.n.setEnabled(true);
        }
        this.n.setText(getString(R.string.mis_action_button_string, new Object[]{getString(R.string.shop_mis_action_done), Integer.valueOf(i2), Integer.valueOf(this.k)}));
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && i3 == -1) {
            try {
                onCameraShot(this.t);
            } catch (Exception e2) {
                LogUtil.a(e2.getMessage());
            }
        }
        if (intent != null && i2 == 123) {
            this.g = intent.getStringArrayListExtra("result");
            LogUtil.b(TAG, this.g.toString());
            if (i3 == -1) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Intent intent = new Intent();
                        intent.putStringArrayListExtra("select_result", MultiImageSelectorActivity.this.g);
                        MultiImageSelectorActivity.this.setResult(-1, intent);
                        MultiImageSelectorActivity.this.finish();
                    }
                });
                return;
            }
            this.o.a(this.g);
            this.o.notifyDataSetChanged();
            updateDoneText();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.q != null && this.q.isShowing()) {
            this.q.dismiss();
        }
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, R.string.mis_msg_no_camera, 0).show();
            return;
        }
        try {
            this.t = FileUtils.a(this);
            Uri uriForFile = FileProvider.getUriForFile(this, ResourceUtil.a("file_provider_authorities"), this.t);
            LogUtil.b("picker file:" + this.t.getAbsolutePath() + ",mCameraTempUri:" + uriForFile);
            if (Build.VERSION.SDK_INT >= 21) {
                intent.addFlags(2);
            } else if (Build.VERSION.SDK_INT >= 16) {
                intent.setClipData(ClipData.newUri(getContentResolver(), this.t.getName(), uriForFile));
                intent.addFlags(2);
            } else {
                for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(intent, 65536)) {
                    grantUriPermission(resolveInfo.activityInfo.packageName, uriForFile, 2);
                }
            }
            intent.putExtra(AgentOptions.k, uriForFile);
            startActivityForResult(intent, 100);
        } catch (Exception e2) {
            e2.printStackTrace();
            Toast.makeText(this, R.string.shop_mis_error_image_not_exist, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public Folder a(String str) {
        if (this.h == null) {
            return null;
        }
        Iterator<Folder> it = this.h.iterator();
        while (it.hasNext()) {
            Folder next = it.next();
            if (TextUtils.equals(next.b, str)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public int b(String str) {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if (TextUtils.equals(this.h.get(i2).b, str)) {
                return i2;
            }
        }
        return 0;
    }

    private boolean c() {
        return this.j;
    }

    public void onCameraShot(File file) {
        if (file != null) {
            Uri fromFile = Uri.fromFile(a(file));
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", fromFile));
            Intent intent = new Intent();
            this.g.add(fromFile.toString());
            intent.putStringArrayListExtra("select_result", this.g);
            setResult(-1, intent);
            LogUtil.b("setResult picker:" + fromFile);
            finish();
        }
    }

    private File a(File file) {
        String absolutePath = file.getAbsolutePath();
        int lastIndexOf = absolutePath.lastIndexOf(46);
        String str = absolutePath.substring(0, lastIndexOf) + "_compress" + absolutePath.substring(lastIndexOf);
        ImageUtil.a(BitmapFactory.decodeFile(absolutePath), str, Bitmap.CompressFormat.JPEG, 80);
        File file2 = new File(str);
        if (!file2.exists()) {
            return file;
        }
        file.delete();
        return file2;
    }
}
