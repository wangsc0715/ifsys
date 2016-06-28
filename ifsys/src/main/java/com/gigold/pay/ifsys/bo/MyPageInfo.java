/**
 * Title: MyPageInfo.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.bo;

/**
 * Title: MyPageInfo<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xb
 * @date 2015年10月8日上午10:57:33
 *
 */

public class MyPageInfo {
    private int pageNum;
    private int pageSize;

    /**
     * @return the pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum
     *            the pageNum to set
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
