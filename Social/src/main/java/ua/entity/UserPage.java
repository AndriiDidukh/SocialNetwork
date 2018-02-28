package ua.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import ua.entity.enums.Country;
import ua.entity.enums.DayOfBirth;
import ua.entity.enums.Gender;
import ua.entity.enums.MounthOfBirth;
import ua.entity.enums.YearOfBirth;

@Entity
public class UserPage implements UserDetails {

	private static final long serialVersionUID = 1512198877402218090L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String login;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String phone;
	private String City;
	private String path;
	private int version;
	@Transient
	private MultipartFile file;
	@Enumerated
	private Role role;
	@Enumerated
	private Country country;
	@Enumerated
	private Gender gender;
	@Enumerated
	private DayOfBirth dayOfBirth;
	@Enumerated
	private MounthOfBirth mounthOfBirth;
	@Enumerated
	private YearOfBirth yearOfBirth;

	@OneToMany(mappedBy = "writer")
	private List<GroupePost> groupePosts = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<GroupesMembers> members = new ArrayList<>();

	@OneToMany(mappedBy = "friend")
	private List<UserPage> friend = new ArrayList<>();

	@OneToMany(mappedBy = "friend2")
	private List<UserPage> friend2 = new ArrayList<>();

	@OneToMany(mappedBy = "followTo")
	private List<UserPage> followTo = new ArrayList<>();

	@OneToMany(mappedBy = "follower")
	private List<UserPage> follower = new ArrayList<>();

	@OneToMany(mappedBy = "senderId")
	private List<Message> sended = new ArrayList<>();

	@OneToMany(mappedBy = "reciverId")
	private List<Message> reciver = new ArrayList<>();

	@OneToMany(mappedBy = "owner")
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "creator")
	private List<Groupe> groupes = new ArrayList<>();

	@OneToMany(mappedBy = "writer")
	private List<Post> writedPosts = new ArrayList<>();

	public String getPath() {
		return path;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public DayOfBirth getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(DayOfBirth dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public MounthOfBirth getMounthOfBirth() {
		return mounthOfBirth;
	}

	public void setMounthOfBirth(MounthOfBirth mounthOfBirth) {
		this.mounthOfBirth = mounthOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<GroupePost> getGroupePosts() {
		return groupePosts;
	}

	public void setGroupePosts(List<GroupePost> groupePosts) {
		this.groupePosts = groupePosts;
	}

	public List<UserPage> getFriend() {
		return friend;
	}

	public YearOfBirth getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(YearOfBirth yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public void setFriend(List<UserPage> friend) {
		this.friend = friend;
	}

	public List<UserPage> getFriend2() {
		return friend2;
	}

	public void setFriend2(List<UserPage> friend2) {
		this.friend2 = friend2;
	}

	public List<UserPage> getFollowTo() {
		return followTo;
	}

	public void setFollowTo(List<UserPage> followTo) {
		this.followTo = followTo;
	}

	public List<UserPage> getFollower() {
		return follower;
	}

	public void setFollower(List<UserPage> follower) {
		this.follower = follower;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<GroupesMembers> getMembers() {
		return members;
	}

	public void setMembers(List<GroupesMembers> members) {
		this.members = members;
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public List<Post> getWritedPosts() {
		return writedPosts;
	}

	public void setWritedPosts(List<Post> writedPosts) {
		this.writedPosts = writedPosts;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public List<Message> getSended() {
		return sended;
	}

	public void setSended(List<Message> sended) {
		this.sended = sended;
	}

	public List<Message> getReciver() {
		return reciver;
	}

	public void setReciver(List<Message> reciver) {
		this.reciver = reciver;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.name()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return String.valueOf(id);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
