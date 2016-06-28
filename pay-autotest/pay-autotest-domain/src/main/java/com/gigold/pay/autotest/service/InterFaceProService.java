package com.gigold.pay.autotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.InterFacePro;
import com.gigold.pay.autotest.dao.InterFaceProDao;

@Service
public class InterFaceProService {

    @Autowired
    InterFaceProDao interFaceProDao;

    public List<InterFacePro> getAllProInfo() {
        return interFaceProDao.getAllProInfo();
    }

    public InterFacePro getProInfoById(InterFacePro interFacePro) {
        return interFaceProDao.getProInfoById(interFacePro);
    }
    
    public List<InterFacePro> getProInfoBySysId(InterFacePro interFacePro){
        return interFaceProDao.getProInfoBySysId(interFacePro);
    }
    

}
