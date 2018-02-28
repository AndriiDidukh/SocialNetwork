package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Post;
import ua.form.filter.PostFilterForm;

public class PostFilterAdapter implements Specification<Post> {

	private final PostFilterForm form;

	private final List<Specification<Post>> filters = new ArrayList<>();

	public PostFilterAdapter(PostFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new PostFilterForm();
		}
	}

	private void findByName() {
		if (!form.getName().isEmpty()) {
			filters.add((root, query, cb) -> root.get("name").in(form.getName()));
		}
	}

	private void findByText() {
		if (!form.getText().isEmpty()) {
			filters.add((root, query, cb) -> root.get("text").in(form.getText()));
		}
	}

	private void findByOwner() {
		if (!form.getOwnerId().isEmpty()) {
			filters.add((root, query, cb) -> root.get("owner").in(form.getOwnerId()));
		}
	}

	private void findByWriter() {
		if (!form.getWriterId().isEmpty()) {
			filters.add((root, query, cb) -> root.get("writer").in(form.getWriterId()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {

			root.fetch("owner", JoinType.LEFT);
			root.fetch("writer", JoinType.LEFT);
			
		}

		findByName();
		findByText();
		findByOwner();
		findByWriter();
		if (!filters.isEmpty()) {
			Specifications<Post> spec = Specifications.where(filters.get(0));
			for (Specification<Post> s : filters.subList(1, filters.size())) {
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
