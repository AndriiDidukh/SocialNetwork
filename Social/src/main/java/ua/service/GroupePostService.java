package ua.service;

import java.security.Principal;
import java.util.List;

import ua.entity.GroupePost;

public interface GroupePostService {

	List<GroupePost> findByOwner(int id);

	void savePost(GroupePost groupePost, Principal principal, int id);

}
