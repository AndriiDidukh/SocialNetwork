package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.Message;

public class MessageValidator implements Validator{

	private final Pattern pattern = Pattern.compile("\\w+");
	

	@Override
	public boolean supports(Class<?> arg0) {
		return Message.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Message message = (Message) obj;
		
		Matcher text = pattern.matcher(message.getText());
		if(!text.matches()){
			errors.rejectValue("text", "", "text can not be empty");
		}
		
	}

}
