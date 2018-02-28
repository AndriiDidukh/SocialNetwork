package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.UserPage;
import ua.form.filter.UserPageFilterForm;

public class UserPageFilterAdapter implements Specification<UserPage> {
	private final UserPageFilterForm form;

	private final List<Specification<UserPage>> filters = new ArrayList<>();

	public UserPageFilterAdapter(UserPageFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new UserPageFilterForm();
		}
	}

	private void findByCountry() {
		if (!form.getCountryIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("country").in(form.getCountryIds()));
		}
	}

	private void findByGender() {
		if (!form.getSexIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("gender").in(form.getSexIds()));
		}
	}

	private void findByDayOfBirth() {
		if (!form.getDayOfBirthIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("dayOfBirth").in(form.getDayOfBirthIds()));
		}
	}

	private void findByMounthOfBirth() {
		if (!form.getMounthOfBirthIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("mounthOfBirth").in(form.getMounthOfBirthIds()));
		}
	}

	private void findByYearOfBirth() {
		if (!form.getYearOfBirthIds().isEmpty()) {
			filters.add((root, query, cb) -> root.get("yearOfBirth").in(form.getYearOfBirthIds()));
		}
	}

	private void findByName() {
		if (!form.getName().isEmpty()) {
			filters.add((root, query, cb) -> root.get("name").in(form.getName()));
		}
	}

	private void findBySurname() {
		if (!form.getSurname().isEmpty()) {
			filters.add((root, query, cb) -> root.get("surname").in(form.getSurname()));
		}
	}

	private void findByPhone() {
		if (!form.getPhone().isEmpty()) {
			filters.add((root, query, cb) -> root.get("phone").in(form.getPhone()));
		}
	}

	private void findByCity() {
		if (!form.getCity().isEmpty()) {
			filters.add((root, query, cb) -> root.get("city").in(form.getCity()));
		}
	}

	private void findByEmail() {
		if (!form.getEmail().isEmpty()) {
			filters.add((root, query, cb) -> root.get("email").in(form.getEmail()));
		}
	}

	@Override
	public Predicate toPredicate(Root<UserPage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {

			// root.fetch("country", JoinType.LEFT);
			// root.get("gender");
			// root.fetch("dayOfBirth", JoinType.LEFT);
			// root.fetch("mounthOfBirth", JoinType.LEFT);
			// root.fetch("yearOfBirth", JoinType.LEFT);
		}
		findBySurname();
		findByPhone();
		findByCity();
		findByEmail();
		findByName();
		findByCountry();
		findByGender();
		findByDayOfBirth();
		findByMounthOfBirth();
		findByYearOfBirth();
		if (!filters.isEmpty()) {
			Specifications<UserPage> spec = Specifications.where(filters.get(0));
			for (Specification<UserPage> s : filters.subList(1, filters.size())) {
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}
}
