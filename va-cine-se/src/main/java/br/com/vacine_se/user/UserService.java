package br.com.vacine_se.user;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@EnableMapRepositories
public class UserService {
	private final CrudRepository<User, String> repository;

	public UserService(CrudRepository<User, String> repository) {
		this.repository = repository;
		this.repository.saveAll(defaultUsers());
	}

	
	private static List<User> defaultUsers(){
		return List.of(
				new User("Joao", 30 , 2L, false, "000.000.000-01", LocalDate.now(), "joao91", "j1991", "joao91@gmail.com", "2222-1111", 1L),
				new User("Maria", 31, 1L, true, "000.000.000-02", LocalDate.now(), "maria90", "m1990", "maria90@gmail.com", "1111-2222", 2L)
				);
	}

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        users.forEach(list::add);
        return list;
    }
	public Optional<User> find(String id) {
		return repository.findById(id);
	}
	
	public User create(User user) {
		User copy = new User(
				user.getName(),
				user.getAge(),
				user.getDistrictId(),
				user.isComorbidity(),
				user.getCpf(),
				user.getDateScheduled(),
				user.getUserName(),
				user.getPassword(),
				user.getEmail(),
				user.getPhoneNumber(),
				user.getSchedulingId()
				);
		return repository.save(copy);
	}
	
	public User update(String id, User newUser) {
		newUser.setId(id);
		return repository.save(newUser);
        // return repository.findById(id)
        //         .map(oldUser-> {
        //         	User updated = oldUser.updateWith(newUser);
        //            return repository.save(updated);
        //         });
    }
	
	public void delete(String id) {
		repository.deleteById(id);
	}
}
