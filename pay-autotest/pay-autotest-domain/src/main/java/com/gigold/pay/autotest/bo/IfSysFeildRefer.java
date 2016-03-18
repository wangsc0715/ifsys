/**
 * Title: IfSysMock.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Title: IfSysMock<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月26日下午4:04:37
 *
 */
@Component
@Scope("prototype")
public class IfSysFeildRefer {
	
	private int id,mockid,ref_mock_id;
	private String ref_feild,alias,status;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMockid() {
		return mockid;
	}

	public void setMockid(int mockid) {
		this.mockid = mockid;
	}

	public int getRef_mock_id() {
		return ref_mock_id;
	}

	public void setRef_mock_id(int ref_mock_id) {
		this.ref_mock_id = ref_mock_id;
	}

	public String getRef_feild() {
		return ref_feild;
	}

	public void setRef_feild(String ref_feild) {
		this.ref_feild = ref_feild;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
