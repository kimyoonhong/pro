package com.myspring.pro.member.controller;

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
import com.myspring.pro.member.service.MemberService;
import com.myspring.pro.member.vo.MemberVO;

import com.myspring.pro.project.controller.projectControllerImpl;
import com.myspring.pro.project.service.projectService;
import com.myspring.pro.project.dao.projectDAO;
import com.myspring.pro.project.vo.tagVO;

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

@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl implements MemberController{
	private static final Logger logger = 
						LoggerFactory.getLogger(MemberControllerImpl.class);
	
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
	
	// 회원 조회
	@Override
	@RequestMapping(value="/listMembers.do" ,method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, 
										HttpServletResponse response) 
												throws Exception {
		
		//String viewName = getViewName(request); // 컨트롤러에 있는 getViewName()메서드 (필터 사용전)
		
		// 인터셉터에서 바인딩 된 뷰 이름을 가져온다.
		String viewName = (String)request.getAttribute("viewName");
		logger.info("info레벨 : viewName = " + viewName);
		
		System.out.println(viewName);
			
		List membersList = memberService.listMembers();
		// viewName이 tiles_member.xml의 <definition>태그에 설정한 뷰이름과 일치한다.
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
		return mav;
	}
	
	// 로그인
	@Override
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	                         //로그인 창에서 전송된 ID,비밀번호를 MemberVO 객체인 member에 저장.
	public ModelAndView login(@ModelAttribute("memberVO") MemberVO memberVO,
			//RedirectAttributes클래스를 이용해 로그인 실패 시 다시 로그인창으로 리다이렉트하여 실패 메시지 전달!
				              RedirectAttributes rAttr,
		                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("로그인() ID :"+memberVO.getMEMBER_ID());
		System.out.println("로그인() PW :"+memberVO.getMEMBER_PW());
		// login()메서드를 호출하면서 로그인 정보를 전달.
		memberVO = memberService.login(memberVO);
		
		if(memberVO != null) {
				HttpSession session = request.getSession();
			    
			    // 세션에 회원 정보를 저장.
				session=request.getSession();
			    session.setAttribute("memberVO", memberVO);
			    
			    // 세션에 로그인 상태를 true로 설정.
				// 세션 정보를 "memberInfo 변수에" memberVO 속성으로 저장.
			    session.setAttribute("isLogOn", true);
			    session.setAttribute("memberInfo", memberVO);
			    
			    // memberVO로 반환된 값이 있으면 세션을 이용해 로그인 상태를 true로 한다.
			    mav.setViewName("redirect:/member/listMembers.do");
			    
		}else {
				// 로그인 실패시 실패 메시지를 로그인 창으로 전달.
			    rAttr.addAttribute("result","loginFailed");
			    
			    // 로그인 실패시 다시 로그인 창으로 리다이렉트.
			    mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
		}
	
	// 로그아웃
	@Override
	@RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, 
							   HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		
		// 로그아웃 요청 시 세션에 저장된 로그인 정보와 회원 정보를 삭제
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		
		// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
		return mav;
	}

	
	
	// 로그인
	@RequestMapping(value = "/loginForm.do", method =  RequestMethod.GET)
							// 로그인창 요청 시 매개변수 result가 전송되면 변수 result에 값을 저장.
							// 최초 로그인창을 요청할 때는 매개변수 result가 전송되지 않으므로 무시한다.
	private String loginform(@RequestParam(value= "result", required=false) String result,
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
	// 회원가입 창 
	@RequestMapping(value = "/memberForm.do", method =  RequestMethod.GET)
								// 로그인창 요청 시 매개변수 result가 전송되면 변수 result에 값을 저장.
								// 최초 로그인창을 요청할 때는 매개변수 result가 전송되지 않으므로 무시한다.
	private ModelAndView memberform(
							       HttpServletRequest request, 
							       HttpServletResponse response) throws Exception {
			//String viewName = getViewName(request); // 컨트롤러에 있는 getViewName()메서드 (필터 사용전)
			
			// 인터셉터에서 바인딩 된 뷰 이름을 가져온다.
			String viewName = (String)request.getAttribute("viewName");
			ModelAndView mav = new ModelAndView(viewName);
			
			// 회원 가입시, project 패키지의 태그 속성도 함께 등록하기 위해 실행.
			List projectTagList = projectService.TagList();
			
			//System.out.println(projectService.TagList());
			mav.addObject("projectTagList",projectTagList);
			
			// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환한다.
			return mav;
		}
	
	// 회원가입
	@Override
	@RequestMapping(value="/addMember.do" ,method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,
					// @ModelAttribute("tagVO") List<String> tagVO
					// 로 해쉬태그를 받아보았으나 값이 하나밖에 넘어오질 못했다.
					// 체크박스를 통해 여러개의 값을 받아오기 위해 @RequestParam으로 받으니 여러값이 받아졌다.
							@RequestParam("tagVO") List<String> tagVO,
							HttpServletRequest request, 
			                HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		/* 값을 제대로 받아들이는지 확인
		System.out.println("tagvo.size() : " + tagVO.size());
		for(int i=0; i<tagVO.size(); i++) {
		System.out.println("tag.get(i) ="+tagVO.get(i));
		}
		*/
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type","text/html;charset=utf-8");
		/*
		 *  DAO에서 구현
		 * List tag; // 태그를 리스트형식으로 선언한다
		 * for(int i=0; i<tag.size(); i++) {
		 * 		memberVO.setTAG(tag[i]);
		 * 		memberService(인서트문);
		 * }
		 * */
		try {
		    memberService.addMember(_memberVO,tagVO);
		    System.out.println("컨트롤러에서 아이디를 잡아넣는가?" + _memberVO.getMEMBER_ID());
		    message  = "<script>";
		    message +=" alert('회원 가입을 마쳤습니다.회원조회창으로 이동합니다.');";
		    message += " location.href='"+request.getContextPath()+"/member/listMembers.do';";
		    message += " </script>";
		    
		}catch(Exception e) {
			message  = "<script>";
		    message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
		    message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
		    message += " </script>";
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	// 아이디 중복확인
	@Override
	@RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("MEMBER_ID") String MEMBER_ID,
										HttpServletRequest request,
										HttpServletResponse response) throws Exception{
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(MEMBER_ID);
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
	
			
	// 회원삭제
	@RequestMapping(value="/removeMember.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView deleteMember(HttpServletRequest request, 
									 HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		//
		System.out.println(request.getParameter("MEMBER_ID"));
		
		String member_id=request.getParameter("MEMBER_ID");
		memberService.deleteMember(member_id);
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
		
	}
	
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