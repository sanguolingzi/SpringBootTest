package com.yinhetianze.business.common.executor;

import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httprequest.SpringBootFileModel;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.FileUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class BatchFileUploadExecutor extends AbstractRestBusiExecutor<Object> {

    @Value("${fileUploadPath}")
    private String fileUploadPath;

    @Value("${mallImagePath}")
    private String mallImagePath;

    @Value("${floorImagePath}")
    private String floorImagePath;

    @Value("${bannerImagePath}")
    private String bannerImagePath;

    @Value("${channelImagePath}")
    private String channelImagePath;

    @Value("${commonImagePath}")
    private String commonImagePath;

    @Value("${helpImagePath}")
    private String helpImagePath;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        SpringBootFileModel fileModel = (SpringBootFileModel)model;
        List<String> keyList = new ArrayList<String>();
        try{
            for(MultipartFile multipartFile : fileModel.getFiles()){
                Object fileName = CommonUtil.getFieldValue(multipartFile,"filename");
                int index = fileName.toString().lastIndexOf(".");
                String storeFileExt = fileName.toString().substring(index);
                String storeFileName = CommonUtil.getSerialnumber()+storeFileExt;
                String sroeFilePath = fileUploadPath+getImagePath(fileModel.getFileBusiType());
                File file = FileUtil.uploadFile(multipartFile,sroeFilePath,   storeFileName);
                keyList.add(file.getName());
            }
            return keyList;
        }catch(Exception e){
            LoggerUtil.error(FileUploadExecutor.class,e.toString());
            throw new BusinessException();
        }
    }
    private String getImagePath(String fileBusiType){
        if("1".equals(fileBusiType)){
            return mallImagePath;
        }else if("2".equals(fileBusiType)){
            return floorImagePath;
        }else if("3".equals(fileBusiType)){
            return bannerImagePath;
        }else if("4".equals(fileBusiType)){
            return channelImagePath;
        }else if("5".equals(fileBusiType)){
            return helpImagePath;
        }else{
            return commonImagePath;
        }
    }
}
