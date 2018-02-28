package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.UserPage;
import ua.service.UserPageService;

public class UserPageEditor extends PropertyEditorSupport{
	private final UserPageService userPageService;
	
	public UserPageEditor(UserPageService userPageService){
		this.userPageService = userPageService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		UserPage userPage = userPageService.findOne(Integer.valueOf(text));
		setValue(userPage);
	}
}
