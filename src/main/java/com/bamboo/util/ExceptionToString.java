package com.bamboo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionToStringExample {

    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
