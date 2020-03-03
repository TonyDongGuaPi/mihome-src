package com.mics.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import com.biubiu.kit.core.KitBaseAdapter;
import com.mics.R;
import com.mics.core.ui.kit.Emoji;
import com.mics.widget.GalleryLayoutManager;
import com.mics.widget.stickyball.widget.DotIndicatorView;
import com.mics.widget.util.SmileyUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

public class EmojiKeyboardLoader {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f7805a = false;

    public static void a(Context context, RecyclerView recyclerView, EditText editText) {
        f7805a = false;
        String[] stringArray = context.getResources().getStringArray(R.array.smiley_values);
        int length = (stringArray.length / 27) + (stringArray.length % 27 == 0 ? 0 : 1);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = i * 27; i2 < stringArray.length && arrayList2.size() < 27; i2++) {
                arrayList2.add(stringArray[i2]);
            }
            EmojiData emojiData = new EmojiData(editText);
            emojiData.a((List<String>) arrayList2);
            arrayList.add(emojiData);
        }
        recyclerView.setAdapter(new KitBaseAdapter(context, arrayList));
    }

    public static void a(final RecyclerView recyclerView, final DotIndicatorView dotIndicatorView) {
        if (!f7805a) {
            f7805a = true;
            dotIndicatorView.setSelectedView(DotIndicatorView.NORMAL_BALL);
            final GalleryLayoutManager galleryLayoutManager = new GalleryLayoutManager(0);
            galleryLayoutManager.a((GalleryLayoutManager.OnItemSelectedListener) new GalleryLayoutManager.OnItemSelectedListener() {
                public void a(RecyclerView recyclerView, View view, int i) {
                    dotIndicatorView.setCurrentItem(i);
                }
            });
            recyclerView.postDelayed(new Runnable() {
                public void run() {
                    galleryLayoutManager.a(recyclerView, 0);
                }
            }, 200);
        }
    }

    public static class EmojiData extends Emoji.Data {

        /* renamed from: a  reason: collision with root package name */
        private EditText f7808a;

        EmojiData(EditText editText) {
            this.f7808a = editText;
        }

        public void a(String str) {
            this.f7808a.getText().append(str);
        }

        public void b() {
            String obj = this.f7808a.getText().toString();
            if (obj.length() > 0 && obj.charAt(obj.length() - 1) == ']') {
                int lastIndexOf = obj.lastIndexOf(Operators.ARRAY_START_STR);
                if (lastIndexOf >= 0) {
                    if (SmileyUtils.a(this.f7808a.getContext(), obj.substring(lastIndexOf)) != 0) {
                        this.f7808a.getText().delete(lastIndexOf, obj.length());
                    }
                }
            } else if (obj.length() > 0) {
                this.f7808a.getText().delete(obj.length() - 1, obj.length());
            }
        }
    }
}
