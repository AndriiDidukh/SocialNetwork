package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Post;
import ua.service.PostService;

public class PostEditor extends PropertyEditorSupport{
private final PostService postService;
	
	public PostEditor(PostService postService){
		this.postService = postService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Post post = postService.findOne(Integer.valueOf(text));
		setValue(post);
	}
}
