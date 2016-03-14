package com.gigold.pay.autotest.datamaker;

/**
 * Created by chenkuan
 * on 16/3/7.
 * 全局变量
 */
public class GlobalVal {
    public static PropertiesPhaser globalVal = new PropertiesPhaser("pay-autotest-script/src/main/resources/server.properties");
    public static String get(String key){
        return globalVal.get(key);
    }
}
