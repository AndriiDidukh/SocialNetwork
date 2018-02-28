package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Post;
import ua.form.UserPageForm;
import ua.service.PostService;
import ua.service.UserPageService;

@Controller
public class IndexController {

	@Autowired
	private UserPageService userPageService;

	@Autowired
	private PostService postService;

	@RequestMapping("/")
	public String showIndex(Principal principal, Model model) {
		model.addAttribute("writers", userPageService.findAll()).addAttribute("owners", userPageService.findAll()).addAttribute("posts", postService.findByOwners(principal))
				.addAttribute("user", userPageService.findByPrincipal(principal));
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveSelfPost(@ModelAttribute("form2") Post post, Principal principal) {
		postService.saveSelfPost(post, principal);
		return "redirect:/";
	}

	@ModelAttribute("form")
	public UserPageForm getForm() {
		return new UserPageForm();
	}

	@ModelAttribute("post")
	public Post getPost() {
		return new Post();
	}

	@RequestMapping("/peoplePage/{id}")
	public String showPeople(Model model, @PathVariable int id) {
		model.addAttribute("user", userPageService.findOne(id)).addAttribute("posts", postService.findAllById(id));
		return "peoplePage";
	}

	@RequestMapping("/admin")
	public String showAdmin() {
		return "adminPanel";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
}
