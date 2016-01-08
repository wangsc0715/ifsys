/**
 * Title: IfSysMockDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import java.util.List;
import java.util.Map;

import com.gigold.pay.autotest.bo.IfSysMock;

/**
 * Title: IfSysMockDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:07:08
 *
 */
public interface IfSysMockDAO {
	/**
	 *
	 * Title: getAllIfSys<br/>
	 * Description:获取接口的关注者 <br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月11日
	 *
	 * @return
	 */
	public List<IfSysMock> getInterfaceFollowShipById(int id);

	/**
	 *
	 * Title: addIfSysMock<br/>
	 * Description: 新增接口测试数据<br/>
	 *
	 * @author xiebin
	 * @date 2015年11月30日上午11:15:29
	 *
	 * @param ifSysMock
	 * @return
	 */
	public int addIfSysMock(IfSysMock ifSysMock);

	/**
	 * 
	 * Title: getMockInfoByIfId<br/>
	 * Description: 根据接口ID获取所有测试信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午2:59:28
	 *
	 * @return
	 */
	public List<IfSysMock> getMockInfoByIfId(int ifId);
	
	/**
	 * 
	 * Title: getMockInfoById<br/>
	 * Description: 根据ID获取测试数据信息<br/>
	 * @author xiebin
	 * @date 2015年12月9日上午11:05:09
	 *
	 * @param ifSysMock
	 * @return
	 */
	public IfSysMock getMockInfoById(IfSysMock ifSysMock); 
	
	/**
	 * 
	 * Title: getMockInfoByIfIdAndRspCdId<br/>
	 * Description: 根据接口ID和返回码ID获取测试数据信息<br/>
	 * @author xiebin
	 * @date 2015年12月9日上午11:05:09
	 *
	 * @param ifSysMock
	 * @return
	 */
	public List<IfSysMock> getMockInfoByIfIdAndRspCdId(IfSysMock ifSysMock);  
	/**
	 * 
	 * Title: getReferByIfId<br/>
	 * Description: 根据接口ID获取依赖接口的测试数据<br/>
	 * @author xiebin
	 * @date 2015年12月10日上午10:25:22
	 *
	 * @param ifId
	 * @return
	 */
	public IfSysMock getReferByIfId(int ifId);  

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 根据筛选条件获取所有测试结果<br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月1日下午2:59:28
	 *
	 * @return
	 */
	public List<IfSysMock> filterMocksByStatus(String flag);

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 获取所有没有通过测试的结果<br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月1日下午2:59:28
	 *
	 * @return
	 */
	public List<IfSysMock> filterMocksByFailed();

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 获取所有已经测试的结果<br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月1日下午2:59:28
	 *
	 * @return
	 */
	public List<IfSysMock> filterAllTestedMocks();
	/**
	 * 
	 * Title: getRefMockInfoByMockId<br/>
	 * Description: 根据mockId获取被依赖用例信息 <br/>
	 * @author xiebin
	 * @date 2016年1月7日下午3:32:10
	 *
	 * @param mockId
	 * @return
	 */
	public List<IfSysMock> getRefMockInfoByMockId(int mockId);
	
	/**
	 * 
	 * Title: updateIfSysMock<br/>
	 * Description: 修改模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:50:33
	 *
	 * @param ifSysMock
	 * @return
	 */
	public int updateIfSysMock(IfSysMock ifSysMock);
	public int initIfSysMock();
	/**
	 * 
	 * Title: deleteIfSysMockById<br/>
	 * Description: 根据ID删除模拟数据 <br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:51:18
	 *
	 * @param id
	 * @return
	 */
	public int deleteIfSysMockById(int id);
	/**
	 * 
	 * Title: deleteIfSysMockByIfId<br/>
	 * Description:  根据IF_ID删除模拟数据<br/>
	 * @author xiebin
	 * @date 2015年12月2日上午10:51:43
	 *
	 * @param id
	 * @return
	 */
	public int deleteIfSysMockByIfId(int id);
	/**
	 * 
	 * Title: writeBack<br/>
	 * Description:  根据IfSysMock对象，回写预期数据信息<br/>
	 * @author chenhl
	 * @date 2015年12月2日上午10:51:43
	 *
	 * @param ifsysmock
	 * @return
	 */
	public int writeBack(IfSysMock ifsysmock);
	
	
	
	public List<IfSysMock> queryMockByPage(IfSysMock ifsysmock);
}
