package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Message;
import ua.entity.UserPage;
import ua.form.filter.MessageFilterForm;
import ua.service.MessageService;
import ua.service.UserPageService;
import ua.service.implementation.editor.UserPageEditor;

@Controller
@Transactional
public class MessageController {

	@Autowired
	private UserPageService userPageService;

	@Autowired
	private MessageService messageService;

	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserPage.class, new UserPageEditor(userPageService));

	}

	@ModelAttribute("adminMessage")
	public Message getMessage() {
		return new Message();
	}

	@ModelAttribute(value = "filter")
	public MessageFilterForm getFilter() {
		return new MessageFilterForm();
	}

	@RequestMapping("/message")
	public String showMyMessage(Model model, Pageable pageable, Principal principal, @ModelAttribute(value = "filter") MessageFilterForm filter) {
		model.addAttribute("messages", messageService.findByTexting(principal));
		return "message";
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public String saveMessage(@ModelAttribute("form") Message message, Model model, @PageableDefault(5) Pageable pageable, Principal principal,
			@ModelAttribute(value = "filter") MessageFilterForm filter) {
		messageService.save(message, principal);
		return "redirect:/message";
	}

	@RequestMapping(value = "/admin/message", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") Message message, Model model, @PageableDefault(5) Pageable pageable, Principal principal, @ModelAttribute(value = "filter") MessageFilterForm filter) {
		messageService.save(message, principal);
		return "redirect:/admin/message";
	}

	@RequestMapping(value = "/admin/message/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") MessageFilterForm filter) {
		model.addAttribute("senderIds", userPageService.findAll()).addAttribute("resiverIds", userPageService.findAll()).addAttribute("messages", messageService.findAll(pageable, filter));
		return "adminMessage";
	}

	@RequestMapping("/admin/message")
	public String show(Model model, Pageable pageable, @ModelAttribute(value = "filter") MessageFilterForm filter) {
		model.addAttribute("senderIds", userPageService.findAll()).addAttribute("reciverIds", userPageService.findAll()).addAttribute("messages", messageService.findAll(pageable, filter));
		return "adminMessage";

	}

	@RequestMapping(value = "/admin/message/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") MessageFilterForm filter) {
		messageService.delete(id);
		return "redirect:/admin/message";
	}

}
