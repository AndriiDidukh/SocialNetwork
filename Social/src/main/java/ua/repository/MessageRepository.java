package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {
	Message findByText(String text);

	default void delete(String text) {
		delete(findByText(text));
	}

	@Modifying
	@Query("DELETE FROM Message c WHERE c.text=:text")
	void deleteByText(@Param("text") String text);

	@Query("SELECT message FROM Message message WHERE message.reciverId.id =:id")
	List<Message> findByRecivers(@Param("id") int id);

	@Query("SELECT m FROM Message m WHERE (m.reciverId.id=:id1 AND m.senderId.id=:id2)or(m.reciverId.id=:id2 AND m.senderId.id=:id1) ORDER BY DATE DESC")
	List<Message> findMesseging(@Param("id1") int a, @Param("id2") int id);

	@Query("SELECT DISTINCT m.senderId FROM Message m WHERE m.reciverId.id=:id")
	List<Message> findByTexting(@Param("id") int a);
}
