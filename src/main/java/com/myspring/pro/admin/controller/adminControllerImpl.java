package com.myspring.pro.admin.controller;

import java.io.File;
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
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.member.vo.MemberVO;
import com.myspring.pro.project.service.projectService;
import com.myspring.pro.admin.service.adminService;
import com.myspring.pro.admin.vo.adminVO;
import com.myspring.pro.project.vo.projectVO;
import com.myspring.pro.project.vo.tagVO;
@Controller("adminController")
@RequestMapping(value="/admin")
@EnableAspectJAutoProxy
//@Repository 
public class adminControllerImpl implements adminController {
	@Autowired
	private projectService projectService;
	@Autowired
	private adminService adminService;
	@Autowired
	private tagVO TagVO;
	@Autowired
	private adminVO adminVO;
	private static final String admin_file = "C:\\admin\\admin_file";
	private static final Logger logger = LoggerFactory.getLogger(adminControllerImpl.class);
	@Override
	@RequestMapping(value="/listprojects.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listprojects(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		//String viewName = (String)request.getAttribute("viewName");
		//System.out.println("viewName: " +viewName);
		logger.info("viewName: "+ viewName);
		logger.debug("viewName: "+ viewName);
		List projectsList = projectService.listprojects();
		List projectTagList = projectService.TagList();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("projectsList", projectsList);
		mav.addObject("projectTagList",projectTagList);
		return mav;
	}
	
	//테그추가
	@Override
	@RequestMapping(value="/addtag.do" ,method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html; charset=utf8")
	public @ResponseBody String tag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> adminMap = new HashMap<String, Object>();
		Enumeration enu=request.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			System.out.println("name : "+name);
			String value=request.getParameter(name);
			System.out.println("value : "+value);
			adminMap.put(name,value);
		}
		
		boolean over= adminService.overtag(adminMap);
		
		if(over) {
			adminService.admintag(adminMap);
			return "a 정상적으로 추가되었습니다.";
		}else if(!over) {
			return "이미 있는 테그입니다.";
		}
		
		
		
