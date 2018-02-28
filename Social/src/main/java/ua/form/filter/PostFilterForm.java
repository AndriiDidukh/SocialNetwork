package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class PostFilterForm {
private String name = "";

private String text = "";

private List<Integer> ownerId = new ArrayList<>();

private List<Integer> writerId = new ArrayList<>();

public List<Integer> getOwnerId() {
	return ownerId;
}

public void setOwnerId(List<Integer> ownerId) {
	this.ownerId = ownerId;
}

public List<Integer> getWriterId() {
	return writerId;
}

public void setWriterId(List<Integer> writerId) {
	this.writerId = writerId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}



}
