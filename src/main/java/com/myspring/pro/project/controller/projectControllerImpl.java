package com.myspring.pro.project.controller;

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
import com.myspring.pro.project.service.projectService;
import com.myspring.pro.project.vo.projectVO;
import com.myspring.pro.project.vo.tagVO;

import net.sf.json.JSONObject;



@Controller("projectController")
@EnableAspectJAutoProxy
public class projectControllerImpl   implements projectController {
	private static final String project_file = "C:\\project\\project_file";
	private static final Logger logger = LoggerFactory.getLogger(projectControllerImpl.class);
	@Autowired
	private projectService projectService;
	@Autowired
	private projectVO projectVO ;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private tagVO TagVO;
	//전체 프로젝트 조회
	@Override
	@RequestMapping(value="/project/listprojects.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listprojects(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
//		String viewName = (String)request.getAttribute("viewName");
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
	
	
	//프로젝트 추가(실)
	@Override
	@RequestMapping(value="/project/addproject.do" ,produces = "text/html; charset=utf8",
							method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseEntity addproject(MultipartHttpServletRequest multipartRequest,@RequestParam("selecttag") List<String> tag, 
	HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> projectMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			projectMap.put(name,value);
		}
		String PROJECT_FILENAME = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		projectMap.put("PROJECT_FILENAME",PROJECT_FILENAME);
		System.out.println("테그 없을때 :"+tag);
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		int PROJECT_CODE = projectService.addproject(projectMap,tag);
		responseHeaders.add("Content=Type","text/html; charset=UTF-8");
		try {
				//projectService.insertprojecttaglist(projectMap, tag);
			if(PROJECT_FILENAME!=null && PROJECT_FILENAME.length()!=0) {
				File srcFile = new 
				File(project_file+"\\"+"temp"+"\\"+PROJECT_FILENAME);
				System.out.println("파일 이름 다오 :"+PROJECT_FILENAME);
				File destDir = new File(project_file+"\\"+PROJECT_CODE);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}
			message = "<script>";
		    message += " alert('글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/project/listprojects.do'";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(project_file+"\\"+"temp"+"\\"+PROJECT_FILENAME);
			srcFile.delete();
			
			
			message = " <script>";
			   message += " alert('오료가 발생했습니다.');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/project/projectadd.do'";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		
		return resEnt;
		
	}
	
	
	//프로젝트 추가(이동)
	@Override
	@RequestMapping(value="/project/projectadd.do" ,method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView projectadd(@ModelAttribute("project") projectVO project,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView(viewName);
		List projectTagList = projectService.TagList();
		mav.addObject("projectTagList",projectTagList);

		return mav;
	}
	
	//수정하기
	 @RequestMapping(value="/project/updateproject.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity updateproject(MultipartHttpServletRequest multipartRequest,  
	    HttpServletResponse response) throws Exception{
	    multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> projectMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			projectMap.put(name,value);
		}
		
		String PROJECT_FILENAME= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO MemberVO = (MemberVO) session.getAttribute("member");
		
		String MEMBER_ID = MemberVO.getMEMBER_ID();
		projectMap.put("MEMBER_ID", MEMBER_ID);
		System.out.println("아아디"+projectMap.get("MEMBER_ID"));
		projectMap.put("PROJECT_FILENAME", PROJECT_FILENAME);
		
		String PROJECT_CODE=(String)projectMap.get("PROJECT_CODE");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		projectService.modproject(projectMap);
	    try {
	    	System.out.println("수정확인");
		

	       if(PROJECT_FILENAME!=null && PROJECT_FILENAME.length()!=0) {
	         File srcFile = new File(project_file+"\\"+"temp"+"\\"+PROJECT_FILENAME);
	         File destDir = new File(project_file+"\\"+PROJECT_CODE);
	         FileUtils.moveFileToDirectory(srcFile, destDir, true);
	         
	         String originalFileName = (String)projectMap.get("originalFileName");
	         File oldFile = new File(project_file+"\\"+PROJECT_CODE+"\\"+originalFileName);
	         oldFile.delete();
	       }	
	       message = "<script>";
		   message += " alert('글을 수정했습니다.');";
		   message += " location.href='"+multipartRequest.getContextPath()+"/project/projectDetail.do?PROJECT_CODE="+PROJECT_CODE+"'";
		   message +=" </script>";
	       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }catch(Exception e) {
	    	System.out.println("수정안됨");
	      File srcFile = new File(project_file+"\\"+"temp"+"\\"+PROJECT_FILENAME);
	      srcFile.delete();
	      message = "<script>";
		  message += " alert('오류가 발생했습니다.다시 수정해주세요');";
		  message += " location.href='"+multipartRequest.getContextPath()+"/project/projectDetail.do?PROJECT_CODE="+PROJECT_CODE+"'";
		  message +=" </script>";
	      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }
	    return resEnt;
	  }
	 
	 //프로젝트 테그 추가
	 @Override
	 @RequestMapping(value="/project/addprojecttag.do" ,method = {RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody String addtag(@RequestParam("PROJECT_CODE") int PROJECT_CODE,HttpServletRequest request,
				HttpServletResponse response)
				throws Exception {
		 request.setCharacterEncoding("UTF-8");
			Map<String,Object> projectMap = new HashMap<String, Object>();
			Enumeration enu=request.getParameterNames();
			while(enu.hasMoreElements()){
				String name=(String)enu.nextElement();
				System.out.println("이름 :"+name);
				String value=request.getParameter(name);
				System.out.println("값 :"+value);
				projectMap.put(name,value);
			}
			String abc = null;
			boolean  overtag =projectService.overlappedTag(projectMap, PROJECT_CODE);
			System.out.println("overtag : " + overtag);
			if(!overtag) {
				abc= "a중복된 테그입니다.";
			}else if(overtag) {
				projectService.addprojecttag(projectMap,PROJECT_CODE);
				abc="b추가 되었습니다.";
			}

		System.out.println("프로젝트 코드 : "+projectMap.get("PROJECT_CODE"));	
		System.out.println("프로젝트 테그  :"+projectMap.get("PROJECT_TAG"));
		System.out.println("프로젝트 테그  :"+projectMap.get("PROJECT_TITTLE"));
		return abc;
		}
	 @Override
	 
		public String memberprojectadd(@RequestParam("PROJECT_CODE") int PROJECT_CODE, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		 request.setCharacterEncoding("UTF-8");
			Map<String,Object> projectMap = new HashMap<String, Object>();
			Enumeration enu=request.getParameterNames();
			while(enu.hasMoreElements()){
				String name=(String)enu.nextElement();
				System.out.println("이름 :"+name);
				String value=request.getParameter(name);
				System.out.println("값 :"+value);
				projectMap.put(name,value);
			}
			String abc = null;
			
			
			return null;
		}
	 
	 //프로젝트 테그 삭제
	@Override
	 @RequestMapping(value="/project/removetag.do" ,method = {RequestMethod.POST,RequestMethod.GET})
	  public ModelAndView removetag(@RequestParam("PROJECT_CODE") int PROJECT_CODE, @RequestParam("PROJECT_TAG") String tag,  
			  HttpServletRequest request, HttpServletResponse response) throws Exception{
		 System.out.println("코드 : "+PROJECT_CODE+"  테그 :"+tag);
		 projectService.removetag(PROJECT_CODE, tag);
		 ModelAndView mav = new ModelAndView("redirect:/project/projectDetail.do?PROJECT_CODE="+PROJECT_CODE);
		 return mav;
	 }
	
	//삭제하기
	@Override
	   @RequestMapping(value="/project/removeproject.do" ,method = RequestMethod.GET)
	   public ModelAndView removeproject(@RequestParam("PROJECT_CODE") int PROJECT_CODE, 
	                    HttpServletRequest request, HttpServletResponse response) throws Exception{
	      request.setCharacterEncoding("utf-8");
	      projectService.removeproject(PROJECT_CODE);
	      ModelAndView mav = new ModelAndView("redirect:/project/listprojects.do");
	      return mav;
	   }
	
	@Override
	@RequestMapping(value="/project/projectDetail.do" ,method = RequestMethod.GET)
	public ModelAndView projectDetail(@RequestParam("PROJECT_CODE") int PROJECT_CODE,
							//	@ModelAttribute("proejcet") projectVO projectVO,
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName = getViewName(request);
		System.out.println("확인");
		System.out.println("코드 :"+PROJECT_CODE);
		projectVO project = projectService.viewproject(PROJECT_CODE); 
		System.out.println("파일 이름 디테일 : "+project.getPROJECT_FILENAME()); 
		List<projectVO> MytagList = projectService.projectTagList(PROJECT_CODE);
		List projectTagList = projectService.TagList();
		System.out.println("테그리스트~"+MytagList);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("project",project);
		mav.addObject("MytagList",MytagList);
		mav.addObject("projectTagList",projectTagList);
		return mav;
	}
	
	/* @RequestMapping(value="/project/search.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
	public @ResponseBody String  search(@RequestParam("keyword") String keyword,
			                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//System.out.println(keyword);
		if(keyword == null || keyword.equals(""))
		   return null ;
	
		keyword = keyword.toUpperCase();
	    List<String> keywordList =projectService.search(keyword);
	    
	 // ���� �ϼ��� JSONObject ����(��ü)
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		 		
	    String jsonInfo = jsonObject.toString();
	   // System.out.println(jsonInfo);
	    return jsonInfo ;
	}*/
	

		@RequestMapping(value="/project/searchprojects.do" ,method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView searchprojects(@RequestParam("searchWord") String searchWord,@RequestParam("selecttag") List<String> tag,
				                       HttpServletRequest request, HttpServletResponse response) throws Exception{
			String viewName = getViewName(request);
			if(tag ==null) {
				tag.add("null");
			}
			System.out.println("키워드"+searchWord);
			System.out.println("tag : "+tag.size());
			logger.info("viewName: "+ viewName);
			logger.debug("viewName: "+ viewName);
			List<String> projectsList=projectService.searchprojects(searchWord,tag);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("projectsList",projectsList);
			return mav;
			
		}
	

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
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
	//한개 이미지 업로드하기
	
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			String projectFileName= null;
			Iterator<String> fileNames = multipartRequest.getFileNames();
				
			while(fileNames.hasNext()){
				String PROJECT_FILENAME = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(PROJECT_FILENAME);
				projectFileName=mFile.getOriginalFilename();
				File file = new File(project_file +"\\"+ PROJECT_FILENAME);
				if(mFile.getSize()!=0){ //File Null Check
					if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
						if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
								file.createNewFile(); //이후 파일 생성
						}
					}
					mFile.transferTo(new File(project_file +"\\"+"temp"+ "\\"+projectFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
				}
			}
			return projectFileName;
		}

	

	

	

	

	}

	






