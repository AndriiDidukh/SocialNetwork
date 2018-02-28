package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Role;
import ua.entity.UserPage;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;
import ua.repository.UserPageRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.UserPageService;
import ua.service.implementation.specification.UserPageFilterAdapter;

@Service("userDetailsService")
@Transactional
public class UserPageServiceImpl implements UserPageService, UserDetailsService {
	@Resource
	private UserPageRepository userPageRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private FileWriter fileWriter;

	@Override
	public List<UserPage> findAll() {
		return userPageRepository.findAll();
	}

	@Override
	public void delete(int id) {
		userPageRepository.delete(id);
	}

	@Override
	public void save(UserPageForm form) {

		UserPage userPage = new UserPage();
		userPage.setLogin(form.getLogin());
		userPage.setRole(Role.ROLE_USER);
		userPage.setEmail(form.getEmail());
		userPage.setName(form.getName());
		userPage.setSurname(form.getSurname());
		userPage.setPhone(form.getPhone());
		userPage.setPassword(encoder.encode(form.getPassword()));
		userPage.setEmail(form.getEmail());
		userPage.setCity(form.getCity());
		userPage.setCountry(form.getCountry());
		userPage.setGender(form.getGender());
		userPage.setDayOfBirth(form.getDayOfBirth());
		userPage.setMounthOfBirth(form.getMounthOfBirth());
		userPage.setYearOfBirth(form.getYearOfBirth());
		userPage.setPath(form.getPath());
		userPage.setVersion(form.getVersion());
		userPageRepository.saveAndFlush(userPage);

		String extension = fileWriter.write(Folder.USERPAGE, form.getFile(), userPage.getId());
		if (extension != null) {
			userPage.setVersion(form.getVersion() + 1);
			userPage.setPath(extension);
			userPageRepository.save(userPage);
		}
	}

	@Override
	public UserPageForm findForForm(int id) {
		UserPage userPage = userPageRepository.findOne(id);
		UserPageForm form = new UserPageForm();
		form.setId(userPage.getId());
		form.setLogin(userPage.getLogin());
		form.setName(userPage.getName());
		form.setEmail(userPage.getEmail());
		form.setSurname(userPage.getSurname());
		form.setPhone(userPage.getPhone());
		form.setPassword(userPage.getPassword());
		form.setCity(userPage.getCity());
		form.setCountry(userPage.getCountry());
		form.setGender(userPage.getGender());
		form.setDayOfBirth(userPage.getDayOfBirth());
		form.setMounthOfBirth(userPage.getMounthOfBirth());
		form.setYearOfBirth(userPage.getYearOfBirth());
		return form;
	}

	@Override
	public UserPage findOne(int id) {
		return userPageRepository.findOne(id);
	}

	@Override
	public Page<UserPage> findAll(Pageable pageable, UserPageFilterForm filter) {
		return userPageRepository.findAll(new UserPageFilterAdapter(filter), pageable);
	}

	@Override
	public UserPage findByLogin(String login) {
		return userPageRepository.findByLogin(login);
	}

	@Override
	public UserPage findById(int id) {
		return userPageRepository.findOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return userPageRepository.findByLogin(login);
	}

	@PostConstruct
	public void saveAdmin() {
		UserPage user = userPageRepository.findOne(1);
		if (user == null) {
			user = new UserPage();
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("admin"));
			user.setLogin("admin");
			user.setId(1);
			user.setName("admin");
			user.setSurname("admin");
			userPageRepository.save(user);
		}
	}

	@Override
	public UserPage findByPrincipal(Principal principal) {
		try {
			return userPageRepository.findOneCountryInited(Integer.valueOf(principal.getName()));
		} catch (Exception e) {
		}
		return null;
	}

}
