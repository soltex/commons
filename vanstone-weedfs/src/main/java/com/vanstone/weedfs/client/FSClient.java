package com.vanstone.weedfs.client;

import java.io.File;
import java.io.InputStream;

import com.vanstone.fs.FSFile;



public interface FSClient {
    
    /**
     * 下载文件id
     * @param fid
     * @param path
     * @return
     */
    RequestResult download(String fid, String path);
    
    /**
     * 下载文件
     * @param fid
     * @return
     */
    InputStream download(String fid);
    
    /**
     * 下载文件到Local FS 中
     * @param fid
     * @return
     */
    void downloadToFSFile(String fid,FSFile fsFile);
    
    /**
     * 下载文件
     * @param id
     * @param file
     */
    boolean downloadToFile(String id,File file);
    
    /**
     * 上传文件
     * @param inputFile
     * @return
     */
    RequestResult upload(File inputFile);
    
    /**
     * 直接上传
     * @param inputFile
     * @return
     */
    RequestResult uploadDirectly(File inputFile);
    
    /**
     * 上传文件
     * @param data
     * @param fileName
     * @param mimeType
     * @return
     */
    RequestResult upload(byte[] data, String fileName, String mimeType);
    
    /**
     * 上传文件
     * @param inputstream
     * @param fileName
     * @param mimeType
     * @return
     */
    RequestResult upload(InputStream inputstream, String fileName, String mimeType);
    
    /**
     * 删除文件
     * @param fid
     * @return
     */
    RequestResult delete(String fid);
    
}
