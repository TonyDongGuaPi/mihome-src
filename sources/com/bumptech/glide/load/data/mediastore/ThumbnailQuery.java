package com.bumptech.glide.load.data.mediastore;

import android.database.Cursor;
import android.net.Uri;

interface ThumbnailQuery {
    Cursor a(Uri uri);
}
