package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.GroupePost;

public interface GroupePostRepository extends JpaRepository<GroupePost, Integer>, JpaSpecificationExecutor<GroupePost> {

	@Query("SELECT gp FROM GroupePost gp WHERE gp.owner.id =:id ORDER BY date DESC")
	List<GroupePost> findByOwner(@Param("id") int id);

	@Query("SELECT gp FROM GroupePost gp WHERE gp.owner.id IN(SELECT gm.groupe.id FROM GroupesMembers gm WHERE gm.member.id=:id)")
	List<GroupePost> findPostBySubscribe(@Param("id") int id);

}
