package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.enums.Gender;
import ua.form.filter.GenderFilterForm;

public class GenderFilterAdapter implements Specification<Gender> {

	private String search = "";

	public GenderFilterAdapter(GenderFilterForm form) {
		if (form.getSearch() != null)
			search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Gender> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
	}
}
