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

import ua.entity.Message;
import ua.form.filter.MessageFilterForm;

public class MessageFilterAdapter implements Specification<Message>{
private final MessageFilterForm form;
	
	private final List<Specification<Message>> filters = new ArrayList<>();	

	public MessageFilterAdapter(MessageFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new MessageFilterForm();
		}
	}
	private void findByText(){
		if(!form.getText().isEmpty()){
			filters.add((root, query, cb)->root.get("text").in(form.getText()));
		}
	}
	
	private void findBySender(){
		if(!form.getSenderIds().isEmpty()){
			filters.add((root, query, cb)->root.get("senderId").in(form.getSenderIds()));
		}
	}
	
	private void findByReciver(){
		if(!form.getReciverIds().isEmpty()){
			filters.add((root, query, cb)->root.get("reciwerId").in(form.getReciverIds()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			
			
			root.fetch("senderId", JoinType.LEFT);
			root.fetch("reciverId", JoinType.LEFT);
		}
		findByText();
		findBySender();
		findByReciver();
		
		
		if(!filters.isEmpty()){
			Specifications<Message> spec = Specifications.where(filters.get(0));
			for(Specification<Message> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}
}
