package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Groupe;
import ua.form.GroupeForm;
import ua.form.filter.GroupeFilterForm;

public interface GroupeService {

	void saveForm(GroupeForm form, Principal principal);

	void save(Groupe groupe);

	void delete(String name);

	void delete(int id);

	Groupe findByName(String name);

	Groupe findOne(int id);

	List<Groupe> findAll();

	Page<Groupe> findAll(Pageable pageable, GroupeFilterForm form);

	List<Groupe> findByCreators(Principal principal);

}
