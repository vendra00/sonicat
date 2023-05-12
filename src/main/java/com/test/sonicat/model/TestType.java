package com.test.sonicat.model;

public enum TestType {
    CRYSTALS("CRY"),
    YEASTS("YEA");

    private final String prefix;

    TestType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
