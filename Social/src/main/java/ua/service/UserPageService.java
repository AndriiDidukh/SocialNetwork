package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.UserPage;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;

public interface UserPageService {

	List<UserPage> findAll();

	void save(UserPageForm form);

	UserPageForm findForForm(int id);

	void delete(int id);

	UserPage findOne(int id);

	Page<UserPage> findAll(Pageable pageable, UserPageFilterForm filter);

	UserPage findByLogin(String login);

	UserPage findById(int id);

	UserPage findByPrincipal(Principal principal);

}
