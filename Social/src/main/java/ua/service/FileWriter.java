package ua.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder {
		USERPAGE, GROUPE
	}

	String write(Folder folder, MultipartFile file, int id);
}