package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.Groupe;
import ua.service.GroupeService;

public class GroupeValidator implements Validator{
	
	private final GroupeService groupeService;
	private final Pattern pattern = Pattern.compile("\\w+");
	
	public GroupeValidator(GroupeService groupeService){
		this.groupeService = groupeService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return Groupe.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Groupe groupe = (Groupe) obj;
		Matcher name = pattern.matcher(groupe.getName());
		if(!name.matches()){
			errors.rejectValue("name", "", "name can not be empty");
		}
		
		if(groupeService.findByName(groupe.getName())!=null){
			errors.rejectValue("name", "", "Groupe with this name is already exist");
		}
	}

}
