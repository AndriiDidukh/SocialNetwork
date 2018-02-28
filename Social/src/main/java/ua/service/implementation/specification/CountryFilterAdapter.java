package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.enums.Country;
import ua.form.filter.CountryFilterForm;

public class CountryFilterAdapter implements Specification<Country> {

	private String search = "";

	public CountryFilterAdapter(CountryFilterForm form) {
		if (form.getSearch() != null)
			search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
	}

}