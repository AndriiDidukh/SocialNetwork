package ua.service;

import java.security.Principal;
import java.util.List;

import ua.entity.GroupePost;
import ua.entity.GroupesMembers;

public interface GroupeMemberService {

	void addMember(GroupesMembers groupeMember, int id, Principal principal);

	List<GroupePost> findPostBySubscriber(Principal principal);

	List<GroupesMembers> findSubscribers(int id);

	List<GroupesMembers> findGroupes(Principal principal);

	void unsubscribe(int id, Principal principal);

	Integer findCount(int id);

}
