package com.myspring.pro.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro.member.controller.MemberController;
import com.myspring.pro.member.controller.MemberControllerImpl;
import com.myspring.pro.member.service.MemberService;
import com.myspring.pro.member.vo.MemberVO;

import com.myspring.pro.project.controller.projectControllerImpl;
import com.myspring.pro.project.service.projectService;
import com.myspring.pro.project.dao.projectDAO;
import com.myspring.pro.project.vo.tagVO;

import com.myspring.pro.mypage.service.MyPageService;
import com.myspring.pro.mypage.dao.MyPageDAO;
import com.myspring.pro.mypage.vo.MyPageVO;


/*
  헷갈리는 부분
  DB에 저장하는것과 VO에 저장하는것을 구분해서 생각!
  
  예를들어, 지금 MemberVO에 TAG(해쉬태그)가 포함되어있다.
  MemberVO는 DB에 T_PROJECT_MEMBER 칼럼을 모두 포함하고있고
  TAG(해쉬태그) 속성은 PROJECTVO에 있는것이다.
  
  회원가입을 할때 MemberVO의 모든 값 + TAG값을 넣어도 무방하다.
  
  DB와 VO는 별개.
  
  
 */
/*
  # 실행순서
  
   브라우저 요청 (ex)127.0.0.1:8090/pro/member/xxxx.do 
   -> 인터셉터(인터셉터에서 .do를 제외한 뷰이름을 request에 바인딩) -> 컨트롤러
 
 */

