package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.GroupePost;
import ua.entity.GroupesMembers;
import ua.service.GroupeMemberService;
import ua.service.GroupePostService;
import ua.service.GroupeService;

@Controller
public class GroupePageController {

	@Autowired
	GroupeService groupeService;

	@Autowired
	GroupePostService groupePostService;

	@Autowired
	GroupeMemberService groupeMemberService;

	@ModelAttribute("form")
	public GroupePost getGroupePost() {
		return new GroupePost();
	}

	@ModelAttribute("memb")
	public GroupesMembers getGroupeMembers() {
		return new GroupesMembers();
	}

	@RequestMapping("/groupePage/{id}")
	public String showGroupePage(Model model, @PathVariable int id) {
		model.addAttribute("groupe", groupeService.findOne(id)).addAttribute("posts", groupePostService.findByOwner(id)).addAttribute("subs", groupeMemberService.findSubscribers(id))
				.addAttribute("count", groupeMemberService.findCount(id));
		return "groupePage";
	}

	@RequestMapping(value = "/groupePage/{id}", method = RequestMethod.POST)
	public String SaveGroupePost(@ModelAttribute("form") GroupePost groupePost, Principal principal, @PathVariable int id) {
		groupePostService.savePost(groupePost, principal, id);
		return "redirect:/groupePage/{id}";
	}

	@RequestMapping(value = "/groupesPage/{id}", method = RequestMethod.POST)
	public String saveGroupeMember(@ModelAttribute("memb") GroupesMembers groupeMember, @PathVariable int id, Principal principal) {
		groupeMemberService.addMember(groupeMember, id, principal);
		return "redirect:/groupePage/{id}";
	}

	@RequestMapping(value = "/groupe/unsubscribe/{id}")
	public String unsubscribeGroupe(@PathVariable int id, Principal principal) {
		groupeMemberService.unsubscribe(id, principal);
		return "redirect:/groupe";
	}

}
