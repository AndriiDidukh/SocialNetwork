package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Follower;
import ua.entity.Friend;
import ua.entity.UserPage;
import ua.repository.FollowerRepository;
import ua.repository.FriendRepository;
import ua.repository.UserPageRepository;
import ua.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	UserPageRepository userPageRepository;

	@Autowired
	FriendRepository friendRepository;

	@Autowired
	FollowerRepository followerRepository;

	@Override
	public void addFriend(Friend friend, Principal principal, int id) {
		Friend friend2 = new Friend();
		UserPage userPage = userPageRepository.findOne(Integer.valueOf(principal.getName()));
		UserPage userPage2 = userPageRepository.findOne(id);
		friend2.setFriend(userPage);
		friend2.setFriend2(userPage2);
		friendRepository.save(friend2);
		followerRepository.deleteToAdd(Integer.valueOf(principal.getName()), id);
	}

	@Override
	public List<Friend> findFriend1(Principal principal) {
		return friendRepository.findFriend1(Integer.valueOf(principal.getName()));
	}

	@Override
	public List<Friend> findFriend2(Principal principal) {
		return friendRepository.findFriend2(Integer.valueOf(principal.getName()));
	}

	@Override
	public void deleteFriend(int id, Principal principal) {
		friendRepository.deleteFriend(id, Integer.valueOf(principal.getName()));
		Follower follower = new Follower();
		UserPage userPage = userPageRepository.findOne(id);
		UserPage userPage2 = userPageRepository.findOne(Integer.valueOf(principal.getName()));
		follower.setFollowTo(userPage2);
		follower.setFollower(userPage);
		followerRepository.save(follower);
	}
}
