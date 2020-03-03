package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRSearchProvinceAdapter;
import com.xiaomi.infrared.bean.NameIdEntity;
import com.xiaomi.infrared.utils.CharacterParser;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IRSearchProvinceActivity extends BaseActivity implements TextWatcher, View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private EditText f10184a;
    private ListView b;
    private IRSearchProvinceAdapter c;
    /* access modifiers changed from: private */
    public List<NameIdEntity> d;
    private List<NameIdEntity> e;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static void showActivity(Activity activity, ArrayList<NameIdEntity> arrayList, int i) {
        Intent intent = new Intent(activity, IRSearchProvinceActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putParcelableArrayListExtra(InifraredContants.IntentParams.b, arrayList);
        activity.startActivityForResult(intent, i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = getIntent().getParcelableArrayListExtra(InifraredContants.IntentParams.b);
        setContentView(R.layout.activity_ir_search);
        this.b = (ListView) findViewById(R.id.ir_search_list);
        View findViewById = findViewById(R.id.title_bar_return);
        this.f10184a = (EditText) findViewById(R.id.edit);
        this.f10184a.setHint(R.string.inifrare_search_hit);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) IRSearchProvinceActivity.this.getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.toggleSoftInput(0, 2);
                }
            }
        }, 200);
        findViewById.setOnClickListener(this);
        this.f10184a.addTextChangedListener(this);
    }

    private void a() {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (this.c == null) {
            this.c = new IRSearchProvinceAdapter(this, this.d);
            this.b.setAdapter(this.c);
        } else {
            this.c.a(this.d);
        }
        this.b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = IRSearchProvinceActivity.this.getIntent();
                intent.putExtra(InifraredContants.IntentParams.c, (Parcelable) IRSearchProvinceActivity.this.d.get(i));
                IRSearchProvinceActivity.this.setResult(-1, intent);
                IRSearchProvinceActivity.this.onBackPressed();
            }
        });
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.d.clear();
            a();
            return;
        }
        this.d = searchContact(charSequence.toString(), this.e);
        a();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancel) {
            b();
        } else if (id == R.id.title_bar_return) {
            finish();
        }
    }

    private void b() {
        this.f10184a.setText("");
    }

    public void finish() {
        super.finish();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.f10184a.getWindowToken(), 0);
        }
    }

    public List<NameIdEntity> searchContact(String str, List<NameIdEntity> list) {
        ArrayList arrayList = new ArrayList();
        if (str.matches("^([0-9]|[/+]).*")) {
            for (NameIdEntity next : list) {
                if (next.c() != null && next.c().contains(str) && !arrayList.contains(next)) {
                    arrayList.add(next);
                }
            }
        } else {
            for (NameIdEntity next2 : list) {
                CharacterParser.SortToken d2 = CharacterParser.a().d(next2.c());
                String c2 = CharacterParser.a().c(next2.c());
                if (next2.c() != null && ((next2.c().toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE)) || c2.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE)) || d2.simpleSpell.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE)) || d2.wholeSpell.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE))) && !arrayList.contains(next2))) {
                    arrayList.add(next2);
                }
            }
        }
        return arrayList;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
    }
}
