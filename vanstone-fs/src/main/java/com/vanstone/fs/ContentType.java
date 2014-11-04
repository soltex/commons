/**
 * 
 */
package com.vanstone.fs;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

/**
 * @author shipeng
 */
public final class ContentType {
	
	private static final Map<String, String> CONTENT_TYPE_REPO = new LinkedHashMap<String, String>();
	
	static {
		CONTENT_TYPE_REPO.put("ez","application/andrew-inset");
		CONTENT_TYPE_REPO.put("hqx","application/mac-binhex40");
		CONTENT_TYPE_REPO.put("cpt","application/mac-compactpro");
		CONTENT_TYPE_REPO.put( "doc","application/msword");
		CONTENT_TYPE_REPO.put( "docx","application/msword");
		CONTENT_TYPE_REPO.put( "bin","application/octet-stream");
		CONTENT_TYPE_REPO.put( "dms","application/octet-stream");
		CONTENT_TYPE_REPO.put( "lha","application/octet-stream");
		CONTENT_TYPE_REPO.put( "lzh","application/octet-stream");
		CONTENT_TYPE_REPO.put( "exe","application/octet-stream");
		CONTENT_TYPE_REPO.put( "class","application/octet-stream");
		CONTENT_TYPE_REPO.put( "so","application/octet-stream");
		CONTENT_TYPE_REPO.put( "dll","application/octet-stream");
		CONTENT_TYPE_REPO.put( "oda","application/oda");
		CONTENT_TYPE_REPO.put( "pdf","application/pdf");
		CONTENT_TYPE_REPO.put( "ai","application/postscript");
		CONTENT_TYPE_REPO.put( "eps","application/postscript");
		CONTENT_TYPE_REPO.put( "ps","application/postscript");
		CONTENT_TYPE_REPO.put( "smi","application/smil");
		CONTENT_TYPE_REPO.put( "smil","application/smil");
		CONTENT_TYPE_REPO.put( "mif","application/vnd.mif");
		CONTENT_TYPE_REPO.put( "xls","application/vnd.ms-excel");
		CONTENT_TYPE_REPO.put( "xlsx","application/vnd.ms-excel");
		CONTENT_TYPE_REPO.put( "ppt","application/vnd.ms-powerpoint");
		CONTENT_TYPE_REPO.put( "wbxml","application/vnd.wap.wbxml");
		CONTENT_TYPE_REPO.put( "wmlc","application/vnd.wap.wmlc");
		CONTENT_TYPE_REPO.put( "wmlsc","application/vnd.wap.wmlscriptc");
		CONTENT_TYPE_REPO.put( "bcpio","application/x-bcpio");
		CONTENT_TYPE_REPO.put( "vcd","application/x-cdlink");
		CONTENT_TYPE_REPO.put( "pgn","application/x-chess-pgn");
		CONTENT_TYPE_REPO.put( "cpio","application/x-cpio");
		CONTENT_TYPE_REPO.put( "csh","application/x-csh");
		CONTENT_TYPE_REPO.put( "dcr","application/x-director");
		CONTENT_TYPE_REPO.put( "dir","application/x-director");
		CONTENT_TYPE_REPO.put( "dxr","application/x-director");
		CONTENT_TYPE_REPO.put( "dvi","application/x-dvi");
		CONTENT_TYPE_REPO.put( "spl","application/x-futuresplash");
		CONTENT_TYPE_REPO.put( "gtar","application/x-gtar");
		CONTENT_TYPE_REPO.put( "hdf","application/x-hdf");
		CONTENT_TYPE_REPO.put( "js","application/x-javascript");
		CONTENT_TYPE_REPO.put( "skp","application/x-koan");
		CONTENT_TYPE_REPO.put( "skd","application/x-koan");
		CONTENT_TYPE_REPO.put( "skt","application/x-koan");
		CONTENT_TYPE_REPO.put( "skm","application/x-koan");
		CONTENT_TYPE_REPO.put( "latex","application/x-latex");
		CONTENT_TYPE_REPO.put( "nc","application/x-netcdf");
		CONTENT_TYPE_REPO.put( "cdf","application/x-netcdf");
		CONTENT_TYPE_REPO.put( "sh","application/x-sh");
		CONTENT_TYPE_REPO.put( "shar","application/x-shar");
		CONTENT_TYPE_REPO.put( "swf","application/x-shockwave-flash");
		CONTENT_TYPE_REPO.put( "sit","application/x-stuffit");
		CONTENT_TYPE_REPO.put( "sv4cpio","application/x-sv4cpio");
		CONTENT_TYPE_REPO.put( "sv4crc","application/x-sv4crc");
		CONTENT_TYPE_REPO.put( "tar","application/x-tar");
		CONTENT_TYPE_REPO.put( "tcl","application/x-tcl");
		CONTENT_TYPE_REPO.put( "tex","application/x-tex");
		CONTENT_TYPE_REPO.put( "texinfo","application/x-texinfo");
		CONTENT_TYPE_REPO.put( "texi","application/x-texinfo");
		CONTENT_TYPE_REPO.put( "t","application/x-troff");
		CONTENT_TYPE_REPO.put( "tr","application/x-troff");
		CONTENT_TYPE_REPO.put( "roff","application/x-troff");
		CONTENT_TYPE_REPO.put( "man","application/x-troff-man");
		CONTENT_TYPE_REPO.put( "me","application/x-troff-me");
		CONTENT_TYPE_REPO.put( "ms","application/x-troff-ms");
		CONTENT_TYPE_REPO.put( "ustar","application/x-ustar");
		CONTENT_TYPE_REPO.put( "src","application/x-wais-source");
		CONTENT_TYPE_REPO.put( "xhtml","application/xhtml+xml");
		CONTENT_TYPE_REPO.put( "xht","application/xhtml+xml");
		CONTENT_TYPE_REPO.put( "zip","application/zip");
		CONTENT_TYPE_REPO.put( "au","audio/basic");
		CONTENT_TYPE_REPO.put( "snd","audio/basic");
		CONTENT_TYPE_REPO.put( "mid","audio/midi");
		CONTENT_TYPE_REPO.put( "midi","audio/midi");
		CONTENT_TYPE_REPO.put( "kar","audio/midi");
		CONTENT_TYPE_REPO.put( "mpga","audio/mpeg");
		CONTENT_TYPE_REPO.put( "mp2","audio/mpeg");
		CONTENT_TYPE_REPO.put( "mp3","audio/mpeg");
		CONTENT_TYPE_REPO.put( "aif","audio/x-aiff");
		CONTENT_TYPE_REPO.put( "aiff","audio/x-aiff");
		CONTENT_TYPE_REPO.put( "aifc","audio/x-aiff");
		CONTENT_TYPE_REPO.put( "m3u","audio/x-mpegurl");
		CONTENT_TYPE_REPO.put( "ram","audio/x-pn-realaudio");
		CONTENT_TYPE_REPO.put( "rm","audio/x-pn-realaudio");
		CONTENT_TYPE_REPO.put( "rpm","audio/x-pn-realaudio-plugin");
		CONTENT_TYPE_REPO.put( "ra","audio/x-realaudio");
		CONTENT_TYPE_REPO.put( "wav","audio/x-wav");
		CONTENT_TYPE_REPO.put( "pdb","chemical/x-pdb");
		CONTENT_TYPE_REPO.put( "xyz","chemical/x-xyz");
		CONTENT_TYPE_REPO.put( "bmp","image/bmp");
		CONTENT_TYPE_REPO.put( "gif","image/gif");
		CONTENT_TYPE_REPO.put( "ief","image/ief");
		CONTENT_TYPE_REPO.put( "jpeg","image/jpeg");
		CONTENT_TYPE_REPO.put( "jpg","image/jpeg");
		CONTENT_TYPE_REPO.put( "jpe","image/jpeg");
		CONTENT_TYPE_REPO.put( "png","image/png");
		CONTENT_TYPE_REPO.put( "tiff","image/tiff");
		CONTENT_TYPE_REPO.put( "tif","image/tiff");
		CONTENT_TYPE_REPO.put( "djvu","image/vnd.djvu");
		CONTENT_TYPE_REPO.put( "djv","image/vnd.djvu");
		CONTENT_TYPE_REPO.put( "wbmp","image/vnd.wap.wbmp");
		CONTENT_TYPE_REPO.put( "ras","image/x-cmu-raster");
		CONTENT_TYPE_REPO.put( "pnm","image/x-portable-anymap");
		CONTENT_TYPE_REPO.put( "pbm","image/x-portable-bitmap");
		CONTENT_TYPE_REPO.put( "pgm","image/x-portable-graymap");
		CONTENT_TYPE_REPO.put( "ppm","image/x-portable-pixmap");
		CONTENT_TYPE_REPO.put( "rgb","image/x-rgb");
		CONTENT_TYPE_REPO.put( "xbm","image/x-xbitmap");
		CONTENT_TYPE_REPO.put( "xpm","image/x-xpixmap");
		CONTENT_TYPE_REPO.put( "xwd","image/x-xwindowdump");
		CONTENT_TYPE_REPO.put( "igs","model/iges");
		CONTENT_TYPE_REPO.put( "iges","model/iges");
		CONTENT_TYPE_REPO.put( "msh","model/mesh");
		CONTENT_TYPE_REPO.put( "mesh","model/mesh");
		CONTENT_TYPE_REPO.put( "silo","model/mesh");
		CONTENT_TYPE_REPO.put( "wrl","model/vrml");
		CONTENT_TYPE_REPO.put( "vrml","model/vrml");
		CONTENT_TYPE_REPO.put( "css","text/css");
		CONTENT_TYPE_REPO.put( "html","text/html");
		CONTENT_TYPE_REPO.put( "htm","text/html");
		CONTENT_TYPE_REPO.put( "asc","text/plain");
		CONTENT_TYPE_REPO.put( "txt","text/plain");
		CONTENT_TYPE_REPO.put( "rtx","text/richtext");
		CONTENT_TYPE_REPO.put( "rtf","text/rtf");
		CONTENT_TYPE_REPO.put( "sgml","text/sgml");
		CONTENT_TYPE_REPO.put( "sgm","text/sgml");
		CONTENT_TYPE_REPO.put( "tsv","text/tab-separated-values");
		CONTENT_TYPE_REPO.put( "wml","text/vnd.wap.wml");
		CONTENT_TYPE_REPO.put( "wmls","text/vnd.wap.wmlscript");
		CONTENT_TYPE_REPO.put( "etx","text/x-setext");
		CONTENT_TYPE_REPO.put( "xsl","text/xml");
		CONTENT_TYPE_REPO.put( "xml","text/xml");
		CONTENT_TYPE_REPO.put( "mpeg","video/mpeg");
		CONTENT_TYPE_REPO.put( "mpg","video/mpeg");
		CONTENT_TYPE_REPO.put( "mpe","video/mpeg");
		CONTENT_TYPE_REPO.put( "qt","video/quicktime");
		CONTENT_TYPE_REPO.put( "mov","video/quicktime");
		CONTENT_TYPE_REPO.put( "mxu","video/vnd.mpegurl");
		CONTENT_TYPE_REPO.put( "avi","video/x-msvideo");
		CONTENT_TYPE_REPO.put( "movie","video/x-sgi-movie");
		CONTENT_TYPE_REPO.put( "ice","x-conference/x-cooltalk" );
	}
	
	private String type;
	private String mimeType;
	
	private ContentType(String type,String mimeType)  {
		if (type == null || "".equals(type) || mimeType == null || "".equals(mimeType)) {
			throw new IllegalArgumentException();
		}
		this.type = type;
		this.mimeType = mimeType;
	}
	
	/**
	 * 获取当前type（文件扩展名）
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 获取MimeType值
	 * @return
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * 解析ContentType
	 * @param extName
	 * @return
	 */
	public static ContentType parseContentType(String extName) {
		if (extName == null || extName.equals("")) {
			return null;
		}
		String value = CONTENT_TYPE_REPO.get(extName.toLowerCase());
		if (value == null || "".equals(value)) {
			return null;
		}
		return new ContentType(extName, value);
	}
	
	/**
	 * 解析ContentType
	 * @param file
	 * @return
	 */
	public static ContentType parseContentType(File file) {
		if (file == null) {
			return null;
		}
		String extName = FilenameUtils.getExtension(file.getName());
		return parseContentType(extName);
	}
	
}
