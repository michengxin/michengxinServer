package org.springboot.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * @ClassName UUIDUtils
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/11 14:43
 * @Version 1.0
 */
public class UUIDUtils implements Serializable {
    public UUIDUtils() {
    }

    public static String generateUUID32Lower() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String generateUUID36Lower() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    public static String generateUUID32Upper() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String generateUUID36Upper() {
        return UUID.randomUUID().toString().toUpperCase();
    }
}