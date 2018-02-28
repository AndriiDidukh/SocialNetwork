package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.GroupesMembers;

public interface GroupeMemberRepository extends JpaRepository<GroupesMembers, Integer>, JpaSpecificationExecutor<GroupesMembers> {

	@Query(value = "SELECT gm FROM GroupesMembers gm WHERE gm.groupe.id=:id")
	List<GroupesMembers> findSubscribers(@Param("id") int id);

	@Query("SELECT gm FROM GroupesMembers gm WHERE gm.member.id=:id")
	List<GroupesMembers> findGroupes(@Param("id") int id);

	@Modifying
	@Transactional
	@Query("DELETE FROM GroupesMembers gm WHERE gm.groupe.id=:id AND gm.member.id=:id2")
	void unsubscribe(@Param("id") int id, @Param("id2") int id2);

	@Query("SELECT count(gm.id) FROM GroupesMembers gm WHERE gm.groupe.id=:id")
	Integer findCount(@Param("id") int id);

	@Query("SELECT gm FROM GroupesMembers gm WHERE gm.groupe.id=:id AND gm.member.id=:id2")
	GroupesMembers findByGroupAndMember(@Param("id") int id, @Param("id2") int id2);

}
