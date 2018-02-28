package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Message;
import ua.entity.Post;
import ua.service.MessageService;
import ua.service.PostService;
import ua.service.UserPageService;

@Controller
public class PeoplePageController {

	@Autowired
	PostService postService;

	@Autowired
	MessageService messageService;

	@Autowired
	UserPageService userPageService;

	@ModelAttribute("message")
	public Message getMessage() {
		return new Message();
	}

	@RequestMapping("/texting/{id}")
	public String showTextting(Model model, Principal principal, @PathVariable int id) {
		model.addAttribute("messages", messageService.findTexting(principal, id)).addAttribute("user", userPageService.findOne(id));
		return "texting";
	}

	@RequestMapping(value = "/texting/{id}", method = RequestMethod.POST)
	public String saveTexting(@ModelAttribute("message") Message message, @PathVariable int id, Principal principal) {
		messageService.saveTexting(id, principal, message);
		return "redirect:/texting/{id}";
	}

	@ModelAttribute("post")
	public Post getPost() {
		return new Post();
	}

	@RequestMapping(value = "/peoplePage/{id}", method = RequestMethod.POST)
	public String savePeoplePost(@ModelAttribute("form2") Post post, @PathVariable int id, Principal principal) {
		postService.savePeoplePost(post, principal, id);
		return "redirect:/peoplePage/{id}";
	}

}
