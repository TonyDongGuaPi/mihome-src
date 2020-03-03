package com.xiaomi.smarthome.feedback.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.debug.NetRequestWarningActivity;
import com.xiaomi.smarthome.library.common.util.BitmapUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 (2\u00020\u0001:\u0002()B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fJ\u0011\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00072\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bH\u0002J(\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\b\u0010 \u001a\u00020\u000fH\u0014J\b\u0010!\u001a\u00020\u000fH\u0014J\u000e\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\fJ\u000e\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u0007J\b\u0010'\u001a\u00020\u000fH\u0002R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nX\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/xiaomi/smarthome/feedback/view/ImagePickerPreview;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mData", "Lcom/xiaomi/smarthome/feedback/view/ImagePickerPreview$LimitQueue;", "Landroid/util/Pair;", "", "Landroid/graphics/Bitmap;", "add", "", "filePath", "getPickedItems", "", "()[Ljava/lang/String;", "makeAddBtn", "Landroid/view/View;", "makePreviewView", "index", "pair", "onActivityResult", "activity", "Landroid/app/Activity;", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDetachedFromWindow", "onFinishInflate", "remove", "position", "file", "setLimit", "limit", "updatePreview", "Companion", "LimitQueue", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class ImagePickerPreview extends LinearLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int b = 703;

    /* renamed from: a  reason: collision with root package name */
    private LimitQueue<Pair<String, Bitmap>> f15983a;
    private HashMap c;

    @JvmOverloads
    public ImagePickerPreview(@NotNull Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
    }

    @JvmOverloads
    public ImagePickerPreview(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
    }

    public void _$_clearFindViewByIdCache() {
        if (this.c != null) {
            this.c.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this.c == null) {
            this.c = new HashMap();
        }
        View view = (View) this.c.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.c.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xiaomi/smarthome/feedback/view/ImagePickerPreview$Companion;", "", "()V", "REQUEST_ALBUM_CODE", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImagePickerPreview(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.f(context, "context");
        this.f15983a = new LimitQueue<>(3);
        setOrientation(0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public /* synthetic */ ImagePickerPreview(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    public final void setLimit(int i) {
        this.f15983a = new LimitQueue<>(i);
        a();
    }

    @NotNull
    public final String[] getPickedItems() {
        Iterable<Pair> iterable = this.f15983a;
        Collection arrayList = new ArrayList(CollectionsKt.a(iterable, 10));
        for (Pair pair : iterable) {
            arrayList.add((String) pair.first);
        }
        Object[] array = ((List) arrayList).toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final void add(@NotNull String str) {
        Intrinsics.f(str, TbsReaderView.KEY_FILE_PATH);
        if (!this.f15983a.isFull()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int a2 = BitmapUtils.a(options.outWidth, options.outHeight, 150, 22500);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = false;
            options2.inSampleSize = a2;
            this.f15983a.add(new Pair(str, BitmapFactory.decodeFile(str, options2)));
            a();
        }
    }

    public final void remove(@NotNull String str) {
        Intrinsics.f(str, "file");
        Iterator it = this.f15983a.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.a((Object) (String) ((Pair) it.next()).first, (Object) str)) {
                break;
            } else {
                i++;
            }
        }
        remove(i);
    }

    public final void remove(int i) {
        Bitmap bitmap;
        int size = this.f15983a.size();
        if (i >= 0 && size > i) {
            Pair remove = this.f15983a.remove(i);
            if (!(remove == null || (bitmap = (Bitmap) remove.second) == null)) {
                bitmap.recycle();
            }
            a();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onActivityResult(@org.jetbrains.annotations.NotNull android.app.Activity r7, int r8, int r9, @org.jetbrains.annotations.Nullable android.content.Intent r10) {
        /*
            r6 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.f(r7, r0)
            r0 = 703(0x2bf, float:9.85E-43)
            if (r8 != r0) goto L_0x0071
            r8 = -1
            if (r9 != r8) goto L_0x0071
            r8 = 0
            r9 = r8
            android.database.Cursor r9 = (android.database.Cursor) r9
            if (r10 == 0) goto L_0x001c
            android.net.Uri r10 = r10.getData()     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r1 = r10
            goto L_0x001d
        L_0x0018:
            r7 = move-exception
            goto L_0x0068
        L_0x001a:
            r7 = r9
            goto L_0x006e
        L_0x001c:
            r1 = r8
        L_0x001d:
            if (r1 == 0) goto L_0x0023
            java.lang.String r8 = r1.getAuthority()     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
        L_0x0023:
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            if (r8 != 0) goto L_0x0061
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r7 = 1
            java.lang.String[] r2 = new java.lang.String[r7]     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r7 = 0
            java.lang.String r8 = "_data"
            r2[r7] = r8     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r7.moveToFirst()     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndex(r8)     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            r9 = r8
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            if (r9 != 0) goto L_0x0062
            java.lang.String r9 = "picPath"
            kotlin.jvm.internal.Intrinsics.b(r8, r9)     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            r6.add(r8)     // Catch:{ Exception -> 0x006e, all -> 0x005d }
            goto L_0x0062
        L_0x005d:
            r8 = move-exception
            r9 = r7
            r7 = r8
            goto L_0x0068
        L_0x0061:
            r7 = r9
        L_0x0062:
            if (r7 == 0) goto L_0x0071
        L_0x0064:
            r7.close()
            goto L_0x0071
        L_0x0068:
            if (r9 == 0) goto L_0x006d
            r9.close()
        L_0x006d:
            throw r7
        L_0x006e:
            if (r7 == 0) goto L_0x0071
            goto L_0x0064
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.feedback.view.ImagePickerPreview.onActivityResult(android.app.Activity, int, int, android.content.Intent):void");
    }

    private final void a() {
        removeAllViews();
        int i = 0;
        for (Object next : this.f15983a) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.b();
            }
            addView(a(i, (Pair) next));
            i = i2;
        }
        if (!this.f15983a.isFull()) {
            addView(b());
        }
    }

    private final View a(int i, Pair<String, Bitmap> pair) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.feedback_image_pick_item, this, false);
        ((ImageView) inflate.findViewById(R.id.image)).setImageBitmap((Bitmap) pair.second);
        inflate.findViewById(R.id.delete_button).setOnClickListener(new ImagePickerPreview$makePreviewView$1(this, i));
        Intrinsics.b(inflate, NetRequestWarningActivity.KEY_ITEM);
        return inflate;
    }

    private final View b() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.feedback_image_pick_item, this, false);
        inflate.setBackgroundColor(0);
        ((ImageView) inflate.findViewById(R.id.image)).setImageResource(R.drawable.image_pick_add);
        View findViewById = inflate.findViewById(R.id.delete_button);
        Intrinsics.b(findViewById, "item.findViewById<View>(R.id.delete_button)");
        findViewById.setVisibility(8);
        inflate.setOnClickListener(new ImagePickerPreview$makeAddBtn$1(this));
        return inflate;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xiaomi/smarthome/feedback/view/ImagePickerPreview$LimitQueue;", "E", "Ljava/util/LinkedList;", "limit", "", "(I)V", "isFull", "", "()Z", "add", "e", "(Ljava/lang/Object;)Z", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    private static final class LimitQueue<E> extends LinkedList<E> {
        private final int limit;

        public LimitQueue(int i) {
            this.limit = i;
        }

        public int getSize() {
            return super.size();
        }

        public final E remove(int i) {
            return removeAt(i);
        }

        public Object removeAt(int i) {
            return super.remove(i);
        }

        public final int size() {
            return getSize();
        }

        public final boolean isFull() {
            return size() >= this.limit;
        }

        public boolean add(E e) {
            if (isFull()) {
                return false;
            }
            return super.add(e);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (Pair pair : this.f15983a) {
            if (pair.second != null) {
                Object obj = pair.second;
                Intrinsics.b(obj, "it.second");
                if (!((Bitmap) obj).isRecycled()) {
                    ((Bitmap) pair.second).recycle();
                }
            }
        }
        this.f15983a.clear();
    }
}
