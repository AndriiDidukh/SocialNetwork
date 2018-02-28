package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
	Post findByName(String name);

	default void delete(String name) {
		delete(findByName(name));
	}

	@Modifying
	@Query("DELETE FROM Post c WHERE c.name=:name")
	void deleteByName(@Param("name") String name);

	@Query("SELECT post FROM Post post WHERE post.owner.id =:id ORDER BY date DESC")
	List<Post> findByOwners(@Param("id") int id);

	@Query("SELECT post FROM Post post WHERE post.writer.id =:id")
	List<Post> findByWriters(@Param("id") int id);

	@Query("SELECT p FROM Post p WHERE p.writer.id =:id ORDER BY date DESC")
	List<Post> findMyPosts(@Param("id") int id);
}
