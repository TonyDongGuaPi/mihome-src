package com.mibi.common.base;

import com.mibi.common.decorator.AutoSave;
import com.mibi.common.decorator.AutoSaveActivityDecorator;
import com.mibi.common.decorator.AutoSaveFragmentDecorator;

public class DecoratorFactory {
    public static void a(DecoratableActivity decoratableActivity) {
        if (decoratableActivity instanceof AutoSave) {
            decoratableActivity.decorate(new AutoSaveActivityDecorator());
        }
    }

    public static void a(DecoratableFragment decoratableFragment) {
        if (decoratableFragment instanceof AutoSave) {
            decoratableFragment.a((FragmentDecorator) new AutoSaveFragmentDecorator());
        }
    }
}
