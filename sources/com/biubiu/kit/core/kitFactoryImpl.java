package com.biubiu.kit.core;

import com.mics.core.ui.kit.Card;
import com.mics.core.ui.kit.CardMutli;
import com.mics.core.ui.kit.DividerHint;
import com.mics.core.ui.kit.Emoji;
import com.mics.core.ui.kit.Hint;
import com.mics.core.ui.kit.ImageLeft;
import com.mics.core.ui.kit.ImageRight;
import com.mics.core.ui.kit.TextLeft;
import com.mics.core.ui.kit.TextRight;
import com.mics.core.ui.kit.Time;
import com.mics.core.ui.page.SettingHostActivity;
import com.mics.core.ui.page.SettingSDKActivity;
import com.mics.widget.EmojiKeyboardLoader;

public class kitFactoryImpl implements IKitFactory {
    public void map() {
        KitFactory.a(SettingSDKActivity.Data.class, SettingSDKActivity.HostKit.class);
        KitFactory.a(SettingSDKActivity.HostTitleKit.Data.class, SettingSDKActivity.HostTitleKit.class);
        KitFactory.a(SettingHostActivity.Data.class, SettingHostActivity.HostKit.class);
        KitFactory.a(SettingHostActivity.HostTitleKit.Data.class, SettingHostActivity.HostTitleKit.class);
        KitFactory.a(CardMutli.Data.class, CardMutli.class);
        KitFactory.a(DividerHint.Data.class, DividerHint.class);
        KitFactory.a(ImageLeft.Data.class, ImageLeft.class);
        KitFactory.a(Card.Data.class, Card.class);
        KitFactory.a(Time.Data.class, Time.class);
        KitFactory.a(TextLeft.Data.class, TextLeft.class);
        KitFactory.a(ImageRight.Data.class, ImageRight.class);
        KitFactory.a(Hint.Data.class, Hint.class);
        KitFactory.a(Emoji.Data.class, Emoji.class);
        KitFactory.a(TextRight.Data.class, TextRight.class);
        KitFactory.a(EmojiKeyboardLoader.EmojiData.class, Emoji.class);
    }

    public Object create(String str) {
        return KitFactory.a(str);
    }
}
