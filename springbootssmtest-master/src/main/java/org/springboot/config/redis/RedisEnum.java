package org.springboot.config.redis;

public enum RedisEnum {

    USERTOKEN("USERTOKEN"), //用户token
    NUMBER("NUMBER");


    String type;
    RedisEnum(String Type) {
        this.type = Type;
    }

    public String getType() {
        return type;
    }
}
