package com.myspring.pro.qna.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro.qna.service.qnaService;
import com.myspring.pro.qna.vo.qnaArticleVO;
import com.myspring.pro.qna.vo.qnatagVO;
import com.myspring.pro.qna.vo.ImageVO;
import com.myspring.pro.member.vo.MemberVO;
//import com.myspring.pro.project.controller.projectControllerImpl;
/*import com.myspring.pro.qna.vo.Criteria;
import com.myspring.pro.qna.vo.PageMaker;*/
import com.myspring.pro.page.Criteria;
import com.myspring.pro.page.PageMaker;

@Controller("qnaController")
public class qnaControllerImpl  implements qnaController{
	
	private static final Logger logger = LoggerFactory.getLogger(qnaControllerImpl.class);
	//이미지 파일 경로
	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	@Autowired
	private qnaService qnaService;
	@Autowired
	private qnaArticleVO qnaArticleVO;
	@Autowired
	private qnatagVO qnatagVO;
	@Autowired
	private Criteria Criteria;
	@Autowired
	private PageMaker PageMaker;
	

	//QNA게시글 목록
	@Override
	@RequestMapping(value = "qna/listArticles.do", method = {RequestMethod.POST ,RequestMethod.GET})
	public ModelAndView list(PageMaker pageMaker,Criteria cri, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String viewName = getViewName(request);
		
		logger.info("viewName: "+ viewName);
		logger.debug("viewName: "+ viewName);
		System.out.println("cri : "+cri);
		List qna_articlesList = qnaService.listArticles(cri);
		List tagList = qnaService.QtagList();
		//List pageList = qnaService.;
		
		ModelAndView mav = new ModelAndView(viewName); 
		
		mav.addObject("qna_articlesList", qna_articlesList);
		mav.addObject("tagList",tagList);
		mav.addObject("pageMaker",pageMaker);
		mav.addObject("Criteria",cri);
		
		System.out.println("qna_articlesList"+qna_articlesList);
		System.out.println("tagList"+tagList);
		System.out.println("pageMaker"+pageMaker);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(qnaService.listCount());
		
		return mav;
		
	}
	
	//주 게시판 글쓰기
	@Override
	@RequestMapping(value="qna/addNewArticle.do" ,method = {RequestMethod.POST})
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		//글정보 저장하기 위한 articleMap 생성
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		
		String tag = multipartRequest.getParameter("TOTALMEMBER");
		System.out.println("태그 불러오기 : "+ tag);
		articleMap.put("tag" ,tag);
		
		System.out.println(articleMap.get(tag));
		//글쓰기창에서 전송된 글 정보를 Map에 key/value로 저장
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
			
