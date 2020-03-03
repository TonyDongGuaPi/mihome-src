package cn.com.fmsh.communication.message.core;

import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.constants.Constants;
import cn.com.fmsh.communication.message.enumerate.ETagType;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MessageHandler implements IMessageHandler {
    private boolean loadFlag = false;
    private FMLog log = LogFactory.getInstance().getLog();
    private String logTag = MessageHandler.class.getName();
    private Map<Integer, MessageDefine> messageDefines = new HashMap();
    private Map<Integer, MessageDefine> messageRetDefines = new HashMap();
    private Map<Byte, TagDefine> tagDefines = new HashMap();

    public int loadDefine(InputStream inputStream) throws FMCommunicationMessageException {
        if (inputStream != null) {
            try {
                Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement();
                NodeList elementsByTagName = documentElement.getElementsByTagName("Tag");
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    TagDefine tagDefine = new TagDefine();
                    Node item = elementsByTagName.item(i);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("id");
                    if (namedItem != null) {
                        tagDefine.setId((byte) Integer.parseInt(namedItem.getNodeValue(), 16));
                    }
                    Node namedItem2 = attributes.getNamedItem("length");
                    if (namedItem2 != null) {
                        tagDefine.setLength(Integer.parseInt(namedItem2.getNodeValue()));
                    }
                    Node namedItem3 = attributes.getNamedItem("type");
                    if (namedItem3 != null) {
                        tagDefine.setType(ETagType.valueOf(namedItem3.getNodeValue()));
                    }
                    Node namedItem4 = attributes.getNamedItem("desc");
                    if (namedItem4 != null) {
                        tagDefine.setDesc(namedItem4.getNodeValue());
                    }
                    NodeList childNodes = item.getChildNodes();
                    for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                        Node item2 = childNodes.item(i2);
                        if (item2 != null) {
                            if (Constants.XMLNode.XMLTag.ITEM.equals(item2.getNodeName())) {
                                NamedNodeMap attributes2 = item2.getAttributes();
                                TagItemDefine tagItemDefine = new TagItemDefine();
                                Node namedItem5 = attributes2.getNamedItem("tag");
                                if (namedItem5 != null) {
                                    tagItemDefine.setTag((byte) Integer.parseInt(namedItem5.getNodeValue(), 16));
                                }
                                Node namedItem6 = attributes2.getNamedItem("desc");
                                if (namedItem6 != null) {
                                    tagItemDefine.setDesc(namedItem6.getNodeValue());
                                }
                                Node namedItem7 = attributes2.getNamedItem("multiple");
                                if (namedItem7 != null) {
                                    tagItemDefine.setMultiple(Integer.parseInt(namedItem7.getNodeValue()));
                                }
                                Node namedItem8 = attributes2.getNamedItem("exist");
                                if (namedItem8 != null) {
                                    tagItemDefine.setExist(Integer.parseInt(namedItem8.getNodeValue()));
                                }
                                Node namedItem9 = attributes2.getNamedItem("order");
                                if (namedItem9 != null) {
                                    tagItemDefine.setOrder(Integer.parseInt(namedItem9.getNodeValue()));
                                }
                                tagDefine.addTagItem(tagItemDefine);
                            }
                        } else if (this.log != null) {
                            this.log.warn(this.logTag, "加载TAG定义文件时，TAG节点为空");
                        }
                    }
                    this.tagDefines.put(Byte.valueOf(tagDefine.getId()), tagDefine);
                }
                NodeList elementsByTagName2 = documentElement.getElementsByTagName(Constants.XMLNode.XMLMessage.MESSAGE);
                for (int i3 = 0; i3 < elementsByTagName2.getLength(); i3++) {
                    Node item3 = elementsByTagName2.item(i3);
                    NamedNodeMap attributes3 = item3.getAttributes();
                    String nodeValue = attributes3.getNamedItem("code").getNodeValue();
                    Node namedItem10 = attributes3.getNamedItem(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE);
                    MessageDefine messageDefine = new MessageDefine();
                    messageDefine.setMessageCode(Integer.parseInt(nodeValue));
                    if (namedItem10 != null) {
                        messageDefine.setRetCode(namedItem10.getNodeValue());
                    }
                    NodeList childNodes2 = item3.getChildNodes();
                    for (int i4 = 0; i4 < childNodes2.getLength(); i4++) {
                        Node item4 = childNodes2.item(i4);
                        if (item4 != null) {
                            if (Constants.XMLNode.XMLMessage.DATA.equals(item4.getNodeName())) {
                                NamedNodeMap attributes4 = item4.getAttributes();
                                MessageTagDefine messageTagDefine = new MessageTagDefine();
                                Node namedItem11 = attributes4.getNamedItem("Tag");
                                if (namedItem11 != null) {
                                    messageTagDefine.setTag((byte) Integer.parseInt(namedItem11.getNodeValue(), 16));
                                }
                                Node namedItem12 = attributes4.getNamedItem("multiple");
                                if (namedItem12 != null) {
                                    messageTagDefine.setMultiple(Integer.parseInt(namedItem12.getNodeValue()));
                                }
                                Node namedItem13 = attributes4.getNamedItem("exist");
                                if (namedItem13 != null) {
                                    messageTagDefine.setExist(Integer.parseInt(namedItem13.getNodeValue()));
                                } else {
                                    messageTagDefine.setExist(1);
                                }
                                Node namedItem14 = attributes4.getNamedItem("order");
                                if (namedItem14 != null) {
                                    messageTagDefine.setOrder(Integer.parseInt(namedItem14.getNodeValue()));
                                }
                                messageDefine.addMessageData(messageTagDefine);
                            }
                        } else if (this.log != null) {
                            this.log.warn(this.logTag, "加载TAG定义文件时，消息节点为空");
                        }
                    }
                    if (messageDefine.getRetCode() == null) {
                        this.messageDefines.put(Integer.valueOf(messageDefine.getMessageCode()), messageDefine);
                    } else {
                        this.messageRetDefines.put(Integer.valueOf(messageDefine.getMessageCode()), messageDefine);
                    }
                }
                this.loadFlag = true;
                if (this.log != null) {
                    this.log.info(this.logTag, "load message config sucess");
                }
                return 0;
            } catch (ParserConfigurationException e) {
                if (this.log != null) {
                    this.log.error(this.logTag, Util4Java.getExceptionInfo(e));
                }
                throw new FMCommunicationMessageException("配置文件加载异常" + Util4Java.getExceptionInfo(e));
            } catch (IOException e2) {
                if (this.log != null) {
                    this.log.error(this.logTag, Util4Java.getExceptionInfo(e2));
                }
                throw new FMCommunicationMessageException("配置文件加载异常" + Util4Java.getExceptionInfo(e2));
            } catch (SAXException e3) {
                if (this.log != null) {
                    this.log.error(this.logTag, Util4Java.getExceptionInfo(e3));
                }
                throw new FMCommunicationMessageException("配置文件加载异常" + Util4Java.getExceptionInfo(e3));
            }
        } else {
            throw new FMCommunicationMessageException("配置文件加载失败");
        }
    }

    public Message createMessage(byte[] bArr) throws FMCommunicationMessageException {
        Message message = new Message(this);
        if (message.fromPackageBody(bArr) == 0) {
            return message;
        }
        return null;
    }

    public Message createMessage(int i, byte[] bArr) throws FMCommunicationMessageException {
        Message message = new Message(this);
        if (message.fromPackageBody(i, bArr) == 0) {
            return message;
        }
        return null;
    }

    public IMessage createMessageAndRetCode(int i, byte[] bArr) throws FMCommunicationMessageException {
        Message message = new Message(this);
        if (message.fromPackageBodyAndRetCode(i, bArr) == 0) {
            return message;
        }
        return null;
    }

    public IMessage createMessageAndRetCode(byte[] bArr) throws FMCommunicationMessageException {
        Message message = new Message(this);
        if (message.fromPackageBodyAndRetCode(bArr) == 0) {
            return message;
        }
        return null;
    }

    public Message createMessage(int i) {
        return new Message(this, i);
    }

    public Tag createTag(byte b, byte[] bArr) throws FMCommunicationMessageException {
        Tag tag = new Tag(this);
        tag.fromPackageBody(b, bArr);
        return tag;
    }

    public Tag createTag(byte[] bArr) throws FMCommunicationMessageException {
        Tag tag = new Tag(this);
        tag.fromPackageBody(bArr);
        return tag;
    }

    public Tag createTag(byte b) {
        return new Tag(this, b);
    }

    public TagDefine getTagDefine(byte b) {
        return this.tagDefines.get(Byte.valueOf(b));
    }

    public MessageDefine getMessageDefine(int i) {
        return this.messageDefines.get(Integer.valueOf(i));
    }

    public MessageDefine getMessageRetDefine(int i) {
        return this.messageRetDefines.get(Integer.valueOf(i));
    }

    public boolean isLoad() {
        return this.loadFlag;
    }
}
