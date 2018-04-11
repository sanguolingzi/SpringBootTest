package com.yinhetianze.core.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtil
{

    /**
     * 将content写入到指定的fileName文件中
     * 
     * @param fileName
     *            服务器文件绝对路径
     * @param content
     *            需要保存的文件内容
     * @throws Exception
     */
    public static synchronized void writeFile(String fileName, String content) throws Exception
    {
        File file = new File(fileName);
        // 生成文件路径
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }

        FileWriter fw = new FileWriter(file);

        try
        {
            fw.write(content);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (CommonUtil.isNotEmpty(fw))
            {
                fw.close();
            }
        }
    }
    
    /**
     * 读取classpath的文件
     * 1、如果src下有keystore/primaryKey.keystore, 那么读取这个文件就传递keystore/primaryKey.keystore
     * 2、如果src下有primaryKey.keystore, 那么就直接传递primaryKey.keystore读取这个文件
     * @param fileName classpath下的相对文件名
     * @return
     * @throws Exception
     */
    public static String readFileFromClasspath(String fileName) throws Exception
    {
        if (CommonUtil.isNotEmpty(fileName))
        {
            return readFile(FileUtil.class.getResource(CommonConstant.CHAR_SLASH).getFile() + CommonConstant.CHAR_SLASH + fileName);
        }
        return null;
    }

    /**
     * 读取指定的文件中的内容，返回字符串信息
     * @param fileName 文件所在服务器绝对路径
     * @return
     * @throws Exception
     */
    public static String readFile(String fileName) throws Exception
    {
        StringBuffer content = new StringBuffer();
        String temp = null;

        if (CommonUtil.isNotEmpty(fileName))
        {
            FileReader in = null;
            BufferedReader br = null;
            try
            {
                File file = new File(fileName);
                if (file.exists())
                {
                    in = new FileReader(file);
                    br = new BufferedReader(in);

                    while (CommonUtil.isNotEmpty(temp = br.readLine()))
                    {
                        content.append(temp);
                        temp = "";
                    }
                }
            }
            catch (Exception e)
            {
                throw e;
            }
            finally
            {
                if (CommonUtil.isNotEmpty(in))
                {
                    in.close();
                }
                if (CommonUtil.isNotEmpty(br))
                {
                    br.close();
                }
            }
        }

        return content.toString();
    }
    
    /**
     * apache common fileupload上传文件方法
     * @param request
     * @param dir
     * @param storeFileName
     * @throws Exception
     */
    /*public static void uploadFile(HttpServletRequest request, String dir, String storeFileName) throws Exception
    {
        if (CommonUtil.isEmpty(storeFileName))
        {
            LoggerUtil.info(FileUtil.class, "上传文件未成功：文件名为空！");
            return;
        }
        
        StringBuffer targetFileName = new StringBuffer();
        // 如果文件路径不存在，创建文件路径
        File fileDir = new File(dir);
        if (!fileDir.exists())
        {
            fileDir.mkdirs();
        }
        // 设置文件夹路径
        targetFileName.append(fileDir);
        // 如果文件路径不以/，\结尾，添加分隔符
        if (!(targetFileName.indexOf("/") == targetFileName.length() - 1) 
                && !(targetFileName.indexOf("\\") == targetFileName.length() - 1))
        {
            targetFileName.append(File.separator);
        }
        // 最终保存的文件全名
        targetFileName.append(storeFileName);
        
        // 文件项工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 上传文件大小，内存区大小，超过了写到磁盘
//        factory.setSizeThreshold(PropertyUtil.getIntProperty("fileupload.maxSize", 512000));
        // 临时文件目录
//        factory.setRepository(new File(PropertyUtil.getProperty("fileUploadRoot")));
        
        // 文件上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 文件上传最大限制
//        upload.setFileSizeMax(PropertyUtil.getLongProperty("fileupload.maxSize"));
        
        // 请求中的文件字段参数
        List<FileItem> items = upload.parseRequest(request);
        if (CommonUtil.isNotEmpty(items))
        {
            // 文件上传限制的文件类型，默认限制只能上传png，jpeg，jpg三中图片
//            String[] types = PropertyUtil.getProperty("fileupload.filetype", ".png, .jpeg, .jpg").split(",");
            for (FileItem fi : items)
            {
                // 针对文件表单域处理
                if(!fi.isFormField())
                {
                    for (String t : types)
                    {
                        if (fi.getName().endsWith(t.trim()))
                        {
                            // 输出文件
                            fi.write(new File(targetFileName.toString()));
                            break;
                        }
                    }
                }
            }
        }
        else
        {
            return ;
        }
    }*/
    
    /**
     * 上传文件，由springMVC的CommonsMultipartFile处理
     * @param multipartFile 文件对象
     * @param dir 文件保存目录(仅文件路径)
     * @param storeFileName 保存的文件名字(不包含文件路径)
     * @throws Exception
     */
    public static Boolean uploadFile(CommonsMultipartFile multipartFile, String dir, String storeFileName) throws Exception
    {
        if (CommonUtil.isNotEmpty(storeFileName)
                && CommonUtil.isNotEmpty(multipartFile))
        {
            int index = storeFileName.lastIndexOf(".");
            if (index > -1)
            {
                // 文件类型校验
//                String allowedFileType = SysConfigUtil.getConfig("upload_file_type_allowed"); // 配置的文件可上传类型
                String allowedFileType = "";
                String storeFileExt = storeFileName.substring(index);
                if (CommonUtil.isEmpty(allowedFileType))
                {
                    LoggerUtil.info(FileUtil.class, "没有配置任何可以上传的文件类型");
                }
                else
                {
                    String[] types = allowedFileType.split(",");
                    boolean isAllowed = false;
                    String temp = "";
                    for (String ext : types)
                    {
                        temp = (ext.indexOf(".") != 0 ? "."+ext : ext).toLowerCase();
                        // 配置的文件拓展名不是以.开头，则拼上.后再与storeFileExt进行比较是否相同
                        if (temp.equals(storeFileExt.toLowerCase()))
                        {
                            isAllowed = true;
                            break;
                        }
                    }
                    if (!isAllowed)
                    {
                        throw new Exception("上传的文件格式不被允许。请检查文件格式是否是："+allowedFileType);
                    }
                }
            }
            else
            {
                LoggerUtil.warn(FileUtil.class, "文件格式不正确或者配置路径不正确。文件：{}；配置路径：{}", new String[]{storeFileName, dir});
                throw new Exception("文件格式不正确。请检查文件格式");
            }

            StringBuffer targetFileName = new StringBuffer();
            // 创建文件路径
            if (CommonUtil.isNotEmpty(dir))
            {
                File storeDir = new File(dir);
                if (!storeDir.exists())
                {
                    storeDir.mkdirs();
                }
                targetFileName.append(storeDir.getAbsolutePath());
            }
            
            // 最终文件保存路径
            if (!(targetFileName.indexOf("/") == targetFileName.length() - 1) 
                    && !(targetFileName.indexOf("\\") == targetFileName.length() - 1))
            {
                targetFileName.append(File.separator);
            }
            targetFileName.append(storeFileName);
            
            multipartFile.transferTo(new File(targetFileName.toString()));
            return true;
        }
        return false;
    }

    /**
     * 获取指定文件名的file对象
     * 没有获取到或者文件不存在则返回空
     * @param fileName
     * @return
     */
    public static File loadFileInClassPath(String fileName)
    {
        if (CommonUtil.isNotEmpty(fileName))
        {
            try
            {
                File file = new File(FileUtil.class.getResource(CommonConstant.CHAR_SLASH).getFile() + CommonConstant.CHAR_SLASH + fileName);
                if (file.exists() && file.isFile())
                {
                    return file;
                }
                else
                {
                    return null;
                }
            }
            catch (Exception e)
            {
                LoggerUtil.error(FileUtil.class, e);
            }
        }
        return null;
    }

    /**
     * 删除指定路径的文件
     * @param path 文件所在路径
     * @param fileName 文件名称
     * @return true=删除成功，false=未删除成功
     */
    public static boolean removeFile(String path, String fileName)
    {
        if (CommonUtil.isNotEmpty(fileName))
        {
            if (CommonUtil.isNotEmpty(path) && !path.endsWith("/"))
            {
                path += File.separator;
            }

            File file = new File(path + fileName);
            if (file.exists())
            {
                return file.delete();
            }
        }
        return false;
    }
}
