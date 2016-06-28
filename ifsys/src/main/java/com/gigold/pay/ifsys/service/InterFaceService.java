package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.dao.InterFaceDao;

@Service
public class InterFaceService extends Domain {

    /** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
    InterFaceDao interFaceDao;
    

    /**
	 * @return the interFaceDao
	 */
	public InterFaceDao getInterFaceDao() {
		return interFaceDao;
	}

	/**
	 * @param interFaceDao the interFaceDao to set
	 */
	public void setInterFaceDao(InterFaceDao interFaceDao) {
		this.interFaceDao = interFaceDao;
	}

	/**
     * 
     * Title: getInterFaceById<br/>
     * Description:根据ID查询接口信息<br/>
     * 
     * @author xb
     * @date 2015年10月8日上午11:12:48
     *
     * @param id
     * @return
     */
    public InterFaceInfo getInterFaceById(InterFaceInfo interFaceInfo) {
    	InterFaceInfo interFace=null;
    	try{
    		interFace=interFaceDao.getInterFaceById(interFaceInfo);
    	}catch(Exception e){
    		debug("调用 interFaceDao.getInterFaceById 发生异常");
    	}
        return interFace;
    }

    /**
     * 
     * Title: getAllInterFace<br/>
     * 分页获取所有接口信息: <br/>
     * @author xb
     * @date 2015年10月15日下午12:46:05
     *
     * @return
     */
    public List<InterFaceInfo> queryInterFaceByPage(InterFaceInfo interFaceInfo) {
    	List<InterFaceInfo> list=null;
    	try{
    		list=interFaceDao.queryInterFaceByPage(interFaceInfo);
    	}catch(Exception e){
    		debug("调用 interFaceDao.queryInterFaceByPage 发生异常");
    	}
        return list;
    }
    
    
    
    /**
     * 
     * Title: addInterFace<br/>
     * 添加接口: <br/>
     * @author xb
     * @date 2015年10月15日下午12:46:44
     *
     * @param interFaceInfo
     * @return
     */
    public boolean addInterFace(InterFaceInfo interFaceInfo) {
    	boolean flag=false;
    	try{
    		int count=interFaceDao.addInterFace(interFaceInfo);
    		if(count>0){
    			flag=true;
    		}
    	}catch(Exception e){
    		debug("调用 interFaceDao.addInterFace 发生异常");
    	}
        return flag;
    }
   /**
    * 
    * Title: deleteInterFaceById<br/>
    * 删除接口: <br/>
    * @author xb
    * @date 2015年10月15日下午12:47:06
    *
    * @param interFaceInfo
    * @return
    */
    public boolean deleteInterFaceById(InterFaceInfo interFaceInfo) {
    	boolean flag=false;
    	try{
    		int count=interFaceDao.deleteInterFaceById(interFaceInfo);
    		if(count>0){
    			flag=true;
    		}
    	}catch(Exception e){
    		debug("调用 interFaceDao.deleteInterFaceById 发生异常");
    	}
        return flag;
    }
    /**
     * 
     * Title: updateInterFace<br/>
     * 修改接口: <br/>
     * @author xb
     * @date 2015年10月15日下午12:47:26
     *
     * @param interFaceInfo
     * @return
     */
    public boolean updateInterFace(InterFaceInfo interFaceInfo) {
    	boolean flag=false;
    	try{
    		int count=interFaceDao.updateInterFace(interFaceInfo);
    		if(count>0){
    			flag=true;
    		}
    	}catch(Exception e){
    		debug("调用 interFaceDao.updateInterFace 发生异常");
    	}
        return flag;
    }

}
