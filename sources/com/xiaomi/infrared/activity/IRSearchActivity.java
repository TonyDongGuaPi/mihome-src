package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRSearchAdapter;
import com.xiaomi.infrared.bean.IRBrandType;
import com.xiaomi.infrared.utils.CharacterParser;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IRSearchActivity extends BaseActivity implements TextWatcher, View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10182a = "IRSearchActivity";
    /* access modifiers changed from: private */
    public EditText b;
    private ArrayList<IRBrandType> c;
    private IRSearchAdapter d;
    private Runnable e = new Runnable() {
        public void run() {
            InputMethodManager inputMethodManager = (InputMethodManager) IRSearchActivity.this.b.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(IRSearchActivity.this.b, 2);
            }
        }
    };

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static void showSearchActivity(Activity activity, ArrayList<IRBrandType> arrayList, int i) {
        Intent intent = new Intent(activity, IRSearchActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putParcelableArrayListExtra(InifraredContants.IntentParams.s, arrayList);
        activity.startActivityForResult(intent, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getIntent().getParcelableArrayListExtra(InifraredContants.IntentParams.s);
        setContentView(R.layout.activity_ir_search);
        ListView listView = (ListView) findViewById(R.id.ir_search_list);
        View findViewById = findViewById(R.id.title_bar_return);
        this.b = (EditText) findViewById(R.id.edit);
        this.d = new IRSearchAdapter(this, (List<IRBrandType>) null);
        listView.setAdapter(this.d);
        findViewById.setOnClickListener(this);
        this.b.addTextChangedListener(this);
        listView.setOnItemClickListener(this);
        this.mHandler.postDelayed(this.e, 100);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        setResult(-1, getIntent().putExtra(InifraredContants.IntentParams.u, (IRBrandType) this.d.getItem(i)));
        onBackPressed();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancel) {
            a();
        } else if (id == R.id.title_bar_return) {
            finish();
        }
    }

    private void a() {
        this.b.setText("");
    }

    public List<IRBrandType> searchContact(String str, List<IRBrandType> list) {
        ArrayList arrayList = new ArrayList();
        if (str.matches("^([0-9]|[/+]).*")) {
            for (IRBrandType next : list) {
                if (next.d() != null && next.d().contains(str) && !arrayList.contains(next)) {
                    arrayList.add(next);
                }
            }
        } else {
            boolean a2 = CommUtil.a();
            for (IRBrandType next2 : list) {
                CharacterParser.SortToken d2 = CharacterParser.a().d(next2.d());
                if (!a2) {
                    Log.d(f10182a, "searchContact: " + next2.e().toLowerCase(Locale.ENGLISH) + " str " + str);
                    if (TextUtils.isEmpty(next2.e())) {
                        break;
                    } else if (next2.e().toLowerCase(Locale.ENGLISH).contains(str.toLowerCase(Locale.ENGLISH)) && !arrayList.contains(next2)) {
                        arrayList.add(next2);
                    }
                } else if (TextUtils.isEmpty(next2.d())) {
                    break;
                } else if ((next2.d().toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE)) || next2.b().toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE)) || d2.simpleSpell.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE)) || d2.wholeSpell.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE))) && !arrayList.contains(next2)) {
                    arrayList.add(next2);
                }
            }
        }
        return arrayList;
    }

    public void onPause() {
        super.onPause();
        b();
        this.mHandler.removeCallbacks(this.e);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void b() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.d.a((List) null);
        } else {
            this.d.a(searchContact(charSequence.toString(), this.c));
        }
    }
}
