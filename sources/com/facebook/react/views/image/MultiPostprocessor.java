package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.Postprocessor;
import com.taobao.weex.el.parse.Operators;
import java.util.LinkedList;
import java.util.List;

public class MultiPostprocessor implements Postprocessor {
    private final List<Postprocessor> mPostprocessors;

    public static Postprocessor from(List<Postprocessor> list) {
        switch (list.size()) {
            case 0:
                return null;
            case 1:
                return list.get(0);
            default:
                return new MultiPostprocessor(list);
        }
    }

    private MultiPostprocessor(List<Postprocessor> list) {
        this.mPostprocessors = new LinkedList(list);
    }

    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Postprocessor next : this.mPostprocessors) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(next.getName());
        }
        sb.insert(0, "MultiPostProcessor (");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public CacheKey getPostprocessorCacheKey() {
        LinkedList linkedList = new LinkedList();
        for (Postprocessor postprocessorCacheKey : this.mPostprocessors) {
            linkedList.push(postprocessorCacheKey.getPostprocessorCacheKey());
        }
        return new MultiCacheKey(linkedList);
    }

    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        CloseableReference<Bitmap> closeableReference = null;
        try {
            CloseableReference<Bitmap> closeableReference2 = null;
            for (Postprocessor process : this.mPostprocessors) {
                CloseableReference<Bitmap> process2 = process.process(closeableReference2 != null ? closeableReference2.get() : bitmap, platformBitmapFactory);
                try {
                    CloseableReference.closeSafely((CloseableReference<?>) closeableReference2);
                    closeableReference2 = process2.clone();
                    closeableReference = process2;
                } catch (Throwable th) {
                    th = th;
                    closeableReference = process2;
                    CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                    throw th;
                }
            }
            CloseableReference<Bitmap> clone = closeableReference.clone();
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            return clone;
        } catch (Throwable th2) {
            th = th2;
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            throw th;
        }
    }
}
