package com.myspring.pro.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.myspring.pro.project.service.projectService;
import com.myspring.pro.project.vo.projectVO;
import com.myspring.pro.project.vo.tagVO;

import net.sf.json.JSONObject;



@Controller("projectController")
@EnableAspectJAutoProxy
public class projectControllerImpl   implements projectController {
	private static final Logger logger = LoggerFactory.getLogger(projectControllerImpl.class);
	@Autowired
	private projectService projectService;
	@Autowired
	private projectVO projectVO ;
	private tagVO TagVO;
	@Override
	@RequestMapping(value="/project/listprojects.do" ,method = RequestMethod.GET)
	public ModelAndView listprojects(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);

		logger.info("viewName: "+ viewName);
		logger.debug("viewName: "+ viewName);
		
		List projectsList = projectService.listprojects();
		List projectTagList = projectService.TagList();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("projectsList", projectsList);
		mav.addObject("projectTagList",projectTagList);
		return mav;
	}

	@Override
	@RequestMapping(value="/project/addproject.do" ,method = RequestMethod.POST)
	public ModelAndView addproject(@ModelAttribute("project") projectVO project,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = projectService.addproject(project);
		ModelAndView mav = new ModelAndView("redirect:/project/listprojects.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/project/removeproject.do" ,method = RequestMethod.GET)
	public ModelAndView removeproject(@RequestParam("PROJECT_CODE") String PROJECT_CODE, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		projectService.removeproject(PROJECT_CODE);
		ModelAndView mav = new ModelAndView("redirect:/project/listprojects.do");
		return mav;
	}
	
		@RequestMapping(value="/project/searchprojects.do" ,method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView searchprojects(@RequestParam("searchWord") String searchWord,@RequestParam("selecttag") List<String> tag,
				                       HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			//String viewName=(String)request.getAttribute("viewName");
			
			String viewName = getViewName(request);
			
			if(tag ==null) {
				tag.add("null");
			}
			System.out.println("searchWord"+searchWord);
			System.out.println("tag : "+tag.size());
			
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

	

	}

	






