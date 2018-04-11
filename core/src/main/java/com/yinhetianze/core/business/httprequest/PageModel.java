package com.yinhetianze.core.business.httprequest;

import com.yinhetianze.core.utils.CommonUtil;

/**
 * 支持分页请求的入参model
 */
public class PageModel extends BasicModel
{
    /**
     * 
     */
    private static final long serialVersionUID = -6948537617722821735L;

    protected Integer pageSize;

    protected Long totalCount;

    protected Integer currentPage;


    /**
     * 获取数字类型的分页大小
     * @return
     */
    public Integer getPageSize()
    {
        return CommonUtil.isEmpty(pageSize) ? 0 : pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Long getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(Long totalCount)
    {
        this.totalCount = totalCount;
    }

    /**
     * 获取数字类型的当前页数
     * @return
     */
    public Integer getCurrentPage()
    {
        return CommonUtil.isEmpty(currentPage) ? 0 : currentPage;
    }

    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage;
    }

    /**
     * 得到字符串形式的分页大小，用于校验
     * @return
     */
    public Integer getPaginSize()
    {
        return this.pageSize;
    }

    /**
     * 得到字符串形式的当前页数，用于校验
     * @return
     */
    public Integer getCurrentPagin()
    {
        return this.currentPage;
    }
}
