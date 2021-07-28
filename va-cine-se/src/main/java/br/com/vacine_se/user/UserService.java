package br.com.vacine_se.user;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository repository;

	private static List<User> defaultUsers(){
		return List.of(
				new User("Joao", 30 , 2L, false, "000.000.000-01", LocalDate.now(), "joao91", "j1991", "joao91@gmail.com", "2222-1111", 1L),
				new User("Maria", 31, 1L, true, "000.000.000-02", LocalDate.now(), "maria90", "m1990", "maria90@gmail.com", "1111-2222", 2L)
				);
	}

	public UserService(UserRepository repository) {
		this.repository = repository;
		for (User user : defaultUsers()) {
			this.repository.save(user);
		}
	}
	
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.getAll();
        users.forEach(list::add);
        return list;
    }

	public Optional<User> find(int id) {
		return repository.getById(id);
	}
	
	public User create(User user) {
		return repository.save(user);
	}
	
	public User update(int id, User newUser) {
		User user = this.find(id).orElseThrow();
		newUser.setId(user.getId());
		return repository.update(newUser);
      }
	
	public void delete(int id) {
		User user = this.find(id).orElseThrow();
		repository.delete(user);
	}
}
