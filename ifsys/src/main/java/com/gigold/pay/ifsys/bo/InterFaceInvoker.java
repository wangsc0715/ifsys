/**
 * Title: InterFaceInvoker.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.bo;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gigold.pay.framework.core.Domain;

/**
 * Title: InterFaceInvoker<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月23日下午3:12:09
 *
 */
@Component
@Scope("prototype")
public class InterFaceInvoker extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long id;
	private long ifFollowId;
	private long ifFollowedId;
	private int uId;
	private Date tmFollow;
	private String status;
	private Timestamp tmSmp;
	private String remark;
	
	
	
	private String userName;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the ifFollowId
	 */
	public long getIfFollowId() {
		return ifFollowId;
	}
	/**
	 * @param ifFollowId the ifFollowId to set
	 */
	public void setIfFollowId(long ifFollowId) {
		this.ifFollowId = ifFollowId;
	}
	
	/**
	 * @return the ifFollowedId
	 */
	public long getIfFollowedId() {
		return ifFollowedId;
	}
	/**
	 * @param ifFollowedId the ifFollowedId to set
	 */
	public void setIfFollowedId(long ifFollowedId) {
		this.ifFollowedId = ifFollowedId;
	}
	/**
	 * @return the uId
	 */
	public int getuId() {
		return uId;
	}
	/**
	 * @param uId the uId to set
	 */
	public void setuId(int uId) {
		this.uId = uId;
	}
	/**
	 * @return the tmFollow
	 */
	public Date getTmFollow() {
		return tmFollow;
	}
	/**
	 * @param tmFollow the tmFollow to set
	 */
	public void setTmFollow(Date tmFollow) {
		this.tmFollow = tmFollow;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the tmSmp
	 */
	public Timestamp getTmSmp() {
		return tmSmp;
	}
	/**
	 * @param tmSmp the tmSmp to set
	 */
	public void setTmSmp(Timestamp tmSmp) {
		this.tmSmp = tmSmp;
	}
	
	
}
