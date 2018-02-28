package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String path;
	private int version;

	@Transient
	private MultipartFile file;

	@ManyToOne
	private UserPage creator;

	@OneToMany(mappedBy = "owner")
	private List<GroupePost> PostService = new ArrayList<>();

	@OneToMany(mappedBy = "groupe")
	private List<GroupesMembers> groupesMembers = new ArrayList<>();

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<GroupesMembers> getGroupesMembers() {
		return groupesMembers;
	}

	public void setGroupesMembers(List<GroupesMembers> groupesMembers) {
		this.groupesMembers = groupesMembers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserPage getCreator() {
		return creator;
	}

	public void setCreator(UserPage creator) {
		this.creator = creator;
	}

}
