/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceField;

public class InterFaceFieldResAddDto extends ResponseDto {
   private InterFaceField interFaceField;

/**
 * @return the interFaceField
 */
public InterFaceField getInterFaceField() {
    return interFaceField;
}

/**
 * @param interFaceField the interFaceField to set
 */
public void setInterFaceField(InterFaceField interFaceField) {
    this.interFaceField = interFaceField;
}

}
