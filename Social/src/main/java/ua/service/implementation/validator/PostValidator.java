package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.Post;

public class PostValidator implements Validator {


	private final Pattern pattern = Pattern.compile("\\w+");

	@Override
	public boolean supports(Class<?> arg0) {
		return Post.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Post post = (Post) obj;

		Matcher name = pattern.matcher(post.getName());
		Matcher text = pattern.matcher(post.getText());
		if (!name.matches()) {
			errors.rejectValue("name", "", "Post name can't be empty");
		}

		if (!text.matches()) {
			errors.rejectValue("text", "", "Text can't be empty");
		}
	}

}
