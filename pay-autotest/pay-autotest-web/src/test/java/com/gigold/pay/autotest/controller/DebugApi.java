package com.gigold.pay.autotest.controller;


import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.autotest.bo.IfSysSQLCallBack;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.datamaker.ConstField;
import com.gigold.pay.autotest.service.IfSysReferService;
import java.util.List;

import com.gigold.pay.autotest.service.IfSysSQLCallBackService;
import com.gigold.pay.autotest.service.InterFaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/*Beans.xml"})
public class DebugApi  {
    @Autowired
    private IfSysReferService ifSysReferService;
    @Autowired
    private InterFaceService interFaceService;
    @Autowired
    private IfSysSQLCallBackService ifSysSQLCallBackService;

    @Test
    public void updataReferFields(){
        try {
            IfSysSQLCallBack ifSysSQLCallBack = new IfSysSQLCallBack();
            ifSysSQLCallBack.setDesc("kllalalala");
            ifSysSQLCallBack.setSql("dsafdsafdsaf");
            ifSysSQLCallBack.setMockid(727);
            ifSysSQLCallBackService.updateIfSysSQLCallBack(ifSysSQLCallBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void queryReferFields(){
//        List<IfSysFeildRefer> a = ifSysReferService.queryReferFields(1);
//        System.out.println(a);
//    }
}
