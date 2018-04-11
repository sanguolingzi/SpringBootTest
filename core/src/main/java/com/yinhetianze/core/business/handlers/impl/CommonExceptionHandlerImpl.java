package com.yinhetianze.core.business.handlers.impl;

import com.yinhetianze.common.business.sys.errorcode.service.ErrorCodeInfoService;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.handlers.ExceptionHandler;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.ResponseData;
import com.yinhetianze.core.business.httpresponse.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommonExceptionHandlerImpl implements ExceptionHandler{

    @Autowired
    private ErrorCodeInfoService commonErrorCodeInfoServiceImpl;

    @Override
    public ResponseData handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ResponseData responseData = new ResponseData();
        Result result = null;
        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException)e;
            Map<String, Object> codeMap = commonErrorCodeInfoServiceImpl.getErrorCodeByCode( businessException.getErrorCode());
            codeMap = codeMap==null?new HashMap<>():codeMap;
            String message = codeMap.getOrDefault("errorMessage","").toString();
            message= StringUtils.isEmpty(message)?businessException.getErrorMessage():message;
            result = new Result();
            /**
             * code     ResponseConstant.RESULT_CODE_BUSI_FAILED 固定 此类 代表业务异常
             * message  根据businessException 中 errorcode 找到表中对应的错误信息模板
             *          否则 同 Description
             * Description   businessException中的errormessage
             */
            result.setCode(ResponseConstant.RESULT_CODE_BUSI_FAILED);
            result.setMessage(businessException.getErrorMessage());
            result.setDescription(message);
        }else{
            result = new Result(e.getMessage());
        }
        responseData.setResultInfo(result);
        return responseData;
    }
}
