package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.UserPageForm;
import ua.service.UserPageService;

public class RegestrationValidator implements Validator{
	
	private final UserPageService userPageService;
	private Pattern Login = Pattern.compile("^[a-zA-Z0-9]{4,16}$");
	private Pattern Name = Pattern.compile("^[a-zA-Z]{3,16}$");
	private Pattern Surname = Pattern.compile("^[a-zA-Z]{3,16}$");
	private Pattern Email = Pattern.compile("^([a-zA-Z0-9_-]+.)*[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)*.[a-z]{2,6}$");
	private Pattern Phone = Pattern.compile("[0-9]{10,13}");
	private Pattern City = Pattern.compile("^[a-zA-Z0-9]{3,16}");
	private Pattern Password = Pattern.compile("^[a-zA-Z0-9]{4,16}$");
	
	public RegestrationValidator(UserPageService userPageService){
		this.userPageService = userPageService;
	}
	

	@Override
	public boolean supports(Class<?> arg0) {
		return UserPageForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserPageForm userPage = (UserPageForm) obj;
		
	Matcher login = Login.matcher(userPage.getLogin());
		Matcher name = Name.matcher(userPage.getName());
		Matcher surname = Surname.matcher(userPage.getSurname());
		Matcher phone = Phone.matcher(userPage.getPhone());
		Matcher password = Password.matcher(userPage.getPassword());
		Matcher city = City.matcher(userPage.getCity());
		Matcher email = Email.matcher(userPage.getEmail());
		
		if(!login.matches()){
			errors.rejectValue("login", "", "Login is not correct");
		}
		if(!name.matches()){
			errors.rejectValue("name", "", "Name is not correct");
		}
		if(!surname.matches()){
			errors.rejectValue("surname", "", "Surname is not  correct");
		}
		if(!phone.matches()){
			errors.rejectValue("phone", "", "Phone is not correct");
		}
		if(!email.matches()){
			errors.rejectValue("email", "", "Email is not correct");
		}
		if(!password.matches()){
			errors.rejectValue("password", "", "Password is not correct");
		}
		if(!city.matches()){
			errors.rejectValue("city", "", "City is not correct");
		}
		if(userPageService.findByLogin(userPage.getLogin())!=null){
			errors.rejectValue("login", "", "This Login is already exist");
		}
	}

}
