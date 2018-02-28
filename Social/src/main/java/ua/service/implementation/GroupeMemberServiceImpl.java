package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Groupe;
import ua.entity.GroupePost;
import ua.entity.GroupesMembers;
import ua.entity.UserPage;
import ua.repository.GroupeMemberRepository;
import ua.repository.GroupePostRepository;
import ua.repository.GroupeRepository;
import ua.repository.UserPageRepository;
import ua.service.GroupeMemberService;

@Service
public class GroupeMemberServiceImpl implements GroupeMemberService {

	@Autowired
	GroupeMemberRepository groupeMemberRepository;

	@Autowired
	GroupeRepository groupeRepository;

	@Autowired
	UserPageRepository userPageRepository;

	@Autowired
	GroupePostRepository groupePostRepository;

	@Override
	public void addMember(GroupesMembers groupeMember, int id, Principal principal) {
		if (groupeMemberRepository.findByGroupAndMember(id, Integer.valueOf(principal.getName())) == null) {
			GroupesMembers groupesMember1 = new GroupesMembers();
			Groupe groupe = groupeRepository.findOne(id);
			UserPage userPage = userPageRepository.getOne(Integer.valueOf(principal.getName()));
			groupesMember1.setGroupe(groupe);
			groupesMember1.setMember(userPage);
			groupeMemberRepository.save(groupesMember1);
		}
	}

	@Override
	public List<GroupePost> findPostBySubscriber(Principal principal) {
		return groupePostRepository.findPostBySubscribe(Integer.valueOf(principal.getName()));
	}

	@Override
	public List<GroupesMembers> findSubscribers(int id) {
		return groupeMemberRepository.findSubscribers(id);
	}

	@Override
	public List<GroupesMembers> findGroupes(Principal principal) {
		return groupeMemberRepository.findGroupes(Integer.valueOf(principal.getName()));
	}

	@Override
	public void unsubscribe(int id, Principal principal) {
		groupeMemberRepository.unsubscribe(id, Integer.valueOf(principal.getName()));

	}

	@Override
	public Integer findCount(int id) {
		return groupeMemberRepository.findCount(id);
	}
}