			System.out.println("맵에 저장되는 value : "+articleMap.get(value));
			System.out.println("맵에 저장되는 name : "+articleMap.get(name));
		}
		
		
		
		
		//이미지파일 업로드
		String IMAGEFILENAME= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		//회원id 불러오기
		String MEMBER_ID = memberVO.getMEMBER_ID();
		//회원ID, 부모글 번호를 articleMap에 저장
		articleMap.put("QNA_PARENTNO", 0);
		articleMap.put("MEMBER_ID", MEMBER_ID);
		articleMap.put("IMAGEFILENAME",IMAGEFILENAME);
		System.out.println("String QNA" +articleMap.get("QNA_TAG"));
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			//글정보가 저장된 articleMap을 Service 클래스의 addarticle()메서드로 전달
			int QNA_ARTICLENO = qnaService.addNewArticle(articleMap);
			
			if(IMAGEFILENAME!=null && IMAGEFILENAME.length()!=0) {
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+IMAGEFILENAME);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+QNA_ARTICLENO);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			
			}
			 
			
			//새글 추가 메시지
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/qna/listArticles.do'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+IMAGEFILENAME);
			srcFile.delete();

			//새글 추가 메시지 실패의 경우
			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/qna/articleForm.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	};
		
	
	//글쓰기 상세 창 
	@Override
	@RequestMapping(value="qna/viewArticle.do" ,method = {RequestMethod.GET})
	public ModelAndView viewArticle(@RequestParam("QNA_ARTICLENO") int QNA_ARTICLENO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = getViewName(request);
		qnaArticleVO=qnaService.viewArticle(QNA_ARTICLENO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", qnaArticleVO);
		return mav;
	}
	
	//글수정
	@RequestMapping(value="qna/modArticle.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  
	    HttpServletResponse response) throws Exception{
	    multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		String IMAGEFILENAME= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String MEMBER_ID = memberVO.getMEMBER_ID();
		articleMap.put("MEMBER_ID", MEMBER_ID);
		articleMap.put("IMAGEFILENAME", IMAGEFILENAME);
		
		String QNA_ARTICLENO=(String)articleMap.get("QNA_ARTICLENO");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    try {
	       qnaService.modArticle(articleMap);
	       if(IMAGEFILENAME!=null && IMAGEFILENAME.length()!=0) {
	    	   //새로 첨부한 파일을 폴더로 이동합니다.
	         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+IMAGEFILENAME);
	         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+QNA_ARTICLENO);
	         FileUtils.moveFileToDirectory(srcFile, destDir, true);
	         
	         //기존 파일 삭제
	         String originalFileName = (String)articleMap.get("originalFileName");
	         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+QNA_ARTICLENO+"\\"+originalFileName);
	         oldFile.delete();
	       }	
	       message = "<script>";
		   message += " alert('글을 수정했습니다.');";
		   message += " location.href='"+multipartRequest.getContextPath()+"/qna/viewArticle.do?QNA_ARTICLENO="+QNA_ARTICLENO+"';";
		   message +=" </script>";
	       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }catch(Exception e) {
	      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+IMAGEFILENAME);
	      srcFile.delete();
	      message = "<script>";
		  message += " alert('오류가 발생했습니다.다시 수정해주세요');";
		  message += " location.href='"+multipartRequest.getContextPath()+"/qna/viewArticle.do?QNA_ARTICLENO="+QNA_ARTICLENO+"';";
		  message +=" </script>";
	      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }
	    return resEnt;
	  }
	
	//삭제
	@Override
	@RequestMapping(value="qna/removeArticle.do" ,method = RequestMethod.POST)
	public ResponseEntity removeArticle(@RequestParam("QNA_ARTICLENO") int QNA_ARTICLENO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			qnaService.removeArticle(QNA_ARTICLENO);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+QNA_ARTICLENO);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='"+request.getContextPath()+"/qna/listArticles.do';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		       
		}catch(Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='"+request.getContextPath()+"/qna/listArticles.do';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    e.printStackTrace();
		}
		return resEnt;
	}
	
	
	
	
	//창을 나타냄.
		@RequestMapping(value = "qna/*Form.do", method =  {RequestMethod.POST, RequestMethod.GET})
		private ModelAndView form(@RequestParam(value= "result", required=false) String result,
			       HttpServletRequest request, 
			       HttpServletResponse response) throws Exception {

			//String viewName = (String)request.getAttribute("viewName");
			String viewName = getViewName(request);
			ModelAndView mav = new ModelAndView();
			List tagList = qnaService.QtagList();
			mav.addObject("tagList",tagList);
			mav.addObject("result",result);
			 //DB테그 리스트 바인딩 한 후 JSP 전달
			mav.setViewName(viewName); 
			
			return mav;
		}
	
	//한개 이미지 업로드하기
		private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			String imageFileName= null;
			Iterator<String> fileNames = multipartRequest.getFileNames();
			
			while(fileNames.hasNext()){
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);
				imageFileName=mFile.getOriginalFilename();
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
				if(mFile.getSize()!=0){ //File Null Check
					if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
						if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
								file.createNewFile(); //이후 파일 생성
						}
					}
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
				}
			}
			return imageFileName;
		}
	
	
	
	//뷰네임 받아오는 메서드
	private String getViewName(HttpServletRequest request) throws Exception {
	      String contextPath = request.getContextPath();
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if (uri == null || uri.trim().equals("")) {
	         uri = request.getRequestURI();
	      }

	      int begin = 0;
	      if (!((contextPath == null) || ("".equals(contextPath)))) {
	         begin = contextPath.length();
	      }

	      int end;
	      if (uri.indexOf(";") != -1) {
	         end = uri.indexOf(";");
	      } else if (uri.indexOf("?") != -1) {
	         end = uri.indexOf("?");
	      } else {
	         end = uri.length();
	      }

	      String viewName = uri.substring(begin, end);
	      if (viewName.indexOf(".") != -1) {
	         viewName = viewName.substring(0, viewName.lastIndexOf("."));
	      }
	      if (viewName.lastIndexOf("/") != -1) {
	         viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
	      }
	      return viewName;
	   }
}
