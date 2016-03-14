package com.gigold.pay.autotest.datamaker;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.sun.javafx.runtime.SystemProperties;

/**
 * Created by chenkuan
 * on 16/3/7.
 * 全局变量
 */
public class GlobalVal {
    public static String get(String key){
        return SystemPropertyConfigure.getProperty(key);
    }
}
