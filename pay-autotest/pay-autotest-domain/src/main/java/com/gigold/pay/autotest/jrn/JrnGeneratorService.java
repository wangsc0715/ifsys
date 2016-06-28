package com.gigold.pay.autotest.jrn;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.base.sequence.MySqlSequence;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.util.common.StringUtil;


/**
 * 
 * Title: JrnGeneratorService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月16日下午2:14:48
 *
 */
@Service
public class JrnGeneratorService  extends Domain{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String generateJrn()  throws Exception{
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowdate = sDateFormat.format(calendar.getTime());
		String random = (Math.random() * 900000 + 100000) + "";
		if (random.indexOf(".") > 0) {
			random = random.substring(0, random.indexOf("."));
		}
		String jrn =nowdate+random;//14位时间＋6位随机数 ＝20位流水号
		
		return jrn;
		
	}
	
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return str+","+temp;  
    } 
	

}
