package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.customtabs.R;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    private static final String b = "BrowserActionskMenuUi";

    /* renamed from: a  reason: collision with root package name */
    BrowserActionsFallMenuUiListener f478a;
    private final Context c;
    private final Uri d;
    private final List<BrowserActionItem> e;
    private BrowserActionsFallbackMenuDialog f;

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    interface BrowserActionsFallMenuUiListener {
        void a(View view);
    }

    BrowserActionsFallbackMenuUi(Context context, Uri uri, List<BrowserActionItem> list) {
        this.c = context;
        this.d = uri;
        this.e = list;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void a(BrowserActionsFallMenuUiListener browserActionsFallMenuUiListener) {
        this.f478a = browserActionsFallMenuUiListener;
    }

    public void a() {
        final View inflate = LayoutInflater.from(this.c).inflate(R.layout.browser_actions_context_menu_page, (ViewGroup) null);
        this.f = new BrowserActionsFallbackMenuDialog(this.c, a(inflate));
        this.f.setContentView(inflate);
        if (this.f478a != null) {
            this.f.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    BrowserActionsFallbackMenuUi.this.f478a.a(inflate);
                }
            });
        }
        this.f.show();
    }

    private BrowserActionsFallbackMenuView a(View view) {
        BrowserActionsFallbackMenuView browserActionsFallbackMenuView = (BrowserActionsFallbackMenuView) view.findViewById(R.id.browser_actions_menu_view);
        final TextView textView = (TextView) view.findViewById(R.id.browser_actions_header_text);
        textView.setText(this.d.toString());
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextViewCompat.getMaxLines(textView) == Integer.MAX_VALUE) {
                    textView.setMaxLines(1);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    return;
                }
                textView.setMaxLines(Integer.MAX_VALUE);
                textView.setEllipsize((TextUtils.TruncateAt) null);
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.browser_actions_menu_items);
        listView.setAdapter(new BrowserActionsFallbackMenuAdapter(this.e, this.c));
        listView.setOnItemClickListener(this);
        return browserActionsFallbackMenuView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            this.e.get(i).c().send();
            this.f.dismiss();
        } catch (PendingIntent.CanceledException e2) {
            Log.e(b, "Failed to send custom item action", e2);
        }
    }
}
