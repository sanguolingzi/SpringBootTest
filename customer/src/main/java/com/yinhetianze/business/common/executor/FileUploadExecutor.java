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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Component
public class FileUploadExecutor extends AbstractRestBusiExecutor<String> {

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
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        SpringBootFileModel fileModel = (SpringBootFileModel)model;
        try{
            Object fileName = CommonUtil.getFieldValue(fileModel.getFile(),"filename");
            int index = fileName.toString().lastIndexOf(".");
            String storeFileExt = fileName.toString().substring(index);
            String storeFileName = CommonUtil.getSerialnumber()+storeFileExt;
            String busiPath = getImagePath(fileModel.getFileBusiType());
            String sroeFilePath = fileUploadPath+busiPath;
            File file = FileUtil.uploadFile(fileModel.getFile(),sroeFilePath,storeFileName);
            return file.getName();
        }catch(Exception e){
            LoggerUtil.error(FileUploadExecutor.class,e.toString());
            throw new BusinessException();
        }
    }

    /**
     * 1、商城活动   2、楼层  3、banner 4、频道
     * @param fileBusiType
     * @return
     */
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
