/**
 * 
 */
package com.vanstone.commons.fs;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSManager;
import com.vanstone.fs.FSType;

/**
 * @author shipeng
 */
public class FSManagerTest {
	
	@Test
	public void testnewFSFile() {
		FSManager fsManager = FSManager.getInstance();
		FSFile commonFSFile = fsManager.newAndCreateFSFile("呵呵呵呵呵","jpg", FSType.CommonType);
		System.out.println("文件id " + commonFSFile.getFileid());
		System.out.println("fileidpath " + commonFSFile.getFileidpath());
		System.out.println("物理路径 " + commonFSFile.getPhysicalFilepath());
		System.out.println(" ======================= ");
		FSFile tmpFsFile =fsManager.newAndCreateFSFile("临时文件","doc", FSType.Temporary);
		System.out.println("文件id " + tmpFsFile.getFileid());
		System.out.println("fileidpath " + tmpFsFile.getFileidpath());
		System.out.println(tmpFsFile.getPhysicalFilepath());
		System.out.println(" ======================= ");
		FSFile constantsFsFile = fsManager.newAndCreateFSFile("常量文件","docx", FSType.Constants);
		System.out.println("文件id " + constantsFsFile.getFileid());
		System.out.println("fileidpath " + constantsFsFile.getFileidpath());
		System.out.println("物理路径 " + constantsFsFile.getPhysicalFilepath());
		
	}
	
	@Test
	public void testdeleteFsFile() {
		///2014/05/22/呵呵呵呵呵.jpg
		FSManager fsManager = FSManager.getInstance();
		fsManager.deleteFile(fsManager.newAndCreateFSFile("/2014/05/22/呵呵呵呵呵.jpg", FSType.CommonType));
		///临时文件.doc
		fsManager.deleteFSFile("/临时文件.doc", FSType.Temporary);
		///常量文件.docx
		fsManager.deleteFSFile("/常量文件.docx", FSType.Constants);
	}
	
	@Test
	public void testgetFilename() {
		String fileid = "/12/12/12/123123123.jpg";
		System.out.println(FilenameUtils.getPath(fileid));
		System.out.println(FilenameUtils.getName(fileid));
		System.out.println(FilenameUtils.getBaseName(fileid));
	}
	
	@Test
	public void createTempFS() {
		FSFile fsFile = FSManager.getInstance().newAndCreateFSFile("aa", "aaaaa", FSType.Temporary);
		System.out.println(fsFile.getPhysicalFilepath());
	}
	
	@Test
	public void testCopy() {
		File file = new File("E:/testimg/130628007-1 铂晶册 8X8 -7.jpg");
		FSFile fsFile = FSManager.getInstance().copy(file, FSType.Temporary);
		System.out.println(fsFile.getFileid());
	}
}
