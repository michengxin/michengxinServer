package org.springboot.entity;

public enum CherryConstants {
    a("a"),
    b("b");
    String name;
    CherryConstants(String name) {
        this.name = name;
    }

    public String getUserType() {
        return name;
    }
}
