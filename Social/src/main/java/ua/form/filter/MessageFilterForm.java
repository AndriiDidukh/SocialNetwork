package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class MessageFilterForm {
	private String text = "";
	
	private List<Integer> senderIds = new ArrayList<>();
	
	private List<Integer> reciverIds = new ArrayList<>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Integer> getSenderIds() {
		return senderIds;
	}

	public void setSenderIds(List<Integer> senderIds) {
		this.senderIds = senderIds;
	}

	public List<Integer> getReciverIds() {
		return reciverIds;
	}

	public void setReciverIds(List<Integer> reciverIds) {
		this.reciverIds = reciverIds;
	}
	
	

}
