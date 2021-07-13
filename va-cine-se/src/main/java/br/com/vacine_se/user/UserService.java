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
	private final CrudRepository<User,Long> repository;

	public UserService(CrudRepository<User, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(defaultUsers());
	}

	
	private static List<User> defaultUsers(){
		return List.of(
				new User(1L, "Joao", 2, 30, false, "000.000.000-01", LocalDate.now(), "joao91", "j1991", "joao91@gmail.com", "2222-1111", 1L),
				new User(2L, "Maria", 1, 31, true, "000.000.000-02", LocalDate.now(), "maria90", "m1990", "maria90@gmail.com", "1111-2222", 2L)
				);
	}

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        users.forEach(list::add);
        return list;
    }
	public Optional<User> find(Long id) {
		return repository.findById(id);
	}
	
	public User create(User user) {
		User copy = new User(
				user.getId(),
				user.getName(),
				user.getDistrictId(),
				user.getAge(),
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
	
	public Optional<User> update( Long id, User newUser) {
        return repository.findById(id)
                .map(oldItem -> {
                	User updated = oldItem.updateWith(newUser);
                   return repository.save(updated);
                });
    }
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
