package com.mobikwik.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.mobikwik.sdk.lib.User;
import com.mobikwik.sdk.lib.utils.Utils;

public class GetUserDetailsActivity extends Activity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private User f8353a;

    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editEmail);
        if (Utils.isValidEmail(editText)) {
            EditText editText2 = (EditText) findViewById(R.id.editPhone);
            if (Utils.isValidMobile(editText2)) {
                String obj = editText.getText().toString();
                String obj2 = editText2.getText().toString();
                if (this.f8353a == null) {
                    this.f8353a = new User(obj, obj2);
                } else {
                    this.f8353a.setEmail(obj);
                    this.f8353a.setCell(obj2);
                }
                Intent intent = new Intent();
                intent.putExtra("user", this.f8353a);
                setResult(-1, intent);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.mk_activity_get_userdetails);
        this.f8353a = (User) getIntent().getSerializableExtra("user");
        findViewById(R.id.btnGetDetailsOk).setOnClickListener(this);
        EditText editText = (EditText) findViewById(R.id.editEmail);
        EditText editText2 = (EditText) findViewById(R.id.editPhone);
        if (this.f8353a != null) {
            if (this.f8353a.isEmailValid()) {
                editText.setText(this.f8353a.getEmail());
            }
            if (this.f8353a.isCellValid()) {
                editText2.setText(this.f8353a.getCell());
            }
        }
    }
}
