package androidx.browser.browseractions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.customtabs.R;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

class BrowserActionsFallbackMenuAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private final List<BrowserActionItem> f474a;
    private final Context b;

    public long getItemId(int i) {
        return (long) i;
    }

    BrowserActionsFallbackMenuAdapter(List<BrowserActionItem> list, Context context) {
        this.f474a = list;
        this.b = context;
    }

    public int getCount() {
        return this.f474a.size();
    }

    public Object getItem(int i) {
        return this.f474a.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderItem viewHolderItem;
        BrowserActionItem browserActionItem = this.f474a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.browser_actions_context_menu_row, (ViewGroup) null);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.f475a = (ImageView) view.findViewById(R.id.browser_actions_menu_item_icon);
            viewHolderItem.b = (TextView) view.findViewById(R.id.browser_actions_menu_item_text);
            view.setTag(viewHolderItem);
        } else {
            viewHolderItem = (ViewHolderItem) view.getTag();
        }
        viewHolderItem.b.setText(browserActionItem.b());
        if (browserActionItem.a() != 0) {
            viewHolderItem.f475a.setImageDrawable(ResourcesCompat.getDrawable(this.b.getResources(), browserActionItem.a(), (Resources.Theme) null));
        } else {
            viewHolderItem.f475a.setImageDrawable((Drawable) null);
        }
        return view;
    }

    static class ViewHolderItem {

        /* renamed from: a  reason: collision with root package name */
        ImageView f475a;
        TextView b;

        ViewHolderItem() {
        }
    }
}
