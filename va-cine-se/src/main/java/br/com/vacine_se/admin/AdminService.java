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
	private final CrudRepository<Admin, String> repository;

	public AdminService(CrudRepository<Admin, String> repository) {
		this.repository = repository;
		this.repository.saveAll(defaultAdmins());
	}

	
	private static List<Admin> defaultAdmins(){
		return List.of(
				new Admin("Jose", "jose89", "j1989", "jose89@gmail.com"),
				new Admin("Lourdes","lourdes40", "m1941", "lourdes41@gmail.com")
				);
	}

    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();
        Iterable<Admin> admins = repository.findAll();
        admins.forEach(list::add);
        return list;
    }
	public Optional<Admin> find(String id) {
		return repository.findById(id);
	}
	
	public Admin create(Admin admin) {
		Admin copy = new Admin(
				admin.getName(),
				admin.getUserName(),
				admin.getPassword(),
				admin.getEmail()
				);
		return repository.save(copy);
	}
	public Admin update( String id, Admin newAdmin) {
		newAdmin.setId(id);
		return newAdmin;
	}
	
	/*
	public Optional<Admin> update( String id, Admin newAdmin) {
        return repository.findById(id)
                .map(oldAdmin-> {
                	Admin updated = oldAdmin.updateWith(newAdmin);
                   return repository.save(updated);
                });
    }
    */
	public void delete(String id) {
		repository.deleteById(id);
	}
}
