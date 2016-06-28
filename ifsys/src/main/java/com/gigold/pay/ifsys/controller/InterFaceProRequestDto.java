/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.ifsys.bo.InterFacePro;

public class InterFaceProRequestDto extends RequestDto {
    private InterFacePro interFacePro;

    /**
     * @return the interFacePro
     */
    public InterFacePro getInterFacePro() {
        return interFacePro;
    }

    /**
     * @param interFacePro the interFacePro to set
     */
    public void setInterFacePro(InterFacePro interFacePro) {
        this.interFacePro = interFacePro;
    }

}
