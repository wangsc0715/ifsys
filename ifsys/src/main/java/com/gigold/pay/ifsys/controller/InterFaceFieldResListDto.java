/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceField;

public class InterFaceFieldResListDto extends ResponseDto {
    private List<InterFaceField> list;

    /**
     * @return the list
     */
    public List<InterFaceField> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<InterFaceField> list) {
        this.list = list;
    }

}
