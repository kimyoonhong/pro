package com.myspring.pro.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FileDownloadController {
	private static final String project_file = "C:\\project\\project_file";
	private static final String admin_file = "C:\\admin\\admin_file";
	@RequestMapping("/download.do")
	protected void projectdownload(@RequestParam("PROJECT_FILENAME") String PROJECT_FILENAME,
							@RequestParam("PROJECT_CODE") int PROJECT_CODE,
							HttpServletRequest request,
							HttpServletResponse response)throws Exception {

		System.out.println("파일 이름"+PROJECT_FILENAME);
		//파일 위치
		String downFile = project_file + "\\" +PROJECT_CODE+"\\"+ PROJECT_FILENAME;
		File file = new File(downFile);
		long length = file.length();
		response.setHeader("Cache-Control","no-cache");
		response.setContentType("application/octet-stream");
		response.setContentLength((int)length);
		
		boolean isIe = request.getHeader("user-agent").toUpperCase().indexOf("MSIE") != -1 ||
				   request.getHeader("user-agent").indexOf("11.0") != -1;
		if(isIe){ // 인터넷 익스플로러
			PROJECT_FILENAME = URLEncoder.encode(PROJECT_FILENAME,"UTF-8");
		}
		else{ // 기타 웹브라우저
			// new String(byte[] bytes, String charset) 사용
			// 1] 파일명은 byte형 배열로 변환
			// 2] String 클래스의 생성자에 변환한 배열과 charset는 8859_1 지정
			PROJECT_FILENAME = new String(PROJECT_FILENAME.getBytes("UTF-8"), "8859_1");
		}
		response.addHeader("Content-disposition", "attachment; fileName=" + PROJECT_FILENAME);
		byte[] buffer = new byte[1024 * 1024*10];
		BufferedInputStream  in = new BufferedInputStream (new FileInputStream(file));
		
		BufferedOutputStream  out = new BufferedOutputStream(response.getOutputStream());
		
		while (true) {
			int count = in.read(buffer); 
			if (count == -1) 
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	@RequestMapping("/noticedownload.do")
	protected void noticedownload(@RequestParam("NOTICE_FILENAME") String NOTICE_FILENAME,
			@RequestParam("NOTICE_CODE") int NOTICE_CODE,
			HttpServletRequest request,
			HttpServletResponse response)throws Exception {

			System.out.println("파일 이름"+NOTICE_FILENAME);
			//파일 위치
			String downFile = admin_file + "\\" +NOTICE_CODE+"\\"+ NOTICE_FILENAME;
			File file = new File(downFile);
			long length = file.length();
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("application/octet-stream");
			response.setContentLength((int)length);
			
			boolean isIe = request.getHeader("user-agent").toUpperCase().indexOf("MSIE") != -1 ||
			   request.getHeader("user-agent").indexOf("11.0") != -1;
			if(isIe){ // 인터넷 익스플로러
				NOTICE_FILENAME = URLEncoder.encode(NOTICE_FILENAME,"UTF-8");
			}
			else{ // 기타 웹브라우저
			// new String(byte[] bytes, String charset) 사용
			// 1] 파일명은 byte형 배열로 변환
			// 2] String 클래스의 생성자에 변환한 배열과 charset는 8859_1 지정
				NOTICE_FILENAME = new String(NOTICE_FILENAME.getBytes("UTF-8"), "8859_1");
			}
			response.addHeader("Content-disposition", "attachment; fileName=" + NOTICE_FILENAME);
			byte[] buffer = new byte[1024 * 1024*10];
			BufferedInputStream  in = new BufferedInputStream (new FileInputStream(file));
			
			BufferedOutputStream  out = new BufferedOutputStream(response.getOutputStream());
			
			while (true) {
			int count = in.read(buffer); 
			if (count == -1) 
			break;
			out.write(buffer, 0, count);
			}
			in.close();
			out.close();
}
	
	

}
