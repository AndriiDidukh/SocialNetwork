package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Message;
import ua.service.MessageService;

public class MessageEditor extends PropertyEditorSupport {
	private final MessageService messageService;
	
	public MessageEditor(MessageService messageService){
		this.messageService = messageService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Message message = messageService.findOne(Integer.valueOf(text));
		setValue(message);
	}
}
