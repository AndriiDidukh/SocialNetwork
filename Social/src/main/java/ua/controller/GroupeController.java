package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import ua.entity.Groupe;
import ua.entity.UserPage;
import ua.form.GroupeForm;
import ua.form.filter.GroupeFilterForm;
import ua.service.GroupeMemberService;
import ua.service.GroupeService;
import ua.service.UserPageService;
import ua.service.implementation.editor.UserPageEditor;
import ua.service.implementation.validator.GroupeValidator;

@Controller
public class GroupeController {
	@Autowired
	private GroupeService groupeService;

	@Autowired
	private UserPageService userPageService;

	@Autowired
	GroupeMemberService groupeMemberService;

	@RequestMapping("/groupeSearch")
	public String groupeSearching(Model model, Pageable pageable, @ModelAttribute(value = "filter") GroupeFilterForm filter) {
		model.addAttribute("creators", userPageService.findAll()).addAttribute("adminGroupes", groupeService.findAll(pageable, filter));

		return "groupeSearch";
	}

	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserPage.class, new UserPageEditor(userPageService));
		binder.setValidator(new GroupeValidator(groupeService));
	}

	@ModelAttribute("form")
	public Groupe getForm() {
		return new Groupe();
	}

	@ModelAttribute("groupeForm")
	public GroupeForm getGroupeForm() {
		return new GroupeForm();
	}

	@RequestMapping("/admin/groupe")
	public String show(Model model, Pageable pageable, @ModelAttribute(value = "filter") GroupeFilterForm filter) {
		model.addAttribute("creators", userPageService.findAll()).addAttribute("adminGroupes", groupeService.findAll(pageable, filter));
		return "adminGroupe";
	}

	@RequestMapping("/groupe")
	public String showGroupes(Model model, Pageable pageable, Principal principal, @ModelAttribute(value = "filter") GroupeFilterForm filter) {
		model.addAttribute("creators", userPageService.findAll()).addAttribute("myGroupes", groupeService.findByCreators(principal)).addAttribute("groups", groupeMemberService.findGroupes(principal));
		return "groupe";
	}

	@RequestMapping(value = "/groupe", method = RequestMethod.POST)
	public String saveGroupe(@ModelAttribute("groupeForm") GroupeForm form, BindingResult br, Model model, Principal principal, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") GroupeFilterForm filter) {
		if (br.hasErrors()) {
			return "groupe";
		}
		groupeService.saveForm(form, principal);
		return "redirect:/groupe";
	}

	@RequestMapping(value = "/admin/groupe", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") Groupe groupe, BindingResult br, Model model, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") GroupeFilterForm filter) {
		groupeService.save(groupe);
		return "redirect:/admin/groupe";
	}

	@RequestMapping(value = "/admin/groupe/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") GroupeFilterForm filter) {
		model.addAttribute("creators", userPageService.findAll()).addAttribute("adminGroupes", groupeService.findAll(pageable, filter));
		return "adminGroupe";

	}

	@RequestMapping(value = "/admin/groupe/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") GroupeFilterForm filter) {
		groupeService.delete(id);
		return "redirect:/admin/groupe";
	}

	@ModelAttribute("filter")
	public GroupeFilterForm getFilter() {
		return new GroupeFilterForm();
	}

}
