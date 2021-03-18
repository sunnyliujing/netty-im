package com.xlef.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {

    private static final AtomicLong idx = new AtomicLong();

    private IdUtil(){

    }

    public static long nextId(){
        return idx.incrementAndGet();
    }
}
