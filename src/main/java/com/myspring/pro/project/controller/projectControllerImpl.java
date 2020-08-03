package com.myspring.pro.project.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.myspring.pro.page.Criteria;
import com.myspring.pro.page.PageMaker;
import com.myspring.pro.project.service.projectService;
import com.myspring.pro.project.vo.projectVO;
import com.myspring.pro.project.vo.tagVO;
/*import com.myspring.pro.admin.controller.adminController;
import com.myspring.pro.admin.service.adminService;
import com.myspring.pro.admin.dao.adminDAO;*/

import net.sf.json.JSONObject;

@Controller("projectController")
@EnableAspectJAutoProxy
public class projectControllerImpl implements projectController {
	private static final String project_file = "C:\\project\\project_file";
	private static final Logger logger = LoggerFactory.getLogger(projectControllerImpl.class);
	/*
	 * @Autowired private adminController adminController;
	 * 
	 * @Autowired private adminService adminService;
	 * 
	 * @Autowired private adminDAO adminDAO;
	 */
	@Autowired
	private projectService projectService;
	@Autowired
	private projectVO projectVO;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private tagVO TagVO;
	//메인 프로젝트 리스트
	@Override
	@RequestMapping(value = "/project/main.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainprojectList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		List ALLprojecttagList = projectService.ALLprojecttagList();
		List projectsList = projectService.listprojects();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("projectsList", projectsList);
		mav.addObject("ALLprojecttagList", ALLprojecttagList);

		return mav;
	}

	// 전체 프로젝트 조회
	@Override
	@RequestMapping(value = "/project/listprojects.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listprojects(/* String TAG_FIRST, */PageMaker pageMaker, Criteria cri,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		List ALLprojecttagList = projectService.ALLprojecttagList();
		String TAG_FIRST ="a";
		List tag_secondlist =projectService.tag_second(TAG_FIRST);
		List projectsList = projectService.listprojects();
		List tag_first = projectService.tag_firstList();
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.addObject("tag_secondlist", tag_secondlist);
		mav.addObject("projectsList", projectsList);
		mav.addObject("tag_first", tag_first);
		mav.addObject("ALLprojecttagList", ALLprojecttagList);
		mav.addObject("PageMaker", pageMaker);
		mav.addObject("Criteria", cri);
		/*
		 * List tag_secondlist = projectService.tag_second(TAG_FIRST);
		 * mav.addObject("tag_secondlist", tag_secondlist); System.out.println("리스트 :" +
		 * tag_secondlist); List tag_thirdList = projectService.tag_third(TAG_SECOND);
		 * mav.addObject("tag_thirdList", tag_thirdList);
		 */

		return mav;
	}
	/*
	  //테그관련
	  @Override 
	  @RequestMapping(value="/project/tagtag.do" ,method = {RequestMethod.GET,RequestMethod.POST}) 
	  public ModelAndView tagtag(String TAG_FIRST,String TAG_SECOND, HttpServletRequest request, HttpServletResponse response) 
			  throws Exception { 
		  	System.out.println("퍼스트" + TAG_FIRST); 
			  List tag_secondlist =projectService.tag_second(TAG_FIRST);
			  List tag_thirdList = projectService.tag_third(TAG_SECOND);
			  ModelAndView mav = new ModelAndView("redirect:/project/listprojects.do");
			  HttpSession session = request.getSession();
			  session.setAttribute("tag_secondlist", tag_secondlist);
			  session.setAttribute("tag_thirdList", tag_thirdList);
			  return mav;
	  }
	 */

