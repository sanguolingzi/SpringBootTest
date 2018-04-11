package com.yinhetianze.core.business.httprequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件上传/下载model
 * 使用spring-fileupload的CommonsMultipartFile上传文件
 * @author Administrator
 *
 */
public class FileModel extends BasicModel
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
    private CommonsMultipartFile file;

    /**
     * 多文件上传时的文件对象数组
     */
    private CommonsMultipartFile[] files;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public CommonsMultipartFile getFile()
    {
        return file;
    }

    public void setFile(CommonsMultipartFile file)
    {
        this.file = file;
    }

    public CommonsMultipartFile[] getFiles()
    {
        return files;
    }

    public void setFiles(CommonsMultipartFile[] files)
    {
        this.files = files;
    }
}
