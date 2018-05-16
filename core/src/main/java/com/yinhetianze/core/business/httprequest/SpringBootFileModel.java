package com.yinhetianze.core.business.httprequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * springboot 自带的文件上传 使用的是 StandardMultipartFile
 * 文件上传/下载model
 * 使用spring-fileupload的CommonsMultipartFile上传文件
 * @author Administrator
 *
 */
public class SpringBootFileModel extends BasicModel
{
    /**
     * 
     */
    private static final long serialVersionUID = -3996768234962436550L;

    /**
     * 只上传一个文件的时候使用的文件名
     */
    private String fileName;

    /**
     * 单文件上传时的入参文件对象
     */
    private MultipartFile file;


    /**
     * 扩展参数 文件所属业务 用来匹配对应存放目录
     */
    private String fileBusiType;


    /**
     * 多文件上传时的文件对象数组
     */
    private MultipartFile[] files;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public MultipartFile getFile()
    {
        return file;
    }

    public void setFile(MultipartFile file)
    {
        this.file = file;
    }

    public MultipartFile[] getFiles()
    {
        return files;
    }

    public void setFiles(MultipartFile[] files)
    {
        this.files = files;
    }

    public String getFileBusiType() {
        return fileBusiType;
    }

    public void setFileBusiType(String fileBusiType) {
        this.fileBusiType = fileBusiType;
    }
}
