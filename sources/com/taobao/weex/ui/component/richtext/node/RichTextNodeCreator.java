package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;

public interface RichTextNodeCreator<T extends RichTextNode> {
    T createRichTextNode(Context context, String str, String str2);
}
