package com.mi.blockcanary.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.mi.blockcanary.BlockCanaryContext;
import com.mi.blockcanary.BlockCanaryInternals;
import com.mi.blockcanary.LogWriter;
import com.mi.blockcanary.R;
import com.mi.blockcanary.internal.BlockInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DisplayActivity extends Activity {
    public static final String SHOW_BLOCK_EXTRA_KEY = "BlockStartTime";

    /* renamed from: a  reason: collision with root package name */
    private static final String f6759a = "DisplayActivity";
    private static final String b = "show_latest";
    /* access modifiers changed from: private */
    public List<BlockInfoEx> c = new ArrayList();
    /* access modifiers changed from: private */
    public String d;
    private ListView e;
    private TextView f;
    private Button g;
    /* access modifiers changed from: private */
    public int h;

    public static PendingIntent createPendingIntent(Context context, String str) {
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra(b, str);
        intent.setFlags(335544320);
        return PendingIntent.getActivity(context, 1, intent, 134217728);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.d = bundle.getString(SHOW_BLOCK_EXTRA_KEY);
        } else {
            Intent intent = getIntent();
            if (intent.hasExtra(b)) {
                this.d = intent.getStringExtra(b);
            }
        }
        setContentView(R.layout.block_canary_display_leak);
        this.e = (ListView) findViewById(R.id.__leak_canary_display_leak_list);
        this.f = (TextView) findViewById(R.id.__leak_canary_display_leak_failure);
        this.g = (Button) findViewById(R.id.__leak_canary_action);
        this.h = getResources().getInteger(R.integer.block_canary_max_stored_count);
        a();
    }

    public Object onRetainNonConfigurationInstance() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(SHOW_BLOCK_EXTRA_KEY, this.d);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LoadBlocks.a(this);
    }

    public void setTheme(int i) {
        if (i == R.style.block_canary_BlockCanary_Base) {
            super.setTheme(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LoadBlocks.a();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        final BlockInfoEx a2 = a(this.d);
        if (a2 == null) {
            return false;
        }
        menu.add(R.string.block_canary_share_leak).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                DisplayActivity.this.a(a2);
                return true;
            }
        });
        menu.add(R.string.block_canary_share_stack_dump).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                DisplayActivity.this.b(a2);
                return true;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        this.d = null;
        a();
        return true;
    }

    public void onBackPressed() {
        if (this.d != null) {
            this.d = null;
            a();
            return;
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a(BlockInfoEx blockInfoEx) {
        String blockInfoEx2 = blockInfoEx.toString();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", blockInfoEx2);
        startActivity(Intent.createChooser(intent, getString(R.string.block_canary_share_with)));
    }

    /* access modifiers changed from: private */
    public void b(BlockInfoEx blockInfoEx) {
        File file = blockInfoEx.D;
        if (Build.VERSION.SDK_INT >= 9) {
            file.setReadable(true, false);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("application/octet-stream");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        startActivity(Intent.createChooser(intent, getString(R.string.block_canary_share_with)));
    }

    /* access modifiers changed from: private */
    public void a() {
        BlockInfoEx a2 = a(this.d);
        if (a2 == null) {
            this.d = null;
        }
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        if (a2 != null) {
            c(a2);
        } else {
            b();
        }
    }

    private void b() {
        ListAdapter adapter = this.e.getAdapter();
        int i = 0;
        if (adapter instanceof BlockListAdapter) {
            ((BlockListAdapter) adapter).notifyDataSetChanged();
        } else {
            this.e.setAdapter(new BlockListAdapter());
            this.e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    String unused = DisplayActivity.this.d = ((BlockInfoEx) DisplayActivity.this.c.get(i)).y;
                    DisplayActivity.this.a();
                }
            });
            if (Build.VERSION.SDK_INT >= 11) {
                invalidateOptionsMenu();
                ActionBar actionBar = getActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(false);
                }
            }
            setTitle(getString(R.string.block_canary_block_list_title, new Object[]{getPackageName()}));
            this.g.setText(R.string.block_canary_delete_all);
            this.g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new AlertDialog.Builder(DisplayActivity.this).setTitle(DisplayActivity.this.getString(R.string.block_canary_delete)).setMessage(DisplayActivity.this.getString(R.string.block_canary_delete_all_dialog_content)).setPositiveButton(DisplayActivity.this.getString(R.string.block_canary_yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LogWriter.b();
                            List unused = DisplayActivity.this.c = Collections.emptyList();
                            DisplayActivity.this.a();
                        }
                    }).setNegativeButton(DisplayActivity.this.getString(R.string.block_canary_no), (DialogInterface.OnClickListener) null).show();
                }
            });
        }
        Button button = this.g;
        if (this.c.isEmpty()) {
            i = 8;
        }
        button.setVisibility(i);
    }

    private void c(final BlockInfoEx blockInfoEx) {
        final DetailAdapter detailAdapter;
        ListAdapter adapter = this.e.getAdapter();
        if (adapter instanceof DetailAdapter) {
            detailAdapter = (DetailAdapter) adapter;
        } else {
            detailAdapter = new DetailAdapter();
            this.e.setAdapter(detailAdapter);
            this.e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    detailAdapter.a(i);
                }
            });
            if (Build.VERSION.SDK_INT >= 11) {
                invalidateOptionsMenu();
                ActionBar actionBar = getActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
            this.g.setVisibility(0);
            this.g.setText(R.string.block_canary_delete);
        }
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (blockInfoEx != null) {
                    blockInfoEx.D.delete();
                    String unused = DisplayActivity.this.d = null;
                    DisplayActivity.this.c.remove(blockInfoEx);
                    DisplayActivity.this.a();
                }
            }
        });
        detailAdapter.a((BlockInfo) blockInfoEx);
        setTitle(getString(R.string.block_canary_class_has_blocked, new Object[]{Long.valueOf(blockInfoEx.w)}));
    }

    private BlockInfoEx a(String str) {
        if (this.c == null || TextUtils.isEmpty(str)) {
            return null;
        }
        for (BlockInfoEx next : this.c) {
            if (next.y != null && str.equals(next.y)) {
                return next;
            }
        }
        return null;
    }

    class BlockListAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        BlockListAdapter() {
        }

        public int getCount() {
            return DisplayActivity.this.c.size();
        }

        /* renamed from: a */
        public BlockInfoEx getItem(int i) {
            return (BlockInfoEx) DisplayActivity.this.c.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str;
            if (view == null) {
                view = LayoutInflater.from(DisplayActivity.this).inflate(R.layout.block_canary_block_row, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.__leak_canary_row_text);
            TextView textView2 = (TextView) view.findViewById(R.id.__leak_canary_row_time);
            BlockInfoEx a2 = getItem(i);
            if (i == 0 && DisplayActivity.this.c.size() == DisplayActivity.this.h) {
                str = "MAX. ";
            } else {
                str = (DisplayActivity.this.c.size() - i) + ". ";
            }
            textView.setText(str + BlockCanaryUtils.a((BlockInfo) a2) + " " + DisplayActivity.this.getString(R.string.block_canary_class_has_blocked, new Object[]{Long.valueOf(a2.w)}));
            textView2.setText(DateUtils.formatDateTime(DisplayActivity.this, a2.D.lastModified(), 17));
            return view;
        }
    }

    static class LoadBlocks implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        static final List<LoadBlocks> f6768a = new ArrayList();
        static final Executor b = Executors.newSingleThreadExecutor();
        /* access modifiers changed from: private */
        public DisplayActivity c;
        private final Handler d = new Handler(Looper.getMainLooper());

        LoadBlocks(DisplayActivity displayActivity) {
            this.c = displayActivity;
        }

        static void a(DisplayActivity displayActivity) {
            LoadBlocks loadBlocks = new LoadBlocks(displayActivity);
            f6768a.add(loadBlocks);
            b.execute(loadBlocks);
        }

        static void a() {
            for (LoadBlocks loadBlocks : f6768a) {
                loadBlocks.c = null;
            }
            f6768a.clear();
        }

        public void run() {
            boolean z;
            final ArrayList arrayList = new ArrayList();
            File[] f = BlockCanaryInternals.f();
            if (f != null) {
                int length = f.length;
                int i = 0;
                while (i < length) {
                    File file = f[i];
                    try {
                        BlockInfoEx a2 = BlockInfoEx.a(file);
                        if (BlockCanaryUtils.b((BlockInfo) a2)) {
                            if (BlockCanaryUtils.c(a2)) {
                                if (BlockCanaryContext.i().n()) {
                                    file.delete();
                                    file = null;
                                }
                                z = false;
                            } else {
                                z = true;
                            }
                            a2.E = BlockCanaryUtils.a((BlockInfo) a2);
                            if (BlockCanaryContext.i().m() && TextUtils.isEmpty(a2.E)) {
                                z = false;
                            }
                            if (z && file != null) {
                                arrayList.add(a2);
                            }
                            i++;
                        } else {
                            throw new BlockInfoCorruptException(a2);
                        }
                    } catch (Exception e) {
                        file.delete();
                        Log.e(DisplayActivity.f6759a, "Could not read block log file, deleted :" + file, e);
                    }
                }
                Collections.sort(arrayList, new Comparator<BlockInfoEx>() {
                    /* renamed from: a */
                    public int compare(BlockInfoEx blockInfoEx, BlockInfoEx blockInfoEx2) {
                        return Long.valueOf(blockInfoEx2.D.lastModified()).compareTo(Long.valueOf(blockInfoEx.D.lastModified()));
                    }
                });
            }
            this.d.post(new Runnable() {
                public void run() {
                    LoadBlocks.f6768a.remove(LoadBlocks.this);
                    if (LoadBlocks.this.c != null) {
                        List unused = LoadBlocks.this.c.c = arrayList;
                        Log.d(DisplayActivity.f6759a, "load block entries: " + arrayList.size());
                        LoadBlocks.this.c.a();
                    }
                }
            });
        }
    }
}
