package com.myspring.pro.common.file;

import java.io.File;
import java.util.Iterator;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Fileupload {
	
	private static final String project_file = "C:\\project\\project_file";

	// 파일 업로드하기
		public String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
			String projectFileName = null;
			Iterator<String> fileNames = multipartRequest.getFileNames();

			while (fileNames.hasNext()) {
				String PROJECT_FILENAME = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(PROJECT_FILENAME);
				projectFileName = mFile.getOriginalFilename();
				File file = new File(project_file + "\\" + PROJECT_FILENAME);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
						if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
							file.createNewFile(); // 이후 파일 생성
						}
					}
					mFile.transferTo(new File(project_file + "\\" + "temp" + "\\" + projectFileName)); // 임시로 저장된
																										// multipartFile을 실제
																										// 파일로 전송
				}
			}
			return projectFileName;
		}
}
