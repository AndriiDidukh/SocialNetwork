package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Follower;

public interface FollowerRepository extends JpaRepository<Follower, Integer>, JpaSpecificationExecutor<Follower> {

	@Query("SELECT f FROM Follower f WHERE f.follower.id=:id AND f.followTo.id=:id2")
	Follower findFollower(@Param("id") int id, @Param("id2") int id2);

	@Query("SELECT f FROM Follower f WHERE (f.follower.id=:id AND f.followTo.id=:id2) OR (f.follower.id=:id2 AND f.followTo.id=:id)")
	Follower findFollower2(@Param("id") int id, @Param("id2") int id2);

	@Query("SELECT f FROM Follower f WHERE f.followTo.id=:id")
	List<Follower> findFollowers(@Param("id") int id);

	@Query("SELECT f FROM Follower f WHERE f.follower.id=:id")
	List<Follower> findFollowTo(@Param("id") int id);

	@Modifying
	@Transactional
	@Query("DELETE FROM Follower f WHERE f.followTo.id=:id")
	void deleteBy(@Param("id") int id);

	@Modifying
	@Transactional
	@Query("DELETE FROM Follower f WHERE f.followTo.id=:id AND f.follower.id=:id2")
	void deleteToAdd(@Param("id") int id, @Param("id2") int id2);

}
