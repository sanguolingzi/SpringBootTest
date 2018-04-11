package com.yinhetianze.core.business.handlers.impl;

import com.yinhetianze.common.business.sys.errorcode.service.ErrorCodeInfoService;
import com.yinhetianze.core.business.handlers.ErrorMessageHandler;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.ResponseData;
import com.yinhetianze.core.business.httpresponse.Result;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.business.httpresponse.message.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class CommonErrorMessageHandlerImpl implements ErrorMessageHandler {

    @Autowired
    private ErrorCodeInfoService commonErrorCodeInfoServiceImpl;

    public ResponseData handleErrorMessage(HttpServletRequest request, HttpServletResponse response, ErrorMessage errorMessage, Object... param) {
        ResponseData responseData = new ResponseData();
        Result result = null;
        /**
         *  若
         *  errorList 为空 则用默认设置的错误描述信息
         *
         *  否则
         *  errorList 中获取errors
         *  errors 中设置的 field 属性名称  errorcode 错误编码   以及 errorMessage.getErrorParams 中 field 对应的 错误描述需要的内容
         *  构建错误信息 返回
         */
        List<Errors> errorsList = errorMessage.getErrorList();
        if(errorsList != null && errorsList.size() > 0){
            StringJoiner stringJoiner = new StringJoiner(",","","");
            for(Errors errors : errorsList){
                Map<String, Object> codeMap = commonErrorCodeInfoServiceImpl.getErrorCodeByCode(errors.getErrorCode());
                codeMap = codeMap==null?new HashMap<String,Object>():codeMap;
                //获取 数据库中设置的 错误信息模板
                String message = codeMap.get("errorMessage")==null?"":codeMap.get("errorMessage").toString();
                //String message = codeMap.getOrDefault("errorMessage","").toString();
                //获取 errorfield 对应的 错误信息内容
                Object[] params = errorMessage.getErrorParams().get(errors.getErrorField());
                if(!StringUtils.isEmpty(message)){
                    if(message.indexOf("{")>=0 && params!=null && params.length > 0 ){
                        for(int i = 0;i<params.length;i++){
                            message=message.replace("{"+i+"}",params[i].toString());
                        }
                        stringJoiner=stringJoiner.add(message);
                    }else{
                        stringJoiner=stringJoiner.add(message);
                    }
                }else{//模板和错误信息没有 则取 defaultMessage
                    stringJoiner=stringJoiner.add(errors.getDefaultMessage());
                }
            }
            result = new Result();
            result.setCode(ResponseConstant.RESULT_CODE_BUSI_FAILED);
            result.setMessage(ResponseConstant.RESULT_DESCRIPTION_FAILED);
            result.setDescription(stringJoiner.length()==0?"业务异常":stringJoiner.toString());
        }else{
            responseData = new ResponseData();
            result = new Result("系统异常");
            responseData.setResultInfo(result);
        }
        responseData.setResultInfo(result);
        return responseData;
    }
}