	// 프로젝트 추가(실)
	@Override
	@RequestMapping(value = "/project/addproject.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/html; charset=utf8")
	@ResponseBody
	public ResponseEntity addproject(MultipartHttpServletRequest multipartRequest,
			@RequestParam("selecttag") List<String> tag, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			projectMap.put(name, value);
		}
		String PROJECT_FILENAME = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		projectMap.put("PROJECT_FILENAME", PROJECT_FILENAME);
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content=Type", "text/html; charset=utf-8");
		try {
			int PROJECT_CODE = projectService.addproject(projectMap, tag);
			if (PROJECT_FILENAME != null && PROJECT_FILENAME.length() != 0) {
				File srcFile = new File(project_file + "\\" + "temp" + "\\" + PROJECT_FILENAME);
				File destDir = new File(project_file + "\\" + PROJECT_CODE);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/project/listprojects.do'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(project_file + "\\" + "temp" + "\\" + PROJECT_FILENAME);
			srcFile.delete();
			message = " <script>";
			message += " alert('오료가 발생했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/project/projectadd.do'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}

		return resEnt;

	}

	// 프로젝트 추가(이동)
	@Override
	@RequestMapping(value = "/project/projectadd.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView projectadd(@ModelAttribute("project") projectVO project, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List projectTagList = projectService.TagList();
		mav.addObject("projectTagList", projectTagList);

		return mav;
	}

	// 삭제하기
	@Override
	@RequestMapping(value = "/project/removeproject.do", method = RequestMethod.GET)
	public ModelAndView removeproject(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		projectService.removeproject(PROJECT_CODE);
		ModelAndView mav = new ModelAndView("redirect:/project/listprojects.do");
		return mav;
	}

	// 프로젝트 수정하기
	@RequestMapping(value = "/project/updateproject.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateproject(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			projectMap.put(name, value);
		}

		String PROJECT_FILENAME = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO MemberVO = (MemberVO) session.getAttribute("member");
		System.out.println("파일 이름 : " + PROJECT_FILENAME);
		String MEMBER_ID = MemberVO.getMEMBER_ID();
		projectMap.put("MEMBER_ID", MEMBER_ID);
		System.out.println("아아디" + projectMap.get("MEMBER_ID"));
		projectMap.put("PROJECT_FILENAME", PROJECT_FILENAME);
		System.out.println("원래 파일 이름 :" + (String) projectMap.get("originalFileName"));
		String PROJECT_CODE = (String) projectMap.get("PROJECT_CODE");
		String message;
		System.out.println("프로젝트 코드" + projectMap.get("PROJECT_CODE"));
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		projectService.modproject(projectMap);
		try {
			if (PROJECT_FILENAME != null && PROJECT_FILENAME.length() != 0) {
				File srcFile = new File(project_file + "\\" + "temp" + "\\" + PROJECT_FILENAME);
				File destDir = new File(project_file + "\\" + PROJECT_CODE);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String) projectMap.get("originalFileName");
				if(originalFileName!=null) {
				System.out.println("올드 파일이름" + originalFileName);
				File oldFile = new File(project_file + "\\" + PROJECT_CODE + "\\" + originalFileName);
				oldFile.delete();
				}
			}
			message = "<script>";
			message += " alert('글을 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath()
					+ "/project/projectDetail.do?PROJECT_CODE=" + PROJECT_CODE + "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("수정안됨");
			File srcFile = new File(project_file + "\\" + "temp" + "\\" + PROJECT_FILENAME);
			srcFile.delete();
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 수정해주세요');";
			message += " location.href='" + multipartRequest.getContextPath()
					+ "/project/projectDetail.do?PROJECT_CODE=" + PROJECT_CODE + "'";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}
	//프로젝트 파일 삭제 
	@Override
	@RequestMapping(value = "/project/removefile.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/html; charset=utf8")
	public ModelAndView removefile(@RequestParam("PROJECT_CODE") int PROJECT_CODE,
			@RequestParam("originalFileName") String originalFileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		projectService.removefile(PROJECT_CODE);
		if(originalFileName!=null) {
			System.out.println("올드 파일이름" + originalFileName);
			File oldFile = new File(project_file + "\\" + PROJECT_CODE + "\\" + originalFileName);
			oldFile.delete();
			}
		ModelAndView mav = new ModelAndView("redirect:/project/projectDetail.do?PROJECT_CODE=" + PROJECT_CODE);
		return mav;
	}
	

	// 프로젝트 상세보기
	@Override
	@RequestMapping(value = "/project/projectDetail.do", method = RequestMethod.GET)
	public ModelAndView projectDetail(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		projectVO project = projectService.viewproject(PROJECT_CODE);
		List<projectVO> MytagList = projectService.projectTagList(PROJECT_CODE);
		List<projectVO> memberproject = projectService.memberprojectlist(PROJECT_CODE);
		List projectTagList = projectService.TagList();

		System.out.println("memberLis" + memberproject);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("project", project);
		mav.addObject("MytagList", MytagList);
		mav.addObject("projectTagList", projectTagList);
		mav.addObject("memberproject", memberproject);
		return mav;
	}

	// 페이징
	@Override
	@RequestMapping(value = "/project/pageing.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/html; charset=utf8")
	public ModelAndView pageing(@RequestParam("page") String page, PageMaker pageMaker,
			com.myspring.pro.page.Criteria cri, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*
		 * ModelAndView mav = new ModelAndView(viewName);
		 * mav.addObject("PageMaker",pageMaker); mav.addObject("Criteria",cri);
		 */
		return null;
	}

