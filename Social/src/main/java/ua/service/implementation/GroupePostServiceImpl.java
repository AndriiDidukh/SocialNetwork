package ua.service.implementation;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Groupe;
import ua.entity.GroupePost;
import ua.entity.UserPage;
import ua.repository.GroupePostRepository;
import ua.repository.GroupeRepository;
import ua.repository.UserPageRepository;
import ua.service.GroupePostService;

@Service
@Transactional
public class GroupePostServiceImpl implements GroupePostService {

	@Autowired
	GroupePostRepository groupePostRepository;

	@Autowired
	UserPageRepository userPageRepository;

	@Autowired
	GroupeRepository groupeRepository;

	@Override
	public List<GroupePost> findByOwner(int id) {
		return groupePostRepository.findByOwner(id);
	}

	@Override
	public void savePost(GroupePost groupePost, Principal principal, int id) {
		GroupePost groupePost2 = new GroupePost();
		UserPage userPage = userPageRepository.findOne(Integer.valueOf(principal.getName()));
		Groupe groupe = groupeRepository.findOne(id);
		groupePost2.setDate(new Timestamp(new Date().getTime()));
		groupePost2.setWriter(userPage);
		groupePost2.setOwner(groupe);
		groupePost2.setText(groupePost.getText());
		groupePost2.setName(groupePost.getName());
		groupePostRepository.save(groupePost2);
	}

}
