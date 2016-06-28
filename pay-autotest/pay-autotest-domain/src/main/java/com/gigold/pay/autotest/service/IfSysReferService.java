/**
 * Title: RetrunCodeService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.util.List;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.autotest.dao.IfSysReferDAO;
import com.gigold.pay.framework.core.Domain;

/**
 * Title: RetrunCodeService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午9:46:14
 *
 */
@Service
public class IfSysReferService extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	IfSysReferDAO ifSysReferDAO;
    /**
     * 
     * Title: getReferList<br/>
     * Description: 获取测试用例的依赖列表<br/>
     * @author xiebin
     * @date 2015年12月10日上午10:15:30
     *
     * @return
     */
	public List<IfSysRefer> getReferList(int mockId) {
		List<IfSysRefer> list = null;
		try {
			list =ifSysReferDAO.getReferList(mockId);
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferList 数据库发送异常");
		}
		return list;
	}

	/**
	 * 深度求解
	 * @param mockId 用例ID
	 * @return 返回用例依赖列表
     */
	public List<IfSysRefer> getDeeplyReferList(int mockId){
		// 要做递归退出检测
		List<IfSysRefer> refers = null;
		try {
			refers =ifSysReferDAO.getReferList(mockId);
			for(IfSysRefer refer: refers){
				// 查询下级依赖
				List<IfSysRefer> __refers = getDeeplyReferList(refer.getRefMockId());
				// 合并下级依赖
				if(__refers.size()>0){
					__refers.addAll(refers);
					refers = __refers;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			debug("调用 getReferList 数据库发送异常");
		}
		return refers;
	}



	
	public IfSysRefer getReferById(int mockId) {
		IfSysRefer ifSysRefer = null;
		try {
			ifSysRefer =ifSysReferDAO.getReferById(mockId);
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferById 数据库发送异常");
		}
		return ifSysRefer;
	}
	
	
	/**
	 * 
	 * Title: getReferList<br/>
	 * Description:新增测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午5:58:00
	 *
	 * @param ifSysRefer
	 * @return
	 */
	public boolean  addMockRefer(IfSysRefer ifSysRefer) {
		boolean flag=false;
		try {
			IfSysRefer ref =ifSysReferDAO.getReferById(ifSysRefer.getId());
			 int count=-1;
			if(ref==null){
				count=	ifSysReferDAO.addMockRefer(ifSysRefer);
			}else{
				ifSysReferDAO.updateMockRefer(ifSysRefer);
			}
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 addMockRefer 数据库发送异常");
		}
		return flag;
	}
	/**
	 * 
	 * Title: deleteMockRefer<br/>
	 * Description: 删除测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:01:13
	 *
	 * @param id
	 * @return
	 */
	public boolean  deleteMockRefer(int id) {
		boolean flag=false;
		try {
			int count =ifSysReferDAO.deleteMockRefer(id);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferList 数据库发送异常");
		}
		return flag;
	}
	/**
	 * 
	 * Title: updateMockRefer<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:25:43
	 *
	 * @return
	 */
	public boolean  updateMockRefer(IfSysRefer ifSysRefer) {
		boolean flag=false;
		try {
			int count =ifSysReferDAO.updateMockRefer(ifSysRefer);
			if(count>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 updateMockRefer 数据库发送异常");
		}
		return flag;
	}
	
	/**
	 * 
	 * Title: getReferByrefMockId<br/>
	 * Description: 根据被依赖ID获取被依赖用例<br/>
	 * @author xiebin
	 * @date 2016年1月5日上午11:26:33
	 *
	 * @param refMockId
	 * @return
	 */
	public List<IfSysRefer> getReferByRefMockId(int refMockId){
		List<IfSysRefer> list = null;
		try {
			list =ifSysReferDAO.getReferByRefMockId(refMockId);
		} catch (Exception e) {
			e.printStackTrace();
            debug("调用 getReferByrefMockId 数据库发送异常");
		}
		return list;
	}

	/**
	 * 获取mock的字段依赖
	 * @param mockid 当前数据的mockid
	 * @return 返回依赖的数据结果
     */
	public List<IfSysFeildRefer> queryReferFields(int mockid){
		List<IfSysFeildRefer> list = null;
		try {
			list =ifSysReferDAO.queryReferFields(mockid);
		} catch (Exception e) {
			e.printStackTrace();
            debug("获取不到当前接口"+String.valueOf(mockid)+" 的依赖");
		}
		return list;
	}


	/**
	 * 更新mock关系
	 * @param ifSysFeildRefers
	 * @return
     */
	public boolean updataReferFields(List<IfSysFeildRefer> ifSysFeildRefers){
		boolean flag = false;
		try{
			for (IfSysFeildRefer ifSysFeildRefer :ifSysFeildRefers){
				ifSysReferDAO.updataReferFields(ifSysFeildRefer);
			}
			flag = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除mock字段依赖关系
	 * @param id 关系id
	 * @return
     */
	public boolean deleteReferField(int id){
		boolean flag = false;
		try {
			ifSysReferDAO.deleteReferField(id);
			flag = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flag;
	};


}
