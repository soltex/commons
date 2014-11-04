package com.vanstone.common.util;

import java.io.File;

public class ExecUtils {
	public static boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		// windows
		return (os.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		String os = System.getProperty("os.name").toLowerCase();
		// Mac
		return (os.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		// linux or unix
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

	public static boolean isSolaris() {
		String os = System.getProperty("os.name").toLowerCase();
		// Solaris
		return (os.indexOf("sunos") >= 0);
	}

	public static boolean execCmd(String cmd) {

		if (isUnix()) {
//			cmd = "/bin/sh -c " + cmd;
		} else {
			cmd = "cmd /c start " + cmd;
		}

		return exec(cmd);
	}

	private static boolean exec(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean pdf2json(String srcPdfPath, String destDirPath){
		File srcPdf = new File(srcPdfPath);
		File destDir = new File(destDirPath);
		if (!srcPdf.exists()) return false;
		if (!destDir.exists()) destDir.mkdirs();
		
		boolean isUnix = isUnix();
		StringBuilder sbCmd = new StringBuilder(
				isUnix ? "pdf2json.sh" : "pdf2json.bat");
		sbCmd.append(" ").append(isUnix ? "pdf2json" : "pdf2json.exe")
				.append(" ").append(srcPdfPath)
				.append(" ").append(destDirPath);
		
		System.out.println(sbCmd);
		return execCmd(sbCmd.toString());

	}
	public static void main(String[] args) {
		StringBuilder sbCmd = new StringBuilder(
				"D:/Program/mupdf/win32/test/pdf2json.bat");
		sbCmd.append(" ").append("D:/Program/mupdf/win32/test/pdf2json.exe")
				.append(" ").append("D:/Program/mupdf/win32/test/v.pdf")
				.append(" ").append("D:/Program/mupdf/win32/test/a");
		System.out.println("test " + execCmd(sbCmd.toString()));

		// try {
		// Runtime.getRuntime().exec("cmd /c start "+sbCmd.toString());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}
}
