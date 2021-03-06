package br.com.vacine_se.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.vacine_se.InMemoryRepository;

@Repository
public class UserRepository implements InMemoryRepository<User> {

    private List<User> users = new ArrayList<>();
    private int userIdCount = 0;

    @Override
    public Optional<User> getById(int id) {
        return Optional.ofNullable(users.get(id));
    }
    
    @Override
    public Collection<User> getAll() {
        return users.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public User save(User user) {
        users.add(user);
        user.setId(this.userIdCount);
        this.userIdCount++;
        return user;
    }

    @Override
    public User update(User user) {
       return users.set(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.set(user.getId(), null);
    }
    
    public boolean usernameExists(String username){
    	for (User user : users) {
			if(user.getUsername().compareTo(username) == 0) {
				return true;
			}
    	}
    	return false;
    }
    
    public boolean cpfExists(String cpf){
    	for (User user : users) {
			if(user.getCpf().compareTo(cpf) == 0) {
				return true;
			}
    	}
    	return false;
    }
}
