package com.mi.global.shop.imageselector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mi.global.shop.R;
import com.mi.global.shop.util.ImageUtil;

public class ImageViewFragment extends Fragment {
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.shop_fragment_image_view, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        String string = getArguments().getString("image");
        if (!TextUtils.isEmpty(string)) {
            ((ImageView) view).setImageBitmap(ImageUtil.a(string, 360, 360));
        }
    }

    public static ImageViewFragment a(String str) {
        ImageViewFragment imageViewFragment = new ImageViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("image", str);
        imageViewFragment.setArguments(bundle);
        return imageViewFragment;
    }
}
