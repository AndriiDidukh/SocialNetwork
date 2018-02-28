package ua.service;

import java.security.Principal;
import java.util.List;

import ua.entity.Follower;

public interface FollowerService {

	void saveFollower(Follower follow, Principal principal, int id);

	boolean findFollower(int id, int id2);

	List<Follower> findFolowers(Principal principal);

	List<Follower> findFolowTo(Principal principal);

	void deleteById(int id);

	void deleteFolower(int id, Principal principal);

}
