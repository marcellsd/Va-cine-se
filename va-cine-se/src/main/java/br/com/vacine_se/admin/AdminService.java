package br.com.vacine_se.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@EnableMapRepositories
public class AdminService {
	private final CrudRepository<Admin,Long> repository;

	public AdminService(CrudRepository<Admin, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(defaultAdmins());
	}

	
	private static List<Admin> defaultAdmins(){
		return List.of(
				new Admin(1L, "Jose", "jose89", "j1989", "jose89@gmail.com"),
				new Admin(2L, "Lourdes","lourdes40", "m1941", "lourdes41@gmail.com")
				);
	}

    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();
        Iterable<Admin> admins = repository.findAll();
        admins.forEach(list::add);
        return list;
    }
	public Optional<Admin> find(Long id) {
		return repository.findById(id);
	}
	
	public Admin create(Admin admin) {
		Admin copy = new Admin(
				admin.getId(),
				admin.getName(),
				admin.getUserName(),
				admin.getPassword(),
				admin.getEmail()
				);
		return repository.save(copy);
	}
	
	public Optional<Admin> update( Long id, Admin newAdmin) {
        return repository.findById(id)
                .map(oldAdmin-> {
                	Admin updated = oldAdmin.updateWith(newAdmin);
                   return repository.save(updated);
                });
    }
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
