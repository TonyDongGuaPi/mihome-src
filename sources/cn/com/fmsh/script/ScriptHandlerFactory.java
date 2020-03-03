package cn.com.fmsh.script;

import cn.com.fmsh.script.core.ScriptHandlerImpl;

public class ScriptHandlerFactory {
    private static volatile ScriptHandlerFactory multiCommandFactory;

    private ScriptHandlerFactory() {
    }

    public static ScriptHandlerFactory getInstance() {
        if (multiCommandFactory == null) {
            scriptHandlerFactoryInit();
        }
        return multiCommandFactory;
    }

    public static void scriptHandlerFactoryInit() {
        if (multiCommandFactory == null) {
            multiCommandFactory = new ScriptHandlerFactory();
        }
    }

    public ScriptHandler getScriptHandler(ApduHandler apduHandler) {
        return new ScriptHandlerImpl(apduHandler);
    }
}
