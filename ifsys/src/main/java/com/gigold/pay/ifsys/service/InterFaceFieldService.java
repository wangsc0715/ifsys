package com.gigold.pay.ifsys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.dao.InterFaceFieldDao;

@Service
public class InterFaceFieldService extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	InterFaceFieldDao interFaceFieldDao;

	/**
	 * @return the interFaceFieldDao
	 */
	public InterFaceFieldDao getInterFaceFieldDao() {
		return interFaceFieldDao;
	}

	/**
	 * @param interFaceFieldDao
	 *            the interFaceFieldDao to set
	 */
	public void setInterFaceFieldDao(InterFaceFieldDao interFaceFieldDao) {
		this.interFaceFieldDao = interFaceFieldDao;
	}

	/**
	 * 
	 * Title: getFirstReqFieldByIfId<br/>
	 * Description: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月17日下午3:13:54
	 *
	 * @param interFaceField
	 * @return
	 */
	public List<InterFaceField> getFirstReqFieldByIfId(InterFaceField interFaceField) {
		List<InterFaceField> list = null;
		try {
			list = interFaceFieldDao.getFirstReqFieldByIfId(interFaceField);
		} catch (Exception e) {
			debug("调用 interFaceFieldDao.getFirstReqFieldByIfId 出现异常");
		}
		return list;
	}

	/**
	 * 
	 * Title: getFieldByparentId<br/>
	 * Description: 获取某个字段的所有子字段<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月17日下午4:20:53
	 *
	 * @param interFaceField
	 * @return
	 */

	public List<InterFaceField> getFieldByparentId(InterFaceField interFaceField) {
		List<InterFaceField> list = null;
		try {
			list = interFaceFieldDao.getFieldByparentId(interFaceField);
		} catch (Exception e) {
			debug("调用 interFaceFieldDao.getFieldByparentId 出现异常");
		}
		return list;
	}
    /**
     * 
     * Title: getFieldByIfId<br/>
     * Description: 获取接口所有的字段<br/>
     * @author xiebin
     * @date 2015年12月17日下午4:22:54
     *
     * @param interFaceField
     * @return
     */
	public List<InterFaceField> getFieldByIfId(InterFaceField interFaceField) {
		List<InterFaceField> list = null;
		try {
			list = interFaceFieldDao.getFieldByIfId(interFaceField);
		} catch (Exception e) {
			debug("调用 interFaceFieldDao.getFieldByIfId 出现异常");
		}
		return list;
	}
    /**
     * 
     * Title: addInterFaceField<br/>
     * Description: 新增一个字段<br/>
     * @author xiebin
     * @date 2015年12月17日下午4:24:35
     *
     * @param interFaceField
     * @return
     */
	public boolean addInterFaceField(InterFaceField interFaceField) {
		boolean flag=false;
		List<InterFaceField> list =null;
		try{
			list=interFaceFieldDao.getFieldById(interFaceField);
			if (list != null && list.size() > 0) {
				InterFaceField ifield = list.get(0);
				interFaceField.setLevel(ifield.getLevel() + new Date().getTime() + "-" + ifield.getId() + "-");
			} else {
				interFaceField.setLevel(interFaceField.getParentId() + "-" + new Date().getTime() + "-");
			}
			
			int count=interFaceFieldDao.addInterFaceField(interFaceField);
			if(count>0){
				flag= true;
			}
		}catch(Exception e){
			debug("调用 interFaceFieldDao.getFieldById出现异常");
		}
		return flag;
		
	}
/**
 * 
 * Title: deleteFieldByLevel<br/>
 * Description: <br/>
 * @author xiebin
 * @date 2015年12月17日下午4:35:40
 *
 * @param interFaceField
 * @return
 */
	public boolean deleteFieldByLevel(InterFaceField interFaceField) {
		boolean flag=false;
		try{
			int count=interFaceFieldDao.deleteFieldByLevel(interFaceField);
			if(count>0){
				flag= true;
			}
		}catch(Exception e){
			debug("调用 interFaceFieldDao.deleteFieldByLevel出现异常");
		}
		return flag;
	}

//	public boolean deleteFieldByParentId(InterFaceField interFaceField) {
//		boolean flag=false;
//		try{
//			int count=interFaceFieldDao.deleteFieldByLevel(interFaceField);
//			if(count>0){
//				flag= true;
//			}
//		}catch(Exception e){
//			debug("调用 interFaceFieldDao.deleteFieldByLevel出现异常");
//		}
//		return flag;
//	}
   /**
    * 
    * Title: updateInterFaceField<br/>
    * Description: 修改接口字段<br/>
    * @author xiebin
    * @date 2015年12月17日下午4:47:05
    *
    * @param interFaceField
    * @return
    */
	public boolean updateInterFaceField(InterFaceField interFaceField) {
		boolean flag=false;
		try{
			int count=interFaceFieldDao.updateInterFaceField(interFaceField);
			if(count>0){
				flag= true;
			}
		}catch(Exception e){
			debug("调用 interFaceFieldDao.updateInterFaceField出现异常");
		}
		return flag;
	}

}
