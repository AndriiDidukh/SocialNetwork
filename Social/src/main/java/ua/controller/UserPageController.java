package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.enums.Country;
import ua.entity.enums.DayOfBirth;
import ua.entity.enums.Gender;
import ua.entity.enums.MounthOfBirth;
import ua.entity.enums.YearOfBirth;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;
import ua.service.UserPageService;
import ua.service.implementation.validator.RegestrationValidator;

@Controller
public class UserPageController {
	@Autowired
	private UserPageService userPageService;

	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegestrationValidator(userPageService));
	}

	@ModelAttribute("form")
	public UserPageForm getForm() {
		return new UserPageForm();
	}

	@RequestMapping("/registration")
	public String register(Model model) {
		model.addAttribute("countries", Country.values()).addAttribute("daysOfBirth", DayOfBirth.values()).addAttribute("mounsesOfBirth", MounthOfBirth.values())
				.addAttribute("yearsOfBirth", YearOfBirth.values()).addAttribute("sexs", Gender.values());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") UserPageForm form, BindingResult br, Model model) {

		userPageService.save(form);
		return "redirect:/login";
	}

	@ModelAttribute("filter")
	public UserPageFilterForm getFilter() {
		return new UserPageFilterForm();
	}

	@RequestMapping("/admin/userPage")
	public String showUserPages(Model model, Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		model.addAttribute("countries", Country.values()).addAttribute("daysOfBirth", DayOfBirth.values()).addAttribute("mounsesOfBirth", MounthOfBirth.values())
				.addAttribute("yearsOfBirth", YearOfBirth.values()).addAttribute("sexs", Gender.values()).addAttribute("userPages", userPageService.findAll(pageable, filter));
		return "adminUserPage";
	}

	@RequestMapping(value = "/admin/userPage", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid UserPageForm form, Model model, BindingResult br, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") UserPageFilterForm filter) {
		if (br.hasErrors()) {
			return "adminUserPage";
		}
		userPageService.save(form);
		return "redirect:/admin/userPage" + getParams(pageable, filter);
	}

	@RequestMapping(value = "/admin/userPage/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		model.addAttribute("form", userPageService.findForForm(id));
		model.addAttribute("countries", Country.values());
		model.addAttribute("sexs", Gender.values());
		model.addAttribute("daysOfBirch", DayOfBirth.values());
		model.addAttribute("mounsesOfBirth", MounthOfBirth.values());
		model.addAttribute("yearsOfBirth", YearOfBirth.values());
		model.addAttribute("userPages", userPageService.findAll(pageable, filter));
		return "adminUserPage";
	}

	@RequestMapping(value = "/admin/userPage/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		userPageService.delete(id);
		return "redirect:/admin/userPage" + getParams(pageable, filter);
	}

	private String getParams(Pageable pageable, UserPageFilterForm form) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		if (form.getSearch().isEmpty()) {
			buffer.append("&search=");
			buffer.append(form.getSearch());
		}

		for (Country i : form.getCountryIds()) {
			buffer.append("&countryIds=");
			buffer.append(i.toString());
		}
		if (!form.getSexIds().isEmpty()) {
			buffer.append("&sexIds=");
			buffer.append(form.getSexIds().toString());
		}
		// for (Gender i : form.getSexIds()) {
		// buffer.append("&sexIds=");
		// buffer.append(i.toString());
		// }
		for (Integer i : form.getDayOfBirthIds()) {
			buffer.append("&dayOfBirthIds=");
			buffer.append(i.toString());
		}
		for (MounthOfBirth i : form.getMounthOfBirthIds()) {
			buffer.append("&mounthOfBirthIds=");
			buffer.append(i.toString());
		}

		if (!form.getYearOfBirthIds().isEmpty()) {
			buffer.append("&sexIds=");
			buffer.append(form.getYearOfBirthIds().toString());
		}
		// for (Integer i : form.getYearOfBirthIds()) {
		// buffer.append("&yearOfBirthIds=");
		// buffer.append(i.toString());
		// }
		return buffer.toString();
	}
}
