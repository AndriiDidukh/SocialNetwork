package ua.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Follower;
import ua.entity.enums.Country;
import ua.entity.enums.DayOfBirth;
import ua.entity.enums.Gender;
import ua.entity.enums.MounthOfBirth;
import ua.entity.enums.YearOfBirth;
import ua.form.filter.UserPageFilterForm;
import ua.service.FollowerService;
import ua.service.UserPageService;

@Controller
public class FollowerController {

	@Autowired
	FollowerService followerService;

	@Autowired
	private UserPageService userPageService;

	@ModelAttribute("form")
	public Follower getFollower() {
		return new Follower();
	}

	@ModelAttribute("filter")
	public UserPageFilterForm getFilter() {
		return new UserPageFilterForm();
	}

	@RequestMapping("/folowFor/delete/{id}")
	public String deleteFolowFor(@PathVariable int id) {
		followerService.deleteById(id);
		return "redirect:/friends";
	}

	@RequestMapping(value = "/follower/{id}", method = RequestMethod.POST)
	public String follow(@ModelAttribute("form") @Valid Follower follow, BindingResult br, Model model, Pageable pageable, Principal principal, @PathVariable int id,
			@ModelAttribute(value = "filter") UserPageFilterForm filter) {
		if (br.hasErrors()) {
			model.addAttribute("countries", Country.values()).addAttribute("daysOfBirth", DayOfBirth.values()).addAttribute("mounsesOfBirth", MounthOfBirth.values())
					.addAttribute("yearsOfBirth", YearOfBirth.values()).addAttribute("sexs", Gender.values()).addAttribute("userPages", userPageService.findAll(pageable, filter));
			return "peopleSearch";
		}
		followerService.saveFollower(follow, principal, id);
		return "redirect:/peopleSearch";
	}
}
