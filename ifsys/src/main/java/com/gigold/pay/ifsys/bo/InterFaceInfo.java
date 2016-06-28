package com.gigold.pay.ifsys.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gigold.pay.framework.core.Domain;

@Component
@Scope("prototype")
public class InterFaceInfo extends Domain implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private String ifName;
    private String ifDesc;
    private int uid;
    private Timestamp ifCreateTime;
    private int ifSysId;
    private int ifCreateBy;
    private int ifProId;
    private String ifStatus;
    private String ifType;
    private String ifProtocol;
    private String isValid;
    private String method;
    private String methodVersion;
    private String sysName;
    private String proName;
    private String designName;
    
    
    
    

    /**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the methodVersion
	 */
	public String getMethodVersion() {
		return methodVersion;
	}

	/**
	 * @param methodVersion the methodVersion to set
	 */
	public void setMethodVersion(String methodVersion) {
		this.methodVersion = methodVersion;
	}

	/**
     * @return the proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName the proName to set
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * @return the designName
     */
    public String getDesignName() {
        return designName;
    }

    /**
     * @param designName the designName to set
     */
    public void setDesignName(String designName) {
        this.designName = designName;
    }

    /**
     * @return the sysName
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * @param sysName the sysName to set
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    /**
     * @return the isValid
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * @param isValid the isValid to set
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    /**
     * @return the ifType
     */
    public String getIfType() {
        return ifType;
    }

    /**
     * @param ifType the ifType to set
     */
    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    /**
     * @return the ifProtocol
     */
    public String getIfProtocol() {
        return ifProtocol;
    }

    /**
     * @param ifProtocol the ifProtocol to set
     */
    public void setIfProtocol(String ifProtocol) {
        this.ifProtocol = ifProtocol;
    }

    public int getIfCreateBy() {
        return ifCreateBy;
    }

    public void setIfCreateBy(int ifCreateBy) {
        this.ifCreateBy = ifCreateBy;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public String getIfDesc() {
        return ifDesc;
    }

    public void setIfDesc(String ifDesc) {
        this.ifDesc = ifDesc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Timestamp getIfCreateTime() {
        return ifCreateTime;
    }

    public void setIfCreateTime(Timestamp ifCreateTime) {
        this.ifCreateTime = ifCreateTime;
    }

    public int getIfSysId() {
        return ifSysId;
    }

    public void setIfSysId(int ifSysId) {
        this.ifSysId = ifSysId;
    }

    public int getIfProId() {
        return ifProId;
    }

    public void setIfProId(int ifProId) {
        this.ifProId = ifProId;
    }


    public String getIfStatus() {
        return ifStatus;
    }

    public void setIfStatus(String ifStatus) {
        this.ifStatus = ifStatus;
    }

    public String getIfUrl() {
        return ifUrl;
    }

    public void setIfUrl(String ifUrl) {
        this.ifUrl = ifUrl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private String ifUrl;

}
