package com.mi.multi_image_selector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.mi.multi_image_selector.MultiImageSelectorFragment;
import java.io.File;
import java.util.ArrayList;

public class MultiImageSelectorActivity extends AppCompatActivity implements MultiImageSelectorFragment.Callback {
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    public static final String EXTRA_RESULT = "select_result";
    public static final String EXTRA_RESULT_BOOLEAN = "boolean_result";
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    public static final String EXTRA_SHOW_FULL_UPLOAD = "show_full_upload";
    public static final int MODE_MULTI = 1;
    public static final int MODE_SINGLE = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final int f7378a = 9;
    /* access modifiers changed from: private */
    public ArrayList<String> b = new ArrayList<>();
    private TextView c;
    private int d = 9;
    /* access modifiers changed from: private */
    public boolean e = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.bbs_mis_activity_default);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        this.d = intent.getIntExtra("max_select_count", 9);
        int intExtra = intent.getIntExtra("select_count_mode", 1);
        boolean booleanExtra = intent.getBooleanExtra("show_camera", true);
        boolean booleanExtra2 = intent.getBooleanExtra("show_full_upload", false);
        if (intExtra == 1 && intent.hasExtra("default_list")) {
            this.b = intent.getStringArrayListExtra("default_list");
        }
        this.c = (TextView) findViewById(R.id.commit);
        if (intExtra == 1) {
            a(this.b);
            this.c.setVisibility(0);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MultiImageSelectorActivity.this.b == null || MultiImageSelectorActivity.this.b.size() <= 0) {
                        MultiImageSelectorActivity.this.setResult(0);
                    } else {
                        Intent intent = new Intent();
                        intent.putStringArrayListExtra("select_result", MultiImageSelectorActivity.this.b);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_RESULT_BOOLEAN, MultiImageSelectorActivity.this.e);
                        MultiImageSelectorActivity.this.setResult(-1, intent);
                    }
                    MultiImageSelectorActivity.this.finish();
                }
            });
        } else {
            this.c.setVisibility(8);
        }
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("max_select_count", this.d);
            bundle2.putInt("select_count_mode", intExtra);
            bundle2.putBoolean("show_camera", booleanExtra);
            bundle2.putBoolean("show_full_upload", booleanExtra2);
            bundle2.putStringArrayList("default_list", this.b);
            getSupportFragmentManager().beginTransaction().add(R.id.image_grid, Fragment.instantiate(this, MultiImageSelectorFragment.class.getName(), bundle2)).commit();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        setResult(0);
        finish();
        return true;
    }

    private void a(ArrayList<String> arrayList) {
        int i;
        if (arrayList == null || arrayList.size() <= 0) {
            this.c.setText(R.string.bbs_mis_action_done);
            this.c.setEnabled(false);
            i = 0;
        } else {
            i = arrayList.size();
            this.c.setEnabled(true);
        }
        this.c.setText(getString(R.string.mis_action_button_string, new Object[]{getString(R.string.bbs_mis_action_done), Integer.valueOf(i), Integer.valueOf(this.d)}));
    }

    public void onSingleImageSelected(String str) {
        Intent intent = new Intent();
        this.b.add(str);
        intent.putStringArrayListExtra("select_result", this.b);
        intent.putExtra(EXTRA_RESULT_BOOLEAN, this.e);
        setResult(-1, intent);
        finish();
    }

    public void onImageSelected(String str) {
        if (!this.b.contains(str)) {
            this.b.add(str);
        }
        a(this.b);
    }

    public void onImageUnselected(String str) {
        if (this.b.contains(str)) {
            this.b.remove(str);
        }
        a(this.b);
    }

    public void onCameraShot(File file) {
        if (file != null) {
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            Intent intent = new Intent();
            this.b.add(file.getAbsolutePath());
            intent.putStringArrayListExtra("select_result", this.b);
            intent.putExtra(EXTRA_RESULT_BOOLEAN, this.e);
            setResult(-1, intent);
            finish();
        }
    }

    public void onUploadFullImage(boolean z) {
        this.e = z;
    }
}
