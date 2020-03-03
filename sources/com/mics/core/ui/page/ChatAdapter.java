package com.mics.core.ui.page;

import android.content.Context;
import com.biubiu.kit.core.KitBaseAdapter;
import java.util.List;

public class ChatAdapter extends KitBaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public ChatDelegate f7714a;

    public ChatAdapter(Context context, List<Object> list) {
        super(context, list);
    }

    public ChatDelegate c() {
        return this.f7714a;
    }

    public void a(ChatDelegate chatDelegate) {
        this.f7714a = chatDelegate;
    }
}
