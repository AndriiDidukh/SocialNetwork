package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.enums.Country;
import ua.entity.enums.DayOfBirth;
import ua.entity.enums.Gender;
import ua.entity.enums.MounthOfBirth;
import ua.entity.enums.YearOfBirth;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;
import ua.service.UserPageService;

@Controller
public class PeopleSearchController {

	@Autowired
	private UserPageService userPageService;

	@ModelAttribute("form")
	public UserPageForm getForm() {
		return new UserPageForm();
	}

	@ModelAttribute("filter")
	public UserPageFilterForm getFilter() {
		return new UserPageFilterForm();
	}

	// @InitBinder("form")
	// protected void initBinder(WebDataBinder binder) {
	// binder.registerCustomEditor(Country.class, new
	// CountryEditor(countryService));
	// // binder.registerCustomEditor(Gender.class, new Ge(sexService));
	// binder.registerCustomEditor(DayOfBirth.class, new
	// DayOfBirthEditor(dayOfBirthService));
	// binder.registerCustomEditor(MounthOfBirth.class, new
	// MounthOfBirthEditor(mounthOfBirthService));
	// // binder.registerCustomEditor(YearOfBirth.class, new
	// // YearOfBirthEditor(yearOfBirthService));
	//
	// }

	@RequestMapping("/peopleSearch")
	public String showUserPages(Model model, Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		model.addAttribute("countries", Country.values()).addAttribute("daysOfBirth", DayOfBirth.values()).addAttribute("mounsesOfBirth", MounthOfBirth.values())
				.addAttribute("yearsOfBirth", YearOfBirth.values()).addAttribute("sexs", Gender.values()).addAttribute("userPages", userPageService.findAll(pageable, filter));
		return "peopleSearch";
	}
}
