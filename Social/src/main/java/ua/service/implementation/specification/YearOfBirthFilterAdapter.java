package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.enums.YearOfBirth;
import ua.form.filter.YearOfBirthFilterForm;

public class YearOfBirthFilterAdapter implements Specification<YearOfBirth> {
	private String search = "";

	public YearOfBirthFilterAdapter(YearOfBirthFilterForm form) {
		if (form.getSearch() != null)
			search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<YearOfBirth> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
	}

}
