package com.gigold.pay.autotest.controller;


import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.autotest.service.IfSysReferService;
import java.util.List;
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
//    @Test
//    public void updataReferFields(){
////        List<IfSysFeildRefer> a = ifSysReferService.queryReferFields(1);
////        ifSysReferService.updataReferFields(a);
//    }
////    @Test
////    public void queryReferFields(){
////        List<IfSysFeildRefer> a = ifSysReferService.queryReferFields(1);
////        System.out.println(a);
////    }
}
