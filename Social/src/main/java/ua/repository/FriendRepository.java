package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer>, JpaSpecificationExecutor<Friend> {

	@Query("SELECT f FROM Friend f WHERE f.friend.id =:id")
	List<Friend> findFriend1(@Param("id") int id);

	@Query("SELECT f FROM Friend f WHERE f.friend2.id =:id")
	List<Friend> findFriend2(@Param("id") int id);

	@Query("SELECT f FROM Friend f WHERE (f.friend.id =:id AND f.friend2.id =:id2) OR (f.friend.id =:id2 AND f.friend2.id =:id)")
	List<Friend> findFriend(@Param("id") int id, @Param("id2") int id2);

	@Query("SELECT f FROM Friend f WHERE (f.friend.id =:id AND f.friend2.id =:id2) OR (f.friend.id =:id2 AND f.friend2.id =:id)")
	Friend findFriend5(@Param("id") int id, @Param("id2") int id2);

	@Modifying
	@Transactional
	@Query("DELETE FROM Friend f WHERE (f.friend.id =:id AND f.friend2.id =:id2) OR (f.friend.id =:id2 AND f.friend2.id =:id)")
	void deleteFriend(@Param("id") int id, @Param("id2") int id2);

}
