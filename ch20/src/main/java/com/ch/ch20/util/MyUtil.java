package com.ch.ch20.util;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 工具类
 */
public class MyUtil {
    /**
     *将实际的文件名重命名
     */
    public static String getNewFileName(String oldFileName){
        int lastIndex=oldFileName.indexOf(".");
        String fileType=oldFileName.substring(lastIndex);
        Date now=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time=sdf.format(now);
        String newFileName=time+fileType;
        return newFileName;
    }
}
