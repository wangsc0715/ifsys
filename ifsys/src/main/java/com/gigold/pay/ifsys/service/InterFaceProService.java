package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.dao.InterFaceProDao;

@Service
public class InterFaceProService extends Domain {

    /** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
    InterFaceProDao interFaceProDao;

    /**
	 * @return the interFaceProDao
	 */
	public InterFaceProDao getInterFaceProDao() {
		return interFaceProDao;
	}

	/**
	 * @param interFaceProDao the interFaceProDao to set
	 */
	public void setInterFaceProDao(InterFaceProDao interFaceProDao) {
		this.interFaceProDao = interFaceProDao;
	}

    /**
     * 
     * Title: getProInfoById<br/>
     * Description: 更具ID获取产品信息<br/>
     * @author xiebin
     * @date 2015年12月17日下午2:41:42
     *
     * @param interFacePro
     * @return
     */
    public InterFacePro getProInfoById(InterFacePro interFacePro) {
    	InterFacePro pro=null;
    	try{
    		pro=interFaceProDao.getProInfoById(interFacePro);
    	}catch(Exception e){
    		debug("调用 interFaceProDao.getProInfoById 发生异常");
    	}
        return pro;
    }
    /**
     * 
     * Title: getProInfoBySysId<br/>
     * Description: 获取某个系统下所有产品信息<br/>
     * @author xiebin
     * @date 2015年12月17日下午2:43:56
     *
     * @param interFacePro
     * @return
     */
    public List<InterFacePro> getProInfoBySysId(InterFacePro interFacePro){
    	List<InterFacePro> list=null;
    	try{
    	  list=interFaceProDao.getProInfoBySysId(interFacePro);
    	}catch(Exception e){
    		debug("调用 interFaceProDao.getProInfoBySysId 发生异常");
    	}
        return list;
    }
    

}
