package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;
import java.text.ParsePosition;
import java.util.List;

public class NameEditDialogHelper {

    public interface NameEditListener {
        void a(String str);

        String b(String str);
    }

    public interface NameEditListenerV2 extends NameEditListener {
        void a(ClientRemarkInputView clientRemarkInputView, String str);
    }

    public static MLAlertDialog a(Context context, String str, String str2, String str3, NameEditListener nameEditListener) {
        return a(context, str, str2, str3, (String) null, (List<String>) null, nameEditListener);
    }

    public static MLAlertDialog a(Context context, String str, String str2, String str3, String str4, List<String> list, NameEditListener nameEditListener) {
        final List<String> list2 = list;
        String str5 = TextUtils.isEmpty(str2) ? "" : str2;
        String str6 = TextUtils.isEmpty(str3) ? "" : str3;
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(context).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        final EditText editText = clientRemarkInputView.getEditText();
        final CommonFlowGroup commonFlowGroup = clientRemarkInputView.getCommonFlowGroup();
        TextView titleRoomRecommend = clientRemarkInputView.getTitleRoomRecommend();
        final ParsePosition parsePosition = new ParsePosition(-1);
        if (list2 == null || list.size() == 0) {
            commonFlowGroup.setVisibility(8);
        } else {
            commonFlowGroup.setData(list2);
            commonFlowGroup.setVisibility(0);
            commonFlowGroup.showAddView(false);
            commonFlowGroup.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
                public void a() {
                }

                public void a(int i) {
                    parsePosition.setIndex(i);
                }
            });
        }
        if (TextUtils.isEmpty(str4)) {
            titleRoomRecommend.setVisibility(8);
        } else {
            titleRoomRecommend.setVisibility(0);
            titleRoomRecommend.setText(str4);
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
        final Context context2 = context;
        final NameEditListener nameEditListener2 = nameEditListener;
        MLAlertDialog b = new MLAlertDialog.Builder(context2).a((CharSequence) str5).b((View) clientRemarkInputView).d(true).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nameEditListener2 == null) {
                    return;
                }
                if (parsePosition.getIndex() <= 0 || list2 == null) {
                    nameEditListener2.a(editText.getText().toString());
                } else {
                    nameEditListener2.a((String) list2.get(parsePosition.getIndex()));
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).b();
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, b, str, str6, false);
        b.show();
        final Button button = b.getButton(-1);
        button.setEnabled(false);
        button.setTextColor(context.getResources().getColor(R.color.black_30_transparent));
        final EditText editText2 = editText;
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                clientRemarkInputView.setAlertText("");
                button.setEnabled(true);
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = editText2.getText().toString();
                if (obj.length() <= 0) {
                    button.setEnabled(false);
                    button.setTextColor(context2.getResources().getColor(R.color.black_30_transparent));
                } else if (StringUtil.u(obj)) {
                    clientRemarkInputView.setAlertText(context2.getString(R.string.tag_save_data_description));
                    button.setEnabled(false);
                    button.setTextColor(context2.getResources().getColor(R.color.black_30_transparent));
                } else if (!HomeManager.A(obj)) {
                    clientRemarkInputView.setAlertText(context2.getString(R.string.room_name_too_long));
                    button.setEnabled(false);
                    button.setTextColor(context2.getResources().getColor(R.color.black_30_transparent));
                } else {
                    if (commonFlowGroup.getSelectIndex() >= 0) {
                        commonFlowGroup.setSelectIndex(-1);
                        parsePosition.setIndex(-1);
                    }
                    if (nameEditListener2 != null) {
                        String b2 = nameEditListener2.b(obj);
                        if (!TextUtils.isEmpty(b2)) {
                            button.setEnabled(false);
                            button.setTextColor(context2.getResources().getColor(R.color.black_30_transparent));
                            clientRemarkInputView.setAlertText(b2);
                            return;
                        }
                        clientRemarkInputView.setAlertText("");
                        button.setEnabled(true);
                        button.setTextColor(context2.getResources().getColor(R.color.std_dialog_button_green));
                        return;
                    }
                    clientRemarkInputView.setAlertText("");
                    button.setEnabled(true);
                    button.setTextColor(context2.getResources().getColor(R.color.std_dialog_button_green));
                }
            }
        });
        return b;
    }

    public static void a(Context context, int i, String str, String str2, String str3, int i2, NameEditListenerV2 nameEditListenerV2) {
        final ClientRemarkInputView clientRemarkInputView;
        int i3 = i;
        String str4 = TextUtils.isEmpty(str2) ? "" : str2;
        String str5 = TextUtils.isEmpty(str3) ? "" : str3;
        if (i3 == -1) {
            clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(context).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        } else {
            clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(context).inflate(i3, (ViewGroup) null);
        }
        final EditText editText = clientRemarkInputView.getEditText();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter((i2 * 2) + 1)});
        final NameEditListenerV2 nameEditListenerV22 = nameEditListenerV2;
        MLAlertDialog b = new MLAlertDialog.Builder(context).a((CharSequence) str4).b((View) clientRemarkInputView).d(true).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nameEditListenerV22 != null) {
                    nameEditListenerV22.a(clientRemarkInputView, editText.getText().toString());
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).b();
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, b, str, str5, false, i2);
        b.show();
        Button button = b.getButton(-1);
        final ClientRemarkInputView clientRemarkInputView2 = clientRemarkInputView;
        b(editText, clientRemarkInputView2, context, button, i2, nameEditListenerV2);
        final Button button2 = button;
        final EditText editText2 = editText;
        final Context context2 = context;
        final int i4 = i2;
        final NameEditListenerV2 nameEditListenerV23 = nameEditListenerV2;
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                clientRemarkInputView2.setAlertText("");
                button2.setEnabled(true);
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NameEditDialogHelper.b(editText2, clientRemarkInputView2, context2, button2, i4, nameEditListenerV23);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(EditText editText, ClientRemarkInputView clientRemarkInputView, Context context, Button button, int i, NameEditListenerV2 nameEditListenerV2) {
        String obj = editText.getText().toString();
        if (obj.length() <= 0) {
            button.setEnabled(false);
            button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
        } else if (StringUtil.a((CharSequence) obj, StringUtil.n)) {
            clientRemarkInputView.setAlertText(context.getString(R.string.tag_save_data_description));
            button.setEnabled(false);
            button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
        } else if (StringUtil.b((CharSequence) obj) > i * 2) {
            clientRemarkInputView.setAlertText(context.getString(R.string.room_name_too_long));
            button.setEnabled(false);
            button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
        } else if (nameEditListenerV2 != null) {
            String b = nameEditListenerV2.b(obj);
            if (!TextUtils.isEmpty(b)) {
                button.setEnabled(false);
                button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
                clientRemarkInputView.setAlertText(b);
                return;
            }
            clientRemarkInputView.setAlertText("");
            button.setEnabled(true);
            button.setTextColor(context.getResources().getColor(R.color.std_dialog_button_green));
        } else {
            clientRemarkInputView.setAlertText("");
            button.setEnabled(true);
            button.setTextColor(context.getResources().getColor(R.color.std_dialog_button_green));
        }
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = context.getResources().getString(R.string.name_duplicate);
        }
        new MLAlertDialog.Builder(context).b((CharSequence) str).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }
}
