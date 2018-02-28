package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Groupe;
import ua.entity.UserPage;
import ua.form.GroupeForm;
import ua.form.filter.GroupeFilterForm;
import ua.repository.GroupeRepository;
import ua.repository.UserPageRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.GroupeService;
import ua.service.implementation.specification.GroupeFilterAdapter;

@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {

	@Autowired
	private GroupeRepository groupeRepository;

	@Autowired
	private UserPageRepository userPageRepository;

	@Autowired
	private FileWriter fileWriter;

	@Override
	public Groupe findByName(String name) {
		return groupeRepository.findByName(name);
	}

	@Override
	public void delete(String name) {
		groupeRepository.delete(name);
	}

	@Override
	public List<Groupe> findAll() {
		return groupeRepository.findAll();
	}

	@Override
	public void delete(int id) {
		groupeRepository.delete(id);
	}

	@Override
	public Groupe findOne(int id) {
		return groupeRepository.findOne(id);
	}

	@Override
	public Page<Groupe> findAll(Pageable pageable, GroupeFilterForm form) {
		return groupeRepository.findAll(new GroupeFilterAdapter(form), pageable);
	}

	@Override
	public void save(Groupe groupe) {
		groupeRepository.save(groupe);
	}

	@Override
	public void saveForm(GroupeForm form, Principal principal) {
		Groupe groupe = new Groupe();
		int a = Integer.valueOf(principal.getName());
		UserPage userPage = userPageRepository.findOne(a);
		groupe.setName(form.getName());
		groupe.setDescription(form.getDescription());
		groupe.setCreator(userPage);
		groupe.setPath(form.getPath());
		groupe.setVersion(form.getVersion());
		groupeRepository.saveAndFlush(groupe);

		String extension = fileWriter.write(Folder.GROUPE, form.getFile(), groupe.getId());
		if (extension != null) {
			groupe.setVersion(form.getVersion() + 1);
			groupe.setPath(extension);
			groupeRepository.save(groupe);
		}
	}

	@Override
	public List<Groupe> findByCreators(Principal principal) {
		try {
			return groupeRepository.findByCreator(Integer.valueOf(principal.getName()));
		} catch (Exception e) {
		}
		return null;

	}

}
