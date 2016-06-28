/**
 * Title: InterFacePro.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.bo;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gigold.pay.framework.core.Domain;

/**
 * Title: InterFacePro<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xb
 * @date 2015年10月12日上午9:38:39
 *
 */

@Component
@Scope("prototype")
public class InterFacePro extends Domain implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int id;
    private int sysId;
    private String proName;
    private String proDesc;
    private String isValid;
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the sysId
     */
    public int getSysId() {
        return sysId;
    }

    /**
     * @param sysId
     *            the sysId to set
     */
    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    /**
     * @return the proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName
     *            the proName to set
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * @return the proDesc
     */
    public String getProDesc() {
        return proDesc;
    }

    /**
     * @param proDesc
     *            the proDesc to set
     */
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
}
