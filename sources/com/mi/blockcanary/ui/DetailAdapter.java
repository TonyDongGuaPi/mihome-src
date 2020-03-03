package com.mi.blockcanary.ui;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mi.blockcanary.R;
import com.mi.blockcanary.internal.BlockInfo;
import com.mi.blockcanary.ui.DisplayConnectorView;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Iterator;

final class DetailAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6758a = 0;
    private static final int b = 1;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private boolean[] c = new boolean[0];
    private BlockInfo d;

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    DetailAdapter() {
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        boolean z = false;
        if (getItemViewType(i) == 0) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.block_canary_ref_top_row, viewGroup, false);
            }
            ((TextView) a(view, R.id.__leak_canary_row_text)).setText(context.getPackageName());
        } else {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.block_canary_ref_row, viewGroup, false);
            }
            TextView textView = (TextView) a(view, R.id.__leak_canary_row_text);
            if (i == 5) {
                z = true;
            }
            String a2 = a(getItem(i), i, this.c[i]);
            if (z && !this.c[i]) {
                a2 = a2 + " <font color='#919191'>blocked</font>";
            }
            textView.setText(Html.fromHtml(a2));
            ((DisplayConnectorView) a(view, R.id.__leak_canary_row_connector)).setType(c(i));
            ((MoreDetailsView) a(view, R.id.__leak_canary_row_more)).setFolding(this.c[i]);
        }
        return view;
    }

    private DisplayConnectorView.Type c(int i) {
        if (i == 1) {
            return DisplayConnectorView.Type.START;
        }
        return i == getCount() - 1 ? DisplayConnectorView.Type.END : DisplayConnectorView.Type.NODE;
    }

    private String a(String str, int i, boolean z) {
        String replaceAll = str.replaceAll("\r\n", "<br>");
        switch (i) {
            case 1:
                if (z) {
                    replaceAll = replaceAll.substring(replaceAll.indexOf(BlockInfo.f));
                }
                return String.format("<font color='#c48a47'>%s</font> ", new Object[]{replaceAll});
            case 2:
                if (z) {
                    replaceAll = replaceAll.substring(0, replaceAll.indexOf(BlockInfo.k));
                }
                return String.format("<font color='#f3cf83'>%s</font> ", new Object[]{replaceAll});
            case 3:
                if (z) {
                    str = str.substring(0, str.indexOf(BlockInfo.h));
                }
                return String.format("<font color='#998bb5'>%s</font> ", new Object[]{str.replace("cpurate = ", "<br>cpurate<br/>")}).replaceAll(Operators.ARRAY_END_STR, "]<br>");
            default:
                if (z) {
                    Iterator<String> it = BlockCanaryUtils.a().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            int indexOf = replaceAll.indexOf(it.next());
                            if (indexOf > 0) {
                                replaceAll = replaceAll.substring(indexOf);
                            }
                        }
                    }
                }
                return String.format("<font color='#ffffff'>%s</font> ", new Object[]{replaceAll});
        }
    }

    public void a(BlockInfo blockInfo) {
        if (this.d == null || !blockInfo.y.equals(this.d.y)) {
            this.d = blockInfo;
            this.c = new boolean[(this.d.C.size() + 4)];
            Arrays.fill(this.c, true);
            notifyDataSetChanged();
        }
    }

    public void a(int i) {
        this.c[i] = !this.c[i];
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.d == null) {
            return 0;
        }
        return this.d.C.size() + 4;
    }

    /* renamed from: b */
    public String getItem(int i) {
        if (getItemViewType(i) == 0) {
            return null;
        }
        switch (i) {
            case 1:
                return this.d.d();
            case 2:
                return this.d.f();
            case 3:
                return this.d.e();
            default:
                return this.d.C.get(i - 4);
        }
    }

    private static <T extends View> T a(View view, int i) {
        return view.findViewById(i);
    }
}
