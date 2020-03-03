package com.xiaomi.smarthome.lite.scene;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.dragdrop.AbstractDraggableItemViewHolder;
import com.xiaomi.smarthome.R;

public class SceneBaseViewHolder extends AbstractDraggableItemViewHolder {

    public enum SCENE_TYPE {
        CLIENT_ALL_PAGE,
        LITE_CLIENT_ALL_PAGE
    }

    public SceneBaseViewHolder(View view) {
        super(view);
    }

    public static SceneBaseViewHolder a(LayoutInflater layoutInflater, int i) {
        if (i == SCENE_TYPE.LITE_CLIENT_ALL_PAGE.ordinal()) {
            return new NormalSceneViewHolder(layoutInflater.inflate(R.layout.lite_scene_loading_item, (ViewGroup) null));
        }
        if (i == SCENE_TYPE.CLIENT_ALL_PAGE.ordinal()) {
            return new NormalSceneViewHolder(layoutInflater.inflate(R.layout.scene_loading_item, (ViewGroup) null));
        }
        return null;
    }

    public static class NormalSceneViewHolder extends SceneBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f19399a;
        public ImageView b;
        public TextView c;
        public ImageView d;
        public View e;
        public ImageView f;

        public NormalSceneViewHolder(View view) {
            super(view);
            this.f19399a = (ImageView) view.findViewById(R.id.icon);
            this.b = (ImageView) view.findViewById(R.id.icon_loading);
            this.c = (TextView) view.findViewById(R.id.name);
            this.d = (ImageView) view.findViewById(R.id.icon_clickable);
            this.e = view.findViewById(R.id.name_container);
            this.f = (ImageView) view.findViewById(R.id.del_icon);
        }
    }
}