	// 프로젝트 테그 추가
	@Override
	@RequestMapping(value = "/project/addprojecttag.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/html; charset=utf8")
	public @ResponseBody String addtag(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			projectMap.put(name, value);
		}
		String abc = null;
		boolean overtag = projectService.overlappedTag(projectMap, PROJECT_CODE);
		System.out.println("overtag : " + overtag);
		if (!overtag) {
			abc = "중복된 테그입니다.";
		} else if (overtag) {
			projectService.addprojecttag(projectMap, PROJECT_CODE);
			abc = "추가 되었습니다.";
		}
		return abc;
	}
	
	

	// 프로젝트 테그 삭제
	@Override
	@RequestMapping(value = "/project/removetag.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/html; charset=utf8")
	public ModelAndView removetag(@RequestParam("PROJECT_CODE") int PROJECT_CODE,
			@RequestParam("PROJECT_TAG") String tag, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		projectService.removetag(PROJECT_CODE, tag);
		ModelAndView mav = new ModelAndView("redirect:/project/projectDetail.do?PROJECT_CODE=" + PROJECT_CODE);
		return mav;
	}

	// 프로젝트 신청 및 찜하기
	@Override
	@RequestMapping(value = "/project/memberprojectadd.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/html; charset=utf8")
	public String memberprojectadd(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			projectMap.put(name, value);
		}
		String abc = null;
		String overmemberproject = projectService.overlappedMemberproject(projectMap, PROJECT_CODE);

		if (!(overmemberproject == null)) {
			abc = "이미 " + overmemberproject + "등록이 되어있습니다.";
			if (projectMap.get("APPLY_CK").equals("신청중") && overmemberproject.equals("관심")) {
				projectService.updateapply_ck(projectMap, PROJECT_CODE);
				abc = "정상적으로 신청되었습니다.";
			}
		} else if ((overmemberproject == null)) {
			projectService.addmemberproject(projectMap, PROJECT_CODE);
			if (projectMap.get("APPLY_CK").equals("신청중")) {
				abc = "정상적으로 신청되었습니다.";
			} else if (projectMap.get("APPLY_CK").equals("관심")) {
				abc = "정상적으로 관심등록이 되었습니다.";
			}
		}

		return abc;
	}

	// 합격 유무
	@Override
	@RequestMapping(value = "/project/memberpass.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html; charset=utf8")
	public String memberpass_ck(int PROJECT_CODE, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			System.out.println("이름 : " + name);
			projectMap.put(name, value);
		}
		System.out.println("pass con : " + projectMap.get("PASS_CK"));
		projectService.memberpass_ck(projectMap, PROJECT_CODE);

		return null;
	}

	// 프로젝트 검색
	@RequestMapping(value = "/project/searchprojects.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchprojects(@RequestParam("searchWord") String searchWord,
			@RequestParam("selecttag") List<String> tag, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		if (tag == null) {
			tag.add("null");
		}
		List<String> projectsList = projectService.searchprojects(searchWord, tag);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("projectsList", projectsList);
		return mav;

	}

	/*
	 * private String getViewName(HttpServletRequest request) throws Exception {
	 * String contextPath = request.getContextPath(); String uri = (String)
	 * request.getAttribute("javax.servlet.include.request_uri"); if (uri == null ||
	 * uri.trim().equals("")) { uri = request.getRequestURI(); }
	 * 
	 * int begin = 0; if (!((contextPath == null) || ("".equals(contextPath)))) {
	 * begin = contextPath.length(); }
	 * 
	 * int end; if (uri.indexOf(";") != -1) { end = uri.indexOf(";"); } else if
	 * (uri.indexOf("?") != -1) { end = uri.indexOf("?"); } else { end =
	 * uri.length(); }
	 * 
	 * String viewName = uri.substring(begin, end); if (viewName.indexOf(".") != -1)
	 * { viewName = viewName.substring(0, viewName.lastIndexOf(".")); } if
	 * (viewName.lastIndexOf("/") != -1) { viewName =
	 * viewName.substring(viewName.lastIndexOf("/", 1), viewName.length()); } return
	 * viewName; }
	 */
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

			@Override
			@RequestMapping(value = "/project/tagList.do", method = { RequestMethod.GET, RequestMethod.POST })
			public void tagList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				
			}

		

}
