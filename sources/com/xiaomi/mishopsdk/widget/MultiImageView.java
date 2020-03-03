package com.xiaomi.mishopsdk.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.DensityUtil;
import com.xiaomi.mishopsdk.util.PicUtil;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiImageView extends RelativeLayout {
    private static final int COLUMN_COUNT = 3;
    private static final int COLUMN_SEPRATE_DIP = 2;
    private boolean fillWidth;
    private int longPx;
    private PicUtil pUtil = PicUtil.getInstance();
    private int shortPx;
    private int totalWidth;

    public MultiImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }

    public void setParams(int i, int i2, int i3, boolean z) {
        this.totalWidth = i;
        this.longPx = i2;
        this.shortPx = i3;
        this.fillWidth = z;
    }

    public void startLoadOneImage(Bitmap bitmap) {
        removeChildViews();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.totalWidth, this.shortPx);
        View inflate = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.mishopsdk_multi_graph_item, (ViewGroup) null);
        inflate.setLayoutParams(layoutParams);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.mishopsdk_multi_graph_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bitmap);
        addView(inflate);
    }

    public void startLoadImages(List<String> list, boolean z, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        int i;
        final List<String> list2 = list;
        View.OnClickListener onClickListener3 = onClickListener;
        View.OnClickListener onClickListener4 = onClickListener2;
        removeChildViews();
        setOnClickListener(onClickListener3);
        if (list2 != null) {
            float f = 2.0f;
            int i2 = 3;
            int dip2px = (this.totalWidth - (DensityUtil.dip2px(2.0f) * 2)) / 3;
            if (list.size() == 1) {
                dip2px = this.longPx;
                i = this.longPx;
                if (this.fillWidth) {
                    dip2px = this.totalWidth;
                    i = this.longPx;
                }
            } else {
                i = dip2px;
            }
            if (list.size() == 4) {
                i2 = 2;
            }
            int i3 = 0;
            boolean z2 = list.size() > 9 && !z;
            final int i4 = 0;
            for (String next : list) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px, i);
                int i5 = i4 % i2;
                int i6 = i4 / i2;
                layoutParams.setMargins(i5 * (dip2px + DensityUtil.dip2px(f)), i6 * (i + DensityUtil.dip2px(f)), i3, i3);
                View inflate = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.mishopsdk_multi_graph_item, (ViewGroup) null);
                inflate.setLayoutParams(layoutParams);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.mishopsdk_multi_graph_image);
                if (list.size() == 1) {
                    this.pUtil.load(next).centerCrop().placeholder(R.drawable.mishopsdk_default_pic_small_inverse).resize(CommonUtils.x, CommonUtils.x).into(imageView);
                } else {
                    this.pUtil.load(next).centerCrop().placeholder(R.drawable.mishopsdk_default_pic_small_inverse).resize(150, 150).into(imageView);
                }
                inflate.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return false;
                    }
                });
                addView(inflate);
                if (!z2 || i4 < 8) {
                    if (onClickListener4 == null) {
                        inflate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("curPicIndex", i4);
                                bundle.putSerializable("picItems", (ArrayList) list2);
                                Intent intent = new Intent();
                                intent.setPackage(Constants.REAL_PACKAGE_NAME);
                                intent.putExtra(Constants.Plugin.ARGUMENT_PLUGINID, Constants.Plugin.PLUGINID_PHOTOPICKER);
                                intent.putExtras(bundle);
                                intent.setAction(Constants.Plugin.ACTION_ROOT);
                                intent.setData(Uri.parse("ShopPlugin://com.xiaomi.shop2.plugin.previewimage.PreviewRemotePicsFragment"));
                                MultiImageView.this.getContext().startActivity(intent);
                            }
                        });
                    } else {
                        inflate.setTag(Integer.valueOf(i4));
                        inflate.setOnClickListener(onClickListener4);
                    }
                    i4++;
                    f = 2.0f;
                    i3 = 0;
                } else {
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dip2px, i);
                    layoutParams2.setMargins(i5 * (dip2px + DensityUtil.dip2px(2.0f)), i6 * (i + DensityUtil.dip2px(2.0f)), 0, 0);
                    TextView textView = new TextView(getContext());
                    textView.setBackgroundColor(getResources().getColor(R.color.mishopsdk_trans_O_50));
                    textView.setLayoutParams(layoutParams2);
                    textView.setTextColor(getResources().getColor(R.color.mishopsdk_white));
                    textView.setTextSize(1, 13.0f);
                    textView.setGravity(17);
                    textView.setText(getResources().getString(R.string.mishopsdk_multi_graph_more, new Object[]{Integer.valueOf(list.size())}));
                    textView.setOnClickListener(onClickListener3);
                    addView(textView);
                    return;
                }
            }
        }
    }

    public void startLoadLocalImages(List<String> list, boolean z, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        List<String> list2 = list;
        View.OnClickListener onClickListener3 = onClickListener;
        View.OnClickListener onClickListener4 = onClickListener2;
        removeChildViews();
        setOnClickListener(onClickListener3);
        float f = 2.0f;
        int dip2px = (this.totalWidth - (DensityUtil.dip2px(2.0f) * 2)) / 3;
        if (list2 != null) {
            list2.add("local_image");
            int i = 0;
            boolean z2 = list.size() > 9 && !z;
            int i2 = 0;
            for (String next : list) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px, dip2px);
                int i3 = i2 % 3;
                int i4 = i2 / 3;
                layoutParams.setMargins((DensityUtil.dip2px(f) + dip2px) * i3, i4 * (dip2px + DensityUtil.dip2px(f)), i, i);
                View inflate = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.mishopsdk_multi_graph_item, (ViewGroup) null);
                inflate.setLayoutParams(layoutParams);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.mishopsdk_multi_graph_image);
                if (list.size() - 1 > i2) {
                    if (!TextUtils.isEmpty(next) && new File(next).isFile()) {
                        this.pUtil.load(new File(next)).placeholder(R.drawable.mishopsdk_default_pic_small_inverse).centerCrop().resize(dip2px, dip2px).into(imageView);
                    }
                } else if (list.size() - 1 == i2) {
                    imageView.setImageResource(R.drawable.mishopsdk_comment_add_image);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }
                inflate.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return false;
                    }
                });
                addView(inflate);
                if (!z2 || i2 < 8) {
                    if (onClickListener4 == null) {
                        inflate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                            }
                        });
                    } else {
                        inflate.setTag(Integer.valueOf(i2));
                        inflate.setOnClickListener(onClickListener4);
                    }
                    i2++;
                    f = 2.0f;
                    i = 0;
                } else {
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dip2px, dip2px);
                    layoutParams2.setMargins(i3 * (DensityUtil.dip2px(2.0f) + dip2px), i4 * (dip2px + DensityUtil.dip2px(2.0f)), 0, 0);
                    TextView textView = new TextView(getContext());
                    textView.setBackgroundColor(getResources().getColor(R.color.mishopsdk_trans_O_50));
                    textView.setLayoutParams(layoutParams2);
                    textView.setTextColor(getResources().getColor(R.color.mishopsdk_white));
                    textView.setTextSize(1, 13.0f);
                    textView.setGravity(17);
                    textView.setText(getResources().getString(R.string.mishopsdk_multi_graph_more, new Object[]{Integer.valueOf(list.size())}));
                    textView.setOnClickListener(onClickListener3);
                    addView(textView);
                    return;
                }
            }
        }
    }

    public void removeChildViews() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) getChildAt(i).findViewById(R.id.mishopsdk_multi_graph_image);
            if (imageView != null) {
                this.pUtil.getPicasso().cancelRequest(imageView);
            }
        }
        removeAllViews();
    }
}
