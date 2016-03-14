package com.gigold.pay.autotest.datamaker;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by chenkuan
 * on 16/3/7.
 */
public class PropertiesPhaser {
    private Properties p = new Properties();

    public PropertiesPhaser(String dir){
        // 生成文件对象
        File pf = new File(getClass().getResource("/").getFile()
                + "/"+dir);

        // 生成文件输入流
        FileInputStream inpf = null;
        try {
            inpf = new FileInputStream(pf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 生成properties对象

        try {
            p.load(inpf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };



    public String get(String Key){
        return this.p.getProperty(Key);
    };
}
