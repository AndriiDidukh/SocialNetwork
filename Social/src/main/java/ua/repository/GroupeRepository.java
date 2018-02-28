package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Integer>, JpaSpecificationExecutor<Groupe> {
	Groupe findByName(String name);

	default void delete(String name) {
		delete(findByName(name));
	}

	@Modifying
	@Query("DELETE FROM Groupe c WHERE c.name=:name")
	void deleteByName(@Param("name") String name);
	
	@Query("SELECT groupe FROM Groupe groupe WHERE groupe.creator.id =:id")
	List<Groupe> findByCreator(@Param("id")int id);
}
