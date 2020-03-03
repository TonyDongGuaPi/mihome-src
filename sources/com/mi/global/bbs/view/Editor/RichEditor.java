package com.mi.global.bbs.view.Editor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RichEditor extends WebView {
    private static final String CALLBACK_SCHEME = "re-callback://";
    private static final String SETUP_HTML = "file:///android_asset/editor.html";
    private static final String STATE_SCHEME = "re-state://";
    /* access modifiers changed from: private */
    public boolean isReady;
    private String mContents;
    private OnDecorationStateListener mDecorationStateListener;
    /* access modifiers changed from: private */
    public AfterInitialLoadListener mLoadListener;
    private OnTextChangeListener mTextChangeListener;

    public interface AfterInitialLoadListener {
        void onAfterInitialLoad(boolean z);
    }

    public interface OnDecorationStateListener {
        void onStateChangeListener(String str, List<Type> list);
    }

    public interface OnTextChangeListener {
        void onTextChange(String str);
    }

    public enum Type {
        BOLD,
        ITALIC,
        SUBSCRIPT,
        SUPERSCRIPT,
        STRIKETHROUGH,
        UNDERLINE,
        H1,
        H2,
        H3,
        H4,
        H5,
        H6,
        ORDEREDLIST,
        UNORDEREDLIST,
        JUSTIFYCENTER,
        JUSTIFYFULL,
        JUSTUFYLEFT,
        JUSTIFYRIGHT
    }

    public RichEditor(Context context) {
        this(context, (AttributeSet) null);
    }

    public RichEditor(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842885);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public RichEditor(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isReady = false;
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(createWebviewClient());
        loadUrl(SETUP_HTML);
        applyAttributes(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public EditorWebViewClient createWebviewClient() {
        return new EditorWebViewClient();
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        this.mTextChangeListener = onTextChangeListener;
    }

    public void setOnDecorationChangeListener(OnDecorationStateListener onDecorationStateListener) {
        this.mDecorationStateListener = onDecorationStateListener;
    }

    public void setOnInitialLoadListener(AfterInitialLoadListener afterInitialLoadListener) {
        this.mLoadListener = afterInitialLoadListener;
    }

    /* access modifiers changed from: private */
    public void callback(String str) {
        this.mContents = str.replaceFirst(CALLBACK_SCHEME, "");
        if (this.mTextChangeListener != null) {
            this.mTextChangeListener.onTextChange(this.mContents);
        }
    }

    /* access modifiers changed from: private */
    public void stateCheck(String str) {
        String upperCase = str.replaceFirst(STATE_SCHEME, "").toUpperCase(Locale.ENGLISH);
        ArrayList arrayList = new ArrayList();
        for (Type type : Type.values()) {
            if (TextUtils.indexOf(upperCase, type.name()) != -1) {
                arrayList.add(type);
            }
        }
        if (this.mDecorationStateListener != null) {
            this.mDecorationStateListener.onStateChangeListener(upperCase, arrayList);
        }
    }

    private void applyAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842927});
        int i = obtainStyledAttributes.getInt(0, -1);
        if (i == 1) {
            exec("javascript:RE.setTextAlign(\"center\")");
        } else if (i == 3) {
            exec("javascript:RE.setTextAlign(\"left\")");
        } else if (i == 5) {
            exec("javascript:RE.setTextAlign(\"right\")");
        } else if (i == 48) {
            exec("javascript:RE.setVerticalAlign(\"top\")");
        } else if (i != 80) {
            switch (i) {
                case 16:
                    exec("javascript:RE.setVerticalAlign(\"middle\")");
                    break;
                case 17:
                    exec("javascript:RE.setVerticalAlign(\"middle\")");
                    exec("javascript:RE.setTextAlign(\"center\")");
                    break;
            }
        } else {
            exec("javascript:RE.setVerticalAlign(\"bottom\")");
        }
        obtainStyledAttributes.recycle();
    }

    public String getHtml() {
        return this.mContents;
    }

    public void setHtml(String str) {
        if (str == null) {
            str = "";
        }
        try {
            exec("javascript:RE.setHtml('" + URLEncoder.encode(str, "UTF-8") + "');");
        } catch (UnsupportedEncodingException unused) {
        }
        this.mContents = str;
    }

    public void setEditorFontColor(int i) {
        String convertHexColorString = convertHexColorString(i);
        exec("javascript:RE.setBaseTextColor('" + convertHexColorString + "');");
    }

    public void setEditorFontSize(int i) {
        exec("javascript:RE.setBaseFontSize('" + i + "px');");
    }

    public void setDirection() {
        exec("javascript:RE.setDirection();");
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        exec("javascript:RE.setPadding('" + i + "px', '" + i2 + "px', '" + i3 + "px', '" + i4 + "px');");
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        setPadding(i, i2, i3, i4);
    }

    public void setEditorBackgroundColor(int i) {
        setBackgroundColor(i);
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
    }

    public void setBackgroundResource(int i) {
        Bitmap decodeResource = Utils.decodeResource(getContext(), i);
        String base64 = Utils.toBase64(decodeResource);
        decodeResource.recycle();
        exec("javascript:RE.setBackgroundImage('url(data:bbs_image/png;base64," + base64 + ")');");
    }

    public void setBackground(Drawable drawable) {
        Bitmap bitmap = Utils.toBitmap(drawable);
        String base64 = Utils.toBase64(bitmap);
        bitmap.recycle();
        exec("javascript:RE.setBackgroundImage('url(data:bbs_image/png;base64," + base64 + ")');");
    }

    public void setBackground(String str) {
        exec("javascript:RE.setBackgroundImage('url(" + str + ")');");
    }

    public void setEditorWidth(int i) {
        exec("javascript:RE.setWidth('" + i + "px');");
    }

    public void setEditorHeight(int i) {
        exec("javascript:RE.setHeight('" + i + "px');");
    }

    public void setPlaceholder(String str) {
        exec("javascript:RE.setPlaceholder('" + str + "');");
    }

    public void loadCSS(String str) {
        exec("javascript:" + ("(function() {    var head  = document.getElementsByTagName(\"head\")[0];    var link  = document.createElement(\"link\");    link.rel  = \"stylesheet\";    link.type = \"text/css\";    link.href = \"" + str + "\";    link.media = \"all\";    head.appendChild(link);}) ();") + "");
    }

    public void undo() {
        exec("javascript:RE.undo();");
    }

    public void redo() {
        exec("javascript:RE.redo();");
    }

    public void setBold() {
        exec("javascript:RE.setBold();");
    }

    public void setItalic() {
        exec("javascript:RE.setItalic();");
    }

    public void setSubscript() {
        exec("javascript:RE.setSubscript();");
    }

    public void setSuperscript() {
        exec("javascript:RE.setSuperscript();");
    }

    public void setStrikeThrough() {
        exec("javascript:RE.setStrikeThrough();");
    }

    public void setUnderline() {
        exec("javascript:RE.setUnderline();");
    }

    public void setTextColor(int i) {
        exec("javascript:RE.prepareInsert();");
        String convertHexColorString = convertHexColorString(i);
        exec("javascript:RE.setTextColor('" + convertHexColorString + "');");
    }

    public void setTextBackgroundColor(int i) {
        exec("javascript:RE.prepareInsert();");
        String convertHexColorString = convertHexColorString(i);
        exec("javascript:RE.setTextBackgroundColor('" + convertHexColorString + "');");
    }

    public void setFontSize(int i) {
        if (i > 7 || i < 1) {
            Log.e("RichEditor", "Font size should have a value between 1-7");
        }
        exec("javascript:RE.setFontSize('" + i + "');");
    }

    public void removeFormat() {
        exec("javascript:RE.removeFormat();");
    }

    public void setHeading(int i) {
        exec("javascript:RE.setHeading('" + i + "');");
    }

    public void setIndent() {
        exec("javascript:RE.setIndent();");
    }

    public void setOutdent() {
        exec("javascript:RE.setOutdent();");
    }

    public void setAlignLeft() {
        exec("javascript:RE.setJustifyLeft();");
    }

    public void setAlignCenter() {
        exec("javascript:RE.setJustifyCenter();");
    }

    public void setAlignRight() {
        exec("javascript:RE.setJustifyRight();");
    }

    public void setBlockquote() {
        exec("javascript:RE.setBlockquote();");
    }

    public void setBullets() {
        exec("javascript:RE.setBullets();");
    }

    public void setNumbers() {
        exec("javascript:RE.setNumbers();");
    }

    public void insertImage(String str, String str2, String str3) {
        exec("javascript:RE.prepareInsert();");
        exec("javascript:RE.insertImage('" + str + "', '" + str2 + "', '" + str3 + "');");
    }

    public void insertVideo(String str) {
        exec("javascript:RE.prepareInsert();");
        exec("javascript:RE.insertVideo('" + str + "');");
    }

    public void insertImage(String str, String str2) {
        insertImage(str, "mi_community_image", str2);
    }

    public void insertLink(String str, String str2) {
        exec("javascript:RE.prepareInsert();");
        exec("javascript:RE.insertLink('" + str + "', '" + str2 + "');");
    }

    public void insertTodo() {
        exec("javascript:RE.prepareInsert();");
        exec("javascript:RE.setTodo('" + Utils.getCurrentTime() + "');");
    }

    public void focusEditor() {
        requestFocus();
        exec("javascript:RE.focus();");
    }

    public void clearFocusEditor() {
        exec("javascript:RE.blurFocus();");
    }

    private String convertHexColorString(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & 16777215)});
    }

    /* access modifiers changed from: protected */
    public void exec(final String str) {
        if (this.isReady) {
            load(str);
        } else {
            postDelayed(new Runnable() {
                public void run() {
                    RichEditor.this.exec(str);
                }
            }, 100);
        }
    }

    private void load(String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            evaluateJavascript(str, (ValueCallback) null);
        } else {
            loadUrl(str);
        }
    }

    protected class EditorWebViewClient extends WebViewClient {
        protected EditorWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            boolean unused = RichEditor.this.isReady = str.equalsIgnoreCase(RichEditor.SETUP_HTML);
            if (RichEditor.this.mLoadListener != null) {
                RichEditor.this.mLoadListener.onAfterInitialLoad(RichEditor.this.isReady);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                String decode = URLDecoder.decode(str, "UTF-8");
                if (TextUtils.indexOf(str, RichEditor.CALLBACK_SCHEME) == 0) {
                    RichEditor.this.callback(decode);
                    return true;
                } else if (TextUtils.indexOf(str, RichEditor.STATE_SCHEME) != 0) {
                    return super.shouldOverrideUrlLoading(webView, str);
                } else {
                    RichEditor.this.stateCheck(decode);
                    return true;
                }
            } catch (UnsupportedEncodingException unused) {
                return false;
            }
        }
    }
}
