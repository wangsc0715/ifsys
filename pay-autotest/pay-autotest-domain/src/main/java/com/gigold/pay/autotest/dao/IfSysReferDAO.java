/**
 * Title: IfSysMockDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import java.util.List;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.autotest.bo.IfSysRefer;

/**
 * Title: IfSysMockDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:07:08
 *
 */
public interface IfSysReferDAO {
	/**
	 * 
	 * Title: getReferList<br/>
	 * Description: 获取测试用例的依赖列表 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月9日下午5:25:59
	 *
	 * @param ifId
	 * @return
	 */
	public List<IfSysRefer> getReferList(int mockId);
	
	/**
	 * 
	 * Title: addMockRefer<br/>
	 * Description: 新增测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午5:53:26
	 *
	 * @param ifSysRefer
	 * @return
	 */
	public int addMockRefer(IfSysRefer ifSysRefer);
	/**
	 * 
	 * Title: deleteMockRefer<br/>
	 * Description: 删除测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:00:08
	 *
	 * @param ifSysRefer
	 * @return
	 */
	public int deleteMockRefer(int id);
	
	/**
	 * 
	 * Title: getReferById<br/>
	 * Description: 根据ID查询测试用例依赖<br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:04:33
	 *
	 * @param id
	 * @return
	 */
	public IfSysRefer getReferById(int id);
	/**
	 * 
	 * Title: getReferByrefMockId<br/>
	 * Description: 根据被依赖ID获取被依赖用例<br/>
	 * @author xiebin
	 * @date 2016年1月5日上午11:24:55
	 *
	 * @param refMockId
	 * @return
	 */
	public List<IfSysRefer> getReferByRefMockId(int refMockId);
	/**
	 * 
	 * Title: updateMockRefer<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2015年12月22日下午6:24:59
	 *
	 * @param id
	 * @return
	 */
	public int updateMockRefer(IfSysRefer ifSysRefer);

	/**
	 * 查询所有依赖的字段
	 * @param mockid 主mock
	 * @return 所依赖的所有字段
	 */
	public List<IfSysFeildRefer> queryReferFields(int mockid);

	/**
	 * 根据关系id查询字段依赖关系
	 * @param id 主mock
	 * @return 所依赖的所有字段
	 */
	public IfSysFeildRefer queryReferFieldById(int id);

	/**
	 * 根据mockid删除关联数据
	 * @param id
	 * @return
     */
	public boolean deleteReferField(int id);

	/**
	 * 更新 插入一条依赖
	 * @param ifSysFeildRefer
     */
	public void updataReferFields(IfSysFeildRefer ifSysFeildRefer);
}
