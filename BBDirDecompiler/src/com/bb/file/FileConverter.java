package com.bb.file;

import java.io.File;

import com.bb.date.DateUtil;
import com.bb.decompile.Decompiler;
import com.bb.exception.MsgException;
import com.bb.form.FormConsole;
import com.bb.util.FileNameUtil;
import com.bb.util.StringUtil;

public class FileConverter {
	
	
	public void convertClassToJava(String filePath) throws MsgException, Exception {
		if (filePath == null || filePath.trim().length() == 0) {
			throw new MsgException("경로를 입력해주세요.");
		} else {
			filePath = filePath.trim();
		}
		
		File file = new File(filePath);
		if (!file.exists()) {
			throw new MsgException("존재하지 않는 경로입니다. [" + file.getAbsolutePath() + "]");
		}
		
		if (file.isFile() || !file.isDirectory()) {
			throw new MsgException("해당 경로는 폴더가 아닌 파일입니다. [" + file.getAbsolutePath() + "]");
		}
		
		FileCollector fileCollector = new FileCollector();
		FileList fileList = fileCollector.getFileList(filePath, true);
		if (fileList == null || fileList.size() == 0) {
			throw new MsgException("해당 폴더 내에 class 파일이 존재하지 않습니다. [" + file.getAbsolutePath() + "]");
		}
	
		String oldDirPath = file.getAbsolutePath();
		String destDirPath = makeDestDir(file);
		
		// FormConsole.print("기존 폴더 : " + oldDirPath);
		// FormConsole.print("결과 폴더 : " + destDirPath);
		
		Decompiler decompiler = new Decompiler();
		
		File oneFile = null;
		String oldFilePath = null;
		String oldFileName = null;
		String destFilePath = null;
		int fileCount = fileList.size();
		
		FormConsole.print("파일 개수 : " + fileCount);
		
		boolean bResult = false;
		for (int i=0; i<fileCount; i++) {
			oneFile = fileList.get(i);
			if (oneFile == null || !oneFile.exists()) {
				continue;
			}
			
			if (!oneFile.getName().toLowerCase().endsWith(".class")) {
				continue;
			}
			
			oldFilePath = oneFile.getAbsolutePath();
			oldFileName = FileNameUtil.getFileName(oldFilePath);
			
			destFilePath = StringUtil.replaceOne(oldFilePath, oldDirPath, destDirPath);
			destFilePath = FileNameUtil.replaceExtension(destFilePath, "java");
			
			bResult = decompiler.decompile(oldFilePath, destFilePath);
			FormConsole.print((i+1) + "/" + fileCount + " : " + oldFileName + " (" + bResult + ")");
		}
		
		FormConsole.print("결과 폴더 : " + destDirPath);
		FormConsole.print("----------");
	}
	
	
	private String makeDestDir(File dirFile) throws Exception {
		if (dirFile == null) {
			return null;
		}
		
		String dirPath = dirFile.getAbsolutePath();
		
		int slashIdx = dirPath.lastIndexOf("\\");
		int slashIdx2 = dirPath.lastIndexOf("/");
		if (slashIdx2 > slashIdx) {
			slashIdx = slashIdx2;
		}
		
		String leftPath = dirPath.substring(0, slashIdx);
		String rightPath = dirPath.substring(slashIdx + 1);
		
		String newPath = leftPath + "/" + rightPath + "_" + DateUtil.getTodayDateTime();
		File newDir = new File(newPath);
		if (!newDir.exists()) {
			newDir.mkdirs();
		}
		
		String destDirPath = newDir.getAbsolutePath();
		return destDirPath;
	}
	
	
	/*
	public void convertJavaToClass() {
		
	}
	*/
}
