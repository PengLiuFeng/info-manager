package com.rainng.coursesystem.util;

import java.util.UUID;

public class UuidUtil {

    public static String createUUID(){
        return UUID.randomUUID().toString().toUpperCase();
    }

}
