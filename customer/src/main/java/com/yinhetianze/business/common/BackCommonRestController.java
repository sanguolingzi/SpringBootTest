package com.yinhetianze.business.common;

import com.yinhetianze.core.business.BusinessExecutor;
import com.yinhetianze.core.business.httprequest.SpringBootFileModel;
import com.yinhetianze.core.utils.ApplicationContextFactory;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.springmvc.controller.RestfulController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * on 2018/04/19.
 */
@RestController
@RequestMapping("back")
public class BackCommonRestController extends RestfulController
{
    /**
     * 文件上传
     * @param request
     * @param response
     * @return
     */
    @PostMapping (value = "/fileUpload")
    public Object fileUploadExecutor(HttpServletRequest request, HttpServletResponse response
            , SpringBootFileModel springBootFileModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>) ApplicationContextFactory.getBean("fileUploadExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, springBootFileModel);
    }

    /**
     * 批量文件上传
     * @param request
     * @param response
     * @return
     */
    @PostMapping (value = "/batchFileUpload")
    public Object batchFileUploadExecutor(HttpServletRequest request, HttpServletResponse response
            , SpringBootFileModel springBootFileModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>) ApplicationContextFactory.getBean("batchFileUploadExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, springBootFileModel);
    }

}
