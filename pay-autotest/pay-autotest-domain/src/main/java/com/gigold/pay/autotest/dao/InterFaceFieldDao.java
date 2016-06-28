package com.gigold.pay.autotest.dao;

import java.util.List;

import com.gigold.pay.autotest.bo.InterFaceField;

public interface InterFaceFieldDao {
    /**
     * 
     * Title: getFirstFieldByIfId<br/>
     * 获取某个接口请求部分的一级请求字段
     * 
     * @author xb
     * @date 2015年10月10日下午3:13:44
     *
     * @param interFaceField
     * @return
     */
    public List<InterFaceField>  getFirstReqFieldByIfId(InterFaceField interFaceField);

    /**
     * 
     * Title: getFirstResFieldByIfId<br/>
     * 获取某个接口响应部分的一级响应字段
     * 
     * @author xb
     * @date 2015年10月10日下午3:19:52
     *
     * @param interFaceField
     * @return
     */
    public List<InterFaceField>  getFirstResFieldByIfId(InterFaceField interFaceField);

    /**
     * 
     * Title: getFieldByparentId<br/>
     * 根据parentId获取字段
     * 
     * @author xb
     * @date 2015年10月10日下午3:25:53
     *
     * @param interFaceField
     * @return
     */
    public List<InterFaceField> getFieldByparentId(InterFaceField interFaceField);
    
    /**
     * 
     * Title: getFieldByIfId<br/>
     * 获取接口所有字段: <br/>
     * @author xb
     * @date 2015年10月13日下午3:21:18
     *
     * @param interFaceField
     * @return
     */
    public List<InterFaceField> getFieldByIfId(InterFaceField interFaceField);
    /**
     * 
     * Title: getFieldById<br/>
     * Description: <br/>
     * @author xb
     * @date 2015年10月13日下午5:29:30
     *
     * @param interFaceField
     * @return
     */
    public List<InterFaceField> getFieldById(InterFaceField interFaceField);
    
    
    
    /**
     * 
     * Title: addInterFaceField<br/>
     * 添加字段: <br/>
     * @author xb
     * @date 2015年10月12日上午11:27:46
     *
     * @param interFaceField
     * @return
     */
    public boolean addInterFaceField(InterFaceField interFaceField);
    
    /**
     * 
     * Title: deleteFieldByLevel<br/>
     * 通过层级码删除字段: <br/>
     * @author xb
     * @date 2015年10月13日下午5:00:42
     *
     * @param interFaceField
     * @return
     */
    public boolean deleteFieldByLevel(InterFaceField interFaceField);
   /**
    * 
    * Title: updateInterFaceField<br/>
    * 修改: <br/>
    * @author xb
    * @date 2015年10月14日上午11:33:49
    *
    * @param interFaceField
    * @return
    */
    public boolean updateInterFaceField(InterFaceField interFaceField);
   

}
