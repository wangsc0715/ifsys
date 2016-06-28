package com.gigold.pay.autotest.email;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.Domain;

/**
 * Title: MailSenderService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月25日下午3:56:25
 *
 */
@Service
public class MailSenderService extends Domain{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	// spring配置中定义
	private JavaMailSender mailSender;
	// spring配置中定义
	private VelocityEngine velocityEngine;
	// spring配置中定义
	private SimpleMailMessage simpleMailMessage;
	
	private String from;
	private List<String> to;
	//private String [] cc;
	private String subject;
	private String content;
	private String templateName;
	// 是否需要身份验证
	private boolean validate = false;

	

	/**
	 * @return the cc
	 */
//	public String[] getCc() {
//		return cc;
//	}

	/**
	 * @param cc the cc to set
	 */
//	public void setCc(String[] cc) {
//		this.cc = cc;
//	}

	/**
	 * 
	 * Title: sendWithTemplate<br/>
	 * Description: 发送模板邮件<br/>
	 * @author xiebin
	 * @date 2015年11月25日下午4:56:07
	 *
	 * @param model
	 */
	public void sendWithTemplate(Map<String,Object> model) {
		debug("调用模版 发送邮件");
		mailSender = this.getMailSender();
		simpleMailMessage.setTo(getAddressTo()); // 接收人
		//simpleMailMessage.setCc(getCc()); // 设置抄送人
		simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
		simpleMailMessage.setSubject(this.getSubject());
		String result = null;
		try {
			result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), this.getTemplateName(),
					"UTF-8", model);
		} catch (Exception e) {
			debug("调用模版失败");
		}
		simpleMailMessage.setText(result);
		mailSender.send(simpleMailMessage);
	}

	/**
	 * 
	 * Title: sendText<br/>
	 * Description: 发送普通文本邮件<br/>
	 * @author xiebin
	 * @date 2015年11月25日下午4:56:30
	 *
	 */
	public void sendText() {
		mailSender = this.getMailSender();
		simpleMailMessage.setTo(getAddressTo()); // 接收人
		//simpleMailMessage.setCc(getCc()); //设置抄送人
		simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
		simpleMailMessage.setSubject(this.getSubject());
		simpleMailMessage.setText(this.getContent());
		mailSender.send(simpleMailMessage);
	}

	/**
	 * 
	 * Title: sendHtml<br/>
	 * Description: 发送普通Html邮件<br/>
	 * @author xiebin
	 * @date 2015年11月25日下午4:56:39
	 *
	 */
	public void sendHtml() {
		mailSender = this.getMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		try {
			messageHelper.setTo(getAddressTo());
			//messageHelper.setCc(getCc());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(this.getSubject());
			messageHelper.setText(this.getContent(), true);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}
    /**
     * 
     * Title: sendWithTemplateForHTML<br/>
     * Description: 利用模版发送HTML邮件<br/>
     * @author xiebin
     * @date 2015年12月5日下午3:56:23
     *
     * @param model
     */
	public void sendWithTemplateForHTML(Map<String,Object> model){
		mailSender = this.getMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		try {
			messageHelper.setTo(getAddressTo());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(this.getSubject());
			String result = null;
			try {
				result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), this.getTemplateName(),
						"UTF-8", model);
			} catch (Exception e) {
				e.printStackTrace();
				debug("调用模版失败");
			}
			messageHelper.setText(result, true);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}
	
	
	
	
	/**
	 * 
	 * Title: sendHtmlWithImage<br/>
	 * Description: 发送普通带一张图片的Html邮件<br/>
	 * @author xiebin
	 * @date 2015年11月25日下午4:56:50
	 *
	 * @param imagePath
	 */
	public void sendHtmlWithImage(String imagePath) {
		mailSender = this.getMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(getAddressTo());
			//messageHelper.setCc(getCc());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(this.getSubject());
			messageHelper.setText(this.getContent(), true);
			// Content="<html><head></head><body><img
			// src=\"cid:image\"/></body></html>";
			// 图片必须这样子：<img src='cid:image'/>
			FileSystemResource img = new FileSystemResource(new File(imagePath));
			messageHelper.addInline("image", img);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}

	/**
	 * 发送普通带附件的Html邮件
	 * 
	 */
	public void sendHtmlWithAttachment(String filePath) {
		mailSender = this.getMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(getAddressTo());
			//messageHelper.setCc(getCc());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(this.getSubject());
			messageHelper.setText(this.getContent(), true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			// System.out.println("file.getFilename==="+file.getFilename());
			messageHelper.addAttachment(file.getFilename(), file);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}

	/**
	 * 发送普通带附件的Html邮件-参数格式为file类
	 *
	 */
	public void sendHtmlWithAttachment(File file) {
		mailSender = this.getMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(getAddressTo());
			//messageHelper.setCc(getCc());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(this.getSubject());
			messageHelper.setText(this.getContent(), true);
			FileSystemResource fs = new FileSystemResource(file);
			// System.out.println("file.getFilename==="+file.getFilename());
			messageHelper.addAttachment(fs.getFilename(), fs);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}

	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * @param velocityEngine the velocityEngine to set
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * @return the simpleMailMessage
	 */
	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

	/**
	 * @param simpleMailMessage the simpleMailMessage to set
	 */
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	
	public String [] getAddressTo(){
	   int size =  this.getTo().size();
		String[] arr = (String[])this.getTo().toArray(new String[size]);
		return arr;
	}
	
	/**
	 * @return the to
	 */
	public List<String> getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(List<String> to) {
		this.to = to;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * @param validate the validate to set
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	
}
