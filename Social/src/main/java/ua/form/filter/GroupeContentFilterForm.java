package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class GroupeContentFilterForm {
private String name = "";

private String text = "";

private List<Integer> owner = new ArrayList<>();

private List<Integer> writer = new ArrayList<>();

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

public List<Integer> getOwner() {
	return owner;
}

public void setOwner(List<Integer> owner) {
	this.owner = owner;
}

public List<Integer> getWriter() {
	return writer;
}

public void setWriter(List<Integer> writer) {
	this.writer = writer;
}
}
