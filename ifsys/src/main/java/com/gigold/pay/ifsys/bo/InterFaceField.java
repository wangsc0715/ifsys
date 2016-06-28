/**
 * Title: InterFaceField.java<br/>
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
 * Title: InterFaceField<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xb
 * @date 2015年10月10日下午2:20:17
 *
 */

@Component
@Scope("prototype")
public class InterFaceField extends Domain implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int id;
    private int parentId;
    private int ifId;
    private String fieldName;
    private String fieldDesc;
    private String fieldType;
    private String fieldCheck;
    private String fieldFlag;
    private String fieldReferValue;
    private String fieldStatusCode;
    private String level;
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
     * @return the level
     */
    public String getLevel() {
        return level;
    }
    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }
    /**
     * @return the fieldStatusCode
     */
    public String getFieldStatusCode() {
        return fieldStatusCode;
    }
    /**
     * @param fieldStatusCode the fieldStatusCode to set
     */
    public void setFieldStatusCode(String fieldStatusCode) {
        this.fieldStatusCode = fieldStatusCode;
    }
    /**
     * @return the fieldReferValue
     */
    public String getFieldReferValue() {
        return fieldReferValue;
    }
    /**
     * @param fieldReferValue the fieldReferValue to set
     */
    public void setFieldReferValue(String fieldReferValue) {
        this.fieldReferValue = fieldReferValue;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }
    /**
     * @param parentId the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    /**
     * @return the ifId
     */
    public int getIfId() {
        return ifId;
    }
    /**
     * @param ifId the ifId to set
     */
    public void setIfId(int ifId) {
        this.ifId = ifId;
    }
    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }
    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    /**
     * @return the fieldDesc
     */
    public String getFieldDesc() {
        return fieldDesc;
    }
    /**
     * @param fieldDesc the fieldDesc to set
     */
    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }
    /**
     * @return the fieldType
     */
    public String getFieldType() {
        return fieldType;
    }
    /**
     * @param fieldType the fieldType to set
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    /**
     * @return the fieldCheck
     */
    public String getFieldCheck() {
        return fieldCheck;
    }
    /**
     * @param fieldCheck the fieldCheck to set
     */
    public void setFieldCheck(String fieldCheck) {
        this.fieldCheck = fieldCheck;
    }
    /**
     * @return the fieldFlag
     */
    public String getFieldFlag() {
        return fieldFlag;
    }
    /**
     * @param fieldFlag the fieldFlag to set
     */
    public void setFieldFlag(String fieldFlag) {
        this.fieldFlag = fieldFlag;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
    
    
}
