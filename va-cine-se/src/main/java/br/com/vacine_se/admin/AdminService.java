package br.com.vacine_se.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private AdminRepository repository;

	public AdminService(AdminRepository repository) {
		this.repository = repository;
	}
	
    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();
        Iterable<Admin> admins = repository.getAll();
        admins.forEach(list::add);
        return list;
    }
	public Optional<Admin> find(int id) {
		return repository.getById(id);
	}
	
	public Admin create(Admin admin) {
		return repository.save(admin);
	}
	public Admin update(int id, Admin newAdmin) {
		Admin admin = this.find(id).orElseThrow();
		newAdmin.setId(admin.getId());
		return repository.update(newAdmin);
	}
	
	public void delete(int id) {
		Admin admin = this.find(id).orElseThrow();
		repository.delete(admin);
	}
}
