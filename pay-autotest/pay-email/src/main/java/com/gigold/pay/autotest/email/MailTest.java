/**
 * Title: MailTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title: MailTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月25日下午4:11:06
 *
 */
public class MailTest {

	/**
	 * Title: main<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2015年11月25日下午4:11:06
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/emailBeansTest.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
		MailSenderService mailSender = (MailSenderService) context.getBean("mailsenderService");
        List<String> addressTo=new ArrayList<String>();
        //addressTo.add("xiebin163126@163.com");
        addressTo.add("xiebin@gigold.com");
		mailSender.setTo(addressTo);
		mailSender.setSubject("来自独孤九剑接口自动化测试的邮件");  
		mailSender.setTemplateName("mail.vm");//设置的邮件模板
		Map model=new HashMap();  
		model.put("username", "zhansan");  
		model.put("sys", "独孤九剑");  
		model.put("pro", "产品1");  
		model.put("interFace", "登录接口");  
		mailSender.sendWithTemplateForHTML(model);  
		//mailSender.sendHtml();
		System.out.println("邮件发送成功！");  

	}

}
