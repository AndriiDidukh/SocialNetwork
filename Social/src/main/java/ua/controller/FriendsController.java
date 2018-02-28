package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Friend;
import ua.service.FollowerService;
import ua.service.FriendService;

@Controller
public class FriendsController {

	@Autowired
	FollowerService followerService;

	@Autowired
	FriendService friendService;

	@ModelAttribute("friend")
	public Friend getFriend() {
		return new Friend();
	}

	@RequestMapping("/friends")
	public String showFriends(Model model, Principal principal) {
		model.addAttribute("followers", followerService.findFolowers(principal)).addAttribute("followTo", followerService.findFolowTo(principal))
				.addAttribute("friends1", friendService.findFriend1(principal)).addAttribute("friends2", friendService.findFriend2(principal));
		return "friends";
	}

	@RequestMapping(value = "/friends/addFriend/{id}", method = RequestMethod.POST)
	public String addFriend(@ModelAttribute("friend") Friend friend, Principal principal, @PathVariable int id) {
		friendService.addFriend(friend, principal, id);
		return "redirect:/friends";
	}

	@RequestMapping(value = "/friends/delete/{id}")
	public String deleteFriend(@PathVariable int id, Principal principal) {
		friendService.deleteFriend(id, principal);
		return "redirect:/friends";
	}
}