		return "알수없는 오류";
	}
	
	//테그 삭제
	@Override
	   @RequestMapping(value="/removetag.do" ,method = RequestMethod.GET,produces = "text/html; charset=utf8")
	   public ModelAndView removetag(@RequestParam ("TAG_THIRD")String tag ,HttpServletRequest request, HttpServletResponse response) throws Exception{
	      request.setCharacterEncoding("utf-8");
	      adminService.removertag(tag);
	      ModelAndView mav = new ModelAndView("redirect:/admin/admintag.do");
	      return mav;
	   }
	
	@RequestMapping(value = "/adminloginForm.do", method =  RequestMethod.GET)
	// 로그인창 요청 시 매개변수 result가 전송되면 변수 result에 값을 저장.
	// 최초 로그인창을 요청할 때는 매개변수 result가 전송되지 않으므로 무시한다.
	private String adminloginform(@RequestParam(value= "result", required=false) String result,
	       HttpServletRequest request, 
	       HttpServletResponse response) throws Exception {
	//String viewName = getViewName(request); // 컨트롤러에 있는 getViewName()메서드 (필터 사용전)
	
	// 인터셉터에서 바인딩 된 뷰 이름을 가져온다.
	String viewName = (String)request.getAttribute("viewName");
	System.out.println("view 이름 : " + viewName);
	ModelAndView mav = new ModelAndView();
	
	mav.addObject("result",result);
	System.out.println(result);
	mav.setViewName(viewName);
	
	// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
	return viewName;
	}
	
	
	// 로그인
		@Override
		@RequestMapping(value = "/login.do", method = RequestMethod.POST)
		                         //로그인 창에서 전송된 ID,비밀번호를 MemberVO 객체인 member에 저장.
		public ModelAndView login(@ModelAttribute("adminVO") adminVO adminVO,
				//RedirectAttributes클래스를 이용해 로그인 실패 시 다시 로그인창으로 리다이렉트하여 실패 메시지 전달!
					              RedirectAttributes rAttr,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			ModelAndView mav = new ModelAndView();
			System.out.println("로그인() ID :"+adminVO.getADMIN_ID());
			System.out.println("로그인() PW :"+adminVO.getADMIN_PW());
			// login()메서드를 호출하면서 로그인 정보를 전달.
			//
			adminVO = adminService.login(adminVO);
		
			
			if(adminVO != null) {
					HttpSession session = request.getSession();
				    
				    // 세션에 회원 정보를 저장.
					session=request.getSession();
				    
				    // 세션에 로그인 상태를 true로 설정.
					// 세션 정보를 "memberInfo 변수에" memberVO 속성으로 저장.
				    session.setAttribute("isLogOn", true);
				    session.setAttribute("admin", adminVO);
				    
				    // memberVO로 반환된 값이 있으면 세션을 이용해 로그인 상태를 true로 한다.
				    mav.setViewName("redirect:/project/main.do");
				    
			}else {
					// 로그인 실패시 실패 메시지를 로그인 창으로 전달.
				    rAttr.addAttribute("result","loginFailed");
				    
				    // 로그인 실패시 다시 로그인 창으로 리다이렉트.
				    mav.setViewName("redirect:/admin/adminloginForm.do");
			}
			return mav;// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
			}
		
		@Override
		@RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
		public ModelAndView logout(HttpServletRequest request, 
								   HttpServletResponse response) throws Exception {
			HttpSession session=request.getSession();
			
			// 로그아웃 요청 시 세션에 저장된 로그인 정보와 회원 정보를 삭제
			session.setAttribute("isLogOn", false);
			session.removeAttribute("admin");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/project/main.do");
			
			// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
			return mav;
		}
		//공지사항 작성(실)
		@Override					
		@RequestMapping(value = "/addnotice.do", method = { RequestMethod.POST,
				RequestMethod.GET }, produces = "text/html; charset=utf8")
		@ResponseBody
		public ResponseEntity addnotice(MultipartHttpServletRequest multipartRequest,
				 HttpServletResponse response) throws Exception {
			multipartRequest.setCharacterEncoding("utf-8");
			Map<String, Object> adminMap = new HashMap<String, Object>();
			Enumeration enu = multipartRequest.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = (String) enu.nextElement();
				String value = multipartRequest.getParameter(name);
				adminMap.put(name, value);
			}
			String NOTICE_FILENAME = upload(multipartRequest);
			HttpSession session = multipartRequest.getSession();
			adminMap.put("NOTICE_FILENAME",NOTICE_FILENAME);
			String message;
			ResponseEntity resEnt = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content=Type", "text/html; charset=utf-8");
			try {
				int NOTICE_CODE = adminService.addt_notice(adminMap);
				if (NOTICE_FILENAME != null && NOTICE_FILENAME.length() != 0) {
					File srcFile = new File(admin_file + "\\" + "temp" + "\\" + NOTICE_FILENAME);
					File destDir = new File(admin_file + "\\" + NOTICE_CODE);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				message = "<script>";
				message += " alert('공지사항을 추가했습니다.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/admin/listnotice.do'";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			} catch (Exception e) {
				File srcFile = new File(admin_file + "\\" + "temp" + "\\" + NOTICE_FILENAME);
				srcFile.delete();
				message = " <script>";
				message += " alert('오류가 발생했습니다.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/admin/addnotice.do'";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				e.printStackTrace();
			}

			return resEnt;

		}
	
		// 파일 업로드하기
		public String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
			String projectFileName = null;
			Iterator<String> fileNames = multipartRequest.getFileNames();

			while (fileNames.hasNext()) {
				String PROJECT_FILENAME = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(PROJECT_FILENAME);
				projectFileName = mFile.getOriginalFilename();
				File file = new File(admin_file + "\\" + PROJECT_FILENAME);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
						if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
							file.createNewFile(); // 이후 파일 생성
						}
					}
					mFile.transferTo(new File(admin_file + "\\" + "temp" + "\\" + projectFileName)); // 임시로 저장된
																										// multipartFile을 실제
																										// 파일로 전송
				}
			}
			return projectFileName;
		}
		//공자사항 리스트
		@Override
		@RequestMapping(value="/listnotice.do" ,method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView listnotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			String viewName = (String)request.getAttribute("viewName");
			
			List noticeList = adminService.t_noticeList();
			System.out.println("ㅁㄴㅇㄴㅁ : "+noticeList);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("noticeList",noticeList);
			return mav;
		}
		//공지사항 작성(이동)
		@Override
		@RequestMapping(value = "/noticeadd.do", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView noticeadd( HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			String viewName = (String) request.getAttribute("viewName");
			ModelAndView mav = new ModelAndView(viewName);
			return mav;
		}
		//공지사항 상세보기
		@Override
		@RequestMapping(value = "/noticeDetail.do", method = RequestMethod.GET)
		public ModelAndView noticeDetail(@RequestParam("NOTICE_CODE") int NOTICE_CODE, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			String viewName = (String) request.getAttribute("viewName");
			adminVO notice = adminService.viewproject(NOTICE_CODE);

			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("notice", notice);

			return mav;
		}
		//공지사항 수정
		@RequestMapping(value = "/updatenotice.do", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity updatenotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
				throws Exception {
			multipartRequest.setCharacterEncoding("utf-8");
			Map<String, Object> noticeMap = new HashMap<String, Object>();
			Enumeration enu = multipartRequest.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = (String) enu.nextElement();
				System.out.println("name :" +name);
				String value = multipartRequest.getParameter(name);
				System.out.println("value :" +value);
				noticeMap.put(name, value);
			}

			String NOTICE_FILENAME = upload(multipartRequest);
			HttpSession session = multipartRequest.getSession();
			/*
			 * MemberVO MemberVO = (MemberVO) session.getAttribute("member");
			 * System.out.println("파일 이름 : " + NOTICE_FILENAME); String MEMBER_ID =
			 * MemberVO.getMEMBER_ID(); noticeMap.put("MEMBER_ID", MEMBER_ID);
			 * System.out.println("아아디" + noticeMap.get("MEMBER_ID"));
			 */
			noticeMap.put("NOTICE_FILENAME", NOTICE_FILENAME);
			System.out.println("원래 파일 이름 :" + (String) noticeMap.get("originalFileName"));
			String NOTICE_CODE = (String) noticeMap.get("NOTICE_CODE");
			String message;
			System.out.println("프로젝트 코드" + noticeMap.get("NOTICE_CODE"));
			System.out.println("여기여기여기2");
			ResponseEntity resEnt = null;
			System.out.println("여기여기여기3");
			HttpHeaders responseHeaders = new HttpHeaders();
			System.out.println("여기여기여기4");
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			System.out.println("여기여기여기1");
			adminService.updatenotice(noticeMap);
			System.out.println("여기여기여기0");
			try {
				System.out.println("여기여기여기1");
				if (NOTICE_FILENAME != null && NOTICE_FILENAME.length() != 0) {
					File srcFile = new File(admin_file + "\\" + "temp" + "\\" + NOTICE_FILENAME);
					File destDir = new File(admin_file + "\\" + NOTICE_CODE);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					System.out.println("여기여기여기2");
					String originalFileName = (String) noticeMap.get("originalFileName");
					if(originalFileName!=null) {
					System.out.println("올드 파일이름" + originalFileName);
					File oldFile = new File(admin_file + "\\" + NOTICE_CODE + "\\" + originalFileName);
					oldFile.delete();
					}
					System.out.println("여기여기여기3");
				}
				message = "<script>";
				message += " alert('글을 수정했습니다.');";
				message += " location.href='" + multipartRequest.getContextPath()
						+ "/admin/noticeDetail.do?NOTICE_CODE=" + NOTICE_CODE + "'";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			} catch (Exception e) {
				System.out.println("수정안됨");
				File srcFile = new File(admin_file + "\\" + "temp" + "\\" + NOTICE_FILENAME);
				srcFile.delete();
				message = "<script>";
				message += " alert('오류가 발생했습니다.다시 수정해주세요');";
				message += " location.href='" + multipartRequest.getContextPath()
						+ "/admin/noticeDetail.do?NOTICE_CODE=" + NOTICE_CODE + "'";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			}
			return resEnt;
		}
		//첨부 파일 삭제 할때 파일선택누르고 취소 누르면 이기능을 함
		@Override
		@RequestMapping(value = "/removenoticefile.do", method = { RequestMethod.POST,
				RequestMethod.GET }, produces = "text/html; charset=utf8")
		public ModelAndView removenoticefile(@RequestParam("NOTICE_CODE") int NOTICE_CODE,
				@RequestParam("originalFileName") String originalFileName,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			adminService.removenoticefile(NOTICE_CODE);
			if(originalFileName!=null) {
				System.out.println("올드 파일이름" + originalFileName);
				File oldFile = new File(admin_file + "\\" + NOTICE_CODE + "\\" + originalFileName);
				oldFile.delete();
				}
			ModelAndView mav = new ModelAndView("redirect:/admin/noticeDetail.do?NOTICE_CODE=" + NOTICE_CODE);
			return mav;
		}

		@Override
		@RequestMapping(value = "/admintag.do", method = { RequestMethod.POST,
				RequestMethod.GET }, produces = "text/html; charset=utf8")
		public ModelAndView admintag(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List projectTagList = projectService.TagList();
			String viewName = (String)request.getAttribute("viewName");
			ModelAndView mav = new ModelAndView(viewName);

			mav.addObject("projectTagList",projectTagList);
			return mav;
		}

		@Override
		@RequestMapping(value = "/removenotice.do", method = { RequestMethod.POST,
				RequestMethod.GET }, produces = "text/html; charset=utf8")
		public ModelAndView removenotice(int NOTICE_CODE, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			adminService.removenotice(NOTICE_CODE);
			ModelAndView mav = new ModelAndView("redirect:/admin/listnotice.do");
			return mav;
		}
	
	

}
