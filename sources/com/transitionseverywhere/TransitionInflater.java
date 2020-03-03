package com.transitionseverywhere;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.transitionseverywhere.extra.Scale;
import com.transitionseverywhere.extra.TranslationTransition;
import com.xiaomi.smarthome.constants.AppConstants;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?>[] f9474a = {Context.class, AttributeSet.class};
    private static final ArrayMap<String, Constructor> b = new ArrayMap<>();
    private Context c;

    private TransitionInflater(Context context) {
        this.c = context;
    }

    public static TransitionInflater a(Context context) {
        return new TransitionInflater(context);
    }

    public Transition a(int i) {
        XmlResourceParser xml = this.c.getResources().getXml(i);
        try {
            Transition a2 = a((XmlPullParser) xml, Xml.asAttributeSet(xml), (Transition) null);
            xml.close();
            return a2;
        } catch (XmlPullParserException e) {
            InflateException inflateException = new InflateException(e.getMessage());
            inflateException.initCause(e);
            throw inflateException;
        } catch (IOException e2) {
            InflateException inflateException2 = new InflateException(xml.getPositionDescription() + ": " + e2.getMessage());
            inflateException2.initCause(e2);
            throw inflateException2;
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    public TransitionManager a(int i, ViewGroup viewGroup) {
        XmlResourceParser xml = this.c.getResources().getXml(i);
        try {
            TransitionManager a2 = a((XmlPullParser) xml, Xml.asAttributeSet(xml), viewGroup);
            xml.close();
            return a2;
        } catch (XmlPullParserException e) {
            InflateException inflateException = new InflateException(e.getMessage());
            inflateException.initCause(e);
            throw inflateException;
        } catch (IOException e2) {
            InflateException inflateException2 = new InflateException(xml.getPositionDescription() + ": " + e2.getMessage());
            inflateException2.initCause(e2);
            throw inflateException2;
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    private Transition a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        Transition transition2;
        int depth = xmlPullParser.getDepth();
        TransitionSet transitionSet = transition instanceof TransitionSet ? (TransitionSet) transition : null;
        loop0:
        while (true) {
            transition2 = null;
            while (true) {
                int next = xmlPullParser.next();
                if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                    if (next == 2) {
                        String name = xmlPullParser.getName();
                        if ("fade".equals(name)) {
                            transition2 = new Fade(this.c, attributeSet);
                        } else if ("changeBounds".equals(name)) {
                            transition2 = new ChangeBounds(this.c, attributeSet);
                        } else if ("slide".equals(name)) {
                            transition2 = new Slide(this.c, attributeSet);
                        } else if ("explode".equals(name)) {
                            transition2 = new Explode(this.c, attributeSet);
                        } else if ("changeImageTransform".equals(name)) {
                            transition2 = new ChangeImageTransform(this.c, attributeSet);
                        } else if ("changeTransform".equals(name)) {
                            transition2 = new ChangeTransform(this.c, attributeSet);
                        } else if ("changeClipBounds".equals(name)) {
                            transition2 = new ChangeClipBounds(this.c, attributeSet);
                        } else if ("autoTransition".equals(name)) {
                            transition2 = new AutoTransition(this.c, attributeSet);
                        } else if ("recolor".equals(name)) {
                            transition2 = new Recolor(this.c, attributeSet);
                        } else if ("changeScroll".equals(name)) {
                            transition2 = new ChangeScroll(this.c, attributeSet);
                        } else if ("transitionSet".equals(name)) {
                            transition2 = new TransitionSet(this.c, attributeSet);
                        } else if ("scale".equals(name)) {
                            transition2 = new Scale(this.c, attributeSet);
                        } else if ("translation".equals(name)) {
                            transition2 = new TranslationTransition(this.c, attributeSet);
                        } else if ("transition".equals(name)) {
                            transition2 = (Transition) a(attributeSet, Transition.class, "transition");
                        } else if ("targets".equals(name)) {
                            b(xmlPullParser, attributeSet, transition);
                        } else if ("arcMotion".equals(name)) {
                            transition.a((PathMotion) new ArcMotion(this.c, attributeSet));
                        } else if ("pathMotion".equals(name)) {
                            transition.a((PathMotion) a(attributeSet, PathMotion.class, "pathMotion"));
                        } else if ("patternPathMotion".equals(name)) {
                            transition.a((PathMotion) new PatternPathMotion(this.c, attributeSet));
                        } else {
                            throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                        }
                        if (transition2 == null) {
                            continue;
                        } else {
                            if (!xmlPullParser.isEmptyElementTag()) {
                                a(xmlPullParser, attributeSet, transition2);
                            }
                            if (transitionSet != null) {
                                break;
                            } else if (transition != null) {
                                throw new InflateException("Could not add transition to another transition.");
                            }
                        }
                    }
                }
            }
            transitionSet.b(transition2);
        }
        return transition2;
    }

    private Object a(AttributeSet attributeSet, Class cls, String str) {
        Object newInstance;
        Class<? extends U> asSubclass;
        String attributeValue = attributeSet.getAttributeValue((String) null, AppConstants.x);
        if (attributeValue != null) {
            try {
                synchronized (b) {
                    Constructor<? extends U> constructor = b.get(attributeValue);
                    if (constructor == null && (asSubclass = this.c.getClassLoader().loadClass(attributeValue).asSubclass(cls)) != null) {
                        constructor = asSubclass.getConstructor(f9474a);
                        if (!constructor.isAccessible()) {
                            constructor.setAccessible(true);
                        }
                        b.put(attributeValue, constructor);
                    }
                    newInstance = constructor.newInstance(new Object[]{this.c, attributeSet});
                }
                return newInstance;
            } catch (InstantiationException e) {
                throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e);
            } catch (ClassNotFoundException e2) {
                throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e2);
            } catch (InvocationTargetException e3) {
                throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e3);
            } catch (NoSuchMethodException e4) {
                throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e4);
            } catch (IllegalAccessException e5) {
                throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e5);
            }
        } else {
            throw new InflateException(str + " tag must have a 'class' attribute");
        }
    }

    private void b(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                if (xmlPullParser.getName().equals(TouchesHelper.TARGET_KEY)) {
                    TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(attributeSet, R.styleable.TransitionTarget);
                    int resourceId = obtainStyledAttributes.getResourceId(R.styleable.TransitionTarget_targetId, 0);
                    if (resourceId != 0) {
                        transition.c(resourceId);
                    } else {
                        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.TransitionTarget_excludeId, 0);
                        if (resourceId2 != 0) {
                            transition.a(resourceId2, true);
                        } else {
                            String string = obtainStyledAttributes.getString(R.styleable.TransitionTarget_targetName);
                            if (string != null) {
                                transition.a(string);
                            } else {
                                String string2 = obtainStyledAttributes.getString(R.styleable.TransitionTarget_excludeName);
                                if (string2 != null) {
                                    transition.a(string2, true);
                                } else {
                                    String string3 = obtainStyledAttributes.getString(R.styleable.TransitionTarget_excludeClass);
                                    if (string3 != null) {
                                        try {
                                            transition.a((Class) Class.forName(string3), true);
                                        } catch (ClassNotFoundException e) {
                                            e = e;
                                            throw new RuntimeException("Could not create " + string3, e);
                                        }
                                    } else {
                                        String string4 = obtainStyledAttributes.getString(R.styleable.TransitionTarget_targetClass);
                                        if (string4 != null) {
                                            try {
                                                transition.a((Class) Class.forName(string4));
                                            } catch (ClassNotFoundException e2) {
                                                e = e2;
                                                string3 = string4;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    obtainStyledAttributes.recycle();
                } else {
                    throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.transitionseverywhere.TransitionManager a(org.xmlpull.v1.XmlPullParser r5, android.util.AttributeSet r6, android.view.ViewGroup r7) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r4 = this;
            int r0 = r5.getDepth()
            r1 = 0
        L_0x0005:
            int r2 = r5.next()
            r3 = 3
            if (r2 != r3) goto L_0x0012
            int r3 = r5.getDepth()
            if (r3 <= r0) goto L_0x0054
        L_0x0012:
            r3 = 1
            if (r2 == r3) goto L_0x0054
            r3 = 2
            if (r2 == r3) goto L_0x0019
            goto L_0x0005
        L_0x0019:
            java.lang.String r2 = r5.getName()
            java.lang.String r3 = "transitionManager"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x002b
            com.transitionseverywhere.TransitionManager r1 = new com.transitionseverywhere.TransitionManager
            r1.<init>()
            goto L_0x0005
        L_0x002b:
            java.lang.String r3 = "transition"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0039
            if (r1 == 0) goto L_0x0039
            r4.a((android.util.AttributeSet) r6, (android.view.ViewGroup) r7, (com.transitionseverywhere.TransitionManager) r1)
            goto L_0x0005
        L_0x0039:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "Unknown scene name: "
            r7.append(r0)
            java.lang.String r5 = r5.getName()
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r6.<init>(r5)
            throw r6
        L_0x0054:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.TransitionInflater.a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):com.transitionseverywhere.TransitionManager");
    }

    private void a(AttributeSet attributeSet, ViewGroup viewGroup, TransitionManager transitionManager) throws Resources.NotFoundException {
        Scene scene;
        Transition a2;
        TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(attributeSet, R.styleable.TransitionManager);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.TransitionManager_transition, -1);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.TransitionManager_fromScene, -1);
        Scene scene2 = null;
        if (resourceId2 < 0) {
            scene = null;
        } else {
            scene = Scene.a(viewGroup, resourceId2, this.c);
        }
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.TransitionManager_toScene, -1);
        if (resourceId3 >= 0) {
            scene2 = Scene.a(viewGroup, resourceId3, this.c);
        }
        if (resourceId >= 0 && (a2 = a(resourceId)) != null) {
            if (scene2 == null) {
                throw new RuntimeException("No toScene for transition ID " + resourceId);
            } else if (scene == null) {
                transitionManager.a(scene2, a2);
            } else {
                transitionManager.a(scene, scene2, a2);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
