package com.mipay.core.internal.registry;

import android.text.TextUtils;
import com.mipay.core.runtime.OSGiBundle;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ExtensionsParser extends DefaultHandler {
    private static final String EXTENSION = "extension";
    private static final String EXTENSION_ID = "id";
    private static final String EXTENSION_NAME = "name";
    private static final String EXTENSION_POINT = "extension-point";
    private static final String EXTENSION_POINT_ID = "id";
    private static final String EXTENSION_POINT_NAME = "name";
    private static final String EXTENSION_POINT_SCHEMA = "schema";
    private static final String EXTENSION_TARGET = "point";
    private static final String PLUGIN = "plugin";
    private static final String PLUGIN_ID = "id";
    private static final String PLUGIN_NAME = "name";
    private static final int STATE_BUNDLE = 2;
    private static final int STATE_BUNDLE_EXTENSION = 6;
    private static final int STATE_BUNDLE_EXTENSION_POINT = 5;
    private static final int STATE_CONFIGURATION_ELEMENT = 10;
    private static final int STATE_IGNORED_ELEMENT = 0;
    private static final int STATE_INITIAL = 1;
    private static SAXParserFactory sParserFactory;
    private final OSGiBundle mBundle;
    private final Stack<ExtensionRegistryElement> mElementStack = new Stack<>();
    private final ExtensionRegistry mRegistry;
    private final Stack<Integer> mStateStack = new Stack<>();

    public ExtensionsParser(ExtensionRegistry extensionRegistry, OSGiBundle oSGiBundle) {
        this.mRegistry = extensionRegistry;
        this.mBundle = oSGiBundle;
        if (sParserFactory == null) {
            sParserFactory = SAXParserFactory.newInstance();
        }
    }

    public void startDocument() {
        this.mStateStack.push(1);
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) {
        switch (this.mStateStack.peek().intValue()) {
            case 1:
                handleInitialState(str2, attributes);
                return;
            case 2:
                handleBundleState(str2, attributes);
                return;
            case 5:
                handleExtensionPointState(str2);
                return;
            case 6:
            case 10:
                handleExtensionState(str2, attributes);
                return;
            default:
                this.mStateStack.push(0);
                return;
        }
    }

    public void endElement(String str, String str2, String str3) {
        int intValue = this.mStateStack.peek().intValue();
        if (intValue != 10) {
            switch (intValue) {
                case 0:
                    this.mStateStack.pop();
                    return;
                case 1:
                    throw new IllegalStateException();
                case 2:
                    if (str2.equals("plugin")) {
                        this.mStateStack.pop();
                        this.mRegistry.addBundle((BundleElement) this.mElementStack.pop());
                        return;
                    }
                    return;
                default:
                    switch (intValue) {
                        case 5:
                            if (str2.equals(EXTENSION_POINT)) {
                                this.mStateStack.pop();
                                this.mElementStack.pop();
                                return;
                            }
                            return;
                        case 6:
                            if (str2.equals("extension")) {
                                this.mStateStack.pop();
                                this.mElementStack.pop();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.mStateStack.pop();
            this.mElementStack.pop();
        }
    }

    public void characters(char[] cArr, int i, int i2) {
        if (this.mStateStack.peek().intValue() == 10) {
            ((ExtensionConfigElement) this.mElementStack.peek()).appendValue(new String(cArr, i, i2));
        }
    }

    private void handleInitialState(String str, Attributes attributes) {
        if (TextUtils.equals(str, "plugin")) {
            this.mStateStack.push(2);
            this.mElementStack.push(new BundleElement(this.mRegistry, this.mBundle));
            return;
        }
        this.mStateStack.push(0);
    }

    private void handleBundleState(String str, Attributes attributes) {
        if (str.equals(EXTENSION_POINT)) {
            this.mStateStack.push(5);
            ExtensionPointElement parseExtensionPoint = parseExtensionPoint(str, attributes);
            if (parseExtensionPoint != null) {
                ExtensionRegistryElement peek = this.mElementStack.peek();
                peek.addChildElement(parseExtensionPoint);
                parseExtensionPoint.setParentElement(peek);
                this.mElementStack.push(parseExtensionPoint);
                return;
            }
            this.mStateStack.pop();
            this.mStateStack.push(0);
        } else if (str.equals("extension")) {
            this.mStateStack.push(6);
            ExtensionElement parseExtension = parseExtension(str, attributes);
            if (parseExtension != null) {
                ExtensionRegistryElement peek2 = this.mElementStack.peek();
                peek2.addChildElement(parseExtension);
                parseExtension.setParentElement(peek2);
                this.mElementStack.push(parseExtension);
                return;
            }
            this.mStateStack.pop();
            this.mStateStack.push(0);
        } else {
            this.mStateStack.push(0);
        }
    }

    private void handleExtensionPointState(String str) {
        this.mStateStack.push(0);
    }

    private void handleExtensionState(String str, Attributes attributes) {
        this.mStateStack.push(10);
        ExtensionConfigElement parseConfigurationElement = parseConfigurationElement(str, attributes);
        ExtensionRegistryElement peek = this.mElementStack.peek();
        peek.addChildElement(parseConfigurationElement);
        parseConfigurationElement.setParentElement(peek);
        this.mElementStack.push(parseConfigurationElement);
    }

    public void parse(InputStream inputStream) {
        try {
            sParserFactory.newSAXParser().parse(new InputSource(inputStream), this);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ParserConfigurationException e3) {
            e3.printStackTrace();
        }
    }

    private ExtensionConfigElement parseConfigurationElement(String str, Attributes attributes) {
        ExtensionConfigElement extensionConfigElement = new ExtensionConfigElement(this.mRegistry);
        extensionConfigElement.setName(str);
        int length = attributes != null ? attributes.getLength() : 0;
        for (int i = 0; i < length; i++) {
            extensionConfigElement.addAttribute(attributes.getLocalName(i), attributes.getValue(i).trim());
        }
        return extensionConfigElement;
    }

    private ExtensionElement parseExtension(String str, Attributes attributes) {
        String str2;
        ExtensionElement extensionElement = new ExtensionElement(this.mRegistry);
        int length = attributes != null ? attributes.getLength() : 0;
        for (int i = 0; i < length; i++) {
            String localName = attributes.getLocalName(i);
            String trim = attributes.getValue(i).trim();
            if (localName.equals("name")) {
                extensionElement.setLabel(trim);
            } else if (localName.equals("id")) {
                int lastIndexOf = trim.lastIndexOf(46);
                if (lastIndexOf != -1) {
                    String substring = trim.substring(lastIndexOf + 1);
                    str2 = trim.substring(0, lastIndexOf);
                    trim = substring;
                } else {
                    str2 = this.mBundle.getSymbolicName();
                }
                extensionElement.setSimpleIdentifier(trim);
                extensionElement.setNamespaceIdentifier(str2);
            } else if (localName.equals(EXTENSION_TARGET)) {
                if (trim.lastIndexOf(46) == -1) {
                    trim = this.mBundle.getSymbolicName() + '.' + trim;
                }
                extensionElement.setExtensionPointUniqueIdentifier(trim);
            }
        }
        if (TextUtils.isEmpty(extensionElement.getExtensionPointUniqueIdentifier())) {
            return null;
        }
        if (TextUtils.isEmpty(extensionElement.getNamespaceIdentifier())) {
            extensionElement.setNamespaceIdentifier(this.mBundle.getSymbolicName());
        }
        return extensionElement;
    }

    private ExtensionPointElement parseExtensionPoint(String str, Attributes attributes) {
        String str2;
        ExtensionPointElement extensionPointElement = new ExtensionPointElement(this.mRegistry);
        int length = attributes != null ? attributes.getLength() : 0;
        for (int i = 0; i < length; i++) {
            String localName = attributes.getLocalName(i);
            String trim = attributes.getValue(i).trim();
            if (localName.equals("name")) {
                extensionPointElement.setLabel(trim);
            } else if (localName.equals("id")) {
                int lastIndexOf = trim.lastIndexOf(46);
                if (lastIndexOf != -1) {
                    String substring = trim.substring(lastIndexOf + 1);
                    str2 = trim.substring(0, lastIndexOf);
                    trim = substring;
                } else {
                    str2 = this.mBundle.getSymbolicName();
                }
                extensionPointElement.setSimpleIdentifier(trim);
                extensionPointElement.setNamespaceIdentifier(str2);
            } else if (localName.equals(EXTENSION_POINT_SCHEMA)) {
                extensionPointElement.setSchemaReference(trim);
            }
        }
        if (TextUtils.isEmpty(extensionPointElement.getSimpleIdentifier()) || TextUtils.isEmpty(extensionPointElement.getLabel())) {
            return null;
        }
        if (TextUtils.isEmpty(extensionPointElement.getNamespaceIdentifier())) {
            extensionPointElement.setNamespaceIdentifier(this.mBundle.getSymbolicName());
        }
        return extensionPointElement;
    }
}
