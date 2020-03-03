package com.mics.core.ui.kit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biubiu.kit.core.AbsKit;
import com.mics.R;
import com.mics.util.Logger;
import com.mics.widget.EmojiKeyboardView;
import com.mics.widget.util.SmileyUtils;
import java.util.ArrayList;
import java.util.List;

public class Emoji extends AbsKit implements EmojiKeyboardView.OnEmojiItemClick {

    /* renamed from: a  reason: collision with root package name */
    private Context f7689a;
    private EmojiKeyboardView b;
    private Data c;

    public View a(ViewGroup viewGroup) {
        Logger.a("Emoji", "emoji kit loading!");
        this.f7689a = viewGroup.getContext();
        View a2 = a(LayoutInflater.from(this.f7689a), viewGroup, R.layout.mics_kit_emoji);
        this.b = (EmojiKeyboardView) a2.findViewById(R.id.emoji);
        return a2;
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            Logger.a("Emoji", "emoji kit binding!");
            Data data = (Data) obj;
            this.c = data;
            List<String> a2 = data.a();
            if (a2 != null) {
                ArrayList arrayList = new ArrayList();
                for (String a3 : a2) {
                    arrayList.add(Integer.valueOf(SmileyUtils.a(this.f7689a, a3)));
                }
                this.b.setResIds(arrayList, R.drawable.mics_icon_delete_normal);
                this.b.setOnEmojiItemClick(this);
            }
        }
    }

    public void a(EmojiKeyboardView emojiKeyboardView, View view, int i, boolean z) {
        List<String> a2 = this.c.a();
        if (z) {
            this.c.b();
            return;
        }
        this.c.a(a2.get(i));
    }

    public static class Data {

        /* renamed from: a  reason: collision with root package name */
        private List<String> f7690a;

        public void a(String str) {
        }

        public void b() {
        }

        public List<String> a() {
            return this.f7690a;
        }

        public void a(List<String> list) {
            this.f7690a = list;
        }
    }
}
