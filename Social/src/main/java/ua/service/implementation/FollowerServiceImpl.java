package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Follower;
import ua.entity.UserPage;
import ua.repository.FollowerRepository;
import ua.repository.FriendRepository;
import ua.repository.UserPageRepository;
import ua.service.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService {

	@Autowired
	UserPageRepository userPageRepository;

	@Autowired
	FollowerRepository followerRepository;

	@Autowired
	FriendRepository friendRepository;

	@Override
	public void saveFollower(Follower follow, Principal principal, int id) {
		int a = Integer.valueOf(principal.getName());
		System.out.println(followerRepository.findFollower(Integer.valueOf(principal.getName()), id));
		if (a != id) {
			if (followerRepository.findFollower2(Integer.valueOf(principal.getName()), id) == null && friendRepository.findFriend5(Integer.valueOf(principal.getName()), id) == null) {

				Follower follower = new Follower();
				UserPage userPage = userPageRepository.findOne(Integer.valueOf(principal.getName()));
				UserPage userPage2 = userPageRepository.findOne(id);
				follower.setFollower(userPage);
				follower.setFollowTo(userPage2);
				followerRepository.save(follower);
			}
		}
	}

	@Override
	public boolean findFollower(int id, int id2) {
		if (followerRepository.findFollower(id, id2) != null)
			return true;
		return false;
	}

	@Override
	public List<Follower> findFolowers(Principal principal) {
		return followerRepository.findFollowers(Integer.valueOf(principal.getName()));
	}

	@Override
	public List<Follower> findFolowTo(Principal principal) {
		return followerRepository.findFollowTo(Integer.valueOf(principal.getName()));
	}

	@Override
	public void deleteById(int id) {
		followerRepository.deleteBy(id);
	}

	@Override
	public void deleteFolower(int id, Principal principal) {
		followerRepository.deleteToAdd(id, Integer.valueOf(principal.getName()));
	}

}
