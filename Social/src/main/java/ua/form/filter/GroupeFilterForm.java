package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class GroupeFilterForm {
private String name="";

private String description="";

private List<Integer> creator = new ArrayList<>();

private List<Integer>posts = new ArrayList<>();

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public List<Integer> getCreator() {
	return creator;
}

public void setCreator(List<Integer> creator) {
	this.creator = creator;
}

public List<Integer> getPosts() {
	return posts;
}

public void setPosts(List<Integer> posts) {
	this.posts = posts;
}




}
