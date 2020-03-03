package com.miuipub.internal.hybrid;

import java.util.Map;

public interface ConfigParser {
    Config a(Map<String, Object> map) throws HybridException;
}
