package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Groupe;
import ua.service.GroupeService;

public class GroupeEditor extends PropertyEditorSupport{
	private final GroupeService groupeService;
	
	public GroupeEditor(GroupeService groupeService){
		this.groupeService = groupeService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Groupe groupe = groupeService.findOne(Integer.valueOf(text));
		setValue(groupe);
	}

}
