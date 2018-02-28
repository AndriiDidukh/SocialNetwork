package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.entity.UserPage;
import ua.service.UserPageService;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private UserPageService userService;

	@ModelAttribute("authUser")
	public UserPage getUser(){
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!"anonymousUser".equals(id)){
			return userService.findById(Integer.valueOf(id));
		}
		return null;
	}
}