@Controller("mypageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl implements MyPageController{
	private static final Logger logger = 
						LoggerFactory.getLogger(MyPageControllerImpl.class);
	
	// Autowired  빈설정을 자동으로 해주는 것인데
	// 하나씩 다 해줘야한다 xx 이것때문에 오늘 몇시간을 날렸다.
	@Autowired
	private MemberService memberService;
	@Autowired
	private projectService projectService;
	@Autowired
	private projectDAO projectDAO;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private tagVO tagVO;
	@Autowired
	private MyPageService myPageService;
	@Autowired
	private MyPageVO myPageVO;
	
	// 회원 조회
		@Override
		@RequestMapping(value="/myProject.do" ,method = RequestMethod.GET)
		public ModelAndView myProject(		HttpServletRequest request, 
											HttpServletResponse response) 
													throws Exception {
			
			// 인터셉터에서 바인딩 된 뷰 이름을 가져온다.
			String viewName = (String)request.getAttribute("viewName");
			
			// 세션
			HttpSession session=request.getSession();		
			memberVO= (MemberVO)session.getAttribute("memberInfo");
			
			String MEMBER_ID = memberVO.getMEMBER_ID();
				
			List myProjectList = myPageService.selectMyProjectList(MEMBER_ID);
			// viewName이 tiles_member.xml의 <definition>태그에 설정한 뷰이름과 일치한다.
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("myProjectList", myProjectList);
			// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
			return mav;
		}
	
	// 회원수정 시 태그 삽입
		@Override
		@RequestMapping(value="/addTag.do" ,method = RequestMethod.POST)
		public @ResponseBody String addTag(@RequestParam("MEMBER_ID") String MEMBER_ID,
									 @RequestParam("TAG_THIRD") String TAG_THIRD,
									HttpServletRequest request,
									HttpServletResponse response) throws Exception{
			
			// 로그인후 생성된 세션을 가져온다
			HttpSession session=request.getSession();		
			memberVO= (MemberVO)session.getAttribute("memberInfo");
			
			System.out.println("추가 태그"+TAG_THIRD);
			
			// memberVO에 추가 할 태그 값을 넣는다.
			memberVO.setTAG(TAG_THIRD);
			
			int result = myPageService.addTag(memberVO);
			
			System.out.println(result);
			String a = "";
			if (result == 1) {
				a="true";
			}else {
				a="false";
			}
			
			return a;
		}
		
		// 회원 상세
		@Override
		@RequestMapping(value="/myDetailInfo.do" ,method = RequestMethod.GET)
		public ModelAndView myDetailInfo(
											HttpServletRequest request,
											HttpServletResponse response)  throws Exception {
				
				//String viewName= (String)getViewName(request);
				String viewName = (String)request.getAttribute("viewName");
				System.out.println(viewName);
				ModelAndView mav = new ModelAndView(viewName);
				
				
				HttpSession session=request.getSession();
				
				memberVO = (MemberVO)session.getAttribute("memberInfo");
				String MEMBER_ID = (String)memberVO.getMEMBER_ID();
						
				System.out.println("세션으로 불러오는 아이디: " + MEMBER_ID);
				// 회원 수정 시 , 모든 태그 속성을 출력하기 위해 실행 ( 프로젝트 태그리스트).
				List ProjectTagList = projectService.TagList();
				// T_MEMBER_TAGLIST에 저장된 해쉬 태그를 불러온다.(회원 태그 리스트)
				List<MemberVO> MemberTagList = myPageService.selectTagList(MEMBER_ID);			
						
				// 모든 태그에 대한 변수는 ProjectTagList,
				// 회원 태그에 대한 변수는 MemberTagList로 사용.
				mav.addObject("ProjectTagList",ProjectTagList);
				mav.addObject("MemberTagList", MemberTagList);
				
				return mav;
			}
			
		// 회원 수정
		@Override
		@RequestMapping(value="/modifyMyInfo.do" ,method = {RequestMethod.POST,RequestMethod.GET})
		public ResponseEntity modifyMyInfo(
					                 @ModelAttribute("memberVO") MemberVO memberVO,
									 // 수정할 회원 정보 속성을 저장.
									 @RequestParam("attribute")  String attribute,
									 // 회원 정보의 속성 값을 저장.
					                 @RequestParam("value")  String value,
					                 HttpServletRequest request, 
					                 HttpServletResponse response)  
					                 throws Exception {
		System.out.println("수정하기 누름");
		Map<String,Object> memberMap=new HashMap<String,Object>();
		String val[]=null;
		// 로그인후 생성된 세션을 가져온다
		HttpSession session=request.getSession();		
		memberVO= (MemberVO)session.getAttribute("memberInfo");
				
		// JSP에서 자바쪽으로 가져온다. 이클립스에서는 "memberVO"객체로 사용.
		if(attribute.equals("MEMBER_BIRTH")){
			val=value.split(",");
			memberMap.put("MEMBER_BIRTH_Y",val[0]);
			memberMap.put("MEMBER_BIRTH_M",val[1]);
			memberMap.put("MEMBER_BIRTH_D",val[2]);					//memberMap.put("member_birth_gn",val[3]);
		}else if(attribute.equals("TEL")){
			val=value.split(",");
			memberMap.put("TEL1",val[0]);
			memberMap.put("TEL2",val[1]);
			memberMap.put("TEL3",val[2]);
		}else if(attribute.equals("HP")){
			val=value.split(",");
			memberMap.put("HP1",val[0]);
			memberMap.put("HP2",val[1]);
			memberMap.put("HP3",val[2]);
			memberMap.put("SMSSTS_YN", val[3]);
		}else if(attribute.equals("EMAIL")){
			val=value.split(",");
			memberMap.put("EMAIL1",val[0]);
			memberMap.put("EMAIL2",val[1]);
			memberMap.put("EMAILSTS_YN", val[2]);
		}else if(attribute.equals("ADDRESS")){
			val=value.split(",");
			memberMap.put("ZIPCODE",val[0]);
			memberMap.put("ROADADDRESS",val[1]);
			memberMap.put("JIBUNADDRESS", val[2]);
			memberMap.put("NAMUJIADDRESS", val[3]);
		}else {
			memberMap.put(attribute,value);	
		}
				
		//memberMap.put("MEMBER_ID", MEMBER_ID);
				
				
		memberVO= (MemberVO) myPageService.modifyMyInfo(memberMap);
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
				
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}	
		// 회원 수정시 태그 삭제
		@RequestMapping(value="/removeTag.do" ,method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView removeTag(@RequestParam("MEMBER_ID") String MEMBER_ID,
										  @RequestParam("MEMBER_TAG") String MEMBER_TAG,
											HttpServletRequest request, 
											 HttpServletResponse response)  throws Exception {
				ModelAndView mav = new ModelAndView();
				/*
				// 로그인후 생성된 세션을 가져온다
				HttpSession session=request.getSession();		
				memberVO= (MemberVO)session.getAttribute("memberInfo");
				*/
				
				// @RequestParam으로 던진 ID, TAG를 받는다.
				memberVO.setMEMBER_ID(MEMBER_ID);
				memberVO.setTAG(MEMBER_TAG);
				// 테스트
				System.out.println(" 삭제할 아이디"+MEMBER_ID);
				System.out.println("삭제할 태그 : " + MEMBER_TAG);
			    // 삭제 
				myPageService.removeTag(memberVO);
				mav.setViewName("redirect:/mypage/myDetailInfo.do");
				return mav;
				
			}
		
		
			
		// 뷰네임으로 가져오기. 	
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