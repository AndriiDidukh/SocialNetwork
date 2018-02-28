package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Message;
import ua.form.filter.MessageFilterForm;

public interface MessageService {
	void save(Message message, Principal principal);

	void save(String text, int senderId, int reciverId);

	Message findByText(String text);

	void delete(String text);

	List<Message> findAll();

	void delete(int id);

	Page<Message> findAll(Pageable pageable);

	Message findOne(int id);

	Page<Message> findAll(Pageable pageable, MessageFilterForm filter);

	List<Message> findByReciver(Principal principal);

	List<Message> findTexting(Principal principal, int id);

	void saveTexting(int id, Principal principal, Message message);

	List<Message> findByTexting(Principal principal);

}
