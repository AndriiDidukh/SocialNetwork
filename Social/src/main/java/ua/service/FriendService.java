package ua.service;

import java.security.Principal;
import java.util.List;

import ua.entity.Friend;

public interface FriendService {

	void addFriend(Friend friend, Principal principal, int id);

	List<Friend> findFriend1(Principal principal);

	List<Friend> findFriend2(Principal principal);

	void deleteFriend(int id, Principal principal);

}
