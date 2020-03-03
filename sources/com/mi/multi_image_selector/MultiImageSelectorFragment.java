package com.mi.multi_image_selector;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.multi_image_selector.adapter.FolderAdapter;
import com.mi.multi_image_selector.adapter.ImageGridAdapter;
import com.mi.multi_image_selector.bean.Folder;
import com.mi.multi_image_selector.bean.Image;
import com.mi.multi_image_selector.utils.AppUtil;
import com.mi.multi_image_selector.utils.FileUtils;
import com.mi.multi_image_selector.utils.ScreenUtils;
import com.mi.multi_image_selector.view.CheckableTextView;
import com.mi.util.ResourceUtil;
import com.mi.util.permission.PermissionUtil;
import com.xiaomi.smarthome.download.Downloads;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class MultiImageSelectorFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7380a = "MultiImageSelectorFragment";
    public static final int b = 0;
    public static final int c = 1;
    public static final String d = "max_select_count";
    public static final String e = "select_count_mode";
    public static final String f = "show_camera";
    public static final String g = "default_list";
    public static final String h = "show_full_upload";
    private static final int i = 110;
    private static final int j = 100;
    private static final String k = "key_temp_file";
    private static final int l = 0;
    private static final int m = 1;
    /* access modifiers changed from: private */
    public ArrayList<String> n = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<Folder> o = new ArrayList<>();
    /* access modifiers changed from: private */
    public GridView p;
    /* access modifiers changed from: private */
    public Callback q;
    /* access modifiers changed from: private */
    public ImageGridAdapter r;
    /* access modifiers changed from: private */
    public FolderAdapter s;
    /* access modifiers changed from: private */
    public ListPopupWindow t;
    private TextView u;
    private View v;
    private File w;
    private CheckableTextView x;
    private LoaderManager.LoaderCallbacks<Cursor> y = new LoaderManager.LoaderCallbacks<Cursor>() {
        private final String[] b = {Downloads._DATA, "_display_name", "date_added", "mime_type", "_size", "_id"};

        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            int i2 = i;
            if (i2 == 0) {
                return new CursorLoader(MultiImageSelectorFragment.this.getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.b, this.b[4] + ">0 AND " + this.b[3] + "=? OR " + this.b[3] + "=? ", new String[]{"image/jpeg", "image/png"}, this.b[2] + " DESC");
            } else if (i2 != 1) {
                return null;
            } else {
                return new CursorLoader(MultiImageSelectorFragment.this.getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.b, this.b[4] + ">0 AND " + this.b[0] + " like '%" + bundle.getString("path") + "%'", (String[]) null, this.b[2] + " DESC");
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
            if (MultiImageSelectorFragment.this.s.getCount() > MultiImageSelectorFragment.this.s.a()) {
                str = MultiImageSelectorFragment.this.s.getItem(MultiImageSelectorFragment.this.s.a()).b;
            }
            MultiImageSelectorFragment.this.o.clear();
            Folder folder = new Folder();
            folder.f7395a = MultiImageSelectorFragment.this.getResources().getString(R.string.bbs_mis_folder_all);
            folder.b = "/sdcard";
            folder.d = new ArrayList();
            MultiImageSelectorFragment.this.o.add(folder);
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
                            Folder a2 = MultiImageSelectorFragment.this.a(absolutePath);
                            if (a2 == null) {
                                Folder folder2 = new Folder();
                                folder2.f7395a = parentFile.getName();
                                folder2.b = absolutePath;
                                folder2.c = image;
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(image);
                                folder2.d = arrayList;
                                MultiImageSelectorFragment.this.o.add(folder2);
                            } else {
                                a2.d.add(image);
                            }
                        }
                    }
                } while (cursor.moveToNext());
                MultiImageSelectorFragment.this.s.a((List<Folder>) MultiImageSelectorFragment.this.o);
                int b2 = MultiImageSelectorFragment.this.b(str);
                MultiImageSelectorFragment.this.s.b(b2);
                MultiImageSelectorFragment.this.a(b2);
                if (MultiImageSelectorFragment.this.n != null && MultiImageSelectorFragment.this.n.size() > 0) {
                    MultiImageSelectorFragment.this.r.a((ArrayList<String>) MultiImageSelectorFragment.this.n);
                }
            }
        }
    };

    public interface Callback {
        void onCameraShot(File file);

        void onImageSelected(String str);

        void onImageUnselected(String str);

        void onSingleImageSelected(String str);

        void onUploadFullImage(boolean z);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.q = (Callback) getActivity();
        } catch (ClassCastException unused) {
            throw new ClassCastException("The Activity must implement MultiImageSelectorFragment.Callback interface...");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.bss_mis_fragment_multi_image, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        ArrayList<String> stringArrayList;
        super.onViewCreated(view, bundle);
        final int e2 = e();
        boolean z = true;
        if (e2 == 1 && (stringArrayList = getArguments().getStringArrayList("default_list")) != null && stringArrayList.size() > 0) {
            this.n = stringArrayList;
        }
        this.r = new ImageGridAdapter(getActivity(), c(), 3);
        ImageGridAdapter imageGridAdapter = this.r;
        int i2 = 0;
        if (e2 != 1) {
            z = false;
        }
        imageGridAdapter.a(z);
        this.v = view.findViewById(R.id.footer);
        this.u = (TextView) view.findViewById(R.id.category_btn);
        this.u.setText(R.string.bbs_mis_folder_all);
        this.u.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MultiImageSelectorFragment.this.t == null) {
                    MultiImageSelectorFragment.this.a();
                }
                if (MultiImageSelectorFragment.this.t.isShowing()) {
                    MultiImageSelectorFragment.this.t.dismiss();
                    return;
                }
                MultiImageSelectorFragment.this.t.show();
                int a2 = MultiImageSelectorFragment.this.s.a();
                if (a2 != 0) {
                    a2--;
                }
                MultiImageSelectorFragment.this.t.getListView().setSelection(a2);
            }
        });
        this.p = (GridView) view.findViewById(R.id.grid);
        this.p.setAdapter(this.r);
        this.p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    com.mi.multi_image_selector.MultiImageSelectorFragment r2 = com.mi.multi_image_selector.MultiImageSelectorFragment.this
                    com.mi.multi_image_selector.adapter.ImageGridAdapter r2 = r2.r
                    boolean r2 = r2.a()
                    if (r2 == 0) goto L_0x0026
                    if (r3 != 0) goto L_0x0014
                    com.mi.multi_image_selector.MultiImageSelectorFragment r1 = com.mi.multi_image_selector.MultiImageSelectorFragment.this
                    r1.b()
                    goto L_0x0037
                L_0x0014:
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.mi.multi_image_selector.bean.Image r1 = (com.mi.multi_image_selector.bean.Image) r1
                    com.mi.multi_image_selector.MultiImageSelectorFragment r2 = com.mi.multi_image_selector.MultiImageSelectorFragment.this
                    int r3 = r7
                    r2.a((com.mi.multi_image_selector.bean.Image) r1, (int) r3)
                    goto L_0x0037
                L_0x0026:
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.mi.multi_image_selector.bean.Image r1 = (com.mi.multi_image_selector.bean.Image) r1
                    com.mi.multi_image_selector.MultiImageSelectorFragment r2 = com.mi.multi_image_selector.MultiImageSelectorFragment.this
                    int r3 = r7
                    r2.a((com.mi.multi_image_selector.bean.Image) r1, (int) r3)
                L_0x0037:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mi.multi_image_selector.MultiImageSelectorFragment.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        this.s = new FolderAdapter(getActivity());
        this.x = (CheckableTextView) view.findViewById(R.id.upload_original);
        this.x.setOnCheckedChangedListener(new CheckableTextView.OnCheckedChangedListener() {
            public void a(CheckableTextView checkableTextView, boolean z) {
                if (MultiImageSelectorFragment.this.q != null) {
                    MultiImageSelectorFragment.this.q.onUploadFullImage(z);
                }
            }
        });
        CheckableTextView checkableTextView = this.x;
        if (!d()) {
            i2 = 4;
        }
        checkableTextView.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void a() {
        Point a2 = ScreenUtils.a(getActivity());
        int i2 = a2.x;
        this.t = new ListPopupWindow(getActivity());
        this.t.setBackgroundDrawable(new ColorDrawable(-1));
        this.t.setAdapter(this.s);
        this.t.setContentWidth(i2);
        this.t.setWidth(i2);
        this.t.setHeight((int) (((float) a2.y) * 0.5625f));
        this.t.setAnchorView(this.v);
        this.t.setModal(true);
        this.t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != MultiImageSelectorFragment.this.s.a()) {
                    MultiImageSelectorFragment.this.s.b(i);
                    MultiImageSelectorFragment.this.t.dismiss();
                    MultiImageSelectorFragment.this.a(i);
                    MultiImageSelectorFragment.this.p.smoothScrollToPosition(0);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        Folder a2 = this.s.getItem(i2);
        if (a2 != null) {
            this.r.a(a2.d);
            this.u.setText(a2.f7395a);
            if (this.n != null && this.n.size() > 0) {
                this.r.a(this.n);
            }
        }
        if (i2 == 0) {
            this.r.b(true);
        } else {
            this.r.b(false);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(k, this.w);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.w = (File) bundle.getSerializable(k);
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().getSupportLoaderManager().initLoader(0, (Bundle) null, this.y);
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 100) {
            return;
        }
        if (i3 != -1) {
            while (this.w != null && this.w.exists()) {
                if (this.w.delete()) {
                    this.w = null;
                }
            }
        } else if (this.w != null && this.q != null) {
            this.q.onCameraShot(this.w);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.t != null && this.t.isShowing()) {
            this.t.dismiss();
        }
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: private */
    public void b() {
        Uri uri;
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            AnonymousClass5 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MultiImageSelectorFragment.this.a("android.permission.WRITE_EXTERNAL_STORAGE", MultiImageSelectorFragment.this.getString(R.string.mis_permission_rationale_write_storage), 110);
                }
            };
            FragmentActivity activity = getActivity();
            PermissionUtil.a(activity, AppUtil.a(getContext()) + " " + getResources().getString(R.string.explain_storage_permission), r0, (DialogInterface.OnClickListener) null);
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                this.w = FileUtils.a(getActivity());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (this.w == null || !this.w.exists()) {
                Toast.makeText(getActivity(), R.string.bbs_mis_error_image_not_exist, 0).show();
                return;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(getActivity(), ResourceUtil.a("file_provider_authorities"), this.w);
                intent.addFlags(1);
            } else {
                uri = Uri.fromFile(this.w);
            }
            intent.putExtra(AgentOptions.k, uri);
            startActivityForResult(intent, 100);
            return;
        }
        Toast.makeText(getActivity(), R.string.mis_msg_no_camera, 0).show();
    }

    /* access modifiers changed from: private */
    public void a(final String str, String str2, final int i2) {
        if (shouldShowRequestPermissionRationale(str)) {
            new AlertDialog.Builder(getContext()).setTitle(R.string.mis_permission_dialog_title).setMessage((CharSequence) str2).setPositiveButton(R.string.mis_permission_dialog_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MultiImageSelectorFragment.this.requestPermissions(new String[]{str}, i2);
                }
            }).setNegativeButton(R.string.mis_permission_dialog_cancel, (DialogInterface.OnClickListener) null).create().show();
            return;
        }
        requestPermissions(new String[]{str}, i2);
    }

    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i2 != 110) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        } else if (iArr[0] == 0) {
            b();
        }
    }

    /* access modifiers changed from: private */
    public void a(Image image, int i2) {
        if (image == null) {
            return;
        }
        if (i2 == 1) {
            if (this.n.contains(image.f7396a)) {
                this.n.remove(image.f7396a);
                if (this.q != null) {
                    this.q.onImageUnselected(image.f7396a);
                }
            } else if (f() == this.n.size()) {
                Toast.makeText(getActivity(), R.string.mis_msg_amount_limit, 0).show();
                return;
            } else {
                this.n.add(image.f7396a);
                if (this.q != null) {
                    this.q.onImageSelected(image.f7396a);
                }
            }
            this.r.a(image);
        } else if (i2 == 0 && this.q != null) {
            this.q.onSingleImageSelected(image.f7396a);
        }
    }

    /* access modifiers changed from: private */
    public Folder a(String str) {
        if (this.o == null) {
            return null;
        }
        Iterator<Folder> it = this.o.iterator();
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
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            if (TextUtils.equals(this.o.get(i2).b, str)) {
                return i2;
            }
        }
        return 0;
    }

    private boolean c() {
        return getArguments() == null || getArguments().getBoolean("show_camera", true);
    }

    private boolean d() {
        return getArguments() != null && getArguments().getBoolean("show_full_upload", false);
    }

    private int e() {
        if (getArguments() == null) {
            return 1;
        }
        return getArguments().getInt("select_count_mode");
    }

    private int f() {
        if (getArguments() == null) {
            return 9;
        }
        return getArguments().getInt("max_select_count");
    }
}
