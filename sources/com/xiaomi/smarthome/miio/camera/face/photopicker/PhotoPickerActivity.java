package com.xiaomi.smarthome.miio.camera.face.photopicker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerBaseActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PhotoPickerActivity extends FaceManagerBaseActivity implements View.OnClickListener {
    private static final int LOADER_ALL = 0;
    private static final int LOADER_CATEGORY = 1;
    /* access modifiers changed from: private */
    public ImagePickerAdapter mImagePickerAdapter;
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
        private final String[] IMAGE_PROJECTION = {Downloads._DATA, "_display_name", "_id"};

        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            if (i == 0) {
                return new CursorLoader(PhotoPickerActivity.this.getContext(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, (String) null, (String[]) null, "date_added DESC");
            }
            if (i != 1) {
                return null;
            }
            Context context = PhotoPickerActivity.this.getContext();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] strArr = this.IMAGE_PROJECTION;
            return new CursorLoader(context, uri, strArr, this.IMAGE_PROJECTION[0] + " not like '%.gif%'", (String[]) null, "date_added DESC");
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                ArrayList arrayList = new ArrayList();
                cursor.moveToFirst();
                do {
                    arrayList.add(new Image(cursor.getString(cursor.getColumnIndexOrThrow(this.IMAGE_PROJECTION[0])), cursor.getString(cursor.getColumnIndexOrThrow(this.IMAGE_PROJECTION[1]))));
                } while (cursor.moveToNext());
                PhotoPickerActivity.this.mImagePickerAdapter.setData(arrayList);
            }
        }
    };
    private RecyclerView mRecyclerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_photo_picker);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        this.mImagePickerAdapter = new ImagePickerAdapter(this);
        this.mRecyclerView.setAdapter(this.mImagePickerAdapter);
        getSupportLoaderManager().initLoader(0, (Bundle) null, this.mLoaderCallback);
    }

    private void submit() {
        List<Image> selectImages = this.mImagePickerAdapter.getSelectImages();
        if (selectImages.size() == 0) {
            ToastUtil.a((int) R.string.select_img_tips);
            return;
        }
        String str = selectImages.get(0).path;
        ToastUtil.a((int) R.string.face_recognitioning);
        mFaceManager.uploadImageFile(this, str, new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                try {
                    String string = ((JSONObject) ((JSONObject) obj).getJSONObject("data").getJSONArray("faceInfoMetas").get(0)).getString("faceId");
                    Intent intent = new Intent();
                    intent.putExtra("faceId", string);
                    PhotoPickerActivity.this.setResult(-1, intent);
                    PhotoPickerActivity.this.finish();
                } catch (JSONException e) {
                    ToastUtil.a((int) R.string.face_recognition_fail_tips);
                    e.printStackTrace();
                }
            }

            public void onFailure(int i, String str) {
                ToastUtil.a((int) R.string.face_recognition_fail_tips);
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.title_bar_return) {
            finish();
        } else if (id == R.id.tv_confirm) {
            submit();
        }
    }
}
