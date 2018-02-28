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

import ua.entity.Groupe;
import ua.form.filter.GroupeFilterForm;

public class GroupeFilterAdapter implements Specification<Groupe>{
	
private final GroupeFilterForm form;
	
	private final List<Specification<Groupe>> filters = new ArrayList<>();
	
	public GroupeFilterAdapter(GroupeFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new GroupeFilterForm();
		}
	}
	
	private void findByCreator(){
		if(!form.getCreator().isEmpty()){
			filters.add((root, query, cb)->root.get("creator").in(form.getCreator()));
		}
	}
	
	private void findByName(){
		if(!form.getName().isEmpty()){
			filters.add((root, query, cb)->root.get("name").in(form.getName()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Groupe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			
			root.fetch("creator", JoinType.LEFT);
		
		}
		
		findByName();
		findByCreator();
	
		if(!filters.isEmpty()){
			Specifications<Groupe> spec = Specifications.where(filters.get(0));
			for(Specification<Groupe> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
